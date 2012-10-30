/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.slatec.fnlib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.ogblas.auxiliary.D1MACH;

/**
 * Provides ln(1+X) accurate in the sense of relative error.
 * This code is an approximate translation of the equivalent function in the "Public Domain" code from SLATEC, see:
 * http://www.netlib.org/slatec/fnlib/dlnrel.f
 */
public class DLNREL {

  private static final double[] s_dALNRCS = new double[43];
  private static double s_dXMIN;
  private static int s_dNLNREL;
  private static Logger s_log;
  static {
    s_dALNRCS[0] = +.10378693562743769800686267719098e+1;
    s_dALNRCS[1] = -.13364301504908918098766041553133e+0;
    s_dALNRCS[2] = +.19408249135520563357926199374750e-1;
    s_dALNRCS[3] = -.30107551127535777690376537776592e-2;
    s_dALNRCS[4] = +.48694614797154850090456366509137e-3;
    s_dALNRCS[5] = -.81054881893175356066809943008622e-4;
    s_dALNRCS[6] = +.13778847799559524782938251496059e-4;
    s_dALNRCS[7] = -.23802210894358970251369992914935e-5;
    s_dALNRCS[8] = +.41640416213865183476391859901989e-6;
    s_dALNRCS[9] = -.73595828378075994984266837031998e-7;
    s_dALNRCS[10] = +.13117611876241674949152294345011e-7;
    s_dALNRCS[11] = -.23546709317742425136696092330175e-8;
    s_dALNRCS[12] = +.42522773276034997775638052962567e-9;
    s_dALNRCS[13] = -.77190894134840796826108107493300e-10;
    s_dALNRCS[14] = +.14075746481359069909215356472191e-10;
    s_dALNRCS[15] = -.25769072058024680627537078627584e-11;
    s_dALNRCS[16] = +.47342406666294421849154395005938e-12;
    s_dALNRCS[17] = -.87249012674742641745301263292675e-13;
    s_dALNRCS[18] = +.16124614902740551465739833119115e-13;
    s_dALNRCS[19] = -.29875652015665773006710792416815e-14;
    s_dALNRCS[20] = +.55480701209082887983041321697279e-15;
    s_dALNRCS[21] = -.10324619158271569595141333961932e-15;
    s_dALNRCS[22] = +.19250239203049851177878503244868e-16;
    s_dALNRCS[23] = -.35955073465265150011189707844266e-17;
    s_dALNRCS[24] = +.67264542537876857892194574226773e-18;
    s_dALNRCS[25] = -.12602624168735219252082425637546e-18;
    s_dALNRCS[26] = +.23644884408606210044916158955519e-19;
    s_dALNRCS[27] = -.44419377050807936898878389179733e-20;
    s_dALNRCS[28] = +.83546594464034259016241293994666e-21;
    s_dALNRCS[29] = -.15731559416479562574899253521066e-21;
    s_dALNRCS[30] = +.29653128740247422686154369706666e-22;
    s_dALNRCS[31] = -.55949583481815947292156013226666e-23;
    s_dALNRCS[32] = +.10566354268835681048187284138666e-23;
    s_dALNRCS[33] = -.19972483680670204548314999466666e-24;
    s_dALNRCS[34] = +.37782977818839361421049855999999e-25;
    s_dALNRCS[35] = -.71531586889081740345038165333333e-26;
    s_dALNRCS[36] = +.13552488463674213646502024533333e-26;
    s_dALNRCS[37] = -.25694673048487567430079829333333e-27;
    s_dALNRCS[38] = +.48747756066216949076459519999999e-28;
    s_dALNRCS[39] = -.92542112530849715321132373333333e-29;
    s_dALNRCS[40] = +.17578597841760239233269760000000e-29;
    s_dALNRCS[41] = -.33410026677731010351377066666666e-30;
    s_dALNRCS[42] = +.63533936180236187354180266666666e-31;
    s_dNLNREL = INITDS.initds(s_dALNRCS, 43, 0.1d * (float) (D1MACH.three()));
    s_dXMIN = -1.0d + Math.sqrt(D1MACH.four());
    s_log = LoggerFactory.getLogger(DLNREL.class);
  }

  public static double dlnrel(double x) {

    double DLNREL; //CSIGNORE
    Catchers.catchValueShouldBeGreaterThanXFromArgList(x, -1, 1);
    if (x < s_dXMIN) {
      s_log.warn("Answer is less than half precision because X is too near -1");
    }

    if (Math.abs(x) <= 0.375d) {
      DLNREL = x * (1.d - x * DCSEVL.dcsevl(x / 0.375d, s_dALNRCS, s_dNLNREL));
    } else {
      DLNREL = Math.log(1.d + x);
    }
    return DLNREL;

  }
}
