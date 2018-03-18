package cn.itcast.d_one2one2;

import java.util.Date;

// 用户的身份证对象
// 一对一映射, 无外键方
public class IdCard {

	private String cardNo;
	private String place;
	private Date date;
	// 身份证，对应的用户信息
	private User user;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
