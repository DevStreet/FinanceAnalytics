/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.interfaces;

import com.opengamma.maths.highlevelapi.datatypes.derived.OGSortResult;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGIndexArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGPermutationArray;

/**
 * Functions for rearranging matrices
 */
public interface DOGMARearrangingMatricesAPI {

  /**
   * Concatenates arrays along a given dimension
   * @param dim the dimension along which the concatenation will take place (1 or 2 at present, 1 indicates column space, 2 indicates row space.)
   * TODO: Extend array dimensions perhaps?
   * @param array the arrays to concatenate
   * @return the concatenated arrays
   */
  OGDoubleArray cat(OGIndexArray dim, OGDoubleArray... array);

  /**
   * Diag performs operations on the matrix diagonals.
   * @param array the array from which the diagonal will be extracted or a diagonal matrix can be created from 
   * @return a diag or a diagonal matrix, TODO: Fix with better explanation, add in DIAG matrix type 
   */
  OGDoubleArray diag(OGDoubleArray array);

  /**
   * Returns a copy of an array that has been flipped around a given dimension.
   * @param array the array to be flipped
   * @param dim the dimension in which the flipping shall take place (1 or 2 at present, 1 indicates column space, 2 indicates row space.) 
   * @return the array flipped about {@code dim}
   */
  OGDoubleArray flipdim(OGDoubleArray array, OGIndexArray dim);

  /**
   * Flips left-to-right (i.e. horizontally) an array
   * @param array the array to be flipped
   * @return a horizontally flipped version of array
   */
  OGDoubleArray fliplr(OGDoubleArray array);

  /**
   * Flips top-to-bottom (i.e. vertically) an array
   * @param array the array to be flipped
   * @return a vertically flipped version of thisArray
   */
  OGDoubleArray flipud(OGDoubleArray array);

  /**
   * Horizontally concatenate arrays
   * @param array a number of {@link OGDoubleArray}s to concatenate horizontally (to the right) in the order specified in vargin.
   * @return the horizontal concatenation of the arrays specified in vargin.
   */
  OGDoubleArray horzcat(OGDoubleArray... array);

  /**
   * The inverse of the permute function
   * @param array the array to inverse permute
   * @param perm the permutation to perform
   * @return inverse permutation of array
   */
  OGDoubleArray ipermute(OGDoubleArray array, OGPermutationArray perm);

  /**
   * The permute function
   * @param array the array to permute
   * @param perm the permutation to perform
   * @return the permutation of array
   */
  OGDoubleArray permute(OGDoubleArray array, OGPermutationArray perm);

  /**
   * Pad the array by appending the value "c" until its column space is of length "l"
   * @param array the array to pad
   * @param l the length of the matrix required. If "l" is less than the dimension of the column space the matrix is truncated to L rows.
   * @param c the value for padding
   * @return the padded array
   */
  OGDoubleArray postpad(OGDoubleArray array, OGIndexArray l, OGDoubleArray c);

  /**
   * Pad the array by appending the value "c" until its dimension "dim" is of length "l"
   * @param array the array to pad
   * @param l the length of the matrix required. If "l" is less than the dimension of the {@code dim} space the orthogonal space is truncated to dimension "l".
   * @param c the value for padding
   * @param dim the dimension along which the operation shall take place
   * @return the padded array
   */
  OGDoubleArray postpad(OGDoubleArray array, OGIndexArray l, OGDoubleArray c, OGIndexArray dim);

  /**
   * Pad the array by prepending the value "c" until its column space is of length "l"
   * @param array the array to pad
   * @param l the length of the matrix required. If "l" is less than the dimension of the column space the matrix is truncated to L rows.
   * @param c the value for padding
   * @return the padded array
   */
  OGDoubleArray prepad(OGDoubleArray array, OGIndexArray l, OGDoubleArray c);

  /**
   * Pad the array by prepending the value "c" until its dimension "dim" is of length "l"
   * @param array the array to pad
   * @param l the length of the matrix required. If "l" is less than the dimension of the {@code dim} space the orthogonal space is truncated to dimension "l".
   * @param c the value for padding
   * @param dim the dimension along which the operation shall take place
   * @return the padded array
   */
  OGDoubleArray prepad(OGDoubleArray array, OGIndexArray l, OGDoubleArray c, OGIndexArray dim);

  /**
   * Reshape the array. The reshaping is performed with regards to the column major backing of the array.
   * The reshaping parameters must commute with the number of elements in the array.
   * @param array the array to reshape
   * @param rows the number of rows in the reshaped array
   * @param columns the number of columns in the reshaped array
   * @return the reshaped array.
   */
  OGDoubleArray reshape(OGDoubleArray array, OGIndexArray rows, OGIndexArray columns);

  /**
   * Resize the array. Truncates a matrix to a given size, the row and column count for the resized array start from element 0.
   * @param array the array to resize
   * @param rows the number of rows in the resized array
   * @param columns the number of columns in the resized array
   * @return the resized array.
   */
  OGDoubleArray resize(OGDoubleArray array, OGIndexArray rows, OGIndexArray columns);

  /**
   * Rotates an array clockwise in 90 degree increments.
   * @param array the array to rotate
   * @return the rotated array.
   */
  OGDoubleArray rot90(OGDoubleArray array);

  /**
   * Rotates an array anti-clockwise in 90 degree increments.
   * @param array the array to rotate
   * @param increments the number of increments to apply (negative rotates clockwise).
   * @return the rotated array.
   */
  OGDoubleArray rot90(OGDoubleArray array, OGIndexArray increments);

  /**
   * Sort the array. Returns the array columns sorted in ascending order.
   * @param array to sort
   * @return the sorted array
   */
  OGDoubleArray sort(OGDoubleArray array);

  /**
   * Sort the array. Returns the array columns sorted in {@code order} order.
   * @param array to sort
   * @param order one of "ascend" or "descend"
   * @return the sorted array
   */
  OGDoubleArray sort(OGDoubleArray array, String order);

  /**
   * Sort the array. Returns the array sorted in {@code order} order in the space given by {@code dim}.
   * @param array to sort
   * @param dim the dimension on which to sort (1 or 2 at present, 1 indicates column space, 2 indicates row space.)
   * @param order one of "ascend" or "descend"
   * @return the sorted array and the indices used to sort the array stored in an {@link OGSortResult} object
   */
  OGSortResult<OGDoubleArray> sort(OGDoubleArray array, OGIndexArray dim, String order);

  /**
   * Sort the rows of an array in ascending order based on the sorted order of the first column
   * @param array the array to which the sort shall be applied
   * @return the sorted array
   */
  OGDoubleArray sortrows(OGDoubleArray array);

  /**
   * Sort the rows of an array in ascending order based on the sorted order of the column given in {@code columnIndex}
   * @param array the array to which the sort shall be applied
   * @param columnIndex the column index from which the sort order shall be applied to all rows
   * @return the sorted array
   */
  OGDoubleArray sortrows(OGDoubleArray array, OGIndexArray columnIndex);

  /**
   * Extracts the upper triangular part of an array setting all other elements to zero.
   * @param array the array from which the upper triangle will be extracted.
   * @return the upper triangle part of array with zero padding elsewhere
   */
  OGDoubleArray triu(OGDoubleArray array);

  
  /**
   * Extracts the lower triangular part of an array setting all other elements to zero
   * @param array the array from which the lower triangle will be extracted.
   * @return the lower triangle part of array with zero padding elsewhere
   */
  OGDoubleArray tril(OGDoubleArray array);  
  
  /**
   * Vertically concatenate arrays
   * @param array a number of {@link OGDoubleArray}s to concatenate vertically (to the bottom) in the order specified in vargin.
   * @return the vertical concatenation of the arrays specified in vargin.
   */
  OGDoubleArray vertcat(OGDoubleArray... array);

}
