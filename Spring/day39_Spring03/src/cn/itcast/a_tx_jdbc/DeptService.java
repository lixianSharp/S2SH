package cn.itcast.a_tx_jdbc;

public class DeptService implements IDeptService{
	//注入dao
	private IDeptDao deptDao;
	public void setDeptDao(IDeptDao deptDao) {
		this.deptDao = deptDao;
	}
	
	
	@Override
	public void save() {
		deptDao.save();//如果出错的话，事务会回滚，回到最初的状态
		int i = 1/0;//这句代码就能体现出事务的作用啦。因为已出现异常，事务就会发生回滚的哦！
	}
	
	
	
}
