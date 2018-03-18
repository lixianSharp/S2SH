package gz.itcast.b_ognl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

public class UserAction extends ActionSupport{
	// Action的参数数据
	private User user = new User("jacky",30);
	
	private Book book = new Book("java基础");

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	/**
	 * 在用户每次访问action对象业务方法时，
	 * 		struts2的框架会创建ActionContext对象，（OgnlCotext对象）（Action对象）
	 */
	@Override
	public String execute() throws Exception {
		//1)获取ActionContext对象
		ActionContext ac = ActionContext.getContext();
		
		//往request域中存放数据
		List<User> list = new ArrayList<User>();
		list.add(new User("eric",20));
		list.add(new User("jacky",30));
		list.add(new User("rose",40));
		list.add(new User("lucy",50));
		ac.getContextMap().put("userList", list);
		//往request域中存放数据
		Map<String,User> map = new HashMap<String,User>();
		map.put("100", new User("mark",20));
		map.put("101", new User("maxwell",30));
		map.put("102", new User("lily",40));
		//往request域中存放数据
		ac.getContextMap().put("userMap", map);
		
		//往request域中存放数据
		ac.getContextMap().put("request_data", "request_data");
		//往session域中存放数
		ac.getSession().put("session_data", "session_data");
		//往context域(也就是application域)中存放数据
		ac.getApplication().put("application_data", "application_data");
		
		//2）从ActionContext对象获取OgnlvalueStack对象(OGNL的值栈对象)
		ValueStack vs = ac.getValueStack();
		
		// 3)查看OgnValueStack对象的结构
		/**
		 * 	ValueStack接口，最终在项目中存储数据的对象是ValueStack的实现类OgnlValueStack

			ValueStack的数据存储结构：分为  List栈（根栈）  和  Map栈（非根栈）

			1）List栈主要存储Action对象和Provider代理对象	
			2）Map栈主要存放各个域存放的数据和用户的参数信息

		 */
		
		//往OgnlValueStack的根对象(List栈)存放数据
		vs.push(user);//往List存数据栈  栈顶
		vs.pop();//从List栈中 取出数据(栈顶元素)
		
		vs.push(book);//往List栈存数据
		
		return SUCCESS;
	}
}	
