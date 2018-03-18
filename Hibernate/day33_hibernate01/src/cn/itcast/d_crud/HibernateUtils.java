package cn.itcast.d_crud;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//工具类
public class HibernateUtils {
	private static SessionFactory sessionFactory;//session的工厂
	
	//静态代码块只加载一次
	static{
		//初始化工厂
		sessionFactory = new Configuration()//创建配置管理器对象
						.configure()//加载主配置文件
						.buildSessionFactory();//创建session的工厂对象
	}
	
	/**
	 * 创建session
	 */
	public static Session getSession(){
		return sessionFactory.openSession();//通过session的工厂对象创建session
	}
	
}
