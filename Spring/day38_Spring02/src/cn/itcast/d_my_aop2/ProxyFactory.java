package cn.itcast.d_my_aop2;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;
/**
 * 在SpringAop编程中，
	如果目标对象有实现接口，spring使用jdk提供的代理生成代理对象(jdk代理也叫动态代理)！
	如果目标对象没有实现接口，使用cglib代理！
	如果目标没有实现接口、且为final , 不能进行aop编程，报错！不能生成代理！

 * @author 贤元
 *
 */
//代理工厂
public class ProxyFactory {
	
	/**
	 * 生成代理对象
	 * @param target 目标对象
	 * @param aop 给目标对象动态注解的重复的代码(关注点代码)
	 * @return
	 */
	public Object getProxyInstance(final Object target,final TransctionAop aop){
		return Proxy.newProxyInstance(
				target.getClass().getClassLoader(),//目标对象使用的类加载器
				target.getClass().getInterfaces(),//目标对象实现的接口
				new InvocationHandler() {//事件处理器
					/**
					 * 事件处理器：
					 *		 当执行目标对象方法的时候，会触发事件； 把当前执行的方法(method对象)，传入事件处理器方法参数中,  
					 *				这样就可以根据业务逻辑，判断是否执行目标对象方法或扩展功能！
					 */
					@Override
					public Object invoke(Object proxy, Method method, Object[] args)
							throws Throwable {
						aop.beginTransaction();//执行重复代码
						Object result = method.invoke(target, args);
						aop.commit();//执行重复代码
						return result;//返回代理对象
					}
				});
	}
}