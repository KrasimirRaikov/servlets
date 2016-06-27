package com.clouway.bank.controllers;

import com.clouway.bank.core.AccountRepository;
import com.clouway.bank.core.BankController;
import com.clouway.bank.core.TransactionValidator;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import javax.xml.bind.ValidationException;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author Krasimir Raikov(raikov.krasimir@gmail.com)
 */
@RunWith(Parameterized.class)
public class BankOperationsControllerTest {

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();
  @Mock
  public AccountRepository accountRepository;
  @Mock
  public TransactionValidator transactionValidator;
  private BankController bankController;
  private String username = "Stanislava";
  private String amount;

  public BankOperationsControllerTest(String amount) {
    this.amount = amount;
  }

  @Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][]{
            {"10"}, {"20"}, {"243.0"}, {"315.0"}, {"46.0"}, {"520.0"}, {"63.0"}
    });
  }

  @Before
  public void setUp() {
    bankController = new BankOperationsController(accountRepository, transactionValidator);
  }

  @Test
  public void happyPath() throws ValidationException {
    context.checking(new Expectations() {{
      oneOf(transactionValidator).validateAmount(amount);
      will(returnValue(""));

      oneOf(accountRepository).deposit(username, Double.parseDouble(amount));

    }});
    bankController.deposit(username, amount);
  }

  @Test(expected = ValidationException.class)
  public void invalidAmount() throws ValidationException {
    context.checking(new Expectations() {{
      oneOf(transactionValidator).validateAmount(amount + "e");
      will(returnValue("invalid amount"));
    }});

    bankController.deposit(username, amount + "e");
  }

}