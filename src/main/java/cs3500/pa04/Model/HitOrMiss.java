package cs3500.pa04.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents whether a hit or miss occurred.
 */
public class HitOrMiss {

  /**
   * @param points the points to be checked
   * @param aiBoard the board to be checked
   * @param trackerBoard the tracker board to be checked
   * @return the list of successful hits
   */
  public List<Coord> humanHit(List<Coord> points, GameBoard aiBoard, char[][] trackerBoard) {
    ArrayList<Coord> humanSuccessfulHits = new ArrayList<>();
    for (Coord c : points) {
      int x = c.getX();
      int y = c.getY();
      if (aiBoard.getBoard(x, y) != '0') {
        aiBoard.setBoard(x, y, 'H');
        trackerBoard[x][y] = 'H';
        humanSuccessfulHits.add(c);
        aiBoard.sinkShip();
      } else {
        aiBoard.setBoard(x, y, 'M');
        trackerBoard[x][y] = 'M';
      }
    }
    return humanSuccessfulHits;
  }

  /**
   * @param points the points to be checked
   * @param HumanBoard the board to be checked
   * @return the list of successful hits
   */
  public List<Coord> aiHit(List<Coord> points, GameBoard HumanBoard) {
    ArrayList<Coord> aiSuccessfulHits = new ArrayList<>();
    for (Coord c : points) {
      int x = c.getX();
      int y = c.getY();
      if (HumanBoard.getBoard(x, y) != '0') {
        HumanBoard.setBoard(x, y, 'H');
        aiSuccessfulHits.add(c);
        HumanBoard.sinkShip();
      } else {
        HumanBoard.setBoard(x, y, 'M');
      }
    }
    return aiSuccessfulHits;
  }

  /**
   * @param shots the shots to be checked
   * @param ships the ships to be checked
   * @return the list of successful hits
   */
  public List<Coord> aiServerBoardHits(List<Coord> shots, List<Ship> ships) {
    ArrayList<Coord> oppenentSuccessfulHits = new ArrayList<>();
    for (Ship s : ships) {
      for (Coord c : shots) {
        Coord firstCoord = s.getFirstCoord();
        if (s.getDirection() == DirectionType.HORIZONTAL) {
          for (int i = 0; i < s.getShipLength(); i++) {
            if (c.getX() == firstCoord.getX() + i && c.getY() == firstCoord.getY()) {
              oppenentSuccessfulHits.add(c);
            }
          }
        } else {
          for (int i = 0; i < s.getShipLength(); i++) {
            if (c.getX() == firstCoord.getX() && c.getY() == firstCoord.getY() + i) {
              oppenentSuccessfulHits.add(c);
            }
          }
        }
      }
    }
    System.out.println("Opponent successful hits: " + oppenentSuccessfulHits);
    return oppenentSuccessfulHits;
  }
}
