package cn.itcast.service.impl;

import java.io.Serializable;

import cn.itcast.entity.Dept;


public interface IDeptService{

	public abstract void save(Dept dept);

	// ----------------------------------
	public abstract void update(Dept dept);

	public abstract void delete(Serializable id);


}