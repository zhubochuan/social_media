import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

import javafx.application.Application;

public class Driver {
	Run run = new Run();
	InformationDB db=new InformationDB();
	Driver() {
		run.file();// create the file to store info
		run.initialAccount();// add people into network in the beginning
		db.createDatabase(); //create a database and create a table
	}

	public void Menu() {// this is main page
		// print out menu
		System.out.println("================================");
		System.out.println("MiniNet Menu");
		System.out.println();
		System.out.println("================================");
		System.out.println();
		System.out.println("1.List everyone");
		// select by name and show person profile,then you can update profile or delete
		// person
		System.out.println("2.Select a person");
		// you can connect two person
		System.out.println("3.Are these two direct friends?");
		System.out.println("4.Add a person into network");
		System.out.println("5.help");
		System.out.println("6.Exit");
		System.out.println("0.Delete account");
		System.out.println();
		System.out.println();
		System.out.println("Enter an option:__");
	}

	// after choose menu
	public void choose() {
		this.Menu();
		Scanner s = new Scanner(System.in);
		int num = s.nextInt();
		switch (num) {
		case 1:// list all people
			System.out.println("Here are all of the people:");
			System.out.println("-----------Memory-----------");
			run.listArray();       //list from memory, it is temporary 
			run.input("people.txt");      //list from people.txt file
			run.input("relations.txt");  //list from relations.txt file
			db.listDatabase();      //list from database
			this.choose();
			break;
		case 2:// select person, search function
			System.out.println("enter the name you want to  search");
			Scanner s5 = new Scanner(System.in);
			String name3 = s5.nextLine();
			run.search(name3).getinfo();
			System.out.println("Children:");
			run.getAccByName(name3).listChildren();
			System.out.println("Friends:");
			run.getAccByName(name3).listFriends();
			System.out.println("Parents:");
			run.getAccByName(name3).listParents();
			System.out.println("colleague:");
			run.getAccByName(name3).listColleagues();
			System.out.println("classmates:");
			run.getAccByName(name3).listClassmates();
			System.out.println("couple: ");
			run.getAccByName(name3).listCouple();
			//read from database
			db.searchDatabase(name3);
			this.choose2();

			break;
		case 3:// are they friends?
			System.out.println("Enter the first person name: ");
			Scanner sa = new Scanner(System.in);
			String af = sa.nextLine();
			System.out.println("Enter the second person name: ");
			Scanner sa2 = new Scanner(System.in);
			String af2 = sa.nextLine();
			this.judgeFriends(af, af2);
			// there are a and b, if there are friends, they will exist in each other friend
			this.choose();
			break;
		case 4:// add people into network

			System.out.println("Enter name:__");
			Scanner s1 = new Scanner(System.in);
			String name = s1.nextLine();
			run.createAccount(name);
			System.out.println("account is created.");

			System.out.println("enter age:_"); // will judge the age
			Scanner s2 = new Scanner(System.in);
			int age = s2.nextInt();
			db.insertDatabase(name,age);
			try {
				run.getAccByName(name).setAge(age);
				this.addAge(name); //age exception method

			} catch (NoSuchAgeException e) {
				System.out.println(e);
				this.choose();
			}
			System.out.println("age is " + run.getAccByName(name).getAge());
			System.out.println("set gender:_");
			Scanner s4 = new Scanner(System.in);
			String gender = s4.nextLine();
			run.getAccByName(name).setGender(gender);
			System.out.println("set your status:__");
			Scanner s3 = new Scanner(System.in);
			String status = s3.nextLine();
			run.getAccByName(name).setStatus(status);
			System.out.println("status is: " + run.getAccByName(name).getStatus());
			System.out.println("your account name is: " + run.getAccByName(name).getName());
			System.out.println("your account age is: " + run.getAccByName(name).getAge());
			System.out.println("your account gender is: " + run.getAccByName(name).getGender());
			System.out.println("your account status is: " +run.getAccByName(name).getStatus());
			System.out.println();
			// judge the age
			if (run.getAccByName(name).getAge() < 16) {
				if (run.getAccByName(name).getAge() <= 2) {
					System.out.println("you age is under the 2 years old");
					System.out.println("you cannot add any friend");
					System.out.println("your must linked with two adults");
					// add two adults
					System.out.println("These are all of people:");
					run.listArray();
					System.out.println();
					System.out.println("Enter your name:_");
					Scanner sp = new Scanner(System.in);
					String ap = sp.nextLine();
					System.out.println("enter the Mom name:_");
					Scanner sp2 = new Scanner(System.in);
					String ap2 = sp2.nextLine();
					System.out.println("enter the Dad name:_");
					Scanner sp3 = new Scanner(System.in);
					String ap3 = sp3.nextLine();
					try {
						this.addParents(ap, ap2, ap3);
						run.getAccByName(ap).setParents(ap2); // add Mom name
						run.getAccByName(ap).setParents(ap3); // add the Dad name
						String message=run.getAccByName(name).getinfo();
						run.output("people.txt", message);
						System.out.println("now, " + ap2 + " and " + ap3 + " is the parents of " + ap);
						this.choose2();
					} catch (NoAvaliableException e) {
						System.out.println(e);
					} catch (NoParentException e) {
						System.out.println(e);
					}
					String message1=run.getAccByName(name).getinfo();
					run.output("people.txt", message1);
					this.choose();
				} else {
					System.out.println("your age is under the 16 years old");
					System.out.println("your must linked with two adults");
					// add two adults
					System.out.println("These are all of people:");
					run.listArray();
					System.out.println();
					System.out.println("Enter your name:_");
					Scanner sp = new Scanner(System.in);
					String ap = sp.nextLine();
					System.out.println("enter the Mom name:_");
					Scanner sp2 = new Scanner(System.in);
					String ap2 = sp2.nextLine();
					System.out.println("enter the Dad name:_");
					Scanner sp3 = new Scanner(System.in);
					String ap3 = sp3.nextLine();
					try {
						this.addParents(ap, ap2, ap3);
						run.getAccByName(ap).setParents(ap2); // add Mom name
						run.getAccByName(ap).setParents(ap3); // add the Dad name
						System.out.println("now, " + ap2 + " and " + ap3 + " is the parents of " + ap);
						String message=run.getAccByName(name).getinfo();
						run.output("people.txt", message);
						this.choose2();
					} catch (NoAvaliableException e) {
						System.out.println(e);
					} catch (NoParentException e) {
						System.out.println(e);
					}
					String message=run.getAccByName(name).getinfo();
					run.output("people.txt", message);
					this.choose();
				}
			} else {
				System.out.println("These are all of people:");
				run.listArray();
				String message=run.getAccByName(name).getinfo();
				run.output("people.txt", message);
				this.choose2();// further decision
				break;
			}
		case 5:// help
			System.out.println("you need choose an option,you can select or add person");
			System.out.println("you need create account in different name");
			this.choose();
			break;
		case 6:
			System.exit(0);
			break;
		case 0:
			System.out.println("enter the name you want to delete");
			Scanner s0 = new Scanner(System.in);
			String name0 = s0.nextLine();
			Account acc = new Run().getAccByName(name0);
			acc.setName(null);
			acc = null;
			System.out.println("acocunt has been deleted");
			this.choose();
			break;
		default:
			System.out.println("error, you need enter the number");
			this.choose();
			break;
		}
	}

	// add friends or family,colleague, classmats
	public void choose2() // submenu
	{

		System.out.println("enter your choice:");
		System.out.println("1.add friends");
		System.out.println("2.add child");
		System.out.println("3.add parents");
		System.out.println("4.list friends");
		System.out.println("5.add colleague");
		System.out.println("6.add classmates");
		System.out.println("7.add couple");
		System.out.println("8.back");
		Scanner s = new Scanner(System.in);
		int num = s.nextInt();
		switch (num) {
		case 1:// add friends
			System.out.println("enter your name: ");
			Scanner sf = new Scanner(System.in);
			String af = sf.nextLine();
			System.out.println("enter friend's name: ");
			Scanner sf2 = new Scanner(System.in);
			String af2 = sf2.nextLine();
			try {
				this.addFriends(af, af2);
				run.getAccByName(af).setFriends(af2); // need record into "relations" file
				run.getAccByName(af2).setFriends(af);
				// record both of names into "relations.txt" as friends
				run.outputRelations(af + "	" + af2 + "	"+"[friends]"+"\r\n");//record friends into txt

				this.choose();
			} catch (TooYoungException e) {
				System.out.println(e);
			} catch (NotToBeFriendsException e) {
				System.out.println(e);
			}
			this.choose();
			break;
		case 2:// add child
			this.addChildren();
			this.choose2();
			break;
		case 3:// add parent
			System.out.println();
			System.out.println("Enter your name:_");
			Scanner sp = new Scanner(System.in);
			String ap = sp.nextLine();
			System.out.println("enter the Mom name:_");
			Scanner sp2 = new Scanner(System.in);
			String ap2 = sp2.nextLine();
			System.out.println("enter the Dad name:_");
			Scanner sp3 = new Scanner(System.in);
			String ap3 = sp3.nextLine();
			try {
				this.addParents(ap, ap2, ap3);
				run.outputRelations(ap2 + "	" + ap3 + "	"+"[parent]"+"\r\n");
				this.choose2();
			} catch (NoAvaliableException e) {
				System.out.println(e);
			} catch (NoParentException e) {
				System.out.println(e);
			}
			this.choose();
			break;
		case 4:// list his or her friends
			System.out.println("enter person name and show his friend list:");
			Scanner sf0 = new Scanner(System.in);
			String nf = sf0.nextLine();
			System.out.println("--friend list--");
			run.getAccByName(nf).listFriends();
			this.choose();
			break;
		case 5: // add colleague
			System.out.println("enter your name: ");
			Scanner sco1 = new Scanner(System.in);
			String scol1 = sco1.nextLine();
			System.out.println("enter colleague's name: ");
			Scanner sco2 = new Scanner(System.in);
			String scol2 = sco2.nextLine();
			try {
				this.addColleague(scol1, scol2);
				run.getAccByName(scol1).setColleague(scol2);
				run.getAccByName(scol2).setColleague(scol1);
				run.outputRelations(scol1 + "	" + scol2 + "	"+"[colleague]"+"\r\n");
				System.out.println("now you are colleagues");

			} catch (NotToBeColleaguesException e) {
				System.out.println(e);
			}
			this.choose();
			break;
		case 6: // add classmates
			System.out.println("enter your name: ");
			Scanner cla1 = new Scanner(System.in);
			String cl = cla1.nextLine();

			System.out.println("enter classmate's name: ");
			Scanner cla2 = new Scanner(System.in);
			String cl2 = cla2.nextLine();
			try {
				this.addClassmates(cl, cl2);
				run.getAccByName(cl).setClassmats(cl2);
				run.getAccByName(cl2).setClassmats(cl);
				run.outputRelations(cl + "	" + cl2 + "	"+"[classmates]"+"\r\n");
				System.out.println("now, you are classmates");
			} catch (NotToBeClassmatesException e) {
				System.out.println(e);
			}
			this.choose();
			break;
		case 7: // add couple
			System.out.println("enter your name: ");
			Scanner sc = new Scanner(System.in);
			String ac = sc.nextLine();
			System.out.println("enter couple's name: ");
			Scanner sc2 = new Scanner(System.in);
			String ac2 = sc2.nextLine();
			try {
				this.addCouple(ac, ac2);
				run.getAccByName(ac).setCouple(ac2);
				run.getAccByName(ac2).setCouple(ac);
				System.out.println("now," + ac + " and " + ac2 + " are couple");
				this.choose();
			} catch (NotToBeCoupledException e) {
				System.out.println(e);
			}
			this.choose();
			break;
		case 8:// back
			this.choose();
			break;
		default:
			System.out.println("error,try again");
			this.choose2();
			break;
		}
	}

	// in this function we need to judge object's age
	public void addFriends(String name1, String name2) throws TooYoungException, NotToBeFriendsException// add
																										// friends,include
																										// TooYoungException
	{
		int age1 = run.getAccByName(name1).getAge();
		int age2 = run.getAccByName(name2).getAge();

		if (age1 <= 2 || age2 <= 2) {
			throw new TooYoungException("you cannot make a friend with young child");
		}
		if ((age1 > 16 && age2 <= 16) || (age2 > 16 && age1 <= 16) || (Math.abs(age1 - age2) > 3) && (age1 <= 16)
				|| (age2 <= 16)) {
			throw new NotToBeFriendsException("a child cannot be friend with adult, two child gap must less than 3");
		}

	}

	public void addCouple(String name1, String name2) throws NotToBeCoupledException {
		int age1 = run.getAccByName(name1).getAge();
		int age2 = run.getAccByName(name2).getAge();

		if (age1 <= 16 || age2 <= 16) {
			throw new NotToBeCoupledException("not to be couple exception");
		}

	}

	// add parents
	public void addParents(String name1, String name2, String name3) throws NoAvaliableException, NoParentException {
		if (name2.equals("") || name3.equals("")) // no parents exception
		{
			throw new NoParentException("No parents Exception");
		}

		int age1 = run.getAccByName(name1).getAge();
		int age2 = run.getAccByName(name2).getAge();
		int age3 = run.getAccByName(name3).getAge();

		Account a1 = run.getAccByName(name1);
		Account a2 = run.getAccByName(name2);
		Account a3 = run.getAccByName(name3);

		if ((a1.listParents() != null) || (a2.listParents() != null) || (a3.listParents() != null)) {
			throw new NoAvaliableException("no avaliable Exception");
		}
		new Run().getAccByName(name1).setParents(name2); // add Mom name
		new Run().getAccByName(name1).setParents(name3); // add the Dad name
		System.out.println("now, " + name2 + " and " + name3 + " is the parents of " + name1);

	}

	// judge age exception
	public void addAge(String name) throws NoSuchAgeException {
		int age = run.getAccByName(name).getAge();
		if (age < 0 || age > 150) {
			throw new NoSuchAgeException("no such age exception");
		}
	}

	// add children
	public void addChildren() {
		System.out.println("enter your name:__");
		Scanner sc = new Scanner(System.in);
		String ac = sc.nextLine();
		System.out.println("enter children name:__");
		Scanner sc2 = new Scanner(System.in);
		String ac2 = sc2.nextLine();
		run.getAccByName(ac).setChildren(ac2); // add children
		System.out.println("now " + ac2 + " is the child of " + ac);
	}

	public void judgeFriends(String name1, String name2) { //friends array,first people name, second people name
		String []friends=run.getAccByName(name1).getFriends();
		for (int i = 0; i < friends.length; i++) {
			if (friends[i] == null) {
				break;
			}
			if (friends[i].equals(name2)) {
				System.out.println("they are direct friends");
				this.choose();
				break;
			}
		} 
		// avoid null pointer exception
		System.out.println("they aren't direct friends");
		this.choose();
	}

	public void addColleague(String name1, String name2) throws NotToBeColleaguesException// add colleagues
	{
		int age1 = run.getAccByName(name1).getAge();
		int age2 = run.getAccByName(name2).getAge();

		if (age1 <= 16 || age2 <= 16) {
			throw new NotToBeColleaguesException("not to be colleagues exception");
		}
	}

	// add classmates
	public void addClassmates(String name1, String name2) throws NotToBeClassmatesException {
		int age1 = run.getAccByName(name1).getAge();
		int age2 = run.getAccByName(name2).getAge();
		if (age1 <= 2 || age2 <= 2) {
			throw new NotToBeClassmatesException("not to be classmatesException");
		}
	}
}
