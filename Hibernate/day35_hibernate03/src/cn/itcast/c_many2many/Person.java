package cn.itcast.c_many2many;

import java.util.HashSet;
import java.util.Set;

//开发人员
//目的：多对多的映射
public class Person {
	
	private int personId;
	private String name;
	
	//开发人员和项目的关系，多对多，也就是一个项目对应多个开发人员，一个开发人员对应多个项目
	//一个开发人员也可以参与多个项目
	private Set<Project> projects = new HashSet<Project>();
	
	
	public Set<Project> getProjects() {
		return projects;
	}
	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
