/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */

package com.opengamma.integration.copiernew.sheet;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.util.ArgumentChecker;

/**
 * A class for importing portfolio data from XLS (pre-Excel 2007) worksheets
 */
public class XlsRawSheetReader extends RawSheetReader {

  private Sheet _sheet;
  private Workbook _workbook;
  private int _currentRowNumber;
  private InputStream _inputStream;
  private Row _buffer;

  
  public XlsRawSheetReader(String filename, int sheetIndex) {
    
    ArgumentChecker.notEmpty(filename, "filename");

    _inputStream = openFile(filename);
    _workbook = getWorkbook(_inputStream);
    _sheet = _workbook.getSheetAt(sheetIndex);
    _currentRowNumber = _sheet.getFirstRowNum();
    
    // Read in the header row
    Row rawRow = _sheet.getRow(_currentRowNumber++);
    
    // Normalise read-in headers (to lower case) and set as columns
    setColumns(getColumnNames(rawRow));

    // Buffer first data row
    _buffer = _sheet.getRow(_currentRowNumber++);

  }
  
  public XlsRawSheetReader(String filename, String sheetName) {
    
    ArgumentChecker.notEmpty(filename, "filename");
    ArgumentChecker.notEmpty(sheetName, "sheetName");

    InputStream fileInputStream = openFile(filename);
    _workbook = getWorkbook(fileInputStream);
    _sheet = _workbook.getSheet(sheetName);
    _currentRowNumber = _sheet.getFirstRowNum();

    // Read in the header row
    Row rawRow = _sheet.getRow(_currentRowNumber++);

    // Normalise read-in headers (to lower case) and set as columns
    setColumns(getColumnNames(rawRow));

    // Buffer first data row
    _buffer = _sheet.getRow(_currentRowNumber++);

  }

  public XlsRawSheetReader(InputStream inputStream, int sheetIndex) {
    
    ArgumentChecker.notNull(inputStream, "inputStream");

    _workbook = getWorkbook(inputStream);
    _sheet = _workbook.getSheetAt(sheetIndex);
    _currentRowNumber = _sheet.getFirstRowNum();
    
    // Read in the header row
    Row rawRow = _sheet.getRow(_currentRowNumber++);
    
    // Normalise read-in headers (to lower case) and set as columns
    setColumns(getColumnNames(rawRow));

    // Buffer first data row
    _buffer = _sheet.getRow(_currentRowNumber++);
  }
  
  public XlsRawSheetReader(InputStream inputStream, String sheetName) {
    
    ArgumentChecker.notNull(inputStream, "inputStream");
    ArgumentChecker.notEmpty(sheetName, "sheetName");

    _workbook = getWorkbook(inputStream);
    _sheet = _workbook.getSheet(sheetName);
    _currentRowNumber = _sheet.getFirstRowNum();

    // Read in the header row
    Row rawRow = _sheet.getRow(_currentRowNumber++);

    String[] columns = getColumnNames(rawRow);
    setColumns(columns);

    // Buffer first data row
    _buffer = _sheet.getRow(_currentRowNumber++);

  }

  
  private Workbook getWorkbook(InputStream inputStream) {
    
    try {
      return new HSSFWorkbook(inputStream);
    } catch (IOException ex) {
      throw new OpenGammaRuntimeException("Error opening Excel workbook: " + ex.getMessage());
    }    
  }
  
  private Map<String, String> loadNextRow() {
    
    // Get a reference to the next Excel row
    Row rawRow = _buffer;

    _buffer = _sheet.getRow(_currentRowNumber++);

    // If the row is empty return null (assume end of table)
    if (rawRow == null || rawRow.getFirstCellNum() == -1) {
      return null; // new HashMap<String, String>();
    } 
      
   
    // Map read-in row onto expected columns
    Map<String, String> result = new HashMap<String, String>();
    for (int i = 0; i < getColumns().length; i++) {
      String cell = getCell(rawRow, rawRow.getFirstCellNum() + i).trim();
      if (cell != null && cell.length() > 0) {
        result.put(getColumns()[i], cell);
      }
    }

    return result;
  }
  
  private String[] getColumnNames(Row rawRow) {
    String[] columns = new String[rawRow.getPhysicalNumberOfCells()];
    for (int i = 0; i < rawRow.getPhysicalNumberOfCells(); i++) {
      columns[i] = getCell(rawRow, i).trim().toLowerCase();
    }
    return columns;
  }
  
  private static Cell getCellSafe(Row rawRow, int column) {
    return rawRow.getCell(column, Row.CREATE_NULL_AS_BLANK);
  }
  
  private static String getCell(Row rawRow, int column) {
    return getCellAsString(getCellSafe(rawRow, column));
  }
  
  private static String getCellAsString(Cell cell) {

    if (cell == null) {
      return "";
    }
    switch (cell.getCellType()) {
      case Cell.CELL_TYPE_NUMERIC:
        //return Double.toString(cell.getNumericCellValue());
        return (new DecimalFormat("#.##")).format(cell.getNumericCellValue());
      case Cell.CELL_TYPE_STRING:
        return cell.getStringCellValue();
      case Cell.CELL_TYPE_BOOLEAN:
        return Boolean.toString(cell.getBooleanCellValue());
      case Cell.CELL_TYPE_BLANK:
        return "";
      default:
        return null;
    }
  }

  @Override
  public void close() {
    try {
      if (_inputStream != null) {
        _inputStream.close();
      }
    } catch (IOException ex) {
      // TODO Auto-generated catch block
    }
  }

  @Override
  public Iterator<Map<String, String>> iterator() {
    return new Iterator<Map<String, String>>() {
      @Override
      public boolean hasNext() {
        return (_buffer == null);
      }

      @Override
      public Map<String, String> next() {
        return loadNextRow();
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException("XLS row iterator does not support the remove operation");
      }
    };
  }
}
