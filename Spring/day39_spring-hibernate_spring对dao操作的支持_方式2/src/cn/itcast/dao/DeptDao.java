package cn.itcast.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import cn.itcast.dao.impl.IDeptDao;
import cn.itcast.entity.Dept;
//方式2：通过注入HibernateTemplate对象
public class DeptDao implements IDeptDao {

	// IOC容器注入HibernateTemplate对象
	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
///*	private SessionFactory sessionFactory;
//
//	public void setSessionFactory(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}*/
	
	

	@Override
	public void save(Dept dept) {
		//hibernateTemplate = new HibernateTemplate(sessionFactory);
		hibernateTemplate.save(dept);
		
		
		
	}

	// ----------------------------------
	@Override
	public void update(Dept dept) {
		//hibernateTemplate = new HibernateTemplate(sessionFactory);
		hibernateTemplate.update(dept);
		

	}

	@Override
	public void delete(Serializable id) {
		//hibernateTemplate = new HibernateTemplate(sessionFactory);
		Dept dept = hibernateTemplate.get(Dept.class, id);
		if(dept != null){
			hibernateTemplate.delete(dept);
		}

	
	}

	@Override
	public List<Dept> getAll() {
		//hibernateTemplate = new HibernateTemplate(sessionFactory);
		List<Dept> list = hibernateTemplate.find("from Dept");
		
		
		return list;
	}
	
}
