import javax.swing.*;

import HomeLayouts.Footer;
import HomeLayouts.HomeTabs;

import java.awt.*;


public class HomeScreen extends JFrame
{
	//Swing Components
	
	//Variables
	
	//Frame Constructor
	public HomeScreen(String Title) 
	{
		super(Title);
		setSize(600,510);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screen.width/2-this.getSize().width/2,screen.height/2-this.getSize().height/2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout());

		//Adding Swing Components To JFrame
		add(new HomeTabs(),BorderLayout.WEST);
		add(new Footer(),BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	
}

