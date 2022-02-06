package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Utilisateur;
import utils.SingletonConnection;

public class UtilisateurDao 
{

	public  boolean ajouter (Utilisateur utilisateur) 
	{
		String requete = "INSERT INTO utilisateur(nom, prenom, login, password, role) VALUES (?, ?, ?, ?, ?);";
        Connection connection = null;
        boolean result = false;
		try 
        {
        	connection = SingletonConnection.getConnection();
        	connection.setAutoCommit(false);
        	// Insertion
        	PreparedStatement preparedStatement = connection.prepareStatement(requete);
            preparedStatement.setString(1, utilisateur.getNom());	
            preparedStatement.setString(2, utilisateur.getPrenom());	
            preparedStatement.setString(3, utilisateur.getLogin());	
            preparedStatement.setString(4, utilisateur.getPassword());	
            preparedStatement.setString(4, utilisateur.getRole());	
            preparedStatement.executeUpdate();
            preparedStatement.close();
            // Récupération de l'id du dernier élément inséré
            PreparedStatement ps = connection.prepareStatement("select max(id) as MAX_ID from utilisateur;");
            ResultSet rs= ps.executeQuery();
            if (rs.next())
            {
            	utilisateur.setId(rs.getInt("MAX_ID"));	// Ajout de l'id sur l'objet package passé en paramètre
            }
            ps.close();
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
	public  ArrayList<Utilisateur> lister()
	{
		ArrayList<Utilisateur> users = new ArrayList<>();
		Connection connection = null;
		try 
     	{
        	connection = SingletonConnection.getConnection();
    	    PreparedStatement ps = connection.prepareStatement("select * from utilisateur;");
            ResultSet rs= ps.executeQuery();
            while (rs.next())
            {
            	Utilisateur user = new Utilisateur();
            	user.setId(rs.getInt("id"));
            	user.setNom(rs.getString("nom"));
            	user.setPrenom(rs.getString("prenom"));		            
            	user.setLogin(rs.getString("login"));		            
            	user.setPassword(rs.getString("password"));		 
            	users.add(user);
            }
            ps.close();
            connection.close();
        } 
		catch (SQLException e) 
		{
		     e.printStackTrace();	      
		}	
		
		return users;
	}
	public  boolean supprimer(int id) 
	{
		String requete = "DELETE FROM utilisateur WHERE id = ?;";
		boolean result = false;
		Connection connection = null;
		try 
		{
        	connection = SingletonConnection.getConnection();
        	connection.setAutoCommit(false);
			PreparedStatement ps = connection.prepareStatement(requete);
	        ps.setInt(1, id);
	        ps.executeUpdate();
	        ps.close();
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
	public  Utilisateur get(int id) 
	{
		Utilisateur user = null;
		Connection connection = null;
		try 
     	{
        	connection = SingletonConnection.getConnection();
    	    PreparedStatement ps = connection.prepareStatement("select * from utilisateur where  id = ?;");
    	    ps.setInt(1, id);
            ResultSet rs= ps.executeQuery();
            if (rs.next())
            {
            	user = new Utilisateur();
            	user.setId(id);
            	user.setNom(rs.getString("nom"));
            	user.setPrenom(rs.getString("prenom"));		            
            	user.setLogin(rs.getString("login"));		            
            	user.setPassword(rs.getString("password"));		            
            }
            ps.close();
            connection.close();
        } 
		catch (SQLException e) 
		{
		     e.printStackTrace();	      
		}		 
		return user;
	}
	public Utilisateur findByLogin(String login) 
	{
		Utilisateur user = null;
		Connection connection = null;
		try 
     	{
        	connection = SingletonConnection.getConnection();
    	    PreparedStatement ps = connection.prepareStatement("select * from utilisateur where login = ?;");
    	    ps.setString(1, login);
            ResultSet rs= ps.executeQuery();
            if (rs.next())
            {
            	user = new Utilisateur();
            	user.setId(rs.getInt("id"));
            	user.setNom(rs.getString("nom"));
            	user.setPrenom(rs.getString("prenom"));		            
            	user.setLogin(rs.getString("login"));		            
            	user.setPassword(rs.getString("password"));		            
            }
            ps.close();
            connection.close();
        } 
		catch (SQLException e) 
		{
		     e.printStackTrace();	      
		}		 
		return user;

	}
	public  boolean modifier(Utilisateur utilisateur)
	{
		String requete = "UPDATE utilisateur SET nom = ?, prenom = ?, login = ?, password = ? WHERE id = ?;";
        Connection connection = null;
        boolean result = false;
		try 
        {
        	connection = SingletonConnection.getConnection();
        	connection.setAutoCommit(false);
        	// Insertion
        	PreparedStatement preparedStatement = connection.prepareStatement(requete);
        	preparedStatement.setString(1, utilisateur.getNom());	
            preparedStatement.setString(2, utilisateur.getPrenom());	
            preparedStatement.setString(3, utilisateur.getLogin());	
            preparedStatement.setString(4, utilisateur.getPassword());	
            preparedStatement.setInt(5, utilisateur.getId());		
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
}
