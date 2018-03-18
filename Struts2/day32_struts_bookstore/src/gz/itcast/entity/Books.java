package gz.itcast.entity;

import java.util.ArrayList;
import java.util.List;

public class Books {
	private String id;
	private String name;
	private double price;//原价
	private double currentPrice;//现价（原价*折扣率）
	private String img;//图书的图片地址
	private double rebate;//折扣
	private String auth;//作者
	private int amt;//用于购物车中统计用户购买的图书数量
	
	//用户存储当前这边数的分类信息
	private List<String> typesId = new ArrayList<String>();

	public List<String> getTypesId() {
		return typesId;
	}
	public void setTypesId(List<String> typesId) {
		this.typesId = typesId;
	}
	public int getAmt() {
		return amt;
	}
	public void setAmt(int amt) {
		this.amt = amt;
	}
	public double getRebate() {
		return rebate;
	}
	public void setRebate(double rebate) {
		this.rebate = rebate;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "Books [amt=" + amt + ", auth=" + auth + ", currentPrice="
				+ currentPrice + ", id=" + id + ", img=" + img + ", name="
				+ name + ", price=" + price + ", rebate=" + rebate
				+ ", typesId=" + typesId + "]";
	}

	
}
