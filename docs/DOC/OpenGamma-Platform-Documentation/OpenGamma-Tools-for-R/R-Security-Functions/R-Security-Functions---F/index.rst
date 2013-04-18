title: R Security Functions - F
shortcut: DOC:R Security Functions - F
---
ExpandFixedInterestRateLeg

..........................
ExpandFixedInterestRateLeg
..........................


Expand the contents of a fixed interest rate leg.



+----------------------+----------+------------------------------------+
| Parameter            | Required | Description                        |
+======================+==========+====================================+
| fixedInterestRateLeg | Yes      | A fixed interest rate leg to query |
+----------------------+----------+------------------------------------+




ExpandFixedVarianceSwapLeg

..........................
ExpandFixedVarianceSwapLeg
..........................


Expand the contents of a fixed leg of a variance swap.



+----------------------+----------+-----------------------------------------+
| Parameter            | Required | Description                             |
+======================+==========+=========================================+
| fixedVarianceSwapLeg | Yes      | A fixed leg of a variance swap to query |
+----------------------+----------+-----------------------------------------+




ExpandFloatingGearingIRLeg

..........................
ExpandFloatingGearingIRLeg
..........................


Expand the contents of a floating gearing interest rate leg of a swap.



+----------------------+----------+---------------------------------------------------------+
| Parameter            | Required | Description                                             |
+======================+==========+=========================================================+
| floatingGearingIRLeg | Yes      | A floating gearing interest rate leg of a swap to query |
+----------------------+----------+---------------------------------------------------------+




ExpandFloatingInterestRateLeg

.............................
ExpandFloatingInterestRateLeg
.............................


Expand the contents of a floating interest rate leg of a swap.



+-------------------------+----------+-------------------------------------------------+
| Parameter               | Required | Description                                     |
+=========================+==========+=================================================+
| floatingInterestRateLeg | Yes      | A floating interest rate leg of a swap to query |
+-------------------------+----------+-------------------------------------------------+




ExpandFloatingSpreadIRLeg

.........................
ExpandFloatingSpreadIRLeg
.........................


Expand the contents of a floating spread interest rate leg of a swap.



+---------------------+----------+--------------------------------------------------------+
| Parameter           | Required | Description                                            |
+=====================+==========+========================================================+
| floatingSpreadIRLeg | Yes      | A floating spread interest rate leg of a swap to query |
+---------------------+----------+--------------------------------------------------------+




ExpandFloatingVarianceSwapLeg

.............................
ExpandFloatingVarianceSwapLeg
.............................


Expand the contents of a floating leg of a variance swap.



+-------------------------+----------+--------------------------------------------+
| Parameter               | Required | Description                                |
+=========================+==========+============================================+
| floatingVarianceSwapLeg | Yes      | A floating leg of a variance swap to query |
+-------------------------+----------+--------------------------------------------+




ExpandForwardSwapSecurity

.........................
ExpandForwardSwapSecurity
.........................


Expand the contents of a forward swap security.



+---------------------+----------+----------------------------------+
| Parameter           | Required | Description                      |
+=====================+==========+==================================+
| forwardSwapSecurity | Yes      | A forward swap security to query |
+---------------------+----------+----------------------------------+




ExpandFRASecurity

.................
ExpandFRASecurity
.................


Expand the contents of a forward rate agreement security.



+-------------+----------+--------------------------------------------+
| Parameter   | Required | Description                                |
+=============+==========+============================================+
| FRASecurity | Yes      | A forward rate agreement security to query |
+-------------+----------+--------------------------------------------+




ExpandFutureSecurity

....................
ExpandFutureSecurity
....................


Expand the contents of a future security.



+----------------+----------+----------------------------+
| Parameter      | Required | Description                |
+================+==========+============================+
| futureSecurity | Yes      | A future security to query |
+----------------+----------+----------------------------+




ExpandFXBarrierOptionSecurity

.............................
ExpandFXBarrierOptionSecurity
.............................


Expand the contents of a FX barrier option security.



+-------------------------+----------+---------------------------------------+
| Parameter               | Required | Description                           |
+=========================+==========+=======================================+
| FXBarrierOptionSecurity | Yes      | A FX barrier option security to query |
+-------------------------+----------+---------------------------------------+




ExpandFXDigitalOptionSecurity

.............................
ExpandFXDigitalOptionSecurity
.............................


Expand the contents of an FX digital option security.



+-------------------------+----------+----------------------------------------+
| Parameter               | Required | Description                            |
+=========================+==========+========================================+
| FXDigitalOptionSecurity | Yes      | An FX digital option security to query |
+-------------------------+----------+----------------------------------------+




ExpandFXForwardSecurity

.......................
ExpandFXForwardSecurity
.......................


Expand the contents of an FX forward security.



+-------------------+----------+---------------------------------+
| Parameter         | Required | Description                     |
+===================+==========+=================================+
| FXForwardSecurity | Yes      | An FX forward security to query |
+-------------------+----------+---------------------------------+




ExpandFXFutureSecurity

......................
ExpandFXFutureSecurity
......................


Expand the contents of an FX future security.



+------------------+----------+--------------------------------+
| Parameter        | Required | Description                    |
+==================+==========+================================+
| FXFutureSecurity | Yes      | An FX future security to query |
+------------------+----------+--------------------------------+




ExpandFXOptionSecurity

......................
ExpandFXOptionSecurity
......................


Expand the contents of an FX option security.



+------------------+----------+--------------------------------+
| Parameter        | Required | Description                    |
+==================+==========+================================+
| FXOptionSecurity | Yes      | An FX option security to query |
+------------------+----------+--------------------------------+




FadeInPayoffStyle

.................
FadeInPayoffStyle
.................


Returns an object representing a 'fade in' option payoff style.



+------------+----------+-----------------+
| Parameter  | Required | Description     |
+============+==========+=================+
| lowerBound | Yes      | The lower bound |
+------------+----------+-----------------+
| upperBound | Yes      | The upper bound |
+------------+----------+-----------------+




FixedInterestRateLeg

....................
FixedInterestRateLeg
....................


Defines a fixed interest rate leg.



+-----------------------+----------+-------------------------------------------------------+
| Parameter             | Required | Description                                           |
+=======================+==========+=======================================================+
| dayCount              | Yes      | The dayCount                                          |
+-----------------------+----------+-------------------------------------------------------+
| frequency             | Yes      | The payment frequency                                 |
+-----------------------+----------+-------------------------------------------------------+
| regionId              | Yes      | The region identifier                                 |
+-----------------------+----------+-------------------------------------------------------+
| businessDayConvention | Yes      | The business day convention                           |
+-----------------------+----------+-------------------------------------------------------+
| notional              | Yes      | The notional                                          |
+-----------------------+----------+-------------------------------------------------------+
| eom                   | Yes      | The EOM flag                                          |
+-----------------------+----------+-------------------------------------------------------+
| rate                  | Yes      | The fixed interest rate as a decimal (e.g. 5% = 0.05) |
+-----------------------+----------+-------------------------------------------------------+




FixedStrikeLookbackPayoffStyle

..............................
FixedStrikeLookbackPayoffStyle
..............................


Returns an object representing a 'fixed strike lookback' option payoff style.

This function takes no parameters.


FixedVarianceSwapLeg

....................
FixedVarianceSwapLeg
....................


Defines a fixed leg of a variance swap.



+-----------------------+----------+-------------------------------------+
| Parameter             | Required | Description                         |
+=======================+==========+=====================================+
| dayCount              | Yes      | The dayCount                        |
+-----------------------+----------+-------------------------------------+
| frequency             | Yes      | The payment frequency               |
+-----------------------+----------+-------------------------------------+
| regionId              | Yes      | The region identifier               |
+-----------------------+----------+-------------------------------------+
| businessDayConvention | Yes      | The business day convention         |
+-----------------------+----------+-------------------------------------+
| notional              | Yes      | The notional                        |
+-----------------------+----------+-------------------------------------+
| eom                   | Yes      | The EOM flag                        |
+-----------------------+----------+-------------------------------------+
| strike                | Yes      | The the strike of the variance swap |
+-----------------------+----------+-------------------------------------+
| type                  | Yes      | The the type of the variance swap   |
+-----------------------+----------+-------------------------------------+




FloatingGearingIRLeg

....................
FloatingGearingIRLeg
....................


Defines a floating gearing interest rate leg of a swap.



+-------------------------+----------+-----------------------------------------------------------------+
| Parameter               | Required | Description                                                     |
+=========================+==========+=================================================================+
| dayCount                | Yes      | The dayCount                                                    |
+-------------------------+----------+-----------------------------------------------------------------+
| frequency               | Yes      | The payment frequency                                           |
+-------------------------+----------+-----------------------------------------------------------------+
| regionId                | Yes      | The region identifier                                           |
+-------------------------+----------+-----------------------------------------------------------------+
| businessDayConvention   | Yes      | The business day convention                                     |
+-------------------------+----------+-----------------------------------------------------------------+
| notional                | Yes      | The notional                                                    |
+-------------------------+----------+-----------------------------------------------------------------+
| eom                     | Yes      | The EOM flag                                                    |
+-------------------------+----------+-----------------------------------------------------------------+
| floatingReferenceRateId | Yes      | The identifier of the object used to provide the reference rate |
+-------------------------+----------+-----------------------------------------------------------------+
| floatingRateType        | Yes      | The floating rate type                                          |
+-------------------------+----------+-----------------------------------------------------------------+
| gearing                 | Yes      | The gearing                                                     |
+-------------------------+----------+-----------------------------------------------------------------+




FloatingInterestRateLeg

.......................
FloatingInterestRateLeg
.......................


Defines a floating interest rate leg of a swap.



+-------------------------+----------+-----------------------------------------------------------------+
| Parameter               | Required | Description                                                     |
+=========================+==========+=================================================================+
| dayCount                | Yes      | The dayCount                                                    |
+-------------------------+----------+-----------------------------------------------------------------+
| frequency               | Yes      | The payment frequency                                           |
+-------------------------+----------+-----------------------------------------------------------------+
| regionId                | Yes      | The region identifier                                           |
+-------------------------+----------+-----------------------------------------------------------------+
| businessDayConvention   | Yes      | The business day convention                                     |
+-------------------------+----------+-----------------------------------------------------------------+
| notional                | Yes      | The notional                                                    |
+-------------------------+----------+-----------------------------------------------------------------+
| eom                     | Yes      | The EOM flag                                                    |
+-------------------------+----------+-----------------------------------------------------------------+
| floatingReferenceRateId | Yes      | The identifier of the object used to provide the reference rate |
+-------------------------+----------+-----------------------------------------------------------------+
| floatingRateType        | Yes      | The floating rate type                                          |
+-------------------------+----------+-----------------------------------------------------------------+




FloatingSpreadIRLeg

...................
FloatingSpreadIRLeg
...................


Defines a floating spread interest rate leg of a swap.



+-------------------------+----------+-----------------------------------------------------------------+
| Parameter               | Required | Description                                                     |
+=========================+==========+=================================================================+
| dayCount                | Yes      | The dayCount                                                    |
+-------------------------+----------+-----------------------------------------------------------------+
| frequency               | Yes      | The payment frequency                                           |
+-------------------------+----------+-----------------------------------------------------------------+
| regionId                | Yes      | The region identifier                                           |
+-------------------------+----------+-----------------------------------------------------------------+
| businessDayConvention   | Yes      | The business day convention                                     |
+-------------------------+----------+-----------------------------------------------------------------+
| notional                | Yes      | The notional                                                    |
+-------------------------+----------+-----------------------------------------------------------------+
| eom                     | Yes      | The EOM flag                                                    |
+-------------------------+----------+-----------------------------------------------------------------+
| floatingReferenceRateId | Yes      | The identifier of the object used to provide the reference rate |
+-------------------------+----------+-----------------------------------------------------------------+
| floatingRateType        | Yes      | The floating rate type                                          |
+-------------------------+----------+-----------------------------------------------------------------+
| spread                  | Yes      | The spread                                                      |
+-------------------------+----------+-----------------------------------------------------------------+




FloatingStrikeLookbackPayoffStyle

.................................
FloatingStrikeLookbackPayoffStyle
.................................


Returns an object representing a 'floating strike lookback' option payoff style.

This function takes no parameters.


FloatingVarianceSwapLeg

.......................
FloatingVarianceSwapLeg
.......................


Defines a floating leg of a variance swap.



+-----------------------+----------+------------------------------------------+
| Parameter             | Required | Description                              |
+=======================+==========+==========================================+
| dayCount              | Yes      | The dayCount                             |
+-----------------------+----------+------------------------------------------+
| frequency             | Yes      | The payment frequency                    |
+-----------------------+----------+------------------------------------------+
| regionId              | Yes      | The region identifier                    |
+-----------------------+----------+------------------------------------------+
| businessDayConvention | Yes      | The business day convention              |
+-----------------------+----------+------------------------------------------+
| notional              | Yes      | The notional                             |
+-----------------------+----------+------------------------------------------+
| eom                   | Yes      | The EOM flag                             |
+-----------------------+----------+------------------------------------------+
| underlyingId          | Yes      | The the identifier of the underlying     |
+-----------------------+----------+------------------------------------------+
| monitoringFrequency   | Yes      | The the monitoring frequency of the swap |
+-----------------------+----------+------------------------------------------+
| annualizationFactor   | Yes      | The the annualization factor             |
+-----------------------+----------+------------------------------------------+




ForwardSwapSecurity

...................
ForwardSwapSecurity
...................


Defines a forward swap security.



+------------------+----------+-------------------------------------------+
| Parameter        | Required | Description                               |
+==================+==========+===========================================+
| name             | Yes      | The display name or label of the security |
+------------------+----------+-------------------------------------------+
| tradeDate        | Yes      | The trade date                            |
+------------------+----------+-------------------------------------------+
| effectiveDate    | Yes      | The 'effective' or 'value' date           |
+------------------+----------+-------------------------------------------+
| maturityDate     | Yes      | The 'maturity' or 'termination' date      |
+------------------+----------+-------------------------------------------+
| counterparty     | Yes      | The counterparty                          |
+------------------+----------+-------------------------------------------+
| payLeg           | Yes      | The pay leg                               |
+------------------+----------+-------------------------------------------+
| receiveLeg       | Yes      | The receive leg                           |
+------------------+----------+-------------------------------------------+
| forwardStartDate | Yes      | The start date of the forward swap        |
+------------------+----------+-------------------------------------------+




FRASecurity

...........
FRASecurity
...........


Defines a forward rate agreement security.



+--------------+----------+-------------------------------------------+
| Parameter    | Required | Description                               |
+==============+==========+===========================================+
| name         | Yes      | The display name or label of the security |
+--------------+----------+-------------------------------------------+
| currency     | Yes      | The currency                              |
+--------------+----------+-------------------------------------------+
| regionId     | Yes      | The region identifier                     |
+--------------+----------+-------------------------------------------+
| startDate    | Yes      | The start date                            |
+--------------+----------+-------------------------------------------+
| endDate      | Yes      | The end date                              |
+--------------+----------+-------------------------------------------+
| rate         | Yes      | The rate as a decimal (e.g. 5% = 0.05)    |
+--------------+----------+-------------------------------------------+
| amount       | Yes      | The notional amount                       |
+--------------+----------+-------------------------------------------+
| underlyingId | Yes      | The underlying identifier                 |
+--------------+----------+-------------------------------------------+
| fixingDate   | Yes      | The fixing date                           |
+--------------+----------+-------------------------------------------+




FXBarrierOptionSecurity

.......................
FXBarrierOptionSecurity
.......................


Defines a FX barrier option security.



+-------------------+----------+-------------------------------------------+
| Parameter         | Required | Description                               |
+===================+==========+===========================================+
| name              | Yes      | The display name or label of the security |
+-------------------+----------+-------------------------------------------+
| putCurrency       | Yes      | The put currency                          |
+-------------------+----------+-------------------------------------------+
| callCurrency      | Yes      | The call currency                         |
+-------------------+----------+-------------------------------------------+
| putAmount         | Yes      | The put amount                            |
+-------------------+----------+-------------------------------------------+
| callAmount        | Yes      | The call amount                           |
+-------------------+----------+-------------------------------------------+
| expiry            | Yes      | The expiry                                |
+-------------------+----------+-------------------------------------------+
| settlementDate    | Yes      | The settlement date                       |
+-------------------+----------+-------------------------------------------+
| barrierType       | Yes      | The barrier type                          |
+-------------------+----------+-------------------------------------------+
| barrierDirection  | Yes      | The barrier direction                     |
+-------------------+----------+-------------------------------------------+
| monitoringType    | Yes      | The monitoring type                       |
+-------------------+----------+-------------------------------------------+
| samplingFrequency | Yes      | The sampling frequency                    |
+-------------------+----------+-------------------------------------------+
| barrierLevel      | Yes      | The barrier level                         |
+-------------------+----------+-------------------------------------------+
| long              | Yes      | The long flag                             |
+-------------------+----------+-------------------------------------------+




FXDigitalOptionSecurity

.......................
FXDigitalOptionSecurity
.......................


Defines an FX digital option security.



+-----------------+----------+-------------------------------------------+
| Parameter       | Required | Description                               |
+=================+==========+===========================================+
| name            | Yes      | The display name or label of the security |
+-----------------+----------+-------------------------------------------+
| putCurrency     | Yes      | The put currency                          |
+-----------------+----------+-------------------------------------------+
| callCurrency    | Yes      | The call currency                         |
+-----------------+----------+-------------------------------------------+
| putAmount       | Yes      | The put amount                            |
+-----------------+----------+-------------------------------------------+
| callAmount      | Yes      | The call amount                           |
+-----------------+----------+-------------------------------------------+
| paymentCurrency | Yes      | The payment currency                      |
+-----------------+----------+-------------------------------------------+
| expiry          | Yes      | The expiry                                |
+-----------------+----------+-------------------------------------------+
| settlementDate  | Yes      | The settlement date                       |
+-----------------+----------+-------------------------------------------+
| long            | Yes      | The long flag                             |
+-----------------+----------+-------------------------------------------+




FXForwardSecurity

.................
FXForwardSecurity
.................


Defines an FX forward security.



+-----------------+----------+-------------------------------------------+
| Parameter       | Required | Description                               |
+=================+==========+===========================================+
| name            | Yes      | The display name or label of the security |
+-----------------+----------+-------------------------------------------+
| payAmount       | Yes      | The pay amount                            |
+-----------------+----------+-------------------------------------------+
| payCurrency     | Yes      | The pay currency                          |
+-----------------+----------+-------------------------------------------+
| receiveAmount   | Yes      | The receive amount                        |
+-----------------+----------+-------------------------------------------+
| receiveCurrency | Yes      | The receive currency                      |
+-----------------+----------+-------------------------------------------+
| forwardDate     | Yes      | The forward date                          |
+-----------------+----------+-------------------------------------------+
| regionId        | Yes      | The identifier of the region              |
+-----------------+----------+-------------------------------------------+




FXFutureSecurity

................
FXFutureSecurity
................


Defines an FX future security.



+--------------------+----------+-------------------------------------------+
| Parameter          | Required | Description                               |
+====================+==========+===========================================+
| name               | Yes      | The display name or label of the security |
+--------------------+----------+-------------------------------------------+
| expiry             | Yes      | The expiry date                           |
+--------------------+----------+-------------------------------------------+
| tradingExchange    | Yes      | The trading exchange                      |
+--------------------+----------+-------------------------------------------+
| settlementExchange | Yes      | The settlement exchange                   |
+--------------------+----------+-------------------------------------------+
| currency           | Yes      | The currency                              |
+--------------------+----------+-------------------------------------------+
| unitAmount         | Yes      | The unit amount                           |
+--------------------+----------+-------------------------------------------+
| numerator          | Yes      | The numerator currency                    |
+--------------------+----------+-------------------------------------------+
| denominator        | Yes      | The denominator currency                  |
+--------------------+----------+-------------------------------------------+
| contractCategory   | Yes      | The category                              |
+--------------------+----------+-------------------------------------------+




FXOptionSecurity

................
FXOptionSecurity
................


Defines an FX option security.



+----------------+----------+-------------------------------------------+
| Parameter      | Required | Description                               |
+================+==========+===========================================+
| name           | Yes      | The display name or label of the security |
+----------------+----------+-------------------------------------------+
| putCurrency    | Yes      | The put currency                          |
+----------------+----------+-------------------------------------------+
| callCurrency   | Yes      | The call currency                         |
+----------------+----------+-------------------------------------------+
| putAmount      | Yes      | The put amount                            |
+----------------+----------+-------------------------------------------+
| callAmount     | Yes      | The call amount                           |
+----------------+----------+-------------------------------------------+
| expiry         | Yes      | The expiry                                |
+----------------+----------+-------------------------------------------+
| settlementDate | Yes      | The settlement date                       |
+----------------+----------+-------------------------------------------+
| long           | Yes      | The long flag                             |
+----------------+----------+-------------------------------------------+
| exerciseType   | Yes      | The exercise type                         |
+----------------+----------+-------------------------------------------+




GetFixedInterestRateLegRate

...........................
GetFixedInterestRateLegRate
...........................


Returns the fixed interest rate as a decimal (e.g. 5% = 0.05) from a fixed interest rate leg.



+----------------------+----------+------------------------------------+
| Parameter            | Required | Description                        |
+======================+==========+====================================+
| fixedInterestRateLeg | Yes      | A fixed interest rate leg to query |
+----------------------+----------+------------------------------------+




GetFixedVarianceSwapLegStrike

.............................
GetFixedVarianceSwapLegStrike
.............................


Returns the the strike of the variance swap from a fixed leg of a variance swap.



+----------------------+----------+-----------------------------------------+
| Parameter            | Required | Description                             |
+======================+==========+=========================================+
| fixedVarianceSwapLeg | Yes      | A fixed leg of a variance swap to query |
+----------------------+----------+-----------------------------------------+




GetFixedVarianceSwapLegType

...........................
GetFixedVarianceSwapLegType
...........................


Returns the the type of the variance swap from a fixed leg of a variance swap.



+----------------------+----------+-----------------------------------------+
| Parameter            | Required | Description                             |
+======================+==========+=========================================+
| fixedVarianceSwapLeg | Yes      | A fixed leg of a variance swap to query |
+----------------------+----------+-----------------------------------------+




GetFloatingGearingIRLegGearing

..............................
GetFloatingGearingIRLegGearing
..............................


Returns the gearing from a floating gearing interest rate leg of a swap.



+----------------------+----------+---------------------------------------------------------+
| Parameter            | Required | Description                                             |
+======================+==========+=========================================================+
| floatingGearingIRLeg | Yes      | A floating gearing interest rate leg of a swap to query |
+----------------------+----------+---------------------------------------------------------+




GetFloatingInterestRateLegFloatingRateType

..........................................
GetFloatingInterestRateLegFloatingRateType
..........................................


Returns the floating rate type from a floating interest rate leg of a swap.



+-------------------------+----------+-------------------------------------------------+
| Parameter               | Required | Description                                     |
+=========================+==========+=================================================+
| floatingInterestRateLeg | Yes      | A floating interest rate leg of a swap to query |
+-------------------------+----------+-------------------------------------------------+




GetFloatingInterestRateLegFloatingReferenceRateId

.................................................
GetFloatingInterestRateLegFloatingReferenceRateId
.................................................


Returns the identifier of the object used to provide the reference rate from a floating interest rate leg of a swap.



+-------------------------+----------+-------------------------------------------------+
| Parameter               | Required | Description                                     |
+=========================+==========+=================================================+
| floatingInterestRateLeg | Yes      | A floating interest rate leg of a swap to query |
+-------------------------+----------+-------------------------------------------------+




GetFloatingInterestRateLegInitialFloatingRate

.............................................
GetFloatingInterestRateLegInitialFloatingRate
.............................................


Returns the floating rate of the first period of the swap (expressed as a decimal) from a floating interest rate leg of a swap.



+-------------------------+----------+-------------------------------------------------+
| Parameter               | Required | Description                                     |
+=========================+==========+=================================================+
| floatingInterestRateLeg | Yes      | A floating interest rate leg of a swap to query |
+-------------------------+----------+-------------------------------------------------+




GetFloatingInterestRateLegOffsetFixing

......................................
GetFloatingInterestRateLegOffsetFixing
......................................


Returns the offset fixing frequency from a floating interest rate leg of a swap.



+-------------------------+----------+-------------------------------------------------+
| Parameter               | Required | Description                                     |
+=========================+==========+=================================================+
| floatingInterestRateLeg | Yes      | A floating interest rate leg of a swap to query |
+-------------------------+----------+-------------------------------------------------+




GetFloatingInterestRateLegSettlementDays

........................................
GetFloatingInterestRateLegSettlementDays
........................................


Returns the settlement days from a floating interest rate leg of a swap.



+-------------------------+----------+-------------------------------------------------+
| Parameter               | Required | Description                                     |
+=========================+==========+=================================================+
| floatingInterestRateLeg | Yes      | A floating interest rate leg of a swap to query |
+-------------------------+----------+-------------------------------------------------+




GetFloatingSpreadIRLegSpread

............................
GetFloatingSpreadIRLegSpread
............................


Returns the spread from a floating spread interest rate leg of a swap.



+---------------------+----------+--------------------------------------------------------+
| Parameter           | Required | Description                                            |
+=====================+==========+========================================================+
| floatingSpreadIRLeg | Yes      | A floating spread interest rate leg of a swap to query |
+---------------------+----------+--------------------------------------------------------+




GetFloatingVarianceSwapLegAnnualizationFactor

.............................................
GetFloatingVarianceSwapLegAnnualizationFactor
.............................................


Returns the the annualization factor from a floating leg of a variance swap.



+-------------------------+----------+--------------------------------------------+
| Parameter               | Required | Description                                |
+=========================+==========+============================================+
| floatingVarianceSwapLeg | Yes      | A floating leg of a variance swap to query |
+-------------------------+----------+--------------------------------------------+




GetFloatingVarianceSwapLegMonitoringFrequency

.............................................
GetFloatingVarianceSwapLegMonitoringFrequency
.............................................


Returns the the monitoring frequency of the swap from a floating leg of a variance swap.



+-------------------------+----------+--------------------------------------------+
| Parameter               | Required | Description                                |
+=========================+==========+============================================+
| floatingVarianceSwapLeg | Yes      | A floating leg of a variance swap to query |
+-------------------------+----------+--------------------------------------------+




GetFloatingVarianceSwapLegUnderlyingId

......................................
GetFloatingVarianceSwapLegUnderlyingId
......................................


Returns the the identifier of the underlying TODO from a floating leg of a variance swap.



+-------------------------+----------+--------------------------------------------+
| Parameter               | Required | Description                                |
+=========================+==========+============================================+
| floatingVarianceSwapLeg | Yes      | A floating leg of a variance swap to query |
+-------------------------+----------+--------------------------------------------+




GetForwardSwapSecurityForwardStartDate

......................................
GetForwardSwapSecurityForwardStartDate
......................................


Returns the start date of the forward swap from a forward swap security.



+---------------------+----------+----------------------------------+
| Parameter           | Required | Description                      |
+=====================+==========+==================================+
| forwardSwapSecurity | Yes      | A forward swap security to query |
+---------------------+----------+----------------------------------+




GetFRASecurityAmount

....................
GetFRASecurityAmount
....................


Returns the notional amount from a forward rate agreement security.



+-------------+----------+--------------------------------------------+
| Parameter   | Required | Description                                |
+=============+==========+============================================+
| FRASecurity | Yes      | A forward rate agreement security to query |
+-------------+----------+--------------------------------------------+




GetFRASecurityCurrency

......................
GetFRASecurityCurrency
......................


Returns the currency from a forward rate agreement security.



+-------------+----------+--------------------------------------------+
| Parameter   | Required | Description                                |
+=============+==========+============================================+
| FRASecurity | Yes      | A forward rate agreement security to query |
+-------------+----------+--------------------------------------------+




GetFRASecurityEndDate

.....................
GetFRASecurityEndDate
.....................


Returns the end date from a forward rate agreement security.



+-------------+----------+--------------------------------------------+
| Parameter   | Required | Description                                |
+=============+==========+============================================+
| FRASecurity | Yes      | A forward rate agreement security to query |
+-------------+----------+--------------------------------------------+




GetFRASecurityFixingDate

........................
GetFRASecurityFixingDate
........................


Returns the fixing date from a forward rate agreement security.



+-------------+----------+--------------------------------------------+
| Parameter   | Required | Description                                |
+=============+==========+============================================+
| FRASecurity | Yes      | A forward rate agreement security to query |
+-------------+----------+--------------------------------------------+




GetFRASecurityRate

..................
GetFRASecurityRate
..................


Returns the rate as a decimal (e.g. 5% = 0.05) from a forward rate agreement security.



+-------------+----------+--------------------------------------------+
| Parameter   | Required | Description                                |
+=============+==========+============================================+
| FRASecurity | Yes      | A forward rate agreement security to query |
+-------------+----------+--------------------------------------------+




GetFRASecurityRegionId

......................
GetFRASecurityRegionId
......................


Returns the region identifier from a forward rate agreement security.



+-------------+----------+--------------------------------------------+
| Parameter   | Required | Description                                |
+=============+==========+============================================+
| FRASecurity | Yes      | A forward rate agreement security to query |
+-------------+----------+--------------------------------------------+




GetFRASecurityStartDate

.......................
GetFRASecurityStartDate
.......................


Returns the start date from a forward rate agreement security.



+-------------+----------+--------------------------------------------+
| Parameter   | Required | Description                                |
+=============+==========+============================================+
| FRASecurity | Yes      | A forward rate agreement security to query |
+-------------+----------+--------------------------------------------+




GetFRASecurityUnderlyingId

..........................
GetFRASecurityUnderlyingId
..........................


Returns the underlying identifier from a forward rate agreement security.



+-------------+----------+--------------------------------------------+
| Parameter   | Required | Description                                |
+=============+==========+============================================+
| FRASecurity | Yes      | A forward rate agreement security to query |
+-------------+----------+--------------------------------------------+




GetFutureSecurityContractCategory

.................................
GetFutureSecurityContractCategory
.................................


Returns the category from a future security.



+----------------+----------+----------------------------+
| Parameter      | Required | Description                |
+================+==========+============================+
| futureSecurity | Yes      | A future security to query |
+----------------+----------+----------------------------+




GetFutureSecurityCurrency

.........................
GetFutureSecurityCurrency
.........................


Returns the currency from a future security.



+----------------+----------+----------------------------+
| Parameter      | Required | Description                |
+================+==========+============================+
| futureSecurity | Yes      | A future security to query |
+----------------+----------+----------------------------+




GetFutureSecurityExpiry

.......................
GetFutureSecurityExpiry
.......................


Returns the expiry date from a future security.



+----------------+----------+----------------------------+
| Parameter      | Required | Description                |
+================+==========+============================+
| futureSecurity | Yes      | A future security to query |
+----------------+----------+----------------------------+




GetFutureSecuritySettlementExchange

...................................
GetFutureSecuritySettlementExchange
...................................


Returns the settlement exchange from a future security.



+----------------+----------+----------------------------+
| Parameter      | Required | Description                |
+================+==========+============================+
| futureSecurity | Yes      | A future security to query |
+----------------+----------+----------------------------+




GetFutureSecurityTradingExchange

................................
GetFutureSecurityTradingExchange
................................


Returns the trading exchange from a future security.



+----------------+----------+----------------------------+
| Parameter      | Required | Description                |
+================+==========+============================+
| futureSecurity | Yes      | A future security to query |
+----------------+----------+----------------------------+




GetFutureSecurityUnitAmount

...........................
GetFutureSecurityUnitAmount
...........................


Returns the unit amount from a future security.



+----------------+----------+----------------------------+
| Parameter      | Required | Description                |
+================+==========+============================+
| futureSecurity | Yes      | A future security to query |
+----------------+----------+----------------------------+




GetFXBarrierOptionSecurityBarrierDirection

..........................................
GetFXBarrierOptionSecurityBarrierDirection
..........................................


Returns the barrier direction from a FX barrier option security.



+-------------------------+----------+---------------------------------------+
| Parameter               | Required | Description                           |
+=========================+==========+=======================================+
| FXBarrierOptionSecurity | Yes      | A FX barrier option security to query |
+-------------------------+----------+---------------------------------------+




GetFXBarrierOptionSecurityBarrierLevel

......................................
GetFXBarrierOptionSecurityBarrierLevel
......................................


Returns the barrier level from a FX barrier option security.



+-------------------------+----------+---------------------------------------+
| Parameter               | Required | Description                           |
+=========================+==========+=======================================+
| FXBarrierOptionSecurity | Yes      | A FX barrier option security to query |
+-------------------------+----------+---------------------------------------+




GetFXBarrierOptionSecurityBarrierType

.....................................
GetFXBarrierOptionSecurityBarrierType
.....................................


Returns the barrier type from a FX barrier option security.



+-------------------------+----------+---------------------------------------+
| Parameter               | Required | Description                           |
+=========================+==========+=======================================+
| FXBarrierOptionSecurity | Yes      | A FX barrier option security to query |
+-------------------------+----------+---------------------------------------+




GetFXBarrierOptionSecurityCallAmount

....................................
GetFXBarrierOptionSecurityCallAmount
....................................


Returns the call amount from a FX barrier option security.



+-------------------------+----------+---------------------------------------+
| Parameter               | Required | Description                           |
+=========================+==========+=======================================+
| FXBarrierOptionSecurity | Yes      | A FX barrier option security to query |
+-------------------------+----------+---------------------------------------+




GetFXBarrierOptionSecurityCallCurrency

......................................
GetFXBarrierOptionSecurityCallCurrency
......................................


Returns the call currency from a FX barrier option security.



+-------------------------+----------+---------------------------------------+
| Parameter               | Required | Description                           |
+=========================+==========+=======================================+
| FXBarrierOptionSecurity | Yes      | A FX barrier option security to query |
+-------------------------+----------+---------------------------------------+




GetFXBarrierOptionSecurityExpiry

................................
GetFXBarrierOptionSecurityExpiry
................................


Returns the expiry from a FX barrier option security.



+-------------------------+----------+---------------------------------------+
| Parameter               | Required | Description                           |
+=========================+==========+=======================================+
| FXBarrierOptionSecurity | Yes      | A FX barrier option security to query |
+-------------------------+----------+---------------------------------------+




GetFXBarrierOptionSecurityLong

..............................
GetFXBarrierOptionSecurityLong
..............................


Returns the long flag from a FX barrier option security.



+-------------------------+----------+---------------------------------------+
| Parameter               | Required | Description                           |
+=========================+==========+=======================================+
| FXBarrierOptionSecurity | Yes      | A FX barrier option security to query |
+-------------------------+----------+---------------------------------------+




GetFXBarrierOptionSecurityMonitoringType

........................................
GetFXBarrierOptionSecurityMonitoringType
........................................


Returns the monitoring type from a FX barrier option security.



+-------------------------+----------+---------------------------------------+
| Parameter               | Required | Description                           |
+=========================+==========+=======================================+
| FXBarrierOptionSecurity | Yes      | A FX barrier option security to query |
+-------------------------+----------+---------------------------------------+




GetFXBarrierOptionSecurityPutAmount

...................................
GetFXBarrierOptionSecurityPutAmount
...................................


Returns the put amount from a FX barrier option security.



+-------------------------+----------+---------------------------------------+
| Parameter               | Required | Description                           |
+=========================+==========+=======================================+
| FXBarrierOptionSecurity | Yes      | A FX barrier option security to query |
+-------------------------+----------+---------------------------------------+




GetFXBarrierOptionSecurityPutCurrency

.....................................
GetFXBarrierOptionSecurityPutCurrency
.....................................


Returns the put currency from a FX barrier option security.



+-------------------------+----------+---------------------------------------+
| Parameter               | Required | Description                           |
+=========================+==========+=======================================+
| FXBarrierOptionSecurity | Yes      | A FX barrier option security to query |
+-------------------------+----------+---------------------------------------+




GetFXBarrierOptionSecuritySamplingFrequency

...........................................
GetFXBarrierOptionSecuritySamplingFrequency
...........................................


Returns the sampling frequency from a FX barrier option security.



+-------------------------+----------+---------------------------------------+
| Parameter               | Required | Description                           |
+=========================+==========+=======================================+
| FXBarrierOptionSecurity | Yes      | A FX barrier option security to query |
+-------------------------+----------+---------------------------------------+




GetFXBarrierOptionSecuritySettlementDate

........................................
GetFXBarrierOptionSecuritySettlementDate
........................................


Returns the settlement date from a FX barrier option security.



+-------------------------+----------+---------------------------------------+
| Parameter               | Required | Description                           |
+=========================+==========+=======================================+
| FXBarrierOptionSecurity | Yes      | A FX barrier option security to query |
+-------------------------+----------+---------------------------------------+




GetFXDigitalOptionSecurityCallAmount

....................................
GetFXDigitalOptionSecurityCallAmount
....................................


Returns the call amount from an FX digital option security.



+-------------------------+----------+----------------------------------------+
| Parameter               | Required | Description                            |
+=========================+==========+========================================+
| FXDigitalOptionSecurity | Yes      | An FX digital option security to query |
+-------------------------+----------+----------------------------------------+




GetFXDigitalOptionSecurityCallCurrency

......................................
GetFXDigitalOptionSecurityCallCurrency
......................................


Returns the call currency from an FX digital option security.



+-------------------------+----------+----------------------------------------+
| Parameter               | Required | Description                            |
+=========================+==========+========================================+
| FXDigitalOptionSecurity | Yes      | An FX digital option security to query |
+-------------------------+----------+----------------------------------------+




GetFXDigitalOptionSecurityExpiry

................................
GetFXDigitalOptionSecurityExpiry
................................


Returns the expiry from an FX digital option security.



+-------------------------+----------+----------------------------------------+
| Parameter               | Required | Description                            |
+=========================+==========+========================================+
| FXDigitalOptionSecurity | Yes      | An FX digital option security to query |
+-------------------------+----------+----------------------------------------+




GetFXDigitalOptionSecurityLong

..............................
GetFXDigitalOptionSecurityLong
..............................


Returns the long flag from an FX digital option security.



+-------------------------+----------+----------------------------------------+
| Parameter               | Required | Description                            |
+=========================+==========+========================================+
| FXDigitalOptionSecurity | Yes      | An FX digital option security to query |
+-------------------------+----------+----------------------------------------+




GetFXDigitalOptionSecurityPaymentCurrency

.........................................
GetFXDigitalOptionSecurityPaymentCurrency
.........................................


Returns the payment currency from an FX digital option security.



+-------------------------+----------+----------------------------------------+
| Parameter               | Required | Description                            |
+=========================+==========+========================================+
| FXDigitalOptionSecurity | Yes      | An FX digital option security to query |
+-------------------------+----------+----------------------------------------+




GetFXDigitalOptionSecurityPutAmount

...................................
GetFXDigitalOptionSecurityPutAmount
...................................


Returns the put amount from an FX digital option security.



+-------------------------+----------+----------------------------------------+
| Parameter               | Required | Description                            |
+=========================+==========+========================================+
| FXDigitalOptionSecurity | Yes      | An FX digital option security to query |
+-------------------------+----------+----------------------------------------+




GetFXDigitalOptionSecurityPutCurrency

.....................................
GetFXDigitalOptionSecurityPutCurrency
.....................................


Returns the put currency from an FX digital option security.



+-------------------------+----------+----------------------------------------+
| Parameter               | Required | Description                            |
+=========================+==========+========================================+
| FXDigitalOptionSecurity | Yes      | An FX digital option security to query |
+-------------------------+----------+----------------------------------------+




GetFXDigitalOptionSecuritySettlementDate

........................................
GetFXDigitalOptionSecuritySettlementDate
........................................


Returns the settlement date from an FX digital option security.



+-------------------------+----------+----------------------------------------+
| Parameter               | Required | Description                            |
+=========================+==========+========================================+
| FXDigitalOptionSecurity | Yes      | An FX digital option security to query |
+-------------------------+----------+----------------------------------------+




GetFXForwardSecurityForwardDate

...............................
GetFXForwardSecurityForwardDate
...............................


Returns the forward date from an FX forward security.



+-------------------+----------+---------------------------------+
| Parameter         | Required | Description                     |
+===================+==========+=================================+
| FXForwardSecurity | Yes      | An FX forward security to query |
+-------------------+----------+---------------------------------+




GetFXForwardSecurityPayAmount

.............................
GetFXForwardSecurityPayAmount
.............................


Returns the pay amount from an FX forward security.



+-------------------+----------+---------------------------------+
| Parameter         | Required | Description                     |
+===================+==========+=================================+
| FXForwardSecurity | Yes      | An FX forward security to query |
+-------------------+----------+---------------------------------+




GetFXForwardSecurityPayCurrency

...............................
GetFXForwardSecurityPayCurrency
...............................


Returns the pay currency from an FX forward security.



+-------------------+----------+---------------------------------+
| Parameter         | Required | Description                     |
+===================+==========+=================================+
| FXForwardSecurity | Yes      | An FX forward security to query |
+-------------------+----------+---------------------------------+




GetFXForwardSecurityReceiveAmount

.................................
GetFXForwardSecurityReceiveAmount
.................................


Returns the receive amount from an FX forward security.



+-------------------+----------+---------------------------------+
| Parameter         | Required | Description                     |
+===================+==========+=================================+
| FXForwardSecurity | Yes      | An FX forward security to query |
+-------------------+----------+---------------------------------+




GetFXForwardSecurityReceiveCurrency

...................................
GetFXForwardSecurityReceiveCurrency
...................................


Returns the receive currency from an FX forward security.



+-------------------+----------+---------------------------------+
| Parameter         | Required | Description                     |
+===================+==========+=================================+
| FXForwardSecurity | Yes      | An FX forward security to query |
+-------------------+----------+---------------------------------+




GetFXForwardSecurityRegionId

............................
GetFXForwardSecurityRegionId
............................


Returns the identifier of the region from an FX forward security.



+-------------------+----------+---------------------------------+
| Parameter         | Required | Description                     |
+===================+==========+=================================+
| FXForwardSecurity | Yes      | An FX forward security to query |
+-------------------+----------+---------------------------------+




GetFXFutureSecurityDenominator

..............................
GetFXFutureSecurityDenominator
..............................


Returns the denominator currency from an FX future security.



+------------------+----------+--------------------------------+
| Parameter        | Required | Description                    |
+==================+==========+================================+
| FXFutureSecurity | Yes      | An FX future security to query |
+------------------+----------+--------------------------------+




GetFXFutureSecurityMultiplicationFactor

.......................................
GetFXFutureSecurityMultiplicationFactor
.......................................


Returns the multiplication factor, i.e. number of numerator units per denominator unit from an FX future security.



+------------------+----------+--------------------------------+
| Parameter        | Required | Description                    |
+==================+==========+================================+
| FXFutureSecurity | Yes      | An FX future security to query |
+------------------+----------+--------------------------------+




GetFXFutureSecurityNumerator

............................
GetFXFutureSecurityNumerator
............................


Returns the numerator currency from an FX future security.



+------------------+----------+--------------------------------+
| Parameter        | Required | Description                    |
+==================+==========+================================+
| FXFutureSecurity | Yes      | An FX future security to query |
+------------------+----------+--------------------------------+




GetFXOptionSecurityCallAmount

.............................
GetFXOptionSecurityCallAmount
.............................


Returns the call amount from an FX option security.



+------------------+----------+--------------------------------+
| Parameter        | Required | Description                    |
+==================+==========+================================+
| FXOptionSecurity | Yes      | An FX option security to query |
+------------------+----------+--------------------------------+




GetFXOptionSecurityCallCurrency

...............................
GetFXOptionSecurityCallCurrency
...............................


Returns the call currency from an FX option security.



+------------------+----------+--------------------------------+
| Parameter        | Required | Description                    |
+==================+==========+================================+
| FXOptionSecurity | Yes      | An FX option security to query |
+------------------+----------+--------------------------------+




GetFXOptionSecurityExerciseType

...............................
GetFXOptionSecurityExerciseType
...............................


Returns the exercise type from an FX option security.



+------------------+----------+--------------------------------+
| Parameter        | Required | Description                    |
+==================+==========+================================+
| FXOptionSecurity | Yes      | An FX option security to query |
+------------------+----------+--------------------------------+




GetFXOptionSecurityExpiry

.........................
GetFXOptionSecurityExpiry
.........................


Returns the expiry from an FX option security.



+------------------+----------+--------------------------------+
| Parameter        | Required | Description                    |
+==================+==========+================================+
| FXOptionSecurity | Yes      | An FX option security to query |
+------------------+----------+--------------------------------+




GetFXOptionSecurityLong

.......................
GetFXOptionSecurityLong
.......................


Returns the long flag from an FX option security.



+------------------+----------+--------------------------------+
| Parameter        | Required | Description                    |
+==================+==========+================================+
| FXOptionSecurity | Yes      | An FX option security to query |
+------------------+----------+--------------------------------+




GetFXOptionSecurityPutAmount

............................
GetFXOptionSecurityPutAmount
............................


Returns the put amount from an FX option security.



+------------------+----------+--------------------------------+
| Parameter        | Required | Description                    |
+==================+==========+================================+
| FXOptionSecurity | Yes      | An FX option security to query |
+------------------+----------+--------------------------------+




GetFXOptionSecurityPutCurrency

..............................
GetFXOptionSecurityPutCurrency
..............................


Returns the put currency from an FX option security.



+------------------+----------+--------------------------------+
| Parameter        | Required | Description                    |
+==================+==========+================================+
| FXOptionSecurity | Yes      | An FX option security to query |
+------------------+----------+--------------------------------+




GetFXOptionSecuritySettlementDate

.................................
GetFXOptionSecuritySettlementDate
.................................


Returns the settlement date from an FX option security.



+------------------+----------+--------------------------------+
| Parameter        | Required | Description                    |
+==================+==========+================================+
| FXOptionSecurity | Yes      | An FX option security to query |
+------------------+----------+--------------------------------+




SetFixedInterestRateLegRate

...........................
SetFixedInterestRateLegRate
...........................


Updates the fixed interest rate as a decimal (e.g. 5% = 0.05) of a fixed interest rate leg. The original object is unchanged - a new object is returned with the updated value.



+----------------------+----------+-------------------------------------------------------+
| Parameter            | Required | Description                                           |
+======================+==========+=======================================================+
| fixedInterestRateLeg | Yes      | A fixed interest rate leg to update                   |
+----------------------+----------+-------------------------------------------------------+
| rate                 | Yes      | The fixed interest rate as a decimal (e.g. 5% = 0.05) |
+----------------------+----------+-------------------------------------------------------+




SetFixedVarianceSwapLegStrike

.............................
SetFixedVarianceSwapLegStrike
.............................


Updates the the strike of the variance swap of a fixed leg of a variance swap. The original object is unchanged - a new object is returned with the updated value.



+----------------------+----------+------------------------------------------+
| Parameter            | Required | Description                              |
+======================+==========+==========================================+
| fixedVarianceSwapLeg | Yes      | A fixed leg of a variance swap to update |
+----------------------+----------+------------------------------------------+
| strike               | Yes      | The the strike of the variance swap      |
+----------------------+----------+------------------------------------------+




SetFixedVarianceSwapLegType

...........................
SetFixedVarianceSwapLegType
...........................


Updates the the type of the variance swap of a fixed leg of a variance swap. The original object is unchanged - a new object is returned with the updated value.



+----------------------+----------+------------------------------------------+
| Parameter            | Required | Description                              |
+======================+==========+==========================================+
| fixedVarianceSwapLeg | Yes      | A fixed leg of a variance swap to update |
+----------------------+----------+------------------------------------------+
| type                 |          | The the type of the variance swap        |
+----------------------+----------+------------------------------------------+




SetFloatingGearingIRLegGearing

..............................
SetFloatingGearingIRLegGearing
..............................


Updates the gearing of a floating gearing interest rate leg of a swap. The original object is unchanged - a new object is returned with the updated value.



+----------------------+----------+----------------------------------------------------------+
| Parameter            | Required | Description                                              |
+======================+==========+==========================================================+
| floatingGearingIRLeg | Yes      | A floating gearing interest rate leg of a swap to update |
+----------------------+----------+----------------------------------------------------------+
| gearing              | Yes      | The gearing                                              |
+----------------------+----------+----------------------------------------------------------+




SetFloatingInterestRateLegFloatingRateType

..........................................
SetFloatingInterestRateLegFloatingRateType
..........................................


Updates the floating rate type of a floating interest rate leg of a swap. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------+--------------------------------------------------+
| Parameter               | Required | Description                                      |
+=========================+==========+==================================================+
| floatingInterestRateLeg | Yes      | A floating interest rate leg of a swap to update |
+-------------------------+----------+--------------------------------------------------+
| floatingRateType        |          | The floating rate type                           |
+-------------------------+----------+--------------------------------------------------+




SetFloatingInterestRateLegFloatingReferenceRateId

.................................................
SetFloatingInterestRateLegFloatingReferenceRateId
.................................................


Updates the identifier of the object used to provide the reference rate of a floating interest rate leg of a swap. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------+-----------------------------------------------------------------+
| Parameter               | Required | Description                                                     |
+=========================+==========+=================================================================+
| floatingInterestRateLeg | Yes      | A floating interest rate leg of a swap to update                |
+-------------------------+----------+-----------------------------------------------------------------+
| floatingReferenceRateId |          | The identifier of the object used to provide the reference rate |
+-------------------------+----------+-----------------------------------------------------------------+




SetFloatingInterestRateLegInitialFloatingRate

.............................................
SetFloatingInterestRateLegInitialFloatingRate
.............................................


Updates the floating rate of the first period of the swap (expressed as a decimal) of a floating interest rate leg of a swap. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------+----------------------------------------------------------------------------+
| Parameter               | Required | Description                                                                |
+=========================+==========+============================================================================+
| floatingInterestRateLeg | Yes      | A floating interest rate leg of a swap to update                           |
+-------------------------+----------+----------------------------------------------------------------------------+
| initialFloatingRate     |          | The floating rate of the first period of the swap (expressed as a decimal) |
+-------------------------+----------+----------------------------------------------------------------------------+




SetFloatingInterestRateLegOffsetFixing

......................................
SetFloatingInterestRateLegOffsetFixing
......................................


Updates the offset fixing frequency of a floating interest rate leg of a swap. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------+--------------------------------------------------+
| Parameter               | Required | Description                                      |
+=========================+==========+==================================================+
| floatingInterestRateLeg | Yes      | A floating interest rate leg of a swap to update |
+-------------------------+----------+--------------------------------------------------+
| offsetFixing            |          | The offset fixing frequency                      |
+-------------------------+----------+--------------------------------------------------+




SetFloatingInterestRateLegSettlementDays

........................................
SetFloatingInterestRateLegSettlementDays
........................................


Updates the settlement days of a floating interest rate leg of a swap. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------+--------------------------------------------------+
| Parameter               | Required | Description                                      |
+=========================+==========+==================================================+
| floatingInterestRateLeg | Yes      | A floating interest rate leg of a swap to update |
+-------------------------+----------+--------------------------------------------------+
| settlementDays          |          | The settlement days                              |
+-------------------------+----------+--------------------------------------------------+




SetFloatingSpreadIRLegSpread

............................
SetFloatingSpreadIRLegSpread
............................


Updates the spread of a floating spread interest rate leg of a swap. The original object is unchanged - a new object is returned with the updated value.



+---------------------+----------+---------------------------------------------------------+
| Parameter           | Required | Description                                             |
+=====================+==========+=========================================================+
| floatingSpreadIRLeg | Yes      | A floating spread interest rate leg of a swap to update |
+---------------------+----------+---------------------------------------------------------+
| spread              | Yes      | The spread                                              |
+---------------------+----------+---------------------------------------------------------+




SetFloatingVarianceSwapLegAnnualizationFactor

.............................................
SetFloatingVarianceSwapLegAnnualizationFactor
.............................................


Updates the the annualization factor of a floating leg of a variance swap. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------+---------------------------------------------+
| Parameter               | Required | Description                                 |
+=========================+==========+=============================================+
| floatingVarianceSwapLeg | Yes      | A floating leg of a variance swap to update |
+-------------------------+----------+---------------------------------------------+
| annualizationFactor     |          | The the annualization factor                |
+-------------------------+----------+---------------------------------------------+




SetFloatingVarianceSwapLegMonitoringFrequency

.............................................
SetFloatingVarianceSwapLegMonitoringFrequency
.............................................


Updates the the monitoring frequency of the swap of a floating leg of a variance swap. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------+---------------------------------------------+
| Parameter               | Required | Description                                 |
+=========================+==========+=============================================+
| floatingVarianceSwapLeg | Yes      | A floating leg of a variance swap to update |
+-------------------------+----------+---------------------------------------------+
| monitoringFrequency     |          | The the monitoring frequency of the swap    |
+-------------------------+----------+---------------------------------------------+




SetFloatingVarianceSwapLegUnderlyingId

......................................
SetFloatingVarianceSwapLegUnderlyingId
......................................


Updates the the identifier of the underlying TODO of a floating leg of a variance swap. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------+---------------------------------------------+
| Parameter               | Required | Description                                 |
+=========================+==========+=============================================+
| floatingVarianceSwapLeg | Yes      | A floating leg of a variance swap to update |
+-------------------------+----------+---------------------------------------------+
| underlyingId            |          | The the identifier of the underlying TODO   |
+-------------------------+----------+---------------------------------------------+




SetForwardSwapSecurityForwardStartDate

......................................
SetForwardSwapSecurityForwardStartDate
......................................


Updates the start date of the forward swap of a forward swap security. The original object is unchanged - a new object is returned with the updated value.



+---------------------+----------+------------------------------------+
| Parameter           | Required | Description                        |
+=====================+==========+====================================+
| forwardSwapSecurity | Yes      | A forward swap security to update  |
+---------------------+----------+------------------------------------+
| forwardStartDate    |          | The start date of the forward swap |
+---------------------+----------+------------------------------------+




SetFRASecurityAmount

....................
SetFRASecurityAmount
....................


Updates the notional amount of a forward rate agreement security. The original object is unchanged - a new object is returned with the updated value.



+-------------+----------+---------------------------------------------+
| Parameter   | Required | Description                                 |
+=============+==========+=============================================+
| FRASecurity | Yes      | A forward rate agreement security to update |
+-------------+----------+---------------------------------------------+
| amount      | Yes      | The notional amount                         |
+-------------+----------+---------------------------------------------+




SetFRASecurityCurrency

......................
SetFRASecurityCurrency
......................


Updates the currency of a forward rate agreement security. The original object is unchanged - a new object is returned with the updated value.



+-------------+----------+---------------------------------------------+
| Parameter   | Required | Description                                 |
+=============+==========+=============================================+
| FRASecurity | Yes      | A forward rate agreement security to update |
+-------------+----------+---------------------------------------------+
| currency    |          | The currency                                |
+-------------+----------+---------------------------------------------+




SetFRASecurityEndDate

.....................
SetFRASecurityEndDate
.....................


Updates the end date of a forward rate agreement security. The original object is unchanged - a new object is returned with the updated value.



+-------------+----------+---------------------------------------------+
| Parameter   | Required | Description                                 |
+=============+==========+=============================================+
| FRASecurity | Yes      | A forward rate agreement security to update |
+-------------+----------+---------------------------------------------+
| endDate     |          | The end date                                |
+-------------+----------+---------------------------------------------+




SetFRASecurityFixingDate

........................
SetFRASecurityFixingDate
........................


Updates the fixing date of a forward rate agreement security. The original object is unchanged - a new object is returned with the updated value.



+-------------+----------+---------------------------------------------+
| Parameter   | Required | Description                                 |
+=============+==========+=============================================+
| FRASecurity | Yes      | A forward rate agreement security to update |
+-------------+----------+---------------------------------------------+
| fixingDate  |          | The fixing date                             |
+-------------+----------+---------------------------------------------+




SetFRASecurityRate

..................
SetFRASecurityRate
..................


Updates the rate as a decimal (e.g. 5% = 0.05) of a forward rate agreement security. The original object is unchanged - a new object is returned with the updated value.



+-------------+----------+---------------------------------------------+
| Parameter   | Required | Description                                 |
+=============+==========+=============================================+
| FRASecurity | Yes      | A forward rate agreement security to update |
+-------------+----------+---------------------------------------------+
| rate        | Yes      | The rate as a decimal (e.g. 5% = 0.05)      |
+-------------+----------+---------------------------------------------+




SetFRASecurityRegionId

......................
SetFRASecurityRegionId
......................


Updates the region identifier of a forward rate agreement security. The original object is unchanged - a new object is returned with the updated value.



+-------------+----------+---------------------------------------------+
| Parameter   | Required | Description                                 |
+=============+==========+=============================================+
| FRASecurity | Yes      | A forward rate agreement security to update |
+-------------+----------+---------------------------------------------+
| regionId    |          | The region identifier                       |
+-------------+----------+---------------------------------------------+




SetFRASecurityStartDate

.......................
SetFRASecurityStartDate
.......................


Updates the start date of a forward rate agreement security. The original object is unchanged - a new object is returned with the updated value.



+-------------+----------+---------------------------------------------+
| Parameter   | Required | Description                                 |
+=============+==========+=============================================+
| FRASecurity | Yes      | A forward rate agreement security to update |
+-------------+----------+---------------------------------------------+
| startDate   |          | The start date                              |
+-------------+----------+---------------------------------------------+




SetFRASecurityUnderlyingId

..........................
SetFRASecurityUnderlyingId
..........................


Updates the underlying identifier of a forward rate agreement security. The original object is unchanged - a new object is returned with the updated value.



+--------------+----------+---------------------------------------------+
| Parameter    | Required | Description                                 |
+==============+==========+=============================================+
| FRASecurity  | Yes      | A forward rate agreement security to update |
+--------------+----------+---------------------------------------------+
| underlyingId |          | The underlying identifier                   |
+--------------+----------+---------------------------------------------+




SetFutureSecurityContractCategory

.................................
SetFutureSecurityContractCategory
.................................


Updates the category of a future security. The original object is unchanged - a new object is returned with the updated value.



+------------------+----------+-----------------------------+
| Parameter        | Required | Description                 |
+==================+==========+=============================+
| futureSecurity   | Yes      | A future security to update |
+------------------+----------+-----------------------------+
| contractCategory |          | The category                |
+------------------+----------+-----------------------------+




SetFutureSecurityCurrency

.........................
SetFutureSecurityCurrency
.........................


Updates the currency of a future security. The original object is unchanged - a new object is returned with the updated value.



+----------------+----------+-----------------------------+
| Parameter      | Required | Description                 |
+================+==========+=============================+
| futureSecurity | Yes      | A future security to update |
+----------------+----------+-----------------------------+
| currency       |          | The currency                |
+----------------+----------+-----------------------------+




SetFutureSecurityExpiry

.......................
SetFutureSecurityExpiry
.......................


Updates the expiry date of a future security. The original object is unchanged - a new object is returned with the updated value.



+----------------+----------+-----------------------------+
| Parameter      | Required | Description                 |
+================+==========+=============================+
| futureSecurity | Yes      | A future security to update |
+----------------+----------+-----------------------------+
| expiry         |          | The expiry date             |
+----------------+----------+-----------------------------+




SetFutureSecuritySettlementExchange

...................................
SetFutureSecuritySettlementExchange
...................................


Updates the settlement exchange of a future security. The original object is unchanged - a new object is returned with the updated value.



+--------------------+----------+-----------------------------+
| Parameter          | Required | Description                 |
+====================+==========+=============================+
| futureSecurity     | Yes      | A future security to update |
+--------------------+----------+-----------------------------+
| settlementExchange |          | The settlement exchange     |
+--------------------+----------+-----------------------------+




SetFutureSecurityTradingExchange

................................
SetFutureSecurityTradingExchange
................................


Updates the trading exchange of a future security. The original object is unchanged - a new object is returned with the updated value.



+-----------------+----------+-----------------------------+
| Parameter       | Required | Description                 |
+=================+==========+=============================+
| futureSecurity  | Yes      | A future security to update |
+-----------------+----------+-----------------------------+
| tradingExchange |          | The trading exchange        |
+-----------------+----------+-----------------------------+




SetFutureSecurityUnitAmount

...........................
SetFutureSecurityUnitAmount
...........................


Updates the unit amount of a future security. The original object is unchanged - a new object is returned with the updated value.



+----------------+----------+-----------------------------+
| Parameter      | Required | Description                 |
+================+==========+=============================+
| futureSecurity | Yes      | A future security to update |
+----------------+----------+-----------------------------+
| unitAmount     | Yes      | The unit amount             |
+----------------+----------+-----------------------------+




SetFXBarrierOptionSecurityBarrierDirection

..........................................
SetFXBarrierOptionSecurityBarrierDirection
..........................................


Updates the barrier direction of a FX barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------+----------------------------------------+
| Parameter               | Required | Description                            |
+=========================+==========+========================================+
| FXBarrierOptionSecurity | Yes      | A FX barrier option security to update |
+-------------------------+----------+----------------------------------------+
| barrierDirection        |          | The barrier direction                  |
+-------------------------+----------+----------------------------------------+




SetFXBarrierOptionSecurityBarrierLevel

......................................
SetFXBarrierOptionSecurityBarrierLevel
......................................


Updates the barrier level of a FX barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------+----------------------------------------+
| Parameter               | Required | Description                            |
+=========================+==========+========================================+
| FXBarrierOptionSecurity | Yes      | A FX barrier option security to update |
+-------------------------+----------+----------------------------------------+
| barrierLevel            | Yes      | The barrier level                      |
+-------------------------+----------+----------------------------------------+




SetFXBarrierOptionSecurityBarrierType

.....................................
SetFXBarrierOptionSecurityBarrierType
.....................................


Updates the barrier type of a FX barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------+----------------------------------------+
| Parameter               | Required | Description                            |
+=========================+==========+========================================+
| FXBarrierOptionSecurity | Yes      | A FX barrier option security to update |
+-------------------------+----------+----------------------------------------+
| barrierType             |          | The barrier type                       |
+-------------------------+----------+----------------------------------------+




SetFXBarrierOptionSecurityCallAmount

....................................
SetFXBarrierOptionSecurityCallAmount
....................................


Updates the call amount of a FX barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------+----------------------------------------+
| Parameter               | Required | Description                            |
+=========================+==========+========================================+
| FXBarrierOptionSecurity | Yes      | A FX barrier option security to update |
+-------------------------+----------+----------------------------------------+
| callAmount              | Yes      | The call amount                        |
+-------------------------+----------+----------------------------------------+




SetFXBarrierOptionSecurityCallCurrency

......................................
SetFXBarrierOptionSecurityCallCurrency
......................................


Updates the call currency of a FX barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------+----------------------------------------+
| Parameter               | Required | Description                            |
+=========================+==========+========================================+
| FXBarrierOptionSecurity | Yes      | A FX barrier option security to update |
+-------------------------+----------+----------------------------------------+
| callCurrency            |          | The call currency                      |
+-------------------------+----------+----------------------------------------+




SetFXBarrierOptionSecurityExpiry

................................
SetFXBarrierOptionSecurityExpiry
................................


Updates the expiry of a FX barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------+----------------------------------------+
| Parameter               | Required | Description                            |
+=========================+==========+========================================+
| FXBarrierOptionSecurity | Yes      | A FX barrier option security to update |
+-------------------------+----------+----------------------------------------+
| expiry                  |          | The expiry                             |
+-------------------------+----------+----------------------------------------+




SetFXBarrierOptionSecurityMonitoringType

........................................
SetFXBarrierOptionSecurityMonitoringType
........................................


Updates the monitoring type of a FX barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------+----------------------------------------+
| Parameter               | Required | Description                            |
+=========================+==========+========================================+
| FXBarrierOptionSecurity | Yes      | A FX barrier option security to update |
+-------------------------+----------+----------------------------------------+
| monitoringType          |          | The monitoring type                    |
+-------------------------+----------+----------------------------------------+




SetFXBarrierOptionSecurityPutAmount

...................................
SetFXBarrierOptionSecurityPutAmount
...................................


Updates the put amount of a FX barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------+----------------------------------------+
| Parameter               | Required | Description                            |
+=========================+==========+========================================+
| FXBarrierOptionSecurity | Yes      | A FX barrier option security to update |
+-------------------------+----------+----------------------------------------+
| putAmount               | Yes      | The put amount                         |
+-------------------------+----------+----------------------------------------+




SetFXBarrierOptionSecurityPutCurrency

.....................................
SetFXBarrierOptionSecurityPutCurrency
.....................................


Updates the put currency of a FX barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------+----------------------------------------+
| Parameter               | Required | Description                            |
+=========================+==========+========================================+
| FXBarrierOptionSecurity | Yes      | A FX barrier option security to update |
+-------------------------+----------+----------------------------------------+
| putCurrency             |          | The put currency                       |
+-------------------------+----------+----------------------------------------+




SetFXBarrierOptionSecuritySamplingFrequency

...........................................
SetFXBarrierOptionSecuritySamplingFrequency
...........................................


Updates the sampling frequency of a FX barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------+----------------------------------------+
| Parameter               | Required | Description                            |
+=========================+==========+========================================+
| FXBarrierOptionSecurity | Yes      | A FX barrier option security to update |
+-------------------------+----------+----------------------------------------+
| samplingFrequency       |          | The sampling frequency                 |
+-------------------------+----------+----------------------------------------+




SetFXBarrierOptionSecuritySettlementDate

........................................
SetFXBarrierOptionSecuritySettlementDate
........................................


Updates the settlement date of a FX barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------+----------------------------------------+
| Parameter               | Required | Description                            |
+=========================+==========+========================================+
| FXBarrierOptionSecurity | Yes      | A FX barrier option security to update |
+-------------------------+----------+----------------------------------------+
| settlementDate          |          | The settlement date                    |
+-------------------------+----------+----------------------------------------+




SetFXDigitalOptionSecurityCallAmount

....................................
SetFXDigitalOptionSecurityCallAmount
....................................


Updates the call amount of an FX digital option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------+-----------------------------------------+
| Parameter               | Required | Description                             |
+=========================+==========+=========================================+
| FXDigitalOptionSecurity | Yes      | An FX digital option security to update |
+-------------------------+----------+-----------------------------------------+
| callAmount              | Yes      | The call amount                         |
+-------------------------+----------+-----------------------------------------+




SetFXDigitalOptionSecurityCallCurrency

......................................
SetFXDigitalOptionSecurityCallCurrency
......................................


Updates the call currency of an FX digital option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------+-----------------------------------------+
| Parameter               | Required | Description                             |
+=========================+==========+=========================================+
| FXDigitalOptionSecurity | Yes      | An FX digital option security to update |
+-------------------------+----------+-----------------------------------------+
| callCurrency            |          | The call currency                       |
+-------------------------+----------+-----------------------------------------+




SetFXDigitalOptionSecurityExpiry

................................
SetFXDigitalOptionSecurityExpiry
................................


Updates the expiry of an FX digital option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------+-----------------------------------------+
| Parameter               | Required | Description                             |
+=========================+==========+=========================================+
| FXDigitalOptionSecurity | Yes      | An FX digital option security to update |
+-------------------------+----------+-----------------------------------------+
| expiry                  |          | The expiry                              |
+-------------------------+----------+-----------------------------------------+




SetFXDigitalOptionSecurityPaymentCurrency

.........................................
SetFXDigitalOptionSecurityPaymentCurrency
.........................................


Updates the payment currency of an FX digital option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------+-----------------------------------------+
| Parameter               | Required | Description                             |
+=========================+==========+=========================================+
| FXDigitalOptionSecurity | Yes      | An FX digital option security to update |
+-------------------------+----------+-----------------------------------------+
| paymentCurrency         |          | The payment currency                    |
+-------------------------+----------+-----------------------------------------+




SetFXDigitalOptionSecurityPutAmount

...................................
SetFXDigitalOptionSecurityPutAmount
...................................


Updates the put amount of an FX digital option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------+-----------------------------------------+
| Parameter               | Required | Description                             |
+=========================+==========+=========================================+
| FXDigitalOptionSecurity | Yes      | An FX digital option security to update |
+-------------------------+----------+-----------------------------------------+
| putAmount               | Yes      | The put amount                          |
+-------------------------+----------+-----------------------------------------+




SetFXDigitalOptionSecurityPutCurrency

.....................................
SetFXDigitalOptionSecurityPutCurrency
.....................................


Updates the put currency of an FX digital option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------+-----------------------------------------+
| Parameter               | Required | Description                             |
+=========================+==========+=========================================+
| FXDigitalOptionSecurity | Yes      | An FX digital option security to update |
+-------------------------+----------+-----------------------------------------+
| putCurrency             |          | The put currency                        |
+-------------------------+----------+-----------------------------------------+




SetFXDigitalOptionSecuritySettlementDate

........................................
SetFXDigitalOptionSecuritySettlementDate
........................................


Updates the settlement date of an FX digital option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------+-----------------------------------------+
| Parameter               | Required | Description                             |
+=========================+==========+=========================================+
| FXDigitalOptionSecurity | Yes      | An FX digital option security to update |
+-------------------------+----------+-----------------------------------------+
| settlementDate          |          | The settlement date                     |
+-------------------------+----------+-----------------------------------------+




SetFXForwardSecurityForwardDate

...............................
SetFXForwardSecurityForwardDate
...............................


Updates the forward date of an FX forward security. The original object is unchanged - a new object is returned with the updated value.



+-------------------+----------+----------------------------------+
| Parameter         | Required | Description                      |
+===================+==========+==================================+
| FXForwardSecurity | Yes      | An FX forward security to update |
+-------------------+----------+----------------------------------+
| forwardDate       |          | The forward date                 |
+-------------------+----------+----------------------------------+




SetFXForwardSecurityPayAmount

.............................
SetFXForwardSecurityPayAmount
.............................


Updates the pay amount of an FX forward security. The original object is unchanged - a new object is returned with the updated value.



+-------------------+----------+----------------------------------+
| Parameter         | Required | Description                      |
+===================+==========+==================================+
| FXForwardSecurity | Yes      | An FX forward security to update |
+-------------------+----------+----------------------------------+
| payAmount         | Yes      | The pay amount                   |
+-------------------+----------+----------------------------------+




SetFXForwardSecurityPayCurrency

...............................
SetFXForwardSecurityPayCurrency
...............................


Updates the pay currency of an FX forward security. The original object is unchanged - a new object is returned with the updated value.



+-------------------+----------+----------------------------------+
| Parameter         | Required | Description                      |
+===================+==========+==================================+
| FXForwardSecurity | Yes      | An FX forward security to update |
+-------------------+----------+----------------------------------+
| payCurrency       |          | The pay currency                 |
+-------------------+----------+----------------------------------+




SetFXForwardSecurityReceiveAmount

.................................
SetFXForwardSecurityReceiveAmount
.................................


Updates the receive amount of an FX forward security. The original object is unchanged - a new object is returned with the updated value.



+-------------------+----------+----------------------------------+
| Parameter         | Required | Description                      |
+===================+==========+==================================+
| FXForwardSecurity | Yes      | An FX forward security to update |
+-------------------+----------+----------------------------------+
| receiveAmount     | Yes      | The receive amount               |
+-------------------+----------+----------------------------------+




SetFXForwardSecurityReceiveCurrency

...................................
SetFXForwardSecurityReceiveCurrency
...................................


Updates the receive currency of an FX forward security. The original object is unchanged - a new object is returned with the updated value.



+-------------------+----------+----------------------------------+
| Parameter         | Required | Description                      |
+===================+==========+==================================+
| FXForwardSecurity | Yes      | An FX forward security to update |
+-------------------+----------+----------------------------------+
| receiveCurrency   |          | The receive currency             |
+-------------------+----------+----------------------------------+




SetFXForwardSecurityRegionId

............................
SetFXForwardSecurityRegionId
............................


Updates the identifier of the region of an FX forward security. The original object is unchanged - a new object is returned with the updated value.



+-------------------+----------+----------------------------------+
| Parameter         | Required | Description                      |
+===================+==========+==================================+
| FXForwardSecurity | Yes      | An FX forward security to update |
+-------------------+----------+----------------------------------+
| regionId          |          | The identifier of the region     |
+-------------------+----------+----------------------------------+




SetFXFutureSecurityDenominator

..............................
SetFXFutureSecurityDenominator
..............................


Updates the denominator currency of an FX future security. The original object is unchanged - a new object is returned with the updated value.



+------------------+----------+---------------------------------+
| Parameter        | Required | Description                     |
+==================+==========+=================================+
| FXFutureSecurity | Yes      | An FX future security to update |
+------------------+----------+---------------------------------+
| denominator      |          | The denominator currency        |
+------------------+----------+---------------------------------+




SetFXFutureSecurityMultiplicationFactor

.......................................
SetFXFutureSecurityMultiplicationFactor
.......................................


Updates the multiplication factor, i.e. number of numerator units per denominator unit of an FX future security. The original object is unchanged - a new object is returned with the updated value.



+----------------------+----------+--------------------------------------------------------------------------------+
| Parameter            | Required | Description                                                                    |
+======================+==========+================================================================================+
| FXFutureSecurity     | Yes      | An FX future security to update                                                |
+----------------------+----------+--------------------------------------------------------------------------------+
| multiplicationFactor | Yes      | The multiplication factor, i.e. number of numerator units per denominator unit |
+----------------------+----------+--------------------------------------------------------------------------------+




SetFXFutureSecurityNumerator

............................
SetFXFutureSecurityNumerator
............................


Updates the numerator currency of an FX future security. The original object is unchanged - a new object is returned with the updated value.



+------------------+----------+---------------------------------+
| Parameter        | Required | Description                     |
+==================+==========+=================================+
| FXFutureSecurity | Yes      | An FX future security to update |
+------------------+----------+---------------------------------+
| numerator        |          | The numerator currency          |
+------------------+----------+---------------------------------+




SetFXOptionSecurityCallAmount

.............................
SetFXOptionSecurityCallAmount
.............................


Updates the call amount of an FX option security. The original object is unchanged - a new object is returned with the updated value.



+------------------+----------+---------------------------------+
| Parameter        | Required | Description                     |
+==================+==========+=================================+
| FXOptionSecurity | Yes      | An FX option security to update |
+------------------+----------+---------------------------------+
| callAmount       | Yes      | The call amount                 |
+------------------+----------+---------------------------------+




SetFXOptionSecurityCallCurrency

...............................
SetFXOptionSecurityCallCurrency
...............................


Updates the call currency of an FX option security. The original object is unchanged - a new object is returned with the updated value.



+------------------+----------+---------------------------------+
| Parameter        | Required | Description                     |
+==================+==========+=================================+
| FXOptionSecurity | Yes      | An FX option security to update |
+------------------+----------+---------------------------------+
| callCurrency     |          | The call currency               |
+------------------+----------+---------------------------------+




SetFXOptionSecurityExerciseType

...............................
SetFXOptionSecurityExerciseType
...............................


Updates the exercise type of an FX option security. The original object is unchanged - a new object is returned with the updated value.



+------------------+----------+---------------------------------+
| Parameter        | Required | Description                     |
+==================+==========+=================================+
| FXOptionSecurity | Yes      | An FX option security to update |
+------------------+----------+---------------------------------+
| exerciseType     |          | The exercise type               |
+------------------+----------+---------------------------------+




SetFXOptionSecurityExpiry

.........................
SetFXOptionSecurityExpiry
.........................


Updates the expiry of an FX option security. The original object is unchanged - a new object is returned with the updated value.



+------------------+----------+---------------------------------+
| Parameter        | Required | Description                     |
+==================+==========+=================================+
| FXOptionSecurity | Yes      | An FX option security to update |
+------------------+----------+---------------------------------+
| expiry           |          | The expiry                      |
+------------------+----------+---------------------------------+




SetFXOptionSecurityPutAmount

............................
SetFXOptionSecurityPutAmount
............................


Updates the put amount of an FX option security. The original object is unchanged - a new object is returned with the updated value.



+------------------+----------+---------------------------------+
| Parameter        | Required | Description                     |
+==================+==========+=================================+
| FXOptionSecurity | Yes      | An FX option security to update |
+------------------+----------+---------------------------------+
| putAmount        | Yes      | The put amount                  |
+------------------+----------+---------------------------------+




SetFXOptionSecurityPutCurrency

..............................
SetFXOptionSecurityPutCurrency
..............................


Updates the put currency of an FX option security. The original object is unchanged - a new object is returned with the updated value.



+------------------+----------+---------------------------------+
| Parameter        | Required | Description                     |
+==================+==========+=================================+
| FXOptionSecurity | Yes      | An FX option security to update |
+------------------+----------+---------------------------------+
| putCurrency      |          | The put currency                |
+------------------+----------+---------------------------------+




SetFXOptionSecuritySettlementDate

.................................
SetFXOptionSecuritySettlementDate
.................................


Updates the settlement date of an FX option security. The original object is unchanged - a new object is returned with the updated value.



+------------------+----------+---------------------------------+
| Parameter        | Required | Description                     |
+==================+==========+=================================+
| FXOptionSecurity | Yes      | An FX option security to update |
+------------------+----------+---------------------------------+
| settlementDate   |          | The settlement date             |
+------------------+----------+---------------------------------+



