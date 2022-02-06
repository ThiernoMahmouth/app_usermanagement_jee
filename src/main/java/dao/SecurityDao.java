package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Secure;
import utils.DateUtils;
import utils.SingletonConnection;

public class SecurityDao 
{
	public  boolean ajouter (Secure secure) 
	{
		String requete = "INSERT INTO secure(salt, date, time) VALUES (?, ?, ?);";
        Connection connection = null;
        boolean result = false;
		try 
        {
        	connection = SingletonConnection.getConnection();
        	connection.setAutoCommit(false);
        	// Insertion
        	PreparedStatement preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setString(1, secure.getSalt());	
            preparedStatement.setDate(2, DateUtils.getSQLDate(secure.getDate()));	
            preparedStatement.setTime(3, DateUtils.getSQLTime(secure.getTime()));	
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
            result = true;
        } 
		catch (SQLException e) 
		{
		     e.printStackTrace();
		     if(connection != null)
		     {
		       try 
		       {
		         // Rollingback the transaction
		         connection.rollback();
		       } 
		       catch (SQLException e1) 
		       {
		         e1.printStackTrace();
		       }
		     }    
		}
		finally
		{
		       if(connection != null)
		       {
		    	   //closing connection 
			        try 
			        {
			          connection.close();
			        } catch (SQLException e) 
			        {
			           e.printStackTrace();
			        }
		      } 
		}				 
		return result;
	}

	public static String getSalt() 
	{
		String code = null;
		Connection connection = null;
		try 
     	{
        	connection = SingletonConnection.getConnection();
    	    PreparedStatement ps = connection.prepareStatement("select * from secure limit 1;");
            ResultSet rs= ps.executeQuery();
            if (rs.next())
            {
            	code = rs.getString("salt");		            
            }
            ps.close();
            connection.close();
        } 
		catch (SQLException e) 
		{
		     e.printStackTrace();	      
		}		 
		return code;
	}
}
