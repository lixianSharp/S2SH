package cn.itcast.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	//创建session的工厂
	public static SessionFactory sf;
	//初始化session的工厂
	static{
		sf = new Configuration().configure().buildSessionFactory();
	}
	
	//返回session
	public static Session getSession(){
		//线程方式创建连接对象，要进行配置
		return sf.getCurrentSession();
	}
}
