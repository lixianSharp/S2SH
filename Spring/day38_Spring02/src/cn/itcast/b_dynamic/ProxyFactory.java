package cn.itcast.b_dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 在SpringAop编程中，
	如果目标对象有实现接口，spring使用jdk提供的代理生成代理对象！
	如果目标对象没有实现接口，使用cglib代理！
	如果目标没有实现接口、且为final , 不能进行aop编程，报错！不能生成代理！

 */
/**
 * 动态代理(也叫jdk代理):
 * 	   代理工厂，给多个目标对象生成代理对象！
 * @author 贤元
 *
 */
public class ProxyFactory {
	
	//接收一个目标对象
	private static Object target;
	
	public ProxyFactory(Object target){
		this.target = target;
	}
	
	
	//返回对目标对象(target)代理后的对象(proxy)   也就是返回代理对象
	public static Object getProxyIntance(){
		Object proxy = Proxy.newProxyInstance(
					target.getClass().getClassLoader(),//目标对象使用的类加载器 
					target.getClass().getInterfaces(),//目标对象实现的接口
					new InvocationHandler() { //执行代理对象方法的时候触发
						@Override
						public Object invoke(Object proxy, Method method, Object[] args)
								throws Throwable {
							//InvocationHandler 是代理实例的调用处理程序 实现的接口。 


							/**
							 * 三个参数：
							 * proxy - 在其上调用方法的代理实例
							   method - 对应于在代理实例(也就是目标对象)上调用的接口方法的 Method 实例
							   args - 包含传入代理实例上方法调用的参数值的对象数组，如果接口方法不使用参数，则为 null
							 */
							//获取当前执行的方法的方法名
							String methodName = method.getName();
							//方法返回值
							Object result = null;
							//判断
							if("find".equals(methodName)){
								//find()方法的情况
								
								//查询调用目标对象方法  ，通过反射实现
								result = method.invoke(target, args);
							}else{
								//save()方法的情况
								System.out.println("开启事务。。");
								//执行目标对象的方法
								result = method.invoke(target, args);
								System.out.println("提交事务。。");
							}
							return result;
						}
					});
		return proxy;//返回代理对象
	}
	
	
}
