package com.clouway.bank.adapter.serlet.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

/**
 * @author Krasimir Raikov(raikov.krasimir@gmail.com)
 */
public class Jetty {
  private final String dbName;
  private Server server;

  public Jetty(int port, String dbName) {
    this.dbName = dbName;
    this.server = new Server(port);
  }

  public synchronized void start() {
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/");


    context.addEventListener(new BankEventListener(dbName));

    server.setHandler(context);
    try {
      server.start();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public void stop() {
    try {
      server.stop();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
