<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>欢迎来到叮咚网后台管理网站</title>
  	<style type="text/css">
  		#content td{
  			border:1px solid gray;
  			border-bottom:0px;
  		}
  		.type{
  			margin-top:10px;
  			font-size:10pt;
  		}
  		.table{
  			border:1px solid gray;
  			border-bottom:0px;
  			width:1020px;
  			height:200%;
  			border-collapse:collapse;
  		}
  		.menu{
  			background:#CECEFF;
  			margin:5px;
  			width:100%;
  			height:20px;
  			vertical-align:middle;
  			text-align: center;
  		}
  		.on{
  			background:#AAAAFF;
  			margin:5px;
  			width:100%;
  			height:20px;
  			vertical-align:middle;
  			text-align: center;
  		}
  		.menu a{
  			text-decoration:none;
  		}
  		.btn{
  			border:0px;
  			background:transparent;
  			width:100px;
  		}
  		.txt{
  			border:1px solid blue;
  		}
  		td a{
  			font-size:10pt;
  		}
  	</style>
  </head>
  <body style="text-align:center;margin-bottom:0px;margin-top:0px;">
     <table class="table">
     	<tr style="height:60px;background:#9393FF">
     		<td colspan="2">
     			<table style="border:0px;width:100%;height:100%;">
     				<tr>
     					<td width="60%" align="center">
     						欢迎来到叮咚网后台主页!
     					</td>
     					<td align="right" style="padding-right:10px;font-size:10pt;font:bold;">
     							欢迎你：
     							<a href="#">${admin.name}</a>
     								|
     							<a target="_content" href="#">密码管理</a>
     								|
     							<a target="_content" href="#">安全退出</a>
     					</td>
     				</tr>
     			</table>
     		</td>
     	</tr>
     	<tr id="content">
     		<td style="width:120px;vertical-align:top;padding:10px;">
     			<div class="menu" onmousemove="chg(this);" onmouseout="_chg(this);">
     					<a target="_content" href="${pageContext.request.contextPath }/admin/type?action=queryTypes" class="type">
     						图书分类
     					</a>
     			</div>
     			<div class="menu" onmousemove="chg(this);" onmouseout="_chg(this);">
     					<a target="_content" href="${pageContext.request.contextPath }/admin/book_toAdd" class="type">
     						图书上架
     					</a>
     			</div>
     			<div class="menu" onmousemove="chg(this);" onmouseout="_chg(this);">
     					<a target="_content" href="#" class="type">
     						图书管理
     					</a>
     			</div>
     			<div class="menu" onmousemove="chg(this);" onmouseout="_chg(this);">
     					<a target="_content" href="#" class="type">
     						发货管理
     					</a>
     			</div>
     		</td>
     		<td>
     			<iframe name="_content" src="" frameborder="0" width="100%" height="100%">
     			
     			</iframe>
     		</td>
     	</tr>
     </table>
  </body>
   <script type="text/javascript">
  	function chg(obj){
  		obj.className="on";
  	}
  	function _chg(obj){
  		obj.className="menu";
  	}
  </script>
</html>
     					