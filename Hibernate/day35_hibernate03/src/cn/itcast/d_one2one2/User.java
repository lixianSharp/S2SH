package cn.itcast.d_one2one2;

// 用户对象
// 一对一映射， 有外键方
public class User {

	private String id;
	private String name;
	private char sex;
	// 用户关联的身份证信息, 在数据库中是外键
	private IdCard idCard;
	
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
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public IdCard getIdCard() {
		return idCard;
	}
	public void setIdCard(IdCard idCard) {
		this.idCard = idCard;
	}
}
