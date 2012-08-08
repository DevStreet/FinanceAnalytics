/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew;

import com.opengamma.util.ArgumentChecker;

/**
 * Provides the ability to copy data
 */
public class Copier<E> {

  public void copy(Iterable<E> reader, Writeable<E> writer) {
    copy(reader, writer, null);
  }

  public void copy(Iterable<E> reader, Writeable<E> writer, LogBack<E> logBack) {

    ArgumentChecker.notNull(writer, "writer");
    ArgumentChecker.notNull(reader, "reader");

    E written = null;

    for (E datum : reader) {
      if (datum != null) {
        written = writer.addOrUpdate(datum);
        if (logBack != null) {
          logBack.info("Wrote: ", written);
        }
      }
    }
  }

}
