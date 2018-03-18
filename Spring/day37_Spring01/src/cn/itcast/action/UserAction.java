package cn.itcast.action;

import cn.itcast.service.UserService;

/**
 * 多例Action实例：在访问的时候创建对象
 * @author 贤元
 *
 */
public class UserAction {
	
	//判断一个类是单例还是多例，主要看有没有维护成员变量、并且对成员变量进行修改！
	//创建service    单例：启动时创建
	private UserService userService;//=new UserService();//注释掉的部分是以前的方式做的
	//提供set方法，给外部容器注入
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
	//业务方法
	public String execute(){
		userService.save();
		return "success";//返回视图的标记
	}

}
