package HomeLayouts;


import javax.swing.*;
import javax.swing.border.*;

import Managers.ProcessManager;

import java.awt.*;
import java.awt.event.*;

public class Tweaks extends JPanel implements ActionListener
{
	//Declaring Swing Components
	JPanel pDeletePwd,pReboot;
	JButton bDeletePattern,bDeletePin,bNorReboot,bRebootRec;
	
	public Tweaks() 
	{
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		Font ftHead = new Font(null,Font.BOLD,13);
		Font ftcommon = new Font(null,Font.BOLD,12);
		
		//Creating Swing Components
		bDeletePattern = new JButton("Delete Pattern"); bDeletePattern.setFont(ftcommon); bDeletePattern.addActionListener(this);
		bDeletePin = new JButton("Delete Pin/Password"); bDeletePin.setFont(ftcommon);
		bNorReboot = new JButton("Normal Reboot"); bNorReboot.setFont(ftcommon); bNorReboot.addActionListener(this);
		bRebootRec = new JButton("Reboot Recovery"); bRebootRec.setFont(ftcommon); bRebootRec.addActionListener(this);
		
		//Delete Password
		pDeletePwd = new JPanel(); pDeletePwd.setLayout(new GridLayout(2,1,5,5)); 
		pDeletePwd.setBorder(BorderFactory.createTitledBorder(null,"Delete Password",TitledBorder.LEFT,Font.BOLD,ftHead,Color.red));
		pDeletePwd.add(bDeletePattern);
		pDeletePwd.add(bDeletePin);
		
		//Reboot
		pReboot = new JPanel();  pReboot.setLayout(new GridLayout(2,1,5,5)); 
		pReboot.setBorder(BorderFactory.createTitledBorder(null,"Reboot",TitledBorder.LEFT,Font.BOLD,ftHead,Color.red));
		pReboot.add(bNorReboot);
		pReboot.add(bRebootRec);
		
		//Adding To Main Panel
		this.add(pDeletePwd);
		this.add(pReboot);
		
		
	}
	
	public void actionPerformed(ActionEvent id)
	{
		//Delete Pattern
		if(id.getSource()==bDeletePattern)
		{
			
		}
		
		//Normal Reboot
		if(id.getSource()==bNorReboot)
		{
			final ProcessManager pm = new ProcessManager();
			Thread RebootNormal = new Thread()
			{
				public void run()
				{
					try
					{
						JOptionPane.showMessageDialog(null, "Press 'Ok' To Begin & Wait Till Process Completes....");
						sleep(500);
						pm.RebootNormal();
						sleep(500);
						JOptionPane.showMessageDialog(null, "Process Completed");
					}
					catch(Exception e){}
				}
			};
			RebootNormal.start();
		}
		
		//Reboot Recovery
		if(id.getSource()==bRebootRec)
		{
			final ProcessManager pm = new ProcessManager();
			Thread RebootRecovery = new Thread()
			{
				public void run()
				{
					try
					{
						JOptionPane.showMessageDialog(null, "Press 'Ok' To Begin & Wait Till Process Completes....");
						sleep(500);
						pm.RebootRecovery();
						sleep(500);
						JOptionPane.showMessageDialog(null, "Process Completed");
					}
					catch(Exception e){}
				}
			};
			RebootRecovery.start();
		}
		
	}

}
