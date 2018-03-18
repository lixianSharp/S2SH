package cn.itcast.c_collection;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class App_list {
	//创建session的工厂
	private static SessionFactory sf;
	static{
		//通过配置管理器对象加载主配置文件，并创建session工厂对象
		sf = new Configuration() //创建配置管理器对象
				.configure() //加载主配置文件
				/**
				 * 这样写可以不用再主配置文件中引入该javabean的映射配置文件了 //
				 * 相当于：<mapping resource="cn/itcast/c_collection/Users.hbm.xml"/>配置   
				 *  使用这种方式要注意：映射必须与javabean在同一个包下。这样写不利于维护
				 */
				.addClass(Users.class) 
				.buildSessionFactory();//创建session的工厂对象
	}
	
	
	//保存对象的时候，会保存对象关联的数据
	@Test
	public void testSave() throws Exception {
		//通过session的工厂创建session对象
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		
		//地址对象
		List<String> addressList = new ArrayList<String>();
		addressList.add("b_深圳福田");//赋值
		addressList.add("a_深圳宝安");
		
		
		//用户  
		Users user = new Users();
		user.setName("老许");
		
		//关系
		user.setAddressList(addressList);
		
		
		//保存
		session.save(user);
		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
	}
	
	
	//获取
	@Test
	public void testGet() throws Exception {
		//通过session的工厂创建session对象
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		Users user = (Users) session.get(Users.class, 1);//即时加载
		
		System.out.println(user.getName());
		System.out.println(user.getAddressList());
		//提交事务
		tx.commit();
		//关闭session
		session.close();		
	}
	
}
