<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
  <head>
  	<link rel="stylesheet" type="text/css" href="<c:url value='/css/Pub.css'/>"></link>
  	<script type="text/javascript">
  		var path = "${pageContext.request.contextPath}";
  	</script>
  	<script type="text/javascript" src="${pageContext.request.contextPath }/js/car.js"></script>
  	<style type="text/css">
  		*{
  			font-size:10pt;
  		}
  		table{
  			border:1px solid gray;
  			border-collapse: collapse;
  			width:80%;
  		}
  		td{
  			border:1px solid gray;
  			padding:5px;
  		}
  		.head{
  			background:#9393FF;
  		}
  		.head td{
  			text-align:center;
  			font:bold;
  			padding:5px;
  		}
  		.even{
  			background:#DDDDFF;
  		}
  		td button{
  			margin-left:7px;
  		}
  		.oper{
  			cursor:pointer;
  			border:0px;
  			background:transparent;
  		}
  	</style>
  </head>
  <body>
  		<p>以下是你买的所有图书-&gt;<font color="red" style="font:bold;">订单明细</font>-->选择/添加收货地址->确认订单->网银付款或到货付款->查收货物</p>
  		<table id="table">
  			<tr class="head">
  				<td>
  					书名
  				</td>
  				<td>
  					价格
  				</td>
  				<td>
  					数量
  				</td>
  				<td>
  					合计
  				</td>
  				<td>
  					增/删
  				</td>
  			</tr>
  			<%--定义一个变量，用于存储总结金额 --%>
  			<c:set var="total" value="0"></c:set>
  			<c:forEach items="${sessionScope.car}" var="entry">
  				<tr>
	  				<td>
	  					<a href="${pageContext.request.contextPath }/index?action=queryBook&id=${entry.value.id}">
	  						${entry.value.name }
	  					</a>
	  				</td>
	  				<td>
	  					${entry.value.currentPrice }
	  				</td>
	  				<td>
	  					${entry.value.amt}
	  					
	  				</td>
	  				<td>
	  					<fmt:formatNumber pattern="#.##" value="${entry.value.currentPrice*entry.value.amt }"></fmt:formatNumber>
	  					
	  					<%--累加 --%>
	  					<c:set var="total" value="${total+entry.value.currentPrice*entry.value.amt}"></c:set>
	  				</td>
	  				<td>
	  					<input class="oper" type="button"  onclick="_add('${entry.value.id}')" value="+"/>
	  					&nbsp;
	  					<input class="oper" type="button"  onclick="_del('${entry.value.id}')" value="-"/>
	  				</td>
  				</tr>
  			</c:forEach>
  			<tr class="head">
  				<td colspan="5" style="text-align:right;">
  					总金额：
  					<label id="sum">
  					<%--格式化数字，保留两位小数 --%>
  					<fmt:formatNumber pattern="#.##" value="${total}"></fmt:formatNumber>
  					</label>
  					元
  				</td>
  			</tr>
  			<tr style="background:#E0E0E0;">
  				<td colspan="5" style="text-align:right;">
  					<button class="OperBtn" onclick="sure();" style="width:220px;">下一步->选择收货地址</button>
  					<button class="OperBtn" onclick="alert('请同学们完成\n清空session中的car数据');" style="width:120px;">全部取消</button>
  				</td>
  			</tr>
  		</table>
  </body>
</html>

