/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions;

import static com.opengamma.maths.highlevelapi.functions.OGFunctions.abs;
import static com.opengamma.maths.highlevelapi.functions.OGFunctions.diag;
import static com.opengamma.maths.highlevelapi.functions.OGFunctions.fliplr;
import static com.opengamma.maths.highlevelapi.functions.OGFunctions.flipud;
import static com.opengamma.maths.highlevelapi.functions.OGFunctions.horzcat;
import static com.opengamma.maths.highlevelapi.functions.OGFunctions.multiply;
import static com.opengamma.maths.highlevelapi.functions.OGFunctions.print;
import static com.opengamma.maths.highlevelapi.functions.OGFunctions.repmat;
import static com.opengamma.maths.highlevelapi.functions.OGFunctions.reshape;
import static com.opengamma.maths.highlevelapi.functions.OGFunctions.transpose;
import static com.opengamma.maths.highlevelapi.functions.OGFunctions.unique;
import static com.opengamma.maths.highlevelapi.functions.OGFunctions.vertcat;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGIndexArray;

/**
 * tests functions on OGArray
 */
public class OGFunctionsTest {

  final double[][] doubleMatrixData = { {1, -2, 3, -4, 5 }, {-6, 7, -8, 9, -10 }, {11, -12, 13, -14, 15 } };
  final double[][] doubleRepeatMatrixData = { {1, 1, 3, -4, 5 }, {7, 7, -8, 9, -10 }, {11, -12, 13, -14, 15 } };
  final double[][] doubleRepeatMatrixUniqueData = {{-14, -12, -10, -8, -4, 1, 3, 5, 7, 9, 11, 13, 15 } };
  final double[][] doubleAbsMatrixData = { {1, 2, 3, 4, 5 }, {6, 7, 8, 9, 10 }, {11, 12, 13, 14, 15 } };
  final double[][] doubleMatrixTranposeData = { {1, -6, 11 }, {-2, 7, -12 }, {3, -8, 13 }, {-4, 9, -14 }, {5, -10, 15 } };
  final double[][] doubleMatrixDataReshape = { {1, -12, 9 }, {-6, 3, -14 }, {11, -8, 5 }, {-2, 13, -10 }, {7, -4, 15 } };

  final double[][] doubleVectorData = { {1 }, {2 }, {3 }, {4 }, {5 } };
  final double[][] doubleShortVectorData = {{1, 2, 3 } };
  final double[][] doubleShortVectorTransposeData = { {1 }, {2 }, {3 } };
  final double[][] doubleVectorTransposeData = {{1, 2, 3, 4, 5 } };
  final OGDoubleArray ogDoubleMatrixData = new OGDoubleArray(doubleMatrixData);
  final OGDoubleArray ogDoubleRepeatMatrixData = new OGDoubleArray(doubleRepeatMatrixData);
  final OGDoubleArray ogDoubleRepeatMatrixUniqueData = new OGDoubleArray(doubleRepeatMatrixUniqueData);
  final OGDoubleArray ogDoubleAbsMatrixData = new OGDoubleArray(doubleAbsMatrixData);
  final OGDoubleArray ogDoubleMatrixTranposeData = new OGDoubleArray(doubleMatrixTranposeData);
  final OGDoubleArray ogDoubleMatrixDataReshape = new OGDoubleArray(doubleMatrixDataReshape);

  final OGDoubleArray ogDoubleVectorData = new OGDoubleArray(doubleVectorData);
  final OGDoubleArray ogDoubleShortVectorData = new OGDoubleArray(doubleShortVectorData);
  final OGDoubleArray ogDoubleVectorTranposeData = new OGDoubleArray(doubleVectorTransposeData);
  final OGDoubleArray ogDoubleShortTransposeVectorData = new OGDoubleArray(doubleShortVectorTransposeData);

  final int[][] intMatrixData = { {1, -2, 3, -4, 5 }, {-6, 7, -8, 9, -10 }, {11, -12, 13, -14, 15 } };
  final int[][] intRepeatMatrixData = { {1, 1, 3, -4, 5 }, {7, 7, -8, 9, -10 }, {11, -12, 13, -14, 15 } };
  final int[][] intRepeatMatrixUniqueData = {{-14, -12, -10, -8, -4, 1, 3, 5, 7, 9, 11, 13, 15 } };
  final OGIndexArray ogIntMatrixData = new OGIndexArray(intMatrixData);
  final OGIndexArray ogIntRepeatMatrixData = new OGIndexArray(intRepeatMatrixData);
  final OGIndexArray ogIntRepeatMatrixUniqueData = new OGIndexArray(intRepeatMatrixUniqueData);

  /* TEST OGArray */
  @Test
  public void testAbsMatrix() {
    assert (abs(ogDoubleMatrixData).equals(ogDoubleAbsMatrixData));
  }

  @Test
  public void testMultiply() {
    OGDoubleArray Result;
    Result = multiply(ogDoubleMatrixData, ogDoubleVectorData); // mat * vec
    System.out.println(Result.toString());

    Result = multiply(ogDoubleShortVectorData, ogDoubleMatrixData); // vec * mat
    System.out.println(Result.toString());

    Result = multiply(ogDoubleMatrixTranposeData, ogDoubleShortTransposeVectorData); // mat^T * vec
    System.out.println(Result.toString());

    Result = multiply(ogDoubleVectorTranposeData, ogDoubleMatrixTranposeData); // vec * mat^T
    System.out.println(Result.toString());

    Result = multiply(ogDoubleMatrixData, ogDoubleMatrixDataReshape); // mat * mat
    System.out.println(Result.toString());
  }

  @Test
  public void testGetNumberOfRows() {
    assert (ogDoubleVectorData.getNumberOfRows() == 5);
  }

  @Test
  public void testGetNumberOfColumns() {
    assert (ogDoubleVectorData.getNumberOfColumns() == 1);
  }

  @Test
  public void testOGArrayUnique() {
    assert (unique(ogDoubleRepeatMatrixData).equals(ogDoubleRepeatMatrixUniqueData));
  }

  @Test
  public void testOGArrayReshape() {
    System.out.println("TESTING RESHAPE");
    System.out.println(ogDoubleMatrixData.toString());
    System.out.println(ogDoubleMatrixDataReshape.toString());
    assert (reshape(ogDoubleMatrixData, 5, 3).equals(ogDoubleMatrixDataReshape));
    System.out.println("reshape = " + reshape(ogDoubleMatrixData, 5, 3).toString());
  }

  @Test
  public void testOGArrayfliplr() {
    System.out.println("fliplr = " + fliplr(ogDoubleMatrixData).toString());
  }

  @Test
  public void testOGArrayflipud() {
    System.out.println("flipud = " + flipud(ogDoubleMatrixData).toString());
  }

  @Test
  public void testOGArrayhorzcat() {
    System.out.println("horzcat = " + horzcat(ogDoubleMatrixData, ogDoubleMatrixData, ogDoubleAbsMatrixData).toString());
  }

  @Test
  public void testOGArrayvertcat() {
    System.out.println("vertcat = " + vertcat(ogDoubleMatrixData, ogDoubleMatrixData, ogDoubleAbsMatrixData).toString());
  }

  @Test
  public void testOGArraytranspose() {
    System.out.println("transpose = " + transpose(ogDoubleMatrixData).toString());
  }

  @Test
  public void testOGArrayRepmat() {
    System.out.println("repmat = " + repmat(ogDoubleMatrixData, 4, 3).toString());
  }

  @Test
  public void testOGArrayDiag() {
    System.out.println("mat = " + ogDoubleMatrixData.toString());
    System.out.println("diag(mat) = " + diag(ogDoubleMatrixData).toString());
    System.out.println("diag(mat,1) = " + diag(ogDoubleMatrixData, 1).toString());
    System.out.println("diag(mat,2) = " + diag(ogDoubleMatrixData, 2).toString());
    System.out.println("diag(mat,3) = " + diag(ogDoubleMatrixData, 3).toString());
    System.out.println("diag(mat,4) = " + diag(ogDoubleMatrixData, 4).toString());

    System.out.println("diag(transpose(mat)) = " + diag(transpose(ogDoubleMatrixData)).toString());
    System.out.println("diag(transpose(mat),1) = " + diag(transpose(ogDoubleMatrixData), 1).toString());
    System.out.println("diag(transpose(mat),2) = " + diag(transpose(ogDoubleMatrixData), 2).toString());

    System.out.println("diag(mat,-1) = " + diag(ogDoubleMatrixData, -1).toString());
    System.out.println("diag(mat,-2) = " + diag(ogDoubleMatrixData, -2).toString());

    System.out.println("diag(transpose(mat)) = " + diag(transpose(ogDoubleMatrixData)).toString());
    System.out.println("diag(transpose(mat),-1) = " + diag(transpose(ogDoubleMatrixData), -1).toString());
    System.out.println("diag(transpose(mat),-2) = " + diag(transpose(ogDoubleMatrixData), -2).toString());
    System.out.println("diag(transpose(mat),-3) = " + diag(transpose(ogDoubleMatrixData), -3).toString());
    System.out.println("diag(transpose(mat),-4) = " + diag(transpose(ogDoubleMatrixData), -4).toString());

    System.out.println("diag(vect,-1) = " + diag(ogDoubleVectorData, -1).toString());
    System.out.println("diag(vect,-4) = " + diag(ogDoubleVectorData, -4).toString());
    System.out.println("diag(vect,7) = " + diag(ogDoubleVectorData, 7).toString());
    System.out.println("diag(vect,0) = " + diag(ogDoubleVectorData, 0).toString());
    System.out.println("diag(vect) = " + diag(ogDoubleVectorData).toString());

  }

  /* TEST OGIndex */
  @Test
  public void testOGIndexUnique() {
    assert (unique(ogIntRepeatMatrixData).equals(ogIntRepeatMatrixUniqueData));
  }

  @Test
  public void testOGIndexfliplr() {
    print(fliplr(ogIntMatrixData));
  }

  @Test
  public void testOGIndexflipud() {
    print(flipud(ogIntMatrixData));
  }

  @Test
  public void testOGIndexhorzcat() {
    print(horzcat(ogIntMatrixData, ogIntMatrixData, ogIntRepeatMatrixData));
  }

  @Test
  public void testOGIndexvertcat() {
    print(vertcat(ogIntMatrixData, ogIntMatrixData, ogIntRepeatMatrixData));
  }

  @Test
  public void testOGIndextranspose() {
    System.out.println("transpose" + transpose(horzcat(ogIntMatrixData, ogIntMatrixData, ogIntRepeatMatrixData)).toString());
  }

  @Test
  public void testOGIndexRepmat() {
    System.out.println("repmat = " + repmat(ogIntMatrixData, 2, 3).toString());
  }

}
