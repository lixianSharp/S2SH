package cn.itcast.b_tx_jdbc_anno2;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository //当前类加入IOC容器，deptDao   这个注解应用于持久层
public class DeptDao implements IDeptDao{
	
	//注入JdbcTemplete对象
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	public void save(){
		jdbcTemplate.update("insert into t_dept(deptName) values('test4..')");
		
	}
}
