import static org.junit.Assert.*;

import java.util.regex.*;
import java.util.Scanner;

/**
 * This program is run in conjunction with two classes, ToyRobot and Table.
 * This class contains the main method which executes the simulated Toy Robot on
 * a Table game.
 * <p>
 * This game allows the user to place the Toy Robot on the Table at any time, move
 * the Toy Robot forward, turn the Toy Robot 90 degrees either left or right, and
 * report its current position. The Toy Robot will start off without being placed on
 * the table and will ignore all commands except valid "PLACE" and "EXIT" commands.
 * <p>
 * The game will stop the Toy Robot from falling off the edges of the Table and the
 * user can exit the game at any stage.
 * 
 * @author Lee Manuele
 * @version 1.0
 */
public class Program
{
  // Class attributes.
  private static Table _table = new Table(); // Create a default table object 5x5 units where 0, 0 is the south western most corner.
  private static ToyRobot _robot = new ToyRobot(); // Create a robot object for our table.
  private static Scanner _scan = new Scanner(System.in); // Assists gathering input from the user via a console.
  private static boolean _continue = true; // Flag to determine program termination.
  
  /**
   * This is the main method where program execution begins.
   * <p>
   * The program accepts commands from the user, validates them and decides to either act on valid commands or ignore invalid commands.
   * The program also decides to act on or ignore commands depending on the state of the ToyRobot.
   * 
   * @param args is not used so do not provide any.
   */
  public static void main(String[] args)
  {
    help();
    
    do
    {
      // Testing all relevant variables are not null.
      assertNotNull("_table is null", _table);
      assertNotNull("_robot is null", _robot);
      assertNotNull("_scan is null", _scan);
      assertNotNull("_continue is null", _continue);
      
      // Capturing the users keyboard input for one line of text and processing it.
      commandSifter(_scan.nextLine());
    }
    while(_continue == true); // Only becomes false if the EXIT command is issued.
  }
  
  /*
   * Parameterless method that displays helpful information to the user on the console.
   */
  private static void help()
  {
    System.out.println("Valid commands are (not case sensitive)...\n\n"
                          + "\tPLACE 0, 0, NORTH (where the 0's represent horizontal and vertical placement respectively)\n"
                          + "\tMOVE (moves the Robot one unit in the direction it is facing)\n"
                          + "\tLEFT (turns the Robot left 90 degrees while not moving from its spot)\n"
                          + "\tRIGHT (turns the Robot right 90 degrees while not moving from its spot)\n"
                          + "\tREPORT (displays a report on the position and direction the Robot is facing)\n"
                          + "\tHELP (displays this message again)\n"
                          + "\tEXIT (ends the program)\n\n"
                          + "Please note, the Robot ignores commands until it has been placed on the Table\n");
  }
  
  /*
   * Takes in commands and validates them.
   * <p>
   * This method will look for valid commands and if necessary,
   * pull relevant information from them. This method then calls
   * the appropriate methods from the ToyRobot and Table instances.
   * The commandSifter method also caters for a large portion of
   * the game rules.
   * 
   * @param input is not case sensitive unexpected input by the user is ignored.
   */
  private static void commandSifter(String input)
  {
    // Testing all relevant variables are not null.
    assertNotNull("input is null", input);
    assertNotNull("_table is null", _table);
    assertNotNull("_continue is null", _continue);
    
    String in = input.toUpperCase();
    
    /*
     * Regular expression that checks for PLACED at the start of the sequence, followed by 0 or more spaces,
     * followed by a digit, followed by a comma, followed by 0 or more spaces, followed by a digit, followed 
     * by a comma, followed by 0 or more spaces, followed by one of the four main compass directions in capital
     * letters, followed by 0 or more spaces.
     */
    if(in.matches("^PLACE\\s*\\d,\\s*\\d,\\s*(NORTH|SOUTH|EAST|WEST)\\s*"))
    {
      // The three variables we need to extract from a valid PLACE command.
      int tempX = 0;
      int tempY = 0;
      String tempDirection = "";
      
      // Looks for NORTH, SOUTH, EAST or WEST.
      Pattern rxPattern = Pattern.compile("(NORTH|SOUTH|EAST|WEST)");
      Matcher rxMatcher = rxPattern.matcher(in);
      
      if(rxMatcher.find())
      {
        tempDirection = rxMatcher.group(1); // Set the direction.
      }
      
      String[] inData = in.split(","); // Separate the digits.
      inData[0] = inData[0].replace("PLACE", " "); // remove PLACE from inData[0].
      inData[0] = inData[0].trim(); // Remove whitespace to leave only the relevant x location digit.
      inData[1] = inData[1].trim();// Remove whitespace to leave only the relevant y location digit.
      tempX = Integer.parseInt(inData[0]);
      tempY = Integer.parseInt(inData[1]);
      
      boolean placeAttempt = _robot.place(tempX, tempY, tempDirection, _table.getWidth(), _table.getHeight());
      if(placeAttempt == true)
        _table.giveRobot();
    }
    
    // Exits the program.
    else if(in.matches("^EXIT"))
    {
      _continue = false;
    }
    
    // Displays the helpful text on the console.
    else if(in.matches("^HELP"))
    {
      help();
    }
    
    // Take these actions only if the ToyRobot is already on the table.
    else if(_table.getHasRobot() == true)
    {
      boolean result = false;
      switch(in)
      {
        case "MOVE":
          result =_robot.move(_table.getWidth(), _table.getHeight()); // Attempt a move.
          break;
        case "LEFT":
          result = _robot.left(); // Attempt a left turn.
          break;
        case "RIGHT":
          result = _robot.right(); // Attempt a right turn.
          break;
        case "REPORT": // Report the position and direction the ToyRobot is facing.
          System.out.println(_robot.report());
          break;
        default: // Ignoring invalid inputs while the ToyRobot is on the table.
          break;
      }
      result = false;
    }
    else{} // Ignoring invalid inputs while the ToyRobot is not on the Table.
    
  }
}