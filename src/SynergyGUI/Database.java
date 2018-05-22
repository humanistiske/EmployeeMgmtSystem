package SynergyGUI;

//import all mysql jdbc components
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import java.sql.ResultSetMetaData;

public class Database 
{
	//Global Variables
	private String query;
	protected static String empid;
	protected String password;
	private boolean res;
	private boolean manager;
	//list of strings for job list
	
	
	//instance variables for db connection
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	
	//other instance variables
	protected EmpAttendance emp1 = new EmpAttendance();
	protected EmpInfo employee1 = new EmpInfo();
	protected Misc misc = new Misc(); 
	protected JobDist job1 = new JobDist();
	String arrayAttendance[][] = new String[100][];
	String contact, emailid, address;
	
	

	//creating constructor for employee
	protected EmpInfo empinfo1 = new EmpInfo();
	
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SynergyDB?zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8", "ganesh", "password");
			stmt = con.createStatement();
			
			System.out.println("Connection established");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	//Employee Login MEthods
	//executes once employee logs in
	protected boolean empLoggingIn()
	{
		try
		{
			//setting query for mysql login
			query = "select * from SynergyDB.empLogin where empid='" + empid + "' && password = '" + password + "';";
					
			rs = stmt.executeQuery(query);
			
			//if stmt.executeQuery is successful
			if(rs.next())
			{
				res = true;
				if(empid.startsWith("M"))
					manager = true;
				else
					manager = false;
				
				empProfile();
			}
			else
				res = false;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		return res;
	}

	
	//EMployee Information: attendance, profile
	//displays attendance of employee in table
	protected ResultSet getEmpAttendance()
	{
		try
		{
			//create query for fetching data
			query = "select date, intime, outtime  from SynergyDB.emplogs where empid = '" + empid + "';";
			
			rs = stmt.executeQuery(query);
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
		//return statement of return type ResultSet
		return rs;
	}

	//shows employee profile information
	protected void empProfile()
	{	
		//getting database values and putting them into employee
		try
		{
			//setting query to select info of employee
			query = "select * from SynergyDB.empinfo where empid = '" + empid + "';";

			rs = stmt.executeQuery(query);
			
			//setting variables as per db records
			while(rs.next())
			{
				empinfo1.setEmpid(rs.getString("empid"));
				empinfo1.setName(rs.getString("name"));
				empinfo1.setAddress(rs.getString("address"));
				empinfo1.setEmailid(rs.getString("emailid"));
				empinfo1.setContact(rs.getLong("contact"));
				empinfo1.setDesignation(rs.getString("designation"));
				empinfo1.setManagerid(rs.getString("managerid"));
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	//updates employee profile information
	protected void empProfileUpdate()
	{
		try
		{
			//query to update datebase
			query = "update SynergyDB.empinfo set contact=" + contact + ", address='" + address + "', emailid='" + emailid + "' where empid='" + empid + "';";
					
					
			int count = stmt.executeUpdate(query);
			
			if(count == 1)
				JOptionPane.showMessageDialog(null, "You profile has been updated");
			else
				JOptionPane.showMessageDialog(null, "Oops Something went wrong");
				
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	
	//Punch Methods
	//if employee has clicked punch in buttton
	protected boolean empPunchIn()
	{
		try
		{
			//setting date and time variables
			emp1.setDate();
			emp1.setInTime();
			
			
			//setting query to update emplogs
			query = "insert into SynergyDB.emplogs(empid, date, intime) values('" + empid + "', '" + emp1.getDate() + "', '" + emp1.getInTime() + "');";

			int count = stmt.executeUpdate(query);
			
			if(count == 1)
			{
				JOptionPane.showMessageDialog(null, "Thank you for punching in");
				res = true;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Oops... Something went wrong");
				res = false;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		return res;
	}
	
	//if employee has clicked punch out button 
	protected boolean empPunchOut()
	{
		try
		{
			//create outtime 
			emp1.setOutTime();
			
			//query to update outtime
			query = "update SynergyDB.emplogs set outtime='" + emp1.getOutTime() + "' "
					+ "where empid='" + empid + "' && intime='" + emp1.getInTime() + "';";
			
			
			
			int count = stmt.executeUpdate(query);
			
			
			//if query if successfully executed then return is true
			if(count == 1)
			{
				JOptionPane.showMessageDialog(null, "Thank you for punching out");
				res = true;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Oops... Something went wrong");
				res = false;
			}
		}
		catch(Exception e)
		{
			res = false;
			System.out.println(e);
		}
		
		return res;
	}
	
	//check if employee has punched out or not. Disables Punch In/Out Buttons based on result of this method
	protected boolean checkPunch()
	{
		try
		{
			//String to check outtime is present or not
			query = "select intime, outtime from SynergyDB.emplogs where empid='" + empid + "' && outtime is null";
			
			
			rs = stmt.executeQuery(query);
			
			
			
			//if outtime has value then user has punched out 
			if(rs.last())
			{
				String empOuttime = rs.getString("outtime");
				String intime = rs.getString("intime");
				
				//set variable according to last time user has punched in
				emp1.setInTime(intime);
				
				//if user has punched in set return to true
				if(empOuttime==null)
					res = true;
				
			}
			//else user has punched out
			else 
			{
				//if user has punched out then set return to false
				res = false;
			}
		}
		catch(Exception e)
		{
			res = false;
			System.out.println(e);
		}
		
		return res;
	}

	
	//Manager Methods
	//Manager method to retrieve status of all jobs
	protected ResultSet getJobList()
	{
		try
		{
			//set date to current date
			emp1.setDate();
			
			//query to select from JobDist table
			query = "select * from jobdist";
			
			//run rs till it runs out of rows
			rs = stmt.executeQuery(query);

			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
		return rs;
	}

	//Manager method to add jobs for employees
	protected void addJob()
	{
		try
		{
			//setting date to current date
			emp1.setDate();
			
			//query to add job
			query = "insert into synergydb.jobdist(empid, job, date) values('"
					+ empid + "', '" + job1.getJob() + "', '" + emp1.getDate() + "');";
			
			int count = stmt.executeUpdate(query);
			
			if(count==1)
				JOptionPane.showMessageDialog(null, "Thank you. Job has been posted.");
			else
				JOptionPane.showMessageDialog(null, "Oops Something went wrong");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
	}

	//Employee method to view status of current jobs
	protected ResultSet getEmpJobList()
	{
		try
		{
			//query to retrieve selected columns for employee to see
			query = "select date, job, remarks, status, jobid from synergydb.jobdist where empid='" + empid + "';";
			
			rs = stmt.executeQuery(query);
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		return rs;
	}

	//Employee method to update Job Status
	protected void updateEmpJobStatus()
	{
		try
		{
			//set jobid as per row selected from Employee job list table
			rs = getJobList();
			while(rs.next())
			{
				if(job1.getJob().equals(rs.getString("job")))
					job1.setJobid(rs.getInt("jobid"));
			}
			
			
			//query to update table jobdist based on data entered by employee
			query = "update synergydb.jobdist set "
					+ "remarks='" + job1.getRemarks() + "'"
					+ ", starttime='" + job1.getStarttime() + "'"
					+ ", endtime='" + job1.getEndtime() + "'"
					+ ", status='" + job1.getStatus() + "'"
					+ " where jobid='" + job1.getJobid() + "';";
			
			
			int count = stmt.executeUpdate(query);
			
			if(count==1)
				JOptionPane.showMessageDialog(null, "Your job status has been posted");
			else
				JOptionPane.showMessageDialog(null, "Oops something went wrong...");
		}
		
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	

	//Employee method to set starttime and endtime in job info field based on row user has selected
	protected boolean setTime(String job)
	{
		try
		{
			//query to select starttime and endtime from jobdist based on provided job
			query = "select starttime, endtime from synergydb.jobdist where job='" + job + "' && jobid ='" + job1.getJobid() + "'";

			
			rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				job1.setStarttime(rs.getTime("starttime").toString());
				job1.setEndtime(rs.getString("endtime").toString());
			}
			
			return true;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
	}


	//New employee registration method
	protected void addEmployee()
	{
		try
		{
			//query to insert data into table for adding employee
			String empInfo = "insert into synergydb.empinfo(name, designation, address, contact, emailid, empid, managerid) values"
					 + "('" + employee1.getName() + "', "
					 + "'" + employee1.getDesignation() + "', "
					 + "'" + employee1.getAddress() + "', "
					 + employee1.getContact() + ", "
					 + "'" + employee1.getEmailid() + "', "
					 + "'" + employee1.getEmpid() + "', "
					 + "'" + employee1.getManagerid() + "');";
			
			String empLogin = "insert into synergydb.emplogin(empid, password) values ('"
					+ employee1.getEmpid() + "', "
					+ "'" + employee1.getPassword() + "');";

			int countEmpInfo = stmt.executeUpdate(empInfo);
			int countEmpLogin = stmt.executeUpdate(empLogin);
			
			if(countEmpInfo == 1)
			{
				if(countEmpLogin == 1)
				{
					JOptionPane.showMessageDialog(null, "Employee has been added");
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	
	}
	

}
