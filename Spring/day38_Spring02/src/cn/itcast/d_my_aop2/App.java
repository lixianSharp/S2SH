package cn.itcast.d_my_aop2;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//测试
public class App {
	//创建IOC容器
	private ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml", 
																				this.getClass());
	
	
	@Test
	public void testApp() throws Exception {
		IUserDao userDao = (IUserDao)ac.getBean("userDao");
		userDao.save();//打印结果:保存
		System.out.println("========");
		
		IUserDao userDao2 = (IUserDao) ac.getBean("userDaoProxy");
		userDao2.save();
		/**
		 * 上一条代码打印结果:
		 *  开启事务
			保存
			提交事务
		 */
	}
}
