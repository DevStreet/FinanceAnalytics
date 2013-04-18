title: Excel Functions - Y
shortcut: DOC:Excel Functions - Y
---
YieldCurve

..........
YieldCurve
..........


Defines a yield curve from a set of fixed income strips.



+--------------+--------------------------------------------------------------+
| Parameter    | Description                                                  |
+==============+==============================================================+
| name         | The name of the yield curve                                  |
+--------------+--------------------------------------------------------------+
| currency     | The currency in which the curve's securities are denominated |
+--------------+--------------------------------------------------------------+
| region       | The region identifier                                        |
+--------------+--------------------------------------------------------------+
| interpolator | The name of the interpolator to be used                      |
+--------------+--------------------------------------------------------------+
| strips       | The table of strips used to build the yield curve            |
+--------------+--------------------------------------------------------------+



YieldCurveSpec

..............
YieldCurveSpec
..............


Returns details about the strips of a curve.



+-----------+------------------------------------------+
| Parameter | Description                              |
+===========+==========================================+
| currency  | The currency of the curve                |
+-----------+------------------------------------------+
| name      | The name of the curve                    |
+-----------+------------------------------------------+
| date      | The date for which the curve is required |
+-----------+------------------------------------------+



YieldCurveTemplate

..................
YieldCurveTemplate
..................


Generates an argument template for the YieldCurve function.

This function takes no parameters.

When used in a worksheet this function is an *overwriting* operation. After execution, if an error is not returned, the area with top-left cell the formula will be replaced by the returned value(s). Any content in these cells, including the original formula, will be replaced without warning. Any array formulae within the overwritten target area will be discarded, even if they include cells outside of this target area.

