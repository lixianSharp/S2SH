package gz.itcast.biz.front.user.dao;

import gz.itcast.entity.Users;

public interface UserDao {
	//�����������û�����
	public Users findByName(String name);
	public Users findById(String id);
	public void save(Users user);
}
