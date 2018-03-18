package gz.itcast.biz.front.user.web;



import gz.itcast.biz.front.user.service.UserService;
import gz.itcast.biz.front.user.service.UserServiceImpl;
import gz.itcast.entity.Users;
import gz.itcast.util.BaseServlet;
import gz.itcast.util.MD5Util;
import gz.itcast.util.WebUtil;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 用户模块的servlet
 * @author APPle
 *
 */
public class UserServlet extends BaseServlet {
	UserService service = new UserServiceImpl();
	/**
	 * 用户请求 ： /user?action=login
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//拷贝表单的数据
		Users user = WebUtil.copyRequestToBean(request, Users.class);
		
		//获取用户的输入的验证码
		String code = request.getParameter("code");
		
		//获取系统生成的验证码
		//从session域获取系统生成的验证码
		HttpSession session = request.getSession(false);
		if(session!=null){
			String sCode = (String)session.getAttribute("sCode");
			//对比
			if(!code.trim().equals(sCode.trim())){
				request.setAttribute("msg", "验证码错误");
				request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
				return;
			}
		}
		
		//验证用户名
		Users loginUser = service.login(user.getName());
		
		if(loginUser!=null){
			//验证密码
			//先对用户输入的密码进行md5加密
			String digestPassword = MD5Util.md5(user.getPassword());
			//数据库的密码和用户的密码进行检查
			if(loginUser.getPassword().equals(digestPassword)){
				  //登录成功
				//把用户数据保存到session中去
				session.setAttribute("user", loginUser);
				//清除当前session的验证码
				session.removeAttribute("sCode");
				//转到首页
				response.sendRedirect(request.getContextPath()+"/index?action=showIndex");
				return;
			}else{
				request.setAttribute("msg", "密码输入有误");
				request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
				return;
			}
		}else{
			request.setAttribute("msg", "用户名不存在");
			request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
			return;
		}
	}
}
