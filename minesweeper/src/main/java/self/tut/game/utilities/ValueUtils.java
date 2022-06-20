package self.tut.game.utilities;

import java.util.Random;

public class ValueUtils {

  static Random random = new Random();

  /**
   * Utility classes should not have public constructors (java:S1118)
   */
  private ValueUtils() {
    throw new IllegalStateException("Utility class");
  }
	

  /**
   * This method return a number
   * 
   * @param size
   * @return
   */
  public static int randomNumberGenerator(int size) {
    return random.nextInt(size);
  }
	
  /**
   * 
   * 
   * @return
   */
  public static int randomNumberBy4NoSize() {
    return random.nextInt() % 4;
  }	
	

	
  /**
   * This method takes in x and y along with field visible to validate for invalid values.
   * 
   * @param x
   * @param y
   * @param fieldVisible
   * @return
   */
  public static boolean checkForInvalidValues(int x, int y, int[][] fieldVisible) {

    return (x < 0 || x > 9 || y < 0 || y > 9 || 0 != fieldVisible[x][y]) ? true : false;

  }

}
