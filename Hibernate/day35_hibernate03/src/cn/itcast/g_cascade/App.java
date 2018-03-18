package cn.itcast.g_cascade;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;


public class App {
	//创建session的工厂
	private static SessionFactory sf;
	//初始化session的工厂，通过静态代码块，因为静态代码块只加载一次
	static{
		//通过配置管理器对象加载主配置文件，创建session工厂对象
		sf = new Configuration() //创建配置管理器对象
				.configure() //加载主配置文件
				.addClass(Dept.class) //加载映射文件
				.addClass(Employee.class) //加载映射文件
				.buildSessionFactory();//创建session的工厂对象
	}
	
	//A、保存
	@Test
	public void testSave() throws Exception {
		//创建session对象，通过session的工厂来创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		//保存的时候，最好通过多的一方维护关联关系，这样可以少产生sql语句
		
		//部门
		Dept dept = new Dept();
		dept.setDeptName("应用开发部");
		
		//员工 第一个员工
		Employee emp1 = new Employee();
		emp1.setName("王晓杰");
		
		//员工 第二个员工
		Employee emp2 = new Employee();
		emp2.setName("赵欢");
		
		
		
		/**
		 * 关系，通过一的一方维护
		 */
		dept.getEmployees().add(emp1);
		dept.getEmployees().add(emp2);
		
		//保存
		session.save(dept);
		/**
		 *  cascade="save-update" 设置后，保存对象，会保存关联的对象！
		 *  		,即使不执行下面两条注释掉的代码都可以保存关联的对象
		 */
		
		//session.save(emp1);
		//session.save(emp2);//保存的时候，会保存关联的对象
		
		/**
		 * 使用级联操作的时候，如果是用部门(一的一方)来维护关系，
		 * 			则保存的对象应该为部门(一的一方)对象。
		 * 			如果是用员工(多的一方)来维护关系，则保存的对象应该为员工(多的一方)对象。				
		 */
		//提交事务
		tx.commit();
		//关闭session
		session.close();
	}
	
	
	//B、通过其中一方获取另外一方
	@Test
	public void testGet() throws Exception {
		//创建session对象，通过session的工厂来创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		//主键查询
		Dept dept = (Dept) session.get(Dept.class, 2);
		System.out.println(dept.getDeptName());//应用开发部
		System.out.println(dept.getEmployees());//[cn.itcast.a_one2many.Employee@574110a9, cn.itcast.a_one2many.Employee@3cebbff7]

		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
	}
	
	
	//C：解除关系
	@Test
	public void test_release() throws Exception {
		//创建session对象，通过session的工厂来创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		//第一种方式解除关系
		//主键查询
		//Dept dept = (Dept) session.get(Dept.class, 3);
		//解除关系  把外键清空  解除部门和员工关系
		//dept.getEmployees().clear();
//		session.update(dept);//这行写不写都一样
		
		/**
		 * 方式2解除关系(解除员工和部门的关系)
		 */
		Employee emp = (Employee) session.get(Employee.class, 2);
		//解除员工与部门的关系
		emp.setDept(null);
		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
	}
	
	
	//D：级联删除，就是不用考虑有没有外键约束都可以直接删除你要删除的数据
	@Test
	public void back() throws Exception {
		//创建session对象，通过session的工厂来创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		//主键查询
		Dept dept = (Dept) session.get(Dept.class, 1);
		//删除部门
		session.delete(dept);
		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
	}
	 


	
	
}