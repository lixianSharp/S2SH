package gz.itcast.biz.front.address.service;

import java.util.List;

import gz.itcast.entity.Address;

public interface AddressService {
	public void save(Address address);
	public List<Address> queryAddress(String userId);
}
