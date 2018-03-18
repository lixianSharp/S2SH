package gz.itcast.framework;

import gz.itcast.framework.bean.ActionMapping;
import gz.itcast.framework.bean.ConfigurationManager;
import gz.itcast.framework.bean.Result;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 总控制器
 * @author APPle
 *
 */
public class ActionServlet extends HttpServlet {
	//设计一个专门读取mystruts.xml文件的类
	ConfigurationManager manager;
	
	@Override
	public void init() throws ServletException {
		//初始化mystruts.xml文件，封装对象
		manager = new ConfigurationManager();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//从配置管理器类中获取所有的ActionMapping对象
			Map<String,ActionMapping> actionMappings = manager.getActionMappings();//获取到封装好的javabean
			
			
			//1)获取用户的请求 （login.action  / register.action ......）
			String uri = request.getRequestURI();  //   /day28_mystruys/login.action
			
			String pathName = uri.substring(uri.lastIndexOf("/")+1,uri.lastIndexOf("."));  // login
				
			//在所有ActionMapping中搜对应name的ActionMaping    是否包含对应的键
			if(!actionMappings.containsKey(pathName)){
				throw new RuntimeException("找不到对应的Action");
			}
			
			//取出ActionMapping
			ActionMapping actionMapping = actionMappings.get(pathName);
			
			//构造Action对象
			String className = actionMapping.getClassName();//gz.itcast.web.LoginAction
			Class clazz = Class.forName(className);//获取Class对象
			Object actionObj = clazz.newInstance();//构造该对象
			
			//执行方法
			String methodName = actionMapping.getMethod();//获取方法名  login
			Method actionMethod = clazz.getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);//根据方法名得到指定的有参的方法对象  login或者execute方法  
			//得到视图的标记
			String resultFlag  = (String)actionMethod.invoke(actionObj, request,response);//success
			//3)得到视图
			Map<String, Result> results = actionMapping.getResults();
			if(!results.containsKey(resultFlag)){
				throw new RuntimeException("找不到对应的Result视图");
			}
			
			Result result = results.get(resultFlag);
			
			//4)跳转视图   上面所做的反射之类的目的就是为了得到跳转的视图
			//跳转类型
			String type = result.getType();//得到跳转的类型
			//跳转的页面
			String page = result.getPage();//得到跳转的视图 /index.jsp
			if(type.equals("dispatcher")){
				//默认的跳转类型是转发
				request.getRequestDispatcher(page).forward(request, response);
			}else{
				//重定向
				response.sendRedirect(request.getContextPath()+page);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
