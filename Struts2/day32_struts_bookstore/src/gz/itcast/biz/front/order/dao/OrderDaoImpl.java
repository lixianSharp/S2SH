package gz.itcast.biz.front.order.dao;

import gz.itcast.entity.OrderLine;
import gz.itcast.entity.Orders;
import gz.itcast.util.JdbcUtil;
import gz.itcast.util.WebUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class OrderDaoImpl implements OrderDao {

	/**
	 * 注意：　这个下订单的方法（这个方法要放到一个事务当中，因为这两个方法要同时成功，或者同时失败）
	 * 			１）把订单概要信息保存orders表
	 * 			2）把所有订单明细保存到orderline表
	 */
	public void saveOrder(Orders orders) {
		QueryRunner qr = new QueryRunner();
		
		//获取连接对象
		Connection conn = null;
		try {
			conn = JdbcUtil.getDataSource().getConnection();
			//开启事务
			conn.setAutoCommit(false);
			//1)把订单概要信息保存orders表
			qr.update(conn,"insert into orders(id,userid,consignee,paytype,amt,state,orderdate) values(?,?,?,?,?,?,?)"
					,new Object[]{
							orders.getId(),
							orders.getUser().getId(),
							orders.getConsignee(),
							orders.getPaytype(),
							orders.getAmt(),
							0,
							WebUtil.getCurrentDate()
					}
			);
			//2)把所有订单明细保存到orderline表
			//2.1 得到所有明细
			List<OrderLine> orderLines = orders.getOrderLines();
			for(OrderLine ol: orderLines){
				qr.update(conn,"insert into orderline(id,bookid,orderid,amt,price) values(?,?,?,?,?)",
						new Object[]{
						      WebUtil.uuid(),
						      ol.getBook().getId(),
						      orders.getId(),
						      ol.getAmt(),
						      ol.getPrice()
				});
			}
			//所有操作成功，则提交事务
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			//回滚
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				conn.close();//返回连接池
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Orders> queryOrders(String userId) {
		try {
			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			return (List<Orders>)qr.query("select * from orders where userid=?", new BeanListHandler(Orders.class),new Object[]{userId});
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
