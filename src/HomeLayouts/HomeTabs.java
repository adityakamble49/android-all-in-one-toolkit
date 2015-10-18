package HomeLayouts;


import javax.swing.*;

public class HomeTabs extends JPanel
{
	//Declaring Swing Components
	JTabbedPane tbMain;
	
	public HomeTabs() 
	{
		tbMain = new JTabbedPane();
		tbMain.addTab("Data Transfer", new DataTransfer());
		tbMain.addTab("Tweaks", new Tweaks());
		tbMain.addTab("APK Manager", new ApkInstaller());
		//tbMain.addTab("Build Prop Editor", new BuidPropEditor());
		tbMain.addTab("About", new About());
		
		//Adding Components
		add(tbMain);
		
	}

}
