package cn.itcast.a_status;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
/**
 * 目的：
 * 	list()与iterator()查询的区别？
 * @author 贤元
 *
 */
public class App_3_list {
	
	//创建session工厂
	private static SessionFactory sf;
	//初始化session的工厂，通过静态代码块来初始化，因为静态代码块只加载一次
	static{
		//通过配置管理器对象加载主配置文件，并且通过配置管理器对象创建session工厂对象
		sf = new Configuration() //创建配置管理器对象
				.configure()//加载主配置文件，默认加载src/hibernate.cfg.xml
				.buildSessionFactory();//创建session的工厂
	}
	
	/**
	 * 1、list查询
	 * 	list查询有放入缓存，但不会从缓存中找
	 * @throws Exception
	 */
	@Test
	public void test_list() throws Exception {
		//创建session对象，通过session工厂来创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		
		//---HQL查询---
		Query q = session.createQuery("from Employee");
		//--第一次查询--
		List<Employee> list = q.list();//【查询数据库】
		//遍历
		for(Employee emp : list){
			System.out.println(emp);
		}
		
		System.out.println("第二次");
		
		//--第二次查询---
		list = q.list();//【查询数据库】,放入缓存？ 没有从缓存中取数据
		//遍历
		for(Employee emp : list){
			System.out.println(emp);
		}
		
		/**
		 * 总的打印结果(此时应为我的数据库中只有两条数据)：
		 *  Hibernate: select employee0_.id as id0_, employee0_.name as name0_, employee0_.dept as dept0_, employee0_.birth as birth0_ from t_employee employee0_
			Employee [id=1, name=jack1, dept=开发部, birth=null]
			Employee [id=2, name=哈哈, dept=研发部, birth=null]
			第二次
			Hibernate: select employee0_.id as id0_, employee0_.name as name0_, employee0_.dept as dept0_, employee0_.birth as birth0_ from t_employee employee0_
			Employee [id=1, name=jack1, dept=开发部, birth=null]
			Employee [id=2, name=哈哈, dept=研发部, birth=null]
		 */
		//提交事务
		tx.commit();
		//关闭session
		session.close();
	}
	
	
	//2、iterator查询
	/**
	 * 	先从缓存找，没有找到，再找数据库，最后把查询的结果放入缓存.
	 * 
	 *  N+1,当使用iterator查询的时候，发送到数据库的sql语句的个数是：总记录数+1
	 *  	N=总记录数
	 *  	1=统计所有的主键
	 *  会放入缓存，且会从缓存中查找
	 * @throws Exception
	 */
	@Test
	public void test_iterator() throws Exception {
		//创建session对象，通过session的工厂创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		//HQL查询，获取查询接口，
		Query q = session.createQuery("from Employee");
		//迭代  第一次查询
		Iterator<Employee> it = q.iterate();
		//遍历
		while(it.hasNext()){
			Employee emp = it.next();
			System.out.println(emp);//先从缓存找，没有找到，再找数据库，最后把查询的结果放入缓存
		}
		
		System.out.println("=======================");
		
		//迭代  第二次查询
		it = q.iterate();
		//遍历
		while(it.hasNext()){
			Employee emp = it.next();
			System.out.println(emp);//从缓存中取
		}
		
		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
		
		/**
		 * 结果：
  	Hibernate: select employee0_.id as col_0_0_ from t_employee employee0_  统计所有的主键
	Hibernate: select employee0_.id as id0_0_, employee0_.name as name0_0_, employee0_.dept as dept0_0_, employee0_.birth as birth0_0_ from t_employee employee0_ where employee0_.id=?
	Employee [id=1, name=Lucy.., dept=null, birth=2017-07-09 10:56:19.0]
	Hibernate: select employee0_.id as id0_0_, employee0_.name as name0_0_, employee0_.dept as dept0_0_, employee0_.birth as birth0_0_ from t_employee employee0_ where employee0_.id=?
	Employee [id=2, name=Lucy., dept=null, birth=2017-07-09 11:13:57.0]
	Hibernate: select employee0_.id as id0_0_, employee0_.name as name0_0_, employee0_.dept as dept0_0_, employee0_.birth as birth0_0_ from t_employee employee0_ where employee0_.id=?
	Employee [id=3, name=Lucy, dept=null, birth=2017-07-09 11:14:23.0]
	======================= 先从缓存找，没有找到，再找数据库，最后把查询的结果放入缓存.
	Hibernate: select employee0_.id as col_0_0_ from t_employee employee0_  统计所有的主键
	Employee [id=1, name=Lucy.., dept=null, birth=2017-07-09 10:56:19.0]
	Employee [id=2, name=Lucy., dept=null, birth=2017-07-09 11:13:57.0]
	Employee [id=3, name=Lucy, dept=null, birth=2017-07-09 11:14:23.0]
		 */
	}
	
	
	//3、list()与iterator()查询的区别??    iterator()查询会统计所有的主键  list()查询不会统计所有的主键
	/**
	 *  整体思路：
	 *  	先执行一次list，再执行iterator!
	 *  	如果iterator查询每一条记录没有去找数据库，说明list查询有放入缓存
	 *  
	 *  
	 *  结果：list查询和iterator查询都是有放入缓存的，但是iterator查询会去缓存中找，但是list查询就不会去缓存中找了。
	 * @throws Exception
	 */
	@Test
	public void test_list_iterator() throws Exception {
		//创建session对象，通过session的工厂来创建
		Session  session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		
		/**
		 * 1、先list
		 */
		Query q = session.createQuery("from Employee");
		List<Employee> list= q.list(); //说明这里有放入缓存
		//遍历
		for(Employee emp : list){
			System.out.println(emp);
		}
		
		System.out.println("=========================");
		
		
		//2、再iterator
		Iterator<Employee> it = q.iterate();
		//遍历
		while(it.hasNext()){
			Employee emp = it.next();
			System.out.println(emp); //1、从缓存取
		}
		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
		
		/**
		 * 结果：
		 * Hibernate: select employee0_.id as id0_, employee0_.name as name0_, employee0_.dept as dept0_, employee0_.birth as birth0_ from t_employee employee0_
			Employee [id=1, name=Lucy.., dept=null, birth=2017-07-09 10:56:19.0]
			Employee [id=2, name=Lucy., dept=null, birth=2017-07-09 11:13:57.0]
			Employee [id=3, name=Lucy, dept=null, birth=2017-07-09 11:14:23.0]
			=========================
			Hibernate: select employee0_.id as col_0_0_ from t_employee employee0_  统计所有的主键
			Employee [id=1, name=Lucy.., dept=null, birth=2017-07-09 10:56:19.0]
			Employee [id=2, name=Lucy., dept=null, birth=2017-07-09 11:13:57.0]
			Employee [id=3, name=Lucy, dept=null, birth=2017-07-09 11:14:23.0]
		 */
	}
	
	
		
}
