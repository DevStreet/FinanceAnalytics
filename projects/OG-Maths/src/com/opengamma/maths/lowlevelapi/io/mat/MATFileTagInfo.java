/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.io.mat;

/**
 * Holds the information about a tag in a MAT file
 */
public class MATFileTagInfo {
  private long _startByte;
  private long _endByte;
  private MATSharedData.MATDataType _dataType;
  private int _nBytes;
  private MATSharedData.MATTagType _tagType;

  /**
   * 
   * @param startByte the starting byte of the data
   * @param endByte the ending byte of the data
   * @param dataType the data type
   * @param nBytes the number of bytes of data
   * @param tagType the tag type indicating "standard" or "small data".
   */
  public MATFileTagInfo(long startByte, long endByte, MATSharedData.MATDataType dataType, int nBytes, MATSharedData.MATTagType tagType) {
    _startByte = startByte;
    _endByte = endByte;
    _dataType = dataType;
    _nBytes = nBytes;
    _tagType = tagType;        
  }

  /**
   * Gets the startByte.
   * @return the startByte
   */
  public long getStartByte() {
    return _startByte;
  }

  /**
   * Gets the endByte.
   * @return the endByte
   */
  public long getEndByte() {
    return _endByte;
  }

  /**
   * Gets the dataType.
   * @return the dataType
   */
  public MATSharedData.MATDataType getDataType() {
    return _dataType;
  }

  /**
   * Gets the nBytes.
   * @return the nBytes
   */
  public int getnBytes() {
    return _nBytes;
  }

  /**
   * Gets the tagType.
   * @return the tagType
   */
  public MATSharedData.MATTagType getTagType() {
    return _tagType;
  }

  public String toString() {
    String str = "";
    str += "Start Byte: " + _startByte + "\n";
    str += "End Byte: " + _endByte + "\n";
    str += "Data Type: " + _dataType + "\n";    
    str += "Number of bytes: " + _nBytes + "\n";
    str += "Tag Type: " + _tagType + "\n";    
    return str;
  }

}
