package SynergyGUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.event.*;

public class Test
{
	Test()
	{
		//creating and setting JFrame using javax.swing
		JFrame f1 = new JFrame("Synergy Employee Management System");
		f1.setBounds(600, 300, 400, 400);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f1.setResizable(false);
		f1.setLayout(null);
		
		
		
		//creating and setting JTextField
		JTextField tf1 = new JTextField();
		tf1.setBounds(10, 10, 200, 25);
		tf1.setVisible(true);
		
		//creating and setting JButton using javax.swing
		JButton b1 = new JButton("Click Me");
		b1.setBounds(50, 50, 100, 30);
		
		//creating action for button click me
		b1.addActionListener(new ActionListener(){
					//setting action performed once button is clicked
					public void actionPerformed(ActionEvent e)
					{
						tf1.setText("Hello");
					}
		});
		
		f1.add(b1);
		f1.add(tf1);
		
		f1.setVisible(true);
		
		
		
	}
	
	public static void main(String args[])
	{
		new Test();
	}
}
