package cn.itcast.d_crud;

import java.io.Serializable;
import java.util.List;

/**
 *  数据访问层接口
 * @author AdminTH
 *
 */
public interface IEmployeeDao {

	/**
	 * 保存
	 * @param emp
	 */
	void save(Employee emp);
	
	/**
	 * 删除
	 * @param id  根据主键删除
	 */
	void delete(Serializable id);
	
	/**
	 * 修改
	 * @param emp
	 */
	void update(Employee emp);
	
	/**
	 * 根据主键查找
	 * @param id
	 * @return
	 */
	Employee findById(Serializable id);
	
	/**
	 * 查询所有
	 * @return
	 */
	List<Employee> getAll();
	
	/**
	 * 分页查询
	 * 例如：select * from 表  limit 0,3
	 */
	List<Employee> getAll(int index, int count);
	
}









