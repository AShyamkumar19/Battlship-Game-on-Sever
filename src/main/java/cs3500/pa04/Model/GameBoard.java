package cs3500.pa04.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents the game board.
 */
public class GameBoard {
  private final int height;
  private final int width;
  private final char[][] board;
  private int sunkShips;
  Random rand = new Random();
  private List<Ship> shipsPlace = new ArrayList<>();
  Coord firstCoord;

  /**
   * @param height the height of the board
   * @param width the width of the board
   */
  public GameBoard(int height, int width) {
    this.height = height;
    this.width = width;
    this.board = new char[height][width];
    createBoard();
  }

  /**
   * Creates the blank board.
   */
  public void createBoard() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        this.board[i][j] = '0';
      }
    }
  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }

  public char getBoard(int x, int y) {
    return board[x][y];
  }

  public void setBoard(int x, int y, char h) {
    this.board[x][y] = h;
  }

  public void sinkShip() {
    sunkShips++;
  }

  public int getShipsSunk() {
    return sunkShips;
  }

  /**
   * @param ship the ship to be placed
   * @return the coordinates of the ship
   */
  public List<Coord> placeShip(ShipType ship, DirectionType direction) {
    int length = ship.getLength();
    boolean vertical = direction == DirectionType.VERTICAL;
    char symbol = ship.getSymbol();
    int x = 0;
    int y = 0;
    boolean validPosition = false;
    ArrayList<Coord> points = new ArrayList<>();
    firstCoord = null;

    while (!validPosition) {
      if (vertical) {
        y = rand.nextInt(height - length + 1);
        x = rand.nextInt(width);
      } else {
        y = rand.nextInt(height);
        x = rand.nextInt(width - length + 1);
      }
      points.add(new Coord(x, y));
      if (firstCoord == null) {
        firstCoord = new Coord(x, y);
      }
      validPosition = openSpot(ship, x, y, vertical);
    }
    if (vertical) {
      for (int i = 0; i < length; i++) {
        board[y + i][x] = symbol;
      }
    } else {
      for (int i = 0; i < length; i++) {
        board[y][x + i] = symbol;
      }
    }
    Ship newShip = new Ship(ship, points, direction, points.get(points.size() - 1));
    shipsPlace.add(newShip);


    return points;
  }

  public Coord getFirstCoord() {
    return firstCoord;
  }

  public List<Ship> getShipsPlace() {
    return shipsPlace;
  }

  /**
   * @param ship - the ship to be placed
   * @param x - the x or x coordinate
   * @param y - the column or y coordinate
   * @param vertical - whether the ship is vertical or horizontal
   * @return boolean
   */
  public boolean openSpot(ShipType ship, int x, int y, boolean vertical) {
    int length = ship.getLength();
    if (vertical) {
      if (y + length >= height) {
        return false;
      }
      for (int i = 0; i < length; i++) {
        if (board[y + i][x] != '0') {
          return false;
        }
      }

    } else {

      if (x + length >= width) {
        return false;
      }
      for (int i = 0; i < length; i++) {
        if (board[y][x + i] != '0') {
          return false;
        }
      }

    }
    return true;
  }
}
