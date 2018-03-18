<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/Pub.css"></link>
  	<%--引入日期选择插件的css和js文件 --%>
  	 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/jscal2/css/jscal2.css" />
  	<script src="${pageContext.request.contextPath }/jscal2/js/jscal2.js"></script>
   	<script src="${pageContext.request.contextPath }/jscal2/js/lang/cn.js"></script>
   	
   	<%--导入文本编辑器的js文件 --%>
  	<script charset="utf-8" src="${pageContext.request.contextPath }/kindeditor/kindeditor.js"></script>
  	
  	<style type="text/css">
  		.table{
  			font-size:10pt;
  			width:530px;
  			border:1px solid gray;
  			border-collapse:collapse;
  		}
  		.txt{
  			border:0px;
  			border-bottom:1px solid blue;
  			width:100%;
  		}
  		.table td{
  			padding:5px;
  			border:1px solid gray;
  		}
  	</style>
  </head>
  <body>
     <p>s请添加新的图书：</p>
     <form enctype="multipart/form-data" action="${pageContext.request.contextPath }/admin/book_saveBook" method="post">
     	<table class="table">
     		<colgroup>
     			<col width="20%" align="right"/>
     			<col width="80%"/>
     		</colgroup>
			  <tr>
			  	<td>
			  		书名：
			  	</td>
			  	<td>
			  		<input type="text" class="txt" name="name"/>
			  	</td>
			  </tr>  
			   <tr>
			  	<td>
			  		价格：
			  	</td>
			  	<td>
			  		<input type="text" class="txt" name="price"/>
			  	</td>
			  </tr>  
			   <tr>
			  	<td>
			  		作者：
			  	</td>
			  	<td>
			  		<input type="text" class="txt" name="auth"/>
			  	</td>
			  </tr>  
			   <tr>
			  	<td>
			  		图片：
			  	</td>
			  	<td>
			  		<input type="file" class="txt" name="attach"/>
			  	</td>
			  </tr>  
			   <tr>
			  	<td>
			  		折扣：
			  	</td>
			  	<td>
			  		<input type="text" class="txt" name="rebate"/>
			  	</td>
			  </tr>  
			   <tr>
			  	<td>
			  		库存数量：
			  	</td>
			  	<td>
			  		<input type="text" class="txt" name="stock"/>
			  	</td>
			  </tr>  
			   <tr>
			  	<td>
			  		出版社：
			  	</td>
			  	<td>
			  		<input type="text" class="txt" name="publisher"/>
			  	</td>
			  </tr> 
			   <tr>
			  	<td>
			  		所属分类：
			  	</td>
			  	<td>
			  		<s:checkboxlist name="typesId" list="#request.types" listKey="id" listValue="name" theme="simple"></s:checkboxlist>
			  		<%--<s:select name="types" list="#request.types" multiple="true" listKey="id" listValue="name"></s:select>--%>	   
			  	</td>
			  </tr>  
			   <tr>
			  	<td>
			  		出版时间：
			  	</td>
			  	<td>
			  		<input id="publishdate" style="width:120px;" readonly="readonly" type="text" class="txt" name="publishdate"/>
			  		<input type="button" id="btn" value="..."/>
			  	</td>
			  </tr>  
			   <tr>
			  	<td>
			  		页数：
			  	</td>
			  	<td>
			  		<input type="text" class="txt" name="pages"/>
			  	</td>
			  </tr>  
			   <tr>
			  	<td>
			  		开本：
			  	</td>
			  	<td>
			  		<input type="text" class="txt" name="size"/>
			  	</td>
			  </tr>  
			   <tr>
			  	<td>
			  		印次：
			  	</td>
			  	<td>
			  		<input type="text" class="txt" name="printtimes"/>
			  	</td>
			  </tr>  
			   <tr>
			  	<td>
			  		版次：
			  	</td>
			  	<td>
			  		<input type="text" class="txt" name="versions"/>
			  	</td>
			  </tr> 
			   <tr>
			  		<td colspan="2" style="height:80px;" align="left">
			  			<p>内容简介：</p>
			  			<textarea id="brief" name="brief" style="width:100%;height:100px;"></textarea>
			  		</td>
			  </tr>  
			   <tr>
			   		
			  		<td colspan="2" style="height:80px;" align="left">
			  			<p>目录：</p>
			  			<textarea id="content" name="content" style="width:100%;height:100px;"></textarea>
			  		</td>
			  </tr>  
			   <tr>
			  		<td colspan="2" align="center">
			  			<button onclick="_save();" class="OperBtn">保存</button>
			  		</td>
			  </tr>  
     	</table>
     </form>
  </body>
  <script type="text/javascript">
  
  	//绑定html页面上的某个标签作为触发日期选择器的元素
  	var cal = Calendar.setup({
  		onSelect: function(cal) {cal.hide();}
	});
	//绑定页面的元素id值为btn的元素,放到name属性名为publishdate的输入框中
	cal.manageFields("btn", "publishdate", "%Y-%m-%d");
	
	
	//绑定一个输入框,把一个输入框使用了文本编辑器
	KE.show({
		id : 'brief',
		newlineTag : 'br'
	});
	KE.show({
		id : 'content',
		newlineTag : 'br'
	});
	
	function _save(){
		document.forms[0].submit();
	}
  </script>
</html>
     					