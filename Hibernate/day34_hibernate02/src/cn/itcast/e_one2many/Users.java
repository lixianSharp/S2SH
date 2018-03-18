package cn.itcast.e_one2many;

import java.util.HashSet;
import java.util.Set;

//用户  一个用户对应多个地址。  
//用一对多的方式实现
public class Users {
	private int id;
	private String name;
	private int age;
	
	//关系配置： 用户与地址，是一对多的关系【注意:一定要用接口接收!因为使用的是jdk代理】   
	private Set<Address> address = new HashSet<Address>();

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Set<Address> getAddress() {
		return address;
	}

	public void setAddress(Set<Address> address) {
		this.address = address;
	}
	
	
	
}
