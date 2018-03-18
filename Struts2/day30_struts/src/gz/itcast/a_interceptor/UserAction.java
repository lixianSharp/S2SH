package gz.itcast.a_interceptor;

import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
	
	public UserAction() {
		System.out.println("3)创建了UserAction对象");
		
	}
	
	public String register() throws Exception{
		System.out.println("5)调用了UserAction方法");
		return SUCCESS;
	}

}
