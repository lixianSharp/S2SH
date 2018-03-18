package gz.itcast.biz.front.index.service;

import gz.itcast.biz.front.index.dao.IndexDao;
import gz.itcast.biz.front.index.dao.IndexDaoImpl;
import gz.itcast.entity.Books;
import gz.itcast.entity.Types;

import java.util.List;
/**
 * Ö÷Ò³µÄservice
 * @author APPle
 *
 */
public class IndexServiceImpl implements IndexService {
	IndexDao dao = new IndexDaoImpl();
	
	public List<Types> queryTypes() {
		return dao.queryTypes();
	}

	public List<Books> queryBooks(String typeId) {
		return dao.queryBooks(typeId);
	}

	public Books queryBook(String id) {
		return dao.queryBook(id);
	}

}
