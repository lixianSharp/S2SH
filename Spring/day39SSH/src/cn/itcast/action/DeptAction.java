package cn.itcast.action;

import org.springframework.stereotype.Controller;

import cn.itcast.entity.Dept;
import cn.itcast.service.IDeptService;

import com.opensymphony.xwork2.ActionSupport;

public class DeptAction extends ActionSupport{
	
	// 容器注入，注意不能使用实现类（jdk代理）
	private IDeptService deptService;
	public void setDeptService(IDeptService deptService) {
		this.deptService = deptService;
	}
	
	// 接收请求参数
	private int id;
	public void setId(int id) {
		this.id = id;
	}

	// 默认保存
	public String execute() {
		System.out.println("默认执行的方法");
		deptService.save(new Dept());
		return "success";
	}
	
	public String delete() {
		deptService.delete(id);
		return "delete";
	}
	
	public String findById() {
		System.out.println(deptService.findById(id));
		return "findById";
	}
}
