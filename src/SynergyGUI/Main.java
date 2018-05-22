package SynergyGUI;


public class Main
{
	public static void main(String args[])
	{
		UIDesign obj = new UIDesign();
		obj.Login();
		
		Database db = new Database();
		db.empLoggingIn();
	}
}
