package gz.itcast.biz.front.address.dao;

import gz.itcast.entity.Address;

import java.util.List;

public interface AddressDao {
	
	public void save(Address address);
	//�����û�id��ѯ��ַ�б�
	public List<Address> queryAddress(String userId);
}
