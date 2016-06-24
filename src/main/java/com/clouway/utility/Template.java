package com.clouway.utility;

/**
 * @author Krasimir Raikov(raikov.krasimir@gmail.com)
 */
public interface Template {

  void setTemplate(String template);

  void put(String placeHolder, String value);

  String evaluate();
}
