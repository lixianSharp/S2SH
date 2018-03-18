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
 * 编码过滤器
 * @author APPle
 *
 */
public class EncodingFilter implements Filter{

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
			
			/**
			 * 思路： 使用装饰者模式，去装饰HttpServletRequest对象 
			 */
		//解决post参数提交的问题
		request.setCharacterEncoding("utf-8");
		MyRequest myRequest = new MyRequest((HttpServletRequest)request);
		//放行，放行的请求时装饰后的请求的对象
		chain.doFilter(myRequest, response);
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}
//装饰类
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
			 * GET提交参数问题
			 */
			//原来的
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
	//待覆盖的方法： getParameterValues("name") /  getParameterMap()
	
	@Override
	public Map getParameterMap() {
		try {
			/**
			 * GET方式提交的问题
			 */
			Map<String,String[]> map = this.request.getParameterMap();
			if(map!=null){
				if("GET".equals(this.request.getMethod())){
					Map<String,String[]> newMap = new HashMap<String,String[]>();
					for(Entry<String,String[]> entry: map.entrySet()){
						String[] arr = entry.getValue();
						//临时的数组对象
						String[] newArray = new String[arr.length];
						//把原来的每个有问题的字符串 转换 成中文，然后放入新的临时数组中
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
