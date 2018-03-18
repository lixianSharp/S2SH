package gz.itcast.action;

import com.opensymphony.xwork2.Action;


/**
 * 实现一个Action接口
 * @author 贤元
 *
 */
public class BookAction implements Action{
	public String execute() throws Exception {
		System.out.println("执行了BookAction.execute()");
		return "success";
	}
}
