package cs3500.pa04.View;

import cs3500.pa04.Model.GameBoard;

/**
 * Prints the board.
 */
public class PrintBoard {

  /**
   * @param board the GameBoard to be printed
   * @param player the title to be printed
   */
  public static void printBoard(GameBoard board, String player) {
    int height = board.getHeight();
    int width = board.getWidth();

    System.out.println(player);
    for (int x = 0; x < height; x++) {
      for (int y = 0; y < width; y++) {
        System.out.print(board.getBoard(x, y) + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  /**
   * @param board the char Board to be printed
   * @param player the title to be printed
   */
  public static void printAiBoard(char[][] board, String player) {
    int height = board.length;
    int width = board[0].length;

    System.out.println(player);
    for (int x = 0; x < height; x++) {
      for (int y = 0; y < width; y++) {
        System.out.print(board[x][y] + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

}
