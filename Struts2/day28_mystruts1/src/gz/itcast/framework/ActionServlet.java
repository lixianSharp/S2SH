package gz.itcast.framework;

import gz.itcast.web.LoginAction;
import gz.itcast.web.RegisterAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 总控制器
 * @author 贤元
 *
 */
public class ActionServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1、获取用户的请求(login.action / register.action)
		String uri = request.getRequestURI();//  /day28_mystruts01/login.jsp  如何获取到login这个标记
		
		String pathName = uri.substring(uri.lastIndexOf("/")+1,uri.lastIndexOf("."));//login
		
		
		//2、根据用户的请求，创建不同的Action对象
		Action action = null;
		if(pathName.equals("login")){
			action = new LoginAction();
		} else if(pathName.equals("register")){
			action = new RegisterAction();
		}
		//...
		//3、调用Action对象里面的execute方法  得到对应的视图
		String view = action.excute(request, response);
		
		//获取需要跳转的页面，然后进行跳转
		if(view.contains("redirect:")){
			//截取视图地址
			view = view.substring(view.lastIndexOf(":")+1);//   /index.jsp
			
			//重定向
			response.sendRedirect(request.getContextPath()+view);//  day28_mystruts01/index.jsp
		}else{
			//转发
			request.getRequestDispatcher(view).forward(request, response);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
