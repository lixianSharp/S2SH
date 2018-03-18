package cn.itcast.c_collection;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class App_set {
	//创建session工厂
	private static SessionFactory sf;
	
	//初始化session工厂，并加载配置文件
	static{
		sf = new Configuration() //
				.configure()  //加载主配置文件
				.addClass(Users.class) 
				/**
				 * 相当于：<mapping resource="cn/itcast/c_collection/Users.hbm.xml"/>配置   
				 * 	 使用这种方式要注意：映射必须与javabean在同一个包下。这样写不利于维护，但方便测试
				 */
				.buildSessionFactory();//创建session的工厂对象
	}
	
	
	//保存对象的时候，会保存对象关联的数据
	@Test
	public void testSave() throws Exception {
		//创建session对象，通过session的工厂创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		
		//地址对象
		Set<String> addressSet = new HashSet<String>();
		addressSet.add("骏景花园");
		addressSet.add("天朗名居");
		
		//用户
		Users user = new Users();
		user.setName("老许");

		//关系
		user.setAddressSet(addressSet);
		
		//保存
		session.save(user);
		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
	}
	
	
	//只要配置好映射，在获取数据的时候，会获取到关联对象的数据(hibernate自动填充关联数据)
	@Test
	public void testGet() throws Exception {
		//创建session对象，通过session的工厂创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		
		//主键查询用户
		Users user = (Users)session.load(Users.class, 2);
		System.out.println(user.getName());
		System.out.println("-----------");
		System.out.println(user.getAddressSet());//懒加载
		
		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
	}
}
