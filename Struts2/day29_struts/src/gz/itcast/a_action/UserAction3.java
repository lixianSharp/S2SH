package gz.itcast.a_action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * struts的Action的三种使用方式。
 * 
 * 第三种方式：继承ActionSupport类(推荐使用)
 * 		好处：
 * 			1）提供了常用的视图标记
 * 			2）提供了数据校验功能
 * 
 * @author 贤元
 *
 */
public class UserAction3 extends ActionSupport{
	public String login() throws Exception{
		System.out.println("执行了UserAction3中的login()方法");
		return SUCCESS;//SUCCESS是Action接口中的常量，代表"success"
	}
}
