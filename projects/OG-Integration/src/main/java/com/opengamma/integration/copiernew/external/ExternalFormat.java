/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.external;

/**
 * Known external formats
 */
public enum ExternalFormat {

  /** CSV */
  CSV, 

  /** XLS */
  XLS, 

  /** XLSX */
  XLSX,
  
  /** ZIP */
  ZIP,

  /** XML */
  XML,

  /** JSON */
  JSON,

  /** Unknown sheet */
  UNKNOWN;
 
  public static ExternalFormat of(String filename) {
    if (filename.lastIndexOf('.') < 0) {
      return ExternalFormat.UNKNOWN;
    }
    String extension = filename.substring(filename.lastIndexOf('.')).toLowerCase().trim();
    if (extension.equals(".csv")) {
      return ExternalFormat.CSV;
    } else if (extension.equals(".xls")) {
      return ExternalFormat.XLS;
    } else if (extension.equals(".xlsx")) {
      return ExternalFormat.XLSX;
    } else if (extension.equals(".zip")) {
      return ExternalFormat.ZIP;
    } else if (extension.equals(".xml")) {
      return ExternalFormat.XML;
    } else if (extension.equals(".json")) {
      return ExternalFormat.JSON;
    } else {
      return ExternalFormat.UNKNOWN;
    }
  }

}
