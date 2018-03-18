package cn.itcast.b_inverse2;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;



public class App {
	//创建session的工厂
	private static SessionFactory sf;
	//初始化session的工厂，通过静态代码块，因为静态代码块只加载一次
	static{
		//通过配置管理器对象加载主配置文件，创建session工厂对象
		sf = new Configuration() //创建配置管理器对象
				.configure() //加载主配置文件
				.addClass(Dept.class) //加载映射文件
				.addClass(Employee.class) //加载映射文件
				.buildSessionFactory();//创建session的工厂对象
	}
	
	//A、保存
	// 只有inverse=false 表示有控制权的时候，才可以保存数据，处理数据的关系！
	// 否则，只能保存各自数据
	@Test
	public void testSave() {
		Session session=sf.openSession();
		session.beginTransaction();
		
		//保存的时候，最好通过多的一方维护关联关系，这样可以少产生sql  update语句
		
		//保存
		//部门
		Dept dept=new Dept();
		dept.setDeptName("应用开发部");
		
		//员工
		Employee emp1=new Employee();
		emp1.setName("王晓杰");
		
		Employee emp2=new Employee();
		emp2.setName("赵欢");
		
		/**
		 * 关系,通过一的一方维护（不推荐）
		 */
		
		dept.getEmployees().add(emp1);
		dept.getEmployees().add(emp2);
		session.save(emp1);
		session.save(emp2);
		session.save(dept);
		
		Set set = new HashSet();
		set.add(emp2);
		set.add(emp1);
		dept.setEmployees(set);
		
		session.getTransaction().commit();
		session.close();
	}
	
	//B、通过其中一方获取另外一方
	//是否设置inverse属性，对获取数据没有影响！(只要配置关系，就可以加载到关联数据)
	@Test
	public void testGet() throws Exception {
		//创建session对象，通过session的工厂来创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		//主键查询
		Dept dept = (Dept) session.get(Dept.class, 1);
		System.out.println(dept.getDeptName());//应用开发部
		System.out.println(dept.getEmployees());//[cn.itcast.a_one2many.Employee@1068e7a9, cn.itcast.a_one2many.Employee@129a01cb]
		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
	}
	
	
	//C：解除关系
	//inverse=false,即有控制权才可以解除关系！
	//如果为true，没有控制权，不能解除关系(不报错)！
	@Test
	public void test_release() throws Exception {
		//创建session对象，通过session的工厂来创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		//第一种方式解除关系  因为控制权从dept转移到了employee，所以下面的代码不能解除关系哦！！！
		//主键查询
		Dept dept = (Dept) session.get(Dept.class, 1);
		//解除关系  把主键清空  解除部门和员工关系
		dept.getEmployees().clear();
		session.update(dept);//这行写不写都一样
		
		/**
		 * 方式2解除关系(解除员工和部门的关系) 因为控制权转移给了employee，
		 * 								所以下面注释掉的代码可以解除关系的哦！
		 */
		//Employee emp = (Employee) session.get(Employee.class, 2);
		//解除员工与部门的关系
		//emp.setDept(null);
		
		
		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
	}
	
	
	//D：删除数据，对关系的影响
	/**
	 * inverse=true,如果数据有被其他外键关联，不能删除数据，包违反主外键约束！
	 * 			true,如果数据没有被其他表引用，可以直接删除。
	 * inverse=false,有控制权，始终可以删除！先清空所有引用，再删除自身
	 * @throws Exception
	 */
	@Test
	public void test_deleteDate() throws Exception {
		//创建session对象，通过session的工厂来创建
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		
		//主键查询
		Dept dept = (Dept) session.get(Dept.class,4);
		//删除部门
		session.delete(dept);
		
		//提交事务
		tx.commit();
		//关闭session
		session.close();
	}
}