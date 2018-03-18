package cn.itcast.d_many2one;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class App {

	//创建session的工厂
	private static SessionFactory sf;
	//初始化session的工厂
	static{
		sf = new Configuration() //创建配置管理器对象
			.configure() //加载主配置文件
			.addClass(Address.class)
			.addClass(Users.class) //相当于：<mapping resource="cn/itcast/c_collection/Users.hbm.xml"/>配置    使用这种方式要注意：映射必须与javabean在同一个包下。这样写不利于维护
			.buildSessionFactory();//创建session的工厂对象
	}
	
	
	
	//注意：hibernate.cfg.xml中在这里要用create
	//多对一关系的保存数据，先保存一的一方的数据，再保存多的一方的数据，(这样会更优！)
	@Test
	public void testSave() throws Exception {
		//创建session对象，通过session工厂创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		
		
		//地址  第一个地址
		Address address_gz = new Address();
		address_gz.setName("广州天河");
		address_gz.setShortName("gzth");
		address_gz.setCode("51000");
		
		//地址  第二个地址
		Address address_sz=new Address();
		address_sz.setName("深圳宝安");
		address_sz.setShortName("szba");
		address_sz.setCode("53000");		
		
		
		//用户
		Users user = new Users();
		user.setName("李贤元");
		user.setAge(24);
		
		//关系
		address_gz.setUser(user);
		address_sz.setUser(user);
		
		
		//----
		// 保存用户、地址  (先保存地址，再保存用户，生成5条sql， 会多2条update语句)
//		session.save(address_gz);
//		session.save(address_sz);
//		session.save(user);
		
		
		//更改保存顺序 (生成3条insert sql语句，因为保存地址的时候，已经有用户id了)
		session.save(user);
		session.save(address_sz);
		session.save(address_gz);
		
		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
	}
	
	
	//注意：hibernate.cfg.xml中在这里要用update
	@Test
	public void testGet() {
		//创建session对象，通过session工厂创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		
		//主键查询地址
		Address address = (Address) session.load(Address.class, 2);//懒加载，只用使用到数据的时候才会去数据库中查询
		System.out.println(address.getName());//广州天河
		//获取地址关联的用户对象，(懒加载)
		System.out.println(address.getUser().getName());//李贤元
		
		
		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
	}
}
