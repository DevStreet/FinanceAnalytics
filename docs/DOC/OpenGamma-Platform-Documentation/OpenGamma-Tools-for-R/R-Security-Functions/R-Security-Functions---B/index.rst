title: R Security Functions - B
shortcut: DOC:R Security Functions - B
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



+------------------+----------+--------------------------------------------------+
| Parameter        | Required | Description                                      |
+==================+==========+==================================================+
| identifiers      | Yes      | The identifier bundle describing the deliverable |
+------------------+----------+--------------------------------------------------+
| conversionFactor | Yes      | The conversion factor                            |
+------------------+----------+--------------------------------------------------+




BondFutureSecurity

..................
BondFutureSecurity
..................


Defines a bond future security.



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
| basket             | Yes      | The basket of deliverables                |
+--------------------+----------+-------------------------------------------+
| firstDeliveryDate  | Yes      | The first delivery date                   |
+--------------------+----------+-------------------------------------------+
| lastDeliveryDate   | Yes      | The last delivery date                    |
+--------------------+----------+-------------------------------------------+
| contractCategory   | Yes      | The category                              |
+--------------------+----------+-------------------------------------------+




ExpandBondFutureDeliverable

...........................
ExpandBondFutureDeliverable
...........................


Expand the contents of a deliverable for a bond future.



+-----------------------+----------+------------------------------------------+
| Parameter             | Required | Description                              |
+=======================+==========+==========================================+
| bondFutureDeliverable | Yes      | A deliverable for a bond future to query |
+-----------------------+----------+------------------------------------------+




ExpandBondFutureSecurity

........................
ExpandBondFutureSecurity
........................


Expand the contents of a bond future security.



+--------------------+----------+---------------------------------+
| Parameter          | Required | Description                     |
+====================+==========+=================================+
| bondFutureSecurity | Yes      | A bond future security to query |
+--------------------+----------+---------------------------------+




ExpandBondSecurity

..................
ExpandBondSecurity
..................


Expand the contents of a bond security.



+--------------+----------+--------------------------+
| Parameter    | Required | Description              |
+==============+==========+==========================+
| bondSecurity | Yes      | A bond security to query |
+--------------+----------+--------------------------+




GetBondFutureDeliverableConversionFactor

........................................
GetBondFutureDeliverableConversionFactor
........................................


Returns the conversion factor from a deliverable for a bond future.



+-----------------------+----------+------------------------------------------+
| Parameter             | Required | Description                              |
+=======================+==========+==========================================+
| bondFutureDeliverable | Yes      | A deliverable for a bond future to query |
+-----------------------+----------+------------------------------------------+




GetBondFutureDeliverableIdentifiers

...................................
GetBondFutureDeliverableIdentifiers
...................................


Returns the identifier bundle describing the deliverable from a deliverable for a bond future.



+-----------------------+----------+------------------------------------------+
| Parameter             | Required | Description                              |
+=======================+==========+==========================================+
| bondFutureDeliverable | Yes      | A deliverable for a bond future to query |
+-----------------------+----------+------------------------------------------+




GetBondFutureSecurityBasket

...........................
GetBondFutureSecurityBasket
...........................


Returns the basket of deliverables from a bond future security.



+--------------------+----------+---------------------------------+
| Parameter          | Required | Description                     |
+====================+==========+=================================+
| bondFutureSecurity | Yes      | A bond future security to query |
+--------------------+----------+---------------------------------+




GetBondFutureSecurityFirstDeliveryDate

......................................
GetBondFutureSecurityFirstDeliveryDate
......................................


Returns the first delivery date from a bond future security.



+--------------------+----------+---------------------------------+
| Parameter          | Required | Description                     |
+====================+==========+=================================+
| bondFutureSecurity | Yes      | A bond future security to query |
+--------------------+----------+---------------------------------+




GetBondFutureSecurityLastDeliveryDate

.....................................
GetBondFutureSecurityLastDeliveryDate
.....................................


Returns the last delivery date from a bond future security.



+--------------------+----------+---------------------------------+
| Parameter          | Required | Description                     |
+====================+==========+=================================+
| bondFutureSecurity | Yes      | A bond future security to query |
+--------------------+----------+---------------------------------+




GetBondSecurityAnnouncementDate

...............................
GetBondSecurityAnnouncementDate
...............................


Returns the announcement date from a bond security.



+--------------+----------+--------------------------+
| Parameter    | Required | Description              |
+==============+==========+==========================+
| bondSecurity | Yes      | A bond security to query |
+--------------+----------+--------------------------+




GetBondSecurityBusinessDayConvention

....................................
GetBondSecurityBusinessDayConvention
....................................


Returns the business day convention from a bond security.



+--------------+----------+--------------------------+
| Parameter    | Required | Description              |
+==============+==========+==========================+
| bondSecurity | Yes      | A bond security to query |
+--------------+----------+--------------------------+




GetBondSecurityCouponFrequency

..............................
GetBondSecurityCouponFrequency
..............................


Returns the coupon frequency from a bond security.



+--------------+----------+--------------------------+
| Parameter    | Required | Description              |
+==============+==========+==========================+
| bondSecurity | Yes      | A bond security to query |
+--------------+----------+--------------------------+




GetBondSecurityCouponRate

.........................
GetBondSecurityCouponRate
.........................


Returns the coupon rate from a bond security.



+--------------+----------+--------------------------+
| Parameter    | Required | Description              |
+==============+==========+==========================+
| bondSecurity | Yes      | A bond security to query |
+--------------+----------+--------------------------+




GetBondSecurityCouponType

.........................
GetBondSecurityCouponType
.........................


Returns the coupon type from a bond security.



+--------------+----------+--------------------------+
| Parameter    | Required | Description              |
+==============+==========+==========================+
| bondSecurity | Yes      | A bond security to query |
+--------------+----------+--------------------------+




GetBondSecurityCurrency

.......................
GetBondSecurityCurrency
.......................


Returns the currency from a bond security.



+--------------+----------+--------------------------+
| Parameter    | Required | Description              |
+==============+==========+==========================+
| bondSecurity | Yes      | A bond security to query |
+--------------+----------+--------------------------+




GetBondSecurityDayCount

.......................
GetBondSecurityDayCount
.......................


Returns the day count convention from a bond security.



+--------------+----------+--------------------------+
| Parameter    | Required | Description              |
+==============+==========+==========================+
| bondSecurity | Yes      | A bond security to query |
+--------------+----------+--------------------------+




GetBondSecurityFirstCouponDate

..............................
GetBondSecurityFirstCouponDate
..............................


Returns the first coupon date from a bond security.



+--------------+----------+--------------------------+
| Parameter    | Required | Description              |
+==============+==========+==========================+
| bondSecurity | Yes      | A bond security to query |
+--------------+----------+--------------------------+




GetBondSecurityGuaranteeType

............................
GetBondSecurityGuaranteeType
............................


Returns the guarantee type from a bond security.



+--------------+----------+--------------------------+
| Parameter    | Required | Description              |
+==============+==========+==========================+
| bondSecurity | Yes      | A bond security to query |
+--------------+----------+--------------------------+




GetBondSecurityInterestAccrualDate

..................................
GetBondSecurityInterestAccrualDate
..................................


Returns the interest accrual date from a bond security.



+--------------+----------+--------------------------+
| Parameter    | Required | Description              |
+==============+==========+==========================+
| bondSecurity | Yes      | A bond security to query |
+--------------+----------+--------------------------+




GetBondSecurityIssuancePrice

............................
GetBondSecurityIssuancePrice
............................


Returns the issuance price from a bond security.



+--------------+----------+--------------------------+
| Parameter    | Required | Description              |
+==============+==========+==========================+
| bondSecurity | Yes      | A bond security to query |
+--------------+----------+--------------------------+




GetBondSecurityIssuerDomicile

.............................
GetBondSecurityIssuerDomicile
.............................


Returns the issuer domicile from a bond security.



+--------------+----------+--------------------------+
| Parameter    | Required | Description              |
+==============+==========+==========================+
| bondSecurity | Yes      | A bond security to query |
+--------------+----------+--------------------------+




GetBondSecurityIssuerName

.........................
GetBondSecurityIssuerName
.........................


Returns the issuer name from a bond security.



+--------------+----------+--------------------------+
| Parameter    | Required | Description              |
+==============+==========+==========================+
| bondSecurity | Yes      | A bond security to query |
+--------------+----------+--------------------------+




GetBondSecurityIssuerType

.........................
GetBondSecurityIssuerType
.........................


Returns the issuer type from a bond security.



+--------------+----------+--------------------------+
| Parameter    | Required | Description              |
+==============+==========+==========================+
| bondSecurity | Yes      | A bond security to query |
+--------------+----------+--------------------------+




GetBondSecurityLastTradeDate

............................
GetBondSecurityLastTradeDate
............................


Returns the last trade date from a bond security.



+--------------+----------+--------------------------+
| Parameter    | Required | Description              |
+==============+==========+==========================+
| bondSecurity | Yes      | A bond security to query |
+--------------+----------+--------------------------+




GetBondSecurityMarket

.....................
GetBondSecurityMarket
.....................


Returns the market from a bond security.



+--------------+----------+--------------------------+
| Parameter    | Required | Description              |
+==============+==========+==========================+
| bondSecurity | Yes      | A bond security to query |
+--------------+----------+--------------------------+




GetBondSecurityMinimumAmount

............................
GetBondSecurityMinimumAmount
............................


Returns the minimum amount from a bond security.



+--------------+----------+--------------------------+
| Parameter    | Required | Description              |
+==============+==========+==========================+
| bondSecurity | Yes      | A bond security to query |
+--------------+----------+--------------------------+




GetBondSecurityMinimumIncrement

...............................
GetBondSecurityMinimumIncrement
...............................


Returns the minimum increment from a bond security.



+--------------+----------+--------------------------+
| Parameter    | Required | Description              |
+==============+==========+==========================+
| bondSecurity | Yes      | A bond security to query |
+--------------+----------+--------------------------+




GetBondSecurityParAmount

........................
GetBondSecurityParAmount
........................


Returns the par amount from a bond security.



+--------------+----------+--------------------------+
| Parameter    | Required | Description              |
+==============+==========+==========================+
| bondSecurity | Yes      | A bond security to query |
+--------------+----------+--------------------------+




GetBondSecurityRedemptionValue

..............................
GetBondSecurityRedemptionValue
..............................


Returns the redemption value from a bond security.



+--------------+----------+--------------------------+
| Parameter    | Required | Description              |
+==============+==========+==========================+
| bondSecurity | Yes      | A bond security to query |
+--------------+----------+--------------------------+




GetBondSecuritySettlementDate

.............................
GetBondSecuritySettlementDate
.............................


Returns the settlement date from a bond security.



+--------------+----------+--------------------------+
| Parameter    | Required | Description              |
+==============+==========+==========================+
| bondSecurity | Yes      | A bond security to query |
+--------------+----------+--------------------------+




GetBondSecurityTotalAmountIssued

................................
GetBondSecurityTotalAmountIssued
................................


Returns the total amount issued from a bond security.



+--------------+----------+--------------------------+
| Parameter    | Required | Description              |
+==============+==========+==========================+
| bondSecurity | Yes      | A bond security to query |
+--------------+----------+--------------------------+




GetBondSecurityYieldConvention

..............................
GetBondSecurityYieldConvention
..............................


Returns the yield convention from a bond security.



+--------------+----------+--------------------------+
| Parameter    | Required | Description              |
+==============+==========+==========================+
| bondSecurity | Yes      | A bond security to query |
+--------------+----------+--------------------------+




SetBondFutureDeliverableConversionFactor

........................................
SetBondFutureDeliverableConversionFactor
........................................


Updates the conversion factor of a deliverable for a bond future. The original object is unchanged - a new object is returned with the updated value.



+-----------------------+----------+-------------------------------------------+
| Parameter             | Required | Description                               |
+=======================+==========+===========================================+
| bondFutureDeliverable | Yes      | A deliverable for a bond future to update |
+-----------------------+----------+-------------------------------------------+
| conversionFactor      | Yes      | The conversion factor                     |
+-----------------------+----------+-------------------------------------------+




SetBondFutureDeliverableIdentifiers

...................................
SetBondFutureDeliverableIdentifiers
...................................


Updates the identifier bundle describing the deliverable of a deliverable for a bond future. The original object is unchanged - a new object is returned with the updated value.



+-----------------------+----------+--------------------------------------------------+
| Parameter             | Required | Description                                      |
+=======================+==========+==================================================+
| bondFutureDeliverable | Yes      | A deliverable for a bond future to update        |
+-----------------------+----------+--------------------------------------------------+
| identifiers           |          | The identifier bundle describing the deliverable |
+-----------------------+----------+--------------------------------------------------+




SetBondFutureSecurityBasket

...........................
SetBondFutureSecurityBasket
...........................


Updates the basket of deliverables of a bond future security. The original object is unchanged - a new object is returned with the updated value.



+--------------------+----------+----------------------------------+
| Parameter          | Required | Description                      |
+====================+==========+==================================+
| bondFutureSecurity | Yes      | A bond future security to update |
+--------------------+----------+----------------------------------+
| basket             |          | The basket of deliverables       |
+--------------------+----------+----------------------------------+




SetBondFutureSecurityFirstDeliveryDate

......................................
SetBondFutureSecurityFirstDeliveryDate
......................................


Updates the first delivery date of a bond future security. The original object is unchanged - a new object is returned with the updated value.



+--------------------+----------+----------------------------------+
| Parameter          | Required | Description                      |
+====================+==========+==================================+
| bondFutureSecurity | Yes      | A bond future security to update |
+--------------------+----------+----------------------------------+
| firstDeliveryDate  |          | The first delivery date          |
+--------------------+----------+----------------------------------+




SetBondFutureSecurityLastDeliveryDate

.....................................
SetBondFutureSecurityLastDeliveryDate
.....................................


Updates the last delivery date of a bond future security. The original object is unchanged - a new object is returned with the updated value.



+--------------------+----------+----------------------------------+
| Parameter          | Required | Description                      |
+====================+==========+==================================+
| bondFutureSecurity | Yes      | A bond future security to update |
+--------------------+----------+----------------------------------+
| lastDeliveryDate   |          | The last delivery date           |
+--------------------+----------+----------------------------------+




SetBondSecurityAnnouncementDate

...............................
SetBondSecurityAnnouncementDate
...............................


Updates the announcement date of a bond security. The original object is unchanged - a new object is returned with the updated value.



+------------------+----------+---------------------------+
| Parameter        | Required | Description               |
+==================+==========+===========================+
| bondSecurity     | Yes      | A bond security to update |
+------------------+----------+---------------------------+
| announcementDate |          | The announcement date     |
+------------------+----------+---------------------------+




SetBondSecurityBusinessDayConvention

....................................
SetBondSecurityBusinessDayConvention
....................................


Updates the business day convention of a bond security. The original object is unchanged - a new object is returned with the updated value.



+-----------------------+----------+-----------------------------+
| Parameter             | Required | Description                 |
+=======================+==========+=============================+
| bondSecurity          | Yes      | A bond security to update   |
+-----------------------+----------+-----------------------------+
| businessDayConvention |          | The business day convention |
+-----------------------+----------+-----------------------------+




SetBondSecurityCouponFrequency

..............................
SetBondSecurityCouponFrequency
..............................


Updates the coupon frequency of a bond security. The original object is unchanged - a new object is returned with the updated value.



+-----------------+----------+---------------------------+
| Parameter       | Required | Description               |
+=================+==========+===========================+
| bondSecurity    | Yes      | A bond security to update |
+-----------------+----------+---------------------------+
| couponFrequency |          | The coupon frequency      |
+-----------------+----------+---------------------------+




SetBondSecurityCouponRate

.........................
SetBondSecurityCouponRate
.........................


Updates the coupon rate of a bond security. The original object is unchanged - a new object is returned with the updated value.



+--------------+----------+---------------------------+
| Parameter    | Required | Description               |
+==============+==========+===========================+
| bondSecurity | Yes      | A bond security to update |
+--------------+----------+---------------------------+
| couponRate   | Yes      | The coupon rate           |
+--------------+----------+---------------------------+




SetBondSecurityCouponType

.........................
SetBondSecurityCouponType
.........................


Updates the coupon type of a bond security. The original object is unchanged - a new object is returned with the updated value.



+--------------+----------+---------------------------+
| Parameter    | Required | Description               |
+==============+==========+===========================+
| bondSecurity | Yes      | A bond security to update |
+--------------+----------+---------------------------+
| couponType   |          | The coupon type           |
+--------------+----------+---------------------------+




SetBondSecurityCurrency

.......................
SetBondSecurityCurrency
.......................


Updates the currency of a bond security. The original object is unchanged - a new object is returned with the updated value.



+--------------+----------+---------------------------+
| Parameter    | Required | Description               |
+==============+==========+===========================+
| bondSecurity | Yes      | A bond security to update |
+--------------+----------+---------------------------+
| currency     |          | The currency              |
+--------------+----------+---------------------------+




SetBondSecurityDayCount

.......................
SetBondSecurityDayCount
.......................


Updates the day count convention of a bond security. The original object is unchanged - a new object is returned with the updated value.



+--------------+----------+---------------------------+
| Parameter    | Required | Description               |
+==============+==========+===========================+
| bondSecurity | Yes      | A bond security to update |
+--------------+----------+---------------------------+
| dayCount     |          | The day count convention  |
+--------------+----------+---------------------------+




SetBondSecurityFirstCouponDate

..............................
SetBondSecurityFirstCouponDate
..............................


Updates the first coupon date of a bond security. The original object is unchanged - a new object is returned with the updated value.



+-----------------+----------+---------------------------+
| Parameter       | Required | Description               |
+=================+==========+===========================+
| bondSecurity    | Yes      | A bond security to update |
+-----------------+----------+---------------------------+
| firstCouponDate |          | The first coupon date     |
+-----------------+----------+---------------------------+




SetBondSecurityGuaranteeType

............................
SetBondSecurityGuaranteeType
............................


Updates the guarantee type of a bond security. The original object is unchanged - a new object is returned with the updated value.



+---------------+----------+---------------------------+
| Parameter     | Required | Description               |
+===============+==========+===========================+
| bondSecurity  | Yes      | A bond security to update |
+---------------+----------+---------------------------+
| guaranteeType |          | The guarantee type        |
+---------------+----------+---------------------------+




SetBondSecurityInterestAccrualDate

..................................
SetBondSecurityInterestAccrualDate
..................................


Updates the interest accrual date of a bond security. The original object is unchanged - a new object is returned with the updated value.



+---------------------+----------+---------------------------+
| Parameter           | Required | Description               |
+=====================+==========+===========================+
| bondSecurity        | Yes      | A bond security to update |
+---------------------+----------+---------------------------+
| interestAccrualDate |          | The interest accrual date |
+---------------------+----------+---------------------------+




SetBondSecurityIssuancePrice

............................
SetBondSecurityIssuancePrice
............................


Updates the issuance price of a bond security. The original object is unchanged - a new object is returned with the updated value.



+---------------+----------+---------------------------+
| Parameter     | Required | Description               |
+===============+==========+===========================+
| bondSecurity  | Yes      | A bond security to update |
+---------------+----------+---------------------------+
| issuancePrice |          | The issuance price        |
+---------------+----------+---------------------------+




SetBondSecurityIssuerDomicile

.............................
SetBondSecurityIssuerDomicile
.............................


Updates the issuer domicile of a bond security. The original object is unchanged - a new object is returned with the updated value.



+----------------+----------+---------------------------+
| Parameter      | Required | Description               |
+================+==========+===========================+
| bondSecurity   | Yes      | A bond security to update |
+----------------+----------+---------------------------+
| issuerDomicile |          | The issuer domicile       |
+----------------+----------+---------------------------+




SetBondSecurityIssuerName

.........................
SetBondSecurityIssuerName
.........................


Updates the issuer name of a bond security. The original object is unchanged - a new object is returned with the updated value.



+--------------+----------+---------------------------+
| Parameter    | Required | Description               |
+==============+==========+===========================+
| bondSecurity | Yes      | A bond security to update |
+--------------+----------+---------------------------+
| issuerName   |          | The issuer name           |
+--------------+----------+---------------------------+




SetBondSecurityIssuerType

.........................
SetBondSecurityIssuerType
.........................


Updates the issuer type of a bond security. The original object is unchanged - a new object is returned with the updated value.



+--------------+----------+---------------------------+
| Parameter    | Required | Description               |
+==============+==========+===========================+
| bondSecurity | Yes      | A bond security to update |
+--------------+----------+---------------------------+
| issuerType   |          | The issuer type           |
+--------------+----------+---------------------------+




SetBondSecurityLastTradeDate

............................
SetBondSecurityLastTradeDate
............................


Updates the last trade date of a bond security. The original object is unchanged - a new object is returned with the updated value.



+---------------+----------+---------------------------+
| Parameter     | Required | Description               |
+===============+==========+===========================+
| bondSecurity  | Yes      | A bond security to update |
+---------------+----------+---------------------------+
| lastTradeDate |          | The last trade date       |
+---------------+----------+---------------------------+




SetBondSecurityMarket

.....................
SetBondSecurityMarket
.....................


Updates the market of a bond security. The original object is unchanged - a new object is returned with the updated value.



+--------------+----------+---------------------------+
| Parameter    | Required | Description               |
+==============+==========+===========================+
| bondSecurity | Yes      | A bond security to update |
+--------------+----------+---------------------------+
| market       |          | The market                |
+--------------+----------+---------------------------+




SetBondSecurityMinimumAmount

............................
SetBondSecurityMinimumAmount
............................


Updates the minimum amount of a bond security. The original object is unchanged - a new object is returned with the updated value.



+---------------+----------+---------------------------+
| Parameter     | Required | Description               |
+===============+==========+===========================+
| bondSecurity  | Yes      | A bond security to update |
+---------------+----------+---------------------------+
| minimumAmount | Yes      | The minimum amount        |
+---------------+----------+---------------------------+




SetBondSecurityMinimumIncrement

...............................
SetBondSecurityMinimumIncrement
...............................


Updates the minimum increment of a bond security. The original object is unchanged - a new object is returned with the updated value.



+------------------+----------+---------------------------+
| Parameter        | Required | Description               |
+==================+==========+===========================+
| bondSecurity     | Yes      | A bond security to update |
+------------------+----------+---------------------------+
| minimumIncrement | Yes      | The minimum increment     |
+------------------+----------+---------------------------+




SetBondSecurityParAmount

........................
SetBondSecurityParAmount
........................


Updates the par amount of a bond security. The original object is unchanged - a new object is returned with the updated value.



+--------------+----------+---------------------------+
| Parameter    | Required | Description               |
+==============+==========+===========================+
| bondSecurity | Yes      | A bond security to update |
+--------------+----------+---------------------------+
| parAmount    | Yes      | The par amount            |
+--------------+----------+---------------------------+




SetBondSecurityRedemptionValue

..............................
SetBondSecurityRedemptionValue
..............................


Updates the redemption value of a bond security. The original object is unchanged - a new object is returned with the updated value.



+-----------------+----------+---------------------------+
| Parameter       | Required | Description               |
+=================+==========+===========================+
| bondSecurity    | Yes      | A bond security to update |
+-----------------+----------+---------------------------+
| redemptionValue | Yes      | The redemption value      |
+-----------------+----------+---------------------------+




SetBondSecuritySettlementDate

.............................
SetBondSecuritySettlementDate
.............................


Updates the settlement date of a bond security. The original object is unchanged - a new object is returned with the updated value.



+----------------+----------+---------------------------+
| Parameter      | Required | Description               |
+================+==========+===========================+
| bondSecurity   | Yes      | A bond security to update |
+----------------+----------+---------------------------+
| settlementDate |          | The settlement date       |
+----------------+----------+---------------------------+




SetBondSecurityTotalAmountIssued

................................
SetBondSecurityTotalAmountIssued
................................


Updates the total amount issued of a bond security. The original object is unchanged - a new object is returned with the updated value.



+-------------------+----------+---------------------------+
| Parameter         | Required | Description               |
+===================+==========+===========================+
| bondSecurity      | Yes      | A bond security to update |
+-------------------+----------+---------------------------+
| totalAmountIssued | Yes      | The total amount issued   |
+-------------------+----------+---------------------------+




SetBondSecurityYieldConvention

..............................
SetBondSecurityYieldConvention
..............................


Updates the yield convention of a bond security. The original object is unchanged - a new object is returned with the updated value.



+-----------------+----------+---------------------------+
| Parameter       | Required | Description               |
+=================+==========+===========================+
| bondSecurity    | Yes      | A bond security to update |
+-----------------+----------+---------------------------+
| yieldConvention |          | The yield convention      |
+-----------------+----------+---------------------------+



