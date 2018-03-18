<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
  	<script type="text/javascript" src="<c:url value='/js/show.js'/>"></script>
  	<style type="text/css">
  		table{
  			width:100%;
  			border:0px solid red;
  			font-size:10pt;
  		}
  		td{
  			border-bottom:1px dotted #0000C6;
  		}
  		.img{
  			width:100px;
  			height:120px;
  			border:0px;
  		}
  	</style>
  </head>
  <body style="font-size:10pt;">
  	<p>以下是《${book.name }》的信息</p>
    <table>
    	<tr>
    		<td style="width:120px;">
    			<img class="img" src="${pageContext.request.contextPath }/imgs/${book.img}"></img>
    		</td>
    		<td align="left" valign="top">
    			书名：${book.name }<br/>
    				原价：<font style="text-decoration:line-through;">${book.price }元</font><br/>
    				现价：${book.currentPrice }元<br/>
    				折扣：${book.rebate }折<br/>
    			作者：${book.auth }<br/>
    			出版：清华出版社<br/>
    			版次：第3版，第4次印刷<br/>
    			出版时间：2015-06-23<br/>
    			开本：16开，共230页<br/>
    			<a href="javascript:_go('${book.id}','${book.name}','${book.currentPrice}','${book.img}');">
    				<img style="border:0px;" src="${pageContext.request.contextPath }/css/imgs/btn_buy.gif"></img>
    			</a>
    		</td>
    	</tr>
    	<tr>
    		<td colspan="2">
    			<p>以下是书的说明信息</p>
    			<p>简介：</p>
    			书籍简介
    			<hr style="border:1px dotted blue;"/>
    			<p>目录</p>
    			第一章：xxxxx<br/>
			第二章：xxxxxxx
    		</td>
    	</tr>
    </table>
  </body>
</html>
