title: Adding a new Function Definition and Invoker
shortcut: DOC:Adding a new Function Definition and Invoker
---
................
Some terminology
................



*  *target* refers to the object that the resulting analytic applies to.  See *target types* for examples.


*  *target types* are the classes of object that analytic results can refer to and may be


   *  **security** \- the target is a security, thus if it is to be used as the basis of a position or trade result it may need to pass through a `PositionScalingFunction` to multiply it by the size of the position (in shares/contracts in the case of an exchange traded instrument), or it may apply equally to a Position as it is, in which case it may need to pass through a `UnitPositionScalingFunction`.  Adding these scaling functions is done in `DemoStandardFunctionConfiguration`.


   *  **trade** \- the target is a trade (a single transaction) and functions processing trades will have access to the trade date and counter-party, which will not be available when processing positions.


   *  **position** \- the target is a position, which represents the current net position in the market of the associated security.  Because it is a *netted* view of a set of one or more trades, it may encompass trades covering multiple trade dates and counter-parties, so those details are not available at this level of abstraction.


   *  **portfolio node** \- the target is an aggregate of a number of positions, represented as a node in the portfolio tree.


   *  **primitive** \- the target is not associated with any specific portfolio element but is a more general result.  Examples are **yield curves** and **volatility surfaces**.  It is important to understand that primitive targets still have a target object, but that is typically something like a currency or region identifier.


*  *requirement* or *value requirement* \- the values/data objects that an analytic function needs to be able to execute.  This will typically be a set of market data identifiers or a more complex object produced as the output of another function


*  *specification* or *value specification* \- the result of fulfilling a requirement.  In practice there is little different between a requirement and a specification, but a specification is basically a requirement + the unique identifier of the function that fulfilled it, which enables functions to find which functions provided them with the data they are asking for.


*  *computed value* \- a *specification* along with the value object that is the result of the calculation.


*  *compilation context* or *function compilation context* \- a map of strings to objects that allows access to interfaces that allow the functions to obtain external data during the compilation phase (building of the dependency graph).  In the OpenGamma financial library (**OG-Financial**) this map is accessed via a wrapper called `OpenGammaCompilationContext` that eliminates dependencies between the engine component and the **OG-Financial** project, meaning it can be replaced in it's entirety if required.


*  *execution context* or *function execution context* \- as the compilation context, but provides services available during execution.  Broadly the same services, any omissions in access methods in `OpenGammaExecutionContext` usually point to that service never having been needed so far rather than some fundamental reason for restricting access.


.............................
About the function interfaces
.............................


Functions that perform analytic tasks in the Engine need to implement several interfaces:

*  FunctionDefinition - this specifies methods called by the engine to determine what kinds of *targets* that the function supports, what it's *requirements* are, and the *specifications* of what it produces.


   *  `init(FunctionCompilationContext)` \- this allows for any one-off initialisations, specific to a ViewProcessor.  It is not called each time a new view is started, for example.


   *  `compile(FunctionCompilationContext, InstantProvider)` \- builds a `CompiledFunctionDefinition` based on the current time.  This allows for Functions to change over time and have their requirements and results change.  A typical example of this is a function that computes a yield curve needing to change the required data depending on the curve date (particularly, fixed date maturity instruments such as interest rate futures in the curve will eventually fall out of scope of the curve definition).  Where this functionality is not required a NonCompiledFunction implementation is available (see later). In the future there will probably be support for triggering the construction of a new `CompiledFunctionDefinition` based on something other than the time e.g. the current definition may able to trigger a recompile by throwing an exception.


   *  `getUniqueId` \- get a unique identifier for this function.  Allows *specifications* to specify the function that produces the *computed values*.


   *  `getShortName` \- simple display name for debugging and display.


   *  `getDefaultFunctionParameters` \- return a default set of function parameters.  FunctionParameters are intended as a side-channel to provide e.g. number of Montecarlo iterations to a function that can be overridden by the user.  In practice this mechanism isn't currently used and is **not** currently available through the `FunctionCompilationContext`.


*  CompiledFunctionDefinition


   *  `getFunctionDefinition` \- returns the `FunctionDefinition` that constructed it.


   *  `getTargetType` \- returns the *target type* that this function supports.


   *  `canApplyTo(FunctionCompilationContext, ComputationTarget)` \- tests, using the services provided through the *compilation context*, whether the function can be applied to this target.The dependency graph building algorithm will have already checked that the target is of the correct type.


   *  `getRequirements(FunctionCompilationContext, ComputationTarget)` \- returns a set of *value requirements* for all the objects required to compute this function's output given this *target*. The dependency graph building algorithm will have already checked that the target is appropriate by calling `canApplyTo`.


   *  `getAdditionalRequirements(FunctionCompilationContext, ComputationTarget, Set<ValueSpecification> inputs, Set<ValueSpecification> outputs)` \- a post-resolution chance to register any further requirements based on both the input and output requirements.  In general you should not override the implementation in `AbstractFunction`.


   *  `getResults(FunctionCompilationContext, ComputationTarget)` \- returns a set of *value specifications* describing the objects produced by the function, as they apply to the supplied *target*


   *  `getResults(FunctionCompilationContext, ComputationTarget, Map<ValueSpecification, ValueRequirement>)` \- as previous, but you have access to the resolved *specifications* that satisfy the *requirements*. This allows the implementation to pass on constraints that use wildcards from the input to the output.  The default implementation in `AbstractFunction` just returns the previous method's results, and this method is rarely implemented.


   *  `getFunctionInvoker()` \- returns an invocation handle to the compiled function


   *  `getEarliestInvocationTime()`/`getLatestInvocationTime()` \- return the earliest and latest times that it is valid to call this function for.  Only necessary when you're not using a `NonCompiledInvoker`.


*  FunctionInvoker - this provides the method for actually executing the function once the engine has fulfilled it's requirements and has all the input data it requires.


   *  `execute(FunctionExecutionContext, FunctionInputs, ComputationTarget, Set<ValueRequirement> desiredValues)` \- actually execute the function, using the *execution context* to access data, the function inputs to get the values fulfilling the requirements (indexed by the requirements themselves), the target of the computation, and the set of desiredValues (including any addition requirements).


~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Why is the execute method in a separate interface?
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


In our current function implementations all these interfaces interfaces are implemented by the same function classes so why are they separated?  The answer is that it allows the possibility for the invoker part of the function to be implemented in another language such as C++, FORTRAN, MATLAB, R, etc without all the meta-data requirements and results processing needing to happen in that language as well, simplifying porting of existing analytics libraries, for example.  Furthermore, it allows the actual executor to operate in an environment completely unaware of OpenGamma, such as a GPU kernel, a job farmed out on a data Grid, or even an external OS process or remote call over the network.

~~~~~~~~~~~~~~~~~~~~~~~~~~~
Actually writing a function
~~~~~~~~~~~~~~~~~~~~~~~~~~~


To hide some of the complexity in the interfaces, an `AbstractFunction` class, provides default implementations of some of the less frequently used methods.  In addition `AbstractFunction` has several static inner classes that provide default implementations of different types of `FunctionInvoker`.  Instances of the latter are typically created inline as instances of anonymous inner classes extending the static inner classes:

*  `NonCompiledInvoker` \- should be used when the inputs to the function do not vary over time.  No inline creation of the Invoker required.This refers to the specification of the inputs, not their values, which can still vary


*  `AbstractInvokingCompiledFunction` \- should be used when the inputs to the function do vary over time.


A simple non compiled invoker function is provided by the `StandardEquityModelFunction`. `StandardEquityModelFunction` simply 'calculates' the `FAIR_VALUE` of an equity by passing through it's current market price and adding the property that the currency is stored against the equity definition in the Security Master database (the engine has already automatically loaded the Security object in this case), so it's *extremely* simple, but is a useful demonstration:



.. code::

    package com.opengamma.financial.analytics.model.equity;
    
    import java.util.Collections;
    import java.util.HashSet;
    import java.util.Set;
    
    import com.opengamma.engine.ComputationTarget;
    import com.opengamma.engine.ComputationTargetType;
    import com.opengamma.engine.function.AbstractFunction;
    import com.opengamma.engine.function.FunctionCompilationContext;
    import com.opengamma.engine.function.FunctionExecutionContext;
    import com.opengamma.engine.function.FunctionInputs;
    import com.opengamma.engine.value.ComputedValue;
    import com.opengamma.engine.value.ValueProperties;
    import com.opengamma.engine.value.ValuePropertyNames;
    import com.opengamma.engine.value.ValueRequirement;
    import com.opengamma.engine.value.ValueRequirementNames;
    import com.opengamma.engine.value.ValueSpecification;
    import com.opengamma.financial.security.equity.EquitySecurity;
    import com.opengamma.livedata.normalization.MarketDataRequirementNames;
    
    /**
     * The Standard Equity Model Function simply returns the market value for any cash Equity security.
     */
    public class StandardEquityModelFunction extends AbstractFunction.NonCompiledInvoker {
    
      @Override
      public String getShortName() {
        return "StandardEquityModel";
      }
    
      @Override
      public ComputationTargetType getTargetType() {
        return ComputationTargetType.SECURITY;
      }
    
      @Override
      public boolean canApplyTo(final FunctionCompilationContext context, final ComputationTarget target) {
        return target.getSecurity() instanceof EquitySecurity;
      }
    
      @Override
      public Set<ValueRequirement> getRequirements(final FunctionCompilationContext context, final ComputationTarget target, final ValueRequirement desiredValue) {
        final EquitySecurity equity = (EquitySecurity) target.getSecurity();
        final Set<ValueRequirement> requirements = new HashSet<ValueRequirement>();
        requirements.add(new ValueRequirement(MarketDataRequirementNames.MARKET_VALUE, ComputationTargetType.SECURITY, equity.getUniqueId()));
        return requirements;
      }
    
      @Override
      public Set<ValueSpecification> getResults(final FunctionCompilationContext context, final ComputationTarget target) {
        final EquitySecurity equity = (EquitySecurity) target.getSecurity();
        return Collections.<ValueSpecification>singleton(
            new ValueSpecification(ValueRequirementNames.FAIR_VALUE, target.toSpecification(),
                                   createValueProperties().with(ValuePropertyNames.CURRENCY, equity.getCurrency().getCode()).get()));
      }
    
      @Override
      public Set<ComputedValue> execute(final FunctionExecutionContext executionContext, final FunctionInputs inputs, final ComputationTarget target, final Set<ValueRequirement> desiredValues) {
        final EquitySecurity equity = (EquitySecurity) target.getSecurity();
        final double price = (Double) inputs.getValue(MarketDataRequirementNames.MARKET_VALUE);
        return Collections.<ComputedValue>singleton(
            new ComputedValue(
                new ValueSpecification(ValueRequirementNames.FAIR_VALUE, target.toSpecification (),
                                       createValueProperties().with(ValuePropertyNames.CURRENCY, equity.getCurrency().getCode()).get()),
                    price));
      }
    
    }



Let's look at these methods one at a time.  First, the simple ones

``````````````
getShortName()
``````````````


Usually a simple string, although if the constructor of the function is parameterised (it isn't in this case), you may construct a parameterised description.

```````````````
getTargetType()
```````````````


Specifies that this function only processes security level targets.  This means that we should only ever be passed a `Security` as a target (although we always double-check, defensive programming FTW) into all the other methods.  The knock on effect of this is that if we want to see the output of this function we have to either:

*  Add it as a one-off 'specific' requirement to a view e.g. add a specific requirement to the view being used of:


   *  value requirement name  is '`FAIR_VALUE`'


   *  target is the identifier of 'AAPL US Equity'


   *  target type is SECURITY


*  If viewing a position in the security, you'll need to add a PositionScalingFunction, which will multiply the per-security `FAIR_VALUE` by the number of shares in the position (or trade), to give the position's `FAIR_VALUE`. Further up the portfolio hierarchy, you'll need to add a PositionSummingFunction to sum up the calculated position values to provide `FAIR_VALUE` results at the aggregate level.  See `Adding a new Function Definition and Invoker </confluence/DOC/OpenGamma-Platform-Documentation/Developing-Applications-to-Integrate-with-the-OpenGamma-Platform/Adding-a-new-Function-Definition-and-Invoker/index.rst>`_  for more information.


````````````
canApplyTo()
````````````


This checks that we can deal with the actual type being passed as the target.  In this case, the function can only process EquitySecurity securities, so it checks that. If the function can apply to all targets of the given type, this can be omitted as the default implementation in the superclass will always return `true`.


`````````````````
getRequirements()
`````````````````


As we know we're getting a security, we can safely call `getSecurity()` on the target object, and as we've checked it's an `EquitySecurity` instance, we can safely cast it to an `EquitySecurity`.  We then create a `HashSet` and put a single `ValueRequirement` in it, where

*  the value requirement name is `MARKET_VALUE`, which is a special name reserved for market data values


*  the computation target type is `SECURITY`


*  the `UniqueIdentifier` of the `EquitySecurity`

Because the requirement name is `MARKET_VALUE` and the target type is `SECURITY`, this will trigger the engine to:

*  realise that the UniqueIdentifier is meant for the SecurityMaster


*  resolve it with the SecurityMaster (although it is almost certainly cached in RAM)


*  retrieve the `IdentifierBundle`


*  the LiveData subsystem will use the most appropriate `Identifier`s in the `IdentifierBundle` to look up the market value from the current snapshot


````````````
getResults()
````````````


Again, as we know we're getting a security, we can safely call `getSecurity()` on the target object, and as we've checked it's an `EquitySecurity` instance and we can safely cast it to an `EquitySecurity`.  We then construct a singleton `Collection` containing a `ValueSpecification` made up of

*  the value requirement name we're producing - `FAIR_VALUE`


*  the target specification of the security the function is working on


   *  Additionally we add some `ValueProperties` meta-data to specify the currency of the value we're going to produce. The `createValueProperties` helper method creates a builder which already contains the function identifier. We add an additional currency property to this builder. The `ValueProperty`'s name comes from `ValuePropertyNames` and the currency comes from the `EquitySecurity` object we resolved earlier.  Note that we just use the raw ISO code of the `Currency` here, rather than the object itself or the `UniqueIdentifier`.  This is to reduce inter-module dependencies and simplify the properties system to essentially String->String pairs wherever possible


`````````
execute()
`````````


Again we extract the `EquitySecurity` from the target.  We then call `getValue` on the `inputs` parameter, which looks up the fulfilled requirements using the requirement we specified as the key.  We can rebuild the full requirement, but as there was only one requirement with the `MARKET_VALUE` name we can use a simpler method.  Once we have the object back, we cast it into a `Double` (because it's coming from a `Map` internally) and let auto-unboxing convert it into a `double`.
Next we build a singleton collection containing the one `ComputedValue` we're generating as a result, with the same `ValueSpecification` we built for `getResults()`.  We could have stored it, but we're choosing here to rebuild it on the fly. The last parameter is the price value itself, which will be auto-boxed into a Double object again.  Lastly we return this resulting collection.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
AbstractInvokingCompiledFunction
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


For examples of `AbstractInvokingCompiledFunction` take a look at the code for `InterpolatedYieldAndDiscountCurveFunction` and `MarketInstrumentImpliedYieldCurveFunction` in OG-Financial.

~~~~~~~~~~~~~~~~~~~~~~~~~
Registering your function
~~~~~~~~~~~~~~~~~~~~~~~~~


You'll then need to register your function configuration.  This is typically done in either `ExampleStandardFunctionConfiguration` (if you're modifying the examples) or `DemoStandardFunctionConfiguration`. In a production context you'd create your own class.  If you write a function that takes `SECURITY`, you'll need to add a position scaling function (either a unit one which doesn't multiply by the quantity or a normal one).  If you're analytic is aggregates in a linear way there are a range of linear aggregation functions available to sum up position level values to the `PORTFOLIO_NODE` level.
