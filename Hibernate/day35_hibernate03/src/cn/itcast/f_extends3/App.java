package cn.itcast.f_extends3;

import java.util.List;

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
		//通过配置管理器对象加载主配置文件，加载映射文件，并且创建session的工厂对象
		sf = new Configuration() //创建配置管理器对象
				.configure()
				.addClass(Animal.class)
				.buildSessionFactory();
		
	}
	
	//保存
	@Test
	public void testSave() throws Exception {
		//创建session，通过session的工厂创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		//狗
		Dog dog = new Dog();
		dog.setId(100);
		dog.setName("花皮狗");
		dog.setPlay("接飞盘");
		//保存
		session.save(dog);
		
		//猫
		Cat cat = new Cat();
		cat.setId(1000);
		cat.setName("黑猫警长");
		cat.setCatching("抓老鼠");
		//保存
		session.save(cat);
		
		
		//提交事务
		tx.commit();
		//关闭session
	}
	
	//获取
	@Test
	public void testGet() throws Exception {
		//创建session，通过session的工厂创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		//获取狗的数据  主键查询
		Dog dog = (Dog)session.get(Dog.class, 1);
		System.out.println(dog.getName()+dog.getId()+dog.getPlay());//花皮狗1接飞盘
		
		//获取猫的数据 主键查询
		Cat cat = (Cat)session.get(Cat.class, 2);
		System.out.println(cat.getName()+cat.getId()+cat.getCatching());//黑猫警长2抓老鼠
		
		//方式3 HQL查询(推荐用这种方式，可以把全部数据都查询到)
		//注意：在继承关系中，如果hql语句是通过父类查找，则需要使用全名
		List list=session.createQuery("from cn.itcast.f_extends3.Animal").list();
		System.out.println(list);//[cn.itcast.f_extends3.Dog@221dc7b3, cn.itcast.f_extends3.Cat@74a9323]
		//List list=session.createQuery("from Dog").list();		
		

		
		
		//提交事务
		tx.commit();
		//关闭session
	}
}
