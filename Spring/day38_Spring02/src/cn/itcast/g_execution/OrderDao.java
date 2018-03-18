package cn.itcast.g_execution;

import org.springframework.stereotype.Repository;
@Repository   //将OrderDao加入IOC容器中
public class OrderDao {

	public void save() {
		System.out.println("保存订单...");
	}
}
