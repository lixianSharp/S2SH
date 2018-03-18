package gz.itcast.biz.admin.book.dao;

import gz.itcast.entity.Books;
import gz.itcast.util.JdbcUtil;
import gz.itcast.util.WebUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

public class BookDaoImpl implements BookDao {

	/**
	 * 保存图书：
	 * 		1)保存图书信息到books表
	 *      2）保存图书和分类的关系信息(一本书可以有多个分类)
	 */
	public void saveBook(Books book) {
		Connection conn = null;
		try {
			conn = JdbcUtil.getDataSource().getConnection();
			QueryRunner qr = new QueryRunner();
			//开启事务
			conn.setAutoCommit(false);
			String bookId = WebUtil.uuid();
			//1)保存图书信息到books表
			qr.update(conn,"insert into books(id,name,price,auth,img,rebate) values(?,?,?,?,?,?)",
					new Object[]{
					bookId,
					book.getName(),
					book.getPrice(),
					book.getAuth(),
					book.getImg(),
					book.getRebate()
			});
			
			//2)保存图书和分类的关系信息(一本书可以有多个分类)
			List<String> typesId = book.getTypesId();
			for (String id : typesId) {
				qr.update(conn,"insert into booktype(bookid,typeid) values(?,?)",
						new Object[]{
						bookId,
						id
				});
			}
			//提交事务
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			//回顾
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
