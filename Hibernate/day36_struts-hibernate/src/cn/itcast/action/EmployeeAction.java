package cn.itcast.action;

import java.util.Map;

import cn.itcast.entity.Employee;
import cn.itcast.service.EmployeeService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

//http://localhost:8080/day36_struts-hibernate/emp_execute.action?id=3
public class EmployeeAction extends ActionSupport{
	
	private int id;
	public void setId(int id){
		this.id = id;
	}
	
	//Service对象
	private EmployeeService employeeService = new EmployeeService();
	
	public String execute() throws Exception {
		//主键查询
		Employee emp = employeeService.findById(id);
		//保存到request域
		//首先得获取到request域对象
		Map<String,Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
		request.put("employee", emp);
		
		return SUCCESS;//返回视图标记
	}
	
	
}
