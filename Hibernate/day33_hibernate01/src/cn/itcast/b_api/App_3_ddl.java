package cn.itcast.b_api;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class App_3_ddl {
	
	/**
	 * 通过代码方式建表
	 * @param args
	 */
	public static void main(String[] args) {
		//创建配置管理器对象，并加载主配置文件(会加载映射)
		Configuration cfg = new Configuration().configure();
		
		//自动建表工具类
		SchemaExport export = new SchemaExport(cfg);
		
		/**
		 * 创建表：
		 * 	  第一个参数：是否打印建表语句到控制台，不会影响执行结果
		 *   第二个参数：是否执行脚本，生成表
		 */
		export.create(true, true);
		
	}
	
}
