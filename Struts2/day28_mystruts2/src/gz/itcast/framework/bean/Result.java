package gz.itcast.framework.bean;
/**
 * 封装视图配置
 * <result name="success" type="redirect">/index.jsp</result>
 * @author APPle
 *
 */
public class Result {
	private String name;
	private String type;//跳转的类型(转发或者重定向)  默认为转发
	private String page;//跳转的页面
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	
}
