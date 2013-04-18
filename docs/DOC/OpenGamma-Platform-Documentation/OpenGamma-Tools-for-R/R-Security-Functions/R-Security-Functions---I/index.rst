title: R Security Functions - I
shortcut: DOC:R Security Functions - I
---
ExpandInterestRateFutureSecurity

................................
ExpandInterestRateFutureSecurity
................................


Expand the contents of an IR future security.



+----------------------------+----------+--------------------------------+
| Parameter                  | Required | Description                    |
+============================+==========+================================+
| interestRateFutureSecurity | Yes      | An IR future security to query |
+----------------------------+----------+--------------------------------+




ExpandInterestRateNotional

..........................
ExpandInterestRateNotional
..........................


Expand the contents of a notional value of an interest rate leg of a swap.



+----------------------+----------+-------------------------------------------------------------+
| Parameter            | Required | Description                                                 |
+======================+==========+=============================================================+
| interestRateNotional | Yes      | A notional value of an interest rate leg of a swap to query |
+----------------------+----------+-------------------------------------------------------------+




ExpandIRFutureOptionSecurity

............................
ExpandIRFutureOptionSecurity
............................


Expand the contents of an IR future security.



+------------------------+----------+--------------------------------+
| Parameter              | Required | Description                    |
+========================+==========+================================+
| IRFutureOptionSecurity | Yes      | An IR future security to query |
+------------------------+----------+--------------------------------+




GetInterestRateFutureSecurityUnderlyingId

.........................................
GetInterestRateFutureSecurityUnderlyingId
.........................................


Returns the underlying identifier from an IR future security.



+----------------------------+----------+--------------------------------+
| Parameter                  | Required | Description                    |
+============================+==========+================================+
| interestRateFutureSecurity | Yes      | An IR future security to query |
+----------------------------+----------+--------------------------------+




GetInterestRateNotionalAmount

.............................
GetInterestRateNotionalAmount
.............................


Returns the amount from a notional value of an interest rate leg of a swap.



+----------------------+----------+-------------------------------------------------------------+
| Parameter            | Required | Description                                                 |
+======================+==========+=============================================================+
| interestRateNotional | Yes      | A notional value of an interest rate leg of a swap to query |
+----------------------+----------+-------------------------------------------------------------+




GetInterestRateNotionalCurrency

...............................
GetInterestRateNotionalCurrency
...............................


Returns the currency from a notional value of an interest rate leg of a swap.



+----------------------+----------+-------------------------------------------------------------+
| Parameter            | Required | Description                                                 |
+======================+==========+=============================================================+
| interestRateNotional | Yes      | A notional value of an interest rate leg of a swap to query |
+----------------------+----------+-------------------------------------------------------------+




GetIRFutureOptionSecurityCurrency

.................................
GetIRFutureOptionSecurityCurrency
.................................


Returns the currency from an IR future security.



+------------------------+----------+--------------------------------+
| Parameter              | Required | Description                    |
+========================+==========+================================+
| IRFutureOptionSecurity | Yes      | An IR future security to query |
+------------------------+----------+--------------------------------+




GetIRFutureOptionSecurityExchange

.................................
GetIRFutureOptionSecurityExchange
.................................


Returns the exchange from an IR future security.



+------------------------+----------+--------------------------------+
| Parameter              | Required | Description                    |
+========================+==========+================================+
| IRFutureOptionSecurity | Yes      | An IR future security to query |
+------------------------+----------+--------------------------------+




GetIRFutureOptionSecurityExerciseType

.....................................
GetIRFutureOptionSecurityExerciseType
.....................................


Returns the exercise type from an IR future security.



+------------------------+----------+--------------------------------+
| Parameter              | Required | Description                    |
+========================+==========+================================+
| IRFutureOptionSecurity | Yes      | An IR future security to query |
+------------------------+----------+--------------------------------+




GetIRFutureOptionSecurityExpiry

...............................
GetIRFutureOptionSecurityExpiry
...............................


Returns the expiry from an IR future security.



+------------------------+----------+--------------------------------+
| Parameter              | Required | Description                    |
+========================+==========+================================+
| IRFutureOptionSecurity | Yes      | An IR future security to query |
+------------------------+----------+--------------------------------+




GetIRFutureOptionSecurityMargined

.................................
GetIRFutureOptionSecurityMargined
.................................


Returns the margined flag from an IR future security.



+------------------------+----------+--------------------------------+
| Parameter              | Required | Description                    |
+========================+==========+================================+
| IRFutureOptionSecurity | Yes      | An IR future security to query |
+------------------------+----------+--------------------------------+




GetIRFutureOptionSecurityOptionType

...................................
GetIRFutureOptionSecurityOptionType
...................................


Returns the type of option (PUT or CALL) from an IR future security.



+------------------------+----------+--------------------------------+
| Parameter              | Required | Description                    |
+========================+==========+================================+
| IRFutureOptionSecurity | Yes      | An IR future security to query |
+------------------------+----------+--------------------------------+




GetIRFutureOptionSecurityPointValue

...................................
GetIRFutureOptionSecurityPointValue
...................................


Returns the point value from an IR future security.



+------------------------+----------+--------------------------------+
| Parameter              | Required | Description                    |
+========================+==========+================================+
| IRFutureOptionSecurity | Yes      | An IR future security to query |
+------------------------+----------+--------------------------------+




GetIRFutureOptionSecurityStrike

...............................
GetIRFutureOptionSecurityStrike
...............................


Returns the strike from an IR future security.



+------------------------+----------+--------------------------------+
| Parameter              | Required | Description                    |
+========================+==========+================================+
| IRFutureOptionSecurity | Yes      | An IR future security to query |
+------------------------+----------+--------------------------------+




GetIRFutureOptionSecurityUnderlyingId

.....................................
GetIRFutureOptionSecurityUnderlyingId
.....................................


Returns the identifier of the underlying security from an IR future security.



+------------------------+----------+--------------------------------+
| Parameter              | Required | Description                    |
+========================+==========+================================+
| IRFutureOptionSecurity | Yes      | An IR future security to query |
+------------------------+----------+--------------------------------+




InterestRateFutureSecurity

..........................
InterestRateFutureSecurity
..........................


Defines an IR future security.



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
| underlyingId       | Yes      | The underlying identifier                 |
+--------------------+----------+-------------------------------------------+
| contractCategory   | Yes      | The category                              |
+--------------------+----------+-------------------------------------------+




InterestRateNotional

....................
InterestRateNotional
....................


Defines a notional value of an interest rate leg of a swap.



+-----------+----------+--------------+
| Parameter | Required | Description  |
+===========+==========+==============+
| currency  | Yes      | The currency |
+-----------+----------+--------------+
| amount    | Yes      | The amount   |
+-----------+----------+--------------+




IRFutureOptionSecurity

......................
IRFutureOptionSecurity
......................


Defines an IR future security.



+--------------+----------+-------------------------------------------+
| Parameter    | Required | Description                               |
+==============+==========+===========================================+
| name         | Yes      | The display name or label of the security |
+--------------+----------+-------------------------------------------+
| exchange     | Yes      | The exchange                              |
+--------------+----------+-------------------------------------------+
| expiry       | Yes      | The expiry                                |
+--------------+----------+-------------------------------------------+
| exerciseType | Yes      | The exercise type                         |
+--------------+----------+-------------------------------------------+
| underlyingId | Yes      | The identifier of the underlying security |
+--------------+----------+-------------------------------------------+
| pointValue   | Yes      | The point value                           |
+--------------+----------+-------------------------------------------+
| margined     | Yes      | The margined flag                         |
+--------------+----------+-------------------------------------------+
| currency     | Yes      | The currency                              |
+--------------+----------+-------------------------------------------+
| strike       | Yes      | The strike                                |
+--------------+----------+-------------------------------------------+
| optionType   | Yes      | The type of option (PUT or CALL)          |
+--------------+----------+-------------------------------------------+




SetInterestRateFutureSecurityUnderlyingId

.........................................
SetInterestRateFutureSecurityUnderlyingId
.........................................


Updates the underlying identifier of an IR future security. The original object is unchanged - a new object is returned with the updated value.



+----------------------------+----------+---------------------------------+
| Parameter                  | Required | Description                     |
+============================+==========+=================================+
| interestRateFutureSecurity | Yes      | An IR future security to update |
+----------------------------+----------+---------------------------------+
| underlyingId               |          | The underlying identifier       |
+----------------------------+----------+---------------------------------+




SetInterestRateNotionalAmount

.............................
SetInterestRateNotionalAmount
.............................


Updates the amount of a notional value of an interest rate leg of a swap. The original object is unchanged - a new object is returned with the updated value.



+----------------------+----------+--------------------------------------------------------------+
| Parameter            | Required | Description                                                  |
+======================+==========+==============================================================+
| interestRateNotional | Yes      | A notional value of an interest rate leg of a swap to update |
+----------------------+----------+--------------------------------------------------------------+
| amount               | Yes      | The amount                                                   |
+----------------------+----------+--------------------------------------------------------------+




SetInterestRateNotionalCurrency

...............................
SetInterestRateNotionalCurrency
...............................


Updates the currency of a notional value of an interest rate leg of a swap. The original object is unchanged - a new object is returned with the updated value.



+----------------------+----------+--------------------------------------------------------------+
| Parameter            | Required | Description                                                  |
+======================+==========+==============================================================+
| interestRateNotional | Yes      | A notional value of an interest rate leg of a swap to update |
+----------------------+----------+--------------------------------------------------------------+
| currency             |          | The currency                                                 |
+----------------------+----------+--------------------------------------------------------------+




SetIRFutureOptionSecurityCurrency

.................................
SetIRFutureOptionSecurityCurrency
.................................


Updates the currency of an IR future security. The original object is unchanged - a new object is returned with the updated value.



+------------------------+----------+---------------------------------+
| Parameter              | Required | Description                     |
+========================+==========+=================================+
| IRFutureOptionSecurity | Yes      | An IR future security to update |
+------------------------+----------+---------------------------------+
| currency               |          | The currency                    |
+------------------------+----------+---------------------------------+




SetIRFutureOptionSecurityExchange

.................................
SetIRFutureOptionSecurityExchange
.................................


Updates the exchange of an IR future security. The original object is unchanged - a new object is returned with the updated value.



+------------------------+----------+---------------------------------+
| Parameter              | Required | Description                     |
+========================+==========+=================================+
| IRFutureOptionSecurity | Yes      | An IR future security to update |
+------------------------+----------+---------------------------------+
| exchange               |          | The exchange                    |
+------------------------+----------+---------------------------------+




SetIRFutureOptionSecurityExerciseType

.....................................
SetIRFutureOptionSecurityExerciseType
.....................................


Updates the exercise type of an IR future security. The original object is unchanged - a new object is returned with the updated value.



+------------------------+----------+---------------------------------+
| Parameter              | Required | Description                     |
+========================+==========+=================================+
| IRFutureOptionSecurity | Yes      | An IR future security to update |
+------------------------+----------+---------------------------------+
| exerciseType           |          | The exercise type               |
+------------------------+----------+---------------------------------+




SetIRFutureOptionSecurityExpiry

...............................
SetIRFutureOptionSecurityExpiry
...............................


Updates the expiry of an IR future security. The original object is unchanged - a new object is returned with the updated value.



+------------------------+----------+---------------------------------+
| Parameter              | Required | Description                     |
+========================+==========+=================================+
| IRFutureOptionSecurity | Yes      | An IR future security to update |
+------------------------+----------+---------------------------------+
| expiry                 |          | The expiry                      |
+------------------------+----------+---------------------------------+




SetIRFutureOptionSecurityMargined

.................................
SetIRFutureOptionSecurityMargined
.................................


Updates the margined flag of an IR future security. The original object is unchanged - a new object is returned with the updated value.



+------------------------+----------+---------------------------------+
| Parameter              | Required | Description                     |
+========================+==========+=================================+
| IRFutureOptionSecurity | Yes      | An IR future security to update |
+------------------------+----------+---------------------------------+
| margined               | Yes      | The margined flag               |
+------------------------+----------+---------------------------------+




SetIRFutureOptionSecurityOptionType

...................................
SetIRFutureOptionSecurityOptionType
...................................


Updates the type of option (PUT or CALL) of an IR future security. The original object is unchanged - a new object is returned with the updated value.



+------------------------+----------+----------------------------------+
| Parameter              | Required | Description                      |
+========================+==========+==================================+
| IRFutureOptionSecurity | Yes      | An IR future security to update  |
+------------------------+----------+----------------------------------+
| optionType             |          | The type of option (PUT or CALL) |
+------------------------+----------+----------------------------------+




SetIRFutureOptionSecurityPointValue

...................................
SetIRFutureOptionSecurityPointValue
...................................


Updates the point value of an IR future security. The original object is unchanged - a new object is returned with the updated value.



+------------------------+----------+---------------------------------+
| Parameter              | Required | Description                     |
+========================+==========+=================================+
| IRFutureOptionSecurity | Yes      | An IR future security to update |
+------------------------+----------+---------------------------------+
| pointValue             | Yes      | The point value                 |
+------------------------+----------+---------------------------------+




SetIRFutureOptionSecurityStrike

...............................
SetIRFutureOptionSecurityStrike
...............................


Updates the strike of an IR future security. The original object is unchanged - a new object is returned with the updated value.



+------------------------+----------+---------------------------------+
| Parameter              | Required | Description                     |
+========================+==========+=================================+
| IRFutureOptionSecurity | Yes      | An IR future security to update |
+------------------------+----------+---------------------------------+
| strike                 | Yes      | The strike                      |
+------------------------+----------+---------------------------------+




SetIRFutureOptionSecurityUnderlyingId

.....................................
SetIRFutureOptionSecurityUnderlyingId
.....................................


Updates the identifier of the underlying security of an IR future security. The original object is unchanged - a new object is returned with the updated value.



+------------------------+----------+-------------------------------------------+
| Parameter              | Required | Description                               |
+========================+==========+===========================================+
| IRFutureOptionSecurity | Yes      | An IR future security to update           |
+------------------------+----------+-------------------------------------------+
| underlyingId           |          | The identifier of the underlying security |
+------------------------+----------+-------------------------------------------+



