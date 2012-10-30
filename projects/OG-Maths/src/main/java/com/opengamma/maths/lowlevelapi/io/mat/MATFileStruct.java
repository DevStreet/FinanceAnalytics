/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.io.mat;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds everything needed to extract data from a MAT file .
 * The _header contains the header information
 * The _tags contains the information about tags 
 */
public class MATFileStruct {
  private MATHeaderStruct _header;
  private ArrayList<MATFileTagInfo> _tags = new ArrayList<MATFileTagInfo>();

  /**
   * Gets the header.
   * @return the header
   */
  public MATHeaderStruct getHeader() {
    return _header;
  }

  /**
   * Gets the tags.
   * @return the tags
   */
  public List<MATFileTagInfo> getTags() {
    return _tags;
  }

  MATFileStruct(MATHeaderStruct header, ArrayList<MATFileTagInfo> tags) {
    _header = header;
    _tags = tags;
  }

  public String toString() {
    String str = "";
    str += _header.toString();
    for (int i = 0; i < _tags.size(); i++) {
      str += _tags.get(i).toString();
    }
    return str;
  }

}
