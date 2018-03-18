package cn.itcast.b_api;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
//测试类
//hibernate中的查询的几种方式
public class App_2_query {

	/**
	 * 主键查询
	 * @throws Exception
	 */
	@Test
	public void test_primary() throws Exception {
		//1、创建配置管理器对象
		Configuration config = new Configuration();
		//2、加载主配置文件，默认加载src/hibernate.cfg.xml
		config.configure();
		//3、根据加载的主配置文件，创建session工厂对象
		SessionFactory sf = config.buildSessionFactory();
		/**
		 * 4、创建session对象  通过session工厂创建session对象 
		 * 			注意：这里的session不是Servlet中的session啊
		 */
		Session session = sf.openSession();
		//5、开启事务
		Transaction tx = session.beginTransaction();
		
		//1.1）get: 查询的主键不存在，就返回null
		//User user = (User) session.get(User.class, 3);
		//System.out.println(user);//[userId=3, userName=lixianyuan, age=0]
		
		//1.2) load:当查询的主键不存在，只要使用就报错
		User u = (User) session.load(User.class, 50);
		System.out.println(u);
		
		//提交事务
		tx.commit();
		//关闭session
	}
	
	
	//2、hql查询(一般用这个，这个需要sql的基础)
	@Test
	public void test_query() throws Exception {
		//1、创建配置管理器对象
		Configuration config = new Configuration();
		//2、加载主配置文件，默认加载src/hibernate.cfg.xml
		config.configure();
		//3、根据加载的主配置文件，创建session工厂对象
		SessionFactory sf = config.buildSessionFactory();
		/**
		 * 4、创建session对象  通过session工厂创建session对象 
		 * 		注意：这里的session不是Servlet中的session啊
		 */
		Session session = sf.openSession();
		//5、开启事务
		Transaction tx = session.beginTransaction();
		
		/**
		 * 准备hql查询
		 */
		//得到hql查询接口
		Query q = session.createQuery("from User");
		//这个是查询所有数据   
		//Query q=session.createQuery("from Users where userId=3");//这个是查询单条数据
		
		//查询数据
		List<User> list = q.list();
		System.out.println(list);
		/**
		 * 	[	
		 * 		Users [userId=1, userName=tom, age=30], 
		 * 		Users [userId=2, userName=tom2, age=30], 
		 * 		Users [userId=3, userName=lixianyuan, age=0], 
		 * 		Users [userId=7, userName=LXY, age=0]
		 * 	]
		 */
		
		//6、提交事务
		tx.commit();
		//关闭session
		session.close();
	}
	
	//3、criteria查询，完全面向对象的查询(这个作为了解)
	@Test
	public void test_criteria() throws Exception {
		//1、创建配置管理器对象
		Configuration config = new Configuration();
		//2、加载主配置文件，默认加载src/hibernate.cfg.xml
		config.configure();
		//3、根据加载的主配置文件，创建session工厂对象
		SessionFactory sf = config.buildSessionFactory();
		/**
		 * 4、创建session对象  通过session工厂创建session对象 
		 * 注意：这里的session不是Servlet中的session啊
		 */
		Session session = sf.openSession();
		//5、开启事务
		Transaction tx = session.beginTransaction();
		
		/**
		 * 准备Criteria查询
		 */
		//获取Criteria接口
		Criteria c = session.createCriteria(User.class);
		//设置条件  //属性名  属性值 表示查询属性值为"tom"的有哪些
		c.add(Restrictions.eq("userName", "tom"));
		List<User> list = c.list();
		System.out.println(list);
		
		//6、提交事务
		tx.commit();
		//关闭session
		session.close();
	}
}
