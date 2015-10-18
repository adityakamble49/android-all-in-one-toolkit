import javax.swing.UIManager;

public class AndroidAllInOneToolkit 
{

	public static void main(String[] args) throws Exception
	{
		//Setting UserInterface 
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		//Calling Homescreen
		HomeScreen hs = new HomeScreen("Android All In One Toolkit");
		
	}

}
