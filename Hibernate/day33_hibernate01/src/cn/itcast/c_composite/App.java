package cn.itcast.c_composite;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class App {

	//保存对象
	@Test
	public void test_save() throws Exception {
		//创建配置管理器对象
		Configuration config = new Configuration();
		//加载主配置文件  会默认加载src/hibernate.hbm.xml
		config.configure();
		
		//创建session的工厂
		SessionFactory sf = config.buildSessionFactory();
		//通过session的工厂创建session对象
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		
		/**
		 * 插入的数据中，联合主键中的数据不能同时重复，如果重复的只是联合主键中的其中一个，
		 * 		则插入没问题，如果联合主键中的两个主键在数据库中已经存在了，则插入不进去。
		 * 			keys.setName("jack");//员工姓名
					keys.setAddress("龙岩..");//员工所在地址
					如果数据库中没有这条数据，则第一次插入可以插入进去
					第二次插入，则不能插入，因为违反了联合主键约束
					如果第二次插入的是：		
							keys.setName("jack");//员工姓名
							keys.setAddress("龙岩");//员工所在地址
							则可以插入
		 * 
		 */
		//联合主键对象
		CompositeKeys keys = new CompositeKeys();
		keys.setName("jack");//员工姓名
		keys.setAddress("龙岩..");//员工所在地址
		
		//对象
		Employee emp = new Employee();
		emp.setKeys(keys);
		emp.setDept("开发部");//部门名称
		emp.setBirth(new Date());//年龄
		
		//保存
		session.save(emp);
		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
		System.out.println("保存成功");
	}
	
	//获取数据
	@Test
	public void test_Get() throws Exception {
		//创建配置管理器对象
		Configuration config = new Configuration();
		//通过配置管理器对象加载主配置文件
		config.configure();
		//创建session的工厂，
		SessionFactory sf = config.buildSessionFactory();
		//通过session的工厂创建session对象
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		/**
		 * 联合主键对象
		 */
		CompositeKeys keys = new CompositeKeys();
		keys.setName("Jack");
		keys.setAddress("龙岩..");
		
		//根据主键(联合主键)查询
		Employee employee = (Employee)session.get(Employee.class, keys);
		System.out.println(employee.getKeys().getName());
		System.out.println(employee.getKeys().getAddress());
		System.out.println(employee.getDept());
		System.out.println(employee.getBirth());
		
		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
		
	}
	
}
