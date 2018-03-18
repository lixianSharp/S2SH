package gz.itcast.util;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ImageServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		//����һ�������֤��ͼƬ����д���������
		OutputStream out = response.getOutputStream();
		String sCode = ValidateCodeUtils.genNewCode(out);
		//��sCode������û���¼ʱʹ��
		session.setAttribute("sCode", sCode);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
