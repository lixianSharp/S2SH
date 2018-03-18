package cn.itcast.d_crud;

import java.util.Date;

//员工
public class Employee {
	private int id;//员工编号
	private String name;//员工姓名
	private String dept;//员工所在部门
	private Date birth;//员工年龄
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
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee( String name, String dept, Date birth) {
		super();
		this.name = name;
		this.dept = dept;
		this.birth = birth;
	}
	
	
	
	
}
