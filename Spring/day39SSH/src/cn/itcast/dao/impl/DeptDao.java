package cn.itcast.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.dao.IDeptDao;
import cn.itcast.entity.Dept;
//dao实现  通过spring对dao的第三种支持方式实现：继承HibernateDaoSupport类
public class DeptDao extends HibernateDaoSupport implements IDeptDao{
	
	
	
	//查询返回总记录数
	@Override
	public Long getTotalCount() {
		return getHibernateTemplate().execute(new HibernateCallback<Long>() {//Long表示你要返回的结果
			@Override
			public Long doInHibernate(Session session)
					throws HibernateException, SQLException {
				Long num = 
					(Long) session.createQuery("select count(*) from Dept").uniqueResult();
				return num;
			}
		});
	}
	
	@Override
	public void delete(Serializable id) {
		getHibernateTemplate().bulkUpdate("delete from Dept where id=" + id);
	}
	
	/**这种是属于hibernate自带的分页机制
	 * 分页查询
	 * @param index  查询的起始行
	 * @param count  查询返回的行
	 * @return
	 */
	//分页查询
	@Override
	public List<Dept> getAll(final int index, final int count) {
		List<Dept> list = getHibernateTemplate().execute(new HibernateCallback<List<Dept>>() {//List<Dept>表示你要返回的结果

			@Override
			public List<Dept> doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query q = session.createQuery("from Dept");
				q.setFirstResult(index);//设置要查询的第一行数据
				q.setMaxResults(count);//设置要查询的结果集的大小
				return q.list();
			}
		});
		return list;
	}
	

	@Override
	public Dept findById(Serializable id) {
		return getHibernateTemplate().get(Dept.class, id);
	}

	@Override
	public List<Dept> getAll() {
		return getHibernateTemplate().loadAll(Dept.class); // 也可以用hql查询
	}

	@Override
	public void save(Dept dept) {
		getHibernateTemplate().save(dept);
	}

	@Override
	public void update(Dept dept) {
		getHibernateTemplate().update(dept);
	}
}