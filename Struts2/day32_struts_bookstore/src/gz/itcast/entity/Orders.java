package gz.itcast.entity;

import java.util.List;

/**
 * 订单对象
 * @author APPle
 *
 */
public class Orders {
	private String id;
	private String consignee;//业务需求： 下完订单不能再改变收货地址了。收货人相关数据,好处是用户修改收货地址，不会影响这里的数据.
	private int paytype;//支付类型
	private double amt;//总金额
	private int state;//订单状态    .０：客户已经提交，等待发货。1商家已经发货２：交易已经完成，３：交易取消。４：客户已经退货。
	private String orderdate;//下订单的时间
	//通过订单得到该订单下的所有订单明细.这样的好处是 在页面上可以得到一张订单就可以得到该订单下的所有明细信息!!!
	private List<OrderLine> orderLines;
	//关联用户
	private Users user;//代表下订单的用户
	
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public int getPaytype() {
		return paytype;
	}
	public void setPaytype(int paytype) {
		this.paytype = paytype;
	}
	public double getAmt() {
		return amt;
	}
	public void setAmt(double amt) {
		this.amt = amt;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
	public List<OrderLine> getOrderLines() {
		return orderLines;
	}
	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}
	
}
