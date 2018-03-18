package cn.itcast.dao;

import java.io.Serializable;
import java.util.List;

import cn.itcast.entity.Dept;

/**
 * 数据访问层接口：定义功能
 * @author 贤元
 *
 */
public interface IDeptDao {

	/**
	 *  保存
	 * @param dept
	 */
	void save(Dept dept);
	/**
	 *  删除
	 * @param id
	 */
	void delete(Serializable id);
	/**
	 *  修改
	 * @param dept
	 */
	void update(Dept dept);
	/**
	 * 主键查询
	 * @param id
	 * @return
	 */
	Dept findById(Serializable id);
	/**
	 *  查询全部
	 * @return
	 */
	List<Dept> getAll();
	/**
	 * 分页查询
	 * @param index  查询的起始行
	 * @param count  查询返回的行
	 * @return
	 */
	List<Dept> getAll(int index, int count);
	
	/**
	 * 返回总记录数
	 * @return
	 */
	Long getTotalCount();
}
