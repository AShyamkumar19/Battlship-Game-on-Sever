package cs3500.pa04.Model;

/**
 * Represents the tracker board.
 */
public class TrackerBoard {
  private char[][] board;

  /**
   * @param height - the height of the board
   * @param width - the width of the board
   * @return - the tracker board
   */
  public char[][] trackerBoard(int height, int width) {
    board = new char[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        this.board[i][j] = '0';
      }
    }
    return board;
  }
}
