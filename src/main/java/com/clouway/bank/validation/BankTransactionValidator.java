package com.clouway.bank.validation;

import com.clouway.bank.core.TransactionValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Krasimir Raikov(raikov.krasimir@gmail.com)
 */
public class BankTransactionValidator implements TransactionValidator {
  @Override
  public String validateAmount(String amount) {
    String amountRegex = "^([0-9]{1,5}).([0-9]{1,2})$";
    Pattern pattern = Pattern.compile(amountRegex);
    Matcher matcher = pattern.matcher(amount);
    if (matcher.matches()) {
      return "";
    } else {
      return "incorrect amount, has to have a positive number '.' and at least one digit afterwards";
    }
  }
}
