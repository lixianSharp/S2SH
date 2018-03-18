package gz.itcast.web;

import gz.itcast.entity.User;
import gz.itcast.framework.Action;
import gz.itcast.service.UserService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 处理登录请求的代码逻辑
 * @author APPle
 *
 */
public class LoginAction implements Action{
	
	public String execute(HttpServletRequest request,HttpServletResponse response)
	throws ServletException, IOException{
		return null;
	}
	
	public String login(HttpServletRequest request,HttpServletResponse response)
		throws ServletException, IOException{
		
		request.setCharacterEncoding("utf-8");
		//1)获取用户参数
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		//封存成javabean对象
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		
		//2)调用业务方法
		UserService  service = new UserService();
		User loginUser = service.login(user);
		if(loginUser==null){
			//没有此用户,登录不成功,返回视图地址
			//区分重定向和转发
			//   redirect:/erorr.jsp  请求重定向
			//  /error.jsp   默认是转发
			return "input";
		}else{
			//把用户数据保存到session域中
			//登录成功,返回视图地址
			return "success";
		}
		
		
	}
}
