package com.opengamma.analytics.financial.interestrate.swap.provider;

import static org.testng.AssertJUnit.assertEquals;

import java.util.LinkedHashMap;

import org.testng.annotations.Test;

import com.opengamma.analytics.financial.instrument.index.IborIndex;
import com.opengamma.analytics.financial.interestrate.E2EUtils;
import com.opengamma.analytics.financial.interestrate.InstrumentDerivative;
import com.opengamma.analytics.financial.interestrate.datasets.StandardDataSetsMulticurveUSD;
import com.opengamma.analytics.financial.provider.calculator.discounting.ParRateDiscountingCalculator;
import com.opengamma.analytics.financial.provider.calculator.discounting.ParSpreadMarketQuoteDiscountingCalculator;
import com.opengamma.analytics.financial.provider.calculator.discounting.PresentValueCurveSensitivityDiscountingCalculator;
import com.opengamma.analytics.financial.provider.calculator.discounting.PresentValueDiscountingCalculator;
import com.opengamma.analytics.financial.provider.calculator.generic.MarketQuoteSensitivityBlockCalculator;
import com.opengamma.analytics.financial.provider.curve.CurveBuildingBlockBundle;
import com.opengamma.analytics.financial.provider.description.interestrate.MulticurveProviderDiscount;
import com.opengamma.analytics.financial.provider.description.interestrate.ParameterProviderInterface;
import com.opengamma.analytics.financial.provider.sensitivity.multicurve.MultipleCurrencyParameterSensitivity;
import com.opengamma.analytics.financial.provider.sensitivity.parameter.ParameterSensitivityParameterCalculator;
import com.opengamma.analytics.financial.util.AssertSensitivityObjects;
import com.opengamma.analytics.math.matrix.DoubleMatrix1D;
import com.opengamma.util.money.Currency;
import com.opengamma.util.money.MultipleCurrencyAmount;
import com.opengamma.util.test.TestGroup;
import com.opengamma.util.tuple.ObjectsPair;
import com.opengamma.util.tuple.Pair;

/**
 * Tests the Swap discounting method with standard data.
 * Demo test - worked-out example on how to use OG-Analytics library for compute standard measure to simple instruments. 
 * The data is hard-coded. It is also available in some integration unit test and in snapshots.
 */
@Test(groups = TestGroup.UNIT)
public class SwapCalculatorE2ETest {

  private static final IborIndex[] INDEX_IBOR_LIST = StandardDataSetsMulticurveUSD.indexIborArrayUSDOisL1L3L6();
  private static final IborIndex USDLIBOR1M = INDEX_IBOR_LIST[0];
  private static final IborIndex USDLIBOR3M = INDEX_IBOR_LIST[1];
  private static final IborIndex USDLIBOR6M = INDEX_IBOR_LIST[2];
  private static final Currency USD = USDLIBOR3M.getCurrency();
  /** Calculators */
  private static final PresentValueDiscountingCalculator PVDC = PresentValueDiscountingCalculator.getInstance();
  private static final ParRateDiscountingCalculator PRDC = ParRateDiscountingCalculator.getInstance();
  private static final ParSpreadMarketQuoteDiscountingCalculator PSMQDC = 
      ParSpreadMarketQuoteDiscountingCalculator.getInstance();
  private static final PresentValueCurveSensitivityDiscountingCalculator PVCSDC = 
      PresentValueCurveSensitivityDiscountingCalculator.getInstance();
  private static final ParameterSensitivityParameterCalculator<ParameterProviderInterface> PSC = 
      new ParameterSensitivityParameterCalculator<>(PVCSDC);
  private static final MarketQuoteSensitivityBlockCalculator<ParameterProviderInterface> MQSBC = 
      new MarketQuoteSensitivityBlockCalculator<>(PSC);
  /** Curve providers */
  private static final Pair<MulticurveProviderDiscount, CurveBuildingBlockBundle> MULTICURVE_OIS_PAIR = 
      StandardDataSetsMulticurveUSD.getCurvesUSDOisL1L3L6();
  private static final MulticurveProviderDiscount MULTICURVE_OIS = MULTICURVE_OIS_PAIR.getFirst();
  private static final CurveBuildingBlockBundle BLOCK_OIS = MULTICURVE_OIS_PAIR.getSecond();
  private static final Pair<MulticurveProviderDiscount, CurveBuildingBlockBundle> MULTICURVE_FF_PAIR = 
      StandardDataSetsMulticurveUSD.getCurvesUSDOisFFL1L3L6();
  private static final MulticurveProviderDiscount MULTICURVE_FFS = MULTICURVE_FF_PAIR.getFirst();
  private static final CurveBuildingBlockBundle BLOCK_FFS = MULTICURVE_FF_PAIR.getSecond();
  
  private static final double TOLERANCE_PV = 1.0E-3;
  private static final double TOLERANCE_PV_DELTA = 1.0E-4;
  private static final double TOLERANCE_RATE = 1.0E-8;
  private static final double BP1 = 1.0E-4;
  
  /**
   * Test the present value versus a hard-coded number.
   * @param ins The instrument to test.
   * @param multicurve The multi-curve provider.
   * @param ccy The currency of the expected PV.
   * @param expectedPv The expected PV amount.
   * @param msg The assert message.
   */
  private void presentValueTest(InstrumentDerivative ins, MulticurveProviderDiscount multicurve, Currency ccy, 
      double expectedPv, String msg) {
    MultipleCurrencyAmount pvComputed = ins.accept(PVDC, multicurve);
    assertEquals(msg, expectedPv, pvComputed.getAmount(ccy), TOLERANCE_PV);
  }

  @Test
  /** Tests present value for a swap fixed vs Fed Fund compounded. */
  public void presentValueONCmp() {
    presentValueTest(SwapInstrumentsDataSet.SWAP_FIXED_ON, MULTICURVE_OIS, USD, -9723.264518929138,
        "Swap Fixed v ON compounded: present value from standard curves");
    presentValueTest(SwapInstrumentsDataSet.SWAP_FIXED_ON, MULTICURVE_FFS, USD, -5969.7908,
        "Swap Fixed v ON compounded: present value - Fed Fund swap based curves");
  }

  @Test
  /** Tests present value for a swap fixed vs Fed Fund compounded with fixing. */
  public void presentValueONCmpWithFixing() {
    presentValueTest(SwapInstrumentsDataSet.SWAP_FIXED_ON_S, MULTICURVE_OIS, USD, -7352.973875972721,
        "Swap Fixed v ON compounded: present value from standard curves");
    presentValueTest(SwapInstrumentsDataSet.SWAP_FIXED_ON_S, MULTICURVE_FFS, USD, -5569.499485016839,
        "Swap Fixed v ON compounded: present value from standard curves");
  }

  @Test
  /** Tests present value of ON Arithmetic Average (+ spread) vs Libor3M swaps. */
  public void presentValueONAA3M() {
    presentValueTest(SwapInstrumentsDataSet.SWAP_FF_3M_0, MULTICURVE_OIS, USD, -1617940.0428,
        "Swap ON Arithmetic Average: present value");
    presentValueTest(SwapInstrumentsDataSet.SWAP_FF_3M, MULTICURVE_OIS, USD, -160663.8362,
        "Swap ON Arithmetic Average: present value");
    presentValueTest(SwapInstrumentsDataSet.SWAP_FF_3M_0, MULTICURVE_FFS, USD, -1304207.7900,
        "Swap ON Arithmetic Average: present value");
    presentValueTest(SwapInstrumentsDataSet.SWAP_FF_3M, MULTICURVE_FFS, USD, 142681.6754,
        "Swap ON Arithmetic Average: present value");
  }

  @Test
  /** Test present value for a swap fixed vs LIBOR3M. Curves with OIS and curves with Fed Fund swaps.*/
  public void presentValue3M() {
    presentValueTest(SwapInstrumentsDataSet.SWAP_FIXED_3M, MULTICURVE_OIS, USD, 7170391.798257509,
        "IRS Fixed v LIBOR3M: present value - OIS based curves");
    presentValueTest(SwapInstrumentsDataSet.SWAP_FIXED_3M, MULTICURVE_FFS, USD, 6072234.4631,
        "IRS Fixed v LIBOR3M: present value - Fed Fund swap based curve");
  }

  @Test
  /** Test present value for a swap fixed vs LIBOR3M with fixing. */
  public void presentValue3MWithFixing() {
    presentValueTest(SwapInstrumentsDataSet.SWAP_FIXED_3M_S, MULTICURVE_OIS, USD, 3588376.471608199,
        "IRS Fixed v LIBOR3M: present value - OIS based curves");
    presentValueTest(SwapInstrumentsDataSet.SWAP_FIXED_3M_S, MULTICURVE_FFS, USD, 3194260.3186,
        "IRS Fixed v LIBOR3M: present value - Fed Fund swap based curve");
  }

  @Test
  /** Test present value for a swap LIBOR3M + Spread V LIBOR6M. */
  public void presentValue3M6M() {
    presentValueTest(SwapInstrumentsDataSet.BS_3M_S_6M, MULTICURVE_OIS, USD, -21875.3763, // -13844.3872,
        "Basis swap L3M v L6M: present value");
    presentValueTest(SwapInstrumentsDataSet.BS_3M_S_6M, MULTICURVE_FFS, USD, 77275.5802, // 83456.1099,
        "Basis swap L3M v L6M: present value");
  }

  @Test
  /** Test present value for a swap LIBOR1M Compounding V LIBOR3M. */
  public void presentValue1MCmp3M() {
    presentValueTest(SwapInstrumentsDataSet.BS_1MCMP_3M, MULTICURVE_OIS, USD, -342591.8856, // -340426.6128, //
        "Basis swap L1MFlat v L3M: present value");
    presentValueTest(SwapInstrumentsDataSet.BS_1MCMP_3M, MULTICURVE_FFS, USD, -530867.6701, // -529528.6102,
        "Basis swap L1MFlat v L3M: present value");
  }

  @Test
  /** Test present value for a swap LIBOR1M Compounding FLAT + Spread V LIBOR3M. */
  public void presentValue1MSpreadFlat3M() {
    presentValueTest(SwapInstrumentsDataSet.BS_1MCMP_S_3M, MULTICURVE_OIS, USD, 150219.6976, // 152384.9704,
        "Basis swap L1MFlat v L3M: present value");
    presentValueTest(SwapInstrumentsDataSet.BS_1MCMP_S_3M, MULTICURVE_FFS, USD, -42059.9916,  // -40720.9318,
        "Basis swap L1MFlat v L3M: present value");
  }

  @Test
  /**Test present value for a swap fixed vs LIBOR1M. */
  public void presentValue1M() {
    presentValueTest(SwapInstrumentsDataSet.SWAP_FIXED_1M, MULTICURVE_OIS, USD, -1003684.8402,
        "IRS Fixed v LIBOR1M: present value from standard curves");
  }
  
  @Test
  /**Tests present value for a ON Cmp + spread v ON AA. */
  public void presentValueONCmpONAA() {
    presentValueTest(SwapInstrumentsDataSet.BS_ONCMP_S_ONAA, MULTICURVE_FFS, USD, -507972.8394,
        "Basis swap ON Cmp + spread v ON AA: present value - FF swap based curves");
  }
  
  @Test
  /**Tests present value for an IRS with stub - fixed leg. Swap Fixed vs Libor3M - Stub 3M. */
  public void presentValueStub1() {
    presentValueTest(SwapInstrumentsDataSet.IRS_STUB1, MULTICURVE_FFS, USD, -181665.9361,
        "IRS with STUB: present value - FF swap based curves");
  }  
  
  @Test
  /**Tests present value for an IRS with stub - ibor leg / same index.
   * IRS Fixed vs Libor3M - Stub 1M: Accrual period is 1M / fixing rate is based on 3M*/
  public void presentValueStub2() {
    E2EUtils.presentValueTest(SwapInstrumentsDataSet.IRS_STUB2, MULTICURVE_FFS, USD, -262948.9316,
        "IRS with STUB: present value - FF swap based curves");
  }  
  
  @Test
  /**Tests present value for an IRS with stub - ibor leg / interpolated index.
   * IRS Fixed vs Libor6M - Stub 3M: Accrual period is 3M / fixing rate is based on 3M */
  public void presentValueStub3() {
    presentValueTest(SwapInstrumentsDataSet.IRS_STUB3, MULTICURVE_FFS, USD, -318570.8721,
        "IRS with STUB: present value - FF swap based curves");
  }
  
  @Test
  /**Tests present value for an IRS with stub - ibor leg / interpolated index.
   * IRS Fixed vs Libor6M - Stub 4M: Accrual period is 4M / fixing rate average 3M and 6M */
  public void presentValueStub4() {
    presentValueTest(SwapInstrumentsDataSet.IRS_STUB4, MULTICURVE_FFS, USD, -406168.2802,
        "IRS with STUB: present value - FF swap based curves");
  }

  @Test
  /**Tests present value for an IRS with stub - ibor leg / interpolated index  - long start.
   * IRS Fixed vs Libor3M - Stub Long Start 6M: Accrual period is 5M30D / fixing rate 6M*/
  public void presentValueStub5() {
    presentValueTest(SwapInstrumentsDataSet.IRS_STUB5, MULTICURVE_FFS, USD, -5492080.770903496,
        "IRS with STUB: present value - FF swap based curves");
  }
  
  @Test
  /**Tests present value for an IRS with stub - ibor leg / interpolated index  - short end.
   * IRS Fixed vs Libor3M - Short end Stub 2M: Accrual period is 2M / fixing rate average 1M and 3M*/
  public void presentValueStub6() {
    presentValueTest(SwapInstrumentsDataSet.IRS_STUB6, MULTICURVE_FFS, USD, -6372357.25938979,
        "IRS with STUB: present value - FF swap based curves");
  }

  @Test
  /** Tests present value for zero coupon swap, fixed vs Libor3M. */
  public void presentValueZC() {
    presentValueTest(SwapInstrumentsDataSet.IRS_ZERO_CPN, MULTICURVE_OIS, USD, 7850279.042216873,
        "Zero Coupon Swap Fixed v Libor3M: present value from standard curves");
    presentValueTest(SwapInstrumentsDataSet.IRS_ZERO_CPN, MULTICURVE_FFS, USD, 6606079.5763,
        "Zero Coupon Swap Fixed v Libor3M: present value - Fed Fund swap based curves");
  }

  @Test
  /** Tests present value for amortizing swap. */
  public void presentValueAmortixing() {
    presentValueTest(SwapInstrumentsDataSet.SWAP_AMORTIZING, MULTICURVE_OIS, USD, -1850080.2895532502,
        "Amortizing Swap Fixed v Libor6M: present value from standard curves");
    presentValueTest(SwapInstrumentsDataSet.SWAP_AMORTIZING, MULTICURVE_FFS, USD, -1963279.9026614893,
        "Amortizing Swap Fixed v Libor6M: present value - Fed Fund swap based curves");
  }
  
  /**
   * Test the parrate versus a hard-coded number.
   * @param ins The instrument to test.
   * @param multicurve The multi-curve provider.
   * @param ccy The currency of the expected PV.
   * @param expectedPv The expected PV amount.
   * @param msg The assert message.
   */
  private void parRateTest(InstrumentDerivative ins, MulticurveProviderDiscount multicurve, double prExpected, 
      String msg) {
    double prComputed = ins.accept(PRDC, multicurve);
    assertEquals(msg, prExpected, prComputed, TOLERANCE_RATE);
  }

  @Test
  /** Tests forward rate for a swap fixed vs Fed Fund compounded. */
  public void parRateONCmp() {
    parRateTest(SwapInstrumentsDataSet.SWAP_FIXED_ON, MULTICURVE_OIS, 6.560723881400023E-4, 
        "Swap Fixed v ON compounded: par rate from standard curves");
  }

  @Test
  /** Test forward rate  for a swap fixed vs LIBOR3M. Curves with OIS and curves with Fed Fund swaps. */
  public void parRate3M() {
    parRateTest(SwapInstrumentsDataSet.SWAP_FIXED_3M, MULTICURVE_OIS, 0.025894715668195054, 
        "IRS Fixed v LIBOR3M: par rate");
    parRateTest(SwapInstrumentsDataSet.SWAP_FIXED_3M, MULTICURVE_FFS, 0.02427361360337821, 
        "IRS Fixed v LIBOR3M: par rate");
  }

  @Test
  /** Test forward rate for a swap fixed vs LIBOR1M. */
  public void parRate1M() {
    parRateTest(SwapInstrumentsDataSet.SWAP_FIXED_1M, MULTICURVE_OIS, 0.007452504182638092,
        "IRS Fixed v LIBOR1M: par rate from standard curves");
  }

  @Test
  /** Test forward rate for a swap fixed vs LIBOR3M - stub. */
  public void parRateStub1() {
    parRateTest(SwapInstrumentsDataSet.IRS_STUB1, MULTICURVE_FFS, 0.01104570764,
        "IRS Fixed v LIBOR3M - stub: par rate");
  }

  @Test
  /** Tests par spread for ON Arithmetic Average (+ spread) vs Libor3M swaps. */
  public void parSpreadMarketQuoteONAA3M() {
    final double parSpread = SwapInstrumentsDataSet.SWAP_FF_3M_0.accept(PSMQDC, MULTICURVE_OIS);
    final double parSpreadExpected = 0.0027756235; // 0.0027741318;
    assertEquals("Swap ON Arithmetic Average: par spread", parSpreadExpected, parSpread, TOLERANCE_RATE);
  }

  @Test
  /** Tests par spread for Fixed vs Libor3M swaps. */
  public void parSpreadMarketQuote3M() {
    final double parSpread = SwapInstrumentsDataSet.SWAP_FIXED_3M.accept(PSMQDC, MULTICURVE_OIS);
    final double parSpreadExpected = 0.01089471566819499;
    assertEquals("Fixed vs Libor3M swaps: par spread", parSpreadExpected, parSpread, TOLERANCE_RATE);
  }

  @Test
  /** Tests par spread for swap fixed vs Fed Fund compounded. */
  public void parSpreadMarketQuoteON() {
    final double parSpread = SwapInstrumentsDataSet.SWAP_FIXED_ON.accept(PSMQDC, MULTICURVE_OIS);
    final double parSpreadExpected = -5.739276118599975E-4;
    assertEquals("Fixed vs Libor3M swaps: par spread", parSpreadExpected, parSpread, TOLERANCE_RATE);
  }

  @Test
  /** Tests Bucketed PV01 of ON Arithmetic Average (+ spread) vs Libor3M swaps. */
  public void BucketedPV01ONAA3M() {
    final double[] deltaDsc = {-0.5362, -0.5362, 1.5056, -20.3864, 156.4589, 
      -3141.2267, -3355.0095, -13.7148, 44.0214, 62.8386, 
      45.9853, -102.8615, 23497.7198, 40079.2957, 0.0000, 
      0.0000, 0.0000};
    final double[] deltaFwd3 = {2605.0956, 2632.0962, 1176.1245, -27.1136 , 33.6303, 
      6.7954, 7.4652, -11436.3319, -52207.1283, 0.00000,
      0.00000, 0.00000, 0.00000, 0.00000, 0.00000 };
    final LinkedHashMap<Pair<String, Currency>, DoubleMatrix1D> sensitivity = new LinkedHashMap<>();
    sensitivity.put(ObjectsPair.of(MULTICURVE_OIS.getName(USD), USD), new DoubleMatrix1D(deltaDsc));
    sensitivity.put(ObjectsPair.of(MULTICURVE_OIS.getName(USDLIBOR3M), USD), new DoubleMatrix1D(deltaFwd3));
    final MultipleCurrencyParameterSensitivity pvpsExpected = new MultipleCurrencyParameterSensitivity(sensitivity);
    final MultipleCurrencyParameterSensitivity pvpsComputed = MQSBC.fromInstrument(SwapInstrumentsDataSet.SWAP_FF_3M, MULTICURVE_OIS, BLOCK_OIS).multipliedBy(BP1);
    AssertSensitivityObjects.assertEquals("Swap ON Arithmetic Average: bucketed deltas - standard curves", 
        pvpsExpected, pvpsComputed, TOLERANCE_PV_DELTA);
    final double[] deltaDsc2 = 
      {-66.2969,438.3827,-1360.2365,1802.0762,-3859.4593,-3805.4806,427.5314,-98.2780,449.5943,-1657.4869,
      6316.1068,-26682.6988,-49579.8748,8890.0988,-2332.2439,504.8800,-49.6672,7.2156,-1.0967,0.2881,-0.0500};
    final double[] deltaFwd32 = {2567.3874,3052.3766,1404.7849,-638.5246,42.4844,
      6.9486,-21.9347,-28.1631,-22.6375,7.6708,-2.9427,0.4286,-0.0671,0.0177,-0.0031 };
    final LinkedHashMap<Pair<String, Currency>, DoubleMatrix1D> sensitivity2 = new LinkedHashMap<>();
    sensitivity2.put(ObjectsPair.of(MULTICURVE_OIS.getName(USD), USD), new DoubleMatrix1D(deltaDsc2));
    sensitivity2.put(ObjectsPair.of(MULTICURVE_OIS.getName(USDLIBOR3M), USD), new DoubleMatrix1D(deltaFwd32));
    final MultipleCurrencyParameterSensitivity pvpsExpected2 = new MultipleCurrencyParameterSensitivity(sensitivity2);
    final MultipleCurrencyParameterSensitivity pvpsComputed2 = MQSBC.fromInstrument(SwapInstrumentsDataSet.SWAP_FF_3M, MULTICURVE_FFS, BLOCK_FFS).multipliedBy(BP1);
    AssertSensitivityObjects.assertEquals("Swap ON Arithmetic Average: bucketed deltas - fed fund based curve", 
        pvpsExpected2, pvpsComputed2, TOLERANCE_PV_DELTA);
  }

  @Test
  /** Test Bucketed PV01 for a swap fixed vs LIBOR3M. Curves with OIS and curves with Fed Fund swaps. */
  public void BucketedPV01IRS3M() {
    final double[] deltaDsc = 
      {-2.0061282888005487, -2.0061296819291816, -8.67452075363044E-5, 0.0011745459201512494, 1.4847039752079148, 
      -56.9491079838621, 1.1272953888594144, -86.07354102781184, -166.96224129263487, -242.22201138850485, 
      -314.19406010048203, -385.9029177491706, -463.2762183477875, -979.7315575792289, -243.35533439972858, 
      243.5314114568193, 139.99052652789604 };
    final double[] deltaFwd3 = 
      {-2604.935862485693, -2632.099517240374, -1176.1264079094185, 27.132459446981603, -34.136228550265635, 
      -8.299063015802915, -10.516911338517652, 0.5088197130590212, 56648.04062948109, 15520.134985155655, 
      0.00, 0.00, 0.00, 0.00, 0.00 };
    final LinkedHashMap<Pair<String, Currency>, DoubleMatrix1D> sensitivity = new LinkedHashMap<>();
    sensitivity.put(ObjectsPair.of(MULTICURVE_OIS.getName(USD), USD), new DoubleMatrix1D(deltaDsc));
    sensitivity.put(ObjectsPair.of(MULTICURVE_OIS.getName(USDLIBOR3M), USD), new DoubleMatrix1D(deltaFwd3));
    final MultipleCurrencyParameterSensitivity pvpsExpected = new MultipleCurrencyParameterSensitivity(sensitivity);
    final MultipleCurrencyParameterSensitivity pvpsComputed = 
        MQSBC.fromInstrument(SwapInstrumentsDataSet.SWAP_FIXED_3M, MULTICURVE_OIS, BLOCK_OIS).multipliedBy(BP1);
    AssertSensitivityObjects.assertEquals("IRS Fixed v LIBOR3M: bucketed deltas", 
        pvpsExpected, pvpsComputed, TOLERANCE_PV_DELTA);
    final double[] deltaDsc2 = 
      {-3.2539,-0.1146,-0.1009,1.6777,-51.6721,
      6.0288,-73.3598,141.7012,182.5853,320.0508,
      360.7834,157.2649,844.6966,226.4134,-234.8655,
      57.1481,28.5688,-9.6478,2.3747,-0.7611,
      0.1493 };
    final double[] deltaFwd32 = 
      {-2503.3045,-2998.9427,-1316.8739,293.6294,523.1624,
      -2822.7769,9566.1654,-20157.7758,70743.1874,17070.5725,
      -5800.7375,848.0514,-129.9927,34.2840,-5.9645 };
    final LinkedHashMap<Pair<String, Currency>, DoubleMatrix1D> sensitivity2 = new LinkedHashMap<>();
    sensitivity2.put(ObjectsPair.of(MULTICURVE_OIS.getName(USD), USD), new DoubleMatrix1D(deltaDsc2));
    sensitivity2.put(ObjectsPair.of(MULTICURVE_OIS.getName(USDLIBOR3M), USD), new DoubleMatrix1D(deltaFwd32));
    final MultipleCurrencyParameterSensitivity pvpsExpected2 = new MultipleCurrencyParameterSensitivity(sensitivity2);
    final MultipleCurrencyParameterSensitivity pvpsComputed2 = 
        MQSBC.fromInstrument(SwapInstrumentsDataSet.SWAP_FIXED_3M, MULTICURVE_FFS, BLOCK_FFS).multipliedBy(BP1);
    AssertSensitivityObjects.assertEquals("IRS Fixed v LIBOR3M: bucketed deltas", 
        pvpsExpected2, pvpsComputed2, TOLERANCE_PV_DELTA);
  }

  @Test
  /** Test Bucketed PV01 for a swap LIBOR3M + Spread V LIBOR6M. */
  public void BucketedPV01BS3M6M() {
    final double[] deltaDsc =
    {0.0075, 0.0075, -0.0001, 0.0016, -0.0103, 0.7778, -5.5386, -2.8894, -21.9283, -37.3247,
      -41.4967, -23.7696, -43.2694, 12.6994, -31.2223, -49.6524, 598.2788 };
    final double[] deltaFwd3 =
    {-2591.5372, -2619.3693, -812.8589, 43.9557, -11.0705, 44.8080, 40.4064, -33.2664, 193.6617, -466.1282,
      -186.7083, -26.0371, 0.0000, 0.0000, 0.0000 }; // Swap 6M curve as spread to 3M.
    final double[] deltaFwd6 =
    {4442.1046, 1584.1646, -44.5508, 51.2340, 13.8415, 4.0097, -6.7514, 66.4129, -68496.6308, -28851.4300,
      0.0000, 0.0000, 0.0000, 0.0000 };
    final LinkedHashMap<Pair<String, Currency>, DoubleMatrix1D> sensitivity = new LinkedHashMap<>();
    sensitivity.put(ObjectsPair.of(MULTICURVE_OIS.getName(USD), USD), new DoubleMatrix1D(deltaDsc));
    sensitivity.put(ObjectsPair.of(MULTICURVE_OIS.getName(USDLIBOR3M), USD), new DoubleMatrix1D(deltaFwd3));
    sensitivity.put(ObjectsPair.of(MULTICURVE_OIS.getName(USDLIBOR6M), USD), new DoubleMatrix1D(deltaFwd6));
    final MultipleCurrencyParameterSensitivity pvpsExpected = new MultipleCurrencyParameterSensitivity(sensitivity);
    final MultipleCurrencyParameterSensitivity pvpsComputed =
        MQSBC.fromInstrument(SwapInstrumentsDataSet.BS_3M_S_6M, MULTICURVE_OIS, BLOCK_OIS).multipliedBy(BP1);
    AssertSensitivityObjects.assertEquals("Basis swap L3M v L6M: bucketed deltas",
        pvpsExpected, pvpsComputed, TOLERANCE_PV_DELTA);
  }

  @Test
  /** Tests Bucketed PV01 for a swap fixed vs LIBOR1M. */
  public void BucketedPV011M() {
    final double[] deltaDsc = 
      {0.3008,0.3008,0.0000,0.0002,1.5114,-13.5657,0.0846,45.9662,99.7453,104.8512,
      0.0000,0.0000,0.0000,0.0000,0.0000,0.0000,0.0000 };
    final double[] deltaFwd1 = 
      {-0.1666,2864.6265,3569.5692,33.0663,-9894.4160,-16771.9988,0.0000,0.0000,0.0000,0.0000,
      0.0000,0.0000,0.0000,0.0000,0.0000 };
    final double[] deltaFwd3 = 
      {-2601.2036,-2629.5677,-1202.4749,-32.6058,9752.5245,16503.8143,0.0000,0.0000,0.0000,0.0000,
      0.0000,0.0000,0.0000,0.0000,0.0000 };
    final LinkedHashMap<Pair<String, Currency>, DoubleMatrix1D> sensitivity = new LinkedHashMap<>();
    sensitivity.put(ObjectsPair.of(MULTICURVE_OIS.getName(USD), USD), new DoubleMatrix1D(deltaDsc));
    sensitivity.put(ObjectsPair.of(MULTICURVE_OIS.getName(USDLIBOR1M), USD), new DoubleMatrix1D(deltaFwd1));
    sensitivity.put(ObjectsPair.of(MULTICURVE_OIS.getName(USDLIBOR3M), USD), new DoubleMatrix1D(deltaFwd3));
    final MultipleCurrencyParameterSensitivity pvpsExpected = new MultipleCurrencyParameterSensitivity(sensitivity);
    final MultipleCurrencyParameterSensitivity pvpsComputed = MQSBC.fromInstrument(SwapInstrumentsDataSet.SWAP_FIXED_1M, MULTICURVE_OIS, BLOCK_OIS).multipliedBy(BP1);
    AssertSensitivityObjects.assertEquals("IRS Fixed v LIBOR1M: bucketed deltas", 
        pvpsExpected, pvpsComputed, TOLERANCE_PV_DELTA);
  }
  
  @Test
  /**Tests Bucketed PV01 for a ON Cmp + spread v ON AA. */
  public void bucketedPV01ONCmpONAA() {
    final double[] deltaDsc = 
      {0.4188,-0.9726,3.0556,-4.0519,7.8822,-14.7702,34.8675,-67.5616,-61.9951,-97.1549,
      114.4063,205.6523,-42.5120,11.4369,-3.1418,0.6878,-0.0408,0.0016,0.0005,-0.0002,0.0001};
    final double[] deltaFwd3 = 
      {0.5394,0.4496,0.7397,-2.9021,71.7050,42.5490,164.8341,-276.6407,-68.7754,12.8565,
      -4.5540,0.6658,-0.1021,0.0269,-0.0047};
    final LinkedHashMap<Pair<String, Currency>, DoubleMatrix1D> sensitivity = new LinkedHashMap<>();
    sensitivity.put(ObjectsPair.of(MULTICURVE_OIS.getName(USD), USD), new DoubleMatrix1D(deltaDsc));
    sensitivity.put(ObjectsPair.of(MULTICURVE_OIS.getName(USDLIBOR3M), USD), new DoubleMatrix1D(deltaFwd3));
    final MultipleCurrencyParameterSensitivity pvpsExpected = new MultipleCurrencyParameterSensitivity(sensitivity);
    final MultipleCurrencyParameterSensitivity pvpsComputed = 
        MQSBC.fromInstrument(SwapInstrumentsDataSet.BS_ONCMP_S_ONAA, MULTICURVE_FFS, BLOCK_FFS).multipliedBy(BP1);
    AssertSensitivityObjects.assertEquals("Basis swap ON Cmp + spread v ON AA: bucketed deltas", 
        pvpsExpected, pvpsComputed, TOLERANCE_PV_DELTA);
  }

  @Test
  /** Tests Bucketed PV01 for zero coupon swap, fixed vs Libor3M. */
  public void bucketedPV01IRSZC() {
    double[] deltaDsc = {
      -3.9356,2.5219,-8.3208,12.5841,-73.2170,67.2551,-188.1475,163.1403,-48.8872,-230.7762,
      -269.1857,-1500.7366,1812.8021,4284.8834,-955.1795,213.7552,16.7521,-8.5082,2.2968,-0.7551,0.1501 };
    double[] deltaFwd3 = {
      -2529.4844,-3035.1020,-1319.5224,204.5212,335.8798,-3568.8244,10300.2451,-23472.8335,76761.5176,18862.4059,
      -6410.6009,937.2112,-143.6599,37.8885,-6.5916};
    final LinkedHashMap<Pair<String, Currency>, DoubleMatrix1D> sensitivity = new LinkedHashMap<>();
    sensitivity.put(ObjectsPair.of(MULTICURVE_OIS.getName(USD), USD), new DoubleMatrix1D(deltaDsc));
    sensitivity.put(ObjectsPair.of(MULTICURVE_OIS.getName(USDLIBOR3M), USD), new DoubleMatrix1D(deltaFwd3));
    final MultipleCurrencyParameterSensitivity pvpsExpected = new MultipleCurrencyParameterSensitivity(sensitivity);
    final MultipleCurrencyParameterSensitivity pvpsComputed =
        MQSBC.fromInstrument(SwapInstrumentsDataSet.IRS_ZERO_CPN, MULTICURVE_FFS, BLOCK_FFS).multipliedBy(BP1);
    AssertSensitivityObjects.assertEquals("Basis swap ON Cmp + spread v ON AA: bucketed deltas",
        pvpsExpected, pvpsComputed, TOLERANCE_PV_DELTA);
  }

  @Test
  /** Tests Bucketed PV01 for amortizing swap, fixed vs Libor3M. */
  public void bucketedPV01Amortizing() {
    double[] deltaDsc = new double[] {1.0332416814799603, 0.170788600990482, -0.38217968369121813, -1.1412686032582162,
        35.167186011164766, -6.883142789226561, 6.83591793828581, -20.632005010515808, -65.32600836825335,
        -94.92144971530911, -148.62374982131084, -92.89314084940884, -183.6594142172096, -75.09113335019632,
        46.332605676727816, -10.986479020159319, -4.643402051685771, 1.5949158610305179, -0.39447592719032504,
        0.1266122038928107, -0.024855263943305863 };
    double[] deltaFwd = new double[] {2687.6311396467036, 3150.9120736542027, 1573.1143397288454, -2009.7387593026897,
        -2271.3910100268918, -3620.257346784671, -4162.9405669380685, -7448.1825822574965, -24857.31225301184,
        -2877.7783840948405, 971.1245382795079, -141.97564697334874, 21.762592588193467, -5.739617449327134,
        0.9985350036032012 };
    final LinkedHashMap<Pair<String, Currency>, DoubleMatrix1D> sensitivity = new LinkedHashMap<>();
    sensitivity.put(ObjectsPair.of(MULTICURVE_FFS.getName(USD), USD), new DoubleMatrix1D(deltaDsc));
    sensitivity.put(ObjectsPair.of(MULTICURVE_FFS.getName(USDLIBOR3M), USD), new DoubleMatrix1D(deltaFwd));
    final MultipleCurrencyParameterSensitivity pvpsExpected = new MultipleCurrencyParameterSensitivity(sensitivity);
    final MultipleCurrencyParameterSensitivity pvpsComputed =
        MQSBC.fromInstrument(SwapInstrumentsDataSet.SWAP_AMORTIZING, MULTICURVE_FFS, BLOCK_FFS).multipliedBy(BP1);
    AssertSensitivityObjects.assertEquals("Amortizing swap: bucketed deltas",
        pvpsExpected, pvpsComputed, TOLERANCE_PV_DELTA);

  }
}
