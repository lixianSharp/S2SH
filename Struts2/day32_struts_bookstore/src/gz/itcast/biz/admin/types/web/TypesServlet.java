package gz.itcast.biz.admin.types.web;



import gz.itcast.biz.admin.types.service.TypesService;
import gz.itcast.biz.admin.types.service.TypesServiceImpl;
import gz.itcast.entity.Types;
import gz.itcast.util.BaseServlet;
import gz.itcast.util.WebUtil;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * ͼ�����ģ���servlet
 * @author APPle
 *
 */
public class TypesServlet extends BaseServlet {
	TypesService service = new TypesServiceImpl();
	/**
	 * ͼ������ѯ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void queryTypes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//1)��ѯ����
		List<Types> types = service.queryTypes();
		//2)ת����jspҳ����ʾ
		request.setAttribute("types", types);
		request.getRequestDispatcher("/jsp/admin/type/list.jsp").forward(request, response);
	}
	
	/**
	 * �������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void saveTypes(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
			//1)��ȡ�û�������
		Types type = WebUtil.copyRequestToBean(request, Types.class);
		
		
			//2)����ҵ�񷽷��������
		service.saveTypes(type);
		
			//3)����addback.jspҳ��
		response.sendRedirect(request.getContextPath()+"/jsp/admin/type/addback.jsp");
	}
}
