package gz.itcast.b_path;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 用户模块
 * 
 * @author 贤元
 *
 */
public class UserAction extends ActionSupport {
	// 登陆方法
	public String login() throws Exception {

		System.out.println("UserAction.login()");
		return "login";// 返回视图的标记
	}

	// 注册方法
	public String regist() throws Exception {
		System.out.println("UserAction.register()");
		return "regist";// 返回视图的标记
	}
}
