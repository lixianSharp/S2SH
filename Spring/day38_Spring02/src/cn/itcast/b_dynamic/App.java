package cn.itcast.b_dynamic;

//测试类
public class App {
	public static void main(String[] args) {
		//创建代理对象
		IUserDao target = new UserDao();
		System.out.println("目标对象:"+target.getClass());//目标对象:class cn.itcast.b_dynamic.UserDao
		
		//代理对象
		IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyIntance();
		System.out.println("代理对象:" + proxy.getClass());//代理对象:class com.sun.proxy.$Proxy0
		System.out.println(proxy);//cn.itcast.b_dynamic.UserDao@5d49453c
		//执行代理对象的方法
		proxy.save();
		/**
		 * 开启事务。。
			目标对象的save()方法
			提交事务。。
		 */
	}
}
