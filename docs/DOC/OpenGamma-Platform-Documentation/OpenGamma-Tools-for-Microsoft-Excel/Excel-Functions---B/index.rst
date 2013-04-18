title: Excel Functions - B
shortcut: DOC:Excel Functions - B
---
BarrierPayoffStyle

..................
BarrierPayoffStyle
..................


Returns an object representing a 'barrier' option payoff style.

This function takes no parameters.

BermudanExerciseType

....................
BermudanExerciseType
....................


Returns an object representing a Bermudan option exercise type.

This function takes no parameters.

BondFutureDeliverable

.....................
BondFutureDeliverable
.....................


Defines a deliverable for a bond future.



+------------------+--------------------------------------------------+
| Parameter        | Description                                      |
+==================+==================================================+
| identifiers      | The identifier bundle describing the deliverable |
+------------------+--------------------------------------------------+
| conversionFactor | The conversion factor                            |
+------------------+--------------------------------------------------+



BondFutureSecurity

..................
BondFutureSecurity
..................


Defines a bond future security. The new security is added to the Security Master and an identifier to it returned.



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
| basket             | The basket of deliverables                |
+--------------------+-------------------------------------------+
| firstDeliveryDate  | The first delivery date                   |
+--------------------+-------------------------------------------+
| lastDeliveryDate   | The last delivery date                    |
+--------------------+-------------------------------------------+
| contractCategory   | The category                              |
+--------------------+-------------------------------------------+



BondFutureSecurityBasket

........................
BondFutureSecurityBasket
........................


Returns the basket of deliverables from a bond future security.



+--------------------+---------------------------------+
| Parameter          | Description                     |
+====================+=================================+
| bondFutureSecurity | A bond future security to query |
+--------------------+---------------------------------+



BondFutureSecurityFirstDeliveryDate

...................................
BondFutureSecurityFirstDeliveryDate
...................................


Returns the first delivery date from a bond future security.



+--------------------+---------------------------------+
| Parameter          | Description                     |
+====================+=================================+
| bondFutureSecurity | A bond future security to query |
+--------------------+---------------------------------+



BondFutureSecurityLastDeliveryDate

..................................
BondFutureSecurityLastDeliveryDate
..................................


Returns the last delivery date from a bond future security.



+--------------------+---------------------------------+
| Parameter          | Description                     |
+====================+=================================+
| bondFutureSecurity | A bond future security to query |
+--------------------+---------------------------------+



BondFutureSecurityObject

........................
BondFutureSecurityObject
........................


Defines a bond future security.



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
| basket             | The basket of deliverables                |
+--------------------+-------------------------------------------+
| firstDeliveryDate  | The first delivery date                   |
+--------------------+-------------------------------------------+
| lastDeliveryDate   | The last delivery date                    |
+--------------------+-------------------------------------------+
| contractCategory   | The category                              |
+--------------------+-------------------------------------------+



BondFutureSecuritySetBasket

...........................
BondFutureSecuritySetBasket
...........................


Updates the basket of deliverables of a bond future security. The original object is unchanged - a new object is returned with the updated value.



+--------------------+----------------------------------+
| Parameter          | Description                      |
+====================+==================================+
| bondFutureSecurity | A bond future security to update |
+--------------------+----------------------------------+
| basket             | The basket of deliverables       |
+--------------------+----------------------------------+



BondFutureSecuritySetFirstDeliveryDate

......................................
BondFutureSecuritySetFirstDeliveryDate
......................................


Updates the first delivery date of a bond future security. The original object is unchanged - a new object is returned with the updated value.



+--------------------+----------------------------------+
| Parameter          | Description                      |
+====================+==================================+
| bondFutureSecurity | A bond future security to update |
+--------------------+----------------------------------+
| firstDeliveryDate  | The first delivery date          |
+--------------------+----------------------------------+



BondFutureSecuritySetLastDeliveryDate

.....................................
BondFutureSecuritySetLastDeliveryDate
.....................................


Updates the last delivery date of a bond future security. The original object is unchanged - a new object is returned with the updated value.



+--------------------+----------------------------------+
| Parameter          | Description                      |
+====================+==================================+
| bondFutureSecurity | A bond future security to update |
+--------------------+----------------------------------+
| lastDeliveryDate   | The last delivery date           |
+--------------------+----------------------------------+



BondSecurityAnnouncementDate

............................
BondSecurityAnnouncementDate
............................


Returns the announcement date from a bond security.



+--------------+--------------------------+
| Parameter    | Description              |
+==============+==========================+
| bondSecurity | A bond security to query |
+--------------+--------------------------+



BondSecurityBusinessDayConvention

.................................
BondSecurityBusinessDayConvention
.................................


Returns the business day convention from a bond security.



+--------------+--------------------------+
| Parameter    | Description              |
+==============+==========================+
| bondSecurity | A bond security to query |
+--------------+--------------------------+



BondSecurityCouponFrequency

...........................
BondSecurityCouponFrequency
...........................


Returns the coupon frequency from a bond security.



+--------------+--------------------------+
| Parameter    | Description              |
+==============+==========================+
| bondSecurity | A bond security to query |
+--------------+--------------------------+



BondSecurityCouponRate

......................
BondSecurityCouponRate
......................


Returns the coupon rate from a bond security.



+--------------+--------------------------+
| Parameter    | Description              |
+==============+==========================+
| bondSecurity | A bond security to query |
+--------------+--------------------------+



BondSecurityCouponType

......................
BondSecurityCouponType
......................


Returns the coupon type from a bond security.



+--------------+--------------------------+
| Parameter    | Description              |
+==============+==========================+
| bondSecurity | A bond security to query |
+--------------+--------------------------+



BondSecurityCurrency

....................
BondSecurityCurrency
....................


Returns the currency from a bond security.



+--------------+--------------------------+
| Parameter    | Description              |
+==============+==========================+
| bondSecurity | A bond security to query |
+--------------+--------------------------+



BondSecurityDayCount

....................
BondSecurityDayCount
....................


Returns the day count convention from a bond security.



+--------------+--------------------------+
| Parameter    | Description              |
+==============+==========================+
| bondSecurity | A bond security to query |
+--------------+--------------------------+



BondSecurityFirstCouponDate

...........................
BondSecurityFirstCouponDate
...........................


Returns the first coupon date from a bond security.



+--------------+--------------------------+
| Parameter    | Description              |
+==============+==========================+
| bondSecurity | A bond security to query |
+--------------+--------------------------+



BondSecurityGuaranteeType

.........................
BondSecurityGuaranteeType
.........................


Returns the guarantee type from a bond security.



+--------------+--------------------------+
| Parameter    | Description              |
+==============+==========================+
| bondSecurity | A bond security to query |
+--------------+--------------------------+



BondSecurityInterestAccrualDate

...............................
BondSecurityInterestAccrualDate
...............................


Returns the interest accrual date from a bond security.



+--------------+--------------------------+
| Parameter    | Description              |
+==============+==========================+
| bondSecurity | A bond security to query |
+--------------+--------------------------+



BondSecurityIssuancePrice

.........................
BondSecurityIssuancePrice
.........................


Returns the issuance price from a bond security.



+--------------+--------------------------+
| Parameter    | Description              |
+==============+==========================+
| bondSecurity | A bond security to query |
+--------------+--------------------------+



BondSecurityIssuerDomicile

..........................
BondSecurityIssuerDomicile
..........................


Returns the issuer domicile from a bond security.



+--------------+--------------------------+
| Parameter    | Description              |
+==============+==========================+
| bondSecurity | A bond security to query |
+--------------+--------------------------+



BondSecurityIssuerName

......................
BondSecurityIssuerName
......................


Returns the issuer name from a bond security.



+--------------+--------------------------+
| Parameter    | Description              |
+==============+==========================+
| bondSecurity | A bond security to query |
+--------------+--------------------------+



BondSecurityIssuerType

......................
BondSecurityIssuerType
......................


Returns the issuer type from a bond security.



+--------------+--------------------------+
| Parameter    | Description              |
+==============+==========================+
| bondSecurity | A bond security to query |
+--------------+--------------------------+



BondSecurityLastTradeDate

.........................
BondSecurityLastTradeDate
.........................


Returns the last trade date from a bond security.



+--------------+--------------------------+
| Parameter    | Description              |
+==============+==========================+
| bondSecurity | A bond security to query |
+--------------+--------------------------+



BondSecurityMarket

..................
BondSecurityMarket
..................


Returns the market from a bond security.



+--------------+--------------------------+
| Parameter    | Description              |
+==============+==========================+
| bondSecurity | A bond security to query |
+--------------+--------------------------+



BondSecurityMinimumAmount

.........................
BondSecurityMinimumAmount
.........................


Returns the minimum amount from a bond security.



+--------------+--------------------------+
| Parameter    | Description              |
+==============+==========================+
| bondSecurity | A bond security to query |
+--------------+--------------------------+



BondSecurityMinimumIncrement

............................
BondSecurityMinimumIncrement
............................


Returns the minimum increment from a bond security.



+--------------+--------------------------+
| Parameter    | Description              |
+==============+==========================+
| bondSecurity | A bond security to query |
+--------------+--------------------------+



BondSecurityParAmount

.....................
BondSecurityParAmount
.....................


Returns the par amount from a bond security.



+--------------+--------------------------+
| Parameter    | Description              |
+==============+==========================+
| bondSecurity | A bond security to query |
+--------------+--------------------------+



BondSecurityRedemptionValue

...........................
BondSecurityRedemptionValue
...........................


Returns the redemption value from a bond security.



+--------------+--------------------------+
| Parameter    | Description              |
+==============+==========================+
| bondSecurity | A bond security to query |
+--------------+--------------------------+



BondSecuritySetAnnouncementDate

...............................
BondSecuritySetAnnouncementDate
...............................


Updates the announcement date of a bond security. The original object is unchanged - a new object is returned with the updated value.



+------------------+---------------------------+
| Parameter        | Description               |
+==================+===========================+
| bondSecurity     | A bond security to update |
+------------------+---------------------------+
| announcementDate | The announcement date     |
+------------------+---------------------------+



BondSecuritySetBusinessDayConvention

....................................
BondSecuritySetBusinessDayConvention
....................................


Updates the business day convention of a bond security. The original object is unchanged - a new object is returned with the updated value.



+-----------------------+-----------------------------+
| Parameter             | Description                 |
+=======================+=============================+
| bondSecurity          | A bond security to update   |
+-----------------------+-----------------------------+
| businessDayConvention | The business day convention |
+-----------------------+-----------------------------+



BondSecuritySetCouponFrequency

..............................
BondSecuritySetCouponFrequency
..............................


Updates the coupon frequency of a bond security. The original object is unchanged - a new object is returned with the updated value.



+-----------------+---------------------------+
| Parameter       | Description               |
+=================+===========================+
| bondSecurity    | A bond security to update |
+-----------------+---------------------------+
| couponFrequency | The coupon frequency      |
+-----------------+---------------------------+



BondSecuritySetCouponRate

.........................
BondSecuritySetCouponRate
.........................


Updates the coupon rate of a bond security. The original object is unchanged - a new object is returned with the updated value.



+--------------+---------------------------+
| Parameter    | Description               |
+==============+===========================+
| bondSecurity | A bond security to update |
+--------------+---------------------------+
| couponRate   | The coupon rate           |
+--------------+---------------------------+



BondSecuritySetCouponType

.........................
BondSecuritySetCouponType
.........................


Updates the coupon type of a bond security. The original object is unchanged - a new object is returned with the updated value.



+--------------+---------------------------+
| Parameter    | Description               |
+==============+===========================+
| bondSecurity | A bond security to update |
+--------------+---------------------------+
| couponType   | The coupon type           |
+--------------+---------------------------+



BondSecuritySetCurrency

.......................
BondSecuritySetCurrency
.......................


Updates the currency of a bond security. The original object is unchanged - a new object is returned with the updated value.



+--------------+---------------------------+
| Parameter    | Description               |
+==============+===========================+
| bondSecurity | A bond security to update |
+--------------+---------------------------+
| currency     | The currency              |
+--------------+---------------------------+



BondSecuritySetDayCount

.......................
BondSecuritySetDayCount
.......................


Updates the day count convention of a bond security. The original object is unchanged - a new object is returned with the updated value.



+--------------+---------------------------+
| Parameter    | Description               |
+==============+===========================+
| bondSecurity | A bond security to update |
+--------------+---------------------------+
| dayCount     | The day count convention  |
+--------------+---------------------------+



BondSecuritySetFirstCouponDate

..............................
BondSecuritySetFirstCouponDate
..............................


Updates the first coupon date of a bond security. The original object is unchanged - a new object is returned with the updated value.



+-----------------+---------------------------+
| Parameter       | Description               |
+=================+===========================+
| bondSecurity    | A bond security to update |
+-----------------+---------------------------+
| firstCouponDate | The first coupon date     |
+-----------------+---------------------------+



BondSecuritySetGuaranteeType

............................
BondSecuritySetGuaranteeType
............................


Updates the guarantee type of a bond security. The original object is unchanged - a new object is returned with the updated value.



+---------------+---------------------------+
| Parameter     | Description               |
+===============+===========================+
| bondSecurity  | A bond security to update |
+---------------+---------------------------+
| guaranteeType | The guarantee type        |
+---------------+---------------------------+



BondSecuritySetInterestAccrualDate

..................................
BondSecuritySetInterestAccrualDate
..................................


Updates the interest accrual date of a bond security. The original object is unchanged - a new object is returned with the updated value.



+---------------------+---------------------------+
| Parameter           | Description               |
+=====================+===========================+
| bondSecurity        | A bond security to update |
+---------------------+---------------------------+
| interestAccrualDate | The interest accrual date |
+---------------------+---------------------------+



BondSecuritySetIssuancePrice

............................
BondSecuritySetIssuancePrice
............................


Updates the issuance price of a bond security. The original object is unchanged - a new object is returned with the updated value.



+---------------+---------------------------+
| Parameter     | Description               |
+===============+===========================+
| bondSecurity  | A bond security to update |
+---------------+---------------------------+
| issuancePrice | The issuance price        |
+---------------+---------------------------+



BondSecuritySetIssuerDomicile

.............................
BondSecuritySetIssuerDomicile
.............................


Updates the issuer domicile of a bond security. The original object is unchanged - a new object is returned with the updated value.



+----------------+---------------------------+
| Parameter      | Description               |
+================+===========================+
| bondSecurity   | A bond security to update |
+----------------+---------------------------+
| issuerDomicile | The issuer domicile       |
+----------------+---------------------------+



BondSecuritySetIssuerName

.........................
BondSecuritySetIssuerName
.........................


Updates the issuer name of a bond security. The original object is unchanged - a new object is returned with the updated value.



+--------------+---------------------------+
| Parameter    | Description               |
+==============+===========================+
| bondSecurity | A bond security to update |
+--------------+---------------------------+
| issuerName   | The issuer name           |
+--------------+---------------------------+



BondSecuritySetIssuerType

.........................
BondSecuritySetIssuerType
.........................


Updates the issuer type of a bond security. The original object is unchanged - a new object is returned with the updated value.



+--------------+---------------------------+
| Parameter    | Description               |
+==============+===========================+
| bondSecurity | A bond security to update |
+--------------+---------------------------+
| issuerType   | The issuer type           |
+--------------+---------------------------+



BondSecuritySetLastTradeDate

............................
BondSecuritySetLastTradeDate
............................


Updates the last trade date of a bond security. The original object is unchanged - a new object is returned with the updated value.



+---------------+---------------------------+
| Parameter     | Description               |
+===============+===========================+
| bondSecurity  | A bond security to update |
+---------------+---------------------------+
| lastTradeDate | The last trade date       |
+---------------+---------------------------+



BondSecuritySetMarket

.....................
BondSecuritySetMarket
.....................


Updates the market of a bond security. The original object is unchanged - a new object is returned with the updated value.



+--------------+---------------------------+
| Parameter    | Description               |
+==============+===========================+
| bondSecurity | A bond security to update |
+--------------+---------------------------+
| market       | The market                |
+--------------+---------------------------+



BondSecuritySetMinimumAmount

............................
BondSecuritySetMinimumAmount
............................


Updates the minimum amount of a bond security. The original object is unchanged - a new object is returned with the updated value.



+---------------+---------------------------+
| Parameter     | Description               |
+===============+===========================+
| bondSecurity  | A bond security to update |
+---------------+---------------------------+
| minimumAmount | The minimum amount        |
+---------------+---------------------------+



BondSecuritySetMinimumIncrement

...............................
BondSecuritySetMinimumIncrement
...............................


Updates the minimum increment of a bond security. The original object is unchanged - a new object is returned with the updated value.



+------------------+---------------------------+
| Parameter        | Description               |
+==================+===========================+
| bondSecurity     | A bond security to update |
+------------------+---------------------------+
| minimumIncrement | The minimum increment     |
+------------------+---------------------------+



BondSecuritySetParAmount

........................
BondSecuritySetParAmount
........................


Updates the par amount of a bond security. The original object is unchanged - a new object is returned with the updated value.



+--------------+---------------------------+
| Parameter    | Description               |
+==============+===========================+
| bondSecurity | A bond security to update |
+--------------+---------------------------+
| parAmount    | The par amount            |
+--------------+---------------------------+



BondSecuritySetRedemptionValue

..............................
BondSecuritySetRedemptionValue
..............................


Updates the redemption value of a bond security. The original object is unchanged - a new object is returned with the updated value.



+-----------------+---------------------------+
| Parameter       | Description               |
+=================+===========================+
| bondSecurity    | A bond security to update |
+-----------------+---------------------------+
| redemptionValue | The redemption value      |
+-----------------+---------------------------+



BondSecuritySetSettlementDate

.............................
BondSecuritySetSettlementDate
.............................


Updates the settlement date of a bond security. The original object is unchanged - a new object is returned with the updated value.



+----------------+---------------------------+
| Parameter      | Description               |
+================+===========================+
| bondSecurity   | A bond security to update |
+----------------+---------------------------+
| settlementDate | The settlement date       |
+----------------+---------------------------+



BondSecuritySetTotalAmountIssued

................................
BondSecuritySetTotalAmountIssued
................................


Updates the total amount issued of a bond security. The original object is unchanged - a new object is returned with the updated value.



+-------------------+---------------------------+
| Parameter         | Description               |
+===================+===========================+
| bondSecurity      | A bond security to update |
+-------------------+---------------------------+
| totalAmountIssued | The total amount issued   |
+-------------------+---------------------------+



BondSecuritySetYieldConvention

..............................
BondSecuritySetYieldConvention
..............................


Updates the yield convention of a bond security. The original object is unchanged - a new object is returned with the updated value.



+-----------------+---------------------------+
| Parameter       | Description               |
+=================+===========================+
| bondSecurity    | A bond security to update |
+-----------------+---------------------------+
| yieldConvention | The yield convention      |
+-----------------+---------------------------+



BondSecuritySettlementDate

..........................
BondSecuritySettlementDate
..........................


Returns the settlement date from a bond security.



+--------------+--------------------------+
| Parameter    | Description              |
+==============+==========================+
| bondSecurity | A bond security to query |
+--------------+--------------------------+



BondSecurityTotalAmountIssued

.............................
BondSecurityTotalAmountIssued
.............................


Returns the total amount issued from a bond security.



+--------------+--------------------------+
| Parameter    | Description              |
+==============+==========================+
| bondSecurity | A bond security to query |
+--------------+--------------------------+



BondSecurityYieldConvention

...........................
BondSecurityYieldConvention
...........................


Returns the yield convention from a bond security.



+--------------+--------------------------+
| Parameter    | Description              |
+==============+==========================+
| bondSecurity | A bond security to query |
+--------------+--------------------------+



