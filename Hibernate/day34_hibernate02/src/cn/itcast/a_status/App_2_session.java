package cn.itcast.a_status;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * 目的：Session缓存
 * 		Session缓存，也叫一级缓存，当执行session的相关方法，
 * 			如: save()/update()/get()/load()等方法的时候，对象会自动放入一级缓存中。
			当Session关闭后，一级缓存内容失效！

 * @author 贤元
 *
 */
public class App_2_session {
	
	// 创建session的工厂
	private static SessionFactory sf;
	
	//通过静态代码块初始化session的工厂，因为静态代码块只加载一次
	static{
		//通过配置管理器对象创建session工厂，并加载主配置文件
		sf = new Configuration()//创建配置管理器对象
					.configure()//加载主配置文件
					.buildSessionFactory();//创建session的工厂
	}
	
	
	
	@Test
	public void testCache1() throws Exception {
		//创建session对象，通过session工厂来创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		//主键查询
		//Employee emp = (Employee) session.get(Employee.class, 1);//持久化状态
		//emp.setName("Lucy..");//执行多次
		
		/**
		 * 保存：(要掌握的内容)
		 * 	1、根据操作的主键，去缓存(Map)找：
		 * 			如果没有找到，去查询数据库再放入缓存。
		 * 			如果有找到，就不查询数据库，直接返回缓存对象。
		 */
		Employee emp = null;
		emp = (Employee) session.get(Employee.class, 1);
		System.out.println("姓名+部门:"+emp.getName()+emp.getDept());
		emp=(Employee)session.get(Employee.class, 1);//根据操作的主键，去缓存(Map)找，如果有找到，就不查询数据库，直接返回缓存对象。
		//emp=(Employee)session.get(Employee.class, 2);//Map<主键,对象>
		
		//emp.setName("jack");
		
		//让一级缓存内容与数据库同步
		session.flush();//手动让让一级缓存内容与数据库同步
		
		emp.setName("jack1");//以最后一次修改的为准，因为这里emp还是处于持久化状态吗， 这里会更新数据库中的数据
		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
		
	}
	
	
	
	@Test
	public void testCache2() throws Exception {
		//创建session，通过session的工厂来创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		
		//主键查询
		Employee emp1 = (Employee) session.get(Employee.class, 2);
		
		//清空一级缓存中的对象，清除指定的对象 (当一级缓存中的对应的对象清空之后，当再次查询该对象的时候，则需要重新查询数据库)
		//session.evict(emp);//清空一级缓存中对象:清除指定对象		
		//清空一级缓存中的对象，清除所有的对象
		//session.clear();
		
		//如果不清空一级缓存中的对象，则这行代码不会从数据库中查询，只会从一级缓存(Map)中取出数据
		Employee emp2 = (Employee) session.get(Employee.class, 2);
		
		
		
		//提交事务
		tx.commit();//提交事务的时候，就相当于同步数据库，即调用的是flush();
		//关闭session
		session.close();
	}
	
}
