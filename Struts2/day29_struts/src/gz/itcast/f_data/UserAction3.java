package gz.itcast.f_data;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 2）请求参数数据的封装的第二种方式：直接把参数赋值给一个javabean对象(推荐使用这种方式)
 * 		请求参数的赋值
 * @author 贤元
 *
 */

public class UserAction3 extends ActionSupport implements ModelDriven<User>{
	
	//创建一个javabean对象
	private User user = new User();
	
	//实现模型驱动
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	public String register() throws Exception{
		//使用表单的数据
		System.out.println(user);
		
		JSONObject json = new JSONObject();
		json.put("result", "注册成功");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(json.toString());
		return null;
	}
}
