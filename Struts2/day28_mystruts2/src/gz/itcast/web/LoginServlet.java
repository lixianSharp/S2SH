package gz.itcast.web;

import gz.itcast.entity.User;
import gz.itcast.service.UserService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 处理登录请求
 * @author APPle
 *
 */
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			//没有此用户,登录不成功,导航视图
			response.sendRedirect(request.getContextPath()+"/error.jsp");
			return;
		}else{
			//登录成功,导航视图
			
			//把用户数据保存到session域中
			
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			return;
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
