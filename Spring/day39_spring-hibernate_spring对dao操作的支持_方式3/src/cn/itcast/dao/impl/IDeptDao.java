package cn.itcast.dao.impl;

import java.io.Serializable;
import java.util.List;

import cn.itcast.entity.Dept;

public interface IDeptDao {

	public abstract void save(Dept dept);

	// ----------------------------------
	public abstract void update(Dept dept);

	public abstract void delete(Serializable id);

	List<Dept> getAll();
	/**
	 * 分页
	 * @return 返回封装好的结果集
	 */
	public List<Dept> Fy();

}