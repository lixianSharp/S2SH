<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%--引入struts标签库 --%>
<%@taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>ognl表达式   查看值栈数据</title>
	<%--
	结论：
		1）从OgnlContext对象的根对象取出数据，不需要#号
		2）从OgnlContext对象的非根对象取出数据，需要#号	
	
	ValueStack的数据存储结构：分为  List栈（根栈）  和  Map栈（非根栈）
	1）List栈主要存储Action对象和Provider代理对象	
	2）Map栈主要存放各个域存放的数据和用户的参数信息
	
	注意：(List栈、Map栈 )与( List集合、Map集合)是两个不同的概念，别搞混了。
	 --%>

  </head>
  <!-- 注意：application就是jsp的context域对象，pageContext域的作用范围只能在当前页面，pageContext是jsp的上下文对象，他能获取到另外8个内置对象 -->
  <body>
	<!-- 1）取出List栈(根栈)的数据 -->    
	<s:property value="user.name"/> - <s:property value="user.age"/><br/>
	<s:property value="book.name"/> - <s:property value="book.user.name"/> - <s:property value="book.user.age"/><br/>
	<!-- 
		打印结果：
			rose - 20
			java基础 - rose - 20 
	 -->
	 
	 <!-- 2)取出Map栈(非根栈)的数据  Map栈主要存放各个域存放的数据和用户的参数信息 -->
	 <%--取出request域的数据 --%>
	 <s:property value="#request.request_data"/><br/><!-- 结果：request_data -->
	 <%--因为值栈对象是通过request域传递到页面，所以可以直接忽略#request去取request域的数据--%>
	 <s:property value="#request_data"/><br/><!-- 结果：request_data --><!-- request的比较特殊，只有request的才可以这样 -->
	 <!-- 取出session域的数据 -->
	 <s:property value="#session.session_data"/><br/><!--结果： session_data -->
	 <!-- 取出application域(context域)中的数据 -->
	 <s:property value="#application.application_data"/><br/><!-- 结果：application_data -->
	 
	 
	 <%-- #attr :类似于findAttribute,依次从小到大在三个域中搜索数据：#request -》#session ->#application--%>
	 <s:property value="#attr.request_data"/><br/>
	 <s:property value="#attr.session_data"/><br/>
	 <s:property value="#attr.application_data"/><br/>
	 <!-- 
	 结果：
	 	request_data
		session_data
		application_data
	  -->
	 
	 ${session_data }<br/><!-- 通过EL表达式获取session域中的数据  结果：session_data -->
	 

	 <!-- 注意：从非根栈取出数据，需要#号 -->
	 <%--遍历集合： List --%><!-- struts标签的迭代器遍历集合 -->
	 <s:iterator  value="#request.userList" var="user"> 
	 	姓名：<s:property value="#user.name"/> - <s:property value="#user.age"/><br/>
	 </s:iterator>
	 <!-- 
	 	结果：
	 		姓名：eric - 20
			姓名：jacky - 30
			姓名：rose - 40
			姓名：lucy - 50
	  -->
	  
	  
	 <%--遍历集合： Map --%>
	 <s:iterator value="#request.userMap" var="entry">
	 	编号：<s:property value="#entry.key"/>
	 			-姓名：<s:property value="#entry.value.name"/>-年龄：<s:property value="#entry.value.age"/><br/>
	 </s:iterator>
	 <!-- 遍历结果：
	 	编号：102-姓名：lily-年龄：40
		编号：101-姓名：maxwell-年龄：30
		编号：100-姓名：mark-年龄：20	
	 	 -->
	 <%--查看值栈的所有数据 --%>
	 <s:debug></s:debug>
	 
	
  </body>
</html>
