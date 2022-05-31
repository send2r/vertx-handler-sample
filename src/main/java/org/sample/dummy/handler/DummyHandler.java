package org.sample.dummy.handler;

import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.parsetools.RecordParser;

public class DummyHandler implements Handler<Buffer> {
  @Override
  public void handle(Buffer buffer) {
    byte[] bytes = buffer.getBytes();
    System.out.printf("Received data: %s\n", new String(bytes));
  }
}
