package cn.itcast.h_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

public class DeptDao_2 {
	
	//接收容器注入的DataSource对象
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	//原始jdbc代码
	public void save(Dept dept) throws Exception{
		Connection con = dataSource.getConnection();
		//执行预编译的sql语句，返回一个PreparedStatement对象
		PreparedStatement pstmt = con.prepareStatement("INSERT INTO t_dept(id,deptName) VALUES(12,'哈哈')");
		//执行sql语句
		pstmt.execute();
		//关闭
		pstmt.close();
		con.close();
	}
	
	
}
