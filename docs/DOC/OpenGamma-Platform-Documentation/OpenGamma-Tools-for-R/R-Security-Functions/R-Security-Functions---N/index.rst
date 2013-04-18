title: R Security Functions - N
shortcut: DOC:R Security Functions - N
---
ExpandNonDeliverableFXOptionSecurity

....................................
ExpandNonDeliverableFXOptionSecurity
....................................


Expand the contents of a non-deliverable FX option security.



+--------------------------------+----------+-----------------------------------------------+
| Parameter                      | Required | Description                                   |
+================================+==========+===============================================+
| nonDeliverableFXOptionSecurity | Yes      | A non-deliverable FX option security to query |
+--------------------------------+----------+-----------------------------------------------+




GetNonDeliverableFXOptionSecurityCallAmount

...........................................
GetNonDeliverableFXOptionSecurityCallAmount
...........................................


Returns the call amount from a non-deliverable FX option security.



+--------------------------------+----------+-----------------------------------------------+
| Parameter                      | Required | Description                                   |
+================================+==========+===============================================+
| nonDeliverableFXOptionSecurity | Yes      | A non-deliverable FX option security to query |
+--------------------------------+----------+-----------------------------------------------+




GetNonDeliverableFXOptionSecurityCallCurrency

.............................................
GetNonDeliverableFXOptionSecurityCallCurrency
.............................................


Returns the call currency from a non-deliverable FX option security.



+--------------------------------+----------+-----------------------------------------------+
| Parameter                      | Required | Description                                   |
+================================+==========+===============================================+
| nonDeliverableFXOptionSecurity | Yes      | A non-deliverable FX option security to query |
+--------------------------------+----------+-----------------------------------------------+




GetNonDeliverableFXOptionSecurityDeliveryInCallCurrency

.......................................................
GetNonDeliverableFXOptionSecurityDeliveryInCallCurrency
.......................................................


Returns the delivery in call currency from a non-deliverable FX option security.



+--------------------------------+----------+-----------------------------------------------+
| Parameter                      | Required | Description                                   |
+================================+==========+===============================================+
| nonDeliverableFXOptionSecurity | Yes      | A non-deliverable FX option security to query |
+--------------------------------+----------+-----------------------------------------------+




GetNonDeliverableFXOptionSecurityExerciseType

.............................................
GetNonDeliverableFXOptionSecurityExerciseType
.............................................


Returns the exercise type from a non-deliverable FX option security.



+--------------------------------+----------+-----------------------------------------------+
| Parameter                      | Required | Description                                   |
+================================+==========+===============================================+
| nonDeliverableFXOptionSecurity | Yes      | A non-deliverable FX option security to query |
+--------------------------------+----------+-----------------------------------------------+




GetNonDeliverableFXOptionSecurityExpiry

.......................................
GetNonDeliverableFXOptionSecurityExpiry
.......................................


Returns the expiry from a non-deliverable FX option security.



+--------------------------------+----------+-----------------------------------------------+
| Parameter                      | Required | Description                                   |
+================================+==========+===============================================+
| nonDeliverableFXOptionSecurity | Yes      | A non-deliverable FX option security to query |
+--------------------------------+----------+-----------------------------------------------+




GetNonDeliverableFXOptionSecurityLong

.....................................
GetNonDeliverableFXOptionSecurityLong
.....................................


Returns the long flag from a non-deliverable FX option security.



+--------------------------------+----------+-----------------------------------------------+
| Parameter                      | Required | Description                                   |
+================================+==========+===============================================+
| nonDeliverableFXOptionSecurity | Yes      | A non-deliverable FX option security to query |
+--------------------------------+----------+-----------------------------------------------+




GetNonDeliverableFXOptionSecurityPutAmount

..........................................
GetNonDeliverableFXOptionSecurityPutAmount
..........................................


Returns the put amount from a non-deliverable FX option security.



+--------------------------------+----------+-----------------------------------------------+
| Parameter                      | Required | Description                                   |
+================================+==========+===============================================+
| nonDeliverableFXOptionSecurity | Yes      | A non-deliverable FX option security to query |
+--------------------------------+----------+-----------------------------------------------+




GetNonDeliverableFXOptionSecurityPutCurrency

............................................
GetNonDeliverableFXOptionSecurityPutCurrency
............................................


Returns the put currency from a non-deliverable FX option security.



+--------------------------------+----------+-----------------------------------------------+
| Parameter                      | Required | Description                                   |
+================================+==========+===============================================+
| nonDeliverableFXOptionSecurity | Yes      | A non-deliverable FX option security to query |
+--------------------------------+----------+-----------------------------------------------+




GetNonDeliverableFXOptionSecuritySettlementDate

...............................................
GetNonDeliverableFXOptionSecuritySettlementDate
...............................................


Returns the settlement date from a non-deliverable FX option security.



+--------------------------------+----------+-----------------------------------------------+
| Parameter                      | Required | Description                                   |
+================================+==========+===============================================+
| nonDeliverableFXOptionSecurity | Yes      | A non-deliverable FX option security to query |
+--------------------------------+----------+-----------------------------------------------+




NonDeliverableFXOptionSecurity

..............................
NonDeliverableFXOptionSecurity
..............................


Defines a non-deliverable FX option security.



+------------------------+----------+-------------------------------------------+
| Parameter              | Required | Description                               |
+========================+==========+===========================================+
| name                   | Yes      | The display name or label of the security |
+------------------------+----------+-------------------------------------------+
| putCurrency            | Yes      | The put currency                          |
+------------------------+----------+-------------------------------------------+
| callCurrency           | Yes      | The call currency                         |
+------------------------+----------+-------------------------------------------+
| putAmount              | Yes      | The put amount                            |
+------------------------+----------+-------------------------------------------+
| callAmount             | Yes      | The call amount                           |
+------------------------+----------+-------------------------------------------+
| expiry                 | Yes      | The expiry                                |
+------------------------+----------+-------------------------------------------+
| settlementDate         | Yes      | The settlement date                       |
+------------------------+----------+-------------------------------------------+
| long                   | Yes      | The long flag                             |
+------------------------+----------+-------------------------------------------+
| exerciseType           | Yes      | The exercise type                         |
+------------------------+----------+-------------------------------------------+
| deliveryInCallCurrency | Yes      | The delivery in call currency             |
+------------------------+----------+-------------------------------------------+




SetNonDeliverableFXOptionSecurityCallAmount

...........................................
SetNonDeliverableFXOptionSecurityCallAmount
...........................................


Updates the call amount of a non-deliverable FX option security. The original object is unchanged - a new object is returned with the updated value.



+--------------------------------+----------+------------------------------------------------+
| Parameter                      | Required | Description                                    |
+================================+==========+================================================+
| nonDeliverableFXOptionSecurity | Yes      | A non-deliverable FX option security to update |
+--------------------------------+----------+------------------------------------------------+
| callAmount                     | Yes      | The call amount                                |
+--------------------------------+----------+------------------------------------------------+




SetNonDeliverableFXOptionSecurityCallCurrency

.............................................
SetNonDeliverableFXOptionSecurityCallCurrency
.............................................


Updates the call currency of a non-deliverable FX option security. The original object is unchanged - a new object is returned with the updated value.



+--------------------------------+----------+------------------------------------------------+
| Parameter                      | Required | Description                                    |
+================================+==========+================================================+
| nonDeliverableFXOptionSecurity | Yes      | A non-deliverable FX option security to update |
+--------------------------------+----------+------------------------------------------------+
| callCurrency                   |          | The call currency                              |
+--------------------------------+----------+------------------------------------------------+




SetNonDeliverableFXOptionSecurityDeliveryInCallCurrency

.......................................................
SetNonDeliverableFXOptionSecurityDeliveryInCallCurrency
.......................................................


Updates the delivery in call currency of a non-deliverable FX option security. The original object is unchanged - a new object is returned with the updated value.



+--------------------------------+----------+------------------------------------------------+
| Parameter                      | Required | Description                                    |
+================================+==========+================================================+
| nonDeliverableFXOptionSecurity | Yes      | A non-deliverable FX option security to update |
+--------------------------------+----------+------------------------------------------------+
| deliveryInCallCurrency         | Yes      | The delivery in call currency                  |
+--------------------------------+----------+------------------------------------------------+




SetNonDeliverableFXOptionSecurityExerciseType

.............................................
SetNonDeliverableFXOptionSecurityExerciseType
.............................................


Updates the exercise type of a non-deliverable FX option security. The original object is unchanged - a new object is returned with the updated value.



+--------------------------------+----------+------------------------------------------------+
| Parameter                      | Required | Description                                    |
+================================+==========+================================================+
| nonDeliverableFXOptionSecurity | Yes      | A non-deliverable FX option security to update |
+--------------------------------+----------+------------------------------------------------+
| exerciseType                   |          | The exercise type                              |
+--------------------------------+----------+------------------------------------------------+




SetNonDeliverableFXOptionSecurityExpiry

.......................................
SetNonDeliverableFXOptionSecurityExpiry
.......................................


Updates the expiry of a non-deliverable FX option security. The original object is unchanged - a new object is returned with the updated value.



+--------------------------------+----------+------------------------------------------------+
| Parameter                      | Required | Description                                    |
+================================+==========+================================================+
| nonDeliverableFXOptionSecurity | Yes      | A non-deliverable FX option security to update |
+--------------------------------+----------+------------------------------------------------+
| expiry                         |          | The expiry                                     |
+--------------------------------+----------+------------------------------------------------+




SetNonDeliverableFXOptionSecurityPutAmount

..........................................
SetNonDeliverableFXOptionSecurityPutAmount
..........................................


Updates the put amount of a non-deliverable FX option security. The original object is unchanged - a new object is returned with the updated value.



+--------------------------------+----------+------------------------------------------------+
| Parameter                      | Required | Description                                    |
+================================+==========+================================================+
| nonDeliverableFXOptionSecurity | Yes      | A non-deliverable FX option security to update |
+--------------------------------+----------+------------------------------------------------+
| putAmount                      | Yes      | The put amount                                 |
+--------------------------------+----------+------------------------------------------------+




SetNonDeliverableFXOptionSecurityPutCurrency

............................................
SetNonDeliverableFXOptionSecurityPutCurrency
............................................


Updates the put currency of a non-deliverable FX option security. The original object is unchanged - a new object is returned with the updated value.



+--------------------------------+----------+------------------------------------------------+
| Parameter                      | Required | Description                                    |
+================================+==========+================================================+
| nonDeliverableFXOptionSecurity | Yes      | A non-deliverable FX option security to update |
+--------------------------------+----------+------------------------------------------------+
| putCurrency                    |          | The put currency                               |
+--------------------------------+----------+------------------------------------------------+




SetNonDeliverableFXOptionSecuritySettlementDate

...............................................
SetNonDeliverableFXOptionSecuritySettlementDate
...............................................


Updates the settlement date of a non-deliverable FX option security. The original object is unchanged - a new object is returned with the updated value.



+--------------------------------+----------+------------------------------------------------+
| Parameter                      | Required | Description                                    |
+================================+==========+================================================+
| nonDeliverableFXOptionSecurity | Yes      | A non-deliverable FX option security to update |
+--------------------------------+----------+------------------------------------------------+
| settlementDate                 |          | The settlement date                            |
+--------------------------------+----------+------------------------------------------------+



