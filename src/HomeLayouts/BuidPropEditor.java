package HomeLayouts;

import javax.swing.*;
import javax.swing.border.*;
import Managers.ProcessManager;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class BuidPropEditor extends JPanel implements ActionListener
{
	//Swing Component Declaration
	JPanel pTextEditor,pControlPanel,pControlButtons,pLog;
	JTextArea taBuildProp,taLog;
	JScrollPane spBuildProp;
	JLabel lSteps,lStepsList;
	JButton bPullBuildProp,bOpenInEditor,bSaveBuildProp,bPushBuildProp;
	
	//Variables
	String sSteps = "<html><body>"
			+ "Steps:<br>"
			+ "1. Pull Build.prop from Device<br>"
			+ "2. Open In Editor<br>"
			+ "3. Edit Build Properties<br>"
			+ "4. Save Build.prop<br>"
			+ "5. Push To Your Phone<br><br>";
	
	ProcessManager pmProcess = new ProcessManager(); 
	
	
	public BuidPropEditor() 
	{
		
		Font common = new Font(null,Font.BOLD,12);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		

		taBuildProp = new JTextArea(21,40);
		spBuildProp = new JScrollPane(taBuildProp); spBuildProp.setBorder(BorderFactory.createTitledBorder("Build.prop")); 
		
		lSteps = new JLabel(sSteps); lSteps.setFont(common); lSteps.setForeground(Color.red);
		bPullBuildProp = new JButton("Pull Build.prop From Device");  bPullBuildProp.setFont(common); bPullBuildProp.addActionListener(this);
		bOpenInEditor = new JButton("Open In Editor"); bOpenInEditor.setFont(common);  bOpenInEditor.addActionListener(this);
		bSaveBuildProp = new JButton("Save Build.prop"); bSaveBuildProp.setFont(common); bSaveBuildProp.addActionListener(this);
		bPushBuildProp = new JButton("Push Build.prop to Device"); bPushBuildProp.setFont(common);
		taLog = new JTextArea(5,20);
		
		pControlButtons = new JPanel();
		pControlButtons.setLayout(new GridLayout(4,1,2,2));
		pControlButtons.add(bPullBuildProp);
		pControlButtons.add(bOpenInEditor);
		pControlButtons.add(bSaveBuildProp);
		pControlButtons.add(bPushBuildProp);
		
		pLog = new JPanel();
		pLog.setLayout(new FlowLayout());
		pLog.setBorder(BorderFactory.createTitledBorder("Log: "));
		pLog.add(taLog);
		
		pControlPanel = new JPanel();
		pControlPanel.setLayout(new GridLayout(3,1,2,2)); pControlPanel.setBorder(BorderFactory.createTitledBorder("Control Panel"));
		pControlPanel.add(lSteps);
		pControlPanel.add(pControlButtons);
		pControlPanel.add(pLog);
		
		this.add(spBuildProp);
		this.add(pControlPanel);
	}

	@Override
	public void actionPerformed(ActionEvent id) 
	{
		if(id.getSource()==bPullBuildProp)
		{
				try
				{
					pmProcess.PullBuildProp();
				}	
				catch(Exception e)
				{ 
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Device Is Not Connected","Error",JOptionPane.ERROR_MESSAGE);
				}
		}
		
		if(id.getSource()==bOpenInEditor)
		{
			try
			{
				taBuildProp.setText(pmProcess.OpenBuildProp());
			}
			catch(Exception e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "File Not Available","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if(id.getSource()==bSaveBuildProp)
		{
			try
			{
				System.out.println(taBuildProp.getText().toString());
				pmProcess.SaveBuildProp(taBuildProp.getText().toCharArray());
			}
			catch(Exception e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Saveing Error","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
}
