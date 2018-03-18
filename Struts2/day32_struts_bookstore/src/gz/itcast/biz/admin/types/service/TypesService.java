package gz.itcast.biz.admin.types.service;

import gz.itcast.entity.Types;

import java.util.List;

public interface TypesService {
	public List<Types> queryTypes();
	public void saveTypes(Types type);
}
