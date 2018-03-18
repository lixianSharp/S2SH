package cn.itcast.d_one2one;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class App {
	//创建session的工厂
	private static SessionFactory sf;
	
	static{
		sf = new Configuration()
				.configure()
				.addClass(User.class)
				.addClass(IdCard.class)
				.buildSessionFactory();
	}
	
	//保存数据，不能重复维护关系
	@Test
	public void testSave() throws Exception {
		//创建session对象，通过session的工厂来创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		
		//创建对象
		User user = new User();
		user.setName("jack");
		user.setSex('男');
		
		//身份证
		IdCard idCard = new IdCard();
		idCard.setCardNo("2014220020216");
		idCard.setPlace("龙岩");
		idCard.setDate(new Date());
		
		//关系  通过有外键的一方维护关系
		user.setIdCard(idCard);
		
		//保存
		session.save(idCard);
		session.save(user);
		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
	}
	

}
