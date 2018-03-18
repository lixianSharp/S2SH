package gz.itcast.biz.front.order.web;



import gz.itcast.biz.front.order.service.OrderService;
import gz.itcast.biz.front.order.service.OrderServiceImpl;
import gz.itcast.entity.Books;
import gz.itcast.entity.OrderLine;
import gz.itcast.entity.Orders;
import gz.itcast.entity.Users;
import gz.itcast.util.BaseServlet;
import gz.itcast.util.WebUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 订单模块的servlet
 * @author APPle
 *
 */
public class OrderServlet extends BaseServlet {
	OrderService servie = new OrderServiceImpl();
	/**
	 * 预览订单以及订单明细
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 *  /order?action=preViewOrder
	 */
	public void preViewOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1)接收上一个页面传递的参数    收货人明细
		String consignee = request.getParameter("consignee");
		
		//2)创建一个Orders订单对象，把数据封装进去
		Orders orders = new Orders();
		orders.setConsignee(consignee.trim());
		orders.setId(WebUtil.getOrderNumber(orders));
		
		//3)转发到订单预览页面jsp
		request.setAttribute("order", orders);
		request.getRequestDispatcher("/jsp/front/order/order.jsp").forward(request, response);
		
	}
	/**
	 * 下订单的方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void saveOrder(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		//1)接收用户传递的订单数据
		Orders order = WebUtil.copyRequestToBean(request, Orders.class);
		
		//把登录用户id传入order对象
		Users loginUser = (Users)request.getSession().getAttribute("user");
		order.setUser(loginUser);
		
		//2)封装成Orders对象（同时要封装OrderLine对象）
		//2.1封装OrderLine对象(数据从session的购物车对象中来)
		Map<String,Books> car = (Map<String,Books>)request.getSession().getAttribute("car");
		List<OrderLine> olList = new ArrayList<OrderLine>();
		if(car!=null){
			for(Entry<String,Books> entry:car.entrySet()){
				OrderLine ol = new OrderLine();
				Books b = entry.getValue();
				ol.setBook(b);
				ol.setAmt(b.getAmt());
				ol.setPrice(b.getCurrentPrice());
				olList.add(ol);
			}
		}
		//2.2 把封装好的OrderLine的集合放入Orders对象
		order.setOrderLines(olList);
		
		//3)调用保存订单业务方法
		servie.saveOrder(order);
		
		request.setAttribute("orderId", order.getId());
		//4)跳转订单成功页面
		request.getRequestDispatcher("/jsp/front/order/ordersucc.jsp").forward(request, response);
	}
	/**
	 * 查询订单列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void queryOrders(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
			//获取登录用户
		 Users loginUser = (Users)request.getSession().getAttribute("user");
		 
		 List<Orders> orders = servie.queryOrders(loginUser.getId());
		 
		 //转发到jsp页面显示
		 request.setAttribute("orders", orders);
		 request.getRequestDispatcher("/jsp/front/order/list.jsp").forward(request, response);
	}
}
