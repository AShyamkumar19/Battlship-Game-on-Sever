package cs3500.pa04.View;

/**
 * Represents the board setup view.
 * just prints everything
 */
public class BoardSetupView {
  private Readable rd;
  private Appendable ap;


  /**
   * Prints the welcome message.
   */
  public void printWelcomeMessage() {
    System.out.println("Hello! Welcome to the OOD BattleSalvo Game! ");
  }

  /**
   * Prints the dimensions message.
   */
  public void printDimensionsMessage() {
    System.out.println("Please enter a valid height and width below: ");
  }

  /**
   * Prints the line.
   */
  public void printLine() {
    System.out.println("------------------------------------------------------");
  }

  /**
   * Prints the invalid dimensions message.
   */
  public void printInvalidDimensions() {
    System.out.println("Uh Oh! You've entered invalid dimensions. "
        + "Please remember that the height and width\nof the game must be in the range (6, 15), "
        + "inclusive. Try again! ");
  }

  /**
   * @param maxShips - the max number of ships
   */
  public void printFleetMessage(int maxShips) {
    System.out.println("Please enter your fleet in the order "
        + "[Carrier, Battleship, Destroyer, Submarine].\n"
        + "Remember, your fleet may not exceed size " + maxShips + ".");
  }

  /**
   * @param maxShips - the max number of ships
   */
  public void printInvalidFleetMessage(int maxShips) {
    System.out.println("Uh Oh! You've entered invalid fleet sizes.\n"
        + "Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].\n"
        + "Remember, your fleet may not exceed size " + maxShips + ".");
  }

  /**
   * @param maxShots - maximum shots allowed
   */
  public void printTakeShots(int maxShots) {
    System.out.println("Please enter + " + maxShots + " Shots (Ex: x y): ");
  }

  /**
   * @param maxShots - maximum shots allowed
   */
  public void printInvalidShots(int maxShots) {
    System.out.println("Uh Oh! You've entered invalid shots.\n"
        + "Please enter + " + maxShots + " Shots: ");
  }

  /**
   * @param x - x coordinate
   * @param y - y coordinate
   */
  public void printAiShots(int x, int y) {
    System.out.println("AI got hit at point: (" + x + ", " + y + ")");
  }

  /**
   * @param x - x coordinate
   * @param y - y coordinate
   */
  public void printHumanShots(int x, int y) {
    System.out.println("You got hit at point: (" + x + ", " + y + ")");
  }

  /**
   * Prints the user winner message.
   */
  public void printUserWinner() {
    System.out.println("Congratulations! You won!");
  }

  /**
   * Prints the user loser message.
   */
  public void printLoser() {
    System.out.println("Sorry! You lost!");
  }

  /**
   * Prints the tie message.
   */
  public void printTie() {
    System.out.println("It's a tie!");
  }
}
