package cn.itcast.c_many2many;

import java.util.HashSet;
import java.util.Set;

//项目
public class Project {
	private int id;
	private String name;//项目名称
	
	//项目和开发人员的关系，多对多，也就是一个项目对应多个开发人员，一个开发人员对应多个项目
	//一个项目由多个开发人员来参与
	private Set<Person> person = new HashSet<Person>();
	
	
	public Set<Person> getPerson() {
		return person;
	}
	public void setPerson(Set<Person> person) {
		this.person = person;
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
	
	
}
