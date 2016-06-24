package com.clouway.bank;

import javax.servlet.ServletException;

/**
 * @author Krasimir Raikov(raikov.krasimir@gmail.com)
 */
public class Demo {
  public static void main(String[] args) {
    Jetty jetty = new Jetty(8080, "bank");
    jetty.start();
  }
}
