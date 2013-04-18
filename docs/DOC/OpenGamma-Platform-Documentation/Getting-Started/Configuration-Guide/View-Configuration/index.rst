title: View Configuration
shortcut: DOC:View Configuration
---
View Definitions consist of several different components:

*  The **Name** of the view definition


*  The **Unique Identifier of the portfolio** to build a view upon, e.g.  `DbPrt~11345`


*  The default **User** who is to run the view - this is only used in the case of a batch process, otherwise a user is passed to the view processor when starting a view client


*  The default **Currency** of the view. This may soon be divided into Risk and PnL currency.


*  **Execution parameters** to control the minimum and maximum rates of computation for both full and delta `Cycle`_ . Within these limits, computation is driven by input data changes, such as market data ticks.


   *  **Minimum delta calculation period** \- the minimum time that must elapse since any previous cycle before a `DeltaCycle`_  may be executed, to throttle the overall execution rate


   *  **Maximum delta calculation period** \- the maximum time that can elapse since any previous cycle before a `DeltaCycle`_  will be forced to execute, to ensure a minimum execution rate


   *  **Minimum full calculation period** \- the minimum time that must elapse since the previous full cycle before a further `FullCycle`_  may be executed, to throttle the `FullCycle`_  execution rate


   *  **Maximum full calculation period** \- the maximum time that can elapse since the previous full cycle before a further `FullCycle`_  will be forced to execute, to ensure a minimum `FullCycle`_  execution rate


*  The **Result model definition**. Each category of target, below, has a `ResultOutputMode` specified as either `NONE` (no outputs), `TERMINAL` (terminal outputs only - excludes intermediate results), and `ALL` (all outputs including intermediate results)


   *  **Aggregate positions** (any portfolio node above position in the hierarchy)


   *  **Positions**


   *  **Trades**


   *  **Securities** (many trades/positions may share a security output)


   *  **Primitives** (outputs that aren't linked to a specific security, trade, position or aggregate position)


*  A list of named **View Calculation Configurations** (stored as a map from names to calculation configurations).  These allow the same analytics to be computed using different settings and viewed side by side at the same time.


   *  Each **View Calculation Configuration** contains:


      *  For each of


         *  Portfolio Requirements - these cover both position and aggregate position (aka portfolio level) requirements


         *  Trade Requirements - these cover only trade level requirements


      *  the calculation configuration is specified by a map


         *  *from* **Security type** (strings like `EQUITY`, `EQUITY_OPTION` derived from the **Security type** property on all Security objects


         *  *to* a pair of a **Value** **Requirement name** and a **properties object**  (see `ValueRequirementNames`  and `ValueProperties).` A grocery list of available value requirements is shown `OpenGamma Analytics Value Requirements </confluence/DOC/OpenGamma-Platform-Documentation/Analytics/OpenGamma-Analytics-Value-Requirements/index.rst>`_ .


      *  and also a set of **Specific value requirements** (name, target type, target), e.g. (`FAIR_VALUE`, `POSITION`, unique id of position)


         *  These cover any *other* requirements that may be needed, either primitive types such as yield curves and volatility surfaces, or even specific trade or portfolio requirements that need not involve the whole portfolio (e.g one trade, position or aggregate position).  They can also specify market data.



.....
Notes
.....

Cycle


~~~~~
Cycle
~~~~~


An execution pass on a compiled view definition for a particular valuation time, with a snapshot of input data, to produce the required outputs. Sometimes called a computation cycle or view cycle.
DeltaCycle


~~~~~~~~~~~
Delta Cycle
~~~~~~~~~~~


An execution pass based on the previous cycle. Only the outputs which are downstream of modified inputs are recalculated; other outputs are reused from the previous cycle. These are lightweight cycles which are designed to be performed more frequently than full cycles.
FullCycle


~~~~~~~~~~
Full Cycle
~~~~~~~~~~


A complete execution pass where every output is recalculated; no calcuations from previous cycles are reused.

................................................
Available computations (value requirement names)
................................................

`OpenGamma Analytics Value Requirements </confluence/DOC/OpenGamma-Platform-Documentation/Analytics/OpenGamma-Analytics-Value-Requirements/index.rst>`_ 


