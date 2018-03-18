package cn.itcast.a_status;


import static org.junit.Assert.*;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * 目的：Hibernate对象状态
 * @author 贤元
 *
 */
/**
 Hibernate对象状态
	(一)	临时状态
		1. 直接new出来的对象
		2. 不处于session的管理(即没有与任何一个session关联)
		3. 对象在数据库中没有对应的记录！
			Employee  e  = new Employee();
			e.setId(2);  只要主键在数据库中存在，就说这个对象在数据库中有对应记录！
		
	
	 	OID, object identified 对象的唯一标识 （对应数据库中的主键）
	
	(二)	持久化状态
		1. 处于session的管理范围
			当执行session的方法如：save/update/saveOrUpdate/get/load
			对象就会自动转变为持久化状态！
		2. 在数据库中有对应的记录
		3. 处于持久化状态的对象，当对对象属性进行更改的时候，提交事务更改会反映到数据库中！
	
	(三)	游离状态
		1. 对象不处于session的管理范围:通常指session关闭后对象的状态
		2. 对象在数据库中有对应的记录!!
	  
 
 * @author 贤元
 *
 */
public class App_1_status {
	//创建session的工厂
	private static SessionFactory sf;
	
	//因为静态代码块只加载一次
	static{
		//通过配置管理器对象来初始化session的工厂，并加载主配置文件，
		sf = new Configuration() //创建配置管理器对象
					.configure() //加载主配置文件 会自动加载src/hibernate.cfg.xml
					.buildSessionFactory();//创建session的工厂对象
	}
	
	//保存
	@Test
	public void testSave() throws Exception {
		//通过session工厂创建session
		Session session  = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		/**
		 * hibernate的对象的三种状态
		 */
		//创建对象		//【临时状态】
		Employee emp = new Employee();
		emp.setName("Rose1");
		emp.setBirth(new Date());
		
		//保存
		session.save(emp);  //【此时对象处于持久化状态】
		emp.setName("Lucy..");  //修改会反映到数据库中，所以这里会生成update语句
		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
		
		emp.setName("New Lucy..");//此时修改不会反映到数据库中
		System.out.println(emp.getName()); // 【此时对象处于游离状态】  //这行的打印结果New Lucy..
		//session.delete(emp); //删除状态 意义不大，会报错：报session已经关闭的错
		/**
		 * 运行结果：
		 * Hibernate: insert into t_employee (name, dept, birth) values (?, ?, ?)
			Hibernate: update t_employee set name=?, dept=?, birth=? where id=?
			New Lucy..

		 */
	}
	
	
	//游离--》持久
	@Test
	public void testStatus() throws Exception {
		//通过session工厂创建session对象
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		//游离状态(手动模拟)
		Employee emp = new Employee();
		emp.setId(1);//【游离状态】(因为对象在数据库中有对应的记录，也就是该主键在数据库中有对应的记录)
		
		session.update(emp);//【持久化状态】
		
		emp.setName("Test..");//修改会反映到数据库中，生成一条update语句
		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
