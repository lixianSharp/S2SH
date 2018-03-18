<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 引入jsp的核心标签 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>获取三个域中的数据</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<!-- 三个域都能取到数据 -->
  	<h3>request域</h3>
	<c:forEach items="${requestScope.request_list }" var = "user"><!-- 从那个域中获取可以用 域范围常量 来获取该域中的数据 -->
		${user }<br/>
	</c:forEach>   

  	<h3>session域</h3>
	<c:forEach items="${sessionScope.session_list }" var = "user"><!-- 从那个域中获取可以用 域范围常量 来获取该域中的数据 -->
		${user }<br/>
	</c:forEach>   

  	<h3>context域</h3>
	<c:forEach items="${applicationScope.context_list }" var = "user"><!-- 从那个域中获取可以用 域范围常量 来获取该域中的数据 -->
		${user }<br/>
	</c:forEach>   

  </body>
</html>
