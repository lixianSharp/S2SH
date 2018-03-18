package gz.itcast.service;

import gz.itcast.dao.UserDao;
import gz.itcast.entity.User;

public class UserService {

	UserDao dao = new UserDao();
	
	/**
	 * 登陆方法
	 * @param user
	 * @return
	 */
	public User login(User user){
		return dao.queryUser(user);//如果查询到该用户，才能让其登陆
	}
	
	/**
	 * 注册方法     
	 * @param user
	 */
	public void register(User user){
		dao.addUser(user);//注册方法就是添加用户啊
	}
	
	
	
}
