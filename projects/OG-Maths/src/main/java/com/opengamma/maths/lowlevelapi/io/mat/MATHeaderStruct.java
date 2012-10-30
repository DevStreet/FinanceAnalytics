/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.io.mat;

import java.nio.ByteOrder;

/**
 * Holds a MAT file header 
 */
public class MATHeaderStruct {
  private String _info;
  private ByteOrder _endianness;
  private short _version;

  MATHeaderStruct(String info, short version, ByteOrder endianness) {
    _info = new String(info);
    _endianness = endianness;
    _version = version;
  }

  /**
   * Gets the info.
   * @return the info
   */
  public String getInfo() {
    return _info;
  }

  /**
   * Gets the endianness.
   * @return the endianness
   */
  public ByteOrder getEndianness() {
    return _endianness;
  }

  /**
   * Gets the version.
   * @return the version
   */
  public short getVersion() {
    return _version;
  }

  public String toString() {
    String tmp = null;
    tmp = "Mat file:\n";
    tmp = tmp + "Header text:" + _info + "\n";
    tmp = tmp + "Version:" + _version + "\n";
    tmp = tmp + "Endianness: " + _endianness + "\n";
    tmp = tmp + "\n";
    return tmp;
  }
  
}
