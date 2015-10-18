package Managers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class ProcessManager
{
	File dir = new File("tools");
	
	/**
	 * PullFromFile Method used for downloading files from device from refereed path to specified LocalPath Dorectory
	 * @param FileName
	 * @param LocalPath
	 * @throws Exception
	 */
	public void PullFromDevice(final String FileName, final String LocalPath) throws Exception
	{
		ProcessBuilder pb = new ProcessBuilder("cmd","/c","adb pull \""+FileName+"\" \""+LocalPath+"\"");
		pb.directory(dir);
		Process p = pb.start();
		p.waitFor();
	}
	
	public void PushToDevice(String FileName , String RemotePath) throws Exception
	{
		ProcessBuilder pb = new ProcessBuilder("cmd","/c","adb push \""+FileName+"\" \""+RemotePath+"\"");
		pb.directory(dir);
		Process p = pb.start();
		p.waitFor();
	}
	
	public void DeletePattern() throws Exception
	{
		
		
	}
	
	public void DeletePassword() throws Exception
	{
		
	}
	
	public void RebootNormal() throws Exception
	{
		ProcessBuilder pb = new ProcessBuilder("cmd","/c","adb reboot");
		pb.directory(dir);
		Process p = pb.start();
		p.waitFor();
	}
	
	public void RebootRecovery() throws Exception
	{
		ProcessBuilder pb = new ProcessBuilder("cmd","/c","adb reboot recovery");
		pb.directory(dir);
		Process p = pb.start();
		p.waitFor();
	}
	
	public void InstallAPK(String FileName) throws Exception
	{
		ProcessBuilder pb = new ProcessBuilder("cmd","/c","adb install \""+FileName+"\"");
		pb.directory(dir);
		Process p = pb.start();
		p.waitFor();
		JOptionPane.showMessageDialog(null, "Installed Successfully");
	}
	
	public void PullBuildProp() throws Exception
	{
		ProcessBuilder pb = new ProcessBuilder("cmd","/c","adb pull /system/build.prop");
		pb.directory(dir);
		Process p = pb.start();
		p.waitFor();
		
	}
	
	public String OpenBuildProp() throws Exception
	{
		String sBuildProp = null;
		File fBuildProp = new File("build.prop");
		BufferedReader brBuildPropReader = new BufferedReader(new FileReader(fBuildProp));
		while(brBuildPropReader.readLine()!=null)
		{
			sBuildProp += brBuildPropReader.readLine();
			sBuildProp +="\n";
		}

		return sBuildProp;
		
	}
	
	public void SaveBuildProp(char[] sBuildProp) throws Exception
	{
		File fBuildProp = new File("build.prop");
		BufferedWriter brBuildPropWriter = new BufferedWriter(new FileWriter(fBuildProp));
		brBuildPropWriter.write(sBuildProp);
		brBuildPropWriter.close();
	}
}

