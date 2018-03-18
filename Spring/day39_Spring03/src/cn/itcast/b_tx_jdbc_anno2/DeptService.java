package cn.itcast.b_tx_jdbc_anno2;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service//当前类加入IOC容器，默认类名的第一个字母小写deptService  该注解应用于service层
public class DeptService implements IDeptService{

	//注入dao对象
	@Resource
	private IDeptDao deptDao;//默认是其加入IOC容器的子类的对象
	
	@Resource
	private LogService logService;//注入对象
	
	//当前方法应用事务
	@Transactional(
			readOnly=false,  	//读写的事务，当修改数据时候用；如果查询就设置为true
			isolation=Isolation.DEFAULT,	//事务隔离级别
			timeout=-1,		//事务执行的超时时间，-1表示不超时
			//noRollbackFor=ArithmeticException.class,  //遇到指定的异常不回滚
			propagation=Propagation.REQUIRED   //事务的传播行为
	)
	@Override
	public void save() {
		//插入日志
		logService.insertLog();//当第25行被注释掉的时候这行也不能能操作成功，如果第25行没有被注释掉，则这行能执行成功
		
		int i = 1/0;//因为这行会抛出一个ArithmeticException异常
		
		//插入部门
		deptDao.save();//这行被回滚了 当第25行注没注释掉这行都不能能操作成功
		
	}

}
