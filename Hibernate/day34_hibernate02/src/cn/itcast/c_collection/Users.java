package cn.itcast.c_collection;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
//需求：购物填写地址，多个地址，对应一个用户
//目的：集合映射
//封装用户信息
public class Users {
 
	private int id; //用户id
	private String name;//用户姓名
	/**
	 * 用户对应多个地址
	 */
	//set集合
	private Set<String> addressSet = new LinkedHashSet<String>();
	//List集合
	private List<String> addressList = new ArrayList<String>();
	//Map集合
	private Map<String,String> addressMap = new LinkedHashMap<String,String>();
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
	public Set<String> getAddressSet() {
		return addressSet;
	}
	public void setAddressSet(Set<String> addressSet) {
		this.addressSet = addressSet;
	}
	public List<String> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<String> addressList) {
		this.addressList = addressList;
	}
	public Map<String, String> getAddressMap() {
		return addressMap;
	}
	public void setAddressMap(Map<String, String> addressMap) {
		this.addressMap = addressMap;
	}
}
