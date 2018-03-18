package cn.itcast.a_one2many;

import java.util.HashSet;
import java.util.Set;

/**
 * 部门
 * @author 贤元
 * 		
 */
//目的：这个案例是一对多和多对一的双向配置
public class Dept {
	
	private int id;//部门id
	private String deptName;//部门名称
	
	/**
	 * 部门下所有的员工      
	 *   	  一对多
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
