package cn.itcast.f_aop_xml;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	
	//创建IOC容器对象
	private ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml",
																				this.getClass());
	
	
	/**
	 * jdk代理,目标对象需要实现接口
	 */
	@Test
	public void testApp() throws Exception {
		//springIOC容器中获取对象，用接口接收！没有问题
		IUserDao userDao = (IUserDao)ac.getBean("userDao");
		System.out.println(userDao.getClass());//class com.sun.proxy.$Proxy4
		
		userDao.save();
		/**
		 * 执行上面一行代码的打印结果(这里没有出现异常，如果出现异常的话，结果会不一样)：
		 * 【环绕前：】
			【前置通知】开启事务..
			保存。。。
			【环绕后：】
			【后置通知】提交事务..
			[返回后通知]
			
			
			如果把UserDao中的那个方法的会出现异常的那行代码打开(不注释掉)，则打印结果如下：
			【环绕前：】
			【前置通知】开启事务..
			保存。。。
			【后置通知】提交事务..
			[异常通知]
		 */
		
		
	}
}
