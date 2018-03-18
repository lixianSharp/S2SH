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
 * �û�ģ���servlet
 * @author APPle
 *
 */
public class UserServlet extends BaseServlet {
	UserService service = new UserServiceImpl();
	/**
	 * �û����� �� /user?action=login
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//������������
		Users user = WebUtil.copyRequestToBean(request, Users.class);
		
		//��ȡ�û����������֤��
		String code = request.getParameter("code");
		
		//��ȡϵͳ���ɵ���֤��
		//��session���ȡϵͳ���ɵ���֤��
		HttpSession session = request.getSession(false);
		if(session!=null){
			String sCode = (String)session.getAttribute("sCode");
			//�Ա�
			if(!code.trim().equals(sCode.trim())){
				request.setAttribute("msg", "��֤�����");
				request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
				return;
			}
		}
		
		//��֤�û���
		Users loginUser = service.login(user.getName());
		
		if(loginUser!=null){
			//��֤����
			//�ȶ��û�������������md5����
			String digestPassword = MD5Util.md5(user.getPassword());
			//���ݿ��������û���������м��
			if(loginUser.getPassword().equals(digestPassword)){
				  //��¼�ɹ�
				//���û����ݱ��浽session��ȥ
				session.setAttribute("user", loginUser);
				//�����ǰsession����֤��
				session.removeAttribute("sCode");
				//ת����ҳ
				response.sendRedirect(request.getContextPath()+"/index?action=showIndex");
				return;
			}else{
				request.setAttribute("msg", "������������");
				request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
				return;
			}
		}else{
			request.setAttribute("msg", "�û���������");
			request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
			return;
		}
	}
}
