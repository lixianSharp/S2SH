package junit.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.entity.Dept;
import cn.itcast.service.IDeptService;

public class App {

	//加载配置文件
	private ApplicationContext ac = 
		new ClassPathXmlApplicationContext(
			"applicationContext-public.xml",
					"applicationContext-dao.xml",
					"applicationContext-service.xml");
	
	//从IOC容器中获取service对象
	private IDeptService deptService = (IDeptService) ac.getBean("deptService");
	
	@Test
	public void testSpring() throws Exception {
		System.out.println(ac);
		System.out.println(ac.getBean("sessionFactory"));
		System.out.println(ac.getBean("deptDao"));
	}
	
	@Test
	public void testSave() throws Exception {
		deptService.save(new Dept());
	}
	@Test
	public void testUpdate() throws Exception {
		Dept d = new Dept();
		d.setId(3);
		d.setName("研发部");
		deptService.update(d);
	}
	@Test
	public void testDelete() throws Exception {
		deptService.delete(33);
	}
	@Test
	public void testfind() throws Exception {
//		System.out.println(deptService.findById(1));
//		System.out.println(deptService.getAll());
//		System.out.println(deptService.getAll(2,2));
		System.out.println(deptService.getTotalCount());
	}
}









