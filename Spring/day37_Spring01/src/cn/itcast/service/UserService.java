package cn.itcast.service;

import cn.itcast.dao.UserDao;

public class UserService {
	//创建dao对象：单例，启动时创建
	private UserDao userDao;// =new UserDao();//注释掉的部分是以前的方式做的
	//提供set方法，给外部容器注入
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void save(){
		userDao.save();
	}
	
}
