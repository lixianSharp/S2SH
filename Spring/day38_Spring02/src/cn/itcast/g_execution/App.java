package cn.itcast.g_execution;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//自己实现aop编程
public class App {
	// 创建IOC容器对象
	private ApplicationContext ac = new ClassPathXmlApplicationContext(
			"applicationContext.xml", this.getClass());

	// jdk代理  jdk代理：则需要实现接口，并且用接口接收对象
	@Test
	public void testApp() throws Exception {
		// springIOC容器中获取对象，用接口接收！没有问题
		IUserDao userDao = (IUserDao) ac.getBean("userDao");
		System.out.println(userDao.getClass());// class com.sun.proxy.$Proxy9
		
		userDao.save();
		userDao.get();

		OrderDao orderDao = (OrderDao) ac.getBean("orderDao");
		orderDao.save();
		
		/**
		 * 执行这个方法之后的打印结果：
		 * class com.sun.proxy.$Proxy6
			【环绕前：】
			保存...
			【环绕后：】
			获取。。。
			【环绕前：】
			保存订单...
			【环绕后：】
		 */

	}
}
