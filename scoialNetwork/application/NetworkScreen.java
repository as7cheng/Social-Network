//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: NetWorkScreen.java
// Files: Main.java GraphADT.java Graph.java NetWork.java NetworkScreen.java
// AddScreen.java RemoveScreen.java FriendshipScreen.java FriendCycle.java
//////////////////// ClearScreen.java LoadAndSaveScreen.java QuitScreen.java
// Course: Comp Sci 400
//
// Author: A-team 21
// Email: scheng93@wisc.edu, ama28@wisc.edu,
//////////////////// mchang39@wisc.edu,cbearden@wisc.edu,hyu279@wisc.edu
// Lecturer's Name: Debra Deppeler
// Description: The Network screen of the application of Social Network
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

package application;

import java.io.File;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * This class represents the screen of the main menu of Network
 * 
 * @author A-Team 21
 *
 */
public class NetworkScreen extends Main {

  /**
   * This method sets up the buttons of Network of the application
   * 
   * @return VBox A VBox contains buttons
   */
  public static VBox setupButtonForNetworkPage() {
    HBox hbox = new HBox();
    VBox vbox = new VBox();

    // Set the button "Friend Cycle"
    Button cycle = new Button("Friend Cycle");
    hbox.getChildren().addAll(cycle);
    vbox.getChildren().add(hbox);
    hbox.setAlignment(Pos.CENTER);
    cycle.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        Main.activate("FriendCycle");
        Main.setupScreens("FriendCycle");
      }
    });

    // Set the button "Friendship Path"
    hbox = new HBox();
    Button path = new Button("Friendship Path");
    hbox.getChildren().addAll(path);
    vbox.getChildren().add(hbox);
    hbox.setAlignment(Pos.CENTER);
    path.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        if (Main.netWork == null) {
          System.out.print("Network is empty");
          
        }
        activate("FriendshipPath");
        Main.setupScreens("FriendshipPath");
      }
    });

    // Set the button "Add Friend"
    hbox = new HBox();
    Button add = new Button("Add Friend");
    hbox.getChildren().addAll(add);
    vbox.getChildren().add(hbox);
    hbox.setAlignment(Pos.CENTER);
    add.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        Main.activate("AddFriend");
        Main.setupScreens("AddFriend");
      }
    });

    // Set the button "Add Friendship"
    hbox = new HBox();
    Button addFS = new Button("Add Friendship");
    hbox.getChildren().addAll(addFS);
    vbox.getChildren().add(hbox);
    hbox.setAlignment(Pos.CENTER);
    addFS.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        Main.activate("AddFriendship");
        Main.setupScreens("AddFriendship");
      }
    });

    // Set the button "Remove Friendship"
    hbox = new HBox();
    Button removeFS = new Button("Remove Friendship");
    hbox.getChildren().addAll(removeFS);
    vbox.getChildren().add(hbox);
    hbox.setAlignment(Pos.CENTER);
    removeFS.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        Main.activate("RemoveFriendship");
        Main.setupScreens("RemoveFriendship");
      }
    });

    // Set the button "Clear"
    hbox = new HBox();
    Button clear = new Button("Clear");
    hbox.getChildren().addAll(clear);
    vbox.getChildren().add(hbox);
    hbox.setAlignment(Pos.CENTER);
    clear.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        Main.activate("Clear");
        Main.setupScreens("Clear");
      }
    });

    // Set the button "Back"
    hbox = new HBox();
    Button back = new Button("Back");
    hbox.getChildren().addAll(back);
    vbox.getChildren().add(hbox);
    hbox.setAlignment(Pos.CENTER);
    back.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        Main.mainScreen.setRoot(root);
      }
    });
    vbox.setSpacing(15);

    return vbox;
  }

  /**
   * This method sets up the page of Network
   * 
   * @param name Click Action
   */
  public static void setupNetworkPage(String name) {
    HBox hbox = new HBox();
    VBox vbox = new VBox();
    Text text = new Text("Network");
    text.setFont(Font.font("Verdana", FontWeight.BOLD, 26));
    screens.get(name).setTop(text);

    text = new Text("Central User: " + Main.netWork.getCenter()
        + "\nCurrent total friends: " + Main.netWork.getNumOfFriends()
        + "\nCurrent total friendships: " + Main.netWork.getNumOfFriendships()
        + "\n\n");
    text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
    screens.get(name).setBottom(text);

    try {
      File file;
      // Check if current network is empty or not
      // If current network is not empty and current central user is not null
      // Get the image path of current central user
      if (Main.netWork != null && Main.netWork.getCenter() != null) {
        String center = Main.netWork.getCenter();
        String imagePath = Main.netWork.getImage(center);
        file = new File(imagePath);
      } else { // If empty, display the default image
        file = new File(Main.defaultImage);
      }
      // Create an image
      Image image = new Image(file.toURI().toString());
      // Create an image view to demonstrate the image
      ImageView imageView = new ImageView();
      imageView.setFitHeight(400);
      imageView.setFitWidth(400);
      // Use the image view to show the image
      imageView.setImage(image);
      hbox.getChildren().addAll(imageView);

      vbox.getChildren().add(hbox);
      hbox.setAlignment(Pos.CENTER);
      screens.get(name).setLeft(vbox);
      vbox.setAlignment(Pos.CENTER);

      // Get all buttons and add them in
      vbox = setupButtonForNetworkPage();
      screens.get(name).setRight(vbox);
      vbox.setAlignment(Pos.CENTER_LEFT);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
