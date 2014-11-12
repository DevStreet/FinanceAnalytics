/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.model.volatility.smile.fitting.demo;

import java.util.Arrays;
import java.util.BitSet;

import org.testng.annotations.Test;

import com.opengamma.analytics.financial.model.option.pricing.analytic.formula.EuropeanVanillaOption;
import com.opengamma.analytics.financial.model.volatility.BlackFormulaRepository;
import com.opengamma.analytics.financial.model.volatility.smile.fitting.SABRModelFitter;
import com.opengamma.analytics.financial.model.volatility.smile.fitting.SmileModelFitter;
import com.opengamma.analytics.financial.model.volatility.smile.fitting.interpolation.BenaimDodgsonKainthExtrapolationFunctionProvider;
import com.opengamma.analytics.financial.model.volatility.smile.fitting.interpolation.GeneralSmileInterpolator;
import com.opengamma.analytics.financial.model.volatility.smile.fitting.interpolation.ShiftedLogNormalTailExtrapolation;
import com.opengamma.analytics.financial.model.volatility.smile.fitting.interpolation.ShiftedLogNormalTailExtrapolationFitter;
import com.opengamma.analytics.financial.model.volatility.smile.fitting.interpolation.SmileInterpolatorSABR;
import com.opengamma.analytics.financial.model.volatility.smile.function.SABRFormulaData;
import com.opengamma.analytics.financial.model.volatility.smile.function.SABRHaganVolatilityFunction;
import com.opengamma.analytics.financial.model.volatility.smile.function.SmileModelData;
import com.opengamma.analytics.financial.model.volatility.smile.function.VolatilityFunctionProvider;
import com.opengamma.analytics.math.differentiation.ScalarFirstOrderDifferentiator;
import com.opengamma.analytics.math.function.Function1D;
import com.opengamma.analytics.math.matrix.DoubleMatrix1D;
import com.opengamma.analytics.math.statistics.leastsquare.LeastSquareResultsWithTransform;

/**
 * 
 */
public class RopemakerSmileDemo {

  private static final int DAYS_TO_MAT = 15;
  private static final double EXPIRY = DAYS_TO_MAT / 365.;
  private static final double FORWARD = 3130;
  private static final double DF = 1.0;
  private static final double[] PUT_STRIKES = new double[] {2825, 2875, 2975, 3075 };
  private static final double[] CALL_STRIKES = new double[] {3100, 3200, 3300, 3400 };
  private static final double[] PUT_PRICES = new double[] {3, 4.7, 11, 30 };
  private static final double[] CALL_PRICES = new double[] {66, 19.5, 3, 0.4 };
  private static final double[] STRIKES = genStrikes();
  private static final double[] IV = genImpVols();
  private static VolatilityFunctionProvider<SABRFormulaData> SABR = new SABRHaganVolatilityFunction();
  private static double[] ERRORS;
  private static final double LOWER_STRIKE = 2750.;
  private static final double UPPER_STRIKE = 3425.;
  private static final double STRIKE_STEP = 25.;
  private static final double[] DISPLAY_STRIKES;

  static {
    int n = (int) ((UPPER_STRIKE - LOWER_STRIKE) / STRIKE_STEP + 1);
    DISPLAY_STRIKES = new double[n];
    for (int i = 0; i < n; i++) {
      DISPLAY_STRIKES[i] = LOWER_STRIKE + i * STRIKE_STEP;
    }
    DISPLAY_STRIKES[n - 1] = UPPER_STRIKE;
  }

  // For least squares fit use an error of 10bps - this only affects the reported chi-square
  static {
    int n = IV.length;
    ERRORS = new double[n];
    Arrays.fill(ERRORS, 1e-3); // 10bps
  }

  private static double[] genStrikes() {
    int nPuts = PUT_STRIKES.length;
    int nCalls = CALL_STRIKES.length;
    double[] k = new double[nPuts + nCalls];
    System.arraycopy(PUT_STRIKES, 0, k, 0, nPuts);
    System.arraycopy(CALL_STRIKES, 0, k, nPuts, nCalls);
    return k;
  }

  private static double[] genImpVols() {
    int nPuts = PUT_STRIKES.length;
    int nCalls = CALL_STRIKES.length;
    double[] iv = new double[nPuts + nCalls];

    for (int i = 0; i < nPuts; i++) {
      iv[i] = BlackFormulaRepository.impliedVolatility(PUT_PRICES[i] / DF, FORWARD, PUT_STRIKES[i], EXPIRY, false);
    }
    for (int i = 0; i < nCalls; i++) {
      iv[i + nPuts] = BlackFormulaRepository.impliedVolatility(CALL_PRICES[i] / DF, FORWARD, CALL_STRIKES[i], EXPIRY, true);
    }
    return iv;
  }

  @Test
  public void fitSabrSmile() {
    // SABR starting parameters
    BitSet fixed = new BitSet();
    // fixed.set(1);
    double atmVol = 0.18;
    double beta = 0.9;
    double rho = -0.9;
    double nu = 1.8;
    double alpha = atmVol * Math.pow(FORWARD, 1 - beta);

    DoubleMatrix1D start = new DoubleMatrix1D(alpha, beta, rho, nu);
    SmileModelFitter<SABRFormulaData> sabrFitter = new SABRModelFitter(FORWARD, STRIKES, EXPIRY, IV, ERRORS, SABR);
    Function1D<Double, Double> smile = fitSmile(sabrFitter, start, fixed);
    printDetails(smile, DISPLAY_STRIKES);
  }

  @Test(description = "Demo")
  void sabrInterpolationTest() {
    GeneralSmileInterpolator sabr_interpolator = new SmileInterpolatorSABR();
    Function1D<Double, Double> smile = sabr_interpolator.getVolatilityFunction(FORWARD, STRIKES, EXPIRY, IV);
    printDetails(smile, DISPLAY_STRIKES);
  }

  // ****************************************************************************************************************
  // Helper methods. If 'smile' fitting is brought under a common API, these could form part of that design
  // ****************************************************************************************************************

  /**
   * Extrapolate a volatility smile to low and high strikes by fitting (separately) a shifted-log-normal model at
   * the low and high strike cutoffs
   * @param forward the forward
   * @param expiry the expiry
   * @param volSmileFunc a function describing the smile (Black vol as a function of strike)
   * @param lowerStrike the lower strike
   * @param upperStrike the upper strike
   * @return a volatility smile (Black vol as a function of strike) that is valid for strikes from zero to infinity
   */
  private Function1D<Double, Double> fitShiftedLogNormalTails(final double forward, final double expiry, final Function1D<Double, Double> volSmileFunc, final double lowerStrike,
      final double upperStrike) {
    ScalarFirstOrderDifferentiator diff = new ScalarFirstOrderDifferentiator();
    Function1D<Double, Double> dVolDkFunc = diff.differentiate(volSmileFunc);
    return fitShiftedLogNormalTails(forward, expiry, volSmileFunc, dVolDkFunc, lowerStrike, upperStrike);
  }

  /**
   * Extrapolate a volatility smile to low and high strikes by fitting (separately) a shifted-log-normal model at
   * the low and high strike cutoffs.
   * @param forward the forward
   * @param expiry the expiry
   * @param volSmileFunc a function describing the smile (Black vol as a function of strike)
   * @param dVolDkFunc the gradient of the smile as a function of strike
   * @param lowerStrike the lower strike
   * @param upperStrike the upper strike
   * @return a volatility smile (Black vol as a function of strike) that is valid for strikes from zero to infinity
   */
  private Function1D<Double, Double> fitShiftedLogNormalTails(final double forward, final double expiry, final Function1D<Double, Double> volSmileFunc, final Function1D<Double, Double> dVolDkFunc,
      final double lowerStrike, final double upperStrike) {
    ShiftedLogNormalTailExtrapolationFitter tailFitter = new ShiftedLogNormalTailExtrapolationFitter();

    double vol = volSmileFunc.evaluate(lowerStrike);
    double volGrad = dVolDkFunc.evaluate(lowerStrike);
    final double[] lowerParms = tailFitter.fitVolatilityAndGrad(forward, lowerStrike, vol, volGrad, expiry);
    vol = volSmileFunc.evaluate(upperStrike);
    volGrad = dVolDkFunc.evaluate(upperStrike);
    final double[] upperParms = tailFitter.fitVolatilityAndGrad(forward, upperStrike, vol, volGrad, expiry);

    return new Function1D<Double, Double>() {
      @Override
      public Double evaluate(Double k) {
        if (k >= lowerStrike && k <= upperStrike) {
          return volSmileFunc.evaluate(k);
        }
        if (k < lowerStrike) {
          return ShiftedLogNormalTailExtrapolation.impliedVolatility(forward, k, expiry, lowerParms[0], lowerParms[1]);
        }
        return ShiftedLogNormalTailExtrapolation.impliedVolatility(forward, k, expiry, upperParms[0], upperParms[1]);
      }
    };
  }

  /**
   * gets a smile function (Black vol as a function of strike) from a VolatilityFunctionProvider and SmileModelData
   * @param fwd the forward
   * @param expiry the expiry
   * @param data parameters of the smile model (e.g. for SABR these would be alpha, beta, rho and nu)
   * @param volModel the volatility model
   * @return the smile function
   */
  private Function1D<Double, Double> toSmileFunction(final double fwd, final double expiry, final SmileModelData data, final VolatilityFunctionProvider<? extends SmileModelData> volModel) {

    return new Function1D<Double, Double>() {
      @Override
      public Double evaluate(Double k) {
        boolean isCall = k >= fwd;
        EuropeanVanillaOption option = new EuropeanVanillaOption(k, expiry, isCall);
        @SuppressWarnings("unchecked")
        Function1D<SmileModelData, Double> func = (Function1D<SmileModelData, Double>) volModel.getVolatilityFunction(option, fwd);
        return func.evaluate(data);
      }
    };
  }

  /**
   * Fit a smile model to the data set and return the smile
   * @param fitter the smile fitter
   * @param start this initial starting point of the model parameters
   * @param fixed map of any fixed parameters
   * @return the smile function
   */
  private Function1D<Double, Double> fitSmile(SmileModelFitter<? extends SmileModelData> fitter, DoubleMatrix1D start, BitSet fixed) {
    LeastSquareResultsWithTransform res = fitter.solve(start, fixed);
    System.out.println("chi-Square: " + res.getChiSq());
    VolatilityFunctionProvider<?> model = fitter.getModel();
    SmileModelData data = fitter.toSmileModelData(res.getModelParameters());

    return toSmileFunction(FORWARD, EXPIRY, data, model);
  }

  /**
   * Fit a smile model to the data set and return the smile. Outside the given range use shifted log-normal extrapolation
   * @param fitter the smile fitter
   * @param start this initial starting point of the model parameters
   * @param fixed map of any fixed parameters
   * @param lowerStrike start of the left extrapolation
   * @param upperStrike start of the right extrapolation
   * @return the smile function
   */
  private Function1D<Double, Double> fitSmile(SmileModelFitter<? extends SmileModelData> fitter, DoubleMatrix1D start, BitSet fixed, double lowerStrike, double upperStrike) {
    LeastSquareResultsWithTransform res = fitter.solve(start, fixed);
    System.out.println("chi-Square: " + res.getChiSq());
    VolatilityFunctionProvider<?> model = fitter.getModel();
    SmileModelData data = fitter.toSmileModelData(res.getModelParameters());

    Function1D<Double, Double> smile = toSmileFunction(FORWARD, EXPIRY, data, model);
    return fitShiftedLogNormalTails(FORWARD, EXPIRY, smile, lowerStrike, upperStrike);
  }

  /**
   * Fit a smile model to the data set and return the smile. Outside the given range use Benaim-Dodgson-Kainth extrapolation
   * @param fitter the smile fitter
   * @param start this initial starting point of the model parameters
   * @param fixed map of any fixed parameters
   * @param lowerStrike start of the left extrapolation
   * @param upperStrike start of the right extrapolation
   * @param lowerMu the left tail control parameter
   * @param upperMu the right tail control parameter
   * @return the smile function
   */
  private Function1D<Double, Double> fitSmile(SABRModelFitter fitter, DoubleMatrix1D start, BitSet fixed, final double lowerStrike, final double upperStrike, double lowerMu, double upperMu) {
    LeastSquareResultsWithTransform res = fitter.solve(start, fixed);
    System.out.println("chi-Square: " + res.getChiSq());
    VolatilityFunctionProvider<SABRFormulaData> model = fitter.getModel();
    SABRFormulaData data = fitter.toSmileModelData(res.getModelParameters());

    final Function1D<Double, Double> smile = toSmileFunction(FORWARD, EXPIRY, data, model);

    BenaimDodgsonKainthExtrapolationFunctionProvider tailPro = new BenaimDodgsonKainthExtrapolationFunctionProvider(lowerMu, upperMu);
    final Function1D<Double, Double> extrapFunc = tailPro.getExtrapolationFunction(data, data, model, FORWARD, EXPIRY, lowerStrike, upperStrike);

    return new Function1D<Double, Double>() {
      @Override
      public Double evaluate(Double k) {
        if (k < lowerStrike || k > upperStrike) {
          return extrapFunc.evaluate(k);
        }
        return smile.evaluate(k);
      }
    };
  }

  /**
   * Print the smile. The number of sample and range is controlled by static variables
   * @param smile the smile function
   */
  private void printDetails(Function1D<Double, Double> smile, double[] strikes) {
    System.out.println("Call Strike\tPrice\tDelta\tGamma\tVega\tImplied Vol\tPut Strike\tPrice\tDelta\tGamma\tVega");

    for (int i = 0; i < strikes.length; i++) {
      double k = strikes[i];
      double vol = smile.evaluate(k);
      double putPrice = DF * BlackFormulaRepository.price(FORWARD, k, EXPIRY, vol, false);
      double putDelta = DF * BlackFormulaRepository.delta(FORWARD, k, EXPIRY, vol, false);
      double gamma = DF * BlackFormulaRepository.gamma(FORWARD, k, EXPIRY, vol);
      double vega = DF * BlackFormulaRepository.gamma(FORWARD, k, EXPIRY, vol);
      double callPrice = DF * BlackFormulaRepository.price(FORWARD, k, EXPIRY, vol, true);
      double callDelta = DF * BlackFormulaRepository.delta(FORWARD, k, EXPIRY, vol, true);
      System.out.println(k + "\t" + callPrice + "\t" + callDelta + "\t" + gamma + "\t" + vega + "\t" + vol + "\t" + k + "\t" + putPrice + "\t" + putDelta + "\t" + gamma + "\t" + vega);
    }
  }

}
