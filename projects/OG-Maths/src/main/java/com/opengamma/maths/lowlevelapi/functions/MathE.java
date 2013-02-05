/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.functions;

/**
 * Provides some of the functions missing from java.lang.Math.
 */
public final class MathE {

  public static double modf(double val, double[] intv) {
    if (Double.isNaN(val)) {
      intv[0] = val;
      return val;
    } else if (Double.isInfinite(val)) { // +/-inf
      intv[0] = val;
      return Math.copySign(0, val);
    } else {
      intv[0] = trunc(val);
      return val - intv[0];
    }

  }

  public static double trunc(double val) {
    long valL = Double.doubleToRawLongBits(val);
    // IEEE double has from MSB: 1 bit for sign, 11 bits for exponent, 52 for significand

    // first get the high 32 bits, shift off low 32
    int hi = ((int) (valL >> 0x20));
    hi &= 0x00000000ffffffffL; 

    // get the sign bit, before masking off the 11 exponent bits, will need for return if not NaN/Inf.
    int signbit = hi & 0x80000000;

    // get the exponent and mask off everything else
    int expnt = (hi >> 0x14) & 0x000007ff;

    // if nan/inf return itself, there's no signalling :(
    if (expnt == 0x000007ff) {
      return val;
    }

    // we have a normal number, seek true magnitude of the exponent
    expnt -= 0x000003ff; // take off exponent bias (1023_dec)

    if (expnt < 0x0L) { // if unbiased expnt < 0L, i.e. is -ve, return signed zero 
      return Double.longBitsToDouble((0x00000000ffffffffL & signbit) << 0x34); // shift the sign bit into 0x0, long cast sign extension not possible
    } else {
      long signpart = ((signbit & 0x80000000) << 0x34);
      long valuepart;
      // reassemble number but truncate as we go, exponent tells us how much to shift
      if (expnt < 0x14) { // we need the high half only, reassemble number and truncate
        // chop off the powers which are identified as fractional by the mag of the exponent,
        // shift a full mask left by 20 (fractional bits in hi) subtract the exponent, 
        // shift the whole lot up to the high 32 bits after removing possible sign extension on widening type 
        valuepart = ((0x00000000ffffffffL & (hi & ((0xffffffff) << 0x14 - expnt))) << 0x20);
        return Double.longBitsToDouble(signpart | valuepart);
      } else if (expnt < 0x34) { // this branch indicates the magnitude of the exponent will effect the significand in the low 32 bits 
        // stick back in the high part
        valuepart = (0x00000000ffffffffL & hi) << 0x20;
        // get low 32 bits 
        int lo = (int) (valL & 0x00000000ffffffffL);
        // OR in the low part less the masked off part based on the exponent mag. 0x34 = 0x14 + 0x20;
        valuepart |= ((0x00000000ffffffffL & (lo & ((0xffffffff) << 0x34 - expnt))));
        return Double.longBitsToDouble(signpart | valuepart);
      }
      // val is larger than representable, trunc() will have no effect
      return val;
    }
  }

}
