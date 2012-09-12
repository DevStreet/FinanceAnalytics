/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */

package com.opengamma.integration.copiernew.external;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.util.ArgumentChecker;

/**
 * An abstract table class for importing portfolio data from spreadsheets
 */
public abstract class RawSheetReader implements Closeable, Iterable<Map<String, String>> {
  
  private String[] _columns; // The column names and order

  public static RawSheetReader newSheetReader(SheetFormat sheetFormat, InputStream inputStream) {
    
    ArgumentChecker.notNull(sheetFormat, "sheetFormat");
    ArgumentChecker.notNull(inputStream, "outputStream");
    
    switch (sheetFormat) {
      case CSV:
        return new CsvRawSheetReader(inputStream);
      case XLS:
        return new XlsRawSheetReader(inputStream, 0);
      default:
        throw new OpenGammaRuntimeException("Could not create a reader for the sheet input format " + sheetFormat.toString());
    }
  }
  
  public static RawSheetReader newSheetReader(String filename) {
    ArgumentChecker.notEmpty(filename, "filename");
    InputStream inputStream = openFile(filename);
    return newSheetReader(SheetFormat.of(filename), inputStream);
  }
  
  public String[] getColumns() {
    return _columns;
  }

  public void setColumns(String[] columns) {
    _columns = columns;
  }
  
  protected static InputStream openFile(String filename) {
    // Open input file for reading
    FileInputStream fileInputStream;
    try {
      fileInputStream = new FileInputStream(filename);
    } catch (FileNotFoundException ex) {
      throw new OpenGammaRuntimeException("Could not open file " + filename + " for reading, exiting immediately.");
    }

    return fileInputStream;
  }

}
