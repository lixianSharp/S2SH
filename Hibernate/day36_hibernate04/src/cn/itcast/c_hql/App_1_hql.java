package cn.itcast.c_hql;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;
/**
 * hql语法基本与数据库查询语法一致！
	支持：模糊、条件、分组、筛选、连接等！
   HQL查询：
		要有一定的sql基础！

 * @author 贤元
 *
 */
public class App_1_hql {
	//创建session的工厂
	private static SessionFactory sf;
	//初始化session的工厂  通过静态代码块来初始化，因为静态代码块只加载一次
	static{
		//通过配置管理器对象加载主配置文件，加载映射文件，并且创建session的工厂对象
		sf = new Configuration()
				.configure()
				.addClass(Dept.class)
				.addClass(Employee.class)
				.buildSessionFactory();
	}
	
	//1.1 统计每个部门人数   这个不需要用到二级缓存，可以把二级缓存的配置给注释了
	@Test
	public void test_demo1() throws Exception {
		//创建session,通过session的工厂来创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		Query q = session.createQuery("select e.dept,count(*) from Employee e group by e.dept having count(*)>=1");
		List<Object[]> list = q.list(); //返回的每一行记录封装在Object[]数据中
										//数据中第一个元素：dept对象
										//数据中第二个元素：统计的记录结果
		
		for(int i = 0;i<list.size();i++){
			Object[] values = list.get(i);
			//获取数组第一个元素
			Dept d = (Dept)values[0];
			//测试
			System.out.println(d.getDeptName()+"--"+values[1]);
		}
		/**
		 * 结果：
		 * 财务部--2
			后端开发发部--2
			HR--2
		 */
		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
	}
	
	//2、统计部门及部门下的员工
	@Test
	public void test_demo2() throws Exception {
		//创建session,通过session的工厂来创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		//内连接
		Query q = session.createQuery("from Employee e inner join e.dept");//多对一
		//或者
		//Query q = session.createQuery("from Dept d inner join d.employees");//一定要配一对多
		//左外连接
		//Query q = session.createQuery("from Employee e left join e.dept");//多对一
		//右外连接
		//Query q = session.createQuery("from Dept d right join d.employees");//多对一
		
		List<Object[]> list = q.list();
		//迭代：显示部门名称与员工名称
		for(int i = 0;i<list.size();i++){
			Object[] values = list.get(i);
			//获取数组第一个元素
			Employee e = (Employee)values[0];
			//获取数组第二个元素
			Dept d = (Dept)values[1];
			//测试
			System.out.println(e.getName()+","+d.getDeptName());
		}
		/**
		 * 刘意,财务部
			李沐,财务部
			刘意,后端开发发部
			李沐,后端开发发部
			刘意,HR
			李沐,HR
		 */
		
		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
	}
	
	
	//3、迫切关联查询，始终会把"右表"数据填充到"左表"中！
	@Test
	public void test_demo3() throws Exception {
		//创建session,通过session的工厂来创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		//迫切内连接，会自动封装数据
		Query q = session.createQuery("from Employee e inner join fetch e.dept");
		q.list();
		//System.out.println(q.list());//[cn.itcast.c_hql.Employee@65cb5512, cn.itcast.c_hql.Employee@55fd422c, cn.itcast.c_hql.Employee@3d24ffba, cn.itcast.c_hql.Employee@5f4bd814, cn.itcast.c_hql.Employee@6c5739ec, cn.itcast.c_hql.Employee@7ac53a21]

		//迫切左外连接
		q = session.createQuery("from Employee e left join fetch e.dept");
		q.list();
		//.out.println(q.list());//[cn.itcast.c_hql.Employee@83e44f4, cn.itcast.c_hql.Employee@660382ce, cn.itcast.c_hql.Employee@48112bb2, cn.itcast.c_hql.Employee@6c5739ec, cn.itcast.c_hql.Employee@529bec85, cn.itcast.c_hql.Employee@2a6b8b7f]
		
		
		/**
		 * 如何解决jsp页面访问懒加载报错问题？如：显示员工，同时显示员工关联的部门名称！
		 * 		连接查询！
		 * 
		 * 懒加载：
		 * 	1）先用一下数据，回显查询出来
		 *  2）强迫代理对象初始化
		 *  3）session不关闭
		 *  4）需要哪些数据，先查询出来！---》hql连接查询
		 * 
		 * 总结：
		 * 	  要在session关闭前，把懒加载的数据拿出来！
		 * 
		 * 项目中一般很少用懒加载数据
		 */
		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
	}
	
	
	@Test
	public void test_demo4() throws Exception {
		//创建session,通过session的工厂来创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		

		//N+1
		//显示员工，以及员工的部门
		Query q = session.createQuery("from Employee");
		List<Employee> list = q.list();
		for(Employee e:list){
			System.out.println(e.getName()+","+e.getDept().getDeptName());
		}
		
		System.out.println("=======");
		
		//N+1,如何解决？用连接查询解决
		q = session.createQuery("from Employee e left join fetch e.dept");
		list = q.list();
		for(Employee e:list){
			System.out.println(e.getName()+","+e.getDept().getDeptName());
		}
		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
	}
	
}
