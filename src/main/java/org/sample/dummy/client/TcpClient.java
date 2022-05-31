package org.sample.dummy.client;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;

public class TcpClient {
  public static void main(String[] args) {
    NetClient netClient = Vertx.vertx().createNetClient();
    netClient.connect(8008, "0.0.0.0", netSocketAsyncResult -> {
      System.out.println("Client connection started.");
      NetSocket netSocket = netSocketAsyncResult.result();
      for (int i = 0; i < 10; i++) {
        Buffer buffer = Buffer.buffer("Client message: " + i);
        netSocket.write(buffer);
      }
      System.out.println("-- messages sent --");
      netSocket.close();
    });
  }
}
