package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	//https://stackoverflow.com/questions/17277001/dll-missing-in-jdbc
	//https://stackoverflow.com/questions/23949890/java-lang-unsatisfiedlinkerror-no-sqljdbc-auth-in-java-library-path

	private static Connection connection;

	public static void open() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=kabp;integratedSecurity=true");
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return connection;
	}

	public static void close() {
		try {
			connection.close();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

}
