title: Writing a C Sharp Client
shortcut: DOC:Writing a C Sharp Client
---
OpenGamma provides a reference implementation of a C# client, available from *GitHub* (`https://github.com/OpenGamma/OG-DotNet <https://github.com/OpenGamma/OG-DotNet>`_ ). As with all clients, this makes use of the same underlying REST API used elsewhere. The C# client implements the main portions of the API as a standalone library, as well as a couple of sample applications to demonstrate how this may be used:

*  OGDotNet.AnalyticsViewer - This is a simple results client


*  OGDotNet.SecurityViewer - This is a simple security client. &nbsp;It also allows you to see time series data


The `readme  <http://github.com/OpenGamma/OG-DotNet/blob/master/README.txt>`_ file should provide you the information you need to get the client working with your server.

............
Introduction
............


The instructions are conceptually similar to  `Writing a Java Client </confluence/DOC/OpenGamma-Platform-Documentation/Developing-Applications-to-Integrate-with-the-OpenGamma-Platform/Writing-a-Java-Client/index.rst>`_ .  In particular, the same concepts are useful before starting with this page: the `Core Concepts </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/Core-Concepts/index.rst>`_  of the OpenGamma Platform, especially `Views and Results </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/Core-Concepts/Views-and-Results/index.rst>`_ .




~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Consuming Computation Results
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


```````````````````````````````
Obtaining a RemoteEngineContext
```````````````````````````````


For this guide I will assume that your integration server is hosted at `http://myopengammaserver:8080/ <http://myopengammaserver/>`_ . You can check that you have the URI correct by navigating to the page in your browser. You should be redirected to the Web UI.

The starting point for all interaction with the OpenGamma Server is the `RemoteEngineContextFactory <https://github.com/OpenGamma/OG-DotNet/blob/master/OGDotNet-Analytics/Model/Context/RemoteEngineContextFactory.cs>`_ *.*

The example applications obtain the context using the `Castle <http://www.castleproject.org/>`_  project for Dependency injection.  A context can be obtained directly like this:



.. code::

    var fudgeContext = new OpenGammaFudgeContext();
    var serviceUri = new Uri("http://myopengammaserver:8080/jax");
    var configId = "0";
    var contextFactory = new RemoteEngineContextFactory(fudgeContext, serviceUri, configId);
    var context = contextFactory.CreateRemoteEngineContext();




````````````````````````
Configuring a ViewClient
````````````````````````


A basic ViewClient can be configured like this:



.. code::

    using (var remoteViewClient = context.ViewProcessor.CreateClient())
    {
        var options = ExecutionOptions.RealTime;
        var resultListener = new EventViewResultListener();
         //TODO: Hook up events
         // resultListener.CycleCompleted += (sender, e) => {/* ... */};
        client.SetResultListener(resultListener);
        client.AttachToViewProcess("My Swap View", options);
    }




The server will now be running computation for this client.  We can query *IsResultAvailable* and fetch the latest results using *GetLatestResult* but the best way to use the results is by attaching listeners to the `EventViewResultListener <https://github.com/OpenGamma/OG-DotNet/blob/master/OGDotNet-Analytics/Mappedtypes/engine/View/listener/EventViewResultListener.cs>`_ *.*

`````````````````````````````````
Using the EventViewResultListener
`````````````````````````````````



*  Almost all clients will listen to *CycleCompleted* to obtain the computation results.


*  Clients may need the compiled view definition to infer the expected structure of the results, for example to draw a grid in which portfolio results will be rendered. This is provided in *ViewDefinitionCompiled* which is invoked whenever the view definition needs to be (re)compiled.


*  Clients should monitor failure notifications -- *ViewDefinitionCompilationFailed* and *CycleExecutionFailed* -- and take appropriate action. A cycle failure is not as serious as a compilation failure, which could prevent many subsequent cycles from executing.


*  If execution options have been used which specify a finite execution sequence, the *ProcessCompleted* event will indicate when the last cycle has completed.


~~~~~~~~~~~~~~
Other services
~~~~~~~~~~~~~~


Other services are available from the `RemoteEngineContext <https://github.com/OpenGamma/OG-DotNet/blob/master/OGDotNet-Analytics/Model/Context/RemoteEngineContext.cs>`_ .  Many of these services are used by the sample applications and/or the Integration tests.  Another way to understand the details of these services is to read the Java code and its documentation.  The rest service is in the Java namespaces containing *rest* (e.g *com.opengamma.util.rest* and *com.opengamma.financial.view.rest*).  The URIs refer to *Resources*. For example the `RemoteSecurityMaster <https://github.com/OpenGamma/OG-DotNet/blob/master/OGDotNet-Analytics/Model/Resources/RemoteSecurityMaster.cs>`_  is the .NET twin of the Java *RemoteSecurityMaster*, and makes REST calls to the `SecurityMasterResource <https://github.com/OpenGamma/OG-Platform/blob/master/projects/OG-Financial/src/com/opengamma/financial/security/rest/SecurityMasterResource.java>`_ .

~~~~~~~~~~~~~~~
Advanced topics
~~~~~~~~~~~~~~~


`````````````````````````````````
Extending Fudge (De)serialization
`````````````````````````````````


If you have extended the analytics engine to introduce extra types of result the .NET client will need to be extended too.  This kind of problem often manifests as an exception like "Fudge.FudgeRuntimeException: Failed to find type for FudgeMsg[0:  => com.opengamma.financial.model.interestrate.curve.YieldCurve, ...".  In this case you would need to implement *OGDotNet.Mappedtypes.financial.model.interestrate.curve.YieldCurve* and possibly a corresponding *IFudgeSerializationSurrogate*.  Note that this implementation doesn't have to reside within the OpenGamma client, it can be in any assembly loaded into the AppDomain.
A full explanation for `Fudge-CSharp <http://www.fudgemsg.org/display/FDG/Fudge+CSharp>`_  is beyond the scope of this document.  The `getting started <http://www.fudgemsg.org/display/FDG/Fudge+CSharp+-+getting+started>`_  page may be helpful, as well as the documentation included with the library.

If a type doesn't seem to have an explicit implementation of serialization it may be being (de)serialized by one of the implementations of *IFudgeSerializationSurrogate* included with Fudge, e.g. *ImmutableSurrogate* or *PropertyBasedSerializationSurrogate.*

``````````````````````````````````````````````
Libraries used in the reference implementation
``````````````````````````````````````````````


All of the requests and responses to the OpenGamma service are encoded using `Fudge Messaging <http://www.fudgemsg.org/>`_ . The client uses `Fudge-CSharp <https://github.com/FudgeMsg/Fudge-CSharp>`_ , The C#/.Net Reference Implementation of the encoding specification, to do this encoding for you.

Results are pushed to clients via a message broker. The reference client uses `Apache.NMS <http://activemq.apache.org/nms/>`_  which has adapters which will work with various brokers, including `ActiveMQ <http://activemq.apache.org/nms/apachenmsactivemq.html>`_ , `MSMQ <http://activemq.apache.org/nms/apachenmsmsmq.html>`_  and any `STOMP <http://activemq.apache.org/nms/apachenmsstomp.html>`_  broker.
