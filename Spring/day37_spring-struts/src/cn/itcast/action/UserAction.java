package cn.itcast.action;

import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{
	
	public UserAction(){
		System.out.println("action实例创建");
	}
	
	@Override
	public String execute() throws Exception {
		System.out.println("UserAction.execute()");
		return SUCCESS;//返回视图标记
	}
	
}
