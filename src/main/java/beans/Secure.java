package beans;

import java.time.LocalDate;
import java.time.LocalTime;

import utils.PasswordUtils;

public class Secure 
{
	private String salt;
	private LocalDate date;
	private LocalTime time;
	
	public Secure()
	{	
		this.salt = PasswordUtils.generateSalt(PasswordUtils.KEY_LENGTH);
		this.date = LocalDate.now();
		this.time = LocalTime.now();
	}
	public String getSalt() 
	{
		return salt;
	}
	public void setSalt(String salt) 
	{
		this.salt = salt;
	}
	public LocalDate getDate() 
	{
		return date;
	}
	public void setDate(LocalDate date) 
	{
		this.date = date;
	}
	public LocalTime getTime() 
	{
		return time;
	}
	public void setTime(LocalTime time) 
	{
		this.time = time;
	}
	
}
