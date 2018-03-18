<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <!-- 不想被外界直接访问的资源可以放到WEB-INF目录下 -->
    <title>登陆成功之后的页面</title>
    


  </head>
  
  <body>
	欢迎回来：${sessionScope.user.name }<!-- 从sesion域中获取数据 -->
  </body>
</html>
