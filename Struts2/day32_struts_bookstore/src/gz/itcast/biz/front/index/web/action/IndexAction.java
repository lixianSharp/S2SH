package gz.itcast.biz.front.index.web.action;

import java.util.List;

import gz.itcast.biz.front.index.service.IndexService;
import gz.itcast.biz.front.index.service.IndexServiceImpl;
import gz.itcast.entity.Books;
import gz.itcast.entity.Types;
import gz.itcast.util.BaseAction;
/**
 * ��ҳģ���action
 * @author APPle
 *
 */
public class IndexAction extends BaseAction {
	
	private String typeId;//���շ���id
	private String id;//����ͼ��id

	IndexService service = new IndexServiceImpl();
	
	//��ʾ��ҳ�ķ���
	public String showIndex()throws Exception{
		
		//1)��serviceȡ����
		List<Types> types = service.queryTypes();
		
		//2)ͨ��requestMap�洢����
		requestMap.put("types", types);
		
		//3)������ҳ��ͼ���
		return "showIndex";
	}
	
	//��ʾ��ҳͼ�飨���ݷ���id��
	public String queryBooks()throws Exception{
		//��ȡ��������
		List<Books> books = service.queryBooks(typeId);
		//�������ݵ�request��
		requestMap.put("books", books);
		return "queryBooks";
	}

	//��ʾͼ������
	public String queryBook()throws Exception{
		//1)��ѯͼ����Ϣ
		Books books = service.queryBook(id);
		//2)���浽request��
		requestMap.put("book", books);
		//3)������ͼ
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
