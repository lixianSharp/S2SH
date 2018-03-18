<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%--导入struts标签库 --%>
<%@taglib uri="/struts-tags"  prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>模型驱动(可以防止表单重复提交)</title>
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
  	<h3><font color="red">这是model.jsp,如果表单中的数据重复提交了，就会再次跳转到这个页面中</font></h3>
  	<%--打印错误信息 --%>
  	<!-- 获取所有的错误信息 -->
  	<%--<font color="red"><s:fielderror></s:fielderror></font> --%>
 	<%--打印指定错误信息 --%>
 	 <s:fielderror fieldName="user.error"></s:fielderror>
     <form action="${pageContext.request.contextPath }/model/user_register.action" method="post">
      	用户名:<input type="text" name="user.name"/><br/>
      	密码:<input type="password" name="user.password"/><br/>
      	
      	<%--在当页面生成token值 --%>
      	token值：<s:token></s:token>
      	
      	<input type="submit" value="<s:text name="login"/>"/>
      </form>
  </body>
</html>
