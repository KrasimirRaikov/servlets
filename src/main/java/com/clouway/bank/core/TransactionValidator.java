package com.clouway.bank.core;

/**
 * @author Krasimir Raikov(raikov.krasimir@gmail.com)
 */
public interface TransactionValidator {
  String validateAmount(String amount);
}
