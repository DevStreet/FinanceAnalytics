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
import com.opengamma.financial.convention.daycount.DayCount;
import com.opengamma.financial.convention.daycount.DayCounts;
import com.opengamma.util.ArgumentChecker;

/**
 * 
 */
public class EquityStressDemo {

  private static final BjerksundStenslandModel BJERKSUND_STENSLAND = new BjerksundStenslandModel();
  private static final String AMERICAN = "American";
  private static final String EUROPEAN = "European";
  private static final double ONE_PC = 0.01;
  private static final double ONE_HUNDRED = 100.;
  private static final double ONE_BP = 1e-4;
  private static final double ONE_DAY = 1.0 / 365.0;
  private static final DayCount ACT365F = DayCounts.ACT_365;
  private static final double TIME_OFFSET = (9 + 15 / 60.) / 24.; // trade time is 12:00 but expiry at 21:15, so extra 9H15M

  private static final String[] GREEKS = new String[] {"Delta", "Gamma", "Vega", "Rho", "Theta" };
  private static double[] s_PriceMoves = new double[] {-0.5, -0.45, -0.4, -0.35, -0.3, -0.25, -0.2, -0.15, -0.1, -0.05, 0, 0.05, 0.1, 0.15, 0.2, 0.25, 0.3, 0.35, 0.4, 0.45, 0.5 };
  private static double[] s_VolMoves = new double[] {-0.5, -0.49, -0.48, -0.47, -0.46, -0.45, -0.44, -0.43, -0.42, -0.41, -0.4, -0.39, -0.38, -0.37, -0.36, -0.35, -0.34, -0.33, -0.32, -0.31, -0.3,
    -0.29, -0.28, -0.27, -0.26, -0.25, -0.24, -0.23, -0.22, -0.21, -0.2, -0.19, -0.18, -0.17, -0.16, -0.15, -0.14, -0.13, -0.12, -0.11, -0.1, -0.09, -0.08, -0.07, -0.06, -0.05, -0.04, -0.03, -0.02,
    -0.01, 0, 0.01, 0.02, 0.03, 0.04, 0.05, 0.06, 0.07, 0.08, 0.09, 0.1, 0.11, 0.12, 0.13, 0.14, 0.15, 0.16, 0.17, 0.18, 0.19, 0.2, 0.21, 0.22, 0.23, 0.24, 0.25, 0.26, 0.27, 0.28, 0.29, 0.3, 0.31,
    0.32, 0.33, 0.34, 0.35, 0.36, 0.37, 0.38, 0.39, 0.4, 0.41, 0.42, 0.43, 0.44, 0.45, 0.46, 0.47, 0.48, 0.49, 0.5 };

  private static String[] SECURITY_DES = new String[] {"CALL- SPXW $1995 EXP 11/07/2014 STANDARD & POORS 500 INDEX", "CALL- SPXW $2010 EXP 11/07/2014 STANDARD & POORS 500 INDEX",
    "CALL- SPXW $2000 EXP 11/07/2014 STANDARD & POORS 500 INDEX", "PUT - SPY 100 @ 185 EXP 12/20/2014 SPDR S&P 500 ETF TRUST", "CALL- SPY 100 @ 205 EXP 10/18/2014 SPDR S&P 500 ETF TRUST",
    "CALL- AIG 100 @ 60 EXP 01/15/2016 AMERICAN INTERNATIONAL GROUP I COM STK", "PUT - SPY 100 @ 180 EXP 03/20/2015 SPDR S&P 500 ETF TRUST",
    "CALL- SPX $2010 EXP 11/22/2014 STANDARD & POORS 500 INDEX", "PUT - VIX $13 EXP 10/22/2014 CBOE VOLATILITY INDEX", "CALL- SPXW $2050 EXP 12/31/2014 STANDARD & POORS 500 INDEX",
    "CALL- SPY 100 @ 215 EXP 03/20/2015 SPDR S&P 500 ETF TRUST", "PUT - VIX $14 EXP 10/22/2014 CBOE VOLATILITY INDEX", "CALL- SPY 100 @ 209 EXP 12/20/2014 SPDR S&P 500 ETF TRUST" };
  private static double[] UNDERLYING_PRICE = new double[] {1924.49, 1933.54, 1936.89, 192.085, 192.025, 49.885, 191.595, 1914.47, 20.44, 1918.5, 191.715, 19.12, 192.335 };
  private static double[] FORWARD = new double[] {1922.65, 1931.69, 1935.04, 191.1374, 192.024, 49.4124, 189.6448, 1910.42, 18.02, 1910.58, 189.7626, 17.82, 191.3875 };
  private static double[] RATE = new double[] {0.00229, 0.00229, 0.00229, 0.00229, 0.00229, 0.00404, 0.00233, 0.00229, 0.00229, 0.00229, 0.0023, 0.00229, 0.00229 };
  private static double[] YIELD = new double[] {0.01446, 0.01439, 0.01437, 0.02803, 0.00095, 0.01163, 0.02562, 0.02048, Double.NaN, 0.02063, 0.02561, Double.NaN, 0.02798 };
  private static double[] PRICE = new double[] {6.3, 4.3, 6.7, 3.52, 0.01, 2.07, 5.45, 5.6, 0.05, 6.8, 0.47, 0.15, 0.19 };
  private static double[] STRIKE = new double[] {1995, 2010, 2000, 185, 205, 60, 180, 2010, 13, 2050, 215, 14, 209 };
  private static String[] EXERCISE = new String[] {"European", "European", "European","European"/*"American"*/, "American", "American", "American", "European", "European", "European", "American", "European",
  "American" };
  private static String[] CALL_PUT = new String[] {"Call", "Call", "Call", "Put", "Call", "Call", "Put", "Call", "Put", "Call", "Call", "Put", "Call" };
  private static int[] DAYS_TO_EXPIRY = new int[] {28, 28, 28, 70, 7, 462, 161, 42, 12, 82, 161, 12, 70 };
  private static double[] IMP_VOL = new double[] {0.13478, 0.12453, 0.12774, 0.18535, 0.1826, 0.24264, 0.19411, 0.13129, 1.02889, 0.12512, 0.12355, 1.04066, 0.11785 };
  private static double[] DELTA = new double[] {16.69, 12.98, 18.15, -32.77, 0.62, 27.97, -31.6, 13.25, -3.46, 12.34, 7, -8.78, 4.72 };
  private static double[] GAMMA = new double[] {6.6637, 6.0828, 7.4019, 4.4225, 0.6905, 1.2295, 2.7382, 4.7863, 0.4577, 3.4271, 1.6449, 0.8923, 1.9106 };
  private static double[] VEGA = new double[] {133.87, 113.9, 142.43, 30.36, 0.48, 18.76, 45.03, 139.7, 0.25, 185.69, 17.07, 0.52, 8.31 };
  private static double[] THETA = new double[] {-30.83, -24.15, -30.87, -4.44, -0.58, -0.46, -3.09, -20.36, -1.05, -12.9, -0.57, -2.2, -0.63 };
  private static double[] RHO = new double[] {0.24, 0.19, 0.27, -0.13, 0, 0.14, -0.29, 0.29, 0, 0.52, 0.05, 0, 0.02 };

  @Test
  public void test() {

    OptionData data = new OptionData();
    double spot = 192.085;
    double vol = 0.18535;
    data.k = 185;
    data.t = (70+TIME_OFFSET) * ONE_DAY;
    data.r = 0.00229; // The risk free rate
    double y = 0.02803; // dividend yield
    data.isCall = false;
    // cost-of-carry - the forward price is S*exp(b*t). For a dividend yield, y, b = r - y, and for a foreign risk
    // free rate, r_f, b = r - r_f
    data.b = data.r - y;

    OptionPricingModel model = new BjerksundStenslandPricingModel();
    printTables(spot, vol, data, model);

    // print the same tables using Black-Scholes-Merton
    model = new BlackScholesMertonPricingModel();
    printTables(spot, vol, data, model);
  }

  /**
   * For each option in the portfolio print the price and the Greeks
   */
  @Test
  public void printPortfolioPriceAndGreeks() {
    int n = SECURITY_DES.length;
    System.out.println("price\tdelta\tgammaP\tvega\trho\ttheta");
    for (int i = 0; i < n; i++) {          
      boolean useBlackModel = EXERCISE[i].equals(EUROPEAN) && Double.isNaN(YIELD[i]);
      OptionPricingModel model = EXERCISE[i].equals(EUROPEAN) ? new BlackScholesMertonPricingModel() : new BjerksundStenslandPricingModel();
      OptionData data = new OptionData();
      double spot = useBlackModel ? FORWARD[i] : UNDERLYING_PRICE[i];
      double vol = IMP_VOL[i];
      data.k = STRIKE[i];
      data.t = (DAYS_TO_EXPIRY[i] + TIME_OFFSET) * ONE_DAY;
      data.r = RATE[i]; // The risk free rate
      double y = YIELD[i]; // dividend yield
      data.isCall = CALL_PUT[i].equals("Call");
      // cost-of-carry - the forward price is S*exp(b*t). For a dividend yield, y, b = r - y, and for a foreign risk
      // free rate, r_f, b = r - r_f
      data.b = useBlackModel ? 0.0 : data.r - y;
      Function1D<double[], Double> pvFunc = model.getPVFunc(data); 
      Function1D<Double, double[]> greeksFunc = model.getGreeksFunc(data, vol);

      double price = pvFunc.evaluate(new double[] {spot, vol});
      double[] greeks = greeksFunc.evaluate(spot);
      System.out.println((price + "\t" + greeks[0] + "\t" + greeks[1] + "\t" +greeks[2]+ "\t" + greeks[3] + "\t" + greeks[4]));   
    }
  }

  @Test
  public void printStressTables() {
    int n = SECURITY_DES.length;

    long tic = System.nanoTime();
    for (int i = 0; i < n; i++) {
      printTable(i);
    }
    System.out.println("Total time: " + (System.nanoTime()-tic)*1e-9+"s");
  }


  private void printTable(int index) {
    PrintStream out = System.out;
    boolean useBlackModel = EXERCISE[index].equals(EUROPEAN) && Double.isNaN(YIELD[index]);
    out.println("Stress table for: " + SECURITY_DES[index]);
    OptionPricingModel model = EXERCISE[index].equals(EUROPEAN) ? new BlackScholesMertonPricingModel() : new BjerksundStenslandPricingModel();
    OptionData data = new OptionData();
    double spot = useBlackModel ? FORWARD[index] : UNDERLYING_PRICE[index];
    double vol = IMP_VOL[index];
    data.k = STRIKE[index];
    data.t = (DAYS_TO_EXPIRY[index] + TIME_OFFSET) * ONE_DAY;
    data.r = RATE[index]; // The risk free rate
    double y = YIELD[index]; // dividend yield
    data.isCall = CALL_PUT[index].equals("Call");
    // cost-of-carry - the forward price is S*exp(b*t). For a dividend yield, y, b = r - y, and for a foreign risk
    // free rate, r_f, b = r - r_f
    data.b = useBlackModel ? 0.0 : data.r - y;

    printTables(spot, vol, data, model);
  }

  private void printTables(double spot, double vol, OptionData optionData, OptionPricingModel model) {
    PrintStream out = System.out;
    genGreekTable(spot, vol, s_PriceMoves, optionData, model, out);
    out.println();
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
          res[0] = ONE_HUNDRED * BlackScholesFormulaRepository.delta(s, optionData.k, optionData.t, vol, optionData.r, optionData.b, optionData.isCall);
          res[1] = s * BlackScholesFormulaRepository.gamma(s, optionData.k, optionData.t, vol, optionData.r, optionData.b);
          res[2] = BlackScholesFormulaRepository.vega(s, optionData.k, optionData.t, vol, optionData.r, optionData.b);
          res[3] = ONE_PC * BlackScholesFormulaRepository.rho(s, optionData.k, optionData.t, vol, optionData.r, optionData.b, optionData.isCall);
          res[4] = ONE_DAY * ONE_HUNDRED * BlackScholesFormulaRepository.theta(s, optionData.k, optionData.t, vol, optionData.r, optionData.b, optionData.isCall);
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



    @Override
    public Function1D<Double, double[]> getGreeksFunc(final OptionData optionData, final double vol) {
      return new Function1D<Double, double[]>() {

        @Override
        public double[] evaluate(Double s) {
          // TODO this is wasteful - should have one function to produce the Greeks
          double[] temp = BJERKSUND_STENSLAND.getPriceAdjoint(s, optionData.k, optionData.r, optionData.b, optionData.t, vol, optionData.isCall);
          double[] dng = BJERKSUND_STENSLAND.getPriceDeltaGamma(s, optionData.k, optionData.r, optionData.b, optionData.t, vol, optionData.isCall);
          double[] res = new double[5];
          res[0] = ONE_HUNDRED * dng[1]; // delta
          res[1] = s * dng[2]; // gamma
          res[2] = temp[6]; // vega
          res[3] = ONE_PC * (temp[3] + temp[4]); // rho
          res[4] = -ONE_DAY * ONE_HUNDRED * temp[5]; // theta wrt the passage of time
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
          return BJERKSUND_STENSLAND.price(s, optionData.k, optionData.r, optionData.b, optionData.t, vol, optionData.isCall);
        }
      };
    }

  }

}
