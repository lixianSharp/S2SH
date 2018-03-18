package cn.itcast.c_composite;

import java.io.Serializable;

/**
 * 联合主键对象，必须要实现可序列化标记！
 * 
 * 将主键所对应属性提取出一个类（称之为主键类），并且主键类需要实现Serializable接口

 * @author 贤元
 *
 */
/**
 * 主键=非空+唯一
 * 什么是联合主键？？？？
 * 就是将几个字段一起做为主键
		给你个例子
		create table grade( 
		stuNum char(10) not null,
		courseNum  char(10)  not null,
		grade  int  not null,
		primary key  (stuNum,courseNum)
		)
 * @author 贤元
 *
 *///将姓名和地址作为联合主键，联合主键对象必须要实现可序列化标记
public class CompositeKeys implements Serializable{
	private String name;//姓名
	private String address;//地址
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
