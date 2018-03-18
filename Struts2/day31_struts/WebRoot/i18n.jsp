<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 导入struts核心标签库 -->
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>国际化</title>
   
  </head>
  
  <body>
   <%--获取国际化资源包的内容 --%>
   <form action="#">
   		<!-- 在页面上使用资源包的内容 -->
   		<s:text name="user"></s:text><input type="text" name="name"/><br/>
   		<s:text name="password"></s:text><input type="password" name="password"/><br/>
   		<input type="submit" value="<s:text name="login"/>"/>
   </form>
   
  </body>
</html>
