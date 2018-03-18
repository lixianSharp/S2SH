package cn.itcast.a_session_cache;

/**
 * 员工
 * @author AdminTH
 *
 */
public class Employee {

	private int id;
	private String name;
	private double salary;
	
	/*
	 * 多对一
	 */
	private Dept dept;
	
	
	
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
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
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
}
