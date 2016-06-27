package com.clouway.bank;

import com.clouway.bank.adapter.serlet.jetty.Jetty;

/**
 * @author Krasimir Raikov(raikov.krasimir@gmail.com)
 */
public class Demo {

  /**
   * Starts the server
   * listening to port 8080
   * using 'bank' database
   *
   * @param args
   */
  public static void main(String[] args) {
    Jetty jetty = new Jetty(8080, "bank");
    jetty.start();
  }
}
