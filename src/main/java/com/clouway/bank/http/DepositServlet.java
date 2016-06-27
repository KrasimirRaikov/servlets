package com.clouway.bank.http;

import com.clouway.bank.core.BankController;
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
 * @author Krasimir Raikov(raikov.krasimir@gmail.com)
 */
public class DepositServlet extends HttpServlet {
  private BankController controller;
  private Template template;

  public DepositServlet(BankController controller, Template template) {
    this.controller = controller;
    this.template = template;
  }

  @Override
  public void init() throws ServletException {
    template.setTemplate(getHtml("web/WEB-INF/pages/Deposit.html"));
    template.put("errorMessage", "");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = "Stanislava";
    Double balance = controller.currentBalance(username);
    PrintWriter writer = resp.getWriter();
    resp.setContentType("text/html");
    template.put("username", username);
    template.put("balance", balance.toString());
    writer.write(template.evaluate());
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = "Stanislava";
    String amount = req.getParameter("amount");
    try {
      controller.deposit(username, amount);
      template.put("errorMessage", "");
    } catch (ValidationException e) {
      template.put("errorMessage", e.getMessage() + "<br>");
    }
    doGet(req, resp);
  }

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
