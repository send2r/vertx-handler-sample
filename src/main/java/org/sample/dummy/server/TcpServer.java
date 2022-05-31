package org.sample.dummy.server;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetServer;
import org.sample.dummy.handler.DummyHandler;
import org.sample.dummy.handler.LoggerHandler;

public class TcpServer {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    NetServer server = vertx.createNetServer();

    //Handle the communication
    server.connectHandler(netSocket -> {
      netSocket.handler(new DummyHandler());
      netSocket.closeHandler(new LoggerHandler());
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
