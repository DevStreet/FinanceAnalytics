title: Views and Results
shortcut: DOC:Views and Results
---
The fundamental unit of calculation in OpenGamma is the **view**. It is referred to as the *unit* of calculation because the set of results from a view at any given time are always consistent: they will have been produced in the same computation cycle using market data from a consistent snapshot and the same version of any referenced data structures.

Calculations begin from a **view definition**, and results flow out in the same way regardless of the mode of execution, the input data source, or the computation infrastructure. *View* is an umbrella term used to refer to a structured, possibly updating set of calculation results; it is what the user sees from the execution of a view definition.

.............
Specification
.............


Independently of how computations will ultimately proceed, the first requirement is a description of what to compute.

~~~~~~~~~
Portfolio
~~~~~~~~~


Often, results will be required over an entire `Sources, Masters, and Databases </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/Core-Concepts/Sources,-Masters,-and-Databases/index.rst>`_  . As such, a reference to a *portfolio* is interpreted by the OpenGamma Engine as a request for *outputs* on every `Sources, Masters, and Databases </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/Core-Concepts/Sources,-Masters,-and-Databases/index.rst>`_ , and for aggregate outputs at each level in the hierarchy. The presence of `Sources, Masters, and Databases </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/Core-Concepts/Sources,-Masters,-and-Databases/index.rst>`_  is required for the calculation of P&L.

~~~~~~~~~~~~~~~
View Definition
~~~~~~~~~~~~~~~


A view definition is simply a specification of what a user wants to have calculated: it states the *outputs* required over a set of *targets*.

The *outputs* can be anything which the OpenGamma engine can produce, from simple market data to complex analytics. For example: fair or present value, P&L, greeks, sensitivities, and Value at Risk.

The *targets* might be specified by referencing a `Portfolio`_ ; in this case the targets are *positions* and the view definition logically describes a two-dimensional template with the hierarchical *portfolio* structure as the rows and the required *outputs* as the columns. The *targets* might also represent specific, arbitrary instruments on which the *outputs* are required, or a combination of both these and a *portfolio*.

Normally there will be many view definitions which users have created, some of which will not be used all the time. Each represents a potential set of results, but does not by itself cause any computations to be performed. This is mainly because view definitions by themselves lack execution details such as the time for which results are required or the mode of execution to use. Such details are only provided once a user decides to use the view definition in a calculation.

View definitions are represented by instances of `ViewDefinition </javadoc/index.html?com/opengamma/engine/view/ViewDefinition.html>`_ and can be stored in the OpenGamma Configuration database or in a file. They also can be created on-the-fly, programmatically, through the `OpenGamma Tools for Microsoft Excel </confluence/DOC/OpenGamma-Platform-Documentation/OpenGamma-Tools-for-Microsoft-Excel/index.rst>`_ , or through `OpenGamma Tools for R </confluence/DOC/OpenGamma-Platform-Documentation/OpenGamma-Tools-for-R/index.rst>`_ .

.........
Execution
.........


The OpenGamma engine always calculates results in the context of a *view processor*. This is the parent of all execution.

~~~~~~~~~~~~~~
View Processor
~~~~~~~~~~~~~~


The *view processor* provides sources of market data, functions, securities, positions, as well as access to computation infrastructure. These form the *view processing context*. The *view processor* also holds a repository of *view definitions* in which any definition required for execution by that *view processor* must be held.

There may be multiple *view processors* in an OpenGamma Platform installation which provide access to a different computation context or, more commonly, different computational resources.

*View processors* are accessed through the `ViewProcessor </javadoc/index.html?com/opengamma/engine/view/ViewProcessor.html>`_ interface.

~~~~~~~~~~~
View Client
~~~~~~~~~~~


In order to create demand for the execution of a view definition, a *view client* is first required from the *view processor*. This must be created for the user requesting the results in order that data permissioning can be applied correctly. The *view processor* to which the *view client* belongs determines the context in which the results will be calculated.

A *view client* is designed to provide results to a single consumer, and therefore provides many client-oriented features:

*  the ability to pause and resume calculation results. Results are collected and batched while the client is paused, and a single result representing any activity which occurred is released when the client is resumed;


*  the ability to throttle the rate of results;


*  the ability to override live data;


*  the ability to access individual computation cycles.


With a *view client* created for the user, a request is made to *attach* it to a *view process*. The *view process* is described by the *view definition* for which it will execute, and the *execution options* which will govern its execution. A *view client* may be attached in one of two modes:

#  To a **shared** *view process* where it is acceptable to be attached to a process that may be already running due to identical demand from other *view clients*.


#  To a **private** *view process* which forces the creation of a new *view process*. This type of *view process* will never be subsequently shared unless another view client attaches to it explicitly by using its identifier.


*View clients* are accessed through the `ViewClient </javadoc/index.html?com/opengamma/engine/view/client/ViewClient.html>`_ interface.

~~~~~~~~~~~~~~~~~
Execution Options
~~~~~~~~~~~~~~~~~


*Execution options* provide all the additional information necessary to allow the execution of any *view definition*. The main component of the *execution options* is a sequence describing the computation cycles to perform when executing the view, including the valuation time at each step in the sequence.

There are several built-in *execution options*, and these can be supplemented with custom implementations for more advanced computation runs.

`````````
Real-Time
`````````


The most common execution options provide a *real-time* sequence representing an infinite number of computation cycles where the valuation time is always the current time, and live market data is used for the inputs. This will produce a stream of results, for as long as the user is interested, representing the current valuation of the targets in the view definition.

````````````
Single Cycle
````````````


The *single cycle* execution options allow a user to run a one-off computation on a view definition for a single, specific valuation time.

````````````
Compile Only
````````````


The *compile only* execution options do not cause any computation cycles to run, but fully initialise a *view process* as if a single computation cycle was about to run for a specific valuation time. This may be useful to allow:

*  a view definition to be validated to ensure that it *would* compile were it required;


*  the compilation results to be accessed and inspected, for example the resolved portfolio or dependency graph;


*  any compilation errors to be retrieved.


`````
Batch
`````


Finally, there is *batch* execution. At its most basic, the *batch* execution options describes a sequence of computation cycles at arbitrary evaluation times, each representing a point in the batch. However, batch processing is fundamental enough to modern risk requirements that the OpenGamma Platform has an additional layer of batch support, providing the following features:

*  Results are organized to be efficiently stored in a database as a stream, rather than being accessed through a programmatic interface.


*  All Market Data inputs to the calculation can be stored in the same database as the result set.  This can be either pre-prepared or post-result.  Currently although pre-prepared results can be written into the batch database, they cannot yet be used as engine inputs.  This will be allowed in future versions, but currently only the post-result input set writing is supported.


*  Batch calculations are logically organized as sequences of the same configuration, each one taking place to represent one logical time (such as "End of Day" or "Noon London Time") on each day.


*  Batch calculations have support for restarting (in the case of a failed job), replacement of results, or non-destructive restatement.


*  As the dependency graph for some batch operations will be beyond the ability of any View Processor to maintain in RAM, heuristics are sometimes used to partially build and tear down the dependency graph during execution.


````````````````````````
Custom Execution Options
````````````````````````


Custom execution options are simply an instance of `ExecutionOptions </javadoc/index.html?com/opengamma/engine/view/execution/ExecutionOptions.html>`_, configured as required. This contains an implementation of `ViewCycleExecutionSequence </javadoc/index.html?com/opengamma/engine/view/execution/ViewCycleExecutionSequence.html>`_ which may be one of the standard sequences used in the execution options above, or may be a custom implementation of the interface.  This is useful for running custom scenarios.

~~~~~~~~~~~~
View Process
~~~~~~~~~~~~


A *view process* is an internal representation of a *view definition* in execution, with a specific set of *execution options*. *View processes* are created in response to *view client* demand, and are shared between clients wherever possible. For example, two *view clients* which make a request to attach to the same *view definition* with the standard *real time* execution options will be attached to the same *view process*.

The *view process* is mainly an Engine concept of which little is exposed to the user or developer, except the notion of whether a *view client* is attached or detached. This is intentional, for the Engine is at liberty to combine and fork *view processes* on-the-fly, where this would be transparent to the *view client*.

```````````````````````
Preparing for execution
```````````````````````


Before the *view process* can commence, the OpenGamma Engine must prepare the *view definition* for calculation in the context of the *view processor*. This involves a number of stages which lead to a *compiled view definition*, accessed through the `CompiledViewDefinition </javadoc/index.html?com/opengamma/engine/view/compilation/CompiledViewDefinition.html>`_ interface:

#  The *view definition* is loaded from the *view processor*'s repository, which is backed by the standard configuration database.


#  All ancillary data must be resolved:


   #  If the view definition references a portfolio, it must be loaded from the `PositionSource </javadoc/index.html?com/opengamma/core/position/PositionSource.html>`_


      #  For each position in the portfolio, the security must be resolved from the `SecuritySource </javadoc/index.html?com/opengamma/core/security/SecuritySource.html>`_


   #  If the view definition references any specific targets then they must be resolved as necessary


#  The dependency graph determining how to execute the view definition must be constructed:


   #  The engine will attempt to find a function that can calculate each desired output, using the function *priority* to choose between multiple functions.


   #  Each selected function has *its* requirements inspected, and the engine will attempt to satisfy these with further functions. This process continues until all inputs have been satisfied, ending with market data inputs. Where the same inputs are required multiple times, the outputs from intermediate functions are reused to avoid duplicate calculations.


   #  The complete set of inputs to outputs via functions forms the dependency graph which is used to drive execution.


#  The dependency graph is used to determine all market data requirements, and subscriptions to these are made.


Depending on the execution sequence, this compiled view definition may be valid for many, if not all, computation cycles. It is reused for as long as possible but will be recompiled as necessary, for example if:


*  the view definition changes;


*  any referenced portfolio changes;


*  the dependency graph is no longer valid for the next valuation time, which normally occurs when market data identifiers change over time.


````````````````````````````````
Execution of a computation cycle
````````````````````````````````


The dependency graph is used to build an *execution plan*. This breaks up the computation work into discrete jobs, minimising the interdependency between the jobs where possible to enable better cache behaviour. Some effort is also made to keep trivial jobs local to the *view processor* (assuming there is spare computation capacity within the same address space) so as to minimize the network overhead. These jobs are dispatched to remote or local *calculation nodes*, where they are executed, and the results collected.

Once execution is underway, it is possible to access, and interact with, a wealth of information:


*  The compiled view definition currently in use. This provides the version of the view definition in use, the resolved portfolio, the live data requirements, the validity period of the compilation, and the expected structure of the results. This is updated as recompilation occurs, in advance of any results produced from the new compilation.


*  The requested outputs from each computation cycle.


*  Access to each completed computation cycle itself, providing the ability to browse the dependency graph and retrieve intermediate values from the computation infrastructure. This allows *explain* functionality to be implemented where the path to an output can be displayed along with every intermediate calculation result.

