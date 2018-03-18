package cn.itcast.b_tx_jdbc_anno2;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	@Test
	public void testApp() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml",getClass());
		IDeptService deptService = (IDeptService) ac.getBean("deptService");
		deptService.save();
	}
}
