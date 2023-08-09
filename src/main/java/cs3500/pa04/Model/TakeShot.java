package cs3500.pa04.Model;

import cs3500.pa04.Controller.BoardSetup;
import cs3500.pa04.View.BoardSetupView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Represents the shots taken by the user.
 */
public class TakeShot {
  BoardSetupView bsv = new BoardSetupView();
  Scanner sc = new Scanner(System.in);
  ArrayList<Coord> history;
  Random rand;

  public TakeShot() {
    history = new ArrayList<>();
    rand = new Random();
  }

  /**
   * @return the list of shots taken by the user. This won't be tested, the bottom method will
   */
  public List<Coord> takeShots(BoardSetup bs) {
    bsv.printTakeShots(bs.getTotalShips());
    ArrayList<Coord> shots = new ArrayList<>();
    int invalidShotsCount = 0;
    while (shots.size() < bs.getTotalShips()) {
      String line = sc.nextLine().trim();
      if (line.isEmpty()) {
        break;
      }
      String[] coordinates = line.split(" ");
      if (coordinates.length != 2) {
        bsv.printLine();
        bsv.printInvalidShots(bs.getTotalShips());
        invalidShotsCount++;
        if (invalidShotsCount >= bs.getTotalShips()) {
          break;
        }
        continue;
      }
      try {
        int x = Integer.parseInt(coordinates[0]);
        int y = Integer.parseInt(coordinates[1]);
        if (x < 0 || x >= bs.getHeight() || y < 0 || y >= bs.getWidth()) {
          bsv.printLine();
          bsv.printInvalidShots(bs.getTotalShips());
          invalidShotsCount++;
          if (invalidShotsCount >= bs.getTotalShips()) {
            break;
          }
          continue;
        }
        Coord shot = new Coord(x, y);
        shots.add(shot);
      } catch (NumberFormatException e) {
        bsv.printLine();
        bsv.printInvalidShots(bs.getTotalShips());
        invalidShotsCount++;
        if (invalidShotsCount >= bs.getTotalShips()) {
          break;
        }
      }
    }
    return shots;
  }

  /**
   * @return the list of shots taken by the AI.
   */
  public List<Coord> aiTakeShots(BoardSetup bs) {
    Random r = new Random();
    ArrayList<Coord> shots = new ArrayList<>();
    for (int i = 0; i < bs.getTotalShips(); i++) {
      int x = r.nextInt(bs.getHeight());
      int y = r.nextInt(bs.getWidth());
      Coord shot = new Coord(x, y);
      shots.add(shot);
    }
    return shots;
  }

  /**
   * @return the list of shots taken by the Server AI.
   */
  public List<Coord> serverTakeShots(int height, int width, int totalShips) {
    ArrayList<Coord> shots = new ArrayList<>();

    while (shots.size() < totalShips) {
      Coord coord = generateRandomCoord(height, width);

      boolean isDuplicate = false;
      for (Coord pastCoord : history) {
        if (coord.getX() == pastCoord.getX() && coord.getY() == pastCoord.getY()) {
          isDuplicate = true;
          break;
        }
      }

      for (Coord currCoord : shots) {
        if (coord.getX() == currCoord.getX() && coord.getY() == currCoord.getY()) {
          isDuplicate = true;
          break;
        }
      }

      if (!isDuplicate) {
        shots.add(coord);
        System.out.println("Server shot: " + coord.getX() + " " + coord.getY());
      }
    }

    history.addAll(shots);
    return shots;
  }

  private Coord generateRandomCoord(int height, int width) {
    int x = rand.nextInt(width);
    int y = rand.nextInt(height);
    Coord shot = new Coord(x, y);
    return shot;
  }

  /**
   * @param bs the board setup
   * @param input the string input containing the shots (e.g., "0 0\n5 5\n7 8\n")
   * @return the list of shots
   */
  public List<Coord> takeShotsFromString(BoardSetup bs, String input) {
    bsv.printTakeShots(bs.getTotalShips());
    ArrayList<Coord> shots = new ArrayList<>();
    int invalidShotsCount = 0;
    String[] lines = input.trim().split("\n");
    for (String line : lines) {
      String[] coordinates = line.trim().split(" ");
      if (coordinates.length != 2) {
        bsv.printLine();
        bsv.printInvalidShots(bs.getTotalShips());
        invalidShotsCount++;
        if (invalidShotsCount >= bs.getTotalShips()) {
          break;
        }
        continue;
      }
      try {
        int x = Integer.parseInt(coordinates[0]);
        int y = Integer.parseInt(coordinates[1]);
        if (x < 0 || x >= bs.getHeight() || y < 0 || y >= bs.getWidth()) {
          bsv.printLine();
          bsv.printInvalidShots(bs.getTotalShips());
          invalidShotsCount++;
          if (invalidShotsCount >= bs.getTotalShips()) {
            break;
          }
          continue;
        }
        Coord shot = new Coord(x, y);
        shots.add(shot);
      } catch (NumberFormatException e) {
        bsv.printLine();
        bsv.printInvalidShots(bs.getTotalShips());
        invalidShotsCount++;
        if (invalidShotsCount >= bs.getTotalShips()) {
          break;
        }
      }
    }
    return shots;
  }
}

