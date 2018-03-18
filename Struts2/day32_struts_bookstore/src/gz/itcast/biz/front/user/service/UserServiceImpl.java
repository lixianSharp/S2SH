package gz.itcast.biz.front.user.service;

import gz.itcast.biz.front.user.dao.UserDao;
import gz.itcast.biz.front.user.dao.UserDaoImpl;
import gz.itcast.entity.Users;

public class UserServiceImpl implements UserService {
	UserDao dao = new UserDaoImpl(); 
	
	public Users login(String name) {
		return dao.findByName(name);
	}

	public void register(Users user) {
		dao.save(user);
	}

}
