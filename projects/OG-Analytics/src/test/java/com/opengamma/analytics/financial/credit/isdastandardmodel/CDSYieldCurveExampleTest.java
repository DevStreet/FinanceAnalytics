package com.opengamma.analytics.financial.credit.isdastandardmodel;

import org.testng.annotations.Test;
import org.threeten.bp.LocalDate;
import org.threeten.bp.Month;
import org.threeten.bp.Period;

import com.opengamma.analytics.math.interpolation.CombinedInterpolatorExtrapolatorFactory;
import com.opengamma.analytics.math.interpolation.Interpolator1D;
import com.opengamma.analytics.math.interpolation.data.Interpolator1DDataBundle;
import com.opengamma.financial.convention.businessday.BusinessDayDateUtils;
import com.opengamma.financial.convention.calendar.Calendar;
import com.opengamma.financial.convention.calendar.MondayToFridayCalendar;

@Test
public class CDSYieldCurveExampleTest {

  private static final double[] DAY = new double[] {91, 183, 274, 365, 457, 548, 639, 731, 1096, 1461, 1826, 2192,
    2557, 2922, 3287, 3653, 4383, 5479, 7305, 9131, 10958, 14610, 18263 };
  private static final int NUM_NODE = DAY.length;
  private static final double[] TIME;
  static {
    TIME = new double[NUM_NODE];
    for (int i = 0; i < NUM_NODE; ++i) {
      TIME[i] = DAY[i] / 365.;
    }
  }
  private static final double[] DISCOUNT_FACTOR = new double[] {0.999829198540859, 0.9996527611741278,
    0.9994706948329838, 0.9992773181206462, 0.999064682720019, 0.9988244611565431, 0.9985396977323168,
    0.9981914957448704, 0.9958878795040178, 0.9913266485465384, 0.9837191754362143, 0.9727087439132421,
    0.9585650232460775, 0.9416595859820656, 0.922635706673141, 0.90211598325509, 0.8592002467360544,
    0.7954480462459721, 0.7016145412095421, 0.6251659631564156, 0.5609548680303263, 0.45423350825036723,
    0.3726483241596532 };
  private static final Interpolator1D INTERP = CombinedInterpolatorExtrapolatorFactory.getInterpolator(
      "LogNaturalCubicWithMonotonicity", "QuadraticLeftExtrapolator", "LinearExtrapolator");
  private static final Interpolator1DDataBundle BUNDLE = INTERP.getDataBundleFromSortedArrays(TIME, DISCOUNT_FACTOR);

  @Test
  public void curveComparisonTest() {
    double[] zeroRate = new double[NUM_NODE];
    for (int i = 0; i < NUM_NODE; ++i) {
      zeroRate[i] = -Math.log(DISCOUNT_FACTOR[i]) / TIME[i];
    }
    ISDACompliantYieldCurve isdaCurve = new ISDACompliantYieldCurve(TIME, zeroRate);

    int nKey = 400; // covers 20 years
    for (int i = 0; i < nKey; ++i) {
      double key = 0.05 * i;
      double resCub = INTERP.interpolate(BUNDLE, key);
      double resLin = isdaCurve.getDiscountFactor(key);
      System.out.println(key + "\t" + resCub + "\t" + resLin + "\t" + Math.abs((resLin - resCub) / resCub));
    }
  }

  private static final Calendar DEFAULT_CALENDAR = new MondayToFridayCalendar("Weekend_Only");
  //Calculators: all calculations are based on ORIGINAL_ISDA
  private static final AnalyticCDSPricer PRICER = new AnalyticCDSPricer();
  private static final FiniteDifferenceSpreadSensitivityCalculator FD_SPREAD_SENSE_CAL = new FiniteDifferenceSpreadSensitivityCalculator();
  //Trade
  private static final CDSAnalyticFactory CDS_FACTORY = new CDSAnalyticFactory(0.4);
  private static final double NOTIONAL = 10.e6;
  private static final double COUPON = 0.01;
  private static final LocalDate TRADE_DATE = LocalDate.of(2011, Month.JUNE, 13);
  private static final LocalDate NEXT_IMM = IMMDateLogic.getNextIMMDate(TRADE_DATE);
  private static final LocalDate STEPIN = TRADE_DATE.plusDays(1);
  private static final LocalDate CASH_SETTLE_DATE = BusinessDayDateUtils.addWorkDays(TRADE_DATE, 3, DEFAULT_CALENDAR);
  private static final LocalDate STARTDATE = IMMDateLogic.getPrevIMMDate(TRADE_DATE);
  private static final boolean PAY_ACC_ON_DEFAULT = true;
  private static final Period PAYMENT_INTERVAL = Period.ofMonths(3);
  private static final StubType STUB = StubType.FRONTSHORT;
  private static final boolean PROCTECTION_START = true;
  private static final double RECOVERY_RATE = 0.4;
  // Credit curve form pillar CDSs
  private static final Period[] TENORS = new Period[] {Period.ofMonths(6), Period.ofYears(1), Period.ofYears(3),
    Period.ofYears(5), Period.ofYears(7), Period.ofYears(10) };
  private static final LocalDate[] PILLAR_DATES = IMMDateLogic.getIMMDateSet(NEXT_IMM, TENORS);
  private static final double[] SPREADS = new double[] {0.007926718, 0.007926718, 0.012239372, 0.016978579,
    0.019270856, 0.02086048 };
  private static final CDSAnalytic[] PILLAR_CDSS;
  static {
    final int nPillars = PILLAR_DATES.length;
    PILLAR_CDSS = new CDSAnalytic[nPillars];
    for (int i = 0; i < nPillars; i++) {
      PILLAR_CDSS[i] = new CDSAnalytic(TRADE_DATE, STEPIN, CASH_SETTLE_DATE, STARTDATE, PILLAR_DATES[i],
          PAY_ACC_ON_DEFAULT, PAYMENT_INTERVAL, STUB, PROCTECTION_START, RECOVERY_RATE);
    }
  }
  //Bucket CDSs 
  private static final Period[] BUCKETS = new Period[] {Period.ofMonths(6), Period.ofYears(1), Period.ofYears(2),
    Period.ofYears(3), Period.ofYears(4), Period.ofYears(5), Period.ofYears(6), Period.ofYears(7), Period.ofYears(8),
    Period.ofYears(9), Period.ofYears(10), Period.ofYears(12), Period.ofYears(15), Period.ofYears(20),
    Period.ofYears(25), Period.ofYears(30) };
  private static final LocalDate[] BUCKET_DATES = IMMDateLogic.getIMMDateSet(NEXT_IMM, BUCKETS);
  private static final CDSAnalytic[] BUCKET_CDSS = CDS_FACTORY.makeCDS(TRADE_DATE, STARTDATE, BUCKET_DATES);

  private static final ISDACompliantYieldCurve[] YIELD_CURVE;
  private static final String[] MESSAGE;
  static {
    double[] zeroRate = new double[NUM_NODE];
    /* apply linear interpolator on r*t */
    for (int i = 0; i < NUM_NODE; ++i) {
      zeroRate[i] = -Math.log(DISCOUNT_FACTOR[i]) / TIME[i];
    }
    ISDACompliantYieldCurve isdaCurve = new ISDACompliantYieldCurve(TIME, zeroRate);
    /* approximating log-cubic by taking large number of sampling points */
    double[] intervals = new double[] {1.0 / 12., 1.0 / 52., 1.0 / 360. };
    MESSAGE = new String[] {"log-linear:     ", "log-cubic (1M): ", "log-cubic (1W): ", "log-cubic (1D): " };
    int nIntervals = intervals.length;
    YIELD_CURVE = new ISDACompliantYieldCurve[nIntervals + 1];
    YIELD_CURVE[0] = isdaCurve;
    for (int j = 0; j < nIntervals; ++j) {
      double interval = intervals[j];
      int num = (int) (30.0 / interval) + 1; // covers 30 years
      double[] rate = new double[num];
      double[] time = new double[num];
      for (int i = 0; i < num; ++i) {
        double key = interval * (i + 1); // interval approximately 1 day
        time[i] = key;
        rate[i] = -Math.log(INTERP.interpolate(BUNDLE, key)) / key;
      }
      YIELD_CURVE[j + 1] = new ISDACompliantYieldCurve(time, rate);
    }
  }

  private static final Period[] tenors = new Period[] {Period.ofMonths(9), Period.of(2, 3, 0), Period.ofYears(8),
    Period.ofYears(13), Period.ofYears(20) };
  
  
  private final double ONE_BP = 1e-4;

  @Test
  public void IMMCDSFromPillarCDSTest() {
    for (int k = 0; k < tenors.length; ++k) {
      // Build pricing CDS
      Period tenor = tenors[k];
      System.out.println("Pricing CDS tenor " + tenor);
      LocalDate maturityDate = NEXT_IMM.plus(tenor); // 2013-09-20
      CDSAnalytic pricingCDS = new CDSAnalytic(TRADE_DATE, STEPIN, CASH_SETTLE_DATE, STARTDATE, maturityDate,
          PAY_ACC_ON_DEFAULT, PAYMENT_INTERVAL, STUB, PROCTECTION_START, RECOVERY_RATE);

      for (int j = 0; j < YIELD_CURVE.length; ++j) {
        // credit curve
        ISDACompliantCreditCurve cc = (new FastCreditCurveBuilder()).calibrateCreditCurve(PILLAR_CDSS, SPREADS,
            YIELD_CURVE[j]);
        // pv and cs01
        double cleanPV = PRICER.pv(pricingCDS, YIELD_CURVE[j], cc, COUPON) * NOTIONAL;
        double parallelCS01 = FD_SPREAD_SENSE_CAL.parallelCS01FromCreditCurve(pricingCDS, COUPON, BUCKET_CDSS,
            YIELD_CURVE[j], cc, ONE_BP) * ONE_BP * NOTIONAL;
        System.out.println(MESSAGE[j] + "\t" + "pv = " + cleanPV + "\t" + "cs01 = " + parallelCS01);
      }
    }
  }

  @Test
  public void quotedSpreadTest() {
    LocalDate tradeDate = LocalDate.of(2015, Month.FEBRUARY, 23);
    CDSAnalyticFactory facotry = new CDSAnalyticFactory(0.3);
    LocalDate effectiveDate = LocalDate.of(2013, Month.SEPTEMBER, 20);

    LocalDate[] maturitySet = new LocalDate[] {LocalDate.of(2016, Month.SEPTEMBER, 20),
      LocalDate.of(2019, Month.MARCH, 20), LocalDate.of(2023, Month.DECEMBER, 20), LocalDate.of(2027, Month.JUNE, 20) };
    for (int i = 0; i < maturitySet.length; ++i) {
      LocalDate maturity = maturitySet[i];
    CDSAnalytic cdx = facotry.makeCDS(tradeDate, effectiveDate, maturity);

    double spread = 398.7137 * 1.e-4;
    double indexCoupon = 500.0 * 1.e-4;
    double notional = 10.e6;

    System.out.println("Pricing CDS maturity " + maturity);
    for (int j = 0; j < YIELD_CURVE.length; ++j) {
      ISDACompliantCreditCurve indexCurve = (new FastCreditCurveBuilder()).calibrateCreditCurve(cdx, spread,
          YIELD_CURVE[j]);
      double cleanPV = PRICER.pv(cdx, YIELD_CURVE[j], indexCurve, indexCoupon, PriceType.CLEAN) * notional;
      double CS01 = FD_SPREAD_SENSE_CAL.parallelCS01FromCreditCurve(cdx, indexCoupon, new CDSAnalytic[] {cdx },
          YIELD_CURVE[j],
          indexCurve, ONE_BP) * notional * ONE_BP;

      System.out.println(MESSAGE[j] + "\t" + "pv = " + cleanPV + "\t" + "cs01 = " + CS01);
    }
    }

  }

}
