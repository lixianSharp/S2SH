package TodayNote;
/**
1、Struts2入门

	1.1 简介
		Struts2就是一个基于MVC模式开发框架，对Servlet技术的封装！！！
		
		回顾MVC模式：
			M：Model模型。使用javabean技术，对业务数据的封装。
			V：View 视图。使用jsp技术。对业务数据的显示。
			C：Control 控制器。使用Servlet技术。
				控制器的作用：
					1）接收请求，获取用户的参数。
					2）调用业务逻辑处理方法。
					3）获取结果，导航视图。把结果显示到视图中。
		需求：用户登录和注册功能(用户模块)。使用MVC模式进行开发。
		  设计：
		  	dao层：
		  	  UserDao类：
		  		queryUser(String name,String password);//查询登陆用户
		  		addUser(User user);//添加用户方法
		  	service层：
		  		UserService类：
		  			login(User user);//登陆业务方法
		  			register(User user);//注册业务方法
		  			
		  	web层：
		  		一个功能对应一个Servlet类
		  		
		  		登陆功能：LoginServlet类：  /login访问
		  			处理登陆请求
		  			跳转到视图(jsp)
		  			如果是登陆成功
		  				跳转到主页
		  			否则，不成功
		  				跳转到失败页面
		  		注册功能：RegisterServlet类：/register访问
		  			处理注册请求
		  			跳转到视图(jsp)
		  			注册成功
		  				跳转到登陆页面
		  	关注：能不能优化web层的MVC的代码？？？？	
		  	现状：一个功能对应一个Servlet处理类，这样的话项目管理的servlet就会很多！！！管理web.xml文件
		  				就会很麻烦！！更多的Servlet对象会消耗更多的服务器内存(单例)。 	
		  	目标： 一个项目只对应一个Servlet处理类						






























* @author 贤元
*
*/
public class TodayNote {

}
