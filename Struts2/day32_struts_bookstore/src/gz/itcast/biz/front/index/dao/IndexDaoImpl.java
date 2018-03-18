package gz.itcast.biz.front.index.dao;

import gz.itcast.entity.Books;
import gz.itcast.entity.Types;
import gz.itcast.util.JdbcUtil;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
/**
 * 主页的dao
 * @author APPle
 *
 */
public class IndexDaoImpl implements IndexDao {

	public List<Types> queryTypes() {
		try {
			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			return (List<Types>)qr.query("select * from types", new BeanListHandler(Types.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 *  显示全部： typeId为空
	 *  分类显示： typeId有值 
	 */
	//-- 子查询： 把一个sql语句的结果作为另一个sql语句的条件
	//select * from books where id in(SELECT bookid FROM booktype WHERE typeid='T003') ;
	public List<Books> queryBooks(String typeId) {
		try {
			StringBuffer sql = new StringBuffer("select id,name,price,img,round(price*rebate,2) as currentPrice from books ");
			//当有分类的时候执行拼接
			if(typeId!=null && !typeId.equals("")){
				sql.append(" where id in(SELECT bookid FROM booktype WHERE typeid='"+typeId+"')");
			}
			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			return (List<Books>)qr.query(sql.toString(), new BeanListHandler(Books.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public Books queryBook(String id) {
		try {
			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			return (Books)qr.query("select id,name,price,rebate,round(price*rebate,2) as currentPrice,auth,img from books where id=?", new BeanHandler(Books.class),new Object[]{id});
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	public static void main(String[] args) {
		IndexDaoImpl dao = new IndexDaoImpl();
		List<Books> list = dao.queryBooks(null);
		for (Books books : list) {
			System.out.println(books);
		}
	}

	
}
