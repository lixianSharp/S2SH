<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
  <head>
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
  	</style>
  </head>
  <body>
  		<p>订单列表：</p>
  		<table id="table">
  			<colgroup>
  				<col width="10%">
  				<col width="40%">
  				<col width="10%">
  				<col width="10%">
  				<col width="10%">
  				<col width="10%">
  				<col width="10%">
  			</colgroup>
  			<tr class="head">
  				<td>
  					订单号
  				</td>
  				<td>
  					收货人明细
  				</td>
  				<td>
  					总金额
  				</td>
  				<td>
  					订单状态
  				</td>
  				<td>
  					付款方式
  				</td>
  				<td>
  					下单时间
  				</td>
  				<td>
  					操作
  				</td>
  			</tr>
  			<c:forEach items="${orders}" var="order">
  				<tr>
  					<td>
  						<a href="detail.html">
  							${order.id}
  						</a>
  					</td>
  					<td>
  						${order.consignee}
  					</td>
  					<td>
  						${order.amt}元
  					</td>
  					<td>
  						<c:choose>
  							<c:when test="${order.state==0}">
  								等待发货
  							</c:when>
  							<c:when test="${order.state==1}">
  								商家已发货
  							</c:when>
  							<c:when test="${order.state==2}">
  								交易已经完成
  							</c:when>
  							<c:when test="${order.state==3}">
  								交易已经取消
  							</c:when>
  							<c:when test="${order.state==4}">
  								客户已退货
  							</c:when>
  						</c:choose>
  					</td>
  					<td>
  						<c:choose>
  							<c:when test="${order.paytype==1}">
  								货到付款
  							</c:when>
  							<c:otherwise>
  								网银付款
  							</c:otherwise>
  						</c:choose>
  					</td>
  					<td>
  						${order.orderdate}
  					</td>
  					<td>
						<c:if test="${order.state==0}">
							<a href="#">取消订单</a>
						</c:if>
  					</td>
  				</tr>
  			</c:forEach>
  		</table>
  </body>
</html>

