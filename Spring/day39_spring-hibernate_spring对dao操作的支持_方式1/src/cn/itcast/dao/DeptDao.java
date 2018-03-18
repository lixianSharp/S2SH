package cn.itcast.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import cn.itcast.dao.impl.IDeptDao;
import cn.itcast.entity.Dept;
//方式一：通过注入SessionFactory对象
public class DeptDao implements IDeptDao {

	// IOC容器注入SessionFactory对象
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(Dept dept) {
		/* sessionFactory.openSession().save(dept); */// 这种方式不会报错，但是执行不成功，数据库中没有数据

		sessionFactory.getCurrentSession().save(dept);// 用这种方式，通过当前线程获得session
	}

	// ----------------------------------
	@Override
	public void update(Dept dept) {
		sessionFactory.getCurrentSession().update(dept);
	}

	@Override
	public void delete(Serializable id) {
		sessionFactory.getCurrentSession()//
				.createQuery("delete from t_dept where id=?")//
				.setParameter(0,id)//
				.executeUpdate();

	}

}
