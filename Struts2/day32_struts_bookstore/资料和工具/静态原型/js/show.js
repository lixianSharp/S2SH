function _go(id,name,price,img){
	var url = "buy/buy?action=addCat&id="+id+
			"&name="+name+"&currentPrice="+price+"&img="+img;
	url = encodeURI(url);
	window.location.href=url;
}
