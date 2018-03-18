<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>主页的过渡页面，跳转到 index?action=showIndex</title>  
  </head>
  
  <body>
  	
  	<c:redirect url="/front/index_showIndex">
  	</c:redirect>   
  	 
  </body>
</html>
