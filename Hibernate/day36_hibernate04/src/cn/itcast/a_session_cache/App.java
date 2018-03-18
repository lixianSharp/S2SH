package cn.itcast.a_session_cache;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class App {
	
	//创建session的工厂
	private static SessionFactory sf;
	
	// 初始化session的工厂
	static{
		//通过配置管理器对象，加载主配置文件，加载映射文件，并且创建session工厂的对象
		
		sf = new Configuration()	
				.configure()
				.addClass(Dept.class)
				.addClass(Employee.class)
				.buildSessionFactory();
	}
	
	
	//不同的session，能否共享一级缓存数据呢？答案是：不能。
	//一级缓存只在当前session有效，缓存时间短、、作用范围小！总体来看缓存效果不明显！
	@Test
	public void test_session_cache() {
		Session session1 = sf.openSession();
		session1.beginTransaction();
		Session session2 = sf.openSession();
		session2.beginTransaction();
		
		// 1. 先使用第一个session查询，
		Dept dept = (Dept) session1.get(Dept.class, 2);
		
		// 2. 再使用第二个session更新，如果是共享一级缓存数据的话就当执行下面这一条一局的时候就不会产生update语句
		session2.update(dept);// 产生这条update语句：Hibernate: update t_dept set deptName=? where id=?

		
		System.out.println("============");
		
		// 3. 修改
		dept.setDeptName("New Dept");
		
		
		session1.getTransaction().commit();  // 第一个session的缓存区与数据库同步
		session1.close();
		session2.getTransaction().commit();   // 第二个session的缓存区与数据库同步
		session2.close();
	}
}
