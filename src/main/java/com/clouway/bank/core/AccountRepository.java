package com.clouway.bank.core;

import com.clouway.bank.persistent.User;

/**
 * @author Krasimir Raikov(raikov.krasimir@gmail.com)
 */
public interface AccountRepository {

  void deposit(String username, double deposit);

  Double getBalance(String username);
}
