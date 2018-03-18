<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
  	</style>
  </head>
  <body>
  	  <p>选择/输入送货地址->订单明细-->选择/添加收货地址->确认订单->网银付款或到货付款-><font color="red" style="font:bold;">查收货物</font></p>
  	  <div style="text-align:center;font:bold;">
  	  	  订单生成成功，可以查看订单进度。保持电话畅通。订单号：${orderId}<br/>
  	  	 <a href="${pageContext.request.contextPath }/order/order?action=queryOrders">转去查看订单</a>
  	  </div>
  </body>
  <script type="text/javascript">
  </script>
</html>
