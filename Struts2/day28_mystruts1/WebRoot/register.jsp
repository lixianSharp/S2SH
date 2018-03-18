<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 
    <title>注册</title>
    
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
  		<h3><font color="red">注册页面</font></h3>
    	<form action="${pageContext.request.contextPath}/RegisterServlet" method="POST">
    		用户名：<input type="text" name="name"/><br/>
    		密码：<input type="password" name="password"/><br/>
    		<input type="submit" value="登陆" />
    	</form>
  </body>
</html>
