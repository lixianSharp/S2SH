package gz.itcast.entity;

public class Address {
	private String id;
	private String name;//地址
	private String phone;//电话
	private String zip;//邮编
	private int dft;//是否为默认地址: 0：非默认  1：默认
	private String mktime;//创建时间
	private Users user;//收货人对象
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public int getDft() {
		return dft;
	}
	public void setDft(int dft) {
		this.dft = dft;
	}
	public String getMktime() {
		return mktime;
	}
	public void setMktime(String mktime) {
		this.mktime = mktime;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
}
