package com.clouway.bank.core;

import javax.xml.bind.ValidationException;

/**
 * @author Krasimir Raikov(raikov.krasimir@gmail.com)
 */
public interface BankController {

  void deposit(String username, String amount) throws ValidationException;

  Double currentBalance(String username);
}
