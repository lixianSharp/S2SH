package gz.itcast.entity;

import java.util.List;

/**
 * ��������
 * @author APPle
 *
 */
public class Orders {
	private String id;
	private String consignee;//ҵ������ ���궩�������ٸı��ջ���ַ�ˡ��ջ����������,�ô����û��޸��ջ���ַ������Ӱ�����������.
	private int paytype;//֧������
	private double amt;//�ܽ��
	private int state;//����״̬    .�����ͻ��Ѿ��ύ���ȴ�������1�̼��Ѿ��������������Ѿ���ɣ���������ȡ���������ͻ��Ѿ��˻���
	private String orderdate;//�¶�����ʱ��
	//ͨ�������õ��ö����µ����ж�����ϸ.�����ĺô��� ��ҳ���Ͽ��Եõ�һ�Ŷ����Ϳ��Եõ��ö����µ�������ϸ��Ϣ!!!
	private List<OrderLine> orderLines;
	//�����û�
	private Users user;//�����¶������û�
	
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
