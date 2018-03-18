package gz.itcast.biz.front.index.web.servlet;



import gz.itcast.biz.front.index.service.IndexService;
import gz.itcast.biz.front.index.service.IndexServiceImpl;
import gz.itcast.entity.Books;
import gz.itcast.entity.Types;
import gz.itcast.util.BaseServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * ��ҳģ���servlet
 * @author APPle
 *
 */
public class IndexServlet extends BaseServlet {

	IndexService service = new IndexServiceImpl();
	
	/**
	 * ��ʾ��ҳͼ�����ķ���
	 * 	�û������URL�� /index?action=showIndex 
	 */
	
	public void showIndex(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//System.out.println("showIndex");
		
		//1)��serviceȡ����
		List<Types> types = service.queryTypes();
		
		//2)ͨ�������������ݵ�jspҳ��
		request.setAttribute("types",types);
		request.getRequestDispatcher("/jsp/front/index/shopIndex.jsp").forward(request, response);
	}
	
	/**
	 * 
	 * ���ݷ���id��ʾͼ����Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 *  /index?action=queryBooks
	 */
	public void queryBooks(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			//System.out.println("queryBooks");
		//����id
		String typeId = request.getParameter("typeId");
		//��ȡ��������
		List<Books> books = service.queryBooks(typeId);
		//������ת��jspҳ��
		request.setAttribute("books", books);
		request.getRequestDispatcher("/jsp/front/book/list.jsp").forward(request, response);
	}
	/**
	 * ����id��ѯ��Ӧ��ͼ����ϸ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * /index?action=queryBook&id=xxx
	 */
	public void queryBook(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		 String id = request.getParameter("id");
		 Books books = service.queryBook(id);
		 request.setAttribute("book", books);
		 request.getRequestDispatcher("/jsp/front/book/detail.jsp").forward(request, response);
	}
	
}
