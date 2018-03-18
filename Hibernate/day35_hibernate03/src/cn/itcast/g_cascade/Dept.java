package cn.itcast.g_cascade;

import java.util.HashSet;
import java.util.Set;

//部门
public class Dept {
	
	private int id;
	private String deptName;
	
	/**
	 * 部门和员工的关系：一对多，一个部门下有多个员工
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
	}}
