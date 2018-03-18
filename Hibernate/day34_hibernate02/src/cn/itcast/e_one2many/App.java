package cn.itcast.e_one2many;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class App {

	//创建session的工厂
	private static SessionFactory sf;
	//初始化session的工厂
	static{
		sf = new Configuration() //创建配置管理器对象
			.configure() //加载主配置文件
			.addClass(Address.class)
			.addClass(Users.class) //相当于：<mapping resource="cn/itcast/c_collection/Users.hbm.xml"/>配置    使用这种方式要注意：映射必须与javabean在同一个包下。这样写不利于维护
			.buildSessionFactory();//创建session的工厂对象
	}
	
	
	
	//注意：hibernate.cfg.xml中在这里要用create
	//多对一关系的保存数据，先保存一的一方的数据，再保存多的一方的数据，(这样会更优！)
	@Test
	public void testSave() throws Exception {
		//创建session对象，通过session工厂创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		

		//用户
		Users user = new Users();
		user.setName("周颖");
		user.setAge(25);
		
		//地址  第一个地址
		Address address_gz = new Address();
		address_gz.setName("广州天河..");
		address_gz.setShortName("gzth");
		address_gz.setCode("51000");
		
		//地址   第二个地址
		Address address_sz = new Address();
		address_sz.setName("深圳宝安..");
		address_sz.setShortName("szba");
		address_sz.setCode("53000");
		
		//关系(通过一的一方维护 ，因为是通过一对多配置的)
		user.getAddress().add(address_sz);
		user.getAddress().add(address_gz);
		
		//保存信息  先保存一的一方
		session.save(user);
		session.save(address_sz);
		session.save(address_gz);
		
		
		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
	}
	
	
	//注意：hibernate.cfg.xml中在这里要用update
	@Test
	public void testGet() {
		//创建session对象，通过session工厂创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		
		//主键查询地址
		Users user = (Users)session.load(Users.class, 1);//get是即时加载，load才是懒加载。
		System.out.println(user.getName());
		
		//解决懒加载数据使用异常的问题，也就是session关闭后使用懒加载数据的问题
		//方式1：把要使用的数据先使用
		//user.getAddress().size();
		//解决方式2：通过初始化代理对象
		Hibernate.initialize(user.getAddress());
				
		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
		
		System.out.println("关闭session后能获取数据？"+user.getAddress().size());
	}
}
