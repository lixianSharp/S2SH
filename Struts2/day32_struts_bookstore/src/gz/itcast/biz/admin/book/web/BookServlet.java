package gz.itcast.biz.admin.book.web;



import gz.itcast.biz.admin.book.service.BookService;
import gz.itcast.biz.admin.book.service.BookServiceImpl;
import gz.itcast.biz.admin.types.service.TypesService;
import gz.itcast.biz.admin.types.service.TypesServiceImpl;
import gz.itcast.entity.Books;
import gz.itcast.entity.Types;
import gz.itcast.util.BaseServlet;
import gz.itcast.util.WebUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
/**
 * 后台图书模块
 * @author APPle
 *
 */
public class BookServlet extends BaseServlet {
	TypesService typeService = new TypesServiceImpl();
	BookService bookService = new BookServiceImpl();
	/**
	 * 跳转到admin/book/add.jsp,跳转之前先查询分类信息
	 */
	public void toAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			List<Types> types = typeService.queryTypes();
			request.setAttribute("types", types);
			request.getRequestDispatcher("/jsp/admin/book/add.jsp").forward(request, response);
		
	}
	
	/**
	 * 添加图书（且上传保存图书图片文件）
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void saveBook(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		try {
			//1)创建DiskFileItemFactory，设置缓存大小，和缓存目录
			DiskFileItemFactory factory = new DiskFileItemFactory(10*1024, new File("e:/temps"));
			
			//2)创建ServletFileUpload对象
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			//3)解析表单内容（包含file表单，普通表单）
			List<FileItem> list = upload.parseRequest(request);
			
			//4)遍历
			Books book = new Books();
			//该变量用于存储分类信息
			List<String> types = new ArrayList<String>();
			if(list!=null){
				for (FileItem item : list) {
					//判断是否是普通表单
					if(item.isFormField()){
						//普通表单
						if(item.getFieldName().equals("name")){
							book.setName(item.getString("utf-8"));
						}
						if(item.getFieldName().equals("price")){
							book.setPrice(Double.parseDouble(item.getString("utf-8")));
						}
						if(item.getFieldName().equals("auth")){
							book.setAuth(item.getString("utf-8"));
						}
						if(item.getFieldName().equals("rebate")){
							book.setRebate(Double.parseDouble(item.getString("utf-8")));
						}
						//接收图书分类，有几个分类就执行获取几次
						
						if(item.getFieldName().equals("types")){
							types.add(item.getString("utf-8"));
						}
						
					}else{
						//file文件
						//1)保存文件到硬盘上
						//1.1 生成一个唯一的文件名
						String uuid = WebUtil.uuid();
						String name = item.getName();
						name = uuid+name.substring(name.lastIndexOf("."));
						
						//1.2 保存到服务器的当前项目的img目录下
						//得到当前项目的某个目录（或文件）的绝对路径
						String path = this.getServletContext().getRealPath("/imgs");
						FileUtils.copyInputStreamToFile(item.getInputStream(), new File(path+"/"+name));
						//删除临时文件
						item.delete();
						
						book.setImg(name);
						
					}
				}
				//设置图书的分类
				book.setTypesId(types);
			}
			//保存数据
			bookService.saveBook(book);
			
			
			response.sendRedirect(request.getContextPath()+"/jsp/admin/book/addsucc.jsp");
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

	}
}
