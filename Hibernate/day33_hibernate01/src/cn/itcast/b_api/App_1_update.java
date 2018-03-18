package cn.itcast.b_api;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
//测试Hibernate框架的一些API
public class App_1_update {

	
	/**
	 * 更新的方法
	 * @throws Exception
	 */
	@Test
	public void testUpdate() throws Exception {
		//1、创建配置管理器对象
		Configuration config = new Configuration();
		//2、加载主配置文件，默认加载src/hibernate.cfg.xml
		config.configure();
		//3、根据加载的主配置文件，创建session工厂对象
		SessionFactory sf = config.buildSessionFactory();
		/**
		 * 4、创建session对象  通过session工厂创建session对象 
		 *		 注意：这里的session不是Servlet中的session啊
		 * 
		 */
		Session session = sf.openSession();
		
		//5、开启事务
		Transaction tx = session.beginTransaction();
		
		//创建一个javabean对象
		User user = new User();
		user.setUserName("tom");
		user.setAge(30);
		
		/**
		 * 1）保存对象
		 */
		//session.save(user);
		
		/**
		 * 2）主键查询
		 */
		//User u = (User) session.get(User.class, 1);
		//System.out.println(u);//Users [userId=1, userName=tom, age=30]
		
		/**
		 * 3）删除
		 */
		//方式一：模拟一个对象 根据主键删除
		//User s = new User();
		//s.setUserId(1);
		//session.delete(s);
		
		//方式二：先查询，再删除
		//如果没查到就会返回null，所以删除之前需要先判断null，否则删除的时候会报错的。
		//Object obj = session.get(User.class,2);
		//if(obj!=null){
		//	session.delete(obj);
		//}
		
		
		/**
		 * 4)修改
		 */
		//第一种修改方式  推荐使用这一种   这种方式是：先查询，再修改
		//User us = (User) session.get(User.class,3);
		//if(us != null){
		//	us.setUserName("new Name");
		//	session.update(us);//把主键id为3的name修改为"new Name"了
		//}
		
		//第二种修改方式， 有可能会出错
		//User s = new User();
		//s.setUserId(3);//如果主键不存在的话就报错
		//s.setUserName("lixian");
		//s.setAge(20);
		//session.update(s);
		
		
		/**
		 * 5)保存或更新
		 * 		当没有设置主键，执行保存。
		 * 		当有设置主键则执行更新，但主键在数据库中必须存在，
		 * 					如果主键不存在就不知道更新哪条记录，hibernate就报错。
		 */
		User u5= new User();
		//u5.setUserId(3);//当没有设置主键，执行保存
		u5.setUserName("LXY");
		session.saveOrUpdate(u5);
		//如果u5为null的话就执行插入(因为没有设置主键)。如果u5不为空且主键存在的话就执行的更新
		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
	}
}
