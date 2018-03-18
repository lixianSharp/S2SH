package gz.itcast.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

/**
 * web开发的一些工具类
 * @author APPle
 *
 */
public class WebUtil {

	/**
	 * 把请求数据拷贝到javabean中
	 * <T> T: 声明一个泛型返回值类型
	 */
	public static <T> T copyRequestToBean(HttpServletRequest request,Class<T> clazz){
		try {
			T obj = clazz.newInstance();
			//取出请求数据
			Map map = request.getParameterMap();
			//使用beanutils工具把map数据拷贝到javabean对象
			BeanUtils.copyProperties(obj, map);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	/**
	 * 返回UUID算法生成的字符串
	 * @return
	 */
	public static String uuid(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy/MM/dd");
	static SimpleDateFormat sdf4 = new SimpleDateFormat("yyyyMMddhhmmss");
	/**
	 * 返回当前日期的字符串
	 */
	public static String getCurrentDate(){
		return sdf1.format(new Date());
	}
	
	/**
	 * 用于生成订单号
	 */
	public static String getOrderNumber(Object obj){
		return obj.hashCode()+sdf4.format(new Date());
	}
}

