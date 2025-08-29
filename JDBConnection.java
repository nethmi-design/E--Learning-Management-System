package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JDBConnection {

	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		@SuppressWarnings("unused")
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/elearning_db", "root", "");
		System.out.println("connection connected");
	}

	public static Connection getConnection() {
		return null;
	}	
}
// -------https://www.youtube.com/watch?si=aHDFM8b_jr-HroxE&v=3PRW9N5yk2I&feature=youtu.be--------