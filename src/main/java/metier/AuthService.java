package metier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Utilisateur;
import dao.UtilisateurDao;
import utils.FormUtils;
import utils.PasswordUtils;

public class AuthService 
{
	private static final String login_field = "login";
	private static final String password_field = "password";
	private UtilisateurDao userDAO;
	
	public AuthService()
	{
		this.userDAO = new UtilisateurDao();
	}
	
	public boolean authentifier(HttpServletRequest request, String key)
	{
		String login = FormUtils.getParameter(request, login_field);
		String password = FormUtils.getParameter(request, password_field);
		
		Utilisateur user = null;
		HttpSession session = request.getSession();
		String passerEncrypted = PasswordUtils.hashPassword("passer", key);
		if ("admin".equals(login) && PasswordUtils.verifyPassword(password,passerEncrypted, key))
		{
			user = new Utilisateur("ADMIN", "admin", "admin", "");
		}
		else 
		{
			user = userDAO.findByLogin(login);
			if (user != null && !PasswordUtils.verifyPassword(password,user.getPassword(), key))
			{
				user = null;
			}	
		}
		if (user != null)
		{
			session.setAttribute("utilisateur", user);
			return true;
		}
		return false;
	}
	
}
