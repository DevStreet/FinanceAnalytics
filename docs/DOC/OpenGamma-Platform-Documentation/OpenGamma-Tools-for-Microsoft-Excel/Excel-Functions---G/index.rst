title: Excel Functions - G
shortcut: DOC:Excel Functions - G
---
GapPayoffStyle

..............
GapPayoffStyle
..............


Returns an object representing a 'gap' option payoff style.



+-----------+-------------+
| Parameter | Description |
+===========+=============+
| payment   | The payment |
+-----------+-------------+



GetAvailableOutputs

...................
GetAvailableOutputs
...................


Estimates the value requirements that can be calculated on the positions and securities within a portfolio.



+----------------+------------------------------------------------------------------------+
| Parameter      | Description                                                            |
+================+========================================================================+
| portfolio      | The portfolio to evaluate                                              |
+----------------+------------------------------------------------------------------------+
| nodeSample     | The number of sub-nodes to sample, omit for the default sampling depth |
+----------------+------------------------------------------------------------------------+
| positionSample | The number of positions to sample in each node, omit for the default   |
+----------------+------------------------------------------------------------------------+
| evaluationTime | The time to evaluate functions at, omit for the current time           |
+----------------+------------------------------------------------------------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

GetBondFutureDeliverableConversionFactor

........................................
GetBondFutureDeliverableConversionFactor
........................................


Returns the conversion factor from a deliverable for a bond future.



+-----------------------+------------------------------------------+
| Parameter             | Description                              |
+=======================+==========================================+
| bondFutureDeliverable | A deliverable for a bond future to query |
+-----------------------+------------------------------------------+



GetBondFutureDeliverableIdentifiers

...................................
GetBondFutureDeliverableIdentifiers
...................................


Returns the identifier bundle describing the deliverable from a deliverable for a bond future.



+-----------------------+------------------------------------------+
| Parameter             | Description                              |
+=======================+==========================================+
| bondFutureDeliverable | A deliverable for a bond future to query |
+-----------------------+------------------------------------------+



GetCurveName

............
GetCurveName
............


Returns the display name from a curve.



+-----------+------------------+
| Parameter | Description      |
+===========+==================+
| curve     | A curve to query |
+-----------+------------------+



GetCurveXData

.............
GetCurveXData
.............


Returns the X data points from a curve.



+-----------+------------------+
| Parameter | Description      |
+===========+==================+
| curve     | A curve to query |
+-----------+------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

GetCurveYData

.............
GetCurveYData
.............


Returns the Y data points from a curve.



+-----------+------------------+
| Parameter | Description      |
+===========+==================+
| curve     | A curve to query |
+-----------+------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

GetCurveYValues

...............
GetCurveYValues
...............


Returns a set of Y values from a curve.



+-----------+----------------------------------------------------------------------------+
| Parameter | Description                                                                |
+===========+============================================================================+
| curve     | A curve to query (currently only "double/double" curves are supported)     |
+-----------+----------------------------------------------------------------------------+
| xValues   | The set of X values to query - the Y values are returned in the same order |
+-----------+----------------------------------------------------------------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

GetFixedInterestRateLegRate

...........................
GetFixedInterestRateLegRate
...........................


Returns the fixed interest rate as a decimal (e.g. 5% = 0.05) from a fixed interest rate leg.



+----------------------+------------------------------------+
| Parameter            | Description                        |
+======================+====================================+
| fixedInterestRateLeg | A fixed interest rate leg to query |
+----------------------+------------------------------------+



GetFixedVarianceSwapLegStrike

.............................
GetFixedVarianceSwapLegStrike
.............................


Returns the the strike of the variance swap from a fixed leg of a variance swap.



+----------------------+-----------------------------------------+
| Parameter            | Description                             |
+======================+=========================================+
| fixedVarianceSwapLeg | A fixed leg of a variance swap to query |
+----------------------+-----------------------------------------+



GetFixedVarianceSwapLegType

...........................
GetFixedVarianceSwapLegType
...........................


Returns the the type of the variance swap from a fixed leg of a variance swap.



+----------------------+-----------------------------------------+
| Parameter            | Description                             |
+======================+=========================================+
| fixedVarianceSwapLeg | A fixed leg of a variance swap to query |
+----------------------+-----------------------------------------+



GetFloatingGearingIRLegGearing

..............................
GetFloatingGearingIRLegGearing
..............................


Returns the gearing from a floating gearing interest rate leg of a swap.



+----------------------+---------------------------------------------------------+
| Parameter            | Description                                             |
+======================+=========================================================+
| floatingGearingIRLeg | A floating gearing interest rate leg of a swap to query |
+----------------------+---------------------------------------------------------+



GetFloatingInterestRateLegFloatingRateType

..........................................
GetFloatingInterestRateLegFloatingRateType
..........................................


Returns the floating rate type from a floating interest rate leg of a swap.



+-------------------------+-------------------------------------------------+
| Parameter               | Description                                     |
+=========================+=================================================+
| floatingInterestRateLeg | A floating interest rate leg of a swap to query |
+-------------------------+-------------------------------------------------+



GetFloatingInterestRateLegFloatingReferenceRateId

.................................................
GetFloatingInterestRateLegFloatingReferenceRateId
.................................................


Returns the identifier of the object used to provide the reference rate from a floating interest rate leg of a swap.



+-------------------------+-------------------------------------------------+
| Parameter               | Description                                     |
+=========================+=================================================+
| floatingInterestRateLeg | A floating interest rate leg of a swap to query |
+-------------------------+-------------------------------------------------+



GetFloatingInterestRateLegInitialFloatingRate

.............................................
GetFloatingInterestRateLegInitialFloatingRate
.............................................


Returns the floating rate of the first period of the swap (expressed as a decimal) from a floating interest rate leg of a swap.



+-------------------------+-------------------------------------------------+
| Parameter               | Description                                     |
+=========================+=================================================+
| floatingInterestRateLeg | A floating interest rate leg of a swap to query |
+-------------------------+-------------------------------------------------+



GetFloatingInterestRateLegOffsetFixing

......................................
GetFloatingInterestRateLegOffsetFixing
......................................


Returns the offset fixing frequency from a floating interest rate leg of a swap.



+-------------------------+-------------------------------------------------+
| Parameter               | Description                                     |
+=========================+=================================================+
| floatingInterestRateLeg | A floating interest rate leg of a swap to query |
+-------------------------+-------------------------------------------------+



GetFloatingInterestRateLegSettlementDays

........................................
GetFloatingInterestRateLegSettlementDays
........................................


Returns the settlement days from a floating interest rate leg of a swap.



+-------------------------+-------------------------------------------------+
| Parameter               | Description                                     |
+=========================+=================================================+
| floatingInterestRateLeg | A floating interest rate leg of a swap to query |
+-------------------------+-------------------------------------------------+



GetFloatingSpreadIRLegSpread

............................
GetFloatingSpreadIRLegSpread
............................


Returns the spread from a floating spread interest rate leg of a swap.



+---------------------+--------------------------------------------------------+
| Parameter           | Description                                            |
+=====================+========================================================+
| floatingSpreadIRLeg | A floating spread interest rate leg of a swap to query |
+---------------------+--------------------------------------------------------+



GetFloatingVarianceSwapLegAnnualizationFactor

.............................................
GetFloatingVarianceSwapLegAnnualizationFactor
.............................................


Returns the the annualization factor from a floating leg of a variance swap.



+-------------------------+--------------------------------------------+
| Parameter               | Description                                |
+=========================+============================================+
| floatingVarianceSwapLeg | A floating leg of a variance swap to query |
+-------------------------+--------------------------------------------+



GetFloatingVarianceSwapLegMonitoringFrequency

.............................................
GetFloatingVarianceSwapLegMonitoringFrequency
.............................................


Returns the the monitoring frequency of the swap from a floating leg of a variance swap.



+-------------------------+--------------------------------------------+
| Parameter               | Description                                |
+=========================+============================================+
| floatingVarianceSwapLeg | A floating leg of a variance swap to query |
+-------------------------+--------------------------------------------+



GetFloatingVarianceSwapLegUnderlyingId

......................................
GetFloatingVarianceSwapLegUnderlyingId
......................................


Returns the the identifier of the underlying from a floating leg of a variance swap.



+-------------------------+--------------------------------------------+
| Parameter               | Description                                |
+=========================+============================================+
| floatingVarianceSwapLeg | A floating leg of a variance swap to query |
+-------------------------+--------------------------------------------+



GetInterestRateNotionalAmount

.............................
GetInterestRateNotionalAmount
.............................


Returns the amount from a notional value of an interest rate leg of a swap.



+----------------------+-------------------------------------------------------------+
| Parameter            | Description                                                 |
+======================+=============================================================+
| interestRateNotional | A notional value of an interest rate leg of a swap to query |
+----------------------+-------------------------------------------------------------+



GetInterestRateNotionalCurrency

...............................
GetInterestRateNotionalCurrency
...............................


Returns the currency from a notional value of an interest rate leg of a swap.



+----------------------+-------------------------------------------------------------+
| Parameter            | Description                                                 |
+======================+=============================================================+
| interestRateNotional | A notional value of an interest rate leg of a swap to query |
+----------------------+-------------------------------------------------------------+



GetPortfolioName

................
GetPortfolioName
................


Returns the display name from a portfolio.



+-----------+----------------------+
| Parameter | Description          |
+===========+======================+
| portfolio | A portfolio to query |
+-----------+----------------------+



GetPortfolioNodeChildNodes

..........................
GetPortfolioNodeChildNodes
..........................


Returns the child portfolio nodes from a portfolio node.



+---------------+---------------------------+
| Parameter     | Description               |
+===============+===========================+
| portfolioNode | A portfolio node to query |
+---------------+---------------------------+



GetPortfolioNodeName

....................
GetPortfolioNodeName
....................


Returns the display name from a portfolio node.



+---------------+---------------------------+
| Parameter     | Description               |
+===============+===========================+
| portfolioNode | A portfolio node to query |
+---------------+---------------------------+



GetPortfolioNodeParentNodeId

............................
GetPortfolioNodeParentNodeId
............................


Returns the parent node unique identifier from a portfolio node.



+---------------+---------------------------+
| Parameter     | Description               |
+===============+===========================+
| portfolioNode | A portfolio node to query |
+---------------+---------------------------+



GetPortfolioNodePositions

.........................
GetPortfolioNodePositions
.........................


Returns the immediate child positions from a portfolio node.



+---------------+---------------------------+
| Parameter     | Description               |
+===============+===========================+
| portfolioNode | A portfolio node to query |
+---------------+---------------------------+



GetPortfolioNodeUniqueId

........................
GetPortfolioNodeUniqueId
........................


Returns the unique identifier from a portfolio node.



+---------------+---------------------------+
| Parameter     | Description               |
+===============+===========================+
| portfolioNode | A portfolio node to query |
+---------------+---------------------------+



GetPortfolioRootNode

....................
GetPortfolioRootNode
....................


Returns the root node from a portfolio.



+-----------+----------------------+
| Parameter | Description          |
+===========+======================+
| portfolio | A portfolio to query |
+-----------+----------------------+



GetPositionAttribute

....................
GetPositionAttribute
....................


Queries an attribute on a position.



+-----------+------------------------+
| Parameter | Description            |
+===========+========================+
| position  | The position to query  |
+-----------+------------------------+
| attribute | The attribute to query |
+-----------+------------------------+



GetPositionAttributes

.....................
GetPositionAttributes
.....................


Returns the aggregation attributes from a position.



+-----------+---------------------+
| Parameter | Description         |
+===========+=====================+
| position  | A position to query |
+-----------+---------------------+



GetPositionParentNodeId

.......................
GetPositionParentNodeId
.......................


Returns the parent node unique identifier from a position.



+-----------+---------------------+
| Parameter | Description         |
+===========+=====================+
| position  | A position to query |
+-----------+---------------------+



GetPositionTrades

.................
GetPositionTrades
.................


Returns the immediate child trades from a position.



+-----------+---------------------+
| Parameter | Description         |
+===========+=====================+
| position  | A position to query |
+-----------+---------------------+



GetSnapshotBasisViewName

........................
GetSnapshotBasisViewName
........................


Fetches the view name the snapshot was originally based on.



+-----------+-----------------------+
| Parameter | Description           |
+===========+=======================+
| snapshot  | The snapshot to query |
+-----------+-----------------------+



GetSnapshotGlobalValue

......................
GetSnapshotGlobalValue
......................


Fetches a global value from a market data values.



+------------+--------------------------------+
| Parameter  | Description                    |
+============+================================+
| snapshot   | No description available       |
+------------+--------------------------------+
| valueName  | The value name to fetch        |
+------------+--------------------------------+
| identifier | The target identifier to fetch |
+------------+--------------------------------+



GetSnapshotName

...............
GetSnapshotName
...............


Fetches the name of a snapshot.



+-----------+-----------------------+
| Parameter | Description           |
+===========+=======================+
| snapshot  | The snapshot to query |
+-----------+-----------------------+



GetSnapshotVolatilityCube

.........................
GetSnapshotVolatilityCube
.........................


Fetches a volatility cube values from a market data values.



+-----------+-------------------------------+
| Parameter | Description                   |
+===========+===============================+
| snapshot  | No description available      |
+-----------+-------------------------------+
| name      | The name of the cube to fetch |
+-----------+-------------------------------+



GetSnapshotVolatilitySurface

............................
GetSnapshotVolatilitySurface
............................


Fetches a volatility surface values from a market data values object.



+-----------+---------------------------------------------------------------------+
| Parameter | Description                                                         |
+===========+=====================================================================+
| snapshot  | No description available                                            |
+-----------+---------------------------------------------------------------------+
| name      | The surface name to fetch, omit to fetch a list of all values names |
+-----------+---------------------------------------------------------------------+



GetSnapshotYieldCurve

.....................
GetSnapshotYieldCurve
.....................


Fetches a yield curve values from a market data values.



+-----------+-------------------------------------------------------------------------------------+
| Parameter | Description                                                                         |
+===========+=====================================================================================+
| snapshot  | No description available                                                            |
+-----------+-------------------------------------------------------------------------------------+
| name      | The name of the yield curve to fetch, omit to fetch a list of available curve names |
+-----------+-------------------------------------------------------------------------------------+



GetSnapshotYieldCurveValuationTime

..................................
GetSnapshotYieldCurveValuationTime
..................................


Returns the valuation time from a market data snapshot yield curve.



+--------------------+---------------------------------------------+
| Parameter          | Description                                 |
+====================+=============================================+
| snapshotYieldCurve | A market data snapshot yield curve to query |
+--------------------+---------------------------------------------+



GetSwapLegBusinessDayConvention

...............................
GetSwapLegBusinessDayConvention
...............................


Returns the business day convention from a leg of a swap.



+-----------+--------------------------+
| Parameter | Description              |
+===========+==========================+
| swapLeg   | A leg of a swap to query |
+-----------+--------------------------+



GetSwapLegDayCount

..................
GetSwapLegDayCount
..................


Returns the dayCount from a leg of a swap.



+-----------+--------------------------+
| Parameter | Description              |
+===========+==========================+
| swapLeg   | A leg of a swap to query |
+-----------+--------------------------+



GetSwapLegEom

.............
GetSwapLegEom
.............


Returns the EOM flag from a leg of a swap.



+-----------+--------------------------+
| Parameter | Description              |
+===========+==========================+
| swapLeg   | A leg of a swap to query |
+-----------+--------------------------+



GetSwapLegFrequency

...................
GetSwapLegFrequency
...................


Returns the payment frequency from a leg of a swap.



+-----------+--------------------------+
| Parameter | Description              |
+===========+==========================+
| swapLeg   | A leg of a swap to query |
+-----------+--------------------------+



GetSwapLegNotional

..................
GetSwapLegNotional
..................


Returns the notional from a leg of a swap.



+-----------+--------------------------+
| Parameter | Description              |
+===========+==========================+
| swapLeg   | A leg of a swap to query |
+-----------+--------------------------+



GetSwapLegRegionId

..................
GetSwapLegRegionId
..................


Returns the region identifier from a leg of a swap.



+-----------+--------------------------+
| Parameter | Description              |
+===========+==========================+
| swapLeg   | A leg of a swap to query |
+-----------+--------------------------+



GetTradeDate

............
GetTradeDate
............


Returns the trade date from a trade.



+-----------+------------------+
| Parameter | Description      |
+===========+==================+
| trade     | A trade to query |
+-----------+------------------+



GetTradeParentPositionId

........................
GetTradeParentPositionId
........................


Returns the parent position unique identifier from a trade.



+-----------+------------------+
| Parameter | Description      |
+===========+==================+
| trade     | A trade to query |
+-----------+------------------+



GetTradePremium

...............
GetTradePremium
...............


Returns the payment amount from a trade.



+-----------+------------------+
| Parameter | Description      |
+===========+==================+
| trade     | A trade to query |
+-----------+------------------+



GetTradePremiumCurrency

.......................
GetTradePremiumCurrency
.......................


Returns the payment currency from a trade.



+-----------+------------------+
| Parameter | Description      |
+===========+==================+
| trade     | A trade to query |
+-----------+------------------+



GetTradePremiumDate

...................
GetTradePremiumDate
...................


Returns the payment date from a trade.



+-----------+------------------+
| Parameter | Description      |
+===========+==================+
| trade     | A trade to query |
+-----------+------------------+



GetTradePremiumTime

...................
GetTradePremiumTime
...................


Returns the payment time from a trade.



+-----------+------------------+
| Parameter | Description      |
+===========+==================+
| trade     | A trade to query |
+-----------+------------------+



GetTradeTime

............
GetTradeTime
............


Returns the trade time from a trade.



+-----------+------------------+
| Parameter | Description      |
+===========+==================+
| trade     | A trade to query |
+-----------+------------------+



GetVarianceSwapNotionalAmount

.............................
GetVarianceSwapNotionalAmount
.............................


Returns the the notional amount from a notional value of the leg of a variance swap.



+----------------------+---------------------------------------------------------+
| Parameter            | Description                                             |
+======================+=========================================================+
| varianceSwapNotional | A notional value of the leg of a variance swap to query |
+----------------------+---------------------------------------------------------+



GetVarianceSwapNotionalCurrency

...............................
GetVarianceSwapNotionalCurrency
...............................


Returns the the notional currency from a notional value of the leg of a variance swap.



+----------------------+---------------------------------------------------------+
| Parameter            | Description                                             |
+======================+=========================================================+
| varianceSwapNotional | A notional value of the leg of a variance swap to query |
+----------------------+---------------------------------------------------------+



GetViewPortfolio

................
GetViewPortfolio
................


Returns the identifier of the portfolio associated with the view definition.



+-----------+----------------------------------------------+
| Parameter | Description                                  |
+===========+==============================================+
| id        | The unique identifier of the view definition |
+-----------+----------------------------------------------+



GetVolatilityCubeTensor

.......................
GetVolatilityCubeTensor
.......................


Fetches a 3D matrix describing only the cube values.



+---------------+-----------------------------------------------------------------------------------------------------------------------+
| Parameter     | Description                                                                                                           |
+===============+=======================================================================================================================+
| snapshot      | The volatility cube snapshot object to query                                                                          |
+---------------+-----------------------------------------------------------------------------------------------------------------------+
| marketValue   | True to query the market data values, false to ignore the market data (true if omitted)                               |
+---------------+-----------------------------------------------------------------------------------------------------------------------+
| overrideValue | True to query override data values (in preference to market data), false to ignore override values (false if omitted) |
+---------------+-----------------------------------------------------------------------------------------------------------------------+



GetVolatilitySurfaceTensor

..........................
GetVolatilitySurfaceTensor
..........................


Fetches a 2D matrix describing only the surface values.



+---------------+-----------------------------------------------------------------------------------------------------------------------+
| Parameter     | Description                                                                                                           |
+===============+=======================================================================================================================+
| snapshot      | The volatility surface snapshot object to query                                                                       |
+---------------+-----------------------------------------------------------------------------------------------------------------------+
| marketValue   | True to query the market data values, false to ignore the market data (true if omitted)                               |
+---------------+-----------------------------------------------------------------------------------------------------------------------+
| overrideValue | True to query override data values (in preference to market data), false to ignore override values (false if omitted) |
+---------------+-----------------------------------------------------------------------------------------------------------------------+



GetYieldCurveTensor

...................
GetYieldCurveTensor
...................


Fetches a 1D matrix describing only the curve values.



+---------------+-----------------------------------------------------------------------------------------------------------------------+
| Parameter     | Description                                                                                                           |
+===============+=======================================================================================================================+
| snapshot      | The yield curve snapshot object to query                                                                              |
+---------------+-----------------------------------------------------------------------------------------------------------------------+
| marketValue   | True to query the market data values, false to ignore the market data (true if omitted)                               |
+---------------+-----------------------------------------------------------------------------------------------------------------------+
| overrideValue | True to query override data values (in preference to market data), false to ignore override values (false if omitted) |
+---------------+-----------------------------------------------------------------------------------------------------------------------+



GovernmentBondSecurity

......................
GovernmentBondSecurity
......................


Defines a government bond security. The new security is added to the Security Master and an identifier to it returned.



+---------------------+-------------------------------------------+
| Parameter           | Description                               |
+=====================+===========================================+
| name                | The display name or label of the security |
+---------------------+-------------------------------------------+
| issuerName          | The issuer name                           |
+---------------------+-------------------------------------------+
| issuerType          | The issuer type                           |
+---------------------+-------------------------------------------+
| issuerDomicile      | The issuer domicile                       |
+---------------------+-------------------------------------------+
| market              | The market                                |
+---------------------+-------------------------------------------+
| currency            | The currency                              |
+---------------------+-------------------------------------------+
| yieldConvention     | The yield convention                      |
+---------------------+-------------------------------------------+
| lastTradeDate       | The last trade date                       |
+---------------------+-------------------------------------------+
| couponType          | The coupon type                           |
+---------------------+-------------------------------------------+
| couponRate          | The coupon rate                           |
+---------------------+-------------------------------------------+
| couponFrequency     | The coupon frequency                      |
+---------------------+-------------------------------------------+
| dayCount            | The day count convention                  |
+---------------------+-------------------------------------------+
| interestAccrualDate | The interest accrual date                 |
+---------------------+-------------------------------------------+
| settlementDate      | The settlement date                       |
+---------------------+-------------------------------------------+
| firstCouponDate     | The first coupon date                     |
+---------------------+-------------------------------------------+
| issuancePrice       | The issuance price                        |
+---------------------+-------------------------------------------+
| totalAmountIssued   | The total amount issued                   |
+---------------------+-------------------------------------------+
| minimumAmount       | The minimum amount                        |
+---------------------+-------------------------------------------+
| minimumIncrement    | The minimum increment                     |
+---------------------+-------------------------------------------+
| parAmount           | The par amount                            |
+---------------------+-------------------------------------------+
| redemptionValue     | The redemption value                      |
+---------------------+-------------------------------------------+



GovernmentBondSecurityObject

............................
GovernmentBondSecurityObject
............................


Defines a government bond security.



+---------------------+-------------------------------------------+
| Parameter           | Description                               |
+=====================+===========================================+
| name                | The display name or label of the security |
+---------------------+-------------------------------------------+
| issuerName          | The issuer name                           |
+---------------------+-------------------------------------------+
| issuerType          | The issuer type                           |
+---------------------+-------------------------------------------+
| issuerDomicile      | The issuer domicile                       |
+---------------------+-------------------------------------------+
| market              | The market                                |
+---------------------+-------------------------------------------+
| currency            | The currency                              |
+---------------------+-------------------------------------------+
| yieldConvention     | The yield convention                      |
+---------------------+-------------------------------------------+
| lastTradeDate       | The last trade date                       |
+---------------------+-------------------------------------------+
| couponType          | The coupon type                           |
+---------------------+-------------------------------------------+
| couponRate          | The coupon rate                           |
+---------------------+-------------------------------------------+
| couponFrequency     | The coupon frequency                      |
+---------------------+-------------------------------------------+
| dayCount            | The day count convention                  |
+---------------------+-------------------------------------------+
| interestAccrualDate | The interest accrual date                 |
+---------------------+-------------------------------------------+
| settlementDate      | The settlement date                       |
+---------------------+-------------------------------------------+
| firstCouponDate     | The first coupon date                     |
+---------------------+-------------------------------------------+
| issuancePrice       | The issuance price                        |
+---------------------+-------------------------------------------+
| totalAmountIssued   | The total amount issued                   |
+---------------------+-------------------------------------------+
| minimumAmount       | The minimum amount                        |
+---------------------+-------------------------------------------+
| minimumIncrement    | The minimum increment                     |
+---------------------+-------------------------------------------+
| parAmount           | The par amount                            |
+---------------------+-------------------------------------------+
| redemptionValue     | The redemption value                      |
+---------------------+-------------------------------------------+



