title: Adding custom engine functions to the FunctionRepository
shortcut: DOC:Adding custom engine functions to the FunctionRepository
---
This is a task you need to perform if you add your own engine functions.

First, make sure that you have developed the `FunctionDefinition`/`FunctionInvoker` pair by following the instructions at `Adding a new Function Definition and Invoker </confluence/DOC/OpenGamma-Platform-Documentation/Developing-Applications-to-Integrate-with-the-OpenGamma-Platform/Adding-a-new-Function-Definition-and-Invoker/index.rst>`_ .

Currently, you'll need to add some entries in `DemoStandardFunctionConfiguration.constructRepositoryConfiguration()` (or `ExampleStandardFunctionConfiguration` for the non-Bloomberg examples code).  In future this will probably be moved into the configuration database.

*  First add the function you've written to the functionConfigs list using of of these wrappers 


   *  ParameterizedFunctionConfiguration, if the constructor of your function takes parameters, pass a list of parameters in here


   *  StaticFunctionConfiguration, if the constructor of your function had no parameters


*  Add any scaling functions


   *  If your function applies to a security target type


      *  If you want to add unit scaling for positions(i.e. position level yields same result as per-security level), use the `addUnitScalingFunction` method, quoting the requirement name that your function produces values for.


      *  If you want to add a scaling function for positions (i.e. position level yields the product of the position size and the per-security result), use the `addScalingFunction` method, quoting the requirement name that your function produces values for.


   *  Dealing with position aggregations


      *  If your analytic doesn't make sense to aggregate (e.g. bond clean price), you can add a 'dummy function' that just returns zero for aggregates using the `addDummyFunction` method, quoting the requirement name that your function produces values for.


      *  If your analytic is linear when aggregated, i.e. you can just add it up, you can add a 'summing function' that just returns the sum of all the positions under it using the `addSummingFunction` method, quoting the requirement name that your function produces values for.


      *  If your analytic is non-linear (e.g. HVaR) when aggregated, then you need to write a function specifically to produce the value for that target type.

