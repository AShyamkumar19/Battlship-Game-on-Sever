package cs3500.pa04.Controller;

import cs3500.pa04.Model.Validation;
import cs3500.pa04.View.BoardSetupView;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Scanner;

/**
 * Represents the setup of the board.
 */
public class BoardSetup {
  private int height;
  private int width;
  private int carrier;
  private int battleship;
  private int destroyer;
  private int submarine;

  private int totalShips;
  private final Readable rd;
  private final Appendable ap;

  /**
   * Constructor for BoardSetup.
   *
   * @param width - width of board
   * @param height - height of board
   * @param totalShips - total number of ships
   */
  public BoardSetup(int width, int height, int totalShips) {
    this.rd = new InputStreamReader(System.in);
    this.ap = System.out;
    this.width = width;
    this.height = height;
    this.totalShips = totalShips;
  }

  public BoardSetup(Readable rd, Appendable ap) {
    this.rd = Objects.requireNonNull(rd);
    this.ap = Objects.requireNonNull(ap);
  }

  /**
   * Sets up the board.
   */
  public void setupBoard() {
    BoardSetupView bsv = new BoardSetupView();
    final Scanner s = new Scanner(rd);
    final Validation valid = new Validation();

    bsv.printWelcomeMessage();
    bsv.printDimensionsMessage();
    bsv.printLine();

    height = valid.validateDimensions(s);
    width = valid.validateDimensions(s);

    int maxLength = Math.min(height, width);

    bsv.printLine();
    bsv.printFleetMessage(maxLength);
    bsv.printLine();

    carrier = valid.validateNumber(s);
    battleship = valid.validateNumber(s);
    destroyer = valid.validateNumber(s);
    submarine = valid.validateNumber(s);

    valid.validateFleet(maxLength, carrier, battleship, destroyer, submarine);

    int ships = carrier + battleship + destroyer + submarine;
    setTotalShips(ships);

  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }

  public int getCarrier() {
    return carrier;
  }

  public int getBattleship() {
    return battleship;
  }

  public int getDestroyer() {
    return destroyer;
  }

  public int getSubmarine() {
    return submarine;
  }

  public void setTotalShips(int allShips) {
    totalShips = allShips;
  }

  public int getTotalShips() {
    return totalShips;
  }
}
