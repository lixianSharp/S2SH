package cn.itcast.c_cglib;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
/**
 * 在SpringAop编程中，
	如果目标对象有实现接口，spring使用jdk提供的代理生成代理对象！
	如果目标对象没有实现接口，使用cglib代理！
	如果目标没有实现接口、且为final , 不能进行aop编程，报错！不能生成代理！

 */
/**
 * cglib代理:(需要实现cglib代理接口:MethodInterceptor(属于spring包下的))
 * 		代理工厂,给多个目标对象生成代理对象!
 * @author 贤元
 *
 */
public class ProxyFactory implements MethodInterceptor{

	//接收一个目标对象
	private static Object target;
	public ProxyFactory(Object target){
		this.target = target;
	}
	
	//返回目标对象代理后的子类对象
	public Object getProxyInstance(){
		//对target生成子类对象
		
		//字节码生成工具类
		Enhancer en = new Enhancer();
		//设置父类
		en.setSuperclass(target.getClass());
		//设置回调函数
		en.setCallback(this);
		//创建子类对象
		return en.create();
	}
	//事件处理器，执行目标方法时触发
	@Override
	public Object intercept(Object proxy, Method method, Object[] args,
			MethodProxy methodProxy) throws Throwable {
		System.out.println("开启事务...");
		Object result = method.invoke(target, args);//执行方法
		System.out.println("提交事务");
		return result;
	}
}
