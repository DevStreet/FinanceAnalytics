/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.math.interpolation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import com.google.common.collect.Iterators;
import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.analytics.financial.credit.cds.ISDAExtrapolator1D;
import com.opengamma.analytics.financial.credit.cds.ISDAInterpolator1D;
import com.opengamma.util.ArgumentChecker;

/**
 * 
 */
public final class Interpolator1DFactory {
  
  /** Linear */
  public static final String LINEAR = "Linear";
  /** Exponential */
  public static final String EXPONENTIAL = "Exponential";
  /** Log-linear */
  public static final String LOG_LINEAR = "LogLinear";
  /** Natural cubic spline */
  public static final String NATURAL_CUBIC_SPLINE = "NaturalCubicSpline";
  /** Barycentric rational function */
  public static final String BARYCENTRIC_RATIONAL_FUNCTION = "BarycentricRationalFunction";
  /** Polynomial */
  public static final String POLYNOMIAL = "Polynomial";
  /** Rational function */
  public static final String RATIONAL_FUNCTION = "RationalFunction";
  /** Step */
  public static final String STEP = "Step";
  /** Step with the value in the interval equal to the value at the upper bound */
  public static final String STEP_UPPER = "StepUpper";
  /** Double quadratic */
  public static final String DOUBLE_QUADRATIC = "DoubleQuadratic";
  /**Monotonicity-Preserving-Cubic-Spline
   * @deprecated Use the name PCHIP instead 
   * */
  @Deprecated
  public static final String MONOTONIC_CUBIC = "MonotonicityPreservingCubicSpline";
  /**Piecewise Cubic Hermite Interpolating Polynomial (PCHIP)*/
  public static final String PCHIP = "PCHIP";
  /**Modified Piecewise Cubic Hermite Interpolating Polynomial (PCHIP) for yield curves*/
  public static final String MOD_PCHIP = "ModifiedPCHIP";
  /** Time square */
  public static final String TIME_SQUARE = "TimeSquare";
  /** Flat extrapolator */
  public static final String FLAT_EXTRAPOLATOR = "FlatExtrapolator";
  /** Linear extrapolator */
  public static final String LINEAR_EXTRAPOLATOR = "LinearExtrapolator";
  /** Linear extrapolator */
  public static final String EXPONENTIAL_EXTRAPOLATOR = "ExponentialExtrapolator";
  /** ISDA interpolator */
  public static final String ISDA_INTERPOLATOR = "ISDAInterpolator";
  /** ISDA extrapolator */
  public static final String ISDA_EXTRAPOLATOR = "ISDAExtrapolator";
  /** Linear instance */
  public static final LinearInterpolator1D LINEAR_INSTANCE = new LinearInterpolator1D();
  /** Exponential instance */
  public static final ExponentialInterpolator1D EXPONENTIAL_INSTANCE = new ExponentialInterpolator1D();
  /** Log-linear instance */
  public static final LogLinearInterpolator1D LOG_LINEAR_INSTANCE = new LogLinearInterpolator1D();
  /** Natural cubic spline instance */
  public static final NaturalCubicSplineInterpolator1D NATURAL_CUBIC_SPLINE_INSTANCE = new NaturalCubicSplineInterpolator1D();
  /** Step instance */
  public static final StepInterpolator1D STEP_INSTANCE = new StepInterpolator1D();
  /** Step-Upper instance */
  public static final StepUpperInterpolator1D STEP_UPPER_INSTANCE = new StepUpperInterpolator1D();
  /** Double quadratic instance */
  public static final DoubleQuadraticInterpolator1D DOUBLE_QUADRATIC_INSTANCE = new DoubleQuadraticInterpolator1D();
  /** MonotonicityPreservingCubicSpline
   * @deprecated use PCHIP_INSTANCE instead 
   * */
  @Deprecated
  public static final PCHIPInterpolator1D MONOTONIC_CUBIC_INSTANCE = new PCHIPInterpolator1D();
  /**Piecewise Cubic Hermite Interpolating Polynomial (PCHIP)*/
  public static final PCHIPInterpolator1D PCHIP_INSTANCE = new PCHIPInterpolator1D();
  /**Modified Piecewise Cubic Hermite Interpolating Polynomial (PCHIP) for yield curves*/
  public static final PCHIPYieldCurveInterpolator1D MOD_PCHIP_INSTANCE = new PCHIPYieldCurveInterpolator1D();
  /** Time square instance */
  public static final TimeSquareInterpolator1D TIME_SQUARE_INSTANCE = new TimeSquareInterpolator1D();
  /** Flat extrapolator instance */
  public static final FlatExtrapolator1D FLAT_EXTRAPOLATOR_INSTANCE = new FlatExtrapolator1D();
  /** Exponential extrapolator instance */
  public static final ExponentialExtrapolator1D EXPONENTIAL_EXTRAPOLATOR_INSTANCE = new ExponentialExtrapolator1D();
  /** ISDA interpolator instance */
  public static final ISDAInterpolator1D ISDA_INTERPOLATOR_INSTANCE = new ISDAInterpolator1D();
  /** ISDA extrapolator instance */
  public static final ISDAExtrapolator1D ISDA_EXTRAPOLATOR_INSTANCE = new ISDAExtrapolator1D();
  /** 
   */

  /**Cubic spline with clamped endpoint conditions*/
  public static final String CLAMPED_CUBIC = "ClampedCubicSpline";
  /**Instance of cubic spline with clamped endpoint conditions*/
  public static final ClampedCubicSplineInterpolator1D CLAMPED_CUBIC_INSTANCE = new ClampedCubicSplineInterpolator1D();
  /**Cubic spline with clamped endpoint conditions and monotonicity filter*/
  public static final String CLAMPED_CUBIC_MONOTONE = "ClampedCubicSplineWithMonotonicity";
  /**Instance of cubic spline with clamped endpoint conditions and monotonicity filter*/
  public static final MonotonicityPreservingCubicSplineInterpolator1D CLAMPED_CUBIC_MONOTONE_INSTANCE = new MonotonicityPreservingCubicSplineInterpolator1D(new CubicSplineInterpolator());
  /**Cubic spline with clamped endpoint conditions and nonnegativity filter*/
  public static final String CLAMPED_CUBIC_NONNEGATIVE = "ClampedCubicSplineWithNonnegativity";
  /**Instance of cubic spline with clamped endpoint conditions and nonnegativity filter*/
  public static final NonnegativityPreservingCubicSplineInterpolator1D CLAMPED_CUBIC_NONNEGATIVE_INSTANCE = new NonnegativityPreservingCubicSplineInterpolator1D(new CubicSplineInterpolator());
  /**Quintic spline with clamped endpoint conditions and monotonicity filter*/
  public static final String CLAMPED_QUINTIC_MONOTONE = "ClampedQuinticSplineWithMonotonicity";
  /**Instance of quintic spline with clamped endpoint conditions and monotonicity filter*/
  public static final MonotonicityPreservingQuinticSplineInterpolator1D CLAMPED_QUINTIC_MONOTONE_INSTANCE = new MonotonicityPreservingQuinticSplineInterpolator1D(new CubicSplineInterpolator());
  /**Quintic spline with clamped endpoint conditions and nonnegativity filter*/
  public static final String CLAMPED_QUINTIC_NONNEGATIVE = "ClampedQuinticSplineWithNonnegativity";
  /**Instance of quintic spline with clamped endpoint conditions nonnegativity filter*/
  public static final NonnegativityPreservingQuinticSplineInterpolator1D CLAMPED_QUINTIC_NONNEGATIVE_INSTANCE = new NonnegativityPreservingQuinticSplineInterpolator1D(new CubicSplineInterpolator());

  /**Cubic spline with natural endpoint conditions and monotonicity filter*/
  public static final String NATURAL_CUBIC_MONOTONE = "NaturalCubicSplineWithMonotonicity";
  /**Instance of cubic spline with clamped endpoint conditions and monotonicity filter*/
  public static final MonotonicityPreservingCubicSplineInterpolator1D NATURAL_CUBIC_MONOTONE_INSTANCE = new MonotonicityPreservingCubicSplineInterpolator1D(new NaturalSplineInterpolator());
  /**Cubic spline with natural endpoint conditions and nonnegativity filter*/
  public static final String NATURAL_CUBIC_NONNEGATIVE = "NaturalCubicSplineWithNonnegativity";
  /**Instance of cubic spline with clamped endpoint conditions and nonnegativity filter*/
  public static final NonnegativityPreservingCubicSplineInterpolator1D NATURAL_CUBIC_NONNEGATIVE_INSTANCE = new NonnegativityPreservingCubicSplineInterpolator1D(new NaturalSplineInterpolator());
  /**Quintic spline with natural endpoint conditions and monotonicity filter*/
  public static final String NATURAL_QUINTIC_MONOTONE = "NaturalQuinticSplineWithMonotonicity";
  /**Instance of quintic spline with clamped endpoint conditions and monotonicity filter*/
  public static final MonotonicityPreservingQuinticSplineInterpolator1D NATURAL_QUINTIC_MONOTONE_INSTANCE = new MonotonicityPreservingQuinticSplineInterpolator1D(new NaturalSplineInterpolator());
  /**Quintic spline with natural endpoint conditions and nonnegativity filter*/
  public static final String NATURAL_QUINTIC_NONNEGATIVE = "NaturalQuinticSplineWithNonnegativity";
  /**Instance of quintic spline with clamped endpoint conditions and nonnegativity filter*/
  public static final NonnegativityPreservingQuinticSplineInterpolator1D NATURAL_QUINTIC_NONNEGATIVE_INSTANCE = new NonnegativityPreservingQuinticSplineInterpolator1D(new NaturalSplineInterpolator());

  /**Cubic spline with not-a-knot endpoint conditions*/
  public static final String NOTAKNOT_CUBIC = "NotAKnotCubicSpline";
  /**Instance of cubic spline with not-a-knot endpoint conditions*/
  public static final NotAKnotCubicSplineInterpolator1D NOTAKNOT_CUBIC_INSTANCE = new NotAKnotCubicSplineInterpolator1D();
  /**Cubic spline with not-a-knot endpoint conditions and monotonicity filter*/
  public static final String NOTAKNOT_CUBIC_MONOTONE = "NotAKnotCubicSplineWithMonotonicity";
  /**Instance of quintic spline with not-a-knot endpoint conditions and monotonicity filter*/
  public static final MonotonicityPreservingCubicSplineInterpolator1D NOTAKNOT_CUBIC_MONOTONE_INSTANCE = new MonotonicityPreservingCubicSplineInterpolator1D(new CubicSplineInterpolator());
  /**Cubic spline with not-a-knot endpoint conditions and nonnegativity filter*/
  public static final String NOTAKNOT_CUBIC_NONNEGATIVE = "NotAKnotCubicSplineWithNonnegativity";
  /**Instance of quintic spline with not-a-knot endpoint conditions and nonnegativity filter*/
  public static final NonnegativityPreservingCubicSplineInterpolator1D NOTAKNOT_CUBIC_NONNEGATIVE_INSTANCE = new NonnegativityPreservingCubicSplineInterpolator1D(new CubicSplineInterpolator());
  /**Quintic spline with not-a-knot endpoint conditions and monotonicity filter*/
  public static final String NOTAKNOT_QUINTIC_MONOTONE = "NotAKnotQuinticSplineWithMonotonicity";
  /**Instance of quintic spline with not-a-knot endpoint conditions and monotonicity filter*/
  public static final MonotonicityPreservingQuinticSplineInterpolator1D NOTAKNOT_QUINTIC_MONOTONE_INSTANCE = new MonotonicityPreservingQuinticSplineInterpolator1D(new CubicSplineInterpolator());
  /**Quintic spline with not-a-knot endpoint conditions and nonnegativity filter*/
  public static final String NOTAKNOT_QUINTIC_NONNEGATIVE = "NotAKnotQuinticSplineWithNonnegativity";
  /**Instance of quintic spline with not-a-knot endpoint conditions and nonnegativity filter*/
  public static final NonnegativityPreservingQuinticSplineInterpolator1D NOTAKNOT_QUINTIC_NONNEGATIVE_INSTANCE = new NonnegativityPreservingQuinticSplineInterpolator1D(new CubicSplineInterpolator());

  /**Constrained cubic interpolation*/
  public static final String CONSTRAINED_CUBIC = "ConstrainedCubicSpline";
  /**Instance of constrained cubic interpolation*/
  public static final ConstrainedCubicSplineInterpolator1D CONSTRAINED_CUBIC_INSTANCE = new ConstrainedCubicSplineInterpolator1D();
  /**Constrained cubic interpolation with monotonicity filter*/
  public static final String CONSTRAINED_CUBIC_MONOTONE = "ConstrainedCubicSplineWithMonotonicity";
  /**Instance of constrained cubic interpolation with monotonicity filter*/
  public static final MonotonicityPreservingCubicSplineInterpolator1D CONSTRAINED_CUBIC_MONOTONE_INSTANCE = new MonotonicityPreservingCubicSplineInterpolator1D(
      new ConstrainedCubicSplineInterpolator());
  /**Constrained cubic interpolation with nonnegativity filter*/
  public static final String CONSTRAINED_CUBIC_NONNEGATIVE = "ConstrainedCubicSplineWithNonnegativity";
  /**Instance of constrained cubic interpolation with nonnegativity filter*/
  public static final NonnegativityPreservingCubicSplineInterpolator1D CONSTRAINED_CUBIC_NONNEGATIVE_INSTANCE = new NonnegativityPreservingCubicSplineInterpolator1D(
      new ConstrainedCubicSplineInterpolator());

  /**Akima cubic interpolation*/
  public static final String AKIMA_CUBIC = "AkimaCubicSpline";
  /**Instance of Akima cubic interpolation*/
  public static final SemiLocalCubicSplineInterpolator1D AKIMA_CUBIC_INSTANCE = new SemiLocalCubicSplineInterpolator1D();
  /**Akima cubic interpolation with monotonicity filter*/
  public static final String AKIMA_CUBIC_MONOTONE = "AkimaCubicSplineWithMonotonicity";
  /**Instance of Akima cubic interpolation with monotonicity filter*/
  public static final MonotonicityPreservingCubicSplineInterpolator1D AKIMA_CUBIC_MONOTONE_INSTANCE = new MonotonicityPreservingCubicSplineInterpolator1D(new SemiLocalCubicSplineInterpolator());
  /**Akima cubic interpolation with nonnegativity filter*/
  public static final String AKIMA_CUBIC_NONNEGATIVE = "AkimaCubicSplineWithNonnegativity";
  /**Instance of Akima cubic interpolation with nonnegativity filter*/
  public static final NonnegativityPreservingCubicSplineInterpolator1D AKIMA_CUBIC_NONNEGATIVE_INSTANCE = new NonnegativityPreservingCubicSplineInterpolator1D(new SemiLocalCubicSplineInterpolator());

  /**Monotone convex cubic interpolation*/
  public static final String MONOTONE_CONVEX_CUBIC = "MonotoneConvexCubicSpline";
  /**Instance of monotone convex cubic interpolation*/
  public static final MonotoneConvexSplineInterpolator1D MONOTONE_CONVEX_CUBIC_INSTANCE = new MonotoneConvexSplineInterpolator1D();

  /**C2 shape preserving cubic interpolation*/
  public static final String C2_SHAPE_PRESERVING_CUBIC = "C2ShapePreservingCubicSpline";
  /**Instance of C2 shape preserving cubic interpolation*/
  public static final ShapePreservingCubicSplineInterpolator1D C2_SHAPE_PRESERVING_CUBIC_INSTANCE = new ShapePreservingCubicSplineInterpolator1D();

  /**Log natural cubic interpolation with monotonicity filter*/
  public static final String LOG_NATURAL_CUBIC_MONOTONE = "LogNaturalCubicWithMonotonicity";
  /**Instance of log natural cubic interpolation with monotonicity filter*/
  public static final LogNaturalCubicMonotonicityPreservingInterpolator1D LOG_NATURAL_CUBIC_MONOTONE_INSTANCE = new LogNaturalCubicMonotonicityPreservingInterpolator1D();
  
  /**
   * Singleton instance.
   */
  public static final Interpolator1DFactory INSTANCE = new Interpolator1DFactory();
  
  /**
   * Map of interpolator name to interpolator.
   */
  private final Map<String, Interpolator1D> _staticInstances = new HashMap<>();

  //-------------------------------------------------------------------------
  /**
   * Creates the factory
   */
  private Interpolator1DFactory() {
    final ResourceBundle interpolators = ResourceBundle.getBundle(Interpolator1D.class.getName());
    final Map<String, Interpolator1D> tmpNameInstanceMap = new HashMap<>();
    for (final String interpolatorName : interpolators.keySet()) {
      final String clazz = interpolators.getString(interpolatorName);
      Interpolator1D instance = tmpNameInstanceMap.get(clazz);
      if (instance == null) {
        try {
          instance = (Interpolator1D) Class.forName(clazz).newInstance();
          tmpNameInstanceMap.put(clazz, instance);
        } catch (InstantiationException ex) {
          throw new OpenGammaRuntimeException("Error initialising Interpolator1D conventions", ex);
        } catch (IllegalAccessException ex) {
          throw new OpenGammaRuntimeException("Error initialising Interpolator1D conventions", ex);
        } catch (ClassNotFoundException ex) {
          throw new OpenGammaRuntimeException("Error initialising Interpolator1D conventions", ex);
        }
      }
      _staticInstances.put(interpolatorName, instance);
    }
    // TODO these override the parsed interpolators - need to convert these to have default constructors
    _staticInstances.put(CLAMPED_CUBIC_MONOTONE, CLAMPED_CUBIC_MONOTONE_INSTANCE);
    _staticInstances.put(CLAMPED_CUBIC_NONNEGATIVE, CLAMPED_CUBIC_NONNEGATIVE_INSTANCE);
    _staticInstances.put(CLAMPED_QUINTIC_MONOTONE, CLAMPED_QUINTIC_MONOTONE_INSTANCE);
    _staticInstances.put(CLAMPED_QUINTIC_NONNEGATIVE, CLAMPED_QUINTIC_NONNEGATIVE_INSTANCE);
    _staticInstances.put(NOTAKNOT_CUBIC_MONOTONE, NOTAKNOT_CUBIC_MONOTONE_INSTANCE);
    _staticInstances.put(NOTAKNOT_CUBIC_NONNEGATIVE, NOTAKNOT_CUBIC_NONNEGATIVE_INSTANCE);
    _staticInstances.put(NOTAKNOT_QUINTIC_MONOTONE, NOTAKNOT_QUINTIC_MONOTONE_INSTANCE);
    _staticInstances.put(NOTAKNOT_QUINTIC_NONNEGATIVE, NOTAKNOT_QUINTIC_NONNEGATIVE_INSTANCE);
    _staticInstances.put(CONSTRAINED_CUBIC_MONOTONE, CONSTRAINED_CUBIC_MONOTONE_INSTANCE);
    _staticInstances.put(CONSTRAINED_CUBIC_NONNEGATIVE, CONSTRAINED_CUBIC_NONNEGATIVE_INSTANCE);
  }

  //-------------------------------------------------------------------------
  /**
   * Gets an interpolator by name.
   * Matching is case insensitive.
   * 
   * @param name  the name, not null
   * @return the convention, not null
   * @throws IllegalArgumentException if not found
   */
  public static Interpolator1D of(final String name) {
    ArgumentChecker.notNull(name, "name");
    Interpolator1D result = Interpolator1DFactory.INSTANCE.getInterpolator1D(name);
    if (result == null) {
      throw new IllegalArgumentException("Unknown Interpolator: " + name);
    }
    return result;
  }
  
  private Interpolator1D getInterpolator1D(String name) {
    return _staticInstances.get(name);
  }

  // -------------------------------------------------------------------------
  /**
   * Gets an interpolator by name.
   * Matching is case insensitive.
   * 
   * @param name  the name, not null
   * @return the interpolator, null if not found
   */
  public static Interpolator1D getInterpolator(final String name) {
    ArgumentChecker.notNull(name, "interpolatorName");
    final Interpolator1D interpolator = INSTANCE.getInterpolator1D(name);
    if (interpolator != null) {
      return interpolator;
    }
    // TODO kirk 2009-12-30 -- Deal with degree for Barycentric, Polynomial, and
    // RationalFunction
    throw new IllegalArgumentException("Interpolator not handled: " + name);
  }

  /**
   * Gets the name for an interpolator.
   * Matching is case insensitive.
   * 
   * @param interpolator  the interpolator, not null
   * @return the name, null if not found
   */
  public static String getInterpolatorName(final Interpolator1D interpolator) {
    ArgumentChecker.notNull(interpolator, "interpolator");
    if (interpolator == null) {
      return null;
    }
    String interpolatorName = null;
    for (Map.Entry<String, Interpolator1D> entry: INSTANCE._staticInstances.entrySet()) {
      if (interpolator.equals(entry.getValue())) {
        interpolatorName = entry.getKey();
      }
    }
    // TODO kirk 2010-03-31 -- Deal with the more complicated rules for
    // Barycentric, Polynomial, and RationalFunction.
    if (interpolator instanceof LinearExtrapolator1D) {
      return LINEAR_EXTRAPOLATOR;
    }
    return interpolatorName;
  }

  /**
   * Iterates over the available interpolators. No particular ordering is specified and interpolators may
   * exist in the system not provided by this factory that aren't included as part of this enumeration.
   * 
   * @return the available interpolators, not null
   */
  public Iterator<Interpolator1D> enumerateAvailableInterpolators() {
    return Iterators.unmodifiableIterator(_staticInstances.values().iterator());
  }
}
