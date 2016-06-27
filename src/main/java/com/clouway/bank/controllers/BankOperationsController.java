package com.clouway.bank.controllers;

import com.clouway.bank.core.AccountRepository;
import com.clouway.bank.core.BankController;
import com.clouway.bank.core.TransactionValidator;

import javax.xml.bind.ValidationException;

/**
 * @author Krasimir Raikov(raikov.krasimir@gmail.com)
 */
public class BankOperationsController implements BankController {
  private AccountRepository accountRepository;
  private TransactionValidator transactionValidator;

  public BankOperationsController(AccountRepository accountRepository, TransactionValidator transactionValidator) {
    this.accountRepository = accountRepository;
    this.transactionValidator = transactionValidator;
  }

  public void deposit(String username, String amount) throws ValidationException {
    String validationMessage = transactionValidator.validateAmount(amount);
    if ("".equals(validationMessage)) {
      accountRepository.deposit(username, Double.parseDouble(amount));
    } else {
      throw new ValidationException(validationMessage);
    }
  }

  @Override
  public Double currentBalance(String username) {
    return accountRepository.getCurrentBalance(username);
  }
}
