<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  	<title>欢迎来到叮咚图书网上商店!</title>
  	<script type="text/javascript">
function reinitIframe(){
var iframe = document.getElementById("_content");
try{
var bHeight = iframe.contentWindow.document.body.scrollHeight;
var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
var height = Math.max(bHeight, dHeight);
iframe.height = height;
}catch (ex){}
}
window.setInterval("reinitIframe()", 200);
</script>
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
     	<tr style="height:60px;background:#fffff">
     		<td colspan="2">
     			<table style="border:0px;width:100%;height:80px;">
     				<tr>
     					<td width="60%" align="center">
						<img src="../../imgs/logo.gif"/>
     						欢迎来到叮叮主页!
     					</td>
     					<td align="right" style="padding-right:10px;font-size:10pt;font:bold;">
     							<s:if test="#session.user==null">
     								<a target="_content" href="${pageContext.request.contextPath }/jsp/front/user/reg.jsp">注册</a>
     								<a target="_content" href="${pageContext.request.contextPath }/jsp/front/user/login.jsp">登录</a>
     							</s:if>
     							<s:else>
     								欢迎你：
     								<a href="#"><s:property value="#session.user.name"/></a>
     								|
     								<a target="_content" href="${pageContext.request.contextPath }/order?action=queryOrders">订单管理</a>
     								|
     								<a target="_content" href="${pageContext.request.contextPath }/jsp/front/buy/car.jsp">查看购物车</a>
     							</s:else>
     					</td>
     				</tr>
     			</table>
     		</td>
     	</tr>
     	<tr id="content">
     		<td style="width:120px;vertical-align:top;padding:10px;">
     			<div class="menu" onmousemove="chg(this);" onmouseout="_chg(this);">
     					<a target="_content" href="${pageContext.request.contextPath }/index?action=queryBooks" class="type">
     						全部
     					</a>
     			</div>
     			<s:iterator value="#request.types" var="type">
     				<div class="menu" onmousemove="chg(this);" onmouseout="_chg(this);">
     					<a target="_content" href="${pageContext.request.contextPath }/front/index_queryBooks?typeId=<s:property value="#type.id"/>" class="type">
     						<s:property value="#type.name"/>
     					</a>
     				</div>
				</s:iterator>
     		</td>
     		<td>
     			<table style="width:100%;">
     			<tr>
     				<td style="text-align:center;background:#E0E0E0;height:30px;" colspan="2">
     					<select class="txt">
     						<option value="name">书名</option>
     						<option value="auth">作者</option>
     					</select>：
     			　		<input class="txt" type="text"/><button class="btn">查询</button>
     				</td>
     			</tr>
     			</table>
     			
     			<iframe id="_content" scrolling="no" name="_content" src="${pageContext.request.contextPath }/front/index_queryBooks" frameborder="0" width="800px" height="600px">
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
     					
     					