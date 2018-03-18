<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%--使用jsp的核心标签库 --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>文件的下载页面</title>
    
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
    <table border="1" width="300px">
    	<tr>
    		<th>编号</th>
    		<th>文件名称</th>
    		<th>操作</th>
    	</tr>
    	<c:forEach items="${list }" var="file" varStatus="varSta">
    	<tr>
    		<th>${varSta.count }</th>
    		<th>${file }</th>
   			<th><a href="${pageContext.request.contextPath}/upload/down_down.action?name=${file}">下载</a></th><!-- 携带参数 -->
    	</tr>
    	</c:forEach>
    </table>
  
  </body>
</html>
