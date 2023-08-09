package cs3500.pa04.Model;

/**
 * Represents the number of units of all the ships combined.
 */
public class ShipUnits {

  /**
   * @param carrier the number of carriers
   * @param battleship the number of battleships
   * @param destroyer the number of destroyers
   * @param submarine the number of submarines
   * @return the number of units of a ship
   */
  public int getShipUnits(int carrier, int battleship, int destroyer, int submarine) {
    int carrierUnits = ShipType.CARRIER.getLength() * carrier;
    int battleshipUnits = ShipType.BATTLESHIP.getLength() * battleship;
    int destroyerUnits = ShipType.DESTROYER.getLength() * destroyer;
    int submarineUnits = ShipType.SUBMARINE.getLength() * submarine;
    return carrierUnits + battleshipUnits + destroyerUnits + submarineUnits;
  }
}
