package gz.itcast.entity;
/**
 * ������ϸ
 * @author APPle
 *
 */
public class OrderLine {

	private Books book;//��ϸ��Ӧ���Ȿ��
	private int amt;//����ϸ������
	private double price;//�ۿۼ�
	
	//���Ҫͨ��������ϸȥ�õ�������Ϣ
	//private Orders orders;
	
	public Books getBook() {
		return book;
	}
	public void setBook(Books book) {
		this.book = book;
	}
	public int getAmt() {
		return amt;
	}
	public void setAmt(int amt) {
		this.amt = amt;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
