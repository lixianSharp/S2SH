package gz.itcast.f_data;

import com.opensymphony.xwork2.ActionSupport;

//请求参数数据的封装
/**
 * 1)第一种方式：直接把参数赋值给一个简单属性
 * 	  请求参数的赋值
 * @author 贤元
 *
 */
public class UserAction extends ActionSupport{

	//参数赋值(注入方式)
	private String name;
	private String password;
	private String gender;
	private String[] hobit;
	
	//参数通过这个set方法注入到Action中
	public void setName(String name) {
		this.name = name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setHobit(String[] hobit) {
		this.hobit = hobit;
	}
	
	//业务方法
	public String register() throws Exception{
		//接收并使用表单的数据
		System.out.println(name);
		System.out.println(password);
		System.out.println(gender);
		System.out.println(hobit);
		
		return SUCCESS;
	}
	
	
}
