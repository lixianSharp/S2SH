<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
  	<link rel="stylesheet" type="text/css" href="<c:url value='/css/Pub.css'/>"></link>
  	<script type="text/javascript">
  		var path = "${PageContext.request.contextPath}/";
  	</script>
  	<style type="text/css">
  		table {
			border: 1px solid gray;
			border-collapse: collapse;
			width: 100%;
		}
		td {
			border: 1px solid gray;
			padding: 5px;
		}
		
		.head {
			background: #9393FF;
		}
		
		.head td {
			text-align: center;
			font: bold;
			padding: 5px;
		}
		
		.even {
			background: #DDDDFF;
		}
		
		td button {
			margin-left: 7px;
		}
		
		.oper {
			cursor: pointer;
			border: 0px;
			background: transparent;
		}
		
		.txt {
			border:0px;
			border-bottom:1px solid  blue;
			width:100%;
		}
  		.textarea{
  			width:100%;
  			height:50px;
  			border:1px solid gray;
  		}
  	</style>
  </head>
  <body>
  	<br/>
  	<p>添加分类：</p>
  	<form target="dataFrame" name="f1" method="post" action="${pageContext.request.contextPath }/admin/type?action=saveTypes">
  		<table id="table">
  			<colgroup>
  				<col width="30%">
  				<col width="70%">
  			</colgroup>
  			<tr>
  				<td>
  					分类名称：
  				</td>
  				<td>
  					<input class="txt" type="text" name="name"/>
  				</td>
  			</tr>
  			<tr>
  				<td>
  					说明：
  				</td>
  				<td>
  					<textarea class="textarea" name="descr"></textarea>
  				</td>
  			</tr>
  			<tr>
  				<td colspan="2" align="center">
  					<button class="OperBtn" onclick="_save();">保存</button>
  					<button class="OperBtn" onclick="window.close();">取消</button>
  				</td>
  			</tr>
  		</table>
  	</form>
  	<iframe name="dataFrame" style="display:none;"></iframe>
  </body>
  <script type="text/javascript">
  	 function _save(){
  		 //前面验证用户是否输了的信息
  		 document.forms['f1'].submit();
  	 }
  	 //调用这个方法，父窗口就会刷新和当前窗口关闭
  	 function saveBack(){
  		 window.opener.window.location.reload();
  		 window.close();
  	 }
  </script>
</html>
