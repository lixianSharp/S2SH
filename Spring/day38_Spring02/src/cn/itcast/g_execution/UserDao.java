package cn.itcast.g_execution;


public class UserDao implements IUserDao {// 使用cglib的时候不能实现接口

	public void save() {
		System.out.println("保存...");
	}
	
	public void get(){
		System.out.println("获取。。。");
	}
}
