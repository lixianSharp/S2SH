package junit.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.action.DeptAction;
import cn.itcast.entity.Dept;
import cn.itcast.service.impl.IDeptService;


public class App {

	ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
	
	//测试保存
	@Test
	public void test_test() throws Exception {
		DeptAction deptAction = (DeptAction) ac.getBean("deptAction");
		deptAction.execute();
		
	}
	
	
	//测试保存
	@Test
	public void test_save() throws Exception {
		
		IDeptService deptService = (IDeptService) ac.getBean("deptService");
		
		deptService.save(new Dept());
	}
	
	//测试修改
	@Test
	public void test_update() throws Exception {
		Dept d = new Dept();
		d.setId(30);
		d.setName("综合5");
		
		IDeptService deptService = (IDeptService) ac.getBean("deptService");
		deptService.update(d);
	}
	
	
	
	//测试删除
	@Test
	public void test_delete() throws Exception {
		
		IDeptService deptService = (IDeptService) ac.getBean("deptService");
		
		deptService.delete(27);
	}
	
	
	
	//查询全部
	@Test
	public void test_getAll() throws Exception {
		
		IDeptService deptService = (IDeptService) ac.getBean("deptService");
		List<Dept> list = deptService.getAll();

		System.out.println(list);
	}
	

	
}
