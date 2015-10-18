package HomeLayouts;


import javax.swing.*;
import java.awt.*;

public class DataTransfer extends JPanel
{
	
	//Declaring Swing Components
	JPanel pPullFile,pPushFile;
	
	public DataTransfer() 
	{
		this.setLayout(new GridLayout(1,2,2,2));
		
		//Creating Swing Components
		pPullFile = new JPanel(); pPullFile.setLayout(new FlowLayout()); 
		pPullFile.add(new PullFromDevice());
		
		pPushFile = new JPanel(); pPushFile.setLayout(new FlowLayout());
		pPushFile.add(new PushToDevice());
		
		//Adding Components To Panel
		this.add(pPullFile);
		this.add(pPushFile);
		
		this.setVisible(true);
		
	}
	
	
}
