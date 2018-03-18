package gz.itcast.web;

import gz.itcast.entity.User;
import gz.itcast.framework.Action;
import gz.itcast.service.UserService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterAction implements Action{

	@Override
	public String excute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//设置编码格式
		request.setCharacterEncoding("utf-8");
		
		//1、获取用户参数
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		//2、封装javabean对象
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		
		//3、调用业务方法
		UserService service = new UserService();
		service.register(user);
		
		//4、导航视图
		return "/redirect:/login.jsp";//请求重定向
	}
	
}
