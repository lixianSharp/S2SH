package cn.itcast.c_hql;

import java.util.List;
import java.util.Set;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;
/**
 Hibernate除了支持面向对象的查询外，还支持原生态的sql语句查询，在数据库段写好的sql也可以直接在hibernate中执行！
	优缺点：
		1 .  对于一些比较负责的查询， hql实现不了， 这时候，本地sql查询就可以作为补充！
		2.   使用本地sql查询不能跨数据库平台！ 一旦更换了数据库，sql语句有可能会更改！ 

 * @author 贤元
 *
 */
public class App_3_sqlquery {
	//创建session的工厂
	private static SessionFactory sf;
	//初始化session的工厂  通过静态代码块来初始化，因为静态代码块只加载一次
	static{
		//通过配置管理器对象加载主配置文件，加载映射文件，并且创建session的工厂对象
		sf = new Configuration()
				.configure()//加载主配置文件hibernate.cfg.xml
				.addClass(Dept.class) //加载映射文件Dept.hbm.xml
				.addClass(Employee.class)//加载映射文件Employee.hbm.xml
				.buildSessionFactory();
	}
	
	//注意：如果使用sql本地查询的话，如果要在配置文件中写sql，应该写在这里面去<sql-query name=""></sql-query>
	@Test
	public void test_demo1() throws Exception {
		//创建session,通过session的工厂来创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		
		/**
		 Query接口中的方法
		 Query q = session.createQuery("");
		 
		 q.list();
		 q.uniqueResult();
		 q.iterate();
		 q.executeUpdate();
		 q.setFirstResult(0);
		 q.setMaxResults(0);
		 
		 */
		
		//本地sql查询
//		SQLQuery q = session.createSQLQuery("select * from t_dept");
//		List<Object[]> list = q.list();//把每一行数据封装为Object[],再添加到list集合
//		System.out.println(list);//[[Ljava.lang.Object;@7fafcb90, [Ljava.lang.Object;@6e5d9c6d, [Ljava.lang.Object;@43c8f0b6]

/*		for(int i=0;i<list.size();i++){
			Object[] values = list.get(i);
			System.out.println(values);
		}*/
		
		
		
		//把查询到的结果，自动封装为对象(对象必须有映射文件)
		SQLQuery q = session.createSQLQuery("select * from t_dept");
		q.addEntity(Dept.class);
		//会自动封装对象
		List<Dept> list = q.list();
		//遍历
		for(Dept dept :list){
			String deptName = dept.getDeptName();
			Set<Employee> emp = dept.getEmployees();
			for(Employee e:emp){
				System.out.println(deptName+":"+e.getName());
			}
		}

		//session.getNamedQuery("");
		

		//提交事务
		tx.commit();
		//关闭session
		session.close();
	}

}
