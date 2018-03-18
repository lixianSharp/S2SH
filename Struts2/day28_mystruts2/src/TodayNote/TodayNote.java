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
 
 1.2 自定义Struts框架
 	编写ActionServlet类，这是核心控制器类，在这个类读取mystruts.xml文件。
   	(为了了解struts2框架的核心原理)	
 
 2、Struts2 的使用
 		2、1使用步骤：
 			1）导入struts2支持的jar包
 				commons-beanutils-1.8.0.jar
 				commons-fileupload-1.2.2.jar
 				commons-io-2.0.1.jar
 				commons-lang3-3.1.jar
 				freemarker-2.3.19.jar
 				javassist-3.11.0.GA.jar
 				ognl-3.0.4.jar
 				struts2-core-2.3.4.1.jar
 				xwork-core-2.3.4.1.jar
 				
 				
 				commons-beanutils [beanutils 工具包]
 				connons-fileupload.jar [文件上传]
 				commons-io-2.0.1.jar   [文件上传]
 				commons-lang3-3.1.jar	[struts2对java.lang.*类的支持]
 				freemarker-2.3.19.jar	[视图显示技术]
 				javassist-3.11.0.GA.jar	[struts2对字节码运算的支持]
 				ognl-3.0.4.jar		[struts2对ognl表达式的支持]
 				struts2-core-2.3.4.1.jar [struts2的核心包]
 				xwork-core-2.3.4.1.jar	[webwork框架的支持，struts2的前身就是webwork,struts2就是对webwork的再一次封装]
 			
 			2)配置启动的全局的过滤器(Filter) -- 和ActionServlet类似
 	在web.xml文件中配置
 	<!-- 配置启动struts2的全局的过滤器 -->
  	<filter>
  		<filter-name>struts2</filter-name>
  		<filter-class>org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter</filter-class>
  	</filter>
  	<filter-mapping>
  		<filter-name>struts2</filter-name>
  		<!-- 过滤所有的请求 -->
  		<url-pattern>/*</url-pattern>
  	</filter-mapping>
  
  注意：struts配置模板.xml一定要放在src目录下
 	
 	3)编写不同的业务Action类，编写不同的业务方法。默认execute方法
 	
  public class LoginAction implements Action{
	//默认方法
	public String execute() throws Exception {
		System.out.println("执行了LoginAction的execute方法");
		return "success";//返回一个视图的标记
	}
}


 	4）在src目录下(类路径的根目录下)，新建一个struts.xml，配置Action对象
 
 <struts>
	<package name="xxx" extends="struts-default">
		
		<!-- 配置Action  http://localhost:8080/day28_struts/login.action-->
		<action name="login" class="gz.itcast.action.LoginAction"><!-- method不写默认为execute -->
			<result name="success" type="redirect">/index.jsp</result>
		</action>
	</package>

</struts>
	
	5)访问Action的业务方法
		http://localhost:8080/day28_struts/login.action

 
 2.2 struts2框架的作用：
 	
 		struts2是基于MVC开发模型的web层框架。
 		struts1也是MVC开发模式的框架。struts2并不是struts1的升级版.
 		struts2是基于webwork的升级版。struts2=webwork+struts1.
 	
 	struts2的具体功能(通过拦截器可以体现这些功能)：
 		请求数据封装
 		简化国际化
 		简化文件上传
 		后台数据校验
 		......
 		
 	debug的时候：alt+上下左右的那个左键  返回调试的上一步
 2.3 struts2的执行过程
 		default-struts.propeteis
 		项目启动：
 			1)创建核心过滤器StrutsPreapreAndExecuteFilter对象
 			2)执行核心过滤器的init方法
 				读取了依次以下的配置文件:
 					struts-default.xml  [struts2框架的默认配置文件(不需要自定义修改，也不建议修改)]
 					struts-plugin.xml	[struts2的插件配置文件]
 					struts.xml			[我们写的业务配置文件(包含配置的Action)]
 		
 		访问资源：
 			3)在内存中查询对应的Action配置，得到class内容，创建Action对象.
 			4)读取Action配置的method内容，执行Action对象对应的方法。
 
 2.4 struts-default.xml文件的详解
 		
 		1)声明struts2框架运行过程中使用到的一些对象
 			<bean class="com.opensymphony.xwork2.ObjectFactory" name="xwork"/>
 		2)默认包，包名叫struts-default（注意：我们自己写的packge必须继承这个默认包，只有继承了这个默认包才可以使用该默认包的功能）
 			<package name="struts-default" abstract="true">
 			2.1)跳转类型
 				常用的跳转类型：
 					redirect:重定向到页面
 					despatcher:转发到页面
 					redirectAction:重定向到Action
 					chain:转发到Action
 			<!--这是其中的跳转类型，可以看一下-->
 			<result-types>
            	<result-type name="chain" class="com.opensymphony.xwork2.ActionChainResult"/>
            	<result-type name="dispatcher" class="org.apache.struts2.dispatcher.ServletDispatcherResult" default="true"/>
            	<result-type name="freemarker" class="org.apache.struts2.views.freemarker.FreemarkerResult"/>
            	<result-type name="httpheader" class="org.apache.struts2.dispatcher.HttpHeaderResult"/>
            	<result-type name="redirect" class="org.apache.struts2.dispatcher.ServletRedirectResult"/>
            	<result-type name="redirectAction" class="org.apache.struts2.dispatcher.ServletActionRedirectResult"/>
            	<result-type name="stream" class="org.apache.struts2.dispatcher.StreamResult"/>
            	<result-type name="velocity" class="org.apache.struts2.dispatcher.VelocityResult"/>
            	<result-type name="xslt" class="org.apache.struts2.views.xslt.XSLTResult"/>
            	<result-type name="plainText" class="org.apache.struts2.dispatcher.PlainTextResult" />
        	</result-types>

 		   2.2)拦截器(Interceptor)
 		   		struts2默认的拦截器(32个)，完成struts2的核心功能。(请求封装，文件上传，国际化...)
 			   
 			   拦截器(interceptor)   vs   过滤器(Filter)
 				过滤器：可以过滤任何类型的请求(html页面或servlet、jsp)和响应。
 				拦截器：是struts2的特有功能。只能过滤Action!!在执行Action的时候加入通用代码。
 		
 		   2.3)声明拦截器栈(<interceptor-stack name="basicStack">)
 				默认拦截器栈(里面一共有18个拦截器)：
 				<interceptor-stack name="defaultStack">(里面有18个拦截器)
 		   
 		   2.4)默认包当前使用的拦截器:
 		   	<default-Interceptor-ref name="defaultStack"/>
 
 		注意：
 			我们写的包(package)就是继承struts-default默认包的，那么就继承了默认的18个拦截器。
 		   
 		   2.5)当前默认包下的默认Action
 		   	<default-class-ref class="com.opensymphony.xwork2.ActionSupport">
 
 
 2.5 struts.xml文件详解
 	
 		该文件是我们开发者自行配置的一个业务配置文件。(包含Action的配置)
 		解决没有提示的问题：
 			window->preference->找到XMLCatalog->User Specified Entries->Add->把key的名字改为-//Apache Software Foundation//DTD Struts Configuration 2.3//EN-》
 					->File System->key type选为Public ID->Location的内容改为你dtd文件存放的那个路径就可以
 
 		1)包(package),用于管理Action，一般可以按模块分包。
 		
 		<!--package中各项参数的含义： 
		package的配置：
		  	package: 代表一个包。管理Action的配置。在同一个包下面就不能有同名的action
		  	name: 包名。 在一个项目中不能出现同名的包     
		  	extends: 继承。类似于类的继承。如果一个包继承另一个包，那么就会把父包的功能继承下来。
		  				我们开发的包就必须继承struts-default包
		  	namespace: 名称空间。区分不同包的访问路径。默认值"/"		
		  			用户访问Action的路径搜索规则：
		  					http://localhost:8080/day28_struts/namespace/action的name
		  				
		  				http://localhost:8080/day28_struts/user/a/b/login2.action    ok
		  				http://localhost:8080/day28_struts/user/b/login2.action    ok
		  				http://localhost:8080/day28_struts/user/login2.action    ok
		  				http://localhost:8080/day28_struts/a/b/login2.action    不行！！
		  		
		  			用户访问：	http://localhost:8080/day28_struts/user/a/b/login2.action    
		  					先搜索：http://localhost:8080/day28_struts/user/a/b/login2.action  没有，	有就返回
		  					接着搜索：http://localhost:8080/day28_struts/user/a/login2.action  没有，	有就返回
		  					再搜索：http://localhost:8080/day28_struts/user/login2.action  有，	有就返回 (为止)
		  		
		  		
		  	abstract:表示当前包是否抽象。如果当前包是抽象包，那么不能含有action。  抽象包中一般用来定义拦截器，公共视图，不做具体的业务。
		 -->
		
		2）Action和result的配置
			
			<!-- 
			action配置：
				name: action的名称。用于访问该Action的路径  
				class: Action业务对象的类名。一定是全名(包名+类名)，这里struts2是用反射去构造Action对象的。
				method: 执行的业务方法。不写默认值是execute。
		
			result配置：
				name: 表示视图的标记。在一个Action中名称不要重复
				type: 跳转的类型。
						redirect:重定向
						dispatcher:转发(默认值)
						redirectAction:重定向到Action
						chain: 转发到Action。可以在不同Action中通过request共享数据。
						stream:下载文件的时候用到。
				文本内容：跳转到的页面或者Action
		 	-->
 
 		3)可以通过划分不同的xml文件来管理package
 	<!-- 该文件默认情况下必须放在项目的src目录下  -->
	<struts>
	<!-- 包含读取其他xml文件  注意：声明的顺序就是读取的顺序！！！-->
		<include file="config/struts-book.xml"></include>
		<include file="config/struts-user.xml"></include>
	</struts>

 
 
 * @author 贤元
 *
 */
public class TodayNote {

}
