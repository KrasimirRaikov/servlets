package com.clouway.bank.core;

/**
 * @author Krasimir Raikov(raikov.krasimir@gmail.com)
 */
public interface AccountRepository {

  void deposit(String username, double deposit);

  Double getCurrentBalance(String username);
}
