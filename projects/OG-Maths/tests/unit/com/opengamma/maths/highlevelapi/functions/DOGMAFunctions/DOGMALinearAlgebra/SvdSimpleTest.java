/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMALinearAlgebra;

import java.lang.reflect.Field;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.derived.OGSvdResult;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMALinearAlgebra.svd.Svd;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMALinearAlgebra.svd.Svd.compute;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASpecialMatrices.Rosser;

/**
 * Simple svd test
 */
public class SvdSimpleTest {

  private final static int m = 4;
  private final static int n = 3;  
  private static double[] s_data=new double[] {1,4,7,10,2,5,8,11,3,6,9,12};
  private static OGDoubleArray A = new OGDoubleArray(s_data, m, n);
  
  static {
  System.setProperty( "java.library.path", "/usr/local/lib/" );

  Field fieldSysPath = null;
  try {
    fieldSysPath = ClassLoader.class.getDeclaredField( "sys_paths" );
  } catch (NoSuchFieldException ex) {
    // TODO Auto-generated catch block
    ex.printStackTrace();
  } catch (SecurityException ex) {
    // TODO Auto-generated catch block
    ex.printStackTrace();
  }
  fieldSysPath.setAccessible( true );
  try {
    fieldSysPath.set( null, null );
  } catch (IllegalArgumentException ex) {
    // TODO Auto-generated catch block
    ex.printStackTrace();
  } catch (IllegalAccessException ex) {
    // TODO Auto-generated catch block
    ex.printStackTrace();
  }
  }
  Svd mySVD = new Svd();
  
  @Test
  public void svdCallTest() {
    OGSvdResult result = mySVD.svd(A, compute.USV);
    System.out.println(result.getU().toString());    
    System.out.println(result.getS().toString());
    System.out.println(result.getVT().toString());    
  }
  
}
