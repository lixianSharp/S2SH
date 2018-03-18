package TodayNote;
/**
 回顾：
 		
 		核心业务：
 			一、国际化
 				1）写好资源包
 				2）在struts.xml配置国际化的资源包。message_zh_CN.properties
 				3）使用：
 					jsp页面：<s:text name="user"/>
 					action: ActionSupport.getText("user")
 
 			二、数据验证：后台数据验证
 				1）代码方式验证所有方法
 					重写Validatable接口的方法：validate()  写验证逻辑
 				2）代码方式验证置顶的方法：
 					修改validate方法名：validateRegister()  validate+方法名()
 				3）xml配置方式验证所有方法：
 					ActionName-validation.xml（和action方法在同一目录）
 					
 					<validators>
 						<field name="user.name">
 							<field-validator type="requriredstring/int/regex...">
 								<message>错误提示信息</message>
 							<field-validator>
 						</field>
 					</validators>
 				
 				4）xml配置方式验证指定方法
 					修改验证文件名称：ActionName-方法调用路径-validation.xml（和action方法在同一目录）
 									UserAction-user_register-validation.xml
 			
 			
 			三、，模型驱动
 				接口：ModelDriven<T>
 						<input type="text" name="name"/> -> UserAction
 						
 			四、struts2的标签
 					<%@ taglib uri="/struts-tags" prefix="s"%>
 			
 				逻辑标签：
 					迭代：<s:iterator value="" var="" status="">
 					赋值：<s:set var="" value="ognl表达式"/>
 					获取：<s:property value="ognl表达式"/>				
 					判断：<s:if/> <s:elseif/> <s:else>
 					
 				UI标签： 1）节省html代码编写  2）数据回显功能
 						<s:form/> -> <form>
 						<s:textfield/> -> <input type="text"/>
 						<s:password/> -> <input type="password"/>
 						<s:checkbox/> -> <input type="checkbox"/>
 						<s:checkboxlist list="多个值"> -> 多个<input type="radio"/>
 						<s:select list="多个值"/> -> <select>多个<option/></select>
 				
 			五、防止重复提交
 					1）打开功能：TokenIntecetpot拦截器
 					2）在jsp页面加入<s:token/>标签
 			
 今天的目标：改造之前的	《网上图书销售系统》
 			之前的架构：
 				web层  	 	 -》 		   service层	 -》 		dao层 	-》 		mysql数据层
 			  servlet+jsp(jstl+el)		(普通的java类)					jdbc(dbutil)
 			  (遵守MVC模式)
 					
 			现在的架构：
 				web层  	     		 -》    service层	 -》 		dao层 	-》 		mysql数据层
 			  struts2+jsp(strutsUI标签) 		(普通的java类)					jdbc(dbutil)
 			 (遵守MVC模式)
 					
 1)项目的目录结构
 		|-bookstore 					
 				|-gz.itcast.entity
 				|-gz.itcast.filter
 				|-gz.itcast.interceptor(拦截器包)
 				|-gz.itcast.utils
 				|-gz.itcast.biz
 							|-front
 								|-book
 									|-dao
 									|-service
 									|-action  (struts2的action类)
 									|-servlet （写servlet）
 							|-admin
 				|-config （配置文件 struts2配置、hibernate配置文件、spring配置文件）			
 		|-WebRoot（保持不变）					
 
 2）设计struts2的配置文件（config目录）
 		struts-constant.xml  存放struts框架的常量值
 		struts-front.xml    存放前台业务的action配置
 						<package namespace="/front">
 				eg:模块	 <action name="book_*" class="gz.itcast.biz.front.book.web.action.BookAction" method="{1}">
 				
 		struts-admin.xml	存放后台业务的action配置
 						<package namespace="/admin">
 				eg:模块	 <action name="book_*" class="gz.itcast.biz.admin.book.web.action.BookAction" method="{1}">

 		
 		
 3)struts2开发步骤		
 		3.1 导入jar包
 		3.2配置全局过滤器
 		3.3在src下方struts.xml文件
 
 4)设计一个BaseAction公共Action类
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 							
 					
 							
 * @author 贤元
 *
 */
public class TodayNote {

}
