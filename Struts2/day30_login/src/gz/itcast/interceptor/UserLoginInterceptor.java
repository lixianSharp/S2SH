package gz.itcast.interceptor;

import gz.itcast.entity.User;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 自定义用户登陆的拦截器
 * 
 * 作用：用于拦截用户的权限
 * 
 * 实现方式：实现拦截器接口：Interceptor
 * @author 贤元
 *
 */
public class UserLoginInterceptor implements Interceptor{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	//前提：假设所有的请求都被这个拦截器拦截
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("拦截器来了");
		
		/**
		 * 问题：该拦截器不需要拦截  登陆(user_login) 和注册请求 (user_register)
		 */
		//得到当前拦截的目标Action代理对象
		ActionProxy actionProxy = invocation.getProxy();
		//得到当前执行的action的方法(只是方法名字)
		String methodName = actionProxy.getMethod();// login / register
		
		//只放行登陆和注册方法
		//判断：因为我们不需要拦截  登陆(user_login) 和注册请求 (user_register)
		if("login".equals(methodName) || "register".equals(methodName)){
			//放行,去执行UserAction的业务方法
			return invocation.invoke();
			
		}
		
		//1）获取session域中的数据
		ActionContext ac = ActionContext.getContext();
		//获取session域对象
		Map<String,Object> sessionMap = ac.getSession();
		User user = (User) sessionMap.get("user");
		
		//2)判断session域的数据是否存在
		if(user == null){
			//3）如果不存在，则跳转到登陆
			return "login";//返回视图标记
		}else{
			//4）如果存在则放行
			return invocation.invoke();
		}


	}

}
