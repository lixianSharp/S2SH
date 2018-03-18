package gz.itcast.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
/**
 * ���������
 * @author APPle
 *
 */
public class EncodingFilter implements Filter{

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
			
			/**
			 * ˼·�� ʹ��װ����ģʽ��ȥװ��HttpServletRequest���� 
			 */
		//���post�����ύ������
		request.setCharacterEncoding("utf-8");
		MyRequest myRequest = new MyRequest((HttpServletRequest)request);
		//���У����е�����ʱװ�κ������Ķ���
		chain.doFilter(myRequest, response);
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}
//װ����
class MyRequest extends HttpServletRequestWrapper{
	private HttpServletRequest request;
	
	public MyRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	
	@Override
	public String getParameter(String name) {
		try {
			/**
			 * GET�ύ��������
			 */
			//ԭ����
			String value = this.request.getParameter(name);
			if(value!=null){
				if("GET".equals(this.request.getMethod())){
					value = new String(value.getBytes("iso-8859-1"),"utf-8");
				}
			}
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	//�����ǵķ����� getParameterValues("name") /  getParameterMap()
	
	@Override
	public Map getParameterMap() {
		try {
			/**
			 * GET��ʽ�ύ������
			 */
			Map<String,String[]> map = this.request.getParameterMap();
			if(map!=null){
				if("GET".equals(this.request.getMethod())){
					Map<String,String[]> newMap = new HashMap<String,String[]>();
					for(Entry<String,String[]> entry: map.entrySet()){
						String[] arr = entry.getValue();
						//��ʱ���������
						String[] newArray = new String[arr.length];
						//��ԭ����ÿ����������ַ��� ת�� �����ģ�Ȼ������µ���ʱ������
						for(int i=0;i<newArray.length;i++){
							newArray[i] = new String(arr[i].getBytes("iso-8859-1"),"utf-8");
						}
						newMap.put(entry.getKey(), newArray);
					}
					map = newMap;
				}
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}
