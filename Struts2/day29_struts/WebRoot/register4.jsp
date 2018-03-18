<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'register4.jsp' starting page</title>
    
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
  
    <form action="${pageContext.request.contextPath }/convert/user_register.action" method="POST">
    	用户名： <input type="text" name="user.name"/><br/>
    	年龄: <input type="text" name="user.age"/><br/>
    	成绩： <input type="text" name="user.score"/><br/>
    	出生日期： <input type="text" name="user.birth"/><br/>
    	<input type="submit" value="注册"/>
    </form>
  </body>
</html>
