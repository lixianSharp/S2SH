package gz.itcast.biz.front.index.service;

import gz.itcast.entity.Books;
import gz.itcast.entity.Types;

import java.util.List;

public interface IndexService {

	public List<Types> queryTypes();
	public List<Books> queryBooks(String typeId);
	public Books queryBook(String id);
}
