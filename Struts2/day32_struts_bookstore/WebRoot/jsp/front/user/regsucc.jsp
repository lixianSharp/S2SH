<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
  <head>
  	<style type="text/css">
  		*{
  			font-size:10pt;
  		}
  	</style>
  </head>
  <body style="text-align:center">
  	  <span>
  	  	注册成功!<br/>
  	  	<label id="msg"></label><br/>
  	  	<a href="${pageContext.request.contextPath }/jsp/front/user/login.jsp">现在转去登录</a>
  	  </span>
  </body>
  <script type="text/javascript">
  	  //计时
  	  var i=4;
  	  function run(){
  		  i--;
  		  document.getElementById("msg").innerHTML=i+"秒以后自动转到登录页面!";
  		  if(i<1){
  			  window.clearInterval(tm);//清除计时器
  			  window.location.href="${pageContext.request.contextPath }/jsp/front/user/login.jsp";
  		  }
  	  }
  	  var tm = window.setInterval(run, 1000);
  </script>
</html>

