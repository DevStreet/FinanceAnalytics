title: Value Requirements and Specifications
shortcut: DOC:Value Requirements and Specifications
---
The OpenGamma Engine is a data-driven system which constructs a full dependency graph of all calculations required for a particular `Views and Results </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/Core-Concepts/Views-and-Results/index.rst>`_ . In order to support the richness required of a modern analytics and risk platform, the Engine works with two main concepts while managing the data flowing through a calculating *view process*: `Value Requirements`_  and `Value Specifications`_ .

..................
Value Requirements
..................


A *value requirement* is a minimal representation of what the user wants to see -- literally the *requirement*. It can be as simple as just a name (`FairValue`, `ValueDelta`), but can also include any number of constraints on the requirement (`Currency=USD`, `YieldCurveDefinition=No Futures`).

....................
Value Specifications
....................


As a *view process* is being prepared for execution, the OpenGamma Engine searches for functions that are able to produce each *value requirement* with the necessary constraints. This continues recursively to satisfy each of these function's inputs.

When the full dependency graph has been constructed, each *value requirement* will have been satisfied by the output of a function. The formal description of this output is a *value specification*; this contains the precise characteristics of the output, including a set of properties which satisfy at least any constraints from the original *value requirement*.

.......
Example
.......


Consider a simple *value requirement* to produce `FairValue` on an Equity Option. The Engine would select a function that advertises `FairValue` as an output for Equity Options, and would add it to the dependency graph. After the graph has been fully constructed by recursively satisfying the inputs to the function, a query for the full *value specification* of the requested output might reveal that the function produces `FairValue` with the following properties:


+----------------------+----------------------+
| Currency             | USD                  |
+======================+======================+
| Method               | Black-Scholes-Merton |
+======================+======================+
| YieldCurveDefinition | No Futures           |
+======================+======================+
| VolatilityTreatment  | Implied              |
+======================+======================+



If instead the *value requirement* was `FairValue` with the constraint `Currency=GBP`, then the dependency graph constructed above would be invalid. If a dependency graph were produced for this new *value requirement*, then the constraint would propagate through the graph to ensure that it is satisfied by the properties of the output. Querying the new dependency graph might reveal that the function now produces `FairValue` with the following properties:


+----------------------+----------------------+
| Currency             | GBP                  |
+======================+======================+
| Method               | Black-Scholes-Merton |
+======================+======================+
| YieldCurveDefinition | No Futures           |
+======================+======================+
| VolatilityTreatment  | Implied              |
+======================+======================+



The *value requirement* is thus what is desired of the calculations (and no more), and the *value specification* contains as much information as necessary to fully distinguish the actual calculation output from any others.

..............
Advanced Usage
..............


Where there are multiple functions able to satisfy a particular *value requirement*, the Engine will choose one based on a number of heuristics. It might pick one which explicitly has a higher priority than another, or it might choose one which the automatically-generated statistics indicate is faster than another.

*Value requirements* which differ only in their constraints are still treated as distinct outputs, which allows users to view the effects of calculating the same output using different properties side-by-side. For example:

*  Fair Value with the default properties


*  Fair Value using a particular named yield curve


*  Fair Value with the yield curve subject to a custom skew function


*  Fair Value in an alternative currency


*  Fair Value in an alternative currency with the yield curve subject to a custom skew function


*  Fair Value computed using the OpenGamma Analytics library


*  Fair Value computed using the current version of an in-house Analytics library


*  Fair Value computed using a development version of an in-house Analytics library

