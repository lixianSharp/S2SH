package gz.itcast.filter;

import gz.itcast.entity.Users;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ǰ̨�û���Ȩ�޵Ĺ���
 * ���ﳵ��
	/buy?action=AddToCar
	����/buy
 	ҳ�棺/jsp/front/buy/*
�ջ���ַ:
	/address?action=xxxx
	����/address
	ҳ�棺 /jsp/front/address/*
����:
	/order?action=xxx
	����/order
	ҳ�棺 /jsp/front/order/*
 * @author APPle
 *
 */
public class UserSecurityFilter implements Filter{

	public void destroy() {
		
	}
	//ִ�й�������
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		//1)�ж��û��Ƿ��Ѿ���¼
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		
		HttpSession session = request.getSession(false);
		if(session==null){
			//��ת����¼ҳ��
			response.sendRedirect(request.getContextPath()+"/jsp/front/user/login.jsp");
			return;
		}else{
			Users loginUser = (Users)session.getAttribute("user");
			if(loginUser==null){
				//��ת����¼ҳ��
				response.sendRedirect(request.getContextPath()+"/jsp/front/user/login.jsp");
				return;
			}
		}
		//����
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
