package cn.itcast.service;

import java.io.Serializable;

import cn.itcast.dao.EmployeeDao;
import cn.itcast.entity.Employee;

public class EmployeeService {
	//创建对象
	private EmployeeDao employeeDao = new EmployeeDao();
	
	public Employee findById(Serializable id){
		return employeeDao.findById(id);
	}
}
