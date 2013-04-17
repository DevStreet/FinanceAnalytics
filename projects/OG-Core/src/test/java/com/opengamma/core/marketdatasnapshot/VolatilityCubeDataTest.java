package com.opengamma.core.marketdatasnapshot;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.opengamma.id.UniqueIdentifiable;
import com.opengamma.util.money.Currency;
import com.opengamma.util.test.TestGroup;
import com.opengamma.util.time.Tenor;

/**
 * Test {@link VolatilityCubeData}.
 */
@Test(groups = TestGroup.UNIT)
public class VolatilityCubeDataTest {

  @Test
  public void test() {
    final String definitionName = "OPENGAMMA";
    final String specificationName = "DEFAULT";
    final UniqueIdentifiable target = Currency.AUD;
    final Tenor[] uniqueXs = new Tenor[] {Tenor.ONE_MONTH, Tenor.TWO_MONTHS, Tenor.THREE_MONTHS, Tenor.SIX_MONTHS, Tenor.ONE_YEAR };
    final String xLabel = "x";
    final Tenor[] uniqueYs = new Tenor[] {Tenor.TWO_YEARS, Tenor.THREE_YEARS, Tenor.FIVE_YEARS, Tenor.TEN_YEARS };
    final String yLabel = "y";
    final Double[] uniqueZs = new Double[] {0.1, 0.25, 0.5, 0., 0.3 };
    final String zLabel = "z";
    final Tenor[] xs = new Tenor[100];
    final Tenor[] ys = new Tenor[100];
    final Double[] zs = new Double[100];
    int i = 0;
    final Map<VolatilityPoint<Tenor, Tenor, Double>, Double> vols = new HashMap<>();
    for (final Tenor x : uniqueXs) {
      for (final Tenor y : uniqueYs) {
        for (final Double z : uniqueZs) {
          xs[i] = x;
          ys[i] = y;
          zs[i++] = z;
          vols.put(new VolatilityPoint<>(x, y, z), Math.random());
        }
      }
    }
    final VolatilityCubeData<Tenor, Tenor, Double> cube = new VolatilityCubeData<>(definitionName, specificationName, target, xs, xLabel, ys,
        yLabel, zs, zLabel, vols);
    assertEquals(definitionName, cube.getDefinitionName());
    assertEquals(specificationName, cube.getSpecificationName());
    assertEquals(xLabel, cube.getXLabel());
    assertEquals(yLabel, cube.getYLabel());
    assertEquals(zLabel, cube.getZLabel());
    assertArrayEquals(xs, cube.getXs());
    assertArrayEquals(ys, cube.getYs());
    assertArrayEquals(zs, cube.getZs());
    assertEquals(vols, cube.getData());
    assertEquals(cube, new VolatilityCubeData<>(definitionName, specificationName, target, xs, xLabel, ys, yLabel, zs, zLabel, vols));
    assertEquals(cube.hashCode(), new VolatilityCubeData<>(definitionName, specificationName, target, xs, xLabel, ys, yLabel, zs, zLabel, vols).hashCode());
  }

  private void assertArrayEquals(final Tenor[] expected, final Tenor[] actual) {
    if (expected == null) {
      assertNull(actual);
      return;
    }
    assertEquals(expected.length, actual.length);
    for (int i = 0; i < expected.length; i++) {
      assertEquals(expected[i], actual[i]);
    }
  }

  private void assertArrayEquals(final Double[] expected, final Double[] actual) {
    if (expected == null) {
      assertNull(actual);
      return;
    }
    assertEquals(expected.length, actual.length);
    for (int i = 0; i < expected.length; i++) {
      assertEquals(expected[i], actual[i], 0);
    }
  }
}
