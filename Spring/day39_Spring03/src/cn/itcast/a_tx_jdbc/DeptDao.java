package cn.itcast.a_tx_jdbc;

import org.springframework.jdbc.core.JdbcTemplate;

public class DeptDao implements IDeptDao{
	//注入JdbcTemplate对象
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	@Override
	public void save() {
		jdbcTemplate.update("insert into t_dept(deptName) values('test..1')");
	}
	
}
