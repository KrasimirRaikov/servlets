package com.clouway.bank.core;

import java.sql.Connection;

/**
 * @author Krasimir Raikov(raikov.krasimir@gmail.com)
 */
public interface ConnectionProvider {

  Connection get();
}
