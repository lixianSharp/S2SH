package cn.itcast.d_one2one2;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
//
public class App {

	private static SessionFactory sf;
	static {
		sf = new Configuration()
			.configure()
			.addClass(User.class)
			.addClass(IdCard.class)
			.buildSessionFactory();
	}
	
	@Test
	public void test_save() {
		Session session = sf.openSession();
		session.beginTransaction();
		
		// 对象
		User user = new User();
		user.setName("Jack");
		user.setSex('男');
		
		IdCard idCard = new IdCard();
		idCard.setCardNo("5412...");
		idCard.setPlace("广州");
		idCard.setDate(new Date());
		
		// 关系
		user.setIdCard(idCard);
		
		// 保存
		session.save(idCard);
		session.save(user);
		
		
		session.getTransaction().commit();
		session.close();
	}
	
	
	@Test
	public void test_Get() {
		Session session = sf.openSession();
		session.beginTransaction();
		

		//主键查询
		User user = (User) session.get(User.class, "5412...");
		System.out.println(user.getIdCard().getPlace());//广州 这步会查询DB，并把数据放入缓存中
		System.out.println(user.getIdCard().getPlace());//广州  这步不会查询DB，数据直接从缓存中取出来用
		session.getTransaction().commit();
		session.close();
	}
}












