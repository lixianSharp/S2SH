package gz.itcast.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

/**
 * web������һЩ������
 * @author APPle
 *
 */
public class WebUtil {

	/**
	 * ���������ݿ�����javabean��
	 * <T> T: ����һ�����ͷ���ֵ����
	 */
	public static <T> T copyRequestToBean(HttpServletRequest request,Class<T> clazz){
		try {
			T obj = clazz.newInstance();
			//ȡ����������
			Map map = request.getParameterMap();
			//ʹ��beanutils���߰�map���ݿ�����javabean����
			BeanUtils.copyProperties(obj, map);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	/**
	 * ����UUID�㷨���ɵ��ַ���
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
	 * ���ص�ǰ���ڵ��ַ���
	 */
	public static String getCurrentDate(){
		return sdf1.format(new Date());
	}
	
	/**
	 * �������ɶ�����
	 */
	public static String getOrderNumber(Object obj){
		return obj.hashCode()+sdf4.format(new Date());
	}
}

