package gz.itcast.biz.front.index.dao;

import gz.itcast.entity.Books;
import gz.itcast.entity.Types;

import java.awt.print.Book;
import java.util.List;

public interface IndexDao {

	//��ѯͼ�����
	public List<Types> queryTypes();
	//���ݷ���id��ѯ��Ӧ��ͼ��
	public List<Books> queryBooks(String typeId);
	//����id��ѯ��Ӧ��ͼ��
	public Books queryBook(String id);
}
