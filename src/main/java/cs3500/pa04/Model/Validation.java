package cs3500.pa04.Model;

import cs3500.pa04.View.BoardSetupView;
import java.util.Scanner;

/**
 * Represents the validation for all user inputs.
 */
public class Validation {

  /**
   * @param s - user input
   * @return int - the valid number
   */
  public int validateDimensions(Scanner s) {
    BoardSetupView bsv = new BoardSetupView();
    int dimension;

    while (!s.hasNextInt() || (dimension = s.nextInt()) < 6 || dimension > 15) {
      if (!s.hasNextInt()) {
        bsv.printLine();
        bsv.printInvalidDimensions();
        s.next();
      } else {
        bsv.printLine();
        bsv.printInvalidDimensions();
      }
    }

    return dimension;
  }

  /**
   * @param s - user input
   * @return int - the valid number
   */
  public int validateNumber(Scanner s) {
    BoardSetupView bsv = new BoardSetupView();
    int number;
    while (!s.hasNextInt()) {
      bsv.printLine();
      bsv.printInvalidDimensions();
      s.next();
    }
    number = s.nextInt();
    return number;
  }

  /**
   * @param maxLength - the max length of the board
   * @param carrier - the number of carriers
   * @param battleship - the number of battleships
   * @param destroyer - the number of destroyers
   * @param submarine - the number of submarines
   */
  public void validateFleet(int maxLength, int carrier, int battleship,
                            int destroyer, int submarine) {
    BoardSetupView bsv = new BoardSetupView();
    Scanner s = new Scanner(System.in);
    while ((carrier + battleship + destroyer + submarine > maxLength)
        || (carrier <= 0 || battleship <= 0 || destroyer <= 0 || submarine <= 0)) {
      bsv.printLine();
      bsv.printInvalidFleetMessage(maxLength);
      carrier = validateNumber(s);
      battleship = validateNumber(s);
      destroyer = validateNumber(s);
      submarine = validateNumber(s);
    }
  }
}
