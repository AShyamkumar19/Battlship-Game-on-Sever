//import static org.junit.jupiter.api.Assertions.*;
//
//import cs3500.pa04.Controller.BattleSalvoController;
//import cs3500.pa04.Controller.ProxyController;
//import cs3500.pa04.Model.AiPlayer;
//import cs3500.pa04.Player;
//import java.io.IOException;
//import java.io.StringReader;
//import java.net.Socket;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//
//public class TestProxyController {
//  ProxyController pc;
//  Player ai;
//  BattleSalvoController bsc;
//
//  @BeforeEach
//  public void setup() throws IOException {
//    bsc = new BattleSalvoController();
//    ai = new AiPlayer(bsc);
//    Socket server = new Socket("0.0.0.0", 35001);
//    pc = new ProxyController(server, ai);
//  }
//
//  @Test
//  public void testRun() {
//    pc.run();
//  }
//
//}
