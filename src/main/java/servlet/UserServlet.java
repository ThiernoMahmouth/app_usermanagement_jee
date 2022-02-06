package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Utilisateur;
import dao.SecurityDao;
import metier.UserService;

@WebServlet("/users/*")
public class UserServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static final String VUE_AJOUT_UTILISATEUR = "/WEB-INF/add.jsp";
	private static final String VUE_LIST_UTILISATEUR = "/index.jsp";
	private static final String VUE_MODIFIER_UTILISATEUR = "/WEB-INF/edit.jsp";
	private String key;
	private UserService userService;

	@Override
	public void init() throws ServletException 
	{
		this.userService = new UserService();
		key = SecurityDao.getSalt();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String path = request.getPathInfo();
		switch (path) 
		{
			case "/delete":
					String identifiant = request.getParameter("id");				
					if(identifiant != null && identifiant.matches("[0-9]+")) 
					{	
						userService.delete(Integer.parseInt(identifiant));
					}
					response.sendRedirect(request.getContextPath());
			break;
			case "/add":
					getServletContext().getRequestDispatcher(VUE_AJOUT_UTILISATEUR).forward(request, response);
			break;
			case "/update":
					String id = request.getParameter("id");
					if( id != null && id.matches("[0-9]+")) 
					{
						Utilisateur utilisateur = userService.findById(Integer.parseInt(id));
						if(utilisateur != null) 
						{
							request.setAttribute("utilisateur", utilisateur);
							getServletContext().getRequestDispatcher(VUE_MODIFIER_UTILISATEUR).forward(request, response);
						}
					}
					else
					{
						response.sendRedirect(request.getContextPath());
					}
			break;
			case "/list":
					request.setAttribute("utilisateurs", userService.findAllUsers());
					getServletContext().getRequestDispatcher(VUE_LIST_UTILISATEUR).forward(request, response);
			break;
			default:
					request.setAttribute("utilisateurs", userService.findAllUsers());
					getServletContext().getRequestDispatcher(VUE_LIST_UTILISATEUR).forward(request, response);
			break;
		} 
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		userService.addOrUpdate(request, key);
		request.setAttribute("status", userService.getResponseDetails().getStatus());
		request.setAttribute("message",  userService.getResponseDetails().getStatusMessage());
		if ( ! userService.getResponseDetails().getStatus() &&  userService.getResponseDetails().getStatusMessage() == null)
		{
			request.setAttribute("message", "Veuillez bien remplir tous les champs du formulaire!");
			if (request.getPathInfo().equals("/add"))
				getServletContext().getRequestDispatcher(VUE_AJOUT_UTILISATEUR).forward(request, response);
			else
				getServletContext().getRequestDispatcher(VUE_MODIFIER_UTILISATEUR).forward(request, response);
		}
		else
		{
			request.setAttribute("utilisateurs", userService.findAllUsers());
			getServletContext().getRequestDispatcher(VUE_LIST_UTILISATEUR).forward(request, response);
		}
	
	}
	
}
