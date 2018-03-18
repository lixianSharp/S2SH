package gz.itcast.a_interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 目的：有了多个拦截器的时候，拦截器如何执行？？
 * 
 * @author 贤元
 *
 */
// 自定义拦截器：实现拦截器接口：Interceptor
public class MyInterceptor2 implements Interceptor {

	public MyInterceptor2() {
		System.out.println("1)创建了拦截器2的对象");
		
	}

	@Override
	public void destroy() {
		System.out.println("拦截器2的对象被销毁了");
	}

	@Override
	public void init() {
		System.out.println("2)调用拦截器2的init方法");
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("4)拦截器2Action--前面的代码");

		// 放行，调用下一个拦截器，如果没有下一个拦截器，那么调用目录的action
		invocation.invoke();

		System.out.println("6)拦截器2Action--后面的代码");

		return null;
	}

}
