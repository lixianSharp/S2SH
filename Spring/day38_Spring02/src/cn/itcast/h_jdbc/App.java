package cn.itcast.h_jdbc;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	//获取IOC容器对象
	private ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml",
																					App.class);
	
	
	public void testApp() throws Exception {
		DeptDao_2 dao = (DeptDao_2) ac.getBean("deptDao");
		dao.save(new Dept());
		System.out.println("ok");
	}
	
	
	public void testApp3() throws Exception {
		DeptDao_3 dao = (DeptDao_3) ac.getBean("deptDao");
		dao.save(new Dept());
		System.out.println("ok");
	}
	
	
	public void testApp4() throws Exception {
		DeptDao_4 deptDao = (DeptDao_4) ac.getBean("deptDao");
		
		//deptDao.save(new Dept());
		//System.out.println("ok");
		
		//deptDao.delete(5);
		
//		Dept dept=new Dept();
//		dept.setId(7);
//		dept.setName("newName");
//		deptDao.update(dept);
		
		
		//查询方法
		//Dept dept = deptDao.findById(12);
		//System.out.println(dept);
		
//		Dept dept = deptDao.findById(1);
//		System.out.println(dept);
//		System.out.println(dept.getName());

		//查询全部
		System.out.println(deptDao.getAll());
		
	}
	
	public static void main(String[] args) throws Exception {
		//new App().testApp();
		//new App().testApp3();
		new App().testApp4();
	}
	
}


