import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.util.Scanner;

import com.sun.javafx.collections.SetAdapterChange;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class Run  {
	private Account[] accounts = new Account[100];// attribute 100 spaces in the array
	
	// create a file named "people.txt" to store the people info
	public void file() {
		File file1 = new File("people.txt");// create the file "people.txt"
		if (file1.exists()) // if file exist, delete it can create a new one
		{
			file1.delete();
			System.out.println("old file has been deleted");
			try {
				file1.createNewFile();
				System.out.println("new file \"people.txt\" has been created");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				file1.createNewFile();
				System.out.println("The file \"people.txt\" has been created");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		File file2 = new File("relations.txt");// create file "relations" to store the relationship
		if (file2.exists()) {
			file2.delete();
			System.out.println("old file has been deleted");
			try {
				file2.createNewFile();
				System.out.println("new file \"relations.txt\" has been created");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				file2.createNewFile();
				System.out.println("The file \"relations.txt\" has been created");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	// create input and output stream

	public void output(String filename, String message) // output stream method
	{
		FileWriter writer = null;
		try {
			writer = new FileWriter(filename, true); // don't cover the previous info
			writer.write(message + "		");

			writer.close(); // close the writer stream
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void changeLine(String filename) {// change the line when output

		FileWriter writer = null;
		try {
			writer = new FileWriter(filename, true);
			writer.write("\r\n"); // change a new line
			writer.close(); // close the writer stream
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void input(String filename) // input stream method, from file
	{
		System.out.println("------------------"+filename+"-------------------");
		Scanner input = null;
		String line = null;  //used for command line
		try {
			input = new Scanner(new FileInputStream(filename));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (input.hasNextLine()) {
			line = input.nextLine();
			System.out.println(line);
		}
		input.close(); // close the input stream
	}
	
	public String input2(String filename) // input stream method, from file,used for gui
	{
		Scanner input2 = null;
		String line2=""; //used for gui
		try {
			input2 = new Scanner(new FileInputStream(filename));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (input2.hasNextLine()) {
			line2+=input2.nextLine()+"      [Read from "+filename+" file]"+"\n";
		}
		input2.close(); // close the input stream
		return line2;
	}

	// output to the "relations.txt" file
	public void outputRelations(String message) // use randomaccessFile because it need be updated
	{
		try {
			RandomAccessFile randomfile = new RandomAccessFile("relations.txt", "rw");
			randomfile.seek(randomfile.length()); // move pointer to the end of file
			message+="\r\n";
			randomfile.write(message.getBytes());
			randomfile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void initialAccount() {
		// initial account
		// There are 4 accounts, Alice, Bob, Cindy and Don
		// Alice and Bob have a child named Cindy; Don is Alice and Bobs' common friend
		this.createAccount("Alice"); // Alice name
		this.getAccByName("Alice").setAge(40); // Alice is 40 years old
		this.getAccByName("Alice").setGender("female"); // set Alice as female
		this.getAccByName("Alice").setStatus("\"I like dancing\""); // Alice like dancing
		String message1=this.getAccByName("Alice").getinfo();
		this.output("people.txt", message1);
		this.changeLine("people.txt"); // change a new line
		System.out.println("add Alice");

		this.createAccount("Bob"); // Bob name
		this.getAccByName("Bob").setAge(40); // Bob is 40 years old
		this.getAccByName("Bob").setGender("male"); // Bob is a man
		this.getAccByName("Bob").setStatus("\"I am a chief\"");// Bob is a chief
		String message2=this.getAccByName("Bob").getinfo();
		this.output("people.txt", message2);
		this.changeLine("people.txt"); // change a new line
		System.out.println("add Bob");

		this.createAccount("Cindy"); // Cindy name
		this.getAccByName("Cindy").setAge(10); // cindy is 10 years old
		this.getAccByName("Cindy").setGender("female"); // Cindy is a girl
		this.getAccByName("Cindy").setStatus("\"I feel sad\""); // Cindy is a sad girl
		String message3=this.getAccByName("Cindy").getinfo();
		this.output("people.txt", message3);
		this.changeLine("people.txt"); // change a new line
		System.out.println("add Cindy");

		this.createAccount("Don");// Don name
		this.getAccByName("Don").setAge(20);// Don is 20 years old
		this.getAccByName("Don").setGender("male"); // Don is a man
		this.getAccByName("Don").setStatus("\"I want to eat lunch\"");// Don want to eat lunch
		String message4=this.getAccByName("Don").getinfo();
		this.output("people.txt", message4);
		this.changeLine("people.txt"); // change a new line
		System.out.println("add Don");

		this.getAccByName("Cindy").setParents("Alice");
		this.getAccByName("Cindy").setParents("Bob");
		this.getAccByName("Alice").setFriends("Don"); // Alice and Don are friends
		this.getAccByName("Bob").setFriends("Don"); // Bob and Don are friends
		this.getAccByName("Don").setFriends("Bob");
		this.getAccByName("Alice").setChildren("Cindy");
		this.getAccByName("Bob").setChildren("Cindy");
		this.getAccByName("Alice").setCouple("Bob"); // Alice and Bob are couple
		this.getAccByName("Bob").setCouple("Alice");

		
		// output the relations above into file, and this relation must can be updated
		String AliceAndBobAndDon = "Alice" + "	" + "Bob" + "	" + "Don" + "	" + "[friends]";
		this.outputRelations(AliceAndBobAndDon); // Bob and Don, Alice are friends
		// this.changeLine("relations.txt");
		String AliceAndBob = "Alice" + "	" + "Bob" + "	" + "[couple]";
		this.outputRelations(AliceAndBob); // Alice and Bob record couple
		// this.changeLine("relations.txt");
		String parentsAliceAndBob = "Alice" + "	" + "Bob" + "	" + "[parent]";
		this.outputRelations(parentsAliceAndBob);
		// this.changeLine("relations.txt");
		String line3 = "[classmates]";
		this.outputRelations(line3);
		// this.changeLine("relations.txt");
		String line4 = "[colleagues]";
		this.outputRelations(line4);
	}

	public Account getAccByName(String name) {  
		for (Account acc : accounts) {
			if (acc != null && name.equals(acc.getName())) {
				return acc;
			}
		}
		return null;
	}

	// list all of elements in the array
	public String listArray() {  //this method just list account name, don't show details, details will list in the search method
		String s="";
		for (Account acc2 : accounts) { //list on the command line
			if (acc2 != null) {
			s+=acc2.getName()+"      ";
			System.out.println(acc2.getName()); 
			}
		}
		s+="     [read from Memory]";
		return s;
	}

	// create account
	public boolean createAccount(String name) {
		// check if it is exist
		Account acc = getAccByName(name);
		if(acc==null) {
			acc=new Account();
			acc.setName(name);
			for (int i = 0; i < accounts.length; i++) {
				if (accounts[i] == null) {
					accounts[i] = acc;
					return true;
				}
			}
			// if account is full, there is an alarm
			return false;
		}else {
				System.out.println("account exist");
				System.exit(0);
				}
	return false;
	}

	// search
	public Account search(String name) { //search function
	//	this.initialAccount();
		try {
			for (Account acc : accounts) {
				if (name.equals(acc.getName())) {
					System.out.println("name is found");
					return acc;
				}
			}
		} catch (Exception e) {
			System.out.println("error! name out found");
		}
		return null;
	}
	// judge age exception
		public void addAge(String name) throws NoSuchAgeException {
			int age = this.getAccByName(name).getAge();
			if (age < 0 || age > 150) {
				throw new NoSuchAgeException("no such age exception");
			}
		}
		// add parents
		public void addParents(String name1, String name2, String name3) throws NoAvaliableException, NoParentException {
			if (name2.equals("") || name3.equals("")) // no parents exception
			{
				throw new NoParentException("No parents Exception");
			}

			int age1 = this.getAccByName(name1).getAge();
			int age2 = this.getAccByName(name2).getAge();
			int age3 = this.getAccByName(name3).getAge();

			Account a1 = this.getAccByName(name1);
			Account a2 = this.getAccByName(name2);
			Account a3 = this.getAccByName(name3);

			if ((a1.listParents() != null) || (a2.listParents() != null) || (a3.listParents() != null)) {
				throw new NoAvaliableException("no avaliable Exception");
			}
			new Run().getAccByName(name1).setParents(name2); // add Mom name
			new Run().getAccByName(name1).setParents(name3); // add the Dad name
			System.out.println("now, " + name2 + " and " + name3 + " is the parents of " + name1);

		}
		// we need to judge friend's age
		public void addFriends(String name1, String name2) throws TooYoungException, NotToBeFriendsException
																											
		{
			int age1 = this.getAccByName(name1).getAge();
			int age2 = this.getAccByName(name2).getAge();

			if (age1 <= 2 || age2 <= 2) {
				throw new TooYoungException("you cannot make a friend with young child");
			}
			if ((age1 > 16 && age2 <= 16) || (age2 > 16 && age1 <= 16) || (Math.abs(age1 - age2) > 3) && (age1 <= 16)
					|| (age2 <= 16)) {
				throw new NotToBeFriendsException("a child cannot be friend with adult, two child gap must less than 3");
			}

		}
		//add colleague
		public void addColleague(String name1, String name2) throws NotToBeColleaguesException// add colleagues
		{
			int age1 = this.getAccByName(name1).getAge();
			int age2 = this.getAccByName(name2).getAge();

			if (age1 <= 16 || age2 <= 16) {
				throw new NotToBeColleaguesException("not to be colleagues exception");
			}
		}
		// add classmates
		public void addClassmates(String name1, String name2) throws NotToBeClassmatesException {
			int age1 = this.getAccByName(name1).getAge();
			int age2 = this.getAccByName(name2).getAge();
			if (age1 <= 2 || age2 <= 2) {
				throw new NotToBeClassmatesException("not to be classmatesException");
			}
		}
		//add couple
		public void addCouple(String name1, String name2) throws NotToBeCoupledException {
			int age1 = this.getAccByName(name1).getAge();
			int age2 = this.getAccByName(name2).getAge();
			if (age1 <= 16 || age2 <= 16) {
				throw new NotToBeCoupledException("not to be couple exception");
			}
		}
}
