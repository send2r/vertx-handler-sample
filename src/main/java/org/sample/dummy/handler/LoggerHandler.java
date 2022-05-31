package org.sample.dummy.handler;

import io.vertx.core.Handler;

public class LoggerHandler implements Handler<Void> {
  @Override
  public void handle(Void unused) {
    System.out.println("--- Socket closed ---");
  }
}
