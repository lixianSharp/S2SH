package cn.itcast.b_api;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
//使用hql语句查询
public class App_4_hbm {
	public static void main(String[] args) {
		//创建配置管理器对象，并加载主配置文件(会加载映射)
		Configuration config = new Configuration().configure();
		//创建session工厂
		SessionFactory sf = config.buildSessionFactory();
		//创建session对象
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		//HQL语句查询
		Query q = session.createQuery("from User");
		/**
		 * 	auto-import="true" (是否自动导包):
		 * 				默认为true，即在写hql的时候，会自动引入包名;
						如果为false，hql中对象要写上包名称。
						Query q = session.createQuery("from cn.itcast.b_api.User");
		 */
		List<User> list = q.list();
		System.out.println(list);
		//提交事务
		tx.commit();
		//关闭session
		session.close();
	}
}
