package com.opengamma.analytics.financial.interestrate.capletstripping;

import java.util.Arrays;
import java.util.BitSet;

import org.testng.annotations.Test;

import cern.jet.random.engine.MersenneTwister;
import cern.jet.random.engine.MersenneTwister64;
import cern.jet.random.engine.RandomEngine;

import com.opengamma.analytics.financial.model.volatility.discrete.DiscreteVolatilityFunction;
import com.opengamma.analytics.financial.model.volatility.discrete.DiscreteVolatilityFunctionProvider;
import com.opengamma.analytics.financial.model.volatility.discrete.DiscreteVolatilityFunctionProviderFromVolSurface;
import com.opengamma.analytics.financial.model.volatility.surface.BasisSplineVolatilitySurfaceProvider;
import com.opengamma.analytics.financial.model.volatility.surface.VolatilitySurface;
import com.opengamma.analytics.financial.model.volatility.surface.VolatilitySurfaceProvider;
import com.opengamma.analytics.math.function.Function1D;
import com.opengamma.analytics.math.interpolation.PenaltyMatrixGenerator;
import com.opengamma.analytics.math.linearalgebra.CholeskyDecompositionCommons;
import com.opengamma.analytics.math.matrix.DoubleMatrix1D;
import com.opengamma.analytics.math.matrix.DoubleMatrix2D;
import com.opengamma.analytics.math.matrix.MatrixAlgebra;
import com.opengamma.analytics.math.matrix.OGMatrixAlgebra;
import com.opengamma.analytics.math.minimization.NonLinearParameterTransforms;
import com.opengamma.analytics.math.minimization.NonLinearTransformFunction;
import com.opengamma.analytics.math.minimization.NullTransform;
import com.opengamma.analytics.math.minimization.ParameterLimitsTransform;
import com.opengamma.analytics.math.minimization.PositiveOrZero;
import com.opengamma.analytics.math.minimization.UncoupledParameterTransforms;
import com.opengamma.analytics.math.statistics.leastsquare.LeastSquareWithPenaltyResults;
import com.opengamma.analytics.math.statistics.leastsquare.NonLinearLeastSquareWithPenalty;
import com.opengamma.analytics.util.AssertMatrix;
import com.opengamma.util.test.TestGroup;
import com.opengamma.util.tuple.DoublesPair;

/**
 * This currently does not work
 */
@Test(groups = TestGroup.UNIT)
public class CapletStripperPSplineSurfaceTest extends CapletStrippingSetup {

  private static final RandomEngine RANDOM = new MersenneTwister64(MersenneTwister.DEFAULT_SEED);
  private static final MatrixAlgebra MA = new OGMatrixAlgebra();

  @SuppressWarnings("deprecation")
  @Test (enabled = false)
  public void test() {

    MultiCapFloorPricer pricer = new MultiCapFloorPricer(getAllCaps(), getYieldCurves());

    double lambda = 1;
    CapletStripper stripper = new CapletStripperPSplineSurface(pricer, 0.0, 0.12, 10, 2, 0.0, 10.0, 10, 2, lambda);

    int size = 11 * 11;
    DoubleMatrix1D guess = new DoubleMatrix1D(size, 0.5);

    double[] vols = getAllCapVols();
    int nVols = vols.length;
    double[] errors = new double[nVols];
    Arrays.fill(errors, 1e-4); // 1bps
    @SuppressWarnings("unused")
    CapletStrippingResult res = stripper.solve(vols, MarketDataType.VOL, errors, guess);
    System.out.println(res);
    // System.out.println(res.getChiSq());
    // assertEquals(expChi2[i], res.getChiSq(), 1e-13);
    // res.printSurface(System.out, 101, 101);
  }

  @Test
  public void directVolFitTest() {
    int tKnots = 10;
    int kKnots = 25;
    int tDegree = 2;
    int kDegree = 3;
    double kMin = 0.005;
    double kMax = 0.12;
    double tMax = 10.0;
    int tSize = tKnots + tDegree - 1;
    int kSize = kKnots + kDegree - 1;
    double tLambda = 1000;
    double kLambda = 100;

    VolatilitySurfaceProvider vsp = new BasisSplineVolatilitySurfaceProvider(0.0, kMax, kKnots, kDegree, 0.0, tMax, tKnots, tDegree);
    DiscreteVolatilityFunctionProvider dvfp = new DiscreteVolatilityFunctionProviderFromVolSurface(vsp);

    // pick some random surface points
    int nSamples = 20;
    DoublesPair[] tkPoints = new DoublesPair[nSamples];
    DoubleMatrix1D vols = new DoubleMatrix1D(nSamples);
    for (int i = 0; i < nSamples; i++) {
      double t = tMax * RANDOM.nextDouble();
      double k = kMin + (kMax - kMin) * RANDOM.nextDouble();
      tkPoints[i] = DoublesPair.of(t, k);
      vols.getData()[i] = 0.2 + 0.2 * RANDOM.nextDouble();
      // System.out.println(t + "\t" + k + "\t" + vols.getEntry(i));
    }

    DoubleMatrix1D sigma = new DoubleMatrix1D(nSamples, 1e-4); // 1bp error
    DoubleMatrix1D start = new DoubleMatrix1D(tSize * kSize, 0.3);

    final DiscreteVolatilityFunction dvf = dvfp.from(tkPoints);
    Function1D<DoubleMatrix1D, DoubleMatrix2D> jacFunc = new Function1D<DoubleMatrix1D, DoubleMatrix2D>() {
      @Override
      public DoubleMatrix2D evaluate(DoubleMatrix1D x) {
        return dvf.calculateJacobian(x);
      }
    };

    NonLinearLeastSquareWithPenalty nllwp = new NonLinearLeastSquareWithPenalty(new CholeskyDecompositionCommons());
    // DoubleMatrix2D penalty1 = PenaltyMatrixGenerator.getPenaltyMatrix(new int[] {kKnots, tKnots }, new int[] {1, 1 }, new double[] {kLambda, tLambda });
    DoubleMatrix2D penalty = PenaltyMatrixGenerator.getPenaltyMatrix(new int[] {kSize, tSize }, new int[] {2, 2 }, new double[] {kLambda, tLambda });
    // DoubleMatrix2D penalty = (DoubleMatrix2D) MA.add(penalty1, penalty2);
    // LeastSquareWithPenaltyResults res = nllwp.solve(vols, sigma, dvf, jacFunc, start, penalty /* ,new PositiveOrZero() */);
    // System.out.println(res.getChiSq() + "\t" + res.getPenalty());
    //
    // DoubleMatrix1D weights = res.getFitParameters();
    // System.out.println(weights);
    // VolatilitySurface surface = vsp.getVolSurface(weights);
    // PDEUtilityTools.printSurface("vol surface", surface.getSurface(), 0.0, tMax, kMin, kMax);

    MultiCapFloorPricer pricer = new MultiCapFloorPricer(getAllCapsExATM(), getYieldCurves());
    int nCaps = pricer.getNumCaps();

    BasisSplineVolatilitySurfaceProvider vtsp = new BasisSplineVolatilitySurfaceProvider(kMin, kMax, kKnots, kDegree, 0.0, tMax, tKnots, tDegree);
    DiscreteVolatilityFunctionProvider volFuncPro = new DiscreteVolatilityFunctionProviderFromVolSurface(vtsp);
    CapletStrippingCore imp = new CapletStrippingCore(pricer, volFuncPro);
    final Function1D<DoubleMatrix1D, DoubleMatrix1D> volFunc = imp.getCapVolFunction();
    final Function1D<DoubleMatrix1D, DoubleMatrix2D> volJacFunc = imp.getCapVolJacobianFunction();

    System.out.println(vtsp.getNumModelParameters());

    // final Function1D<DoubleMatrix1D, DoubleMatrix1D> square = new Function1D<DoubleMatrix1D, DoubleMatrix1D>() {
    // @Override
    // public DoubleMatrix1D evaluate(DoubleMatrix1D x) {
    // double[] data = x.getData();
    // int n = data.length;
    // DoubleMatrix1D w = new DoubleMatrix1D(n);
    // double[] wData = w.getData();
    // for (int i = 0; i < n; i++) {
    // wData[i] = data[i] * data[i];
    // }
    // return w;
    // }
    // };
    //
    //
    //
    //
    // Function1D<DoubleMatrix1D, DoubleMatrix1D> transVolFunc = new Function1D<DoubleMatrix1D, DoubleMatrix1D>() {
    // @Override
    // public DoubleMatrix1D evaluate(DoubleMatrix1D x) {
    // return volFunc.evaluate(square.evaluate(x));
    // }
    // };
    //
    // final Function1D<DoubleMatrix1D, DoubleMatrix2D> transVolJacFunc = new Function1D<DoubleMatrix1D, DoubleMatrix2D>() {
    //
    // @Override
    // public DoubleMatrix2D evaluate(DoubleMatrix1D x) {
    // double[] data = x.getData();
    // int m = data.length;
    // DoubleMatrix2D jac = volJacFunc.evaluate(square.evaluate(x));
    // double[][] jacData = jac.getData();
    // int n = jac.getNumberOfRows();
    // for (int i = 0; i < n; i++) {
    // for (int j = 0; j < m; j++) {
    // double twoAlpha = 2 * data[j];
    // jacData[i][j] *= twoAlpha;
    // }
    // }
    // return jac;
    // }
    // };

    ParameterLimitsTransform positive = new NullTransform(); // SingleRangeLimitTransform(0.0, LimitType.GREATER_THAN);
    ParameterLimitsTransform[] temp = new ParameterLimitsTransform[(tSize * kSize)];
    Arrays.fill(temp, positive);
    NonLinearParameterTransforms transforms = new UncoupledParameterTransforms(start, temp, new BitSet());
    NonLinearTransformFunction transFuncGen = new NonLinearTransformFunction(volFunc, volJacFunc, transforms);
    start = transforms.transform(start);

    // VectorFieldFirstOrderDifferentiator diff = new VectorFieldFirstOrderDifferentiator();
    // Function1D<DoubleMatrix1D, DoubleMatrix2D> fdJacFunc = diff.differentiate(transVolFunc);
    // DoubleMatrix2D jac = transVolJacFunc.evaluate(start);
    // DoubleMatrix2D fdJac = fdJacFunc.evaluate(start);
    // AssertMatrix.assertEqualsMatrix(fdJac, jac, 1e-6);

    LeastSquareWithPenaltyResults res = nllwp.solve(new DoubleMatrix1D(getAllCapVolsExATM()), new DoubleMatrix1D(nCaps, 1e-4), transFuncGen.getFittingFunction(), transFuncGen.getFittingJacobian(),
        start, penalty);
    System.out.println("chi2: " + res.getChiSq() + "\t penalty: " + res.getPenalty());

    double[] error = new double[pricer.getNumCaps()];
    Arrays.fill(error, 1e-4);
    //
    CapletStripper stripper = new CapletStripperPSplineSurface(pricer, kMin, kMax, kKnots, kDegree, 0.0, tMax, tKnots, tDegree, 1000);
    CapletStrippingResult sRes = stripper.solve(getAllCapVolsExATM(), MarketDataType.VOL, error, start);
    //
    System.out.println(sRes);

    //    VolatilitySurface vs = vsp.getVolSurface(transforms.inverseTransform(res.getFitParameters()));
    //    PDEUtilityTools.printSurface("vol surface", vs.getSurface(), 0.0, tMax, kMin, kMax);
  }

  @Test
  // (enabled = false)
  public void debugTest() {
    MultiCapFloorPricer pricer = new MultiCapFloorPricer(getAllCaps(), getYieldCurves());

    int tSize = 3;
    int kSize = 3;
    int tDegree = 1;
    int kDegree = 1;
    double kMax = 0.12;
    double tMax = 10.0;
    int tKnots = tSize + tDegree - 1;
    int kKnots = kSize + kDegree - 1;
    double tLambda = 100;
    double kLambda = 100;

    VolatilitySurfaceProvider vsp = new BasisSplineVolatilitySurfaceProvider(0.0, kMax, kSize, kDegree, 0.0, tMax, tSize, tDegree);
    int n = vsp.getNumModelParameters();
    DoubleMatrix1D x = new DoubleMatrix1D(n);
    for (int i = 0; i < kKnots; i++) {
      double k = i / (kKnots - 1.0);
      for (int j = 0; j < tKnots; j++) {
        double t = j / (tKnots - 1.0);
        double w = 0.7;// 0.2 + 0.4 * k + 0.7 * t + t * k + 0.1*t*t;
        x.getData()[i * tKnots + j] = w;
      }
      // x.getData()[i * tKnots + 0] = 1.0;
      // x.getData()[i * tKnots + 5] = 1.0;
      // x.getData()[i * tKnots + 8] = 1.0;
    }
    int nStrikePoints = 101;
    int nExpPoints = 101;
    double[] strikes = new double[nStrikePoints];
    double[] times = new double[nExpPoints];
    VolatilitySurface vs = vsp.getVolSurface(x);

    for (int i = 0; i < nStrikePoints; i++) {
      strikes[i] = -0.05 + 0.22 * i / (nStrikePoints - 1.0);
    }
    // System.out.println();
    // for (int j = 0; j < nExpPoints; j++) {
    // times[j] = -5 + 20.0 * j / (nExpPoints - 1.0);
    // System.out.print("\t" + times[j]);
    // }
    //
    // for (int i = 0; i < nStrikePoints; i++) {
    // System.out.print("\n" + strikes[i]);
    // for (int j = 0; j < nExpPoints; j++) {
    // Double vol = vs.getVolatility(times[j], strikes[i]);
    // System.out.print("\t" + vol);
    // }
    // }
    // System.out.println("\n");

    DiscreteVolatilityFunctionProvider dvfp = new DiscreteVolatilityFunctionProviderFromVolSurface(vsp);
    DiscreteVolatilityFunction func = dvfp.from(pricer.getExpiryStrikeArray());
    System.out.println(func.evaluate(x));
    DoubleMatrix2D jac = func.calculateJacobian(x);
    DoubleMatrix2D jacFD = func.calculateJacobianViaFD(x);
    AssertMatrix.assertEqualsMatrix(jac, jacFD, 1e-9);

    DoubleMatrix2D penalty = PenaltyMatrixGenerator.getPenaltyMatrix(new int[] {kKnots, tKnots }, new int[] {1, 1 }, new double[] {kLambda, tLambda });
    // double p = MA.getInnerProduct(x, MA.multiply(penalty, x));
    // System.out.println(penalty);
    // System.out.println(p);
    CapletStrippingCore imp = new CapletStrippingCore(pricer, dvfp);
    double[] vols = getAllCapVols();
    int nVols = vols.length;
    double[] errors = new double[nVols];
    Arrays.fill(errors, 1e-4); // 1bps
    CapletStrippingResult res = imp.solveForCapVols(vols, errors, x, penalty, new PositiveOrZero());
    System.out.println(res);

    DoubleMatrix1D xFit = res.getFitParameters();
    double p = MA.getInnerProduct(xFit, MA.multiply(penalty, xFit));
    System.out.println(p);

    vs = vsp.getVolSurface(xFit);
    for (int i = 0; i < nStrikePoints; i++) {
      strikes[i] = 0.0 + 0.12 * i / (nStrikePoints - 1.0);
    }
    System.out.println();
    for (int j = 0; j < nExpPoints; j++) {
      times[j] = 0 + 10.0 * j / (nExpPoints - 1.0);
      System.out.print("\t" + times[j]);
    }

    for (int i = 0; i < nStrikePoints; i++) {
      System.out.print("\n" + strikes[i]);
      for (int j = 0; j < nExpPoints; j++) {
        Double vol = vs.getVolatility(times[j], strikes[i]);
        System.out.print("\t" + vol);
      }
    }
    System.out.println("\n");

  }
}
