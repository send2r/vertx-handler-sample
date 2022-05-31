package org.sample.dummy.server;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetServer;
import io.vertx.core.parsetools.RecordParser;
import org.sample.dummy.handler.DummyHandler;
import org.sample.dummy.handler.LoggerHandler;

/**
 * Basic tcp server to test the behavior of handle method
 *
 */
public class TcpServer {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    NetServer server = vertx.createNetServer();

    //Handle the communication
    server.connectHandler(netSocket -> {
      netSocket.handler(new DummyHandler());
      netSocket.closeHandler(new LoggerHandler());
    });
    RecordParser recordParser = RecordParser.newDelimited("<<EOM>>", data -> {
      System.out.printf("Received data: %s\n", data.toString());
    });

    //Start server
    server.listen(8008, "0.0.0.0", netServerAsyncResult -> {
      if (netServerAsyncResult.succeeded()) {
        System.out.println("Server is up on port " + server.actualPort());
      } else {
        System.out.println("Bind error.Bye!");
      }
    });
  }
}
