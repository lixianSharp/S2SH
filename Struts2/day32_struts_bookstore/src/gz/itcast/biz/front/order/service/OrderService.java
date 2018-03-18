package gz.itcast.biz.front.order.service;

import java.util.List;

import gz.itcast.entity.Orders;

public interface OrderService {
	public void saveOrder(Orders orders);
	public List<Orders> queryOrders(String userId);
}
