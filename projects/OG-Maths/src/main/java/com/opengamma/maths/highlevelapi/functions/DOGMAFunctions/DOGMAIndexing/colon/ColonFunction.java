/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAIndexing.colon;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.DOGMAMethodLiteral;
import com.opengamma.maths.dogma.engine.methodhookinstances.arbitrary.Colon;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.lowlevelapi.functions.D1mach;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * For generating vector data
 */
@DOGMAMethodHook(provides = Colon.class)
public class ColonFunction {

  @DOGMAMethodLiteral
  public static OGMatrix colon(Number low, Number step, Number high) {
    Catchers.catchNullFromArgList(low, 1);
    Catchers.catchNullFromArgList(step, 2);
    Catchers.catchNullFromArgList(high, 3);
    Catchers.catchInfOrNaN(high.doubleValue());
    Catchers.catchInfOrNaN(step.doubleValue());
    Catchers.catchInfOrNaN(low.doubleValue());
    double lv = low.doubleValue();
    double sv = step.doubleValue();
    double hv = high.doubleValue();

    if (sv == 0) {
      throw new MathsExceptionIllegalArgument("Cannot construct vector with stepping of zero");
    }
    if (lv > hv && sv > 0) {
      throw new MathsExceptionIllegalArgument("Cannot construct vector starting at " + lv + " with steps of " + sv + " to " + hv);
    }

    if (lv < hv && sv < 0) {
      throw new MathsExceptionIllegalArgument("Cannot construct vector starting at " + lv + " with steps of " + sv + " to " + hv);
    }

    int nvals = (int) ((5 * D1mach.four() + Math.abs(hv - lv)) / Math.abs(sv));
    double[] tmp = new double[nvals + 1];
    tmp[0] = low.doubleValue();
    for (int i = 1; i <= nvals; i++) {
      tmp[i] = low.doubleValue() + step.doubleValue() * i;
    }
    return new OGMatrix(tmp, 1, nvals + 1);
  }

  @DOGMAMethodLiteral
  public static OGMatrix colon(Number low, Number high) {
    Catchers.catchNullFromArgList(low, 1);
    Catchers.catchNullFromArgList(high, 2);
    Double step = 1.d;
    return colon(low, step, high);
  }

}
