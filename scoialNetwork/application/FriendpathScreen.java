//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: FriendshipScreen.java
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

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * This class represents the friend path page of the network
 * @author shihan
 *
 */
public class FriendpathScreen extends Main {

  private static String checkedFriend;
  private static String number;
  private static List<String> friendsPath;

  /**
   * This method sets up the friendship path of the network application
   * @param name
   */
  public static void setupFriendshipPathPage(String name) {

    try {
      HBox hbox = new HBox();
      VBox vbox = new VBox();
      Text text = new Text(
          "Choose 1 of following freinds \nFind the friend path with central user");
      text.setFont(Font.font("Verdana", FontWeight.BOLD, 26));
      screens.get(name).setTop(text);
      ArrayList<CheckBox> checkBoxCollections = new ArrayList<CheckBox>();
      ArrayList<String> allFriends = new ArrayList<String>();
      allFriends = Main.netWork.getAllUsers();
      String friend = "";
      for (int i = 0; i < allFriends.size(); i++) {
        friend = allFriends.get(i);
        CheckBox checkBox1 = new CheckBox(friend);
        vbox.getChildren().add(checkBox1);
        checkBoxCollections.add(checkBox1);
      }

      EventHandler<ActionEvent> eHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
          int selcetedCheckBox = 0;
          if (event.getSource() instanceof CheckBox) {
            HBox hbox1 = new HBox();
            CheckBox chk = (CheckBox) event.getSource();
            for (int i = 0; i < checkBoxCollections.size(); i++) {
              String eq = checkBoxCollections.get(i).getText();
              if (checkBoxCollections.get(i).isSelected()) {
                selcetedCheckBox = i;

              }
              List<String> friendsPath =
                  Main.netWork.getPath(Main.netWork.getCenter(),
                      Main.netWork.getAllUsers().get(selcetedCheckBox));

              ComboBox<String> combo_box =
                  new ComboBox<String>(FXCollections.observableArrayList(friendsPath));
              screens.get(name).setCenter(combo_box);
              Text text = new Text("Here is the friend path");
              text.setFont(Font.font("Verdana", FontWeight.BOLD, 26));
              screens.get(name).setTop(text);
              
              Button back = new Button("Back");
              screens.get(name).setBottom(back);
              back.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent me) {
                  Main.activate("Network");
                  Main.setupScreens("Network");
                }
              });
              
            }
          }
        }
      };


      for (int i = 0; i < checkBoxCollections.size(); i++) {
        checkBoxCollections.get(i).setOnAction(eHandler);
      }

      Button ok = new Button("Show path");
      Button back = new Button("Back");
      hbox.getChildren().addAll(ok, back);

      hbox.setAlignment(Pos.CENTER);
      vbox.getChildren().add(hbox);
      ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent me) {
          activate("FriendPathPage");
          setupScreens("FriendPathPage");

          System.out.println("show friendship path");
        }
      });

      back.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent me) {
          Main.activate("Network");
          Main.setupScreens("Network");
        }
      });

      screens.get(name).setCenter(vbox);

    } catch (Exception e) {

    }
  }

  /**
   * 
   * @param name
   */
  public static void FriendPathPage(String name, int index) {
    HBox hbox = new HBox();
    ArrayList<String> allFriends = Main.netWork.getAllUsers();
    checkedFriend = allFriends.get(index);

    Text text = new Text("Here is the friend path between "
        + Main.netWork.getCenter() + " and " + checkedFriend);
    text.setFont(Font.font("Verdana", FontWeight.BOLD, 26));
    screens.get(name).setTop(text);
    String centerFriend = Main.netWork.getCenter();

    try {
      List<String> friendsPath =
          Main.netWork.getPath(centerFriend, checkedFriend);
      for (int i = 0; i < friendsPath.size(); i++) {
        String diffriend = friendsPath.get(i);
        Label friend1 = new Label(diffriend);
        hbox.getChildren().add(friend1);
        Label spacing = new Label("        ");
      }

      Button back = new Button("Back");
      hbox.getChildren().addAll(back);

      back.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent me) {
          Main.activate("Network");
          Main.setupScreens("Network");
        }
      });
      screens.get(name).setCenter(hbox);
    } catch (Exception e) {

    }

  }

}
