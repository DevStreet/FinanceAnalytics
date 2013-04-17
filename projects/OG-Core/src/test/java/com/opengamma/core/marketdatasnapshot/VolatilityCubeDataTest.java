package com.opengamma.core.marketdatasnapshot;

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

  private static VolatilityCubeData getSimpleData() {
    final String definitionName = "OPENGAMMA";
    final String specificationName = "DEFAULT";
    final UniqueIdentifiable target = Currency.AUD;
    final Tenor[] xs = new Tenor[] {Tenor.ONE_MONTH, Tenor.TWO_MONTHS, Tenor.THREE_MONTHS, Tenor.SIX_MONTHS, Tenor.ONE_YEAR };
    final String xLabel = "x";
    final Tenor[] ys = new Tenor[] {Tenor.TWO_YEARS, Tenor.THREE_YEARS, Tenor.FIVE_YEARS, Tenor.TEN_YEARS };
    final String yLabel = "y";
    final Double[] zs = new Double[] {0.1, 0.25, 0.5, 0., 0.3 };
    final String zLabel = "z";
    final Map<VolatilityPoint<Tenor, Tenor, Double>, Double> vols = new HashMap<>();
    for (final Tenor x : xs) {
      for (final Tenor y : ys) {
        for (final Double z : zs) {
          vols.put(new VolatilityPoint<>(x, y, z), Math.random());
        }
      }
    }
    return new VolatilityCubeData<>(definitionName, specificationName, target, xLabel, yLabel, zLabel, vols);
  }

}
