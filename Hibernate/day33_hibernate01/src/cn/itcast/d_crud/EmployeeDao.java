package cn.itcast.d_crud;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmployeeDao implements IEmployeeDao{

	//保存
	@Override
	public void save(Employee emp) {
		Session session = null;
		
		try {
			//创建session
			session = HibernateUtils.getSession();
			//开启事务
			Transaction tx = session.beginTransaction();

			//保存
			session.save(emp);
			
			
			//提交事务
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
			//关闭session
			session.close();
		}
		
		
		
	}

	//删除，根据id删除
	@Override
	public void delete(Serializable id) {
		Session session = null;
		try {
			// 创建session
			session = HibernateUtils.getSession();
			// 开启事务
			Transaction tx = session.beginTransaction();
			// -- 先查询， 再删除 ---
			Object obj = session.get(Employee.class, id);//如果不存在该id，则返回null。所以删除前需要判断一下是否为null
			// 判断
			if (obj != null) {
				session.delete(obj);
			}
			
			// 提交事务
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭session
			session.close();
		}		
	}

	//更新
	@Override
	public void update(Employee emp) {
		Session session = null;
		try {
			//获取session对象
			session = HibernateUtils.getSession();
			//开启事务
			Transaction tx = session.beginTransaction();
			// -- 更新 -- 
			session.update(emp);
			//提交事务
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭session
			session.close();
		}
	}

	//根据主键id查询
	@Override
	public Employee findById(Serializable id) {
		Session session = null;
		Employee emp = null;
		try {
			session = HibernateUtils.getSession();
			Transaction tx = session.beginTransaction();
			//-- 主键查询 --
			emp = (Employee) session.get(Employee.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return emp;
	}

	/**
	 * 通过hql查询语句查询所有的数据
	 */
	@Override
	public List<Employee> getAll() {
		Session session = null;
		List<Employee> list = null;
		
		try {
			//初始化session
			session  = HibernateUtils.getSession();
			//开启事务
			Transaction tx = session.beginTransaction();
			//查询所有
			Query q = session.createQuery("from Employee");
			list = q.list();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭session
			session.close();
		}
		return list;
	}

	/**
	 * 分页查询
	 * index:当前页的页号
	 * count：每页显示的记录数
	 */
	@Override 
	public List<Employee> getAll(int index, int count) {
		Session session = null;
		List<Employee> list = null;
		
		try {
			//初始化session
			session = HibernateUtils.getSession();
			//开启事务
			Transaction tx = session.beginTransaction();
			//先查询所有，把你需要的所有的数据先查询出来
			Query q = session.createQuery("from Employee");
			//list = q.list();
			
			//-----获取总记录相关代码
			ScrollableResults r = q.scroll();//获取结果集//返回查询结果scrollableresults。返回的结果的scrollability取决于JDBC驱动程序支持可滚动的结果集。
			r.last();//滚动到结果集的最后一行
			int totalCount = r.getRowNumber()+1;//行编号，如果没有当前行，则从0或-1编号。
			System.out.println("总记录数："+totalCount);
			
			q.setFirstResult(index);//设置要检索的第一行，如果没有设置，检索将会从0开始
			//设置查询返回行数
			q.setMaxResults(count);//设置要检索的行的最大行数。如果未设置，则检索到的行数没有限制。 


			
			// 执行查询
			list = q.list();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭session
			session.close();
		}
		return list;
	}

}
