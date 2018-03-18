
//验证用户姓名和密码是否输入正常
function valdate(){
	var nm = document.getElementsByName("name")[0].value;
	nm = nm.trim();
	if(nm==""){
		alert("请输入用户名");
		document.getElementsByName("name")[0].focus();
		return false;
	}
	var p1 = document.getElementsByName("password")[0].value;
	var p2 = document.getElementsByName("password2")[0].value;
	p1 = p1.trim();
	if(p1=="" || p2==""){
		alert("请输入密码!");
		document.getElementsByName("password")[0].focus();
		return false;
	}
	if(p1!=p2){
		alert("输入的两次密码不一样");
		document.getElementsByName("password2")[0].focus();
		return false;
	}
	var code = document.getElementsByName("sCode")[0].value;
	code = code.trim();
	if(code==""){
		alert("请输入验证码!");
		document.getElementsByName("sCode")[0].focus();
		return false;
	}
	return true;
	//document.forms['f1'].submit();//提交表单
			
}