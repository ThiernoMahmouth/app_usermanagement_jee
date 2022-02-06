package metier;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import beans.ResponseDetails;
import beans.Utilisateur;
import dao.UtilisateurDao;
import utils.FormUtils;
import utils.PasswordUtils;

public class UserService 
{
	private static final String nom_field = "nom";
	private static final String prenom_field = "prenom";
	private static final String login_field = "login";
	private static final String password_field = "password";
	private static final String password_bis_field = "password_bis";

	/**********************************************************************/
	
	private Utilisateur utilisateur;
	private UtilisateurDao userDAO;
	private ResponseDetails responseDetails;
	
	public UserService()
	{
		this.userDAO = new UtilisateurDao();
	}
	public boolean addOrUpdate(HttpServletRequest request, String key)
	{
		responseDetails = new ResponseDetails();
		
		String id = request.getParameter("id");
		String nom = FormUtils.getParameter(request, nom_field);
		String prenom = FormUtils.getParameter(request, prenom_field);
		String login = FormUtils.getParameter(request, login_field);
		String password = FormUtils.getParameter(request, password_field);
		String password_bis = FormUtils.getParameter(request, password_bis_field);
		
		utilisateur = new Utilisateur(nom, prenom, login, password);
		FormUtils.validateFields(request, responseDetails.getErreurs(), nom_field, prenom_field, login_field, password_field, password_bis_field);
		FormUtils.validatePassword(request, responseDetails.getErreurs());
		
		if (responseDetails.getErreurs().isEmpty()) 	// Le formulaire a bien été rempli
		{
			if( id != null && id.matches("[0-9]+")) // Il s'agit d'une modification
			{
				utilisateur.setId(Integer.parseInt(id));
				responseDetails.setStatus(userDAO.modifier(utilisateur));
				if (responseDetails.getStatus())	// Modification reussie
				{
					responseDetails.setStatusMessage("Modification effectuée avec succès!");
					return true;
				}
				// Erreur 
				responseDetails.setStatusMessage("Echec de la modification!");
				return false;
			}
			else	// Ajout
			{
				// On crypte le mot de passe
				utilisateur.setPassword(PasswordUtils.hashPassword(password, key));
				responseDetails.setStatus(userDAO.ajouter(utilisateur));
				if (responseDetails.getStatus())		// Ajout reussi
				{
					responseDetails.setStatusMessage("Ajout effectué avec succès!");
					return true;
				}
				// Erreur
				responseDetails.setStatusMessage("Echec de l'ajout!");
				return false;
			}		
		}
		// Le formulaire n'a pas ete bien rempli
		return false;
	}
	
	public ArrayList<Utilisateur> findAllUsers()
	{
		return userDAO.lister();
	}
	
	public Utilisateur findById(int id)
	{
		return userDAO.get(id);
	}
	public boolean delete(int id)
	{
		return userDAO.supprimer(id);
	}
	
	public ResponseDetails getResponseDetails() {
		return responseDetails;
	}
	public void setResponseDetails(ResponseDetails responseDetails) {
		this.responseDetails = responseDetails;
	}

}
