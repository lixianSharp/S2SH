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
 * ����ģ���servlet
 * @author APPle
 *
 */
public class OrderServlet extends BaseServlet {
	OrderService servie = new OrderServiceImpl();
	/**
	 * Ԥ�������Լ�������ϸ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 *  /order?action=preViewOrder
	 */
	public void preViewOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1)������һ��ҳ�洫�ݵĲ���    �ջ�����ϸ
		String consignee = request.getParameter("consignee");
		
		//2)����һ��Orders�������󣬰����ݷ�װ��ȥ
		Orders orders = new Orders();
		orders.setConsignee(consignee.trim());
		orders.setId(WebUtil.getOrderNumber(orders));
		
		//3)ת��������Ԥ��ҳ��jsp
		request.setAttribute("order", orders);
		request.getRequestDispatcher("/jsp/front/order/order.jsp").forward(request, response);
		
	}
	/**
	 * �¶����ķ���
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void saveOrder(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		//1)�����û����ݵĶ�������
		Orders order = WebUtil.copyRequestToBean(request, Orders.class);
		
		//�ѵ�¼�û�id����order����
		Users loginUser = (Users)request.getSession().getAttribute("user");
		order.setUser(loginUser);
		
		//2)��װ��Orders����ͬʱҪ��װOrderLine����
		//2.1��װOrderLine����(���ݴ�session�Ĺ��ﳵ��������)
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
		//2.2 �ѷ�װ�õ�OrderLine�ļ��Ϸ���Orders����
		order.setOrderLines(olList);
		
		//3)���ñ��涩��ҵ�񷽷�
		servie.saveOrder(order);
		
		request.setAttribute("orderId", order.getId());
		//4)��ת�����ɹ�ҳ��
		request.getRequestDispatcher("/jsp/front/order/ordersucc.jsp").forward(request, response);
	}
	/**
	 * ��ѯ�����б�
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void queryOrders(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
			//��ȡ��¼�û�
		 Users loginUser = (Users)request.getSession().getAttribute("user");
		 
		 List<Orders> orders = servie.queryOrders(loginUser.getId());
		 
		 //ת����jspҳ����ʾ
		 request.setAttribute("orders", orders);
		 request.getRequestDispatcher("/jsp/front/order/list.jsp").forward(request, response);
	}
}
