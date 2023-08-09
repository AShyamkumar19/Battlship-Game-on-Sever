package cs3500.pa04;

import cs3500.pa04.Controller.BattleSalvoController;
import cs3500.pa04.Controller.ProxyController;
import cs3500.pa04.Model.AiPlayer;
import java.io.IOException;
import java.net.Socket;

/**
 * Represents the Main Class.
 */
public class Driver {

  /**
   * @param args - the arguments
   */
  public static void main(String[] args) {
    BattleSalvoController bsc = new BattleSalvoController();
    if (args.length == 0) {
      bsc.setupGame();
    } else if (args.length >= 2) {
      try {
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        runClient(host, port);
      } catch (NumberFormatException | IOException e) {
        throw new IllegalArgumentException("Invalid port number");
      }
    } else {
      throw new IllegalArgumentException("Invalid number of arguments");
    }
  }

  private static void runClient(String host, int port) throws IOException {
    Socket server = new Socket(host, port);
    BattleSalvoController bs = new BattleSalvoController();
    Player player = new AiPlayer(bs, null, 0, 0, 0);
    ProxyController proxy = new ProxyController(server, player);
    proxy.run();
  }
}
