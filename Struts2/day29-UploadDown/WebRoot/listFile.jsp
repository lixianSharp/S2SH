<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%--使用jsp的核心标签库 --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>文件的下载页面</title>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.2.min.js"></script>

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
    		<td>${varSta.count }</td>
    		<td class="filename">${file }</td>
   			<td>
   			<a href="${pageContext.request.contextPath}/upload/down_down.action?name=${file}">下载</a>  
   			</td><!-- 携带参数 --> 
    	</tr>
    	</c:forEach>
    </table>
  	<a href="${pageContext.request.contextPath}/upload/down_list.action">我要下载文件啊</a>
  </body>
</html>
