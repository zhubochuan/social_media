import java.util.Scanner;

import com.sun.javafx.collections.SetAdapterChange;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI extends Application { // design GUI in this class
	// GUI
	Run r = new Run();
	InformationDB d = new InformationDB();

	public GUI() {
		this.r.file();// create the file to store info
		this.r.initialAccount();
		// d.createDatabase(); //create a database and create a table
	}

	public void start(Stage primaryStage) throws Exception {
		// design the GUI
		GridPane grid = new GridPane();// use grid layout
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10); // set gap
		grid.setVgap(10); // set gap
		grid.setPadding(new Insets(25, 25, 25, 25)); // set the gap
		
		Text scenetitle = new Text("Welcome"); // this will be displayed on the menu page
		scenetitle.setFont(Font.font("Tahoma", FontWeight.BLACK, 30)); // set the font
		grid.add(scenetitle, 0, 0, 2, 1); // set the position of the "scenetitle"
		Text notification=new Text("<----Add connection in here!");
		// set buttons on the pane
		Button b1 = new Button("List everyone");
		Button b2 = new Button("Select a person");
		Button b3 = new Button("Are these two direct friends?");
		Button b4 = new Button("Add a person into network");
		Button b5 = new Button("help");
		Button b6 = new Button("Exit");
		Button b7 = new Button("Delete account");

		// set button action
		// b1 is used to list everyone
		b1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {// list everyone
				// create a page to show the result after click the button
				Stage stage2 = new Stage(); // set stage
				GridPane grid = new GridPane();// use grid layout
				Text listTitle = new Text("All People"); // show the result on text area
				listTitle.setFont(Font.font("Tahoma", FontWeight.BLACK, 15)); // set the font
				grid.add(listTitle, 0, 1);

				TextArea toDisplay = new TextArea();
				toDisplay.setWrapText(true);

				toDisplay.setText(r.listArray()); // list account, read from Memory

				TextArea toDisplay2 = new TextArea(); // list account, from people.txt file
				toDisplay2.setText(r.input2("people.txt"));

				TextArea toDisplay3 = new TextArea(); // list account, from relation.txt file
				toDisplay3.setWrapText(true);
				toDisplay3.setText(r.input2("relations.txt"));

				TextArea toDisplay4 = new TextArea(); // list account, from database
				toDisplay4.setWrapText(true);
				toDisplay4.setText(d.listDatabase());

				grid.add(toDisplay, 0, 2); // add to the grid
				grid.add(toDisplay2, 0, 3); // add to the grid
				grid.add(toDisplay3, 0, 4); // add to the grid
				grid.add(toDisplay4, 0, 5); // add to the grid

				Scene scene2 = new Scene(grid, 500, 500); // set scene
				stage2.setTitle("Result"); // set title
				stage2.setScene(scene2); // add scene to stage
				stage2.show(); // make stage visible

			}
		});
		// add b2 action, b2 is select a person
		b2.setOnAction(new EventHandler<ActionEvent>() { // b2 button action

			@Override
			public void handle(ActionEvent event) {
				Stage stage3 = new Stage();
				Button searchbutton = new Button("Search");// this button need to be set action for search
				Button addconnection=new Button("add connection");//this button is add connection
				
				Text text = new Text("Name:");
				TextField seachArea = new TextField();

				TextArea searchResult = new TextArea();
				searchResult.setWrapText(true);
				addconnection.setOnAction(new EventHandler<ActionEvent>() {//connection button action
					public void handle(ActionEvent event) {
						Stage stage0=new Stage();
						
						//button 
						Button addf=new Button("add friends");
						addf.setOnAction(new EventHandler<ActionEvent>() {
						//add friends
						public void handle(ActionEvent event) {
						
							
						//NotToBeFriendsException dialog
						Alert alertN=new Alert(AlertType.ERROR);
						alertN.setTitle("NotToBeFriendsException");
						alertN.setHeaderText("NotToBeFriendsException");
						alertN.setContentText("NotToBeFriendsException");
							
						//TooYoungException dialog
						Alert alertY=new Alert(AlertType.ERROR);
						alertY.setTitle("TooYoungException");
						alertY.setHeaderText("TooYoungException");
						alertY.setContentText("TooYoungException");
						
						Text tf1=new Text("your name");
						Text tf2=new Text("friend\'s name");
						TextField f1=new TextField();
						TextField f2=new TextField();
						Button add=new Button("add"); //action
						add.setOnAction(new EventHandler<ActionEvent>() {
								public void handle(ActionEvent event) {
									//add friends
									try {
										r.addFriends(f1.getText(), f2.getText());
										r.getAccByName(f1.getText()).setFriends(f2.getText()); // need record into "relations" file
										r.getAccByName(f2.getText()).setFriends(f1.getText());
										// record both of names into "relations.txt" as friends
										r.outputRelations(f1.getText() + "	" + f2.getText() + "	"+"[friends]"+"\r\n");//record friends into txt
										add.setText("Finished!!");
									}catch(TooYoungException e) {
									//	System.out.println(e);
										e.printStackTrace();
										alertY.showAndWait();
									}catch(NotToBeFriendsException e) {
										e.printStackTrace();
									    alertN.showAndWait();
									}
								}
								
							});
							
							Stage sfriends=new Stage();
							GridPane grid = new GridPane();// new window
							grid.setAlignment(Pos.CENTER);
							grid.setHgap(10); // set gap
							grid.setVgap(10); // set gap
							grid.setPadding(new Insets(25, 25, 25, 25));
							//add into grid
							grid.add(tf1, 0, 1);
							grid.add(f1, 0,2 );
							grid.add(tf2, 0, 3);
							grid.add(f2, 0, 4);
							grid.add(add, 0, 5);
							
							Scene scenef=new Scene(grid,400,400);
							sfriends.setScene(scenef);
							sfriends.setTitle("add friends");
							sfriends.show();
						}
							
						});
						
						Button addchild=new Button("add child");//add action
						addchild.setOnAction(new EventHandler<ActionEvent>() {
							public void handle(ActionEvent event) {//add child
								//design window
								Text tf1=new Text("your name");
								Text tf2=new Text("child\'s name");
								TextField f1=new TextField();
								TextField f2=new TextField();
								Button add=new Button("add"); //action
								add.setOnAction(new EventHandler<ActionEvent>() {
									public void handle(ActionEvent event) {
										r.getAccByName(f1.getText()).setChildren(f2.getText()); // add children
										add.setText("Finished!!");
									}
								});
								Stage schild=new Stage();
								GridPane grid = new GridPane();// new window
								grid.setAlignment(Pos.CENTER);
								grid.setHgap(10); // set gap
								grid.setVgap(10); // set gap
								grid.setPadding(new Insets(25, 25, 25, 25));
								//add into grid
								grid.add(tf1, 0, 1);
								grid.add(f1, 0,2 );
								grid.add(tf2, 0, 3);
								grid.add(f2, 0, 4);
								grid.add(add, 0, 5);
								
								Scene scenef=new Scene(grid,400,400);
								schild.setScene(scenef);
								schild.setTitle("add child");
								schild.show();
							}
							
						});
						
						Button addparents=new Button("add parents");//add action
						addparents.setOnAction(new EventHandler<ActionEvent>() {
							public void handle(ActionEvent event) {
								//NoParentException	dialog
								Alert alertP=new Alert(AlertType.ERROR);
								alertP.setTitle("NoParentException");
								alertP.setHeaderText("NoParentException");
								alertP.setContentText("NoParentException");
								//NoAvaliableException dialog
								Alert at=new Alert(AlertType.ERROR);
								at.setTitle("NoAvaliableException");
								at.setHeaderText("NoAvaliableException");
								at.setContentText("NoAvaliableException");	
								
								
								Text tf1=new Text("your name");
								Text tf2=new Text("parent\'s name");
								Text tf3=new Text("parent\'s name");
								TextField f1=new TextField();
								TextField f2=new TextField();
								TextField f3=new TextField();
								Button add=new Button("add"); //action
								add.setOnAction(new EventHandler<ActionEvent>() {
									public void handle(ActionEvent event) {
									try {
										r.addParents(f1.getText(),f2.getText(), f3.getText());
										r.outputRelations(f2.getText() + "	" + f3.getText() + "	"+"[parent]"+"\r\n");
										add.setText("Finished!!");
									}catch(NoAvaliableException e) {
										e.printStackTrace();
										at.show();
									}catch(NoParentException e) {
										e.printStackTrace();
										alertP.show();
									}
									}
								});
								
								Stage sp=new Stage();
								GridPane grid = new GridPane();// new window
								grid.setAlignment(Pos.CENTER);
								grid.setHgap(10); // set gap
								grid.setVgap(10); // set gap
								grid.setPadding(new Insets(25, 25, 25, 25));
								//add into grid
								grid.add(tf1, 0, 1);
								grid.add(f1, 0,2 );
								grid.add(tf2, 0, 3);
								grid.add(f2, 0, 4);
								grid.add(tf3, 0, 5);
								grid.add(f3,0, 6);
								grid.add(add, 0, 7);
								
								Scene scenef=new Scene(grid,400,400);
								sp.setScene(scenef);
								sp.setTitle("add parent");
								sp.show();
								
							}
						});
						
						Button addcol=new Button("add colleague");//add action
						addcol.setOnAction(new EventHandler<ActionEvent>() {
							public void handle(ActionEvent event) {
							//NotToBeColleaguesException dialog
							Alert cc=new Alert(AlertType.ERROR);
							cc.setTitle("NotToBeColleaguesException");
							cc.setHeaderText("NotToBeColleaguesException");
							cc.setContentText("NotToBeColleaguesException");	
								
							Text tf1=new Text("your name");
							Text tf2=new Text("colleague\'s name");
							TextField f1=new TextField();
							TextField f2=new TextField();
							Button add=new Button("add"); //action
							add.setOnAction(new EventHandler<ActionEvent>() {
								public void handle(ActionEvent event) {
									try {
									r.addColleague(f1.getText(), f2.getText());
									r.getAccByName(f1.getText()).setColleague(f2.getText());
									r.getAccByName(f2.getText()).setColleague(f1.getText());
									r.outputRelations(f1.getText() + "	" + f2.getText() + "	"+"[colleague]"+"\r\n");
									add.setText("Finished!!");
									}catch(NotToBeColleaguesException e) {
										e.printStackTrace();
										cc.show();
									}
								}
							});	
							
							Stage sc=new Stage();
							GridPane grid = new GridPane();// new window
							grid.setAlignment(Pos.CENTER);
							grid.setHgap(10); // set gap
							grid.setVgap(10); // set gap
							grid.setPadding(new Insets(25, 25, 25, 25));
							//add into grid
							grid.add(tf1, 0, 1);
							grid.add(f1, 0,2 );
							grid.add(tf2, 0, 3);
							grid.add(f2, 0, 4);
							grid.add(add, 0, 5);
							
							Scene scenef=new Scene(grid,400,400);
							sc.setScene(scenef);
							sc.setTitle("add colleague");
							sc.show();
							}
						});
						Button addclass=new Button("add classmates");//add action
						addclass.setOnAction(new EventHandler<ActionEvent>() {
							public void handle(ActionEvent event) {
							//NotToBeClassmatesException dialog
							Alert cl=new Alert(AlertType.ERROR);
							cl.setTitle("NotToBeClassmatesException");
							cl.setHeaderText("NotToBeClassmatesException");
							cl.setContentText("NotToBeClassmatesException");	
								
							Text tf1=new Text("your name");
							Text tf2=new Text("classmates\'s name");
							TextField f1=new TextField();
							TextField f2=new TextField();
							Button add=new Button("add"); //action
							add.setOnAction(new EventHandler<ActionEvent>() {
								public void handle(ActionEvent event) {
									try {
									r.addClassmates(f1.getText(), f2.getText());
									r.getAccByName(f1.getText()).setClassmats(f2.getText());
									r.getAccByName(f2.getText()).setClassmats(f1.getText());
									r.outputRelations(f1.getText() + "	" + f2.getText() + "	"+"[classmates]"+"\r\n");
									add.setText("Finished!!");
									}catch(NotToBeClassmatesException e) {
										e.printStackTrace();
										cl.show();
									}
								}
							});	
							
							Stage sx=new Stage();// new window
							GridPane grid = new GridPane();
							grid.setAlignment(Pos.CENTER);
							grid.setHgap(10); // set gap
							grid.setVgap(10); // set gap
							grid.setPadding(new Insets(25, 25, 25, 25));
							//add into grid
							grid.add(tf1, 0, 1);
							grid.add(f1, 0,2 );
							grid.add(tf2, 0, 3);
							grid.add(f2, 0, 4);
							grid.add(add, 0, 5);
							
							Scene scenex=new Scene(grid,400,400);
							sx.setScene(scenex);
							sx.setTitle("add classmataes");
							sx.show();
							}
						});
						Button addcouple=new Button("add couple");//add action
						addcouple.setOnAction(new EventHandler<ActionEvent>() {
							
							@Override
							public void handle(ActionEvent event) {
								//Not To be coupled exception dialog
								Alert op=new Alert(AlertType.ERROR);
								op.setTitle("NotToBecoupledException");
								op.setHeaderText("NotToBecoupledException");
								op.setContentText("NotToBecoupledException");
								
								Text tf1=new Text("your name");
								Text tf2=new Text("couple\'s name");
								TextField f1=new TextField();
								TextField f2=new TextField();
								Button add=new Button("add");
								add.setOnAction(new EventHandler<ActionEvent>() {
									//add button action
									public void handle(ActionEvent event) {
										try {
											r.addCouple(f1.getText(), f2.getText());
											r.getAccByName(f1.getText()).setCouple(f2.getText());
											r.getAccByName(f2.getText()).setCouple(f1.getText());
											r.outputRelations(f1.getText()+" "+f2.getText()+"	"+"[couple]"+"\r\n");
											add.setText("Finished!!");
										}catch(NotToBeCoupledException e) {
											e.printStackTrace();
											op.showAndWait();
										}
									}
								});
								Stage oo=new Stage();
								GridPane grid = new GridPane();// new window
								grid.setAlignment(Pos.CENTER);
								grid.setHgap(10); // set gap
								grid.setVgap(10); // set gap
								grid.setPadding(new Insets(25, 25, 25, 25));
								//add into grid
								grid.add(tf1, 0, 1);
								grid.add(f1, 0,2 );
								grid.add(tf2, 0, 3);
								grid.add(f2, 0, 4);
								grid.add(add, 0, 5);
								
								Scene scenef=new Scene(grid,400,400);
								oo.setScene(scenef);
								oo.setTitle("add couple");
								oo.show();
							}
						});
						
						
						//this is the main window in this function
						GridPane grid = new GridPane();// use grid layout
						grid.setAlignment(Pos.CENTER);
						grid.setHgap(10); // set gap
						grid.setVgap(10); // set gap
						grid.setPadding(new Insets(25, 25, 25, 25)); // set the gap
						grid.add(addf, 0, 1);
						grid.add(addchild, 0, 2);
						grid.add(addparents, 0, 3);
						grid.add(addcol, 0, 4);
						grid.add(addclass, 0, 5);
						grid.add(addcouple, 0, 6);
						//create window
						Scene scene0=new Scene(grid,400,400);
						stage0.setTitle("Now,add connection");
						stage0.setScene(scene0);
						stage0.show();
					}
				});
				searchbutton.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) { // search action of searchbutton
					if ((seachArea.getText() != null && !seachArea.getText().isEmpty())) {
						// search from Memory
						String sr = "[Memory] " + r.search(seachArea.getText()).getinfo() + "\n"; // get info, it's
																										// the search
																										// reuslt
						sr += "[Children]: " + r.search(seachArea.getText()).listChildren() + "\n";
						sr += "[Parent]: " + r.search(seachArea.getText()).listParents() + "\n";
						sr += "[Friends]: " + r.search(seachArea.getText()).listFriends() + "\n";
						sr += "[Colleague]: " + r.search(seachArea.getText()).listColleagues() + "\n";
						sr += "[Classmates]: " + r.search(seachArea.getText()).listClassmates() + "\n";
						sr += "[Couple]: " + r.search(seachArea.getText()).listCouple() + "\n";

						String s = "[database] " + d.searchDatabase(seachArea.getText()) + "\n"; // search from
																										// database
						searchResult.setText(sr + "\n" + s);
						} else {
							text.setText("try again.");
						}
					}
				});

				GridPane grid = new GridPane();// use grid layout
				grid.setAlignment(Pos.CENTER);
				grid.setHgap(10); // set gap
				grid.setVgap(10); // set gap
				grid.setPadding(new Insets(25, 25, 25, 25)); // set the gap
				grid.add(text, 0, 1); // set the position of the "scenetitle"
				grid.add(seachArea, 0, 2);
				grid.add(searchbutton, 0, 3);
				grid.add(addconnection, 0,9);
				grid.add(searchResult, 0, 4);
				
				
				Scene scene3 = new Scene(grid, 500, 500); // set scene
				stage3.setTitle("Now Search"); // set title
				stage3.setScene(scene3); // add scene to stage
				stage3.show(); // make stage visible
			}
		});

		// add b3 action, are they friends?
		b3.setOnAction(new EventHandler<ActionEvent>() { // b3 button action
			public void handle(ActionEvent event) {
				Stage stage4 = new Stage();
				Button searchbutton = new Button("Judge");// this button need to be set action for judge
				Text text = new Text("Name1:"); // enter first name
				TextField seachArea = new TextField(); // enter second name
				Text text2 = new Text("Name2:");
				TextField seachArea2 = new TextField();
				TextArea searchResult = new TextArea();
				searchResult.setWrapText(true);
				searchbutton.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) { // judge action
				// show judge result
				String js = this.judgeFriends(seachArea.getText(), seachArea2.getText());
				searchResult.setText(js);
				}

				private String judgeFriends(String name1, String name2) {
					String judge="";
					String []friends=r.getAccByName(name1).getFriends();
					for (int i = 0; i < friends.length; i++) {
						if (friends[i] == null) {
							break;
						}
						if (friends[i].equals(name2)) {
							judge="they are direct friends";
							break;
						}else {
							judge="they aren not direct friends";
						}
					} 
					return judge;
				}
				});
				GridPane grid = new GridPane();// use grid layout
				grid.setAlignment(Pos.CENTER);
				grid.setHgap(10); // set gap
				grid.setVgap(10); // set gap
				grid.setPadding(new Insets(25, 25, 25, 25)); // set the gap
				grid.add(text, 0, 1); // set the position of the "scenetitle"
				grid.add(seachArea, 0, 2);
				grid.add(text2, 0, 3);
				grid.add(seachArea2, 0, 4);
				grid.add(searchbutton, 0, 5);
				grid.add(searchResult, 0, 6);
				Scene scene4 = new Scene(grid, 500, 500); // set scene
				stage4.setTitle("Find"); // set title
				stage4.setScene(scene4); // add scene to stage
				stage4.show(); // make stage visible
			}
		});
		// b4
		// add b4 action, add a person
		b4.setOnAction(new EventHandler<ActionEvent>() { // b4 button action
			public void handle(ActionEvent event) {
				Stage stage5 = new Stage();
				Button searchbutton = new Button("create account");// this button need to be set action for search
				Text text = new Text("Name:");
				TextField seachArea1 = new TextField();
				Text text2 = new Text("Age:");
				TextField seachArea2 = new TextField();
				Text text3 = new Text("Gender:");
				TextField seachArea3 = new TextField();
				Text text4 = new Text("Status:");
				TextField seachArea4 = new TextField();
				
				//these show on the bottom in this page
				Text ta = new Text("Adult1 name:");
				Button ba=new Button("add adult");
				TextField sa = new TextField();
				
				Text ta2 = new Text("Adult2 name:");
				TextField sa2 = new TextField();
				
				//NoParentException dialog
				Alert alert4=new Alert(AlertType.ERROR);
				alert4.setTitle("NoParentException");
				alert4.setHeaderText("NoParentException");
				alert4.setContentText("NoParentException");
				
				//NoAvaliableException  dialog
				Alert alert3=new Alert(AlertType.ERROR);
				alert3.setTitle("NoAvaliableException ");
				alert3.setHeaderText("NoAvaliableException");
				alert3.setContentText("NoAvaliableException");
				
				//NoSuchAgeException dialog
				Alert alert=new Alert(AlertType.ERROR);
				alert.setTitle("error");
				alert.setHeaderText("NoSuchAgeException");
				alert.setContentText("age need bigger than 0 and smaller than 150!");
				//age less than 2, dialog
				Alert alert2=new Alert(AlertType.INFORMATION);
				alert2.setTitle("Carefully");
				alert2.setHeaderText("Carefully");
				String s="you age is under the 2 years old"+"\n";
				s+="you cannot add any friend"+"\n";
				s+="your must linked with two adults";
				alert2.setContentText(s);
				//age less than 16, dialog
				Alert alert16=new Alert(AlertType.INFORMATION);
				alert16.setTitle("Carefully");
				alert16.setHeaderText("Carefully");
				String s16="your age is under the 16 years old"+"\n";
				s16+="your must linked with two adults";
				alert16.setContentText(s16);
				
				
				searchbutton.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) { // searchbutton action
						r.createAccount(seachArea1.getText()); //create a account
						int age=Integer.parseInt(seachArea2.getText()); //age
						d.insertDatabase(seachArea1.getText(),age); //set age and name into database
						try {
							r.getAccByName(seachArea1.getText()).setAge(age); //set age
							r.addAge(seachArea1.getText()); //age exception
						} catch (NoSuchAgeException e) {
							e.printStackTrace();
							alert.showAndWait();  //NoSuchAgeException 
						}
						//set gender and status
						r.getAccByName(seachArea1.getText()).setGender(seachArea3.getText());
						r.getAccByName(seachArea1.getText()).setStatus(seachArea4.getText());
						
						//different age different treat
						if (r.getAccByName(seachArea1.getText()).getAge() < 16) {
							if(r.getAccByName(seachArea1.getText()).getAge()<=2) {
							alert2.showAndWait(); //open notification
							try {//add two adults as parents
								r.addParents(seachArea1.getText(), sa.getText(), sa2.getText());
							//sa and sa2 are the parents of this person
							r.getAccByName(seachArea1.getText()).setParents(sa.getText()); // add Mom name
							r.getAccByName(seachArea1.getText()).setParents(sa2.getText()); // add the Dad name
							String message=r.getAccByName(seachArea1.getText()).getinfo();
							r.output("people.txt", message);  //output this person info into people.txt
							} catch (NoAvaliableException e) {
								e.printStackTrace();
								alert3.showAndWait();
							} catch (NoParentException e) {
								e.printStackTrace();
								alert4.showAndWait();
							}//end catch
							String message1=r.getAccByName(seachArea1.getText()).getinfo();
							r.output("people.txt", message1);
					}else{
						alert16.showAndWait();
						try { //add parents for this person
							r.addParents(seachArea1.getText(),sa.getText() , sa2.getText());
							r.getAccByName(seachArea1.getText()).setParents(sa.getText());//add one parent
							r.getAccByName(seachArea1.getText()).setParents(sa2.getText());//add the other parent
							String message=r.getAccByName(seachArea1.getText()).getinfo();
							r.output("people.txt", message);
						}catch(NoAvaliableException e) {
							e.printStackTrace();
							alert3.showAndWait();
						}catch(NoParentException e) {
							e.printStackTrace();
							alert4.showAndWait();
						}
						String message=r.getAccByName(seachArea1.getText()).getinfo();
						r.output("people.txt", message);
					}
						}else {
							String message=r.getAccByName(seachArea1.getText()).getinfo();
							r.output("people.txt", message);
						}
					}

					
					});
				
				GridPane grid = new GridPane();// use grid layout
				grid.setAlignment(Pos.CENTER);
				grid.setHgap(10); // set gap
				grid.setVgap(10); // set gap
				grid.setPadding(new Insets(10, 10, 10, 10)); // set the gap
				grid.add(text, 0, 1); // set the position of the "scenetitle"
				grid.add(seachArea1, 0, 2);
				grid.add(text2, 0, 3);
				grid.add(seachArea2, 0, 4);
				grid.add(text3, 0, 5);
				grid.add(seachArea3, 0, 6);
				grid.add(text4, 0, 7);
				grid.add(seachArea4, 0, 8);
				grid.add(searchbutton, 2, 8);
				grid.add(ta, 0, 11);
				grid.add(sa, 0, 12);
				grid.add(ba, 2 ,14);
				grid.add(ta2, 0, 13);
				grid.add(sa2, 0, 14);
				Scene scene5 = new Scene(grid, 500, 500); // set scene
				stage5.setTitle("Now add"); // set title
				stage5.setScene(scene5); // add scene to stage
				stage5.show(); // make stage visible
			}
		});

		// b5
		// add b5 action, b5 is "help"
		b5.setOnAction(new EventHandler<ActionEvent>() { // b5 button action
			public void handle(ActionEvent event) {
				Stage stage5 = new Stage();
				Text text = new Text();
				text.setText("you need choose an option,you can select or add person" + "\n"
						+ "you need create account in different name");
				GridPane grid = new GridPane();// use grid layout
				grid.setAlignment(Pos.CENTER);
				grid.setHgap(10); // set gap
				grid.setVgap(10); // set gap
				grid.setPadding(new Insets(10, 10, 10, 10)); // set the gap
				grid.add(text, 0, 1); // set the position of the "scenetitle"
				Scene scene5 = new Scene(grid, 500, 500); // set scene
				stage5.setTitle("Help"); // set title
				stage5.setScene(scene5); // add scene to stage
				stage5.show(); // make stage visible
			}
		});
		// b6
		// add b6 action, b6 is "Exit"
		b6.setOnAction(new EventHandler<ActionEvent>() { // b6 button action
			public void handle(ActionEvent event) {
				System.exit(1);
			}
		});

		// add b7 action, b7 is Delete
		b7.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Stage stage7 = new Stage();
				Text text = new Text();
				text.setText("Enter the name want to delete");
				TextField seachArea= new TextField(); //catch the account name
				Button b = new Button("Delete");
				b.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
					Account acc=r.getAccByName(seachArea.getText());
					acc=null;
					text.setText("acocunt has been deleted");
					}
				});
				
				GridPane grid = new GridPane();// use grid layout
				grid.setAlignment(Pos.CENTER);
				grid.setHgap(10); // set gap
				grid.setVgap(10); // set gap
				grid.setPadding(new Insets(10, 10, 10, 10)); // set the gap
				grid.add(text, 0, 1); // set the position of the "scenetitle"
				grid.add(seachArea, 0, 2);
				grid.add(b, 0, 3);

				Scene scene7 = new Scene(grid, 500, 500); // set scene
				stage7.setTitle("Delete"); // set title
				stage7.setScene(scene7); // add scene to stage
				stage7.show(); // make stage visible
			}
		});

		// add button into grid layout
		grid.add(b1, 1, 1); // List everyone
		grid.add(b2, 1, 2); // Select a person
		grid.add(notification, 2, 2);
		grid.add(b3, 1, 3); // Are these two direct friends?
		grid.add(b4, 1, 4); // Add a person into network
		grid.add(b5, 1, 5); // help
		grid.add(b6, 1, 6); // Exit
		grid.add(b7, 1, 7); // Delete account

		Scene scene = new Scene(grid, 500, 500); // add grid to scene, the size of scene is 500*500
		primaryStage.setScene(scene); // add scene to the stage
		// this is the stage, title is "menu"
		primaryStage.setTitle("Menu");
		primaryStage.show(); // let it become visible
			
	}
}
