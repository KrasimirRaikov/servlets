package com.clouway.utility;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Krasimir Raikov(raikov.krasimir@gmail.com)
 */
public class BracketsTemplate implements Template {
  private final Map<String, String> placeHolderToValue = new LinkedHashMap<String, String>();
  private String template;

  public void setTemplate(String template) {
    this.template = template;
  }

  public void put(String placeHolder, String value) {
    placeHolderToValue.put(placeHolder, value);
  }

  public String evaluate() {
    String evaluationResult = template;
    for (String placeHolder : placeHolderToValue.keySet()) {
      evaluationResult = evaluationResult.replaceAll("\\$\\{" + placeHolder + "\\}", placeHolderToValue.get(placeHolder));
    }

    return evaluationResult;
  }
}
