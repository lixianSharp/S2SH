package TodayNote;


/**
 回顾重点内容：
 	struts2开发步骤：
 		1）导入jar包
 		2）在web.xml配置全局过滤器(启动时候创建)
 		3）写业务的Action类
 		4）在src目录下写struts.xml文件，在这个文件配置action.
 
 大纲：
 	struts2的相关配置：
 		1）struts2的Action三种使用方式
 		2）struts2的路径通配符
 		3）struts2的常量配置
 		4）struts2的全局视图配置和默认配置
 		5）Action的属性注入
 		
 	struts2的核心业务功能
 		1）struts2的数据共享的三种方式
 		2）请求参数数据的封装
 		3）自定义类型转换
 		4）文件上传和下载
 
 
 1、struts2的Action三种使用方式
 	
 	1.1第一种方式。不需要实现Action接口
 	//不需要实现或继承任何接口或类
	public class UserAction2 {
		public String login() throws Exception{
			System.out.println("UserAction2");
			return "success";
		}
	}

	
	1.2 第二种方式，实现Action接口。
		
 		* 第二种方式：实现Action接口
 		* 	1)定义了默认的execute方法的标准
 		* 	2）提供了项目中常用的视图标记
 		* 	@author 贤元
 	public class UserAction implements Action{
		public String login() throws Exception {
			System.out.println("执行了UserAction的login方法");
			return "success";
		}
		
	@Override
		public String execute() throws Exception {
			return null;
		}
	}

 	
 	1.3第三种方式，继承ActionSupport类。(推荐使用这种方式)
 		
 		* 第三种方式，继承ActionSupport类(推荐使用)
 		* 		好处：
 		* 			1）提供了常用的视图标记
 		* 			2）提供了数据校验功能
 		* @author 贤元
 
	public class UserAction3 extends ActionSupport{
		public String login() throws Exception{
			System.out.println("UserAction3.login()");
			return SUCCESS;//SUCCESS是Action接口中的常量，代表"success"		
		}
	}

 
 2.struts2的路径的通配符
 	
 	<!-- http://localhost:8080/day28_struts/path/user_login.action 访问login方法 -->
		<!-- http://localhost:8080/day28_struts/path/user_register.action 访问register方法 -->		
		<!-- 一个模块(Action对象) 使用一个action配置 -->
		<!-- * (星号):表示路径的通配符。使用通配符的内容：{1} (表示获取第一个通配符的实际内容) 
			好处：大大地减少action的配置
		-->
		<!-- 1表示第一个星号 -->
		<!--
		<action name="user_*" class="gz.itcast.b_path.UserAction" method="{1}">
			<result name="{1}">/{1}.jsp</result>
		</action>
		 -->
		 
		 <!-- 多个模块使用一个action
		 约定前提：第一个星号代表模块，第二个星号代表方法
		 	 User_login
		 	 
		 	http://localhost:8080/day29_struts/path/User_login.action
		 	http://localhost:8080/day29_struts/path/Book_add.action
		  -->
		<action name="*_*" class="gz.itcast.b_path.{1}Action" method="{2}">
			<result name="{2}">/{1}/{2}.jsp</result>		
		</action>
 
 3、struts2的常量配置
 	
 		struts2的常量就是用于在struts2的程序运行过程中使用的一些常量参数。
 	
    指定默认编码集,作用于HttpServletRequest的setCharacterEncoding方法 和freemarker 、velocity的输出
    <constant name="struts.i18n.encoding" value="UTF-8"/>
    自定义后缀修改常量
    <constant name="struts.action.extension" value="do"/>
    设置浏览器是否缓存静态内容,默认值为true(生产环境下使用),开发阶段最好关闭 
    <constant name="struts.serve.static.browserCache" value="false"/>
    当struts的配置文件修改后,系统是否自动重新加载该文件,默认值为false(生产环境下使用),开发阶段最好打开 
    <constant name="struts.configuration.xml.reload" value="true"/>
    开发模式下使用,这样可以打印出更详细的错误信息 
    <constant name="struts.devMode" value="true" />
    默认的视图主题 
    <constant name="struts.ui.theme" value="simple" />
    与spring集成时，指定由spring负责action对象的创建 
    <constant name="struts.objectFactory" value="spring" />
    该属性设置Struts 2是否支持动态方法调用，该属性的默认值是true。如果需要关闭动态方法调用，则可设置该属性
    为 false
    <constant name="struts.enable.DynamicMethodInvocation" value="false"/>
    上传文件的大小限制
    <constant name="struts.multipart.maxSize" value=“10701096"/>

 
 	注意：
 		通过struts.xml文件声明<constant name="struts.action.extension" value="action,do,,">修改常量配置
 
 
 4、struts2的全局视图配置和默认配置
 	
 		4.1 全局视图作用：当该报下的所有action都使用到的一些视图是可以放到全局视图配置中的。
 		注意：
 			当action中也有相同名称的视图时，那么action的局部视图会覆盖全局视图。
 		
 		<!--全局视图配置：把该包下的所有action公用的视图都集中在这里写-->
 		<global-results>
 			<result name="success">/login.jsp</result>
 		<global-results>
 		
 		4.2 action的默认配置
 		
 		<!-- 默认配置
		name:必填项
		class:可选项。默认配置：ActionSupport类. 该类继承自struts-default （<default-class-ref class="com.opensymphony.xwork2.ActionSupport" />）
		method：可选。默认配置。excuse
		result:
				name：可选。默认配置：success
				type:可选。默认配置：dispatch
		 -->
		 <!-- 全部使用默认配置的action的作用：专门用于转发到WEB-INF下的页面 -->
		 <action name="book" class="" method="">
		 	<result name="">/WEB-INF/jsp/login.jsp</result>
		 </action>
		 
		
 5、action的属性注入		
		作用：如果Action对象中需要把一些经常改变的参数提取到配置文件中，那么久可以使用属性注入的方式
		
		Action属性注入的步骤：
			1）在Action类中声明要给成员变量，用于接收xml配置文件传入的内容。
			2）在Action类提供一个该变量的setter方法，该方法接收了xml配置的内容。
			
				//1）在action中提供一个属性
				private String savePath;
				//2)提供属性的setter方法，用于外部的action的参数进行注入
				public void setSavePath(String savePath) {
					this.savePath = savePath;
				}
			
			
			3）在对应的struts.xml文件中，找到对应的action对象的配置，然后再action中使用
				<param name=""></param>这个变迁来向Action对象的属性注入内容
 				
 				
 			<action name="upload" class="gz.itcast.d_ioc.UploadAction" method="upload">
			<!-- 3)使用该配置可以往Action对象的属性注入内容(只要有setter都可以使用param进行注入) 
			param:	
				  name:setter方法名。 setsavePath->savePath
			-->
			<param name="savePath">d:/images/</param>
			<result>/login.jsp</result>
		</action>
				
		
 6、 struts2的数据共享的三种方式
			
			在web项目中都是使用域对象来共享数据。
			
			struts2提供给开发者使用域对象来共享数据的方法一共有三种。
				
		6.1 第一种方式
			ServletActionContext类：
				getRequest();获取request对象
				getRequest().getSession();获取session对象
				getServletContext();获取ServletContext对象
			注意：
				1）该方式依赖servlet的api，耦合比较高
				2）如果要通过域对象来获取域对象的相关信息必须使用该方式
				
	    6.2 第二种方式
	    	
	    	ActionContext类：
	    			getContextMap();获取request域对象数据的map集合
	    			getSession();获取session域对象数据的map集合
	    			getApplication();获取操作context域对象数据的map集合
	    		 
	    		 注意：
	    		 	1）不依赖servlet的api，耦合性低
	    		 	2）只能用在Action对象的一个方法中。不能再所有方法中都使用同一个ActionContext
	    		 	
	    6.3 第三种方式
	    		
	    		使用RequestAware，SessionAware,ApplicationAware接口
	    		注入操作对应域对象数据的Map集合
	    			
	    			注意：
	    				1）不依赖servlet的api
	    				2）可以在Action对象的所有方法中共享Map集合
	    				
 
 7、请求参数数据的封装
 		
 	   7.1直接复制给简单数据类型
 	   	
 public class UserAction extends ActionSupport{
	//参数赋值(注入方式)
	private String name;
	private String password;
	private String gender;
	private String[] hobit;
	//参数通过这个set方法注入到Action中
	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	
	public void setHobit(String[] hobit) {
		this.hobit = hobit;
	}

	public String register() throws Exception{
		//接收并使用表单的数据
		System.out.println(name);
		System.out.println(password);
		System.out.println(gender);
		System.out.println(hobit);
		return SUCCESS;
	}
 }
	
		
		7.2 赋值给一个javabean对象
			
	<form action="${pageContext.request.contextPath }/data/user_register.action" method="post">
    	用户名： <input type="text" name="user.name"/><br/>
    	密码： <input type="password" name="user.password"/><br/>
    	性别： <input type="radio" name="user.gender" value="男"/>男
    	 <input type="radio" name="user.gender" value="女"/>女<br/>
    	爱好：
    	<input type="checkbox" name="user.hobit" value="篮球"/>篮球
    	<input type="checkbox" name="user.hobit" value="足球"/>足球
    	<input type="checkbox" name="user.hobit" value="羽毛球"/>羽毛球<br/>
    	<input type="submit" value="注册"/>
    </form>
    
    
    public class UserAction2 extends ActionSupport{
	//使用一个javabean对象接收
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String register() throws Exception{
		//使用表单的数据
		System.out.println(user);
		return SUCCESS;
		}
	}
	
 注意：请求数据的封装通过struts2的ParametersInterceptor拦截器进行赋值


 8、自定义类型转换
 		
 		作用：默认情况下，也main的日期类型只能接收yyyy-MM-dd类型。如果要转换yyyy/MM/dd这种类型，则需要使用自定义类型转换器进行转换
 
 		struts2提供了自定义类型转换器的基类：StrutsTypeConverter类
		
		
/**
 * 自定义日期类型转换器
 * @author 贤元
 * 
 *
public class MyDateConvert extends StrutsTypeConverter {

	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");

	/**
	 * 从页面的数据到服务器的转换 
	 * 参数一：context:值栈上下文对象
	 *  参数二：values:从页面传递过来的参数值
	 * 参数三：toClass：转换到的类型。String->java.util.Date
	 *
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		System.out.println("进入了转换器");
		try {
			// 1、判断value是否为空
			if (values == null || values.length == 0) {
				return null;
			}

			if (values.length > 1) {
				//多个值的情况
				Date[] dates = new Date[values.length];
				// 2)取出数据进行转换
				for (int i = 0; i < dates.length; i++) {
					//一个值的情况
					Date date = sdf1.parse(values[i]);
					dates[i] = date;

				}
				return dates;
			} else {
				//一个值的情况
				Date date = sdf1.parse(values[0]);
				return date;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 从服务器到页面的类型转换
	 *
	@Override
	public String convertToString(Map arg0, Object arg1) {

		return null;
		}
	}
		
		
		绑定自定义转换器的方式：
			方式一：局部绑定(只能绑定一个Action)
				1）建立一个  Action的文件名-conversion.properties 文件。(这是一个固定写法)
				2）一定和绑定的Action放在同一个目录下面
			  user.birth=gz.itcast.g_convert.MyDateConvert
				
				
			方式二：全部绑定(绑定整个项目多个Action)(推荐使用)
				1）建立一个 xwork-conversion.properties 文件
				2）该文件一定要放到src目录下。
			
			  java.util.Date=gz.itcast.g_convert.MyDateConvert
			  
 
 9、文件上传和下载
 		
 		9.1文件上传
 			1）三个条件
 				表单有file
 				post提交
 				enctype="multiparty/form-data"
 			2）在Action中接收文件内容
 				File attach;(attach是file表单的name属性)
 				String attachContentType;文件类型
 				String attachFileName
 			细节：
 				修改上传大小
 			
 			<!-- 修改默认文件上传的大小 -->
			<constant name="struts.multipart.maxSize" value="100000000"></constant>
			
				修改允许上传的文件类型和文件后缀
				
			<action name="upload_*" class="gz.itcast.h_upload_down.UploadAction">
				<!-- 往FileUploadInterceptor拦截器的属性注入值(调用setter方法) -->
				<interceptor-ref name="defaultStack">
					<!-- 改变当前文件上传拦截器的允许文件类型 -->
					<param name="fileUpload.allowedTypes">image/jpeg,image/jpg</param>
					<!-- 允许的文件后缀 -->
					<param name="fileUpload.allowedExtensions">jpg,jpeg,gif</param>
					<!-- 如果以上配置都写了，那么取他们的交集 -->
				</interceptor-ref>
			
				<param name="savePath">d:/images/</param>
				<result>/login.jsp</result>
				<result name="input">/error.jsp</result>
			</action>

 				
 		9.2文件下载
 			视图类型一定是stream类型
 			
 		 <action name="down_*" class="gz.itcast.h_upload_down.DownAction" method="{1}">
				<param name="serverPath">d:/images/</param>
				<result name="list">/listFile.jsp</result>
				<!-- 文件下载的关键：视图类型一定是stream -->
				<result name="down" type="stream">
					 <!--  往StreamResult类中的属性注入内容 -->
					 <!-- 返回给浏览器的文件类型。返回通用的二进制 -->
					 <param name="contentType">application/octet-stream</param>
					 <!-- 返回给浏览器的输入流 -->
  					 <param name="inputName">inputStream</param>
  					 <!--  告诉浏览器的方式下载资源
  					 ${name}: 获取Action中的getName()方法的数据
  					 -->
  					 <param name="contentDisposition">attachment;filename=${name}</param>
  					 <!-- 缓存大小 -->
   					 <param name="bufferSize">1024</param>
				</result>
			</action>
			
			
			在Action对象中提供一个对应的获取输入流的方法
			
    //需要提供给struts写出数据的输入流
	public InputStream getInputStream(){
		try {
			FileInputStream fis=new FileInputStream(new File(serverPath+name));
			
			return fis;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
 	该方法中的getInputStream中的InputStream应该对上 <param name="inputName">inputStream</param>中的inputstream。(不是name中的inputStream)		
 	
 	public String getName() {
		return name;
	}
    <param name="contentDisposition">attachment;filename=${name}</param>中的${name}应该对上getName方法中的Name。(方法名上的Name)

	
	
				
 	
 * @author 贤元
 *
 */
public class TodayNote {

}
