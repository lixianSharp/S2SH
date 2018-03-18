package cn.itcast.service.impl;

import java.io.Serializable;
import java.util.List;

import cn.itcast.dao.IDeptDao;
import cn.itcast.entity.Dept;
import cn.itcast.service.IDeptService;

public class DeptService implements IDeptService{

	// 注入dao
	private IDeptDao deptDao;
	public void setDeptDao(IDeptDao deptDao) {
		this.deptDao = deptDao;
	}

	@Override
	public void delete(Serializable id) {
		deptDao.delete(id);
	}

	@Override
	public Dept findById(Serializable id) {
		return deptDao.findById(id);
	}

	@Override
	public List<Dept> getAll() {
		return deptDao.getAll();
	}

	@Override
	public List<Dept> getAll(int index, int count) {
		return deptDao.getAll(index, count);
	}

	@Override
	public Long getTotalCount() {
		return deptDao.getTotalCount();
	}

	@Override
	public void save(Dept dept) {
		deptDao.save(dept);		
	}

	@Override
	public void update(Dept dept) {
		deptDao.update(dept);		
	}


}
