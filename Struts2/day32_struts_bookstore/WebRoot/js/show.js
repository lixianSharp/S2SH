function _go(id,name,price,img){
	var url = "buy?action=addToCat&id="+id+
			"&name="+name+"&currentPrice="+price+"&img="+img;
	//对图书名称进行URLEncoder加密
	url = encodeURI(url);
	window.location.href=url;
}
