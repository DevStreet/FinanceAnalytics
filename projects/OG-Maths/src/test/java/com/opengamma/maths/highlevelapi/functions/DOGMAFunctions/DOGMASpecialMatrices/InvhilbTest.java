/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASpecialMatrices;

import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;
import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionOverflow;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.ogblas.auxiliary.D1MACH;

/**
 * Tests the Inverse Hilbert matrix formation
 */
public class InvhilbTest {

  double[] invhilb10x10 = new double[] {100.0e0, -4950.0e0, 79200.0e0, -600600.0e0, 2522520.0e0, -6306300.0e0, 9609600.0e0, -8751600.0e0, 4375800.0e0, -923780.0e0, -4950.0e0, 326700.0e0,
      -5880600.0e0, 47567520.0e0, -208107900.0e0, 535134600.0e0, -832431600.0e0, 770140800.0e0, -389883780.0e0, 83140200.0e0, 79200.0e0, -5880600.0e0, 112907520.0e0, -951350400.0e0, 4281076800.0e0,
      -11237826600.0e0, 17758540800.0e0, -16635041280.0e0, 8506555200.0e0, -1829084400.0e0, -600600.0e0, 47567520.0e0, -951350400.0e0, 8245036800.0e0, -37875637800.0e0, 101001700800.0e0,
      -161602721280.0e0, 152907955200.0e0, -78843164400.0e0, 17071454400.0e0, 2522520.0e0, -208107900.0e0, 4281076800.0e0, -37875637800.0e0, 176752976400.0e0, -477233036280.0e0, 771285715200.0e0,
      -735869534400.0e0, 382086104400.0e0, -83223340200.0e0, -6306300.0e0, 535134600.0e0, -11237826600.0e0, 101001700800.0e0, -477233036280.0e0, 1301544644400.0e0, -2121035716800.0e0,
      2037792556800.0e0, -1064382719400.0e0, 233025352560.0e0, 9609600.0e0, -832431600.0e0, 17758540800.0e0, -161602721280.0e0, 771285715200.0e0, -2121035716800.0e0, 3480673996800.0e0,
      -3363975014400.0e0, 1766086882560.0e0, -388375587600.0e0, -8751600.0e0, 770140800.0e0, -16635041280.0e0, 152907955200.0e0, -735869534400.0e0, 2037792556800.0e0, -3363975014400.0e0,
      3267861442560.0e0, -1723286307600.0e0, 380449555200.0e0, 4375800.0e0, -389883780.0e0, 8506555200.0e0, -78843164400.0e0, 382086104400.0e0, -1064382719400.0e0, 1766086882560.0e0,
      -1723286307600.0e0, 912328045200.0e0, -202113826200.0e0, -923780.0e0, 83140200.0e0, -1829084400.0e0, 17071454400.0e0, -83223340200.0e0, 233025352560.0e0, -388375587600.0e0, 380449555200.0e0,
      -202113826200.0e0, 44914183600.0e0 };

  double[] invhilb2x2=new double[] {4.d, -6.d,-6.d, 12.d };
  
  
  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void badNTest() {
    Invhilb.invhilb(-1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void zeroNTest() {
    Invhilb.invhilb(0);
  }  
  
  @Test(expectedExceptions = MathsExceptionOverflow.class)
  public void overflowTest() {  
    Invhilb.invhilb(204);
  }
  
  @Test
  public void simple2x2Test() {
    OGMatrix tmp = Invhilb.invhilb(2);
    assertArrayEquals(tmp.getData(),invhilb2x2,10*D1MACH.four());
  }

  @Test
  public void simple10x10Test() {
    OGMatrix tmp = Invhilb.invhilb(10);
    assertArrayEquals(tmp.getData(),invhilb10x10,10*D1MACH.four());
  }  
  
  @Test
  public void hilbSimple1x1Test() {
    OGMatrix tmp = Invhilb.invhilb(1);
    assertArrayEquals(tmp.getData(),new double[] {1},10*D1MACH.four());
  }  
  
    

}
