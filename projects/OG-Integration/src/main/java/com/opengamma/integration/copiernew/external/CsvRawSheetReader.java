/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */

package com.opengamma.integration.copiernew.external;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.util.ArgumentChecker;

import au.com.bytecode.opencsv.CSVReader;

/**
 * A class to facilitate importing portfolio data from comma-separated value files
 */
public class CsvRawSheetReader extends RawSheetReader {

  private CSVReader _csvReader;
  private String[] _buffer;

  public CsvRawSheetReader(String filename) {

    ArgumentChecker.notEmpty(filename, "filename");

    // Open file
    InputStream fileInputStream = openFile(filename);

    // Set up CSV reader
    _csvReader = new CSVReader(new InputStreamReader(fileInputStream));

    // Set columns
    setColumns(readHeaderRow());

    // Buffer first data row
    try {
      _buffer = _csvReader.readNext();
    } catch (IOException ex) {
      throw new OpenGammaRuntimeException("Error reading CSV file data row: " + ex.getMessage());
    }

  }

  public CsvRawSheetReader(InputStream inputStream) {

    ArgumentChecker.notNull(inputStream, "inputStream");

    // Set up CSV reader
    _csvReader = new CSVReader(new InputStreamReader(inputStream));

    // Set columns
    setColumns(readHeaderRow());

    // Buffer first data row
    try {
      _buffer = _csvReader.readNext();
    } catch (IOException ex) {
      throw new OpenGammaRuntimeException("Error reading CSV file data row: " + ex.getMessage());
    }

  }

  private Map<String, String> loadNextRow() {

    // Read in next row
    String[] rawRow = _buffer;
    try {
      _buffer = _csvReader.readNext();
    } catch (IOException ex) {
      throw new OpenGammaRuntimeException("Error reading CSV file data row: " + ex.getMessage());
    }

    // Return null if EOF
    if (rawRow == null) {
      return null;
    }

    // Map read-in row onto expected columns
    Map<String, String> result = new HashMap<String, String>();
    for (int i = 0; i < getColumns().length; i++) {
      if (i >= rawRow.length) {
        break;
      }
      if (rawRow[i] != null && rawRow[i].trim().length() > 0) {
        result.put(getColumns()[i], rawRow[i]);
      }
    }

    return result;
  }

  private String[] readHeaderRow() {
    // Read in the header row
    String[] rawRow;
    try {
      rawRow = _csvReader.readNext();
    } catch (IOException ex) {
      throw new OpenGammaRuntimeException("Error reading CSV file header row: " + ex.getMessage());
    }

    // Normalise read-in headers (to lower case) and set as columns
    String[] columns = new String[rawRow.length];
    for (int i = 0; i < rawRow.length; i++) {
      columns[i] = rawRow[i].trim().toLowerCase();
    }

    return columns;
  }

  @Override
  public void close() {
    try {
      _csvReader.close();
    } catch (IOException ex) {
      // Quiet
    }
  }

  @Override
  public Iterator<Map<String, String>> iterator() {
    return new Iterator<Map<String, String>>() {
      @Override
      public boolean hasNext() {
        return !(_buffer == null);
      }

      @Override
      public Map<String, String> next() {
        return loadNextRow();
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException("CSV row iterator does not support the remove operation");
      }
    };
  }
}
