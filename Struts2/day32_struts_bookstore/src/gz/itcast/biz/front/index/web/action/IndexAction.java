package gz.itcast.biz.front.index.web.action;

import java.util.List;

import gz.itcast.biz.front.index.service.IndexService;
import gz.itcast.biz.front.index.service.IndexServiceImpl;
import gz.itcast.entity.Books;
import gz.itcast.entity.Types;
import gz.itcast.util.BaseAction;
/**
 * 首页模块的action
 * @author APPle
 *
 */
public class IndexAction extends BaseAction {
	
	private String typeId;//接收分类id
	private String id;//接收图书id

	IndexService service = new IndexServiceImpl();
	
	//显示主页的方法
	public String showIndex()throws Exception{
		
		//1)到service取数据
		List<Types> types = service.queryTypes();
		
		//2)通过requestMap存储数据
		requestMap.put("types", types);
		
		//3)返回首页视图标记
		return "showIndex";
	}
	
	//显示首页图书（根据分类id）
	public String queryBooks()throws Exception{
		//获取分类数据
		List<Books> books = service.queryBooks(typeId);
		//保存数据到request域
		requestMap.put("books", books);
		return "queryBooks";
	}

	//显示图书详情
	public String queryBook()throws Exception{
		//1)查询图书信息
		Books books = service.queryBook(id);
		//2)保存到request域
		requestMap.put("book", books);
		//3)返回视图
		return "queryBook";
	}
	
	
	
	
	
	
	
	
	
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	
}
