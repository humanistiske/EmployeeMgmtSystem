package SynergyGUI;

import java.time.LocalTime;

public class Misc 
{
	public boolean checkTime(String time)
	{
		boolean res = false;
		try
		{
			LocalTime time1 = LocalTime.parse(time);
			res = true;
		}
		catch(RuntimeException e)
		{
			res = false;
		}
		
		return res;
	}
	

}
