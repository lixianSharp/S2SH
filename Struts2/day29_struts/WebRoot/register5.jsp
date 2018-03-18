<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>提交数据到UserAction f_data</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>

<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<form id="btnForm">
		用户名： <input type="text" name="name" /><br />
		<!-- 注意：这里的user要和UserAction2中的成员变量user的名字要一样 -->
		密码： <input type="password" name="password" /><br /> 性别： <input
			type="radio" name="gender" value="男" />男 <input type="radio"
			name="gender" value="女" />女<br /> 爱好： <input type="checkbox"
			name="hobit" value="篮球" />篮球 <input type="checkbox"
			name="hobit" value="足球" />足球 <input type="checkbox"
			name="hobit" value="羽毛球" />羽毛球<br /> 
			<!-- <input type="submit"
			value="注册" />  -->
		 <button onclick="saveButton()" type="button" >将数据提交到后台</button>
	</form>
	<script type="text/javascript">
		function saveButton(){
			$.ajax({
				url : '${pageContext.request.contextPath }/data/user3_register.action',
				data : $("#btnForm").serialize(),
				type : 'POST',
				dataType : 'json',
				async : true,
				success : function(data) {
					//弹出是否录入成功
					alert(data.result);
				}
			});
		}
	</script>


</body>
</html>
