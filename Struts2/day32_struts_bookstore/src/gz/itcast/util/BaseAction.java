package gz.itcast.util;

import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 所有Action类的基类
 *   存放业务action公共的内容
 * @author APPle
 *
 */
public class BaseAction extends ActionSupport implements RequestAware,SessionAware,ApplicationAware{
	
	protected Map<String,Object> requestMap;//操作request域
	protected Map<String,Object> sessionMap;//操作session域
	protected Map<String,Object> applicationMap;//操作context域
	
	public void setRequest(Map<String, Object> requestMap) {
		this.requestMap = requestMap;
	}

	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public void setApplication(Map<String, Object> applicationMap) {
		this.applicationMap = applicationMap;
	}

}
