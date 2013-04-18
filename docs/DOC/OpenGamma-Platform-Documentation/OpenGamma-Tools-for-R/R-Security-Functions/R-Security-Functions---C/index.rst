title: R Security Functions - C
shortcut: DOC:R Security Functions - C
---
CapFloorCMSSpreadSecurity

.........................
CapFloorCMSSpreadSecurity
.........................


Defines a Cap/Floor CMS Spread security.



+--------------+----------+-------------------------------------------+
| Parameter    | Required | Description                               |
+==============+==========+===========================================+
| name         | Yes      | The display name or label of the security |
+--------------+----------+-------------------------------------------+
| startDate    | Yes      | The start date                            |
+--------------+----------+-------------------------------------------+
| maturityDate | Yes      | The maturity date                         |
+--------------+----------+-------------------------------------------+
| notional     | Yes      | The notional                              |
+--------------+----------+-------------------------------------------+
| longId       | Yes      | The identifier of the 'long' component    |
+--------------+----------+-------------------------------------------+
| shortId      | Yes      | The identifier of the 'short' component   |
+--------------+----------+-------------------------------------------+
| strike       | Yes      | The strike                                |
+--------------+----------+-------------------------------------------+
| frequency    | Yes      | The frequency                             |
+--------------+----------+-------------------------------------------+
| currency     | Yes      | The currency                              |
+--------------+----------+-------------------------------------------+
| dayCount     | Yes      | The day count                             |
+--------------+----------+-------------------------------------------+
| payer        | Yes      | The payer flag                            |
+--------------+----------+-------------------------------------------+
| cap          | Yes      | The cap flag                              |
+--------------+----------+-------------------------------------------+




CapFloorSecurity

................
CapFloorSecurity
................


Defines a Cap/Floor security.



+--------------+----------+-------------------------------------------+
| Parameter    | Required | Description                               |
+==============+==========+===========================================+
| name         | Yes      | The display name or label of the security |
+--------------+----------+-------------------------------------------+
| startDate    | Yes      | The start date                            |
+--------------+----------+-------------------------------------------+
| maturityDate | Yes      | The maturity date                         |
+--------------+----------+-------------------------------------------+
| notional     | Yes      | The notional                              |
+--------------+----------+-------------------------------------------+
| underlyingId | Yes      | The identifier of the underlying security |
+--------------+----------+-------------------------------------------+
| strike       | Yes      | The strike                                |
+--------------+----------+-------------------------------------------+
| frequency    | Yes      | The frequency                             |
+--------------+----------+-------------------------------------------+
| currency     | Yes      | The currency                              |
+--------------+----------+-------------------------------------------+
| dayCount     | Yes      | The day count convention                  |
+--------------+----------+-------------------------------------------+
| payer        | Yes      | The payer flag                            |
+--------------+----------+-------------------------------------------+
| cap          | Yes      | The cap flag                              |
+--------------+----------+-------------------------------------------+
| ibor         | Yes      | The ibor flag                             |
+--------------+----------+-------------------------------------------+




CappedPoweredPayoffStyle

........................
CappedPoweredPayoffStyle
........................


Returns an object representing a 'capped powered' option payoff style.



+-----------+----------+-------------+
| Parameter | Required | Description |
+===========+==========+=============+
| power     | Yes      | The power   |
+-----------+----------+-------------+
| cap       | Yes      | The cap     |
+-----------+----------+-------------+




CashOrNothingPayoffStyle

........................
CashOrNothingPayoffStyle
........................


Returns an object representing a 'cash or nothing' option payoff style.



+-----------+----------+-------------+
| Parameter | Required | Description |
+===========+==========+=============+
| payment   | Yes      | The payment |
+-----------+----------+-------------+




ExpandCapFloorCMSSpreadSecurity

...............................
ExpandCapFloorCMSSpreadSecurity
...............................


Expand the contents of a Cap/Floor CMS Spread security.



+---------------------------+----------+------------------------------------------+
| Parameter                 | Required | Description                              |
+===========================+==========+==========================================+
| capFloorCMSSpreadSecurity | Yes      | A Cap/Floor CMS Spread security to query |
+---------------------------+----------+------------------------------------------+




ExpandCapFloorSecurity

......................
ExpandCapFloorSecurity
......................


Expand the contents of a Cap/Floor security.



+------------------+----------+-------------------------------+
| Parameter        | Required | Description                   |
+==================+==========+===============================+
| capFloorSecurity | Yes      | A Cap/Floor security to query |
+------------------+----------+-------------------------------+




ExpandCommodityFutureSecurity

.............................
ExpandCommodityFutureSecurity
.............................


Expand the contents of a commodity future.



+-------------------------+----------+-----------------------------+
| Parameter               | Required | Description                 |
+=========================+==========+=============================+
| commodityFutureSecurity | Yes      | A commodity future to query |
+-------------------------+----------+-----------------------------+




GetCapFloorCMSSpreadSecurityCap

...............................
GetCapFloorCMSSpreadSecurityCap
...............................


Returns the cap flag from a Cap/Floor CMS Spread security.



+---------------------------+----------+------------------------------------------+
| Parameter                 | Required | Description                              |
+===========================+==========+==========================================+
| capFloorCMSSpreadSecurity | Yes      | A Cap/Floor CMS Spread security to query |
+---------------------------+----------+------------------------------------------+




GetCapFloorCMSSpreadSecurityCurrency

....................................
GetCapFloorCMSSpreadSecurityCurrency
....................................


Returns the currency from a Cap/Floor CMS Spread security.



+---------------------------+----------+------------------------------------------+
| Parameter                 | Required | Description                              |
+===========================+==========+==========================================+
| capFloorCMSSpreadSecurity | Yes      | A Cap/Floor CMS Spread security to query |
+---------------------------+----------+------------------------------------------+




GetCapFloorCMSSpreadSecurityDayCount

....................................
GetCapFloorCMSSpreadSecurityDayCount
....................................


Returns the day count from a Cap/Floor CMS Spread security.



+---------------------------+----------+------------------------------------------+
| Parameter                 | Required | Description                              |
+===========================+==========+==========================================+
| capFloorCMSSpreadSecurity | Yes      | A Cap/Floor CMS Spread security to query |
+---------------------------+----------+------------------------------------------+




GetCapFloorCMSSpreadSecurityFrequency

.....................................
GetCapFloorCMSSpreadSecurityFrequency
.....................................


Returns the frequency from a Cap/Floor CMS Spread security.



+---------------------------+----------+------------------------------------------+
| Parameter                 | Required | Description                              |
+===========================+==========+==========================================+
| capFloorCMSSpreadSecurity | Yes      | A Cap/Floor CMS Spread security to query |
+---------------------------+----------+------------------------------------------+




GetCapFloorCMSSpreadSecurityLongId

..................................
GetCapFloorCMSSpreadSecurityLongId
..................................


Returns the identifier of the 'long' component from a Cap/Floor CMS Spread security.



+---------------------------+----------+------------------------------------------+
| Parameter                 | Required | Description                              |
+===========================+==========+==========================================+
| capFloorCMSSpreadSecurity | Yes      | A Cap/Floor CMS Spread security to query |
+---------------------------+----------+------------------------------------------+




GetCapFloorCMSSpreadSecurityMaturityDate

........................................
GetCapFloorCMSSpreadSecurityMaturityDate
........................................


Returns the maturity date from a Cap/Floor CMS Spread security.



+---------------------------+----------+------------------------------------------+
| Parameter                 | Required | Description                              |
+===========================+==========+==========================================+
| capFloorCMSSpreadSecurity | Yes      | A Cap/Floor CMS Spread security to query |
+---------------------------+----------+------------------------------------------+




GetCapFloorCMSSpreadSecurityNotional

....................................
GetCapFloorCMSSpreadSecurityNotional
....................................


Returns the notional from a Cap/Floor CMS Spread security.



+---------------------------+----------+------------------------------------------+
| Parameter                 | Required | Description                              |
+===========================+==========+==========================================+
| capFloorCMSSpreadSecurity | Yes      | A Cap/Floor CMS Spread security to query |
+---------------------------+----------+------------------------------------------+




GetCapFloorCMSSpreadSecurityPayer

.................................
GetCapFloorCMSSpreadSecurityPayer
.................................


Returns the payer flag from a Cap/Floor CMS Spread security.



+---------------------------+----------+------------------------------------------+
| Parameter                 | Required | Description                              |
+===========================+==========+==========================================+
| capFloorCMSSpreadSecurity | Yes      | A Cap/Floor CMS Spread security to query |
+---------------------------+----------+------------------------------------------+




GetCapFloorCMSSpreadSecurityShortId

...................................
GetCapFloorCMSSpreadSecurityShortId
...................................


Returns the identifier of the 'short' component from a Cap/Floor CMS Spread security.



+---------------------------+----------+------------------------------------------+
| Parameter                 | Required | Description                              |
+===========================+==========+==========================================+
| capFloorCMSSpreadSecurity | Yes      | A Cap/Floor CMS Spread security to query |
+---------------------------+----------+------------------------------------------+




GetCapFloorCMSSpreadSecurityStartDate

.....................................
GetCapFloorCMSSpreadSecurityStartDate
.....................................


Returns the start date from a Cap/Floor CMS Spread security.



+---------------------------+----------+------------------------------------------+
| Parameter                 | Required | Description                              |
+===========================+==========+==========================================+
| capFloorCMSSpreadSecurity | Yes      | A Cap/Floor CMS Spread security to query |
+---------------------------+----------+------------------------------------------+




GetCapFloorCMSSpreadSecurityStrike

..................................
GetCapFloorCMSSpreadSecurityStrike
..................................


Returns the strike from a Cap/Floor CMS Spread security.



+---------------------------+----------+------------------------------------------+
| Parameter                 | Required | Description                              |
+===========================+==========+==========================================+
| capFloorCMSSpreadSecurity | Yes      | A Cap/Floor CMS Spread security to query |
+---------------------------+----------+------------------------------------------+




GetCapFloorSecurityCap

......................
GetCapFloorSecurityCap
......................


Returns the cap flag from a Cap/Floor security.



+------------------+----------+-------------------------------+
| Parameter        | Required | Description                   |
+==================+==========+===============================+
| capFloorSecurity | Yes      | A Cap/Floor security to query |
+------------------+----------+-------------------------------+




GetCapFloorSecurityCurrency

...........................
GetCapFloorSecurityCurrency
...........................


Returns the currency from a Cap/Floor security.



+------------------+----------+-------------------------------+
| Parameter        | Required | Description                   |
+==================+==========+===============================+
| capFloorSecurity | Yes      | A Cap/Floor security to query |
+------------------+----------+-------------------------------+




GetCapFloorSecurityDayCount

...........................
GetCapFloorSecurityDayCount
...........................


Returns the day count convention from a Cap/Floor security.



+------------------+----------+-------------------------------+
| Parameter        | Required | Description                   |
+==================+==========+===============================+
| capFloorSecurity | Yes      | A Cap/Floor security to query |
+------------------+----------+-------------------------------+




GetCapFloorSecurityFrequency

............................
GetCapFloorSecurityFrequency
............................


Returns the frequency from a Cap/Floor security.



+------------------+----------+-------------------------------+
| Parameter        | Required | Description                   |
+==================+==========+===============================+
| capFloorSecurity | Yes      | A Cap/Floor security to query |
+------------------+----------+-------------------------------+




GetCapFloorSecurityIbor

.......................
GetCapFloorSecurityIbor
.......................


Returns the ibor flag from a Cap/Floor security.



+------------------+----------+-------------------------------+
| Parameter        | Required | Description                   |
+==================+==========+===============================+
| capFloorSecurity | Yes      | A Cap/Floor security to query |
+------------------+----------+-------------------------------+




GetCapFloorSecurityMaturityDate

...............................
GetCapFloorSecurityMaturityDate
...............................


Returns the maturity date from a Cap/Floor security.



+------------------+----------+-------------------------------+
| Parameter        | Required | Description                   |
+==================+==========+===============================+
| capFloorSecurity | Yes      | A Cap/Floor security to query |
+------------------+----------+-------------------------------+




GetCapFloorSecurityNotional

...........................
GetCapFloorSecurityNotional
...........................


Returns the notional from a Cap/Floor security.



+------------------+----------+-------------------------------+
| Parameter        | Required | Description                   |
+==================+==========+===============================+
| capFloorSecurity | Yes      | A Cap/Floor security to query |
+------------------+----------+-------------------------------+




GetCapFloorSecurityPayer

........................
GetCapFloorSecurityPayer
........................


Returns the payer flag from a Cap/Floor security.



+------------------+----------+-------------------------------+
| Parameter        | Required | Description                   |
+==================+==========+===============================+
| capFloorSecurity | Yes      | A Cap/Floor security to query |
+------------------+----------+-------------------------------+




GetCapFloorSecurityStartDate

............................
GetCapFloorSecurityStartDate
............................


Returns the start date from a Cap/Floor security.



+------------------+----------+-------------------------------+
| Parameter        | Required | Description                   |
+==================+==========+===============================+
| capFloorSecurity | Yes      | A Cap/Floor security to query |
+------------------+----------+-------------------------------+




GetCapFloorSecurityStrike

.........................
GetCapFloorSecurityStrike
.........................


Returns the strike from a Cap/Floor security.



+------------------+----------+-------------------------------+
| Parameter        | Required | Description                   |
+==================+==========+===============================+
| capFloorSecurity | Yes      | A Cap/Floor security to query |
+------------------+----------+-------------------------------+




GetCapFloorSecurityUnderlyingId

...............................
GetCapFloorSecurityUnderlyingId
...............................


Returns the identifier of the underlying security from a Cap/Floor security.



+------------------+----------+-------------------------------+
| Parameter        | Required | Description                   |
+==================+==========+===============================+
| capFloorSecurity | Yes      | A Cap/Floor security to query |
+------------------+----------+-------------------------------+




GetCommodityFutureSecurityUnitName

..................................
GetCommodityFutureSecurityUnitName
..................................


Returns the name of units unitNumber is measured in from a commodity future.



+-------------------------+----------+-----------------------------+
| Parameter               | Required | Description                 |
+=========================+==========+=============================+
| commodityFutureSecurity | Yes      | A commodity future to query |
+-------------------------+----------+-----------------------------+




GetCommodityFutureSecurityUnitNumber

....................................
GetCommodityFutureSecurityUnitNumber
....................................


Returns the number of units from a commodity future.



+-------------------------+----------+-----------------------------+
| Parameter               | Required | Description                 |
+=========================+==========+=============================+
| commodityFutureSecurity | Yes      | A commodity future to query |
+-------------------------+----------+-----------------------------+




SetCapFloorCMSSpreadSecurityCap

...............................
SetCapFloorCMSSpreadSecurityCap
...............................


Updates the cap flag of a Cap/Floor CMS Spread security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+----------+-------------------------------------------+
| Parameter                 | Required | Description                               |
+===========================+==========+===========================================+
| capFloorCMSSpreadSecurity | Yes      | A Cap/Floor CMS Spread security to update |
+---------------------------+----------+-------------------------------------------+
| cap                       | Yes      | The cap flag                              |
+---------------------------+----------+-------------------------------------------+




SetCapFloorCMSSpreadSecurityCurrency

....................................
SetCapFloorCMSSpreadSecurityCurrency
....................................


Updates the currency of a Cap/Floor CMS Spread security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+----------+-------------------------------------------+
| Parameter                 | Required | Description                               |
+===========================+==========+===========================================+
| capFloorCMSSpreadSecurity | Yes      | A Cap/Floor CMS Spread security to update |
+---------------------------+----------+-------------------------------------------+
| currency                  |          | The currency                              |
+---------------------------+----------+-------------------------------------------+




SetCapFloorCMSSpreadSecurityDayCount

....................................
SetCapFloorCMSSpreadSecurityDayCount
....................................


Updates the day count of a Cap/Floor CMS Spread security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+----------+-------------------------------------------+
| Parameter                 | Required | Description                               |
+===========================+==========+===========================================+
| capFloorCMSSpreadSecurity | Yes      | A Cap/Floor CMS Spread security to update |
+---------------------------+----------+-------------------------------------------+
| dayCount                  |          | The day count                             |
+---------------------------+----------+-------------------------------------------+




SetCapFloorCMSSpreadSecurityFrequency

.....................................
SetCapFloorCMSSpreadSecurityFrequency
.....................................


Updates the frequency of a Cap/Floor CMS Spread security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+----------+-------------------------------------------+
| Parameter                 | Required | Description                               |
+===========================+==========+===========================================+
| capFloorCMSSpreadSecurity | Yes      | A Cap/Floor CMS Spread security to update |
+---------------------------+----------+-------------------------------------------+
| frequency                 |          | The frequency                             |
+---------------------------+----------+-------------------------------------------+




SetCapFloorCMSSpreadSecurityLongId

..................................
SetCapFloorCMSSpreadSecurityLongId
..................................


Updates the identifier of the 'long' component of a Cap/Floor CMS Spread security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+----------+-------------------------------------------+
| Parameter                 | Required | Description                               |
+===========================+==========+===========================================+
| capFloorCMSSpreadSecurity | Yes      | A Cap/Floor CMS Spread security to update |
+---------------------------+----------+-------------------------------------------+
| longId                    |          | The identifier of the 'long' component    |
+---------------------------+----------+-------------------------------------------+




SetCapFloorCMSSpreadSecurityMaturityDate

........................................
SetCapFloorCMSSpreadSecurityMaturityDate
........................................


Updates the maturity date of a Cap/Floor CMS Spread security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+----------+-------------------------------------------+
| Parameter                 | Required | Description                               |
+===========================+==========+===========================================+
| capFloorCMSSpreadSecurity | Yes      | A Cap/Floor CMS Spread security to update |
+---------------------------+----------+-------------------------------------------+
| maturityDate              |          | The maturity date                         |
+---------------------------+----------+-------------------------------------------+




SetCapFloorCMSSpreadSecurityNotional

....................................
SetCapFloorCMSSpreadSecurityNotional
....................................


Updates the notional of a Cap/Floor CMS Spread security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+----------+-------------------------------------------+
| Parameter                 | Required | Description                               |
+===========================+==========+===========================================+
| capFloorCMSSpreadSecurity | Yes      | A Cap/Floor CMS Spread security to update |
+---------------------------+----------+-------------------------------------------+
| notional                  | Yes      | The notional                              |
+---------------------------+----------+-------------------------------------------+




SetCapFloorCMSSpreadSecurityPayer

.................................
SetCapFloorCMSSpreadSecurityPayer
.................................


Updates the payer flag of a Cap/Floor CMS Spread security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+----------+-------------------------------------------+
| Parameter                 | Required | Description                               |
+===========================+==========+===========================================+
| capFloorCMSSpreadSecurity | Yes      | A Cap/Floor CMS Spread security to update |
+---------------------------+----------+-------------------------------------------+
| payer                     | Yes      | The payer flag                            |
+---------------------------+----------+-------------------------------------------+




SetCapFloorCMSSpreadSecurityShortId

...................................
SetCapFloorCMSSpreadSecurityShortId
...................................


Updates the identifier of the 'short' component of a Cap/Floor CMS Spread security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+----------+-------------------------------------------+
| Parameter                 | Required | Description                               |
+===========================+==========+===========================================+
| capFloorCMSSpreadSecurity | Yes      | A Cap/Floor CMS Spread security to update |
+---------------------------+----------+-------------------------------------------+
| shortId                   |          | The identifier of the 'short' component   |
+---------------------------+----------+-------------------------------------------+




SetCapFloorCMSSpreadSecurityStartDate

.....................................
SetCapFloorCMSSpreadSecurityStartDate
.....................................


Updates the start date of a Cap/Floor CMS Spread security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+----------+-------------------------------------------+
| Parameter                 | Required | Description                               |
+===========================+==========+===========================================+
| capFloorCMSSpreadSecurity | Yes      | A Cap/Floor CMS Spread security to update |
+---------------------------+----------+-------------------------------------------+
| startDate                 |          | The start date                            |
+---------------------------+----------+-------------------------------------------+




SetCapFloorCMSSpreadSecurityStrike

..................................
SetCapFloorCMSSpreadSecurityStrike
..................................


Updates the strike of a Cap/Floor CMS Spread security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+----------+-------------------------------------------+
| Parameter                 | Required | Description                               |
+===========================+==========+===========================================+
| capFloorCMSSpreadSecurity | Yes      | A Cap/Floor CMS Spread security to update |
+---------------------------+----------+-------------------------------------------+
| strike                    | Yes      | The strike                                |
+---------------------------+----------+-------------------------------------------+




SetCapFloorSecurityCap

......................
SetCapFloorSecurityCap
......................


Updates the cap flag of a Cap/Floor security. The original object is unchanged - a new object is returned with the updated value.



+------------------+----------+--------------------------------+
| Parameter        | Required | Description                    |
+==================+==========+================================+
| capFloorSecurity | Yes      | A Cap/Floor security to update |
+------------------+----------+--------------------------------+
| cap              | Yes      | The cap flag                   |
+------------------+----------+--------------------------------+




SetCapFloorSecurityCurrency

...........................
SetCapFloorSecurityCurrency
...........................


Updates the currency of a Cap/Floor security. The original object is unchanged - a new object is returned with the updated value.



+------------------+----------+--------------------------------+
| Parameter        | Required | Description                    |
+==================+==========+================================+
| capFloorSecurity | Yes      | A Cap/Floor security to update |
+------------------+----------+--------------------------------+
| currency         |          | The currency                   |
+------------------+----------+--------------------------------+




SetCapFloorSecurityDayCount

...........................
SetCapFloorSecurityDayCount
...........................


Updates the day count convention of a Cap/Floor security. The original object is unchanged - a new object is returned with the updated value.



+------------------+----------+--------------------------------+
| Parameter        | Required | Description                    |
+==================+==========+================================+
| capFloorSecurity | Yes      | A Cap/Floor security to update |
+------------------+----------+--------------------------------+
| dayCount         |          | The day count convention       |
+------------------+----------+--------------------------------+




SetCapFloorSecurityFrequency

............................
SetCapFloorSecurityFrequency
............................


Updates the frequency of a Cap/Floor security. The original object is unchanged - a new object is returned with the updated value.



+------------------+----------+--------------------------------+
| Parameter        | Required | Description                    |
+==================+==========+================================+
| capFloorSecurity | Yes      | A Cap/Floor security to update |
+------------------+----------+--------------------------------+
| frequency        |          | The frequency                  |
+------------------+----------+--------------------------------+




SetCapFloorSecurityIbor

.......................
SetCapFloorSecurityIbor
.......................


Updates the ibor flag of a Cap/Floor security. The original object is unchanged - a new object is returned with the updated value.



+------------------+----------+--------------------------------+
| Parameter        | Required | Description                    |
+==================+==========+================================+
| capFloorSecurity | Yes      | A Cap/Floor security to update |
+------------------+----------+--------------------------------+
| ibor             | Yes      | The ibor flag                  |
+------------------+----------+--------------------------------+




SetCapFloorSecurityMaturityDate

...............................
SetCapFloorSecurityMaturityDate
...............................


Updates the maturity date of a Cap/Floor security. The original object is unchanged - a new object is returned with the updated value.



+------------------+----------+--------------------------------+
| Parameter        | Required | Description                    |
+==================+==========+================================+
| capFloorSecurity | Yes      | A Cap/Floor security to update |
+------------------+----------+--------------------------------+
| maturityDate     |          | The maturity date              |
+------------------+----------+--------------------------------+




SetCapFloorSecurityNotional

...........................
SetCapFloorSecurityNotional
...........................


Updates the notional of a Cap/Floor security. The original object is unchanged - a new object is returned with the updated value.



+------------------+----------+--------------------------------+
| Parameter        | Required | Description                    |
+==================+==========+================================+
| capFloorSecurity | Yes      | A Cap/Floor security to update |
+------------------+----------+--------------------------------+
| notional         | Yes      | The notional                   |
+------------------+----------+--------------------------------+




SetCapFloorSecurityPayer

........................
SetCapFloorSecurityPayer
........................


Updates the payer flag of a Cap/Floor security. The original object is unchanged - a new object is returned with the updated value.



+------------------+----------+--------------------------------+
| Parameter        | Required | Description                    |
+==================+==========+================================+
| capFloorSecurity | Yes      | A Cap/Floor security to update |
+------------------+----------+--------------------------------+
| payer            | Yes      | The payer flag                 |
+------------------+----------+--------------------------------+




SetCapFloorSecurityStartDate

............................
SetCapFloorSecurityStartDate
............................


Updates the start date of a Cap/Floor security. The original object is unchanged - a new object is returned with the updated value.



+------------------+----------+--------------------------------+
| Parameter        | Required | Description                    |
+==================+==========+================================+
| capFloorSecurity | Yes      | A Cap/Floor security to update |
+------------------+----------+--------------------------------+
| startDate        |          | The start date                 |
+------------------+----------+--------------------------------+




SetCapFloorSecurityStrike

.........................
SetCapFloorSecurityStrike
.........................


Updates the strike of a Cap/Floor security. The original object is unchanged - a new object is returned with the updated value.



+------------------+----------+--------------------------------+
| Parameter        | Required | Description                    |
+==================+==========+================================+
| capFloorSecurity | Yes      | A Cap/Floor security to update |
+------------------+----------+--------------------------------+
| strike           | Yes      | The strike                     |
+------------------+----------+--------------------------------+




SetCapFloorSecurityUnderlyingId

...............................
SetCapFloorSecurityUnderlyingId
...............................


Updates the identifier of the underlying security of a Cap/Floor security. The original object is unchanged - a new object is returned with the updated value.



+------------------+----------+-------------------------------------------+
| Parameter        | Required | Description                               |
+==================+==========+===========================================+
| capFloorSecurity | Yes      | A Cap/Floor security to update            |
+------------------+----------+-------------------------------------------+
| underlyingId     |          | The identifier of the underlying security |
+------------------+----------+-------------------------------------------+




SetCommodityFutureSecurityUnitName

..................................
SetCommodityFutureSecurityUnitName
..................................


Updates the name of units unitNumber is measured in of a commodity future. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------+---------------------------------------------+
| Parameter               | Required | Description                                 |
+=========================+==========+=============================================+
| commodityFutureSecurity | Yes      | A commodity future to update                |
+-------------------------+----------+---------------------------------------------+
| unitName                |          | The name of units unitNumber is measured in |
+-------------------------+----------+---------------------------------------------+




SetCommodityFutureSecurityUnitNumber

....................................
SetCommodityFutureSecurityUnitNumber
....................................


Updates the number of units of a commodity future. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------+------------------------------+
| Parameter               | Required | Description                  |
+=========================+==========+==============================+
| commodityFutureSecurity | Yes      | A commodity future to update |
+-------------------------+----------+------------------------------+
| unitNumber              |          | The number of units          |
+-------------------------+----------+------------------------------+



