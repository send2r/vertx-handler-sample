package org.sample.dummy.server;

import io.vertx.core.Vertx;
import io.vertx.core.parsetools.RecordParser;

/**
 * This is a server with parser. Treating <<EOM>> as the delimiter.
 */
public class TcpServerWithParser {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.createNetServer().connectHandler(sock -> {

      RecordParser parser = RecordParser.newDelimited("<<EOM>>", sock);
      parser
        .endHandler(v -> sock.close())
        .exceptionHandler(t -> {
          t.printStackTrace();
          sock.close();
        })
        .handler(buffer -> {
          String message = buffer.toString("UTF-8");
          System.out.println(message);
        });

    }).listen(8009);
    System.out.println("Server is now listening");
  }
}
