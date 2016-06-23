package com.clouway.bank;

import com.clouway.bank.core.AccountRepository;
import com.clouway.bank.persistent.PersistentAccountRepository;
import com.clouway.bank.utils.AccountRepositoryUtility;
import com.clouway.bank.utils.ConnectionManager;
import com.clouway.bank.utils.UserRepositoryUtility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * @author Krasimir Raikov(raikov.krasimir@gmail.com)
 */
@RunWith(Parameterized.class)
public class DepositTest {
  private AccountRepository accountRepository;
  private Jetty jetty;
  private Connection connection;
  private ConnectionManager connectionManager;
  private AccountRepositoryUtility accountRepositoryUtility;
  private UserRepositoryUtility userRepositoryUtility;

  @Before
  public void setUp() throws SQLException {
    jetty = new Jetty(8080);
    accountRepository = new PersistentAccountRepository();
    jetty.start();
    connectionManager = new ConnectionManager();
    connection = connectionManager.getConnection();
    accountRepositoryUtility = new AccountRepositoryUtility(connectionManager.getConnection());
    accountRepositoryUtility.clearAccountTable();
    userRepositoryUtility = new UserRepositoryUtility(connectionManager.getConnection());
    userRepositoryUtility.clearUsersTable();
    userRepositoryUtility.registerUser("Stanislava", "123456");
    accountRepositoryUtility.instantiateAccount("Stanislava");
  }

  @After
  public void TearDown() throws SQLException {
    accountRepositoryUtility.clearAccountTable();
    userRepositoryUtility.clearUsersTable();
    connection.close();
    jetty.stop();
  }

  @Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][]{
            {10.0}, {110.0}, {243.0}, {315.0}, {46.0}, {520.0}, {63.0}
    });
  }

  private Double amount;

  public DepositTest(java.lang.Double amount) {
    this.amount = amount;
  }

  @Test
  public void depositFunds() throws Exception {


    String url = "http://localhost:8080/deposit?amount=" + amount;

    URL urlObj = new URL(url);

    HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();

    connection.setRequestMethod("POST");

    int responseCode = connection.getResponseCode();

    Double balance = accountRepository.getBalance("Stanislava");
    assertThat(balance, is(equalTo(amount)));

  }

  @Test
  public void multipleDeposits() throws Exception {
    String url = "http://localhost:8080/deposit?amount=" + amount;


    URL urlObj = new URL(url);
    HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
    connection.setRequestMethod("POST");
    int responseCode = connection.getResponseCode();

    HttpURLConnection connection2 = (HttpURLConnection) urlObj.openConnection();
    connection2.setRequestMethod("POST");
    int responseCode2 = connection2.getResponseCode();


    Double balance = accountRepository.getBalance("Stanislava");
    assertThat(balance, is(equalTo(amount*2)));
  }
}
