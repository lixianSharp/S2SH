package TodayNote;


/**
 回顾重点内容：
 	struts2第二天：
 		1）struts2使用Action方法：
 			1.1不识闲任何接口，不继承任何类
 			1.2 实现Action接口(定了标准，提供了视图的常量)
 			1.3继承ActionSupport类(只需选择覆盖方法，提供了视图常量，提供数据验证的功能)
 		2）struts2的常量配置
 			struts-core.jar  default.properties常量文件
 			在struts.xml文件使用<constant name="" value"" />修改常量值
 			2.1默认请求编码：struts.i18n.encoding
 			2.2动态方法调用：struts.***.DynamicMethodInvocation  actionName!methodName
 			2.3修改上传文件的临时目录：struts.multipart.saveDir
 			2.4修改上传文件的大小：struts.multipart.maxSize
 			2.5访问action的后缀名：struts.**.actionExtension
 		3）全局视图
 			<grobal-result>当前包下的所有action共享这些视图
 			
 		4）（域对象）数据共享三种方法
 			4.1 ServletActionSupport得到servlet的api(依赖servlet)
 			4.2 ActionContext得到操作域对象数据的Map集合(不依赖servlet，方法局部)
 			4.3 Action类实现RequestAware，SessionAware，ApplicationAware接口(action全局)
 					实现提供的方法:获取操作域对象数据的Map集合
 					
 		5）请求参数封装
 			5.1 基本数据类型接收参数(action中提供setter方法)
 			5.2javabean类型接收参数:user(action提供getter和setter)
 					表单的name属性值: user.name/user.password...
 		6）类型转换
 			默认情况下，基本数据类型可以直接从String转换，日期只能转yyyy-MM-dd类型
 			如果按照自己需要转换，定义自定义类型转换器。
 				
 				继承StrutsTypeConverter类
 					convertFromString();从页面的数据转换为后台需要的类型
 			
 			局部绑定：
 				1）在action的同一个包下建立ActionName-conversion.properties (约定)
 				2）user.birth=xxxx.xxxxx
 			全局绑定：
 				1）在src下建立 xwork-conversion.properties（约定）
 				2）java.util.Date=xxxxxx
 		
 		7)上传与下载
 			上传：
 				表单：<input type="file" name="img"/>
 				单文件Action处理文件：
 					File img;
 					String imgFileName
 					String imgContentType
 				
 				表单：
 					<input type="file" name="img"/>
 					<input type="file" name="img"/>
 					<input type="file" name="img"/>
 				
 				多文件Action处理文件
 					File[] img;
 					String[] imgFileName;
 					String[] imgContentType;
 			下载:
 				<action name="down" class="xxxxx">
 					<result type="stream">
 						<param name="contentType">xxxxx</param>
 						<param name="inputName">Action中定义获取输入流的方法(方法名)</param>
 						<param name="contentDispostion">xxxxxx</param>
 						<param name="bufferedSize"></param>
 					</result>
 				</action>
 			
 
 大纲：
 		拦截器(interceptor)（重点）
 			拦截器编写规则
 			拦截器的生命周期
 			拦截器栈(引用多个拦截器)
 			案例
 		值栈(value stack):（重点）
 			OgnlContext对象使用
 			分析值栈的内部组成结构(关键点)
 			页面获取值栈数据
 			struts2的常用逻辑标签
 			
 1、拦截器
 		
 		1.1拦截器简介
 			
 		  拦截器 类似于 过滤器的功能，过滤器是项目的任何请求(servlet/jsp/html/img),
 		  	但拦截器只能拦截Action资源。拦截完Action，添加逻辑代码。
 		
 		1.2 拦截器的编写规则
 			
 			struts2提供Interceptor接口用于自定义拦截器。
  			
  			步骤：
  				1）编写类，实现Interceptor接口
  				
 			
 * 目的：查看拦截器的生命周期
 * 
 * @author 贤元
 * 
 *
public class MyInterceptor1 implements Interceptor {

	public MyInterceptor1(){
		System.out.println("1)创建了拦截器1对象");
	}
	
	@Override
	public void destroy() {

	}

	@Override
	public void init() {
		System.out.println("2)调用了拦截器1的init方法");
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("4)拦截器Action--前面的代码");
		
		//放行，调用action
		invocation.invoke();
		
		System.out.println("6)拦截了Action--后面的代码");
		return null;
	}

}
 
 	
 				2）在struts.xml文件中定义和使用拦截器
 
 	<package name="inter" extends="struts-default" namespace="/inter">
		<!-- 定义拦截器 -->
		<interceptors>
			<interceptor name="inter1" class="gz.itcast.a_interceptor.MyInterceptor1"></interceptor>
		</interceptors>
		
		
		
		
		<action name="user_*" class="gz.itcast.a_interceptor.UserAction" method="{1}">
			<!-- 使用拦截器 -->
			<interceptor-ref name="inter1"></interceptor-ref>
			<result>/index.jsp</result>
		</action>
	</package>
	
				
			注意1：拦截器的执行顺序
				启动：
					1）拦截器对象创建，调用拦截器的init方法
				访问：
					2）创建Action对象
					3）执行拦截器的interceptor方法
						3.1 执行拦截器前面的代码(invocation.invoke();方法之前的代码)
						3.2 执行invocation.invoke();放行执行下一个拦截器或Action的方法
						3.3 执行拦截器后面的代码(invocation.invoke();方法之后的代码)
	
			
			注意2：拦截器范围
				局部使用：action起作用
	
		<action name="user_*" class="gz.itcast.a_interceptor.UserAction" method="{1}">
			<!-- 使用拦截器  局部起作用的拦截器 -->
			<interceptor-ref name="inter1"></interceptor-ref>
			<result>/index.jsp</result>
		</action>
 				
 				全局使用：在当前包起作用
 				
 		<package name="inter" extends="struts-default" namespace="/inter">
		<!-- 定义拦截器 -->
		<interceptors>
			<interceptor name="inter1" class="gz.itcast.a_interceptor.MyInterceptor1"></interceptor>
		</interceptors>
		
		<!-- 全局起作用的拦截器 -->
		<default-interceptor-ref name="inter1"></default-interceptor-ref>
	
 		
 		1.3 拦截器栈
 			概念：当一个或多个Action同时被多个拦截器所拦截，就可以使用拦截器栈。
 			
 			
 		<interceptors>
			<interceptor name="inter1" class="gz.itcast.a_interceptor.MyInterceptor1"></interceptor>
			<interceptor name="inter2" class="gz.itcast.a_interceptor.MyInterceptor2"></interceptor>
			<!--定义拦截器栈 -->
			<interceptor-stack name="interStack">
				<interceptor-ref name="inter1"></interceptor-ref>
				<interceptor-ref name="inter2"></interceptor-ref>
			</interceptor-stack>
		</interceptors>
 		
 			注意1：定义拦截器栈的时候，引用拦截器的顺序决定了创建拦截器对象的顺序。(先指定就先创建)
 			
 			注意2：当前有了拦截器栈(多个拦截器)的执行顺序
 				启动：
 					1）创建拦截器inter1对象，调用init方法
 					2）创建拦截器inter2对象，调用init方法
 				
 				访问：
 					3）执行inter1的interceptor方法前面代码
 					4）执行inter2deinterceptor方法前面代码
 					5）Action的业务方法
 					6）执行inter2的interceptor方法后面的代码
 					7）执行inter1的interceptor方法后面的代码
 			
 			注意3：当我们的包下引用了自定义拦截器，则会把默认包下的default-stack拦截器给覆盖掉！！！
 				  这时需要这么做：
 				  	
 			<interceptor-stack name="myStack">
				<interceptor-ref name="defaultStack"></interceptor-ref><!-- 引入了默认的18个拦截器 -->
				<interceptor-ref name="interStack"></interceptor-ref><!-- 引入了自定义的两个拦截器 -->
			</interceptor-stack>
 
 2、值栈
 	
 		2.1 Ognl表达式简介
 			Ognl是Object Graphic Navigation Language（对象图导航语言）的缩写，他是一个开源项目。struts2框架使用OGNL作为默认的表达式语言。
 			
 			在struts2项目中导入ognl.jar 包来实现支持ognl表达式
 			
 			ognl表达式	vs  EL表达式
 			EL表达式：获取域对象的数据。不能存放数据，不能调用方法。
 			Ognl表达式：获取域对象的数据。可以存放数据，可以调用方法。
 			
 			OGNL的优势：
 			
 	   			1、支持对象方法调用，如xxx.doSomeSpecial()； 
       			2、支持类静态的方法调用和值访问，表达式的格式:
             			@[类全名（包括包路径）]@[方法名 |  值名]，例如：
             			@java.lang.String@format('foo %s', 'bar')
                            			或@tutorial.MyConstant@APP_NAME； 
       			3、支持赋值操作和表达式串联，如price=100, discount=0.8,
             			calculatePrice()，这个表达式会返回80； 
       			4、访问OGNL上下文（OGNL context）和ActionContext； 
       			5、操作集合对象。
 	
	
		2.2 Ognl表达式核心 OgnlContext对象
		
		package gz.itcast.b_ognl;

import static org.junit.Assert.*;
import ognl.Ognl;
import ognl.OgnlContext;

import org.junit.Test;
/**
 * 目标：学习了解Ognl表达式的核心对象OgnlContext对象的使用。
 * @author 贤元
 *
 *
public class OgnlDemo {
	
	@Test
	public void test1() throws Exception{
		User user =new User();
		user.setName("eric");
		user.setAge(20);
		
		//1）创建一个OgnlContext对象
		OgnlContext context=new OgnlContext();
		//2）把user对象存入OgnlContext对象
		context.put("user", user);
		
		//3）从OgnlContext对象取出对象
		User user2=(User)context.get("user");
		System.out.println(user2.getName()+"\t"+user2.getAge());	
	}
	
	//使用Ognl表达式取出OgnlContext的数据，如果是非根对象数据，那么使用#号去取
	@Test
	public void test2() throws Exception {
		User user =new User();
		user.setName("eric");
		user.setAge(20);
		
		//1）创建一个OgnlContext对象
		OgnlContext context=new OgnlContext();
		//2）把user对象存入OgnlContext对象
		context.put("user", user);//往OgnlContext的非根对象 存数据
		
		//使用Ognl表达式从OgnlContext对象取出数据
		Object ognlObj=Ognl.parseExpression("#user.name");//表达式对象
		String name=(String)Ognl.getValue(ognlObj, context, context.getRoot());//获取数据方法
		System.out.println(name);
	}
	
	
	//使用Ognl表达式取出OgnlContext的数据，如果是根对象数据，不需要使用#号，不需要key名称，直接写存入对象的属性即可
	@Test
	public void test3() throws Exception {
		User user =new User();
		user.setName("eric");
		user.setAge(20);
		
		//1）创建一个OgnlContext对象
		OgnlContext context=new OgnlContext();
		//2）把user对象存入OgnlContext对象
		context.setRoot(user);//往OgnlContext的根对象 存数据
		
		//使用Ognl表达式从OgnlContext对象取出数据
		Object ognlObj=Ognl.parseExpression("name");//表达式对象  因为64行的set数据的时候没有名字，所以直接写"name"即可，即不要写key。
		String name=(String)Ognl.getValue(ognlObj, context, context.getRoot());//获取数据方法
		System.out.println(name);
		
	}
	
	/**
	 * java.util.Math
	 * @throws Exception
	 *
	//Ognl表达式调用静态方法
	@Test
	public void test4() throws Exception {
		//Math.round(3.14);
		
		
		//1）创建一个OgnlContext对象
		OgnlContext context=new OgnlContext();
		
		//使用Ognl表达式从OgnlContext对象取出数据
		//Object ognlObj=Ognl.parseExpression("@java.lang.Math@round(10.5)");//表达式对象  因为64行的set数据的时候没有名字，所以直接写"name"即可，即不要写key。
		Object ognlObj=Ognl.parseExpression("@Math@round(10.5)");//表达式对象  因为64行的set数据的时候没有名字，所以直接写"name"即可，即不要写key。
		Object result=Ognl.getValue(ognlObj, context, context.getRoot());//获取数据方法
		System.out.println(result);//11
		
		
		
	}
	
	
}

		
		结论：	
			1）从OgnlContext对象的跟对象取出数据，不需要#号。
			2）从OgnlContext对象的冯恩对象取出数据，需要#号。
		
 		
 		2.3 struts2的值栈(ValueStack)对象（利用OgnlContext对象）
 		
 			ValueStack接口，最终在项目中存储数据的对象是ValueStack的实现类OgnlValueStack.
 			OgnlValueStack对象(struts2存取数据的核心)
 			
 			ValueStack的数据存储结构：分为 List栈(根栈) 和 Map栈(非根栈)
 			
 			
 			1）List栈主要存储Action对象和Provider代理对象
 			2）Map栈主要存放各个域存放的数据和用户的参数信息
 		
 		2.4 页面获取值栈(ValueStack)数据
 			
 			使用Ognl表达式取ValueStack数据

使用<s:property/>标签可以使用Ognl表达式获取值栈的数据
一、List栈的数据（不需要#号,不需要key）
		输入Ognl：user
		
		作用：直接到List栈的每个对象去搜索对应的属性
		搜索规则：
			从List栈的栈顶开始搜索——》user ——》返回，找不到继续向下找
			栈的第二个元素		  ——》user ——》返回，找不到继续向下找
			......				  ——》user ——》空

			
				注意：在List栈中尽量不要再两个栈对象中出现相同的属性名，如果出现相同的属性名，只获取第一个对象的属性。
		

 		
 	
 * @author 贤元
 *
 */
public class TodayNote {

}
