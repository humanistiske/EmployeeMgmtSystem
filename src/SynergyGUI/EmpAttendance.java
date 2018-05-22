package SynergyGUI;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class EmpAttendance 
{
	//Instance Variables
	private String inTime, outTime;
	private String date;
	
	//Date and time variables
	private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("YYYY-MM-dd"); 
	private final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

	
	protected void setInTime()
	{
		this.inTime = timeFormat.format(LocalTime.now()).toString();
	}
	
	//overwriting setInTime() to reset value of intime if user has punched in and closed application  
	protected void setInTime(String intime)
	{
		this.inTime = intime;
	}
	
	protected String getInTime()
	{
		return inTime;
	}
	
	protected void setOutTime()
	{
		this.outTime = timeFormat.format(LocalTime.now()).toString();
	}
	
	protected String getOutTime()
	{
		return outTime;
	}

	public String getDate() 
	{
		return date;
	}

	public void setDate() 
	{
		this.date = dateFormat.format(LocalDate.now()).toString();
	}
	
	
}
