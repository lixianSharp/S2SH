<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>文件上传</title>
    
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
    <%--文件上传的三个条件：
    	1、提交方式必须为POST提交
    	2、enctype="multipart/form-data"
    	3、表单有file,也就文件的类型为file
     --%>
     
     <form action="${pageContext.request.contextPath }/upload/upload.action" 
     		method="POST" enctype="multipart/form-data">
     	文件：<input type="file" name="attach"/><br/>
     	描述：<input type="text" name="info"/><br/>
     	<input type="submit" value="上传"/>
     </form>
 
  </body>
</html>
