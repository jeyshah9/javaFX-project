package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqlHelperClass {
	 

	public static Connection connector() {
		
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:project.sqlite");
			
			return connection;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			return null;
		}

	}

}
