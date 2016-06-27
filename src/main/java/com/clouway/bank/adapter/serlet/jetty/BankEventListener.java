package com.clouway.bank.adapter.serlet.jetty;

import com.clouway.bank.controllers.BankOperationsController;
import com.clouway.bank.filters.ConnectionFilter;
import com.clouway.bank.http.DepositServlet;
import com.clouway.bank.persistent.PerRequestConnectionProvider;
import com.clouway.bank.persistent.PersistentAccountRepository;
import com.clouway.bank.validation.BankTransactionValidator;
import com.clouway.utility.BracketsTemplate;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.EnumSet;

/**
 * @author Krasimir Raikov(raikov.krasimir@gmail.com)
 */
public class BankEventListener implements ServletContextListener {

  private String dbName;

  public BankEventListener(String dbName) {
    this.dbName = dbName;
  }

  public void contextInitialized(ServletContextEvent servletContextEvent) {
    ServletContext servletContext = servletContextEvent.getServletContext();

    servletContext.addFilter("ConnectionFilter", new ConnectionFilter(dbName)).addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
    servletContext.addServlet("DepositServlet", new DepositServlet(new BankOperationsController(new PersistentAccountRepository(new PerRequestConnectionProvider()), new BankTransactionValidator()), new BracketsTemplate())).addMapping("/deposit");
  }

  public void contextDestroyed(ServletContextEvent servletContextEvent) {

  }
}
