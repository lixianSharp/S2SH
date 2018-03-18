package cn.itcast.dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import cn.itcast.entity.Dept;
public class DeptDao {
	public void save(Dept dept){
		/**
		 * 步骤：
		 * 1、创建配置管理器
		 * 2、加载主配置文件
		 * 3、创建SessionFactory
		 * 4、创建session
		 * 5、开启事务
		 * 6、执行保存
		 * 7、提交事务
		 */
		Configuration config = new Configuration();
		config.configure();
		SessionFactory sf = config.buildSessionFactory();//【SpringIOC容器】通过线程方式创建session
		Session session = sf.getCurrentSession();//【Spring 声明式事务管理】
		session.beginTransaction();
		//执行保存
		session.save(dept);
		
		session.getTransaction().commit();;
	}
}