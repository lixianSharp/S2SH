package gz.itcast.c_modeldriver;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 模型驱动:作用：可以防止表单中的数据重复提交
 * 使用模型驱动方式获取请求数据
 * 
 * 必须实现泛型的模型驱动接口：ModelDriven<E>
 * @author 贤元
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User>{

	
	/**
	 * 注意：使用模型驱动的方式，存放数据的模型引用不能为空
	 */
	private User user = new User();
	/**
	 * 该方法struts2可以把值栈中的请求参数封装到User对象中
	 */
	@Override
	public User getModel() {
		return user;
	}
	
	public String register() throws Exception{
		System.out.println(user);
		
		/**
		 * 传递一些内容到ui页面  ui.jsp
		 */
		List<User> list = new ArrayList<User>();
		list.add(new User("eric","1234"));
		list.add(new User("jacky","5678"));
		list.add(new User("rose","4321"));
		
		//将数据保存在request域中，首先得通过ActionContext对象获取request域对象
		ActionContext ac = ActionContext.getContext();
		//获取request域对象
		Map<String,Object> request = ac.getContextMap();
		request.put("userList", list);
		
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("101", "eric");
		map.put("102", "mark");
		map.put("103", "maxwell");
		map.put("104", "rose");
		
		//将数据保存在request域中
		request.put("userMap", map);
		
		request.put("userName", "jacky");
		request.put("userPwd", "123456");
		
		//以上的数据都是保存在Ognl值栈中的非根对象中，所以获取的时候需要用#号
		
		//返回视图标记
		//return SUCCESS;
		return "ui";
	}
	
}
