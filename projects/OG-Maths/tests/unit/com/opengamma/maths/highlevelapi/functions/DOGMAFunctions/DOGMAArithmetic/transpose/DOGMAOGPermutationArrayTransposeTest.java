/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.transpose;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNullPointer;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGPermutationArray;

/**
 * Tests OGPermutation transpose
 */
public class DOGMAOGPermutationArrayTransposeTest {

  TransposeOGPermutationArray t = TransposeOGPermutationArray.getInstance();

  int[] _data = new int[] {0,3,6,9,1,4,7,10,2,5,8,11};
  int[] _transposedata = new int [] {0,4,8,1,5,9,2,6,10,3,7,11};
  OGPermutationArray array1 = new OGPermutationArray(_data);

  OGPermutationArray array1tranposed = new OGPermutationArray(_transposedata);
  
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void nullInTest() {
    OGPermutationArray tmp = null;
    t.transpose(tmp);
  }

  @Test
  public void testTranspose() {  
    assertTrue(array1tranposed.equals(t.transpose(array1)));
  }

  @Test
  public void testTransposeEqTransposeTransposed() {  
    assertTrue(array1.equals(t.transpose((OGPermutationArray) t.transpose(array1))));
  }  
  
}
