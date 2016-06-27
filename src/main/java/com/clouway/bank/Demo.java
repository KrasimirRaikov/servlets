package com.clouway.bank;

import com.clouway.bank.adapter.serlet.jetty.Jetty;

/**
 * @author Krasimir Raikov(raikov.krasimir@gmail.com)
 */
public class Demo {
  public static void main(String[] args) {
    Jetty jetty = new Jetty(8080, "bank");
    jetty.start();
  }
}
