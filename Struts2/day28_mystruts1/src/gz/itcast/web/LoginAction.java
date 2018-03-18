package gz.itcast.web;

import gz.itcast.entity.User;
import gz.itcast.framework.Action;
import gz.itcast.service.UserService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理登陆请求的代码逻辑
 * 这是一个普通的类
 * @author 贤元
 *
 */
public class LoginAction implements Action{

	@Override
	public String excute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//设置参数编码
		request.setCharacterEncoding("utf-8");
		//1、获取用户参数
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		//封装成javabean对象
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		
		//调用业务方法
		UserService service = new UserService();
		User loginUser = service.login(user);
		if(loginUser == null){
			//没有此用户，登陆不成功，返回视图地址
			//区分重定向和转发
			//如果带着 redirect:/error.jsp 则为请求重定向
			//如果带着 /error.jsp 默认是转发
			return "/error.jsp";
		}else{
			//把用户数据保存到session域中
			
			//登陆成功，返回视图地址
			return "redirect:/index";//请求重定向
		}
		
	}
	
}
