package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {

	private static Connection conn;

	private dbConnection() {
	}

	public static Connection getConnection() {

		if (conn != null) {
			return conn;
		}

		try {

			Class.forName("org.gjt.mm.mysql.Driver");
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolData?autoReconnect=true&useSSL=false",
					"root", "123123");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을수없습니다 : " + e);
		} catch (SQLException e) {
			System.out.println(("일반 예외:" + e));
		}

		return conn;
	}

}
