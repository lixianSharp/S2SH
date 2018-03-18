package cn.itcast.b_inverse2;

import java.util.HashSet;
import java.util.Set;

//部门
public class Dept {
	private int id;
	private String deptName;
	
	/**
	 * 部门下的所有员工
	 * 		一对多      也就是一个部门对应多个员工
	 */
	private Set<Employee> employees = new HashSet<Employee>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
}