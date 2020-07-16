//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: FriendCycle.java
// Files: Main.java GraphADT.java Graph.java NetWork.java NetworkScreen.java
// AddScreen.java RemoveScreen.java FriendshipScreen.java FriendCycle.java
//////////////////// ClearScreen.java LoadAndSaveScreen.java QuitScreen.java
// Course: Comp Sci 400
//
// Author: A-team 21
// Email: scheng93@wisc.edu, ama28@wisc.edu,
//////////////////// mchang39@wisc.edu,cbearden@wisc.edu,hyu279@wisc.edu
// Lecturer's Name: Debra Deppeler
// Description: The Friend cycle page of the application of Social Network with JavaFX
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * This class represents the friend cycle page of the network application
 * @author A-Team 21
 *
 */
public class FriendCycleScreen {

  public static void setupFriendCyclePage(String name) {
	  HBox hbox = new HBox();
	    VBox vbox = new VBox();
	    Text text = new Text();
	    text = new Text("Friend Cycle");
	    text.setFont(Font.font("Verdana", FontWeight.BOLD, 26));
	    Main.screens.get(name).setTop(text);
	    ArrayList<CheckBox> checkBoxCollections = new ArrayList<CheckBox>();
	    ArrayList<String> allFriends = new ArrayList<String>();
	    allFriends = Main.netWork.getAllUsers();
	    String friend = "";
	    for (int i = 0; i < allFriends.size(); i++) {
	      friend = allFriends.get(i);
	      CheckBox checkBox1 = new CheckBox(friend);
	      vbox.getChildren().add(checkBox1);
	      checkBoxCollections.add(checkBox1);
	      // checkBoxCollections.get(i).selectedProperty().bind(selector.);
	    }

	    EventHandler eh = new EventHandler<ActionEvent>() {
	      @Override
	      public void handle(ActionEvent event) {
	        int selcetedCheckBox = 0;
	        if (event.getSource() instanceof CheckBox) {
	          HBox hbox1 = new HBox();
	          CheckBox chk = (CheckBox) event.getSource();
	          System.out.println("done");
	          for (int i = 0; i < checkBoxCollections.size(); i++) {
	            String eq = checkBoxCollections.get(i).getText();
	            if (checkBoxCollections.get(i).isSelected()) {
	              selcetedCheckBox = i;
	            }
	        Main.netWork.setCenter(Main.netWork.getFrinds( Main.netWork.getCenter()).get(selcetedCheckBox));
	           
	          }
	        }
	      }
	    };
	    
	    for (int i = 0; i < checkBoxCollections.size(); i++) {
	      checkBoxCollections.get(i).setOnAction(eh);
	    }
	    
	    
	    Button back = new Button("Back");
	    vbox.getChildren().addAll(back);


	    back.setOnMouseClicked(new EventHandler<MouseEvent>() {
	      @Override
	      public void handle(MouseEvent me) {
	        Main.setupScreens("Network");
	        Main.activate("Network");
	        Main.setupScreens("Network");
	      }
	    });

	    Main.screens.get(name).setCenter(vbox);
	  }
  }



