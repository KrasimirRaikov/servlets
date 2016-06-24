package com.clouway.bank.core;

/**
 * @author Krasimir Raikov(raikov.krasimir@gmail.com)
 */
public interface BankController {

  void deposit(String username, Double amount);

  Double currentBalance(String username);
}
