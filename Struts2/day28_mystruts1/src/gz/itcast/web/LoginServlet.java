package gz.itcast.web;

import gz.itcast.entity.User;
import gz.itcast.service.UserService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 处理登陆请求
 * @author 贤元
 *
 */
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置编码方式
		request.setCharacterEncoding("utf-8");
		
		//获取用户名
		String name = request.getParameter("name");
		//获取密码
		String password = request.getParameter("password");
		
		//System.out.println("用户名：密码"+name+":"+password);//eric 1234
		
		//封装成javabean对象
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		//调用业务方法
		UserService service = new UserService();
		User loginUser = service.login(user);
		if(loginUser == null){
			//没有此用户登陆不成功  导航试图,跳转到登陆失败页面
			response.sendRedirect(request.getContextPath()+"/error.jsp");//request.getContextPath()获取web应用路径：day28_mystruts01
			return;
		}else{
			//登陆成功 导航到视图
			
			//把用户数据保存到session域中
			//request.getSession().setAttribute("user", user);
			
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
