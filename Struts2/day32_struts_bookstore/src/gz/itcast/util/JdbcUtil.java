package gz.itcast.util;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * ���ӳذ汾��jdbcUtil
 * @author APPle
 *
 */
public class JdbcUtil {

	//�������ӳض���
	private static DataSource ds = new ComboPooledDataSource(); 
	
	/**
	 * ��ȡ���ӳض���
	 */
	public static DataSource getDataSource(){
		 //ע�⣺ ��Ҫ��ôд�������ᵼ�µ����Ӻܿ�������
		//DataSource ds = new ComboPooledDataSource(); 
		return ds;
	}
	
	
	
}
