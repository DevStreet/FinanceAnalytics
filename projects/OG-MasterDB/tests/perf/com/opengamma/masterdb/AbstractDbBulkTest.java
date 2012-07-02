/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.masterdb;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.opengamma.util.functional.Functional.cycle;
import static org.apache.commons.lang.StringUtils.join;

import java.io.*;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

import javax.time.calendar.LocalDateTime;
import javax.time.calendar.TimeZone;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.testng.annotations.AfterSuite;

import com.opengamma.id.UniqueId;
import com.opengamma.util.test.DbTest;
import com.opengamma.util.tuple.Pair;

/**
 * Base tests for DbSecurityMasterWorker via DbSecurityMaster.
 */
public abstract class AbstractDbBulkTest extends DbTest {

  private static final Logger s_logger = LoggerFactory.getLogger(AbstractDbBulkTest.class);

  private static final int CHUNK = 1000;

  final protected Random _random = new Random();


  public AbstractDbBulkTest(String databaseType, String databaseVersion) {
    super(databaseType, databaseVersion);
    s_logger.info("running testcases for {}", databaseType);
  }

  protected abstract AbstractDbMaster getMaster();

  protected String milliIntervalToString(long interval) {
    String unit = "milliseconds";
    if (interval > 1000) {
      interval /= 1000.0;
      unit = "seconds";
      if (interval > 60) {
        interval /= 60;
        unit = "minutes";
      }
    }
    return interval + " " + unit;
  }

  protected String nanosecondsIntervalToString(long interval) {
    String unit = "nanoseconds";
    if (interval > 1000) {
      interval /= 1000.0;
      unit = "microseconds";
      if (interval > 1000) {
        interval /= 1000.0;
        unit = "miliseconds";
        if (interval > 1000) {
          interval /= 1000.0;
          unit = "seconds";
        }
      }

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

  protected UniqueId randomUniqueId() {
    return UniqueId.of(getMaster().getUniqueIdScheme(), "" + _random.nextInt());
  }

  protected byte[] randomBytes(int count) {
    byte[] bytes = new byte[count];
    _random.nextBytes(bytes);
    return bytes;
  }

  abstract protected void seed(int count);

  ExecutorService execSvc = Executors.newFixedThreadPool(18);

  private AtomicInteger progress = new AtomicInteger(1);

  /**
   * Atomically increments by one the current value.
   *
   * @return the previous value
   */
  public final int getAndIncrement(int increment) {
    for (; ; ) {
      int current = progress.get();
      int next = current + increment;
      if (progress.compareAndSet(current, next))
        return current;
    }
  }

  class Seeder implements Callable<Long> {
    private int _count;
    private int _seedCount;
    private long _start;
    private FutureTask<Long> _prev;

    Seeder(int count, int seedCount, long start, FutureTask<Long> prev) {
      _count = count;
      _start = start;
      _prev = prev;
      _seedCount = seedCount;
    }

    @Override
    public Long call() throws Exception {
      final long intraStart = System.nanoTime();
      seed(_count);
      final long intraEnd = System.nanoTime();
      int c = getAndIncrement(_count);
      s_logger.warn("CHUNK {} of {}. Seeding {} chunk took {} (You need to wait aprox {})", new Object[]{1 + (c / CHUNK), _seedCount / CHUNK, CHUNK, nanosecondsIntervalToString(intraEnd - intraStart), nanosecondsIntervalToString((_seedCount - c) * (intraEnd - _start) / c)});
      if (_prev == null) {
        return intraEnd - _start;
      } else {
        _prev.get();
        return intraEnd - _start;
      }
    }
  }

  private void seedChunk(int count) {
    progress.set(1);
    s_logger.warn("Starting seeding {} records at {}", count, new Date());
    final long start = System.nanoTime();

    FutureTask<Long> task = null;


    for (int c = 1; c <= count; c += CHUNK) {
      task = new FutureTask<Long>(new Seeder(Math.min(CHUNK, count - c + 1), count, start, task));
      execSvc.execute(task);
    }
    if (task != null) {
      try {
        s_logger.warn("Seeding {} records finished at {}. It took {}", new Object[]{count, new Date(), nanosecondsIntervalToString(task.get())});
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }


  @AfterSuite
  public static void closeAfterSuite() {
    DbMasterTestUtils.closeAfterSuite();


  }

  public void testOperations(int steps, int seedIncrement, int initSeedCount) {

    String operationOnly = System.getProperty("operation");

    long count = 0;
    final long[] start = new long[1];
    final long[] end = new long[1];
    start[0] = System.nanoTime();
    s_logger.warn("INITIAL SEED: {}", initSeedCount);
    seedChunk(initSeedCount);
    s_logger.warn("\n\n\n\n\n\n\n");
    end[0] = System.nanoTime();

    count += initSeedCount;

    Map<Method, Pair<Map<String, Object>, List<Pair<Long, Double>>>> methods = newHashMap();

    for (final Method m : getClass().getMethods()) {
      if (m.isAnnotationPresent(Operation.class) && (operationOnly == null || m.getName().equals(operationOnly))) {
        Operation operation = m.getAnnotation(Operation.class);
        final int batchSize = operation.batchSize();
        final List<Pair<Long, Double>> operatonMasurments = newArrayList();

        Map<String, Object> meta = newHashMap();
        meta.put("batchSize", batchSize);
        meta.put("preseed", initSeedCount);
        meta.put("seed", seedIncrement);
        meta.put("batchSize", batchSize);
        methods.put(m, Pair.of(meta, operatonMasurments));
      }
    }

    Iterator<Map.Entry<Method, Pair<Map<String, Object>, List<Pair<Long, Double>>>>> entries = cycle(methods.entrySet().iterator());

    final Object self = this;
    long veryStart = System.currentTimeMillis();

    for (int i = 1; i <= steps; i++) {
      final Map.Entry<Method, Pair<Map<String, Object>, List<Pair<Long, Double>>>> entry = entries.next();
      final Method m = entry.getKey();
      Map<String, Object> meta = entry.getValue().getFirst();
      final int batchSize = (Integer) meta.get("batchSize");
      final List<Pair<Long, Double>> operatonMasurments = entry.getValue().getSecond();

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
      seedChunk(seedIncrement);
      count += seedIncrement;
      long innerEnd = System.currentTimeMillis();
      s_logger.warn("Step: {} of {} took {}. You will need to wait aprox. {}", new Object[]{i, steps, nanosecondsIntervalToString(end[0] - start[0]), milliIntervalToString((steps - i) * (innerEnd - veryStart) / (i))});
    }

    for (Map.Entry<Method, Pair<Map<String, Object>, List<Pair<Long, Double>>>> methodsWithMeta : methods.entrySet()) {
      String name = methodsWithMeta.getKey().getName();
      List<Pair<Long, Double>> operatonMasurments = methodsWithMeta.getValue().getSecond();
      Map<String, Object> meta = methodsWithMeta.getValue().getFirst();
      dump(operatonMasurments, getDatabaseType(), getClass().getName().replace(getClass().getPackage().getName() + ".", ""), name, meta);
    }
  }

  private void dump(List<Pair<Long, Double>> measurments, String dbType, String master, String operation, Map<String, Object> meta) {
    OutputStream os = null;
    try {

      String template = "define(function () {\n" +
        "return {" +
        "    preseed: '{{preseed}}',   \n" +
        "  batchSize: '{{batchSize}}', \n" +
        "       seed: '{{seed}}',      \n" +
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
            .replace("{{master}}", master)
            .replace("{{preseed}}", meta.get("preseed").toString())
            .replace("{{batchSize}}", meta.get("batchSize").toString())
            .replace("{{seed}}", meta.get("seed").toString())
        );
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
