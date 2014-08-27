/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.interestrate.capletstripping;

import static org.testng.AssertJUnit.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import cern.jet.random.engine.MersenneTwister;
import cern.jet.random.engine.MersenneTwister64;
import cern.jet.random.engine.RandomEngine;

import com.opengamma.analytics.financial.model.volatility.discrete.DiscreteVolatilityFunctionProvider;
import com.opengamma.analytics.financial.model.volatility.discrete.DiscreteVolatilityFunctionProviderDirect;
import com.opengamma.analytics.math.differentiation.VectorFieldFirstOrderDifferentiator;
import com.opengamma.analytics.math.function.Function1D;
import com.opengamma.analytics.math.matrix.DoubleMatrix1D;
import com.opengamma.analytics.math.matrix.DoubleMatrix2D;
import com.opengamma.analytics.math.matrix.MatrixAlgebra;
import com.opengamma.analytics.math.matrix.OGMatrixAlgebra;
import com.opengamma.analytics.math.statistics.leastsquare.NonLinearLeastSquareWithPenalty;
import com.opengamma.util.test.TestGroup;

/**
 * 
 */
@SuppressWarnings("deprecation")
public class CapletStrippingDirectTest extends CapletStrippingSetup {

  private static final VectorFieldFirstOrderDifferentiator DIFF = new VectorFieldFirstOrderDifferentiator();
  private static final RandomEngine RANDOM = new MersenneTwister64(MersenneTwister.DEFAULT_SEED);

  MatrixAlgebra MA = new OGMatrixAlgebra();

  /**
   * R White - This takes about 53s on my machine
   * It takes 32 iterations of {@link NonLinearLeastSquareWithPenalty} to converge
   */
  @Test(groups = TestGroup.UNIT_SLOW)
  public void priceTest() {
    double lambda = 0.03; // this is chosen to give a chi2/DoF of around 1

    MultiCapFloorPricerGrid pricer = new MultiCapFloorPricerGrid(getAllCapsExATM(), getYieldCurves());
    double[] capVols = getAllCapVolsExATM();
    double[] capPrices = pricer.price(capVols);
    double[] capVega = pricer.vega(capVols);
    int n = capVega.length;
    for (int i = 0; i < n; i++) {
      capVega[i] *= 1e-4; // this is approximately like having a 1bps error on volatility
    }

    CapletStripperDirect stripper = new CapletStripperDirect(pricer, lambda);

    DoubleMatrix1D guess = new DoubleMatrix1D(pricer.getNumCaplets(), 0.7);

    CapletStrippingResult res = stripper.solve(capPrices, MarketDataType.PRICE, capVega, guess);
    // System.out.println(res);
    assertEquals(106.90175236602136, res.getChiSq(), 1e-15);
  }

  /**
   * R White - this takes about 20s on my machine
   * it takes 11 iterations of {@link NonLinearLeastSquareWithPenalty} to converge
   */
  @Test(groups = TestGroup.UNIT_SLOW)
  public void volTest() {
    double lambda = 0.03; // this is chosen to give a chi2/DoF of around 1
    MultiCapFloorPricerGrid pricer = new MultiCapFloorPricerGrid(getAllCapsExATM(), getYieldCurves());
    CapletStripperDirect stripper = new CapletStripperDirect(pricer, lambda);

    double[] capVols = getAllCapVolsExATM();
    int n = capVols.length;
    double[] errors = new double[n];
    Arrays.fill(errors, 1e-4); // 1bps
    DoubleMatrix1D guess = new DoubleMatrix1D(pricer.getGridSize(), 0.7);

    CapletStrippingResult res = stripper.solve(capVols, MarketDataType.VOL, errors, guess);
    // System.out.println(res);
    assertEquals(106.90744994491888, res.getChiSq(), 1e-15);
  }

  @Test(groups = TestGroup.UNIT_SLOW)
  public void atmCapsVolTest() {
    double lambda = 0.7; // this is chosen to give a chi2/DoF of around 1
    MultiCapFloorPricerGrid pricer = new MultiCapFloorPricerGrid(getATMCaps(), getYieldCurves());
    CapletStripperDirect stripper = new CapletStripperDirect(pricer, lambda);

    double[] capVols = getATMCapVols();
    int n = capVols.length;
    double[] errors = new double[n];
    Arrays.fill(errors, 1e-4); // 1bps
    DoubleMatrix1D guess = new DoubleMatrix1D(pricer.getGridSize(), 0.7);

    CapletStrippingResult res = stripper.solve(capVols, MarketDataType.VOL, errors, guess);
    // System.out.println(res);
    assertEquals(5.760490240384456, res.getChiSq(), 1e-15);
  }

  @Test(groups = TestGroup.UNIT_SLOW)
  public void allCapsVolTest() {
    double lambdaT = 0.01; // this is chosen to give a chi2/DoF of around 1
    double lambdaK = 0.0002;
    List<CapFloor> allCaps = getAllCaps();
    List<CapFloor> atmCaps = getATMCaps();
    MultiCapFloorPricerGrid pricer = new MultiCapFloorPricerGrid(allCaps, getYieldCurves());
    CapletStripperDirect stripper = new CapletStripperDirect(pricer, lambdaK, lambdaT);

    double[] capVols = getAllCapVols();
    int n = capVols.length;
    double[] errors = new double[n];
    Arrays.fill(errors, 1e-3); // 10bps

    int nATM = atmCaps.size();
    for (int i = 0; i < nATM; i++) {
      int index = allCaps.indexOf(atmCaps.get(i));
      errors[index] = 1e-4; // 1bps
    }

    DoubleMatrix1D guess = new DoubleMatrix1D(pricer.getGridSize(), 0.7);

    CapletStrippingResult res = stripper.solve(capVols, MarketDataType.VOL, errors, guess);
    // System.out.println(res);
    assertEquals(131.50826652583146, res.getChiSq(), 1e-15);

  }

  /**
   * We solve strike-by-strike (this takes 3-4 iterations), then concatenate the results as a starting
   * guess of a global fit; this doesn't make much different - converge in 9 rather than 11 iterations,
   * to a slightly different point from above.
   */
  @Test(groups = TestGroup.UNIT_SLOW)
  public void singleStrikeTest() {
    double lambda = 0.03;
    DoubleMatrix1D guess = null;
    int nStrikes = getNumberOfStrikes();
    CapletStrippingResult[] singleStrikeResults = new CapletStrippingResult[nStrikes];
    for (int i = 0; i < nStrikes; i++) {
      MultiCapFloorPricerGrid pricer = new MultiCapFloorPricerGrid(getCaps(i), getYieldCurves());
      CapletStripperDirect stripper = new CapletStripperDirect(pricer, lambda);

      double[] capVols = getCapVols(i);
      int n = capVols.length;
      double[] errors = new double[n];
      Arrays.fill(errors, 1e-4); // 1bps
      if (i == 0) {
        guess = new DoubleMatrix1D(pricer.getNumCaplets(), 0.7);
      }

      CapletStrippingResult res = stripper.solve(capVols, MarketDataType.VOL, errors, guess);
      singleStrikeResults[i] = res;
      guess = res.getFitParameters();
      // System.out.println(res);
    }

    MultiCapFloorPricerGrid pricer = new MultiCapFloorPricerGrid(getAllCapsExATM(), getYieldCurves());
    CapletStripperDirect stripper = new CapletStripperDirect(pricer, lambda);

    double[] capVols = getAllCapVolsExATM();
    int n = capVols.length;
    double[] errors = new double[n];
    Arrays.fill(errors, 1e-4); // 1bps

    double[] data = new double[pricer.getNumCaplets()];
    int pos = 0;
    for (int i = 0; i < nStrikes; i++) {
      double[] ssData = singleStrikeResults[i].getFitParameters().getData();
      int m = ssData.length;
      System.arraycopy(singleStrikeResults[i].getFitParameters().getData(), 0, data, pos, m);
      pos += m;
    }
    guess = new DoubleMatrix1D(data);
    // System.out.println(guess);
    CapletStrippingResult res = stripper.solve(capVols, MarketDataType.VOL, errors, guess);
    // System.out.println(res);
    assertEquals(106.90677987346953, res.getChiSq(), 1e-15);
  }

  @Test
  public void functionsTest() {

    MultiCapFloorPricer pricer = new MultiCapFloorPricer(getAllCapsExATM(), getYieldCurves());
    int size = pricer.getNumCaplets();
    DoubleMatrix1D flat = new DoubleMatrix1D(size, 0.4);

    DiscreteVolatilityFunctionProvider volPro = new DiscreteVolatilityFunctionProviderDirect(size);
    CapletStrippingImp imp = new CapletStrippingImp(pricer, volPro);

    Function1D<DoubleMatrix1D, DoubleMatrix1D> pFunc = imp.getCapPriceFunction();
    Function1D<DoubleMatrix1D, DoubleMatrix2D> pJacFun = imp.getCapPriceJacobianFunction();
    Function1D<DoubleMatrix1D, DoubleMatrix2D> pJacFunFD = DIFF.differentiate(pFunc);
    compareJacobianFunc(pJacFun, pJacFunFD, flat, 1e-11);

    Function1D<DoubleMatrix1D, DoubleMatrix1D> vFunc = imp.getCapVolFunction();
    Function1D<DoubleMatrix1D, DoubleMatrix2D> vJacFun = imp.getCapVolJacobianFunction();
    Function1D<DoubleMatrix1D, DoubleMatrix2D> vJacFunFD = DIFF.differentiate(vFunc);
    compareJacobianFunc(vJacFun, vJacFunFD, flat, 1e-6);

    // random entries
    // The FD calculation of the Jacobian takes a long time
    DoubleMatrix1D x = new DoubleMatrix1D(size, 0.0);
    int nRuns = 2;
    for (int run = 0; run < nRuns; run++) {
      for (int i = 0; i < size; i++) {
        x.getData()[i] = 0.2 + 0.7 * RANDOM.nextDouble();
      }
      compareJacobianFunc(pJacFun, pJacFunFD, x, 1e-11);
      compareJacobianFunc(vJacFun, vJacFunFD, x, 1e-6);
    }

    // test against CapletStrippingDirectGlobalWithPenalty

    double[] capStrikes = pricer.getStrikes();
    double[] capletExps = pricer.getCapletExpiries();
    int nStrikes = capStrikes.length;

    @SuppressWarnings("unchecked")
    List<CapFloor>[] caps = new List[nStrikes];
    double[][] capVols = new double[nStrikes][];
    for (int i = 0; i < nStrikes; ++i) {
      caps[i] = getCaps(i);
      capVols[i] = getCapVols(i);
    }

    CapletVolatilityNodalSurfaceProvider provider = new CapletVolatilityNodalSurfaceProvider(capStrikes, capletExps);
    CapletStrippingDirectGlobalWithPenalty cpst = new CapletStrippingDirectGlobalWithPenalty(caps, getYieldCurves(),
        provider);
    Function1D<DoubleMatrix1D, DoubleMatrix1D> vFuncOld = cpst.getCapVolFunc;
    Function1D<DoubleMatrix1D, DoubleMatrix2D> vJacFuncOld = cpst.getCapVolJacFunc;

    compareFunc(vFunc, vFuncOld, flat, 1e-10);
    compareJacobianFunc(vJacFun, vJacFuncOld, flat, 1e-10);
    nRuns = 50;
    for (int run = 0; run < nRuns; run++) {
      for (int i = 0; i < size; i++) {
        x.getData()[i] = 0.2 + 0.7 * RANDOM.nextDouble();
      }
      compareFunc(vFunc, vFuncOld, x, 1e-10);
      compareJacobianFunc(vJacFun, vJacFuncOld, x, 1e-10);
    }
  }

  @Test
  public void functionsTest2() {

    MultiCapFloorPricerGrid pricer = new MultiCapFloorPricerGrid(getAllCaps(), getYieldCurves());
    int size = pricer.getGridSize();
    DoubleMatrix1D flat = new DoubleMatrix1D(size, 0.4);

    DiscreteVolatilityFunctionProvider volPro = new DiscreteVolatilityFunctionProviderDirect(size);
    CapletStrippingImp imp = new CapletStrippingImp(pricer, volPro);

    Function1D<DoubleMatrix1D, DoubleMatrix1D> pFunc = imp.getCapPriceFunction();
    Function1D<DoubleMatrix1D, DoubleMatrix2D> pJacFun = imp.getCapPriceJacobianFunction();
    Function1D<DoubleMatrix1D, DoubleMatrix2D> pJacFunFD = DIFF.differentiate(pFunc);
    // System.out.println(pJacFun.evaluate(flat));
    // System.out.println(pJacFunFD.evaluate(flat));
    compareJacobianFunc(pJacFun, pJacFunFD, flat, 1e-11);

    Function1D<DoubleMatrix1D, DoubleMatrix1D> vFunc = imp.getCapVolFunction();
    // System.out.println(vFunc.evaluate(flat));

    Function1D<DoubleMatrix1D, DoubleMatrix2D> vJacFun = imp.getCapVolJacobianFunction();
    Function1D<DoubleMatrix1D, DoubleMatrix2D> vJacFunFD = DIFF.differentiate(vFunc);
    // System.out.println(vJacFun.evaluate(flat));
    // System.out.println(vJacFunFD.evaluate(flat));
    compareJacobianFunc(vJacFun, vJacFunFD, flat, 1e-6);

    // random entries
    // The FD calculation of the Jacobian takes a long time
    DoubleMatrix1D x = new DoubleMatrix1D(size, 0.0);
    int nRuns = 2;
    for (int run = 0; run < nRuns; run++) {
      for (int i = 0; i < size; i++) {
        x.getData()[i] = 0.2 + 0.7 * RANDOM.nextDouble();
      }
      compareJacobianFunc(pJacFun, pJacFunFD, x, 1e-11);
      compareJacobianFunc(vJacFun, vJacFunFD, x, 1e-4);
      // System.out.println(vJacFun.evaluate(x));
      // System.out.println(vJacFunFD.evaluate(x));
    }
  }

  @Test
  public void timingTest() {
    MultiCapFloorPricerGrid pricer = new MultiCapFloorPricerGrid(getAllCapsExATM(), getYieldCurves());
    int size = pricer.getNumCaplets();
    DiscreteVolatilityFunctionProvider volPro = new DiscreteVolatilityFunctionProviderDirect(size);
    CapletStrippingImp imp = new CapletStrippingImp(pricer, volPro);

    Function1D<DoubleMatrix1D, DoubleMatrix2D> vJacFun = imp.getCapVolJacobianFunction();
    double[] capStrikes = getStrikes();
    int nStrikes = capStrikes.length;

    @SuppressWarnings("unchecked")
    List<CapFloor>[] caps = new List[nStrikes];
    double[][] capVols = new double[nStrikes][];

    for (int i = 0; i < nStrikes; ++i) {
      caps[i] = getCaps(i);
      capVols[i] = getCapVols(i);
    }
    CapletVolatilityNodalSurfaceProvider provider = new CapletVolatilityNodalSurfaceProvider(capStrikes,
        pricer.getCapletExpiries());
    CapletStrippingDirectGlobalWithPenalty cpst = new CapletStrippingDirectGlobalWithPenalty(caps, getYieldCurves(),
        provider);

    Function1D<DoubleMatrix1D, DoubleMatrix2D> vJacFuncOld = cpst.getCapVolJacFunc;

    int warmup = 5;
    int hotspot = 30;

    // double t1 = funcTiming(size, vFunc, warmup, hotspot);
    // double t2 = funcTiming(size, vFuncOld, warmup, hotspot);
    double t1 = jacTiming(size, vJacFun, warmup, hotspot);
    double t2 = jacTiming(size, vJacFuncOld, warmup, hotspot);
    System.out.println(t1 + "s\t" + t2 + "s");
  }

  @SuppressWarnings("unused")
  private double funcTiming(int nParms, Function1D<DoubleMatrix1D, DoubleMatrix1D> func, int warmup, int hotspot) {

    for (int i = 0; i < warmup; i++) {
      genFunc(nParms, func);
    }

    long tStart = System.nanoTime();
    for (int i = 0; i < hotspot; i++) {
      genFunc(nParms, func);
    }
    long tEnd = System.nanoTime();
    return (1e-9 * (tEnd - tStart)) / hotspot;
  }

  private double genFunc(int nParms, Function1D<DoubleMatrix1D, DoubleMatrix1D> func) {
    DoubleMatrix1D x = new DoubleMatrix1D(new double[nParms]);
    double[] data = x.getData();

    for (int i = 0; i < nParms; i++) {
      data[i] = 0.05 + RANDOM.nextDouble();
    }
    DoubleMatrix1D y = func.evaluate(x);
    return y.getEntry(0) * 2.0;
  }

  private double jacTiming(int nParms, Function1D<DoubleMatrix1D, DoubleMatrix2D> jacFunc, int warmup, int hotspot) {

    for (int i = 0; i < warmup; i++) {
      genjac(nParms, jacFunc);
    }

    long tStart = System.nanoTime();
    for (int i = 0; i < hotspot; i++) {
      genjac(nParms, jacFunc);
    }
    long tEnd = System.nanoTime();
    return (1e-9 * (tEnd - tStart)) / hotspot;
  }

  private double genjac(int nParms, Function1D<DoubleMatrix1D, DoubleMatrix2D> jacFunc) {
    DoubleMatrix1D x = new DoubleMatrix1D(new double[nParms]);
    double[] data = x.getData();

    for (int i = 0; i < nParms; i++) {
      data[i] = RANDOM.nextDouble();
    }
    DoubleMatrix2D jac = jacFunc.evaluate(x);
    return jac.getEntry(0, 0) * 2.0;
  }

}
