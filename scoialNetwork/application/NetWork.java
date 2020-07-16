//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: NetWork.java
// Files: Main.java GraphADT.java Graph.java NetWork.java NetworkScreen.java
// AddScreen.java RemoveScreen.java FriendshipScreen.java FriendCycle.java
//////////////////// ClearScreen.java LoadAndSaveScreen.java QuitScreen.java
// Course: Comp Sci 400
//
// Author: A-team 21
// Email: scheng93@wisc.edu, ama28@wisc.edu,
//////////////////// mchang39@wisc.edu,cbearden@wisc.edu,hyu279@wisc.edu
// Lecturer's Name: Debra Deppeler
// Description: Network is a data structure which implements Graph to store
//////////////////// users and their friends
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * This class implements the data structure of Graph, creates an object of the
 * whole application, stores data of the whole application of the back end
 * 
 * @author A-Team 21
 *
 */
public class NetWork {
  // Fields
  private Graph graph;
  private String center;
  private HashMap<String, String> imageMap;

  /*
   * The default no-argument constructor.
   */
  public NetWork() {
    this.graph = new Graph();
    this.center = null;
    this.imageMap = new HashMap<String, String>();
  }

  /**
   * This method gets all users in the network.
   * 
   * @return Set<String> of all the users
   */
  public ArrayList<String> getAllUsers() {
    ArrayList<String> retList = new ArrayList<String>();
    for (String friend : this.graph.getAllVertices())
      retList.add(friend);
    return retList;
  }

  /**
   * This method gets a shortest path between two users based on Dijkstra's
   * algorithm
   * 
   * @param user1 The first user
   * @param user2 The second user
   * @return A list contains all users on the path or an empty list if no
   *         connections between them
   */
  public List<String> getPath(String user1, String user2) {
    // Initializations
    HashSet<String> visited = new HashSet<String>();
    HashMap<String, String> parent = new HashMap<String, String>();
    List<String> path = new LinkedList<String>();
    Queue<String> queue = new LinkedList<String>();

    String current = user1;
    queue.add(current);
    visited.add(current);

    // Check user 1 then start the while loop to iterate the whole graph until
    // find user2 or completely iterated the graph
    while (!queue.isEmpty()) {
      current = queue.remove();
      if (current.equals(user2)) { // User2 found
        break;
      } else { // Check all successors of current
        for (String friend : this.getFrinds(current)) {
          if (!visited.contains(friend)) {
            queue.add(friend);
            visited.add(friend);
            parent.put(friend, current);
          }
        }
      }
    }
    // If the very last user is not user2, return an empty list
    if (!current.equals(user2)) {
      return new ArrayList<String>();
    }
    // Otherwise, create and return the list of path
    for (String friend = user2; friend != null; friend = parent.get(friend)) {
      path.add(friend);
    }

    return path;
  }

  /**
   * This method checks if the network contains a user
   * 
   * @param user The user we want to check
   * @return true if user in the network. False otherwise
   */
  public boolean containsUser(String user) {
    return this.graph.contains(user);
  }

  /**
   * This method returns the current center of the graph
   * 
   * @return Center of the network
   */
  public String getCenter() {
    return this.center;
  }

  /**
   * This method sets the center of the graph
   * 
   * @param center The user be set as center
   */
  public void setCenter(String center) {
    this.center = center;
  }

  /**
   * This method adds user in the network
   * 
   * @param user The user we want to add in the network
   */
  public void addUser(String user) {
    this.graph.addVertex(user);
    if (this.center == null)
      this.center = user;
  }

  /**
   * This method removes a user from the network
   * 
   * @param user The user we want to remove from the network
   */
  public void removeUser(String user) {
    if (this.center == user)
      this.center = user;
    this.graph.removeVertex(user);
  }

  /**
   * This method create and add a friendship into the network
   * 
   * @param user1 The first user
   * @param user2 The second user
   */
  public void addFriendship(String user1, String user2) {
    this.graph.addEdge(user1, user2);
  }

  /**
   * This method remove a friendship between tow users from the network
   * 
   * @param user1 The first user
   * @param user2 The scond user
   */
  public void removeFriendship(String user1, String user2) {
    this.graph.removeEdge(user1, user2);
  }

  /**
   * This method gets all friends of a user in the network
   * 
   * @param user The user
   * @return A list of all friend of the user
   */
  public List<String> getFrinds(String user) {
    return this.graph.getAdjacentVerticesOf(user);
  }

  /**
   * This method clear the whole network
   */
  public void clear() {
    if (this.graph != null)
      this.graph = new Graph();
    this.center = null;
    this.imageMap = new HashMap<String, String>();
  }

  /**
   * This method sets the picture for the user
   * 
   * @param user      The user
   * @param imagePath The file path of the image of the user
   */
  public void setImage(String user, String imagePath) {
    this.imageMap.put(user, imagePath);
  }

  /**
   * This method gets the image of the user
   * 
   * @param user The user
   * @return A file path of the image of the user
   */
  public String getImage(String user) {
    return this.imageMap.get(user);
  }

  /**
   * This method gets the number of users in the network
   * 
   * @return The number of users in the network
   */
  public int getNumOfFriends() {
    return this.graph.order();
  }

  /**
   * This method gets the number of friendships in the network
   * 
   * @return The number of friendships in the network
   */
  public int getNumOfFriendships() {
    return this.graph.size();
  }

}
