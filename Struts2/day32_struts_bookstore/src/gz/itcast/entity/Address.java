package gz.itcast.entity;

public class Address {
	private String id;
	private String name;//��ַ
	private String phone;//�绰
	private String zip;//�ʱ�
	private int dft;//�Ƿ�ΪĬ�ϵ�ַ: 0����Ĭ��  1��Ĭ��
	private String mktime;//����ʱ��
	private Users user;//�ջ��˶���
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
