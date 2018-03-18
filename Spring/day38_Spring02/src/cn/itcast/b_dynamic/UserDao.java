package cn.itcast.b_dynamic;

//目标对象，这里有实现接口，所有要用动态代理，也就是jdk代理
public class UserDao implements IUserDao {
	@Override
	public void save() {
		System.out.println("目标对象的save()方法");
	}

	@Override
	public void find() {
		System.out.println("查询");
	}
}
