package org.sample.dummy.client;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;

/**
 * To be used to test the TcpServerWithParser server
 * sends the <<EOM>> delimiter between messages
 */
public class TcpEomClient {
  public static void main(String[] args) {
    NetClient netClient = Vertx.vertx().createNetClient();
    netClient.connect(8009, "0.0.0.0", netSocketAsyncResult -> {
      System.out.println("Client connection started.");
      NetSocket netSocket = netSocketAsyncResult.result();
      for (int i = 0; i < 10; i++) {
        Buffer buffer = Buffer.buffer("Client message: " + i + "<<EOM>>");
        netSocket.write(buffer);
      }

      System.out.println("-- messages sent --");
      netSocket.close();
    });
  }
}
