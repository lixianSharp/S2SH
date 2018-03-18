<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>登录页面</title>  
  </head>
  
  <body>
  		<h3>登录页面</h3>
    <form action="${pageContext.request.contextPath }/login.action" method="post">
    	用户名:<input type="text" name="name"/><br/>
    	密码：<input type="password" name="password"/><br/>
    	<input type="submit" value="登录"/>
    </form>
  </body>
</html>
