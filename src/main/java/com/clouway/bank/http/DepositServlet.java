package com.clouway.bank.http;

import com.clouway.bank.core.BankController;
import com.clouway.utility.Template;
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
public class DepositServlet extends HttpServlet {
  private BankController controller;
  private Template template;

  public DepositServlet(BankController controller, Template template) {
    this.controller = controller;
    this.template = template;
  }

  @Override
  public void init() throws ServletException {
    try {

      template.setTemplate(getHtml("web/WEB-INF/pages/Deposit.html"));
    } catch (IOException e) {
      e.printStackTrace();
    }
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
    controller.deposit(username, Double.parseDouble(amount));
    doGet(req, resp);
  }

  private String getHtml(String filePath) throws IOException {
    File file = new File(filePath);
    URL url = file.toURI().toURL();
    InputStream in = url.openStream();

    return IOUtils.toString(in);
  }


}
