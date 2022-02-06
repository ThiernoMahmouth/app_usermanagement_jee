package utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class FormUtils 
{
	public static void validateFields(HttpServletRequest request, Map<String,String> erreurs, String ...fields)
	{
		for (String field : fields)
		{ 
			if (getParameter(request, field) == null)
			{
				erreurs.put(field, "Vous devez renseigner ce champ!");
			}
		}
	}
	
	public static String getParameter(HttpServletRequest request, String param)
	{
		String value = request.getParameter(param);
		if (value == null || value.trim().isEmpty())
		{
			return null;
		}
		return value.trim();
	}
	
	
	public static void validatePassword(HttpServletRequest request, Map<String, String> erreurs)
	{
		String password = getParameter(request, "password");
		String password_bis = getParameter(request, "password_bis");
		
		if (password != null && !password.equals(password_bis) )
		{
			erreurs.put("password", "Les mots de passe ne sont pas conformes!");
			erreurs.put("password_bis", "Les mots de passe ne sont pas conformes!");
		}
	}
	
	public static boolean validateFields(HttpServletRequest request, String ...fields)
	{
		boolean isValide = true;
		for (String field : fields)
		{ 
			if (getParameter(request, field) == null)
			{
				isValide = false;
				break;
			}
		}
		return isValide;
	}
	
	public static boolean validatePassword(HttpServletRequest request)
	{
		boolean isValide = false;
		String password = getParameter(request, "password");
		String password_bis = getParameter(request, "password_bis");
		
		if (password != null && password.equals(password_bis) )
		{
			isValide = true;			
		}
		return isValide;
	}
}

