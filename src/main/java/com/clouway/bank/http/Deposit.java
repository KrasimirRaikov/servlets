package com.clouway.bank.http;

import com.clouway.bank.BankOperationsController;
import com.clouway.bank.persistent.PersistentAccountRepository;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;

/**
 * @author Krasimir Raikov(raikov.krasimir@gmail.com)
 */
public class Deposit extends HttpServlet {
  private PersistentAccountRepository accountRepository;
  private BankOperationsController controller;

  @Override
  public void init() throws ServletException {
    accountRepository = new PersistentAccountRepository();
    controller = new BankOperationsController(accountRepository);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    display(resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String amount = req.getParameter("amount");
    String username = "Stanislava";
    accountRepository.deposit(username, Double.parseDouble(amount));
    display(resp);
  }

  private void display(HttpServletResponse resp) throws IOException {
    PrintWriter writer = resp.getWriter();
    resp.setContentType("text/html");

    File file = new File("web/WEB-INF/pages/Deposit.html");
    URL url = file.toURI().toURL();
    InputStream in = url.openStream();

    writer.write(IOUtils.toString(in));
    writer.flush();
  }


}
