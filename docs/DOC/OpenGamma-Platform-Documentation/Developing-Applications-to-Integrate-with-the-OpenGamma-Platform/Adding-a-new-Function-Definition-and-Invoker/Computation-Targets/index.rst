title: Computation Targets
shortcut: DOC:Computation Targets
---
This document is to describe the changes introduced to the core of OG-Engine as part of JIRA PLAT-2286. A future revision will change it to just describe the core concepts as they now exist.

.................
Existing Concepts
.................


~~~~~~~~~~~~~~~~~
ComputationTarget
~~~~~~~~~~~~~~~~~


The target is the primary parameter for an OG-Engine function - every function operates on a target, and any necessary inputs, to produce one or more outputs. It is typically a data representation object such as a `Position` or a `Security`. For example, we might create a function which operates on a `SwapSecurity` target, with any necessary external market data as its inputs, to produce a "present value" figure. An alternative object-oriented design might have the present value calculation as a method on the swap security definition - the object instance can be considered the target and the method considered the function - but this introduces a tight coupling between the data representation and the analytics which reduces system flexibility.

Each node in the dependency graph used for calculation corresponds to a function applied to a target. As such a target can serve as a trigger for re-construction and/or re-evaluation of the graph. For example, at the leaves of a graph a node might have `MarketDataSourcingFunction` applied to a market data ticker target with the arrival of new data triggering any necessary recalculations. Alternatively, although this is not currently implemented, if a position is the target of a node then changes to that position could trigger a rebuild (if needed) and re-evaluation of the affected part of the dependency graph.

A target must be a data object that can be converted to/from a Fudge representation. It should be immutable or be used in a read-only way by any functions. Targets are described by a compact `ComputationTargetSpecification`, with the full target instances created by a `ComputationTargetResolver`.

~~~~~~~~~~~~~~~~~~~~~
ComputationTargetType
~~~~~~~~~~~~~~~~~~~~~


All `ComputationTarget` instances have a given type. There are five pre-defined types:



+------------------+-----------------+-----------------------------------------------------------------------------+
| Type             | Class           | Description                                                                 |
+==================+=================+=============================================================================+
| `PORTFOLIO_NODE` | `PortfolioNode` | A point in the aggregation structure of a portfolio                         |
+------------------+-----------------+-----------------------------------------------------------------------------+
| `POSITION`       | `Position`      | A holding in a particular security                                          |
+------------------+-----------------+-----------------------------------------------------------------------------+
| `TRADE`          | `Trade`         | A single transaction in which some quantity of a security is bought or sold |
+------------------+-----------------+-----------------------------------------------------------------------------+
| `SECURITY`       | `Security`      | Any asset that may be owned by someone                                      |
+------------------+-----------------+-----------------------------------------------------------------------------+
| `PRIMITIVE`      | `UniqueId`      | Anything else that can be described by an identifier                        |
+------------------+-----------------+-----------------------------------------------------------------------------+



All functions are declared to operate on a particular type (as returned by `CompiledFunctionDefinition.getTargetType`). This optimises the search for functions during dependency graph construction by allowing the set of all functions to be partitioned into subsets valid for each target type.

The `ComputationTargetType` component of `ComputationTargetSpecification` is used by the `ComputationTargetResolver` to select the correct resolution strategy for the specification.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
ComputationTargetSpecification
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


All targets can be described by a compact specification. This contains the `ComputationTargetType` and a `UniqueId` only. This can be used to describe a target without having to look up or construct the full target object, for example when `CompiledFunctionDefinition.getRequirements` is producing a description of the inputs a function needs.

Most network messages sent between hosts in an OpenGamma installation will use a `ComputationTargetSpecification` instead of the full `ComputationTarget` object to reduce the communication overheads.

When a target instance is required the specification can be resolved to a `ComputationTarget` instance by a `ComputationTargetResolver`.

~~~~~~~~~~~~~~~~~~~~~~~~~
ComputationTargetResolver
~~~~~~~~~~~~~~~~~~~~~~~~~


A resolver can convert `ComputationTargetSpecification` instances to `ComputationTarget` instances.
Most network messages contain a specification instead of the full target. A resolver at the receiver can produce the full target if/when it is needed, using a local cache to minimise traffic back to the original databases where the definitive form of the target may reside.

A resolver will use the `ComputationTargetType` component of a specification to select a resolution strategy. For example, a type of `PORTFOLIO_NODE` would mean `PositionSource.getPortfolioNode` will be used to resolve the unique identifier. A type of `PRIMITIVE` is not resolved - the `ComputationTarget` object will contain the unique identifier from the specification.

`Position` and `Trade` objects returned by a resolver have their `Security` link members resolved with the `SecuritySource` that is associated with the resolver. This means that function implementations can assume that `Position.getSecurity` or `Trade.getSecurity` can always be called but may return `null` if the referenced security does not exist. Note that this lookup might be deferred until the `getSecurity` method is called for the first time on that target. This is an optimisation to reduce security lookups for functions that never interrogate the security associated with a position or trade.

........................
Changes and New Concepts
........................


~~~~~~~~~~~~~~~~~~~~~
ComputationTargetType
~~~~~~~~~~~~~~~~~~~~~


Instead of a hard coded list of five constants, a `ComputationTargetType` can now refer to any Java class. The previous constants still exist, referring to the appropriate Java classes, along with additional commonly used target types such as `CURRENCY` and `UNORDERED_CURRENCY_PAIR`.

Two additional types have been introduced - `NULL` and `ANYTHING`. The `NULL` type is only valid for a `NULL` computation target and can be used for functions for which there is no meaningful target. The `ANYTHING` type can be used by functions to indicate that they can apply to any possible target. Use of these types is likely to be rare but examples can be found in the OG-Financial package.

Basic types can be composed to form more complex type descriptors. For example it is possible, among other things, to specify a type of "`POSITION` or `TRADE`" (a union type, written as `POSITION|TRADE`) or a type of "`POSITION` within a `PORTFOLIO_NODE`" (a nested type, written as `PORTFOLIO_NODE/POSITION`).

The `SECURITY` type still exists and will typically be used when constructing `ComputationTargetSpecification` instances so that the identifiers will be resolved to securities. More specific types are defined in `FinancialSecurityTypes` for use with OG-Financial. These allow the target types of functions to be declared as a specific asset class (or asset classes using a union type).

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
ComputationTargetRequirement, ComputationTargetSpecification and ComputationTargetReference
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


A `ComputationTargetSpecification` describes a basic target with a `ComputationTargetType` and `UniqueId`. This is not always appropriate, for example market data, as a unique identifier may not be immediately available but a looser external identifier or identifier bundle is. Instead of choosing an arbitrary external identifier from such a bundle and shoe-horning it into a unique identifier, or requiring the external identifier be resolved somehow, a `ComputationTargetRequirement` now exists which can hold an external identifier bundle.

The `ComputationTargetReference` class is the common super-class of `ComputationTargetRequirement` and `ComputationTargetSpecification`.

`ValueSpecification` objects still contain a `ComputationTargetSpecification`. `ValueRequirement` objects contain a `ComputationTargetReference` so that it may be either a `ComputationTargetSpecification` or an unresolved `ComputationTargetRequirement`. This avoids duplicating the identifier resolution code and allows functions to declare their inputs in terms of the bundles of market data tickers or underlying security references held in the data models.

The dependency graph builder will work with the market data or snapshot data provider and other system components to resolve any value requirements described in this manner to a more specific form needed to create `ComputationTarget` instances and produce a satisfying `ValueSpecification`.

When nested target types are used, a target specification or requirement holds a reference to its "parent". This allows, for example, a `POSITION` to be specified along with the `PORTFOLIO_NODE` it is immediately under without the identifier mangling previously used.

~~~~~~~~~~~~~~~~~
ComputationTarget
~~~~~~~~~~~~~~~~~


The target object still contains the target value, such as a `Position` or `Security` instance, and type as before. When used with a nested type, there is an additional data member to hold the unique identifiers of the logically containing target objects. The `ComputationTarget` instance does not contain copies or direct references to these objects. If object instances are required, the `ComputationTarget` can be used to create a `ComputationTargetSpecification` of an outer object and this be resolved to the outer target object.

~~~~~~~~~~~~~~~~~~~~~~~~~
ComputationTargetResolver
~~~~~~~~~~~~~~~~~~~~~~~~~


The `ComputationTargetResolver` includes an additional `simplifyType` method to reduce a `ComputationTargetSpecification` to the simplest form that can be resolved. For example, if all securities are resolved using the same strategy, referred to by type `SECURITY`, then using a type which describes the concrete asset class is unnecessary.

Resolver implementations, such as `DefaultComputationTargetResolver`, might include more than just a `PositionSource` and `SecuritySource`. A map of computation target types to `Resolver` instances is used to determine the resolution strategy based on the type of a specification and that strategy used to obtain the target object. These type-specific resolvers need to be supplied to the target resolver when it is constructed. For example, a resolver that works with `HistoricalTimeSeriesSource` might be registed for the `ComputationTargetType.of(HistoricalTimeSeries.class)` type.

.................
Java Code Changes
.................


~~~~~~~~~~~~~~~~~~~~~
ComputationTargetType
~~~~~~~~~~~~~~~~~~~~~


Before these changes, `ComputationTargetType` was an enum located in the `com.opengamma.engine` package containing the five hard-coded types. This is now an abstract class located in the `com.opengamma.engine.target` package. It still contains the five member constants `PORTFOLIO_NODE`, `POSITION`, `TRADE`, `SECURITY` and `PRIMITIVE` so most existing code will only require changes to its `import` section although reviewing the code to identify whether a more specific value returned by `getTargetType` would be better than having `instanceof` checks in `canApplyTo` is recommended.

As `ComputationTargetType` is no longer an enum, it can't be used in `switch` statements. Most switch statements can be replaced with an instance of `ComputationTargetTypeMap`, for example:



.. code::

    String getDisplayName(ComputationTargetType type) {
      switch (type) {
        case PORTFOLIO_NODE : return "Node";
        case POSITION : return "Pos";
        case TRADE : return "Trade";
        case SECURITY : return "Sec";
        case PRIMITIVE : return "Prim";
        default : throw new IllegalStateException();
      }
    }




Can become:



.. code::

    private static final ComputationTargetTypeMap<String> s_getDisplayName = createGetDisplayName();
    
    private static ComputationTargetTypeMap<String> createGetDisplayName() {
      final ComputationTargetTypeMap<String> type = new ComputationTargetTypeMap<String>();
      type.put(ComputationTargetType.PORTFOLIO_NODE, "Node");
      type.put(ComputationTargetType.POSITION, "Pos");
      type.put(ComputationTargetType.TRADE, "Trade");
      type.put(ComputationTargetType.SECURITY, "Sec");
      type.put(ComputationTargetType.ANYTHING, "Prim");
      return type;
    }
    
    String getDisplayName(ComputationTargetType type) {
      return s_getDisplayName.get(type);
    }




Note the use of `ANYTHING` instead of `PRIMITIVE`. The `PRIMITIVE` type, previously described as "anything else", now refers specifically to a `UniqueId` instance that will not be resolved. The `ANYTHING` type is defined as anything implementing `UniqueIdentifiable`.

Comparison of `ComputationTargetType` values by identity is not recommended. The public type constants are singletons, but there is the possibility that the tested type is a sub-type. Instead of:



.. code::

    void handleTarget(ComputationTarget target) {
      if (target.getType() == ComputationTargetType.POSITION) {
        handlePosition(target.getPosition());
      } else if (target.getType () == ComputationTargetType.SECURITY) {
        handleSecurity(target.getSecurity());
      } else {
        throw new IllegalStateException("type=" + target.getType());
      }
    }




We should use `type.getType().isTargetType(ComputationTargetType. ...)`:



.. code::

    void handleTarget(ComputationTarget target) {
      if (target.getType().isTargetType(ComputationTargetType.POSITION)) {
        handlePosition(target.getPosition());
      } else if (target.getType().isTargetType(ComputationTargetType.SECURITY)) {
        handleSecurity(target.getSecurity());
      } else {
        throw new IllegalStateException("type=" + target.getType());
      }
    }




Alternatively, noting that the original code could have been written as a `switch` statement, the `ComputationTargetTypeMap` pattern could be used with the map returning the code to be executed:



.. code::

    private final ComputationTargetTypeMap<Function1<ComputationTarget, Void>> _handleTarget =
        createHandleTarget();
    
    private ComputationTargetTypeMap<Function1<ComputationTarget, Void>> createHandleTarget() {
      final ComputationTargetMap<Function1<ComputationTarget, Void>> type =
          new ComputationTargetMap<Function1<ComputationTarget, Void>>();
      type.put(ComputationTargetType.POSITION, new Function1<ComputationTarget, Void>() {
        Void execute(ComputationTarget target) {
          handlePosition(target.getPosition());
          return null;
        }
      });
      type.put(ComputationTargetType.SECURITY, new Function1<ComputationTarget, Void>() {
        Void execute(ComputationTarget target) {
          handleSecurity(target.getSecurity());
          return null;
        }
      });
      type.put(ComputationTargetType.ANYTHING, new Function1<ComputationTarget, Void>() {
        Void execute(ComputationTarget target) {
          throw new IllegalStateException("type=" + target.getType());
        }
      });
    }
    
    public void handleTarget(ComputationTarget target) {
      _handleTarget(target.getType()).execute(target);
    }




This is the pattern used by `DefaultComputationTargetResolver` to select the correct resolution strategy given the type in a `ComputationTargetSpecification` (in that instance the map contains instances of the `TargetResolver<T>` interface but could equally use `Function1<UniqueId, T>`.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
ComputationTargetSpecification
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


The constructor for `ComputationTargetSpecification` which took an arbitrary object and determined its type with `instanceof` tests has been removed. Instances can only be created by the constructor which takes an explicit `ComputationTargetType` and `UniqueId`.

Additional helper methods which take instances of the types built into `OG-Engine` can be used instead of the previous constructor. For example:



.. code::

    ValueRequirement createRequirement(Position position) {
      return new ValueRequirement("Foo", new ComputationTargetSpecification(position));
    }




Can become:



.. code::

    ValueRequirement createRequirement(Position position) {
      return new ValueRequirement("Foo", ComputationTargetSpecification.of(position));
    }




~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
ValueRequirement and ValueSpecification
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


Following the changes to `ComputationTargetSpecification` the constructors which took arbitrary objects have been removed. Instances can only be created with either:


*  An explicit `ComputationTargetType` and `UniqueId` (or `ExternalId`/`ExternalIdBundle` for `ValueRequirement`); or


*  An existing `ComputationTargetSpecification` object (or `ComputationTargetRequirement` for `ValueRequirement`)


`ValueRequirement` instances have been changed to hold a `ComputationTargetReference`. This allows them to hold a `ComputationTargetSpecification` as before, or the new `ComputationTargetRequirement`. For example, a market data requirement can be created directly from an identifier bundle without having to resolve it to a single identifier:



.. code::

    ValueRequirement createRequirement() {
      return new ValueRequirement(
          MarketDataRequirementNames.MARKET_VALUE,
          ComputationTargetType.PRIMITIVE,
          ExternalIdBundle.of(
              ExternalId.of(ExternalSchemes.BLOOMBERG_TICKER, ...),
              ExternalId.of(ExternalSchemes.ACTIVFEED_TICKER, ...)));
    }




This could be used, as in the example above, to build resilience into a system by specifying data in terms of multiple market data providers so that downtime on one can be tolerated.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Position, PositionSource, PositionMaster and PortfolioMaster
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


The `getParentNode` has been removed from the `Position` interface. Positions can now exist and be referenced independently of their parent node(s). This matches the model already supported by the `PositionMaster` and `PortfolioMaster`. The unique identifiers used by these now no longer use the mangling logic to identify "resolved" positions within their parent portfolio nodes. Nested target types can be used instead. For example, consider the following portfolio:



.. code::

    Root node (DbPrt~1)
      Node A (DbPrt~2)
        Position A (DbPos~1)
      Node B (DbPrt~3)
        Position A (DbPos~1)




To work with the previous `Position` interface, the portfolio would contain two positions with unique identifiers `DbPrt-DbPos~2-1` and `DbPrt-DbPos~3-1`. The dependency graph could then contain unnecessary duplication of calculations that should have been shared for both instances of `Position A`. The new form of target specifications allows `Position A` to be referred to with a specification of `(POSITION, DbPos~1)` for functions of type `POSITION`, such as `PositionScalingFunction`, to avoid this duplication in the dependency graph. Functions of type `PORTFOLIO_NODE/POSITION`, such as `PositionWeightFunction`, can still refer to the two aliases of `Position A` as `(PORTFOLIO_NODE/POSITION, DbPrt~2, Db~Pos1)` and `PORTFOLIO_NODE/POSITION, DbPrt~3, DbPos~1)`.

........
Examples
........


~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Functions Applied to Multiple Types (1)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


A function might apply to two or more target types. The previous pattern in OG-Financial would have created an abstract class with the main logic in and use concrete sub-classes that implement the `getTargetType` method returning the correct type. For example, a currency conversion function might include:



.. code::

    public ComputationTargetType getTargetType() {
      return ComputationTargetType.PORTFOLIO_NODE.or(ComputationTargetType.POSITION);
    }




The other methods (such as `canApplyTo`) will get called during graph construction with a target that is compatible with either the `PORTFOLIO_NODE` or `POSITION` types. The target it is called with will have a specific type rather than the union type and so it is important to use the target object to create specifications used on input requirements instead of the union type from `getTargetType`.

A conversion function could equally be written using the `ANYTHING` built-in type, although care should be taken that this really is necessary as the performance of a function repository including such a function might be affected.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Functions Applied to Multiple Types (2)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


A function might apply to a subset of asset classes, such as those for fixed income instruments. If the asset hierarchy includes an interface that all of the instruments implement then a target of `ComputationTargetType.of(FixedIncomeInstrument.class)` could be used. If such an interface doesn't exist, then a function could include:



.. code::

    public ComputationTargetType getTargetType() {
      return FinancialSecurityTypes.SWAPTION_SECURITY.or(FinancialSecurityTypes.SWAP_SECURITY).or( ... ;
    }




It would only be considered for targets that are a sub-class of `SwapSecurity`, `SwaptionSecurity` or any other asset classes listed. This is more efficient than the previous pattern of:



.. code::

    public ComputationTargetType getTargetType() {
      return ComputationTargetType.SECURITY;
    }
    
    public boolean canApplyTo(FunctionCompilationContext context, ComputationTarget target) {
      return (target.getSecurity() instanceof SwapSecurity) || (target.getSecurity() instanceof SwaptionSecurity) || ... ;
    }




This can make the `canApplyTo` method redundant for many common cases. It is only necessary when the
function's applicability depends on an aspect of the target that is not captured by the class hierarchy. For example, code like:



.. code::

    public ComputationTargetType getTargetType() {
      return FinancialSecurityTypes.SWAPTION_SECURITY.or(FinancialSecurityTypes.SWAP_SECURITY);
    }
    
    public boolean canApplyTo(FunctionCompilationContext context, ComputationTarget target) {
      if (target.getSecurity () instanceof SwapSecurity) {
        InterestRateInstrumentType type = SwapSecurityUtils.getSwapType((SwapSecurity) security);
        if ((type != InterestRateType.SWAP_FIXED_CMS) && (type != InterestRateType.SWAP_IBOR_CMS)) {
          return false;
        }
      }
      return true;
    }




Will only consider the function for `SwapSecurity` and `SwaptionSecurity` target, but `SwapSecurity` targets will be rejected if they are not of the exact type of swap that the function can support.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Functions Applied to Scoped Objects
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


Functions that apply to `POSITION` targets are typically independent of that position's place within the portfolio structure. An example is `PositionScalingFunction` which just requires the position's quantity and the value produced on the associated security target. Some functions such as `PositionWeightFunction` do require the additional information. This can now be defined as:



.. code::

    class PositionWeightFunction extends AbstractFunction.NonCompiledInvoker {
    
      public ComputationTargetType getTargetType() {
        return ComputationTargetType.PORTFOLIO_NODE.containing(ComputationTargetType.POSITION);
      }
      
      public Set<ValueSpecification> getResults(FunctionCompilationContext context, ComputationTarget target) {
        return ImmutableSet.of(new ValueSpecification(ValueRequirementNames.POSITION_WEIGHT, target.toSpecification(), createValueProperties().get());
      }
      
      public Set<ValueRequirement> getRequirements(FunctionCompilationContext context, ComputationTarget target, ValueRequirement desiredValue) {
        return ImmutableSet.of(
            new ValueRequirement(ValueRequirementNames.FAIR_VALUE, target.getLeafSpecification()),
            new ValueRequirement(ValueRequirementNames.FAIR_VALUE, target.getContextSpecification()));
      }
      
      // ...
      
    }




Note that the value specification created by `getResults` uses `target.toSpecification()` - this has to reference the position target in the context of its containing portfolio node. The value requirements created by `getRequirements` use `target.getLeafSpecification()` and `target.getContextSpecification()`. These refer to the position on its own and the portfolio node on its own respectively. A dependency graph for a portfolio that contains a position under multiple nodes will then look like:



.. code::

    PositionWeightFunction <-----------------------------------+
        Node 1/Position 1    <----------+                        |
                                        |                        |
                                FairValueFunction <------ FairValueFunction
                                     Node 1       <--+       Position 1
                                        |            |
      PositionWeightFunction <----------+            |
        Node 1/Position 2    <-----------------------+-----------+
                                                                 |
                                                          FairValueFunction
                                                             Position 2
                                                                 |
      PositionWeightFunction <-----------------------+-----------+
        Node 2/Position 2    <----------+            |
                                        |            |
                                FairValueFunction <--+    FairValueFunction
                                     Node 2       <------    Position 3
                                        |                        |
                                        |                        |
      PositionWeightFunction <----------+                        |
        Node 2/Position 3    <-----------------------------------+




The `FairValueFunction` calculation for position 2 is only performed once as the position is aliased under nodes 1 and 2. The `PositionWeightFunction` for position 2 is performed twice, once for each of its aliased locations so that the weight can be calculated with respect to the correct parent node.

..........................
Additional Utility Classes
..........................


~~~~~~~~~~~~~~~~~~~~~~~~
ComputationTargetTypeMap
~~~~~~~~~~~~~~~~~~~~~~~~


The `ComputationTargetTypeMap` is a "map" (although doesn't implement the `Map` interface) from `ComputationTargetType` instances to arbitary values. The full type is not used as the key, only the "leaf" class, so `PORTFOLIO_NODE/POSITION` and `POSITION` will map to the same value.

Lookups can be performed that query an exact value, or find one from the nearest super-type. For example, if there is an entry with a key of `ANYTHING` it will be returned if the map is queried with a key of `POSITION` unless a specific value was inserted with that key.

Due to the presence of union types, the query and update operations include additional parameters for "folding" functions that allow multiple/conflicting values to be reduced to a single value. For example the `DefaultComputationTargetResolver` class will use `ChainedResolver` to compose two existing resolvers into a single one.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
ComputationTargetSpecificationResolver
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


A `ComputationTargetReference` from a `ValueRequirement` must be resolved to a `ComputationTargetSpecification` before it can be used to create a satisfying `ValueSpecification`. The `ComputationTargetSpecificationResolver` class provides this service. References that are already specifications will be returned directly. A resolution strategy will be applied to requirements to obtain a unique identifier. For example a requirement of `(SECURITY, Ticker~Foo)` may query a `SecuritySource` for a matching security and use the unique identifier of that security to create the target specification.

If the identifiers from a requirement cannot be resolved, one will be selected based on a ranking of external identifier schemes, and coerced into a unique identifier. 

