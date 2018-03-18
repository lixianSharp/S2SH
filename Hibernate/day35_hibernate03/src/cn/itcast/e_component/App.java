package cn.itcast.e_component;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class App {
	//创建session的工厂
	private static SessionFactory sf;
	//初始化session的工厂 ，通过静态代码块，因为静态代码块只加载一次
	static{
		//通过创建配置管理器对象，先加载主配置文件并且加载映射配置文件并且创建session的工厂对象
		sf = new Configuration()
				.configure()
				.addClass(Car.class)
				.buildSessionFactory();
	}
	
	//保存
	@Test
	public void testSave() throws Exception {
		//创建session，通过session的工厂创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		//汽车
		Car car = new Car();
		car.setType("Volvo");
		
		//车轮
		Wheel wheel = new Wheel();
		wheel.setCount(4);
		wheel.setSize(37);
		
		//关系  组合关系，车轮是组成汽车的一部分
		car.setWheel(wheel);
		
		//保存
		session.save(car);
		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
		
	}
	
	//获取
	@Test
	public void testGet() throws Exception {
		//创建session，通过session的工厂创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		//主键查询
		Car car = (Car)session.get(Car.class, 1);
		System.out.println(car.getType());//Volvo
		System.out.println(car.getWheel().getCount());//4
		//提交事务
		tx.commit();
		//关闭session
		session.close();
		
	}
}
