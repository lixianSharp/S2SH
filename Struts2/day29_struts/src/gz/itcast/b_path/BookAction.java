package gz.itcast.b_path;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 图书模块
 * @author 贤元
 *
 */
public class BookAction extends ActionSupport{
	//添加方法
	public String add() throws Exception{
		System.out.println("BookAction.add()");
		return "add";//返回视图的标记
	}
	
	
	//查询方法
	public String list() throws Exception{
		System.out.println("BookAction.add()");
		return "list";
	}
}
