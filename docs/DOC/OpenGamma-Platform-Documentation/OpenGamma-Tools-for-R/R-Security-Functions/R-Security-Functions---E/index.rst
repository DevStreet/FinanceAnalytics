title: R Security Functions - E
shortcut: DOC:R Security Functions - E
---
EnergyFutureSecurity

....................
EnergyFutureSecurity
....................


Defines an energy future security.



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
| contractCategory   | Yes      | The category                              |
+--------------------+----------+-------------------------------------------+




EquityBarrierOptionSecurity

...........................
EquityBarrierOptionSecurity
...........................


Defines an equity barrier option security.



+-------------------+----------+-------------------------------------------+
| Parameter         | Required | Description                               |
+===================+==========+===========================================+
| name              | Yes      | The display name or label of the security |
+-------------------+----------+-------------------------------------------+
| optionType        | Yes      | The type of option (PUT or CALL)          |
+-------------------+----------+-------------------------------------------+
| strike            | Yes      | The strike                                |
+-------------------+----------+-------------------------------------------+
| currency          | Yes      | The currency                              |
+-------------------+----------+-------------------------------------------+
| underlyingId      | Yes      | The identifier of the underlying security |
+-------------------+----------+-------------------------------------------+
| exerciseType      | Yes      | The exercise type                         |
+-------------------+----------+-------------------------------------------+
| expiry            | Yes      | The expiry                                |
+-------------------+----------+-------------------------------------------+
| pointValue        | Yes      | The point value                           |
+-------------------+----------+-------------------------------------------+
| exchange          | Yes      | The exchange                              |
+-------------------+----------+-------------------------------------------+
| barrierType       | Yes      | The barrier type                          |
+-------------------+----------+-------------------------------------------+
| barrierDirection  | Yes      | The barrier direction                     |
+-------------------+----------+-------------------------------------------+
| monitoringType    | Yes      | The monitoringType                        |
+-------------------+----------+-------------------------------------------+
| samplingFrequency | Yes      | The sampling frequency                    |
+-------------------+----------+-------------------------------------------+
| barrierLevel      | Yes      | The barrier level                         |
+-------------------+----------+-------------------------------------------+




EquityFutureSecurity

....................
EquityFutureSecurity
....................


Defines an equity future security.



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
| settlementDate     | Yes      | The settlement date                       |
+--------------------+----------+-------------------------------------------+
| underlyingId       | Yes      | The underlying identifier                 |
+--------------------+----------+-------------------------------------------+
| contractCategory   | Yes      | The category                              |
+--------------------+----------+-------------------------------------------+




EquityIndexDividendFutureSecurity

.................................
EquityIndexDividendFutureSecurity
.................................


Defines an equity index dividend future.



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
| settlementDate     | Yes      | The settlement date                       |
+--------------------+----------+-------------------------------------------+
| underlyingId       | Yes      | The underlying identifier                 |
+--------------------+----------+-------------------------------------------+
| contractCategory   | Yes      | The category                              |
+--------------------+----------+-------------------------------------------+




EquityIndexOptionSecurity

.........................
EquityIndexOptionSecurity
.........................


Defines an equity index option security.



+--------------+----------+---------------------------------------------+
| Parameter    | Required | Description                                 |
+==============+==========+=============================================+
| name         | Yes      | The display name or label of the security   |
+--------------+----------+---------------------------------------------+
| optionType   | Yes      | The type of option (PUT or CALL)            |
+--------------+----------+---------------------------------------------+
| strike       | Yes      | The strike                                  |
+--------------+----------+---------------------------------------------+
| currency     | Yes      | The currency                                |
+--------------+----------+---------------------------------------------+
| underlyingId | Yes      | The identifier of the underlying identifier |
+--------------+----------+---------------------------------------------+
| exerciseType | Yes      | The exercise type                           |
+--------------+----------+---------------------------------------------+
| expiry       | Yes      | The expiry                                  |
+--------------+----------+---------------------------------------------+
| pointValue   | Yes      | The point value                             |
+--------------+----------+---------------------------------------------+
| exchange     | Yes      | The exchange                                |
+--------------+----------+---------------------------------------------+




EquityOptionSecurity

....................
EquityOptionSecurity
....................


Defines an equity option security.



+--------------+----------+-------------------------------------------+
| Parameter    | Required | Description                               |
+==============+==========+===========================================+
| name         | Yes      | The display name or label of the security |
+--------------+----------+-------------------------------------------+
| optionType   | Yes      | The type of option (PUT or CALL)          |
+--------------+----------+-------------------------------------------+
| strike       | Yes      | The strike                                |
+--------------+----------+-------------------------------------------+
| currency     | Yes      | The currency                              |
+--------------+----------+-------------------------------------------+
| underlyingId | Yes      | The identifier of the underlying security |
+--------------+----------+-------------------------------------------+
| exerciseType | Yes      | The exercise type                         |
+--------------+----------+-------------------------------------------+
| expiry       | Yes      | The expiry                                |
+--------------+----------+-------------------------------------------+
| pointValue   | Yes      | The point value                           |
+--------------+----------+-------------------------------------------+
| exchange     | Yes      | The exchange                              |
+--------------+----------+-------------------------------------------+




EquitySecurity

..............
EquitySecurity
..............


Defines an equity security.



+--------------+----------+-------------------------------------------+
| Parameter    | Required | Description                               |
+==============+==========+===========================================+
| name         | Yes      | The display name or label of the security |
+--------------+----------+-------------------------------------------+
| exchange     | Yes      | The exchange name                         |
+--------------+----------+-------------------------------------------+
| exchangeCode | Yes      | The exchange code                         |
+--------------+----------+-------------------------------------------+
| companyName  | Yes      | The company name                          |
+--------------+----------+-------------------------------------------+
| currency     | Yes      | The currency                              |
+--------------+----------+-------------------------------------------+




EquityVarianceSwapSecurity

..........................
EquityVarianceSwapSecurity
..........................


Defines an equity variance swap security.



+-------------------------+----------+-------------------------------------------+
| Parameter               | Required | Description                               |
+=========================+==========+===========================================+
| name                    | Yes      | The display name or label of the security |
+-------------------------+----------+-------------------------------------------+
| spotUnderlyingId        | Yes      | The underlying identifier                 |
+-------------------------+----------+-------------------------------------------+
| currency                | Yes      | The currency                              |
+-------------------------+----------+-------------------------------------------+
| strike                  | Yes      | The strike                                |
+-------------------------+----------+-------------------------------------------+
| notional                | Yes      | The notional                              |
+-------------------------+----------+-------------------------------------------+
| parameterizedAsVariance | Yes      | The parameterized as variance             |
+-------------------------+----------+-------------------------------------------+
| annualizationFactor     | Yes      | The annualization factor                  |
+-------------------------+----------+-------------------------------------------+
| firstObservationDate    | Yes      | The first observation date                |
+-------------------------+----------+-------------------------------------------+
| lastObservationDate     | Yes      | The last observation date                 |
+-------------------------+----------+-------------------------------------------+
| settlementDate          | Yes      | The settlement date                       |
+-------------------------+----------+-------------------------------------------+
| regionId                | Yes      | The region                                |
+-------------------------+----------+-------------------------------------------+
| observationFrequency    | Yes      | The observation frequency                 |
+-------------------------+----------+-------------------------------------------+




EuropeanExerciseType

....................
EuropeanExerciseType
....................


Returns an object representing an European option exercise type.

This function takes no parameters.


ExpandEnergyFutureSecurity

..........................
ExpandEnergyFutureSecurity
..........................


Expand the contents of an energy future security.



+----------------------+----------+------------------------------------+
| Parameter            | Required | Description                        |
+======================+==========+====================================+
| energyFutureSecurity | Yes      | An energy future security to query |
+----------------------+----------+------------------------------------+




ExpandEquityBarrierOptionSecurity

.................................
ExpandEquityBarrierOptionSecurity
.................................


Expand the contents of an equity barrier option security.



+-----------------------------+----------+--------------------------------------------+
| Parameter                   | Required | Description                                |
+=============================+==========+============================================+
| equityBarrierOptionSecurity | Yes      | An equity barrier option security to query |
+-----------------------------+----------+--------------------------------------------+




ExpandEquityFutureSecurity

..........................
ExpandEquityFutureSecurity
..........................


Expand the contents of an equity future security.



+----------------------+----------+------------------------------------+
| Parameter            | Required | Description                        |
+======================+==========+====================================+
| equityFutureSecurity | Yes      | An equity future security to query |
+----------------------+----------+------------------------------------+




ExpandEquityIndexDividendFutureSecurity

.......................................
ExpandEquityIndexDividendFutureSecurity
.......................................


Expand the contents of an equity index dividend future.



+-----------------------------------+----------+------------------------------------------+
| Parameter                         | Required | Description                              |
+===================================+==========+==========================================+
| equityIndexDividendFutureSecurity | Yes      | An equity index dividend future to query |
+-----------------------------------+----------+------------------------------------------+




ExpandEquityIndexOptionSecurity

...............................
ExpandEquityIndexOptionSecurity
...............................


Expand the contents of an equity index option security.



+---------------------------+----------+------------------------------------------+
| Parameter                 | Required | Description                              |
+===========================+==========+==========================================+
| equityIndexOptionSecurity | Yes      | An equity index option security to query |
+---------------------------+----------+------------------------------------------+




ExpandEquityOptionSecurity

..........................
ExpandEquityOptionSecurity
..........................


Expand the contents of an equity option security.



+----------------------+----------+------------------------------------+
| Parameter            | Required | Description                        |
+======================+==========+====================================+
| equityOptionSecurity | Yes      | An equity option security to query |
+----------------------+----------+------------------------------------+




ExpandEquitySecurity

....................
ExpandEquitySecurity
....................


Expand the contents of an equity security.



+----------------+----------+-----------------------------+
| Parameter      | Required | Description                 |
+================+==========+=============================+
| equitySecurity | Yes      | An equity security to query |
+----------------+----------+-----------------------------+




ExpandEquityVarianceSwapSecurity

................................
ExpandEquityVarianceSwapSecurity
................................


Expand the contents of an equity variance swap security.



+----------------------------+----------+-------------------------------------------+
| Parameter                  | Required | Description                               |
+============================+==========+===========================================+
| equityVarianceSwapSecurity | Yes      | An equity variance swap security to query |
+----------------------------+----------+-------------------------------------------+




ExtremeSpreadPayoffStyle

........................
ExtremeSpreadPayoffStyle
........................


Returns an object representing an 'extreme spread' option payoff style.



+-----------+----------+---------------------+
| Parameter | Required | Description         |
+===========+==========+=====================+
| periodEnd | Yes      | The period end      |
+-----------+----------+---------------------+
| isReverse | Yes      | The is reverse flag |
+-----------+----------+---------------------+




GetEnergyFutureSecurityUnderlyingId

...................................
GetEnergyFutureSecurityUnderlyingId
...................................


Returns the underlying identifier  from an energy future security.



+----------------------+----------+------------------------------------+
| Parameter            | Required | Description                        |
+======================+==========+====================================+
| energyFutureSecurity | Yes      | An energy future security to query |
+----------------------+----------+------------------------------------+




GetEquityBarrierOptionSecurityBarrierDirection

..............................................
GetEquityBarrierOptionSecurityBarrierDirection
..............................................


Returns the barrier direction from an equity barrier option security.



+-----------------------------+----------+--------------------------------------------+
| Parameter                   | Required | Description                                |
+=============================+==========+============================================+
| equityBarrierOptionSecurity | Yes      | An equity barrier option security to query |
+-----------------------------+----------+--------------------------------------------+




GetEquityBarrierOptionSecurityBarrierLevel

..........................................
GetEquityBarrierOptionSecurityBarrierLevel
..........................................


Returns the barrier level from an equity barrier option security.



+-----------------------------+----------+--------------------------------------------+
| Parameter                   | Required | Description                                |
+=============================+==========+============================================+
| equityBarrierOptionSecurity | Yes      | An equity barrier option security to query |
+-----------------------------+----------+--------------------------------------------+




GetEquityBarrierOptionSecurityBarrierType

.........................................
GetEquityBarrierOptionSecurityBarrierType
.........................................


Returns the barrier type from an equity barrier option security.



+-----------------------------+----------+--------------------------------------------+
| Parameter                   | Required | Description                                |
+=============================+==========+============================================+
| equityBarrierOptionSecurity | Yes      | An equity barrier option security to query |
+-----------------------------+----------+--------------------------------------------+




GetEquityBarrierOptionSecurityCurrency

......................................
GetEquityBarrierOptionSecurityCurrency
......................................


Returns the currency from an equity barrier option security.



+-----------------------------+----------+--------------------------------------------+
| Parameter                   | Required | Description                                |
+=============================+==========+============================================+
| equityBarrierOptionSecurity | Yes      | An equity barrier option security to query |
+-----------------------------+----------+--------------------------------------------+




GetEquityBarrierOptionSecurityExchange

......................................
GetEquityBarrierOptionSecurityExchange
......................................


Returns the exchange from an equity barrier option security.



+-----------------------------+----------+--------------------------------------------+
| Parameter                   | Required | Description                                |
+=============================+==========+============================================+
| equityBarrierOptionSecurity | Yes      | An equity barrier option security to query |
+-----------------------------+----------+--------------------------------------------+




GetEquityBarrierOptionSecurityExerciseType

..........................................
GetEquityBarrierOptionSecurityExerciseType
..........................................


Returns the exercise type from an equity barrier option security.



+-----------------------------+----------+--------------------------------------------+
| Parameter                   | Required | Description                                |
+=============================+==========+============================================+
| equityBarrierOptionSecurity | Yes      | An equity barrier option security to query |
+-----------------------------+----------+--------------------------------------------+




GetEquityBarrierOptionSecurityExpiry

....................................
GetEquityBarrierOptionSecurityExpiry
....................................


Returns the expiry from an equity barrier option security.



+-----------------------------+----------+--------------------------------------------+
| Parameter                   | Required | Description                                |
+=============================+==========+============================================+
| equityBarrierOptionSecurity | Yes      | An equity barrier option security to query |
+-----------------------------+----------+--------------------------------------------+




GetEquityBarrierOptionSecurityMonitoringType

............................................
GetEquityBarrierOptionSecurityMonitoringType
............................................


Returns the monitoringType from an equity barrier option security.



+-----------------------------+----------+--------------------------------------------+
| Parameter                   | Required | Description                                |
+=============================+==========+============================================+
| equityBarrierOptionSecurity | Yes      | An equity barrier option security to query |
+-----------------------------+----------+--------------------------------------------+




GetEquityBarrierOptionSecurityOptionType

........................................
GetEquityBarrierOptionSecurityOptionType
........................................


Returns the type of option (PUT or CALL) from an equity barrier option security.



+-----------------------------+----------+--------------------------------------------+
| Parameter                   | Required | Description                                |
+=============================+==========+============================================+
| equityBarrierOptionSecurity | Yes      | An equity barrier option security to query |
+-----------------------------+----------+--------------------------------------------+




GetEquityBarrierOptionSecurityPointValue

........................................
GetEquityBarrierOptionSecurityPointValue
........................................


Returns the point value from an equity barrier option security.



+-----------------------------+----------+--------------------------------------------+
| Parameter                   | Required | Description                                |
+=============================+==========+============================================+
| equityBarrierOptionSecurity | Yes      | An equity barrier option security to query |
+-----------------------------+----------+--------------------------------------------+




GetEquityBarrierOptionSecuritySamplingFrequency

...............................................
GetEquityBarrierOptionSecuritySamplingFrequency
...............................................


Returns the sampling frequency from an equity barrier option security.



+-----------------------------+----------+--------------------------------------------+
| Parameter                   | Required | Description                                |
+=============================+==========+============================================+
| equityBarrierOptionSecurity | Yes      | An equity barrier option security to query |
+-----------------------------+----------+--------------------------------------------+




GetEquityBarrierOptionSecurityStrike

....................................
GetEquityBarrierOptionSecurityStrike
....................................


Returns the strike from an equity barrier option security.



+-----------------------------+----------+--------------------------------------------+
| Parameter                   | Required | Description                                |
+=============================+==========+============================================+
| equityBarrierOptionSecurity | Yes      | An equity barrier option security to query |
+-----------------------------+----------+--------------------------------------------+




GetEquityBarrierOptionSecurityUnderlyingId

..........................................
GetEquityBarrierOptionSecurityUnderlyingId
..........................................


Returns the identifier of the underlying security from an equity barrier option security.



+-----------------------------+----------+--------------------------------------------+
| Parameter                   | Required | Description                                |
+=============================+==========+============================================+
| equityBarrierOptionSecurity | Yes      | An equity barrier option security to query |
+-----------------------------+----------+--------------------------------------------+




GetEquityFutureSecuritySettlementDate

.....................................
GetEquityFutureSecuritySettlementDate
.....................................


Returns the settlement date  from an equity future security.



+----------------------+----------+------------------------------------+
| Parameter            | Required | Description                        |
+======================+==========+====================================+
| equityFutureSecurity | Yes      | An equity future security to query |
+----------------------+----------+------------------------------------+




GetEquityFutureSecurityUnderlyingId

...................................
GetEquityFutureSecurityUnderlyingId
...................................


Returns the underlying identifier from an equity future security.



+----------------------+----------+------------------------------------+
| Parameter            | Required | Description                        |
+======================+==========+====================================+
| equityFutureSecurity | Yes      | An equity future security to query |
+----------------------+----------+------------------------------------+




GetEquityIndexOptionSecurityCurrency

....................................
GetEquityIndexOptionSecurityCurrency
....................................


Returns the currency from an equity index option security.



+---------------------------+----------+------------------------------------------+
| Parameter                 | Required | Description                              |
+===========================+==========+==========================================+
| equityIndexOptionSecurity | Yes      | An equity index option security to query |
+---------------------------+----------+------------------------------------------+




GetEquityIndexOptionSecurityExchange

....................................
GetEquityIndexOptionSecurityExchange
....................................


Returns the exchange from an equity index option security.



+---------------------------+----------+------------------------------------------+
| Parameter                 | Required | Description                              |
+===========================+==========+==========================================+
| equityIndexOptionSecurity | Yes      | An equity index option security to query |
+---------------------------+----------+------------------------------------------+




GetEquityIndexOptionSecurityExerciseType

........................................
GetEquityIndexOptionSecurityExerciseType
........................................


Returns the exercise type from an equity index option security.



+---------------------------+----------+------------------------------------------+
| Parameter                 | Required | Description                              |
+===========================+==========+==========================================+
| equityIndexOptionSecurity | Yes      | An equity index option security to query |
+---------------------------+----------+------------------------------------------+




GetEquityIndexOptionSecurityExpiry

..................................
GetEquityIndexOptionSecurityExpiry
..................................


Returns the expiry from an equity index option security.



+---------------------------+----------+------------------------------------------+
| Parameter                 | Required | Description                              |
+===========================+==========+==========================================+
| equityIndexOptionSecurity | Yes      | An equity index option security to query |
+---------------------------+----------+------------------------------------------+




GetEquityIndexOptionSecurityOptionType

......................................
GetEquityIndexOptionSecurityOptionType
......................................


Returns the type of option (PUT or CALL) from an equity index option security.



+---------------------------+----------+------------------------------------------+
| Parameter                 | Required | Description                              |
+===========================+==========+==========================================+
| equityIndexOptionSecurity | Yes      | An equity index option security to query |
+---------------------------+----------+------------------------------------------+




GetEquityIndexOptionSecurityPointValue

......................................
GetEquityIndexOptionSecurityPointValue
......................................


Returns the point value from an equity index option security.



+---------------------------+----------+------------------------------------------+
| Parameter                 | Required | Description                              |
+===========================+==========+==========================================+
| equityIndexOptionSecurity | Yes      | An equity index option security to query |
+---------------------------+----------+------------------------------------------+




GetEquityIndexOptionSecurityStrike

..................................
GetEquityIndexOptionSecurityStrike
..................................


Returns the strike from an equity index option security.



+---------------------------+----------+------------------------------------------+
| Parameter                 | Required | Description                              |
+===========================+==========+==========================================+
| equityIndexOptionSecurity | Yes      | An equity index option security to query |
+---------------------------+----------+------------------------------------------+




GetEquityIndexOptionSecurityUnderlyingId

........................................
GetEquityIndexOptionSecurityUnderlyingId
........................................


Returns the identifier of the underlying identifier from an equity index option security.



+---------------------------+----------+------------------------------------------+
| Parameter                 | Required | Description                              |
+===========================+==========+==========================================+
| equityIndexOptionSecurity | Yes      | An equity index option security to query |
+---------------------------+----------+------------------------------------------+




GetEquityOptionSecurityCurrency

...............................
GetEquityOptionSecurityCurrency
...............................


Returns the currency from an equity option security.



+----------------------+----------+------------------------------------+
| Parameter            | Required | Description                        |
+======================+==========+====================================+
| equityOptionSecurity | Yes      | An equity option security to query |
+----------------------+----------+------------------------------------+




GetEquityOptionSecurityExchange

...............................
GetEquityOptionSecurityExchange
...............................


Returns the exchange from an equity option security.



+----------------------+----------+------------------------------------+
| Parameter            | Required | Description                        |
+======================+==========+====================================+
| equityOptionSecurity | Yes      | An equity option security to query |
+----------------------+----------+------------------------------------+




GetEquityOptionSecurityExerciseType

...................................
GetEquityOptionSecurityExerciseType
...................................


Returns the exercise type from an equity option security.



+----------------------+----------+------------------------------------+
| Parameter            | Required | Description                        |
+======================+==========+====================================+
| equityOptionSecurity | Yes      | An equity option security to query |
+----------------------+----------+------------------------------------+




GetEquityOptionSecurityExpiry

.............................
GetEquityOptionSecurityExpiry
.............................


Returns the expiry from an equity option security.



+----------------------+----------+------------------------------------+
| Parameter            | Required | Description                        |
+======================+==========+====================================+
| equityOptionSecurity | Yes      | An equity option security to query |
+----------------------+----------+------------------------------------+




GetEquityOptionSecurityOptionType

.................................
GetEquityOptionSecurityOptionType
.................................


Returns the type of option (PUT or CALL) from an equity option security.



+----------------------+----------+------------------------------------+
| Parameter            | Required | Description                        |
+======================+==========+====================================+
| equityOptionSecurity | Yes      | An equity option security to query |
+----------------------+----------+------------------------------------+




GetEquityOptionSecurityPointValue

.................................
GetEquityOptionSecurityPointValue
.................................


Returns the point value from an equity option security.



+----------------------+----------+------------------------------------+
| Parameter            | Required | Description                        |
+======================+==========+====================================+
| equityOptionSecurity | Yes      | An equity option security to query |
+----------------------+----------+------------------------------------+




GetEquityOptionSecurityStrike

.............................
GetEquityOptionSecurityStrike
.............................


Returns the strike from an equity option security.



+----------------------+----------+------------------------------------+
| Parameter            | Required | Description                        |
+======================+==========+====================================+
| equityOptionSecurity | Yes      | An equity option security to query |
+----------------------+----------+------------------------------------+




GetEquityOptionSecurityUnderlyingId

...................................
GetEquityOptionSecurityUnderlyingId
...................................


Returns the identifier of the underlying security from an equity option security.



+----------------------+----------+------------------------------------+
| Parameter            | Required | Description                        |
+======================+==========+====================================+
| equityOptionSecurity | Yes      | An equity option security to query |
+----------------------+----------+------------------------------------+




GetEquitySecurityCompanyName

............................
GetEquitySecurityCompanyName
............................


Returns the company name from an equity security.



+----------------+----------+-----------------------------+
| Parameter      | Required | Description                 |
+================+==========+=============================+
| equitySecurity | Yes      | An equity security to query |
+----------------+----------+-----------------------------+




GetEquitySecurityCurrency

.........................
GetEquitySecurityCurrency
.........................


Returns the currency from an equity security.



+----------------+----------+-----------------------------+
| Parameter      | Required | Description                 |
+================+==========+=============================+
| equitySecurity | Yes      | An equity security to query |
+----------------+----------+-----------------------------+




GetEquitySecurityExchange

.........................
GetEquitySecurityExchange
.........................


Returns the exchange name from an equity security.



+----------------+----------+-----------------------------+
| Parameter      | Required | Description                 |
+================+==========+=============================+
| equitySecurity | Yes      | An equity security to query |
+----------------+----------+-----------------------------+




GetEquitySecurityExchangeCode

.............................
GetEquitySecurityExchangeCode
.............................


Returns the exchange code from an equity security.



+----------------+----------+-----------------------------+
| Parameter      | Required | Description                 |
+================+==========+=============================+
| equitySecurity | Yes      | An equity security to query |
+----------------+----------+-----------------------------+




GetEquitySecurityGicsCode

.........................
GetEquitySecurityGicsCode
.........................


Returns the GICS code from an equity security.



+----------------+----------+-----------------------------+
| Parameter      | Required | Description                 |
+================+==========+=============================+
| equitySecurity | Yes      | An equity security to query |
+----------------+----------+-----------------------------+




GetEquitySecurityShortName

..........................
GetEquitySecurityShortName
..........................


Returns the short name from an equity security.



+----------------+----------+-----------------------------+
| Parameter      | Required | Description                 |
+================+==========+=============================+
| equitySecurity | Yes      | An equity security to query |
+----------------+----------+-----------------------------+




GetEquityVarianceSwapSecurityAnnualizationFactor

................................................
GetEquityVarianceSwapSecurityAnnualizationFactor
................................................


Returns the annualization factor from an equity variance swap security.



+----------------------------+----------+-------------------------------------------+
| Parameter                  | Required | Description                               |
+============================+==========+===========================================+
| equityVarianceSwapSecurity | Yes      | An equity variance swap security to query |
+----------------------------+----------+-------------------------------------------+




GetEquityVarianceSwapSecurityCurrency

.....................................
GetEquityVarianceSwapSecurityCurrency
.....................................


Returns the currency from an equity variance swap security.



+----------------------------+----------+-------------------------------------------+
| Parameter                  | Required | Description                               |
+============================+==========+===========================================+
| equityVarianceSwapSecurity | Yes      | An equity variance swap security to query |
+----------------------------+----------+-------------------------------------------+




GetEquityVarianceSwapSecurityFirstObservationDate

.................................................
GetEquityVarianceSwapSecurityFirstObservationDate
.................................................


Returns the first observation date from an equity variance swap security.



+----------------------------+----------+-------------------------------------------+
| Parameter                  | Required | Description                               |
+============================+==========+===========================================+
| equityVarianceSwapSecurity | Yes      | An equity variance swap security to query |
+----------------------------+----------+-------------------------------------------+




GetEquityVarianceSwapSecurityLastObservationDate

................................................
GetEquityVarianceSwapSecurityLastObservationDate
................................................


Returns the last observation date from an equity variance swap security.



+----------------------------+----------+-------------------------------------------+
| Parameter                  | Required | Description                               |
+============================+==========+===========================================+
| equityVarianceSwapSecurity | Yes      | An equity variance swap security to query |
+----------------------------+----------+-------------------------------------------+




GetEquityVarianceSwapSecurityNotional

.....................................
GetEquityVarianceSwapSecurityNotional
.....................................


Returns the notional from an equity variance swap security.



+----------------------------+----------+-------------------------------------------+
| Parameter                  | Required | Description                               |
+============================+==========+===========================================+
| equityVarianceSwapSecurity | Yes      | An equity variance swap security to query |
+----------------------------+----------+-------------------------------------------+




GetEquityVarianceSwapSecurityObservationFrequency

.................................................
GetEquityVarianceSwapSecurityObservationFrequency
.................................................


Returns the observation frequency from an equity variance swap security.



+----------------------------+----------+-------------------------------------------+
| Parameter                  | Required | Description                               |
+============================+==========+===========================================+
| equityVarianceSwapSecurity | Yes      | An equity variance swap security to query |
+----------------------------+----------+-------------------------------------------+




GetEquityVarianceSwapSecurityParameterizedAsVariance

....................................................
GetEquityVarianceSwapSecurityParameterizedAsVariance
....................................................


Returns the parameterized as variance from an equity variance swap security.



+----------------------------+----------+-------------------------------------------+
| Parameter                  | Required | Description                               |
+============================+==========+===========================================+
| equityVarianceSwapSecurity | Yes      | An equity variance swap security to query |
+----------------------------+----------+-------------------------------------------+




GetEquityVarianceSwapSecurityRegionId

.....................................
GetEquityVarianceSwapSecurityRegionId
.....................................


Returns the region from an equity variance swap security.



+----------------------------+----------+-------------------------------------------+
| Parameter                  | Required | Description                               |
+============================+==========+===========================================+
| equityVarianceSwapSecurity | Yes      | An equity variance swap security to query |
+----------------------------+----------+-------------------------------------------+




GetEquityVarianceSwapSecuritySettlementDate

...........................................
GetEquityVarianceSwapSecuritySettlementDate
...........................................


Returns the settlement date from an equity variance swap security.



+----------------------------+----------+-------------------------------------------+
| Parameter                  | Required | Description                               |
+============================+==========+===========================================+
| equityVarianceSwapSecurity | Yes      | An equity variance swap security to query |
+----------------------------+----------+-------------------------------------------+




GetEquityVarianceSwapSecuritySpotUnderlyingId

.............................................
GetEquityVarianceSwapSecuritySpotUnderlyingId
.............................................


Returns the underlying identifier from an equity variance swap security.



+----------------------------+----------+-------------------------------------------+
| Parameter                  | Required | Description                               |
+============================+==========+===========================================+
| equityVarianceSwapSecurity | Yes      | An equity variance swap security to query |
+----------------------------+----------+-------------------------------------------+




GetEquityVarianceSwapSecurityStrike

...................................
GetEquityVarianceSwapSecurityStrike
...................................


Returns the strike from an equity variance swap security.



+----------------------------+----------+-------------------------------------------+
| Parameter                  | Required | Description                               |
+============================+==========+===========================================+
| equityVarianceSwapSecurity | Yes      | An equity variance swap security to query |
+----------------------------+----------+-------------------------------------------+




SetEnergyFutureSecurityUnderlyingId

...................................
SetEnergyFutureSecurityUnderlyingId
...................................


Updates the underlying identifier  of an energy future security. The original object is unchanged - a new object is returned with the updated value.



+----------------------+----------+-------------------------------------+
| Parameter            | Required | Description                         |
+======================+==========+=====================================+
| energyFutureSecurity | Yes      | An energy future security to update |
+----------------------+----------+-------------------------------------+
| underlyingId         |          | The underlying identifier           |
+----------------------+----------+-------------------------------------+




SetEquityBarrierOptionSecurityBarrierDirection

..............................................
SetEquityBarrierOptionSecurityBarrierDirection
..............................................


Updates the barrier direction of an equity barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-----------------------------+----------+---------------------------------------------+
| Parameter                   | Required | Description                                 |
+=============================+==========+=============================================+
| equityBarrierOptionSecurity | Yes      | An equity barrier option security to update |
+-----------------------------+----------+---------------------------------------------+
| barrierDirection            |          | The barrier direction                       |
+-----------------------------+----------+---------------------------------------------+




SetEquityBarrierOptionSecurityBarrierLevel

..........................................
SetEquityBarrierOptionSecurityBarrierLevel
..........................................


Updates the barrier level of an equity barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-----------------------------+----------+---------------------------------------------+
| Parameter                   | Required | Description                                 |
+=============================+==========+=============================================+
| equityBarrierOptionSecurity | Yes      | An equity barrier option security to update |
+-----------------------------+----------+---------------------------------------------+
| barrierLevel                | Yes      | The barrier level                           |
+-----------------------------+----------+---------------------------------------------+




SetEquityBarrierOptionSecurityBarrierType

.........................................
SetEquityBarrierOptionSecurityBarrierType
.........................................


Updates the barrier type of an equity barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-----------------------------+----------+---------------------------------------------+
| Parameter                   | Required | Description                                 |
+=============================+==========+=============================================+
| equityBarrierOptionSecurity | Yes      | An equity barrier option security to update |
+-----------------------------+----------+---------------------------------------------+
| barrierType                 |          | The barrier type                            |
+-----------------------------+----------+---------------------------------------------+




SetEquityBarrierOptionSecurityCurrency

......................................
SetEquityBarrierOptionSecurityCurrency
......................................


Updates the currency of an equity barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-----------------------------+----------+---------------------------------------------+
| Parameter                   | Required | Description                                 |
+=============================+==========+=============================================+
| equityBarrierOptionSecurity | Yes      | An equity barrier option security to update |
+-----------------------------+----------+---------------------------------------------+
| currency                    |          | The currency                                |
+-----------------------------+----------+---------------------------------------------+




SetEquityBarrierOptionSecurityExchange

......................................
SetEquityBarrierOptionSecurityExchange
......................................


Updates the exchange of an equity barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-----------------------------+----------+---------------------------------------------+
| Parameter                   | Required | Description                                 |
+=============================+==========+=============================================+
| equityBarrierOptionSecurity | Yes      | An equity barrier option security to update |
+-----------------------------+----------+---------------------------------------------+
| exchange                    |          | The exchange                                |
+-----------------------------+----------+---------------------------------------------+




SetEquityBarrierOptionSecurityExerciseType

..........................................
SetEquityBarrierOptionSecurityExerciseType
..........................................


Updates the exercise type of an equity barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-----------------------------+----------+---------------------------------------------+
| Parameter                   | Required | Description                                 |
+=============================+==========+=============================================+
| equityBarrierOptionSecurity | Yes      | An equity barrier option security to update |
+-----------------------------+----------+---------------------------------------------+
| exerciseType                |          | The exercise type                           |
+-----------------------------+----------+---------------------------------------------+




SetEquityBarrierOptionSecurityExpiry

....................................
SetEquityBarrierOptionSecurityExpiry
....................................


Updates the expiry of an equity barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-----------------------------+----------+---------------------------------------------+
| Parameter                   | Required | Description                                 |
+=============================+==========+=============================================+
| equityBarrierOptionSecurity | Yes      | An equity barrier option security to update |
+-----------------------------+----------+---------------------------------------------+
| expiry                      |          | The expiry                                  |
+-----------------------------+----------+---------------------------------------------+




SetEquityBarrierOptionSecurityMonitoringType

............................................
SetEquityBarrierOptionSecurityMonitoringType
............................................


Updates the monitoringType of an equity barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-----------------------------+----------+---------------------------------------------+
| Parameter                   | Required | Description                                 |
+=============================+==========+=============================================+
| equityBarrierOptionSecurity | Yes      | An equity barrier option security to update |
+-----------------------------+----------+---------------------------------------------+
| monitoringType              |          | The monitoringType                          |
+-----------------------------+----------+---------------------------------------------+




SetEquityBarrierOptionSecurityOptionType

........................................
SetEquityBarrierOptionSecurityOptionType
........................................


Updates the type of option (PUT or CALL) of an equity barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-----------------------------+----------+---------------------------------------------+
| Parameter                   | Required | Description                                 |
+=============================+==========+=============================================+
| equityBarrierOptionSecurity | Yes      | An equity barrier option security to update |
+-----------------------------+----------+---------------------------------------------+
| optionType                  |          | The type of option (PUT or CALL)            |
+-----------------------------+----------+---------------------------------------------+




SetEquityBarrierOptionSecurityPointValue

........................................
SetEquityBarrierOptionSecurityPointValue
........................................


Updates the point value of an equity barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-----------------------------+----------+---------------------------------------------+
| Parameter                   | Required | Description                                 |
+=============================+==========+=============================================+
| equityBarrierOptionSecurity | Yes      | An equity barrier option security to update |
+-----------------------------+----------+---------------------------------------------+
| pointValue                  | Yes      | The point value                             |
+-----------------------------+----------+---------------------------------------------+




SetEquityBarrierOptionSecuritySamplingFrequency

...............................................
SetEquityBarrierOptionSecuritySamplingFrequency
...............................................


Updates the sampling frequency of an equity barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-----------------------------+----------+---------------------------------------------+
| Parameter                   | Required | Description                                 |
+=============================+==========+=============================================+
| equityBarrierOptionSecurity | Yes      | An equity barrier option security to update |
+-----------------------------+----------+---------------------------------------------+
| samplingFrequency           |          | The sampling frequency                      |
+-----------------------------+----------+---------------------------------------------+




SetEquityBarrierOptionSecurityStrike

....................................
SetEquityBarrierOptionSecurityStrike
....................................


Updates the strike of an equity barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-----------------------------+----------+---------------------------------------------+
| Parameter                   | Required | Description                                 |
+=============================+==========+=============================================+
| equityBarrierOptionSecurity | Yes      | An equity barrier option security to update |
+-----------------------------+----------+---------------------------------------------+
| strike                      | Yes      | The strike                                  |
+-----------------------------+----------+---------------------------------------------+




SetEquityBarrierOptionSecurityUnderlyingId

..........................................
SetEquityBarrierOptionSecurityUnderlyingId
..........................................


Updates the identifier of the underlying security of an equity barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-----------------------------+----------+---------------------------------------------+
| Parameter                   | Required | Description                                 |
+=============================+==========+=============================================+
| equityBarrierOptionSecurity | Yes      | An equity barrier option security to update |
+-----------------------------+----------+---------------------------------------------+
| underlyingId                |          | The identifier of the underlying security   |
+-----------------------------+----------+---------------------------------------------+




SetEquityFutureSecuritySettlementDate

.....................................
SetEquityFutureSecuritySettlementDate
.....................................


Updates the settlement date  of an equity future security. The original object is unchanged - a new object is returned with the updated value.



+----------------------+----------+-------------------------------------+
| Parameter            | Required | Description                         |
+======================+==========+=====================================+
| equityFutureSecurity | Yes      | An equity future security to update |
+----------------------+----------+-------------------------------------+
| settlementDate       |          | The settlement date                 |
+----------------------+----------+-------------------------------------+




SetEquityFutureSecurityUnderlyingId

...................................
SetEquityFutureSecurityUnderlyingId
...................................


Updates the underlying identifier of an equity future security. The original object is unchanged - a new object is returned with the updated value.



+----------------------+----------+-------------------------------------+
| Parameter            | Required | Description                         |
+======================+==========+=====================================+
| equityFutureSecurity | Yes      | An equity future security to update |
+----------------------+----------+-------------------------------------+
| underlyingId         |          | The underlying identifier           |
+----------------------+----------+-------------------------------------+




SetEquityIndexOptionSecurityCurrency

....................................
SetEquityIndexOptionSecurityCurrency
....................................


Updates the currency of an equity index option security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+----------+-------------------------------------------+
| Parameter                 | Required | Description                               |
+===========================+==========+===========================================+
| equityIndexOptionSecurity | Yes      | An equity index option security to update |
+---------------------------+----------+-------------------------------------------+
| currency                  |          | The currency                              |
+---------------------------+----------+-------------------------------------------+




SetEquityIndexOptionSecurityExchange

....................................
SetEquityIndexOptionSecurityExchange
....................................


Updates the exchange of an equity index option security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+----------+-------------------------------------------+
| Parameter                 | Required | Description                               |
+===========================+==========+===========================================+
| equityIndexOptionSecurity | Yes      | An equity index option security to update |
+---------------------------+----------+-------------------------------------------+
| exchange                  |          | The exchange                              |
+---------------------------+----------+-------------------------------------------+




SetEquityIndexOptionSecurityExerciseType

........................................
SetEquityIndexOptionSecurityExerciseType
........................................


Updates the exercise type of an equity index option security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+----------+-------------------------------------------+
| Parameter                 | Required | Description                               |
+===========================+==========+===========================================+
| equityIndexOptionSecurity | Yes      | An equity index option security to update |
+---------------------------+----------+-------------------------------------------+
| exerciseType              |          | The exercise type                         |
+---------------------------+----------+-------------------------------------------+




SetEquityIndexOptionSecurityExpiry

..................................
SetEquityIndexOptionSecurityExpiry
..................................


Updates the expiry of an equity index option security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+----------+-------------------------------------------+
| Parameter                 | Required | Description                               |
+===========================+==========+===========================================+
| equityIndexOptionSecurity | Yes      | An equity index option security to update |
+---------------------------+----------+-------------------------------------------+
| expiry                    |          | The expiry                                |
+---------------------------+----------+-------------------------------------------+




SetEquityIndexOptionSecurityOptionType

......................................
SetEquityIndexOptionSecurityOptionType
......................................


Updates the type of option (PUT or CALL) of an equity index option security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+----------+-------------------------------------------+
| Parameter                 | Required | Description                               |
+===========================+==========+===========================================+
| equityIndexOptionSecurity | Yes      | An equity index option security to update |
+---------------------------+----------+-------------------------------------------+
| optionType                |          | The type of option (PUT or CALL)          |
+---------------------------+----------+-------------------------------------------+




SetEquityIndexOptionSecurityPointValue

......................................
SetEquityIndexOptionSecurityPointValue
......................................


Updates the point value of an equity index option security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+----------+-------------------------------------------+
| Parameter                 | Required | Description                               |
+===========================+==========+===========================================+
| equityIndexOptionSecurity | Yes      | An equity index option security to update |
+---------------------------+----------+-------------------------------------------+
| pointValue                | Yes      | The point value                           |
+---------------------------+----------+-------------------------------------------+




SetEquityIndexOptionSecurityStrike

..................................
SetEquityIndexOptionSecurityStrike
..................................


Updates the strike of an equity index option security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+----------+-------------------------------------------+
| Parameter                 | Required | Description                               |
+===========================+==========+===========================================+
| equityIndexOptionSecurity | Yes      | An equity index option security to update |
+---------------------------+----------+-------------------------------------------+
| strike                    | Yes      | The strike                                |
+---------------------------+----------+-------------------------------------------+




SetEquityIndexOptionSecurityUnderlyingId

........................................
SetEquityIndexOptionSecurityUnderlyingId
........................................


Updates the identifier of the underlying identifier of an equity index option security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+----------+---------------------------------------------+
| Parameter                 | Required | Description                                 |
+===========================+==========+=============================================+
| equityIndexOptionSecurity | Yes      | An equity index option security to update   |
+---------------------------+----------+---------------------------------------------+
| underlyingId              |          | The identifier of the underlying identifier |
+---------------------------+----------+---------------------------------------------+




SetEquityOptionSecurityCurrency

...............................
SetEquityOptionSecurityCurrency
...............................


Updates the currency of an equity option security. The original object is unchanged - a new object is returned with the updated value.



+----------------------+----------+-------------------------------------+
| Parameter            | Required | Description                         |
+======================+==========+=====================================+
| equityOptionSecurity | Yes      | An equity option security to update |
+----------------------+----------+-------------------------------------+
| currency             |          | The currency                        |
+----------------------+----------+-------------------------------------+




SetEquityOptionSecurityExchange

...............................
SetEquityOptionSecurityExchange
...............................


Updates the exchange of an equity option security. The original object is unchanged - a new object is returned with the updated value.



+----------------------+----------+-------------------------------------+
| Parameter            | Required | Description                         |
+======================+==========+=====================================+
| equityOptionSecurity | Yes      | An equity option security to update |
+----------------------+----------+-------------------------------------+
| exchange             |          | The exchange                        |
+----------------------+----------+-------------------------------------+




SetEquityOptionSecurityExerciseType

...................................
SetEquityOptionSecurityExerciseType
...................................


Updates the exercise type of an equity option security. The original object is unchanged - a new object is returned with the updated value.



+----------------------+----------+-------------------------------------+
| Parameter            | Required | Description                         |
+======================+==========+=====================================+
| equityOptionSecurity | Yes      | An equity option security to update |
+----------------------+----------+-------------------------------------+
| exerciseType         |          | The exercise type                   |
+----------------------+----------+-------------------------------------+




SetEquityOptionSecurityExpiry

.............................
SetEquityOptionSecurityExpiry
.............................


Updates the expiry of an equity option security. The original object is unchanged - a new object is returned with the updated value.



+----------------------+----------+-------------------------------------+
| Parameter            | Required | Description                         |
+======================+==========+=====================================+
| equityOptionSecurity | Yes      | An equity option security to update |
+----------------------+----------+-------------------------------------+
| expiry               |          | The expiry                          |
+----------------------+----------+-------------------------------------+




SetEquityOptionSecurityOptionType

.................................
SetEquityOptionSecurityOptionType
.................................


Updates the type of option (PUT or CALL) of an equity option security. The original object is unchanged - a new object is returned with the updated value.



+----------------------+----------+-------------------------------------+
| Parameter            | Required | Description                         |
+======================+==========+=====================================+
| equityOptionSecurity | Yes      | An equity option security to update |
+----------------------+----------+-------------------------------------+
| optionType           |          | The type of option (PUT or CALL)    |
+----------------------+----------+-------------------------------------+




SetEquityOptionSecurityPointValue

.................................
SetEquityOptionSecurityPointValue
.................................


Updates the point value of an equity option security. The original object is unchanged - a new object is returned with the updated value.



+----------------------+----------+-------------------------------------+
| Parameter            | Required | Description                         |
+======================+==========+=====================================+
| equityOptionSecurity | Yes      | An equity option security to update |
+----------------------+----------+-------------------------------------+
| pointValue           | Yes      | The point value                     |
+----------------------+----------+-------------------------------------+




SetEquityOptionSecurityStrike

.............................
SetEquityOptionSecurityStrike
.............................


Updates the strike of an equity option security. The original object is unchanged - a new object is returned with the updated value.



+----------------------+----------+-------------------------------------+
| Parameter            | Required | Description                         |
+======================+==========+=====================================+
| equityOptionSecurity | Yes      | An equity option security to update |
+----------------------+----------+-------------------------------------+
| strike               | Yes      | The strike                          |
+----------------------+----------+-------------------------------------+




SetEquityOptionSecurityUnderlyingId

...................................
SetEquityOptionSecurityUnderlyingId
...................................


Updates the identifier of the underlying security of an equity option security. The original object is unchanged - a new object is returned with the updated value.



+----------------------+----------+-------------------------------------------+
| Parameter            | Required | Description                               |
+======================+==========+===========================================+
| equityOptionSecurity | Yes      | An equity option security to update       |
+----------------------+----------+-------------------------------------------+
| underlyingId         |          | The identifier of the underlying security |
+----------------------+----------+-------------------------------------------+




SetEquitySecurityCompanyName

............................
SetEquitySecurityCompanyName
............................


Updates the company name of an equity security. The original object is unchanged - a new object is returned with the updated value.



+----------------+----------+------------------------------+
| Parameter      | Required | Description                  |
+================+==========+==============================+
| equitySecurity | Yes      | An equity security to update |
+----------------+----------+------------------------------+
| companyName    |          | The company name             |
+----------------+----------+------------------------------+




SetEquitySecurityCurrency

.........................
SetEquitySecurityCurrency
.........................


Updates the currency of an equity security. The original object is unchanged - a new object is returned with the updated value.



+----------------+----------+------------------------------+
| Parameter      | Required | Description                  |
+================+==========+==============================+
| equitySecurity | Yes      | An equity security to update |
+----------------+----------+------------------------------+
| currency       |          | The currency                 |
+----------------+----------+------------------------------+




SetEquitySecurityExchange

.........................
SetEquitySecurityExchange
.........................


Updates the exchange name of an equity security. The original object is unchanged - a new object is returned with the updated value.



+----------------+----------+------------------------------+
| Parameter      | Required | Description                  |
+================+==========+==============================+
| equitySecurity | Yes      | An equity security to update |
+----------------+----------+------------------------------+
| exchange       |          | The exchange name            |
+----------------+----------+------------------------------+




SetEquitySecurityExchangeCode

.............................
SetEquitySecurityExchangeCode
.............................


Updates the exchange code of an equity security. The original object is unchanged - a new object is returned with the updated value.



+----------------+----------+------------------------------+
| Parameter      | Required | Description                  |
+================+==========+==============================+
| equitySecurity | Yes      | An equity security to update |
+----------------+----------+------------------------------+
| exchangeCode   |          | The exchange code            |
+----------------+----------+------------------------------+




SetEquitySecurityGicsCode

.........................
SetEquitySecurityGicsCode
.........................


Updates the GICS code of an equity security. The original object is unchanged - a new object is returned with the updated value.



+----------------+----------+------------------------------+
| Parameter      | Required | Description                  |
+================+==========+==============================+
| equitySecurity | Yes      | An equity security to update |
+----------------+----------+------------------------------+
| gicsCode       |          | The GICS code                |
+----------------+----------+------------------------------+




SetEquitySecurityShortName

..........................
SetEquitySecurityShortName
..........................


Updates the short name of an equity security. The original object is unchanged - a new object is returned with the updated value.



+----------------+----------+------------------------------+
| Parameter      | Required | Description                  |
+================+==========+==============================+
| equitySecurity | Yes      | An equity security to update |
+----------------+----------+------------------------------+
| shortName      |          | The short name               |
+----------------+----------+------------------------------+




SetEquityVarianceSwapSecurityAnnualizationFactor

................................................
SetEquityVarianceSwapSecurityAnnualizationFactor
................................................


Updates the annualization factor of an equity variance swap security. The original object is unchanged - a new object is returned with the updated value.



+----------------------------+----------+--------------------------------------------+
| Parameter                  | Required | Description                                |
+============================+==========+============================================+
| equityVarianceSwapSecurity | Yes      | An equity variance swap security to update |
+----------------------------+----------+--------------------------------------------+
| annualizationFactor        | Yes      | The annualization factor                   |
+----------------------------+----------+--------------------------------------------+




SetEquityVarianceSwapSecurityCurrency

.....................................
SetEquityVarianceSwapSecurityCurrency
.....................................


Updates the currency of an equity variance swap security. The original object is unchanged - a new object is returned with the updated value.



+----------------------------+----------+--------------------------------------------+
| Parameter                  | Required | Description                                |
+============================+==========+============================================+
| equityVarianceSwapSecurity | Yes      | An equity variance swap security to update |
+----------------------------+----------+--------------------------------------------+
| currency                   |          | The currency                               |
+----------------------------+----------+--------------------------------------------+




SetEquityVarianceSwapSecurityFirstObservationDate

.................................................
SetEquityVarianceSwapSecurityFirstObservationDate
.................................................


Updates the first observation date of an equity variance swap security. The original object is unchanged - a new object is returned with the updated value.



+----------------------------+----------+--------------------------------------------+
| Parameter                  | Required | Description                                |
+============================+==========+============================================+
| equityVarianceSwapSecurity | Yes      | An equity variance swap security to update |
+----------------------------+----------+--------------------------------------------+
| firstObservationDate       |          | The first observation date                 |
+----------------------------+----------+--------------------------------------------+




SetEquityVarianceSwapSecurityLastObservationDate

................................................
SetEquityVarianceSwapSecurityLastObservationDate
................................................


Updates the last observation date of an equity variance swap security. The original object is unchanged - a new object is returned with the updated value.



+----------------------------+----------+--------------------------------------------+
| Parameter                  | Required | Description                                |
+============================+==========+============================================+
| equityVarianceSwapSecurity | Yes      | An equity variance swap security to update |
+----------------------------+----------+--------------------------------------------+
| lastObservationDate        |          | The last observation date                  |
+----------------------------+----------+--------------------------------------------+




SetEquityVarianceSwapSecurityNotional

.....................................
SetEquityVarianceSwapSecurityNotional
.....................................


Updates the notional of an equity variance swap security. The original object is unchanged - a new object is returned with the updated value.



+----------------------------+----------+--------------------------------------------+
| Parameter                  | Required | Description                                |
+============================+==========+============================================+
| equityVarianceSwapSecurity | Yes      | An equity variance swap security to update |
+----------------------------+----------+--------------------------------------------+
| notional                   | Yes      | The notional                               |
+----------------------------+----------+--------------------------------------------+




SetEquityVarianceSwapSecurityObservationFrequency

.................................................
SetEquityVarianceSwapSecurityObservationFrequency
.................................................


Updates the observation frequency of an equity variance swap security. The original object is unchanged - a new object is returned with the updated value.



+----------------------------+----------+--------------------------------------------+
| Parameter                  | Required | Description                                |
+============================+==========+============================================+
| equityVarianceSwapSecurity | Yes      | An equity variance swap security to update |
+----------------------------+----------+--------------------------------------------+
| observationFrequency       |          | The observation frequency                  |
+----------------------------+----------+--------------------------------------------+




SetEquityVarianceSwapSecurityParameterizedAsVariance

....................................................
SetEquityVarianceSwapSecurityParameterizedAsVariance
....................................................


Updates the parameterized as variance of an equity variance swap security. The original object is unchanged - a new object is returned with the updated value.



+----------------------------+----------+--------------------------------------------+
| Parameter                  | Required | Description                                |
+============================+==========+============================================+
| equityVarianceSwapSecurity | Yes      | An equity variance swap security to update |
+----------------------------+----------+--------------------------------------------+
| parameterizedAsVariance    | Yes      | The parameterized as variance              |
+----------------------------+----------+--------------------------------------------+




SetEquityVarianceSwapSecurityRegionId

.....................................
SetEquityVarianceSwapSecurityRegionId
.....................................


Updates the region of an equity variance swap security. The original object is unchanged - a new object is returned with the updated value.



+----------------------------+----------+--------------------------------------------+
| Parameter                  | Required | Description                                |
+============================+==========+============================================+
| equityVarianceSwapSecurity | Yes      | An equity variance swap security to update |
+----------------------------+----------+--------------------------------------------+
| regionId                   |          | The region                                 |
+----------------------------+----------+--------------------------------------------+




SetEquityVarianceSwapSecuritySettlementDate

...........................................
SetEquityVarianceSwapSecuritySettlementDate
...........................................


Updates the settlement date of an equity variance swap security. The original object is unchanged - a new object is returned with the updated value.



+----------------------------+----------+--------------------------------------------+
| Parameter                  | Required | Description                                |
+============================+==========+============================================+
| equityVarianceSwapSecurity | Yes      | An equity variance swap security to update |
+----------------------------+----------+--------------------------------------------+
| settlementDate             |          | The settlement date                        |
+----------------------------+----------+--------------------------------------------+




SetEquityVarianceSwapSecuritySpotUnderlyingId

.............................................
SetEquityVarianceSwapSecuritySpotUnderlyingId
.............................................


Updates the underlying identifier of an equity variance swap security. The original object is unchanged - a new object is returned with the updated value.



+----------------------------+----------+--------------------------------------------+
| Parameter                  | Required | Description                                |
+============================+==========+============================================+
| equityVarianceSwapSecurity | Yes      | An equity variance swap security to update |
+----------------------------+----------+--------------------------------------------+
| spotUnderlyingId           |          | The underlying identifier                  |
+----------------------------+----------+--------------------------------------------+




SetEquityVarianceSwapSecurityStrike

...................................
SetEquityVarianceSwapSecurityStrike
...................................


Updates the strike of an equity variance swap security. The original object is unchanged - a new object is returned with the updated value.



+----------------------------+----------+--------------------------------------------+
| Parameter                  | Required | Description                                |
+============================+==========+============================================+
| equityVarianceSwapSecurity | Yes      | An equity variance swap security to update |
+----------------------------+----------+--------------------------------------------+
| strike                     | Yes      | The strike                                 |
+----------------------------+----------+--------------------------------------------+



