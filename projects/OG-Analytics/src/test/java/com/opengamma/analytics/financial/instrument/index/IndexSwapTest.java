/**
 * Copyright (C) 2011 - present by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.instrument.index;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

import org.testng.annotations.Test;
import org.threeten.bp.Period;
import com.opengamma.util.test.TestGroup;

/**
 * Test related to swap index.
 */
@Test(groups = TestGroup.UNIT)
public class IndexSwapTest {
  
  private static final GeneratorSwapFixedIborMaster GENERATOR_SWAP_MASTER = GeneratorSwapFixedIborMaster.getInstance();
  private static final String NAME_GEN = "USD6MLIBOR3M";
  private static final GeneratorSwapFixedIbor USD6MLIBOR3M = GENERATOR_SWAP_MASTER.getGenerator(NAME_GEN);
  private static final Period SWAP_TENOR = Period.ofYears(2);
  private static final String NAME_INDEX = NAME_GEN + "_" + SWAP_TENOR.toString();
  private static final IndexSwap SWAP_INDEX = new IndexSwap(NAME_INDEX, USD6MLIBOR3M, SWAP_TENOR);

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void nullGenerator() {
    new IndexSwap(null, SWAP_TENOR);
  }
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void nullTenor() {
    new IndexSwap(USD6MLIBOR3M, null);
  }

  @Test
  public void testGetter() {
    assertEquals("IndexSwap: getter", USD6MLIBOR3M, SWAP_INDEX.getGenerator());
    assertEquals("IndexSwap: getter", SWAP_TENOR, SWAP_INDEX.getTenor());
    assertEquals("IndexSwap: getter", NAME_INDEX, SWAP_INDEX.getName());
  }

  @Test
  public void testEqualHash() {
    assertEquals("IndexSwap: equal-hash", SWAP_INDEX, SWAP_INDEX);
    final IndexSwap indexDuplicate  = new IndexSwap(NAME_INDEX, USD6MLIBOR3M, SWAP_TENOR);
    assertEquals("IndexSwap: equal-hash", SWAP_INDEX, indexDuplicate);
    assertEquals("IndexSwap: equal-hash", SWAP_INDEX.hashCode(), indexDuplicate.hashCode());
    IndexSwap indexModified;
    indexModified = new IndexSwap(NAME_GEN, USD6MLIBOR3M, SWAP_TENOR);
    assertFalse("IndexSwap: equal-hash", SWAP_INDEX.equals(indexModified));
    final GeneratorSwapFixedIbor otherGen = GENERATOR_SWAP_MASTER.getGenerator("EUR1YEURIBOR3M");
    indexModified = new IndexSwap(NAME_INDEX, otherGen, SWAP_TENOR);
    assertFalse("IndexSwap: equal-hash", SWAP_INDEX.equals(indexModified));
    indexModified = new IndexSwap(NAME_INDEX, USD6MLIBOR3M, Period.ofYears(5));
    assertFalse("IndexSwap: equal-hash", SWAP_INDEX.equals(indexModified));
    assertFalse(SWAP_INDEX.equals(null));
    assertFalse(SWAP_INDEX.equals(NAME_GEN));
  }

}
