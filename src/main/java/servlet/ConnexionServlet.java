package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SecurityDao;
import metier.AuthService;

@WebServlet({"/login", "/logout"})
public class ConnexionServlet extends HttpServlet
{

	private static final long serialVersionUID = 1L;
	private static final String VUE_LOGIN = "/login.jsp";
	private AuthService authService;
	private String key;

	
	@Override
	public void init() throws ServletException 
	{
		authService = new AuthService();
		key = SecurityDao.getSalt();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		switch (request.getServletPath())
		{
			case "/login":
					getServletContext().getRequestDispatcher(VUE_LOGIN).forward(request, response);
			break;
			default:
					HttpSession session = request.getSession();
					session.invalidate();
					getServletContext().getRequestDispatcher(VUE_LOGIN).forward(request, response);
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		if (authService.authentifier(request, key))
		{
			response.sendRedirect(request.getContextPath() + "/users/list");
		}
		else
		{
			request.setAttribute("erreur", "Login ou mot de passe incorrect!");
			getServletContext().getRequestDispatcher(VUE_LOGIN).forward(request, response);
		}
	}

}
