package gz.itcast.service;

import gz.itcast.dao.UserDao;
import gz.itcast.entity.User;

public class UserService {
	UserDao dao = new UserDao();
	/**
	 * 登录方法
	 * @param user
	 * @return
	 */
	public User login(User user){
		return dao.queryUser(user);
	}
	/**
	 * 注册方法
	 * @param user
	 */
	public void register(User user){
		dao.addUser(user);
	}
}
