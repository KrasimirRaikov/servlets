package com.clouway.bank.filters;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Krasimir Raikov(raikov.krasimir@gmail.com)
 */
@WebFilter(filterName = "ConnectionFilter")
public class ConnectionFilter implements Filter {
  private MysqlConnectionPoolDataSource connectionPool;
  private static ThreadLocal<Connection> connections = new ThreadLocal<Connection>();

  public void destroy() {
  }

  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

    try {
      Connection connection = connectionPool.getConnection();
      connections.set(connection);
      chain.doFilter(req, resp);

    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  public void init(FilterConfig config) throws ServletException {
    connectionPool = new MysqlConnectionPoolDataSource();
    connectionPool.setURL("jdbc:mysql://localhost:3306/bank?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
    connectionPool.setUser("root");
    connectionPool.setPassword("clouway.com");

    try{
      Connection connection = connectionPool.getConnection();
      connections.set(connection);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static Connection getConnection(){
    return connections.get();
  }

}