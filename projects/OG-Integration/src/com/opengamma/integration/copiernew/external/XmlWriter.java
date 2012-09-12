/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.external;

import com.opengamma.integration.copiernew.Writeable;

import org.joda.beans.Bean;

import java.io.IOException;
import java.io.OutputStream;

public class XmlWriter<T extends Bean> implements Writeable<T> {

  public XmlWriter(OutputStream outputStream) {

  }

  @Override
  public void addOrUpdate(T datum) {

  }

  @Override
  public void addOrUpdate(Iterable<T> data) {
    for (T datum : data) {
      addOrUpdate(datum);
    }
  }

  @Override
  public void flush() throws IOException {
    // TODO
  }
}
