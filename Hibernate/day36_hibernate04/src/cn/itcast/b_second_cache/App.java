package cn.itcast.b_second_cache;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

//二级缓存，要从主配置文件中开启二级缓存啊
public class App {
	//创建session的工厂
	private static SessionFactory sf;
	
	// 初始化session的工厂
	static{
		//通过配置管理器对象，加载主配置文件，加载映射文件，并且创建session工厂的对象
		
		sf = new Configuration()	
				.configure() //加载主配置文件
				.addClass(Dept.class) //加载Dept.hbm.xml文件
				.addClass(Employee.class)//加载Employee.hbm.xml文件
				.buildSessionFactory();
	}
	
	@Test
	public void testSave() throws Exception {
		Session session = sf.openSession();
		session.beginTransaction();
		
		Dept dept = new Dept();
		dept.setDeptName("后端开发发部");
		
		Employee emp1= new Employee();
		emp1.setName("刘意");
		Employee emp2 = new Employee();
		emp2.setName("李沐");
		
		//关系：
		//因为是多对一的配置，所以通过多的一方来维护关系
		emp1.setDept(dept);
		emp2.setDept(dept);
		
		//保存
		session.save(dept);
		session.save(emp1);
		session.save(emp2);
		
		//提交事务，并关闭session
		session.getTransaction().commit();
		session.close();
	}
	
	
	// 不同的Session，能否共享一级缓存数据！不能的。但不同的session，却可以共享二级缓存
	//主键查询
	@Test
	public void test_session_cache() {
		Dept dept=null;
		/**
		 * Session1:
		 */
		Session session1 = sf.openSession();
		session1.beginTransaction();
		//先查询  主键查询
		dept=(Dept) session1.get(Dept.class, 1);
		System.out.println(dept.getEmployees());//[cn.itcast.b_second_cache.Employee@d1e43ed, cn.itcast.b_second_cache.Employee@50bb85b4]

		
		session1.getTransaction().commit(); 
		session1.close();
		
		System.out.println("================");

		/**
		 * Session2:
		 */
		Session session2 = sf.openSession();
		session2.beginTransaction();
		//又查询
		dept=(Dept) session2.get(Dept.class, 1);
		//dept.setDeptName("财务部");
		System.out.println(dept.getEmployees());//没有发送sql，说明从二级缓存获取数据成功
		//[cn.itcast.b_second_cache.Employee@60021a1, cn.itcast.b_second_cache.Employee@4e3916ae]

		
		session2.getTransaction().commit();   
		session2.close();
		
		/**
		 * 结果：
		 * Hibernate: select dept0_.id as id0_0_, dept0_.deptName as deptName0_0_ from t_dept dept0_ where dept0_.id=?
			Hibernate: select employees0_.dept_id as dept4_0_1_, employees0_.id as id1_, employees0_.id as id1_0_, employees0_.name as name1_0_, employees0_.salary as salary1_0_, employees0_.dept_id as dept4_1_0_ from t_employee employees0_ where employees0_.dept_id=?
			[cn.itcast.b_second_cache.Employee@6c759865, cn.itcast.b_second_cache.Employee@3f7e18e]
			================
			[cn.itcast.b_second_cache.Employee@1cb4ccc5, cn.itcast.b_second_cache.Employee@4d9d761a]
		 */
	}
	
	
	
	//hql 查询缓存
	//List()查询，不会从一级缓存获取？那二级缓存呢？
	//==》不会从sessin缓存(一级缓存)获取数据
	//==》默认不会从二级缓存获取数据，但可以指定从二级缓存获取数据
	@Test
	public void test_Query_cache() throws Exception {
		Dept dept = null;
		/**
		 * session1:
		 */
		Session session1 = sf.openSession();
		session1.beginTransaction();
		
		//hql查询
		Query q = session1.createQuery("from Dept");
		q.list();
		
		//提交事务，关闭session
		session1.getTransaction().commit();
		session1.close();
		
		System.out.println("========");
		
		/**
		 * session2:
		 */
		Session session2 = sf.openSession();
		session2.beginTransaction();
		
		//hql查询  让list查询指定从二级缓存获取数据
		q = session2.createQuery("from Dept").setCacheable(true);//放入二级缓存或者从二级缓存中获取   
		q.list();
		
		
		//提交事务，并关闭session
		session2.getTransaction().commit();
		session2.close();
	}
	
	
	
	/**
	 * 更新数据，会不会通知一级缓存、二级缓存？
	 * 		不会通知一级缓存,会通知二级缓存。(即也会把更新后的数据放入二级缓存中)
	 */
	@Test
	public void test_demo() throws Exception {
		Dept dept = null;
		/**
		 * session1:
		 */
		Session session1 = sf.openSession();
		session1.beginTransaction();
		
		//1、对象加入session缓存
		dept = (Dept) session1.get(Dept.class, 1);
		
		//2、修改
		session1.createQuery("update Dept set DeptName=? where id=?")
			.setParameter(0, "HR")
			.setParameter(1, 3)
			.executeUpdate();//执行更新
		
		dept = (Dept)session1.get(Dept.class, 3);
		System.out.println(dept.getDeptName());
		
		session1.getTransaction().commit();
		session1.close();
		/**
		 * Hibernate: select dept0_.id as id0_0_, dept0_.deptName as deptName0_0_ from t_dept dept0_ where dept0_.id=?
			Hibernate: update t_dept set DeptName=? where id=?
			Hibernate: select dept0_.id as id0_0_, dept0_.deptName as deptName0_0_ from t_dept dept0_ where dept0_.id=?
		 */
	}
	
	/**
	 * 更新数据，会不会通知一级缓存、二级缓存？
	 * 		不会通知一级缓存。
	 * 		更新数据：二级缓存会监测到最新的结果
	 * 		更改数据，会通知二级缓存。
	 * @throws Exception
	 */
	@Test
	public void test_demo2() throws Exception {
		Dept dept = null;
		/**
		 * session1:
		 */
		Session session1 = sf.openSession();
		session1.beginTransaction();
		
		//1、对象加入session缓存
		dept = (Dept)session1.get(Dept.class, 3);
		
		//2、修改
		session1.createQuery("update Dept set deptName=? where id=?")
					.setParameter(0, "HR")
					.setParameter(1, 3)
					.executeUpdate(); //执行更新
		
		session1.getTransaction().commit();
		session1.close();
		
		System.out.println("===================");

		/******另外的session******/
		Session session2 = sf.openSession();
		session2.beginTransaction();
		
		dept = (Dept)session2.get(Dept.class, 3);
		System.out.println(dept.getDeptName());
		
		session2.getTransaction().commit();
		session2.close();
		
	}

}
