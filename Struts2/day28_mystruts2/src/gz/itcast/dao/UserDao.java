package gz.itcast.dao;

import gz.itcast.entity.User;

/**
 * 用户模块的dao
 * @author APPle
 *
 */
public class UserDao {

	/**
	 * 模拟查询用户的方法
	 * @param user
	 * @return
	 */
	public User queryUser(User user){
		if(user.getName().equals("eric") && user.getPassword().equals("1234")){
			//有这个用户，返回对应的用户对象
			return new User("eric","1234");
		}else{
			//没有这个用户
			return null;
		}
	}
	/**
	 * 模拟添加用户方法
	 * @param user
	 */
	public void addUser(User user){
		System.out.println(user);
		System.out.println("注册成功");
	}
}
