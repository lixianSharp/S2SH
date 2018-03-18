package cn.itcast.c_many2many;

import java.util.Set;

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
				.addClass(Person.class)
				.addClass(Project.class)
				.buildSessionFactory();
	}
	
	//保存数据，不能重复维护关系
	//inverse=true 如果没有控制权，不能维护关系，不能往中间表插入记录。
	@Test
	public void testSave() throws Exception {
		//创建session对象，通过session的工厂来创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		
		//项目
		Project p_oa = new Project();
		p_oa.setName("OA项目");
		Project p_crm = new Project();
		p_crm.setName("CRM项目");
		
		//开发人员
		Person p_wl = new Person();
		p_wl.setName("王蕾");
		Person p_lc = new Person();
		p_lc.setName("刘昌");
		
		//关系【crm:王蕾/刘昌】 一个项目可以有多个开发人员
		p_crm.getPerson().add(p_wl);
		p_crm.getPerson().add(p_lc);
		//关系【OA:王蕾】 一个开发人员可以开发多个项目
		p_oa.getPerson().add(p_wl);
		
		//p_wl.getProjects().add(p_oa);
		//错误，关系不能重复维护，因为每次维护关系，都是往中间表插入记录，所以不能重复插入！
		
		
		//保存
		session.save(p_crm);
		session.save(p_oa);
		session.save(p_lc);
		session.save(p_wl);
		
		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
	}
	
	
	//通过一方获取另外一方
	@Test
	public void testGet() throws Exception {
		//创建session对象，通过session的工厂来创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		

		//主键查询项目
		Project prj = (Project)session.get(Project.class, 1);
		System.out.println(prj.getName());
		
		/**
		 * lazy:
		 * 		true set集合默认懒加载 (在映射中关联数据默认都是懒加载)
		 * 		false  关闭懒加载
		 * 		extra 懒加载,在真正使用数据的时候,才向数据库发查询的sql,当执行集合的isEmpty()/size()方法的时候,只是统计
		 * 
		 */
		Set<Person> person = prj.getPerson();
		for(Person p : person){
			System.out.println(p.getName());
		}

		System.out.println(prj.getPerson());//默认是懒加载
		prj.getPerson();//这个不算使用数据，不会懒加载
		prj.getPerson().size();//懒加载
		
		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
	}

	
	//解除关系
	//inverse=false,有控制权，可以解除关系，指删除中间表的数据
	//inverse=true,不能解除，没有执行任何操作，但也不会操作
	@Test
	public void test_releaseRealtion() throws Exception {
		//创建session对象，通过session的工厂来创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		
		//项目
		Project prj = (Project)session.get(Project.class, 1);
		prj.getPerson().clear();//清空集合数据，提交事务的时候，会反映到数据库中(持久化对象)
		

		
		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
	}

	
	//删除数据
	//inverse=false,删除数据的时候，会先删除中间表的数据，再删除自身
	//inverse=true,只有删除的数据没有被关联的时候，才可以删除
	@Test
	public void test_deleteDate() throws Exception {
		//创建session对象，通过session的工厂来创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		
		//项目
		Project prj = (Project)session.get(Project.class, 2);
		session.delete(prj);

		
		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
	}

	
	
}
