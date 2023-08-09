package cs3500.pa04.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a ship.
 */
public class Ship {
  private ShipType type;
  private List<Coord> points;
  private DirectionType direction;
  private int shipLength;
  private Coord firstCoord;

  /**
   * @param type the type of ship
   * @param points the points of the ship
   * @param direction the direction of the ship
   * @param firstCoord the first coordinate of the ship
   */
  public Ship(ShipType type, List<Coord> points, DirectionType direction, Coord firstCoord) {
    this.type = type;
    this.points = new ArrayList<>(points);
    this.direction = direction;
    this.shipLength = type.getLength();
    this.firstCoord = firstCoord;
    setPoints();
  }

  public ShipType getShipType() {
    return type;
  }

  /**
   * @return the points of the ship
   */
  public List<Coord> getPoints() {
    List<Coord> shipPoints = new ArrayList<>(points);
    return shipPoints;
  }

  public void setPoints() {
    List<Coord> shipPoints = new ArrayList<>(points);
    if (this.direction == DirectionType.HORIZONTAL) {
      for (int i = 1; i < shipLength; i++) {
        shipPoints.add(new Coord(firstCoord.getX() + i, firstCoord.getY()));
      }
    } else {
      for (int i = 1; i < shipLength; i++) {
        shipPoints.add(new Coord(firstCoord.getX(), firstCoord.getY() + i));
      }
    }
    points = shipPoints;
  }

  public Coord getFirstCoord() {
    return firstCoord;
  }

  public DirectionType getDirection() {
    return direction;
  }

  public int getShipLength() {
    return shipLength;
  }

  public void markHit(Coord c) {
    for (Coord point : getPoints()) {
      if (point.getX() == c.getX() && point.getY() == c.getY()) {
        points.remove(point);
        break;
      }
    }
  }

  public boolean isSunk() {
    return points.isEmpty();
  }

}
