package com.clouway.bank.persistent;

import com.clouway.bank.core.AccountRepository;
import com.clouway.bank.filters.ConnectionFilter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Krasimir Raikov(raikov.krasimir@gmail.com)
 */
public class PersistentAccountRepository implements AccountRepository {
  @Override
  public void deposit(String username, double amount) {
    try {
      Connection connection = ConnectionFilter.getConnection();

      double currentBalance = getBalance(username);
      double newBalance = currentBalance+amount;

      PreparedStatement preparedStatement = connection.prepareStatement("UPDATE account SET balance=? WHERE username=?");
      preparedStatement.setDouble(1, newBalance);
      preparedStatement.setString(2, username);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public Double getBalance(String username) {
    try {
      Connection connection = ConnectionFilter.getConnection();
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
