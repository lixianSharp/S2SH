<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
  	<link rel="stylesheet" type="text/css" href="<c:url value='/css/Pub.css'/>"></link>
  	<script type="text/javascript">
  		var path = "${pageContext.request.contextPath}";
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
  		td button{
  			margin-left:5px;
  		}
  	</style>
  </head>
  <body>
  	  <%--判断是否是从订单明细过来的数据，如果是则显示进度 --%>
  	  <c:if test="${!empty requestScope.order}" var="boo">
  	  	 <p>选择/输入送货地址->订单明细--><font color="red" style="font:bold;">选择/添加收货地址</font>->确认订单->网银付款或到货付款->查收货物</p>
  	  </c:if>
  	  <c:if test="${!boo}">
  	  	<p>以下是你的收货地址列表：</p>
  	  </c:if>
  	  <table id="table">
  			<tr class="head">
  				<td style="width:60%;">
  					收货人姓名/地址
  				</td>
  				<td>
  					电话
  				</td>
  				<td>
  					邮编
  				</td>
  				<td style="width:15%;">
  					默认地址
  				</td>
  			</tr>
  			<c:forEach items="${addressList}" var="addr">
 				 	<tr>
 				 		<td>
 				 			${addr.user.name }/${addr.name}
 				 		</td>
 				 		<td>
 				 			${addr.phone}
 				 		</td>
 				 		<td>
							${addr.zip} 				 			
 				 		</td>
 				 		<td>
 				 			<input type="radio" <c:if test="${addr.dft==1}">checked='checked'</c:if> name="dft" />
 				 			<button class="OperBtn" style="width:40px;" onclick="_del('${addr.id}');">删除</button>
 				 		</td>
 				 	</tr>
  			</c:forEach>
  			<tr style="background:#E0E0E0;">
  				<td colspan="4" style="text-align:right;">
  					<button class="OperBtn" style="width:120px;" onclick="_toOrder();">下一步->订单页面</button>	
  					<button class="OperBtn" onclick="_add();">增加</button>
  				</td>
  			</tr>
  		</table>
  		<form id="f1" name="f1" action="${pageContext.request.contextPath}/order?action=preViewOrder" method="post">
  			<input id="consignee"  type="hidden" name="consignee"/>
  		</form>
  </body>
  <script type="text/javascript">
  		//跳转到添加页面
  	  function _add(){
  		  var url = path+"/jsp/front/address/add.jsp";
  		  window.open(url,"_blank","width=410px;height=300px;");
  	  }
  	  //设置为默认收货地址
  	  function _chgDft(obj,id){
  		 if(obj.checked==true && obj.value==1){//如果本来就是选中，则不做任何工作
  			 return;
  		 }
  		 var url = path+"/address/address?action=changeToDefault&id="+id;
  		 window.location.href=url;
  	  }
  	  //删除一个地址
  	  function _del(id){
  		 var url = path+"/address/address?action=delAddress&id="+id;
  		 window.location.href=url;
  	  }
  	  //确定订单
  	  function _toOrder(){
  		//1)检查是否选择了一个默认收货地址
  		//获取单选按钮
  		var flag = false;
  		var dftList = document.getElementsByName("dft");
  		for(var i=0;i<dftList.length;i++){
  			if(dftList[i].checked==true){
  				//有一个被选择了！！
  				flag = true;
  				//2)拿到单选对象所在的这行的收货人相关的信息
  				//得到所在行节点
  				var trNode = dftList[i].parentNode.parentNode;
  				//得到所有子节点
  				var childNodes = trNode.childNodes;
  				
  				//第一个td节点的文本内容
  				var address = childNodes[1].innerHTML;
  				//第二个td节点的文本内容
  				var phone = childNodes[3].innerHTML;
  				
  				var consignee = address.trim()+","+phone.trim();
  				//把数据放入f1表单
  				document.getElementById("consignee").value = consignee;
  				//alert(consignee);
  				//提交表单
  				document.getElementById("f1").submit();
  				break;
  			}
  		}
  		//没有一个单选按钮被选中
  		if(!flag){
  			alert("请求选择一个默认收货地址");
  		}
  	  }
  </script>
</html>
