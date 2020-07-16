//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: AddScreen.java
// Files: Main.java GraphADT.java Graph.java NetWork.java NetworkScreen.java
// AddScreen.java RemoveScreen.java FriendshipScreen.java FriendCycle.java
//////////////////// ClearScreen.java LoadAndSaveScreen.java QuitScreen.java
// Course: Comp Sci 400
//
// Author: A-team 21
// Email: scheng93@wisc.edu, ama28@wisc.edu,
//////////////////// mchang39@wisc.edu,cbearden@wisc.edu,hyu279@wisc.edu
// Lecturer's Name: Debra Deppeler
// Description: The Add user page of the application of Social Network with
//////////////////// JavaFX
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////


package application;

import java.io.File;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * This class represents the screen of the function of adding user of the
 * network application
 * 
 * @author A-Team 21
 *
 */
public class AddScreen extends Main {
  // Fields
  private static String iconPath = Main.defaultImage;
  private static TextField textField;

  /**
   * This method sets up the page of AddFriend for user adding friend in current
   * network
   * 
   * @param name Click Action
   */
  public static void setupAddFriendPage(String name) {
    // Set up the boxes
    HBox hbox = new HBox();
    VBox vbox = new VBox();
    Text text = new Text();
    // set the text field
    textField = new TextField();
    hbox.getChildren().addAll(new Text("Name: "), textField);
    vbox.getChildren().add(hbox);
    hbox.setAlignment(Pos.TOP_CENTER);
    // set the add button
    hbox = new HBox();
    Button add = new Button("Add");
    hbox.getChildren().addAll(add);
    vbox.getChildren().add(hbox);
    hbox.setAlignment(Pos.TOP_CENTER);

    // set the file of photo
    File file1 = new File(iconPath);
    hbox = new HBox();
    Label line3 = new Label("\n");
    Image image1 = new Image(file1.toURI().toString());
    // create an image view to demonstrate the image
    ImageView imageView1 = new ImageView();
    // use the image view to show the image
    imageView1.setImage(image1);
    imageView1.setFitHeight(200);
    imageView1.setFitWidth(200);
    imageView1.setPreserveRatio(true);
    hbox.getChildren().addAll(imageView1, line3);
    vbox.getChildren().add(hbox);
    hbox.setAlignment(Pos.CENTER);
    vbox.setSpacing(10);
    text = new Text("Load File");
    text.setFont(Font.font("Verdana", FontWeight.BOLD, 60));
    screens.get(name).setTop(text);
    // Labels
    Label line1 = new Label("     ");
    Label line2 = new Label("Upload a picture for friend!");
    // set the upload button
    vbox.getChildren().addAll(setupButtonForIcon(), line2);
    vbox.setAlignment(Pos.CENTER);
    // Textfield
    text = new Text("Add a Friend");
    text.setFont(Font.font("Verdana", FontWeight.BOLD, 60));
    // Set and change the screens
    screens.get(name).setTop(text);
    screens.get(name).setCenter(vbox);
    screens.get(name).setBottom(line1);

    // Set the action for add button
    add.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        // First record name typed in by the user
        String userName = textField.getText();
        if (Main.netWork.containsUser(userName)) {
          Main.setupScreens("Network");
          Main.activate("Network");
          System.out.println("User exists");
        } else {
          // Add new friend with the name into the network and set image
          Main.netWork.addUser(userName);
          Main.netWork.setImage(userName, iconPath);
          // Record the command to the commandList
          String[] command = {"a", userName};
          Main.commandList.add(command);
          // Reset the iconPath
          iconPath = Main.defaultImage;
          // Get back to the page of Network
          Main.activate("Network");
          Main.setupScreens("Network");
          System.out.println("Add friend succesfully");
        }
      }
    });

  }
  
  /**
   * This method sets up functions of uploading for adding user
   * @return HBox contains all buttons
   */
  public static HBox setupButtonForIcon() {
    HBox hbox = new HBox();
    Button load = new Button("Upload");
    Button back = new Button("Back");
    // Set the boxes
    hbox.getChildren().addAll(load, back);
    hbox.setAlignment(Pos.TOP_CENTER);
    hbox.setSpacing(15);
    // Set up the back button
    back.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        Main.setupScreens("Network");
        Main.activate("Network");
        iconPath = Main.defaultImage;
      }
    });
    
    // Set up the load button
    load.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        iconPath = Main.defaultImage;
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        try {
          File file = chooser.showOpenDialog(new Stage());
          String filePath = file.toString();
          if (filePath.length() < 4 || (!filePath
              .substring(filePath.length() - 3).equals("jpg")
              && !filePath.substring(filePath.length() - 3).equals("png"))) {
            throw new Exception();
          }
          iconPath = filePath;
          setupAddFriendPage("AddFriend");
        } catch (Exception e) {

        }
      }
    });
    return hbox;
  }

}
