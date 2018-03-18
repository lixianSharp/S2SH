<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 引入struts标签库 -->
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>注册表单</title>
	 <%--http://localhost:8080/day31_struts/validate/user_register --%>

	<style type="text/css">
		ul,ul li{
  			display: inline;
  			
  		}
  		ul li{
  			color:red;
  		}
	</style>

  </head>
  
  <body>
    
    <%--打印指定错误信息 --%>
<%-- 	<s:fielderror fieldName="user.error"></s:fielderror>
	
	<s:fielderror fieldName="user.name"></s:fielderror> --%>
    <%--获取所有的错误信息 第一种方式进行验证，通过validate方法中的代码进行验证 --%>
   <%--  <font color="red"><s:fielderror></s:fielderror></font>  --%>
    
    <form action="${pageContext.request.contextPath }/validate/user_register.action" method="post">
    	<%-- 用代码方式对action的所有方法进行验证 --%>
    	<!--这个等价于:"用户名" 因为他使用的是国际化-->
    	<%-- <s:text name="user"></s:text><input type="text" name="user.name"/>
    	<s:fielderror fieldName="user.error.requried"></s:fielderror><br/><!-- 打印错误信息 -->
    	
    	<s:text name="password"></s:text><input type="password" name="user.password"/>
    	<s:fielderror fieldName="password.error.requried"></s:fielderror><br/><!-- 打印错误信息 -->
    	 <input type="submit" value="<s:text name="login"></s:text>"/> --%>
    	
    	<%--用xml配置方式对action的方法进行验证 --%>
    	 用户名:<input type="text" name="user.name"/><s:fielderror fieldName="user.name"></s:fielderror><br/>
    	 密码：<input type="password" name="user.password"/><s:fielderror fieldName="user.password"></s:fielderror><br/>
    	 <input type="submit" value="<s:text name="login"></s:text>"/>
    </form>
    	  
    
  </body>
</html>
