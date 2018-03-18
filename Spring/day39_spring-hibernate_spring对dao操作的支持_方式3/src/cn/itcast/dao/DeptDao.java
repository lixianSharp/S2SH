package cn.itcast.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.dao.impl.IDeptDao;
import cn.itcast.entity.Dept;

//推荐使用方式3，也就是推荐Dao类直接继承HibernateDaoSupport。HibernateDaoSupport对hibernateTemlate类进行了封装
//方式3：直接继承HibernateDaoSupprot
//dao开发：可以直接继承HibernateDaoSupport，这样就可以直接拿到HibernateTemplate类
//注意：这个dao要想用hibernateTemplate，就一定要注入SessionFactory对象
//一定要看HibernateDaoSupport的源码啊！！
public class DeptDao extends HibernateDaoSupport implements IDeptDao {

	@Override
	public void save(Dept dept) {
		getHibernateTemplate().save(dept);
	}

	@Override
	public void update(Dept dept) {
		getHibernateTemplate().update(dept);

	}

	@Override
	public void delete(Serializable id) {
		Dept dept = getHibernateTemplate().get(Dept.class, id);
		if (dept != null) {
			getHibernateTemplate().delete(dept);
		}

	}

	@Override
	public List<Dept> getAll() {
		List<Dept> list = getHibernateTemplate().find("from Dept");

		return list;
	}
	
	/**
	 * 分页查询  效果是：从第3行开始查询5条数据，结果也就是第3行之后的5条数据(就是第四行~第8行的数据   【4,8】)
	 * @return
	 */
	public List<Dept> Fy(){
		Session session = this.getSession();
		Query query = session.createQuery("from Dept");
		query.setFirstResult(3);//要查询的第一行 (从第三行开始查询)
		query.setMaxResults(5);//要查询的结果集的大小(也就是你要查询多少条数据(你要查询的记录数))
		List<Dept> list = query.list();//将查询出来的结果封装成list集合  结果是从第4行开始的5条数据
		return list;
	}

}
