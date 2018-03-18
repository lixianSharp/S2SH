package gz.itcast.e_scope;

import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 这也是第三种方式：只是把通用的直接抽取出来放到一个自定义BaseAction中了。
 * 
 * 基础Action 在这个基础Action当前注入了三个域对象的操作Map集合
 * 
 * @author 贤元
 *
 */
public class BaseAction extends ActionSupport implements RequestAware,
		SessionAware, ApplicationAware {
	protected Map<String, Object> requestMap;// protected只能在子类中使用 request域对象
	protected Map<String, Object> sessionMap;// session域对象
	protected Map<String, Object> contextMap;// context域对象

	@Override
	public void setApplication(Map<String, Object> context) {
		// TODO Auto-generated method stub
		this.contextMap = context;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.sessionMap = session;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		// TODO Auto-generated method stub
		this.requestMap = request;
	}

}
