package cn.itcast.b_second_cache;

import java.util.HashSet;
import java.util.Set;
//这是一对多和多对一关系的双向配置
//部门
public class Dept {
	private int id;
	private String deptName;
	
	/**
	 * 部门下的所有员工
	 *   	关系：一对多
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
