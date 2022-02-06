package utils;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class DateUtils 
{
	public static Date getSQLDate(LocalDate date) 
	{
        return java.sql.Date.valueOf(date);
    }
	
	public static Time getSQLTime(LocalTime heure) 
	{
        return java.sql.Time.valueOf(heure);
    }

    public static LocalDate getLocalDate(Date sqlDate) 
    {
        return sqlDate.toLocalDate();
    }
    public static LocalTime getLocalTime(Time sqlTime)
    {
    	return sqlTime.toLocalTime();
    }
}
