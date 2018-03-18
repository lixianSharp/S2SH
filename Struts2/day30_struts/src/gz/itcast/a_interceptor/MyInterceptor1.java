package gz.itcast.a_interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 拦截器的编写规则：
 * 	 struts提供Interceptor接口用于自定义拦截器。 
 * 		自定义拦截器只需要实现拦截器接口：Interceptor
 * 
 * @author 贤元
 *
 */
// 目的：查看拦截器的生命周期
public class MyInterceptor1 implements Interceptor {

	public MyInterceptor1() {
		System.out.println("1)创建了拦截器1对象");
		
	}

	@Override
	public void init() {
		System.out.println("2)调用了拦截器1的init方法");
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("4)拦截器1Action--前面的代码");

		// 放行，调用下一个拦截器，如果没有下一个拦截器，那么调用目录的action
		invocation.invoke();

		System.out.println("6)拦截了1Action--后面的代码");
		return null;
	}

	@Override
	public void destroy() {
		System.out.println("拦截器1对象销毁了");
	}

}
