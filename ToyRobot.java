import static org.junit.Assert.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ToyRobot creates a toy robot which can be placed on a Table and moved around.
 * <p>
 * This class allows a toy robot to be placed on an instance of a Table, move around
 * one unit at a time, turn left or right 90 degrees and report its current position.
 * The toy robot is not allowed to fall off the edge or be placed off the table.
 * 
 * @author Lee Manuele
 * @version 1.0
 *
 */
class ToyRobot
{
  // Class attributes
  private String _facing = "NORTH";
  private int _xPosition = 0;
  private int _yPosition = 0;
  
  /**
   * Constructor that creates an instance of the ToyRobot with a position of 0, 0 while facing north.
   * An instance of the Table class is created but the robot is not placed on the table. 
   * <p>The Table object created is a default 5x5 units.
   */
  public ToyRobot()
  {
    _facing = "NORTH";
    _xPosition = 0;
    _yPosition = 0;
  }
  
  /**
   * Places the ToyRobot on the Table at the coordinates and gives it a direction to face.
   * Given valid parameter input, this method will always place a ToyRobot on a Table.
   * 
   * @param xPos a valid X coordinate on the Table supplied.
   * @param yPos a valid Y coordinate on the Table supplied.
   * @param facing one of the four major compass directions, not case sensitive.
   * @param tableWidth the width of the table used.
   * @param tableHeight the height of the table used.
   * @return true if the placement of the ToyRobot was successful, false otherwise.
   */
  public boolean place(int xPos, int yPos, String facing, int tableWidth, int tableHeight)
  {
    // Testing all relevant variables are not null.
    assertNotNull("xPos is null", xPos);
    assertNotNull("yPos is null", xPos);
    assertNotNull("_xPosition is null", _xPosition);
    assertNotNull("_yPosition is null", _yPosition);
    assertNotNull("facing is null", facing);
    assertNotNull("_facing is null", _facing);
    assertNotNull("tableWidth is null", tableWidth);
    assertNotNull("tableHeight is null", tableHeight);
    
    // Testing all relevant integers for larger than valid values.
    assertFalse("xPos is larger than a valid integer.", xPos > Integer.MAX_VALUE);
    assertFalse("yPos is larger than a valid integer.", yPos > Integer.MAX_VALUE);
    assertFalse("tableWidth is larger than a valid integer.", tableWidth > Integer.MAX_VALUE);
    assertFalse("tableHeight is larger than a valid integer.", tableHeight > Integer.MAX_VALUE);
    
    if((xPos < 0 ) || (yPos < 0) || (tableWidth < 1) || (tableHeight < 1))
      return false; // Reject if one or more passed integer variables less than valid values.
    
    if((xPos >= 0 && xPos < tableWidth) && (yPos >= 0 && yPos < tableHeight)) // The placement lands somewhere on the table.
    {
      Pattern pat = Pattern.compile("(NORTH|SOUTH|EAST|WEST)");
      Matcher mat = pat.matcher(facing);
      
      if(mat.find()) // The direction is a valid entry.
      {
        _xPosition = xPos;
        _yPosition = yPos;
        _facing = facing;
        return true;
      }
      else // The direction was an invalid entry.
      {
        return false; 
      }
    }
    else // The placement coordinates were not on the table.
    {
      return false;
    }
  }
  
  /**
   * Turns the ToyRobot left 90 degrees while remaining in its current position.
   * 
   * @return true if the ToyRobot was turned successfully, false otherwise.
   */
  public boolean left() // Turn the ToyRobot 90 degrees left.
  {
 // Testing all relevant variables are not null.
    assertNotNull("_facing is null", _facing);
    switch(_facing)
    {
    case "NORTH":
      _facing = "WEST";
      return true;
    case "WEST":
      _facing = "SOUTH";
      return true;
    case "SOUTH":
      _facing = "EAST";
      return true;
    case "EAST":
      _facing = "NORTH";
      return true;
    }
    fail("_facing does not have a valid value.");
    return false;
  }
  
  /**
   * Turns the ToyRobot right 90 degrees while remaining in its current position.
   * 
   * @return true if the ToyRobot was turned successfully, false otherwise.
   */
  public boolean right()
  {
 // Testing all relevant variables are not null.
    assertNotNull("_facing is null", _facing);
    switch(_facing) // Turn the ToyRobot right 90 degrees.
    {
    case "NORTH":
      _facing = "EAST";
      return true;
    case "EAST":
      _facing = "SOUTH";
      return true;
    case "SOUTH":
      _facing = "WEST";
      return true;
    case "WEST":
      _facing = "NORTH";
      return true;
    }
    fail("_facing does not have a valid value.");
    return false;
  }
  
  /**
   * Moves the ToyRobot 1 unit in the direction it is currently facing. The
   * ToyRobot cannot fall off the table. Will not perform the operation if
   * the ToyRobot has not been placed on the Table.
   * 
   * @return true if the ToyRobot is already on the table and has successfully
   * moved to a valid spot on the table, false otherwise.
   */
  public boolean move(int tableWidth, int tableHeight)
  {
 // Testing all relevant variables are not null.
    assertNotNull("_xPosition is null", _xPosition);
    assertNotNull("_yPosition is null", _yPosition);
    assertNotNull("_facing is null", _facing);
    assertNotNull("tableWidth parameter passed is null", tableWidth);
    assertNotNull("tableHeight parameter passed is null", tableHeight);
    
    // Testing all relevant integers for larger than valid values.
    assertFalse("_xPosition is larger than a valid integer.", _xPosition > Integer.MAX_VALUE);
    assertFalse("_yPosition is larger than a valid integer.", _yPosition > Integer.MAX_VALUE);
    assertFalse("tableWidth is larger than a valid integer.", tableWidth > Integer.MAX_VALUE);
    assertFalse("tableHeight is larger than a valid integer.", tableHeight > Integer.MAX_VALUE);
    
    
    if((tableWidth < 1) || (tableHeight < 1)) // Decline tables with 0 or negative height or width.
      return false;
    
    if("NORTH".equals(_facing))
    {
      if(_yPosition + 1 == tableHeight) // Off the table
        return false;
      else
      {
        _yPosition++; // Move 1 unit
        return true;
      }
    }
    else if("SOUTH".equals(_facing))
    {
      if(_yPosition - 1 < 0) // Off the table
        return false;
      else
      {
        _yPosition--; // Move 1 unit
        return true;
      }
    }
    else if("EAST".equals(_facing))
    {
      if(_xPosition + 1 == tableWidth) // Off the table
        return false;
      else
      {
        _xPosition++; // Move 1 unit
        return true;
      }
    }
    else if("WEST".equals(_facing))
    {
      if(_xPosition - 1 < 0) // Off the table
        return false;
      else
      {
        _xPosition--; // Move 1 unit
        return true;
      }
    }
    else
      fail("Program execution should not reach this point");
      return false;
  }
  
  /**
   * This method returns a report on the state of the ToyRobot. The details are...
   * <ul>
   * <li>X position</li>
   * <li>Y position</li>
   * <li>direction faced</li>
   * </ul>
   * <p>
   * String example:
   * "\nOutput: 3, 2, NORTH\n"
   <p>
   * Will not perform operation if the ToyRobot has not been placed on the table.
   * 
   * @return a report on the position and direction the ToyRobot is facing.

   */
  public String report()
  {
    // Testing all relevant variables are not null.
    assertNotNull("_xPosition is null", _xPosition);
    assertNotNull("_yPosition is null", _yPosition);
    assertNotNull("_facing is null", _facing);
    
    String report = "\nOutput: " + String.valueOf(_xPosition) + 
                    ", " + String.valueOf(_yPosition) + 
                    ", " + _facing + "\n";
      
    return report;
  }
}