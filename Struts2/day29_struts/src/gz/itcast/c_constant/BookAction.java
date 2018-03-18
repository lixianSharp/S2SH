package gz.itcast.c_constant;

import com.opensymphony.xwork2.ActionSupport;

public class BookAction extends ActionSupport {
	public String add() {

		System.out.println("BookAction.add()");

		return SUCCESS;
	}
}
