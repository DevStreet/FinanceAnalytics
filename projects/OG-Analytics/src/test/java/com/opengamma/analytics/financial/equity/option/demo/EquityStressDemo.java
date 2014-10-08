/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.equity.option.demo;

import java.io.PrintStream;

import org.testng.annotations.Test;

import com.opengamma.analytics.financial.model.option.pricing.analytic.BjerksundStenslandModel;
import com.opengamma.analytics.financial.model.volatility.BlackScholesFormulaRepository;
import com.opengamma.analytics.math.function.Function1D;
import com.opengamma.util.ArgumentChecker;

/**
 * 
 */
public class EquityStressDemo {

  private static final String[] GREEKS = new String[] {"Delta", "Gamma", "Vega", "Rho", "Theta" };
  private static double[] s_PriceMoves = new double[] {-0.5, -0.45, -0.4, -0.35, -0.3, -0.25, -0.2, -0.15, -0.1, -0.05, 0, 0.05, 0.1, 0.15, 0.2, 0.25, 0.3, 0.35, 0.4, 0.45, 0.5 };
  private static double[] s_VolMoves = new double[] {-0.5, -0.49, -0.48, -0.47, -0.46, -0.45, -0.44, -0.43, -0.42, -0.41, -0.4, -0.39, -0.38, -0.37, -0.36, -0.35, -0.34, -0.33, -0.32, -0.31, -0.3,
    -0.29, -0.28, -0.27, -0.26, -0.25, -0.24, -0.23, -0.22, -0.21, -0.2, -0.19, -0.18, -0.17, -0.16, -0.15, -0.14, -0.13, -0.12, -0.11, -0.1, -0.09, -0.08, -0.07, -0.06, -0.05, -0.04, -0.03, -0.02,
    -0.01, 0, 0.01, 0.02, 0.03, 0.04, 0.05, 0.06, 0.07, 0.08, 0.09, 0.1, 0.11, 0.12, 0.13, 0.14, 0.15, 0.16, 0.17, 0.18, 0.19, 0.2, 0.21, 0.22, 0.23, 0.24, 0.25, 0.26, 0.27, 0.28, 0.29, 0.3, 0.31,
    0.32, 0.33, 0.34, 0.35, 0.36, 0.37, 0.38, 0.39, 0.4, 0.41, 0.42, 0.43, 0.44, 0.45, 0.46, 0.47, 0.48, 0.49, 0.5 };

  @Test
  public void test() {

    OptionData data = new OptionData();
    double spot = 6734.4;
    double vol = 0.248;
    data.k = 6500.0;
    data.t = 0.35;
    data.r = 0.005; // The risk free rate
    double y = 0.08; // dividend yield
    data.isCall = true;
    // cost-of-carry - the forward price is S*exp(b*t). For a dividend yield, y, b = r - y, and for a foreign risk
    // free rate, r_f, b = r - r_f
    data.b = data.r - y;

    OptionPricingModel model = new BjerksundStenslandPricingModel();
    printTables(spot, vol, data, model);

    //print the same tables using Black-Scholes-Merton 
    model = new BlackScholesMertonPricingModel();
    printTables(spot, vol, data, model);
  }

  private void printTables(double spot, double vol, OptionData optionData, OptionPricingModel model) {
    PrintStream out = System.out;
    genGreekTable(spot, vol, s_PriceMoves, optionData, model, out);
    genPVTable(spot, s_PriceMoves, vol, s_VolMoves, optionData, model, out);
  }

  private void genGreekTable(double spot, double vol, double[] priceMoves, OptionData optionData, OptionPricingModel model, PrintStream out) {

    ArgumentChecker.notEmpty(priceMoves, "pcMoves");
    int nMoves = priceMoves.length;
    int nGreeks = GREEKS.length;
    out.print("Price Move");
    for (int i = 0; i < nMoves; i++) {
      ArgumentChecker.isTrue(priceMoves[i] > -1.0, "What does a price move of {}% mean?", 100 * priceMoves[i]);
      out.print("\t" + priceMoves[i] * 100 + "%");
    }
    out.print("\n");

    Function1D<Double, double[]> func = model.getGreeksFunc(optionData, vol);
    double[][] res = new double[nMoves][];

    for (int i = 0; i < nMoves; i++) {
      Double s0 = spot * (1.0 + priceMoves[i]);
      res[i] = func.evaluate(s0);
    }

    // print table the other way round
    for (int i = 0; i < nGreeks; i++) {
      out.print(GREEKS[i]);
      for (int j = 0; j < nMoves; j++) {
        out.print("\t" + res[j][i]);
      }
      out.print("\n");
    }
    out.print("\n");
  }

  private void genPVTable(double spot, double[] priceMoves, double impVol, double[] volMoves, OptionData optionData, OptionPricingModel model, PrintStream out) {
    ArgumentChecker.notEmpty(priceMoves, "pcMoves");
    ArgumentChecker.notEmpty(volMoves, "volMoves");
    int nPriceMoves = priceMoves.length;
    int nVolMoves = volMoves.length;

    Function1D<double[], Double> func = model.getPVFunc(optionData);

    out.print("Price/Vol");
    for (int j = 0; j < nPriceMoves; j++) {
      ArgumentChecker.isTrue(priceMoves[j] > -1.0, "What does a price move of {}% mean?", 100 * priceMoves[j]);
      out.print("\t" + priceMoves[j] * 100 + "%");
    }
    out.print("\n");
    for (int i = 0; i < nVolMoves; i++) {
      ArgumentChecker.isTrue(volMoves[i] > -1.0, "What does a vol move of {}% mean?", 100 * volMoves[i]);
      out.print(volMoves[i] * 100 + "%");
      for (int j = 0; j < nPriceMoves; j++) {
        double vol = impVol * (1.0 + volMoves[i]);
        double s0 = spot * (1.0 + priceMoves[j]);
        double pv = func.evaluate(new double[] {s0, vol });
        out.print("\t" + pv);
      }
      out.print("\n");
    }
    out.print("\n");
  }

  class OptionData {
    public double t;
    public double k;
    public double r;
    public double b;
    public boolean isCall;
  }

  interface OptionPricingModel {
    Function1D<Double, double[]> getGreeksFunc(OptionData optionData, double vol);

    Function1D<double[], Double> getPVFunc(OptionData optionData);
  }

  class BlackScholesMertonPricingModel implements OptionPricingModel {

    @Override
    public Function1D<Double, double[]> getGreeksFunc(final OptionData optionData, final double vol) {
      return new Function1D<Double, double[]>() {

        @Override
        public double[] evaluate(Double s) {
          double[] res = new double[5];
          res[0] = BlackScholesFormulaRepository.delta(s, optionData.k, optionData.t, vol, optionData.r, optionData.b, optionData.isCall);
          res[1] = BlackScholesFormulaRepository.gamma(s, optionData.k, optionData.t, vol, optionData.r, optionData.b);
          res[2] = BlackScholesFormulaRepository.vega(s, optionData.k, optionData.t, vol, optionData.r, optionData.b);
          res[3] = BlackScholesFormulaRepository.rho(s, optionData.k, optionData.t, vol, optionData.r, optionData.b, optionData.isCall);
          res[4] = BlackScholesFormulaRepository.theta(s, optionData.k, optionData.t, vol, optionData.r, optionData.b, optionData.isCall);
          return res;
        }
      };
    }

    @Override
    public Function1D<double[], Double> getPVFunc(final OptionData optionData) {
      return new Function1D<double[], Double>() {
        @Override
        public Double evaluate(double[] x) {
          double s = x[0];
          double vol = x[1];
          return BlackScholesFormulaRepository.price(s, optionData.k, optionData.t, vol, optionData.r, optionData.b, optionData.isCall);
        }
      };
    }
  }

  class BjerksundStenslandPricingModel implements OptionPricingModel {

    private final BjerksundStenslandModel _model = new BjerksundStenslandModel();

    @Override
    public Function1D<Double, double[]> getGreeksFunc(final OptionData optionData, final double vol) {
      return new Function1D<Double, double[]>() {

        @Override
        public double[] evaluate(Double s) {
          // TODO this is wasteful - should have one function to produce the Greeks
          double[] temp = _model.getPriceAdjoint(s, optionData.k, optionData.r, optionData.b, optionData.t, vol, optionData.isCall);
          double[] dng = _model.getPriceDeltaGamma(s, optionData.k, optionData.r, optionData.b, optionData.t, vol, optionData.isCall);
          double[] res = new double[5];
          res[0] = dng[1]; // delta
          res[1] = dng[2]; // gamma
          res[2] = temp[6]; // vega
          res[3] = temp[3]; // rho
          res[4] = -temp[5]; // theta wrt the pasage of time
          return res;
        }
      };
    }

    @Override
    public Function1D<double[], Double> getPVFunc(final OptionData optionData) {

      return new Function1D<double[], Double>() {
        @Override
        public Double evaluate(double[] x) {
          double s = x[0];
          double vol = x[1];
          return _model.price(s, optionData.k, optionData.r, optionData.b, optionData.t, vol, optionData.isCall);
        }
      };
    }

  }

}
