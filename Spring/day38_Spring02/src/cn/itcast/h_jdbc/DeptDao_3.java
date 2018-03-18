package cn.itcast.h_jdbc;

import org.springframework.jdbc.core.JdbcTemplate;

//没有实现接口IDeptDao
public class DeptDao_3 {
	//接收容器注入的JdbcTemplate对象
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	//1、使用JdbcTemplate
	public void save(Dept dept) throws Exception{
		jdbcTemplate.update("insert into t_dept(deptName) values(?)", dept.getName());
				
	}
	
}
