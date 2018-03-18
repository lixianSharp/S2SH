package gz.itcast.biz.front.address.dao;

import gz.itcast.entity.Address;

import java.util.List;

public interface AddressDao {
	
	public void save(Address address);
	//根据用户id查询地址列表
	public List<Address> queryAddress(String userId);
}
