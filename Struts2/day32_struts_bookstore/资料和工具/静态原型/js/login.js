//验证用户名密码和验证码
function validate(){
	var nm = document.getElementsByName("name")[0];
	if(nm.value.trim()==""){
		alert("姓名必须输入");
		nm.focus();
		return false;
	}
	var pwd = document.getElementsByName("password")[0];
	if(pwd.value.trim()==""){
		alert("密码必须输入");
		pwd.focus();
		return false;
	}
	var code = document.getElementsByName("sCode")[0];
	if(code.value.trim()==""){
		alert("验证码必须输入");
		code.focus();
		return false;
	}
	return true;
}
