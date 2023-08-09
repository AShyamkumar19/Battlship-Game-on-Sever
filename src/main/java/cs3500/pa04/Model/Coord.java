package cs3500.pa04.Model;

/**
 * Represents the coordinates of a cell.
 */
public class Coord {
  private  final int x;
  private final int y;

  public Coord(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    Coord other = (Coord) obj;
    return x == other.x && y == other.y;
  }

  @Override
  public int hashCode() {
    return 31 * x + y;
  }
}
