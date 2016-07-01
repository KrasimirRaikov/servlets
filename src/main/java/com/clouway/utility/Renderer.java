package com.clouway.utility;

import java.io.Writer;

/**
 * 
 *
 * @author Krasimir Raikov(raikov.krasimir@gmail.com)
 */
public interface Renderer {

  void fieldValue(String fieldName, String value);

  void render(String destination, Writer writer);
}
