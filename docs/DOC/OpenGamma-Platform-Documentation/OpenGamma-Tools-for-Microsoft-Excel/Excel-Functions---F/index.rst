title: Excel Functions - F
shortcut: DOC:Excel Functions - F
---
FRASecurity

...........
FRASecurity
...........


Defines a forward rate agreement security. The new security is added to the Security Master and an identifier to it returned.



+--------------+-------------------------------------------+
| Parameter    | Description                               |
+==============+===========================================+
| name         | The display name or label of the security |
+--------------+-------------------------------------------+
| currency     | The currency                              |
+--------------+-------------------------------------------+
| regionId     | The region identifier                     |
+--------------+-------------------------------------------+
| startDate    | The start date                            |
+--------------+-------------------------------------------+
| endDate      | The end date                              |
+--------------+-------------------------------------------+
| rate         | The rate as a decimal (e.g. 5% = 0.05)    |
+--------------+-------------------------------------------+
| amount       | The notional amount                       |
+--------------+-------------------------------------------+
| underlyingId | The underlying identifier                 |
+--------------+-------------------------------------------+
| fixingDate   | The fixing date                           |
+--------------+-------------------------------------------+



FRASecurityAmount

.................
FRASecurityAmount
.................


Returns the notional amount from a forward rate agreement security.



+-------------+--------------------------------------------+
| Parameter   | Description                                |
+=============+============================================+
| FRASecurity | A forward rate agreement security to query |
+-------------+--------------------------------------------+



FRASecurityCurrency

...................
FRASecurityCurrency
...................


Returns the currency from a forward rate agreement security.



+-------------+--------------------------------------------+
| Parameter   | Description                                |
+=============+============================================+
| FRASecurity | A forward rate agreement security to query |
+-------------+--------------------------------------------+



FRASecurityEndDate

..................
FRASecurityEndDate
..................


Returns the end date from a forward rate agreement security.



+-------------+--------------------------------------------+
| Parameter   | Description                                |
+=============+============================================+
| FRASecurity | A forward rate agreement security to query |
+-------------+--------------------------------------------+



FRASecurityFixingDate

.....................
FRASecurityFixingDate
.....................


Returns the fixing date from a forward rate agreement security.



+-------------+--------------------------------------------+
| Parameter   | Description                                |
+=============+============================================+
| FRASecurity | A forward rate agreement security to query |
+-------------+--------------------------------------------+



FRASecurityObject

.................
FRASecurityObject
.................


Defines a forward rate agreement security.



+--------------+-------------------------------------------+
| Parameter    | Description                               |
+==============+===========================================+
| name         | The display name or label of the security |
+--------------+-------------------------------------------+
| currency     | The currency                              |
+--------------+-------------------------------------------+
| regionId     | The region identifier                     |
+--------------+-------------------------------------------+
| startDate    | The start date                            |
+--------------+-------------------------------------------+
| endDate      | The end date                              |
+--------------+-------------------------------------------+
| rate         | The rate as a decimal (e.g. 5% = 0.05)    |
+--------------+-------------------------------------------+
| amount       | The notional amount                       |
+--------------+-------------------------------------------+
| underlyingId | The underlying identifier                 |
+--------------+-------------------------------------------+
| fixingDate   | The fixing date                           |
+--------------+-------------------------------------------+



FRASecurityRate

...............
FRASecurityRate
...............


Returns the rate as a decimal (e.g. 5% = 0.05) from a forward rate agreement security.



+-------------+--------------------------------------------+
| Parameter   | Description                                |
+=============+============================================+
| FRASecurity | A forward rate agreement security to query |
+-------------+--------------------------------------------+



FRASecurityRegionId

...................
FRASecurityRegionId
...................


Returns the region identifier from a forward rate agreement security.



+-------------+--------------------------------------------+
| Parameter   | Description                                |
+=============+============================================+
| FRASecurity | A forward rate agreement security to query |
+-------------+--------------------------------------------+



FRASecuritySetAmount

....................
FRASecuritySetAmount
....................


Updates the notional amount of a forward rate agreement security. The original object is unchanged - a new object is returned with the updated value.



+-------------+---------------------------------------------+
| Parameter   | Description                                 |
+=============+=============================================+
| FRASecurity | A forward rate agreement security to update |
+-------------+---------------------------------------------+
| amount      | The notional amount                         |
+-------------+---------------------------------------------+



FRASecuritySetCurrency

......................
FRASecuritySetCurrency
......................


Updates the currency of a forward rate agreement security. The original object is unchanged - a new object is returned with the updated value.



+-------------+---------------------------------------------+
| Parameter   | Description                                 |
+=============+=============================================+
| FRASecurity | A forward rate agreement security to update |
+-------------+---------------------------------------------+
| currency    | The currency                                |
+-------------+---------------------------------------------+



FRASecuritySetEndDate

.....................
FRASecuritySetEndDate
.....................


Updates the end date of a forward rate agreement security. The original object is unchanged - a new object is returned with the updated value.



+-------------+---------------------------------------------+
| Parameter   | Description                                 |
+=============+=============================================+
| FRASecurity | A forward rate agreement security to update |
+-------------+---------------------------------------------+
| endDate     | The end date                                |
+-------------+---------------------------------------------+



FRASecuritySetFixingDate

........................
FRASecuritySetFixingDate
........................


Updates the fixing date of a forward rate agreement security. The original object is unchanged - a new object is returned with the updated value.



+-------------+---------------------------------------------+
| Parameter   | Description                                 |
+=============+=============================================+
| FRASecurity | A forward rate agreement security to update |
+-------------+---------------------------------------------+
| fixingDate  | The fixing date                             |
+-------------+---------------------------------------------+



FRASecuritySetRate

..................
FRASecuritySetRate
..................


Updates the rate as a decimal (e.g. 5% = 0.05) of a forward rate agreement security. The original object is unchanged - a new object is returned with the updated value.



+-------------+---------------------------------------------+
| Parameter   | Description                                 |
+=============+=============================================+
| FRASecurity | A forward rate agreement security to update |
+-------------+---------------------------------------------+
| rate        | The rate as a decimal (e.g. 5% = 0.05)      |
+-------------+---------------------------------------------+



FRASecuritySetRegionId

......................
FRASecuritySetRegionId
......................


Updates the region identifier of a forward rate agreement security. The original object is unchanged - a new object is returned with the updated value.



+-------------+---------------------------------------------+
| Parameter   | Description                                 |
+=============+=============================================+
| FRASecurity | A forward rate agreement security to update |
+-------------+---------------------------------------------+
| regionId    | The region identifier                       |
+-------------+---------------------------------------------+



FRASecuritySetStartDate

.......................
FRASecuritySetStartDate
.......................


Updates the start date of a forward rate agreement security. The original object is unchanged - a new object is returned with the updated value.



+-------------+---------------------------------------------+
| Parameter   | Description                                 |
+=============+=============================================+
| FRASecurity | A forward rate agreement security to update |
+-------------+---------------------------------------------+
| startDate   | The start date                              |
+-------------+---------------------------------------------+



FRASecuritySetUnderlyingId

..........................
FRASecuritySetUnderlyingId
..........................


Updates the underlying identifier of a forward rate agreement security. The original object is unchanged - a new object is returned with the updated value.



+--------------+---------------------------------------------+
| Parameter    | Description                                 |
+==============+=============================================+
| FRASecurity  | A forward rate agreement security to update |
+--------------+---------------------------------------------+
| underlyingId | The underlying identifier                   |
+--------------+---------------------------------------------+



FRASecurityStartDate

....................
FRASecurityStartDate
....................


Returns the start date from a forward rate agreement security.



+-------------+--------------------------------------------+
| Parameter   | Description                                |
+=============+============================================+
| FRASecurity | A forward rate agreement security to query |
+-------------+--------------------------------------------+



FRASecurityUnderlyingId

.......................
FRASecurityUnderlyingId
.......................


Returns the underlying identifier from a forward rate agreement security.



+-------------+--------------------------------------------+
| Parameter   | Description                                |
+=============+============================================+
| FRASecurity | A forward rate agreement security to query |
+-------------+--------------------------------------------+



FXBarrierOptionSecurity

.......................
FXBarrierOptionSecurity
.......................


Defines a FX barrier option security. The new security is added to the Security Master and an identifier to it returned.



+-------------------+-------------------------------------------+
| Parameter         | Description                               |
+===================+===========================================+
| name              | The display name or label of the security |
+-------------------+-------------------------------------------+
| putCurrency       | The put currency                          |
+-------------------+-------------------------------------------+
| callCurrency      | The call currency                         |
+-------------------+-------------------------------------------+
| putAmount         | The put amount                            |
+-------------------+-------------------------------------------+
| callAmount        | The call amount                           |
+-------------------+-------------------------------------------+
| expiry            | The expiry                                |
+-------------------+-------------------------------------------+
| settlementDate    | The settlement date                       |
+-------------------+-------------------------------------------+
| barrierType       | The barrier type                          |
+-------------------+-------------------------------------------+
| barrierDirection  | The barrier direction                     |
+-------------------+-------------------------------------------+
| monitoringType    | The monitoring type                       |
+-------------------+-------------------------------------------+
| samplingFrequency | The sampling frequency                    |
+-------------------+-------------------------------------------+
| barrierLevel      | The barrier level                         |
+-------------------+-------------------------------------------+
| long              | The long flag                             |
+-------------------+-------------------------------------------+



FXBarrierOptionSecurityBarrierDirection

.......................................
FXBarrierOptionSecurityBarrierDirection
.......................................


Returns the barrier direction from a FX barrier option security.



+-------------------------+---------------------------------------+
| Parameter               | Description                           |
+=========================+=======================================+
| FXBarrierOptionSecurity | A FX barrier option security to query |
+-------------------------+---------------------------------------+



FXBarrierOptionSecurityBarrierLevel

...................................
FXBarrierOptionSecurityBarrierLevel
...................................


Returns the barrier level from a FX barrier option security.



+-------------------------+---------------------------------------+
| Parameter               | Description                           |
+=========================+=======================================+
| FXBarrierOptionSecurity | A FX barrier option security to query |
+-------------------------+---------------------------------------+



FXBarrierOptionSecurityBarrierType

..................................
FXBarrierOptionSecurityBarrierType
..................................


Returns the barrier type from a FX barrier option security.



+-------------------------+---------------------------------------+
| Parameter               | Description                           |
+=========================+=======================================+
| FXBarrierOptionSecurity | A FX barrier option security to query |
+-------------------------+---------------------------------------+



FXBarrierOptionSecurityCallAmount

.................................
FXBarrierOptionSecurityCallAmount
.................................


Returns the call amount from a FX barrier option security.



+-------------------------+---------------------------------------+
| Parameter               | Description                           |
+=========================+=======================================+
| FXBarrierOptionSecurity | A FX barrier option security to query |
+-------------------------+---------------------------------------+



FXBarrierOptionSecurityCallCurrency

...................................
FXBarrierOptionSecurityCallCurrency
...................................


Returns the call currency from a FX barrier option security.



+-------------------------+---------------------------------------+
| Parameter               | Description                           |
+=========================+=======================================+
| FXBarrierOptionSecurity | A FX barrier option security to query |
+-------------------------+---------------------------------------+



FXBarrierOptionSecurityExpiry

.............................
FXBarrierOptionSecurityExpiry
.............................


Returns the expiry from a FX barrier option security.



+-------------------------+---------------------------------------+
| Parameter               | Description                           |
+=========================+=======================================+
| FXBarrierOptionSecurity | A FX barrier option security to query |
+-------------------------+---------------------------------------+



FXBarrierOptionSecurityLong

...........................
FXBarrierOptionSecurityLong
...........................


Returns the long flag from a FX barrier option security.



+-------------------------+---------------------------------------+
| Parameter               | Description                           |
+=========================+=======================================+
| FXBarrierOptionSecurity | A FX barrier option security to query |
+-------------------------+---------------------------------------+



FXBarrierOptionSecurityMonitoringType

.....................................
FXBarrierOptionSecurityMonitoringType
.....................................


Returns the monitoring type from a FX barrier option security.



+-------------------------+---------------------------------------+
| Parameter               | Description                           |
+=========================+=======================================+
| FXBarrierOptionSecurity | A FX barrier option security to query |
+-------------------------+---------------------------------------+



FXBarrierOptionSecurityObject

.............................
FXBarrierOptionSecurityObject
.............................


Defines a FX barrier option security.



+-------------------+-------------------------------------------+
| Parameter         | Description                               |
+===================+===========================================+
| name              | The display name or label of the security |
+-------------------+-------------------------------------------+
| putCurrency       | The put currency                          |
+-------------------+-------------------------------------------+
| callCurrency      | The call currency                         |
+-------------------+-------------------------------------------+
| putAmount         | The put amount                            |
+-------------------+-------------------------------------------+
| callAmount        | The call amount                           |
+-------------------+-------------------------------------------+
| expiry            | The expiry                                |
+-------------------+-------------------------------------------+
| settlementDate    | The settlement date                       |
+-------------------+-------------------------------------------+
| barrierType       | The barrier type                          |
+-------------------+-------------------------------------------+
| barrierDirection  | The barrier direction                     |
+-------------------+-------------------------------------------+
| monitoringType    | The monitoring type                       |
+-------------------+-------------------------------------------+
| samplingFrequency | The sampling frequency                    |
+-------------------+-------------------------------------------+
| barrierLevel      | The barrier level                         |
+-------------------+-------------------------------------------+
| long              | The long flag                             |
+-------------------+-------------------------------------------+



FXBarrierOptionSecurityPutAmount

................................
FXBarrierOptionSecurityPutAmount
................................


Returns the put amount from a FX barrier option security.



+-------------------------+---------------------------------------+
| Parameter               | Description                           |
+=========================+=======================================+
| FXBarrierOptionSecurity | A FX barrier option security to query |
+-------------------------+---------------------------------------+



FXBarrierOptionSecurityPutCurrency

..................................
FXBarrierOptionSecurityPutCurrency
..................................


Returns the put currency from a FX barrier option security.



+-------------------------+---------------------------------------+
| Parameter               | Description                           |
+=========================+=======================================+
| FXBarrierOptionSecurity | A FX barrier option security to query |
+-------------------------+---------------------------------------+



FXBarrierOptionSecuritySamplingFrequency

........................................
FXBarrierOptionSecuritySamplingFrequency
........................................


Returns the sampling frequency from a FX barrier option security.



+-------------------------+---------------------------------------+
| Parameter               | Description                           |
+=========================+=======================================+
| FXBarrierOptionSecurity | A FX barrier option security to query |
+-------------------------+---------------------------------------+



FXBarrierOptionSecuritySetBarrierDirection

..........................................
FXBarrierOptionSecuritySetBarrierDirection
..........................................


Updates the barrier direction of a FX barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------------------------------------+
| Parameter               | Description                            |
+=========================+========================================+
| FXBarrierOptionSecurity | A FX barrier option security to update |
+-------------------------+----------------------------------------+
| barrierDirection        | The barrier direction                  |
+-------------------------+----------------------------------------+



FXBarrierOptionSecuritySetBarrierLevel

......................................
FXBarrierOptionSecuritySetBarrierLevel
......................................


Updates the barrier level of a FX barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------------------------------------+
| Parameter               | Description                            |
+=========================+========================================+
| FXBarrierOptionSecurity | A FX barrier option security to update |
+-------------------------+----------------------------------------+
| barrierLevel            | The barrier level                      |
+-------------------------+----------------------------------------+



FXBarrierOptionSecuritySetBarrierType

.....................................
FXBarrierOptionSecuritySetBarrierType
.....................................


Updates the barrier type of a FX barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------------------------------------+
| Parameter               | Description                            |
+=========================+========================================+
| FXBarrierOptionSecurity | A FX barrier option security to update |
+-------------------------+----------------------------------------+
| barrierType             | The barrier type                       |
+-------------------------+----------------------------------------+



FXBarrierOptionSecuritySetCallAmount

....................................
FXBarrierOptionSecuritySetCallAmount
....................................


Updates the call amount of a FX barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------------------------------------+
| Parameter               | Description                            |
+=========================+========================================+
| FXBarrierOptionSecurity | A FX barrier option security to update |
+-------------------------+----------------------------------------+
| callAmount              | The call amount                        |
+-------------------------+----------------------------------------+



FXBarrierOptionSecuritySetCallCurrency

......................................
FXBarrierOptionSecuritySetCallCurrency
......................................


Updates the call currency of a FX barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------------------------------------+
| Parameter               | Description                            |
+=========================+========================================+
| FXBarrierOptionSecurity | A FX barrier option security to update |
+-------------------------+----------------------------------------+
| callCurrency            | The call currency                      |
+-------------------------+----------------------------------------+



FXBarrierOptionSecuritySetExpiry

................................
FXBarrierOptionSecuritySetExpiry
................................


Updates the expiry of a FX barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------------------------------------+
| Parameter               | Description                            |
+=========================+========================================+
| FXBarrierOptionSecurity | A FX barrier option security to update |
+-------------------------+----------------------------------------+
| expiry                  | The expiry                             |
+-------------------------+----------------------------------------+



FXBarrierOptionSecuritySetMonitoringType

........................................
FXBarrierOptionSecuritySetMonitoringType
........................................


Updates the monitoring type of a FX barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------------------------------------+
| Parameter               | Description                            |
+=========================+========================================+
| FXBarrierOptionSecurity | A FX barrier option security to update |
+-------------------------+----------------------------------------+
| monitoringType          | The monitoring type                    |
+-------------------------+----------------------------------------+



FXBarrierOptionSecuritySetPutAmount

...................................
FXBarrierOptionSecuritySetPutAmount
...................................


Updates the put amount of a FX barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------------------------------------+
| Parameter               | Description                            |
+=========================+========================================+
| FXBarrierOptionSecurity | A FX barrier option security to update |
+-------------------------+----------------------------------------+
| putAmount               | The put amount                         |
+-------------------------+----------------------------------------+



FXBarrierOptionSecuritySetPutCurrency

.....................................
FXBarrierOptionSecuritySetPutCurrency
.....................................


Updates the put currency of a FX barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------------------------------------+
| Parameter               | Description                            |
+=========================+========================================+
| FXBarrierOptionSecurity | A FX barrier option security to update |
+-------------------------+----------------------------------------+
| putCurrency             | The put currency                       |
+-------------------------+----------------------------------------+



FXBarrierOptionSecuritySetSamplingFrequency

...........................................
FXBarrierOptionSecuritySetSamplingFrequency
...........................................


Updates the sampling frequency of a FX barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------------------------------------+
| Parameter               | Description                            |
+=========================+========================================+
| FXBarrierOptionSecurity | A FX barrier option security to update |
+-------------------------+----------------------------------------+
| samplingFrequency       | The sampling frequency                 |
+-------------------------+----------------------------------------+



FXBarrierOptionSecuritySetSettlementDate

........................................
FXBarrierOptionSecuritySetSettlementDate
........................................


Updates the settlement date of a FX barrier option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+----------------------------------------+
| Parameter               | Description                            |
+=========================+========================================+
| FXBarrierOptionSecurity | A FX barrier option security to update |
+-------------------------+----------------------------------------+
| settlementDate          | The settlement date                    |
+-------------------------+----------------------------------------+



FXBarrierOptionSecuritySettlementDate

.....................................
FXBarrierOptionSecuritySettlementDate
.....................................


Returns the settlement date from a FX barrier option security.



+-------------------------+---------------------------------------+
| Parameter               | Description                           |
+=========================+=======================================+
| FXBarrierOptionSecurity | A FX barrier option security to query |
+-------------------------+---------------------------------------+



FXDigitalOptionSecurity

.......................
FXDigitalOptionSecurity
.......................


Defines an FX digital option security. The new security is added to the Security Master and an identifier to it returned.



+-----------------+-------------------------------------------+
| Parameter       | Description                               |
+=================+===========================================+
| name            | The display name or label of the security |
+-----------------+-------------------------------------------+
| putCurrency     | The put currency                          |
+-----------------+-------------------------------------------+
| callCurrency    | The call currency                         |
+-----------------+-------------------------------------------+
| putAmount       | The put amount                            |
+-----------------+-------------------------------------------+
| callAmount      | The call amount                           |
+-----------------+-------------------------------------------+
| paymentCurrency | The payment currency                      |
+-----------------+-------------------------------------------+
| expiry          | The expiry                                |
+-----------------+-------------------------------------------+
| settlementDate  | The settlement date                       |
+-----------------+-------------------------------------------+
| long            | The long flag                             |
+-----------------+-------------------------------------------+



FXDigitalOptionSecurityCallAmount

.................................
FXDigitalOptionSecurityCallAmount
.................................


Returns the call amount from an FX digital option security.



+-------------------------+----------------------------------------+
| Parameter               | Description                            |
+=========================+========================================+
| FXDigitalOptionSecurity | An FX digital option security to query |
+-------------------------+----------------------------------------+



FXDigitalOptionSecurityCallCurrency

...................................
FXDigitalOptionSecurityCallCurrency
...................................


Returns the call currency from an FX digital option security.



+-------------------------+----------------------------------------+
| Parameter               | Description                            |
+=========================+========================================+
| FXDigitalOptionSecurity | An FX digital option security to query |
+-------------------------+----------------------------------------+



FXDigitalOptionSecurityExpiry

.............................
FXDigitalOptionSecurityExpiry
.............................


Returns the expiry from an FX digital option security.



+-------------------------+----------------------------------------+
| Parameter               | Description                            |
+=========================+========================================+
| FXDigitalOptionSecurity | An FX digital option security to query |
+-------------------------+----------------------------------------+



FXDigitalOptionSecurityLong

...........................
FXDigitalOptionSecurityLong
...........................


Returns the long flag from an FX digital option security.



+-------------------------+----------------------------------------+
| Parameter               | Description                            |
+=========================+========================================+
| FXDigitalOptionSecurity | An FX digital option security to query |
+-------------------------+----------------------------------------+



FXDigitalOptionSecurityObject

.............................
FXDigitalOptionSecurityObject
.............................


Defines an FX digital option security.



+-----------------+-------------------------------------------+
| Parameter       | Description                               |
+=================+===========================================+
| name            | The display name or label of the security |
+-----------------+-------------------------------------------+
| putCurrency     | The put currency                          |
+-----------------+-------------------------------------------+
| callCurrency    | The call currency                         |
+-----------------+-------------------------------------------+
| putAmount       | The put amount                            |
+-----------------+-------------------------------------------+
| callAmount      | The call amount                           |
+-----------------+-------------------------------------------+
| paymentCurrency | The payment currency                      |
+-----------------+-------------------------------------------+
| expiry          | The expiry                                |
+-----------------+-------------------------------------------+
| settlementDate  | The settlement date                       |
+-----------------+-------------------------------------------+
| long            | The long flag                             |
+-----------------+-------------------------------------------+



FXDigitalOptionSecurityPaymentCurrency

......................................
FXDigitalOptionSecurityPaymentCurrency
......................................


Returns the payment currency from an FX digital option security.



+-------------------------+----------------------------------------+
| Parameter               | Description                            |
+=========================+========================================+
| FXDigitalOptionSecurity | An FX digital option security to query |
+-------------------------+----------------------------------------+



FXDigitalOptionSecurityPutAmount

................................
FXDigitalOptionSecurityPutAmount
................................


Returns the put amount from an FX digital option security.



+-------------------------+----------------------------------------+
| Parameter               | Description                            |
+=========================+========================================+
| FXDigitalOptionSecurity | An FX digital option security to query |
+-------------------------+----------------------------------------+



FXDigitalOptionSecurityPutCurrency

..................................
FXDigitalOptionSecurityPutCurrency
..................................


Returns the put currency from an FX digital option security.



+-------------------------+----------------------------------------+
| Parameter               | Description                            |
+=========================+========================================+
| FXDigitalOptionSecurity | An FX digital option security to query |
+-------------------------+----------------------------------------+



FXDigitalOptionSecuritySetCallAmount

....................................
FXDigitalOptionSecuritySetCallAmount
....................................


Updates the call amount of an FX digital option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+-----------------------------------------+
| Parameter               | Description                             |
+=========================+=========================================+
| FXDigitalOptionSecurity | An FX digital option security to update |
+-------------------------+-----------------------------------------+
| callAmount              | The call amount                         |
+-------------------------+-----------------------------------------+



FXDigitalOptionSecuritySetCallCurrency

......................................
FXDigitalOptionSecuritySetCallCurrency
......................................


Updates the call currency of an FX digital option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+-----------------------------------------+
| Parameter               | Description                             |
+=========================+=========================================+
| FXDigitalOptionSecurity | An FX digital option security to update |
+-------------------------+-----------------------------------------+
| callCurrency            | The call currency                       |
+-------------------------+-----------------------------------------+



FXDigitalOptionSecuritySetExpiry

................................
FXDigitalOptionSecuritySetExpiry
................................


Updates the expiry of an FX digital option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+-----------------------------------------+
| Parameter               | Description                             |
+=========================+=========================================+
| FXDigitalOptionSecurity | An FX digital option security to update |
+-------------------------+-----------------------------------------+
| expiry                  | The expiry                              |
+-------------------------+-----------------------------------------+



FXDigitalOptionSecuritySetPaymentCurrency

.........................................
FXDigitalOptionSecuritySetPaymentCurrency
.........................................


Updates the payment currency of an FX digital option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+-----------------------------------------+
| Parameter               | Description                             |
+=========================+=========================================+
| FXDigitalOptionSecurity | An FX digital option security to update |
+-------------------------+-----------------------------------------+
| paymentCurrency         | The payment currency                    |
+-------------------------+-----------------------------------------+



FXDigitalOptionSecuritySetPutAmount

...................................
FXDigitalOptionSecuritySetPutAmount
...................................


Updates the put amount of an FX digital option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+-----------------------------------------+
| Parameter               | Description                             |
+=========================+=========================================+
| FXDigitalOptionSecurity | An FX digital option security to update |
+-------------------------+-----------------------------------------+
| putAmount               | The put amount                          |
+-------------------------+-----------------------------------------+



FXDigitalOptionSecuritySetPutCurrency

.....................................
FXDigitalOptionSecuritySetPutCurrency
.....................................


Updates the put currency of an FX digital option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+-----------------------------------------+
| Parameter               | Description                             |
+=========================+=========================================+
| FXDigitalOptionSecurity | An FX digital option security to update |
+-------------------------+-----------------------------------------+
| putCurrency             | The put currency                        |
+-------------------------+-----------------------------------------+



FXDigitalOptionSecuritySetSettlementDate

........................................
FXDigitalOptionSecuritySetSettlementDate
........................................


Updates the settlement date of an FX digital option security. The original object is unchanged - a new object is returned with the updated value.



+-------------------------+-----------------------------------------+
| Parameter               | Description                             |
+=========================+=========================================+
| FXDigitalOptionSecurity | An FX digital option security to update |
+-------------------------+-----------------------------------------+
| settlementDate          | The settlement date                     |
+-------------------------+-----------------------------------------+



FXDigitalOptionSecuritySettlementDate

.....................................
FXDigitalOptionSecuritySettlementDate
.....................................


Returns the settlement date from an FX digital option security.



+-------------------------+----------------------------------------+
| Parameter               | Description                            |
+=========================+========================================+
| FXDigitalOptionSecurity | An FX digital option security to query |
+-------------------------+----------------------------------------+



FXForwardSecurity

.................
FXForwardSecurity
.................


Defines an FX forward security. The new security is added to the Security Master and an identifier to it returned.



+-----------------+-------------------------------------------+
| Parameter       | Description                               |
+=================+===========================================+
| name            | The display name or label of the security |
+-----------------+-------------------------------------------+
| payAmount       | The pay amount                            |
+-----------------+-------------------------------------------+
| payCurrency     | The pay currency                          |
+-----------------+-------------------------------------------+
| receiveAmount   | The receive amount                        |
+-----------------+-------------------------------------------+
| receiveCurrency | The receive currency                      |
+-----------------+-------------------------------------------+
| forwardDate     | The forward date                          |
+-----------------+-------------------------------------------+
| regionId        | The identifier of the region              |
+-----------------+-------------------------------------------+



FXForwardSecurityForwardDate

............................
FXForwardSecurityForwardDate
............................


Returns the forward date from an FX forward security.



+-------------------+---------------------------------+
| Parameter         | Description                     |
+===================+=================================+
| FXForwardSecurity | An FX forward security to query |
+-------------------+---------------------------------+



FXForwardSecurityObject

.......................
FXForwardSecurityObject
.......................


Defines an FX forward security.



+-----------------+-------------------------------------------+
| Parameter       | Description                               |
+=================+===========================================+
| name            | The display name or label of the security |
+-----------------+-------------------------------------------+
| payAmount       | The pay amount                            |
+-----------------+-------------------------------------------+
| payCurrency     | The pay currency                          |
+-----------------+-------------------------------------------+
| receiveAmount   | The receive amount                        |
+-----------------+-------------------------------------------+
| receiveCurrency | The receive currency                      |
+-----------------+-------------------------------------------+
| forwardDate     | The forward date                          |
+-----------------+-------------------------------------------+
| regionId        | The identifier of the region              |
+-----------------+-------------------------------------------+



FXForwardSecurityPayAmount

..........................
FXForwardSecurityPayAmount
..........................


Returns the pay amount from an FX forward security.



+-------------------+---------------------------------+
| Parameter         | Description                     |
+===================+=================================+
| FXForwardSecurity | An FX forward security to query |
+-------------------+---------------------------------+



FXForwardSecurityPayCurrency

............................
FXForwardSecurityPayCurrency
............................


Returns the pay currency from an FX forward security.



+-------------------+---------------------------------+
| Parameter         | Description                     |
+===================+=================================+
| FXForwardSecurity | An FX forward security to query |
+-------------------+---------------------------------+



FXForwardSecurityReceiveAmount

..............................
FXForwardSecurityReceiveAmount
..............................


Returns the receive amount from an FX forward security.



+-------------------+---------------------------------+
| Parameter         | Description                     |
+===================+=================================+
| FXForwardSecurity | An FX forward security to query |
+-------------------+---------------------------------+



FXForwardSecurityReceiveCurrency

................................
FXForwardSecurityReceiveCurrency
................................


Returns the receive currency from an FX forward security.



+-------------------+---------------------------------+
| Parameter         | Description                     |
+===================+=================================+
| FXForwardSecurity | An FX forward security to query |
+-------------------+---------------------------------+



FXForwardSecurityRegionId

.........................
FXForwardSecurityRegionId
.........................


Returns the identifier of the region from an FX forward security.



+-------------------+---------------------------------+
| Parameter         | Description                     |
+===================+=================================+
| FXForwardSecurity | An FX forward security to query |
+-------------------+---------------------------------+



FXForwardSecuritySetForwardDate

...............................
FXForwardSecuritySetForwardDate
...............................


Updates the forward date of an FX forward security. The original object is unchanged - a new object is returned with the updated value.



+-------------------+----------------------------------+
| Parameter         | Description                      |
+===================+==================================+
| FXForwardSecurity | An FX forward security to update |
+-------------------+----------------------------------+
| forwardDate       | The forward date                 |
+-------------------+----------------------------------+



FXForwardSecuritySetPayAmount

.............................
FXForwardSecuritySetPayAmount
.............................


Updates the pay amount of an FX forward security. The original object is unchanged - a new object is returned with the updated value.



+-------------------+----------------------------------+
| Parameter         | Description                      |
+===================+==================================+
| FXForwardSecurity | An FX forward security to update |
+-------------------+----------------------------------+
| payAmount         | The pay amount                   |
+-------------------+----------------------------------+



FXForwardSecuritySetPayCurrency

...............................
FXForwardSecuritySetPayCurrency
...............................


Updates the pay currency of an FX forward security. The original object is unchanged - a new object is returned with the updated value.



+-------------------+----------------------------------+
| Parameter         | Description                      |
+===================+==================================+
| FXForwardSecurity | An FX forward security to update |
+-------------------+----------------------------------+
| payCurrency       | The pay currency                 |
+-------------------+----------------------------------+



FXForwardSecuritySetReceiveAmount

.................................
FXForwardSecuritySetReceiveAmount
.................................


Updates the receive amount of an FX forward security. The original object is unchanged - a new object is returned with the updated value.



+-------------------+----------------------------------+
| Parameter         | Description                      |
+===================+==================================+
| FXForwardSecurity | An FX forward security to update |
+-------------------+----------------------------------+
| receiveAmount     | The receive amount               |
+-------------------+----------------------------------+



FXForwardSecuritySetReceiveCurrency

...................................
FXForwardSecuritySetReceiveCurrency
...................................


Updates the receive currency of an FX forward security. The original object is unchanged - a new object is returned with the updated value.



+-------------------+----------------------------------+
| Parameter         | Description                      |
+===================+==================================+
| FXForwardSecurity | An FX forward security to update |
+-------------------+----------------------------------+
| receiveCurrency   | The receive currency             |
+-------------------+----------------------------------+



FXForwardSecuritySetRegionId

............................
FXForwardSecuritySetRegionId
............................


Updates the identifier of the region of an FX forward security. The original object is unchanged - a new object is returned with the updated value.



+-------------------+----------------------------------+
| Parameter         | Description                      |
+===================+==================================+
| FXForwardSecurity | An FX forward security to update |
+-------------------+----------------------------------+
| regionId          | The identifier of the region     |
+-------------------+----------------------------------+



FXFutureSecurity

................
FXFutureSecurity
................


Defines an FX future security. The new security is added to the Security Master and an identifier to it returned.



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
| numerator          | The numerator currency                    |
+--------------------+-------------------------------------------+
| denominator        | The denominator currency                  |
+--------------------+-------------------------------------------+
| contractCategory   | The category                              |
+--------------------+-------------------------------------------+



FXFutureSecurityDenominator

...........................
FXFutureSecurityDenominator
...........................


Returns the denominator currency from an FX future security.



+------------------+--------------------------------+
| Parameter        | Description                    |
+==================+================================+
| FXFutureSecurity | An FX future security to query |
+------------------+--------------------------------+



FXFutureSecurityMultiplicationFactor

....................................
FXFutureSecurityMultiplicationFactor
....................................


Returns the multiplication factor, i.e. number of numerator units per denominator unit from an FX future security.



+------------------+--------------------------------+
| Parameter        | Description                    |
+==================+================================+
| FXFutureSecurity | An FX future security to query |
+------------------+--------------------------------+



FXFutureSecurityNumerator

.........................
FXFutureSecurityNumerator
.........................


Returns the numerator currency from an FX future security.



+------------------+--------------------------------+
| Parameter        | Description                    |
+==================+================================+
| FXFutureSecurity | An FX future security to query |
+------------------+--------------------------------+



FXFutureSecurityObject

......................
FXFutureSecurityObject
......................


Defines an FX future security.



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
| numerator          | The numerator currency                    |
+--------------------+-------------------------------------------+
| denominator        | The denominator currency                  |
+--------------------+-------------------------------------------+
| contractCategory   | The category                              |
+--------------------+-------------------------------------------+



FXFutureSecuritySetDenominator

..............................
FXFutureSecuritySetDenominator
..............................


Updates the denominator currency of an FX future security. The original object is unchanged - a new object is returned with the updated value.



+------------------+---------------------------------+
| Parameter        | Description                     |
+==================+=================================+
| FXFutureSecurity | An FX future security to update |
+------------------+---------------------------------+
| denominator      | The denominator currency        |
+------------------+---------------------------------+



FXFutureSecuritySetMultiplicationFactor

.......................................
FXFutureSecuritySetMultiplicationFactor
.......................................


Updates the multiplication factor, i.e. number of numerator units per denominator unit of an FX future security. The original object is unchanged - a new object is returned with the updated value.



+----------------------+--------------------------------------------------------------------------------+
| Parameter            | Description                                                                    |
+======================+================================================================================+
| FXFutureSecurity     | An FX future security to update                                                |
+----------------------+--------------------------------------------------------------------------------+
| multiplicationFactor | The multiplication factor, i.e. number of numerator units per denominator unit |
+----------------------+--------------------------------------------------------------------------------+



FXFutureSecuritySetNumerator

............................
FXFutureSecuritySetNumerator
............................


Updates the numerator currency of an FX future security. The original object is unchanged - a new object is returned with the updated value.



+------------------+---------------------------------+
| Parameter        | Description                     |
+==================+=================================+
| FXFutureSecurity | An FX future security to update |
+------------------+---------------------------------+
| numerator        | The numerator currency          |
+------------------+---------------------------------+



FXOptionSecurity

................
FXOptionSecurity
................


Defines an FX option security. The new security is added to the Security Master and an identifier to it returned.



+----------------+-------------------------------------------+
| Parameter      | Description                               |
+================+===========================================+
| name           | The display name or label of the security |
+----------------+-------------------------------------------+
| putCurrency    | The put currency                          |
+----------------+-------------------------------------------+
| callCurrency   | The call currency                         |
+----------------+-------------------------------------------+
| putAmount      | The put amount                            |
+----------------+-------------------------------------------+
| callAmount     | The call amount                           |
+----------------+-------------------------------------------+
| expiry         | The expiry                                |
+----------------+-------------------------------------------+
| settlementDate | The settlement date                       |
+----------------+-------------------------------------------+
| long           | The long flag                             |
+----------------+-------------------------------------------+
| exerciseType   | The exercise type                         |
+----------------+-------------------------------------------+



FXOptionSecurityCallAmount

..........................
FXOptionSecurityCallAmount
..........................


Returns the call amount from an FX option security.



+------------------+--------------------------------+
| Parameter        | Description                    |
+==================+================================+
| FXOptionSecurity | An FX option security to query |
+------------------+--------------------------------+



FXOptionSecurityCallCurrency

............................
FXOptionSecurityCallCurrency
............................


Returns the call currency from an FX option security.



+------------------+--------------------------------+
| Parameter        | Description                    |
+==================+================================+
| FXOptionSecurity | An FX option security to query |
+------------------+--------------------------------+



FXOptionSecurityExerciseType

............................
FXOptionSecurityExerciseType
............................


Returns the exercise type from an FX option security.



+------------------+--------------------------------+
| Parameter        | Description                    |
+==================+================================+
| FXOptionSecurity | An FX option security to query |
+------------------+--------------------------------+



FXOptionSecurityExpiry

......................
FXOptionSecurityExpiry
......................


Returns the expiry from an FX option security.



+------------------+--------------------------------+
| Parameter        | Description                    |
+==================+================================+
| FXOptionSecurity | An FX option security to query |
+------------------+--------------------------------+



FXOptionSecurityLong

....................
FXOptionSecurityLong
....................


Returns the long flag from an FX option security.



+------------------+--------------------------------+
| Parameter        | Description                    |
+==================+================================+
| FXOptionSecurity | An FX option security to query |
+------------------+--------------------------------+



FXOptionSecurityObject

......................
FXOptionSecurityObject
......................


Defines an FX option security.



+----------------+-------------------------------------------+
| Parameter      | Description                               |
+================+===========================================+
| name           | The display name or label of the security |
+----------------+-------------------------------------------+
| putCurrency    | The put currency                          |
+----------------+-------------------------------------------+
| callCurrency   | The call currency                         |
+----------------+-------------------------------------------+
| putAmount      | The put amount                            |
+----------------+-------------------------------------------+
| callAmount     | The call amount                           |
+----------------+-------------------------------------------+
| expiry         | The expiry                                |
+----------------+-------------------------------------------+
| settlementDate | The settlement date                       |
+----------------+-------------------------------------------+
| long           | The long flag                             |
+----------------+-------------------------------------------+
| exerciseType   | The exercise type                         |
+----------------+-------------------------------------------+



FXOptionSecurityPutAmount

.........................
FXOptionSecurityPutAmount
.........................


Returns the put amount from an FX option security.



+------------------+--------------------------------+
| Parameter        | Description                    |
+==================+================================+
| FXOptionSecurity | An FX option security to query |
+------------------+--------------------------------+



FXOptionSecurityPutCurrency

...........................
FXOptionSecurityPutCurrency
...........................


Returns the put currency from an FX option security.



+------------------+--------------------------------+
| Parameter        | Description                    |
+==================+================================+
| FXOptionSecurity | An FX option security to query |
+------------------+--------------------------------+



FXOptionSecuritySetCallAmount

.............................
FXOptionSecuritySetCallAmount
.............................


Updates the call amount of an FX option security. The original object is unchanged - a new object is returned with the updated value.



+------------------+---------------------------------+
| Parameter        | Description                     |
+==================+=================================+
| FXOptionSecurity | An FX option security to update |
+------------------+---------------------------------+
| callAmount       | The call amount                 |
+------------------+---------------------------------+



FXOptionSecuritySetCallCurrency

...............................
FXOptionSecuritySetCallCurrency
...............................


Updates the call currency of an FX option security. The original object is unchanged - a new object is returned with the updated value.



+------------------+---------------------------------+
| Parameter        | Description                     |
+==================+=================================+
| FXOptionSecurity | An FX option security to update |
+------------------+---------------------------------+
| callCurrency     | The call currency               |
+------------------+---------------------------------+



FXOptionSecuritySetExerciseType

...............................
FXOptionSecuritySetExerciseType
...............................


Updates the exercise type of an FX option security. The original object is unchanged - a new object is returned with the updated value.



+------------------+---------------------------------+
| Parameter        | Description                     |
+==================+=================================+
| FXOptionSecurity | An FX option security to update |
+------------------+---------------------------------+
| exerciseType     | The exercise type               |
+------------------+---------------------------------+



FXOptionSecuritySetExpiry

.........................
FXOptionSecuritySetExpiry
.........................


Updates the expiry of an FX option security. The original object is unchanged - a new object is returned with the updated value.



+------------------+---------------------------------+
| Parameter        | Description                     |
+==================+=================================+
| FXOptionSecurity | An FX option security to update |
+------------------+---------------------------------+
| expiry           | The expiry                      |
+------------------+---------------------------------+



FXOptionSecuritySetPutAmount

............................
FXOptionSecuritySetPutAmount
............................


Updates the put amount of an FX option security. The original object is unchanged - a new object is returned with the updated value.



+------------------+---------------------------------+
| Parameter        | Description                     |
+==================+=================================+
| FXOptionSecurity | An FX option security to update |
+------------------+---------------------------------+
| putAmount        | The put amount                  |
+------------------+---------------------------------+



FXOptionSecuritySetPutCurrency

..............................
FXOptionSecuritySetPutCurrency
..............................


Updates the put currency of an FX option security. The original object is unchanged - a new object is returned with the updated value.



+------------------+---------------------------------+
| Parameter        | Description                     |
+==================+=================================+
| FXOptionSecurity | An FX option security to update |
+------------------+---------------------------------+
| putCurrency      | The put currency                |
+------------------+---------------------------------+



FXOptionSecuritySetSettlementDate

.................................
FXOptionSecuritySetSettlementDate
.................................


Updates the settlement date of an FX option security. The original object is unchanged - a new object is returned with the updated value.



+------------------+---------------------------------+
| Parameter        | Description                     |
+==================+=================================+
| FXOptionSecurity | An FX option security to update |
+------------------+---------------------------------+
| settlementDate   | The settlement date             |
+------------------+---------------------------------+



FXOptionSecuritySettlementDate

..............................
FXOptionSecuritySettlementDate
..............................


Returns the settlement date from an FX option security.



+------------------+--------------------------------+
| Parameter        | Description                    |
+==================+================================+
| FXOptionSecurity | An FX option security to query |
+------------------+--------------------------------+



FXRate

......
FXRate
......


Returns the FX rate quoted using the market convention currency pair.



+-------------------+-----------------------------------------------------+
| Parameter         | Description                                         |
+===================+=====================================================+
| currency1         | No description available                            |
+-------------------+-----------------------------------------------------+
| currency2         | No description available                            |
+-------------------+-----------------------------------------------------+
| amount1           | The amount in currency1                             |
+-------------------+-----------------------------------------------------+
| amount2           | The amount in currency2                             |
+-------------------+-----------------------------------------------------+
| currencyPairsName | Name of the set of market convention currency pairs |
+-------------------+-----------------------------------------------------+



FadeInPayoffStyle

.................
FadeInPayoffStyle
.................


Returns an object representing a 'fade in' option payoff style.



+------------+-----------------+
| Parameter  | Description     |
+============+=================+
| lowerBound | The lower bound |
+------------+-----------------+
| upperBound | The upper bound |
+------------+-----------------+



FetchPortfolio

..............
FetchPortfolio
..............


Fetches a portfolio from a Position Source, applying filters and aggregation.



+-------------+---------------------------------------------------+
| Parameter   | Description                                       |
+=============+===================================================+
| portfolio   | The identifier of the portfolio                   |
+-------------+---------------------------------------------------+
| aggregation | The aggregation order, omit for portfolio default |
+-------------+---------------------------------------------------+
| filter      | The portfolio filter expression, omit for none    |
+-------------+---------------------------------------------------+



This function is available from Visual Basic only and cannot be used on a worksheet.

FetchPosition

.............
FetchPosition
.............


Fetches a position from the position master.



+------------+---------------------------------------------------+
| Parameter  | Description                                       |
+============+===================================================+
| identifier | The unique identifier of the position to retrieve |
+------------+---------------------------------------------------+



FetchSecurity

.............
FetchSecurity
.............


Fetches security objects from the security source.



+-------------+-----------------------------------------------------------------------------------------------+
| Parameter   | Description                                                                                   |
+=============+===============================================================================================+
| identifiers | The identifier (or identifier bundle) of the security to fetch, omit if uniqueId is specified |
+-------------+-----------------------------------------------------------------------------------------------+
| uniqueId    | The unique identifier of the security to fetch, omit if identifiers is specified              |
+-------------+-----------------------------------------------------------------------------------------------+



FetchSnapshot

.............
FetchSnapshot
.............


Retrieves a values from the system .



+------------+------------------------------+
| Parameter  | Description                  |
+============+==============================+
| identifier | The identifier of the values |
+------------+------------------------------+



FetchTimeSeries

...............
FetchTimeSeries
...............


Retrieves a time-series from the system .



+------------------------+------------------------------------------------------------------------+
| Parameter              | Description                                                            |
+========================+========================================================================+
| identifier             | The identifier or identifier bundle of the time-series to load         |
+------------------------+------------------------------------------------------------------------+
| start                  | The start date, omit to load from the earliest date available          |
+------------------------+------------------------------------------------------------------------+
| end                    | The end date, omit to load until the latest date available             |
+------------------------+------------------------------------------------------------------------+
| dataField              | The type of data required, e.g. PX_LAST                                |
+------------------------+------------------------------------------------------------------------+
| resolutionKey          | The key to resolve the correct time-series                             |
+------------------------+------------------------------------------------------------------------+
| inclusiveStart         | Whether to include the start date in the time-series, defaults to TRUE |
+------------------------+------------------------------------------------------------------------+
| inclusiveEnd           | Whether to include the end date in the time-series, defaults to FALSE  |
+------------------------+------------------------------------------------------------------------+
| dataSource             | The data source                                                        |
+------------------------+------------------------------------------------------------------------+
| dataProvider           | The data provider                                                      |
+------------------------+------------------------------------------------------------------------+
| identifierValidityDate | The date that the identifier must be valid on                          |
+------------------------+------------------------------------------------------------------------+
| maxPoints              | The maximum number of points to retrieve, omit to retrieve all         |
+------------------------+------------------------------------------------------------------------+



FetchViewDefinition

...................
FetchViewDefinition
...................


Fetches a stored view definition from the database.



+-----------+----------------------------------------------+
| Parameter | Description                                  |
+===========+==============================================+
| id        | The unique identifier of the view definition |
+-----------+----------------------------------------------+



FindR

.....
FindR
.....


Returns the starting position of one text string within another text string, searching from right to left. FINDR is case-sensitive.



+------------+----------------------------------------------------+
| Parameter  | Description                                        |
+============+====================================================+
| findText   | The text to find. Wildcard matches are not allowed |
+------------+----------------------------------------------------+
| withinText | The text string to search through                  |
+------------+----------------------------------------------------+



This function is available on a worksheet only and cannot be used from Visual Basic.

FixedInterestRateLeg

....................
FixedInterestRateLeg
....................


Defines a fixed interest rate leg.



+-----------------------+-------------------------------------------------------+
| Parameter             | Description                                           |
+=======================+=======================================================+
| dayCount              | The dayCount                                          |
+-----------------------+-------------------------------------------------------+
| frequency             | The payment frequency                                 |
+-----------------------+-------------------------------------------------------+
| regionId              | The region identifier                                 |
+-----------------------+-------------------------------------------------------+
| businessDayConvention | The business day convention                           |
+-----------------------+-------------------------------------------------------+
| notional              | The notional                                          |
+-----------------------+-------------------------------------------------------+
| eom                   | The EOM flag                                          |
+-----------------------+-------------------------------------------------------+
| rate                  | The fixed interest rate as a decimal (e.g. 5% = 0.05) |
+-----------------------+-------------------------------------------------------+



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



+-----------------------+-------------------------------------+
| Parameter             | Description                         |
+=======================+=====================================+
| dayCount              | The dayCount                        |
+-----------------------+-------------------------------------+
| frequency             | The payment frequency               |
+-----------------------+-------------------------------------+
| regionId              | The region identifier               |
+-----------------------+-------------------------------------+
| businessDayConvention | The business day convention         |
+-----------------------+-------------------------------------+
| notional              | The notional                        |
+-----------------------+-------------------------------------+
| eom                   | The EOM flag                        |
+-----------------------+-------------------------------------+
| strike                | The the strike of the variance swap |
+-----------------------+-------------------------------------+
| type                  | The the type of the variance swap   |
+-----------------------+-------------------------------------+



FloatingGearingIRLeg

....................
FloatingGearingIRLeg
....................


Defines a floating gearing interest rate leg of a swap.



+-------------------------+-----------------------------------------------------------------+
| Parameter               | Description                                                     |
+=========================+=================================================================+
| dayCount                | The dayCount                                                    |
+-------------------------+-----------------------------------------------------------------+
| frequency               | The payment frequency                                           |
+-------------------------+-----------------------------------------------------------------+
| regionId                | The region identifier                                           |
+-------------------------+-----------------------------------------------------------------+
| businessDayConvention   | The business day convention                                     |
+-------------------------+-----------------------------------------------------------------+
| notional                | The notional                                                    |
+-------------------------+-----------------------------------------------------------------+
| eom                     | The EOM flag                                                    |
+-------------------------+-----------------------------------------------------------------+
| floatingReferenceRateId | The identifier of the object used to provide the reference rate |
+-------------------------+-----------------------------------------------------------------+
| floatingRateType        | The floating rate type                                          |
+-------------------------+-----------------------------------------------------------------+
| gearing                 | The gearing                                                     |
+-------------------------+-----------------------------------------------------------------+



FloatingInterestRateLeg

.......................
FloatingInterestRateLeg
.......................


Defines a floating interest rate leg of a swap.



+-------------------------+-----------------------------------------------------------------+
| Parameter               | Description                                                     |
+=========================+=================================================================+
| dayCount                | The dayCount                                                    |
+-------------------------+-----------------------------------------------------------------+
| frequency               | The payment frequency                                           |
+-------------------------+-----------------------------------------------------------------+
| regionId                | The region identifier                                           |
+-------------------------+-----------------------------------------------------------------+
| businessDayConvention   | The business day convention                                     |
+-------------------------+-----------------------------------------------------------------+
| notional                | The notional                                                    |
+-------------------------+-----------------------------------------------------------------+
| eom                     | The EOM flag                                                    |
+-------------------------+-----------------------------------------------------------------+
| floatingReferenceRateId | The identifier of the object used to provide the reference rate |
+-------------------------+-----------------------------------------------------------------+
| floatingRateType        | The floating rate type                                          |
+-------------------------+-----------------------------------------------------------------+



FloatingSpreadIRLeg

...................
FloatingSpreadIRLeg
...................


Defines a floating spread interest rate leg of a swap.



+-------------------------+-----------------------------------------------------------------+
| Parameter               | Description                                                     |
+=========================+=================================================================+
| dayCount                | The dayCount                                                    |
+-------------------------+-----------------------------------------------------------------+
| frequency               | The payment frequency                                           |
+-------------------------+-----------------------------------------------------------------+
| regionId                | The region identifier                                           |
+-------------------------+-----------------------------------------------------------------+
| businessDayConvention   | The business day convention                                     |
+-------------------------+-----------------------------------------------------------------+
| notional                | The notional                                                    |
+-------------------------+-----------------------------------------------------------------+
| eom                     | The EOM flag                                                    |
+-------------------------+-----------------------------------------------------------------+
| floatingReferenceRateId | The identifier of the object used to provide the reference rate |
+-------------------------+-----------------------------------------------------------------+
| floatingRateType        | The floating rate type                                          |
+-------------------------+-----------------------------------------------------------------+
| spread                  | The spread                                                      |
+-------------------------+-----------------------------------------------------------------+



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



+-----------------------+------------------------------------------+
| Parameter             | Description                              |
+=======================+==========================================+
| dayCount              | The dayCount                             |
+-----------------------+------------------------------------------+
| frequency             | The payment frequency                    |
+-----------------------+------------------------------------------+
| regionId              | The region identifier                    |
+-----------------------+------------------------------------------+
| businessDayConvention | The business day convention              |
+-----------------------+------------------------------------------+
| notional              | The notional                             |
+-----------------------+------------------------------------------+
| eom                   | The EOM flag                             |
+-----------------------+------------------------------------------+
| underlyingId          | The the identifier of the underlying     |
+-----------------------+------------------------------------------+
| monitoringFrequency   | The the monitoring frequency of the swap |
+-----------------------+------------------------------------------+
| annualizationFactor   | The the annualization factor             |
+-----------------------+------------------------------------------+



FormatNumber

............
FormatNumber
............


Converts a number to a string representation, abbreviating where possible. For example 10500000 is formatted as 10.5M.



+-----------+---------------------+
| Parameter | Description         |
+===========+=====================+
| value     | The value to format |
+-----------+---------------------+



This function is available on a worksheet only and cannot be used from Visual Basic.

ForwardSwapSecurity

...................
ForwardSwapSecurity
...................


Defines a forward swap security. The new security is added to the Security Master and an identifier to it returned.



+------------------+-------------------------------------------+
| Parameter        | Description                               |
+==================+===========================================+
| name             | The display name or label of the security |
+------------------+-------------------------------------------+
| tradeDate        | The trade date                            |
+------------------+-------------------------------------------+
| effectiveDate    | The 'effective' or 'value' date           |
+------------------+-------------------------------------------+
| maturityDate     | The 'maturity' or 'termination' date      |
+------------------+-------------------------------------------+
| counterparty     | The counterparty                          |
+------------------+-------------------------------------------+
| payLeg           | The pay leg                               |
+------------------+-------------------------------------------+
| receiveLeg       | The receive leg                           |
+------------------+-------------------------------------------+
| forwardStartDate | The start date of the forward swap        |
+------------------+-------------------------------------------+



ForwardSwapSecurityForwardStartDate

...................................
ForwardSwapSecurityForwardStartDate
...................................


Returns the start date of the forward swap from a forward swap security.



+---------------------+----------------------------------+
| Parameter           | Description                      |
+=====================+==================================+
| forwardSwapSecurity | A forward swap security to query |
+---------------------+----------------------------------+



ForwardSwapSecurityObject

.........................
ForwardSwapSecurityObject
.........................


Defines a forward swap security.



+------------------+-------------------------------------------+
| Parameter        | Description                               |
+==================+===========================================+
| name             | The display name or label of the security |
+------------------+-------------------------------------------+
| tradeDate        | The trade date                            |
+------------------+-------------------------------------------+
| effectiveDate    | The 'effective' or 'value' date           |
+------------------+-------------------------------------------+
| maturityDate     | The 'maturity' or 'termination' date      |
+------------------+-------------------------------------------+
| counterparty     | The counterparty                          |
+------------------+-------------------------------------------+
| payLeg           | The pay leg                               |
+------------------+-------------------------------------------+
| receiveLeg       | The receive leg                           |
+------------------+-------------------------------------------+
| forwardStartDate | The start date of the forward swap        |
+------------------+-------------------------------------------+



ForwardSwapSecuritySetForwardStartDate

......................................
ForwardSwapSecuritySetForwardStartDate
......................................


Updates the start date of the forward swap of a forward swap security. The original object is unchanged - a new object is returned with the updated value.



+---------------------+------------------------------------+
| Parameter           | Description                        |
+=====================+====================================+
| forwardSwapSecurity | A forward swap security to update  |
+---------------------+------------------------------------+
| forwardStartDate    | The start date of the forward swap |
+---------------------+------------------------------------+



FutureSecurityContractCategory

..............................
FutureSecurityContractCategory
..............................


Returns the category from a future security.



+----------------+----------------------------+
| Parameter      | Description                |
+================+============================+
| futureSecurity | A future security to query |
+----------------+----------------------------+



FutureSecurityCurrency

......................
FutureSecurityCurrency
......................


Returns the currency from a future security.



+----------------+----------------------------+
| Parameter      | Description                |
+================+============================+
| futureSecurity | A future security to query |
+----------------+----------------------------+



FutureSecurityExpiry

....................
FutureSecurityExpiry
....................


Returns the expiry date from a future security.



+----------------+----------------------------+
| Parameter      | Description                |
+================+============================+
| futureSecurity | A future security to query |
+----------------+----------------------------+



FutureSecuritySetContractCategory

.................................
FutureSecuritySetContractCategory
.................................


Updates the category of a future security. The original object is unchanged - a new object is returned with the updated value.



+------------------+-----------------------------+
| Parameter        | Description                 |
+==================+=============================+
| futureSecurity   | A future security to update |
+------------------+-----------------------------+
| contractCategory | The category                |
+------------------+-----------------------------+



FutureSecuritySetCurrency

.........................
FutureSecuritySetCurrency
.........................


Updates the currency of a future security. The original object is unchanged - a new object is returned with the updated value.



+----------------+-----------------------------+
| Parameter      | Description                 |
+================+=============================+
| futureSecurity | A future security to update |
+----------------+-----------------------------+
| currency       | The currency                |
+----------------+-----------------------------+



FutureSecuritySetExpiry

.......................
FutureSecuritySetExpiry
.......................


Updates the expiry date of a future security. The original object is unchanged - a new object is returned with the updated value.



+----------------+-----------------------------+
| Parameter      | Description                 |
+================+=============================+
| futureSecurity | A future security to update |
+----------------+-----------------------------+
| expiry         | The expiry date             |
+----------------+-----------------------------+



FutureSecuritySetSettlementExchange

...................................
FutureSecuritySetSettlementExchange
...................................


Updates the settlement exchange of a future security. The original object is unchanged - a new object is returned with the updated value.



+--------------------+-----------------------------+
| Parameter          | Description                 |
+====================+=============================+
| futureSecurity     | A future security to update |
+--------------------+-----------------------------+
| settlementExchange | The settlement exchange     |
+--------------------+-----------------------------+



FutureSecuritySetTradingExchange

................................
FutureSecuritySetTradingExchange
................................


Updates the trading exchange of a future security. The original object is unchanged - a new object is returned with the updated value.



+-----------------+-----------------------------+
| Parameter       | Description                 |
+=================+=============================+
| futureSecurity  | A future security to update |
+-----------------+-----------------------------+
| tradingExchange | The trading exchange        |
+-----------------+-----------------------------+



FutureSecuritySetUnitAmount

...........................
FutureSecuritySetUnitAmount
...........................


Updates the unit amount of a future security. The original object is unchanged - a new object is returned with the updated value.



+----------------+-----------------------------+
| Parameter      | Description                 |
+================+=============================+
| futureSecurity | A future security to update |
+----------------+-----------------------------+
| unitAmount     | The unit amount             |
+----------------+-----------------------------+



FutureSecuritySettlementExchange

................................
FutureSecuritySettlementExchange
................................


Returns the settlement exchange from a future security.



+----------------+----------------------------+
| Parameter      | Description                |
+================+============================+
| futureSecurity | A future security to query |
+----------------+----------------------------+



FutureSecurityTradingExchange

.............................
FutureSecurityTradingExchange
.............................


Returns the trading exchange from a future security.



+----------------+----------------------------+
| Parameter      | Description                |
+================+============================+
| futureSecurity | A future security to query |
+----------------+----------------------------+



FutureSecurityUnitAmount

........................
FutureSecurityUnitAmount
........................


Returns the unit amount from a future security.



+----------------+----------------------------+
| Parameter      | Description                |
+================+============================+
| futureSecurity | A future security to query |
+----------------+----------------------------+



