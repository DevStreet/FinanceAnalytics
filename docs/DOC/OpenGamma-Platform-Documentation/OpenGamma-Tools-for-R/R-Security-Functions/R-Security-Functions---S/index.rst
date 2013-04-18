title: R Security Functions - S
shortcut: DOC:R Security Functions - S
---
ExpandSecurity

..............
ExpandSecurity
..............


Expand the contents of a security.



+-----------+----------+---------------------+
| Parameter | Required | Description         |
+===========+==========+=====================+
| security  | Yes      | A security to query |
+-----------+----------+---------------------+



The Security base class contains few attributes. For a more detailed expansion, use one of the sub-class expansion methods (e.g. ExpandEquityOptionSecurity). The asset class, as returned by the GetSecurityType function, can be used to determine which of the sub-class expansion methods to use.

ExpandSecurityNotional

......................
ExpandSecurityNotional
......................


Expand the contents of a notional value defined as a security .



+------------------+----------+--------------------------------------------------+
| Parameter        | Required | Description                                      |
+==================+==========+==================================================+
| securityNotional | Yes      | A notional value defined as a security  to query |
+------------------+----------+--------------------------------------------------+




ExpandSwapLeg

.............
ExpandSwapLeg
.............


Expand the contents of a leg of a swap.



+-----------+----------+--------------------------+
| Parameter | Required | Description              |
+===========+==========+==========================+
| swapLeg   | Yes      | A leg of a swap to query |
+-----------+----------+--------------------------+




ExpandSwapSecurity

..................
ExpandSwapSecurity
..................


Expand the contents of a swap security.



+--------------+----------+--------------------------+
| Parameter    | Required | Description              |
+==============+==========+==========================+
| swapSecurity | Yes      | A swap security to query |
+--------------+----------+--------------------------+




ExpandSwaptionSecurity

......................
ExpandSwaptionSecurity
......................


Expand the contents of a swaption security.



+------------------+----------+------------------------------+
| Parameter        | Required | Description                  |
+==================+==========+==============================+
| swaptionSecurity | Yes      | A swaption security to query |
+------------------+----------+------------------------------+




FetchSecurity

.............
FetchSecurity
.............


Fetches security objects from the security source.



+-------------+----------+-----------------------------------------------------------------------------------------------+
| Parameter   | Required | Description                                                                                   |
+=============+==========+===============================================================================================+
| identifiers |          | The identifier (or identifier bundle) of the security to fetch, omit if uniqueId is specified |
+-------------+----------+-----------------------------------------------------------------------------------------------+
| uniqueId    |          | The unique identifier of the security to fetch, omit if identifiers is specified              |
+-------------+----------+-----------------------------------------------------------------------------------------------+




GetSecurityExternalIdBundle

...........................
GetSecurityExternalIdBundle
...........................


Returns the identifiers for various schemes from a security.



+-----------+----------+---------------------+
| Parameter | Required | Description         |
+===========+==========+=====================+
| security  | Yes      | A security to query |
+-----------+----------+---------------------+




GetSecurityName

...............
GetSecurityName
...............


Returns the display name from a security.



+-----------+----------+---------------------+
| Parameter | Required | Description         |
+===========+==========+=====================+
| security  | Yes      | A security to query |
+-----------+----------+---------------------+




GetSecurityNotionalNotionalId

.............................
GetSecurityNotionalNotionalId
.............................


Returns the unique identifier of the security from a notional value defined as a security .



+------------------+----------+--------------------------------------------------+
| Parameter        | Required | Description                                      |
+==================+==========+==================================================+
| securityNotional | Yes      | A notional value defined as a security  to query |
+------------------+----------+--------------------------------------------------+




GetSecurityType

...............
GetSecurityType
...............


Returns the asset class from a security.



+-----------+----------+---------------------+
| Parameter | Required | Description         |
+===========+==========+=====================+
| security  | Yes      | A security to query |
+-----------+----------+---------------------+




GetSwapLegBusinessDayConvention

...............................
GetSwapLegBusinessDayConvention
...............................


Returns the business day convention from a leg of a swap.



+-----------+----------+--------------------------+
| Parameter | Required | Description              |
+===========+==========+==========================+
| swapLeg   | Yes      | A leg of a swap to query |
+-----------+----------+--------------------------+




GetSwapLegDayCount

..................
GetSwapLegDayCount
..................


Returns the dayCount from a leg of a swap.



+-----------+----------+--------------------------+
| Parameter | Required | Description              |
+===========+==========+==========================+
| swapLeg   | Yes      | A leg of a swap to query |
+-----------+----------+--------------------------+




GetSwapLegEom

.............
GetSwapLegEom
.............


Returns the EOM flag from a leg of a swap.



+-----------+----------+--------------------------+
| Parameter | Required | Description              |
+===========+==========+==========================+
| swapLeg   | Yes      | A leg of a swap to query |
+-----------+----------+--------------------------+




GetSwapLegFrequency

...................
GetSwapLegFrequency
...................


Returns the payment frequency from a leg of a swap.



+-----------+----------+--------------------------+
| Parameter | Required | Description              |
+===========+==========+==========================+
| swapLeg   | Yes      | A leg of a swap to query |
+-----------+----------+--------------------------+




GetSwapLegNotional

..................
GetSwapLegNotional
..................


Returns the notional from a leg of a swap.



+-----------+----------+--------------------------+
| Parameter | Required | Description              |
+===========+==========+==========================+
| swapLeg   | Yes      | A leg of a swap to query |
+-----------+----------+--------------------------+




GetSwapLegRegionId

..................
GetSwapLegRegionId
..................


Returns the region identifier from a leg of a swap.



+-----------+----------+--------------------------+
| Parameter | Required | Description              |
+===========+==========+==========================+
| swapLeg   | Yes      | A leg of a swap to query |
+-----------+----------+--------------------------+




GetSwapSecurityCounterparty

...........................
GetSwapSecurityCounterparty
...........................


Returns the counterparty from a swap security.



+--------------+----------+--------------------------+
| Parameter    | Required | Description              |
+==============+==========+==========================+
| swapSecurity | Yes      | A swap security to query |
+--------------+----------+--------------------------+




GetSwapSecurityEffectiveDate

............................
GetSwapSecurityEffectiveDate
............................


Returns the 'effective' or 'value' date from a swap security.



+--------------+----------+--------------------------+
| Parameter    | Required | Description              |
+==============+==========+==========================+
| swapSecurity | Yes      | A swap security to query |
+--------------+----------+--------------------------+




GetSwapSecurityMaturityDate

...........................
GetSwapSecurityMaturityDate
...........................


Returns the 'maturity' or 'termination' date from a swap security.



+--------------+----------+--------------------------+
| Parameter    | Required | Description              |
+==============+==========+==========================+
| swapSecurity | Yes      | A swap security to query |
+--------------+----------+--------------------------+




GetSwapSecurityPayLeg

.....................
GetSwapSecurityPayLeg
.....................


Returns the pay leg from a swap security.



+--------------+----------+--------------------------+
| Parameter    | Required | Description              |
+==============+==========+==========================+
| swapSecurity | Yes      | A swap security to query |
+--------------+----------+--------------------------+




GetSwapSecurityReceiveLeg

.........................
GetSwapSecurityReceiveLeg
.........................


Returns the receive leg from a swap security.



+--------------+----------+--------------------------+
| Parameter    | Required | Description              |
+==============+==========+==========================+
| swapSecurity | Yes      | A swap security to query |
+--------------+----------+--------------------------+




GetSwapSecurityTradeDate

........................
GetSwapSecurityTradeDate
........................


Returns the trade date from a swap security.



+--------------+----------+--------------------------+
| Parameter    | Required | Description              |
+==============+==========+==========================+
| swapSecurity | Yes      | A swap security to query |
+--------------+----------+--------------------------+




GetSwaptionSecurityCashSettled

..............................
GetSwaptionSecurityCashSettled
..............................


Returns the cash settlement flag from a swaption security.



+------------------+----------+------------------------------+
| Parameter        | Required | Description                  |
+==================+==========+==============================+
| swaptionSecurity | Yes      | A swaption security to query |
+------------------+----------+------------------------------+




GetSwaptionSecurityCurrency

...........................
GetSwaptionSecurityCurrency
...........................


Returns the currency from a swaption security.



+------------------+----------+------------------------------+
| Parameter        | Required | Description                  |
+==================+==========+==============================+
| swaptionSecurity | Yes      | A swaption security to query |
+------------------+----------+------------------------------+




GetSwaptionSecurityExpiry

.........................
GetSwaptionSecurityExpiry
.........................


Returns the expiry from a swaption security.



+------------------+----------+------------------------------+
| Parameter        | Required | Description                  |
+==================+==========+==============================+
| swaptionSecurity | Yes      | A swaption security to query |
+------------------+----------+------------------------------+




GetSwaptionSecurityLong

.......................
GetSwaptionSecurityLong
.......................


Returns the long flag from a swaption security.



+------------------+----------+------------------------------+
| Parameter        | Required | Description                  |
+==================+==========+==============================+
| swaptionSecurity | Yes      | A swaption security to query |
+------------------+----------+------------------------------+




GetSwaptionSecurityPayer

........................
GetSwaptionSecurityPayer
........................


Returns the payer flag from a swaption security.



+------------------+----------+------------------------------+
| Parameter        | Required | Description                  |
+==================+==========+==============================+
| swaptionSecurity | Yes      | A swaption security to query |
+------------------+----------+------------------------------+




GetSwaptionSecurityUnderlyingId

...............................
GetSwaptionSecurityUnderlyingId
...............................


Returns the identifier of the underlying swap from a swaption security.



+------------------+----------+------------------------------+
| Parameter        | Required | Description                  |
+==================+==========+==============================+
| swaptionSecurity | Yes      | A swaption security to query |
+------------------+----------+------------------------------+




SecurityNotional

................
SecurityNotional
................


Defines a notional value defined as a security .



+------------+----------+---------------------------------------+
| Parameter  | Required | Description                           |
+============+==========+=======================================+
| notionalId | Yes      | The unique identifier of the security |
+------------+----------+---------------------------------------+




SetSecurityExternalIdBundle

...........................
SetSecurityExternalIdBundle
...........................


Updates the identifiers for various schemes of a security. The original object is unchanged - a new object is returned with the updated value.



+------------------+----------+-------------------------------------+
| Parameter        | Required | Description                         |
+==================+==========+=====================================+
| security         | Yes      | A security to update                |
+------------------+----------+-------------------------------------+
| externalIdBundle |          | The identifiers for various schemes |
+------------------+----------+-------------------------------------+




SetSecurityName

...............
SetSecurityName
...............


Updates the display name of a security. The original object is unchanged - a new object is returned with the updated value.



+-----------+----------+----------------------+
| Parameter | Required | Description          |
+===========+==========+======================+
| security  | Yes      | A security to update |
+-----------+----------+----------------------+
| name      |          | The display name     |
+-----------+----------+----------------------+




SetSecurityNotionalNotionalId

.............................
SetSecurityNotionalNotionalId
.............................


Updates the unique identifier of the security of a notional value defined as a security . The original object is unchanged - a new object is returned with the updated value.



+------------------+----------+---------------------------------------------------+
| Parameter        | Required | Description                                       |
+==================+==========+===================================================+
| securityNotional | Yes      | A notional value defined as a security  to update |
+------------------+----------+---------------------------------------------------+
| notionalId       |          | The unique identifier of the security             |
+------------------+----------+---------------------------------------------------+




SetSwapLegBusinessDayConvention

...............................
SetSwapLegBusinessDayConvention
...............................


Updates the business day convention of a leg of a swap. The original object is unchanged - a new object is returned with the updated value.



+-----------------------+----------+-----------------------------+
| Parameter             | Required | Description                 |
+=======================+==========+=============================+
| swapLeg               | Yes      | A leg of a swap to update   |
+-----------------------+----------+-----------------------------+
| businessDayConvention |          | The business day convention |
+-----------------------+----------+-----------------------------+




SetSwapLegDayCount

..................
SetSwapLegDayCount
..................


Updates the dayCount of a leg of a swap. The original object is unchanged - a new object is returned with the updated value.



+-----------+----------+---------------------------+
| Parameter | Required | Description               |
+===========+==========+===========================+
| swapLeg   | Yes      | A leg of a swap to update |
+-----------+----------+---------------------------+
| dayCount  |          | The dayCount              |
+-----------+----------+---------------------------+




SetSwapLegEom

.............
SetSwapLegEom
.............


Updates the EOM flag of a leg of a swap. The original object is unchanged - a new object is returned with the updated value.



+-----------+----------+---------------------------+
| Parameter | Required | Description               |
+===========+==========+===========================+
| swapLeg   | Yes      | A leg of a swap to update |
+-----------+----------+---------------------------+
| eom       | Yes      | The EOM flag              |
+-----------+----------+---------------------------+




SetSwapLegFrequency

...................
SetSwapLegFrequency
...................


Updates the payment frequency of a leg of a swap. The original object is unchanged - a new object is returned with the updated value.



+-----------+----------+---------------------------+
| Parameter | Required | Description               |
+===========+==========+===========================+
| swapLeg   | Yes      | A leg of a swap to update |
+-----------+----------+---------------------------+
| frequency |          | The payment frequency     |
+-----------+----------+---------------------------+




SetSwapLegNotional

..................
SetSwapLegNotional
..................


Updates the notional of a leg of a swap. The original object is unchanged - a new object is returned with the updated value.



+-----------+----------+---------------------------+
| Parameter | Required | Description               |
+===========+==========+===========================+
| swapLeg   | Yes      | A leg of a swap to update |
+-----------+----------+---------------------------+
| notional  |          | The notional              |
+-----------+----------+---------------------------+




SetSwapLegRegionId

..................
SetSwapLegRegionId
..................


Updates the region identifier of a leg of a swap. The original object is unchanged - a new object is returned with the updated value.



+-----------+----------+---------------------------+
| Parameter | Required | Description               |
+===========+==========+===========================+
| swapLeg   | Yes      | A leg of a swap to update |
+-----------+----------+---------------------------+
| regionId  |          | The region identifier     |
+-----------+----------+---------------------------+




SetSwapSecurityCounterparty

...........................
SetSwapSecurityCounterparty
...........................


Updates the counterparty of a swap security. The original object is unchanged - a new object is returned with the updated value.



+--------------+----------+---------------------------+
| Parameter    | Required | Description               |
+==============+==========+===========================+
| swapSecurity | Yes      | A swap security to update |
+--------------+----------+---------------------------+
| counterparty |          | The counterparty          |
+--------------+----------+---------------------------+




SetSwapSecurityEffectiveDate

............................
SetSwapSecurityEffectiveDate
............................


Updates the 'effective' or 'value' date of a swap security. The original object is unchanged - a new object is returned with the updated value.



+---------------+----------+---------------------------------+
| Parameter     | Required | Description                     |
+===============+==========+=================================+
| swapSecurity  | Yes      | A swap security to update       |
+---------------+----------+---------------------------------+
| effectiveDate |          | The 'effective' or 'value' date |
+---------------+----------+---------------------------------+




SetSwapSecurityMaturityDate

...........................
SetSwapSecurityMaturityDate
...........................


Updates the 'maturity' or 'termination' date of a swap security. The original object is unchanged - a new object is returned with the updated value.



+--------------+----------+--------------------------------------+
| Parameter    | Required | Description                          |
+==============+==========+======================================+
| swapSecurity | Yes      | A swap security to update            |
+--------------+----------+--------------------------------------+
| maturityDate |          | The 'maturity' or 'termination' date |
+--------------+----------+--------------------------------------+




SetSwapSecurityPayLeg

.....................
SetSwapSecurityPayLeg
.....................


Updates the pay leg of a swap security. The original object is unchanged - a new object is returned with the updated value.



+--------------+----------+---------------------------+
| Parameter    | Required | Description               |
+==============+==========+===========================+
| swapSecurity | Yes      | A swap security to update |
+--------------+----------+---------------------------+
| payLeg       |          | The pay leg               |
+--------------+----------+---------------------------+




SetSwapSecurityReceiveLeg

.........................
SetSwapSecurityReceiveLeg
.........................


Updates the receive leg of a swap security. The original object is unchanged - a new object is returned with the updated value.



+--------------+----------+---------------------------+
| Parameter    | Required | Description               |
+==============+==========+===========================+
| swapSecurity | Yes      | A swap security to update |
+--------------+----------+---------------------------+
| receiveLeg   |          | The receive leg           |
+--------------+----------+---------------------------+




SetSwapSecurityTradeDate

........................
SetSwapSecurityTradeDate
........................


Updates the trade date of a swap security. The original object is unchanged - a new object is returned with the updated value.



+--------------+----------+---------------------------+
| Parameter    | Required | Description               |
+==============+==========+===========================+
| swapSecurity | Yes      | A swap security to update |
+--------------+----------+---------------------------+
| tradeDate    |          | The trade date            |
+--------------+----------+---------------------------+




SetSwaptionSecurityCashSettled

..............................
SetSwaptionSecurityCashSettled
..............................


Updates the cash settlement flag of a swaption security. The original object is unchanged - a new object is returned with the updated value.



+------------------+----------+-------------------------------+
| Parameter        | Required | Description                   |
+==================+==========+===============================+
| swaptionSecurity | Yes      | A swaption security to update |
+------------------+----------+-------------------------------+
| cashSettled      | Yes      | The cash settlement flag      |
+------------------+----------+-------------------------------+




SetSwaptionSecurityCurrency

...........................
SetSwaptionSecurityCurrency
...........................


Updates the currency of a swaption security. The original object is unchanged - a new object is returned with the updated value.



+------------------+----------+-------------------------------+
| Parameter        | Required | Description                   |
+==================+==========+===============================+
| swaptionSecurity | Yes      | A swaption security to update |
+------------------+----------+-------------------------------+
| currency         |          | The currency                  |
+------------------+----------+-------------------------------+




SetSwaptionSecurityExpiry

.........................
SetSwaptionSecurityExpiry
.........................


Updates the expiry of a swaption security. The original object is unchanged - a new object is returned with the updated value.



+------------------+----------+-------------------------------+
| Parameter        | Required | Description                   |
+==================+==========+===============================+
| swaptionSecurity | Yes      | A swaption security to update |
+------------------+----------+-------------------------------+
| expiry           |          | The expiry                    |
+------------------+----------+-------------------------------+




SetSwaptionSecurityPayer

........................
SetSwaptionSecurityPayer
........................


Updates the payer flag of a swaption security. The original object is unchanged - a new object is returned with the updated value.



+------------------+----------+-------------------------------+
| Parameter        | Required | Description                   |
+==================+==========+===============================+
| swaptionSecurity | Yes      | A swaption security to update |
+------------------+----------+-------------------------------+
| payer            | Yes      | The payer flag                |
+------------------+----------+-------------------------------+




SetSwaptionSecurityUnderlyingId

...............................
SetSwaptionSecurityUnderlyingId
...............................


Updates the identifier of the underlying swap of a swaption security. The original object is unchanged - a new object is returned with the updated value.



+------------------+----------+---------------------------------------+
| Parameter        | Required | Description                           |
+==================+==========+=======================================+
| swaptionSecurity | Yes      | A swaption security to update         |
+------------------+----------+---------------------------------------+
| underlyingId     |          | The identifier of the underlying swap |
+------------------+----------+---------------------------------------+




SimpleChooserPayoffStyle

........................
SimpleChooserPayoffStyle
........................


Returns an object representing a 'simple chooser' option payoff style.



+------------------+----------+----------------------------+
| Parameter        | Required | Description                |
+==================+==========+============================+
| chooseDate       | Yes      | The choose date            |
+------------------+----------+----------------------------+
| underlyingStrike | Yes      | The underlying strike      |
+------------------+----------+----------------------------+
| underlyingExpiry | Yes      | The underlying expiry date |
+------------------+----------+----------------------------+




StoreSecurity

.............
StoreSecurity
.............


Writes a security to the security master database.



+------------+----------+----------------------------------------------------------------------------------------+
| Parameter  | Required | Description                                                                            |
+============+==========+========================================================================================+
| security   | Yes      | The security object to write                                                           |
+------------+----------+----------------------------------------------------------------------------------------+
| identifier |          | The unique identifier of the security to update, omit to write a new security instance |
+------------+----------+----------------------------------------------------------------------------------------+
| master     |          | The master database to write to, omit for the session default                          |
+------------+----------+----------------------------------------------------------------------------------------+



This function does not return a value.


SupersharePayoffStyle

.....................
SupersharePayoffStyle
.....................


Returns an object representing a 'supershare' option payoff style.



+------------+----------+-----------------+
| Parameter  | Required | Description     |
+============+==========+=================+
| lowerBound | Yes      | The lower bound |
+------------+----------+-----------------+
| upperBound | Yes      | The upper bound |
+------------+----------+-----------------+




SwapSecurity

............
SwapSecurity
............


Defines a swap security.



+---------------+----------+-------------------------------------------+
| Parameter     | Required | Description                               |
+===============+==========+===========================================+
| name          | Yes      | The display name or label of the security |
+---------------+----------+-------------------------------------------+
| tradeDate     | Yes      | The trade date                            |
+---------------+----------+-------------------------------------------+
| effectiveDate | Yes      | The 'effective' or 'value' date           |
+---------------+----------+-------------------------------------------+
| maturityDate  | Yes      | The 'maturity' or 'termination' date      |
+---------------+----------+-------------------------------------------+
| counterparty  | Yes      | The counterparty                          |
+---------------+----------+-------------------------------------------+
| payLeg        | Yes      | The pay leg                               |
+---------------+----------+-------------------------------------------+
| receiveLeg    | Yes      | The receive leg                           |
+---------------+----------+-------------------------------------------+




SwaptionSecurity

................
SwaptionSecurity
................


Defines a swaption security.



+--------------+----------+-------------------------------------------+
| Parameter    | Required | Description                               |
+==============+==========+===========================================+
| name         | Yes      | The display name or label of the security |
+--------------+----------+-------------------------------------------+
| payer        | Yes      | The payer flag                            |
+--------------+----------+-------------------------------------------+
| underlyingId | Yes      | The identifier of the underlying swap     |
+--------------+----------+-------------------------------------------+
| long         | Yes      | The long flag                             |
+--------------+----------+-------------------------------------------+
| expiry       | Yes      | The expiry                                |
+--------------+----------+-------------------------------------------+
| cashSettled  | Yes      | The cash settlement flag                  |
+--------------+----------+-------------------------------------------+
| currency     | Yes      | The currency                              |
+--------------+----------+-------------------------------------------+



