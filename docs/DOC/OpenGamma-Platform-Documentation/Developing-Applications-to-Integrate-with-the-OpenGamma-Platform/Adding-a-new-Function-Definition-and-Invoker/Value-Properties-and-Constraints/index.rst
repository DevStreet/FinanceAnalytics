title: Value Properties and Constraints
shortcut: DOC:Value Properties and Constraints
---
This page tries to explain the details of the value property and constraint mechanisms. First by presenting the background and operations that can be performed, and then by considering some examples in the context of some common function definitions.

................................
Why do we have value properties?
................................


In the early development stages of the OpenGamma calculation engine a value was specified as a conceptual name that can be produced from a stated target. For example, *Fair Value* produced from *Security X*. This worked, but lacked flexibility and required components outside of the engine to go to some effort to interpret the values. In the previous example a floating-point number would be produced, such as 4.2, but is this $4.20, 4.2p, or €4.2M? Without knowledge of the security and any relevant conventions the number is meaningless. The moment one attempts any form of aggregation across a portfolio the problem manifests itself - it may not be meaningful to add the values together as they might be in different currencies and/or magnitudes.

Consistent with other technical issues presented to the early OpenGamma developers the answer is meta data. In this case, the meta data is called *value properties*. Thus a value specification which describes a value that can be, or has been, produced consists of the conceptual name, the target, and the properties of the value. For example, *Fair Value* from *Security X* such that *currency is US Dollars*.

We could have just added a *currency* field to the value specification class along with the then existing function identifier, but this would have been restrictive in the long term. A more general purpose solution was introduced that could cover both these two cases and future ones.

.................................
Where are value properties found?
.................................


The `ValueProperties` class is badly named. The class itself is the general purpose structure that can be used to express the meta data about values the engine can or has produced. The same structure is used when formulating requests to the engine to produce values. It is used to resolve (or reduce) any ambiguity if there are multiple ways the engine can produce a conceptual value on a target. When used in this context we refer to them as value constraints. It is unfortunate that the same class is used for both these value properties and value constraints yet seems to be named after just one of them.

As a historical note, they were originally referred to (and these names still appear in the open source code in places) as *requested properties* and *resolved/actual properties* which gave rise to the class name. They are now more commonly referred to as *constraints* and *properties* respectively.

The `ValueRequirement` which requests production of a value from the engine thus consists of the conceptual name, the target and the constraints (or requested properties) of the value. For example, *Present Value* from *Security Y* such that *the curve used was called either FORWARD*3M or FORWARD*6M*. The `ValueSpecification` which describes a value the engine has produced similarly contains the actual properties.

At a higher level, constraints will appear within view definitions (from which a set of value requirements will be constructed) and properties will appear within result models (as part of the value specifications describing the terminal and intermediate values).

The mapping of value specifications to/from value requirements is generated as part of the dependency graph construction and may be present in the result model. This mapping can give the mapping of value properties to/from value constraints for any given name/target pair.

....................................
What do value properties consist of?
....................................


In their simplest form, value properties are a set of “property name” and “property value” pairs. For example, a property name could be `Currency` and the corresponding property value `USD`. Using just strings for both, the properties for a value could specify any number of facts about it. When used with the OpenGamma analytics library this commonly includes the name of the curve(s), surface(s) and cube(s) used to price the security (if applicable to the asset class).

In their full form, additional states can be expressed for each named property. These are typically only relevant when expressing the constraints of a value requirement to control the conditions under which a value will be produced. The value specification describing a value that has been, or can be, produced is likely to consist only of the simple “single value” case. These additional states are given below.


*  A given property name can have multiple values. For example *Curve is called either FORWARD*3M or FORWARD*6M*.


*  A given property name can be optional. For example *Currency is either UK Sterling or it's a unit-less amount*.


*  A given property name can take any value. For example *There is a surface name; I don't care what it is, as long as I get told it*.


.......................................
What can be done with value properties?
.......................................


The `ValueProperties` class supports five operations. These are an equality test, intersection, *satisfaction*, *composition*, and a *subtraction* operations.

~~~~~~~~
Equality
~~~~~~~~


Two sets of value properties, *A* and *B*, are equal if:


*  *A* defines the same set of property names as *B*;


*  For each of the common property names, *A* defines the same set of values as *B*; and


*  For each of the common property names, *A* declares it optional if and only if *B* declares it as optional.


~~~~~~~~~~~~
Intersection
~~~~~~~~~~~~


The intersection of two sets of value properties, *A* and *B*, is defined as:


*  Containing only property names defined in both *A* and *B*;


*  For each of common property names, the values are the intersection of *A*'s and *B*'s values. If there are no common values between *A* and *B* the property name is not present in the intersection; and


*  For each of common property names, optional if *A* and *B* both declare the property optional, mandatory if either *A* or *B* declare it mandatory.


~~~~~~~~~~~~
Satisfaction
~~~~~~~~~~~~


Satisfaction is an asymmetrical relationship. For two sets of value properties, *A* and *B*, with *A* in the context of properties and *B* in the context of constraints, then *A* satisfies *B* if for all properties in *B*:


*  *A* defines the property with one or more matching values; or


*  It is declared optional and *A* has no definition


Satisfaction is the fundamental operation used within the engine's graph building algorithm. A value requirement must be satisfied by a value specification. This means that the conceptual name must match, the target must match and the properties (on the value specification) must satisfy the constraints (on the value requirement).

~~~~~~~~~~~
Composition
~~~~~~~~~~~


The composition operation, sometimes referred to as a left intersection, is defined in terms of the satisfaction operation. If value properties *A* satisfies value properties *B*, and the composition of *A* with *B* satisfies *C* then *A* can satisfy both *B* and *C*. This is different from using the intersection as the intersection of *A* with *B* may not satisfy *C*. For example:


*  *A* is defined as `X={1,2}, Y={1,2}, Z={1,2}`;


*  *B* is defined as `X={1}, Y={1}`;


*  *C* is defined as `Z={2}`; then


*  The composition of *A* with *B* must be `X={1}, Y={1}, Z={1,2}` which still satisfies *C*, whereas the intersection (`X={1}, Y={1}`) would not.


If the intersection were to be used during graph building, this ability to satisfy multiple requirements might be lost resulting in redundancy within the dependency graph. The composition operation is used to allow results to potentially satisfy multiple requirements without ever satisfying conflicting requirements. Thus the composition of *A* with *B* is defined as:


*  For any property names common to *A* and *B* the intersection of values is taken;


*  If a property name common to both *A* and *B* is optional in both, it is optional in the result;


*  For any property names defined in *A* but not *B*, the definition of *A* is taken; and


*  If there any property names defined in *B* but not *A*, the operation is invalid


~~~~~~~~~~~
Subtraction
~~~~~~~~~~~


Seen by some as just a method on the `ValueProperties` class, or the builders used to construct them, the `withoutAny` method is the subtraction operation. It is best defined in terms of the builder used for `ValueProperties` as it removes all references to the named property from the builder. For example, to remove the previous `Function` property name before setting the correct one.

The `withoutAny` operation is defined in general terms as `copy().withoutAny(...).get()`. If a property set A satisfied a property set B that includes non-optional property X then `A.withoutAny(X)` will not satisfy property set B.

We define the infinite property set as able to satisfy any constraints. A common example of this is a conversion function. It can satisfy any properties; by delegating to its requirements any that it doesn't process. When the infinite property set has a property subtracted it gives the *nearly infinite* properties class (a bad but functional name). This can satisfy any constraints that do not include the subtracted property or declare it optional. A common example is for constraint injection. It will satisfy requirements that do not include the constraint and can be used as part of a dummy function (one that participates in graph building but reduces to the identity function and so appears in the resulting graph or executes) to replace them with requirements that include one or more default constraint values.

......................................
Are value properties always necessary?
......................................


Value requirement names and value properties can be used for similar purposes. It is possible to integrate a function library using a single value requirement name for all values and a property to distinguish between them. At the other extreme, value properties can be minimal (just the function identifier) and different requirement names used to distinguish all of the possible values (e.g. *Fair Value computed with curve X*). Generally, value requirement names should align to financial/analytic concepts. The properties address ambiguities by specifying exactly what is required or what has been produced. Anyone not caring about such details can then omit them.

There are exceptions to this case; a requirement name might be used to express a computation concept and the properties to express the financial value requirement name. This is because it is easier to map a value requirement name to/from a function than it is to map a property to/from a function. For example, a requirement for *the top 5 Present Value positions* would be awkward if implemented as a requirement name of `Present Value` and properties that indicate it is the top 5 positions within the portfolio as a user requesting simply `Present Value` with no constraints may receive it instead of the aggregated value for the portfolio they were probably expecting. In cases such as these we instead declare a value requirement name as, for example, `Top 5` and properties including `ValueRequirement=Present Value`.

Value properties, or more specifically the constraint/property handling, are available during dependency graph construction. They control which functions get selected for the graph, but may also control how functions execute in order to produce the properties on the desired values. They can be considered as a basic parameter passing mechanism. They should be used whenever they will affect the input requirements a function requires or if execution behaviour must be controlled at the node, position, trade or security level.

If this level of control is not required or the parameter will not affect a function's input requirements (and graph construction), the `FunctionParameters` mechanism can be used instead. `FunctionParameters` are specified at the calculation configuration level and provide information to a function instance specific to that configuration. An example could be the number of iterations to use for a Monte Carlo algorithm as this does not affect graph construction and should be controlled at the configuration level and not at a per\- security level. Using `FunctionParameters` can also be more efficient as the parameterization data is associated only with nodes for that function in the dependency graph whereas property information can propagate on edges up to the terminal outputs.

........
Examples
........


~~~~~~~~~~~~~~~~~~
A summing function
~~~~~~~~~~~~~~~~~~


A summing function applies to a portfolio node and gives the sum of the value produced for each member position. Constraints made on the portfolio node sum must be passed to the positions. Any common properties on the positions must be available as properties on the portfolio node sum.

The first `getResults` returns the infinite property set on the value specification produced. This is necessary as the properties from the positions within it are not known at this stage.

The `getRequirements` call takes the constraints from the `desiredValue` parameter and uses these as constraints on the value requirements it produces for each of the positions within it.

The second `getResults` takes the intersection of all of the properties on the input value specifications to give properties on the value specification it produces. If the input properties are not compatible, the function may fail at this point. If this happens then backtracking from the dependency graph builder may result in suitable conversion functions being selected for the graph that remove the incompatibility. Consider the following as inputs:


*  `Price{Currency=GBP,Foo=A}`, `Price{Currency=GBP,Foo=B}`


The intersection gives an output of `Price{Currency=GBP}` and no mention of the `Foo` property. Depending on the meaning of the `Foo` property this may be an acceptable value and the function should not fail. A view definition would therefore have to request `Price{Foo=[]}` to explicitly reject the sum if the `Foo` properties of items being aggregated do not match. For some properties it is seldom correct to add amounts that have differing values, for example Currency, so the function should fail in this case instead of shifting the burden to the view definition to ensure the output values are valid.

~~~~~~~~~~~~~~~~~~~~~~~
A controllable function
~~~~~~~~~~~~~~~~~~~~~~~


A controllable function may include a number of tunable behaviours. For example which of the underlying algorithms from the analytics library to use, or perhaps parameters to those algorithms to control their behaviours.

The first `getResults` returns properties that include either wildcards or the choice of algorithms available (presenting the choice is better, but may not be possible depending on the interface to the library). For example, `{XMethod=[A,B,C],YMethod=[A,B,C]}`.

The `getRequirements` call takes the constraints from the `desiredValue` parameter and may fail if `XMethod` or `YMethod` are not available or too loosely specified. Instead of failing, the function may assume a reasonable default or use a ranking scheme to select a single method from a loose specification. It is often preferable, however, to externalise this logic from the function wrapper and use a separate function to supply such default behaviours. The requirements may vary depending on the algorithm(s) requested.

In this example, the results are not dependent on input values, so the second `getResults` call should return the same as the first.

At execution time, the `desiredValues` parameter will include constraints that specify the `XMethod` and `YMethod` properties so that the appropriate behaviour may be run.

Note that the output properties of the function may depend on the input requirements as in the previous example. In this case the first `getResults` should return the infinite property set and the second `getResults` should combine those properties with the `XMethod` and `YMethod` properties.

~~~~~~~~~~~~~~~~~~~~~~~~~~~
A default property function
~~~~~~~~~~~~~~~~~~~~~~~~~~~


A default property function matches value requirements that do not specify a property and supplies a default based on the calculation configuration or some other source of information. In the previous example, we might wish to inject `XMethod=B` as a default if it is unspecified.

The first `getResults` returns properties that are the infinite set minus `XMethod`. This will not be able to satisfy any value requirements that include an `XMethod` property. It will satisfy any value requirements that do not.

The `getRequirements` call takes the constraints from the `desiredValue` parameter and requests a value that contains these and includes the `XMethod=B` default.

The second `getResults` must pass the resolved input value out unchanged as an output. The graph builder will use that value directly and so the injection function will not appear within the dependency graph, and never be executed.

Default property functions may be configured with either a resolution priority above or below the associated functions that use the defaults. Using a higher priority will mean the default property value is always injected if it is unspecified. Using a lower priority will mean the function will have a chance to satisfy the requirements and the defaults only used if that is not possible. See the `Propagating constraints`_  example for further details.

~~~~~~~~~~~~~~~~~~~~~~~~~
A conversion function - 1
~~~~~~~~~~~~~~~~~~~~~~~~~


A conversion function takes a computed value and produces a new form with different properties that describe the conversion. For example, a currency conversion will alter the "Currency" property and leave all others unchanged.

The first `getResults` returns properties that are the infinite set on the value specification produced. If this is a generic currency conversion function, the exact properties (apart from Currency) are not known at this stage.

The `getRequirements` call takes the constraints from the `desiredValue` parameter and checks for a Currency constraint. If this is not available or too loosely specified the function must fail. The constraints are then used to request the unconverted value, this time with a `Currency=[]]` constraint.

The second `getResults` takes the properties from the input value but replaces the output with a `Currency=[]` constraint. The dependency graph builder will compose this against the original value requirement.

The `getAdditionalRequirements` call will see the composed output value requirement that includes the target currency. The source currency is given by the resolved input requirement. If conversion is going to be done from ticking market spot rates it may then request a conversion rate that a market data provider can supply.

When the execute method is called, the original currency will be described by the input value specification and the desired currency will be described by the `desiredValue`. If market data was requested by the `getAdditionalRequirements` call, this will also be present in the function inputs.

~~~~~~~~~~~~~~~~~~~~~~~~~
A conversion function - 2
~~~~~~~~~~~~~~~~~~~~~~~~~


Some conversion functions may introduce additional properties. For example, a curve shifter will add a `ShiftAmount` property but leave all of the others unchanged.

The first `getResults` returns the infinite property set on the value specification produced. This is necessary as the properties from the underlying curve are not known at this stage.

The `getRequirements` call takes the constraints from the `desiredValue` parameter and checks for a `ShiftAmount` constraint. If this is not available or too loosely specified the function must fail. The constraints (without the `ShiftAmount`) could then used to request the original curve but this would be incorrect as a curve that has been shifted and in use elsewhere in the dependency graph may be selected as it would satisfy the constraints. To ensure the original is provided, the `ShiftAmount` constraint is put on the requirement but made optional and given a value that must never be used in practice. Only the original, unshifted, curve can satisfy this.

The second `getResults` takes the properties from the underlying curve and adds an any `ShiftAmount` to them. Although it is possible to determine the shift amount that is being considered (i.e. the constraint passed to the associated `getRequirements` call), returning *any* is simpler as the graph builder will compose this against the original value requirement so that the resultant dependency graph contains the shift amount.

Conversion functions should normally have a very low resolution priority. If the curve shifting function above were not lower priority than the function that produces the actual curve then requesting a curve without a `ShiftAmount` constraint may give a shifted curve which may not be the desired behaviour.

Alternatively, if all curves must be shifted then a high-priority default property function as described above could inject the shift amount from the calculation configuration's default properties.

~~~~~~~~~~~~~~~~~~~~~~~
Propagating constraints
~~~~~~~~~~~~~~~~~~~~~~~


A function may take two or more similar inputs (for example of the form `Curve{Name=[],Shift=[]}`), each of which should be controlled independently. Properties (and constraints) do not propagate automatically as the rules will vary depending on the nature of the function. The summing function example had to take an intersection of its inputs to produce a valid output, whereas in this example we want to expose the properties from each input in addition to the function's own parameters.

The first `getResults` returns properties on the value specification of the form `{ForwardCurve_Name=[],ForwardCurve_Shift=[],FundingCurve_Name=[],FundingCurve_Shift=[],Method=[A,B,C]}`. To avoid the name collision, the property names from the two inputs are all prefixed with a string that relates to the input.

The `getRequirements` call can inspect the constraints in the `desiredValue` parameter to construct the two required inputs. A desired value of `{ForwardCurve_Name=X,ForwardCurve_Shift=0.5,FundingCurve_Name=Y,FundingCurve_Shift=-0.5,Method=B}` should give two input requirements `Curve{Name=X,Shift=0.5,.Id=Forward?}` and `Curve{Name=Y,Shift=-0.5,.Id=Funding?}`. The `.Id` property is an input tag, described later, to distinguish the inputs.

The second `getResults` call should combine the properties from the input value requirements to produce the output properties. For example there may have been a wildcard input constraint that has now been resolved (e.g. by a downstream default injection function).

Note that this approach means that property names can be chosen that relate directly to the value being produced. For example, a property called `Name` makes sense when considering an individual curve/surface/cube but becomes `CurveName`, `SurfaceName`, `CubeName` when in the properties of a function that is using it on its inputs.

The injection of default values can take place in one of two places in scenarios such as this, depending on function priorities. For example, suppose two default value injection functions *X* and *Y*. *X* injects `ForwardCurve_Name=A` for the `Foo` value. *Y* injects `Name=B` for the curve value. For the requirement `Foo{ForwardCurve_Name=[]}`, the resolved value depends on the relative priorities of the functions. First the simple case of just one default function being used:



+----------------+------------------------------+-------------------------------------------------------------------------------------------------------------+
| Priority order | Result                       | Explanation                                                                                                 |
+================+==============================+=============================================================================================================+
| _X_, _Foo_     |  `Foo{ForwardCurve_Name=A}`  | _X_ injects the value before _Foo_ is considered.                                                           |
+----------------+------------------------------+-------------------------------------------------------------------------------------------------------------+
| _Foo_, _X_     |  `Foo{ForwardCurve_Name=A}`  | _Foo_ is considered first, but fails as its inputs won't resolve. _X_ is then used and injects the default. |
+----------------+------------------------------+-------------------------------------------------------------------------------------------------------------+
| _Y_, _Foo_     |  `Foo{ForwardCurve_Name=B}`  | _Foo_ is considered first. _Y_ injects the value when _Foo_'s input requirements are processed.             |
+----------------+------------------------------+-------------------------------------------------------------------------------------------------------------+
| _Foo_, _Y_     |  `Foo{ForwardCurve_Name=B}`  | _Foo_ is considered first. _Y_ injects the value when _Foo_'s input requirements are processed.             |
+----------------+------------------------------+-------------------------------------------------------------------------------------------------------------+



When both are used the relative priorities are more important:



+-----------------+------------------------------+------------------------------------------------------------------------------------------------------------------+
| Priority order  | Result                       | Explanation                                                                                                      |
+=================+==============================+==================================================================================================================+
| _X_, _Foo_, _Y_ |  `Foo{ForwardCurve_Name=A}`  | _X_ injects the value before _Foo_ is considered. _Y_ is not used.                                               |
+-----------------+------------------------------+------------------------------------------------------------------------------------------------------------------+
| _Y_, _Foo_, _X_ |  `Foo{ForwardCurve_Name=B}`  | _Foo_ is considered first. _Y_ injects the value when _Foo_'s input requirements are processed. _X_ is not used. |
+-----------------+------------------------------+------------------------------------------------------------------------------------------------------------------+
| _X_, _Y_, _Foo_ |  `Foo{ForwardCurve_Name=A}`  | _X_ injects the value before _Foo_ is considered. _Y_ is not used.                                               |
+-----------------+------------------------------+------------------------------------------------------------------------------------------------------------------+
| _Y_, _X_, _Foo_ |  `Foo{ForwardCurve_Name=A}`  | _X_ injects the value before _Foo_ is considered. _Y_ is not used.                                               |
+-----------------+------------------------------+------------------------------------------------------------------------------------------------------------------+
| _Foo_, _X_, _Y_ |  `Foo{ForwardCurve_Name=B}`  | _Foo_ is considered first. _Y_ injects the value when _Foo_'s input requirements are processed. _X_ is not used. |
+-----------------+------------------------------+------------------------------------------------------------------------------------------------------------------+
| _Foo_, _Y_, _X_ |  `Foo{ForwardCurve_Name=B}`  | _Foo_ is considered first. _Y_ injects the value when _Foo_'s input requirements are processed. _X_ is not used. |
+-----------------+------------------------------+------------------------------------------------------------------------------------------------------------------+



~~~~~~~~~~~~~~~~~~~~~~~~~~
Tagging input requirements
~~~~~~~~~~~~~~~~~~~~~~~~~~


For some functions the input requirements are easily identified based on the value requirement names or computation targets. For others it may not be possible to distinguish between different requirements. In the `Propagating constraints`_  example, two curves were requested that would be put to two different uses, but the requirements `Curve{Name=X}` and `Curve{Name=Y}` do not indicate which is which. This makes writing the second `getResults` method awkward.

Adding an optional property can resolve this. Making the property optional does not affect the resolution of the input requirement and the second `getResults` method gets a map containing the requested requirements and the resolved specifications. It is important to use a property that will not influence graph building however. Property names beginning with a `.` are reserved and can be used for this purpose. For example, `Curve{Name=X,.Id=Funding?}` and `Curve{Name=Y,.Id=Forward?}`.

This implies that any functions that propagate constraints from the desired value to their inputs should ignore any that start with a `.` character although if they do it is unlikely to cause a problem as long as the optionality is preserved.
