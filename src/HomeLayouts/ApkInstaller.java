package HomeLayouts;


import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import Managers.ProcessManager;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ApkInstaller extends JPanel implements ActionListener
{
	//Declaring Swing Components
	JPanel pInstall,pProgress;
	JLabel lFilePath,lStatus;
	JTextField tfFilePath;
	JButton bBrowse,bInstall;
	JFileChooser fcApkChooser;
	File fApkFile;
	int iFileChoose;
	JProgressBar pbApkInstall;
	FileNameExtensionFilter ftApkFilter;
	Dimension dmApkInstall;
	
		public ApkInstaller() 
		{
			Font ftHead = new Font(null,Font.BOLD,13);
			Font ftcommon = new Font(null,Font.BOLD,12);
			this.setLayout(new BorderLayout());
			this.setBorder(BorderFactory.createTitledBorder(null,"Install Apk To Android",TitledBorder.LEFT,Font.BOLD,ftcommon, Color.red));

			
			//Creating Swing Components
			lFilePath = new JLabel("APK File Path: "); lFilePath.setFont(ftcommon);
			tfFilePath = new JTextField(30); tfFilePath.setPreferredSize(new Dimension(300,20));
			bBrowse = new JButton("Browse"); bBrowse.setFont(ftcommon); bBrowse.addActionListener(this);
			bInstall = new JButton("Install APK"); bInstall.setFont(ftcommon); bInstall.addActionListener(this);
			fcApkChooser = new JFileChooser(); fcApkChooser.setName("Choose APK File");
			lStatus = new JLabel("Ready : "); lStatus.setFont(ftcommon);
			pbApkInstall = new JProgressBar(0,10); 
			dmApkInstall = pbApkInstall.getPreferredSize();
			dmApkInstall.width=480; dmApkInstall.height=22;
			pbApkInstall.setPreferredSize(dmApkInstall);
			
			pInstall = new JPanel();
			pInstall.setLayout(new FlowLayout(FlowLayout.LEFT));
			pInstall.add(lFilePath);	pInstall.add(tfFilePath);	pInstall.add(bBrowse);	pInstall.add(bInstall);
			
			pProgress = new JPanel();
			pProgress.setLayout(new FlowLayout(FlowLayout.LEFT));
			pProgress.add(lStatus);
			pProgress.add(pbApkInstall);
			
			//Adding To Main Panel
			this.add(pInstall, BorderLayout.NORTH);
			this.add(pProgress, BorderLayout.CENTER);

			
			
		}

		public void actionPerformed(ActionEvent id)
		{
			if(id.getSource()==bBrowse)
			{
				ftApkFilter = new FileNameExtensionFilter("APK Files", "apk");
				fcApkChooser.setFileFilter(ftApkFilter);
				iFileChoose=fcApkChooser.showOpenDialog(this.getParent());
				if(iFileChoose==JFileChooser.APPROVE_OPTION)
				{
					fApkFile = fcApkChooser.getSelectedFile();
					tfFilePath.setText(fApkFile.getAbsolutePath());
				}
			}
			
			if(id.getSource()==bInstall)
			{
				if(!tfFilePath.getText().isEmpty())
				{
					final ProcessManager pm = new ProcessManager();
					Thread ApkInstaller = new Thread()
					{
						public void run()
						{
							bBrowse.setEnabled(false);	bInstall.setEnabled(false);
							pbApkInstall.setValue(4);
							lStatus.setText("Installing..");
							try
							{ 	
								pm.InstallAPK(tfFilePath.getText());
								pbApkInstall.setValue(10);
								sleep(500);
								lStatus.setText("Done");
								sleep(500);
								pbApkInstall.setValue(0);
								lStatus.setText("Ready");
								tfFilePath.setText(""); tfFilePath.setText("");
								bBrowse.setEnabled(true);	bInstall.setEnabled(true);
								
							}
							catch (Exception e) {	e.printStackTrace();	}
						}
					};
					ApkInstaller.start();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Choose Apk File First","Error",JOptionPane.ERROR_MESSAGE);
				}

			}
			
		}
}
