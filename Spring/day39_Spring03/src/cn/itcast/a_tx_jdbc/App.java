package cn.itcast.a_tx_jdbc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	
	public void testApp() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml", this.getClass());
		//如果实现了接口，需要用接口来接收
		IDeptService deptService = (IDeptService)ac.getBean("deptService");
		deptService.save();
	}
	
	public static void main(String[] args) throws Exception {
		new App().testApp();
		System.out.println("ok");
	}
}
