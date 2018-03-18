package cn.itcast.service;

import java.io.Serializable;
import java.util.List;

import cn.itcast.dao.impl.IDeptDao;
import cn.itcast.entity.Dept;
import cn.itcast.service.impl.IDeptService;

public class DeptService implements IDeptService {
	//容器注入
	private IDeptDao deptDao;
	public void setDeptDao(IDeptDao deptDao) {
		this.deptDao = deptDao;
	}
	
	@Override
	public void save(Dept dept){
		deptDao.save(dept);
	}

	
	
	@Override
	public void update(Dept dept) {
		// TODO Auto-generated method stub
		deptDao.update(dept);
	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		deptDao.delete(id);
	}

	@Override
	public List<Dept> getAll() {
		
		return deptDao.getAll();
	}
	
	
}
