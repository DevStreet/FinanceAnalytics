/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.slatec.fnlib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.referenceblas.D1mach;

/**
 * Computes the inverse hyperbolic tangent at position 'x'
 * This code is an approximate translation of the equivalent function in the "Public Domain" code from SLATEC, see:
 * http://www.netlib.org/slatec/fnlib/datanh.f
 */
public class DATANH {

  private static Logger s_log = LoggerFactory.getLogger(DATANH.class);

  //CSOFF // we keep DATA// blocks as written
  private static double[] s_atnhcs = new double[] { //
  +.9439510239319549230842892218633e-1, //
      +.4919843705578615947200034576668e-1, //
      +.2102593522455432763479327331752e-2, //
      +.1073554449776116584640731045276e-3, //
      +.5978267249293031478642787517872e-5, //
      +.3505062030889134845966834886200e-6, //
      +.2126374343765340350896219314431e-7, //
      +.1321694535715527192129801723055e-8, //
      +.8365875501178070364623604052959e-10, //
      +.5370503749311002163881434587772e-11, //
      +.3486659470157107922971245784290e-12, //
      +.2284549509603433015524024119722e-13, //
      +.1508407105944793044874229067558e-14, //
      +.1002418816804109126136995722837e-15, //
      +.6698674738165069539715526882986e-17, //
      +.4497954546494931083083327624533e-18, //
      +.3032954474279453541682367146666e-19, //
      +.2052702064190936826463861418666e-20, //
      +.1393848977053837713193014613333e-21, //
      +.9492580637224576971958954666666e-23, //
      +.6481915448242307604982442666666e-24, //
      +.4436730205723615272632320000000e-25, //
      +.3043465618543161638912000000000e-26, //
      +.2091881298792393474047999999999e-27, //
      +.1440445411234050561365333333333e-28, //
      +.9935374683141640465066666666666e-30, //
      +.6863462444358260053333333333333e-31 };
  //CSON

  private static int s_nterms;
  private static double s_sqeps, s_dxrel;

  static {
    s_nterms = INITDS.initds(s_atnhcs, 27, 0.1d * D1mach.three());
    s_sqeps = Math.sqrt(3.d * D1mach.three());
    s_dxrel = Math.sqrt(D1mach.four());
  }

  public static double datanh(final double x) {
    double y;
    y = Math.abs(x);
    if (y >= 1.d) {
      throw new MathsExceptionIllegalArgument("abs(x)>=1.d, value goes to infinity");
    }
    if (1.d - y < s_dxrel) {
      s_log.warn("Result to less than half precision as abs(x) too near 1.d0");
    }
    if (y > s_sqeps && y <= 0.5d) {
      return x * (1 + DCSEVL.dcsevl(8 * x * x - 1.d, s_atnhcs, s_nterms));
    } else if (y > 0.5d) {
      return 0.5d * (Math.log1p(x) - Math.log1p(-x)); // builtin log1p used
    } else {
      return x;
    }

  }
}
