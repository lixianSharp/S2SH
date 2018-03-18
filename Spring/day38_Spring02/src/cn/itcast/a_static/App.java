package cn.itcast.a_static;
/**
 * 静态代理：
 * 	1、目标对象要实现接口
 *  2、代理对象要实现和目标对象一样的接口
 * @author 贤元
 *
 */
//测试类
public class App {
	public static void main(String[] args) {
		//代理对象
		IUserDao proxy = new UserDaoProxy();
		//执行代理方法
		proxy.save();
		/**
		 * 运行结果：
		 * 代理操作,开启事务。。。
			目标对象的save()方法
			代理操作:提交事务。。
		 */
	}
}
