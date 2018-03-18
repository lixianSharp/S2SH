package gz.itcast.a_action;

import com.opensymphony.xwork2.Action;

/**
 * struts2的Action的三种使用方式
 * 
 * 第二种方式：实现Action接口
 * 	 1）定义了默认的execute方法的标准
 * 	 2）提供了项目中常用的视图标记
 * 
 * @author 贤元
 *
 */
public class UserAction2 implements Action {

	public String login() throws Exception {
		System.out.println("执行了UserAction2的login()方法");
		return "success";// 返回视图的标记
	}

	@Override
	public String execute() throws Exception {
		return null;
	}
}
