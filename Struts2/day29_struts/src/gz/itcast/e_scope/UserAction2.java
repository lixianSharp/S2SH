package gz.itcast.e_scope;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 3)struts2提供的第三种使用域对象的方法。(当Action对象方法比较多的时候，推荐使用这种方式)
 * 通过struts2提供的接口注入到Action对象中。 特点： 1）必须依赖struts2接口！ 2）可以在action对象的所有业务方法中使用
 * 
 * @author 贤元
 *
 */
// 实现三个接口 推荐用这种方式使用域对象
public class UserAction2 extends ActionSupport implements RequestAware,
		SessionAware, ApplicationAware {
	private Map<String, Object> requestMap;// request域
	private Map<String, Object> sessionMap;// session域
	private Map<String, Object> contextMap;// context域

	// struts2自动会把操作request域的map集合传入,struts2会自动调用这个方法
	@Override
	public void setRequest(Map<String, Object> request) {
		this.requestMap = request;
	}

	// struts2自动会把操作session域的map集合传入
	@Override
	public void setSession(Map<String, Object> session) {
		this.sessionMap = session;
	}

	// struts2会自动把操作context域的map集合传入
	@Override
	public void setApplication(Map<String, Object> context) {
		this.contextMap = context;
	}

	public String list() throws Exception {
		// 1）从数据库中的到数据
		List<String> list = new ArrayList<String>();
		list.add("eric");
		list.add("jacky");
		list.add("rose");

		// 往request域中存放数据
		requestMap.put("request_list", list);
		// 往session域中存放数据
		sessionMap.put("session_list", list);
		// 往context域中存放数据
		contextMap.put("context_list", list);

		return "success";
	}
}
