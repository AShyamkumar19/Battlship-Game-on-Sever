package cs3500.pa04.Model;

import cs3500.pa04.Controller.BattleSalvoController;
import cs3500.pa04.Player;
import cs3500.pa04.View.BoardSetupView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Represents the AI player.
 */
public class AiPlayer implements Player {
  BattleSalvoController bsg;
  Random rand = new Random();

  int height;
  int width;
  int numShips;
  List<Ship> fleet;
  TakeShot ts;
  List<Ship> sunkShips;


  @Override
  public String name() {
    return "A-goofyGoober";
  }

  /**
   * Constructor for AiPlayer
   *
   * @param bsg - controller
   * @param fleet - fleet
   */
  public AiPlayer(BattleSalvoController bsg, List<Ship> fleet,
                  int width, int height, int numShips) {
    this.bsg = bsg;
    this.fleet = fleet;
    this.width = width;
    this.height = height;
    ts = new TakeShot();
    sunkShips = new ArrayList<>();
    this.numShips = numShips;
  }

  /**
   * @param height the height of the board, range: [6, 15] inclusive
   * @param width the width of the board, range: [6, 15] inclusive
   * @param specifications a map of ship type to the number of occurrences each ship should
   *                       appear on the board
   * @return list of ships
   */
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    ArrayList<Ship> ships = new ArrayList<>();
    for (ShipType shipType : specifications.keySet()) {
      for (int i = 0; i < specifications.get(shipType); i++) {
        GameBoard gm = new GameBoard(height, width);
        DirectionType direction = rand.nextBoolean() ? DirectionType.VERTICAL
            : DirectionType.HORIZONTAL;
        List<Coord> shipCoords = gm.placeShip(shipType, direction);
        Coord startCoord = gm.getFirstCoord();
        Ship ship = new Ship(shipType, shipCoords, direction, startCoord);
        ships.add(ship);

      }
    }
    this.width = width;
    this.height = height;
    //this.numShips = ships.size();
    this.numShips = ships.size();
    System.out.println("INITIAL FLEET");
    for (Ship ship : ships) {
      for (Coord coord : ship.getPoints()) {
        System.out.print("(" + coord.getX() + " " + coord.getY() + ")");
      }
      System.out.println();
    }
    return ships;
  }

  @Override
  public List<Coord> takeShots() {

    return ts.serverTakeShots(this.height, this.width, this.numShips);
  }

  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    System.out.println("Opponent Shots");
    for (Coord coord : opponentShotsOnBoard) {
      System.out.println("(" + coord.getX() + " " + coord.getY() + ")");
    }

    List<Ship> shipsToRemove = new ArrayList<>();

    for (Ship ship : this.fleet) {
      List<Coord> shipCoords = ship.getPoints();

      for (Coord hitCoord : opponentShotsOnBoard) {
        for (Coord shipPoint : shipCoords) {
          if (shipPoint.getX() == hitCoord.getX() && shipPoint.getY() == hitCoord.getY()) {
            ship.markHit(hitCoord);
            break;
          }
        }

      }
      System.out.println("Ship Coordinates");

      for (Ship ships : this.fleet) {
        for (Coord scoord : ships.getPoints()) {
          System.out.print("(" + scoord.getX() + " " + scoord.getY() + ") ");
        }
        System.out.println();
      }
      if (ship.isSunk()) {
        System.out.println("Ship " + ship.getShipType() + " is sunk!");
        this.numShips--;
        shipsToRemove.add(ship);
      }
    }
    for (Ship ship : shipsToRemove) {
      this.fleet.remove(ship);
    }

    return opponentShotsOnBoard;
  }

  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    BoardSetupView bsv = new BoardSetupView();
    for (Coord c : shotsThatHitOpponentShips) {
      int x = c.getX();
      int y = c.getY();
      bsv.printAiShots(x, y);
    }

  }

  @Override
  public void endGame(GameResult result, String reason) {
    System.out.println("Result, " + result + ", Reason: " + reason);
  }
}