//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: QuitScreen.java
// Files: Main.java GraphADT.java Graph.java NetWork.java NetworkScreen.java
// AddScreen.java RemoveScreen.java FriendshipScreen.java FriendCycle.java
//////////////////// ClearScreen.java LoadAndSaveScreen.java QuitScreen.java
// Course: Comp Sci 400
//
// Author: A-team 21
// Email: scheng93@wisc.edu, ama28@wisc.edu,
//////////////////// mchang39@wisc.edu,cbearden@wisc.edu,hyu279@wisc.edu
// Lecturer's Name: Debra Deppeler
// Description: The Quit page of the application of Social Network with JavaFX
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

package application;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * This class represents the screen of Quit Page of the network application
 * @author A-Team 21
 *
 */
public class QuitScreen extends Main {

  /**
   * This method sets up the buttons of Quit
   * 
   * @return A HBox contains buttons
   */
  public static HBox setupButtonForQuitPage() {

    HBox hbox = new HBox();
    Button save = new Button("Save");
    Button quit = new Button("Quit");
    Button back = new Button("Back");

    hbox.getChildren().addAll(save, quit, back);
    hbox.setAlignment(Pos.TOP_CENTER);
    hbox.setSpacing(10);

    // Set the button "Save"
    save.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        LoadAndSaveScreen.writeSavedFile();
        System.out.println("Thank you for using! Bye!");
        Main.stage.close();
      }
    });

    // Set the button "Quit"
    quit.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        System.out.println("Thank you for using! Bye!");
        Main.stage.close();
      }
    });

    // Set the button "Back"
    back.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        Main.mainScreen.setRoot(root);
      }
    });

    return hbox;
  }

  /**
   * This method sets up the page of Quit
   * 
   * @param name Click Action
   */
  public static void setupQuitPage(String name) {
    // Set up the boxes
    VBox vbox = new VBox();
    Text text = new Text("Quit");
    text.setFont(Font.font("Verdana", FontWeight.BOLD, 60));
    Main.screens.get(name).setTop(text);
    // Create new labels
    Label line1 = new Label("Would you like to save?");
    Label line2 = new Label("    ");
    // Add them into the boxes
    vbox.getChildren().addAll(line1, line2, setupButtonForQuitPage());
    vbox.setAlignment(Pos.CENTER);
    // Set and change the screen
    Main.screens.get(name).setCenter(vbox);
  }

}
