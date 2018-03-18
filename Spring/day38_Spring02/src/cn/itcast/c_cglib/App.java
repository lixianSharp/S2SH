package cn.itcast.c_cglib;
//测试类
public class App {
	public static void main(String[] args) {
		//创建目标对象
		UserDao target = new UserDao();//目标对象:class cn.itcast.c_cglib.UserDao
		System.out.println("目标对象:"+target.getClass());//目标对象:class cn.itcast.c_cglib.UserDao
		//代理对象，创建代理对象的时候传入一个目标对象
		UserDao proxy = (UserDao) new ProxyFactory(target).getProxyInstance();
		System.out.println("代理对象："+ proxy.getClass());
		//上行代码运行结果：  代理对象：class cn.itcast.c_cglib.UserDao$$EnhancerByCGLIB$$14bef282
		//执行代理对象方法
		proxy.save();
		proxy.find();
		/**
		 * 打印结果:
		 *  目标对象:class cn.itcast.c_cglib.UserDao
			代理对象：class cn.itcast.c_cglib.UserDao$$EnhancerByCGLIB$$4a9d0946
			开启事务...
			模拟保存对象:save()
			提交事务
			开启事务...
			查询:find()
			提交事务
		 */
	}
}
