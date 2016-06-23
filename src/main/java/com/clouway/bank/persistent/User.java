package com.clouway.bank.persistent;

/**
 * @author Krasimir Raikov(raikov.krasimir@gmail.com)
 */
public class User {
  public final String username;
  public final String password;
  public final java.lang.Double balance;

  public User(String username, String password, java.lang.Double balance) {
    this.username = username;
    this.password = password;
    this.balance = balance;
  }
}
