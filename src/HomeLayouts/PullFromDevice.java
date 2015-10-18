package HomeLayouts;

import Managers.ProcessManager;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class PullFromDevice extends JPanel implements ActionListener
{
	//Swing Components
	JLabel lRemotePath,lLocalPath;
	JRadioButton rDownloads,rMusic,rVideo,rCustomPath;
	ButtonGroup bgPath;
	JPanel pPath,pLocalPath,pRemotePath,pProgressBar,pPullButton;
	JTextField tfPath,tfLocalPath;
	JButton bPull,bBrowse;
	JProgressBar pbPull;
	File fLocalPath;
	JFileChooser fcLocalPath;
	int iLocalPath;
	Dimension pbsPull;
	
	public PullFromDevice() 
	{
		Font common = new Font(null,Font.BOLD,12);
		
		//Setting Panel Properties
		this.setLayout(new GridLayout(6,1,2,2));
		this.setBorder(BorderFactory.createTitledBorder(null,"Pull Files From Android",TitledBorder.LEFT,Font.BOLD,common, Color.red));
		
		//Creating Swing Components
		lRemotePath = new JLabel("     Remote Path: File / Folder in Your Android Device    ");
		lLocalPath = new JLabel(" Local Path: ");
		rDownloads = new JRadioButton("Downloads"); rDownloads.addActionListener(this);
		rMusic = new JRadioButton("Music");  rMusic.addActionListener(this);
		rVideo = new JRadioButton("Video");  rVideo.addActionListener(this);
		rCustomPath = new JRadioButton("Custom Path");  rCustomPath.addActionListener(this);
		bgPath = new ButtonGroup(); 
		bgPath.add(rDownloads); bgPath.add(rMusic); bgPath.add(rVideo); bgPath.add(rCustomPath);
		tfPath = new JTextField(30);
		tfLocalPath = new JTextField(17);
		bPull = new JButton("Pull Files From Device"); bPull.setFont(common); bPull.addActionListener(this);
		bBrowse = new JButton(" "); bBrowse.setFont(common); bBrowse.addActionListener(this);
		pbPull = new JProgressBar(0,10); 
		pbsPull = pbPull.getPreferredSize(); pbsPull.width=220; pbsPull.height=22;
		pbPull.setPreferredSize(pbsPull);
		fcLocalPath = new JFileChooser(); 
		
		pPath = new JPanel(); pPath.setLayout(new GridLayout(2,2,2,2));
		pPath.add(rDownloads); pPath.add(rMusic);
		pPath.add(rVideo); pPath.add(rCustomPath);
		
		pRemotePath = new JPanel(); pRemotePath.setLayout(new FlowLayout(FlowLayout.CENTER));
		pRemotePath.add(tfPath);
		
		pLocalPath = new JPanel(); pLocalPath.setLayout(new FlowLayout(FlowLayout.CENTER));
		pLocalPath.add(lLocalPath);
		pLocalPath.add(tfLocalPath);
		pLocalPath.add(bBrowse);
		
		pProgressBar = new JPanel(); pProgressBar.setLayout(new FlowLayout(FlowLayout.CENTER));
		pProgressBar.add(pbPull);
		
		pPullButton = new JPanel(); pPullButton.setLayout(new FlowLayout(FlowLayout.CENTER));
		pPullButton.add(bPull);
		
		//Adding Components To Panel
		this.add(lRemotePath);
		this.add(pPath);
		this.add(pRemotePath);
		this.add(pLocalPath);
		this.add(pPullButton);
		this.add(pProgressBar);
		
	}

	public void actionPerformed(ActionEvent id) 
	{
		if(id.getSource()==rDownloads)	{	tfPath.setText("/sdcard/download");	}
		if(id.getSource()==rMusic)		{	tfPath.setText("/sdcard/Music");	}
		if(id.getSource()==rVideo)		{	tfPath.setText("/sdcard/Video");	}
		if(id.getSource()==rCustomPath)	{	tfPath.setText("");					}
		
		if(id.getSource()==bBrowse)
		{
			fcLocalPath.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			iLocalPath=fcLocalPath.showOpenDialog(this.getParent());
			if(iLocalPath==JFileChooser.APPROVE_OPTION)
			{
				fLocalPath = fcLocalPath.getSelectedFile();
				tfLocalPath.setText(fLocalPath.getAbsolutePath());
			}
		}
		
		if(id.getSource()==bPull) 
		{
			if(!tfPath.getText().isEmpty()&&!tfLocalPath.getText().isEmpty())
			{
				final ProcessManager pm = new ProcessManager();
				Thread PullThread = new Thread()
				{
					public void run()
					{
						bBrowse.setEnabled(false);	bPull.setEnabled(false);
						pbPull.setValue(4);
						try
						{ 	
							pm.PullFromDevice(tfPath.getText(), tfLocalPath.getText());  
							pbPull.setValue(10);
							sleep(500);
							JOptionPane.showMessageDialog(null, "Completed");
							sleep(500);
							pbPull.setValue(0);
							tfLocalPath.setText(""); tfPath.setText("");
							bBrowse.setEnabled(true);	bPull.setEnabled(true);
							
						}
						catch(Exception e){ e.printStackTrace();}
					}
				};
				PullThread.start();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Specify Remote And Local Path","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		


	}

}
