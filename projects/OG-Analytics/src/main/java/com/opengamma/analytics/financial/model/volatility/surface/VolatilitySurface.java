/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.model.volatility.surface;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.opengamma.analytics.financial.model.volatility.VolatilityModel;
import com.opengamma.analytics.financial.model.volatility.curve.VolatilityCurve;
import com.opengamma.analytics.math.Axis;
import com.opengamma.analytics.math.curve.Curve;
import com.opengamma.analytics.math.interpolation.Interpolator1D;
import com.opengamma.analytics.math.surface.Surface;
import com.opengamma.analytics.math.surface.SurfaceShiftFunctionFactory;
import com.opengamma.analytics.math.surface.SurfaceSliceFunction;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.time.Tenor;
import com.opengamma.util.tuple.DoublesPair;

/**
 * 
 */
public class VolatilitySurface implements VolatilityModel<DoublesPair> {
  private final Surface<Double, Double, Double> _surface;
  /** x-axis */
  public static final Axis EXPIRY_AXIS = Axis.X; // TODO Review
  /** y-axis */
  public static final Axis STRIKE_AXIS = Axis.Y;

  /**
   * Map of tenor to the index of the corresponding point on the x axis.
   * This isn't guaranteed to be populated for all surfaces.
   */
  private final Map<Tenor, Integer> _expiryIndices;

  /**
   * Creates a new volatility surface backed by the specified surface.
   *
   * @param surface  a surface containing the volatility data with expiries on the x-axis, strikes on the y-axis
   */
  public VolatilitySurface(Surface<Double, Double, Double> surface) {
    _surface = ArgumentChecker.notNull(surface, "surface");
    _expiryIndices = ImmutableMap.of();
  }

  /**
   * Creates a new volatility surface backed by the specified surface and with the specified tenors on the x-axis.
   *
   * @param surface  a surface containing the volatility data with expiries on the x-axis, strikes on the y-axis
   * @param expiryTenors  the tenors corresponding to the points on the x-axis. This must be the same length
   *   as the x-axis data in the surface
   */
  public VolatilitySurface(Surface<Double, Double, Double> surface, List<Tenor> expiryTenors) {
    ArgumentChecker.notNull(surface, "surface");
    ArgumentChecker.notNull(expiryTenors, "expiryTenors");

    if (surface.getXData().length != expiryTenors.size()) {
      throw new IllegalArgumentException("Surface x-axis length must equal the number of expiry tenors");
    }
    ImmutableMap.Builder<Tenor, Integer> indexBuilder = ImmutableMap.builder();

    for (int i = 0; i < expiryTenors.size(); i++) {
      indexBuilder.put(expiryTenors.get(i), i);
    }
    _expiryIndices = indexBuilder.build();
    _surface = surface;
  }

  /**
   * Returns the year fraction of the surface x-axis point corresponding to the expiry.
   * <p>
   * {@code Optional.absent()} will be returned if there is no point matching the specified expiry or
   * if this surface doesn't contain expiry data.
   *
   * @param expiry  an expiry
   * @return the year fraction of the surface x-axis point corresponding to the expiry if there is one
   */
  public Optional<Double> getExpiryYearFraction(Tenor expiry) {
    Integer index = _expiryIndices.get(expiry);

    if (index == null) {
      return Optional.absent();
    } else {
      return Optional.of(_surface.getXData()[index]);
    }
  }

  @Override
  public Double getVolatility(final DoublesPair xy) {
    ArgumentChecker.notNull(xy, "xy pair");
    return _surface.getZValue(xy);
  }

  /**
   * Return a volatility for the expiry, strike pair provided.
   * Interpolation/extrapolation behaviour depends on underlying surface
   * @param t time to maturity
   * @param k strike
   * @return The Black (implied) volatility
   */
  public double getVolatility(final double t, final double k) {
    final DoublesPair temp = DoublesPair.of(t, k);
    return getVolatility(temp);
  }


  public VolatilityCurve getSlice(final Axis axis, final double here, final Interpolator1D interpolator) {
    final Curve<Double, Double> curve = SurfaceSliceFunction.cut(_surface, axis, here, interpolator);
    return new VolatilityCurve(curve);
  }

  public Surface<Double, Double, Double> getSurface() {
    return _surface;
  }

  public VolatilitySurface withParallelShift(final double shift) {
    return new VolatilitySurface(getParallelShiftedSurface(shift));
  }

  public VolatilitySurface withSingleAdditiveShift(final double x, final double y, final double shift) {
    return new VolatilitySurface(getSingleAdditiveShiftSurface(x, y, shift));
  }

  public VolatilitySurface withMultipleAdditiveShifts(final double[] x, final double[] y, final double[] shifts) {
    return new VolatilitySurface(getMultipleAdditiveShiftsSurface(x, y, shifts));
  }

  public VolatilitySurface withConstantMultiplicativeShift(final double shift) {
    return new VolatilitySurface(getConstantMultiplicativeShiftSurface(shift));
  }

  public VolatilitySurface withSingleMultiplicativeShift(final double x, final double y, final double shift) {
    return new VolatilitySurface(getSingleMultiplicativeShiftSurface(x, y, shift));
  }

  public VolatilitySurface withMultipleMultiplicativeShifts(final double[] x, final double[] y, final double[] shifts) {
    return new VolatilitySurface(getMultipleMultiplicativeShiftsSurface(x, y, shifts));
  }

  protected Surface<Double, Double, Double> getParallelShiftedSurface(final double shift) {
    return SurfaceShiftFunctionFactory.getShiftedSurface(_surface, shift, true);
  }

  protected Surface<Double, Double, Double> getSingleAdditiveShiftSurface(final double x, final double y, final double shift) {
    return SurfaceShiftFunctionFactory.getShiftedSurface(_surface, x, y, shift, true);
  }

  protected Surface<Double, Double, Double> getMultipleAdditiveShiftsSurface(final double[] x, final double[] y, final double[] shifts) {
    return SurfaceShiftFunctionFactory.getShiftedSurface(_surface, x, y, shifts, true);
  }

  protected Surface<Double, Double, Double> getConstantMultiplicativeShiftSurface(final double shift) {
    return SurfaceShiftFunctionFactory.getShiftedSurface(_surface, shift, false);
  }

  protected Surface<Double, Double, Double> getSingleMultiplicativeShiftSurface(final double x, final double y, final double shift) {
    return SurfaceShiftFunctionFactory.getShiftedSurface(_surface, x, y, shift, false);
  }

  protected Surface<Double, Double, Double> getMultipleMultiplicativeShiftsSurface(final double[] x, final double[] y, final double[] shifts) {
    return SurfaceShiftFunctionFactory.getShiftedSurface(_surface, x, y, shifts, false);
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + _surface.hashCode();
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final VolatilitySurface other = (VolatilitySurface) obj;
    return ObjectUtils.equals(_surface, other._surface);
  }
}
