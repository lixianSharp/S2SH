<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
  <head>
  	<style type="text/css">
  		.div{
  			border:1px dotted #E0E0E0;
  			font-size:10pt;
  			margin:3px;
  			float:left;
  			text-align:center;
  			width:120px;
  		}
  		.img{
  			width:100px;
  			height:120px;
  			border:0px;
  		}
  		.divin{
  			border:1px solid gray;
  			font-size:10pt;
  			margin:3px;
  			float:left;
  			text-align:center;
  			width:120px;
  		}
  	</style>
  </head>
  <body>
  	<s:iterator value="#request.books" var="book">
  		<div class="div" onmousemove="divin(this);" onmouseout="divout(this);">
  			<a href="${pageContext.request.contextPath }/front/index_queryBook?id=<s:property value="#book.id"/>">
  				<img class="img" src="${pageContext.request.contextPath }/imgs/<s:property value="#book.img"/>"></img>
  			</a>
  			<br/>
  			<s:property value="#book.name"/>
  			<br/>
  			<font style="text-decoration:line-through;"><s:property value="#book.price"/>元</font>
  			<font style="color:red;"><s:property value="#book.currentPrice"/>元</font>
  		</div>
	</s:iterator>
  		
  	<table width="800px">
  		<tr>
  		<td>

    	 	 		<a href="javascript:void(0)" onclick="toPage(${pageBean.firstPage})">首页</a>&nbsp;
    	 			<a href="javascript:void(0)" onclick="toPage(${pageBean.prePage})">上一页</a>&nbsp;
    	 	 		<a href="javascript:void(0)" onclick="toPage(${pageBean.nextPage})">下一页</a>&nbsp;
    	 			<a href="javascript:void(0)" onclick="toPage(${pageBean.totalPage})">末页</a>&nbsp;
    	 		当前为第1页/共5页&nbsp;
    	 		共20条数据&nbsp;
    	 		每页显示 <input type="text" size="2" id="pageSize" value="5" onblur="changePageSize()"/> 条&nbsp;
    	 		跳转到第<input type="text" id="curPage" size="2" onblur="changePage()"/>页
    	</td></tr>
    	</table>
  </body>
</html>
