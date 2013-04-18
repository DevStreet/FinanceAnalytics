title: Excel Functions - C
shortcut: DOC:Excel Functions - C
---
CapFloorCMSSpreadSecurity

.........................
CapFloorCMSSpreadSecurity
.........................


Defines a Cap/Floor CMS Spread security. The new security is added to the Security Master and an identifier to it returned.



+--------------+-------------------------------------------+
| Parameter    | Description                               |
+==============+===========================================+
| name         | The display name or label of the security |
+--------------+-------------------------------------------+
| startDate    | The start date                            |
+--------------+-------------------------------------------+
| maturityDate | The maturity date                         |
+--------------+-------------------------------------------+
| notional     | The notional                              |
+--------------+-------------------------------------------+
| longId       | The identifier of the 'long' component    |
+--------------+-------------------------------------------+
| shortId      | The identifier of the 'short' component   |
+--------------+-------------------------------------------+
| strike       | The strike                                |
+--------------+-------------------------------------------+
| frequency    | The frequency                             |
+--------------+-------------------------------------------+
| currency     | The currency                              |
+--------------+-------------------------------------------+
| dayCount     | The day count                             |
+--------------+-------------------------------------------+
| payer        | The payer flag                            |
+--------------+-------------------------------------------+
| cap          | The cap flag                              |
+--------------+-------------------------------------------+



CapFloorCMSSpreadSecurityCap

............................
CapFloorCMSSpreadSecurityCap
............................


Returns the cap flag from a Cap/Floor CMS Spread security.



+---------------------------+------------------------------------------+
| Parameter                 | Description                              |
+===========================+==========================================+
| capFloorCMSSpreadSecurity | A Cap/Floor CMS Spread security to query |
+---------------------------+------------------------------------------+



CapFloorCMSSpreadSecurityCurrency

.................................
CapFloorCMSSpreadSecurityCurrency
.................................


Returns the currency from a Cap/Floor CMS Spread security.



+---------------------------+------------------------------------------+
| Parameter                 | Description                              |
+===========================+==========================================+
| capFloorCMSSpreadSecurity | A Cap/Floor CMS Spread security to query |
+---------------------------+------------------------------------------+



CapFloorCMSSpreadSecurityDayCount

.................................
CapFloorCMSSpreadSecurityDayCount
.................................


Returns the day count from a Cap/Floor CMS Spread security.



+---------------------------+------------------------------------------+
| Parameter                 | Description                              |
+===========================+==========================================+
| capFloorCMSSpreadSecurity | A Cap/Floor CMS Spread security to query |
+---------------------------+------------------------------------------+



CapFloorCMSSpreadSecurityFrequency

..................................
CapFloorCMSSpreadSecurityFrequency
..................................


Returns the frequency from a Cap/Floor CMS Spread security.



+---------------------------+------------------------------------------+
| Parameter                 | Description                              |
+===========================+==========================================+
| capFloorCMSSpreadSecurity | A Cap/Floor CMS Spread security to query |
+---------------------------+------------------------------------------+



CapFloorCMSSpreadSecurityLongId

...............................
CapFloorCMSSpreadSecurityLongId
...............................


Returns the identifier of the 'long' component from a Cap/Floor CMS Spread security.



+---------------------------+------------------------------------------+
| Parameter                 | Description                              |
+===========================+==========================================+
| capFloorCMSSpreadSecurity | A Cap/Floor CMS Spread security to query |
+---------------------------+------------------------------------------+



CapFloorCMSSpreadSecurityMaturityDate

.....................................
CapFloorCMSSpreadSecurityMaturityDate
.....................................


Returns the maturity date from a Cap/Floor CMS Spread security.



+---------------------------+------------------------------------------+
| Parameter                 | Description                              |
+===========================+==========================================+
| capFloorCMSSpreadSecurity | A Cap/Floor CMS Spread security to query |
+---------------------------+------------------------------------------+



CapFloorCMSSpreadSecurityNotional

.................................
CapFloorCMSSpreadSecurityNotional
.................................


Returns the notional from a Cap/Floor CMS Spread security.



+---------------------------+------------------------------------------+
| Parameter                 | Description                              |
+===========================+==========================================+
| capFloorCMSSpreadSecurity | A Cap/Floor CMS Spread security to query |
+---------------------------+------------------------------------------+



CapFloorCMSSpreadSecurityObject

...............................
CapFloorCMSSpreadSecurityObject
...............................


Defines a Cap/Floor CMS Spread security.



+--------------+-------------------------------------------+
| Parameter    | Description                               |
+==============+===========================================+
| name         | The display name or label of the security |
+--------------+-------------------------------------------+
| startDate    | The start date                            |
+--------------+-------------------------------------------+
| maturityDate | The maturity date                         |
+--------------+-------------------------------------------+
| notional     | The notional                              |
+--------------+-------------------------------------------+
| longId       | The identifier of the 'long' component    |
+--------------+-------------------------------------------+
| shortId      | The identifier of the 'short' component   |
+--------------+-------------------------------------------+
| strike       | The strike                                |
+--------------+-------------------------------------------+
| frequency    | The frequency                             |
+--------------+-------------------------------------------+
| currency     | The currency                              |
+--------------+-------------------------------------------+
| dayCount     | The day count                             |
+--------------+-------------------------------------------+
| payer        | The payer flag                            |
+--------------+-------------------------------------------+
| cap          | The cap flag                              |
+--------------+-------------------------------------------+



CapFloorCMSSpreadSecurityPayer

..............................
CapFloorCMSSpreadSecurityPayer
..............................


Returns the payer flag from a Cap/Floor CMS Spread security.



+---------------------------+------------------------------------------+
| Parameter                 | Description                              |
+===========================+==========================================+
| capFloorCMSSpreadSecurity | A Cap/Floor CMS Spread security to query |
+---------------------------+------------------------------------------+



CapFloorCMSSpreadSecuritySetCap

...............................
CapFloorCMSSpreadSecuritySetCap
...............................


Updates the cap flag of a Cap/Floor CMS Spread security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+-------------------------------------------+
| Parameter                 | Description                               |
+===========================+===========================================+
| capFloorCMSSpreadSecurity | A Cap/Floor CMS Spread security to update |
+---------------------------+-------------------------------------------+
| cap                       | The cap flag                              |
+---------------------------+-------------------------------------------+



CapFloorCMSSpreadSecuritySetCurrency

....................................
CapFloorCMSSpreadSecuritySetCurrency
....................................


Updates the currency of a Cap/Floor CMS Spread security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+-------------------------------------------+
| Parameter                 | Description                               |
+===========================+===========================================+
| capFloorCMSSpreadSecurity | A Cap/Floor CMS Spread security to update |
+---------------------------+-------------------------------------------+
| currency                  | The currency                              |
+---------------------------+-------------------------------------------+



CapFloorCMSSpreadSecuritySetDayCount

....................................
CapFloorCMSSpreadSecuritySetDayCount
....................................


Updates the day count of a Cap/Floor CMS Spread security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+-------------------------------------------+
| Parameter                 | Description                               |
+===========================+===========================================+
| capFloorCMSSpreadSecurity | A Cap/Floor CMS Spread security to update |
+---------------------------+-------------------------------------------+
| dayCount                  | The day count                             |
+---------------------------+-------------------------------------------+



CapFloorCMSSpreadSecuritySetFrequency

.....................................
CapFloorCMSSpreadSecuritySetFrequency
.....................................


Updates the frequency of a Cap/Floor CMS Spread security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+-------------------------------------------+
| Parameter                 | Description                               |
+===========================+===========================================+
| capFloorCMSSpreadSecurity | A Cap/Floor CMS Spread security to update |
+---------------------------+-------------------------------------------+
| frequency                 | The frequency                             |
+---------------------------+-------------------------------------------+



CapFloorCMSSpreadSecuritySetLongId

..................................
CapFloorCMSSpreadSecuritySetLongId
..................................


Updates the identifier of the 'long' component of a Cap/Floor CMS Spread security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+-------------------------------------------+
| Parameter                 | Description                               |
+===========================+===========================================+
| capFloorCMSSpreadSecurity | A Cap/Floor CMS Spread security to update |
+---------------------------+-------------------------------------------+
| longId                    | The identifier of the 'long' component    |
+---------------------------+-------------------------------------------+



CapFloorCMSSpreadSecuritySetMaturityDate

........................................
CapFloorCMSSpreadSecuritySetMaturityDate
........................................


Updates the maturity date of a Cap/Floor CMS Spread security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+-------------------------------------------+
| Parameter                 | Description                               |
+===========================+===========================================+
| capFloorCMSSpreadSecurity | A Cap/Floor CMS Spread security to update |
+---------------------------+-------------------------------------------+
| maturityDate              | The maturity date                         |
+---------------------------+-------------------------------------------+



CapFloorCMSSpreadSecuritySetNotional

....................................
CapFloorCMSSpreadSecuritySetNotional
....................................


Updates the notional of a Cap/Floor CMS Spread security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+-------------------------------------------+
| Parameter                 | Description                               |
+===========================+===========================================+
| capFloorCMSSpreadSecurity | A Cap/Floor CMS Spread security to update |
+---------------------------+-------------------------------------------+
| notional                  | The notional                              |
+---------------------------+-------------------------------------------+



CapFloorCMSSpreadSecuritySetPayer

.................................
CapFloorCMSSpreadSecuritySetPayer
.................................


Updates the payer flag of a Cap/Floor CMS Spread security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+-------------------------------------------+
| Parameter                 | Description                               |
+===========================+===========================================+
| capFloorCMSSpreadSecurity | A Cap/Floor CMS Spread security to update |
+---------------------------+-------------------------------------------+
| payer                     | The payer flag                            |
+---------------------------+-------------------------------------------+



CapFloorCMSSpreadSecuritySetShortId

...................................
CapFloorCMSSpreadSecuritySetShortId
...................................


Updates the identifier of the 'short' component of a Cap/Floor CMS Spread security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+-------------------------------------------+
| Parameter                 | Description                               |
+===========================+===========================================+
| capFloorCMSSpreadSecurity | A Cap/Floor CMS Spread security to update |
+---------------------------+-------------------------------------------+
| shortId                   | The identifier of the 'short' component   |
+---------------------------+-------------------------------------------+



CapFloorCMSSpreadSecuritySetStartDate

.....................................
CapFloorCMSSpreadSecuritySetStartDate
.....................................


Updates the start date of a Cap/Floor CMS Spread security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+-------------------------------------------+
| Parameter                 | Description                               |
+===========================+===========================================+
| capFloorCMSSpreadSecurity | A Cap/Floor CMS Spread security to update |
+---------------------------+-------------------------------------------+
| startDate                 | The start date                            |
+---------------------------+-------------------------------------------+



CapFloorCMSSpreadSecuritySetStrike

..................................
CapFloorCMSSpreadSecuritySetStrike
..................................


Updates the strike of a Cap/Floor CMS Spread security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+-------------------------------------------+
| Parameter                 | Description                               |
+===========================+===========================================+
| capFloorCMSSpreadSecurity | A Cap/Floor CMS Spread security to update |
+---------------------------+-------------------------------------------+
| strike                    | The strike                                |
+---------------------------+-------------------------------------------+



CapFloorCMSSpreadSecurityShortId

................................
CapFloorCMSSpreadSecurityShortId
................................


Returns the identifier of the 'short' component from a Cap/Floor CMS Spread security.



+---------------------------+------------------------------------------+
| Parameter                 | Description                              |
+===========================+==========================================+
| capFloorCMSSpreadSecurity | A Cap/Floor CMS Spread security to query |
+---------------------------+------------------------------------------+



CapFloorCMSSpreadSecurityStartDate

..................................
CapFloorCMSSpreadSecurityStartDate
..................................


Returns the start date from a Cap/Floor CMS Spread security.



+---------------------------+------------------------------------------+
| Parameter                 | Description                              |
+===========================+==========================================+
| capFloorCMSSpreadSecurity | A Cap/Floor CMS Spread security to query |
+---------------------------+------------------------------------------+



CapFloorCMSSpreadSecurityStrike

...............................
CapFloorCMSSpreadSecurityStrike
...............................


Returns the strike from a Cap/Floor CMS Spread security.



+---------------------------+------------------------------------------+
| Parameter                 | Description                              |
+===========================+==========================================+
| capFloorCMSSpreadSecurity | A Cap/Floor CMS Spread security to query |
+---------------------------+------------------------------------------+



CapFloorSecurity

................
CapFloorSecurity
................


Defines a Cap/Floor security. The new security is added to the Security Master and an identifier to it returned.



+--------------+-------------------------------------------+
| Parameter    | Description                               |
+==============+===========================================+
| name         | The display name or label of the security |
+--------------+-------------------------------------------+
| startDate    | The start date                            |
+--------------+-------------------------------------------+
| maturityDate | The maturity date                         |
+--------------+-------------------------------------------+
| notional     | The notional                              |
+--------------+-------------------------------------------+
| underlyingId | The identifier of the underlying security |
+--------------+-------------------------------------------+
| strike       | The strike                                |
+--------------+-------------------------------------------+
| frequency    | The frequency                             |
+--------------+-------------------------------------------+
| currency     | The currency                              |
+--------------+-------------------------------------------+
| dayCount     | The day count convention                  |
+--------------+-------------------------------------------+
| payer        | The payer flag                            |
+--------------+-------------------------------------------+
| cap          | The cap flag                              |
+--------------+-------------------------------------------+
| ibor         | The ibor flag                             |
+--------------+-------------------------------------------+



CapFloorSecurityCap

...................
CapFloorSecurityCap
...................


Returns the cap flag from a Cap/Floor security.



+------------------+-------------------------------+
| Parameter        | Description                   |
+==================+===============================+
| capFloorSecurity | A Cap/Floor security to query |
+------------------+-------------------------------+



CapFloorSecurityCurrency

........................
CapFloorSecurityCurrency
........................


Returns the currency from a Cap/Floor security.



+------------------+-------------------------------+
| Parameter        | Description                   |
+==================+===============================+
| capFloorSecurity | A Cap/Floor security to query |
+------------------+-------------------------------+



CapFloorSecurityDayCount

........................
CapFloorSecurityDayCount
........................


Returns the day count convention from a Cap/Floor security.



+------------------+-------------------------------+
| Parameter        | Description                   |
+==================+===============================+
| capFloorSecurity | A Cap/Floor security to query |
+------------------+-------------------------------+



CapFloorSecurityFrequency

.........................
CapFloorSecurityFrequency
.........................


Returns the frequency from a Cap/Floor security.



+------------------+-------------------------------+
| Parameter        | Description                   |
+==================+===============================+
| capFloorSecurity | A Cap/Floor security to query |
+------------------+-------------------------------+



CapFloorSecurityIbor

....................
CapFloorSecurityIbor
....................


Returns the ibor flag from a Cap/Floor security.



+------------------+-------------------------------+
| Parameter        | Description                   |
+==================+===============================+
| capFloorSecurity | A Cap/Floor security to query |
+------------------+-------------------------------+



CapFloorSecurityMaturityDate

............................
CapFloorSecurityMaturityDate
............................


Returns the maturity date from a Cap/Floor security.



+------------------+-------------------------------+
| Parameter        | Description                   |
+==================+===============================+
| capFloorSecurity | A Cap/Floor security to query |
+------------------+-------------------------------+



CapFloorSecurityNotional

........................
CapFloorSecurityNotional
........................


Returns the notional from a Cap/Floor security.



+------------------+-------------------------------+
| Parameter        | Description                   |
+==================+===============================+
| capFloorSecurity | A Cap/Floor security to query |
+------------------+-------------------------------+



CapFloorSecurityObject

......................
CapFloorSecurityObject
......................


Defines a Cap/Floor security.



+--------------+-------------------------------------------+
| Parameter    | Description                               |
+==============+===========================================+
| name         | The display name or label of the security |
+--------------+-------------------------------------------+
| startDate    | The start date                            |
+--------------+-------------------------------------------+
| maturityDate | The maturity date                         |
+--------------+-------------------------------------------+
| notional     | The notional                              |
+--------------+-------------------------------------------+
| underlyingId | The identifier of the underlying security |
+--------------+-------------------------------------------+
| strike       | The strike                                |
+--------------+-------------------------------------------+
| frequency    | The frequency                             |
+--------------+-------------------------------------------+
| currency     | The currency                              |
+--------------+-------------------------------------------+
| dayCount     | The day count convention                  |
+--------------+-------------------------------------------+
| payer        | The payer flag                            |
+--------------+-------------------------------------------+
| cap          | The cap flag                              |
+--------------+-------------------------------------------+
| ibor         | The ibor flag                             |
+--------------+-------------------------------------------+



CapFloorSecurityPayer

.....................
CapFloorSecurityPayer
.....................


Returns the payer flag from a Cap/Floor security.



+------------------+-------------------------------+
| Parameter        | Description                   |
+==================+===============================+
| capFloorSecurity | A Cap/Floor security to query |
+------------------+-------------------------------+



CapFloorSecuritySetCap

......................
CapFloorSecuritySetCap
......................


Updates the cap flag of a Cap/Floor security. The original object is unchanged - a new object is returned with the updated value.



+------------------+--------------------------------+
| Parameter        | Description                    |
+==================+================================+
| capFloorSecurity | A Cap/Floor security to update |
+------------------+--------------------------------+
| cap              | The cap flag                   |
+------------------+--------------------------------+



CapFloorSecuritySetCurrency

...........................
CapFloorSecuritySetCurrency
...........................


Updates the currency of a Cap/Floor security. The original object is unchanged - a new object is returned with the updated value.



+------------------+--------------------------------+
| Parameter        | Description                    |
+==================+================================+
| capFloorSecurity | A Cap/Floor security to update |
+------------------+--------------------------------+
| currency         | The currency                   |
+------------------+--------------------------------+



CapFloorSecuritySetDayCount

...........................
CapFloorSecuritySetDayCount
...........................


Updates the day count convention of a Cap/Floor security. The original object is unchanged - a new object is returned with the updated value.



+------------------+--------------------------------+
| Parameter        | Description                    |
+==================+================================+
| capFloorSecurity | A Cap/Floor security to update |
+------------------+--------------------------------+
| dayCount         | The day count convention       |
+------------------+--------------------------------+



CapFloorSecuritySetFrequency

............................
CapFloorSecuritySetFrequency
............................


Updates the frequency of a Cap/Floor security. The original object is unchanged - a new object is returned with the updated value.



+------------------+--------------------------------+
| Parameter        | Description                    |
+==================+================================+
| capFloorSecurity | A Cap/Floor security to update |
+------------------+--------------------------------+
| frequency        | The frequency                  |
+------------------+--------------------------------+



CapFloorSecuritySetIbor

.......................
CapFloorSecuritySetIbor
.......................


Updates the ibor flag of a Cap/Floor security. The original object is unchanged - a new object is returned with the updated value.



+------------------+--------------------------------+
| Parameter        | Description                    |
+==================+================================+
| capFloorSecurity | A Cap/Floor security to update |
+------------------+--------------------------------+
| ibor             | The ibor flag                  |
+------------------+--------------------------------+



CapFloorSecuritySetMaturityDate

...............................
CapFloorSecuritySetMaturityDate
...............................


Updates the maturity date of a Cap/Floor security. The original object is unchanged - a new object is returned with the updated value.



+------------------+--------------------------------+
| Parameter        | Description                    |
+==================+================================+
| capFloorSecurity | A Cap/Floor security to update |
+------------------+--------------------------------+
| maturityDate     | The maturity date              |
+------------------+--------------------------------+



CapFloorSecuritySetNotional

...........................
CapFloorSecuritySetNotional
...........................


Updates the notional of a Cap/Floor security. The original object is unchanged - a new object is returned with the updated value.



+------------------+--------------------------------+
| Parameter        | Description                    |
+==================+================================+
| capFloorSecurity | A Cap/Floor security to update |
+------------------+--------------------------------+
| notional         | The notional                   |
+------------------+--------------------------------+



CapFloorSecuritySetPayer

........................
CapFloorSecuritySetPayer
........................


Updates the payer flag of a Cap/Floor security. The original object is unchanged - a new object is returned with the updated value.



+------------------+--------------------------------+
| Parameter        | Description                    |
+==================+================================+
| capFloorSecurity | A Cap/Floor security to update |
+------------------+--------------------------------+
| payer            | The payer flag                 |
+------------------+--------------------------------+



CapFloorSecuritySetStartDate

............................
CapFloorSecuritySetStartDate
............................


Updates the start date of a Cap/Floor security. The original object is unchanged - a new object is returned with the updated value.



+------------------+--------------------------------+
| Parameter        | Description                    |
+==================+================================+
| capFloorSecurity | A Cap/Floor security to update |
+------------------+--------------------------------+
| startDate        | The start date                 |
+------------------+--------------------------------+



CapFloorSecuritySetStrike

.........................
CapFloorSecuritySetStrike
.........................


Updates the strike of a Cap/Floor security. The original object is unchanged - a new object is returned with the updated value.



+------------------+--------------------------------+
| Parameter        | Description                    |
+==================+================================+
| capFloorSecurity | A Cap/Floor security to update |
+------------------+--------------------------------+
| strike           | The strike                     |
+------------------+--------------------------------+



CapFloorSecuritySetUnderlyingId

...............................
CapFloorSecuritySetUnderlyingId
...............................


Updates the identifier of the underlying security of a Cap/Floor security. The original object is unchanged - a new object is returned with the updated value.



+------------------+-------------------------------------------+
| Parameter        | Description                               |
+==================+===========================================+
| capFloorSecurity | A Cap/Floor security to update            |
+------------------+-------------------------------------------+
| underlyingId     | The identifier of the underlying security |
+------------------+-------------------------------------------+



CapFloorSecurityStartDate

.........................
CapFloorSecurityStartDate
.........................


Returns the start date from a Cap/Floor security.



+------------------+-------------------------------+
| Parameter        | Description                   |
+==================+===============================+
| capFloorSecurity | A Cap/Floor security to query |
+------------------+-------------------------------+



CapFloorSecurityStrike

......................
CapFloorSecurityStrike
......................


Returns the strike from a Cap/Floor security.



+------------------+-------------------------------+
| Parameter        | Description                   |
+==================+===============================+
| capFloorSecurity | A Cap/Floor security to query |
+------------------+-------------------------------+



CapFloorSecurityUnderlyingId

............................
CapFloorSecurityUnderlyingId
............................


Returns the identifier of the underlying security from a Cap/Floor security.



+------------------+-------------------------------+
| Parameter        | Description                   |
+==================+===============================+
| capFloorSecurity | A Cap/Floor security to query |
+------------------+-------------------------------+



CappedPoweredPayoffStyle

........................
CappedPoweredPayoffStyle
........................


Returns an object representing a 'capped powered' option payoff style.



+-----------+-------------+
| Parameter | Description |
+===========+=============+
| power     | The power   |
+-----------+-------------+
| cap       | The cap     |
+-----------+-------------+



CashOrNothingPayoffStyle

........................
CashOrNothingPayoffStyle
........................


Returns an object representing a 'cash or nothing' option payoff style.



+-----------+-------------+
| Parameter | Description |
+===========+=============+
| payment   | The payment |
+-----------+-------------+



CommodityFutureSecuritySetUnitName

..................................
CommodityFutureSecuritySetUnitName
..................................


Updates the name of units unitNumber is measured in of a commodity future. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+---------------------------------------------+
| Parameter               | Description                                 |
+=========================+=============================================+
| commodityFutureSecurity | A commodity future to update                |
+-------------------------+---------------------------------------------+
| unitName                | The name of units unitNumber is measured in |
+-------------------------+---------------------------------------------+



CommodityFutureSecuritySetUnitNumber

....................................
CommodityFutureSecuritySetUnitNumber
....................................


Updates the number of units of a commodity future. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+------------------------------+
| Parameter               | Description                  |
+=========================+==============================+
| commodityFutureSecurity | A commodity future to update |
+-------------------------+------------------------------+
| unitNumber              | The number of units          |
+-------------------------+------------------------------+



CommodityFutureSecurityUnitName

...............................
CommodityFutureSecurityUnitName
...............................


Returns the name of units unitNumber is measured in from a commodity future.



+-------------------------+-----------------------------+
| Parameter               | Description                 |
+=========================+=============================+
| commodityFutureSecurity | A commodity future to query |
+-------------------------+-----------------------------+



CommodityFutureSecurityUnitNumber

.................................
CommodityFutureSecurityUnitNumber
.................................


Returns the number of units from a commodity future.



+-------------------------+-----------------------------+
| Parameter               | Description                 |
+=========================+=============================+
| commodityFutureSecurity | A commodity future to query |
+-------------------------+-----------------------------+



ConfigureViewClient

...................
ConfigureViewClient
...................


Applies configuration to a ViewClient.



+----------------+------------------------------------------------+
| Parameter      | Description                                    |
+================+================================================+
| view_client_id | The identifier of the view client to configure |
+----------------+------------------------------------------------+
| config         | A range containing configuration items         |
+----------------+------------------------------------------------+



CurrencyPair

............
CurrencyPair
............


Returns the market convention currency pair for the two currencies.



+-------------------+-----------------------------------------------------+
| Parameter         | Description                                         |
+===================+=====================================================+
| currency1         | No description available                            |
+-------------------+-----------------------------------------------------+
| currency2         | No description available                            |
+-------------------+-----------------------------------------------------+
| currencyPairsName | Name of the set of market convention currency pairs |
+-------------------+-----------------------------------------------------+



