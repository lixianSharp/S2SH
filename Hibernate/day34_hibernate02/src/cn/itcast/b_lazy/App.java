package cn.itcast.b_lazy;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * 懒加载
 * @author 贤元
 *
 */
public class App {
	
	//创建session的工厂对象
	private static SessionFactory sf;
	
	//初始化session的工厂，通过静态代码块初始化，因为静态代码块只加载一次
	static{
		//通过配置管理器对象加载主配置文件，并且创建session的工厂对象
		sf = new Configuration().configure().buildSessionFactory();
	}
	
	//查询
	@Test
	public void testApp() throws Exception {
		//创建session对象,通过session的工厂创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		//主键查询
		//get：及时加载，只要get就立刻查询数据库
		//如果查询的主键不存在，返回null
		Employee emp = (Employee) session.get(Employee.class, 1);//查询DB
		System.out.println(emp);//不查询DB
		System.out.println(emp.getName());//不查询DB
		System.out.println(emp.getId());//不查询DB
		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
		/**
		 * Hibernate: select employee0_.id as id0_0_, employee0_.name as name0_0_, employee0_.dept as dept0_0_, employee0_.birth as birth0_0_ from t_employee employee0_ where employee0_.id=?
			Employee [id=1, name=Lucy.., dept=null, birth=2017-07-09 10:56:19.0]
			Lucy..
			1
		 */
	}
	
	
	
	//懒加载
	@Test
	public void testLoad() throws Exception {
		//创建session对象,通过session的工厂创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		
		//懒加载
		Employee emp = (Employee) session.load(Employee.class, 1);
		/**
		 * 解决懒加载异常：
		 * 		Session关闭后，不能使用懒加载数据!(以下是两种解决懒加载异常的方式)
		 * 			(解决方式1)在session关闭之前，先使用一次数据，那么session关闭后再使用对象数据，就不是懒加载数据了
		 * 			(解决方式2)强迫代理对象初始化
		 */
		//emp.getName();//(解决方式1)
		Hibernate.initialize(emp);//(解决方式2:强迫代理对象初始化)
		
		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
		
		//在session关闭后，不能用懒加载数据， 那么就要求关闭前把懒加载使用的数据先查询出来！
		/**
		 * 如果直接在session关闭后使用数据的话，则会包懒加载异常：
		 * org.hibernate.LazyInitializationException: could not initialize proxy - no Session
		 * 
		 */
		System.out.println(emp.getName());//不是懒加载数据，因为数据已经查询出来了
	}
}
