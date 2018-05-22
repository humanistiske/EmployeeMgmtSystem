package SynergyGUI;

public class EmpInfo 
{
	//employee instance variables
	private String empid, name, address, emailid, designation, managerid, password;
	private long contact;
	
	//employee getters and setters
	protected String getEmpid() 
	{
		return empid;
	}
	
	protected void setEmpid(String empid) 
	{
		this.empid = empid;
	}
	
	protected String getPassword() 
	{
		return password;
	}
	
	protected void setPassword(String password)
	{
		this.password = password;
	}
	
	protected String getName() 
	{
		return name;
	}
	
	protected void setName(String name) 
	{
		this.name = name;
	}
	
	protected String getAddress() 
	{
		return address;
	}
	
	protected void setAddress(String address) 
	{
		this.address = address;
	}
	
	protected String getEmailid() 
	{
		return emailid;
	}
	
	protected void setEmailid(String emailid) 
	{
		this.emailid = emailid;
	}
	
	protected String getDesignation() 
	{
		return designation;
	}

	protected void setDesignation(String designation) 
	{
		this.designation = designation;
	}
	
	protected String getManagerid() 
	{
		return managerid;
	}
	
	protected void setManagerid(String managerid) 
	{
		this.managerid = managerid;
	}
	
	protected long getContact() 
	{
		return contact;
	}
	
	protected void setContact(long contact) 
	{
		this.contact = contact;
	}
	
	//overriden toString method for employee
	public String toString() 
	{
		return "EmpInfo [empid=" + empid + ", name=" + name + ", address=" + address + ", emailid=" + emailid
				+ ", designation=" + designation + ", managerid=" + managerid + ", contact=" + contact + "]";
	}

}
