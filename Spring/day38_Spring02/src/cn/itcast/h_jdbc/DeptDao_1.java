package cn.itcast.h_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.junit.Test;

public class DeptDao_1 {
	
	//1、原始jdbc代码
	public void save(Dept dept) throws Exception{
		//注册驱动
		Class.forName("com.mysql.jdbc.Driver");
		//获取连接对象
		Connection con = DriverManager.getConnection("jdbc:mysql:///hib_demo","root","root");
		//执行预编译的sql
		PreparedStatement pstmt = con.prepareStatement("insert into t_dept(id,deptName) values(1,'test')");
		//执行sql
		pstmt.executeUpdate();
		//关闭
		pstmt.close();
		con.close();
	}
}
