title: Excel Functions - I
shortcut: DOC:Excel Functions - I
---
IRFutureOptionSecurity


......................
IRFutureOptionSecurity
......................


Defines an IR future security. The new security is added to the Security Master and an identifier to it returned.



+--------------+-------------------------------------------+
| Parameter    | Description                               |
+==============+===========================================+
| name         | The display name or label of the security |
+--------------+-------------------------------------------+
| exchange     | The exchange                              |
+--------------+-------------------------------------------+
| expiry       | The expiry                                |
+--------------+-------------------------------------------+
| exerciseType | The exercise type                         |
+--------------+-------------------------------------------+
| underlyingId | The identifier of the underlying security |
+--------------+-------------------------------------------+
| pointValue   | The point value                           |
+--------------+-------------------------------------------+
| margined     | The margined flag                         |
+--------------+-------------------------------------------+
| currency     | The currency                              |
+--------------+-------------------------------------------+
| strike       | The strike                                |
+--------------+-------------------------------------------+
| optionType   | The type of option (PUT or CALL)          |
+--------------+-------------------------------------------+



IRFutureOptionSecurityCurrency


..............................
IRFutureOptionSecurityCurrency
..............................


Returns the currency from an IR future security.



+------------------------+--------------------------------+
| Parameter              | Description                    |
+========================+================================+
| IRFutureOptionSecurity | An IR future security to query |
+------------------------+--------------------------------+



IRFutureOptionSecurityExchange


..............................
IRFutureOptionSecurityExchange
..............................


Returns the exchange from an IR future security.



+------------------------+--------------------------------+
| Parameter              | Description                    |
+========================+================================+
| IRFutureOptionSecurity | An IR future security to query |
+------------------------+--------------------------------+



IRFutureOptionSecurityExerciseType


..................................
IRFutureOptionSecurityExerciseType
..................................


Returns the exercise type from an IR future security.



+------------------------+--------------------------------+
| Parameter              | Description                    |
+========================+================================+
| IRFutureOptionSecurity | An IR future security to query |
+------------------------+--------------------------------+



IRFutureOptionSecurityExpiry


............................
IRFutureOptionSecurityExpiry
............................


Returns the expiry from an IR future security.



+------------------------+--------------------------------+
| Parameter              | Description                    |
+========================+================================+
| IRFutureOptionSecurity | An IR future security to query |
+------------------------+--------------------------------+



IRFutureOptionSecurityMargined


..............................
IRFutureOptionSecurityMargined
..............................


Returns the margined flag from an IR future security.



+------------------------+--------------------------------+
| Parameter              | Description                    |
+========================+================================+
| IRFutureOptionSecurity | An IR future security to query |
+------------------------+--------------------------------+



IRFutureOptionSecurityObject


............................
IRFutureOptionSecurityObject
............................


Defines an IR future security.



+--------------+-------------------------------------------+
| Parameter    | Description                               |
+==============+===========================================+
| name         | The display name or label of the security |
+--------------+-------------------------------------------+
| exchange     | The exchange                              |
+--------------+-------------------------------------------+
| expiry       | The expiry                                |
+--------------+-------------------------------------------+
| exerciseType | The exercise type                         |
+--------------+-------------------------------------------+
| underlyingId | The identifier of the underlying security |
+--------------+-------------------------------------------+
| pointValue   | The point value                           |
+--------------+-------------------------------------------+
| margined     | The margined flag                         |
+--------------+-------------------------------------------+
| currency     | The currency                              |
+--------------+-------------------------------------------+
| strike       | The strike                                |
+--------------+-------------------------------------------+
| optionType   | The type of option (PUT or CALL)          |
+--------------+-------------------------------------------+



IRFutureOptionSecurityOptionType


................................
IRFutureOptionSecurityOptionType
................................


Returns the type of option (PUT or CALL) from an IR future security.



+------------------------+--------------------------------+
| Parameter              | Description                    |
+========================+================================+
| IRFutureOptionSecurity | An IR future security to query |
+------------------------+--------------------------------+



IRFutureOptionSecurityPointValue


................................
IRFutureOptionSecurityPointValue
................................


Returns the point value from an IR future security.



+------------------------+--------------------------------+
| Parameter              | Description                    |
+========================+================================+
| IRFutureOptionSecurity | An IR future security to query |
+------------------------+--------------------------------+



IRFutureOptionSecuritySetCurrency


.................................
IRFutureOptionSecuritySetCurrency
.................................


Updates the currency of an IR future security. The original object is unchanged - a new object is returned with the updated value.



+------------------------+---------------------------------+
| Parameter              | Description                     |
+========================+=================================+
| IRFutureOptionSecurity | An IR future security to update |
+------------------------+---------------------------------+
| currency               | The currency                    |
+------------------------+---------------------------------+



IRFutureOptionSecuritySetExchange


.................................
IRFutureOptionSecuritySetExchange
.................................


Updates the exchange of an IR future security. The original object is unchanged - a new object is returned with the updated value.



+------------------------+---------------------------------+
| Parameter              | Description                     |
+========================+=================================+
| IRFutureOptionSecurity | An IR future security to update |
+------------------------+---------------------------------+
| exchange               | The exchange                    |
+------------------------+---------------------------------+



IRFutureOptionSecuritySetExerciseType


.....................................
IRFutureOptionSecuritySetExerciseType
.....................................


Updates the exercise type of an IR future security. The original object is unchanged - a new object is returned with the updated value.



+------------------------+---------------------------------+
| Parameter              | Description                     |
+========================+=================================+
| IRFutureOptionSecurity | An IR future security to update |
+------------------------+---------------------------------+
| exerciseType           | The exercise type               |
+------------------------+---------------------------------+



IRFutureOptionSecuritySetExpiry


...............................
IRFutureOptionSecuritySetExpiry
...............................


Updates the expiry of an IR future security. The original object is unchanged - a new object is returned with the updated value.



+------------------------+---------------------------------+
| Parameter              | Description                     |
+========================+=================================+
| IRFutureOptionSecurity | An IR future security to update |
+------------------------+---------------------------------+
| expiry                 | The expiry                      |
+------------------------+---------------------------------+



IRFutureOptionSecuritySetMargined


.................................
IRFutureOptionSecuritySetMargined
.................................


Updates the margined flag of an IR future security. The original object is unchanged - a new object is returned with the updated value.



+------------------------+---------------------------------+
| Parameter              | Description                     |
+========================+=================================+
| IRFutureOptionSecurity | An IR future security to update |
+------------------------+---------------------------------+
| margined               | The margined flag               |
+------------------------+---------------------------------+



IRFutureOptionSecuritySetOptionType


...................................
IRFutureOptionSecuritySetOptionType
...................................


Updates the type of option (PUT or CALL) of an IR future security. The original object is unchanged - a new object is returned with the updated value.



+------------------------+----------------------------------+
| Parameter              | Description                      |
+========================+==================================+
| IRFutureOptionSecurity | An IR future security to update  |
+------------------------+----------------------------------+
| optionType             | The type of option (PUT or CALL) |
+------------------------+----------------------------------+



IRFutureOptionSecuritySetPointValue


...................................
IRFutureOptionSecuritySetPointValue
...................................


Updates the point value of an IR future security. The original object is unchanged - a new object is returned with the updated value.



+------------------------+---------------------------------+
| Parameter              | Description                     |
+========================+=================================+
| IRFutureOptionSecurity | An IR future security to update |
+------------------------+---------------------------------+
| pointValue             | The point value                 |
+------------------------+---------------------------------+



IRFutureOptionSecuritySetStrike


...............................
IRFutureOptionSecuritySetStrike
...............................


Updates the strike of an IR future security. The original object is unchanged - a new object is returned with the updated value.



+------------------------+---------------------------------+
| Parameter              | Description                     |
+========================+=================================+
| IRFutureOptionSecurity | An IR future security to update |
+------------------------+---------------------------------+
| strike                 | The strike                      |
+------------------------+---------------------------------+



IRFutureOptionSecuritySetUnderlyingId


.....................................
IRFutureOptionSecuritySetUnderlyingId
.....................................


Updates the identifier of the underlying security of an IR future security. The original object is unchanged - a new object is returned with the updated value.



+------------------------+-------------------------------------------+
| Parameter              | Description                               |
+========================+===========================================+
| IRFutureOptionSecurity | An IR future security to update           |
+------------------------+-------------------------------------------+
| underlyingId           | The identifier of the underlying security |
+------------------------+-------------------------------------------+



IRFutureOptionSecurityStrike


............................
IRFutureOptionSecurityStrike
............................


Returns the strike from an IR future security.



+------------------------+--------------------------------+
| Parameter              | Description                    |
+========================+================================+
| IRFutureOptionSecurity | An IR future security to query |
+------------------------+--------------------------------+



IRFutureOptionSecurityUnderlyingId


..................................
IRFutureOptionSecurityUnderlyingId
..................................


Returns the identifier of the underlying security from an IR future security.



+------------------------+--------------------------------+
| Parameter              | Description                    |
+========================+================================+
| IRFutureOptionSecurity | An IR future security to query |
+------------------------+--------------------------------+



Identifier


..........
Identifier
..........


Constructs an identifier by which the OpenGamma engine can refer to an entity.



+-----------+----------------------------------------+
| Parameter | Description                            |
+===========+========================================+
| scheme    | The identification scheme              |
+-----------+----------------------------------------+
| value     | The identifier value within the scheme |
+-----------+----------------------------------------+



IdentifierACTIVFEED_TICKER


..........................
IdentifierACTIVFEED_TICKER
..........................


Constructs an identifier within the `ACTIVFEED_TICKER` scheme by which the OpenGamma engine can refer to an entity.



+-----------+----------------------------------------+
| Parameter | Description                            |
+===========+========================================+
| value     | The identifier value within the scheme |
+-----------+----------------------------------------+



IdentifierBLOOMBERG_BUID


........................
IdentifierBLOOMBERG_BUID
........................


Constructs an identifier within the `BLOOMBERG_BUID` scheme by which the OpenGamma engine can refer to an entity.



+-----------+----------------------------------------+
| Parameter | Description                            |
+===========+========================================+
| value     | The identifier value within the scheme |
+-----------+----------------------------------------+



IdentifierBLOOMBERG_BUID_WEAK


.............................
IdentifierBLOOMBERG_BUID_WEAK
.............................


Constructs an identifier within the `BLOOMBERG_BUID_WEAK` scheme by which the OpenGamma engine can refer to an entity.



+-----------+----------------------------------------+
| Parameter | Description                            |
+===========+========================================+
| value     | The identifier value within the scheme |
+-----------+----------------------------------------+



IdentifierBLOOMBERG_TCM


.......................
IdentifierBLOOMBERG_TCM
.......................


Constructs an identifier within the `BLOOMBERG_TCM` scheme by which the OpenGamma engine can refer to an entity.



+-----------+----------------------------------------+
| Parameter | Description                            |
+===========+========================================+
| value     | The identifier value within the scheme |
+-----------+----------------------------------------+



IdentifierBLOOMBERG_TICKER


..........................
IdentifierBLOOMBERG_TICKER
..........................


Constructs an identifier within the `BLOOMBERG_TICKER` scheme by which the OpenGamma engine can refer to an entity.



+-----------+----------------------------------------+
| Parameter | Description                            |
+===========+========================================+
| value     | The identifier value within the scheme |
+-----------+----------------------------------------+



IdentifierBLOOMBERG_TICKER_WEAK


...............................
IdentifierBLOOMBERG_TICKER_WEAK
...............................


Constructs an identifier within the `BLOOMBERG_TICKER_WEAK` scheme by which the OpenGamma engine can refer to an entity.



+-----------+----------------------------------------+
| Parameter | Description                            |
+===========+========================================+
| value     | The identifier value within the scheme |
+-----------+----------------------------------------+



IdentifierCOPP_CLARK_LOCODE


...........................
IdentifierCOPP_CLARK_LOCODE
...........................


Constructs an identifier within the `COPP_CLARK_LOCODE` scheme by which the OpenGamma engine can refer to an entity.



+-----------+----------------------------------------+
| Parameter | Description                            |
+===========+========================================+
| value     | The identifier value within the scheme |
+-----------+----------------------------------------+



IdentifierCUSIP


...............
IdentifierCUSIP
...............


Constructs an identifier within the CUSIP scheme by which the OpenGamma engine can refer to an entity.



+-----------+----------------------------------------+
| Parameter | Description                            |
+===========+========================================+
| value     | The identifier value within the scheme |
+-----------+----------------------------------------+



IdentifierCurrencyISO


.....................
IdentifierCurrencyISO
.....................


Constructs an identifier within the CurrencyISO scheme by which the OpenGamma engine can refer to an entity.



+-----------+----------------------------------------+
| Parameter | Description                            |
+===========+========================================+
| value     | The identifier value within the scheme |
+-----------+----------------------------------------+



IdentifierFINANCIAL_REGION


..........................
IdentifierFINANCIAL_REGION
..........................


Constructs an identifier within the `FINANCIAL_REGION` scheme by which the OpenGamma engine can refer to an entity.



+-----------+----------------------------------------+
| Parameter | Description                            |
+===========+========================================+
| value     | The identifier value within the scheme |
+-----------+----------------------------------------+



IdentifierICAP


..............
IdentifierICAP
..............


Constructs an identifier within the ICAP scheme by which the OpenGamma engine can refer to an entity.



+-----------+----------------------------------------+
| Parameter | Description                            |
+===========+========================================+
| value     | The identifier value within the scheme |
+-----------+----------------------------------------+



IdentifierISIN


..............
IdentifierISIN
..............


Constructs an identifier within the ISIN scheme by which the OpenGamma engine can refer to an entity.



+-----------+----------------------------------------+
| Parameter | Description                            |
+===========+========================================+
| value     | The identifier value within the scheme |
+-----------+----------------------------------------+



IdentifierISO_COUNTRY_ALPHA2


............................
IdentifierISO_COUNTRY_ALPHA2
............................


Constructs an identifier within the `ISO_COUNTRY_ALPHA2` scheme by which the OpenGamma engine can refer to an entity.



+-----------+----------------------------------------+
| Parameter | Description                            |
+===========+========================================+
| value     | The identifier value within the scheme |
+-----------+----------------------------------------+



IdentifierISO_CURRENCY_ALPHA3


.............................
IdentifierISO_CURRENCY_ALPHA3
.............................


Constructs an identifier within the `ISO_CURRENCY_ALPHA3` scheme by which the OpenGamma engine can refer to an entity.



+-----------+----------------------------------------+
| Parameter | Description                            |
+===========+========================================+
| value     | The identifier value within the scheme |
+-----------+----------------------------------------+



IdentifierISO_MIC


.................
IdentifierISO_MIC
.................


Constructs an identifier within the `ISO_MIC` scheme by which the OpenGamma engine can refer to an entity.



+-----------+----------------------------------------+
| Parameter | Description                            |
+===========+========================================+
| value     | The identifier value within the scheme |
+-----------+----------------------------------------+



IdentifierOG_SYNTHETIC_TICKER


.............................
IdentifierOG_SYNTHETIC_TICKER
.............................


Constructs an identifier within the `OG_SYNTHETIC_TICKER` scheme by which the OpenGamma engine can refer to an entity.



+-----------+----------------------------------------+
| Parameter | Description                            |
+===========+========================================+
| value     | The identifier value within the scheme |
+-----------+----------------------------------------+



IdentifierRIC


.............
IdentifierRIC
.............


Constructs an identifier within the RIC scheme by which the OpenGamma engine can refer to an entity.



+-----------+----------------------------------------+
| Parameter | Description                            |
+===========+========================================+
| value     | The identifier value within the scheme |
+-----------+----------------------------------------+



IdentifierSEDOL1


................
IdentifierSEDOL1
................


Constructs an identifier within the SEDOL1 scheme by which the OpenGamma engine can refer to an entity.



+-----------+----------------------------------------+
| Parameter | Description                            |
+===========+========================================+
| value     | The identifier value within the scheme |
+-----------+----------------------------------------+



IdentifierSURF


..............
IdentifierSURF
..............


Constructs an identifier within the SURF scheme by which the OpenGamma engine can refer to an entity.



+-----------+----------------------------------------+
| Parameter | Description                            |
+===========+========================================+
| value     | The identifier value within the scheme |
+-----------+----------------------------------------+



IdentifierTZDB_TIME_ZONE


........................
IdentifierTZDB_TIME_ZONE
........................


Constructs an identifier within the `TZDB_TIME_ZONE` scheme by which the OpenGamma engine can refer to an entity.



+-----------+----------------------------------------+
| Parameter | Description                            |
+===========+========================================+
| value     | The identifier value within the scheme |
+-----------+----------------------------------------+



IdentifierUN_LOCODE_2010_2


..........................
IdentifierUN_LOCODE_2010_2
..........................


Constructs an identifier within the `UN_LOCODE_2010_2` scheme by which the OpenGamma engine can refer to an entity.



+-----------+----------------------------------------+
| Parameter | Description                            |
+===========+========================================+
| value     | The identifier value within the scheme |
+-----------+----------------------------------------+



InterestRate


............
InterestRate
............


Extracts the yield from a curve at time t.



+-----------+------------------------------------+
| Parameter | Description                        |
+===========+====================================+
| curve     | The yield curve                    |
+-----------+------------------------------------+
| t         | The time at which to get the yield |
+-----------+------------------------------------+



InterestRateFutureSecurity


..........................
InterestRateFutureSecurity
..........................


Defines an IR future security. The new security is added to the Security Master and an identifier to it returned.



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
| underlyingId       | The underlying identifier                 |
+--------------------+-------------------------------------------+
| contractCategory   | The category                              |
+--------------------+-------------------------------------------+



InterestRateFutureSecurityObject


................................
InterestRateFutureSecurityObject
................................


Defines an IR future security.



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
| underlyingId       | The underlying identifier                 |
+--------------------+-------------------------------------------+
| contractCategory   | The category                              |
+--------------------+-------------------------------------------+



InterestRateFutureSecuritySetUnderlyingId


.........................................
InterestRateFutureSecuritySetUnderlyingId
.........................................


Updates the underlying identifier of an IR future security. The original object is unchanged - a new object is returned with the updated value.



+----------------------------+---------------------------------+
| Parameter                  | Description                     |
+============================+=================================+
| interestRateFutureSecurity | An IR future security to update |
+----------------------------+---------------------------------+
| underlyingId               | The underlying identifier       |
+----------------------------+---------------------------------+



InterestRateFutureSecurityUnderlyingId


......................................
InterestRateFutureSecurityUnderlyingId
......................................


Returns the underlying identifier from an IR future security.



+----------------------------+--------------------------------+
| Parameter                  | Description                    |
+============================+================================+
| interestRateFutureSecurity | An IR future security to query |
+----------------------------+--------------------------------+



InterestRateNotional


....................
InterestRateNotional
....................


Defines a notional value of an interest rate leg of a swap.



+-----------+--------------+
| Parameter | Description  |
+===========+==============+
| currency  | The currency |
+-----------+--------------+
| amount    | The amount   |
+-----------+--------------+


