package com.clouway.bank;

import com.clouway.bank.core.AccountRepository;
import com.clouway.bank.persistent.PersistentAccountRepository;

/**
 * @author Krasimir Raikov(raikov.krasimir@gmail.com)
 */
public class BankOperationsController {
  AccountRepository accountRepository;

  public BankOperationsController(PersistentAccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public void execute(String operation, String username, Double amount) {
    if ("deposit".equals(operation)){
      accountRepository.deposit(username, amount);
    }
  }

  public void deposit(String username, Double amount){
      accountRepository.deposit(username, amount);
  }
}
