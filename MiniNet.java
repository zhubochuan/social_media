import javafx.application.Application;

public class MiniNet {	//run in here
	public static void main(String[] args)	//start point
	{
		Driver driver=new Driver();  //run some initial methods
		Application.launch(GUI.class,args);  //run GUI
		driver.choose();  //display the menu in the command line
	}
}
