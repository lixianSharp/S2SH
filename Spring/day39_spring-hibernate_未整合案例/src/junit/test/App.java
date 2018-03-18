package junit.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.entity.Dept;
import cn.itcast.service.DeptService;


public class App {

	public static void main(String[] args) throws Exception {
		new App().testApp();
	}
	
	public void testApp() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
		DeptService deptService = (DeptService) ac.getBean("deptService");
		System.out.println(deptService);
		deptService.save(new Dept());
	}
}
