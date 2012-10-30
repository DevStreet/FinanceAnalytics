/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
/**
 * Code is a translation of the test code at http://www.netlib.org/blas/dblat1
 * It forms part of the Netlib BLAS/LAPACK reference library which is provided under a modified BSD licensed.
 */
package com.opengamma.maths.lowlevelapi.exposedapi;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionGeneric;

/**
 * Does the Netlib BLAS1 test (approximately)
 */
public class Dblat1 {

  private BLAS _blas = new BLAS();

  // equiv of common block
  private int _icase, _n, _incx, _incy, _mode;
  private boolean _pass;
  private double _sfac = 9.765625e-4;

  @Test
  public void dblat1() {
    for (int ic = 0; ic < 10; ic++) {
      _icase = ic;
      header();
      _pass = true;
      _incx = 9999;
      _incy = 9999;
      _mode = 9999;
      if (_icase == 2) {
        check0(_sfac);
      } else if (_icase == 6 || _icase == 7 || _icase == 8 || _icase == 9) {
        check1(_sfac);
      } else if (_icase == 0 || _icase == 1 || _icase == 4 || _icase == 5) {
        check2(_sfac);
      } else if (_icase == 3) {
        check3(_sfac);
      }
      if (_pass) {
        System.out.println("...passes");
      }
    }

  }

  private void header() {
    String[] nameL = new String[10];
    nameL[0] = "DDOT";
    nameL[1] = "DAXPY";
    nameL[2] = "DROTG";
    nameL[3] = "DROT";
    nameL[4] = "DCOPY";
    nameL[5] = "DSWAP";
    nameL[6] = "DNRM2";
    nameL[7] = "DASUM";
    nameL[8] = "DSCAL";
    nameL[9] = "IDAMAX";
    System.out.println("Testing " + nameL[_icase]);
  }

  private void check0(double sfac) {
    double[] da1 = {0.3d, 0.4d, -0.3d, -0.4d, -0.3d, 0.0d, 0.0d, 1.0d };
    double[] db1 = {0.4d, 0.3d, 0.4d, 0.3d, -0.4d, 0.0d, 1.0d, 0.0d };
    double[] dc1 = {0.6d, 0.8d, -0.6d, 0.8d, 0.6d, 1.0d, 0.0d, 1.0d };
    double[] ds1 = {0.8d, 0.6d, 0.8d, -0.6d, 0.8d, 0.0d, 1.0d, 0.0d };
    double[] datrue = {0.5d, 0.5d, 0.5d, -0.5d, -0.5d, 0.0d, 1.0d, 1.0d };
    double[] dbtrue = {0.0d, 0.6d, 0.0d, -0.6d, 0.0d, 0.0d, 1.0d, 0.0d };
    dbtrue[0] = 1.0d / 0.6d;
    dbtrue[2] = -1.0d / 0.6d;
    dbtrue[4] = 1.0d / 0.6d;
    double[] sa, sb, ss, sc;
    sa = new double[1];
    sb = new double[1];
    ss = new double[1];
    sc = new double[1];
    for (int k = 0; k < 8; k++) {
      _n = k;
      if (_icase == 2) {
        sa[0] = da1[k];
        sb[0] = db1[k];
        _blas.drotg(sa, sb, sc, ss);
        stest1(sa[0], datrue[k], datrue[k], sfac);
        stest1(sb[0], dbtrue[k], dbtrue[k], sfac);
        stest1(sc[0], dc1[k], dc1[k], sfac);
        stest1(ss[0], ds1[k], ds1[k], sfac);
      }

    }
  }

  private void check1(double sfac) {
    double[] SA = {0.3e0, -1.0e0, 0.0e0, 1.0e0, 0.3e0, 0.3e0, 0.3e0, 0.3e0, 0.3e0, 0.3e0 };
    double[][][] DV =
    { {
    {0.1e0, 0.1e0 },
    {0.3e0, 0.3e0 },
    {0.3e0, 0.3e0 },
    {0.2e0, 0.2e0 },
    {0.1e0, 0.1e0 }
    }, {
    {2.0e0, 8.0e0 },
    {3.0e0, 9.0e0 },
    {-0.4e0, 2.0e0 },
    {-0.6e0, 3.0e0 },
    {-0.3e0, 4.0e0 }
    }, {
    {2.0e0, 8.0e0 },
    {3.0e0, 9.0e0 },
    {4.0e0, -0.4e0 },
    {0.3e0, -0.6e0 },
    {0.50e0, -0.3e0 }
    }, {
    {2.0e0, 8.0e0 },
    {3.0e0, 9.0e0 },
    {4.0e0, 2.0e0 },
    {5.0e0, 5.0e0 },
    {-0.1e0, 6.0e0 }
    }, {
    {2.0e0, 8.0e0 },
    {3.0e0, 9.0e0 },
    {4.0e0, 2.0e0 },
    {5.0e0, 0.3e0 },
    {6.0e0, -0.50e0 }
    }, {
    {2.0e0, 8.0e0 },
    {3.0e0, 9.0e0 },
    {4.0e0, 2.0e0 },
    {5.0e0, 2.0e0 },
    {6.0e0, 7.0e0 }
    }, {
    {2.0e0, 8.0e0 },
    {3.0e0, 9.0e0 },
    {4.0e0, 2.0e0 },
    {5.0e0, 2.0e0 },
    {6.0e0, -0.1e0 }
    }, {
    {2.0e0, 8.0e0 },
    {3.0e0, 9.0e0 },
    {4.0e0, 2.0e0 },
    {5.0e0, 2.0e0 },
    {6.0e0, 3.0e0 }
    }
    };
    double[] DTRUE1 = {0.0e0, 0.3e0, 0.5e0, 0.7e0, 0.6e0 };
    double[] DTRUE3 = {0.0e0, 0.3e0, 0.7e0, 1.1e0, 1.0e0 };
    double[][][] DTRUE5 = {
        {
        {0.1e0, 0.1e0 },
        {-0.3e0, 9e-2 },
        {0.0e0, 9e-2 },
        {0.2e0, 6e-2 },
        {3e-2, 3e-2 }
    }, {
    {2.0e0, 8.0e0 },
    {3.0e0, 9.0e0 },
    {0.0e0, 2.0e0 },
    {-0.6e0, 3.0e0 },
    {-9e-2, 4.0e0 }
    }, {
    {2.0e0, 8.0e0 },
    {3.0e0, 9.0e0 },
    {4.0e0, -0.12e0 },
    {0.3e0, -0.18e0 },
    {0.15e0, -9e-2 }
    }, {
    {2.0e0, 8.0e0 },
    {3.0e0, 9.0e0 },
    {4.0e0, 2.0e0 },
    {5.0e0, 5.0e0 },
    {-3.e-2, 6.0e0 }
    }, {
    {2.0e0, 8.0e0 },
    {3.0e0, 9.0e0 },
    {4.0e0, 2.0e0 },
    {5.0e0, 9e-2 },
    {6.0e0, -0.15e0 }
    }, {
    {2.0e0, 8.0e0 },
    {3.0e0, 9.0e0 },
    {4.0e0, 2.0e0 },
    {5.0e0, 2.0e0 },
    {6.0e0, 7.0e0 }
    }, {
    {2.0e0, 8.0e0 },
    {3.0e0, 9.0e0 },
    {4.0e0, 2.0e0 },
    {5.0e0, 2.0e0 },
    {6.0e0, -3.e-2 }
    }, {
    {2.0e0, 8.0e0 },
    {3.0e0, 9.0e0 },
    {4.0e0, 2.0e0 },
    {5.0e0, 2.0e0 },
    {6.0e0, 3.0e0 }
    }
    };
    // indexes of expected max element positions for idamax, these are 1 based, the 0 at the start is the signal for "weird/not found/undefined"
    int[] itrue2 = {0, 1, 2, 2, 3 };
    double[] strue = new double[8];
    double[] sx = new double[8];
    double stemp = 0;
    int n, len;
    for (_incx = 1; _incx <= 2; _incx++) {
      for (int np1 = 0; np1 < 5; np1++) {
        n = np1;
        len = 2 * Math.max(n, 1);
        for (int i = 0; i < len; i++) {
          sx[i] = DV[i][np1][_incx - 1];
        }

        if (_icase == 6) { // DNRM2
          stemp = DTRUE1[np1];
          stest1(_blas.dnrm2(n, sx, _incx), stemp, stemp, sfac);
        } else if (_icase == 7) { // DASUM
          stemp = DTRUE3[np1];
          stest1(_blas.dasum(n, sx, _incx), stemp, stemp, sfac);
        } else if (_icase == 8) { // DSCAL
          _blas.dscal(n, SA[(_incx - 1) * 5 + np1], sx, _incx);
          for (int i = 0; i < len; i++) {
            strue[i] = DTRUE5[i][np1][_incx - 1];
          }
          stest(len, sx, strue, strue, sfac);
        } else if (_icase == 9) { // IDAMAX
          itest1(_blas.idamax(n, sx, _incx), itrue2[np1]);
        }

      }
    }
    return;
  }

  private void check2(double sfac) {
    int[] incxs = new int[] {1, 2, -2, -1 };
    int[] incys = new int[] {1, -2, 1, -2 };
    int my, mx, n;
    double sa = 0.3e0;
    int ksize, lenx, leny;

    double[] dx1 = {0.6e0, 0.1e0, -0.5e0, 0.8e0, 0.9e0, -0.3e0, -0.4e0 };
    double[] dy1 = {0.5e0, -0.9e0, 0.3e0, 0.7e0, -0.6e0, 0.2e0, 0.8e0 };
    double[][] dt7 = { {0.0e0, 0.0e0, 0.0e0, 0.0e0 },
        {0.3e0, 0.3e0, 0.3e0, 0.3e0 },
        {0.21e0, -7.e-2, -0.79e0, 0.33e0 },
        {0.62e0, 0.85e0, -0.74e0, 1.27e0 }
    };
    double[] ssize1 = {0.0e0, 0.3e0, 1.6e0, 3.2e0 };
    double[][] ssize2 = { {0.e0, 1.17e0 },
        {0.e0, 1.17e0 },
        {0.e0, 1.17e0 },
        {0.e0, 1.17e0 },
        {0.e0, 1.17e0 },
        {0.e0, 1.17e0 },
        {0.e0, 1.17e0 },
        {0.e0, 1.17e0 },
        {0.e0, 1.17e0 },
        {0.e0, 1.17e0 },
        {0.e0, 1.17e0 },
        {0.e0, 1.17e0 },
        {0.e0, 1.17e0 },
        {0.e0, 1.17e0 } };

    double[][][] dt8 =
    {
        { {0.5e00, 0.5e00, 0.5e00, 0.5e00 }, {0.68e0, 0.68e0, 0.68e0, 0.68e0 },
        {0.68e0, 0.35e0, 0.35e0, 0.68e0 }, {0.68e0, 0.38e0, 0.38e0, 0.68e0 } },
        { {0.e0, 0.e0, 0.e0, 0.e0 }, {0.e0, 0.e0, 0.e0, 0.e0 },
        {-0.87e0, -0.9e00, -0.72e0, -0.9e00 }, {-0.87e0, -0.9e0, -0.63e0, -0.9e0 } },
        { {0.e0, 0.e0, 0.e0, 0.e0 }, {0.e0, 0.e0, 0.e0, 0.e000 },
        {0.e0, 0.48e0, 0.e0, 0.33e0 }, {0.15e0, 0.57e0, 0.15e0, 0.33e0 } },
        { {0.e0, 0.e0, 0.e0, 0.e0 }, {0.e0, 0.e0, 0.e0, 0.e000 },
        {0.e0, 0.e0, 0.e0, 0.e0 }, {0.94e0, 0.7e00, 0.88e0, 0.7e00 } },
        { {0.e0, 0.e0, 0.e0, 0.e0 }, {0.e0, 0.e0, 0.e0, 0.e000 },
        {0.e0, 0.e0, 0.e0, 0.e0 }, {0.e0, -0.75e0, 0.e0, -0.75e0 } },
        { {0.e0, 0.e0, 0.e0, 0.e0 }, {0.e0, 0.e0, 0.e0, 0.e000 },
        {0.e0, 0.e0, 0.e0, 0.e0 }, {0.e0, 0.2e00, 0.e0, 0.2e00 } },
        { {0.e0, 0.e0, 0.e0, 0.e0 }, {0.e0, 0.e0, 0.e0, 0.e0 },
        {0.e0, 0.e0, 0.e0, 0.e0 }, {0.e0, 0.98e0, 0.e0, 1.04e0 } } };

    double[][][] dt10y = {
        { {0.5e0, 0.5e0, 0.5e0, 0.5e0 }, {0.6e0, 0.6e0, 0.6e0, 0.6e0 },
        {0.6e0, -0.5e0, -0.5e0, 0.6e0 }, {0.6e0, -0.4e0, -0.4e0, 0.6e0 } },
        { {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, 0.e00, 0.e00, 0.e00 },
        {0.1e0, -0.9e0, 0.6e0, -0.9e0 }, {0.1e0, -0.9e0, 0.9e0, -0.9e0 } },
        { {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, 0.e00, 0.e00, 0.e00 },
        {0.e00, 0.6e0, 0.e00, 0.1e0 }, {-0.5e0, 0.9e0, -0.5e0, 0.1e0 } },
        { {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, 0.e00, 0.e00, 0.e00 },
        {0.e00, 0.e00, 0.e00, 0.e00 }, {0.8e0, 0.7e0, 0.6e0, 0.7e0 } },
        { {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, 0.e00, 0.e00, 0.e00 },
        {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, -0.5e0, 0.e00, -0.5e0 } },
        { {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, 0.e00, 0.e00, 0.e00 },
        {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, 0.2e0, 0.e00, 0.2e0 } },
        { {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, 0.e00, 0.e00, 0.e00 },
        {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, 0.6e0, 0.e00, 0.8e0 } } };

    double[][][] dt10x =
    {
        { {0.6e0, 0.6e0, 0.6e0, 0.6e0 }, {0.5e0, 0.5e0, 0.5e0, 0.5e0 },
        {0.5e0, 0.3e0, -0.9e0, 0.5e0 }, {0.5e0, 0.8e0, 0.7e0, 0.5e0 } },
        { {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, 0.e00, 0.e00, 0.e00 },
        {-0.9e0, 0.1e0, 0.1e0, 0.3e0 }, {-0.9e0, 0.1e0, 0.1e0, 0.3e0 } },
        { {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, 0.e00, 0.e00, 0.e00 },
        {0.e00, 0.5e0, 0.5e0, 0.e00 }, {0.3e0, -0.6e0, 0.3e0, -0.6e0 } },
        { {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, 0.e00, 0.e00, 0.e00 },
        {0.e00, 0.e00, 0.e00, 0.e00 }, {0.7e0, 0.8e0, 0.8e0, 0.8e0 } },
        { {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, 0.e00, 0.e00, 0.e00 },
        {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, 0.3e0, -0.9e0, 0.e00 } },
        { {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, 0.e00, 0.e00, 0.e00 },
        {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, -0.3e0, -0.3e0, 0.e00 } },
        { {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, 0.e00, 0.e00, 0.e00 },
        {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, 0.5e0, 0.5e0, 0.e00 } } };

    int[][] lens = new int[][] { {1, 1 }, {1, 1 }, {2, 3 }, {4, 7 } };
    int[] ns = new int[] {0, 1, 2, 4 };
    double[] sx = new double[7], sy = new double[7];
    double[] stx = new double[7], sty = new double[7];
    int knfortran = 0;
    for (int ki = 0; ki < 4; ki++) {
      _incx = incxs[ki];
      _incy = incys[ki];
      mx = Math.abs(_incx) - 1;// fortran 1 indexed
      my = Math.abs(_incy) - 1;// fortran 1 indexed

      for (int knjava = 0; knjava < 4; knjava++) {
        knfortran = knjava + 1;
        n = ns[knjava];
        ksize = Math.min(1, knjava);
        lenx = lens[knjava][mx]; // fortran 1 indexed
        leny = lens[knjava][my]; // fortran 1 indexed

        for (int i = 0; i < 7; i++) {
          sx[i] = dx1[i];
          sy[i] = dy1[i];
        }

        if (_icase == 0) {
          //ddot
          stest1(_blas.ddot(n, sx, _incx, sy, _incy), dt7[knjava][ki], ssize1[knjava], sfac);
        } else if (_icase == 1) {
          // daxpy
          _blas.daxpy(n, sa, sx, _incx, sy, _incy);
          for (int j = 0; j < leny; j++) {
            sty[j] = dt8[j][knjava][ki];
          }
          double[] tmp = new double[leny]; // Fortran uses a naughty pointer access to do this, Fortran rocks
          for (int j = 0; j < leny; j++) {
            tmp[j] = ssize2[0][ksize];
          }
          stest(leny, sy, sty, tmp, sfac);
        } else if (_icase == 4) {
          // dcopy
          for (int j = 0; j < leny; j++) {
            sty[j] = dt10y[j][knjava][ki];
          }
          _blas.dcopy(n, sx, _incx, sy, _incy);
          double[] tmp = new double[leny]; // Fortran uses a naughty pointer access to do this, Fortran rocks         
          for (int j = 0; j < leny; j++) {
            tmp[j] = ssize2[0][0];
          }
          stest(leny, sy, sty, tmp, 1.0e0);
        } else if (_icase == 5) {
          _blas.dswap(n, sx, _incx, sy, _incy);
          for (int i = 0; i < 7; i++) {
            stx[i] = dt10x[i][knjava][ki];
            sty[i] = dt10y[i][knjava][ki];
          }
          double[] tmp;
          tmp = new double[lenx]; // Fortran uses a naughty pointer access to do this, Fortran rocks         
          for (int j = 0; j < lenx; j++) {
            tmp[j] = ssize2[0][0];
          }
          stest(lenx, sx, stx, tmp, 1.0e0);
          tmp = new double[leny]; // Fortran uses a naughty pointer access to do this, Fortran rocks         
          for (int j = 0; j < leny; j++) {
            tmp[j] = ssize2[0][0];
          }
          stest(leny, sy, sty, tmp, 1.0e0);
        }

      }

    }
  }
  
  private void check3(double sfac) {
    int mx, my;
    int[] incxs = {1, 2, -2, -1 };
    int[] incys = {1, -2, 1, -2 };
    int[] ns = {0, 1, 2, 4 };
    int[][] lens = { {1, 1 }, {1, 1 }, {2, 3 }, {4, 7 } };
    int n, lenx, leny, ksize;

    double[][][] dt9x = {
        { {0.6e0, 0.6e0, 0.6e0, 0.6e0 }, {0.78e0, 0.78e0, 0.78e0, 0.78e0 },
        {0.78e0, 0.66e0, -0.06e0, 0.78e0 }, {0.78e0, 0.96e0, 0.9e0, 0.78e0 } },
        { {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, 0.e00, 0.e00, 0.e00 },
        {-0.46e0, 0.1e0, 0.1e0, 0.26e0 }, {-0.46e0, 0.1e0, 0.1e0, 0.26e0 } },
        { {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, 0.e00, 0.e00, 0.e00 },
        {0.e00, -0.1e0, -0.1e0, 0.e00 }, {-0.22e0, -0.76e0, -0.22e0, -0.76e0 } },
        { {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, 0.e00, 0.e00, 0.e00 },
        {0.e00, 0.e00, 0.e00, 0.e00 }, {1.06e0, 0.8e0, 0.8e0, 1.12e0 } },
        { {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, 0.e00, 0.e00, 0.e00 },
        {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, 0.9e0, 0.18e0, 0.e00 } },
        { {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, 0.e00, 0.e00, 0.e00 },
        {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, -0.3e0, -0.3e0, 0.e00 } },
        { {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, 0.e00, 0.e00, 0.e00 },
        {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, -0.02e0, -0.02e0, 0.e00 } } };

    double[][][] dt9y = {
        { {0.5e0, 0.5e0, 0.5e0, 0.5e0 }, {0.04e0, 0.04e0, 0.04e0, 0.04e0 },
        {0.04e0, 0.7e0, 0.7e0, 0.04e0 }, {0.04e0, 0.64e0, 0.64e0, 0.04e0 } },
        { {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, 0.e00, 0.e00, 0.e00 },
        {-0.78e0, -0.9e0, -1.08e0, -0.9e0 }, {-0.78e0, -0.9e0, -1.26e0, -0.9e0 } },
        { {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, 0.e00, 0.e00, 0.e00 },
        {0.e00, -0.12e0, 0.e00, 0.18e0 }, {0.54e0, -0.3e0, 0.54e0, 0.18e0 } },
        { {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, 0.e00, 0.e00, 0.e00 },
        {0.e00, 0.e00, 0.e00, 0.e00 }, {0.08e0, 0.7e0, 0.2e0, 0.7e0 } },
        { {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, 0.e00, 0.e00, 0.e00 },
        {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, -0.18e0, 0.e00, -0.18e0 } },
        { {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, 0.e00, 0.e00, 0.e00 },
        {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, 0.2e0, 0.e00, 0.2e0 } },
        { {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, 0.e00, 0.e00, 0.e00 },
        {0.e00, 0.e00, 0.e00, 0.e00 }, {0.e00, 0.28e0, 0.e00, 0.16e0 } } };

    double[][] ssize2 = { {0.e00, 1.17e0 },
        {0.e00, 1.17e0 },
        {0.e00, 1.17e0 },
        {0.e00, 1.17e0 },
        {0.e00, 1.17e0 },
        {0.e00, 1.17e0 },
        {0.e00, 1.17e0 },
        {0.e00, 1.17e0 },
        {0.e00, 1.17e0 },
        {0.e00, 1.17e0 },
        {0.e00, 1.17e0 },
        {0.e00, 1.17e0 },
        {0.e00, 1.17e0 },
        {0.e00, 1.17e0 } };

    double[] dx1 = {0.6e0, 0.1e0, -0.5e0, 0.8e0, 0.9e0, -0.3e0, -0.4e0 };
    double[] dy1 = {0.5e0, -0.9e0, 0.3e0, 0.7e0, -0.6e0, 0.2e0, 0.8e0 };

    double[] mwpc = new double[11], mwps = new double[11], mwpstx = new double[5];
    double[] mwpsty = new double[5], mwpx = new double[5], mwpy = new double[5];
    double[][] mwptx = new double[11][5], mwpty = new double[11][5];
    double[] copyx = new double[5], copyy = new double[5];

    int[] mwpinx = new int[11], mwpiny = new int[11], mwpn = new int[11];

    double[] sx = new double[7], sy = new double[7], stx = new double[7], sty = new double[7];
    double sc = 0.8e0, ss = 0.6e0;
    for (int ki = 0; ki < 4; ki++) {
      _incx = incxs[ki];
      _incy = incys[ki];
      mx = Math.abs(_incx) - 1; // fortran 1 indexed
      my = Math.abs(_incy) - 1; // fortran 1 indexed

      for (int kn = 0; kn < 4; kn++) {
        n = ns[kn];
        ksize = Math.min(1, kn);
        lenx = lens[kn][mx];
        leny = lens[kn][my];
        if (_icase == 4) {
          // drot
          for (int i = 0; i < 7; i++) {
            sx[i] = dx1[i];
            sy[i] = dy1[i];
            stx[i] = dt9x[i][kn][ki];
            sty[i] = dt9y[i][kn][ki];
          }
          _blas.drot(n, sx, _incx, sy, _incy, sc, ss);
          double[] tmp;
          tmp = new double[lenx]; // Fortran uses a naughty pointer access to do this, Fortran rocks         
          for (int j = 0; j < lenx; j++) {
            tmp[j] = ssize2[0][0];
          }
          stest(lenx, sx, stx, tmp, sfac);

          tmp = new double[leny]; // Fortran uses a naughty pointer access to do this, Fortran rocks         
          for (int j = 0; j < leny; j++) {
            tmp[j] = ssize2[0][0];
          }
          stest(leny, sy, sty, tmp, sfac);
        }

      }

    }

    mwpc[0] = 1;
    for (int i = 1; i < 6; i++) {
      mwps[i] = 1;
    }
    for (int i = 6; i < 11; i++) {
      mwps[i] = -1;
    }
    mwpinx[0] = 1;
    mwpinx[1] = 1;
    mwpinx[2] = 1;
    mwpinx[3] = -1;
    mwpinx[4] = 1;
    mwpinx[5] = -1;
    mwpinx[6] = 1;
    mwpinx[7] = 1;
    mwpinx[8] = -1;
    mwpinx[9] = 1;
    mwpinx[10] = -1;
    mwpiny[0] = 1;
    mwpiny[1] = 1;
    mwpiny[2] = -1;
    mwpiny[3] = -1;
    mwpiny[4] = 2;
    mwpiny[5] = 1;
    mwpiny[6] = 1;
    mwpiny[7] = -1;
    mwpiny[8] = -1;
    mwpiny[9] = 2;
    mwpiny[10] = 1;
    for (int i = 0; i < 11; i++) {
      mwpn[i] = 5;
    }
    mwpn[4] = 3;
    mwpn[9] = 3;

    int iftn;
    for (int i = 0; i < 5; i++) {
      iftn = i + 1;
      mwpx[i] = iftn;
      mwpy[i] = iftn;
      mwptx[0][i] = iftn;
      mwpty[0][i] = iftn;
      mwptx[1][i] = iftn;
      mwpty[1][i] = -iftn;
      mwptx[2][i] = 6 - iftn;
      mwpty[2][i] = iftn - 6;
      mwptx[3][i] = iftn;
      mwpty[3][i] = -iftn;
      mwptx[5][i] = 6 - iftn;
      mwpty[5][i] = iftn - 6;
      mwptx[6][i] = -iftn;
      mwpty[6][i] = iftn;
      mwptx[7][i] = iftn - 6;
      mwpty[7][i] = 6 - iftn;
      mwptx[8][i] = -iftn;
      mwpty[8][i] = iftn;
      mwptx[10][i] = iftn - 6;
      mwpty[10][i] = 6 - iftn;
    }
    mwptx[4][0] = 1;
    mwptx[4][1] = 3;
    mwptx[4][2] = 5;
    mwptx[4][3] = 4;
    mwptx[4][4] = 5;
    mwpty[4][0] = -1;
    mwpty[4][1] = 2;
    mwpty[4][2] = -2;
    mwpty[4][3] = 4;
    mwpty[4][4] = -3;
    mwptx[9][0] = -1;
    mwptx[9][1] = -3;
    mwptx[9][2] = -5;
    mwptx[9][3] = 4;
    mwptx[9][4] = 5;
    mwpty[9][0] = 1;
    mwpty[9][1] = 2;
    mwpty[9][2] = 2;
    mwpty[9][3] = 4;
    mwpty[9][4] = 3;

    for (int i = 0; i < 11; i++) {
      _incx = mwpinx[i];
      _incy = mwpiny[i];
      for (int k = 0; k < 5; k++) {
        copyx[k] = mwpx[k];
        copyy[k] = mwpy[k];
        mwpstx[k] = mwptx[i][k];
        mwpsty[k] = mwpty[i][k];
      }
      _blas.drot(mwpn[i], copyx, _incx, copyy, _incy, mwpc[i], mwps[i]);
      stest(5, copyx, mwpstx, mwpstx, sfac);
      stest(5, copyy, mwpsty, mwpsty, sfac);
    }
  }

  private void stest(int len, double[] scomp, double[] strue, double[] ssize, double sfac) {
    double sd;
    for (int i = 0; i < len; i++) {
      sd = scomp[i] - strue[i];
      if (sdiff(Math.abs(ssize[i]) + Math.abs(sfac * sd), Math.abs(ssize[i])) != 0.d) {
        _pass = false;
        System.out.println("FAIL");
        System.out.println("case" + _icase);
        System.out.println("N" + _n);
        System.out.println("incx" + _incx);
        System.out.println("incy" + _incy);
        System.out.println("mode" + _mode);
        System.out.println("i" + i);
        System.out.println("comp[i]" + scomp[i]);
        System.out.println("true[i]" + strue[i]);        
        System.out.println("size[i]" + ssize[i]);
        System.out.println("absolute difference= " + Math.abs(sdiff(Math.abs(ssize[i]) + Math.abs(sfac * sd), Math.abs(ssize[i]))));
        throw new MathsExceptionGeneric("BLAS TEST FAILED");
      }
    }

  }

  private void stest1(double scomp1, double strue1, double ssize, double sfac) {
    stest(1, new double[] {scomp1 }, new double[] {strue1 }, new double[] {ssize }, sfac);
  }

  private double sdiff(double sa, double sb) {
    return sa - sb;
  }

  private void itest1(int icomp, int itrue) {
    if (icomp != itrue) {
      _pass = false;
      System.out.println("FAIL");
      System.out.println("case" + _icase);
      System.out.println("N" + _n);
      System.out.println("incx" + _incx);
      System.out.println("incy" + _incy);
      System.out.println("mode" + _mode);
      System.out.println("comp[i]" + icomp);
      System.out.println("true[i]" + itrue);
      System.out.println("absolute difference= " + Math.abs(icomp - itrue));
      throw new MathsExceptionGeneric("BLAS TEST FAILED");
    }
  }

}
