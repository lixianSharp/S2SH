package gz.itcast.biz.front.buy.web;



import gz.itcast.entity.Books;
import gz.itcast.util.BaseServlet;
import gz.itcast.util.WebUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * ���ﳵģ���servlet
 * @author APPle
 *
 */
public class BuyServlet extends BaseServlet {

	//��һ����ӵ����ﳵ��
	public void addToCat(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		//1)�����û������ͼ�����Ϣ
		Books book = WebUtil.copyRequestToBean(request, Books.class);
		
		//2)����һ��Map���������湺�ﳵ������ͼ����Ϣ
		//�ȴ�session�ж�ȡ���ﳵ������
		Map<String,Books> car = (Map<String,Books>)session.getAttribute("car");
		if(car==null){
			//��һ�η���
			car = new HashMap<String,Books>();
		}
		
		/**
		 *  �������⣺
		 *  	1������ǵ�ǰ���ﳵ��û���Ȿ�飬��Ϊ1
		 *  	2�������ǰ���ﳵ�������Ȿ�飬��Ϊ ����ǰ����+1��
		 */
		if(car.containsKey(book.getId())){
			//��ȡ�������е�����+1
			book.setAmt(car.get(book.getId()).getAmt()+1);
		}else{
			book.setAmt(1);
		}
		
		
		//3)��ͼ����빺�ﳵ������
		car.put(book.getId(), book);
		
		//4)�ѹ��ﳵ�������session��
		session.setAttribute("car", car);
		
		//5)��ת�����ﳵjspҳ��
		response.sendRedirect(request.getContextPath()+"/jsp/front/buy/car.jsp");
	}
}
