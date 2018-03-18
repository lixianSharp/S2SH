package cn.itcast.c_cglib;
//目标对象,目标对象没有实现接口，所以用cglib代理，spring提供的代理
public class UserDao{
	//使用cglib代理的时候，目标对象不能实现接口
	
	public void save() {
		System.out.println("模拟保存对象:save()");
	}

	
	public void find() {
		System.out.println("查询:find()");
	}
	
	
}
