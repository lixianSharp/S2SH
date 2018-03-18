package cn.itcast.h_jdbc;

import java.io.Serializable;
import java.util.List;

//接口
public interface IDeptDao {
	public abstract void save(Dept dept);
	public abstract void update(Dept dept); 
	public abstract void delete(Serializable id);
	public abstract Dept findById(Serializable id);
	public abstract List<Dept> getAll();
	
}
