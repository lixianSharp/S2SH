package gz.itcast.util;

import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * ����Action��Ļ���
 *   ���ҵ��action����������
 * @author APPle
 *
 */
public class BaseAction extends ActionSupport implements RequestAware,SessionAware,ApplicationAware{
	
	protected Map<String,Object> requestMap;//����request��
	protected Map<String,Object> sessionMap;//����session��
	protected Map<String,Object> applicationMap;//����context��
	
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
