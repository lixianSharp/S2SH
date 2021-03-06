package cn.itcast.entity;
//员工
public class Employee {
	private int id;
	private String name;//姓名
	private double salary;//薪水
	
	/**
	 * 员工和部门的关系：
	 * 	多对一：也就是多个员工对应一个部门
	 */
	private Dept dept;

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

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}
	
	
}
