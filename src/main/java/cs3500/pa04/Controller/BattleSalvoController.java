package cs3500.pa04.Controller;

import static cs3500.pa04.View.PrintBoard.printAiBoard;
import static cs3500.pa04.View.PrintBoard.printBoard;

import cs3500.pa04.Model.AiPlayer;
import cs3500.pa04.Model.Coord;
import cs3500.pa04.Model.GameBoard;
import cs3500.pa04.Model.HitOrMiss;
import cs3500.pa04.Model.HumanPlayer;
import cs3500.pa04.Model.Ship;
import cs3500.pa04.Model.ShipType;
import cs3500.pa04.Model.ShipUnits;
import cs3500.pa04.Model.TrackerBoard;
import cs3500.pa04.Player;
import cs3500.pa04.View.BoardSetupView;
import cs3500.pa04.View.PrintBoard;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the controller for the game of BattleSalvo. For single player/offline mode
 */
public class BattleSalvoController {
  private BoardSetup bs;
  List<Ship> fleet = new ArrayList<>();

  /**
   * Gets Board info
   */
  public void setupGame() {
    final ShipUnits su = new ShipUnits();
    bs = new BoardSetup(new InputStreamReader(System.in), System.out);

    bs.setupBoard();
    int height = bs.getHeight();
    int width = bs.getWidth();
    int carrier = bs.getCarrier();
    int battleship = bs.getBattleship();
    int destroyer = bs.getDestroyer();
    int submarine = bs.getSubmarine();
    int totalShipUnits = su.getShipUnits(carrier, battleship, destroyer, submarine);
    startGame(height, width, carrier, battleship, destroyer, submarine, totalShipUnits, bs);
  }

  /**
   * Sets up baord
   *
   * @param height - height of board
   * @param width - width of board
   * @param carrier - number of carrier ships
   * @param battleship - number of battleship ships
   * @param destroyer - number of destroyer ships
   * @param submarine - number of submarine ships
   * @param totalShipUnits - total number of ship units
   */
  private void startGame(int height, int width, int carrier, int battleship, int destroyer,
                         int submarine, int totalShipUnits, BoardSetup bs) {
    this.bs = bs;
    Player p1 = new HumanPlayer(this);
    Player p2 = new AiPlayer(this, null, 0, 0, 0);
    final PrintBoard pb = new PrintBoard();
    final TrackerBoard tb = new TrackerBoard();

    Map<ShipType, Integer> specifications = new HashMap<>();
    specifications.put(ShipType.SUBMARINE, submarine);
    specifications.put(ShipType.DESTROYER, destroyer);
    specifications.put(ShipType.BATTLESHIP, battleship);
    specifications.put(ShipType.CARRIER, carrier);

    List<Ship> playerShips = p1.setup(height, width, specifications);
    List<Ship> aiShips = p2.setup(height, width, specifications);

    this.fleet = aiShips;

    GameBoard humanBoard = new GameBoard(height, width);
    for (Ship ship : playerShips) {
      humanBoard.placeShip(ship.getShipType(), ship.getDirection());
    }

    GameBoard aiBoard = new GameBoard(height, width);
    for (Ship ship : aiShips) {
      aiBoard.placeShip(ship.getShipType(), ship.getDirection());
    }

    char[][] trackerBoard = tb.trackerBoard(height, width);

    // pb.printBoard(aiBoard, "Opponent's Board Data"); // uncomment to see opponent's board
    printAiBoard(trackerBoard, "Opponent's Board");
    printBoard(humanBoard, "Your Board");
    runGame(humanBoard, aiBoard, trackerBoard, totalShipUnits, this);
  }

  public BoardSetup getBoardSetup() {
    return this.bs;
  }

  /**
   * Runs the game
   *
   * @param humanBoard - human player's board
   * @param aiBoard - ai player's board
   * @param trackerBoard - tracker board
   * @param totalShipUnits - total number of ship units
   * @param bsg - battlesalvo controller
   */
  private void runGame(GameBoard humanBoard, GameBoard aiBoard, char[][] trackerBoard,
                       int totalShipUnits, BattleSalvoController bsg) {
    HumanPlayer humanPlayer = new HumanPlayer(bsg);
    AiPlayer aiPlayer = new AiPlayer(bsg, this.fleet, bsg.getBoardSetup().getWidth(),
        bsg.getBoardSetup().getHeight(), 0);
    HitOrMiss hm = new HitOrMiss();
    BoardSetupView bsv = new BoardSetupView();
    boolean isGameOver = false;
    while (!isGameOver) {
      List<Coord> humanShots = humanPlayer.takeShots();
      List<Coord> aiShots = aiPlayer.takeShots();

      List<Coord> humanShotsOnBoard = humanPlayer.reportDamage(hm.aiHit(aiShots, humanBoard));
      List<Coord> aiShotsOnBoard = aiPlayer.reportDamage(hm.humanHit(humanShots, aiBoard,
          trackerBoard));

      humanPlayer.successfulHits(humanShotsOnBoard);
      aiPlayer.successfulHits(aiShotsOnBoard);

      printAiBoard(trackerBoard, "Opponent's Board");
      printBoard(humanBoard, "Your Board");

      if (humanBoard.getShipsSunk() == totalShipUnits) {
        bsv.printLoser();
        isGameOver = true;
        break;
      }
      if (aiBoard.getShipsSunk() == totalShipUnits) {
        bsv.printUserWinner();
        isGameOver = true;
        break;
      }
      if (humanBoard.getShipsSunk() == totalShipUnits && aiBoard.getShipsSunk() == totalShipUnits) {
        bsv.printTie();
        isGameOver = true;
        break;
      }

    }
  }
}