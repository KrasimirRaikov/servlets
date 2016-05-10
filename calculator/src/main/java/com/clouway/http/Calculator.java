package com.clouway.http;

import com.clouway.core.StringCalculator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Slavi Dichkov (slavidichkof@gmail.com)
 */
public class Calculator extends HttpServlet {
    StringCalculator stringCalculator;

    public Calculator(StringCalculator stringCalculator) {
        this.stringCalculator = stringCalculator;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(printPage(""));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String buttonValue = req.getParameter("clickedButton");
        String displayValue = req.getParameter("display");
        PrintWriter writer=resp.getWriter();

        if (isNumber(buttonValue)) {
            displayValue += buttonValue;
        }
        if ("".equals(displayValue)){
            writer.println(printPage(displayValue));
            writer.flush();
            return;
        }
        if ("C".equals(buttonValue)) {
            displayValue = displayValue.substring(0, displayValue.length() - 1);
        }
        if ("CE".equals(buttonValue)) {
            displayValue = "";
        }
        if ( isLastSymbolOperator(buttonValue) && !isLastSymbolOperator(displayValue)) {
            displayValue += buttonValue;
        }
        if (isPoint(buttonValue) && !isPointLastUsedOperator(displayValue)){
            displayValue += buttonValue;
        }
        if ("=".equals(buttonValue) && !isLastSymbolOperator(displayValue) && !isPointLastUsedOperator(displayValue)) {
            displayValue = stringCalculator.eval(displayValue);
        }
        writer.println(printPage(displayValue));
        writer.flush();
    }

    private boolean isLastSymbolOperator(String value) {
        return value.substring(value.length() - 1).matches("[-+*/]");
    }

    private boolean isPoint(String value) {
        return value.substring(value.length() - 1).matches("[.]");
    }

    private boolean  isPointLastUsedOperator(String value) {
        String lastUsedOperator = "";
        for (int i=0; i<value.length(); i++){
            String symbol=value.substring(i,i+1);
            if (symbol.matches("[-+*/.]")){
                lastUsedOperator=symbol;
            }
        }
        return ".".equals(lastUsedOperator);
    }

    private boolean isNumber(String value) {
        return value.matches("[0-9]");
    }

    public String printPage(String expression) {
        return "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>StringCalculator</title>\n" +
                "</head>" +
                "<body>" +
                "<form action=\"calculator\" method=\"post\">" +
                "<div class=\"box\">" +
                "    <div>" +
                "        <input type=\"text\" name=\"display\" VALUE=\"" + expression + "\" style=\"height:30px; width:206px\" readonly>" +
                "    </div>" +
                "    <div>" +
                "        <button type=\"submit\" name=\"clickedButton\" VALUE=\"CE\" style=\"height:30px; width:103px\">CE</button>" +
                "        <button type=\"submit\" name=\"clickedButton\" VALUE=\"C\" style=\"height:30px; width:49px\">C</button>" +
                "        <button type=\"submit\" name=\"clickedButton\" VALUE=\".\" style=\"height:30px; width:49px\">.</button>" +
                "    </div>" +
                "    <div>" +
                "        <button type=\"submit\" name=\"clickedButton\" VALUE=\"1\" style=\"height:30px; width:49px\">1</button>" +
                "        <button type=\"submit\" name=\"clickedButton\" VALUE=\"2\" style=\"height:30px; width:49px\">2</button>" +
                "        <button type=\"submit\" name=\"clickedButton\" VALUE=\"3\" style=\"height:30px; width:49px\">3</button>" +
                "        <button type=\"submit\" name=\"clickedButton\" VALUE=\"*\" style=\"height:30px; width:49px\">X</button>" +
                "    </div>" +
                "    <div>" +
                "        <button type=\"submit\" name=\"clickedButton\" VALUE=\"4\" style=\"height:30px; width:49px\">4</button>" +
                "        <button type=\"submit\" name=\"clickedButton\" VALUE=\"5\" style=\"height:30px; width:49px\">5</button>" +
                "        <button type=\"submit\" name=\"clickedButton\" VALUE=\"6\" style=\"height:30px; width:49px\">6</button>" +
                "        <button type=\"submit\" name=\"clickedButton\" VALUE=\"-\" style=\"height:30px; width:49px\">-</button>" +
                "    </div>" +
                "    <div>" +
                "        <button type=\"submit\" name=\"clickedButton\" VALUE=\"7\" style=\"height:30px; width:49px\">7</button>" +
                "        <button type=\"submit\" name=\"clickedButton\" VALUE=\"8\" style=\"height:30px; width:49px\">8</button>" +
                "        <button type=\"submit\" name=\"clickedButton\" VALUE=\"9\" style=\"height:30px; width:49px\">9</button>" +
                "        <button type=\"submit\" name=\"clickedButton\" VALUE=\"+\" style=\"height:30px; width:49px\">+</button>" +
                "    </div>" +
                "    <div>" +
                "        <button type=\"submit\" name=\"clickedButton\" VALUE=\"=\" style=\"height:30px; width:103px\">=</button>" +
                "        <button type=\"submit\" name=\"clickedButton\" VALUE=\"0\" style=\"height:30px; width:49px\">0</button>" +
                "        <button type=\"submit\" name=\"clickedButton\" VALUE=\"/\" style=\"height:30px; width:49px\">/</button>" +
                "    </div>" +
                "</div>" +
                "</form>" +
                "</body>" +
                "</html>";
    }
}