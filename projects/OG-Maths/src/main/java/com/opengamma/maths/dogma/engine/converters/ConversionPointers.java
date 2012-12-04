/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.converters;

import com.opengamma.maths.dogma.engine.converters.convertOGComplexDiagonalMatrix.ConvertOGComplexDiagonalToOGComplexMatrix;
import com.opengamma.maths.dogma.engine.converters.convertOGComplexDiagonalMatrix.ConvertOGComplexDiagonalToOGComplexSparseMatrix;
import com.opengamma.maths.dogma.engine.converters.convertOGComplexScalar.ConvertOGComplexScalarToOGComplexDiagonalMatrix;
import com.opengamma.maths.dogma.engine.converters.convertOGComplexScalar.ConvertOGComplexScalarToOGComplexMatrix;
import com.opengamma.maths.dogma.engine.converters.convertOGComplexScalar.ConvertOGComplexScalarToOGComplexSparseMatrix;
import com.opengamma.maths.dogma.engine.converters.convertOGComplexSparseMatrix.ConvertOGComplexSparseMatrixToOGComplexMatrix;
import com.opengamma.maths.dogma.engine.converters.convertOGDiagonalMatrix.ConvertOGDiagonalToOGComplexDiagonalMatrix;
import com.opengamma.maths.dogma.engine.converters.convertOGDiagonalMatrix.ConvertOGDiagonalToOGComplexMatrix;
import com.opengamma.maths.dogma.engine.converters.convertOGDiagonalMatrix.ConvertOGDiagonalToOGComplexSparseMatrix;
import com.opengamma.maths.dogma.engine.converters.convertOGDiagonalMatrix.ConvertOGDiagonalToOGMatrix;
import com.opengamma.maths.dogma.engine.converters.convertOGDiagonalMatrix.ConvertOGDiagonalToOGSparseMatrix;
import com.opengamma.maths.dogma.engine.converters.convertOGMatrix.ConvertOGMatrixToOGComplexMatrix;
import com.opengamma.maths.dogma.engine.converters.convertOGMatrix.ConvertOGMatrixToOGSparseMatrix;
import com.opengamma.maths.dogma.engine.converters.convertOGRealScalar.ConvertOGRealScalarToOGComplexDiagonalMatrix;
import com.opengamma.maths.dogma.engine.converters.convertOGRealScalar.ConvertOGRealScalarToOGComplexMatrix;
import com.opengamma.maths.dogma.engine.converters.convertOGRealScalar.ConvertOGRealScalarToOGComplexScalar;
import com.opengamma.maths.dogma.engine.converters.convertOGRealScalar.ConvertOGRealScalarToOGDiagonalMatrix;
import com.opengamma.maths.dogma.engine.converters.convertOGRealScalar.ConvertOGRealScalarToOGMatrix;
import com.opengamma.maths.dogma.engine.converters.convertOGSparseMatrix.ConvertOGSparseMatrixToOGComplexMatrix;
import com.opengamma.maths.dogma.engine.converters.convertOGSparseMatrix.ConvertOGSparseMatrixToOGComplexSparseMatrix;
import com.opengamma.maths.dogma.engine.converters.convertOGSparseMatrix.ConvertOGSparseMatrixToOGMatrix;

/**
 * 
 */
public class ConversionPointers {
  private static ConversionPointers s_instance;

  public ConversionPointers() {
  }

  public static ConversionPointers getInstance() {
    return s_instance;
  }

  // malloc conversion set    
  private static Converter<?>[][] s_conversionDict = new Converter<?>[10][10];

  public static Converter<?>[][] getConverters() {
    return s_conversionDict;
  }

  // bit table
  //{1, 1, 1, 1, 0, 0, 1, 1, 1, 1 }
  //{0, 1, 0, 1, 0, 0, 0, 1, 0, 1 }
  //{0, 0, 1, 1, 0, 0, 1, 1, 1, 1 }
  //{0, 0, 0, 1, 0, 0, 0, 1, 0, 1 }
  //{0, 0, 0, 0, 1, 1, 1, 1, 1, 1 }
  //{0, 0, 0, 0, 0, 1, 1, 1, 1, 1 }
  //{0, 0, 0, 0, 0, 0, 1, 1, 1, 1 }
  //{0, 0, 0, 0, 0, 0, 0, 1, 0, 1 }
  //{0, 0, 0, 0, 0, 0, 0, 0, 1, 1 }
  //{0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }

  // assign pointers, should really be a) sparse, b) a jump table
  static {
    ConvertMatrixNOP matrixNOP = new ConvertMatrixNOP();
    //                                            _TO_    
    // _FROM_      Number  Complex  Diag  CplxDiag  Perm  Idx   Sparse  CplxSparse   Mat  CplxMat
    //Number     |  Yes   |   Yes  | Yes |   Yes   | No  | No  |  Yes  |    Yes    | Yes |  Yes  |
    //Complex    |   No   |   Yes  | No  |   Yes   | No  | No  |  No   |    Yes    | No  |  Yes  |
    //Diag       |   No   |   No   | Yes |   Yes   | No  | No  |  Yes  |    Yes    | Yes |  Yes  |
    //CplxDiag   |   No   |   No   | No  |   Yes   | No  | No  |  No   |    Yes    | No  |  Yes  |
    //Perm       |   No   |   No   | No  |   No    | Yes | No  |  Yes  |    Yes    | Yes |  Yes  |
    //Idx        |   No   |   No   | No  |   No    | No  | Yes |  Yes  |    Yes    | Yes |  Yes  |
    //Sparse     |   No   |   No   | No  |   No    | No  | No  |  Yes  |    Yes    | Yes |  Yes  |
    //CplxSparse |   No   |   No   | No  |   No    | No  | No  |  No   |    Yes    | No  |  Yes  |
    //Mat        |   No   |   No   | No  |   No    | No  | No  |  No   |    No     | Yes |  Yes  |
    //CplxMat    |   No   |   No   | No  |   No    | No  | No  |  No   |    No     | No  |  Yes  |

    // generate template from:
    // types={'Number','Complex','Diag','CplxDiag','Perm','Idx','Sparse','CplxSparse','Mat','CplxMat'}
    // for i = 1:10; for j = 1:10; printf("s_conversionDict[%g][%g]=null; // %s-->%s\n",i-1,j-1,types{i},types{j});end;end
    s_conversionDict[0][0] = matrixNOP; // Number-->Number
    s_conversionDict[0][1] = new ConvertOGRealScalarToOGComplexScalar(); // Number-->Complex
    s_conversionDict[0][2] = new ConvertOGRealScalarToOGDiagonalMatrix(); // Number-->Diag
    s_conversionDict[0][3] = new ConvertOGRealScalarToOGComplexDiagonalMatrix(); // Number-->CplxDiag
    s_conversionDict[0][4] = null; // Number-->Perm
    s_conversionDict[0][5] = null; // Number-->Idx
    s_conversionDict[0][6] = null; // Number-->Sparse
    s_conversionDict[0][7] = null; // Number-->CplxSparse
    s_conversionDict[0][8] = new ConvertOGRealScalarToOGMatrix(); // Number-->Mat
    s_conversionDict[0][9] = new ConvertOGRealScalarToOGComplexMatrix(); // Number-->CplxMat
    s_conversionDict[1][0] = null; // Complex-->Number
    s_conversionDict[1][1] = matrixNOP; // Complex-->Complex
    s_conversionDict[1][2] = null; // Complex-->Diag
    s_conversionDict[1][3] = new ConvertOGComplexScalarToOGComplexDiagonalMatrix(); // Complex-->CplxDiag
    s_conversionDict[1][4] = null; // Complex-->Perm
    s_conversionDict[1][5] = null; // Complex-->Idx
    s_conversionDict[1][6] = null; // Complex-->Sparse
    s_conversionDict[1][7] = new ConvertOGComplexScalarToOGComplexSparseMatrix(); // Complex-->CplxSparse
    s_conversionDict[1][8] = null; // Complex-->Mat
    s_conversionDict[1][9] = new ConvertOGComplexScalarToOGComplexMatrix(); // Complex-->CplxMat
    s_conversionDict[2][0] = null; // Diag-->Number
    s_conversionDict[2][1] = null; // Diag-->Complex
    s_conversionDict[2][2] = matrixNOP; // Diag-->Diag
    s_conversionDict[2][3] = new ConvertOGDiagonalToOGComplexDiagonalMatrix(); // Diag-->CplxDiag
    s_conversionDict[2][4] = null; // Diag-->Perm
    s_conversionDict[2][5] = null; // Diag-->Idx
    s_conversionDict[2][6] = new ConvertOGDiagonalToOGSparseMatrix(); // Diag-->Sparse
    s_conversionDict[2][7] = new ConvertOGDiagonalToOGComplexSparseMatrix(); // Diag-->CplxSparse
    s_conversionDict[2][8] = new ConvertOGDiagonalToOGMatrix(); // Diag-->Mat
    s_conversionDict[2][9] = new ConvertOGDiagonalToOGComplexMatrix(); // Diag-->CplxMat
    s_conversionDict[3][0] = null; // CplxDiag-->Number
    s_conversionDict[3][1] = null; // CplxDiag-->Complex
    s_conversionDict[3][2] = null; // CplxDiag-->Diag
    s_conversionDict[3][3] = matrixNOP; // CplxDiag-->CplxDiag
    s_conversionDict[3][4] = null; // CplxDiag-->Perm
    s_conversionDict[3][5] = null; // CplxDiag-->Idx
    s_conversionDict[3][6] = null; // CplxDiag-->Sparse
    s_conversionDict[3][7] = new ConvertOGComplexDiagonalToOGComplexSparseMatrix(); // CplxDiag-->CplxSparse
    s_conversionDict[3][8] = null; // CplxDiag-->Mat
    s_conversionDict[3][9] = new ConvertOGComplexDiagonalToOGComplexMatrix(); // CplxDiag-->CplxMat
    s_conversionDict[4][0] = null; // Perm-->Number
    s_conversionDict[4][1] = null; // Perm-->Complex
    s_conversionDict[4][2] = null; // Perm-->Diag
    s_conversionDict[4][3] = null; // Perm-->CplxDiag
    s_conversionDict[4][4] = matrixNOP; // Perm-->Perm
    s_conversionDict[4][5] = null; // Perm-->Idx
    s_conversionDict[4][6] = null; // Perm-->Sparse
    s_conversionDict[4][7] = null; // Perm-->CplxSparse
    s_conversionDict[4][8] = null; // Perm-->Mat
    s_conversionDict[4][9] = null; // Perm-->CplxMat
    s_conversionDict[5][0] = null; // Idx-->Number
    s_conversionDict[5][1] = null; // Idx-->Complex
    s_conversionDict[5][2] = null; // Idx-->Diag
    s_conversionDict[5][3] = null; // Idx-->CplxDiag
    s_conversionDict[5][4] = null; // Idx-->Perm
    s_conversionDict[5][5] = matrixNOP; // Idx-->Idx
    s_conversionDict[5][6] = null; // Idx-->Sparse
    s_conversionDict[5][7] = null; // Idx-->CplxSparse
    s_conversionDict[5][8] = null; // Idx-->Mat
    s_conversionDict[5][9] = null; // Idx-->CplxMat
    s_conversionDict[6][0] = null; // Sparse-->Number
    s_conversionDict[6][1] = null; // Sparse-->Complex
    s_conversionDict[6][2] = null; // Sparse-->Diag
    s_conversionDict[6][3] = null; // Sparse-->CplxDiag
    s_conversionDict[6][4] = null; // Sparse-->Perm
    s_conversionDict[6][5] = null; // Sparse-->Idx
    s_conversionDict[6][6] = matrixNOP; // Sparse-->Sparse
    s_conversionDict[6][7] = new ConvertOGSparseMatrixToOGComplexSparseMatrix(); // Sparse-->CplxSparse
    s_conversionDict[6][8] = new ConvertOGSparseMatrixToOGMatrix(); // Sparse-->Mat
    s_conversionDict[6][9] = new ConvertOGSparseMatrixToOGComplexMatrix(); // Sparse-->CplxMat
    s_conversionDict[7][0] = null; // CplxSparse-->Number
    s_conversionDict[7][1] = null; // CplxSparse-->Complex
    s_conversionDict[7][2] = null; // CplxSparse-->Diag
    s_conversionDict[7][3] = null; // CplxSparse-->CplxDiag
    s_conversionDict[7][4] = null; // CplxSparse-->Perm
    s_conversionDict[7][5] = null; // CplxSparse-->Idx
    s_conversionDict[7][6] = null; // CplxSparse-->Sparse
    s_conversionDict[7][7] = matrixNOP; // CplxSparse-->CplxSparse
    s_conversionDict[7][8] = null; // CplxSparse-->Mat
    s_conversionDict[7][9] = new ConvertOGComplexSparseMatrixToOGComplexMatrix(); // CplxSparse-->CplxMat
    s_conversionDict[8][0] = null; // Mat-->Number
    s_conversionDict[8][1] = null; // Mat-->Complex
    s_conversionDict[8][2] = null; // Mat-->Diag
    s_conversionDict[8][3] = null; // Mat-->CplxDiag
    s_conversionDict[8][4] = null; // Mat-->Perm
    s_conversionDict[8][5] = null; // Mat-->Idx
    s_conversionDict[8][6] = new ConvertOGMatrixToOGSparseMatrix(); // Mat-->Sparse
    s_conversionDict[8][7] = null; // Mat-->CplxSparse
    s_conversionDict[8][8] = matrixNOP; // Mat-->Mat
    s_conversionDict[8][9] = new ConvertOGMatrixToOGComplexMatrix(); // Mat-->CplxMat
    s_conversionDict[9][0] = null; // CplxMat-->Number
    s_conversionDict[9][1] = null; // CplxMat-->Complex
    s_conversionDict[9][2] = null; // CplxMat-->Diag
    s_conversionDict[9][3] = null; // CplxMat-->CplxDiag
    s_conversionDict[9][4] = null; // CplxMat-->Perm
    s_conversionDict[9][5] = null; // CplxMat-->Idx
    s_conversionDict[9][6] = null; // CplxMat-->Sparse
    s_conversionDict[9][7] = null; // CplxMat-->CplxSparse
    s_conversionDict[9][8] = null; // CplxMat-->Mat
    s_conversionDict[9][9] = matrixNOP; // CplxMat-->CplxMat
  }

  @Override
  public String toString() {
    int m = s_conversionDict.length;
    int n = s_conversionDict[0].length;
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (s_conversionDict[i][j] != null) {
          sb.append(s_conversionDict[i][j].getClass() + ",  ");
        } else {
          sb.append("null, ");
        }
      }
      sb.append("\n");
    }
    return new String(sb);
  }
   
}
