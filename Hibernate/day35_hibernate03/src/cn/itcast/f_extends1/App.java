package cn.itcast.f_extends1;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * 这里只演示了Dog的，Cat的还没有演示，但是也是和Dog的演示类似
 * @author 贤元
 *
 */
public class App {
	//创建session的工厂
	private static SessionFactory sf;
	//初始化session的工厂
	static{
		//通过配置管理器对象加载主配置文件，加载映射文件，并且创建session的工厂对象
		sf = new Configuration() //创建配置管理器对象
				.configure()
				.addClass(Dog.class)
				.addClass(Cat.class)
				.buildSessionFactory();
		
	}
	
	//保存
	@Test
	public void testSave() throws Exception {
		//创建session，通过session的工厂创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		Dog dog = new Dog();
		dog.setName("花皮狗");
		dog.setPlay("接飞盘");
		
		Cat cat = new Cat();
		cat.setName("kitty");
		cat.setCatching("抓老鼠");
		
		//保存
		session.save(dog);
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
		
		//方式1查询
		//Dog dog = (Dog)session.get(Dog.class, 1);
		//System.out.println(dog.getName()+dog.getId()+dog.getPlay());//花皮狗1接飞盘
		
		//方式2查询
		//Animal dog = (Animal) session.get(Dog.class, 1);
		//System.out.println(dog.getName()+dog.getId());//花皮狗1  但是不能使用dog.play()，因为父类中没有这个
		
		
		//方式3 HQL查询(推荐用这种方式，可以把全部数据都查询到)
		//注意：在继承关系中，如果hql语句是通过父类查找，则需要使用全名
		List<Dog> list = session.createQuery("from cn.itcast.f_extends1.Animal").list();
		System.out.println(list);//[cn.itcast.f_extends1.Dog@2d07c721]
		//List<Dog> list = session.createQuery("from Dog").list();
		//System.out.println(list);//[cn.itcast.f_extends1.Dog@2d07c721]
		for(Dog d:list){
			System.out.println("看看能不能获取到dog中的play"+d.getPlay());//接飞盘
		}
		//提交事务
		tx.commit();
		//关闭session
	}
}
