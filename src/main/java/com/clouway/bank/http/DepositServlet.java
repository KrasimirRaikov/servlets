package com.clouway.bank.http;

import com.clouway.bank.core.AccountRepository;
import com.clouway.utility.Template;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;

/**
 * Servlet for the deposit, displaying the html and handling requests for deposit
 *
 * @author Krasimir Raikov(raikov.krasimir@gmail.com)
 */
public class DepositServlet extends HttpServlet {
  private AccountRepository accountRepository;
  private Template template;


  /**
   * Constructor for the deposit setting the AccountRepository and Template
   *
   * @param accountRepository account repository storing the account data
   * @param template          template for manipulating strings
   */
  public DepositServlet(AccountRepository accountRepository, Template template) {
    this.accountRepository = accountRepository;
    this.template = template;
  }

  /**
   * initializes the servlet by setting the html file on the template
   * and hides the errorMessage template tag
   *
   * @throws ServletException
   */
  @Override
  public void init() throws ServletException {
    template.setTemplate(getHtml("web/WEB-INF/pages/Deposit.html"));
    template.put("errorMessage", "");
  }

  /**
   * Handling GET requests, displaying the html file
   *
   * @param req  http request
   * @param resp http response
   * @throws ServletException
   * @throws IOException
   */
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = "Stanislava";
    Double balance = accountRepository.getCurrentBalance(username);
    PrintWriter writer = resp.getWriter();
    resp.setContentType("text/html");
    template.put("username", username);
    template.put("balance", balance.toString());
    writer.write(template.evaluate());
  }

  /**
   * Handling the POST request, about depositing funds
   *
   * @param req  http request
   * @param resp http response
   * @throws ServletException
   * @throws IOException
   */
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = "Stanislava";
    String amount = req.getParameter("amount");
    try {
      accountRepository.deposit(username, amount);
      template.put("errorMessage", "");
    } catch (ValidationException e) {
      template.put("errorMessage", e.getMessage() + "<br>");
    }
    doGet(req, resp);
  }

  /**
   * Loading file as string
   *
   * @param filePath the path to the file
   * @return the string read from the file
   */
  private String getHtml(String filePath) {
    File file = new File(filePath);
    URL url = null;
    try {
      url = file.toURI().toURL();
      InputStream in = url.openStream();

      return IOUtils.toString(in);

    } catch (IOException e) {
      return "404 Page not found!";
    }

  }


}
