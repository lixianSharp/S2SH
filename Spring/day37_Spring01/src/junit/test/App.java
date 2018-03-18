package junit.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.action.UserAction;


public class App {
	
	//用以前的方式做的
	@Test
	public void testApp() throws Exception {
		UserAction userAction = new UserAction();
		userAction.execute();//UserDao.save()
	}
	
	
	//从IOC容器获取对象
	@Test
	public void testIOC() throws Exception {
		//容器对象(加载applicationContext.xml配置文件)
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		//获取userAction对象
		UserAction userAction = (UserAction) ac.getBean("userAction");
		userAction.execute();//UserDao.save()		
	}
	
	
}
