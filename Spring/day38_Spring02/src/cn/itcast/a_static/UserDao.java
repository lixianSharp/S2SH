package cn.itcast.a_static;

//目标对象
public class UserDao implements IUserDao {
	@Override
	public void save() {
		System.out.println("目标对象的save()方法");
	}
}
