import java.io.File;

public  class Account implements Method{	//set Account info in this class
	private String name;
	private int age;
	private String gender;
	private String status=null;
	private String []couple=new String[1]; //create an array to store couple
	private String[] friends=new String[100];	//create an array to store friends
	private String[] parents=new String[100];	//create an array to store parents
	private String[] children=new String[100];  //create an array to store children
	private String[] colleague=new String[100]; //create an array to store colleague
	private String[] classmates=new String[100]; //create an array to store classmates
	
	public Account()	
	{
		
	}
	//get friends[]
	public String[] getFriends() {
	for(int i=0;i<friends.length;i++)
	{
		if(friends[i]!=null)
		{
		System.out.println(friends[i]);
		}
	}
		return friends;
	}
	
	
	//set friends
	public void setFriends(String input)	
	{
		for(int i=0;i<friends.length;i++)
		{
		if(friends[i]==null)
		{
		this.friends[i]=new String(input);
		break;
		}
		}
	}
	
	//set parents
	public void setParents(String input)
	{
		for(int i=0;i<parents.length;i++)
		{
			if(parents[i]==null)
			{
				this.parents[i]=new String(input);
				break;
			}
		}
	}
	
	//set children 
	public void setChildren(String input)
	{
		for(int i=0;i<children.length;i++)
		{
			if(children[i]==null)
			{
				this.children[i]=new String(input);
				break;
			}
		}
	}
	//set colleagues
	public void setColleague(String input)
	{
		for(int i=0;i<colleague.length;i++)
		{
			if(colleague[i]==null)
			{
				this.colleague[i]=new String(input);
				break;
			}
		}
	}
	//set classmates
	public void setClassmats(String input)
	{
		for(int i=0;i<classmates.length;i++)
		{
			if(classmates[i]==null)
			{
				this.classmates[i]=new String(input);
				break;
			}
		}
	}
	
	
	//list all parents of the selected person
	public String listParents()
	{
		String s="";
		for(int i=0;i<parents.length;i++)
		{
			if(parents[i]!=null)
			{
				s+=parents[i]+" ";
				System.out.println(parents[i]);
			}
		}
		return s;
	}
	
	//list all children
	public String listChildren()
	{
		String s="";
		for(int i=0;i<children.length;i++)
		{
			if(children[i]!=null)
			{
				s+=children[i]+" ";
				System.out.println(children[i]);
			}
		}
		return s;
	}
	//list colleagues
	public String listColleagues()
	{
		String s="";
		for(int i=0;i<colleague.length;i++)
		{
			if(colleague[i]!=null)
			{
				s+=colleague[i]+" ";
				System.out.println(colleague[i]);
			}
		}
		return s;
	}
	//list classmates
	public String listClassmates()
	{
		String s="";
		for(int i=0;i<classmates.length;i++)
		{
			if(classmates[i]!=null)
			{
				s+=classmates[i]+" ";
				System.out.println(classmates[i]);
			}
		}
		return s;
	}
	//list all friends
		public String listFriends()
		{
			String s="";
			for(int i=0;i<friends.length;i++)
			{
				if(friends[i]!=null)
				{
				s+=friends[i]+" ";
				System.out.println(friends[i]);
				}
			}
			return s;
		}
	public void setName(String name)	//set name
	{
		this.name=name;
	}
	public String getName()	//get account name
	{
		return name;
	}
	public void setAge(int age)
	{
		this.age=age;
	}

	public void setCouple(String input) 
	{
		for(int i=0;i<couple.length;i++)
		{
			if(couple[i]==null)
			{
				this.couple[i]=new String(input);
				break;
			}
		}
		
	}
	public String listCouple()
	{
		String s="";
		for(int i=0;i<couple.length;i++)
		{
			if(couple[i]!=null)
			{
				s+=couple[i]+" ";
				System.out.println(couple[i]);
			}
		}
		return s;
	}
	public int getAge()
	{
		return age;
	}
	public String  getinfo()	//get account info and details
	{
	//	new Run().initialAccount();
		String info="";
		
		System.out.println("---- profile----");
		System.out.println("Name: "+name);
		System.out.println("Age: "+age);
		System.out.println("Gender: "+gender);
		System.out.println("Status: "+status);
		
		info+="Name: "+name+"	"+"\n"+"Age: "+age+"	"+"\n"+"Gender: "+gender+"	"+"\n"+"Status: "+status; 
		return info; //info will be display on the GUI text area
	}
	public void setStatus(String status)	//set up status
	{
		this.status=status;
	}
	public String getStatus()
	{
		return status;
	}
	public void setGender(String gender)
	{
		this.gender=gender;
	}
	public String getGender()
	{
		return gender;
	}
	
}
