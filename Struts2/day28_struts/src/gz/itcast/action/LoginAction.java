package gz.itcast.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;

/**
 * 这是一个普通的java类，不是一个Servlet
 * 
 * 实现一个strut2的一个Action接口
 * @author 贤元
 *
 */
/**
 * 登陆逻辑的Action
 * 
 * @author 贤元
 *
 */
public class LoginAction implements Action {
	// 默认方法
	// method不写默认为这个方法
	@Override
	public String execute() throws Exception {
		System.out.println("执行了LoginAction的execute方法");
		// 往request存放数据
		ServletActionContext.getRequest().setAttribute("lxyName", "李贤元");
		// 返回一个视图标记
		return "success";// 返回一个视图标记
	}

	public String login() throws Exception {

		System.out.println("执行了LoginAction的login方法");
		// 从request获取数据
		String lxyName = (String) ServletActionContext.getRequest()
				.getAttribute("lxyName");
		System.out.println("lxyName=" + lxyName);
		return "login";// 返回一个视图标记

	}
}
