package com.clouway.bank.persistent;

import com.clouway.bank.PerRequestConnectionProvider;
import com.clouway.bank.core.AccountRepository;
import com.clouway.bank.core.ConnectionProvider;
import com.clouway.bank.filters.ConnectionFilter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Krasimir Raikov(raikov.krasimir@gmail.com)
 */
public class PersistentAccountRepository implements AccountRepository {
  private ConnectionProvider connectionProvider;

  public PersistentAccountRepository(ConnectionProvider connectionProvider) {
    this.connectionProvider = connectionProvider;
  }

  @Override
  public void deposit(String username, double amount) {
    try {
      Connection connection = connectionProvider.get();

      double currentBalance = getCurrentBalance(username);
      double newBalance = currentBalance+amount;

      PreparedStatement preparedStatement = connection.prepareStatement("UPDATE account SET balance=? WHERE username=?");
      preparedStatement.setDouble(1, newBalance);
      preparedStatement.setString(2, username);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Double getCurrentBalance(String username) {
    try {
      Connection connection = connectionProvider.get();
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT balance FROM account WHERE username=?");
      preparedStatement.setString(1, username);
      ResultSet resultSet = preparedStatement.executeQuery();
      resultSet.next();
      Double balance = resultSet.getDouble("balance");
      return balance;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
