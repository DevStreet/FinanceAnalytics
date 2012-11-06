/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.test;

import com.opengamma.integration.copiernew.ReaderWriterUtils;
import com.opengamma.integration.copiernew.Writeable;
import com.opengamma.integration.copiernew.external.StreamReader;
import com.opengamma.integration.copiernew.external.StreamWriter;

import com.sun.org.apache.xerces.internal.dom.ElementImpl;
import org.apache.commons.io.IOUtils;

import org.custommonkey.xmlunit.examples.RecursiveElementNameAndTextQualifier;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import org.custommonkey.xmlunit.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/*
Round trip for each data type and external format (cross-product)
Empty and malformed data/wrong data type in each external format
Valid/invalid filters and renaming options
*/

@Test
public class RoundTripTest {

  @Test
  public void testRoundTrip() throws Exception {

    for (String name : new String[] {"Config", "Security", "Holiday"}) { //, "Region", "Exchange", "Portfolio", "Position"}) {

      // Create mock master
      Object master = ReaderWriterUtils.getMasterClassInfo(name).getInMemoryMasterClass().newInstance();

      // Write to master
      InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("com/opengamma/integration/copiernew/test/" + name + ".xml");
      Iterable streamReader = new StreamReader(inputStream);
      Writeable masterWriter = ReaderWriterUtils.getMasterClassInfo(name).getWriterClass().getConstructor(ReaderWriterUtils.getMasterClassInfo(name).getMasterClass()).newInstance(master);
      masterWriter.addOrUpdate(streamReader);
      masterWriter.flush();

      // Read from master
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      StreamWriter streamWriter = new StreamWriter(outputStream);
      Iterable masterReader = ReaderWriterUtils.getMasterClassInfo(name).getReaderClass().getConstructor(ReaderWriterUtils.getMasterClassInfo(name).getMasterClass()).newInstance(master);
      streamWriter.addOrUpdate(masterReader);
      streamWriter.flush();
      streamWriter.close();

      // Compare results
      String original = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("com/opengamma/integration/copiernew/test/" + name + ".xml"));

      XMLUnit.setIgnoreAttributeOrder(true);
      XMLUnit.setNormalize(true);
      XMLUnit.setIgnoreComments(true);
      XMLUnit.setIgnoreWhitespace(true);
      XMLUnit.setIgnoreDiffBetweenTextAndCDATA(true);

      System.out.print("Performing " + name + " round trip test... ");

      Diff diff = new Diff(original, outputStream.toString());
      diff.overrideElementQualifier(new RecursiveElementNameAndTextQualifier());
      System.out.println("ready: " + diff.similar());

      if (!diff.similar()) {
        StringBuilder sb = new StringBuilder();
        DetailedDiff myDiff = new DetailedDiff(diff);
        myDiff.overrideElementQualifier(new
          RecursiveElementNameAndTextQualifier());
        List<Difference> differences = myDiff.getAllDifferences();
        for (Difference d : differences)
            sb.append(d.toString() + "\n");
        System.out.println(sb.toString());
        System.out.println();
      }

      assertTrue(diff.similar());
    }
  }

}
