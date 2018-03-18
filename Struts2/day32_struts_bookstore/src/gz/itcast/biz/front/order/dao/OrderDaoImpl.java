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
	 * ע�⣺������¶����ķ������������Ҫ�ŵ�һ�������У���Ϊ����������Ҫͬʱ�ɹ�������ͬʱʧ�ܣ�
	 * 			�����Ѷ�����Ҫ��Ϣ����orders��
	 * 			2�������ж�����ϸ���浽orderline��
	 */
	public void saveOrder(Orders orders) {
		QueryRunner qr = new QueryRunner();
		
		//��ȡ���Ӷ���
		Connection conn = null;
		try {
			conn = JdbcUtil.getDataSource().getConnection();
			//��������
			conn.setAutoCommit(false);
			//1)�Ѷ�����Ҫ��Ϣ����orders��
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
			//2)�����ж�����ϸ���浽orderline��
			//2.1 �õ�������ϸ
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
			//���в����ɹ������ύ����
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			//�ع�
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				conn.close();//�������ӳ�
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
