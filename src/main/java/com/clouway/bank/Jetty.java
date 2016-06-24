package com.clouway.bank;

import com.clouway.bank.filters.ConnectionFilter;
import com.clouway.bank.http.DepositServlet;
import com.clouway.bank.persistent.PersistentAccountRepository;
import com.clouway.utility.BracketsTemplate;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.servlet.*;
import java.util.EnumSet;

/**
 * @author Krasimir Raikov(raikov.krasimir@gmail.com)
 */
public class Jetty {
  private final String dbName;
  private Server server;

  public Jetty(int port, String dbName) {
    this.dbName = dbName;
    this.server = new Server(port);
  }

  public synchronized void start() {
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/");



    context.addEventListener(new ServletContextListener() {

      public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        servletContext.addFilter("ConnectionFilter", new ConnectionFilter(dbName)).addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
        servletContext.addServlet("DepositServlet", new DepositServlet(new BankOperationsController(new PersistentAccountRepository(new PerRequestConnectionProvider())), new BracketsTemplate())).addMapping("/deposit");
      }

      public void contextDestroyed(ServletContextEvent servletContextEvent) {

      }
    });

    server.setHandler(context);
    try {
      server.start();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public void stop() {
    try {
      server.stop();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
