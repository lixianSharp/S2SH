package cn.itcast.service;

import cn.itcast.dao.DeptDao;
import cn.itcast.entity.Dept;

public class DeptService {
	//容器注入
	private DeptDao deptDao;
	public void setDeptDao(DeptDao deptDao) {
		this.deptDao = deptDao;
	}
	
	public void save(Dept dept){
		deptDao.save(dept);
	}
	
	
}
