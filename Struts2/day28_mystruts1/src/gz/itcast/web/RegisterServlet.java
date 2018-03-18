package gz.itcast.web;

import gz.itcast.entity.User;
import gz.itcast.service.UserService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 处理注册请求
 * @author 贤元
 *
 */
public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//1、获取用户参数
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		//2、封装javabean对象
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		
		//3、调用业务的方法
		UserService service = new UserService();
		service.register(user);
		
		//4、导航视图
		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
