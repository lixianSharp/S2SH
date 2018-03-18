package cn.itcast.b_tx_jdbc_anno2;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service //加入IOC容器,默认类名的第一个字母小写logService    这个注解用于Service层
public class LogService {
	@Resource //注入JdbcTemplate对象
	private JdbcTemplate jdbcTemplate;
	
	//事务控制
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void insertLog(){
		jdbcTemplate.update("insert into t_log values('在插入部门...')");
	}
}
