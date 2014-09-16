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
   * Generates a set of b-splines with knots a fixed distance apart
   * @param xa minimum value of the function domain
   * @param xb maximum value of the function domain
   * @param nKnots number of internal knots (minimum of degree + 1)
   * @param degree The order of the polynomial splines
   * @return a List of functions
   */
  public List<Function1D<Double, Double>> generateSet(final double xa, final double xb, final int nKnots, final int degree) {
    // args are checked in the constructor below
    BasisFunctionKnots k = BasisFunctionKnots.fromUniform(xa, xb, nKnots, degree);
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
    BasisFunctionKnots k = BasisFunctionKnots.fromInternalKnots(internalKnots, degree);
    return generateSet(k);
  }

  /**
   * Generate a set of b-splines with a given polynomial degree on the specified knots
   * @param knots holder for the knots and degree
   * @return a List of functions
   */
  public List<Function1D<Double, Double>> generateSet(BasisFunctionKnots knots) {
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
   * @param knots holder for the knots and degree in each dimension
   * @return a List of functions
   */
  public List<Function1D<double[], Double>> generateSet(BasisFunctionKnots[] knots) {
    ArgumentChecker.noNulls(knots, "knots");
    int dim = knots.length;
    int[] nSplines = new int[dim];
    int product = 1;
    for (int i = 0; i < dim; i++) {
      nSplines[i] = knots[i].getNumSplines();
      product *= nSplines[i];
    }
    final List<Function1D<double[], Double>> functions = new ArrayList<>(product);

    for (int i = 0; i < product; i++) {
      int[] indices = FunctionUtils.fromTensorIndex(i, nSplines);
      functions.add(generate(knots, indices));
    }
    return functions;

  }

  @Deprecated
  public List<Function1D<double[], Double>> generateSet(final double[] xa, final double[] xb, final int[] nKnots, final int[] degree) {

    final int dim = xa.length;
    ArgumentChecker.isTrue(dim == xb.length, "xb wrong dimension");
    ArgumentChecker.isTrue(dim == nKnots.length, "nKnots wrong dimension");
    ArgumentChecker.isTrue(dim == degree.length, "degree wrong dimension");
    BasisFunctionKnots[] knots = new BasisFunctionKnots[dim];
    for (int i = 0; i < dim; i++) {
      knots[i] = BasisFunctionKnots.fromUniform(xa[i], xb[i], nKnots[i], degree[i]);
    }
    return generateSet(knots);
  }

  /**
   * Generate the i^th basis function
   * @param data Container for the knots and degree of the basis function
   * @param index The index (from zero) of the function. Must be in range 0 to data.getNumSplines() (exclusive)
   * For example if the degree is 1, and index is 0, this will cover the first three knots.
   * @return The i^th basis function
   */
  public Function1D<Double, Double> generate(BasisFunctionKnots data, final int index) {
    ArgumentChecker.notNull(data, "data");
    ArgumentChecker.isTrue(index >= 0 && index < data.getNumSplines(), "index must be in range {] to {} (exclusive)", 0, data.getNumSplines());
    return generate(data.getKnots(), data.getDegree(), index);
  }

  /**
   * Generate the n-dimensional basis function for the given index position. This is formed as the product of 1-d basis
   * functions.
   * @param data Containers for the knots and degree of the basis function in each dimension
   * @param index index (from zero) of the basis function in each dimension.
   * @return A n-dimensional basis function
   */
  public Function1D<double[], Double> generate(BasisFunctionKnots[] data, final int[] index) {
    ArgumentChecker.noNulls(data, "kAndD");
    ArgumentChecker.notEmpty(index, "index");
    final int dim = data.length;
    ArgumentChecker.isTrue(dim == index.length, "index wrong dimension");
    final List<Function1D<Double, Double>> funcs = new ArrayList<>(dim);
    for (int i = 0; i < dim; i++) {
      funcs.add(generate(data[i], index[i]));
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

  private Function1D<Double, Double> generate(final double[] knots, final int degree, final int index) {

    if (degree == 0) {
      return new Function1D<Double, Double>() {
        private final double _l = knots[index];
        private final double _h = knots[index + 1];

        @Override
        public Double evaluate(final Double x) {
          return (x >= _l && x < _h) ? 1.0 : 0.0;
        }
      };
    }

    if (degree == 1) {
      return new Function1D<Double, Double>() {
        private final double _l = knots[index];
        private final double _m = knots[index + 1];
        private final double _h = knots[index + 2];
        private final double _inv1 = 1.0 / (knots[index + 1] - knots[index]);
        private final double _inv2 = 1.0 / (knots[index + 2] - knots[index + 1]);

        @Override
        public Double evaluate(final Double x) {
          return (x <= _l || x >= _h) ? 0.0 : (x <= _m ? (x - _l) * _inv1 : (_h - x) * _inv2);
        }
      };
    }

    // for degree > 1 use the recursion
    return new Function1D<Double, Double>() {
      private final Function1D<Double, Double> _fa = generate(knots, degree - 1, index);
      private final Function1D<Double, Double> _fb = generate(knots, degree - 1, index + 1);
      private final double _inv1 = 1.0 / (knots[index + degree] - knots[index]);
      private final double _inv2 = 1.0 / (knots[index + degree + 1] - knots[index + 1]);
      private final double _xa = knots[index];
      private final double _xb = knots[index + degree + 1];

      @Override
      public Double evaluate(final Double x) {
        return (x - _xa) * _inv1 * _fa.evaluate(x) + (_xb - x) * _inv2 * _fb.evaluate(x);
      }

    };

  }

  /**
   * Generate a set of b-splines with a given polynomial degree on the specified knots
   * @param knots holder for the knots and degree
   * @return a List of functions
   */
  public List<Function1D<Double, Double>> generateSetNew(BasisFunctionKnots knots) {
    ArgumentChecker.notNull(knots, "knots");

    double[] k = knots.getKnots();
    List<Function1D<Double, Double>> set = null;
    for (int d = 0; d <= knots.getDegree(); d++) {
      set = generateSetUp(k, d , set);
    }
    return set;
  }

  private List<Function1D<Double, Double>> generateSetUp(final double[] knots, final int degree, final List<Function1D<Double, Double>> degreeM1Set) {

    int nSplines = knots.length - degree - 1;
    final List<Function1D<Double, Double>> functions = new ArrayList<>(nSplines);
    for (int i = 0; i < nSplines; i++) {
      functions.add(generate(knots, degree, i, degreeM1Set));
    }
    return functions;
  }

  private Function1D<Double, Double> generate(final double[] knots, final int degree, final int index, final List<Function1D<Double, Double>> degreeM1Set) {

    if (degree == 0) {
      return new Function1D<Double, Double>() {
        private final double _l = knots[index];
        private final double _h = knots[index + 1];

        @Override
        public Double evaluate(final Double x) {
          return (x >= _l && x < _h) ? 1.0 : 0.0;
        }
      };
    }

    if (degree == 1) {
      return new Function1D<Double, Double>() {
        private final double _l = knots[index];
        private final double _m = knots[index + 1];
        private final double _h = knots[index + 2];
        private final double _inv1 = 1.0 / (knots[index + 1] - knots[index]);
        private final double _inv2 = 1.0 / (knots[index + 2] - knots[index + 1]);

        @Override
        public Double evaluate(final Double x) {
          return (x <= _l || x >= _h) ? 0.0 : (x <= _m ? (x - _l) * _inv1 : (_h - x) * _inv2);
        }
      };
    }

    return new Function1D<Double, Double>() {
      private final Function1D<Double, Double> _fa = degreeM1Set.get(index);
      private final Function1D<Double, Double> _fb = degreeM1Set.get(index + 1);
      private final double _inv1 = 1.0 / (knots[index + degree] - knots[index]);
      private final double _inv2 = 1.0 / (knots[index + degree + 1] - knots[index + 1]);
      private final double _xa = knots[index];
      private final double _xb = knots[index + degree + 1];

      @Override
      public Double evaluate(final Double x) {
        return (x - _xa) * _inv1 * _fa.evaluate(x) + (_xb - x) * _inv2 * _fb.evaluate(x);
      }

    };
  }

}
