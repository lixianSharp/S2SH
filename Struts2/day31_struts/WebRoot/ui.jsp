<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%--导入struts标签库 --%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>ui.jsp</title>

  </head>
  
  <body>
  	<!-- 使用struts表单标签 -->
  	<!-- 注意：使用struts标签中的表单提交的时候，提交的路径直接写在struts配置文件中的：namespace名/action的name名字就可以 -->
    <s:form action="/validate/user_register" method="post" name="userForm" theme="xhtml"> 
    	<s:textfield name="userName" label="用户名"></s:textfield>
    	<s:password name="userPwd" label="密码"></s:password>
    	<s:submit value="登陆"></s:submit>
    </s:form>
    
    <hr/>
	
	
	
	<h3><font color="red" size="+6">checkboxlist标签</font></h3>
	<%--
		list:显示的内容
		value:默认值(出现哪个值就会勾选哪个框)
		
	 --%>
	<s:checkboxlist name="hobit" list="{'篮球','乒乓球','羽毛球'}" value="{'乒乓球'}"></s:checkboxlist>
    <s:checkboxlist name="hobit2" list="#{101:'篮球',102:'足球',103:'羽毛球' }"></s:checkboxlist><br/>
	
	<%--获取保存在request域中的数据  使用checkboxlist标签获取 
		list:取出那个域中的数据
		listValue:用那个数据作为显示数据
		listKey:用那个数据作为ckeckbox的value	
	--%>
	<s:checkboxlist name="hobit3" list="#request.userList" listKey="#request.name" listValue="#request.password"></s:checkboxlist><br/>
	<s:checkboxlist name="hobit4" list="#request.userMap" ></s:checkboxlist>
	
    <hr/>
    
    <%-- <s:set var="userName" value="'eric'" scope="request"></s:set> --%>
    
    <%-- <s:property value="#request.userName"/> --%>
 
 
	<%--数据回显  在编辑数据需求下需要用到数据回显 --%>
	<s:textfield name="userName" label="用户名"></s:textfield>
	<s:password name="userPwd" label="密码" showPassword="true"></s:password>
	
	
	<s:debug></s:debug>
  </body>
</html>
