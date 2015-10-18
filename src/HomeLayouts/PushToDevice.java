package HomeLayouts;
import Managers.ProcessManager;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class PushToDevice extends JPanel implements ActionListener
{
	//Swing Components
	JPanel pLocalCase,pProgressBar,pPushButton,pRemotePath,pLocalPath,pRemoteChoise;
	JLabel lRemotePath,lLocalPath;
	JButton bPush,bBrowse;
	JRadioButton rFile,rFolder,rDownloads,rMusic,rCustom,rVideos;
	JTextField tfLocalPath,tfRemotePath;
	ButtonGroup bgLocalPath,bgRemotePath;
	JProgressBar pbPush;
	JFileChooser fcLocalFile;
	int iLocalFile,iLocalCase=0;
	File fLocalFile;
	Dimension pbsPush;
	
	
	public PushToDevice() 
	{
		Font common = new Font(null,Font.BOLD,12);
		
		//Setting Panel Properties
		this.setLayout(new GridLayout(9,1,2,2));
		this.setBorder(BorderFactory.createTitledBorder(null,"Push Files To Android",TitledBorder.LEFT,Font.BOLD,common, Color.red));
		
		//Creating Swing Components
		lLocalPath = new JLabel("      1. Local Path: File / Folder On Your PC        ");
		rFile = new JRadioButton("File"); rFile.addActionListener(this);
		rFolder = new JRadioButton("Folder"); rFolder.addActionListener(this);
		bgLocalPath = new ButtonGroup();
		bgLocalPath.add(rFile); bgLocalPath.add(rFolder);
		tfLocalPath = new JTextField(25);
		bBrowse = new JButton(" "); bBrowse.addActionListener(this);
		lRemotePath = new JLabel("    2. Remote Path: File / Folder in Your Android Device ");
		rDownloads = new JRadioButton("Downloads"); rDownloads.addActionListener(this);
		rMusic = new JRadioButton("Music"); rMusic.addActionListener(this);
		rVideos = new JRadioButton("Videos"); rVideos.addActionListener(this);
		rCustom = new JRadioButton("Custom Path"); rCustom.addActionListener(this);
		bgRemotePath = new ButtonGroup();
		bgRemotePath.add(rDownloads); bgRemotePath.add(rMusic); bgRemotePath.add(rVideos); bgRemotePath.add(rCustom);
		tfRemotePath = new JTextField(30);
		bPush = new JButton("Push Files To Device"); bPush.addActionListener(this); bPush.setFont(common);
		pbPush = new JProgressBar(0,10); 
		pbsPush = pbPush.getPreferredSize(); pbsPush.width=220; pbsPush.height=22;
		pbPush.setPreferredSize(pbsPush);
		
		pLocalCase = new JPanel();
		pLocalCase.setLayout(new FlowLayout(FlowLayout.CENTER));
		pLocalCase.add(rFile); pLocalCase.add(rFolder);
		
		pLocalPath = new JPanel();
		pLocalPath.setLayout(new FlowLayout(FlowLayout.LEFT));
		pLocalPath.add(tfLocalPath); pLocalPath.add(bBrowse);
		
		pRemoteChoise = new JPanel();
		pRemoteChoise.setLayout(new GridLayout(2,2,2,2));
		pRemoteChoise.add(rDownloads); pRemoteChoise.add(rMusic);
		pRemoteChoise.add(rVideos); pRemoteChoise.add(rCustom);
		
		pRemotePath = new JPanel();
		pRemotePath.setLayout(new FlowLayout(FlowLayout.LEFT));
		pRemotePath.add(tfRemotePath);
		
		pPushButton = new JPanel();
		pPushButton.setLayout(new FlowLayout(FlowLayout.CENTER));
		pPushButton.add(bPush);
		
		pProgressBar = new JPanel();
		pProgressBar.setLayout(new FlowLayout(FlowLayout.CENTER));
		pProgressBar.add(pbPush);
		
		//Adding Components To Panel
		this.add(lLocalPath);
		this.add(pLocalCase);
		this.add(pLocalPath);
		this.add(lRemotePath);
		this.add(pRemoteChoise);
		this.add(pRemotePath);
		this.add(pPushButton);
		this.add(pProgressBar);
	}


	//Action Listeners
	public void actionPerformed(ActionEvent id) 
	{
		//Local File Selection Mode Radio Listener
		if(id.getSource()==rFile)	{	iLocalCase=1; tfLocalPath.setText("");	}
		if(id.getSource()==rFolder)	{	iLocalCase=2; tfLocalPath.setText("");	}
		
		//Browse Local File/Folder
		if(id.getSource()==bBrowse)
		{
			if(iLocalCase!=0)
			{
				fcLocalFile = new JFileChooser();
				if(iLocalCase==2)
				{
					fcLocalFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				}
				iLocalFile = fcLocalFile.showOpenDialog(this.getParent());
				if(iLocalFile==JFileChooser.APPROVE_OPTION)
				{
					fLocalFile = fcLocalFile.getSelectedFile();
					tfLocalPath.setText(fLocalFile.getAbsolutePath());
				}
				
			}
			else{	JOptionPane.showMessageDialog(null, "Select Either File Or Folder","Error",JOptionPane.ERROR_MESSAGE);	}
		}
		
		//Remote Location Selection
		if(id.getSource()==rDownloads)	{	tfRemotePath.setText("/sdcard/download");	}
		if(id.getSource()==rMusic)		{	tfRemotePath.setText("/sdcard/Music");		}
		if(id.getSource()==rVideos)		{	tfRemotePath.setText("/sdcard/Videos");		}
		if(id.getSource()==rCustom)		{	tfRemotePath.setText("");					}
		
		//Push To Device
		if(id.getSource()==bPush)
		{
			if(!tfRemotePath.getText().isEmpty()&&!tfLocalPath.getText().isEmpty())
			{
				final ProcessManager pm = new ProcessManager();
				
				Thread PushThread = new Thread()
				{
					public void run()
					{
						bBrowse.setEnabled(false);	bPush.setEnabled(false);
						pbPush.setValue(4);
						try
						{ 	
							pm.PushToDevice(tfLocalPath.getText(), tfRemotePath.getText());  
							pbPush.setValue(10);
							sleep(500);
							JOptionPane.showMessageDialog(null, "Completed");
							sleep(500);
							pbPush.setValue(0);
							tfLocalPath.setText(""); tfRemotePath.setText("");
							bBrowse.setEnabled(true);	bPush.setEnabled(true);
						}
						catch(Exception e){ e.printStackTrace();}
					}
				};
				PushThread.start();
				
			}
			else{	JOptionPane.showMessageDialog(null, "Select Local & Remote Path","Error",JOptionPane.ERROR_MESSAGE);}
			
		}
		
	}

}
