package beans;

import java.util.HashMap;
import java.util.Map;

public class ResponseDetails 
{
	private boolean status;
	private String statusMessage;
	private Map<String, String> erreurs;
	
	public ResponseDetails() 
	{
		this.status = false;
		this.erreurs = new HashMap<>();
	}
	public boolean getStatus() 
	{
		return status;
	}
	public void setStatus(boolean status) 
	{
		this.status = status;
	}
	public String getStatusMessage() 
	{
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage)
	{
		this.statusMessage = statusMessage;
	}
	public Map<String, String> getErreurs()
	{
		return erreurs;
	}
	public void setErreurs(Map<String, String> erreurs)
	{
		this.erreurs = erreurs;
	}
}
