title: Excel Functions - E
shortcut: DOC:Excel Functions - E
---
EnableCycleAccess

.................
EnableCycleAccess
.................


Defines an enable cycle access configuration item.

This function takes no parameters.

EnergyFutureSecurity

....................
EnergyFutureSecurity
....................


Defines an energy future security. The new security is added to the Security Master and an identifier to it returned.



+--------------------+-------------------------------------------+
| Parameter          | Description                               |
+====================+===========================================+
| name               | The display name or label of the security |
+--------------------+-------------------------------------------+
| expiry             | The expiry date                           |
+--------------------+-------------------------------------------+
| tradingExchange    | The trading exchange                      |
+--------------------+-------------------------------------------+
| settlementExchange | The settlement exchange                   |
+--------------------+-------------------------------------------+
| currency           | The currency                              |
+--------------------+-------------------------------------------+
| unitAmount         | The unit amount                           |
+--------------------+-------------------------------------------+
| contractCategory   | The category                              |
+--------------------+-------------------------------------------+



EnergyFutureSecurityObject

..........................
EnergyFutureSecurityObject
..........................


Defines an energy future security.



+--------------------+-------------------------------------------+
| Parameter          | Description                               |
+====================+===========================================+
| name               | The display name or label of the security |
+--------------------+-------------------------------------------+
| expiry             | The expiry date                           |
+--------------------+-------------------------------------------+
| tradingExchange    | The trading exchange                      |
+--------------------+-------------------------------------------+
| settlementExchange | The settlement exchange                   |
+--------------------+-------------------------------------------+
| currency           | The currency                              |
+--------------------+-------------------------------------------+
| unitAmount         | The unit amount                           |
+--------------------+-------------------------------------------+
| contractCategory   | The category                              |
+--------------------+-------------------------------------------+



EnergyFutureSecuritySetUnderlyingId

...................................
EnergyFutureSecuritySetUnderlyingId
...................................


Updates the underlying identifier  of an energy future security. The original object is unchanged - a new object is returned with the updated value.



+----------------------+-------------------------------------+
| Parameter            | Description                         |
+======================+=====================================+
| energyFutureSecurity | An energy future security to update |
+----------------------+-------------------------------------+
| underlyingId         | The underlying identifier           |
+----------------------+-------------------------------------+



EnergyFutureSecurityUnderlyingId

................................
EnergyFutureSecurityUnderlyingId
................................


Returns the underlying identifier  from an energy future security.



+----------------------+------------------------------------+
| Parameter            | Description                        |
+======================+====================================+
| energyFutureSecurity | An energy future security to query |
+----------------------+------------------------------------+



EngineJobResult

...............
EngineJobResult
...............


Obtains a result from an engine job, delivering the result asynchronously when it becomes available.



+------------+---------------------------------------------------+
| Parameter  | Description                                       |
+============+===================================================+
| jobId      | The identifier of the job                         |
+------------+---------------------------------------------------+
| cycleIndex | The index of the cycle whose snapshot is required |
+------------+---------------------------------------------------+



This function is capable of live operation. If automatic sheet calculation is enabled it may return new values pushed from the OpenGamma Platform even if the input parameters have not changed.

This function is available on a worksheet only and cannot be used from Visual Basic.

EngineJobStatus

...............
EngineJobStatus
...............


Displays the up-to-date status of an engine job.



+-----------+---------------------------+
| Parameter | Description               |
+===========+===========================+
| jobId     | The identifier of the job |
+-----------+---------------------------+



This function is capable of live operation. If automatic sheet calculation is enabled it may return new values pushed from the OpenGamma Platform even if the input parameters have not changed.

This function is available on a worksheet only and cannot be used from Visual Basic.

EquityBarrierOptionSecurity

...........................
EquityBarrierOptionSecurity
...........................


Defines an equity barrier option security. The new security is added to the Security Master and an identifier to it returned.



+-------------------+-------------------------------------------+
| Parameter         | Description                               |
+===================+===========================================+
| name              | The display name or label of the security |
+-------------------+-------------------------------------------+
| optionType        | The type of option (PUT or CALL)          |
+-------------------+-------------------------------------------+
| strike            | The strike                                |
+-------------------+-------------------------------------------+
| currency          | The currency                              |
+-------------------+-------------------------------------------+
| underlyingId      | The identifier of the underlying security |
+-------------------+-------------------------------------------+
| exerciseType      | The exercise type                         |
+-------------------+-------------------------------------------+
| expiry            | The expiry                                |
+-------------------+-------------------------------------------+
| pointValue        | The point value                           |
+-------------------+-------------------------------------------+
| exchange          | The exchange                              |
+-------------------+-------------------------------------------+
| barrierType       | The barrier type                          |
+-------------------+-------------------------------------------+
| barrierDirection  | The barrier direction                     |
+-------------------+-------------------------------------------+
| monitoringType    | The monitoringType                        |
+-------------------+-------------------------------------------+
| samplingFrequency | The sampling frequency                    |
+-------------------+-------------------------------------------+
| barrierLevel      | The barrier level                         |
+-------------------+-------------------------------------------+



EquityBarrierOptionSecurityBarrierDirection

...........................................
EquityBarrierOptionSecurityBarrierDirection
...........................................


Returns the barrier direction from an equity barrier option security.



+-----------------------------+--------------------------------------------+
| Parameter                   | Description                                |
+=============================+============================================+
| equityBarrierOptionSecurity | An equity barrier option security to query |
+-----------------------------+--------------------------------------------+



EquityBarrierOptionSecurityBarrierLevel

.......................................
EquityBarrierOptionSecurityBarrierLevel
.......................................


Returns the barrier level from an equity barrier option security.



+-----------------------------+--------------------------------------------+
| Parameter                   | Description                                |
+=============================+============================================+
| equityBarrierOptionSecurity | An equity barrier option security to query |
+-----------------------------+--------------------------------------------+



EquityBarrierOptionSecurityBarrierType

......................................
EquityBarrierOptionSecurityBarrierType
......................................


Returns the barrier type from an equity barrier option security.



+-----------------------------+--------------------------------------------+
| Parameter                   | Description                                |
+=============================+============================================+
| equityBarrierOptionSecurity | An equity barrier option security to query |
+-----------------------------+--------------------------------------------+



EquityBarrierOptionSecurityCurrency

...................................
EquityBarrierOptionSecurityCurrency
...................................


Returns the currency from an equity barrier option security.



+-----------------------------+--------------------------------------------+
| Parameter                   | Description                                |
+=============================+============================================+
| equityBarrierOptionSecurity | An equity barrier option security to query |
+-----------------------------+--------------------------------------------+



EquityBarrierOptionSecurityExchange

...................................
EquityBarrierOptionSecurityExchange
...................................


Returns the exchange from an equity barrier option security.



+-----------------------------+--------------------------------------------+
| Parameter                   | Description                                |
+=============================+============================================+
| equityBarrierOptionSecurity | An equity barrier option security to query |
+-----------------------------+--------------------------------------------+



EquityBarrierOptionSecurityExerciseType

.......................................
EquityBarrierOptionSecurityExerciseType
.......................................


Returns the exercise type from an equity barrier option security.



+-----------------------------+--------------------------------------------+
| Parameter                   | Description                                |
+=============================+============================================+
| equityBarrierOptionSecurity | An equity barrier option security to query |
+-----------------------------+--------------------------------------------+



EquityBarrierOptionSecurityExpiry

.................................
EquityBarrierOptionSecurityExpiry
.................................


Returns the expiry from an equity barrier option security.



+-----------------------------+--------------------------------------------+
| Parameter                   | Description                                |
+=============================+============================================+
| equityBarrierOptionSecurity | An equity barrier option security to query |
+-----------------------------+--------------------------------------------+



EquityBarrierOptionSecurityMonitoringType

.........................................
EquityBarrierOptionSecurityMonitoringType
.........................................


Returns the monitoringType from an equity barrier option security.



+-----------------------------+--------------------------------------------+
| Parameter                   | Description                                |
+=============================+============================================+
| equityBarrierOptionSecurity | An equity barrier option security to query |
+-----------------------------+--------------------------------------------+



EquityBarrierOptionSecurityObject

.................................
EquityBarrierOptionSecurityObject
.................................


Defines an equity barrier option security.



+-------------------+-------------------------------------------+
| Parameter         | Description                               |
+===================+===========================================+
| name              | The display name or label of the security |
+-------------------+-------------------------------------------+
| optionType        | The type of option (PUT or CALL)          |
+-------------------+-------------------------------------------+
| strike            | The strike                                |
+-------------------+-------------------------------------------+
| currency          | The currency                              |
+-------------------+-------------------------------------------+
| underlyingId      | The identifier of the underlying security |
+-------------------+-------------------------------------------+
| exerciseType      | The exercise type                         |
+-------------------+-------------------------------------------+
| expiry            | The expiry                                |
+-------------------+-------------------------------------------+
| pointValue        | The point value                           |
+-------------------+-------------------------------------------+
| exchange          | The exchange                              |
+-------------------+-------------------------------------------+
| barrierType       | The barrier type                          |
+-------------------+-------------------------------------------+
| barrierDirection  | The barrier direction                     |
+-------------------+-------------------------------------------+
| monitoringType    | The monitoringType                        |
+-------------------+-------------------------------------------+
| samplingFrequency | The sampling frequency                    |
+-------------------+-------------------------------------------+
| barrierLevel      | The barrier level                         |
+-------------------+-------------------------------------------+



EquityBarrierOptionSecurityOptionType

.....................................
EquityBarrierOptionSecurityOptionType
.....................................


Returns the type of option (PUT or CALL) from an equity barrier option security.



+-----------------------------+--------------------------------------------+
| Parameter                   | Description                                |
+=============================+============================================+
| equityBarrierOptionSecurity | An equity barrier option security to query |
+-----------------------------+--------------------------------------------+



EquityBarrierOptionSecurityPointValue

.....................................
EquityBarrierOptionSecurityPointValue
.....................................


Returns the point value from an equity barrier option security.



+-----------------------------+--------------------------------------------+
| Parameter                   | Description                                |
+=============================+============================================+
| equityBarrierOptionSecurity | An equity barrier option security to query |
+-----------------------------+--------------------------------------------+



EquityBarrierOptionSecuritySamplingFrequency

............................................
EquityBarrierOptionSecuritySamplingFrequency
............................................


Returns the sampling frequency from an equity barrier option security.



+-----------------------------+--------------------------------------------+
| Parameter                   | Description                                |
+=============================+============================================+
| equityBarrierOptionSecurity | An equity barrier option security to query |
+-----------------------------+--------------------------------------------+



EquityBarrierOptionSecuritySetBarrierDirection

..............................................
EquityBarrierOptionSecuritySetBarrierDirection
..............................................


Updates the barrier direction of an equity barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-----------------------------+---------------------------------------------+
| Parameter                   | Description                                 |
+=============================+=============================================+
| equityBarrierOptionSecurity | An equity barrier option security to update |
+-----------------------------+---------------------------------------------+
| barrierDirection            | The barrier direction                       |
+-----------------------------+---------------------------------------------+



EquityBarrierOptionSecuritySetBarrierLevel

..........................................
EquityBarrierOptionSecuritySetBarrierLevel
..........................................


Updates the barrier level of an equity barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-----------------------------+---------------------------------------------+
| Parameter                   | Description                                 |
+=============================+=============================================+
| equityBarrierOptionSecurity | An equity barrier option security to update |
+-----------------------------+---------------------------------------------+
| barrierLevel                | The barrier level                           |
+-----------------------------+---------------------------------------------+



EquityBarrierOptionSecuritySetBarrierType

.........................................
EquityBarrierOptionSecuritySetBarrierType
.........................................


Updates the barrier type of an equity barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-----------------------------+---------------------------------------------+
| Parameter                   | Description                                 |
+=============================+=============================================+
| equityBarrierOptionSecurity | An equity barrier option security to update |
+-----------------------------+---------------------------------------------+
| barrierType                 | The barrier type                            |
+-----------------------------+---------------------------------------------+



EquityBarrierOptionSecuritySetCurrency

......................................
EquityBarrierOptionSecuritySetCurrency
......................................


Updates the currency of an equity barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-----------------------------+---------------------------------------------+
| Parameter                   | Description                                 |
+=============================+=============================================+
| equityBarrierOptionSecurity | An equity barrier option security to update |
+-----------------------------+---------------------------------------------+
| currency                    | The currency                                |
+-----------------------------+---------------------------------------------+



EquityBarrierOptionSecuritySetExchange

......................................
EquityBarrierOptionSecuritySetExchange
......................................


Updates the exchange of an equity barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-----------------------------+---------------------------------------------+
| Parameter                   | Description                                 |
+=============================+=============================================+
| equityBarrierOptionSecurity | An equity barrier option security to update |
+-----------------------------+---------------------------------------------+
| exchange                    | The exchange                                |
+-----------------------------+---------------------------------------------+



EquityBarrierOptionSecuritySetExerciseType

..........................................
EquityBarrierOptionSecuritySetExerciseType
..........................................


Updates the exercise type of an equity barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-----------------------------+---------------------------------------------+
| Parameter                   | Description                                 |
+=============================+=============================================+
| equityBarrierOptionSecurity | An equity barrier option security to update |
+-----------------------------+---------------------------------------------+
| exerciseType                | The exercise type                           |
+-----------------------------+---------------------------------------------+



EquityBarrierOptionSecuritySetExpiry

....................................
EquityBarrierOptionSecuritySetExpiry
....................................


Updates the expiry of an equity barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-----------------------------+---------------------------------------------+
| Parameter                   | Description                                 |
+=============================+=============================================+
| equityBarrierOptionSecurity | An equity barrier option security to update |
+-----------------------------+---------------------------------------------+
| expiry                      | The expiry                                  |
+-----------------------------+---------------------------------------------+



EquityBarrierOptionSecuritySetMonitoringType

............................................
EquityBarrierOptionSecuritySetMonitoringType
............................................


Updates the monitoringType of an equity barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-----------------------------+---------------------------------------------+
| Parameter                   | Description                                 |
+=============================+=============================================+
| equityBarrierOptionSecurity | An equity barrier option security to update |
+-----------------------------+---------------------------------------------+
| monitoringType              | The monitoringType                          |
+-----------------------------+---------------------------------------------+



EquityBarrierOptionSecuritySetOptionType

........................................
EquityBarrierOptionSecuritySetOptionType
........................................


Updates the type of option (PUT or CALL) of an equity barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-----------------------------+---------------------------------------------+
| Parameter                   | Description                                 |
+=============================+=============================================+
| equityBarrierOptionSecurity | An equity barrier option security to update |
+-----------------------------+---------------------------------------------+
| optionType                  | The type of option (PUT or CALL)            |
+-----------------------------+---------------------------------------------+



EquityBarrierOptionSecuritySetPointValue

........................................
EquityBarrierOptionSecuritySetPointValue
........................................


Updates the point value of an equity barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-----------------------------+---------------------------------------------+
| Parameter                   | Description                                 |
+=============================+=============================================+
| equityBarrierOptionSecurity | An equity barrier option security to update |
+-----------------------------+---------------------------------------------+
| pointValue                  | The point value                             |
+-----------------------------+---------------------------------------------+



EquityBarrierOptionSecuritySetSamplingFrequency

...............................................
EquityBarrierOptionSecuritySetSamplingFrequency
...............................................


Updates the sampling frequency of an equity barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-----------------------------+---------------------------------------------+
| Parameter                   | Description                                 |
+=============================+=============================================+
| equityBarrierOptionSecurity | An equity barrier option security to update |
+-----------------------------+---------------------------------------------+
| samplingFrequency           | The sampling frequency                      |
+-----------------------------+---------------------------------------------+



EquityBarrierOptionSecuritySetStrike

....................................
EquityBarrierOptionSecuritySetStrike
....................................


Updates the strike of an equity barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-----------------------------+---------------------------------------------+
| Parameter                   | Description                                 |
+=============================+=============================================+
| equityBarrierOptionSecurity | An equity barrier option security to update |
+-----------------------------+---------------------------------------------+
| strike                      | The strike                                  |
+-----------------------------+---------------------------------------------+



EquityBarrierOptionSecuritySetUnderlyingId

..........................................
EquityBarrierOptionSecuritySetUnderlyingId
..........................................


Updates the identifier of the underlying security of an equity barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-----------------------------+---------------------------------------------+
| Parameter                   | Description                                 |
+=============================+=============================================+
| equityBarrierOptionSecurity | An equity barrier option security to update |
+-----------------------------+---------------------------------------------+
| underlyingId                | The identifier of the underlying security   |
+-----------------------------+---------------------------------------------+



EquityBarrierOptionSecurityStrike

.................................
EquityBarrierOptionSecurityStrike
.................................


Returns the strike from an equity barrier option security.



+-----------------------------+--------------------------------------------+
| Parameter                   | Description                                |
+=============================+============================================+
| equityBarrierOptionSecurity | An equity barrier option security to query |
+-----------------------------+--------------------------------------------+



EquityBarrierOptionSecurityUnderlyingId

.......................................
EquityBarrierOptionSecurityUnderlyingId
.......................................


Returns the identifier of the underlying security from an equity barrier option security.



+-----------------------------+--------------------------------------------+
| Parameter                   | Description                                |
+=============================+============================================+
| equityBarrierOptionSecurity | An equity barrier option security to query |
+-----------------------------+--------------------------------------------+



EquityFutureSecurity

....................
EquityFutureSecurity
....................


Defines an equity future security. The new security is added to the Security Master and an identifier to it returned.



+--------------------+-------------------------------------------+
| Parameter          | Description                               |
+====================+===========================================+
| name               | The display name or label of the security |
+--------------------+-------------------------------------------+
| expiry             | The expiry date                           |
+--------------------+-------------------------------------------+
| tradingExchange    | The trading exchange                      |
+--------------------+-------------------------------------------+
| settlementExchange | The settlement exchange                   |
+--------------------+-------------------------------------------+
| currency           | The currency                              |
+--------------------+-------------------------------------------+
| unitAmount         | The unit amount                           |
+--------------------+-------------------------------------------+
| settlementDate     | The settlement date                       |
+--------------------+-------------------------------------------+
| underlyingId       | The underlying identifier                 |
+--------------------+-------------------------------------------+
| contractCategory   | The category                              |
+--------------------+-------------------------------------------+



EquityFutureSecurityObject

..........................
EquityFutureSecurityObject
..........................


Defines an equity future security.



+--------------------+-------------------------------------------+
| Parameter          | Description                               |
+====================+===========================================+
| name               | The display name or label of the security |
+--------------------+-------------------------------------------+
| expiry             | The expiry date                           |
+--------------------+-------------------------------------------+
| tradingExchange    | The trading exchange                      |
+--------------------+-------------------------------------------+
| settlementExchange | The settlement exchange                   |
+--------------------+-------------------------------------------+
| currency           | The currency                              |
+--------------------+-------------------------------------------+
| unitAmount         | The unit amount                           |
+--------------------+-------------------------------------------+
| settlementDate     | The settlement date                       |
+--------------------+-------------------------------------------+
| underlyingId       | The underlying identifier                 |
+--------------------+-------------------------------------------+
| contractCategory   | The category                              |
+--------------------+-------------------------------------------+



EquityFutureSecuritySetSettlementDate

.....................................
EquityFutureSecuritySetSettlementDate
.....................................


Updates the settlement date  of an equity future security. The original object is unchanged - a new object is returned with the updated value.



+----------------------+-------------------------------------+
| Parameter            | Description                         |
+======================+=====================================+
| equityFutureSecurity | An equity future security to update |
+----------------------+-------------------------------------+
| settlementDate       | The settlement date                 |
+----------------------+-------------------------------------+



EquityFutureSecuritySetUnderlyingId

...................................
EquityFutureSecuritySetUnderlyingId
...................................


Updates the underlying identifier of an equity future security. The original object is unchanged - a new object is returned with the updated value.



+----------------------+-------------------------------------+
| Parameter            | Description                         |
+======================+=====================================+
| equityFutureSecurity | An equity future security to update |
+----------------------+-------------------------------------+
| underlyingId         | The underlying identifier           |
+----------------------+-------------------------------------+



EquityFutureSecuritySettlementDate

..................................
EquityFutureSecuritySettlementDate
..................................


Returns the settlement date  from an equity future security.



+----------------------+------------------------------------+
| Parameter            | Description                        |
+======================+====================================+
| equityFutureSecurity | An equity future security to query |
+----------------------+------------------------------------+



EquityFutureSecurityUnderlyingId

................................
EquityFutureSecurityUnderlyingId
................................


Returns the underlying identifier from an equity future security.



+----------------------+------------------------------------+
| Parameter            | Description                        |
+======================+====================================+
| equityFutureSecurity | An equity future security to query |
+----------------------+------------------------------------+



EquityIndexDividendFutureSecurity

.................................
EquityIndexDividendFutureSecurity
.................................


Defines an equity index dividend future. The new security is added to the Security Master and an identifier to it returned.



+--------------------+-------------------------------------------+
| Parameter          | Description                               |
+====================+===========================================+
| name               | The display name or label of the security |
+--------------------+-------------------------------------------+
| expiry             | The expiry date                           |
+--------------------+-------------------------------------------+
| tradingExchange    | The trading exchange                      |
+--------------------+-------------------------------------------+
| settlementExchange | The settlement exchange                   |
+--------------------+-------------------------------------------+
| currency           | The currency                              |
+--------------------+-------------------------------------------+
| unitAmount         | The unit amount                           |
+--------------------+-------------------------------------------+
| settlementDate     | The settlement date                       |
+--------------------+-------------------------------------------+
| underlyingId       | The underlying identifier                 |
+--------------------+-------------------------------------------+
| contractCategory   | The category                              |
+--------------------+-------------------------------------------+



EquityIndexDividendFutureSecurityObject

.......................................
EquityIndexDividendFutureSecurityObject
.......................................


Defines an equity index dividend future.



+--------------------+-------------------------------------------+
| Parameter          | Description                               |
+====================+===========================================+
| name               | The display name or label of the security |
+--------------------+-------------------------------------------+
| expiry             | The expiry date                           |
+--------------------+-------------------------------------------+
| tradingExchange    | The trading exchange                      |
+--------------------+-------------------------------------------+
| settlementExchange | The settlement exchange                   |
+--------------------+-------------------------------------------+
| currency           | The currency                              |
+--------------------+-------------------------------------------+
| unitAmount         | The unit amount                           |
+--------------------+-------------------------------------------+
| settlementDate     | The settlement date                       |
+--------------------+-------------------------------------------+
| underlyingId       | The underlying identifier                 |
+--------------------+-------------------------------------------+
| contractCategory   | The category                              |
+--------------------+-------------------------------------------+



EquityIndexOptionSecurity

.........................
EquityIndexOptionSecurity
.........................


Defines an equity index option security. The new security is added to the Security Master and an identifier to it returned.



+--------------+---------------------------------------------+
| Parameter    | Description                                 |
+==============+=============================================+
| name         | The display name or label of the security   |
+--------------+---------------------------------------------+
| optionType   | The type of option (PUT or CALL)            |
+--------------+---------------------------------------------+
| strike       | The strike                                  |
+--------------+---------------------------------------------+
| currency     | The currency                                |
+--------------+---------------------------------------------+
| underlyingId | The identifier of the underlying identifier |
+--------------+---------------------------------------------+
| exerciseType | The exercise type                           |
+--------------+---------------------------------------------+
| expiry       | The expiry                                  |
+--------------+---------------------------------------------+
| pointValue   | The point value                             |
+--------------+---------------------------------------------+
| exchange     | The exchange                                |
+--------------+---------------------------------------------+



EquityIndexOptionSecurityCurrency

.................................
EquityIndexOptionSecurityCurrency
.................................


Returns the currency from an equity index option security.



+---------------------------+------------------------------------------+
| Parameter                 | Description                              |
+===========================+==========================================+
| equityIndexOptionSecurity | An equity index option security to query |
+---------------------------+------------------------------------------+



EquityIndexOptionSecurityExchange

.................................
EquityIndexOptionSecurityExchange
.................................


Returns the exchange from an equity index option security.



+---------------------------+------------------------------------------+
| Parameter                 | Description                              |
+===========================+==========================================+
| equityIndexOptionSecurity | An equity index option security to query |
+---------------------------+------------------------------------------+



EquityIndexOptionSecurityExerciseType

.....................................
EquityIndexOptionSecurityExerciseType
.....................................


Returns the exercise type from an equity index option security.



+---------------------------+------------------------------------------+
| Parameter                 | Description                              |
+===========================+==========================================+
| equityIndexOptionSecurity | An equity index option security to query |
+---------------------------+------------------------------------------+



EquityIndexOptionSecurityExpiry

...............................
EquityIndexOptionSecurityExpiry
...............................


Returns the expiry from an equity index option security.



+---------------------------+------------------------------------------+
| Parameter                 | Description                              |
+===========================+==========================================+
| equityIndexOptionSecurity | An equity index option security to query |
+---------------------------+------------------------------------------+



EquityIndexOptionSecurityObject

...............................
EquityIndexOptionSecurityObject
...............................


Defines an equity index option security.



+--------------+---------------------------------------------+
| Parameter    | Description                                 |
+==============+=============================================+
| name         | The display name or label of the security   |
+--------------+---------------------------------------------+
| optionType   | The type of option (PUT or CALL)            |
+--------------+---------------------------------------------+
| strike       | The strike                                  |
+--------------+---------------------------------------------+
| currency     | The currency                                |
+--------------+---------------------------------------------+
| underlyingId | The identifier of the underlying identifier |
+--------------+---------------------------------------------+
| exerciseType | The exercise type                           |
+--------------+---------------------------------------------+
| expiry       | The expiry                                  |
+--------------+---------------------------------------------+
| pointValue   | The point value                             |
+--------------+---------------------------------------------+
| exchange     | The exchange                                |
+--------------+---------------------------------------------+



EquityIndexOptionSecurityOptionType

...................................
EquityIndexOptionSecurityOptionType
...................................


Returns the type of option (PUT or CALL) from an equity index option security.



+---------------------------+------------------------------------------+
| Parameter                 | Description                              |
+===========================+==========================================+
| equityIndexOptionSecurity | An equity index option security to query |
+---------------------------+------------------------------------------+



EquityIndexOptionSecurityPointValue

...................................
EquityIndexOptionSecurityPointValue
...................................


Returns the point value from an equity index option security.



+---------------------------+------------------------------------------+
| Parameter                 | Description                              |
+===========================+==========================================+
| equityIndexOptionSecurity | An equity index option security to query |
+---------------------------+------------------------------------------+



EquityIndexOptionSecuritySetCurrency

....................................
EquityIndexOptionSecuritySetCurrency
....................................


Updates the currency of an equity index option security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+-------------------------------------------+
| Parameter                 | Description                               |
+===========================+===========================================+
| equityIndexOptionSecurity | An equity index option security to update |
+---------------------------+-------------------------------------------+
| currency                  | The currency                              |
+---------------------------+-------------------------------------------+



EquityIndexOptionSecuritySetExchange

....................................
EquityIndexOptionSecuritySetExchange
....................................


Updates the exchange of an equity index option security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+-------------------------------------------+
| Parameter                 | Description                               |
+===========================+===========================================+
| equityIndexOptionSecurity | An equity index option security to update |
+---------------------------+-------------------------------------------+
| exchange                  | The exchange                              |
+---------------------------+-------------------------------------------+



EquityIndexOptionSecuritySetExerciseType

........................................
EquityIndexOptionSecuritySetExerciseType
........................................


Updates the exercise type of an equity index option security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+-------------------------------------------+
| Parameter                 | Description                               |
+===========================+===========================================+
| equityIndexOptionSecurity | An equity index option security to update |
+---------------------------+-------------------------------------------+
| exerciseType              | The exercise type                         |
+---------------------------+-------------------------------------------+



EquityIndexOptionSecuritySetExpiry

..................................
EquityIndexOptionSecuritySetExpiry
..................................


Updates the expiry of an equity index option security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+-------------------------------------------+
| Parameter                 | Description                               |
+===========================+===========================================+
| equityIndexOptionSecurity | An equity index option security to update |
+---------------------------+-------------------------------------------+
| expiry                    | The expiry                                |
+---------------------------+-------------------------------------------+



EquityIndexOptionSecuritySetOptionType

......................................
EquityIndexOptionSecuritySetOptionType
......................................


Updates the type of option (PUT or CALL) of an equity index option security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+-------------------------------------------+
| Parameter                 | Description                               |
+===========================+===========================================+
| equityIndexOptionSecurity | An equity index option security to update |
+---------------------------+-------------------------------------------+
| optionType                | The type of option (PUT or CALL)          |
+---------------------------+-------------------------------------------+



EquityIndexOptionSecuritySetPointValue

......................................
EquityIndexOptionSecuritySetPointValue
......................................


Updates the point value of an equity index option security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+-------------------------------------------+
| Parameter                 | Description                               |
+===========================+===========================================+
| equityIndexOptionSecurity | An equity index option security to update |
+---------------------------+-------------------------------------------+
| pointValue                | The point value                           |
+---------------------------+-------------------------------------------+



EquityIndexOptionSecuritySetStrike

..................................
EquityIndexOptionSecuritySetStrike
..................................


Updates the strike of an equity index option security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+-------------------------------------------+
| Parameter                 | Description                               |
+===========================+===========================================+
| equityIndexOptionSecurity | An equity index option security to update |
+---------------------------+-------------------------------------------+
| strike                    | The strike                                |
+---------------------------+-------------------------------------------+



EquityIndexOptionSecuritySetUnderlyingId

........................................
EquityIndexOptionSecuritySetUnderlyingId
........................................


Updates the identifier of the underlying identifier of an equity index option security. The original object is unchanged - a new object is returned with the updated value.



+---------------------------+---------------------------------------------+
| Parameter                 | Description                                 |
+===========================+=============================================+
| equityIndexOptionSecurity | An equity index option security to update   |
+---------------------------+---------------------------------------------+
| underlyingId              | The identifier of the underlying identifier |
+---------------------------+---------------------------------------------+



EquityIndexOptionSecurityStrike

...............................
EquityIndexOptionSecurityStrike
...............................


Returns the strike from an equity index option security.



+---------------------------+------------------------------------------+
| Parameter                 | Description                              |
+===========================+==========================================+
| equityIndexOptionSecurity | An equity index option security to query |
+---------------------------+------------------------------------------+



EquityIndexOptionSecurityUnderlyingId

.....................................
EquityIndexOptionSecurityUnderlyingId
.....................................


Returns the identifier of the underlying identifier from an equity index option security.



+---------------------------+------------------------------------------+
| Parameter                 | Description                              |
+===========================+==========================================+
| equityIndexOptionSecurity | An equity index option security to query |
+---------------------------+------------------------------------------+



EquityOptionSecurity

....................
EquityOptionSecurity
....................


Defines an equity option security. The new security is added to the Security Master and an identifier to it returned.



+--------------+-------------------------------------------+
| Parameter    | Description                               |
+==============+===========================================+
| name         | The display name or label of the security |
+--------------+-------------------------------------------+
| optionType   | The type of option (PUT or CALL)          |
+--------------+-------------------------------------------+
| strike       | The strike                                |
+--------------+-------------------------------------------+
| currency     | The currency                              |
+--------------+-------------------------------------------+
| underlyingId | The identifier of the underlying security |
+--------------+-------------------------------------------+
| exerciseType | The exercise type                         |
+--------------+-------------------------------------------+
| expiry       | The expiry                                |
+--------------+-------------------------------------------+
| pointValue   | The point value                           |
+--------------+-------------------------------------------+
| exchange     | The exchange                              |
+--------------+-------------------------------------------+



EquityOptionSecurityCurrency

............................
EquityOptionSecurityCurrency
............................


Returns the currency from an equity option security.



+----------------------+------------------------------------+
| Parameter            | Description                        |
+======================+====================================+
| equityOptionSecurity | An equity option security to query |
+----------------------+------------------------------------+



EquityOptionSecurityExchange

............................
EquityOptionSecurityExchange
............................


Returns the exchange from an equity option security.



+----------------------+------------------------------------+
| Parameter            | Description                        |
+======================+====================================+
| equityOptionSecurity | An equity option security to query |
+----------------------+------------------------------------+



EquityOptionSecurityExerciseType

................................
EquityOptionSecurityExerciseType
................................


Returns the exercise type from an equity option security.



+----------------------+------------------------------------+
| Parameter            | Description                        |
+======================+====================================+
| equityOptionSecurity | An equity option security to query |
+----------------------+------------------------------------+



EquityOptionSecurityExpiry

..........................
EquityOptionSecurityExpiry
..........................


Returns the expiry from an equity option security.



+----------------------+------------------------------------+
| Parameter            | Description                        |
+======================+====================================+
| equityOptionSecurity | An equity option security to query |
+----------------------+------------------------------------+



EquityOptionSecurityObject

..........................
EquityOptionSecurityObject
..........................


Defines an equity option security.



+--------------+-------------------------------------------+
| Parameter    | Description                               |
+==============+===========================================+
| name         | The display name or label of the security |
+--------------+-------------------------------------------+
| optionType   | The type of option (PUT or CALL)          |
+--------------+-------------------------------------------+
| strike       | The strike                                |
+--------------+-------------------------------------------+
| currency     | The currency                              |
+--------------+-------------------------------------------+
| underlyingId | The identifier of the underlying security |
+--------------+-------------------------------------------+
| exerciseType | The exercise type                         |
+--------------+-------------------------------------------+
| expiry       | The expiry                                |
+--------------+-------------------------------------------+
| pointValue   | The point value                           |
+--------------+-------------------------------------------+
| exchange     | The exchange                              |
+--------------+-------------------------------------------+



EquityOptionSecurityOptionType

..............................
EquityOptionSecurityOptionType
..............................


Returns the type of option (PUT or CALL) from an equity option security.



+----------------------+------------------------------------+
| Parameter            | Description                        |
+======================+====================================+
| equityOptionSecurity | An equity option security to query |
+----------------------+------------------------------------+



EquityOptionSecurityPointValue

..............................
EquityOptionSecurityPointValue
..............................


Returns the point value from an equity option security.



+----------------------+------------------------------------+
| Parameter            | Description                        |
+======================+====================================+
| equityOptionSecurity | An equity option security to query |
+----------------------+------------------------------------+



EquityOptionSecuritySetCurrency

...............................
EquityOptionSecuritySetCurrency
...............................


Updates the currency of an equity option security. The original object is unchanged - a new object is returned with the updated value.



+----------------------+-------------------------------------+
| Parameter            | Description                         |
+======================+=====================================+
| equityOptionSecurity | An equity option security to update |
+----------------------+-------------------------------------+
| currency             | The currency                        |
+----------------------+-------------------------------------+



EquityOptionSecuritySetExchange

...............................
EquityOptionSecuritySetExchange
...............................


Updates the exchange of an equity option security. The original object is unchanged - a new object is returned with the updated value.



+----------------------+-------------------------------------+
| Parameter            | Description                         |
+======================+=====================================+
| equityOptionSecurity | An equity option security to update |
+----------------------+-------------------------------------+
| exchange             | The exchange                        |
+----------------------+-------------------------------------+



EquityOptionSecuritySetExerciseType

...................................
EquityOptionSecuritySetExerciseType
...................................


Updates the exercise type of an equity option security. The original object is unchanged - a new object is returned with the updated value.



+----------------------+-------------------------------------+
| Parameter            | Description                         |
+======================+=====================================+
| equityOptionSecurity | An equity option security to update |
+----------------------+-------------------------------------+
| exerciseType         | The exercise type                   |
+----------------------+-------------------------------------+



EquityOptionSecuritySetExpiry

.............................
EquityOptionSecuritySetExpiry
.............................


Updates the expiry of an equity option security. The original object is unchanged - a new object is returned with the updated value.



+----------------------+-------------------------------------+
| Parameter            | Description                         |
+======================+=====================================+
| equityOptionSecurity | An equity option security to update |
+----------------------+-------------------------------------+
| expiry               | The expiry                          |
+----------------------+-------------------------------------+



EquityOptionSecuritySetOptionType

.................................
EquityOptionSecuritySetOptionType
.................................


Updates the type of option (PUT or CALL) of an equity option security. The original object is unchanged - a new object is returned with the updated value.



+----------------------+-------------------------------------+
| Parameter            | Description                         |
+======================+=====================================+
| equityOptionSecurity | An equity option security to update |
+----------------------+-------------------------------------+
| optionType           | The type of option (PUT or CALL)    |
+----------------------+-------------------------------------+



EquityOptionSecuritySetPointValue

.................................
EquityOptionSecuritySetPointValue
.................................


Updates the point value of an equity option security. The original object is unchanged - a new object is returned with the updated value.



+----------------------+-------------------------------------+
| Parameter            | Description                         |
+======================+=====================================+
| equityOptionSecurity | An equity option security to update |
+----------------------+-------------------------------------+
| pointValue           | The point value                     |
+----------------------+-------------------------------------+



EquityOptionSecuritySetStrike

.............................
EquityOptionSecuritySetStrike
.............................


Updates the strike of an equity option security. The original object is unchanged - a new object is returned with the updated value.



+----------------------+-------------------------------------+
| Parameter            | Description                         |
+======================+=====================================+
| equityOptionSecurity | An equity option security to update |
+----------------------+-------------------------------------+
| strike               | The strike                          |
+----------------------+-------------------------------------+



EquityOptionSecuritySetUnderlyingId

...................................
EquityOptionSecuritySetUnderlyingId
...................................


Updates the identifier of the underlying security of an equity option security. The original object is unchanged - a new object is returned with the updated value.



+----------------------+-------------------------------------------+
| Parameter            | Description                               |
+======================+===========================================+
| equityOptionSecurity | An equity option security to update       |
+----------------------+-------------------------------------------+
| underlyingId         | The identifier of the underlying security |
+----------------------+-------------------------------------------+



EquityOptionSecurityStrike

..........................
EquityOptionSecurityStrike
..........................


Returns the strike from an equity option security.



+----------------------+------------------------------------+
| Parameter            | Description                        |
+======================+====================================+
| equityOptionSecurity | An equity option security to query |
+----------------------+------------------------------------+



EquityOptionSecurityUnderlyingId

................................
EquityOptionSecurityUnderlyingId
................................


Returns the identifier of the underlying security from an equity option security.



+----------------------+------------------------------------+
| Parameter            | Description                        |
+======================+====================================+
| equityOptionSecurity | An equity option security to query |
+----------------------+------------------------------------+



EquitySecurity

..............
EquitySecurity
..............


Defines an equity security. The new security is added to the Security Master and an identifier to it returned.



+--------------+-------------------------------------------+
| Parameter    | Description                               |
+==============+===========================================+
| name         | The display name or label of the security |
+--------------+-------------------------------------------+
| exchange     | The exchange name                         |
+--------------+-------------------------------------------+
| exchangeCode | The exchange code                         |
+--------------+-------------------------------------------+
| companyName  | The company name                          |
+--------------+-------------------------------------------+
| currency     | The currency                              |
+--------------+-------------------------------------------+



EquitySecurityCompanyName

.........................
EquitySecurityCompanyName
.........................


Returns the company name from an equity security.



+----------------+-----------------------------+
| Parameter      | Description                 |
+================+=============================+
| equitySecurity | An equity security to query |
+----------------+-----------------------------+



EquitySecurityCurrency

......................
EquitySecurityCurrency
......................


Returns the currency from an equity security.



+----------------+-----------------------------+
| Parameter      | Description                 |
+================+=============================+
| equitySecurity | An equity security to query |
+----------------+-----------------------------+



EquitySecurityExchange

......................
EquitySecurityExchange
......................


Returns the exchange name from an equity security.



+----------------+-----------------------------+
| Parameter      | Description                 |
+================+=============================+
| equitySecurity | An equity security to query |
+----------------+-----------------------------+



EquitySecurityExchangeCode

..........................
EquitySecurityExchangeCode
..........................


Returns the exchange code from an equity security.



+----------------+-----------------------------+
| Parameter      | Description                 |
+================+=============================+
| equitySecurity | An equity security to query |
+----------------+-----------------------------+



EquitySecurityGicsCode

......................
EquitySecurityGicsCode
......................


Returns the GICS code from an equity security.



+----------------+-----------------------------+
| Parameter      | Description                 |
+================+=============================+
| equitySecurity | An equity security to query |
+----------------+-----------------------------+



EquitySecurityObject

....................
EquitySecurityObject
....................


Defines an equity security.



+--------------+-------------------------------------------+
| Parameter    | Description                               |
+==============+===========================================+
| name         | The display name or label of the security |
+--------------+-------------------------------------------+
| exchange     | The exchange name                         |
+--------------+-------------------------------------------+
| exchangeCode | The exchange code                         |
+--------------+-------------------------------------------+
| companyName  | The company name                          |
+--------------+-------------------------------------------+
| currency     | The currency                              |
+--------------+-------------------------------------------+



EquitySecuritySetCompanyName

............................
EquitySecuritySetCompanyName
............................


Updates the company name of an equity security. The original object is unchanged - a new object is returned with the updated value.



+----------------+------------------------------+
| Parameter      | Description                  |
+================+==============================+
| equitySecurity | An equity security to update |
+----------------+------------------------------+
| companyName    | The company name             |
+----------------+------------------------------+



EquitySecuritySetCurrency

.........................
EquitySecuritySetCurrency
.........................


Updates the currency of an equity security. The original object is unchanged - a new object is returned with the updated value.



+----------------+------------------------------+
| Parameter      | Description                  |
+================+==============================+
| equitySecurity | An equity security to update |
+----------------+------------------------------+
| currency       | The currency                 |
+----------------+------------------------------+



EquitySecuritySetExchange

.........................
EquitySecuritySetExchange
.........................


Updates the exchange name of an equity security. The original object is unchanged - a new object is returned with the updated value.



+----------------+------------------------------+
| Parameter      | Description                  |
+================+==============================+
| equitySecurity | An equity security to update |
+----------------+------------------------------+
| exchange       | The exchange name            |
+----------------+------------------------------+



EquitySecuritySetExchangeCode

.............................
EquitySecuritySetExchangeCode
.............................


Updates the exchange code of an equity security. The original object is unchanged - a new object is returned with the updated value.



+----------------+------------------------------+
| Parameter      | Description                  |
+================+==============================+
| equitySecurity | An equity security to update |
+----------------+------------------------------+
| exchangeCode   | The exchange code            |
+----------------+------------------------------+



EquitySecuritySetGicsCode

.........................
EquitySecuritySetGicsCode
.........................


Updates the GICS code of an equity security. The original object is unchanged - a new object is returned with the updated value.



+----------------+------------------------------+
| Parameter      | Description                  |
+================+==============================+
| equitySecurity | An equity security to update |
+----------------+------------------------------+
| gicsCode       | The GICS code                |
+----------------+------------------------------+



EquitySecuritySetShortName

..........................
EquitySecuritySetShortName
..........................


Updates the short name of an equity security. The original object is unchanged - a new object is returned with the updated value.



+----------------+------------------------------+
| Parameter      | Description                  |
+================+==============================+
| equitySecurity | An equity security to update |
+----------------+------------------------------+
| shortName      | The short name               |
+----------------+------------------------------+



EquitySecurityShortName

.......................
EquitySecurityShortName
.......................


Returns the short name from an equity security.



+----------------+-----------------------------+
| Parameter      | Description                 |
+================+=============================+
| equitySecurity | An equity security to query |
+----------------+-----------------------------+



EquityVarianceSwapSecurity

..........................
EquityVarianceSwapSecurity
..........................


Defines an equity variance swap security. The new security is added to the Security Master and an identifier to it returned.



+-------------------------+-------------------------------------------+
| Parameter               | Description                               |
+=========================+===========================================+
| name                    | The display name or label of the security |
+-------------------------+-------------------------------------------+
| spotUnderlyingId        | The underlying identifier                 |
+-------------------------+-------------------------------------------+
| currency                | The currency                              |
+-------------------------+-------------------------------------------+
| strike                  | The strike                                |
+-------------------------+-------------------------------------------+
| notional                | The notional                              |
+-------------------------+-------------------------------------------+
| parameterizedAsVariance | The parameterized as variance             |
+-------------------------+-------------------------------------------+
| annualizationFactor     | The annualization factor                  |
+-------------------------+-------------------------------------------+
| firstObservationDate    | The first observation date                |
+-------------------------+-------------------------------------------+
| lastObservationDate     | The last observation date                 |
+-------------------------+-------------------------------------------+
| settlementDate          | The settlement date                       |
+-------------------------+-------------------------------------------+
| regionId                | The region                                |
+-------------------------+-------------------------------------------+
| observationFrequency    | The observation frequency                 |
+-------------------------+-------------------------------------------+



EquityVarianceSwapSecurityAnnualizationFactor

.............................................
EquityVarianceSwapSecurityAnnualizationFactor
.............................................


Returns the annualization factor from an equity variance swap security.



+----------------------------+-------------------------------------------+
| Parameter                  | Description                               |
+============================+===========================================+
| equityVarianceSwapSecurity | An equity variance swap security to query |
+----------------------------+-------------------------------------------+



EquityVarianceSwapSecurityCurrency

..................................
EquityVarianceSwapSecurityCurrency
..................................


Returns the currency from an equity variance swap security.



+----------------------------+-------------------------------------------+
| Parameter                  | Description                               |
+============================+===========================================+
| equityVarianceSwapSecurity | An equity variance swap security to query |
+----------------------------+-------------------------------------------+



EquityVarianceSwapSecurityFirstObservationDate

..............................................
EquityVarianceSwapSecurityFirstObservationDate
..............................................


Returns the first observation date from an equity variance swap security.



+----------------------------+-------------------------------------------+
| Parameter                  | Description                               |
+============================+===========================================+
| equityVarianceSwapSecurity | An equity variance swap security to query |
+----------------------------+-------------------------------------------+



EquityVarianceSwapSecurityLastObservationDate

.............................................
EquityVarianceSwapSecurityLastObservationDate
.............................................


Returns the last observation date from an equity variance swap security.



+----------------------------+-------------------------------------------+
| Parameter                  | Description                               |
+============================+===========================================+
| equityVarianceSwapSecurity | An equity variance swap security to query |
+----------------------------+-------------------------------------------+



EquityVarianceSwapSecurityNotional

..................................
EquityVarianceSwapSecurityNotional
..................................


Returns the notional from an equity variance swap security.



+----------------------------+-------------------------------------------+
| Parameter                  | Description                               |
+============================+===========================================+
| equityVarianceSwapSecurity | An equity variance swap security to query |
+----------------------------+-------------------------------------------+



EquityVarianceSwapSecurityObject

................................
EquityVarianceSwapSecurityObject
................................


Defines an equity variance swap security.



+-------------------------+-------------------------------------------+
| Parameter               | Description                               |
+=========================+===========================================+
| name                    | The display name or label of the security |
+-------------------------+-------------------------------------------+
| spotUnderlyingId        | The underlying identifier                 |
+-------------------------+-------------------------------------------+
| currency                | The currency                              |
+-------------------------+-------------------------------------------+
| strike                  | The strike                                |
+-------------------------+-------------------------------------------+
| notional                | The notional                              |
+-------------------------+-------------------------------------------+
| parameterizedAsVariance | The parameterized as variance             |
+-------------------------+-------------------------------------------+
| annualizationFactor     | The annualization factor                  |
+-------------------------+-------------------------------------------+
| firstObservationDate    | The first observation date                |
+-------------------------+-------------------------------------------+
| lastObservationDate     | The last observation date                 |
+-------------------------+-------------------------------------------+
| settlementDate          | The settlement date                       |
+-------------------------+-------------------------------------------+
| regionId                | The region                                |
+-------------------------+-------------------------------------------+
| observationFrequency    | The observation frequency                 |
+-------------------------+-------------------------------------------+



EquityVarianceSwapSecurityObservationFrequency

..............................................
EquityVarianceSwapSecurityObservationFrequency
..............................................


Returns the observation frequency from an equity variance swap security.



+----------------------------+-------------------------------------------+
| Parameter                  | Description                               |
+============================+===========================================+
| equityVarianceSwapSecurity | An equity variance swap security to query |
+----------------------------+-------------------------------------------+



EquityVarianceSwapSecurityParameterizedAsVariance

.................................................
EquityVarianceSwapSecurityParameterizedAsVariance
.................................................


Returns the parameterized as variance from an equity variance swap security.



+----------------------------+-------------------------------------------+
| Parameter                  | Description                               |
+============================+===========================================+
| equityVarianceSwapSecurity | An equity variance swap security to query |
+----------------------------+-------------------------------------------+



EquityVarianceSwapSecurityRegionId

..................................
EquityVarianceSwapSecurityRegionId
..................................


Returns the region from an equity variance swap security.



+----------------------------+-------------------------------------------+
| Parameter                  | Description                               |
+============================+===========================================+
| equityVarianceSwapSecurity | An equity variance swap security to query |
+----------------------------+-------------------------------------------+



EquityVarianceSwapSecuritySetAnnualizationFactor

................................................
EquityVarianceSwapSecuritySetAnnualizationFactor
................................................


Updates the annualization factor of an equity variance swap security. The original object is unchanged - a new object is returned with the updated value.



+----------------------------+--------------------------------------------+
| Parameter                  | Description                                |
+============================+============================================+
| equityVarianceSwapSecurity | An equity variance swap security to update |
+----------------------------+--------------------------------------------+
| annualizationFactor        | The annualization factor                   |
+----------------------------+--------------------------------------------+



EquityVarianceSwapSecuritySetCurrency

.....................................
EquityVarianceSwapSecuritySetCurrency
.....................................


Updates the currency of an equity variance swap security. The original object is unchanged - a new object is returned with the updated value.



+----------------------------+--------------------------------------------+
| Parameter                  | Description                                |
+============================+============================================+
| equityVarianceSwapSecurity | An equity variance swap security to update |
+----------------------------+--------------------------------------------+
| currency                   | The currency                               |
+----------------------------+--------------------------------------------+



EquityVarianceSwapSecuritySetFirstObservationDate

.................................................
EquityVarianceSwapSecuritySetFirstObservationDate
.................................................


Updates the first observation date of an equity variance swap security. The original object is unchanged - a new object is returned with the updated value.



+----------------------------+--------------------------------------------+
| Parameter                  | Description                                |
+============================+============================================+
| equityVarianceSwapSecurity | An equity variance swap security to update |
+----------------------------+--------------------------------------------+
| firstObservationDate       | The first observation date                 |
+----------------------------+--------------------------------------------+



EquityVarianceSwapSecuritySetLastObservationDate

................................................
EquityVarianceSwapSecuritySetLastObservationDate
................................................


Updates the last observation date of an equity variance swap security. The original object is unchanged - a new object is returned with the updated value.



+----------------------------+--------------------------------------------+
| Parameter                  | Description                                |
+============================+============================================+
| equityVarianceSwapSecurity | An equity variance swap security to update |
+----------------------------+--------------------------------------------+
| lastObservationDate        | The last observation date                  |
+----------------------------+--------------------------------------------+



EquityVarianceSwapSecuritySetNotional

.....................................
EquityVarianceSwapSecuritySetNotional
.....................................


Updates the notional of an equity variance swap security. The original object is unchanged - a new object is returned with the updated value.



+----------------------------+--------------------------------------------+
| Parameter                  | Description                                |
+============================+============================================+
| equityVarianceSwapSecurity | An equity variance swap security to update |
+----------------------------+--------------------------------------------+
| notional                   | The notional                               |
+----------------------------+--------------------------------------------+



EquityVarianceSwapSecuritySetObservationFrequency

.................................................
EquityVarianceSwapSecuritySetObservationFrequency
.................................................


Updates the observation frequency of an equity variance swap security. The original object is unchanged - a new object is returned with the updated value.



+----------------------------+--------------------------------------------+
| Parameter                  | Description                                |
+============================+============================================+
| equityVarianceSwapSecurity | An equity variance swap security to update |
+----------------------------+--------------------------------------------+
| observationFrequency       | The observation frequency                  |
+----------------------------+--------------------------------------------+



EquityVarianceSwapSecuritySetParameterizedAsVariance

....................................................
EquityVarianceSwapSecuritySetParameterizedAsVariance
....................................................


Updates the parameterized as variance of an equity variance swap security. The original object is unchanged - a new object is returned with the updated value.



+----------------------------+--------------------------------------------+
| Parameter                  | Description                                |
+============================+============================================+
| equityVarianceSwapSecurity | An equity variance swap security to update |
+----------------------------+--------------------------------------------+
| parameterizedAsVariance    | The parameterized as variance              |
+----------------------------+--------------------------------------------+



EquityVarianceSwapSecuritySetRegionId

.....................................
EquityVarianceSwapSecuritySetRegionId
.....................................


Updates the region of an equity variance swap security. The original object is unchanged - a new object is returned with the updated value.



+----------------------------+--------------------------------------------+
| Parameter                  | Description                                |
+============================+============================================+
| equityVarianceSwapSecurity | An equity variance swap security to update |
+----------------------------+--------------------------------------------+
| regionId                   | The region                                 |
+----------------------------+--------------------------------------------+



EquityVarianceSwapSecuritySetSettlementDate

...........................................
EquityVarianceSwapSecuritySetSettlementDate
...........................................


Updates the settlement date of an equity variance swap security. The original object is unchanged - a new object is returned with the updated value.



+----------------------------+--------------------------------------------+
| Parameter                  | Description                                |
+============================+============================================+
| equityVarianceSwapSecurity | An equity variance swap security to update |
+----------------------------+--------------------------------------------+
| settlementDate             | The settlement date                        |
+----------------------------+--------------------------------------------+



EquityVarianceSwapSecuritySetSpotUnderlyingId

.............................................
EquityVarianceSwapSecuritySetSpotUnderlyingId
.............................................


Updates the underlying identifier of an equity variance swap security. The original object is unchanged - a new object is returned with the updated value.



+----------------------------+--------------------------------------------+
| Parameter                  | Description                                |
+============================+============================================+
| equityVarianceSwapSecurity | An equity variance swap security to update |
+----------------------------+--------------------------------------------+
| spotUnderlyingId           | The underlying identifier                  |
+----------------------------+--------------------------------------------+



EquityVarianceSwapSecuritySetStrike

...................................
EquityVarianceSwapSecuritySetStrike
...................................


Updates the strike of an equity variance swap security. The original object is unchanged - a new object is returned with the updated value.



+----------------------------+--------------------------------------------+
| Parameter                  | Description                                |
+============================+============================================+
| equityVarianceSwapSecurity | An equity variance swap security to update |
+----------------------------+--------------------------------------------+
| strike                     | The strike                                 |
+----------------------------+--------------------------------------------+



EquityVarianceSwapSecuritySettlementDate

........................................
EquityVarianceSwapSecuritySettlementDate
........................................


Returns the settlement date from an equity variance swap security.



+----------------------------+-------------------------------------------+
| Parameter                  | Description                               |
+============================+===========================================+
| equityVarianceSwapSecurity | An equity variance swap security to query |
+----------------------------+-------------------------------------------+



EquityVarianceSwapSecuritySpotUnderlyingId

..........................................
EquityVarianceSwapSecuritySpotUnderlyingId
..........................................


Returns the underlying identifier from an equity variance swap security.



+----------------------------+-------------------------------------------+
| Parameter                  | Description                               |
+============================+===========================================+
| equityVarianceSwapSecurity | An equity variance swap security to query |
+----------------------------+-------------------------------------------+



EquityVarianceSwapSecurityStrike

................................
EquityVarianceSwapSecurityStrike
................................


Returns the strike from an equity variance swap security.



+----------------------------+-------------------------------------------+
| Parameter                  | Description                               |
+============================+===========================================+
| equityVarianceSwapSecurity | An equity variance swap security to query |
+----------------------------+-------------------------------------------+



EuropeanExerciseType

....................
EuropeanExerciseType
....................


Returns an object representing an European option exercise type.

This function takes no parameters.

ExpandAgricultureFutureSecurity

...............................
ExpandAgricultureFutureSecurity
...............................


Expand the contents of an agriculture future security.



+---------------------------+-----------------------------------------+
| Parameter                 | Description                             |
+===========================+=========================================+
| agricultureFutureSecurity | An agriculture future security to query |
+---------------------------+-----------------------------------------+
| headers                   | Specifies whether to include headers    |
+---------------------------+-----------------------------------------+



When headers are requested (set the headers parameter to TRUE or a non-zero numeric value), the result is returned in a 2xN range and should be used as an array formula. The first row contains the field headers. The second row contains the field values.

When headers are not requested (omit the headers parameter or set to FALSE or zero), the result is returned in a 1xN range containing the field values and should be used as an array formula.

Field values are always returned in the same order. To format a set of securities in a table the first can be expanded with headers and subsequent ones without. For example:



+---+----------------+-------------------------+-------------------------+-----+
|   | A              | B                       | C                       | D   |
+===+================+=========================+=========================+=====+
| 1 | Security       | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 2 | _identifier 1_ | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 3 | _identifier 2_ | {=ExpandSecurity(A3)}   | {=ExpandSecurity(A3)}   | ... |
+===+================+=========================+=========================+=====+
| 4 | _identifier 3_ | {=ExpandSecurity(A4)}   | {=ExpandSecurity(A4)}   | ... |
+===+================+=========================+=========================+=====+
| 5 | ...            | ...                     | ...                     | ... |
+===+================+=========================+=========================+=====+



ExpandBondFutureDeliverable

...........................
ExpandBondFutureDeliverable
...........................


Expand the contents of a deliverable for a bond future.



+-----------------------+------------------------------------------+
| Parameter             | Description                              |
+=======================+==========================================+
| bondFutureDeliverable | A deliverable for a bond future to query |
+-----------------------+------------------------------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

ExpandBondFutureSecurity

........................
ExpandBondFutureSecurity
........................


Expand the contents of a bond future security.



+--------------------+--------------------------------------+
| Parameter          | Description                          |
+====================+======================================+
| bondFutureSecurity | A bond future security to query      |
+--------------------+--------------------------------------+
| headers            | Specifies whether to include headers |
+--------------------+--------------------------------------+



When headers are requested (set the headers parameter to TRUE or a non-zero numeric value), the result is returned in a 2xN range and should be used as an array formula. The first row contains the field headers. The second row contains the field values.

When headers are not requested (omit the headers parameter or set to FALSE or zero), the result is returned in a 1xN range containing the field values and should be used as an array formula.

Field values are always returned in the same order. To format a set of securities in a table the first can be expanded with headers and subsequent ones without. For example:



+---+----------------+-------------------------+-------------------------+-----+
|   | A              | B                       | C                       | D   |
+===+================+=========================+=========================+=====+
| 1 | Security       | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 2 | _identifier 1_ | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 3 | _identifier 2_ | {=ExpandSecurity(A3)}   | {=ExpandSecurity(A3)}   | ... |
+===+================+=========================+=========================+=====+
| 4 | _identifier 3_ | {=ExpandSecurity(A4)}   | {=ExpandSecurity(A4)}   | ... |
+===+================+=========================+=========================+=====+
| 5 | ...            | ...                     | ...                     | ... |
+===+================+=========================+=========================+=====+



ExpandBondSecurity

..................
ExpandBondSecurity
..................


Expand the contents of a bond security.



+--------------+--------------------------------------+
| Parameter    | Description                          |
+==============+======================================+
| bondSecurity | A bond security to query             |
+--------------+--------------------------------------+
| headers      | Specifies whether to include headers |
+--------------+--------------------------------------+



When headers are requested (set the headers parameter to TRUE or a non-zero numeric value), the result is returned in a 2xN range and should be used as an array formula. The first row contains the field headers. The second row contains the field values.

When headers are not requested (omit the headers parameter or set to FALSE or zero), the result is returned in a 1xN range containing the field values and should be used as an array formula.

Field values are always returned in the same order. To format a set of securities in a table the first can be expanded with headers and subsequent ones without. For example:



+---+----------------+-------------------------+-------------------------+-----+
|   | A              | B                       | C                       | D   |
+===+================+=========================+=========================+=====+
| 1 | Security       | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 2 | _identifier 1_ | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 3 | _identifier 2_ | {=ExpandSecurity(A3)}   | {=ExpandSecurity(A3)}   | ... |
+===+================+=========================+=========================+=====+
| 4 | _identifier 3_ | {=ExpandSecurity(A4)}   | {=ExpandSecurity(A4)}   | ... |
+===+================+=========================+=========================+=====+
| 5 | ...            | ...                     | ...                     | ... |
+===+================+=========================+=========================+=====+



ExpandCapFloorCMSSpreadSecurity

...............................
ExpandCapFloorCMSSpreadSecurity
...............................


Expand the contents of a Cap/Floor CMS Spread security.



+---------------------------+------------------------------------------+
| Parameter                 | Description                              |
+===========================+==========================================+
| capFloorCMSSpreadSecurity | A Cap/Floor CMS Spread security to query |
+---------------------------+------------------------------------------+
| headers                   | Specifies whether to include headers     |
+---------------------------+------------------------------------------+



When headers are requested (set the headers parameter to TRUE or a non-zero numeric value), the result is returned in a 2xN range and should be used as an array formula. The first row contains the field headers. The second row contains the field values.

When headers are not requested (omit the headers parameter or set to FALSE or zero), the result is returned in a 1xN range containing the field values and should be used as an array formula.

Field values are always returned in the same order. To format a set of securities in a table the first can be expanded with headers and subsequent ones without. For example:



+---+----------------+-------------------------+-------------------------+-----+
|   | A              | B                       | C                       | D   |
+===+================+=========================+=========================+=====+
| 1 | Security       | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 2 | _identifier 1_ | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 3 | _identifier 2_ | {=ExpandSecurity(A3)}   | {=ExpandSecurity(A3)}   | ... |
+===+================+=========================+=========================+=====+
| 4 | _identifier 3_ | {=ExpandSecurity(A4)}   | {=ExpandSecurity(A4)}   | ... |
+===+================+=========================+=========================+=====+
| 5 | ...            | ...                     | ...                     | ... |
+===+================+=========================+=========================+=====+



ExpandCapFloorSecurity

......................
ExpandCapFloorSecurity
......................


Expand the contents of a Cap/Floor security.



+------------------+--------------------------------------+
| Parameter        | Description                          |
+==================+======================================+
| capFloorSecurity | A Cap/Floor security to query        |
+------------------+--------------------------------------+
| headers          | Specifies whether to include headers |
+------------------+--------------------------------------+



When headers are requested (set the headers parameter to TRUE or a non-zero numeric value), the result is returned in a 2xN range and should be used as an array formula. The first row contains the field headers. The second row contains the field values.

When headers are not requested (omit the headers parameter or set to FALSE or zero), the result is returned in a 1xN range containing the field values and should be used as an array formula.

Field values are always returned in the same order. To format a set of securities in a table the first can be expanded with headers and subsequent ones without. For example:



+---+----------------+-------------------------+-------------------------+-----+
|   | A              | B                       | C                       | D   |
+===+================+=========================+=========================+=====+
| 1 | Security       | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 2 | _identifier 1_ | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 3 | _identifier 2_ | {=ExpandSecurity(A3)}   | {=ExpandSecurity(A3)}   | ... |
+===+================+=========================+=========================+=====+
| 4 | _identifier 3_ | {=ExpandSecurity(A4)}   | {=ExpandSecurity(A4)}   | ... |
+===+================+=========================+=========================+=====+
| 5 | ...            | ...                     | ...                     | ... |
+===+================+=========================+=========================+=====+



ExpandCommodityFutureSecurity

.............................
ExpandCommodityFutureSecurity
.............................


Expand the contents of a commodity future.



+-------------------------+--------------------------------------+
| Parameter               | Description                          |
+=========================+======================================+
| commodityFutureSecurity | A commodity future to query          |
+-------------------------+--------------------------------------+
| headers                 | Specifies whether to include headers |
+-------------------------+--------------------------------------+



When headers are requested (set the headers parameter to TRUE or a non-zero numeric value), the result is returned in a 2xN range and should be used as an array formula. The first row contains the field headers. The second row contains the field values.

When headers are not requested (omit the headers parameter or set to FALSE or zero), the result is returned in a 1xN range containing the field values and should be used as an array formula.

Field values are always returned in the same order. To format a set of securities in a table the first can be expanded with headers and subsequent ones without. For example:



+---+----------------+-------------------------+-------------------------+-----+
|   | A              | B                       | C                       | D   |
+===+================+=========================+=========================+=====+
| 1 | Security       | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 2 | _identifier 1_ | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 3 | _identifier 2_ | {=ExpandSecurity(A3)}   | {=ExpandSecurity(A3)}   | ... |
+===+================+=========================+=========================+=====+
| 4 | _identifier 3_ | {=ExpandSecurity(A4)}   | {=ExpandSecurity(A4)}   | ... |
+===+================+=========================+=========================+=====+
| 5 | ...            | ...                     | ...                     | ... |
+===+================+=========================+=========================+=====+



ExpandComputedValues

....................
ExpandComputedValues
....................


Expands a list of computed values from a view into a table structure.



+-------------------+--------------------------------------------------------------------+
| Parameter         | Description                                                        |
+===================+====================================================================+
| values            | The list of computed values                                        |
+-------------------+--------------------------------------------------------------------+
| includeIdentifier | Whether to include the identifier of the target, defaults to FALSE |
+-------------------+--------------------------------------------------------------------+
| includeName       | Whether to include the name of the target, defaults to TRUE        |
+-------------------+--------------------------------------------------------------------+
| includeValue      | Whether to include the computed value, defaults to TRUE            |
+-------------------+--------------------------------------------------------------------+
| includeType       | Whether to include the target type, defaults to FALSE              |
+-------------------+--------------------------------------------------------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

ExpandCurve

...........
ExpandCurve
...........


Expand the contents of a curve.



+-----------+------------------+
| Parameter | Description      |
+===========+==================+
| curve     | A curve to query |
+-----------+------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

ExpandEnergyFutureSecurity

..........................
ExpandEnergyFutureSecurity
..........................


Expand the contents of an energy future security.



+----------------------+--------------------------------------+
| Parameter            | Description                          |
+======================+======================================+
| energyFutureSecurity | An energy future security to query   |
+----------------------+--------------------------------------+
| headers              | Specifies whether to include headers |
+----------------------+--------------------------------------+



When headers are requested (set the headers parameter to TRUE or a non-zero numeric value), the result is returned in a 2xN range and should be used as an array formula. The first row contains the field headers. The second row contains the field values.

When headers are not requested (omit the headers parameter or set to FALSE or zero), the result is returned in a 1xN range containing the field values and should be used as an array formula.

Field values are always returned in the same order. To format a set of securities in a table the first can be expanded with headers and subsequent ones without. For example:



+---+----------------+-------------------------+-------------------------+-----+
|   | A              | B                       | C                       | D   |
+===+================+=========================+=========================+=====+
| 1 | Security       | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 2 | _identifier 1_ | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 3 | _identifier 2_ | {=ExpandSecurity(A3)}   | {=ExpandSecurity(A3)}   | ... |
+===+================+=========================+=========================+=====+
| 4 | _identifier 3_ | {=ExpandSecurity(A4)}   | {=ExpandSecurity(A4)}   | ... |
+===+================+=========================+=========================+=====+
| 5 | ...            | ...                     | ...                     | ... |
+===+================+=========================+=========================+=====+



ExpandEquityBarrierOptionSecurity

.................................
ExpandEquityBarrierOptionSecurity
.................................


Expand the contents of an equity barrier option security.



+-----------------------------+--------------------------------------------+
| Parameter                   | Description                                |
+=============================+============================================+
| equityBarrierOptionSecurity | An equity barrier option security to query |
+-----------------------------+--------------------------------------------+
| headers                     | Specifies whether to include headers       |
+-----------------------------+--------------------------------------------+



When headers are requested (set the headers parameter to TRUE or a non-zero numeric value), the result is returned in a 2xN range and should be used as an array formula. The first row contains the field headers. The second row contains the field values.

When headers are not requested (omit the headers parameter or set to FALSE or zero), the result is returned in a 1xN range containing the field values and should be used as an array formula.

Field values are always returned in the same order. To format a set of securities in a table the first can be expanded with headers and subsequent ones without. For example:



+---+----------------+-------------------------+-------------------------+-----+
|   | A              | B                       | C                       | D   |
+===+================+=========================+=========================+=====+
| 1 | Security       | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 2 | _identifier 1_ | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 3 | _identifier 2_ | {=ExpandSecurity(A3)}   | {=ExpandSecurity(A3)}   | ... |
+===+================+=========================+=========================+=====+
| 4 | _identifier 3_ | {=ExpandSecurity(A4)}   | {=ExpandSecurity(A4)}   | ... |
+===+================+=========================+=========================+=====+
| 5 | ...            | ...                     | ...                     | ... |
+===+================+=========================+=========================+=====+



ExpandEquityFutureSecurity

..........................
ExpandEquityFutureSecurity
..........................


Expand the contents of an equity future security.



+----------------------+--------------------------------------+
| Parameter            | Description                          |
+======================+======================================+
| equityFutureSecurity | An equity future security to query   |
+----------------------+--------------------------------------+
| headers              | Specifies whether to include headers |
+----------------------+--------------------------------------+



When headers are requested (set the headers parameter to TRUE or a non-zero numeric value), the result is returned in a 2xN range and should be used as an array formula. The first row contains the field headers. The second row contains the field values.

When headers are not requested (omit the headers parameter or set to FALSE or zero), the result is returned in a 1xN range containing the field values and should be used as an array formula.

Field values are always returned in the same order. To format a set of securities in a table the first can be expanded with headers and subsequent ones without. For example:



+---+----------------+-------------------------+-------------------------+-----+
|   | A              | B                       | C                       | D   |
+===+================+=========================+=========================+=====+
| 1 | Security       | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 2 | _identifier 1_ | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 3 | _identifier 2_ | {=ExpandSecurity(A3)}   | {=ExpandSecurity(A3)}   | ... |
+===+================+=========================+=========================+=====+
| 4 | _identifier 3_ | {=ExpandSecurity(A4)}   | {=ExpandSecurity(A4)}   | ... |
+===+================+=========================+=========================+=====+
| 5 | ...            | ...                     | ...                     | ... |
+===+================+=========================+=========================+=====+



ExpandEquityIndexDividendFutureSecurity

.......................................
ExpandEquityIndexDividendFutureSecurity
.......................................


Expand the contents of an equity index dividend future.



+-----------------------------------+------------------------------------------+
| Parameter                         | Description                              |
+===================================+==========================================+
| equityIndexDividendFutureSecurity | An equity index dividend future to query |
+-----------------------------------+------------------------------------------+
| headers                           | Specifies whether to include headers     |
+-----------------------------------+------------------------------------------+



When headers are requested (set the headers parameter to TRUE or a non-zero numeric value), the result is returned in a 2xN range and should be used as an array formula. The first row contains the field headers. The second row contains the field values.

When headers are not requested (omit the headers parameter or set to FALSE or zero), the result is returned in a 1xN range containing the field values and should be used as an array formula.

Field values are always returned in the same order. To format a set of securities in a table the first can be expanded with headers and subsequent ones without. For example:



+---+----------------+-------------------------+-------------------------+-----+
|   | A              | B                       | C                       | D   |
+===+================+=========================+=========================+=====+
| 1 | Security       | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 2 | _identifier 1_ | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 3 | _identifier 2_ | {=ExpandSecurity(A3)}   | {=ExpandSecurity(A3)}   | ... |
+===+================+=========================+=========================+=====+
| 4 | _identifier 3_ | {=ExpandSecurity(A4)}   | {=ExpandSecurity(A4)}   | ... |
+===+================+=========================+=========================+=====+
| 5 | ...            | ...                     | ...                     | ... |
+===+================+=========================+=========================+=====+



ExpandEquityIndexOptionSecurity

...............................
ExpandEquityIndexOptionSecurity
...............................


Expand the contents of an equity index option security.



+---------------------------+------------------------------------------+
| Parameter                 | Description                              |
+===========================+==========================================+
| equityIndexOptionSecurity | An equity index option security to query |
+---------------------------+------------------------------------------+
| headers                   | Specifies whether to include headers     |
+---------------------------+------------------------------------------+



When headers are requested (set the headers parameter to TRUE or a non-zero numeric value), the result is returned in a 2xN range and should be used as an array formula. The first row contains the field headers. The second row contains the field values.

When headers are not requested (omit the headers parameter or set to FALSE or zero), the result is returned in a 1xN range containing the field values and should be used as an array formula.

Field values are always returned in the same order. To format a set of securities in a table the first can be expanded with headers and subsequent ones without. For example:



+---+----------------+-------------------------+-------------------------+-----+
|   | A              | B                       | C                       | D   |
+===+================+=========================+=========================+=====+
| 1 | Security       | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 2 | _identifier 1_ | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 3 | _identifier 2_ | {=ExpandSecurity(A3)}   | {=ExpandSecurity(A3)}   | ... |
+===+================+=========================+=========================+=====+
| 4 | _identifier 3_ | {=ExpandSecurity(A4)}   | {=ExpandSecurity(A4)}   | ... |
+===+================+=========================+=========================+=====+
| 5 | ...            | ...                     | ...                     | ... |
+===+================+=========================+=========================+=====+



ExpandEquityOptionSecurity

..........................
ExpandEquityOptionSecurity
..........................


Expand the contents of an equity option security.



+----------------------+--------------------------------------+
| Parameter            | Description                          |
+======================+======================================+
| equityOptionSecurity | An equity option security to query   |
+----------------------+--------------------------------------+
| headers              | Specifies whether to include headers |
+----------------------+--------------------------------------+



When headers are requested (set the headers parameter to TRUE or a non-zero numeric value), the result is returned in a 2xN range and should be used as an array formula. The first row contains the field headers. The second row contains the field values.

When headers are not requested (omit the headers parameter or set to FALSE or zero), the result is returned in a 1xN range containing the field values and should be used as an array formula.

Field values are always returned in the same order. To format a set of securities in a table the first can be expanded with headers and subsequent ones without. For example:



+---+----------------+-------------------------+-------------------------+-----+
|   | A              | B                       | C                       | D   |
+===+================+=========================+=========================+=====+
| 1 | Security       | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 2 | _identifier 1_ | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 3 | _identifier 2_ | {=ExpandSecurity(A3)}   | {=ExpandSecurity(A3)}   | ... |
+===+================+=========================+=========================+=====+
| 4 | _identifier 3_ | {=ExpandSecurity(A4)}   | {=ExpandSecurity(A4)}   | ... |
+===+================+=========================+=========================+=====+
| 5 | ...            | ...                     | ...                     | ... |
+===+================+=========================+=========================+=====+



ExpandEquitySecurity

....................
ExpandEquitySecurity
....................


Expand the contents of an equity security.



+----------------+--------------------------------------+
| Parameter      | Description                          |
+================+======================================+
| equitySecurity | An equity security to query          |
+----------------+--------------------------------------+
| headers        | Specifies whether to include headers |
+----------------+--------------------------------------+



When headers are requested (set the headers parameter to TRUE or a non-zero numeric value), the result is returned in a 2xN range and should be used as an array formula. The first row contains the field headers. The second row contains the field values.

When headers are not requested (omit the headers parameter or set to FALSE or zero), the result is returned in a 1xN range containing the field values and should be used as an array formula.

Field values are always returned in the same order. To format a set of securities in a table the first can be expanded with headers and subsequent ones without. For example:



+---+----------------+-------------------------+-------------------------+-----+
|   | A              | B                       | C                       | D   |
+===+================+=========================+=========================+=====+
| 1 | Security       | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 2 | _identifier 1_ | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 3 | _identifier 2_ | {=ExpandSecurity(A3)}   | {=ExpandSecurity(A3)}   | ... |
+===+================+=========================+=========================+=====+
| 4 | _identifier 3_ | {=ExpandSecurity(A4)}   | {=ExpandSecurity(A4)}   | ... |
+===+================+=========================+=========================+=====+
| 5 | ...            | ...                     | ...                     | ... |
+===+================+=========================+=========================+=====+



ExpandEquityVarianceSwapSecurity

................................
ExpandEquityVarianceSwapSecurity
................................


Expand the contents of an equity variance swap security.



+----------------------------+-------------------------------------------+
| Parameter                  | Description                               |
+============================+===========================================+
| equityVarianceSwapSecurity | An equity variance swap security to query |
+----------------------------+-------------------------------------------+
| headers                    | Specifies whether to include headers      |
+----------------------------+-------------------------------------------+



When headers are requested (set the headers parameter to TRUE or a non-zero numeric value), the result is returned in a 2xN range and should be used as an array formula. The first row contains the field headers. The second row contains the field values.

When headers are not requested (omit the headers parameter or set to FALSE or zero), the result is returned in a 1xN range containing the field values and should be used as an array formula.

Field values are always returned in the same order. To format a set of securities in a table the first can be expanded with headers and subsequent ones without. For example:



+---+----------------+-------------------------+-------------------------+-----+
|   | A              | B                       | C                       | D   |
+===+================+=========================+=========================+=====+
| 1 | Security       | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 2 | _identifier 1_ | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 3 | _identifier 2_ | {=ExpandSecurity(A3)}   | {=ExpandSecurity(A3)}   | ... |
+===+================+=========================+=========================+=====+
| 4 | _identifier 3_ | {=ExpandSecurity(A4)}   | {=ExpandSecurity(A4)}   | ... |
+===+================+=========================+=========================+=====+
| 5 | ...            | ...                     | ...                     | ... |
+===+================+=========================+=========================+=====+



ExpandFRASecurity

.................
ExpandFRASecurity
.................


Expand the contents of a forward rate agreement security.



+-------------+--------------------------------------------+
| Parameter   | Description                                |
+=============+============================================+
| FRASecurity | A forward rate agreement security to query |
+-------------+--------------------------------------------+
| headers     | Specifies whether to include headers       |
+-------------+--------------------------------------------+



When headers are requested (set the headers parameter to TRUE or a non-zero numeric value), the result is returned in a 2xN range and should be used as an array formula. The first row contains the field headers. The second row contains the field values.

When headers are not requested (omit the headers parameter or set to FALSE or zero), the result is returned in a 1xN range containing the field values and should be used as an array formula.

Field values are always returned in the same order. To format a set of securities in a table the first can be expanded with headers and subsequent ones without. For example:



+---+----------------+-------------------------+-------------------------+-----+
|   | A              | B                       | C                       | D   |
+===+================+=========================+=========================+=====+
| 1 | Security       | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 2 | _identifier 1_ | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 3 | _identifier 2_ | {=ExpandSecurity(A3)}   | {=ExpandSecurity(A3)}   | ... |
+===+================+=========================+=========================+=====+
| 4 | _identifier 3_ | {=ExpandSecurity(A4)}   | {=ExpandSecurity(A4)}   | ... |
+===+================+=========================+=========================+=====+
| 5 | ...            | ...                     | ...                     | ... |
+===+================+=========================+=========================+=====+



ExpandFXBarrierOptionSecurity

.............................
ExpandFXBarrierOptionSecurity
.............................


Expand the contents of a FX barrier option security.



+-------------------------+---------------------------------------+
| Parameter               | Description                           |
+=========================+=======================================+
| FXBarrierOptionSecurity | A FX barrier option security to query |
+-------------------------+---------------------------------------+
| headers                 | Specifies whether to include headers  |
+-------------------------+---------------------------------------+



When headers are requested (set the headers parameter to TRUE or a non-zero numeric value), the result is returned in a 2xN range and should be used as an array formula. The first row contains the field headers. The second row contains the field values.

When headers are not requested (omit the headers parameter or set to FALSE or zero), the result is returned in a 1xN range containing the field values and should be used as an array formula.

Field values are always returned in the same order. To format a set of securities in a table the first can be expanded with headers and subsequent ones without. For example:



+---+----------------+-------------------------+-------------------------+-----+
|   | A              | B                       | C                       | D   |
+===+================+=========================+=========================+=====+
| 1 | Security       | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 2 | _identifier 1_ | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 3 | _identifier 2_ | {=ExpandSecurity(A3)}   | {=ExpandSecurity(A3)}   | ... |
+===+================+=========================+=========================+=====+
| 4 | _identifier 3_ | {=ExpandSecurity(A4)}   | {=ExpandSecurity(A4)}   | ... |
+===+================+=========================+=========================+=====+
| 5 | ...            | ...                     | ...                     | ... |
+===+================+=========================+=========================+=====+



ExpandFXDigitalOptionSecurity

.............................
ExpandFXDigitalOptionSecurity
.............................


Expand the contents of an FX digital option security.



+-------------------------+----------------------------------------+
| Parameter               | Description                            |
+=========================+========================================+
| FXDigitalOptionSecurity | An FX digital option security to query |
+-------------------------+----------------------------------------+
| headers                 | Specifies whether to include headers   |
+-------------------------+----------------------------------------+



When headers are requested (set the headers parameter to TRUE or a non-zero numeric value), the result is returned in a 2xN range and should be used as an array formula. The first row contains the field headers. The second row contains the field values.

When headers are not requested (omit the headers parameter or set to FALSE or zero), the result is returned in a 1xN range containing the field values and should be used as an array formula.

Field values are always returned in the same order. To format a set of securities in a table the first can be expanded with headers and subsequent ones without. For example:



+---+----------------+-------------------------+-------------------------+-----+
|   | A              | B                       | C                       | D   |
+===+================+=========================+=========================+=====+
| 1 | Security       | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 2 | _identifier 1_ | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 3 | _identifier 2_ | {=ExpandSecurity(A3)}   | {=ExpandSecurity(A3)}   | ... |
+===+================+=========================+=========================+=====+
| 4 | _identifier 3_ | {=ExpandSecurity(A4)}   | {=ExpandSecurity(A4)}   | ... |
+===+================+=========================+=========================+=====+
| 5 | ...            | ...                     | ...                     | ... |
+===+================+=========================+=========================+=====+



ExpandFXForwardSecurity

.......................
ExpandFXForwardSecurity
.......................


Expand the contents of an FX forward security.



+-------------------+--------------------------------------+
| Parameter         | Description                          |
+===================+======================================+
| FXForwardSecurity | An FX forward security to query      |
+-------------------+--------------------------------------+
| headers           | Specifies whether to include headers |
+-------------------+--------------------------------------+



When headers are requested (set the headers parameter to TRUE or a non-zero numeric value), the result is returned in a 2xN range and should be used as an array formula. The first row contains the field headers. The second row contains the field values.

When headers are not requested (omit the headers parameter or set to FALSE or zero), the result is returned in a 1xN range containing the field values and should be used as an array formula.

Field values are always returned in the same order. To format a set of securities in a table the first can be expanded with headers and subsequent ones without. For example:



+---+----------------+-------------------------+-------------------------+-----+
|   | A              | B                       | C                       | D   |
+===+================+=========================+=========================+=====+
| 1 | Security       | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 2 | _identifier 1_ | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 3 | _identifier 2_ | {=ExpandSecurity(A3)}   | {=ExpandSecurity(A3)}   | ... |
+===+================+=========================+=========================+=====+
| 4 | _identifier 3_ | {=ExpandSecurity(A4)}   | {=ExpandSecurity(A4)}   | ... |
+===+================+=========================+=========================+=====+
| 5 | ...            | ...                     | ...                     | ... |
+===+================+=========================+=========================+=====+



ExpandFXFutureSecurity

......................
ExpandFXFutureSecurity
......................


Expand the contents of an FX future security.



+------------------+--------------------------------------+
| Parameter        | Description                          |
+==================+======================================+
| FXFutureSecurity | An FX future security to query       |
+------------------+--------------------------------------+
| headers          | Specifies whether to include headers |
+------------------+--------------------------------------+



When headers are requested (set the headers parameter to TRUE or a non-zero numeric value), the result is returned in a 2xN range and should be used as an array formula. The first row contains the field headers. The second row contains the field values.

When headers are not requested (omit the headers parameter or set to FALSE or zero), the result is returned in a 1xN range containing the field values and should be used as an array formula.

Field values are always returned in the same order. To format a set of securities in a table the first can be expanded with headers and subsequent ones without. For example:



+---+----------------+-------------------------+-------------------------+-----+
|   | A              | B                       | C                       | D   |
+===+================+=========================+=========================+=====+
| 1 | Security       | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 2 | _identifier 1_ | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 3 | _identifier 2_ | {=ExpandSecurity(A3)}   | {=ExpandSecurity(A3)}   | ... |
+===+================+=========================+=========================+=====+
| 4 | _identifier 3_ | {=ExpandSecurity(A4)}   | {=ExpandSecurity(A4)}   | ... |
+===+================+=========================+=========================+=====+
| 5 | ...            | ...                     | ...                     | ... |
+===+================+=========================+=========================+=====+



ExpandFXOptionSecurity

......................
ExpandFXOptionSecurity
......................


Expand the contents of an FX option security.



+------------------+--------------------------------------+
| Parameter        | Description                          |
+==================+======================================+
| FXOptionSecurity | An FX option security to query       |
+------------------+--------------------------------------+
| headers          | Specifies whether to include headers |
+------------------+--------------------------------------+



When headers are requested (set the headers parameter to TRUE or a non-zero numeric value), the result is returned in a 2xN range and should be used as an array formula. The first row contains the field headers. The second row contains the field values.

When headers are not requested (omit the headers parameter or set to FALSE or zero), the result is returned in a 1xN range containing the field values and should be used as an array formula.

Field values are always returned in the same order. To format a set of securities in a table the first can be expanded with headers and subsequent ones without. For example:



+---+----------------+-------------------------+-------------------------+-----+
|   | A              | B                       | C                       | D   |
+===+================+=========================+=========================+=====+
| 1 | Security       | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 2 | _identifier 1_ | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 3 | _identifier 2_ | {=ExpandSecurity(A3)}   | {=ExpandSecurity(A3)}   | ... |
+===+================+=========================+=========================+=====+
| 4 | _identifier 3_ | {=ExpandSecurity(A4)}   | {=ExpandSecurity(A4)}   | ... |
+===+================+=========================+=========================+=====+
| 5 | ...            | ...                     | ...                     | ... |
+===+================+=========================+=========================+=====+



ExpandFixedInterestRateLeg

..........................
ExpandFixedInterestRateLeg
..........................


Expand the contents of a fixed interest rate leg.



+----------------------+------------------------------------+
| Parameter            | Description                        |
+======================+====================================+
| fixedInterestRateLeg | A fixed interest rate leg to query |
+----------------------+------------------------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

ExpandFixedVarianceSwapLeg

..........................
ExpandFixedVarianceSwapLeg
..........................


Expand the contents of a fixed leg of a variance swap.



+----------------------+-----------------------------------------+
| Parameter            | Description                             |
+======================+=========================================+
| fixedVarianceSwapLeg | A fixed leg of a variance swap to query |
+----------------------+-----------------------------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

ExpandFloatingGearingIRLeg

..........................
ExpandFloatingGearingIRLeg
..........................


Expand the contents of a floating gearing interest rate leg of a swap.



+----------------------+---------------------------------------------------------+
| Parameter            | Description                                             |
+======================+=========================================================+
| floatingGearingIRLeg | A floating gearing interest rate leg of a swap to query |
+----------------------+---------------------------------------------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

ExpandFloatingInterestRateLeg

.............................
ExpandFloatingInterestRateLeg
.............................


Expand the contents of a floating interest rate leg of a swap.



+-------------------------+-------------------------------------------------+
| Parameter               | Description                                     |
+=========================+=================================================+
| floatingInterestRateLeg | A floating interest rate leg of a swap to query |
+-------------------------+-------------------------------------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

ExpandFloatingSpreadIRLeg

.........................
ExpandFloatingSpreadIRLeg
.........................


Expand the contents of a floating spread interest rate leg of a swap.



+---------------------+--------------------------------------------------------+
| Parameter           | Description                                            |
+=====================+========================================================+
| floatingSpreadIRLeg | A floating spread interest rate leg of a swap to query |
+---------------------+--------------------------------------------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

ExpandFloatingVarianceSwapLeg

.............................
ExpandFloatingVarianceSwapLeg
.............................


Expand the contents of a floating leg of a variance swap.



+-------------------------+--------------------------------------------+
| Parameter               | Description                                |
+=========================+============================================+
| floatingVarianceSwapLeg | A floating leg of a variance swap to query |
+-------------------------+--------------------------------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

ExpandForwardSwapSecurity

.........................
ExpandForwardSwapSecurity
.........................


Expand the contents of a forward swap security.



+---------------------+--------------------------------------+
| Parameter           | Description                          |
+=====================+======================================+
| forwardSwapSecurity | A forward swap security to query     |
+---------------------+--------------------------------------+
| headers             | Specifies whether to include headers |
+---------------------+--------------------------------------+



When headers are requested (set the headers parameter to TRUE or a non-zero numeric value), the result is returned in a 2xN range and should be used as an array formula. The first row contains the field headers. The second row contains the field values.

When headers are not requested (omit the headers parameter or set to FALSE or zero), the result is returned in a 1xN range containing the field values and should be used as an array formula.

Field values are always returned in the same order. To format a set of securities in a table the first can be expanded with headers and subsequent ones without. For example:



+---+----------------+-------------------------+-------------------------+-----+
|   | A              | B                       | C                       | D   |
+===+================+=========================+=========================+=====+
| 1 | Security       | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 2 | _identifier 1_ | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 3 | _identifier 2_ | {=ExpandSecurity(A3)}   | {=ExpandSecurity(A3)}   | ... |
+===+================+=========================+=========================+=====+
| 4 | _identifier 3_ | {=ExpandSecurity(A4)}   | {=ExpandSecurity(A4)}   | ... |
+===+================+=========================+=========================+=====+
| 5 | ...            | ...                     | ...                     | ... |
+===+================+=========================+=========================+=====+



ExpandFutureSecurity

....................
ExpandFutureSecurity
....................


Expand the contents of a future security.



+----------------+--------------------------------------+
| Parameter      | Description                          |
+================+======================================+
| futureSecurity | A future security to query           |
+----------------+--------------------------------------+
| headers        | Specifies whether to include headers |
+----------------+--------------------------------------+



When headers are requested (set the headers parameter to TRUE or a non-zero numeric value), the result is returned in a 2xN range and should be used as an array formula. The first row contains the field headers. The second row contains the field values.

When headers are not requested (omit the headers parameter or set to FALSE or zero), the result is returned in a 1xN range containing the field values and should be used as an array formula.

Field values are always returned in the same order. To format a set of securities in a table the first can be expanded with headers and subsequent ones without. For example:



+---+----------------+-------------------------+-------------------------+-----+
|   | A              | B                       | C                       | D   |
+===+================+=========================+=========================+=====+
| 1 | Security       | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 2 | _identifier 1_ | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 3 | _identifier 2_ | {=ExpandSecurity(A3)}   | {=ExpandSecurity(A3)}   | ... |
+===+================+=========================+=========================+=====+
| 4 | _identifier 3_ | {=ExpandSecurity(A4)}   | {=ExpandSecurity(A4)}   | ... |
+===+================+=========================+=========================+=====+
| 5 | ...            | ...                     | ...                     | ... |
+===+================+=========================+=========================+=====+



ExpandGovernmentBondSecurity

............................
ExpandGovernmentBondSecurity
............................


Expand the contents of a government bond security.



+------------------------+--------------------------------------+
| Parameter              | Description                          |
+========================+======================================+
| governmentBondSecurity | A government bond security to query  |
+------------------------+--------------------------------------+
| headers                | Specifies whether to include headers |
+------------------------+--------------------------------------+



When headers are requested (set the headers parameter to TRUE or a non-zero numeric value), the result is returned in a 2xN range and should be used as an array formula. The first row contains the field headers. The second row contains the field values.

When headers are not requested (omit the headers parameter or set to FALSE or zero), the result is returned in a 1xN range containing the field values and should be used as an array formula.

Field values are always returned in the same order. To format a set of securities in a table the first can be expanded with headers and subsequent ones without. For example:



+---+----------------+-------------------------+-------------------------+-----+
|   | A              | B                       | C                       | D   |
+===+================+=========================+=========================+=====+
| 1 | Security       | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 2 | _identifier 1_ | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 3 | _identifier 2_ | {=ExpandSecurity(A3)}   | {=ExpandSecurity(A3)}   | ... |
+===+================+=========================+=========================+=====+
| 4 | _identifier 3_ | {=ExpandSecurity(A4)}   | {=ExpandSecurity(A4)}   | ... |
+===+================+=========================+=========================+=====+
| 5 | ...            | ...                     | ...                     | ... |
+===+================+=========================+=========================+=====+



ExpandIRFutureOptionSecurity

............................
ExpandIRFutureOptionSecurity
............................


Expand the contents of an IR future security.



+------------------------+--------------------------------------+
| Parameter              | Description                          |
+========================+======================================+
| IRFutureOptionSecurity | An IR future security to query       |
+------------------------+--------------------------------------+
| headers                | Specifies whether to include headers |
+------------------------+--------------------------------------+




When headers are requested (set the headers parameter to TRUE or a non-zero numeric value), the result is returned in a 2xN range and should be used as an array formula. The first row contains the field headers. The second row contains the field values.

When headers are not requested (omit the headers parameter or set to FALSE or zero), the result is returned in a 1xN range containing the field values and should be used as an array formula.

Field values are always returned in the same order. To format a set of securities in a table the first can be expanded with headers and subsequent ones without. For example:



+---+----------------+-------------------------+-------------------------+-----+
|   | A              | B                       | C                       | D   |
+===+================+=========================+=========================+=====+
| 1 | Security       | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 2 | _identifier 1_ | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 3 | _identifier 2_ | {=ExpandSecurity(A3)}   | {=ExpandSecurity(A3)}   | ... |
+===+================+=========================+=========================+=====+
| 4 | _identifier 3_ | {=ExpandSecurity(A4)}   | {=ExpandSecurity(A4)}   | ... |
+===+================+=========================+=========================+=====+
| 5 | ...            | ...                     | ...                     | ... |
+===+================+=========================+=========================+=====+



ExpandInterestRateFutureSecurity

................................
ExpandInterestRateFutureSecurity
................................


Expand the contents of an IR future security.



+----------------------------+--------------------------------------+
| Parameter                  | Description                          |
+============================+======================================+
| interestRateFutureSecurity | An IR future security to query       |
+----------------------------+--------------------------------------+
| headers                    | Specifies whether to include headers |
+----------------------------+--------------------------------------+




When headers are requested (set the headers parameter to TRUE or a non-zero numeric value), the result is returned in a 2xN range and should be used as an array formula. The first row contains the field headers. The second row contains the field values.

When headers are not requested (omit the headers parameter or set to FALSE or zero), the result is returned in a 1xN range containing the field values and should be used as an array formula.

Field values are always returned in the same order. To format a set of securities in a table the first can be expanded with headers and subsequent ones without. For example:



+---+----------------+-------------------------+-------------------------+-----+
|   | A              | B                       | C                       | D   |
+===+================+=========================+=========================+=====+
| 1 | Security       | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 2 | _identifier 1_ | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 3 | _identifier 2_ | {=ExpandSecurity(A3)}   | {=ExpandSecurity(A3)}   | ... |
+===+================+=========================+=========================+=====+
| 4 | _identifier 3_ | {=ExpandSecurity(A4)}   | {=ExpandSecurity(A4)}   | ... |
+===+================+=========================+=========================+=====+
| 5 | ...            | ...                     | ...                     | ... |
+===+================+=========================+=========================+=====+



ExpandInterestRateNotional

..........................
ExpandInterestRateNotional
..........................


Expand the contents of a notional value of an interest rate leg of a swap.



+----------------------+-------------------------------------------------------------+
| Parameter            | Description                                                 |
+======================+=============================================================+
| interestRateNotional | A notional value of an interest rate leg of a swap to query |
+----------------------+-------------------------------------------------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

ExpandLabeledMatrix2D

.....................
ExpandLabeledMatrix2D
.....................


Expands a labeled matrix.



+----------------+--------------------+
| Parameter      | Description        |
+================+====================+
| labeled_matrix | The labeled matrix |
+----------------+--------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

ExpandLabeledMatrix3D

.....................
ExpandLabeledMatrix3D
.....................


Expands a labeled matrix.



+----------------+--------------------+
| Parameter      | Description        |
+================+====================+
| labeled_matrix | The labeled matrix |
+----------------+--------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

ExpandLabeledVector

...................
ExpandLabeledVector
...................


Expands a labeled vector.



+---------------+--------------------+
| Parameter     | Description        |
+===============+====================+
| labeledVector | The labeled vector |
+---------------+--------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

ExpandLabeledVectorsTable

.........................
ExpandLabeledVectorsTable
.........................


Expands one or more labeled vectors into a table structure.



+----------------+------------------------------------------+
| Parameter      | Description                              |
+================+==========================================+
| labeledVectors | The range of one or more labeled vectors |
+----------------+------------------------------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

ExpandMetalFutureSecurity

.........................
ExpandMetalFutureSecurity
.........................


Expand the contents of a metal future security.



+---------------------+--------------------------------------+
| Parameter           | Description                          |
+=====================+======================================+
| metalFutureSecurity | A metal future security to query     |
+---------------------+--------------------------------------+
| headers             | Specifies whether to include headers |
+---------------------+--------------------------------------+



When headers are requested (set the headers parameter to TRUE or a non-zero numeric value), the result is returned in a 2xN range and should be used as an array formula. The first row contains the field headers. The second row contains the field values.

When headers are not requested (omit the headers parameter or set to FALSE or zero), the result is returned in a 1xN range containing the field values and should be used as an array formula.

Field values are always returned in the same order. To format a set of securities in a table the first can be expanded with headers and subsequent ones without. For example:



+---+----------------+-------------------------+-------------------------+-----+
|   | A              | B                       | C                       | D   |
+===+================+=========================+=========================+=====+
| 1 | Security       | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 2 | _identifier 1_ | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 3 | _identifier 2_ | {=ExpandSecurity(A3)}   | {=ExpandSecurity(A3)}   | ... |
+===+================+=========================+=========================+=====+
| 4 | _identifier 3_ | {=ExpandSecurity(A4)}   | {=ExpandSecurity(A4)}   | ... |
+===+================+=========================+=========================+=====+
| 5 | ...            | ...                     | ...                     | ... |
+===+================+=========================+=========================+=====+



ExpandNonDeliverableFXOptionSecurity

....................................
ExpandNonDeliverableFXOptionSecurity
....................................


Expand the contents of a non-deliverable FX option security.



+--------------------------------+-----------------------------------------------+
| Parameter                      | Description                                   |
+================================+===============================================+
| nonDeliverableFXOptionSecurity | A non-deliverable FX option security to query |
+--------------------------------+-----------------------------------------------+
| headers                        | Specifies whether to include headers          |
+--------------------------------+-----------------------------------------------+



When headers are requested (set the headers parameter to TRUE or a non-zero numeric value), the result is returned in a 2xN range and should be used as an array formula. The first row contains the field headers. The second row contains the field values.

When headers are not requested (omit the headers parameter or set to FALSE or zero), the result is returned in a 1xN range containing the field values and should be used as an array formula.

Field values are always returned in the same order. To format a set of securities in a table the first can be expanded with headers and subsequent ones without. For example:



+---+----------------+-------------------------+-------------------------+-----+
|   | A              | B                       | C                       | D   |
+===+================+=========================+=========================+=====+
| 1 | Security       | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 2 | _identifier 1_ | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 3 | _identifier 2_ | {=ExpandSecurity(A3)}   | {=ExpandSecurity(A3)}   | ... |
+===+================+=========================+=========================+=====+
| 4 | _identifier 3_ | {=ExpandSecurity(A4)}   | {=ExpandSecurity(A4)}   | ... |
+===+================+=========================+=========================+=====+
| 5 | ...            | ...                     | ...                     | ... |
+===+================+=========================+=========================+=====+



ExpandPortfolio

...............
ExpandPortfolio
...............


Expand the contents of a portfolio.



+-----------+----------------------+
| Parameter | Description          |
+===========+======================+
| portfolio | A portfolio to query |
+-----------+----------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

ExpandPortfolioNode

...................
ExpandPortfolioNode
...................


Expand the contents of a portfolio node.



+---------------+---------------------------+
| Parameter     | Description               |
+===============+===========================+
| portfolioNode | A portfolio node to query |
+---------------+---------------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

ExpandPosition

..............
ExpandPosition
..............


Expand the contents of a position.



+-----------+---------------------+
| Parameter | Description         |
+===========+=====================+
| position  | A position to query |
+-----------+---------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

ExpandSecurity

..............
ExpandSecurity
..............


Expand the contents of a security.



+-----------+--------------------------------------+
| Parameter | Description                          |
+===========+======================================+
| security  | A security to query                  |
+-----------+--------------------------------------+
| headers   | Specifies whether to include headers |
+-----------+--------------------------------------+



The Security base class contains few attributes. For a more detailed expansion, use one of the sub-class expansion methods (e.g. ExpandEquityOptionSecurity). The asset class, as returned by the GetSecurityType function, can be used to determine which of the sub-class expansion methods to use.

When headers are requested (set the headers parameter to TRUE or a non-zero numeric value), the result is returned in a 2xN range and should be used as an array formula. The first row contains the field headers. The second row contains the field values.

When headers are not requested (omit the headers parameter or set to FALSE or zero), the result is returned in a 1xN range containing the field values and should be used as an array formula.

Field values are always returned in the same order. To format a set of securities in a table the first can be expanded with headers and subsequent ones without. For example:



+---+----------------+-------------------------+-------------------------+-----+
|   | A              | B                       | C                       | D   |
+===+================+=========================+=========================+=====+
| 1 | Security       | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 2 | _identifier 1_ | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 3 | _identifier 2_ | {=ExpandSecurity(A3)}   | {=ExpandSecurity(A3)}   | ... |
+===+================+=========================+=========================+=====+
| 4 | _identifier 3_ | {=ExpandSecurity(A4)}   | {=ExpandSecurity(A4)}   | ... |
+===+================+=========================+=========================+=====+
| 5 | ...            | ...                     | ...                     | ... |
+===+================+=========================+=========================+=====+



ExpandSecurityNotional

......................
ExpandSecurityNotional
......................


Expand the contents of a notional value defined as a security .



+------------------+--------------------------------------------------+
| Parameter        | Description                                      |
+==================+==================================================+
| securityNotional | A notional value defined as a security  to query |
+------------------+--------------------------------------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

ExpandSnapshot

..............
ExpandSnapshot
..............


Expand the contents of a market data snapshot.



+-----------+---------------------------------+
| Parameter | Description                     |
+===========+=================================+
| snapshot  | A market data snapshot to query |
+-----------+---------------------------------+



ExpandSnapshotYieldCurve

........................
ExpandSnapshotYieldCurve
........................


Expand the contents of a market data snapshot yield curve.



+--------------------+---------------------------------------------+
| Parameter          | Description                                 |
+====================+=============================================+
| snapshotYieldCurve | A market data snapshot yield curve to query |
+--------------------+---------------------------------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

ExpandSwapLeg

.............
ExpandSwapLeg
.............


Expand the contents of a leg of a swap.



+-----------+--------------------------+
| Parameter | Description              |
+===========+==========================+
| swapLeg   | A leg of a swap to query |
+-----------+--------------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

ExpandSwapSecurity

..................
ExpandSwapSecurity
..................


Expand the contents of a swap security.



+--------------+--------------------------------------+
| Parameter    | Description                          |
+==============+======================================+
| swapSecurity | A swap security to query             |
+--------------+--------------------------------------+
| headers      | Specifies whether to include headers |
+--------------+--------------------------------------+



When headers are requested (set the headers parameter to TRUE or a non-zero numeric value), the result is returned in a 2xN range and should be used as an array formula. The first row contains the field headers. The second row contains the field values.

When headers are not requested (omit the headers parameter or set to FALSE or zero), the result is returned in a 1xN range containing the field values and should be used as an array formula.

Field values are always returned in the same order. To format a set of securities in a table the first can be expanded with headers and subsequent ones without. For example:



+---+----------------+-------------------------+-------------------------+-----+
|   | A              | B                       | C                       | D   |
+===+================+=========================+=========================+=====+
| 1 | Security       | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 2 | _identifier 1_ | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 3 | _identifier 2_ | {=ExpandSecurity(A3)}   | {=ExpandSecurity(A3)}   | ... |
+===+================+=========================+=========================+=====+
| 4 | _identifier 3_ | {=ExpandSecurity(A4)}   | {=ExpandSecurity(A4)}   | ... |
+===+================+=========================+=========================+=====+
| 5 | ...            | ...                     | ...                     | ... |
+===+================+=========================+=========================+=====+



ExpandSwaptionSecurity

......................
ExpandSwaptionSecurity
......................


Expand the contents of a swaption security.



+------------------+--------------------------------------+
| Parameter        | Description                          |
+==================+======================================+
| swaptionSecurity | A swaption security to query         |
+------------------+--------------------------------------+
| headers          | Specifies whether to include headers |
+------------------+--------------------------------------+



When headers are requested (set the headers parameter to TRUE or a non-zero numeric value), the result is returned in a 2xN range and should be used as an array formula. The first row contains the field headers. The second row contains the field values.

When headers are not requested (omit the headers parameter or set to FALSE or zero), the result is returned in a 1xN range containing the field values and should be used as an array formula.

Field values are always returned in the same order. To format a set of securities in a table the first can be expanded with headers and subsequent ones without. For example:



+---+----------------+-------------------------+-------------------------+-----+
|   | A              | B                       | C                       | D   |
+===+================+=========================+=========================+=====+
| 1 | Security       | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 2 | _identifier 1_ | {=ExpandSecurity(A2,1)} | {=ExpandSecurity(A2,1)} | ... |
+===+================+=========================+=========================+=====+
| 3 | _identifier 2_ | {=ExpandSecurity(A3)}   | {=ExpandSecurity(A3)}   | ... |
+===+================+=========================+=========================+=====+
| 4 | _identifier 3_ | {=ExpandSecurity(A4)}   | {=ExpandSecurity(A4)}   | ... |
+===+================+=========================+=========================+=====+
| 5 | ...            | ...                     | ...                     | ... |
+===+================+=========================+=========================+=====+



ExpandTimeSeries

................
ExpandTimeSeries
................


Expands a time series.



+------------------+---------------------------------------------------------+
| Parameter        | Description                                             |
+==================+=========================================================+
| timeSeries       | The time series                                         |
+------------------+---------------------------------------------------------+
| startDate        | The optional start date                                 |
+------------------+---------------------------------------------------------+
| endDate          | The optional end date                                   |
+------------------+---------------------------------------------------------+
| numLatestEntries | The optional number of most recent entries in the range |
+------------------+---------------------------------------------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

ExpandTimeSeriesFrequencyDistribution

.....................................
ExpandTimeSeriesFrequencyDistribution
.....................................


Expands a table show the frequency distribution of values in a time series.



+------------+-------------------------------+
| Parameter  | Description                   |
+============+===============================+
| timeSeries | The time series               |
+------------+-------------------------------+
| numBuckets | The desired number of buckets |
+------------+-------------------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

ExpandTrade

...........
ExpandTrade
...........


Expand the contents of a trade.



+-----------+------------------+
| Parameter | Description      |
+===========+==================+
| trade     | A trade to query |
+-----------+------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

ExpandVarianceSwapNotional

..........................
ExpandVarianceSwapNotional
..........................


Expand the contents of a notional value of the leg of a variance swap.



+----------------------+---------------------------------------------------------+
| Parameter            | Description                                             |
+======================+=========================================================+
| varianceSwapNotional | A notional value of the leg of a variance swap to query |
+----------------------+---------------------------------------------------------+



This function must be invoked as an array formula. If there is insufficient room, the minimum size of the receiving area needed will be returned.

ExternalIdBundle

................
ExternalIdBundle
................


Creates an ExternalIdBundle from one or more identifiers.



+-----------+------------------------------------------------------------------------+
| Parameter | Description                                                            |
+===========+========================================================================+
| id1       | The first identifier (or array of identifiers) to add to the bundle    |
+-----------+------------------------------------------------------------------------+
| id2       | The second identifier (or array of identifiers) to add to the bundle   |
+-----------+------------------------------------------------------------------------+
| id3       | The third identifier (or array of identifiers) to add to the bundle    |
+-----------+------------------------------------------------------------------------+
| id4       | The fourth identifier (or array of identifiers) to add to the bundle   |
+-----------+------------------------------------------------------------------------+
| id5       | The fifth identifier (or array of identifiers) to add to the bundle    |
+-----------+------------------------------------------------------------------------+
| id6       | The sixth identifier (or array of identifiers) to add to the bundle    |
+-----------+------------------------------------------------------------------------+
| id7       | The seventh identifier (or array of identifiers) to add to the bundle  |
+-----------+------------------------------------------------------------------------+
| id8       | The eighth identifier (or array of identifiers) to add to the bundle   |
+-----------+------------------------------------------------------------------------+
| id9       | The ninth identifier (or array of identifiers) to add to the bundle    |
+-----------+------------------------------------------------------------------------+
| id10      | The tenth identifier (or array of identifiers) to add to the bundle    |
+-----------+------------------------------------------------------------------------+
| id11      | The eleventh identifier (or array of identifiers) to add to the bundle |
+-----------+------------------------------------------------------------------------+
| id12      | The twelfth identifier (or array of identifiers) to add to the bundle  |
+-----------+------------------------------------------------------------------------+
| id13      | The 13th identifier (or array of identifiers) to add to the bundle     |
+-----------+------------------------------------------------------------------------+
| id14      | The 14th identifier (or array of identifiers) to add to the bundle     |
+-----------+------------------------------------------------------------------------+
| id15      | The 15th identifier (or array of identifiers) to add to the bundle     |
+-----------+------------------------------------------------------------------------+
| id16      | The 16th identifier (or array of identifiers) to add to the bundle     |
+-----------+------------------------------------------------------------------------+
| id17      | The 17th identifier (or array of identifiers) to add to the bundle     |
+-----------+------------------------------------------------------------------------+
| id18      | The 18th identifier (or array of identifiers) to add to the bundle     |
+-----------+------------------------------------------------------------------------+
| id19      | The 19th identifier (or array of identifiers) to add to the bundle     |
+-----------+------------------------------------------------------------------------+
| id20      | The 20th identifier (or array of identifiers) to add to the bundle     |
+-----------+------------------------------------------------------------------------+



ExtremeSpreadPayoffStyle

........................
ExtremeSpreadPayoffStyle
........................


Returns an object representing an 'extreme spread' option payoff style.



+-----------+---------------------+
| Parameter | Description         |
+===========+=====================+
| periodEnd | The period end      |
+-----------+---------------------+
| isReverse | The is reverse flag |
+-----------+---------------------+



