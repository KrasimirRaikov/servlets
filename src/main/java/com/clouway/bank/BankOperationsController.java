package com.clouway.bank;

import com.clouway.bank.core.AccountRepository;
import com.clouway.bank.core.BankController;
import com.clouway.bank.persistent.PersistentAccountRepository;

/**
 * @author Krasimir Raikov(raikov.krasimir@gmail.com)
 */
public class BankOperationsController implements BankController {
  AccountRepository accountRepository;

  public BankOperationsController(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public void deposit(String username, Double amount){
      accountRepository.deposit(username, amount);
  }

  @Override
  public Double currentBalance(String username) {
    return accountRepository.getCurrentBalance(username);
  }
}
