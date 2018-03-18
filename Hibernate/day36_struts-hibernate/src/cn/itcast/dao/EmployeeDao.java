package cn.itcast.dao;

import java.io.Serializable;

import cn.itcast.entity.Employee;
import cn.itcast.utils.HibernateUtils;

public class EmployeeDao {
	public Employee findById(Serializable id){
		//获取session，根据主键查询
		return (Employee) HibernateUtils.getSession().get(Employee.class,id);
	}
}
