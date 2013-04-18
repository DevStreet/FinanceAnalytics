title: Excel Functions - S
shortcut: DOC:Excel Functions - S
---
SavePortfolio


.............
SavePortfolio
.............


Writes a portfolio and the referenced securities to the shared masters.



+-----------+------------------------------------------------------+
| Parameter | Description                                          |
+===========+======================================================+
| portfolio | The portfolio identifier                             |
+-----------+------------------------------------------------------+
| rename    | New name for the portfolio, omit to use current name |
+-----------+------------------------------------------------------+



SecurityACTIVFEED_TICKER


........................
SecurityACTIVFEED_TICKER
........................


Finds the `ACTIVFEED_TICKER` for a given security.



+-----------+-----------------------+
| Parameter | Description           |
+===========+=======================+
| security  | The security to query |
+-----------+-----------------------+



SecurityBLOOMBERG_BUID


......................
SecurityBLOOMBERG_BUID
......................


Finds the `BLOOMBERG_BUID` for a given security.



+-----------+-----------------------+
| Parameter | Description           |
+===========+=======================+
| security  | The security to query |
+-----------+-----------------------+



SecurityBLOOMBERG_BUID_WEAK


...........................
SecurityBLOOMBERG_BUID_WEAK
...........................


Finds the `BLOOMBERG_BUID_WEAK` for a given security.



+-----------+-----------------------+
| Parameter | Description           |
+===========+=======================+
| security  | The security to query |
+-----------+-----------------------+



SecurityBLOOMBERG_TCM


.....................
SecurityBLOOMBERG_TCM
.....................


Finds the `BLOOMBERG_TCM` for a given security.



+-----------+-----------------------+
| Parameter | Description           |
+===========+=======================+
| security  | The security to query |
+-----------+-----------------------+



SecurityBLOOMBERG_TICKER


........................
SecurityBLOOMBERG_TICKER
........................


Finds the `BLOOMBERG_TICKER` for a given security.



+-----------+-----------------------+
| Parameter | Description           |
+===========+=======================+
| security  | The security to query |
+-----------+-----------------------+



SecurityBLOOMBERG_TICKER_WEAK


.............................
SecurityBLOOMBERG_TICKER_WEAK
.............................


Finds the `BLOOMBERG_TICKER_WEAK` for a given security.



+-----------+-----------------------+
| Parameter | Description           |
+===========+=======================+
| security  | The security to query |
+-----------+-----------------------+



SecurityCOPP_CLARK_LOCODE


.........................
SecurityCOPP_CLARK_LOCODE
.........................


Finds the `COPP_CLARK_LOCODE` for a given security.



+-----------+-----------------------+
| Parameter | Description           |
+===========+=======================+
| security  | The security to query |
+-----------+-----------------------+



SecurityCUSIP


.............
SecurityCUSIP
.............


Finds the CUSIP for a given security.



+-----------+-----------------------+
| Parameter | Description           |
+===========+=======================+
| security  | The security to query |
+-----------+-----------------------+



SecurityExternalIdBundle


........................
SecurityExternalIdBundle
........................


Returns the identifiers for various schemes from a security.



+-----------+---------------------+
| Parameter | Description         |
+===========+=====================+
| security  | A security to query |
+-----------+---------------------+



SecurityFINANCIAL_REGION


........................
SecurityFINANCIAL_REGION
........................


Finds the `FINANCIAL_REGION` for a given security.



+-----------+-----------------------+
| Parameter | Description           |
+===========+=======================+
| security  | The security to query |
+-----------+-----------------------+



SecurityICAP


............
SecurityICAP
............


Finds the ICAP for a given security.



+-----------+-----------------------+
| Parameter | Description           |
+===========+=======================+
| security  | The security to query |
+-----------+-----------------------+



SecurityISIN


............
SecurityISIN
............


Finds the ISIN for a given security.



+-----------+-----------------------+
| Parameter | Description           |
+===========+=======================+
| security  | The security to query |
+-----------+-----------------------+



SecurityISO_COUNTRY_ALPHA2


..........................
SecurityISO_COUNTRY_ALPHA2
..........................


Finds the `ISO_COUNTRY_ALPHA2` for a given security.



+-----------+-----------------------+
| Parameter | Description           |
+===========+=======================+
| security  | The security to query |
+-----------+-----------------------+



SecurityISO_CURRENCY_ALPHA3


...........................
SecurityISO_CURRENCY_ALPHA3
...........................


Finds the `ISO_CURRENCY_ALPHA3` for a given security.



+-----------+-----------------------+
| Parameter | Description           |
+===========+=======================+
| security  | The security to query |
+-----------+-----------------------+



SecurityISO_MIC


...............
SecurityISO_MIC
...............


Finds the `ISO_MIC` for a given security.



+-----------+-----------------------+
| Parameter | Description           |
+===========+=======================+
| security  | The security to query |
+-----------+-----------------------+



SecurityName


............
SecurityName
............


Returns the display name from a security.



+-----------+---------------------+
| Parameter | Description         |
+===========+=====================+
| security  | A security to query |
+-----------+---------------------+



SecurityNotional


................
SecurityNotional
................


Defines a notional value defined as a security .



+------------+---------------------------------------+
| Parameter  | Description                           |
+============+=======================================+
| notionalId | The unique identifier of the security |
+------------+---------------------------------------+



SecurityNotionalNotionalId


..........................
SecurityNotionalNotionalId
..........................


Returns the unique identifier of the security from a notional value defined as a security .



+------------------+--------------------------------------------------+
| Parameter        | Description                                      |
+==================+==================================================+
| securityNotional | A notional value defined as a security  to query |
+------------------+--------------------------------------------------+



SecurityOG_SYNTHETIC_TICKER


...........................
SecurityOG_SYNTHETIC_TICKER
...........................


Finds the `OG_SYNTHETIC_TICKER` for a given security.



+-----------+-----------------------+
| Parameter | Description           |
+===========+=======================+
| security  | The security to query |
+-----------+-----------------------+



SecurityRIC


...........
SecurityRIC
...........


Finds the RIC for a given security.



+-----------+-----------------------+
| Parameter | Description           |
+===========+=======================+
| security  | The security to query |
+-----------+-----------------------+



SecuritySEDOL1


..............
SecuritySEDOL1
..............


Finds the SEDOL1 for a given security.



+-----------+-----------------------+
| Parameter | Description           |
+===========+=======================+
| security  | The security to query |
+-----------+-----------------------+



SecuritySURF


............
SecuritySURF
............


Finds the SURF for a given security.



+-----------+-----------------------+
| Parameter | Description           |
+===========+=======================+
| security  | The security to query |
+-----------+-----------------------+



SecuritySetExternalIdBundle


...........................
SecuritySetExternalIdBundle
...........................


Updates the identifiers for various schemes of a security. The original object is unchanged - a new object is returned with the updated value.



+------------------+-------------------------------------+
| Parameter        | Description                         |
+==================+=====================================+
| security         | A security to update                |
+------------------+-------------------------------------+
| externalIdBundle | The identifiers for various schemes |
+------------------+-------------------------------------+



If only a single identifier should be set, the *identifiers* parameter can be specified as a string (e.g. `OG_SYNTHETIC_TICKER~Example`) or be constructed from one of the Identifier functions. To set multiple identifiers on the security they should be combined using the NO LINK FOUND FOR 'Excel Functions -E' function. For example:



.. code::

    =SecuritySetExternalIdBundle(_security_,ExternalIdBundle(IdentifierOG_SYNTHETIC_TICKER("Example"),IdentifierISIN("...")))




SecuritySetName


...............
SecuritySetName
...............


Updates the display name of a security. The original object is unchanged - a new object is returned with the updated value.



+-----------+----------------------+
| Parameter | Description          |
+===========+======================+
| security  | A security to update |
+-----------+----------------------+
| name      | The display name     |
+-----------+----------------------+



SecuritySetNotionalNotionalId


.............................
SecuritySetNotionalNotionalId
.............................


Updates the unique identifier of the security of a notional value defined as a security . The original object is unchanged - a new object is returned with the updated value.



+------------------+---------------------------------------------------+
| Parameter        | Description                                       |
+==================+===================================================+
| securityNotional | A notional value defined as a security  to update |
+------------------+---------------------------------------------------+
| notionalId       | The unique identifier of the security             |
+------------------+---------------------------------------------------+



SecuritySummary


...............
SecuritySummary
...............


Displays standard summary details about one or more securities.



+------------+-----------------------------------------------+
| Parameter  | Description                                   |
+============+===============================================+
| securities | The table of securities, or a single security |
+------------+-----------------------------------------------+



SecurityTZDB_TIME_ZONE


......................
SecurityTZDB_TIME_ZONE
......................


Finds the `TZDB_TIME_ZONE` for a given security.



+-----------+-----------------------+
| Parameter | Description           |
+===========+=======================+
| security  | The security to query |
+-----------+-----------------------+



SecurityType


............
SecurityType
............


Returns the asset class from a security.



+-----------+---------------------+
| Parameter | Description         |
+===========+=====================+
| security  | A security to query |
+-----------+---------------------+



SecurityUID


...........
SecurityUID
...........


Finds the UID for a given security.



+-----------+-----------------------+
| Parameter | Description           |
+===========+=======================+
| security  | The security to query |
+-----------+-----------------------+



SecurityUN_LOCODE_2010_2


........................
SecurityUN_LOCODE_2010_2
........................


Finds the `UN_LOCODE_2010_2` for a given security.



+-----------+-----------------------+
| Parameter | Description           |
+===========+=======================+
| security  | The security to query |
+-----------+-----------------------+



SetBondFutureDeliverableConversionFactor


........................................
SetBondFutureDeliverableConversionFactor
........................................


Updates the conversion factor of a deliverable for a bond future. The original object is unchanged - a new object is returned with the updated value.



+-----------------------+-------------------------------------------+
| Parameter             | Description                               |
+=======================+===========================================+
| bondFutureDeliverable | A deliverable for a bond future to update |
+-----------------------+-------------------------------------------+
| conversionFactor      | The conversion factor                     |
+-----------------------+-------------------------------------------+



SetBondFutureDeliverableIdentifiers


...................................
SetBondFutureDeliverableIdentifiers
...................................


Updates the identifier bundle describing the deliverable of a deliverable for a bond future. The original object is unchanged - a new object is returned with the updated value.



+-----------------------+--------------------------------------------------+
| Parameter             | Description                                      |
+=======================+==================================================+
| bondFutureDeliverable | A deliverable for a bond future to update        |
+-----------------------+--------------------------------------------------+
| identifiers           | The identifier bundle describing the deliverable |
+-----------------------+--------------------------------------------------+



SetFixedInterestRateLegRate


...........................
SetFixedInterestRateLegRate
...........................


Updates the fixed interest rate as a decimal (e.g. 5% = 0.05) of a fixed interest rate leg. The original object is unchanged - a new object is returned with the updated value.



+----------------------+-------------------------------------------------------+
| Parameter            | Description                                           |
+======================+=======================================================+
| fixedInterestRateLeg | A fixed interest rate leg to update                   |
+----------------------+-------------------------------------------------------+
| rate                 | The fixed interest rate as a decimal (e.g. 5% = 0.05) |
+----------------------+-------------------------------------------------------+



SetFixedVarianceSwapLegStrike


.............................
SetFixedVarianceSwapLegStrike
.............................


Updates the the strike of the variance swap of a fixed leg of a variance swap. The original object is unchanged - a new object is returned with the updated value.



+----------------------+------------------------------------------+
| Parameter            | Description                              |
+======================+==========================================+
| fixedVarianceSwapLeg | A fixed leg of a variance swap to update |
+----------------------+------------------------------------------+
| strike               | The the strike of the variance swap      |
+----------------------+------------------------------------------+



SetFixedVarianceSwapLegType


...........................
SetFixedVarianceSwapLegType
...........................


Updates the the type of the variance swap of a fixed leg of a variance swap. The original object is unchanged - a new object is returned with the updated value.



+----------------------+------------------------------------------+
| Parameter            | Description                              |
+======================+==========================================+
| fixedVarianceSwapLeg | A fixed leg of a variance swap to update |
+----------------------+------------------------------------------+
| type                 | The the type of the variance swap        |
+----------------------+------------------------------------------+



SetFloatingGearingIRLegGearing


..............................
SetFloatingGearingIRLegGearing
..............................


Updates the gearing of a floating gearing interest rate leg of a swap. The original object is unchanged - a new object is returned with the updated value.



+----------------------+----------------------------------------------------------+
| Parameter            | Description                                              |
+======================+==========================================================+
| floatingGearingIRLeg | A floating gearing interest rate leg of a swap to update |
+----------------------+----------------------------------------------------------+
| gearing              | The gearing                                              |
+----------------------+----------------------------------------------------------+



SetFloatingInterestRateLegFloatingRateType


..........................................
SetFloatingInterestRateLegFloatingRateType
..........................................


Updates the floating rate type of a floating interest rate leg of a swap. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+--------------------------------------------------+
| Parameter               | Description                                      |
+=========================+==================================================+
| floatingInterestRateLeg | A floating interest rate leg of a swap to update |
+-------------------------+--------------------------------------------------+
| floatingRateType        | The floating rate type                           |
+-------------------------+--------------------------------------------------+



SetFloatingInterestRateLegFloatingReferenceRateId


.................................................
SetFloatingInterestRateLegFloatingReferenceRateId
.................................................


Updates the identifier of the object used to provide the reference rate of a floating interest rate leg of a swap. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+-----------------------------------------------------------------+
| Parameter               | Description                                                     |
+=========================+=================================================================+
| floatingInterestRateLeg | A floating interest rate leg of a swap to update                |
+-------------------------+-----------------------------------------------------------------+
| floatingReferenceRateId | The identifier of the object used to provide the reference rate |
+-------------------------+-----------------------------------------------------------------+



SetFloatingInterestRateLegInitialFloatingRate


.............................................
SetFloatingInterestRateLegInitialFloatingRate
.............................................


Updates the floating rate of the first period of the swap (expressed as a decimal) of a floating interest rate leg of a swap. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------------------------------------------------------------------------+
| Parameter               | Description                                                                |
+=========================+============================================================================+
| floatingInterestRateLeg | A floating interest rate leg of a swap to update                           |
+-------------------------+----------------------------------------------------------------------------+
| initialFloatingRate     | The floating rate of the first period of the swap (expressed as a decimal) |
+-------------------------+----------------------------------------------------------------------------+



SetFloatingInterestRateLegOffsetFixing


......................................
SetFloatingInterestRateLegOffsetFixing
......................................


Updates the offset fixing frequency of a floating interest rate leg of a swap. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+--------------------------------------------------+
| Parameter               | Description                                      |
+=========================+==================================================+
| floatingInterestRateLeg | A floating interest rate leg of a swap to update |
+-------------------------+--------------------------------------------------+
| offsetFixing            | The offset fixing frequency                      |
+-------------------------+--------------------------------------------------+



SetFloatingInterestRateLegSettlementDays


........................................
SetFloatingInterestRateLegSettlementDays
........................................


Updates the settlement days of a floating interest rate leg of a swap. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+--------------------------------------------------+
| Parameter               | Description                                      |
+=========================+==================================================+
| floatingInterestRateLeg | A floating interest rate leg of a swap to update |
+-------------------------+--------------------------------------------------+
| settlementDays          | The settlement days                              |
+-------------------------+--------------------------------------------------+



SetFloatingSpreadIRLegSpread


............................
SetFloatingSpreadIRLegSpread
............................


Updates the spread of a floating spread interest rate leg of a swap. The original object is unchanged - a new object is returned with the updated value.



+---------------------+---------------------------------------------------------+
| Parameter           | Description                                             |
+=====================+=========================================================+
| floatingSpreadIRLeg | A floating spread interest rate leg of a swap to update |
+---------------------+---------------------------------------------------------+
| spread              | The spread                                              |
+---------------------+---------------------------------------------------------+



SetFloatingVarianceSwapLegAnnualizationFactor


.............................................
SetFloatingVarianceSwapLegAnnualizationFactor
.............................................


Updates the the annualization factor of a floating leg of a variance swap. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+---------------------------------------------+
| Parameter               | Description                                 |
+=========================+=============================================+
| floatingVarianceSwapLeg | A floating leg of a variance swap to update |
+-------------------------+---------------------------------------------+
| annualizationFactor     | The the annualization factor                |
+-------------------------+---------------------------------------------+



SetFloatingVarianceSwapLegMonitoringFrequency


.............................................
SetFloatingVarianceSwapLegMonitoringFrequency
.............................................


Updates the the monitoring frequency of the swap of a floating leg of a variance swap. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+---------------------------------------------+
| Parameter               | Description                                 |
+=========================+=============================================+
| floatingVarianceSwapLeg | A floating leg of a variance swap to update |
+-------------------------+---------------------------------------------+
| monitoringFrequency     | The the monitoring frequency of the swap    |
+-------------------------+---------------------------------------------+



SetFloatingVarianceSwapLegUnderlyingId


......................................
SetFloatingVarianceSwapLegUnderlyingId
......................................


Updates the the identifier of the underlying of a floating leg of a variance swap. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+---------------------------------------------+
| Parameter               | Description                                 |
+=========================+=============================================+
| floatingVarianceSwapLeg | A floating leg of a variance swap to update |
+-------------------------+---------------------------------------------+
| underlyingId            | The the identifier of the underlying        |
+-------------------------+---------------------------------------------+



SetInterestRateNotionalAmount


.............................
SetInterestRateNotionalAmount
.............................


Updates the amount of a notional value of an interest rate leg of a swap. The original object is unchanged - a new object is returned with the updated value.



+----------------------+--------------------------------------------------------------+
| Parameter            | Description                                                  |
+======================+==============================================================+
| interestRateNotional | A notional value of an interest rate leg of a swap to update |
+----------------------+--------------------------------------------------------------+
| amount               | The amount                                                   |
+----------------------+--------------------------------------------------------------+



SetInterestRateNotionalCurrency


...............................
SetInterestRateNotionalCurrency
...............................


Updates the currency of a notional value of an interest rate leg of a swap. The original object is unchanged - a new object is returned with the updated value.



+----------------------+--------------------------------------------------------------+
| Parameter            | Description                                                  |
+======================+==============================================================+
| interestRateNotional | A notional value of an interest rate leg of a swap to update |
+----------------------+--------------------------------------------------------------+
| currency             | The currency                                                 |
+----------------------+--------------------------------------------------------------+



SetPositionAttribute


....................
SetPositionAttribute
....................


Sets or removes an attribute on a position, returning the updated position object.



+-----------+----------------------------------------------------------+
| Parameter | Description                                              |
+===========+==========================================================+
| position  | The position to update                                   |
+-----------+----------------------------------------------------------+
| attribute | The attribute to set, update or remove                   |
+-----------+----------------------------------------------------------+
| value     | The new attribute value, or omit to remove the attribute |
+-----------+----------------------------------------------------------+



SetSnapshotBasisViewName


........................
SetSnapshotBasisViewName
........................


Updates the view name the snapshot was originally based on, returning the updated snapshot.



+-----------+------------------------------------------+
| Parameter | Description                              |
+===========+==========================================+
| snapshot  | The snapshot to update                   |
+-----------+------------------------------------------+
| name      | The new basis view name for the snapshot |
+-----------+------------------------------------------+



SetSnapshotGlobalValue


......................
SetSnapshotGlobalValue
......................


Updates a global value within a market data values, returning the updated values.



+---------------+-------------------------------------------------------------------------------------------------------------------+
| Parameter     | Description                                                                                                       |
+===============+===================================================================================================================+
| snapshot      | No description available                                                                                          |
+---------------+-------------------------------------------------------------------------------------------------------------------+
| valueName     | The name of the value to add/update/remove                                                                        |
+---------------+-------------------------------------------------------------------------------------------------------------------+
| identifier    | The target identifier of the value to add/update/remove                                                           |
+---------------+-------------------------------------------------------------------------------------------------------------------+
| overrideValue | The new "override" market value, omit both this and the original value to remove from the snapshot                |
+---------------+-------------------------------------------------------------------------------------------------------------------+
| marketValue   | The new "original" market value, may be omitted if an override value is specified to leave the original unchanged |
+---------------+-------------------------------------------------------------------------------------------------------------------+
| type          | The type of the target identifier, e.g. SECURITY or PRIMITIVE (default is SECURITY)                               |
+---------------+-------------------------------------------------------------------------------------------------------------------+



SetSnapshotName


...............
SetSnapshotName
...............


Updates the name of a snapshot, returning the updated snapshot.



+-----------+-------------------------------+
| Parameter | Description                   |
+===========+===============================+
| snapshot  | The snapshot to update        |
+-----------+-------------------------------+
| name      | The new name for the snapshot |
+-----------+-------------------------------+



SetSnapshotVolatilityCube


.........................
SetSnapshotVolatilityCube
.........................


Updates a volatility cube values within a market data values object, returning the updated object.



+-----------+------------------------------------------------------------------------+
| Parameter | Description                                                            |
+===========+========================================================================+
| snapshot  | No description available                                               |
+-----------+------------------------------------------------------------------------+
| name      | The name of the cube to add/remove/update                              |
+-----------+------------------------------------------------------------------------+
| cube      | The volatility cube values, or NULL to remove the cube from the values |
+-----------+------------------------------------------------------------------------+



SetSnapshotVolatilitySurface


............................
SetSnapshotVolatilitySurface
............................


Updates a volatility surface values within a market data values object, returning the updated object.



+-----------+------------------------------------------------------------------------------+
| Parameter | Description                                                                  |
+===========+==============================================================================+
| snapshot  | No description available                                                     |
+-----------+------------------------------------------------------------------------------+
| name      | The name of the surface to add/remove/update                                 |
+-----------+------------------------------------------------------------------------------+
| surface   | The volatility surface values, or NULL to remove the surface from the values |
+-----------+------------------------------------------------------------------------------+



SetSnapshotYieldCurve


.....................
SetSnapshotYieldCurve
.....................


Updates a yield curve values within a market data values object, returning the updated object.



+------------+---------------------------------------------------------------------+
| Parameter  | Description                                                         |
+============+=====================================================================+
| snapshot   | No description available                                            |
+------------+---------------------------------------------------------------------+
| name       | The name of the yield curve to add/remove/update                    |
+------------+---------------------------------------------------------------------+
| yieldCurve | The yield curve values, or NULL to remove the curve from the values |
+------------+---------------------------------------------------------------------+



SetSnapshotYieldCurveValuationTime


..................................
SetSnapshotYieldCurveValuationTime
..................................


Updates the valuation time of a market data snapshot yield curve. The original object is unchanged - a new object is returned with the updated value.



+--------------------+----------------------------------------------+
| Parameter          | Description                                  |
+====================+==============================================+
| snapshotYieldCurve | A market data snapshot yield curve to update |
+--------------------+----------------------------------------------+
| valuationTime      | The valuation time                           |
+--------------------+----------------------------------------------+



SetSwapLegBusinessDayConvention


...............................
SetSwapLegBusinessDayConvention
...............................


Updates the business day convention of a leg of a swap. The original object is unchanged - a new object is returned with the updated value.



+-----------------------+-----------------------------+
| Parameter             | Description                 |
+=======================+=============================+
| swapLeg               | A leg of a swap to update   |
+-----------------------+-----------------------------+
| businessDayConvention | The business day convention |
+-----------------------+-----------------------------+



SetSwapLegDayCount


..................
SetSwapLegDayCount
..................


Updates the dayCount of a leg of a swap. The original object is unchanged - a new object is returned with the updated value.



+-----------+---------------------------+
| Parameter | Description               |
+===========+===========================+
| swapLeg   | A leg of a swap to update |
+-----------+---------------------------+
| dayCount  | The dayCount              |
+-----------+---------------------------+



SetSwapLegEom


.............
SetSwapLegEom
.............


Updates the EOM flag of a leg of a swap. The original object is unchanged - a new object is returned with the updated value.



+-----------+---------------------------+
| Parameter | Description               |
+===========+===========================+
| swapLeg   | A leg of a swap to update |
+-----------+---------------------------+
| eom       | The EOM flag              |
+-----------+---------------------------+



SetSwapLegFrequency


...................
SetSwapLegFrequency
...................


Updates the payment frequency of a leg of a swap. The original object is unchanged - a new object is returned with the updated value.



+-----------+---------------------------+
| Parameter | Description               |
+===========+===========================+
| swapLeg   | A leg of a swap to update |
+-----------+---------------------------+
| frequency | The payment frequency     |
+-----------+---------------------------+



SetSwapLegNotional


..................
SetSwapLegNotional
..................


Updates the notional of a leg of a swap. The original object is unchanged - a new object is returned with the updated value.



+-----------+---------------------------+
| Parameter | Description               |
+===========+===========================+
| swapLeg   | A leg of a swap to update |
+-----------+---------------------------+
| notional  | The notional              |
+-----------+---------------------------+



SetSwapLegRegionId


..................
SetSwapLegRegionId
..................


Updates the region identifier of a leg of a swap. The original object is unchanged - a new object is returned with the updated value.



+-----------+---------------------------+
| Parameter | Description               |
+===========+===========================+
| swapLeg   | A leg of a swap to update |
+-----------+---------------------------+
| regionId  | The region identifier     |
+-----------+---------------------------+



SetVarianceSwapNotionalAmount


.............................
SetVarianceSwapNotionalAmount
.............................


Updates the the notional amount of a notional value of the leg of a variance swap. The original object is unchanged - a new object is returned with the updated value.



+----------------------+----------------------------------------------------------+
| Parameter            | Description                                              |
+======================+==========================================================+
| varianceSwapNotional | A notional value of the leg of a variance swap to update |
+----------------------+----------------------------------------------------------+
| amount               | The the notional amount                                  |
+----------------------+----------------------------------------------------------+



SetVarianceSwapNotionalCurrency


...............................
SetVarianceSwapNotionalCurrency
...............................


Updates the the notional currency of a notional value of the leg of a variance swap. The original object is unchanged - a new object is returned with the updated value.



+----------------------+----------------------------------------------------------+
| Parameter            | Description                                              |
+======================+==========================================================+
| varianceSwapNotional | A notional value of the leg of a variance swap to update |
+----------------------+----------------------------------------------------------+
| currency             | The the notional currency                                |
+----------------------+----------------------------------------------------------+



SetViewClientExecutionFlag


..........................
SetViewClientExecutionFlag
..........................


Sets or clears an execution flag on a view client descriptor, the updated descriptor is returned.



+------------+---------------------------------------------------------------------------+
| Parameter  | Description                                                               |
+============+===========================================================================+
| viewClient | The view client descriptor to update                                      |
+------------+---------------------------------------------------------------------------+
| flag       | The flag to add or remove                                                 |
+------------+---------------------------------------------------------------------------+
| include    | Omit to set the flag, set to FALSE to remove the flag from the descriptor |
+------------+---------------------------------------------------------------------------+



SetVolatilityCubePoint


......................
SetVolatilityCubePoint
......................


Updates a point on a volatility cube values, returning the updated values.



+----------------+-------------------------------------------------------------------------------------------------------------------+
| Parameter      | Description                                                                                                       |
+================+===================================================================================================================+
| snapshot       | No description available                                                                                          |
+----------------+-------------------------------------------------------------------------------------------------------------------+
| swapTenor      | The swap tenor coordinate into the cube                                                                           |
+----------------+-------------------------------------------------------------------------------------------------------------------+
| optionExpiry   | The option expiry coordinate into the cube                                                                        |
+----------------+-------------------------------------------------------------------------------------------------------------------+
| relativeStrike | The strike relative to at the money (in Bps) coordinate into the cube                                             |
+----------------+-------------------------------------------------------------------------------------------------------------------+
| overrideValue  | The new "override" market value, omit both this and the original value to remove from the values                  |
+----------------+-------------------------------------------------------------------------------------------------------------------+
| marketValue    | The new "original" market value, may be omitted if an override value is specified to leave the original unchanged |
+----------------+-------------------------------------------------------------------------------------------------------------------+



SetVolatilityCubeTensor


.......................
SetVolatilityCubeTensor
.......................


Updates the values used for a volatility cube, returning the updated volatility surface.



+---------------+---------------------------------------------------------------------------------------------------+
| Parameter     | Description                                                                                       |
+===============+===================================================================================================+
| snapshot      | The volatility cube snapshot to update                                                            |
+---------------+---------------------------------------------------------------------------------------------------+
| overrideValue | The modified tensor to apply to the override values, omit to leave override values unchanged      |
+---------------+---------------------------------------------------------------------------------------------------+
| marketValue   | The modified tensor to apply to the market data values, omit to leave market data value unchanged |
+---------------+---------------------------------------------------------------------------------------------------+



SetVolatilitySurfacePoint


.........................
SetVolatilitySurfacePoint
.........................


Updates a point on a volatility surface values, returning the updated values.



+---------------+----------------------------------------------------------------------------------------------------------+
| Parameter     | Description                                                                                              |
+===============+==========================================================================================================+
| snapshot      | No description available                                                                                 |
+---------------+----------------------------------------------------------------------------------------------------------+
| x             | The X index onto the surface                                                                             |
+---------------+----------------------------------------------------------------------------------------------------------+
| y             | The Y index onto the surface                                                                             |
+---------------+----------------------------------------------------------------------------------------------------------+
| overrideValue | The new override value, omit to remove the override value                                                |
+---------------+----------------------------------------------------------------------------------------------------------+
| marketValue   | The new "original" market data value, omit to leave the market value unchanged                           |
+---------------+----------------------------------------------------------------------------------------------------------+
| xc            | The type to coerce the X index value to (required to add new points, may omit to update existing points) |
+---------------+----------------------------------------------------------------------------------------------------------+
| yc            | The type to coerce the Y index value to (required to add new points, may omit to update existing points) |
+---------------+----------------------------------------------------------------------------------------------------------+



SetVolatilitySurfaceTensor


..........................
SetVolatilitySurfaceTensor
..........................


Updates the values used for a volatility surface, returning the updated volatility surface.



+---------------+---------------------------------------------------------------------------------------------------+
| Parameter     | Description                                                                                       |
+===============+===================================================================================================+
| snapshot      | The volatility surface snapshot to update                                                         |
+---------------+---------------------------------------------------------------------------------------------------+
| overrideValue | The modified tensor to apply to the override values, omit to leave override values unchanged      |
+---------------+---------------------------------------------------------------------------------------------------+
| marketValue   | The modified tensor to apply to the market data values, omit to leave market data value unchanged |
+---------------+---------------------------------------------------------------------------------------------------+



SetYieldCurvePoint


..................
SetYieldCurvePoint
..................


Updates a point on a yield curve values, returning the updated values.



+---------------+--------------------------------------------------------------------------------+
| Parameter     | Description                                                                    |
+===============+================================================================================+
| snapshot      | No description available                                                       |
+---------------+--------------------------------------------------------------------------------+
| valueName     | The name of the market data line value                                         |
+---------------+--------------------------------------------------------------------------------+
| identifier    | The identifier of the underlying instrument                                    |
+---------------+--------------------------------------------------------------------------------+
| overrideValue | The new override value, omit to remove the override value                      |
+---------------+--------------------------------------------------------------------------------+
| marketValue   | The new "original" market data value, omit to leave the market value unchanged |
+---------------+--------------------------------------------------------------------------------+



SetYieldCurveTensor


...................
SetYieldCurveTensor
...................


Updates the values used for a yield curve, returning the updated yield curve.



+---------------+---------------------------------------------------------------------------------------------------+
| Parameter     | Description                                                                                       |
+===============+===================================================================================================+
| snapshot      | The yield curve snapshot to update                                                                |
+---------------+---------------------------------------------------------------------------------------------------+
| overrideValue | The modified tensor to apply to the override values, omit to leave override values unchanged      |
+---------------+---------------------------------------------------------------------------------------------------+
| marketValue   | The modified tensor to apply to the market data values, omit to leave market data value unchanged |
+---------------+---------------------------------------------------------------------------------------------------+



ShowDepGraph


............
ShowDepGraph
............


Displays the dependency graph.



+-----------------------+-----------------------------------------------------------------------+
| Parameter             | Description                                                           |
+=======================+=======================================================================+
| view_client           | The identifier of the view client which will provide the data         |
+-----------------------+-----------------------------------------------------------------------+
| view_client_reference | Name of a cell containing the view client identifier, for referencing |
+-----------------------+-----------------------------------------------------------------------+
| portfolio_index       | The index of the target within the flattened portfolio                |
+-----------------------+-----------------------------------------------------------------------+
| value_requirement     | The position-level value requirement                                  |
+-----------------------+-----------------------------------------------------------------------+



When used in a worksheet this function is an *overwriting* operation. After execution, if an error is not returned, the area with top-left cell the formula will be replaced by the returned value(s). Any content in these cells, including the original formula, will be replaced without warning. Any array formulae within the overwritten target area will be discarded, even if they include cells outside of this target area.

ShowExpandedViewClientPortfolio


...............................
ShowExpandedViewClientPortfolio
...............................


Extracts and formats a portfolio onto the spreadsheet, with expanded analytics where possible.



+-----------------------+------------------------------------------------------------------------------------+
| Parameter             | Description                                                                        |
+=======================+====================================================================================+
| view_client           | The identifier of the view client which will provide the data                      |
+-----------------------+------------------------------------------------------------------------------------+
| view_client_reference | The optional name of a cell containing the view client identifier, for referencing |
+-----------------------+------------------------------------------------------------------------------------+



When used in a worksheet this function is an *overwriting* operation. After execution, if an error is not returned, the area with top-left cell the formula will be replaced by the returned value(s). Any content in these cells, including the original formula, will be replaced without warning. Any array formulae within the overwritten target area will be discarded, even if they include cells outside of this target area.

ShowGroupedViewClientPortfolio


..............................
ShowGroupedViewClientPortfolio
..............................


Extracts and formats a portfolio onto the spreadsheet, grouping rows according to the portfolio structure .



+-----------------------+------------------------------------------------------------------------------------+
| Parameter             | Description                                                                        |
+=======================+====================================================================================+
| view_client           | The identifier of the view client which will provide the data                      |
+-----------------------+------------------------------------------------------------------------------------+
| view_client_reference | The optional name of a cell containing the view client identifier, for referencing |
+-----------------------+------------------------------------------------------------------------------------+



When used in a worksheet this function is an *overwriting* operation. After execution, if an error is not returned, the area with top-left cell the formula will be replaced by the returned value(s). Any content in these cells, including the original formula, will be replaced without warning. Any array formulae within the overwritten target area will be discarded, even if they include cells outside of this target area.

ShowPortfolio


.............
ShowPortfolio
.............


Extracts and formats a portfolio onto the spreadsheet.



+-------------+---------------------------------------------------+
| Parameter   | Description                                       |
+=============+===================================================+
| portfolio   | The identifier of the portfolio                   |
+-------------+---------------------------------------------------+
| aggregation | The aggregation order, omit for portfolio default |
+-------------+---------------------------------------------------+
| filter      | The portfolio filter expression, omit for none    |
+-------------+---------------------------------------------------+



When used in a worksheet this function is an *overwriting* operation. After execution, if an error is not returned, the area with top-left cell the formula will be replaced by the returned value(s). Any content in these cells, including the original formula, will be replaced without warning. Any array formulae within the overwritten target area will be discarded, even if they include cells outside of this target area.

A portfolio can be filtered by evaluating the filter expression. Each position is evaluated and only included in the resulting portfolio if the expression is TRUE. After filtering the positions, any nodes which do not have any positions remaining underneath them are pruned from the portfolio.

~~~~~~~~~~~~~~~~~~~~~
Comparison Operations
~~~~~~~~~~~~~~~~~~~~~




+--------------+-----------------------------------------------------------+
| Operator     | Description                                               |
+==============+===========================================================+
|  _a_`<`_b_   | Tests if the value of _a_ is less than _b_                |
+--------------+-----------------------------------------------------------+
|  _a_`<=`_b_  | Tests if the value of _a_ is less than or equal to _b_    |
+--------------+-----------------------------------------------------------+
|  _a_`>`_b_   | Tests if the value of _a_ is greater than _b_             |
+--------------+-----------------------------------------------------------+
|  _a_`>=`_b_  | Tests if the value of _a_ is greater than or equal to _b_ |
+--------------+-----------------------------------------------------------+
|  _a_`=`_b_   | Tests if the value of _a_ is equal to _b_                 |
+--------------+-----------------------------------------------------------+
|  _a_`<>`_b_  | Tests if the value of _a_ is not equal to _b_             |
+--------------+-----------------------------------------------------------+



Numbers are compared using the natural ordering. Strings are compared alphabetically and case-sensitively.

~~~~~~~~~~~~~~~~~~
Logical Operations
~~~~~~~~~~~~~~~~~~




+---------------+------------------------------------+
| Operator      | Description                        |
+===============+====================================+
|  `Not` _a_    | Logical inverse of _a_             |
+---------------+------------------------------------+
|  _a_`And`_b_  | True if _a_ and _b_ are both true  |
+---------------+------------------------------------+
|  _a_`Or`_b_   | True if either _a_ or _b_ are true |
+---------------+------------------------------------+



~~~~~~~~~~~~~~~~~~~
Position Properties
~~~~~~~~~~~~~~~~~~~




+----------------------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| Symbol               | Description                                                                                                                                                                                                            |
+======================+========================================================================================================================================================================================================================+
|  `Quantity`          | The total quantity of the position                                                                                                                                                                                     |
+----------------------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| _name_               | The value of the named attribute on the position. If no attribute is defined, a field on the associated security. If no matching field is defined on the security, the named property from one of the component trades |
+----------------------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `Attribute`._name_  | The value of the named attribute on the position                                                                                                                                                                       |
+----------------------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `Trade`._name_      | The value of a the named property on one of the component trades                                                                                                                                                       |
+----------------------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
|  `Security`._name_   | The value of the named property from the associated security                                                                                                                                                           |
+----------------------+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+



~~~~~~~~~~~~~~~~
Trade Properties
~~~~~~~~~~~~~~~~




+----------------------+-----------------------------------------------+
| Symbol               | Description                                   |
+======================+===============================================+
|  `Counterparty`      | The counterparty                              |
+----------------------+-----------------------------------------------+
|  `Premium`           | The premium                                   |
+----------------------+-----------------------------------------------+
|  `PremiumCurrency`   | The premium currency                          |
+----------------------+-----------------------------------------------+
|  `PremiumDate`       | The premium date                              |
+----------------------+-----------------------------------------------+
|  `PremiumTime`       | The premium time                              |
+----------------------+-----------------------------------------------+
| _name_               | The value of the named attribute on the trade |
+----------------------+-----------------------------------------------+
|  `Attribute`._name_  | The value of the named attribute on the trade |
+----------------------+-----------------------------------------------+



~~~~~~~~~~~~~~~~~~~
Security Properties
~~~~~~~~~~~~~~~~~~~




+----------+-------------------------------------------+
| Symbol   | Description                               |
+==========+===========================================+
|  `Name`  | The name of the security                  |
+----------+-------------------------------------------+
|  `Type`  | The top-level asset class of the security |
+----------+-------------------------------------------+



After the function has executed, the extracted data is static on the worksheet and can be modified. This will not update the original portfolio. Similarly, changes to the portfolio after the function has executed will not be reflected. The Security and Trade/Quantity columns in the expanded portfolio can be used as input to the Portfolio UDF to define a new portfolio based on the content of the sheet which would be updated as the sheet changes.

ShowPortfolioSummary


....................
ShowPortfolioSummary
....................


Extracts and formats a portfolio summary onto the spreadsheet.



+-------------+---------------------------------------------------+
| Parameter   | Description                                       |
+=============+===================================================+
| portfolio   | The identifier of the portfolio                   |
+-------------+---------------------------------------------------+
| aggregation | The aggregation order, omit for portfolio default |
+-------------+---------------------------------------------------+
| filter      | The portfolio filter expression, omit for none    |
+-------------+---------------------------------------------------+



When used in a worksheet this function is an *overwriting* operation. After execution, if an error is not returned, the area with top-left cell the formula will be replaced by the returned value(s). Any content in these cells, including the original formula, will be replaced without warning. Any array formulae within the overwritten target area will be discarded, even if they include cells outside of this target area.

ShowPortfolioSummaryWithBlanks


..............................
ShowPortfolioSummaryWithBlanks
..............................


Extracts and formats a portfolio summary onto the spreadsheet, including a group of blank rows for custom positions.



+-------------+---------------------------------------------------+
| Parameter   | Description                                       |
+=============+===================================================+
| portfolio   | The identifier of the portfolio                   |
+-------------+---------------------------------------------------+
| aggregation | The aggregation order, omit for portfolio default |
+-------------+---------------------------------------------------+
| filter      | The portfolio filter expression, omit for none    |
+-------------+---------------------------------------------------+



When used in a worksheet this function is an *overwriting* operation. After execution, if an error is not returned, the area with top-left cell the formula will be replaced by the returned value(s). Any content in these cells, including the original formula, will be replaced without warning. Any array formulae within the overwritten target area will be discarded, even if they include cells outside of this target area.

ShowTimeSeries


..............
ShowTimeSeries
..............


Expands a time series in two columns below the current cell to it's full size.



+-----------------+-----------------------------------------------------------------+
| Parameter       | Description                                                     |
+=================+=================================================================+
| time_series     | The time series to expand                                       |
+-----------------+-----------------------------------------------------------------+
| time_series_ref | A string containing the named cell reference of the time series |
+-----------------+-----------------------------------------------------------------+
| start_date      | The optional start date                                         |
+-----------------+-----------------------------------------------------------------+
| end_date        | The optional end date                                           |
+-----------------+-----------------------------------------------------------------+



When used in a worksheet this function is an *overwriting* operation. After execution, if an error is not returned, the area with top-left cell the formula will be replaced by the returned value(s). Any content in these cells, including the original formula, will be replaced without warning. Any array formulae within the overwritten target area will be discarded, even if they include cells outside of this target area.

SimpleChooserPayoffStyle


........................
SimpleChooserPayoffStyle
........................


Returns an object representing a 'simple chooser' option payoff style.



+------------------+----------------------------+
| Parameter        | Description                |
+==================+============================+
| chooseDate       | The choose date            |
+------------------+----------------------------+
| underlyingStrike | The underlying strike      |
+------------------+----------------------------+
| underlyingExpiry | The underlying expiry date |
+------------------+----------------------------+



Snapshot


........
Snapshot
........


Defines a market data snapshot.

This function takes no parameters.

SnapshotJob


...........
SnapshotJob
...........


Takes a sequence of market data snapshots.



+-------------------+--------------------------------------------------------------------------+
| Parameter         | Description                                                              |
+===================+==========================================================================+
| viewDefinitionId  | The identifier of the view definition                                    |
+-------------------+--------------------------------------------------------------------------+
| executionSequence | The execution sequence defining the cycles whose market data to snapshot |
+-------------------+--------------------------------------------------------------------------+



This function is capable of live operation. If automatic sheet calculation is enabled it may return new values pushed from the OpenGamma Platform even if the input parameters have not changed.

This function is available on a worksheet only and cannot be used from Visual Basic.

SnapshotVersions


................
SnapshotVersions
................


Returns the available versions of a snapshot.



+------------+--------------------------+
| Parameter  | Description              |
+============+==========================+
| snapshot   | No description available |
+------------+--------------------------+
| correction | No description available |
+------------+--------------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

SnapshotVolatilityCube


......................
SnapshotVolatilityCube
......................


Defines a market data snapshot volatility cube.

This function takes no parameters.

SnapshotVolatilitySurface


.........................
SnapshotVolatilitySurface
.........................


Defines a market data snapshot volatility surface.

This function takes no parameters.

SnapshotYieldCurve


..................
SnapshotYieldCurve
..................


Defines a market data snapshot yield curve.

This function takes no parameters.

Snapshots


.........
Snapshots
.........


Returns the set of current market data snapshots.



+-----------+---------------------------------------------------------------+
| Parameter | Description                                                   |
+===========+===============================================================+
| name      | Search string to match snapshots by name, use * as a wildcard |
+-----------+---------------------------------------------------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

StaticMarketDataViewClient


..........................
StaticMarketDataViewClient
..........................


Creates a view client descriptor for a single cycle (or manually triggered cycles) of a view from (live) market data.



+------------+----------------------------------------------------+
| Parameter  | Description                                        |
+============+====================================================+
| view       | Identifier of the referenced view                  |
+------------+----------------------------------------------------+
| dataSource | The source of live data, omit for platform default |
+------------+----------------------------------------------------+



StaticSnapshotViewClient


........................
StaticSnapshotViewClient
........................


Creates a view client descriptor for a single cycle (or manually triggered cycles) of a view from a market data snapshot.



+---------------+-------------------------------------------------------------------------------------------+
| Parameter     | Description                                                                               |
+===============+===========================================================================================+
| view          | Identifier of the referenced view                                                         |
+---------------+-------------------------------------------------------------------------------------------+
| snapshot      | Identifier of the snapshot to source market data from                                     |
+---------------+-------------------------------------------------------------------------------------------+
| valuationTime | Valuation time of the cycles to run, omit to use the system time when the view cycle runs |
+---------------+-------------------------------------------------------------------------------------------+



StoreSecurity


.............
StoreSecurity
.............


Stores a security object into a Security Master.



+-----------+-----------------------+
| Parameter | Description           |
+===========+=======================+
| security  | The security to store |
+-----------+-----------------------+



SumLabeledMatrix2D


..................
SumLabeledMatrix2D
..................


Sum of values in a row or column for a given labeled matrix2D.



+------------------+----------------------+
| Parameter        | Description          |
+==================+======================+
| labeled_matrix   | The labeled matrix   |
+------------------+----------------------+
| x_index_or_label | The X index or label |
+------------------+----------------------+
| y_index_or_label | The Y index or label |
+------------------+----------------------+



SumLabeledVector


................
SumLabeledVector
................


Sum of values in a labeled vector for a given range.



+----------------------+--------------------------+
| Parameter            | Description              |
+======================+==========================+
| labeledVector        | The labeled vector       |
+----------------------+--------------------------+
| start_index_or_label | The start index or label |
+----------------------+--------------------------+
| end_index_or_label   | The end index or label   |
+----------------------+--------------------------+



SupersharePayoffStyle


.....................
SupersharePayoffStyle
.....................


Returns an object representing a 'supershare' option payoff style.



+------------+-----------------+
| Parameter  | Description     |
+============+=================+
| lowerBound | The lower bound |
+------------+-----------------+
| upperBound | The upper bound |
+------------+-----------------+



SwapSecurity


............
SwapSecurity
............


Defines a swap security. The new security is added to the Security Master and an identifier to it returned.



+---------------+-------------------------------------------+
| Parameter     | Description                               |
+===============+===========================================+
| name          | The display name or label of the security |
+---------------+-------------------------------------------+
| tradeDate     | The trade date                            |
+---------------+-------------------------------------------+
| effectiveDate | The 'effective' or 'value' date           |
+---------------+-------------------------------------------+
| maturityDate  | The 'maturity' or 'termination' date      |
+---------------+-------------------------------------------+
| counterparty  | The counterparty                          |
+---------------+-------------------------------------------+
| payLeg        | The pay leg                               |
+---------------+-------------------------------------------+
| receiveLeg    | The receive leg                           |
+---------------+-------------------------------------------+



SwapSecurityCounterparty


........................
SwapSecurityCounterparty
........................


Returns the counterparty from a swap security.



+--------------+--------------------------+
| Parameter    | Description              |
+==============+==========================+
| swapSecurity | A swap security to query |
+--------------+--------------------------+



SwapSecurityEffectiveDate


.........................
SwapSecurityEffectiveDate
.........................


Returns the 'effective' or 'value' date from a swap security.



+--------------+--------------------------+
| Parameter    | Description              |
+==============+==========================+
| swapSecurity | A swap security to query |
+--------------+--------------------------+



SwapSecurityMaturityDate


........................
SwapSecurityMaturityDate
........................


Returns the 'maturity' or 'termination' date from a swap security.



+--------------+--------------------------+
| Parameter    | Description              |
+==============+==========================+
| swapSecurity | A swap security to query |
+--------------+--------------------------+



SwapSecurityObject


..................
SwapSecurityObject
..................


Defines a swap security.



+---------------+-------------------------------------------+
| Parameter     | Description                               |
+===============+===========================================+
| name          | The display name or label of the security |
+---------------+-------------------------------------------+
| tradeDate     | The trade date                            |
+---------------+-------------------------------------------+
| effectiveDate | The 'effective' or 'value' date           |
+---------------+-------------------------------------------+
| maturityDate  | The 'maturity' or 'termination' date      |
+---------------+-------------------------------------------+
| counterparty  | The counterparty                          |
+---------------+-------------------------------------------+
| payLeg        | The pay leg                               |
+---------------+-------------------------------------------+
| receiveLeg    | The receive leg                           |
+---------------+-------------------------------------------+



SwapSecurityPayLeg


..................
SwapSecurityPayLeg
..................


Returns the pay leg from a swap security.



+--------------+--------------------------+
| Parameter    | Description              |
+==============+==========================+
| swapSecurity | A swap security to query |
+--------------+--------------------------+



SwapSecurityReceiveLeg


......................
SwapSecurityReceiveLeg
......................


Returns the receive leg from a swap security.



+--------------+--------------------------+
| Parameter    | Description              |
+==============+==========================+
| swapSecurity | A swap security to query |
+--------------+--------------------------+



SwapSecuritySetCounterparty


...........................
SwapSecuritySetCounterparty
...........................


Updates the counterparty of a swap security. The original object is unchanged - a new object is returned with the updated value.



+--------------+---------------------------+
| Parameter    | Description               |
+==============+===========================+
| swapSecurity | A swap security to update |
+--------------+---------------------------+
| counterparty | The counterparty          |
+--------------+---------------------------+



SwapSecuritySetEffectiveDate


............................
SwapSecuritySetEffectiveDate
............................


Updates the 'effective' or 'value' date of a swap security. The original object is unchanged - a new object is returned with the updated value.



+---------------+---------------------------------+
| Parameter     | Description                     |
+===============+=================================+
| swapSecurity  | A swap security to update       |
+---------------+---------------------------------+
| effectiveDate | The 'effective' or 'value' date |
+---------------+---------------------------------+



SwapSecuritySetMaturityDate


...........................
SwapSecuritySetMaturityDate
...........................


Updates the 'maturity' or 'termination' date of a swap security. The original object is unchanged - a new object is returned with the updated value.



+--------------+--------------------------------------+
| Parameter    | Description                          |
+==============+======================================+
| swapSecurity | A swap security to update            |
+--------------+--------------------------------------+
| maturityDate | The 'maturity' or 'termination' date |
+--------------+--------------------------------------+



SwapSecuritySetPayLeg


.....................
SwapSecuritySetPayLeg
.....................


Updates the pay leg of a swap security. The original object is unchanged - a new object is returned with the updated value.



+--------------+---------------------------+
| Parameter    | Description               |
+==============+===========================+
| swapSecurity | A swap security to update |
+--------------+---------------------------+
| payLeg       | The pay leg               |
+--------------+---------------------------+



SwapSecuritySetReceiveLeg


.........................
SwapSecuritySetReceiveLeg
.........................


Updates the receive leg of a swap security. The original object is unchanged - a new object is returned with the updated value.



+--------------+---------------------------+
| Parameter    | Description               |
+==============+===========================+
| swapSecurity | A swap security to update |
+--------------+---------------------------+
| receiveLeg   | The receive leg           |
+--------------+---------------------------+



SwapSecuritySetTradeDate


........................
SwapSecuritySetTradeDate
........................


Updates the trade date of a swap security. The original object is unchanged - a new object is returned with the updated value.



+--------------+---------------------------+
| Parameter    | Description               |
+==============+===========================+
| swapSecurity | A swap security to update |
+--------------+---------------------------+
| tradeDate    | The trade date            |
+--------------+---------------------------+



SwapSecurityTradeDate


.....................
SwapSecurityTradeDate
.....................


Returns the trade date from a swap security.



+--------------+--------------------------+
| Parameter    | Description              |
+==============+==========================+
| swapSecurity | A swap security to query |
+--------------+--------------------------+



SwaptionSecurity


................
SwaptionSecurity
................


Defines a swaption security. The new security is added to the Security Master and an identifier to it returned.



+--------------+-------------------------------------------+
| Parameter    | Description                               |
+==============+===========================================+
| name         | The display name or label of the security |
+--------------+-------------------------------------------+
| payer        | The payer flag                            |
+--------------+-------------------------------------------+
| underlyingId | The identifier of the underlying swap     |
+--------------+-------------------------------------------+
| long         | The long flag                             |
+--------------+-------------------------------------------+
| expiry       | The expiry                                |
+--------------+-------------------------------------------+
| cashSettled  | The cash settlement flag                  |
+--------------+-------------------------------------------+
| currency     | The currency                              |
+--------------+-------------------------------------------+



SwaptionSecurityCashSettled


...........................
SwaptionSecurityCashSettled
...........................


Returns the cash settlement flag from a swaption security.



+------------------+------------------------------+
| Parameter        | Description                  |
+==================+==============================+
| swaptionSecurity | A swaption security to query |
+------------------+------------------------------+



SwaptionSecurityCurrency


........................
SwaptionSecurityCurrency
........................


Returns the currency from a swaption security.



+------------------+------------------------------+
| Parameter        | Description                  |
+==================+==============================+
| swaptionSecurity | A swaption security to query |
+------------------+------------------------------+



SwaptionSecurityExpiry


......................
SwaptionSecurityExpiry
......................


Returns the expiry from a swaption security.



+------------------+------------------------------+
| Parameter        | Description                  |
+==================+==============================+
| swaptionSecurity | A swaption security to query |
+------------------+------------------------------+



SwaptionSecurityLong


....................
SwaptionSecurityLong
....................


Returns the long flag from a swaption security.



+------------------+------------------------------+
| Parameter        | Description                  |
+==================+==============================+
| swaptionSecurity | A swaption security to query |
+------------------+------------------------------+



SwaptionSecurityObject


......................
SwaptionSecurityObject
......................


Defines a swaption security.



+--------------+-------------------------------------------+
| Parameter    | Description                               |
+==============+===========================================+
| name         | The display name or label of the security |
+--------------+-------------------------------------------+
| payer        | The payer flag                            |
+--------------+-------------------------------------------+
| underlyingId | The identifier of the underlying swap     |
+--------------+-------------------------------------------+
| long         | The long flag                             |
+--------------+-------------------------------------------+
| expiry       | The expiry                                |
+--------------+-------------------------------------------+
| cashSettled  | The cash settlement flag                  |
+--------------+-------------------------------------------+
| currency     | The currency                              |
+--------------+-------------------------------------------+



SwaptionSecurityPayer


.....................
SwaptionSecurityPayer
.....................


Returns the payer flag from a swaption security.



+------------------+------------------------------+
| Parameter        | Description                  |
+==================+==============================+
| swaptionSecurity | A swaption security to query |
+------------------+------------------------------+



SwaptionSecuritySetCashSettled


..............................
SwaptionSecuritySetCashSettled
..............................


Updates the cash settlement flag of a swaption security. The original object is unchanged - a new object is returned with the updated value.



+------------------+-------------------------------+
| Parameter        | Description                   |
+==================+===============================+
| swaptionSecurity | A swaption security to update |
+------------------+-------------------------------+
| cashSettled      | The cash settlement flag      |
+------------------+-------------------------------+



SwaptionSecuritySetCurrency


...........................
SwaptionSecuritySetCurrency
...........................


Updates the currency of a swaption security. The original object is unchanged - a new object is returned with the updated value.



+------------------+-------------------------------+
| Parameter        | Description                   |
+==================+===============================+
| swaptionSecurity | A swaption security to update |
+------------------+-------------------------------+
| currency         | The currency                  |
+------------------+-------------------------------+



SwaptionSecuritySetExpiry


.........................
SwaptionSecuritySetExpiry
.........................


Updates the expiry of a swaption security. The original object is unchanged - a new object is returned with the updated value.



+------------------+-------------------------------+
| Parameter        | Description                   |
+==================+===============================+
| swaptionSecurity | A swaption security to update |
+------------------+-------------------------------+
| expiry           | The expiry                    |
+------------------+-------------------------------+



SwaptionSecuritySetPayer


........................
SwaptionSecuritySetPayer
........................


Updates the payer flag of a swaption security. The original object is unchanged - a new object is returned with the updated value.



+------------------+-------------------------------+
| Parameter        | Description                   |
+==================+===============================+
| swaptionSecurity | A swaption security to update |
+------------------+-------------------------------+
| payer            | The payer flag                |
+------------------+-------------------------------+



SwaptionSecuritySetUnderlyingId


...............................
SwaptionSecuritySetUnderlyingId
...............................


Updates the identifier of the underlying swap of a swaption security. The original object is unchanged - a new object is returned with the updated value.



+------------------+---------------------------------------+
| Parameter        | Description                           |
+==================+=======================================+
| swaptionSecurity | A swaption security to update         |
+------------------+---------------------------------------+
| underlyingId     | The identifier of the underlying swap |
+------------------+---------------------------------------+



SwaptionSecurityUnderlyingId


............................
SwaptionSecurityUnderlyingId
............................


Returns the identifier of the underlying swap from a swaption security.



+------------------+------------------------------+
| Parameter        | Description                  |
+==================+==============================+
| swaptionSecurity | A swaption security to query |
+------------------+------------------------------+


