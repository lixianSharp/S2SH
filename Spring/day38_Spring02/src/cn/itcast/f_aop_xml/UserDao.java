package cn.itcast.f_aop_xml;

public class UserDao implements IUserDao{

	@Override
	public void save() {
		System.out.println("保存。。。");
		//int i = 1/0;//如果这行代码没有注释，则会发生回滚
	}
	

}
