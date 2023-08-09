package cs3500.pa04.Model;

/**
 * Represents the type of ship and its attributes.
 */
public enum ShipType {
  CARRIER(6, 'C'),
  BATTLESHIP(5, 'B'),
  SUBMARINE(3, 'S'),
  DESTROYER(4, 'D');

  private final int length;
  private final char symbol;

  ShipType(int length, char c) {
    this.length = length;
    this.symbol = c;
  }

  public int getLength() {
    return length;
  }

  @Override
  public String toString() {
    return String.valueOf(length);
  }

  public char getSymbol() {
    return symbol;
  }
}
