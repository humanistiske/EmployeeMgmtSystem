package SynergyGUI;

//imports with javax.swing for GUI
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.proteanit.sql.DbUtils;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.Color;
import java.awt.Dimension;
//imort for actions and evenets
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class UIDesign extends Database
{
	//Instance variables
	private JFrame frameLogin; 
	private JFrame frameApplication;
	private JTabbedPane tpAI;
	private JPanel pJobList, pAttendance, pProfile, pJobDist, pAddEmp;
	private JMenuBar mbMenuBar;
	private JMenu file, help;
	private JMenuItem New, print, save, logout, about;
	private JTable tAttendance, tMJobList, tEJobList;
	private JScrollPane spAttendance;
	private BorderLayout layout = new BorderLayout();
	
	protected final String[] columnNames = {"Date", "In Time", "Out Time"};
	
	
	public void Login()
	{
		//creating and setting JFrame
		frameLogin = new JFrame("Synergy Employee Management Login");
		frameLogin.setSize(400, 400);
		frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//creating and setting layout for JFrame
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();

		
		frameLogin.setLayout(gridBag);

		
		//creating and setting employee id
		JPanel pEmpId = new JPanel();
		JLabel lbEmpid = new JLabel("Employee ID: ");
		lbEmpid.setBounds(250, 300, 100, 20);
		JTextField tfEmpid = new JTextField(15);
		pEmpId.add(lbEmpid);
		pEmpId.add(tfEmpid);
		
		
		//creating and setting password
		JPanel pPassword = new JPanel();
		JLabel lbPassword = new JLabel("Password:    ");
		lbPassword.setBounds(250, 330, 100, 20);
		JPasswordField tfPassword = new JPasswordField(15);
		
		pPassword.add(lbPassword);
		pPassword.add(tfPassword);
		
	
		//creating and setting buttons
		JPanel pButtons = new JPanel();
		JButton btLogin = new JButton("Login");
		btLogin.setBounds(290, 380, 80, 25);
		JButton btReset = new JButton("Reset");
		btReset.setBounds(390, 380, 80, 25);
		pButtons.add(btLogin);
		pButtons.add(btReset);
		
		//creating action for Login
		btLogin.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						//fetching employeeid and password for database
						empid = tfEmpid.getText();
						password = tfPassword.getText();
						
						if(empLoggingIn())
						{
							frameLogin.setVisible(false);
							AI();
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Incorrect Username/Password");
						}
					}
				});
		
		
		//creating action for Reset
		btReset.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						tfEmpid.setText("");
						tfPassword.setText("");
					}
				});

		//positioning components as per GridBagLayout
		//adding componenets to JFrame
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;

		frameLogin.add(pEmpId, gbc);
		
		gbc.ipady = 20;
		gbc.gridx = 0;
		gbc.gridy = 1;
		frameLogin.add(pPassword, gbc);
		
		
		gbc.ipady = 30;
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		frameLogin.add(pButtons, gbc);
		
		
		//setting layout to JFrame and making it visible
		frameLogin.pack();
		frameLogin.setVisible(true);
		
	}

	public void AI()
	{
		//creating AI after login
		frameApplication = new JFrame("Synergy Employee Management System");
		frameApplication.setPreferredSize(new Dimension(800, 790));
		frameApplication.setMinimumSize(new Dimension(800, 790));
		
		

		frameApplication.getContentPane().setBackground(Color.WHITE);
		frameApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//create menu bar and add menu items
		mbMenuBar = new JMenuBar();
		
		file = new JMenu("File");
		help = new JMenu("Help");
		
		//creating menu items
		print = new JMenuItem("Print");
		logout = new JMenuItem("Logout");
		about = new JMenuItem("About");
		
		//adding menuitems to menus
		file.add(print);
		file.add(logout);
		help.add(about);
		
		mbMenuBar.add(file);
		mbMenuBar.add(help);
		
		
		//create and setting TabbedPane
		tpAI = new JTabbedPane();
		tpAI.setBounds(20, 20, 750, 700);
		
		//create and setting JPanel for all tabs
		pJobList = getEmpJobInfo();
		pAttendance = getEmpAttendancePanel();
		pProfile = getEmpProfile();
		pJobDist = getJobDistPanel();
		pAddEmp = addEmp();
		
	
		
		//adding panels to tpAI
		tpAI.addTab("Job List", pJobList);
		tpAI.addTab("Job Assignment", pJobDist);
		tpAI.addTab("Attendance Sheet", pAttendance);
		tpAI.addTab("Employee Profile", pProfile);
		tpAI.addTab("Add Employee", pAddEmp);
		
		//setting visibility of tabs as per login 
		if(empid.startsWith("S"))
			tpAI.removeTabAt(1);
		
		if(empid.startsWith("S") || empid.startsWith("M"))
			tpAI.removeTabAt(3);
		
		
		//adding components to AI
		frameApplication.add(tpAI); 
		frameApplication.setJMenuBar(mbMenuBar);;
		
		frameApplication.pack();
		
		//setting frame visibility to true
		frameApplication.setVisible(true);
	}
	
	//method to set Employee Profile
	protected JPanel getEmpProfile()
	{
		//creating panel for info
		pProfile = new JPanel();
		
		//creating Spring layout variable
		SpringLayout slayout = new SpringLayout();
		
		//creating and setting components of profile
		JLabel lEmpid = new JLabel("Employee ID: ");
		JTextField tfEmpid = new JTextField();
		JLabel lName = new JLabel("Name: ");
		JTextField tfName = new JTextField();
		JLabel lDesignation = new JLabel("Designation: ");
		JTextField tfDesignation = new JTextField();
		JLabel lContact = new JLabel("Contact: ");
		JTextField tfContact = new JTextField();
		JLabel lAddress = new JLabel("Address: ");
		JTextArea taAddress = new JTextArea();
		JLabel lEmailID = new JLabel("Email ID: ");
		JTextField tfEmailID = new JTextField();
		JLabel lManagerID = new JLabel("Manager ID: ");
		JTextField tfManagerID = new JTextField();
		JButton bEdit = new JButton("Edit");
		JButton bSave = new JButton("Save");
		JButton bRefresh = new JButton("Refresh");
		JScrollPane scrollPane = new JScrollPane(taAddress);
		
		
		
		//setting textfields
		tfEmpid.enable(false);
		tfEmpid.setText(empinfo1.getEmpid());
		tfEmpid.setDisabledTextColor(Color.BLACK);;
		tfEmpid.setBackground(null);
		tfEmpid.setColumns(15);
		tfName.enable(false);
		tfName.setDisabledTextColor(Color.BLACK);;
		tfName.setText(empinfo1.getName());
		tfName.setBackground(null);
		tfName.setColumns(15);
		tfDesignation.enable(false);
		tfDesignation.setDisabledTextColor(Color.BLACK);;
		tfDesignation.setText(empinfo1.getDesignation());
		tfDesignation.setColumns(15);
		tfDesignation.setBackground(null);
		tfContact.enable(false);
		tfContact.setText(new Long(empinfo1.getContact()).toString());
		tfContact.setColumns(15);
		tfContact.setDisabledTextColor(Color.BLACK);;
		tfContact.setBackground(null);
		taAddress.enable(false);
		taAddress.setText(empinfo1.getAddress());
		taAddress.setWrapStyleWord(true);
		taAddress.setLineWrap(true);
		taAddress.setColumns(15);
		taAddress.setRows(5);
		taAddress.setBackground(null);
		taAddress.setDisabledTextColor(Color.BLACK);;
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		tfEmailID.enable(false);
		tfEmailID.setText(empinfo1.getEmailid());
		tfEmailID.setColumns(15);
		tfEmailID.setDisabledTextColor(Color.BLACK);
		tfEmailID.setBackground(null);
		tfManagerID.enable(false);
		tfManagerID.setText(empinfo1.getManagerid());
		tfManagerID.setColumns(15);
		tfManagerID.setBackground(null);
		tfManagerID.setDisabledTextColor(Color.BLACK);;
		
		//creating labels for data validation
		JLabel vEmailID = new JLabel("Incorrect Email ID");
		vEmailID.setForeground(Color.red);
		vEmailID.setEnabled(false);
		JLabel vContact = new JLabel("Incorrect Contact Number");
		vContact.setForeground(Color.red);
		vContact.setEnabled(false);
		
		/*//creating action for validation
		tfContact.addKeyListener(new KeyAdapter()
				{
					public void keyTyped(KeyEvent e)
					{
						if(tfContact.getText().matches(".*\\d+.*"))
							vContact.setEnabled(true);
						else
							vContact.setEnabled(false);
				
						if(tfEmailID.getText().matches("^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*\r\n" + 
												"@[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$"))
							vEmailID.setEnabled(true);
						else
							vEmailID.setEnabled(false);
					}
				});*/
				
		
		//creating actions for buttons
		bEdit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					tfContact.setBackground(Color.WHITE);
					taAddress.setBackground(Color.WHITE);
					tfEmailID.setBackground(Color.WHITE);
					
					
					tfContact.enable(true);
					taAddress.enable(true);
					tfEmailID.enable(true);
					
					
				}
		});
		
		bSave.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						contact = tfContact.getText();
						address = taAddress.getText();
						emailid = tfEmailID.getText();
						
						empProfileUpdate();
						
						tfContact.setEnabled(false);
						taAddress.setEnabled(false);
						tfEmailID.setEnabled(false);
						
						tfContact.setBackground(null);
						taAddress.setBackground(null);
						tfEmailID.setBackground(null);
					}
				});
		
		bRefresh.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						//setting textfields
						tfEmpid.enable(false);
						tfEmpid.setText(empinfo1.getEmpid());
						tfEmpid.setDisabledTextColor(Color.BLACK);;
						tfEmpid.setBackground(null);
						tfEmpid.setColumns(15);
						tfName.enable(false);
						tfName.setDisabledTextColor(Color.BLACK);;
						tfName.setText(empinfo1.getName());
						tfName.setBackground(null);
						tfName.setColumns(15);
						tfDesignation.enable(false);
						tfDesignation.setDisabledTextColor(Color.BLACK);;
						tfDesignation.setText(empinfo1.getDesignation());
						tfDesignation.setColumns(15);
						tfDesignation.setBackground(null);
						tfContact.enable(false);
						tfContact.setText(new Long(empinfo1.getContact()).toString());
						tfContact.setColumns(15);
						tfContact.setDisabledTextColor(Color.BLACK);;
						tfContact.setBackground(null);
						taAddress.enable(false);
						taAddress.setText(empinfo1.getAddress());
						taAddress.setWrapStyleWord(true);
						taAddress.setLineWrap(true);
						taAddress.setColumns(15);
						taAddress.setRows(5);
						taAddress.setBackground(null);
						taAddress.setDisabledTextColor(Color.BLACK);;
						scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
						tfEmailID.enable(false);
						tfEmailID.setText(empinfo1.getEmailid());
						tfEmailID.setColumns(15);
						tfEmailID.setDisabledTextColor(Color.BLACK);
						tfEmailID.setBackground(null);
						tfManagerID.enable(false);
						tfManagerID.setText(empinfo1.getManagerid());
						tfManagerID.setColumns(15);
						tfManagerID.setBackground(null);
						tfManagerID.setDisabledTextColor(Color.BLACK);;
					}
				}
				);
		
		//adding JPAnel components to it
		pProfile.add(lEmpid);
		pProfile.add(tfEmpid);
		pProfile.add(lName);
		pProfile.add(tfName);
		pProfile.add(lDesignation);
		pProfile.add(tfDesignation);
		pProfile.add(lContact);
		pProfile.add(tfContact);
		pProfile.add(lAddress);
		pProfile.add(scrollPane);
		pProfile.add(lEmailID);
		pProfile.add(tfEmailID);
		pProfile.add(lManagerID);
		pProfile.add(tfManagerID);
		pProfile.add(bEdit);
		pProfile.add(bSave);
		pProfile.add(bRefresh);

		
		
		//setting up constraints for positioning JPanel components
		slayout.putConstraint(SpringLayout.WEST, lEmpid, 250, SpringLayout.WEST, pProfile);
		slayout.putConstraint(SpringLayout.NORTH, lEmpid, 20, SpringLayout.NORTH, pProfile);
		slayout.putConstraint(SpringLayout.EAST, tfEmpid, -250, SpringLayout.EAST, pProfile);
		slayout.putConstraint(SpringLayout.NORTH, tfEmpid, 20, SpringLayout.NORTH, pProfile);
		slayout.putConstraint(SpringLayout.WEST, lName, 250, SpringLayout.WEST, pProfile);
		slayout.putConstraint(SpringLayout.NORTH, lName, 50, SpringLayout.NORTH, pProfile);
		slayout.putConstraint(SpringLayout.EAST, tfName, -250, SpringLayout.EAST, pProfile);
		slayout.putConstraint(SpringLayout.NORTH, tfName, 50, SpringLayout.NORTH, pProfile);
		slayout.putConstraint(SpringLayout.WEST, lDesignation, 250, SpringLayout.WEST, pProfile);
		slayout.putConstraint(SpringLayout.NORTH, lDesignation, 80, SpringLayout.NORTH, pProfile);
		slayout.putConstraint(SpringLayout.EAST, tfDesignation, -250, SpringLayout.EAST, pProfile);
		slayout.putConstraint(SpringLayout.NORTH, tfDesignation, 80, SpringLayout.NORTH, pProfile);
		slayout.putConstraint(SpringLayout.WEST, lContact, 250, SpringLayout.WEST, pProfile);
		slayout.putConstraint(SpringLayout.NORTH, lContact, 110, SpringLayout.NORTH, pProfile);
		slayout.putConstraint(SpringLayout.EAST, tfContact, -250, SpringLayout.EAST, pProfile);
		slayout.putConstraint(SpringLayout.NORTH, tfContact, 110, SpringLayout.NORTH, pProfile);
		slayout.putConstraint(SpringLayout.WEST, lAddress, 250, SpringLayout.WEST, pProfile);
		slayout.putConstraint(SpringLayout.NORTH, lAddress, 140, SpringLayout.NORTH, pProfile);
		slayout.putConstraint(SpringLayout.EAST, scrollPane, -250, SpringLayout.EAST, pProfile);
		slayout.putConstraint(SpringLayout.NORTH, scrollPane, 140, SpringLayout.NORTH, pProfile);
		slayout.putConstraint(SpringLayout.WEST, lEmailID, 250, SpringLayout.WEST, pProfile);
		slayout.putConstraint(SpringLayout.NORTH, lEmailID, 230, SpringLayout.NORTH, pProfile);
		slayout.putConstraint(SpringLayout.EAST, tfEmailID, -250, SpringLayout.EAST, pProfile);
		slayout.putConstraint(SpringLayout.NORTH, tfEmailID, 230, SpringLayout.NORTH, pProfile);
		slayout.putConstraint(SpringLayout.WEST, lManagerID, 250, SpringLayout.WEST, pProfile);
		slayout.putConstraint(SpringLayout.NORTH, lManagerID, 260, SpringLayout.NORTH, pProfile);
		slayout.putConstraint(SpringLayout.EAST, tfManagerID, -250, SpringLayout.EAST, pProfile);
		slayout.putConstraint(SpringLayout.NORTH, tfManagerID, 260, SpringLayout.NORTH, pProfile);
		
		slayout.putConstraint(SpringLayout.WEST, bEdit, 260, SpringLayout.WEST, pProfile);
		slayout.putConstraint(SpringLayout.NORTH, bEdit, 340, SpringLayout.NORTH, pProfile);
		slayout.putConstraint(SpringLayout.WEST, bSave, 350, SpringLayout.WEST, pProfile);
		slayout.putConstraint(SpringLayout.NORTH, bSave, 340, SpringLayout.NORTH, pProfile);
		slayout.putConstraint(SpringLayout.EAST, bRefresh, -270, SpringLayout.EAST, pProfile);
		slayout.putConstraint(SpringLayout.NORTH, bRefresh, 340, SpringLayout.NORTH, pProfile);

		
		
		
		pProfile.setLayout(slayout);
		
		return pProfile;
	}

	//method to get attendance profile
	protected JPanel getEmpAttendancePanel()
	{
		//creating and setting JPanel
		pAttendance = new JPanel();
		pAttendance.setLayout(layout);
		
		
		//adding AttendanceTable to pAttendance
		tAttendance = new JTable() ;
		tAttendance.setBounds(20, 60, 600, 600);
		tAttendance.setModel(DbUtils.resultSetToTableModel(getEmpAttendance()));
		spAttendance = new JScrollPane(tAttendance);
		spAttendance.setBounds(tAttendance.getBounds());
		
		
		//creating panel and adding buttons to it
		JPanel pPunchButtons = new JPanel();
		JButton bPunchIn = new JButton("Start Shift");
		JButton bPunchOut = new JButton("End Shift");
		pPunchButtons.add(bPunchIn);
		pPunchButtons.add(bPunchOut);
		
		//enabling and disabling buttons based on intime and outtime
		if(checkPunch())
		{
			bPunchIn.setEnabled(false);
			bPunchOut.setEnabled(true);
		}
		else
		{
			bPunchIn.setEnabled(true);
			bPunchOut.setEnabled(false);
		}
		
		
		//creating action for buttons
		bPunchIn.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(empPunchIn())
						{
							bPunchIn.setEnabled(false);
							bPunchOut.setEnabled(true);
						}
						else
						{
							bPunchIn.setEnabled(true);
							bPunchOut.setEnabled(false);
						}
						tAttendance.setModel(DbUtils.resultSetToTableModel(getEmpAttendance()));
					}
				});
		
		bPunchOut.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(empPunchOut())
						{
							bPunchIn.setEnabled(true);
							bPunchOut.setEnabled(false);
						}
						else
						{
							bPunchIn.setEnabled(false);
							bPunchOut.setEnabled(true);
						}
						tAttendance.setModel(DbUtils.resultSetToTableModel(getEmpAttendance()));
					}
				});
		
		//adding data to panels
		pAttendance.add(spAttendance, BorderLayout.PAGE_START);
		pAttendance.add(pPunchButtons, BorderLayout.PAGE_END);
		
		return pAttendance;
	}
	
	//MANAGER JPAnel
	//main panel for employee to set and get job list and info for Manager
	protected JPanel getJobDistPanel()
	{
		//main JObDist panel containing sub panels
		pJobDist = new JPanel();
		pJobDist.setLayout(new GridLayout(2, 0));
		
		
		
		
		//adding subpanels to main JPanel JobDist
		pJobDist.add(getJobAssignmentPanel());
		pJobDist.add(getJobStatus());
		
		
		//returning main JobDist panel
		return pJobDist;
	}
	
	//Panel for manager to assign jobs and retrive current job list for and from employees
	protected JPanel getJobAssignmentPanel()
	{
		//Create panel for creating job list
		JPanel pJobAssignment = new JPanel();
		pJobAssignment.setSize(100, 100);
		pJobAssignment.setBackground(Color.WHITE);
		SpringLayout sLayout = new SpringLayout();
		//creating border for title and assignment of panel JobAssignment
		TitledBorder JaBorder = new TitledBorder("Job Assignment"); 
		pJobAssignment.setBorder(JaBorder);
		
		//setting and creating components for job description
		JLabel lempid = new JLabel("Employee ID: ");
		JTextField tfempID = new JTextField();
		tfempID.setColumns(15);
		//setting up textarea for Job DEscription
		JLabel lempJob = new JLabel("Job Description: ");
		JTextArea tfempJob = new JTextArea(2, 25);
		tfempJob.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(tfempJob);
		tfempJob.setColumns(20);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		tfempJob.setBorder(border);
		tfempJob.setLineWrap(true);
		JLabel lempName = new JLabel("Employee Name: ");
		JTextField tfempName = new JTextField();
		tfempName.setColumns(15);
		tfempName.setEditable(false);
		JButton bSubmit = new JButton("Submit");
		JButton bRefresh = new JButton("Refresh");
		
		
		//actions to perform after clicking buttons submit and refresh
		bSubmit.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						job1.setJob(tfempJob.getText());
						addJob();
						tMJobList.setModel(DbUtils.resultSetToTableModel(getJobList()));
					}
				});
		
		bRefresh.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						tMJobList.setModel(DbUtils.resultSetToTableModel(getJobList()));
					}
				});
		
		
		//adding and setting components to JPanels
		pJobAssignment.add(lempid);
		pJobAssignment.add(tfempID);
		pJobAssignment.add(lempJob);
		pJobAssignment.add(tfempJob);
		pJobAssignment.add(lempName);
		pJobAssignment.add(tfempName);
		pJobAssignment.add(bSubmit);
		pJobAssignment.add(bRefresh);
		
		
		
		
		
		//positioning components of JobList Panel
		sLayout.putConstraint(SpringLayout.NORTH, lempid, 30, SpringLayout.NORTH, pJobAssignment);
		sLayout.putConstraint(SpringLayout.WEST, lempid, 270, SpringLayout.WEST, pJobAssignment);
		sLayout.putConstraint(SpringLayout.NORTH, tfempID, 30, SpringLayout.NORTH, pJobAssignment);
		sLayout.putConstraint(SpringLayout.EAST, tfempID, -250, SpringLayout.EAST, pJobAssignment);
		sLayout.putConstraint(SpringLayout.NORTH, lempName, 60, SpringLayout.NORTH, pJobAssignment);
		sLayout.putConstraint(SpringLayout.WEST, lempName, 250, SpringLayout.WEST, pJobAssignment);
		sLayout.putConstraint(SpringLayout.NORTH, tfempName, 60, SpringLayout.NORTH, pJobAssignment);
		sLayout.putConstraint(SpringLayout.EAST, tfempName, -250, SpringLayout.EAST, pJobAssignment);
		sLayout.putConstraint(SpringLayout.NORTH, lempJob, 90, SpringLayout.NORTH, pJobAssignment);
		sLayout.putConstraint(SpringLayout.WEST, lempJob, 250, SpringLayout.WEST, pJobAssignment);
		sLayout.putConstraint(SpringLayout.NORTH, tfempJob, 90, SpringLayout.NORTH, pJobAssignment);
		sLayout.putConstraint(SpringLayout.EAST, tfempJob, -195, SpringLayout.EAST, pJobAssignment);
		sLayout.putConstraint(SpringLayout.NORTH, bSubmit, 250, SpringLayout.NORTH, pJobAssignment);
		sLayout.putConstraint(SpringLayout.WEST, bSubmit, 300, SpringLayout.WEST, pJobAssignment);
		sLayout.putConstraint(SpringLayout.NORTH, bRefresh, 250, SpringLayout.NORTH, pJobAssignment);
		sLayout.putConstraint(SpringLayout.EAST, bRefresh, -300, SpringLayout.EAST, pJobAssignment);
		
		
		//setting layout and border for panels
		pJobAssignment.setLayout(sLayout);
		

		return pJobAssignment;
	}
	
	//Table to get status of all employees job list for manager
	protected JPanel getJobStatus()
	{
		//creating and setting panel that lists all the job
		JPanel pJobStatusPanel = new JPanel(new GridLayout());
		TitledBorder border = new TitledBorder("Job Status");
		pJobStatusPanel.setBorder(border);
		pJobStatusPanel.setBackground(Color.WHITE);
		
		//creating and setting table for job list
		tMJobList = new JTable();
		tMJobList.setBounds(20, 30, 400, 400);

		JScrollPane scroll = new JScrollPane(tMJobList);
		scroll.setBounds(tMJobList.getBounds());
		
		
		tMJobList.setModel(DbUtils.resultSetToTableModel(getJobList()));
		
		//adding JTable to JPanel
		pJobStatusPanel.add(scroll);
		
		
		
		return pJobStatusPanel;
	}
	
	//EMPLOYEE JPanel
	//panel to get job information based on above job list for Employee
	protected JPanel getEmpJobInfo()
	{
		//JPanel to set and retrieve job description
		pJobList = new JPanel();
		
		//subpanels of Job List Panel to receive jobs using getEmpJobs() method written below
		JPanel pEmpJobs = getEmpJobs();
		TitledBorder border = new TitledBorder("Your Jobs");
		pEmpJobs.setBackground(Color.white);
		pEmpJobs.setBorder(border);
		
		
		
		//setting Layout for pJobList Panel
		pJobList.setLayout(new GridLayout(2, 0));
		
		//adding subpanels to pJobList Panel main panel
		pJobList.add(pEmpJobs);
		pJobList.add(setEmpJobs());
		
		
		return pJobList;
	}
	
	//panel to receive jobs assigned to employee
	protected JPanel getEmpJobs()
	{
		//creating and setting panel for Emp job list
		JPanel pEmpJobs = new JPanel();
		pEmpJobs.setLayout(new GridLayout());
		
		//creating components for Employee Job List Table
		tEJobList = new JTable();
		//to retrieve columns heads and scroll bars adding JScrollPane
		JScrollPane scroll = new JScrollPane(tEJobList);
		scroll.setBounds(tEJobList.getBounds());
		
		//adding selected rows using getEmpJobList method of Database class to the table 
		tEJobList.setModel(DbUtils.resultSetToTableModel(getEmpJobList()));
		
		
		
		//hiding jobid value from JTable
		tEJobList.getColumnModel().getColumn(4).setMaxWidth(0);
		tEJobList.getColumnModel().getColumn(4).setMinWidth(0);
		tEJobList.getColumnModel().getColumn(4).setPreferredWidth(0);
		
		//adding JTable to JPAnel
		pEmpJobs.add(scroll);
		
		return pEmpJobs;
	}
	
	//panel to set information related to Job assigned to employee
	protected JPanel setEmpJobs()
	{
		//creating and setting Job Info panel
		JPanel pEmpJobInfo = new JPanel();
		TitledBorder border = new TitledBorder("Job Information");
		pEmpJobInfo.setBorder(border);
		
		//creating and setting components for Job Info panel
		JLabel lDate = new JLabel("Job Date: ");
		JTextField tfDate = new JTextField();
		tfDate.setEditable(false);
		tfDate.setColumns(10);
		JLabel lJob = new JLabel("Job Description: ");
		JTextArea tfJob = new JTextArea();
		Border tfBorder = BorderFactory.createLineBorder(Color.BLACK);
		tfJob.setBorder(tfBorder);
		tfJob.setEditable(false);
		tfJob.setLineWrap(true);
		tfJob.setColumns(15);
		tfJob.setRows(5);
		tfJob.setWrapStyleWord(true);
		JScrollPane scroll = new JScrollPane(tfJob);
		tfJob.setColumns(20);
		JLabel lStatus = new JLabel("Status: ");
		
		//creating ButtonGroup so that only one radio button is enabled
		ButtonGroup cbStatusButtons = new ButtonGroup();
		JRadioButton cbIP = new JRadioButton("In Progress");
		JRadioButton cbDone = new JRadioButton("Completed");
		JRadioButton cbCancel = new JRadioButton("Cancelled");
		//default Radio button value is set to In Progress
		cbIP.setSelected(true);
		cbIP.setActionCommand("In Progress");
		cbDone.setActionCommand("Completed");
		cbCancel.setActionCommand("Cancelled");
		
		
		//adding radio buttons to the group
		cbStatusButtons.add(cbIP);
		cbStatusButtons.add(cbDone);
		cbStatusButtons.add(cbCancel);
		
		JLabel lStartTime = new JLabel("Start Time (HH:MM:SS): ");
		JTextField tfStartTime = new JTextField();
		tfStartTime.setColumns(12);
		JLabel lEndTime = new JLabel("End Time (HH:MM:SS): ");
		JTextField tfEndTime = new JTextField();
		tfEndTime.setColumns(12);
		JLabel lRemarks = new JLabel("Remarks: ");
		JTextArea tfRemarks = new JTextArea();
		tfRemarks.setBorder(tfBorder);
		tfRemarks.setLineWrap(true);
		tfRemarks.setWrapStyleWord(true);
		tfRemarks.setColumns(15);
		tfRemarks.setRows(5);
		JScrollPane scrollR = new JScrollPane(tfRemarks);
		tfRemarks.setColumns(25);
		//creating labels if user enters incorrect input
		JLabel lSTError = new JLabel("Incorrect input for start time");
		JLabel lETError = new JLabel("Incorrect input for end time");
		JLabel lTError = new JLabel("End time is less than Start time");
		lSTError.setForeground(Color.RED);
		lETError.setForeground(Color.red);
		lTError.setForeground(Color.red);
		lSTError.setVisible(false);
		lETError.setVisible(false);
		lTError.setVisible(false);
		
		//buttons to submit or refresh job information
		JButton bSubmit = new JButton("Submit");
		JButton bRefresh = new JButton("Refresh");


		//Actions to be performed once button is clicked
		bRefresh.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						//updates table data when changes are made and shows it in JTable
						tEJobList.setModel(DbUtils.resultSetToTableModel(getEmpJobList()));	
						
						tfRemarks.setText("");
						tfStartTime.setText("");
						tfEndTime.setText("");
							
					}
				});
		
		//action for submit button
		bSubmit.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						boolean res=true;
						
						//setting setters if JobDist class based on inputs of textfields
						//checking if start time is as per time format or not 
						try
						{
							if(misc.checkTime(tfStartTime.getText()))
							{
								job1.setStarttime(tfStartTime.getText());
								lSTError.setVisible(false);
							}
							else
							{
								res = false;
								lSTError.setVisible(true);
								throw new RuntimeException();
							}
							
						}
						catch(RuntimeException evt)
						{
							res = false;
							lSTError.setVisible(true);
						}
						
						//checking if end time is as per time format or not
						try
						{
							if(misc.checkTime(tfEndTime.getText()))
							{
								job1.setEndtime(tfEndTime.getText());
								lETError.setVisible(false);
							}
							else
							{
								res = false;
								lETError.setVisible(true);
								throw new RuntimeException();
							}
							
						}
						catch(RuntimeException evt)
						{
							res = false;
							lETError.setVisible(true);
						}

						
						//checking is end time is less than start time
						if(LocalTime.parse(job1.getEndtime()).isBefore(LocalTime.parse(job1.getStarttime())))
						{
							res = false;
							if(LocalTime.parse(job1.getEndtime()).toString() != null)
								lTError.setVisible(true);
						}
						else
						{
							lTError.setVisible(false);
						}
			
						if(res)
						{
							
							
							job1.setRemarks(tfRemarks.getText());
							job1.setStatus(cbStatusButtons.getSelection().getActionCommand());
							lSTError.setVisible(false);
							lETError.setVisible(false);
							updateEmpJobStatus();
							
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Oops something went wrong");
						}
						
						
					}
				});
		
		//Action to be performed if row from JTable is selected
		//ListSelectionListener is to fetch data regrading particular row that was selected
		tEJobList.getSelectionModel().addListSelectionListener(new ListSelectionListener()
				{
					public void valueChanged(ListSelectionEvent e)
					{
						/*when you use DbUtils.resultSetToTableModel() row selection of tEJobList goes to -1 making it show ArrayIndexOutBounds
						 *Exception this checking if rowCount is -1 is necessary. If its -1 then blank return is made making program take no action
						 *avoiding ArrayOutOfBoudns Exception by not setting textFields 
						*/
						int row = tEJobList.getSelectedRow();	
						if(row == -1)
						{
							return;
						}
						
						tfDate.setText(tEJobList.getValueAt(tEJobList.getSelectedRow(), 0).toString());
						tfJob.setText(tEJobList.getValueAt(tEJobList.getSelectedRow(), 1). toString());
						
						//setting job description JobDist class fields to get correct start and end time from JTable
						job1.setJob(tEJobList.getValueAt(tEJobList.getSelectedRow(), 1). toString());
						
						//set jobid as per row selected remember jobid column's width is set to 0 doesnt mean its remove thus column
						//index is still working
						job1.setJobid(Integer.parseInt(tEJobList.getValueAt(tEJobList.getSelectedRow(), 4).toString()));
						
						
						//settext of remarks field to null if value is null
						try
						{
							tfRemarks.setText(tEJobList.getValueAt(tEJobList.getSelectedRow(), 2).toString());
							
							//setting remarks textfield to enable if status is in progress else it will be disabled
							if((tEJobList.getValueAt(tEJobList.getSelectedRow(), 3).toString()).equals("In Progress"))
								tfRemarks.setEditable(true);
							else
								tfRemarks.setEditable(false);
						}
						catch(NullPointerException evt)
						{
							tfRemarks.setText("");
							tfRemarks.setEditable(true);
						}
						
						//setting value for starttime and endtime based on row selected and status of job
						try
						{
							//if status is not equals to in progress then setEnabled is set to false
							setTime(job1.getJob());  									
							//setting time from fetched records of Database class into the textfield
							{
								tfStartTime.setText(job1.getStarttime());
								
								
								System.out.println(job1.getStarttime());
								
								tfStartTime.setEnabled(false);
							
								tfEndTime.setText(job1.getEndtime());
								tfEndTime.setEnabled(false);
							}
							if((tEJobList.getValueAt(tEJobList.getSelectedRow(), 3).toString()).equals("In Progress"))
							{
								tfStartTime.setText(job1.getStarttime());
								tfStartTime.setEnabled(true);
								
								tfEndTime.setText(job1.getEndtime());
								tfEndTime.setEnabled(true);	
							}
							else
							{
								tfStartTime.setText(job1.getStarttime());
								tfEndTime.setText(job1.getEndtime());
								
								tfStartTime.setEnabled(false);
								tfEndTime.setEnabled(false);
							}
						}
						catch(NullPointerException evt)
						{
							tfStartTime.setText("");
							tfEndTime.setText("");
							tfStartTime.setEnabled(true);
							tfEndTime.setEnabled(true);
						}
								
						//setting value of status radio button group depending on JTAble data for job status
						try
						{
							String value = tEJobList.getValueAt(tEJobList.getSelectedRow(), 3).toString();
							
							//check if value does exists as per above statement all radio buttons are disabled
							cbIP.setEnabled(false);
							cbDone.setEnabled(false);
							cbCancel.setEnabled(false);
							
							//if value of status is null or In Progress then radio buttons are activated
							if(value.equalsIgnoreCase("null") || value.equals("In Progress"))
							{
								cbIP.setEnabled(true);
								cbDone.setEnabled(true);
								cbCancel.setEnabled(true);
								//setting radio button for In Progresss as checked if value in Jtable is In Progress or null
								cbIP.setSelected(true);
							}
							
							//setting radio button for Completed as checked if value in Jtable is Completed
							if(value.equals("Completed"))
								cbDone.setSelected(true);
							
							//setting radio button for Cancelled as checked if value in Jtable is Cancelled
							if(value.equals("Cancelled"))
								cbCancel.setSelected(true);
								
						}
						catch(NullPointerException evt)
						{
							//if value of status is null then radio buttons are enabled else they are disabled
							cbIP.setEnabled(true);
							cbDone.setEnabled(true);
							cbCancel.setEnabled(true);	
						}
							
					}
				});
		
		//positioning components created above into the JPanel
		SpringLayout sLayout = new SpringLayout();
		sLayout.putConstraint(SpringLayout.NORTH, lDate, 30, SpringLayout.NORTH, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.WEST, lDate, 20, SpringLayout.WEST, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.NORTH, tfDate, 30, SpringLayout.NORTH, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.WEST, tfDate, 90, SpringLayout.WEST, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.NORTH, lJob, 70, SpringLayout.NORTH, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.WEST, lJob, 20, SpringLayout.WEST, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.NORTH, tfJob, 90, SpringLayout.NORTH, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.WEST, tfJob, 20, SpringLayout.WEST, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.NORTH, lStatus, 180, SpringLayout.NORTH, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.WEST, lStatus, 20, SpringLayout.WEST, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.NORTH, cbIP, 200, SpringLayout.NORTH, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.WEST, cbIP, 20, SpringLayout.WEST, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.NORTH, cbDone, 220, SpringLayout.NORTH, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.WEST, cbDone, 20, SpringLayout.WEST, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.NORTH, cbCancel, 240, SpringLayout.NORTH, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.WEST, cbCancel, 20, SpringLayout.WEST, pEmpJobInfo);
		
		sLayout.putConstraint(SpringLayout.NORTH, lStartTime, 30, SpringLayout.NORTH, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.EAST, lStartTime, -210, SpringLayout.EAST, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.NORTH, tfStartTime, 30, SpringLayout.NORTH, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.EAST, tfStartTime, -65, SpringLayout.EAST, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.NORTH, lEndTime, 100, SpringLayout.NORTH, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.EAST, lEndTime, -215, SpringLayout.EAST, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.NORTH, tfEndTime, 100, SpringLayout.NORTH, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.EAST, tfEndTime, -65, SpringLayout.EAST, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.NORTH, lRemarks, 160, SpringLayout.NORTH, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.EAST, lRemarks, -285, SpringLayout.EAST, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.NORTH, tfRemarks, 180, SpringLayout.NORTH, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.EAST, tfRemarks, -65, SpringLayout.EAST, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.NORTH, lSTError, 55, SpringLayout.NORTH, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.EAST, lSTError, -40, SpringLayout.EAST, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.NORTH, lETError, 125, SpringLayout.NORTH, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.EAST, lETError, -45, SpringLayout.EAST, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.NORTH, lTError, 125, SpringLayout.NORTH, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.EAST, lTError, -25, SpringLayout.EAST, pEmpJobInfo);
		
		sLayout.putConstraint(SpringLayout.NORTH, bSubmit, 290, SpringLayout.NORTH, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.WEST, bSubmit, 300, SpringLayout.WEST, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.NORTH, bRefresh, 290, SpringLayout.NORTH, pEmpJobInfo);
		sLayout.putConstraint(SpringLayout.EAST, bRefresh, -300, SpringLayout.EAST, pEmpJobInfo);
		
		
		//adding positioned component into JPanel
		pEmpJobInfo.add(lDate);
		pEmpJobInfo.add(tfDate);
		pEmpJobInfo.add(lJob);
		pEmpJobInfo.add(tfJob);
		pEmpJobInfo.add(lStatus);
		pEmpJobInfo.add(cbIP);
		pEmpJobInfo.add(cbDone);
		pEmpJobInfo.add(cbCancel);
		pEmpJobInfo.add(lStartTime);
		pEmpJobInfo.add(tfStartTime);
		pEmpJobInfo.add(lEndTime);
		pEmpJobInfo.add(tfEndTime);
		pEmpJobInfo.add(lRemarks);
		pEmpJobInfo.add(tfRemarks);
		pEmpJobInfo.add(bSubmit);
		pEmpJobInfo.add(bRefresh);
		pEmpJobInfo.add(lSTError);
		pEmpJobInfo.add(lETError);
		pEmpJobInfo.add(lTError);
		
		//setting layout for JPanel
		pEmpJobInfo.setLayout(sLayout);
		
		
		
		return pEmpJobInfo;
	}

	//method to set Add Employee Panel
	protected JPanel addEmp()
	{
		//creating panle
		pAddEmp = new JPanel();
		
		//creating and setting layout
		SpringLayout sLayout = new SpringLayout();
		
		//components needed to add new employee
		JLabel lName = new JLabel("Name: ");
		JTextField tfName = new JTextField();
		JLabel lEmpid = new JLabel("Employee ID: ");
		JTextField tfEmpid = new JTextField();
		JLabel lPassword = new JLabel("Password: ");
		JTextField tfPassword = new JTextField();
		JLabel lAddress = new JLabel("Address: ");
		JTextArea tfAddress = new JTextArea();
		JLabel lContact = new JLabel("Contact: ");
		JTextField tfContact = new JTextField();
		JLabel lEmailid = new JLabel("Email ID: ");
		JTextField tfEmailid = new JTextField();
		JLabel lDesignation = new JLabel("Designation: ");
		JTextField tfDesignation = new JTextField();
		JLabel lManagerid = new JLabel("Manager ID: ");
		JTextField tfManagerid = new JTextField();
		
		tfName.setColumns(15);
		tfEmpid.setColumns(15);
		tfPassword.setColumns(15);
		
		//setting up textarea for address
		tfAddress.setColumns(15);
		Border border = BorderFactory.createLineBorder(Color.black);
		tfAddress.setBorder(border);
		tfAddress.setWrapStyleWord(true);
		tfAddress.setLineWrap(true);
		tfAddress.setColumns(15);
		tfAddress.setRows(5);
		tfAddress.setBackground(Color.white);
		JScrollPane scPane = new JScrollPane(tfAddress);
		scPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		tfContact.setColumns(15);
		tfEmailid.setColumns(15);
		tfDesignation.setColumns(15);
		tfManagerid.setColumns(15);
		
		
		JButton bSubmit = new JButton("Submit");
		JButton bReset = new JButton("Reset");
		
		//positioning components in the JPanel
		sLayout.putConstraint(SpringLayout.NORTH, lName, 20, SpringLayout.NORTH, pAddEmp);
		sLayout.putConstraint(SpringLayout.WEST, lName, 250, SpringLayout.WEST, pAddEmp);
		sLayout.putConstraint(SpringLayout.NORTH, lEmpid, 50, SpringLayout.NORTH, pAddEmp);
		sLayout.putConstraint(SpringLayout.WEST, lEmpid, 250, SpringLayout.WEST, pAddEmp);
		sLayout.putConstraint(SpringLayout.NORTH, lPassword,80, SpringLayout.NORTH, pAddEmp);
		sLayout.putConstraint(SpringLayout.WEST, lPassword, 250, SpringLayout.WEST, pAddEmp);
		sLayout.putConstraint(SpringLayout.NORTH, lAddress, 110, SpringLayout.NORTH, pAddEmp);
		sLayout.putConstraint(SpringLayout.WEST, lAddress, 250, SpringLayout.WEST, pAddEmp);
		sLayout.putConstraint(SpringLayout.NORTH, lContact, 210, SpringLayout.NORTH, pAddEmp);
		sLayout.putConstraint(SpringLayout.WEST, lContact, 250, SpringLayout.WEST, pAddEmp);
		sLayout.putConstraint(SpringLayout.NORTH, lEmailid, 240, SpringLayout.NORTH, pAddEmp);
		sLayout.putConstraint(SpringLayout.WEST, lEmailid, 250, SpringLayout.WEST, pAddEmp);
		sLayout.putConstraint(SpringLayout.NORTH, lDesignation, 270, SpringLayout.NORTH, pAddEmp);
		sLayout.putConstraint(SpringLayout.WEST, lDesignation, 250, SpringLayout.WEST, pAddEmp);
		sLayout.putConstraint(SpringLayout.NORTH, lManagerid, 300, SpringLayout.NORTH, pAddEmp);
		sLayout.putConstraint(SpringLayout.WEST, lManagerid, 250, SpringLayout.WEST, pAddEmp);
		
		sLayout.putConstraint(SpringLayout.NORTH, tfName, 20, SpringLayout.NORTH, pAddEmp);
		sLayout.putConstraint(SpringLayout.EAST, tfName, -250, SpringLayout.EAST, pAddEmp);
		
		sLayout.putConstraint(SpringLayout.NORTH, tfEmpid, 50, SpringLayout.NORTH, pAddEmp);
		sLayout.putConstraint(SpringLayout.EAST, tfEmpid, -250, SpringLayout.EAST, pAddEmp);
		sLayout.putConstraint(SpringLayout.NORTH, tfPassword, 80, SpringLayout.NORTH, pAddEmp);
		sLayout.putConstraint(SpringLayout.EAST, tfPassword, -250, SpringLayout.EAST, pAddEmp);
		sLayout.putConstraint(SpringLayout.NORTH, scPane, 110, SpringLayout.NORTH, pAddEmp);
		sLayout.putConstraint(SpringLayout.EAST, scPane, -250, SpringLayout.EAST, pAddEmp);
		sLayout.putConstraint(SpringLayout.NORTH, tfContact, 210, SpringLayout.NORTH, pAddEmp);
		sLayout.putConstraint(SpringLayout.EAST, tfContact, -250, SpringLayout.EAST, pAddEmp);
		sLayout.putConstraint(SpringLayout.NORTH, tfEmailid, 240, SpringLayout.NORTH, pAddEmp);
		sLayout.putConstraint(SpringLayout.EAST, tfEmailid, -250, SpringLayout.EAST, pAddEmp);
		sLayout.putConstraint(SpringLayout.NORTH, tfDesignation, 270, SpringLayout.NORTH, pAddEmp);
		sLayout.putConstraint(SpringLayout.EAST, tfDesignation, -250, SpringLayout.EAST, pAddEmp);
		sLayout.putConstraint(SpringLayout.NORTH, tfManagerid, 300, SpringLayout.NORTH, pAddEmp);
		sLayout.putConstraint(SpringLayout.EAST, tfManagerid, -250, SpringLayout.EAST, pAddEmp);
		
		sLayout.putConstraint(SpringLayout.NORTH, bSubmit, 400, SpringLayout.NORTH, pAddEmp);
		sLayout.putConstraint(SpringLayout.WEST, bSubmit, 300, SpringLayout.WEST, pAddEmp);
		sLayout.putConstraint(SpringLayout.NORTH, bReset, 400, SpringLayout.NORTH, pAddEmp);
		sLayout.putConstraint(SpringLayout.EAST, bReset, -300, SpringLayout.EAST, pAddEmp);
		
		//adding positioned components into JPanel
		pAddEmp.add(lName);
		pAddEmp.add(tfName);
		pAddEmp.add(lEmpid);
		pAddEmp.add(tfEmpid);
		pAddEmp.add(lPassword);
		pAddEmp.add(tfPassword);
		pAddEmp.add(lAddress);
		pAddEmp.add(scPane);
		pAddEmp.add(lContact);
		pAddEmp.add(tfContact);
		pAddEmp.add(lEmailid);
		pAddEmp.add(tfEmailid);
		pAddEmp.add(lDesignation);
		pAddEmp.add(tfDesignation);
		pAddEmp.add(lManagerid);
		pAddEmp.add(tfManagerid);
		pAddEmp.add(bSubmit);
		pAddEmp.add(bReset);
		
		//actions performed by JButtons save and reset
		bSubmit.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						//setting up empInfo variables as per textfields 
						employee1.setEmpid(tfEmpid.getText());
						employee1.setName(tfName.getText());
						employee1.setPassword(tfPassword.getText());
						employee1.setContact(Long.parseLong(tfContact.getText()));
						employee1.setAddress(tfAddress.getText());
						employee1.setEmailid(tfEmailid.getText());
						employee1.setManagerid(tfManagerid.getText());
						employee1.setDesignation(tfDesignation.getText());
						
						//running method from database class to add employee
						addEmployee();
					}
				});
		
		bReset.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e)
					{
						tfName.setText("");
						tfEmpid.setText("");
						tfPassword.setText("");
						tfAddress.setText("");
						tfContact.setText("");
						tfEmailid.setText("");
						tfManagerid.setText("");
						tfDesignation.setText("");
					}
				});
		
		
		//setting layout to JPanel
		pAddEmp.setLayout(sLayout);
		
		return pAddEmp;
	}

}


