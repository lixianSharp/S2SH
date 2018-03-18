package cn.itcast.a_config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

//测试类
public class App {
	
	/**
	 * 保存对象
	 * @throws Exception
	 */
	@Test
	public void testSave() throws Exception {
		//对象
		Users users = new Users();
		users.setUserName("lixian");
		users.setAge(22);
		
		//1、创建配置管理器对象
		Configuration config = new Configuration();
		//2、加载主配置文件，默认加载src/hibernate.cfg.xml
		config.configure();
		//3、根据加载的主配置文件，创建session工厂对象
		SessionFactory sf = config.buildSessionFactory();
		//4、创建session对象  通过session工厂创建session对象 注意：这里的session不是Servlet中的session啊
		Session session  = sf.openSession();
		//5、开启事务
		Transaction tx = session.beginTransaction();
		
		//保存
		session.save(users);
		
		//6、提交事务/关闭session
		tx.commit();
		session.clear();
		
	}
	
	
	/**
	 * 获取对象
	 * @throws Exception
	 */
	@Test
	public void testGet() throws Exception {		
		//1、创建配置管理器对象
		Configuration config = new Configuration();
		//2、加载主配置文件，默认加载src/hibernate.cfg.xml
		config.configure();
		//3、根据加载的主配置文件，创建对象
		SessionFactory sf = config.buildSessionFactory();
		//4、创建session对象  注	意：这里的session不是Servlet中的session啊
		Session session = sf.openSession();
		//5、开启事务
		Transaction tx = session.beginTransaction();
		
		//获取(根据主键查询)
		Users users = (Users) session.get(Users.class, 1);
		//打印获取到的结果
		System.out.println(users);
		
		//6、提交事务/关闭session
		tx.commit();
		session.clear();
		
	}
	
	
	
	
	
}
