<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
  <head>
  	<title>一张订单的信息</title>
  	 	<link rel="stylesheet" type="text/css" href="<c:url value='/css/Pub.css'/>"></link>
  	<script type="text/javascript">
  		var path = "<c:url value='/'/>";
  	</script>
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
  		.txt{
  			width:100%;
  			border:0px;
  			border-bottom:1px solid blue;
  			background:transparent;
  		}
  	</style>
  </head>
  <body>
  	  <p>选择/输入送货地址->订单明细-->选择/添加收货地址-><font color="red" style="font:bold;">确认订单</font>->网银付款或到货付款->查收货物</p>
  	  <div style="background:#F0F0F0;border:1px solid #F0F0FF;">
			<form name="order" action="${pageContext.request.contextPath}/order?action=saveOrder" method="post">
				<table style="width:70%">
					<tr>
						<td>
							订单编号：
						</td>
						<td>
							<input class="txt" readonly="readonly" type="text" name="id" value="${order.id}"/>
						</td>
					</tr>
					<tr>
						<td>
							收货人/地址：
						</td>
						<td>
							<input class="txt" readonly="readonly" type="text" name="consignee" value="${order.consignee}"/>
						</td>
					</tr>
					<tr style="background:#E0E0E0;">
						<td>
							总金额：
						</td>
  						<td>
  							<input class="txt" readonly="readonly" type="text" name="amt" id="amt"/>
  					   </td>
  			       </tr>
  			       <tr>
						<td>
							支付方式：
						</td>
  						<td>
  							<select name="paytype">
  								<option value="1" selected="selected">货到付款</option>
  								<option value="2">在线支付</option>
  							</select>
  					   </td>
  			       </tr>
  			       <tr style="background:#E0E0E0;">
  						<td colspan="2" style="text-align:right;">
  							<button class="OperBtn" onclick="sure();" style="width:220px;">确认订单</button>
  					   </td>
  			       </tr>
				</table>
			</form>  	  
  	  </div>
  	  <p>订单明细：</p>
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
  			</tr>
  			<c:set value="0" var="sum"/>
  			<c:forEach items="${sessionScope.car}" var="entry" varStatus="idx">
  				<tr <c:if test="${idx.count%2==0}">class="even"</c:if>>
	  				<td>
	  					<a href="${pageContext.request.contextPath }/index?action=queryBook&id=${entry.value.id}">
	  						${entry.value.name}
	  					</a>
	  				</td>
	  				<td>
	  					${entry.value.currentPrice}
	  				</td>
	  				<td>
	  					${entry.value.amt}
	  				</td>
	  				<td>
	  					<fmt:formatNumber value="${entry.value.currentPrice*entry.value.amt}" pattern="#.##"/>
	  					<c:set value="${sum+(entry.value.currentPrice*entry.value.amt)}" var="sum"/>
	  					<fmt:formatNumber value="${sum}" pattern="#.##" var="sum"/>
	  				</td>
  				</tr>
  			</c:forEach>
  			<tr class="head">
  				<td colspan="4" style="text-align:right;">
  					总金额：
  					<label id="sum">
  					${sum}
  					</label>
  					元
  				</td>
  			</tr>
  		</table>
  </body>
  <script type="text/javascript">
  		//把订单明细技术出来的总金额，放到订单总览信息的总金额字段
  	  document.getElementById("amt").value=document.getElementById("sum").innerHTML;
  	  
  	  
  	  
  	  function sure(){
  		  var boo = window.confirm("确订要提交订单?");
  		  if(boo){
  			  document.forms['order'].submit();
  		  }
  	  }
  </script>
</html>

