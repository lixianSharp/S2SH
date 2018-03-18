package gz.itcast.framework.bean;

import java.util.Map;

/**
 * 封装Action配置
 * @author APPle
 *<action name="register" class="gz.itcast.web.RegisterAction" method="execute">
			<result name="success" type="redirect">/login.jsp</result>
		</action>
 */
public class ActionMapping {
	private String name;
	private String className;
	private String method;//默认为execute
	
	//关联视图集  key: 视图的name   value：Result对象
	Map<String,Result> results = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Map<String, Result> getResults() {
		return results;
	}

	public void setResults(Map<String, Result> results) {
		this.results = results;
	} 
	
}
