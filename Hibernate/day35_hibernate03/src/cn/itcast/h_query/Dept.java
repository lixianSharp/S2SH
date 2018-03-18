package cn.itcast.h_query;

import java.util.HashSet;
import java.util.Set;

/**
 * 部门
 * @author AdminTH
 *
 */
public class Dept {

	private int id;
	private String deptName;
	/*
	 * 部门下的所有员工
	 *   一对多 
	 */
	private Set<Employee> employees = new HashSet<Employee>();
	
	
	public Dept() {
		super();
	}
	public Dept(int id, String deptName) {
		this.id = id;
		this.deptName = deptName;
	}



	public Set<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
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
	
	
}
 