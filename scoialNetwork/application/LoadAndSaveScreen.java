//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: LoadAndSaveScreen.java
// Files: Main.java GraphADT.java Graph.java NetWork.java NetworkScreen.java
// AddScreen.java RemoveScreen.java FriendshipScreen.java FriendCycle.java
//////////////////// ClearScreen.java LoadAndSaveScreen.java QuitScreen.java
// Course: Comp Sci 400
//
// Author: A-team 21
// Email: scheng93@wisc.edu, ama28@wisc.edu,
//////////////////// mchang39@wisc.edu,cbearden@wisc.edu,hyu279@wisc.edu
// Lecturer's Name: Debra Deppeler
// Description: The Load and Save page of the application of Social Network with JavaFX
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

package application;

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * This class represents the load and save functions of application to load,
 * read files to automatically create a network vis the commands in the file.
 * Also we can export a log file to record all interactions during the using
 * 
 * @author A-Team 21
 *
 */
public class LoadAndSaveScreen extends Main {

  /**
   * This method sets up the buttons of Load to help user upload file into
   * application
   * 
   * @param name Click Action
   */
  public static HBox setupButtonForLoadPage() {
    HBox hbox = new HBox();
    Button load = new Button("Upload");
    Button back = new Button("Back");

    hbox.getChildren().addAll(load, back);
    hbox.setAlignment(Pos.TOP_CENTER);
    hbox.setSpacing(15);
    
    // Set the back button
    back.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        Main.mainScreen.setRoot(Main.root);
      }
    });
    
    // Set the load button
    load.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        try {
          File file = chooser.showOpenDialog(new Stage());
          readLoadFile(file);
          Main.setupScreens("Network");
          Main.activate("Network");
          System.out.println("Load file successfully");
        } catch (Exception e) {
          System.out.println("Error, couldn't load the file");
        }
      }
    });
    return hbox;
  }

  /**
   * This method sets up the page of Load to help user upload file into
   * application
   * 
   * @param name Click Action
   */
  public static void setupLoadPage(String name) {
    // Initialization
    VBox vbox = new VBox();
    Text text = new Text("Load File");
    text.setFont(Font.font("Verdana", FontWeight.BOLD, 60));
    Main.screens.get(name).setTop(text);

    Label line1 = new Label("     ");
    Label line2 = new Label("Upload a file\n");
    
    // Set boxes
    vbox.getChildren().addAll(line2, setupButtonForLoadPage());
    vbox.setAlignment(Pos.CENTER);
    // Set and change the screen
    Main.screens.get(name).setTop(line1);
    Main.screens.get(name).setCenter(vbox);
  }

  /**
   * This method reads and organizes data from the file by given file path
   * 
   * @param path File path
   */
  public static void readLoadFile(File file) {
    try {
      Scanner sc = new Scanner(file);
      // Read and record the input file
      while (sc.hasNextLine()) {
        String[] split = sc.nextLine().trim().split(" ");
        if (split[0].equals("a")) { // When the command is "a"
          if (split.length == 3) {
            if (!Main.netWork.containsUser(split[1])) {
              Main.netWork.addUser(split[1]);
              Main.netWork.setImage(split[1], Main.defaultImage);
            }
            if (!Main.netWork.containsUser(split[2])) {
              Main.netWork.addUser(split[2]);
              Main.netWork.setImage(split[2], Main.defaultImage);
            }
            Main.netWork.addFriendship(split[1], split[2]);
          } else if (split.length == 2) {
            Main.netWork.addUser(split[1]);
            Main.netWork.setImage(split[1], Main.defaultImage);
          }
        } else if (split[0].equals("r")) { // When the command is "r"
          if (split.length == 3) {
            Main.netWork.removeFriendship(split[1], split[2]);
          } else if (split.length == 2) {
            Main.netWork.removeUser(split[1]);
          }
        } else if (split[0].equals("s")) { // When the command is "s"
          if (!Main.netWork.containsUser(split[1])) {
            Main.netWork.addUser(split[1]);
            Main.netWork.setImage(split[1], Main.defaultImage);
          }
          // Set the central user of the network
          Main.netWork.setCenter(split[1]);
        }
        // Record the interaction if the command is not blank
        if (!split[0].trim().isEmpty()) {
          Main.commandList.add(split);
        }
      }
    } catch (Exception e) {

    }
  }

  /**
   * This method sets up the buttons of Save page of the application
   * 
   * @return HBox A HBox contains buttons
   */
  public static HBox setupButtonForSavePage() {
    // Create boxes to user later
    HBox hbox = new HBox();
    Button save = new Button("Save");
    Button back = new Button("Back");
    // Set the size of buttons
    save.setPrefSize(100, 50);
    back.setPrefSize(100, 50);
    // Set the boxes
    hbox.getChildren().addAll(save, back);
    hbox.setAlignment(Pos.TOP_CENTER);
    hbox.setSpacing(15);
    
    // Set the save button
    save.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        // Save info to file
        writeSavedFile();
        Main.mainScreen.setRoot(Main.root);
        System.out.println("Saved successfully");
      }
    });
    
    // Set the back button
    back.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        Main.mainScreen.setRoot(Main.root);
        System.out.println("Go back to root without any action");
      }
    });

    return hbox;
  }

  /**
   * This method sets up the page of Save to help user save the current network
   * as a file
   * 
   * @param name Click Action
   */
  public static void setupSavePage(String name) {
    VBox vbox;
    Text text;
    // Set the boxes and labels
    vbox = new VBox();
    Label line2 = new Label("     \n\n\n\n\n ");
    Label line = new Label("  ");
    vbox.getChildren().addAll(line2, setupButtonForSavePage());
    text = new Text("Save");
    text.setFont(Font.font("Verdana", FontWeight.BOLD, 60));
    // Set and change the screen
    Main.screens.get(name).setTop(text);
    Main.screens.get(name).setCenter(vbox);
    Main.screens.get(name).setBottom(line);
  }

  /**
   * This method writes a file to help user save current network as a file
   */
  public static void writeSavedFile() {
    try {
      // Create a log file
      File file = new File("log.txt");
      PrintWriter pw = new PrintWriter(file);
      for (int i = 0; i < Main.commandList.size(); ++i) {
        for (int j = 0; j < Main.commandList.get(i).length; ++j) {
          pw.write(Main.commandList.get(i)[j]);
          pw.write(" ");
        }
        pw.write("\n");
      }
      pw.flush();
      pw.close();
    } catch (Exception e) {
      System.out.println("Error, couldn't save the file");
    }
  }
}
