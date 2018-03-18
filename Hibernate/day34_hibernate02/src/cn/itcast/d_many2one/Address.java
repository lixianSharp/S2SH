package cn.itcast.d_many2one;
//地址
/**
 * 地址与用户关系：
			多对一：
				多个地址对应一个用户。 关系在多的一方维护
 * @author 贤元
 *
 */
//用多对一的方式实现
public class Address {
	//alt+shift+a
	private int id;//
	private String name;//地址名字
	private String shortName;//简称
	private String code;//邮编
	
	//地址与用户，是多对一的关系
	private Users user;
	
	
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
