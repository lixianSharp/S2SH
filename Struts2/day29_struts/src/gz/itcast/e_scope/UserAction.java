package gz.itcast.e_scope;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * struts2数据共享的三种方式
 * 
 * @author 贤元
 *
 */
public class UserAction extends ActionSupport {

	/**
	 * 这种用法有问题!!因为ActionContext对象的构造是通过拦截器创建的， 而拦截器的执行是在创建UserAction之后
	 */
	/*
	 * ActionContext ac=null;
	 * 
	 * public UserAction(){ ac=ActionContext.getContext(); }
	 */

	/**
	 * 业务方法是在拦截器之后被执行的，所有ActionContext被拦截器成功创建。
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		/**
		 * 第一种方式
		 */
		// 1)从数据库中得到数据
		List<String> list = new ArrayList<String>();
		list.add("eric");
		list.add("jacky");
		list.add("rose");

		// 2)用request,session,context域对象来共享数据
		/*
		 * //** struts2提供的第一种使用域对象的方法。(如果单纯的使用域对象存取数据，不推荐使用这种方式)
		 * 
		 * 特点： 1）依赖servlet原生的api 2）只能在action的某个业务方法中使用
		 *//*
			 * //获取request对象 HttpServletRequest
			 * request=ServletActionContext.getRequest(); //将数据保存在request域中
			 * request.setAttribute("request_list",list);
			 * 
			 * //获取session域对象 HttpSession
			 * session=ServletActionContext.getRequest().getSession(true);
			 * //将数据保存在session域中 session.setAttribute("session_list", list);
			 * 
			 * //获取context域 ServletContext context =
			 * ServletActionContext.getServletContext(); //将数据保存在context域中
			 * context.setAttribute("context_list", list);
			 */

		// 得到客户端请求的相关数据(这种情况下就必须使用第一种方式)
		/**
		 * 注意：如果用到了request/session/servletContext对象中的除了存取数据意外的其他方法，
		 * 就必须使用ServletActionContext来获取数据
		 * 
		 */
		ServletActionContext.getRequest().getMethod();

		// 第二种方式
		/**
		 * 第二种方式：struts2提供的第二种使用域对象的方法
		 * ActionContext对象：action的上下文对象，在ActionContext对象中提供操作不同域对象的Map集合
		 * 特点：不依赖Servlet原生的api，方便测试。
		 */
		// 得到context域对象
		ActionContext ac = ActionContext.getContext();

		// 得到操作request域的map集合(操作Map集合就等同于操作了request域的数据)
		Map<String, Object> requestMap = ac.getContextMap();
		requestMap.put("request_list", list);// 放到request域中
		// 得到操作session域的map集合
		Map<String, Object> sessionMap = ac.getSession();
		sessionMap.put("session_list", list);// 将数据保存在session域中
		// 得到操作context域的map集合
		Map<String, Object> contextMap = ac.getApplication();
		contextMap.put("context_list", list);// 将数据保存在context域中

		return SUCCESS;
	}

}
