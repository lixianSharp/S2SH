package gz.itcast.biz.front.user.dao;

import gz.itcast.entity.Users;
import gz.itcast.util.BaseDao;
import gz.itcast.util.JdbcUtil;
import gz.itcast.util.MD5Util;
import gz.itcast.util.WebUtil;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

public class UserDaoImpl extends BaseDao<Users> implements UserDao {

	public Users findByName(String name) {
		try {
			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			return (Users)qr.query("select * from users where name=?", 
						new BeanHandler(Users.class),
						new Object[]{name});
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	//保存用户
	public void save(Users user) {
		try {
			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			qr.update("insert into users(id,name,password,phone,email) values(?,?,?,?,?)",
					new Object[]{
						WebUtil.uuid(),
						user.getName(),
						MD5Util.md5(user.getPassword()),//md5加密之后再保存
						user.getPhone(),
						user.getEmail()
			});
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
