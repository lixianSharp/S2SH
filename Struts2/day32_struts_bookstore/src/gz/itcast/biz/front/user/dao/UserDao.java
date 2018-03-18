package gz.itcast.biz.front.user.dao;

import gz.itcast.entity.Users;

public interface UserDao {
	//根据名称找用户对象
	public Users findByName(String name);
	public Users findById(String id);
	public void save(Users user);
}
