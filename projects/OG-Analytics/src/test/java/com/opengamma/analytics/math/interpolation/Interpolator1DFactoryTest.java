/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.math.interpolation;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.fail;

import java.util.Iterator;

import org.testng.annotations.Test;

/**
 * 
 */
public class Interpolator1DFactoryTest {

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testBadName() {
    Interpolator1DFactory.getInterpolator("x");
  }

  @Test
  public void testNullCalculator() {
    try {
      Interpolator1DFactory.getInterpolatorName(null);
      fail("Did not throw an exception after passing a null.");
    } catch (IllegalArgumentException e) {
    }
  }

  @Test
  public void testStaticLookupByName() {
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.LINEAR));
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.EXPONENTIAL));
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.LOG_LINEAR));
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.NATURAL_CUBIC_SPLINE));
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.STEP));
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.STEP_UPPER));
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.DOUBLE_QUADRATIC));
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.PCHIP));
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.MOD_PCHIP));
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.TIME_SQUARE));
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.FLAT_EXTRAPOLATOR));
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.EXPONENTIAL_EXTRAPOLATOR));
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.ISDA_INTERPOLATOR));
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.ISDA_EXTRAPOLATOR));
    
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.CLAMPED_CUBIC));
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.CLAMPED_CUBIC_MONOTONE));
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.CLAMPED_CUBIC_NONNEGATIVE));
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.CLAMPED_QUINTIC_MONOTONE));
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.CLAMPED_QUINTIC_NONNEGATIVE));
    
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.NATURAL_CUBIC_MONOTONE));
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.NATURAL_CUBIC_NONNEGATIVE));
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.NATURAL_QUINTIC_MONOTONE));
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.NATURAL_QUINTIC_NONNEGATIVE));
    
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.NOTAKNOT_CUBIC));
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.NOTAKNOT_CUBIC_MONOTONE));
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.NOTAKNOT_CUBIC_NONNEGATIVE));
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.NOTAKNOT_QUINTIC_MONOTONE));
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.NOTAKNOT_QUINTIC_NONNEGATIVE));
    
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.AKIMA_CUBIC));
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.AKIMA_CUBIC_MONOTONE));
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.AKIMA_CUBIC_NONNEGATIVE));
    
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.MONOTONE_CONVEX_CUBIC));
    
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.C2_SHAPE_PRESERVING_CUBIC));
    
    assertNotNull(Interpolator1DFactory.of(Interpolator1DFactory.LOG_NATURAL_CUBIC_MONOTONE));
  }
  
  public void testStaticInstanceToStaticNamesMapping() {
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.LINEAR), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.LINEAR)));
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.EXPONENTIAL), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.EXPONENTIAL)));
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.LOG_LINEAR), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.LOG_LINEAR)));
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.NATURAL_CUBIC_SPLINE), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.NATURAL_CUBIC_SPLINE)));
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.STEP), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.STEP)));
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.STEP_UPPER), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.STEP_UPPER)));
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.DOUBLE_QUADRATIC), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.DOUBLE_QUADRATIC)));
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.PCHIP), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.PCHIP)));
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.MOD_PCHIP), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.MOD_PCHIP)));
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.TIME_SQUARE), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.TIME_SQUARE)));
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.FLAT_EXTRAPOLATOR), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.FLAT_EXTRAPOLATOR)));
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.EXPONENTIAL_EXTRAPOLATOR), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.EXPONENTIAL_EXTRAPOLATOR)));
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.ISDA_INTERPOLATOR), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.ISDA_INTERPOLATOR)));
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.ISDA_EXTRAPOLATOR), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.ISDA_EXTRAPOLATOR)));
    
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.CLAMPED_CUBIC), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.CLAMPED_CUBIC)));
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.CLAMPED_CUBIC_MONOTONE), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.CLAMPED_CUBIC_MONOTONE)));
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.CLAMPED_CUBIC_NONNEGATIVE), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.CLAMPED_CUBIC_NONNEGATIVE)));
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.CLAMPED_QUINTIC_MONOTONE), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.CLAMPED_QUINTIC_MONOTONE)));
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.CLAMPED_QUINTIC_NONNEGATIVE), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.CLAMPED_QUINTIC_NONNEGATIVE)));
    
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.NATURAL_CUBIC_MONOTONE), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.NATURAL_CUBIC_MONOTONE)));
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.NATURAL_CUBIC_NONNEGATIVE), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.NATURAL_CUBIC_NONNEGATIVE)));
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.NATURAL_QUINTIC_MONOTONE), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.NATURAL_QUINTIC_MONOTONE)));
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.NATURAL_QUINTIC_NONNEGATIVE), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.NATURAL_QUINTIC_NONNEGATIVE)));
    
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.NOTAKNOT_CUBIC), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.NOTAKNOT_CUBIC)));
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.NOTAKNOT_CUBIC_MONOTONE), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.NOTAKNOT_CUBIC_MONOTONE)));
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.NOTAKNOT_CUBIC_NONNEGATIVE), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.NOTAKNOT_CUBIC_NONNEGATIVE)));
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.NOTAKNOT_QUINTIC_MONOTONE), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.NOTAKNOT_QUINTIC_MONOTONE)));
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.NOTAKNOT_QUINTIC_NONNEGATIVE), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.NOTAKNOT_QUINTIC_NONNEGATIVE)));
    
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.AKIMA_CUBIC), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.AKIMA_CUBIC)));
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.AKIMA_CUBIC_MONOTONE), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.AKIMA_CUBIC_MONOTONE)));
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.AKIMA_CUBIC_NONNEGATIVE), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.AKIMA_CUBIC_NONNEGATIVE)));
    
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.MONOTONE_CONVEX_CUBIC), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.MONOTONE_CONVEX_CUBIC)));
    
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.C2_SHAPE_PRESERVING_CUBIC), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.C2_SHAPE_PRESERVING_CUBIC)));
    
    assertEquals(Interpolator1DFactory.of(Interpolator1DFactory.LOG_NATURAL_CUBIC_MONOTONE), Interpolator1DFactory.getInterpolatorName(Interpolator1DFactory.getInterpolator(Interpolator1DFactory.LOG_NATURAL_CUBIC_MONOTONE)));
  }
  
  public void testEnumerate() {
    Iterator<Interpolator1D> interpolators = Interpolator1DFactory.INSTANCE.enumerateAvailableInterpolators();
    assertNotNull(interpolators);
    assertTrue(interpolators.hasNext());
  }
}
