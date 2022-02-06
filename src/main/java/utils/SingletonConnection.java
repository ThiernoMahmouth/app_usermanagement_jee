package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection 
{
	private static Connection connection;
	
	private SingletonConnection() 
	{
		
	}
	// Pour jdbc
	public static Connection getConnection() throws SQLException
	{
		if (connection == null || connection.isClosed())
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tp_jee","root","");
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		return connection;
	}
}
