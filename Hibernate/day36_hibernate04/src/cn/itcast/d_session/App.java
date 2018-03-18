package cn.itcast.d_session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

/**
 * 两种方式创建session
 * @author 贤元
 *
 */
public class App {
	//创建session的工厂
	private static SessionFactory sf;
	static{
		//通过配置管理器对象加载主配置文件，并创建session的工厂对象
		sf = new Configuration().configure().buildSessionFactory();
	}
	
	//2种方式创建session
	@Test
	public void testSession() throws Exception {
		System.out.println(sf);
		//方式1：sf.opSession();每次都创建一个新的session！
		Session session1 = sf.openSession();
		Session session2 = sf.openSession();
		System.out.println(session1 == session2);//false
		//关闭方式1创建的两个session
		session1.close();
		session2.close();
		
		
		/**
		 * 方式2：线程方式创建session（推荐用这种方式，对资源的消耗比较小）
		 * 要在主配置文件hibernate.cfg.xml中加上如下代码：
		 *   <!-- 配置session的创建方式，线程方式创建session -->
         *	 <property name="hibernate.current_session_context_class">thread</property>
		 */
		//getCurrentSession() 先从当前线程获取session，没有获取到，就创建一个新的session，创建完成后，再绑定到当前线程
		Session session3 = sf.getCurrentSession();
		Session session4 = sf.getCurrentSession();
		System.out.println(session3 == session4);//true
		
		session3.close();
		//session4.close();//不能重复关闭
	}
}
