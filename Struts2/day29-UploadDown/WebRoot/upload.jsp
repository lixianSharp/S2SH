<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>文件上传</title>
	<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
  </head>
  
  <body>
    <%--文件上传的三个条件：
    	1、提交方式必须为POST提交
    	2、enctype="multipart/form-data"
    	3、表单有file,也就文件的类型为file
     --%>
     
     <form id="trainForm"  enctype="multipart/form-data">
     	文件：<input type="file" name="attach"/><br/>
     	描述：<input type="text" name="info"/><br/>
     	<button type="button" onclick="btnSave()">上传</button>
     </form>
 	
 	    <script type="text/javascript">
        	function btnSave(){
        		alert("进入方法")
        		var formData = new FormData($("#trainForm")[0]);
        		 $.ajax({
        			type:"POST",
        			url:"${pageContext.request.contextPath }/upload/upload_execute.action",
        			data:formData,
        			//必填  
        	        processData: false,  
        	       //必填  
        	        contentType: false,
        			dataType:"json",
        			success:function(data){
        				//进入回掉函数
        				alert("进入回掉函数")
        				alert(data.result)
        				//alert(data.result);
        			},
        		}); 
        		
        	}
        </script>
 
 <!-- 测试 -->
 <button type="button" onclick="btn()">测试</button>
  	    <script type="text/javascript">
        	function btn(){
        		alert("进入方法")
        		 $.ajax({
        			type:"POST",
        			url:"${pageContext.request.contextPath }/upload/test_test.action",
        			dataType:"json",
        			success:function(data){
        				//进入回掉函数
        				alert("进入回掉函数")
        				alert(data.result)
        				//alert(data.result)
        				//alert(data.result);
        			},
        			error:function(){
        				alert("增加培训资料失败，请重试")
        			}
        		}); 
        		
        	}
        </script>
 
  </body>
</html>
