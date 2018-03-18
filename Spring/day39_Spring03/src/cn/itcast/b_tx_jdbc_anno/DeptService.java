package cn.itcast.b_tx_jdbc_anno;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service  // 加入IOC容器,默认是类名的第一个字母小写deptService, 该注解用于Service层
public class DeptService implements IDeptService {

	@Resource
	private IDeptDao deptDao;

	public void setDeptDao(IDeptDao deptDao) {
		this.deptDao = deptDao;
	}

	// 当前方法应用事务
	@Transactional(
			readOnly=false,//读写的事务，当修改数据时候用，如果查询就设置为true
			isolation=Isolation.DEFAULT,//事务隔离级别
			timeout = -1,//事务执行的超时时间，-1表示不超时
			noRollbackFor = ArithmeticException.class,//遇到指定的异常不回滚
			propagation=Propagation.REQUIRED //事务的传播行为
	)
	public void save() {
		deptDao.save();
		int i = 1 / 0;
		deptDao.save();
	}
}