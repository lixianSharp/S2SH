package gz.itcast.c_constant;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
	
	public String login(){
		//查看当前request的characterEncoding编码
		//首先获取request
		String characterEncoding = ServletActionContext.getRequest().getCharacterEncoding();
		System.out.println("当前request的编码是："+characterEncoding);//UTF-8
		System.out.println("UserAction.login()");
		return SUCCESS;
	}
	
//	public String login() {
//		System.out.println("UserAction.login()");
//		return SUCCESS;
//	}
	
	
	public String regist(){
		System.out.println("UserAction.register()");
		return "regist";
	}
	
}
