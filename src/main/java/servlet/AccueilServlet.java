package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Secure;
import dao.SecurityDao;
import metier.UserService;


@WebServlet({"", "/test"})
public class AccueilServlet extends HttpServlet 
{
	
	private static final long serialVersionUID = 1L;
	private static final String VUE_LIST_USERS = "/index.jsp";
    
	private UserService userService;
	@Override
	public void init() throws ServletException 
	{
		userService = new UserService();
	}
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	String path = request.getServletPath();
    	if(path.equals(""))
    	{
    		request.setAttribute("utilisateurs", userService.findAllUsers());
        	getServletContext().getRequestDispatcher(VUE_LIST_USERS).forward(request, response);
    	}
    	else
    	{
    		Secure secure = new Secure();
  		  	SecurityDao secDAO = new SecurityDao();
  		  	if (secDAO.ajouter(secure))
  			  	System.out.println("Reussi");
  		  	else
  			  	System.out.println("Erreur");
    	}
    	
	}


}
