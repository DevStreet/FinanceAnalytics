/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.io.mat;

import java.util.HashMap;
import java.util.Map;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;

/**
 * Shared data about MAT file types
 * The data is mainly from the Table 1-1 "MAT-File Data Types", page 1-9.
 * http://www.mathworks.com/help/pdf_doc/matlab/matfile_format.pdf_doc
 * or derived from within that document
 */
public class MATSharedData {
  /**
   * enumerates the MAT file tag as either "small data" or the standard layout
   */
  public enum MATTagType {
    /**
     * For data in the "small data" format
     */
    smallData,
    /**
     * For data in the standard layout
     */
    standardData
  }
  
  /**
   * MAT file data types
   */
  public enum MATDataType {
    /**
     * MAT file 8 bit signed int type
     */
    miINT8,
    /**
     * MAT file 8 bit unsigned int type
     */    
    miUINT8,
    /**
     * MAT file 16 bit signed int type
     */
    miINT16,
    /**
     * MAT file 16 bit unsigned int type
     */    
    miUINT16,
    /**
     * MAT file 32 bit signed int type
     */
    miINT32,
    /**
     * MAT file 32 bit unsigned int type
     */    
    miUINT32,
    /**
     * MAT file single precision type IEEE(R) 754
     */
    miSINGLE,
    /**
     * MAT file double precision type IEEE(R) 754
     */    
    miDOUBLE,
    /**
     * MAT file 64 bit signed int type
     */
    miINT64,
    /**
     * MAT file 64 bit unsigned int type
     */    
    miUINT64,
    /**
     * MAT file MATRIX storage format
     */
    miMATRIX,
    /**
     * MAT file compressed data format
     */
    miCOMPRESSED,
    /**
     * Unicode UTF-8 Encoded Character Data
     */
    miUTF8,
    /**
     * Unicode UTF-16 Encoded Character Data
     */    
    miUTF16,
    /**
     * Unicode UTF-32 Encoded Character Data
     */    
    miUTF32,
    /**
     * Indicates that the datatype is "small data" and is packed in the tag header
     */
    SPECIALSMALL
  }

  
  public static MATDataType mDataTypeFromInt(int lookup)  {
    switch(lookup) {
      case(1):
        return MATDataType.miINT8;
      case(2):
        return MATDataType.miUINT8;      
      case(3):
        return MATDataType.miINT16;
      case(4):
        return MATDataType.miUINT16;
      case(5):
        return MATDataType.miINT32;
      case(6):
        return MATDataType.miUINT32;
      case(7):
        return MATDataType.miSINGLE;
      case(8):
        throw new MathsExceptionIllegalArgument("Reserved data type decoded from MAT file");
      case(9):
        return MATDataType.miDOUBLE;
      case(10):
        throw new MathsExceptionIllegalArgument("Reserved data type decoded from MAT file");
      case(11):
        throw new MathsExceptionIllegalArgument("Reserved data type decoded from MAT file");   
      case(12):
        return MATDataType.miINT64;      
      case(13):
        return MATDataType.miUINT64;
      case(14):
        return MATDataType.miMATRIX;
      case(15):
        return MATDataType.miCOMPRESSED;
      case(16):
        return MATDataType.miUTF8;
      case(17):
        return MATDataType.miUTF16;
      case(18):
        return MATDataType.miUTF32;
      default:
        throw new MathsExceptionIllegalArgument("Unknown data type decoded from MAT file");
    }
  }
  
/**
   * Gets the matFileDataTypes struct.
   * @return the matFileDataTypes
   */
  public static Strpair[] getMatFileDataTypes() {
    return s_matFileDataTypes;
  }

/**
 * Contains a struct for human readable information on MAT file internal types
 */
  private static Strpair[] s_matFileDataTypes = {
    null, // yes, null, matlab enumerates from 1, so to save a load of value - 1's we offset this array. If we had a pointer this would be a different story.
    new Strpair("miINT8", "8 bit, signed"),
    new Strpair("miUINT8", "8 bit, unsigned"),
    new Strpair("miINT16", "16-bit, signed"),
    new Strpair("miUINT16", "16-bit, unsigned"),
    new Strpair("miINT32", "32-bit, signed"),
    new Strpair("miUINT32", "32-bit, unsigned"),
    new Strpair("miSINGLE", "IEEE(R) 754 single format"),
    new Strpair("--", "Reserved"),
    new Strpair("miDOUBLE", "IEEE(R) 754 double format"),
    new Strpair("--", "Reserved"),
    new Strpair("--", "Reserved"),
    new Strpair("miINT64", "64 bit, signed"),
    new Strpair("miUINT64", "64 bit, unsigned"),
    new Strpair("miMATRIX", "MATLAB array"),
    new Strpair("miCOMPRESSED", "Compressed Data"),
    new Strpair("miUTF8", "Unicode UTF-8 Encoded Character Data"),
    new Strpair("miUTF16", "Unicode UTF-16 Encoded Character Data"),
    new Strpair("miUTF32", "Unicode UTF-32 Encoded Character Data")
  };

  {
    Map<String, Object> typeImplLookup = new HashMap<String, Object>();
    typeImplLookup.put("miINT8", short.class);
    typeImplLookup.put("miDouble", double.class);
  }
  
  private static class Strpair {
    private String _str1;
    private String _str2;

    Strpair(String str1, String str2) {
      _str1 = str1;
      _str2 = str2;
    }

    public String getStr1() {
      return _str1;
    }

    public String getStr2() {
      return _str2;
    }
  }
}
