/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctionCollection;

import com.opengamma.maths.commonapi.properties.DOGMAconfig;
import com.opengamma.maths.highlevelapi.datatypes.derived.OGSvdResult;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMALinearAlgebra.svd.Svd;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMALinearAlgebra.svd.Svd.compute;
import com.opengamma.maths.highlevelapi.functions.DOGMAinterfaces.DOGMALinearAlgebraAPI;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Wiring for the linear algebra operations
 */
public class DOGMALinearAlgebra implements DOGMALinearAlgebraAPI {

  private static final Svd SVD = new Svd();

  @Override
  public OGSvdResult svd(OGArraySuper<Number> array1, compute required) {
    if (DOGMAconfig.getHaltOnNaNOnFunctionEntry()) {
      Catchers.catchNaN(array1);
    }
    if (DOGMAconfig.getHaltOnInfOnFunctionEntry()) {
      Catchers.catchInf(array1);
    }
    OGSvdResult result = SVD.svd(array1, required);

    // test U
    if (required == compute.U || required == compute.US || required == compute.UV || required == compute.USV) {
      OGArraySuper<Number> tmp = result.getU();
      if (DOGMAconfig.getHaltOnNaNOnFunctionExit()) {
        Catchers.catchNaN(tmp);
      }
      if (DOGMAconfig.getHaltOnInfOnFunctionExit()) {
        Catchers.catchInf(tmp);
      }
    }

    // test S
    if (required == compute.S || required == compute.US || required == compute.SV || required == compute.USV) {
      OGArraySuper<Number> tmp = result.getS();
      if (DOGMAconfig.getHaltOnNaNOnFunctionExit()) {
        Catchers.catchNaN(tmp);
      }
      if (DOGMAconfig.getHaltOnInfOnFunctionExit()) {
        Catchers.catchInf(tmp);
      }
    }

    // test V
    if (required == compute.V || required == compute.UV || required == compute.SV || required == compute.USV) {
      OGArraySuper<Number> tmp = result.getVT();
      if (DOGMAconfig.getHaltOnNaNOnFunctionExit()) {
        Catchers.catchNaN(tmp);
      }
      if (DOGMAconfig.getHaltOnInfOnFunctionExit()) {
        Catchers.catchInf(tmp);
      }
    }

    return result;
  }
}
