//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Clear.java
// Files: Main.java GraphADT.java Graph.java NetWork.java NetworkScreen.java
// AddScreen.java RemoveScreen.java FriendshipScreen.java FriendCycle.java
//////////////////// ClearScreen.java LoadAndSaveScreen.java QuitScreen.java
// Course: Comp Sci 400
//
// Author: A-team 21
// Email: scheng93@wisc.edu, ama28@wisc.edu,
//////////////////// mchang39@wisc.edu,cbearden@wisc.edu,hyu279@wisc.edu
// Lecturer's Name: Debra Deppeler
// Description: The Clear page of the application of Social Network with JavaFX
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

package application;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * This class represents the clear function of the network application
 * @author A-Team 21
 *
 */
public class ClearScreen extends Main {
  
/**
 * This method sets up the screen of clear page
 * @param name Click Actions
 */
  public static void setupClearPage(String name) {
    // Create boxes
    HBox hbox = new HBox();
    VBox vbox = new VBox();
    Text text = new Text();
    // Set up the boxes
    vbox.getChildren().add(hbox);
    hbox.setAlignment(Pos.TOP_CENTER);
    text = new Text("Are you sure you want to clear all?");
    text.setFont(Font.font("Verdana", FontWeight.BOLD, 26));
    // Add the buttons and boxes in
    hbox = new HBox();
    Button ok = new Button("OK");
    Button cancel = new Button("Cancel");
    hbox.getChildren().addAll(ok, cancel);
    vbox.getChildren().add(hbox);
    hbox.setAlignment(Pos.TOP_CENTER);
    
    // Set up the ok button
    ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        if (Main.netWork != null)
          Main.netWork.clear();
        mainScreen.setRoot(root);
      }
    });
    
    // Set up the cancel button
    cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        Main.activate("Network");
        Main.setupScreens("Network");
      }
    });
    
    // Set and change the screen
    screens.get(name).setTop(text);
    screens.get(name).setCenter(vbox);
  }

}
