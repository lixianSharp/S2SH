package gz.itcast.biz.admin.book.web.action;

import gz.itcast.biz.admin.book.service.BookService;
import gz.itcast.biz.admin.book.service.BookServiceImpl;
import gz.itcast.biz.admin.types.service.TypesService;
import gz.itcast.biz.admin.types.service.TypesServiceImpl;
import gz.itcast.entity.Books;
import gz.itcast.entity.Types;
import gz.itcast.util.BaseAction;
import gz.itcast.util.WebUtil;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
/**
 * 后台图书模块的action
 * @author APPle
 *
 */
public class BookAction extends BaseAction implements ModelDriven<Books> {
	
	TypesService typeService = new TypesServiceImpl();
	BookService bookService = new BookServiceImpl();
	
	//接收上传图片数据
	private File attach;//图片
	private String attachFileName;//图片名称
	private String attachContentType;//图片类型
	private Books books = new Books();//接收图书的其他信息
	
	//注入保存图片的目录名称
	private String savePath;
	//添加前查询分类
	public String toAdd()throws Exception{
		//1)查询分类信息
		List<Types> types = typeService.queryTypes();
		//2)存放到request域
		requestMap.put("types", types);
		//3)返回视图
		return "toAdd";
	}
	
	
	//保存图书方法
	public String saveBook()throws Exception{
		//1)保存文件到硬盘
		//得到指定目录在机器上的绝对路径
		String path = ServletActionContext.getServletContext().getRealPath(savePath);
		//处理文件名
		String newFileName = WebUtil.uuid()+attachFileName.substring(attachFileName.lastIndexOf("."));
		FileUtils.copyFile(attach, new File(path+"/"+newFileName));

		//2)调用业务方法
		books.setImg(newFileName);
		bookService.saveBook(books);
		
		return "saveBook";
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Books getModel() {
		return books;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}


	public File getAttach() {
		return attach;
	}


	public void setAttach(File attach) {
		this.attach = attach;
	}


	public String getAttachFileName() {
		return attachFileName;
	}


	public void setAttachFileName(String attachFileName) {
		this.attachFileName = attachFileName;
	}


	public String getAttachContentType() {
		return attachContentType;
	}


	public void setAttachContentType(String attachContentType) {
		this.attachContentType = attachContentType;
	}


}
