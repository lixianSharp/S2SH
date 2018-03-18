package cn.itcast.dao;

import org.hibernate.SessionFactory;

import cn.itcast.entity.Dept;

public class DeptDao {
	//IOC容器注入SessionFactory对象
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void save(Dept dept){
		/*sessionFactory.openSession().save(dept);*///这种方式不会报错，但是执行不成功，数据库中没有数据
		
		sessionFactory.getCurrentSession().save(dept);//用这种方式
	}
	
}
