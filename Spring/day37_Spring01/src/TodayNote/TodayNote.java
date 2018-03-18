package TodayNote;
/**
 框架概述：
 	struts2:基于mvc模式的应用层框架！
 			主要是作为控制层组建，javabean，jsp！
 	Hibernate：持久层组建，简化jdbc操作
 	Spring:主要有六大模块功能
 			事务管理！
 			与其他框架整合！
 			
 	MVC开发模式：
 		基于mvc模式的项目分层
 
 
 
 1、Spring框架
 	Spring 春天来了！
 	
 	1）框架理解：
 		框架，都会预先实现一些功能，给开发人员开发提供遍历！
 			提高开发效率，提升程序的可扩展性、健壮！
 	2）专业术语
 		高内聚、低耦合：
 			类内部的关系越紧密越好，类与类的关系越少越好！
 		侵入式设计：
 			 引入的组件对现有的类的结构会有影响，这种就是"侵入式设计组件"
 			Struts2: 侵入式设计!
 			Hibernate: 非侵入式设计！
 		非侵入式设计：
 			引入的组件对现有的类的结构没有影响！
 			Spring，非侵入式设计！
 		IOC容器：
 			Inversion of control 控制反转
 				控制反转容器--》对象创建的 问题！
 				解释：
 					User user=new User();自己控制对象的创建
 					现在需要对象，自己不创建，交给外部的容器创建，叫控制反转！
 					IOC容器=bean.xml配置 + ApplicationContext容器类
 			DI，dependency injection依赖注入
 					创建对象后，处理对象的依赖关系！
 					User user=new User();
 					user.setAddress(...);//需要DI(依赖注入)
 			
 	3）Spring框架
 		Spring开源框架，提供的是一站式解决方案！
 		六大模块
 		  ......
 		
 	4）Spring第一个案例，解决项目中如何创建对象！	
 		
 		1) 引入jar文件 (3.2版本)
			commons-logging-1.1.3.jar       【单独下载】
			spring-beans-3.2.5.RELEASE.jar		【spring源码, bean节点管理】
			spring-context-3.2.5.RELEASE.jar      【spring上下文类】
			spring-core-3.2.5.RELEASE.jar         【IOC容器】
			spring-expression-3.2.5.RELEASE.jar    【spring表达式】

		注意：
			使用的版本Spring3.2
			在这个版本中，只有spring自身的核心功能，spring依赖的其他组件，需要单独下载！ 例如：日志jar文件，就需要单独下载！

		2) 新建applicationContext.xml , 源码中拷贝约束
	
			<?xml version="1.0" encoding="UTF-8"?>
			<beans xmlns="http://www.springframework.org/schema/beans"
    			xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    			xmlns:context="http://www.springframework.org/schema/context"
    			xsi:schemaLocation="
        			http://www.springframework.org/schema/beans
        			http://www.springframework.org/schema/beans/spring-beans.xsd
        			http://www.springframework.org/schema/context
        			http://www.springframework.org/schema/context/spring-context.xsd">
			</beans>        

 		3) 配置

			<!-- 创建Dao实例 -->
			<bean id="userDao" class="cn.itcast.dao.UserDao"></bean>
	
			<!-- 创建Service实例 -->
			<bean id="userService" class="cn.itcast.service.UserService">
				<property name="userDao" ref="userDao"></property>
			</bean>
	
			<!-- 创建Action实例 -->
			<bean id="userAction" class="cn.itcast.action.UserAction">
				<property name="userService" ref="userService"></property>
			</bean>

 		
 		4) 测试
			// 从IOC容器获取对象
			@Test
			public void testApp2() throws Exception {
				// 容器对象(加载applicationContext.xml配置文件)
				ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
				// 获取对象
				UserAction userAction = (UserAction) ac.getBean("userAction");
				userAction.execute();
			}

 		
 		
 2、Spring IOC容器
 		创建bean细节：
 		
 		1)bean在容器中的写法
 		2）bean的生命周期
 
 		<?xml version="1.0" encoding="UTF-8"?>
		<beans xmlns="http://www.springframework.org/schema/beans"
    		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    		xmlns:context="http://www.springframework.org/schema/context"
    		xsi:schemaLocation="
        		http://www.springframework.org/schema/beans
        		http://www.springframework.org/schema/beans/spring-beans.xsd
        		http://www.springframework.org/schema/context
        		http://www.springframework.org/schema/context/spring-context.xsd">
	
			<!-- 把对象加入IOC容器 -->
			<!-- 
			<bean id="user1" name="user2,user3,user4" class="cn.itcast.a_config.User"></bean>
			细节1：
				id 与 name的区别：
					id 不能以数字开头，不能含有特殊符号，不能有空格、逗号等;id 不能重复！
					name 可以以数字开头，可以有特殊符号，如果name值重复，编译没有问题但运行保存！
			<bean id="user" name="user2,user3 user4" class="cn.itcast.a_config.User"></bean>
			<bean name="user5" class="cn.itcast.a_config.User"></bean>
			<bean id="user6" class="cn.itcast.a_config.User"></bean>
			<bean id="user6" class="cn.itcast.a_config.User"></bean>
	 		-->
	 	
	 		<!-- 细节2： (单例/多例)-->
	 		<!-- 
	 			scope="singleton"  默认表示单例！
	 			scope="prototype"  表示多例
	 	 		init-method=""  在创建完对象之后执行初始化方法
	 			destroy-method=""	在调用容器类的destroy()方法时候，对单例的对象有效！
	 			lazy-init="true"  延迟初始化 / 这个属性的设置只对单例有影响，对多例没有影响       单例的对象默认是在创建容器的时候就创建所有单例的对象，如果希望在第一次访问的时候创建单例的对象，就设置延迟初始化
	 	
	 			Bean的生命周期：
	 		 		bean在xml中配置：
	 		   		singleton 单例
	 		 			1）创建对象
	 		 				如果有配置延迟初始化：
	 		 					lazy-init=true  如果单例的对象有配置延迟初始化，在创建容器之后，在第一次从容器取货对象的时候创建单例的对象！
	 		 				如果没有配置或延迟初始化为默认值，单例的对象会在创建容器的时候创建对象
	 		 			2）执行初始化，ini-method配置的方法会执行
	 		 			3）调用destroy()方法时候，容器在销毁单例对象的实例的时候，会调用destroy-method对象的方法，此时bean对象会被销毁！	
	 		 	
	 		 		prototype 多例
	 		 			1）每次在从容器获取对象的时候，都会创建新的对象
	 		 			2）每次创建完对象后，就执行初始化方法
	 		 			3）java会回收不用的资源(jvm  gc)	
	 		 	
	  			-->
	 				<bean id="user" class="cn.itcast.a_config.User"  init-method="init" destroy-method="destroy_" lazy-init="true" scope="singleton"></bean>
				</beans>      


		
		创建对象的几种方式
			1）调用无参构造器
			2）调用有参数构造器
			3）工厂
				* 静态方法
					<bean id=”’  class=””  factory-method=””/>
				* 非静态方法
					<bean id=”factory”  class=”..”>
					<bean id=””  factory-bean=” factory”   factory-method=”实例方法” />
			4)  反射


	
 处理对象的依赖关系:
 	给对象属性赋值(DI，依赖注入),几种方式
  		1)构造函数赋值
  		2）set方法注入值
 			*普通字段赋值
 			*集合属性(list/map/property)
 		3）案例
 			Dao/service/action实例，处理依赖关系
 			*常用的通过set方法注入（必须要掌握）
 			*内部bean
 			*p 名称空间
 			*自动装配
 			*注解(必须要掌握)
 
 				注解总结：
 					@Component 表示一个组件(类)，把当前组件加入ioc容器，	
 									加入容器的组建的名称默认是类名第一个字母小写
 					@Component("") 指定加入ioc容器的组件类的类名
 					
 					@Repository 标识是一个持久层的组建
 					@Service 标识是一个业务逻辑层的组建
 					@Controller 标识是一个控制层的组建
 					
 					@Scpe("prororype") 指定对象多例
 					@Scpe("single") 指定对象单例
 					@Resource  1、默认根据修饰符的字段名称获取ioc容器找对象自动注入，找到后注入
 							   2、如果名称没有找到，再根据类型查找，找到后就立刻注入。如果该类型在ioc容器中有多个对象，报错！
 							   3、根据类型也没有找到对象，报错！
 					@Resource(name = "") 会根据指定的名称去容器找对象自动注入
 
 			
 			配置方式与注解方式：
 				1、配置：便于维护
 					(配置过多，比较繁琐)
 				2、注解、开发方便
 					(简化配置，不利于后期维护，如果修改对象创建、处理关系，需要改代码！)
 					
 
 3、Spring 与 Struts整合
 	
 	Spring与struts整合，关键点
 		把action的创建，交给spring的ioc容器！
 	所以：需要引入jar文件：
 		struts2-spring-plugin-2.3.4.1.jar		【struts源码】
		spring-web-3.2.5.RELEASE.jar			【spring源码】
	
	整合的完整步骤：
		1、引入jar
			struts核心jar
			Spring
				Core 核心  （5个）
 				Web  对web支持（2个）
 		2、配置     (file:///D:/Encoding/Java/%E5%B8%B8%E7%94%A8%E5%B7%A5%E5%85%B7/spring%E7%9B%B8%E5%85%B3/spring-framework-3.2.5.RELEASE-dist/spring-framework-3.2.5.RELEASE/docs/spring-framework-reference/html/index.html)
 			bean.xml
 			struts.xml
 			web.xml
 				struts2核心过滤器
 				启动时候，配置springIOC容器
 				
 		3、Action类
 			
 		
 		
 		
 		
 		
 		
 		
 				
 				
 				
 				
 				
 				
 				
 				
 				
 					
 					
 					
 					
 					
 					
 					
 					
 					
 					
 					
 					
 					
 					
 					
 					
 					
 					
 					
 					
 					
 					
 					
 					
 					
 					
 					
 					
 					
 					
 					
 					
 					
 					
 					
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 * @author 贤元
 *
 */
public class TodayNote {

}
