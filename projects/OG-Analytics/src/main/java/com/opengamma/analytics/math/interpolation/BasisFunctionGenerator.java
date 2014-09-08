/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.math.interpolation;

import java.util.ArrayList;
import java.util.List;

import com.opengamma.analytics.math.FunctionUtils;
import com.opengamma.analytics.math.function.Function1D;
import com.opengamma.util.ArgumentChecker;

/**
 * Generator for a set of basis functions
 */
public class BasisFunctionGenerator {

  /**
   * Helper class to hold the knots and polynomial degree that specify a set of basis functions
   */
  public static class KnotsAndDegree {
    private final double[] _knots;
    private final int _degree;
    private final int _nSplines;

    /**
     * Generate knots uniformly in the range xa and xb and knots outside this range to support the basis functions on
     * the edge of the range
     * @param xa start of the range
     * @param xb end of the range
     * @param nKnots number of knots in the range (internal knots)
     * @param degree the polynomial degree of the basis functions (this will determine how many external knots are required)
     */
    public KnotsAndDegree(double xa, double xb, int nKnots, int degree) {
      ArgumentChecker.isTrue(xb > xa, "Require xb > xa, values are xa = {}, xb = {}", xa, xb);
      ArgumentChecker.notNegative(degree, "degree");
      ArgumentChecker.isTrue(nKnots - degree > 0, "Require at least {} knots for degree {}, only given {}", degree + 1, degree, nKnots);

      _degree = degree;
      int nTotalKnots = nKnots + 2 * degree; // this is the total number of knots, including those outside the range
      _nSplines = nKnots + degree - 1;
      _knots = new double[nTotalKnots];
      double dx = (xb - xa) / (nKnots - 1);

      // knots to the left and right of the range
      for (int i = 0; i < degree; i++) {
        _knots[i] = (i - degree) * dx + xa;
        _knots[degree + nKnots + i] = xb + dx * (i + 1);
      }
      // knots in the main range
      for (int i = 0; i < nKnots - 1; i++) {
        _knots[i + degree] = xa + i * dx;
      }
      _knots[nKnots + degree - 1] = xb;
    }

    /**
     * Generate a set of knots capable of supporting the given degree of basis functions. The given knots are used inside
     * the range, with knots generated outside this range to support the basis functions on the edge of the range
     * @param internalKnots the internal knots. The start of the range is the first knot and the end is the last.
     * @param degree the polynomial degree of the basis functions (this will determine how many external knots are required)
     */
    public KnotsAndDegree(double[] internalKnots, int degree) {
      ArgumentChecker.notEmpty(internalKnots, "knots");
      ArgumentChecker.notNegative(degree, "degree");
      final int nInterKnots = internalKnots.length;
      ArgumentChecker.isTrue(nInterKnots - degree > 0, "Require at least {} knots for degree {}, only given {}", degree + 1, degree, nInterKnots);

      // check knots are ascending
      for (int i = 1; i < nInterKnots; i++) {
        ArgumentChecker.isTrue(internalKnots[i] - internalKnots[i - 1] > 0, "knots are not ascending");
      }
      _degree = degree;
      _nSplines = nInterKnots + degree - 1;

      final int nTotalKnots = nInterKnots + 2 * degree; // add in extra knots outside the range to handle basis functions on the edge
      _knots = new double[nTotalKnots];

      final double dxa = internalKnots[1] - internalKnots[0];
      final double dxb = internalKnots[nInterKnots - 1] - internalKnots[nInterKnots - 2];
      // knots to the left and right of the range
      for (int i = 0; i < degree; i++) {
        _knots[i] = (i - degree) * dxa + internalKnots[0];
        _knots[degree + nInterKnots + i] = internalKnots[nInterKnots - 1] + dxb * (i + 1);
      }
      // knots in the main range
      System.arraycopy(internalKnots, 0, _knots, degree, nInterKnots);
    }

    public double[] getKnots() {
      return _knots;
    }

    public int getDegree() {
      return _degree;
    }

    public int getNumSplines() {
      return _nSplines;
    }
  }

  /**
   * Generates a set of b-splines with knots a fixed distance apart
   * @param xa minimum value of the function domain
   * @param xb maximum value of the function domain
   * @param nKnots number of internal knots (minimum of degree + 1)
   * @param degree The order of the polynomial splines
   * @return a List of functions
   */
  public List<Function1D<Double, Double>> generateSet(final double xa, final double xb, final int nKnots, final int degree) {
    // args are checked in the constructor below
    KnotsAndDegree k = new KnotsAndDegree(xa, xb, nKnots, degree);
    return generateSet(k);
  }

  /**
   * Generates a set of b-splines a given polynomial degree on the specified knots
   * @param internalKnots the internal knots. The start of the range is the first knot and the end is the last.
   * @param degree the polynomial degree of the basis functions (this will determine how many external knots are required)
   * @return a List of functions
   */
  public List<Function1D<Double, Double>> generateSet(final double[] internalKnots, final int degree) {
    // args are checked in the constructor below
    KnotsAndDegree k = new KnotsAndDegree(internalKnots, degree);
    return generateSet(k);
  }

  /**
   * Generate a set of b-splines with a given polynomial degree on the specified knots
   * @param knots holder for the knots and degree
   * @return a List of functions
   */
  public List<Function1D<Double, Double>> generateSet(KnotsAndDegree knots) {
    ArgumentChecker.notNull(knots, "knots");
    int nSplines = knots.getNumSplines();
    final List<Function1D<Double, Double>> functions = new ArrayList<>(nSplines);
    for (int i = 0; i < nSplines; i++) {
      functions.add(generate(knots, i));
    }
    return functions;
  }

  /**
   * Generate a set of N-dimensional b-splines as the produce of 1-dimensional b-splines with a given polynomial degree
   * on the specified knots
   * @param kAndD holder for the knots and degree in each dimension
   * @return a List of functions
   */
  public List<Function1D<double[], Double>> generateSet(KnotsAndDegree[] kAndD) {
    ArgumentChecker.noNulls(kAndD, "kAndD");
    int dim = kAndD.length;
    int[] nSplines = new int[dim];
    int product = 1;
    for (int i = 0; i < dim; i++) {
      nSplines[i] = kAndD[i].getNumSplines();
      product *= nSplines[i];
    }
    final List<Function1D<double[], Double>> functions = new ArrayList<>(product);

    for (int i = 0; i < product; i++) {
      int[] indices = FunctionUtils.fromTensorIndex(i, nSplines);
      functions.add(generate(kAndD, indices));
    }
    return functions;

  }

  @Deprecated
  public List<Function1D<double[], Double>> generateSet(final double[] xa, final double[] xb, final int[] nKnots, final int[] degree) {

    final int dim = xa.length;
    ArgumentChecker.isTrue(dim == xb.length, "xb wrong dimension");
    ArgumentChecker.isTrue(dim == nKnots.length, "nKnots wrong dimension");
    ArgumentChecker.isTrue(dim == degree.length, "degree wrong dimension");

    final int[] n = new int[dim];
    final int[] nSplines = new int[dim];
    final double[][] knots = new double[dim][];

    final double[] dx = new double[dim];
    int product = 1;
    for (int k = 0; k < dim; k++) {
      final int p = nKnots[k] + 2 * degree[k];
      n[k] = p;
      knots[k] = new double[p];
      nSplines[k] = nKnots[k] + degree[k] - 1;
      product *= nSplines[k];
      dx[k] = (xb[k] - xa[k]) / (nKnots[k] - 1);

      // knots to the left and right of the range
      for (int i = 0; i < degree[k]; i++) {
        knots[k][i] = (i - degree[k]) * dx[k] + xa[k];
        knots[k][degree[k] + nKnots[k] + i] = xb[k] + dx[k] * (i + 1);
      }
      // knots in the main range
      for (int i = 0; i < nKnots[k] - 1; i++) {
        knots[k][i + degree[k]] = xa[k] + i * dx[k];
      }
      knots[k][nKnots[k] + degree[k] - 1] = xb[k];
    }

    final List<Function1D<double[], Double>> functions = new ArrayList<>(product);

    for (int i = 0; i < product; i++) {
      final int[] indices = FunctionUtils.fromTensorIndex(i, nSplines);
      functions.add(generate(knots, degree, indices));
    }
    return functions;
  }

  @Deprecated
  public Function1D<Double, Double> generate(final double xa, final double xb, final int nKnots, final int degree, final int j) {
    KnotsAndDegree kAndD = new KnotsAndDegree(xa, xb, nKnots, degree);
    return generate(kAndD, j);
  }

  @Deprecated
  public Function1D<double[], Double> generate(final double[][] knots, final int[] degree, final int[] index) {
    ArgumentChecker.notNull(knots, "knots are null");
    final int dim = knots.length;
    ArgumentChecker.isTrue(dim == degree.length, "degree wrong dimension");
    ArgumentChecker.isTrue(dim == index.length, "index wrong dimension");
    final List<Function1D<Double, Double>> funcs = new ArrayList<>(dim);
    for (int i = 0; i < dim; i++) {
      funcs.add(generate(knots[i], degree[i], index[i]));
    }
    return new Function1D<double[], Double>() {

      @Override
      public Double evaluate(final double[] x) {
        double product = 1.0;
        ArgumentChecker.isTrue(dim == x.length, "length of x {} was not equal to dimension {}", x.length, dim);
        for (int i = 0; i < dim; i++) {
          product *= funcs.get(i).evaluate(x[i]);
        }
        return product;
      }
    };

  }

  public Function1D<double[], Double> generate(KnotsAndDegree[] kAndD, final int[] index) {
    ArgumentChecker.noNulls(kAndD, "kAndD");
    ArgumentChecker.notEmpty(index, "index");
    final int dim = kAndD.length;
    ArgumentChecker.isTrue(dim == index.length, "index wrong dimension");
    final List<Function1D<Double, Double>> funcs = new ArrayList<>(dim);
    for (int i = 0; i < dim; i++) {
      funcs.add(generate(kAndD[i], index[i]));
    }
    return new Function1D<double[], Double>() {

      @Override
      public Double evaluate(final double[] x) {
        double product = 1.0;
        ArgumentChecker.isTrue(dim == x.length, "length of x {} was not equal to dimension {}", x.length, dim);
        for (int i = 0; i < dim; i++) {
          product *= funcs.get(i).evaluate(x[i]);
        }
        return product;
      }
    };

  }

  /**
   * Generate the i^th basis function
   * @param data Container for the knots and degree of the basis function
   * @param index The index (from zero) of the function. Must be in range 0 to data.getNumSplines() (exclusive)
   * For example if the degree is 1, and index is 0, this will cover the first three knots.
   * @return The i^th basis function
   */
  public Function1D<Double, Double> generate(KnotsAndDegree data, final int index) {
    ArgumentChecker.notNull(data, "data");
    ArgumentChecker.isTrue(index >= 0 && index < data.getNumSplines(), "index must be in range {] to {} (exclusive)", 0,
        data.getNumSplines());

    final int degree = data.getDegree();
    final double[] knots = data.getKnots();
    return generate(knots, degree, index);
  }

  private Function1D<Double, Double> generate(final double[] knots, final int degree, final int index) {

    if (degree == 0) {
      return new Function1D<Double, Double>() {
        @Override
        public Double evaluate(final Double x) {
          return (x >= knots[index] && x < knots[index + 1]) ? 1.0 : 0.0;
        }
      };
    }

    return new Function1D<Double, Double>() {
      @Override
      public Double evaluate(final Double x) {
        final Function1D<Double, Double> fa = generate(knots, degree - 1, index);
        final Function1D<Double, Double> fb = generate(knots, degree - 1, index + 1);
        return (x - knots[index]) / (knots[index + degree] - knots[index]) * fa.evaluate(x) + (knots[index + degree + 1] - x) / (knots[index + degree + 1] - knots[index + 1]) * fb.evaluate(x);
      }

    };

  }

}
