package cn.itcast.c_hql;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Example;
import org.junit.Test;
/**
 * Hibernate提供的面向对象的查询的另外的方式！
更面向对象！


 * @author 贤元
 *
 */
public class App_2_criteria {
	//创建session的工厂
	private static SessionFactory sf;
	//初始化session的工厂  通过静态代码块来初始化，因为静态代码块只加载一次
	static{
		//通过配置管理器对象加载主配置文件，加载映射文件，并且创建session的工厂对象
		sf = new Configuration()
				.configure()
				.addClass(Dept.class)
				.addClass(Employee.class)
				.buildSessionFactory();
	}
	
	
	//QBC查询
	@Test
	public void test_demo1() throws Exception {
		//创建session,通过session的工厂来创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		

		/**Query接口中的方法
		Query q = session.createQuery("")
		q.list();
		q.uniqueResult();
		q.iterate();
		q.executeUpdate();
		q.setFirstResult();
		q.setFirstResult(0)
		q.setMaxResults(0);
		*/
		
		
		//获取Criteria查询接口
		Criteria c = session.createCriteria(Dept.class);
		//1、主键查询
		//c.add(Restrictions.idEq(1));

		//2、等值条件查询
		//c.add(Restrictions.eq("deptName", "HR"));
		
		//3、模糊查询
		//c.add(Restrictions.like("deptName", "&h%"));
		
		//4、map作为条件，map的key就是bean的属性
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("deptName", "HR");
//		map.put("id", 100);
//		c.add(Restrictions.allEq(map));
		
		//5、example查询
//		Dept dept = new Dept();
//		//dept.setId(1000);//不起作用
//		dept.setDeptName("HR");
//		//把对象的非主键属性作为条件(自动把对象的非空属性值作为查询条件)
//		c.add(Example.create(dept));
//		
		//查询全部
		List<Dept> list = c.list();
		System.out.println(list);
		//[cn.itcast.c_hql.Dept@560973d9, cn.itcast.c_hql.Dept@190b17ef, cn.itcast.c_hql.Dept@275058c1]

		
		
		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
	}
	
	

	

}
