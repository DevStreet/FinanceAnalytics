/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.tool;


import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.integration.copiernew.Writeable;
import com.opengamma.integration.copiernew.sheet.PrettyWriter;

import com.opengamma.util.generate.scripts.Scriptable;

import java.io.FileOutputStream;
import java.io.IOException;

@Scriptable
public class CopyTool {

  public static void main(String[] args) {

    if (args.length < 1 || args.length > 2) {
      throw new OpenGammaRuntimeException("At least one argument required");
    }

    Iterable reader = ReaderWriterUtils.getMasterReader(args[0]);

    Writeable writer;
    if (args.length == 2) {
      writer = ReaderWriterUtils.getMasterWriter(args[1]);
    } else {
      writer = new PrettyWriter(System.out);
    }

    writer.addOrUpdate(reader);

    try {
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();  // TODO
    }

  }
}

//    Iterable reader = ReaderWriterUtils.getMasterReader("Config", "central", "http://devsvr-lx-2:8080", "K*");
//    Writeable writer = ReaderWriterUtils.getMasterWriter("Security", "central", "http://devsvr-lx-2:8080", "<name> Copy");
//    Writeable writer = ReaderWriterUtils.getMasterWriter("http://devsvr-lx-2:8080/jax/components/SecurityMaster/central/<name> copy");
//    RowWriter rowWriter = new SecurityRowWriter(EquitySecurity.class);
//    Writeable writer = new SheetWriter(new CsvRawSheetWriter(System.out, rowWriter.getColumns()), rowWriter);
