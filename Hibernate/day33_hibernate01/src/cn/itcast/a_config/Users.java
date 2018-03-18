package cn.itcast.a_config;
//javabean对象
public class Users {
	private int userId;//代表主键
	private String userName;//用户名
	private int age;//年龄
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Users(int userId, String userName, int age) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.age = age;
	}
	@Override
	public String toString() {
		return "Users [userId=" + userId + ", userName=" + userName + ", age="
				+ age + "]";
	}
	
	
	
}
