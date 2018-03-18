package cn.itcast.c_composite;

import java.util.Date;
/**
 * 
 * @author 贤元
 *
 */
//员工
public class Employee {
	//名称、地址、部门、年龄
	
	//联合主键对象(名称、地址)
	private CompositeKeys keys;
	public CompositeKeys getKeys() {
		return keys;
	}
	public void setKeys(CompositeKeys keys) {
		this.keys = keys;
	}
	private String dept;//部门
	private Date birth;//年龄
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
}
