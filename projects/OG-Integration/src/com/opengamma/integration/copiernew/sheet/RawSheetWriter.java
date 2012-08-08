/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */

package com.opengamma.integration.copiernew.sheet;

import java.io.*;
import java.util.Map;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.integration.copiernew.Writeable;
import com.opengamma.util.ArgumentChecker;

/**
 * This abstract class represents a sheet writer that, given a map from column names to data, writes out a row containing that
 * data under the matching columns.
 */
public abstract class RawSheetWriter implements Writeable<Map<String, String>>, Closeable {

  private String[] _columns; // The column names and order

  protected String[] getColumns() {
    return _columns;
  }

  protected void setColumns(String[] columns) {
    _columns = columns;
  }

  protected static OutputStream openFile(String filename) {
    // Open input file for writing
    FileOutputStream fileOutputStream;
    try {
      fileOutputStream = new FileOutputStream(filename);
    } catch (IOException ex) {
      throw new OpenGammaRuntimeException("Could not open file " + filename + " for writing.");
    }

    return fileOutputStream;
  }

}
