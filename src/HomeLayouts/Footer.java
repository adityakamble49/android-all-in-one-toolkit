package HomeLayouts;


import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Footer extends JPanel implements MouseListener
{
	JLabel lDeveloper,lEmail;
	
	public Footer() 
	{
		//Setting Panel Properties
		this.setLayout(new FlowLayout());
		
		//Creating Swing Components
		Font common = new Font(null,Font.BOLD,12);
		lDeveloper = new JLabel("Developer: Aditya Kamble                    "); 
		lDeveloper.setFont(common); lDeveloper.setForeground(Color.red); lDeveloper.addMouseListener(this);
		lDeveloper.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lEmail = new JLabel("Contact: adityakamble69@gmail.com"); lEmail.setFont(common); lEmail.setForeground(Color.blue);
		lEmail.addMouseListener(this);
		lEmail.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		//Adding Components To Panel
		this.setBorder(BorderFactory.createEtchedBorder());
		this.add(lDeveloper);
		this.add(lEmail);
		
	}


	public void mousePressed(MouseEvent id) 
	{
		if(id.getSource()==lDeveloper)
		{
			try {	Desktop.getDesktop().browse(new URL("http://forum.xda-developers.com/member.php?u=5269659").toURI());	} 
			catch (Exception e) {	e.printStackTrace();	}
			
		}
		if(id.getSource()==lEmail)
		{
			try {	Desktop.getDesktop().browse(new URL("mailto:adityakamble69@gmail.com").toURI());	} 
			catch (Exception e) {	e.printStackTrace();	}
		}
	}
	public void mouseReleased(MouseEvent arg0){}
	public void mouseClicked(MouseEvent arg0){}
	public void mouseEntered(MouseEvent arg0){}
	public void mouseExited(MouseEvent arg0){}
	
}
