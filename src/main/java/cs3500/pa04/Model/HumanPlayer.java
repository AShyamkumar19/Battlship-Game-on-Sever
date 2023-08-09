package cs3500.pa04.Model;

import cs3500.pa04.Controller.BattleSalvoController;
import cs3500.pa04.Player;
import cs3500.pa04.View.BoardSetupView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Represents the human player.
 */
public class HumanPlayer implements Player {
  BattleSalvoController gm;
  Random rand = new Random();

  @Override
  public String name() {
    return "User Player";
  }

  public HumanPlayer(BattleSalvoController gm) {
    this.gm = gm;
  }

  @Override
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    ArrayList<Ship> ships = new ArrayList<>();
    GameBoard gm = new GameBoard(height, width);
    for (ShipType shipType : specifications.keySet()) {
      for (int i = 0; i < specifications.get(shipType); i++) {
        DirectionType direction = rand.nextBoolean() ? DirectionType.HORIZONTAL
            : DirectionType.VERTICAL;
        List<Coord> shipCoords = gm.placeShip(shipType, direction);
        Coord startCoord = gm.getFirstCoord();
        Ship ship = new Ship(shipType, shipCoords, direction, startCoord);
        ships.add(ship);

      }
    }
    return ships;
  }

  @Override
  public List<Coord> takeShots() {
    TakeShot ts = new TakeShot();
    return ts.takeShots(gm.getBoardSetup());
  }

  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    BoardSetupView bsv = new BoardSetupView();
    for (Coord c : opponentShotsOnBoard) {
      int x = c.getX();
      int y = c.getY();
      bsv.printAiShots(x, y);
    }
    return opponentShotsOnBoard;
  }

  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    BoardSetupView bsv = new BoardSetupView();
    for (Coord c : shotsThatHitOpponentShips) {
      int x = c.getX();
      int y = c.getY();
      bsv.printHumanShots(x, y);
    }
  }

  @Override
  public void endGame(GameResult result, String reason) {
    return;
  }
}
