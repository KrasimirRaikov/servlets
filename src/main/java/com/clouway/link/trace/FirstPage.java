package com.clouway.link.trace;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Krasimir Raikov(raikov.krasimir@gmail.com)
 */
@WebServlet("/first")
public class FirstPage extends HttpServlet {

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<body>");
    out.println("<a href='tracer'>link</a>");
    out.println("</body>");
    out.println("</html>");
  }
}
