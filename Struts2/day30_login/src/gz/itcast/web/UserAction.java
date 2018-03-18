package gz.itcast.web;

import gz.itcast.entity.User;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{
	private User user;//接收表单的数据
	
	//让从表单提交过来的数据注入到user对象中
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	//注册方法
	public String register() throws Exception{
		System.out.println("调用了UserAction方法");
		return SUCCESS;
	}
	
	/**
	 * 这个方法是用于测试拦截器有没有用的啊，如果直接运行这个方法的话就会被拦截器拦截掉，
	 * 				强制跳转到/login.jsp页面。如果没有被拦截器拦截的话就会跳转到/lixian.jsp页面
	 * @return
	 * @throws Exception
	 */
	public String lixian() throws Exception{
		return "lixian";
	}
	
	//登陆方法    执行完Action的业务方法之后还要去执行拦截器的放行方法之后的代码
	public String login() throws Exception{
		System.out.println(user);
		if("eric".equals(user.getName()) && "1234".equals(user.getPassword()) ){
			//登陆成功
			//1)保存数据到session域中
			//首先通过ActionContext获取sesson域
			ActionContext ac = ActionContext.getContext();
			//获取session域对象
			Map<String,Object> session = ac.getSession();
			//往session域中存放数据
			session.put("user", user);
			
			//2）跳转到用户主页
			return SUCCESS;//返回视图标记			
		}else{
			//登陆失败
			return ERROR;//返回视图标记
		}
		
	}
/**
 * Action中的一些源码:
 * public abstract interface com.opensymphony.xwork2.Action {
  
  // Field descriptor #4 Ljava/lang/String;
  public static final java.lang.String SUCCESS = "success";
  
  // Field descriptor #4 Ljava/lang/String;
  public static final java.lang.String NONE = "none";
  
  // Field descriptor #4 Ljava/lang/String;
  public static final java.lang.String ERROR = "error";
  
  // Field descriptor #4 Ljava/lang/String;
  public static final java.lang.String INPUT = "input";
  
  // Field descriptor #4 Ljava/lang/String;
  public static final java.lang.String LOGIN = "login";
  
  // Method descriptor #16 ()Ljava/lang/String;
  public abstract java.lang.String execute() throws java.lang.Exception;
}
 */
}
