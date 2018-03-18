package cn.itcast.c_hql;

import java.util.HashSet;
import java.util.Set;

/**
 * 部门
 * @author 贤元
 *
 */
public class Dept {
	private int id;//主键
	private String deptName;//部门名称
	
	/**
	 * 部门下的所有员工：
	 * 	  部门和员工的关系，一对多
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

	public Dept() {
		super();
	}

	public Dept(int id, String deptName) {
		this.id = id;
		this.deptName = deptName;
	}
	
	
	
}
