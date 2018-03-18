package gz.itcast.a_i18n;

import com.opensymphony.xwork2.ActionSupport;
//国际化
public class UserAction extends ActionSupport{
	
	@Override
	public String execute() throws Exception {
		//获取资源包的内容
		//从struts.xml配置文件中获取数据。根据参数名获取参数值，或者是根据键获取值
		String name = super.getText("user");
		
		System.out.println(name);
		return SUCCESS;
	}
}
