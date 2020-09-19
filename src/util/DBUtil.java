package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	public static String classname="com.mysql.jdbc.Driver";
	public static String mysqlurl="jdbc:mysql://106.15.248.6:3306/zytter?characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false";
	public static String mysqluser="zytter";
	public static String mysqlpass="123123";
	static {
		try {
			Class.forName(classname);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnect(){
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(mysqlurl,mysqluser,mysqlpass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void closeRs(ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void closePSMT(PreparedStatement psmt) {
		if(psmt!=null) {
			try {
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void closeConnect(Connection conn) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
