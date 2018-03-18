package gz.itcast.biz.front.address.service;

import java.util.List;

import gz.itcast.biz.front.address.dao.AddressDao;
import gz.itcast.biz.front.address.dao.AddressDaoImpl;
import gz.itcast.entity.Address;

public class AddressServiceImpl implements AddressService {
	
	AddressDao dao = new AddressDaoImpl();
	
	public void save(Address address) {
		dao.save(address);
	}
	
	public List<Address> queryAddress(String userId){
		return dao.queryAddress(userId);
	}

}
