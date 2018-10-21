import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.hsqldb.Server;
import org.hsqldb.result.Result;

import java.sql.Statement;
public class InformationDB { // This class operate database called "Information"
	public void createDatabase() { //create a database as well as table
		Server hsqlServer = null;
		Connection connection = null;
		ResultSet rs = null;
		hsqlServer = new Server();
		hsqlServer.setLogWriter(null);
		hsqlServer.setSilent(true);
		hsqlServer.setDatabaseName(0, "Information"); // set database name as "Information"
		hsqlServer.setDatabasePath(0, "file:MYDB");
		hsqlServer.start();
		// making a connection, create a table named "personalInfo"
		try {
			Class.forName("org.hsqldb.jdbcDriver");  //use driver to manage
			connection = DriverManager.getConnection("jdbc:hsqldb:Information", "sa", "123");
			connection.prepareStatement("drop table personalInfo if exists;").execute(); // drop table "personalInfo"
			connection.prepareStatement("create table personalInfo (name varchar(20) not null,age integer);").execute();
			connection.prepareStatement("insert into personalInfo (name,age)" + "values ('Alice',40);").execute();
			connection.prepareStatement("insert into personalInfo (name,age)" + "values ('Bob',40);").execute();
			connection.prepareStatement("insert into personalInfo (name,age)" + "values ('Cindy',10);").execute();
			connection.prepareStatement("insert into personalInfo (name,age)" + "values ('Don',20);").execute();
			 //query from the database  
			rs = connection.prepareStatement("select name,age from personalInfo;").executeQuery();
			//list multiply rows in the database
			System.out.println("---Database---");
			while(rs.next()) {
			System.out.println(String.format("Name: %1s  Age:%1d",rs.getString(1),rs.getInt(2)));
			}
			connection.commit();
		} catch (SQLException e2) {
			e2.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
		// end of stub code for in/out stub
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertDatabase(String name,int age) { //this method insert into database
		//connect to the database   
		Connection connection = null;
		PreparedStatement sql;
		ResultSet res;
		try {
			//registering the HSQLDB JDBC driver
			Class.forName("org.hsqldb.jdbcDriver");
			//creating the connection with HSQLDB
			connection = DriverManager.getConnection("jdbc:hsqldb:Information", "sa", "123");
			//need use wildcard, because I will enter parameter into this method
			sql=connection.prepareStatement("insert into personalInfo (name,age)" + "values (?,?);");
			sql.setString(1, name);
			sql.setInt(2, age);
			sql.execute();
			connection.commit();
			System.out.println("Rows inserted successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public String listDatabase() {   // list from database
		Connection connection=null;
		String s="";
		try {   
			connection = DriverManager.getConnection("jdbc:hsqldb:Information", "sa", "123");
			ResultSet rs = null;
			rs = connection.prepareStatement("select name,age from personalInfo;").executeQuery();
			System.out.println("------------Database-------------");
			while(rs.next()) {  //define list format and print it out
			System.out.println(String.format("Name: %1s Age:%1d",rs.getString(1),rs.getInt(2)));
			s+=String.format("Name: %1s Age:%1d",rs.getString(1),rs.getInt(2))+"     [read from database]"+"\n";
			}
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	public String searchDatabase(String name) {  // search from database, enter the name to search
		String s="";
		
		//connect to the database
		Connection connection=null;
		PreparedStatement sql;
		ResultSet res=null;  //initial the value of res
		try {   
			//regiest to the JDBC driver
			Class.forName("org.hsqldb.jdbcDriver");
			//connect with HSQLDB
			connection = DriverManager.getConnection("jdbc:hsqldb:Information", "sa", "123");
			//use wildcard
			sql=connection.prepareStatement("select * from personalInfo where name=?;");
			sql.setString(1, name);
			res=sql.executeQuery();
			System.out.println("-----------Search-From-Database----------");
			
			while(res.next()) {  //define list format and print it out
			System.out.println(String.format("Name: %1s Age:%1d",res.getString(1),res.getInt(2)));
			s+=String.format("Name: %1s Age:%1d",res.getString(1),res.getInt(2));
			}
		//	connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}
}
