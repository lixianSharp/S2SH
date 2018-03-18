package gz.itcast.biz.front.address.dao;

import gz.itcast.biz.front.user.dao.UserDao;
import gz.itcast.biz.front.user.dao.UserDaoImpl;
import gz.itcast.entity.Address;
import gz.itcast.util.JdbcUtil;
import gz.itcast.util.WebUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

public class AddressDaoImpl implements AddressDao {
	
	UserDao userDao = new UserDaoImpl();

	public void save(Address address) {
		try {
			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			qr.update("insert into address(id,name,phone,zip,dft,userid,mktime) values(?,?,?,?,?,?,?)",
					new Object[]{
					WebUtil.uuid(),
					address.getName(),
					address.getPhone(),
					address.getZip(),
					0,//�������   Ϊ0
					address.getUser().getId(),
					WebUtil.getCurrentDate()
				});
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<Address> queryAddress(String userId) {
		try {
			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			return (List<Address>)qr.query("select * from address where userid=?",new MyAddressListHandler(), new Object[]{userId});
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	//���з�װ
	class MyAddressListHandler implements ResultSetHandler{

		public Object handle(ResultSet rs) throws SQLException {
			List<Address> list = new ArrayList<Address>();
			while(rs.next()){
				Address addr = new Address();
				addr.setId(rs.getString("id"));
				addr.setName(rs.getString("name"));
				addr.setPhone(rs.getString("phone"));
				addr.setZip(rs.getString("zip"));
				addr.setMktime(rs.getString("mktime"));
				String userid =rs.getString("userid");
				//����id��ѯ��Ӧ���û���Ȼ���װ��Address��ȥ
				addr.setUser(userDao.findById(userid));
				list.add(addr);
			}
			return list;
		}
		
	}

}
