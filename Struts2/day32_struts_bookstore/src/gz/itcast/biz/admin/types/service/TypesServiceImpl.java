package gz.itcast.biz.admin.types.service;

import gz.itcast.biz.admin.types.dao.TypesDao;
import gz.itcast.biz.admin.types.dao.TypesDaoImpl;
import gz.itcast.entity.Types;

import java.util.List;

public class TypesServiceImpl implements TypesService {
	TypesDao dao = new TypesDaoImpl();
	
	public List<Types> queryTypes() {
		return dao.findAll();
	}

	public void saveTypes(Types type) {
		dao.saveTypes(type);
	}

}
