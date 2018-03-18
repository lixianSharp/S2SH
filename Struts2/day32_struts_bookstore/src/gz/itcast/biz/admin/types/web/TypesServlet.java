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
 * 图书分类模块的servlet
 * @author APPle
 *
 */
public class TypesServlet extends BaseServlet {
	TypesService service = new TypesServiceImpl();
	/**
	 * 图书分类查询
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void queryTypes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//1)查询数据
		List<Types> types = service.queryTypes();
		//2)转发到jsp页面显示
		request.setAttribute("types", types);
		request.getRequestDispatcher("/jsp/admin/type/list.jsp").forward(request, response);
	}
	
	/**
	 * 保存分类
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void saveTypes(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
			//1)获取用户表单数据
		Types type = WebUtil.copyRequestToBean(request, Types.class);
		
		
			//2)调用业务方法保存分类
		service.saveTypes(type);
		
			//3)回显addback.jsp页面
		response.sendRedirect(request.getContextPath()+"/jsp/admin/type/addback.jsp");
	}
}
