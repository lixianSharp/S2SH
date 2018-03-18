package cn.itcast.action;

import cn.itcast.service.impl.IDeptService;

public class DeptAction {
	//容器注入service
	private IDeptService deptService;//private DeptService deptService;这里应该用接口，不能用实现
	public void setDeptService(IDeptService deptService) {
		this.deptService = deptService;
	}


	public String execute(){
		System.out.println("deptService:  "+deptService);
		return null;
	}
}
