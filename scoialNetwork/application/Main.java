//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Main.java
// Files: Main.java GraphADT.java Graph.java NetWork.java NetworkScreen.java
// AddScreen.java RemoveScreen.java FriendshipScreen.java FriendCycle.java
//////////////////// ClearScreen.java LoadAndSaveScreen.java QuitScreen.java
// Course: Comp Sci 400
//
// Author: A-team 21
// Email: scheng93@wisc.edu, ama28@wisc.edu,
//////////////////// mchang39@wisc.edu,cbearden@wisc.edu,hyu279@wisc.edu
// Lecturer's Name: Debra Deppeler
// Description: The Main of the application of Social Network with JavaFX
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

package application;

import javafx.scene.text.Font;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import java.io.File;
import java.util.*;

/**
 * Main GUI class for the project of Social Network.
 * 
 * @author A-team 21
 *
 */
public class Main extends Application {
  // Field
  protected static NetWork netWork = new NetWork();
  protected static Stage stage;
  protected static HashMap<String, BorderPane> screens = new HashMap<>();
  protected static Scene mainScreen; // Scene to display different panes
  protected static BorderPane root; // the main menu
  protected static ArrayList<String[]> commandList = new ArrayList<String[]>(); // Records
                                                                                // all
                                                                                // interactions
  final static String defaultImage = "default.png"; // The default user image

  /**
   * This method sets up the buttons of root(main page) of the application
   * 
   * @return HBox A HBox contains buttons
   */
  public static HBox setButtonForRootScreen() {

    // Initialization of the HBox and buttons
    HBox hbox = new HBox();
    Button nwButton = new Button("Network");
    Button loadButton = new Button("Load");
    Button saveButton = new Button("Save");
    Button quitButton = new Button("Quit");

    // Set the size for each button
    nwButton.setPrefSize(150, 60);
    loadButton.setPrefSize(150, 60);
    saveButton.setPrefSize(150, 60);
    quitButton.setPrefSize(150, 60);

    // Add above buttons into the HBox
    hbox.getChildren().addAll(nwButton, loadButton, saveButton, quitButton);
    hbox.setAlignment(Pos.CENTER);
    hbox.setSpacing(15);

    // EventHandler for next move(Click)
    // Call activate method to set scene
    // Case 1: When click the button "Network"
    nwButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        activate("Network");
        setupScreens("Network");
        System.out.println("Network page");
      }
    });

    // Case 2: When click the button "Load"
    loadButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        activate("Load");
        setupScreens("Load");
        System.out.println("Load files");
      }
    });
    // Case 3: When click the button "Save"
    saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        activate("Save");
        setupScreens("Save");
        System.out.println("Save current network");
      }
    });
    // Case 4: When click the button "Quit"
    quitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
        activate("Quit");
        setupScreens("Quit");
      }
    });

    return hbox;
  }

  /**
   * This method initializes all screens that we need
   */
  public void initializeScreens() {
    String[] screenNames = {"Network", "Load", "Save", "Quit", "FriendCycle",
        "Friendship", "FriendshipPath", "FriendshipPath1", "AddFriend",
        "RemoveFriend", "AddFriendship", "RemoveFriendship", "Clear"};
    for (String name : screenNames) {
      this.addScreen(name);
    }
  }

  /**
   * This methods sets up screens for all functions
   * 
   * @param name Screen's name
   */
  public static void setupScreens(String name) {
    // A switch function to set screens by user's commands
    switch (name) {
      case "Network":
        NetworkScreen.setupNetworkPage(name);
        break;

      case "Load":
        LoadAndSaveScreen.setupLoadPage(name);
        break;
      
        //1
      case "FriendCycle":
        FriendCycleScreen.setupFriendCyclePage(name);
        break;
        
        //2
      case "FriendshipPath":
        FriendpathScreen.setupFriendshipPathPage(name);
        break;

      case "AddFriend":
        AddScreen.setupAddFriendPage(name);
        break;
        
      
        case "AddFriendship":
        FriendshipScreen.setupAddFriendshipPage(name); 
        break;
       
        case "RemoveFriendship":
          FriendshipScreen.setupRemoveFriendshipPage(name); 
          break;
       

      case "Clear":
        ClearScreen.setupClearPage(name);
        break;

      case "Save":
        LoadAndSaveScreen.setupSavePage(name);
        break;

      case "Quit":
        QuitScreen.setupQuitPage(name);
        break;
    }
  }

  /**
   * This methods adds new screen into current GUI
   * 
   * @param name Name of the new screen 
   */
  public void addScreen(String name) {
    BorderPane pane = new BorderPane();
    screens.put(name, pane);
  }

  /**
   * This methods remove screen from current GUI
   * 
   * @param name
   */
  protected void removeScreen(String name) {
    screens.remove(name);
  }

  /**
   * This method changes scene to desired pane
   * 
   * @param name
   */
  protected static void activate(String name) {
    mainScreen.setRoot(screens.get(name));
  }

  /**
   * This method shows the main stage
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    try {
      Main.stage = primaryStage;
      Main.stage.setTitle("iGroup");
      root = new BorderPane();
      mainScreen = new Scene(root, 960, 800);
      // set title
      Text title = new Text("iGroup");
      title.setFont(Font.font("Verdana", FontWeight.BOLD, 60));

      root.setTop(title);
      BorderPane.setMargin(root.getTop(), new Insets(150));
      BorderPane.setAlignment(title, Pos.CENTER);
      root.setCenter(Main.setButtonForRootScreen());

      initializeScreens();
      mainScreen.getStylesheets()
          .add(getClass().getResource("application.css").toExternalForm());
      primaryStage.setScene(mainScreen);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Main method to run the application
   * 
   * @param args Any type
   */
  public static void main(String[] args) {
    launch(args);
  }

}
