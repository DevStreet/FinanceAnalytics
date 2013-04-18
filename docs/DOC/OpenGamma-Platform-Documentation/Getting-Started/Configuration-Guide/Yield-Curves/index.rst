title: Yield Curves
shortcut: DOC:Yield Curves
---
...............................
How yields curves are specified
...............................


Yield curves are specified using two structures: a *yield curve definition* and a *yield curve specification builder configuration*.  The *yield curve specification builder configuration* is a data structure that specifies a set of market instruments (securities) that can be used as nodal points for the construction of a yield curve.  It is intended to be fairly exhaustive and provides a great deal of overlap across the asset classes that it supports.  The intention is that this structure then becomes a 'menu' that the creator of a *yield curve definition* can choose from, making it much easier to quickly specify and change curve definitions, than if the instrument codes of the underlying instruments had to be entered by hand each time.

~~~~~~~
Dynamic
~~~~~~~


Because some common constituents of yield curves are specified relative to fixed dates, curve definitions can often change over time.  The biggest example of this is Interest Rate Futures.  Because they are specified with a fixed expiry, as time moves forward over days, weeks and months, the original futures the user wanted move towards the start of the yield curve and eventually expire.  In practice, the user typically has to remove futures from their curve definitions as they approach the short end of the curve (where there are shorter dated instruments such as cash, deposit or libor rates), and add new futures to the end of the middle section of the curve.  To reduce the amount of manual work in this kind of management, each point in the *yield curve specification builder configuration* is capable of dynamically producing an Identifier based on the curve date, and the tenor offset of the nodal point into the curve.  This means that the Identifiers produced vary over time.

Below is an example of a *yield curve specification builder configuration* containing mock identifiers to give you an idea:



+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| Tenor | LIBOR       | DEPOSIT       | SWAP       | FUTURES    | FRA          | TENOR SWAP      | OIS       |
+=======+=============+===============+============+============+==============+=================+===========+
| 1D    | USDLIBOR1D  | USDDEPOSIT1D  |            |            |              |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 2D    |             | USDDEPOSIT2D  |            |            |              |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 3D    |             | USDDEPOSIT3D  |            |            |              |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 7D    | USDLIBOR7D  | USDDEPOSIT7D  |            |            |              |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 14D   | USDLIBOR14D | USDDEPOSIT14D |            |            |              |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 21D   |             | USDDEPOSIT21D |            |            |              |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 1M    | USDLIBOR1M  | USDDEPOSIT1M  | USDSWAP1M  |            |              | USDTENORSWAP1M  | USDOIS1M  |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 2M    | USDLIBOR2M  | USDDEPOSIT2M  | USDSWAP2M  |            |              |                 | USDOIS2M  |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 3M    | USDLIBOR3M  | USDDEPOSIT3M  | USDSWAP3M  |            | USDFRA3M6M   | USDTENORSWAP3M  | USDOIS3M  |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 4M    | USDLIBOR4M  | USDDEPOSIT4M  | USDSWAP4M  |            |              |                 | USDOIS4M  |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 5M    | USDLIBOR5M  | USDDEPOSIT5M  | USDSWAP5M  |            |              |                 | USDOIS5M  |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 6M    | USDLIBOR6M  | USDDEPOSIT6M  | USDSWAP6M  |            | USDFRA6M9M   | USDTENORSWAP6M  | USDOIS6M  |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 7M    | USDLIBOR7M  | USDDEPOSIT7M  | USDSWAP7M  |            |              |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 8M    | USDLIBOR8M  | USDDEPOSIT8M  | USDSWAP8M  |            |              |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 9M    | USDLIBOR9M  | USDDEPOSIT9M  | USDSWAP9M  |            | USDFRA9M12M  | USDTENORSWAP9M  | USDOIS9M  |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 10M   | USDLIBOR10M | USDDEPOSIT10M | USDSWAP10M |            |              |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 11M   | USDLIBOR11M | USDDEPOSIT11M | USDSWAP11M |            |              |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 12M   | USDLIBOR12M | USDDEPOSIT12M |            | prefix: ED | USDFRA12M15M |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 1Y    |             | USDDEPOSIT1Y  | USDSWAP1Y  |            |              | USDTENORSWAP1Y  | USDOIS1Y  |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 15M   |             |               |            |            | USDFRA15M18M |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 18M   |             |               |            | prefix: ED | USDFRA18M21M |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 21M   |             |               |            |            | USDFRA21M24M |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 24M   |             |               |            | prefix: ED | USDFRA24M27M |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 2Y    |             | USDDEPOSIT2Y  | USDSWAP2Y  | prefix: ED |              | USDTENORSWAP2Y  | USDOIS2Y  |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 36M   |             |               |            | prefix: ED |              |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 3Y    |             | USDDEPOSIT3Y  | USDSWAP3Y  | prefix: ED |              | USDTENORSWAP3Y  | USDOIS3Y  |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 4Y    |             | USDDEPOSIT4Y  | USDSWAP4Y  |            |              | USDTENORSWAP4Y  | USDOIS4Y  |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 5Y    |             | USDDEPOSIT5Y  | USDSWAP5Y  |            |              | USDTENORSWAP5Y  | USDOIS5Y  |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 6Y    |             |               | USDSWAP6Y  |            |              | USDTENORSWAP6Y  |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 7Y    |             |               | USDSWAP7Y  |            |              | USDTENORSWAP7Y  |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 8Y    |             |               | USDSWAP8Y  |            |              | USDTENORSWAP8Y  |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 9Y    |             |               | USDSWAP9Y  |            |              | USDTENORSWAP9Y  |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 10Y   |             |               | USDSWAP10Y |            |              | USDTENORSWAP10Y | USDOIS10Y |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 11Y   |             |               | USDSWAP11Y |            |              |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 12Y   |             |               | USDSWAP12Y |            |              | USDTENORSWAP12Y |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 13Y   |             |               | USDSWAP13Y |            |              |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 14Y   |             |               | USDSWAP14Y |            |              |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 15Y   |             |               | USDSWAP15Y |            |              | USDTENORSWAP15Y |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 16Y   |             |               | USDSWAP16Y |            |              |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 17Y   |             |               | USDSWAP17Y |            |              |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 18Y   |             |               | USDSWAP18Y |            |              |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 19Y   |             |               | USDSWAP19Y |            |              |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 20Y   |             |               | USDSWAP20Y |            |              | USDTENORSWAP20Y | USDOIS20Y |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 21Y   |             |               | USDSWAP21Y |            |              |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 22Y   |             |               | USDSWAP22Y |            |              |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 23Y   |             |               | USDSWAP23Y |            |              |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 24Y   |             |               | USDSWAP24Y |            |              |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 25Y   |             |               | USDSWAP25Y |            |              | USDTENORSWAP25Y |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 26Y   |             |               | USDSWAP26Y |            |              |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 27Y   |             |               | USDSWAP27Y |            |              |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 28Y   |             |               | USDSWAP28Y |            |              |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 29Y   |             |               | USDSWAP29Y |            |              |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 30Y   |             |               | USDSWAP30Y |            |              | USDTENORSWAP30Y | USDOIS30Y |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 35Y   |             |               | USDSWAP35Y |            |              |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 40Y   |             |               | USDSWAP40Y |            |              |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 45Y   |             |               | USDSWAP45Y |            |              |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 50Y   |             |               | USDSWAP50Y |            |              |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+
| 60Y   |             |               | USDSWAP60Y |            |              |                 |           |
+-------+-------------+---------------+------------+------------+--------------+-----------------+-----------+



Each point on that matrix actually has a `CurveInstrumentProvider` object backing it.  The simplest implementations of this interface just return a hard-coded identifier, but more complex ones are available, that will, for example, generate identifiers based on a provided curve date and tenor or a curve date, tenor and number of futures.  This last function is used to calculate the identifier of a future offset from a specific point.  I can therefore set up a `CurveInstrumentProvider` that will calculate the 'nth' future on or after, say, one year from the curve date.  Where does the information to drive the `CurveInstrumentProviders` come from?  The `YieldCurveDefinition`.

~~~~~~~~~~~~~~~~~~~~~~~~~~
The Yield Curve Definition
~~~~~~~~~~~~~~~~~~~~~~~~~~


This object is much simpler, and will typically consist of something like:



+-------+-----------------+-------------------+-----------------+
| Tenor | Instrument Type | Number of futures | Convention Name |
+=======+=================+===================+=================+
| 1D    | LIBOR           |                   | DEFAULT         |
+-------+-----------------+-------------------+-----------------+
| 7D    | LIBOR           |                   | DEFAULT         |
+-------+-----------------+-------------------+-----------------+
| 14D   | LIBOR           |                   | DEFAULT         |
+-------+-----------------+-------------------+-----------------+
| 1M    | LIBOR           |                   | DEFAULT         |
+-------+-----------------+-------------------+-----------------+
| 2M    | LIBOR           |                   | DEFAULT         |
+-------+-----------------+-------------------+-----------------+
| 3M    | LIBOR           |                   | DEFAULT         |
+-------+-----------------+-------------------+-----------------+
| 6M    | LIBOR           |                   | DEFAULT         |
+-------+-----------------+-------------------+-----------------+
| 9M    | LIBOR           |                   | DEFAULT         |
+-------+-----------------+-------------------+-----------------+
| 12M   | LIBOR           |                   | DEFAULT         |
+-------+-----------------+-------------------+-----------------+
| 1Y    | FUTURE          | 1                 | DEFAULT         |
+-------+-----------------+-------------------+-----------------+
| 1Y    | FUTURE          | 2                 | DEFAULT         |
+-------+-----------------+-------------------+-----------------+
| 1Y    | FUTURE          | 3                 | DEFAULT         |
+-------+-----------------+-------------------+-----------------+
| 2Y    | SWAP            |                   | DEFAULT         |
+-------+-----------------+-------------------+-----------------+
| 3Y    | SWAP            |                   | DEFAULT         |
+-------+-----------------+-------------------+-----------------+
| 4Y    | SWAP            |                   | DEFAULT         |
+-------+-----------------+-------------------+-----------------+
| 5Y    | SWAP            |                   | DEFAULT         |
+-------+-----------------+-------------------+-----------------+
| 7Y    | SWAP            |                   | DEFAULT         |
+-------+-----------------+-------------------+-----------------+
| 10Y   | SWAP            |                   | DEFAULT         |
+-------+-----------------+-------------------+-----------------+
| 12Y   | SWAP            |                   | DEFAULT         |
+-------+-----------------+-------------------+-----------------+
| 15Y   | SWAP            |                   | DEFAULT         |
+-------+-----------------+-------------------+-----------------+
| 20Y   | SWAP            |                   | DEFAULT         |
+-------+-----------------+-------------------+-----------------+
| 25Y   | SWAP            |                   | DEFAULT         |
+-------+-----------------+-------------------+-----------------+
| 30Y   | SWAP            |                   | DEFAULT         |
+-------+-----------------+-------------------+-----------------+



So, each row (nodal point) in the curve definition is used to look up a `CurveInstrumentProvider`.  For many of these, this will likely be an instance of a class that just returns the hard-coded identifier within, which would typically be determined during system installation, but for the futures, a different class is called that computes the appropriate code.  For example, if the curve date was 1/1/2011, then the future code generator for the above would return an identifier like 'EDH2' - the January 2012 future for the (1Y, FUTURE, 1, DEFAULT) row because it's the 1st quarterly IR future *after* one year.  The next row (1Y, FUTURE, 2, DEFAULT) would return an identifier like 'EDM2' - the March 2012 future because it's the 2nd quarterly IR future *after* one year.

So a good question is, why don't we just exclude the number and assume a sequential sequence? The reason is that this configuration allows you to skip futures that aren't liquid enough, or whose value you don't trust - it's perfectly possible to use e.g. the 2nd and 4th futures after 18 months, for example, which gives maximum flexibility.

So, what is the 'Convention Name' column for?  It's the *name* of the *Yield Curve Specification Builder Configuration* to use for constructing that nodal point.  This means you can source yield curve nodes even when there are multiple possible choices available for a given data provider, or you want to source data from multiple different data providers.  For example, you could have one yield curve specification builder configuration that produced Bloomberg ticker identifiers, and another that produced Thomson Reuters RICs, and configure the short end from one config and the long end of the curve from the other.  While theoretically they could be mixed into a single specification matrix, that would require the specification matrix to be rebuilt if that was ever to change.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
How to set up the configurations for your own data source
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


A good starting point is to look at the curve set up for the swap view in `OG-Examples`.  You should start by looking at the `SECONDARY` configurations and curve definitions that are used by the `OG-Examples` project.  These are built using code in `SecondaryCurveDefinitionAndSpecifications`, and are editable via the web user interface.  Currently though, it is probably easier to modify/augment that code than to hand edit the JSON format available in the current configuration editor.  At the time of writing, a proper GUI for editing curve definitions and curve specification builder configurations is under development.  Depending on your underlying data source and what you want to achieve, you may have to write new CurveInstrumentProviders (especially if you want IR futures in your curve), but it is unlikely you'll need to do this to just get started.  The simplest CurveInstrumentProvider is the StaticCurveInstrumentProvider, which just returns the Identifier given to it.  The SyntheticIdentifierCurveInstrumentProvider generates the synthetic identifiers used by the OpenGamma demo system, so it probably won't be useful for you.  For an example of how to build a future code generator, see the `BloombergFutureCurveInstrumentProvider`.  Note if you write a new `CurveInstrumentProvider` you'll also need to add a fudge builder for it, see e.g. `BloombergFutureCurveInstrumentProviderBuilder` for an example.

The curve definitions you populate into the configuration system (from `YieldCurveConfigPopulator`, which is driven from a 'depends-on' attribute in `OG-Financial/config/com/opengamma/financial/demoMasters.xml` spring file (around line 77 at the time of writing) are iterated over in `DemoCurveFunctionConfiguration`, and an instance of `MarketInstrumentImpliedYieldCurveFunction` and its helper functions `YieldCurveMarketDataFunction` and `YieldCurveInterpolatingFunction`, which collect input data, and interpolate the output respectively.

```````````
Conventions
```````````


Because the curve construction process involved the synthesis and pricing of implied market instruments, we also need to be able to access market conventions.  These are currently stored in `InMemoryConventionBundleMaster` (which is long overdue a refactor and have the conventions put into a database).  You will need to make sure you add your identifiers, for e.g. the three month Libor rate into the identifier bundles constructed in that class.  Conventions are sometimes looked up using the simple scheme, which uses a descriptive String such as 'USD LIBOR 3m' - this allows you to specify rates in e.g. swap legs without tying you to a specific identifier scheme.

You might find some places where there are special temporary special cases to deal with the fact the `SECONDARY` curves don't use Bloomberg tickers, but generally we've tried to make the system as neutral as possible, and you should expect such code fragments to go away over time.

An example of such a special case is that the `SECONDARY` curve functions are added to the function repository at a lower priority than the standard curve functions we use in production, which means that if you have both 'primary' (AKA `SINGLE`, `FUNDING` & `FORWARD`) curves, value specifications (columns) that don't specify a specific curve will be provided with curves by the functions initialised with the 'primary' definitions rather than the `SECONDARY` curve definitions, or worst, a non-deterministic mixture of primary and secondary curves (which is what happens if two functions provide the same unconstrained value within the engine).

`````````````````
Data requirements
`````````````````


In addition to the nodal points you've generated/specified in your curve, you'll likely also need time series of some of the indices, such as the aforementioned `USD LIBOR 3m`, but also probably `USD LIBOR 6m` and `USD LIBOR 12m`.  Obviously you'll need the equivalent for any non-USD currencies you're supporting with other curves.  These time series are used to get the correct Libor rates for swaps, cash and so on when cash flows are calculated.

```````````````
View Definition
```````````````


Use the example swap view definition generated by the examples to see how to set up a curve.  Note that currently you'll need to specify 'FundingCurve' and 'ForwardCurve' with your curve names even if they're the same as what you're setting the 'Curve' property.  We'd recommend setting these in the default properties section to start with and keep your portfolio requirements free of extra curve properties.
