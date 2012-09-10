/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.performanceutils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.Set;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionGeneric;

/**
 * Dumps a ResultsHashMapToFile, TODO: make this neater, perhaps ask tech if they have a csv writer or something 
 */
public class DumpResultsHashMapToFile {

  public static void dump(String filenameRoot, ResultsHashMap map) {

    Set<String> testedThingyStrings = map.keySet();

    FileWriter fp;
    for (String keys : testedThingyStrings) {
      StringBuffer sb = new StringBuffer();
      Formatter format = new Formatter(sb);
      try {
        fp = new FileWriter("results" + File.separatorChar + filenameRoot + keys + ".txt");
      } catch (IOException e) {
        throw new MathsExceptionGeneric("Dump file requested cannot be opened");
      }
      BufferedWriter outstream = new BufferedWriter(fp);
      TimerResultsSet tr = map.get(keys);
      try {
        int[] dataI = tr.getSizes();
        for (int i = 0; i < dataI.length - 1; i++) {
          format.format("%d", dataI[i]);
          format.format("%s", ",");
        }
        format.format("%d", dataI[dataI.length - 1]);
        format.format("%s", "\n");

        long[] dataL = tr.getTimes();
        for (int i = 0; i < dataL.length - 1; i++) {
          format.format("%d", dataL[i]);
          format.format("%s", ",");
        }
        format.format("%d", dataL[dataL.length - 1]);
        outstream.write(sb.toString());
      } catch (IOException e) {
        throw new MathsExceptionGeneric("Dump file write failed on times");
      }
      try {
        outstream.close();
      } catch (IOException e) {
        throw new MathsExceptionGeneric("Dump file failed to close");
      }
    }

  }

}
