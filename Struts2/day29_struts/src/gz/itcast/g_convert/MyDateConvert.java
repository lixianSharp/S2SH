package gz.itcast.g_convert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

/**
 * 自定义日期类型转换器
 * 
 * 继承StrutsTeypConverter struts的类型转换器
 * 
 * @author 贤元
 *
 */
// 转换器的执行是自动执行的
public class MyDateConvert extends StrutsTypeConverter {

	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");

	/**
	 * 从页面的数据到服务器的转换 参数一：context：值栈上下文对象 参数二：values：从页面传递过来的参数值
	 * 参数三：toClass:转换的类型。String-》java.util.Date
	 */
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		System.out.println("进入了转化器");
		// 1、判断values是否为空
		try {
			if (values == null || values.length == 0) {
				return null;
			}

			if (values.length > 1) {
				// 多个值的情况
				Date[] dates = new Date[values.length];// 用来存放已经转换好的日期的值
														// String->Date
				// 2、取出数据进行转换
				for (int i = 0; i < dates.length; i++) {
					// 多个值的情况
					Date date = sdf1.parse(values[i]);// 将String->Date
					dates[i] = date;
				}
				return dates;
			} else {
				// 一个值的情况
				Date date = sdf1.parse(values[0]);
				return date;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 从服务器到页面的类型转换
	 */
	@Override
	public String convertToString(Map arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
