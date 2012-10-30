/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.io.mat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import static com.opengamma.maths.lowlevelapi.io.mat.MATSharedData.MATTagType;
import static com.opengamma.maths.lowlevelapi.io.mat.MATSharedData.MATDataType;
import static com.opengamma.maths.lowlevelapi.io.mat.MATSharedData.mDataTypeFromInt;
import com.opengamma.util.tuple.Pair;

/**
 * Reads a MAT File
 */
public class ReadMAT {

  /**
   * Reads a MAT file
   * @param filename name of file
   * @throws FileNotFoundException
   */
  public static void read(String filename) throws IOException {
    MATFileStruct matStructure = null;
    matStructure = parseMATFile(filename);

    List<MATFileTagInfo> tags = matStructure.getTags();

    File matfile = null;
    matfile = new File(filename);
    RandomAccessFile fp = new RandomAccessFile(matfile, "r");

    // walk in tags
    for (int i = 0; i < tags.size(); i++) {
      MATFileTagInfo tag = tags.get(i);
      long sbyte = tag.getStartByte();
      long ebyte = tag.getEndByte();
      fp.seek(sbyte);
      byte[] buf = new byte[(int) (ebyte - sbyte + 1)];
      MATTagType tagType = tag.getTagType();
      MATDataType dataType = tag.getDataType();
      if (tagType == MATTagType.standardData) {
        switch (dataType) {
          case miDOUBLE:
            break;
        }
      }

    }

    fp.close();

  }

  /**
   * Parses a MAT File
   * @param filename the file to parse
   * @return a {@link MATFileStruct} that contains all the information about the parsed MAT File  
   * @throws IOException
   */
  public static MATFileStruct parseMATFile(String filename) throws IOException {
    File matfile = null;
    matfile = new File(filename);
    RandomAccessFile fp = new RandomAccessFile(matfile, "r");
    MATHeaderStruct header = readMATHeader(fp, filename);
    int headerWidth = 8;

    // file pointer is now at end of header region
    ArrayList<MATFileTagInfo> tags = new ArrayList<MATFileTagInfo>();
    long pos = fp.getFilePointer();
    ByteOrder endianness = header.getEndianness();
    int nBytes;
    while (pos < fp.length()) {
      //      System.out.println("Top of loop" + pos);
      MATTagType tagType = getMatTagType(fp, endianness);
      //      System.out.println("Tag type:" + tagType);

      MATDataType dataType;
      if (tagType != MATTagType.smallData) {
        Pair<Integer, Integer> tagData = readStandardTagHeaderRegion(fp, endianness);
        dataType = mDataTypeFromInt(tagData.getFirst().intValue());
        nBytes = tagData.getSecond().intValue();
      } else {
        dataType = MATDataType.SPECIALSMALL;
        nBytes = headerWidth;
      }
      long sFPtr = fp.getFilePointer();
      MATFileTagInfo thisTag = new MATFileTagInfo(sFPtr, sFPtr + nBytes, dataType, nBytes, tagType);
      tags.add(thisTag);
      //      System.out.println("Before update " + pos);
      fp.seek(pos + headerWidth + nBytes);
      pos = fp.getFilePointer();
    }

    MATFileStruct fileInfo = new MATFileStruct(header, tags);
    fp.close();
    return fileInfo;
  }

  /**
   * Reads a standard tag header region
   * @param fp file pointer
   * @param endian byte order
   * @return a pair containing the "file type" to be looked up in {@link MATSharedData.MATDataType} and the number of bytes of data in the data section.
   */
  private static Pair<Integer, Integer> readStandardTagHeaderRegion(RandomAccessFile fp, ByteOrder endian) {
    long startingPos = 0;
    // save start of tag  
    try {
      startingPos = fp.getFilePointer();
    } catch (IOException e) {
      e.printStackTrace();
    }
    int mDataType = getStandardTagDataType(fp, endian);
    int mNoBytes = getStandardTagNoBytes(fp, endian);
    System.out.println("M-Data type is " + mDataType);
    System.out.println("M-Data byte count is " + mNoBytes);

    // rewind to start of tag
    try {
      fp.seek(startingPos);
    } catch (IOException e) {
      System.out.println(e.toString());
    }
    return Pair.of(new Integer(mDataType), new Integer(mNoBytes));
  }

  /**
   * Gets the number of bytes from a standard tag
   * @param fp file pointer
   * @param endian byte order
   * @return int number of bytes
   */
  private static int getStandardTagNoBytes(RandomAccessFile fp, ByteOrder endian) {
    return get4bytesAsInt(fp, endian);
  }

  /**
   * Gets the number of bytes from a standard tag
   * @param fp file pointer
   * @param endian byte order
   * @return an int representing the "file type" to be looked up in {@link MATSharedData.MATDataType} 
   */
  private static int getStandardTagDataType(RandomAccessFile fp, ByteOrder endian) {
    return get4bytesAsInt(fp, endian);
  }

  /**
   * Reads 4 bytes as an Int from the current file position held by fp. The file pointer position is incremented 4 bytes.
   * @param fp The file pointer, the file pointer position is incremented 4 bytes by this function.
   * @param endian byte order 
   * @return an Int representation of the 4 bytes read.
   */
  private static int get4bytesAsInt(RandomAccessFile fp, ByteOrder endian) {
    int dataType;
    int mDataTypeLen = 4;
    byte[] rTAGDataTypeRaw = new byte[mDataTypeLen];

    try {
      fp.read(rTAGDataTypeRaw);
    } catch (IOException e) {
      e.printStackTrace();
    }

    ByteBuffer bTAGDataTypeBuf = ByteBuffer.wrap(rTAGDataTypeRaw);
    bTAGDataTypeBuf.order(endian);
    dataType = bTAGDataTypeBuf.getInt();
    return dataType;
  }

  /**
   * Gets the type of MAT Tag
   * @param fp the file pointer, on return the file pointer position is not affected by this function.
   * @param endian byte order
   * @return a matTagType indicating whether the tag is a "Small Data Format" file or a "Standard" data format tag
   */
  private static MATTagType getMatTagType(RandomAccessFile fp, ByteOrder endian) {
    MATTagType ret;

    int mDataTypeLen = 4;
    int mNoBytesLen = 4;

    byte[] rTAGDataTypeRaw = new byte[mDataTypeLen];
    byte[] rTAGNoBytesRaw = new byte[mNoBytesLen];

    long startingPos = 0;
    // save start of tag  
    try {
      startingPos = fp.getFilePointer();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // read data type and length
    try {
      fp.read(rTAGDataTypeRaw);
      fp.read(rTAGNoBytesRaw);
    } catch (IOException e) {
      e.printStackTrace();
    }

    // inspect first 2 bytes to see if we are dealing with a "Small Data Element Format"
    if (rTAGDataTypeRaw[0] == 0x0 && rTAGDataTypeRaw[1] == 0x0) {
      System.out.println("Small Data Element Format");
      ret = MATTagType.smallData;

    } else {
      System.out.println("Standard Format");
      ret = MATTagType.standardData;
    }

    // rewind to start of tag
    try {
      fp.seek(startingPos);
    } catch (IOException e) {
      System.out.println(e.toString());
    }

    return ret;
  }

  /**
   * Reads the header of a MAT File
   * @param fp the file pointer, incremented 128 bytes by this function
   * @param filename the name of the file to read
   * @return a {@link MATHeaderStruct} containing the header data for this file
   */
  private static MATHeaderStruct readMATHeader(RandomAccessFile fp, String filename) {
    String header;
    short version;
    ByteOrder endian = null;

    int mHEADERtxtHeaderLen = 116;
    int mHEADERSubSysDataOffsetLen = 8;
    int mHEADERVersionLen = 2;
    int mHEADEREndianLen = 2;

    byte[] rHEADERtxtRaw = new byte[mHEADERtxtHeaderLen];
    byte[] rHEADERSubSysRaw = new byte[mHEADERSubSysDataOffsetLen];
    byte[] rHEADERVersionRaw = new byte[mHEADERVersionLen];
    byte[] rHEADEREndianRaw = new byte[mHEADEREndianLen];

    try {
      fp.read(rHEADERtxtRaw);
      fp.read(rHEADERSubSysRaw);
      fp.read(rHEADERVersionRaw);
      fp.read(rHEADEREndianRaw);
    } catch (Exception e) {
      System.out.println(e.toString());
    }
    header = new String(rHEADERtxtRaw);

    ByteBuffer bHEADERVersionBuf = ByteBuffer.wrap(rHEADERVersionRaw);
    version = bHEADERVersionBuf.getShort();

    short mVersionShouldBe = 0x01;
    if (version != mVersionShouldBe) {
      throw new RuntimeException("Version is not recognised");
    }

    ByteBuffer bHEADEREndianBuf = ByteBuffer.wrap(rHEADEREndianRaw);
    short rEndiannessBytes = bHEADEREndianBuf.getShort();

    // this is stupid
    short oMI = (byte) 'M' << 8 | (byte) 'I';
    short oIM = (byte) 'I' << 8 | (byte) 'M';

    if (rEndiannessBytes == oMI) {
      endian = ByteOrder.BIG_ENDIAN;
    } else if (rEndiannessBytes == oIM) {
      endian = ByteOrder.LITTLE_ENDIAN;
    } else {
      throw new RuntimeException("Endianness is not recognised");
    }

    return new MATHeaderStruct(header, version, endian);

  }

}
