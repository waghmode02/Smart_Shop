package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/smart_shop";
	private static final String user = "root";
	private static final String password = "Nagesh@123";

	public static Connection getConnection() throws SQLException {

		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return con;
	}

}
