package HomeLayouts;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;

public class About extends JPanel
{

	JPanel pHeading,pAboutOptions;
	JLabel lTitle,lDescription,lUserManual,lChangeLog,lAboutDeveloper;
	String sTitle,sDescription,sUserManual,sChangeLog,sAboutDeveloper;
	
	public About() 
	{
	
		Font common = new Font(null,Font.BOLD,12);
		Font fTitle = new Font(null,Font.BOLD,15);
		Font fSubTitle = new Font(null,Font.BOLD,12);
		
		//Setting Panel Properties
		this.setLayout(new FlowLayout(FlowLayout.CENTER));

		sTitle = new String("<html><body><center><b>"
				+ "<font color=red size=5>Android All In One Toolkit</font>"
				+ "<br><br><font color=gray size=3>Stable Release Version 2.0"
				+ "<br>Build 2013.12.25</font><br>"
				+ "</b></center></body></html>");
		lTitle = new JLabel(sTitle);
		
		sDescription = new String("<html><body>"
				+ "<font color=black size=3><b>Changelog</b>:<br><br>"
				+ "Stable release Version : 2.0<br>"
				+ "1. No Need To Setup ADB Manually , Now BuiltIn To Toolkit<br>"
				+ "2. Added Option To Save Pull Files On Desire Location<br>"
				+ "3. Added Option For Push File as well as Folder As per your Requirement<br>"
				+ "4. Tweaks Added - Normal Reboot and Reboot Recovery<br>"
				+ "5. Redesigned in JAVA<br>"
				+ "6. Bug Fixes Using Multithreading<br>"
				+ "7. More Smoother Experience<br>"
				+ "8. Enhanced User Interface <br><br><br></font>"
				+ "</body></html>");
		
		lDescription = new JLabel(sDescription);
		
		pHeading = new JPanel();
		pHeading.setLayout(new GridLayout(3,1,2,2));
		pHeading.add(lTitle);
		pHeading.add(lDescription);

		
		
		//Adding To Main Panel
		this.add(pHeading);
	}
}
