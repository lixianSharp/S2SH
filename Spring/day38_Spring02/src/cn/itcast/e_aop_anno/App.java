package cn.itcast.e_aop_anno;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//自己实现aop编程
public class App {
	
	//创建IOC容器对象
	private ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml", this.getClass());
	
	//jdk代理
	@Test
	public void testApp() throws Exception {
		//springIOC容器中获取对象，用接口接收！没有问题
//		IUserDao userDao = (IUserDao)ac.getBean("userDao");
//		System.out.println(userDao.getClass());//class com.sun.proxy.$Proxy12
//		userDao.save();
//		
		
		//springIOC容器中获取对象，用实现类接收!报错，有问题！！！
		/**
		 * java.lang.ClassCastException: 
		 * 			com.sun.proxy.$Proxy13 cannot be cast to cn.itcast.e_aop_anno.UserDao
		 * 
		 * 		$Proxy13 implements IUserDao{}
		 * 
		 * 		user ud = $Proxy13
		 * 
		 */
		//总结：在springAop编程中，符合切入点表达式的目标类，如果目标对象有实现接口，容容器获取对象的时候，一定要通过接口接收!否则会报类型转换错误.
		UserDao userDao = (UserDao) ac.getBean("userDao");
		System.out.println(userDao.getClass());//
		userDao.save();
		
	}
	
	
	//cglib代理
	@Test
	public void testApp_cglib() throws Exception {
		UserDao userDao = (UserDao) ac.getBean("userDao");
		System.out.println(userDao.getClass());//class cn.itcast.e_aop_anno.UserDao$$EnhancerByCGLIB$$f233656a
		
		userDao.save();
		/**
		 * 执行上面一行代码的打印结果：
		 * 【环绕前：】
			【前置通知】开启事务。。
			保存》UserDao.save()。。
			【环绕后：】
			【后置通知】提交事务。。
		 */
	}
	
	
	//没有生成代理对象，因为没有被切入点表达式拦截
	@Test
	public void testApp_save_order() throws Exception {
		OrderDao orderDao = (OrderDao) ac.getBean("orderDao");
		System.out.println(orderDao.getClass());//class cn.itcast.e_aop_anno.OrderDao

		orderDao.save();//打印结果: 保存订单OrderDao.save()。。。
	}
	
}