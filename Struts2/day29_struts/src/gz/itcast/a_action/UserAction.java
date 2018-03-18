package gz.itcast.a_action;
/**
 * struts2的Action的三种使用方式。
 * 
 * 
 * 第一种方式：不需要实现或继承任何接口或类。不实现Action接口。
 * @author 贤元
 *
 */
public class UserAction {
	public String login() throws Exception{
		System.out.println("UserAction");
		return "success";
	}
}
