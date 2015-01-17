import static org.junit.Assert.*;

/**
 * Table is a class which can can be used by a ToyRobot allowing it to be placed on the
 * surface and moved around. 
 * <p>
 * The table is of rectangular shape and must have a height and width both greater than 
 * 0, but less than the size of a Java integers positive maximum. The dimensions can be
 * loaded as a default 5x5 units, or set manually.
 * <p>
 * Point 0, 0 on the Table instance is the south western most corner.
 * 
 * @author Lee Manuele
 * @version 1.0
 * @since 1.0
 */
public class Table
{
  // Class attributes.
  private int _width = 1;
  private int _height = 1;
  private boolean _hasRobot = false;
  
  /**
   * Parameterless constructor which instantiates a square table 5 units high and
   * 5 units wide.
   */
  public Table()
  {
        _width = 5;
        _height = 5;
        _hasRobot = false;
  }
  
  /**
   * Access method for the table width.
   * 
   * @return the width of the table.
   */
  public int getWidth()
  {
    // Testing all relevant variables are not null.
    assertNotNull(_width); // Ensure the _width variable has been initialized.
    
    return _width;
  }

  /**
   * Access method for the table height.
   * 
   * @return the height of the table.
   */
  public int getHeight()
  {
    // Testing all relevant variables are not null.
    assertNotNull(_height);
    
    return _height;
  }
  
  /**
   * Access method that determines if a ToyRobot has been placed on the table.
   * 
   * @return a boolean, true if a ToyRobot currently exists on the table, false otherwise.
   */
  public boolean getHasRobot()
  {
    // Testing all relevant variables are not null.
    assertNotNull(_hasRobot);
    return _hasRobot;
  }
  
  /**
   * This method tells the table it has a Toy Robot that has been successfully placed.
   */
  public void giveRobot()
  {
    _hasRobot = true;
  }
}
