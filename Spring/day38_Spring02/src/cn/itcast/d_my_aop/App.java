package cn.itcast.d_my_aop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	
	//获取IOC容器
	private ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml", getClass());
	
	@Test
	public void testApp() throws Exception {
		//获取UserDao对象
		UserDao userDao = (UserDao)ac.getBean("userDao");
		
		//执行方法
		userDao.save();
	}
}
