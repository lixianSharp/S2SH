function _go(id,name,price,img){
	var url = "buy?action=addToCat&id="+id+
			"&name="+name+"&currentPrice="+price+"&img="+img;
	//��ͼ�����ƽ���URLEncoder����
	url = encodeURI(url);
	window.location.href=url;
}
