package com.wz.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
	public static final String Driver = "com.mysql.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8";
	public static final String user = "root";
	public static final String password = "123";

	static {
		try {
			Class.forName(Driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
}
