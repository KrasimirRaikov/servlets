package com.clouway.bank.persistent;

import com.clouway.bank.core.ConnectionProvider;
import com.clouway.bank.filters.ConnectionFilter;

import java.sql.Connection;

/**
 * @author Krasimir Raikov(raikov.krasimir@gmail.com)
 */
public class PerRequestConnectionProvider implements ConnectionProvider {
  @Override
  public Connection get() {
    return ConnectionFilter.getConnection();
  }
}
