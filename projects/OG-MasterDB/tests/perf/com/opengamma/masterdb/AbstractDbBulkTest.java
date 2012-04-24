/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.masterdb;

import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.lang.StringUtils.join;

import java.io.*;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;

import javax.time.calendar.LocalDateTime;
import javax.time.calendar.TimeZone;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.testng.annotations.AfterSuite;

import com.opengamma.util.test.DbTest;
import com.opengamma.util.tuple.Pair;

/**
 * Base tests for DbSecurityMasterWorker via DbSecurityMaster.
 */
public abstract class AbstractDbBulkTest extends DbTest {

  private static final Logger s_logger = LoggerFactory.getLogger(AbstractDbBulkTest.class);

  final protected Random _random = new Random();

  public AbstractDbBulkTest(String databaseType, String databaseVersion) {
    super(databaseType, databaseVersion);
    s_logger.info("running testcases for {}", databaseType);
  }


  protected String nanosecondsIntervalToString(long interval) {
    String unit = "nanoseconds";
    if (interval > 1000) {
      interval /= 1000.0;
      unit = "microseconds";
    }
    if (interval > 1000) {
      interval /= 1000.0;
      unit = "miliseconds";
    }
    if (interval > 1000) {
      interval /= 1000.0;
      unit = "seconds";
    }
    return interval + " " + unit;
  }

  protected String reportTime(long start, long end, long count) {
    return String.format("%d operations took %s. That is %s per operation", count, nanosecondsIntervalToString(end - start), nanosecondsIntervalToString((end - start) / count));
  }

  protected LocalDateTime randomDate(LocalDateTime start, LocalDateTime end) {
    long seconds = end.atZone(TimeZone.UTC).toEpochSeconds() - start.atZone(TimeZone.UTC).toEpochSeconds();
    long randomSeconds = _random.nextLong() % seconds;
    return start.plusSeconds((int) randomSeconds);
  }

  protected String randomString(int count) {
    int byteCount = (count / 2) + (count % 2);
    byte[] bytes = new byte[byteCount];
    _random.nextBytes(bytes);
    return new String(Hex.encodeHex(bytes)).substring(0, count);
  }

  protected byte[] randomBytes(int count) {
    byte[] bytes = new byte[count];
    _random.nextBytes(bytes);
    return bytes;
  }

  abstract protected void seed(int count);


  @AfterSuite
  public static void closeAfterSuite() {
    DbMasterTestUtils.closeAfterSuite();
  }

  public void testOperations(int steps, int seedIncrement, int initSeedCount) {

    long count = 0;
    seed(initSeedCount);
    count += initSeedCount;

    final long[] start = new long[1];
    final long[] end = new long[1];

    for (final Method m : getClass().getMethods()) {
      if (m.isAnnotationPresent(Operation.class)) {

        Operation operation = m.getAnnotation(Operation.class);
        final int batchSize = operation.batchSize();
        final List<Pair<Long, Double>> operatonMasurments = newArrayList();
        final Object self = this;

        for (int i = 0; i < steps; i++) {
          System.gc();
          final long finalCount = count;
          getDbConnector().getTransactionTemplate().execute(new TransactionCallback<Void>() {
            @Override
            public Void doInTransaction(TransactionStatus status) {
              start[0] = System.nanoTime();
              for (int i = 0; i < batchSize; i++) {
                try {
                  m.invoke(self);
                } catch (Throwable ex) {
                  throw new RuntimeException(ex);
                }
              }
              end[0] = System.nanoTime();
              operatonMasurments.add(Pair.of(finalCount, (1e9 * batchSize / (end[0] - start[0]))));
              return null;
            }
          });
          seed(seedIncrement);
          count += seedIncrement;
        }
        dump(operatonMasurments, getDatabaseType(), getClass().getName(), m.getName());
      }
    }


  }

  private void dump(List<Pair<Long, Double>> measurments, String dbType, String master, String operation) {
    OutputStream os = null;
    try {

      String template = "define(function () {\n" +
        "return {" +
        "  operation: '{{operation}}', \n" +
        "     dbtype: '{{dbtype}}',    \n" +
        "     master: '{{master}}'     \n," +
        "       data: [{{data}}]       \n" +
        "}});                          \n";
      try {
        os = new FileOutputStream(new File("./tests/perf-tests-output/app/" + dbType + "_" + master + "_" + operation + ".js"));

        Writer writer = new OutputStreamWriter(os);
        writer.write(
          template
            .replace("{{data}}", join(measurments, ",\n"))
            .replace("{{operation}}", operation)
            .replace("{{dbtype}}", dbType)
            .replace("{{master}}", master));
        writer.flush();
      } finally {
        if (os != null) {
          os.close();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  protected long nextId(String sequenceName) {
    return getDbConnector().getJdbcTemplate().queryForLong(getDbConnector().getDialect().sqlNextSequenceValueSelect(sequenceName));
  }

}
