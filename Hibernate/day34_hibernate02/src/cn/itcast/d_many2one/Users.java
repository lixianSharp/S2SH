package cn.itcast.d_many2one;
//用户
/**
 * 用户与地址的关系：一对多  【一个用户对应多个地址】
 */
public class Users {
	private int id;
	private String name;//姓名
	private int age;//年龄
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
