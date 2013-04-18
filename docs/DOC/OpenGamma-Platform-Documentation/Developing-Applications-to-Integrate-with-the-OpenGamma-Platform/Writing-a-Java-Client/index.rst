title: Writing a Java Client
shortcut: DOC:Writing a Java Client
---
Java is the language of the OpenGamma Platform, and as such Java clients benefit from a direct and complete alignment of the API with the Platform itself, and may even be run as local components of the Platform if necessary. However it should be noted that the remote Java API makes use of the same underlying REST API used elsewhere, so other languages may be more appropriate for remote clients.

............
Introduction
............


This guide is aimed at developers who are familiar with Java and the  `Core Concepts </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/Core-Concepts/index.rst>`_  of the OpenGamma platform, especially  `Views and Results </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/Core-Concepts/Views-and-Results/index.rst>`_  which will greatly help in understanding the terms used here.

Generally a core feature of any client is to access computation results, which will be covered in this guide. This is performed in the same way regardless of the *view definition* or the *execution options*. More advanced clients might first define their own *view definition* on which they wish to operate, perhaps with a custom *portfolio*, but doing so is beyond the scope of this document.

The code examples below deal entirely with Engine interfaces which are part of the public API; the implementations may be local or remote.

.............................
Consuming Computation Results
.............................


~~~~~~~~~~~~~~~~~~~~~~~~~~
Obtaining a View Processor
~~~~~~~~~~~~~~~~~~~~~~~~~~


Gaining access to a *view processor* is the only step which may require knowledge about where the *view processor* is running and whether it is local or remote. Ideally a `ViewProcessor </javadoc/index.html?com/opengamma/engine/view/ViewProcessor.html>`_ instance would be injected, for example via Spring, to allow the client to work with any implementation.

Most clients will run remotely from the rest of the OpenGamma Platform, so for this example we will use a remote `ViewProcessor </javadoc/index.html?com/opengamma/engine/view/ViewProcessor.html>`_ implementation. This assumes that:

*  the *view processor* is running on `opengamma-server`


*  its REST API is configured to use port `8080`


*  it has the classifier `main`


These can be checked by querying the URL `http://opengamma-server:port/jax/components`.



.. code::

    import java.net.URI;
    
    import com.opengamma.component.factory.RemoteComponentFactory;
    import com.opengamma.engine.view.ViewProcessor;
    
    ...
    
    URI baseUri = URI.create("http://opengamma-server:8080/jax");
    RemoteComponentFactory componentFactory = new RemoteComponentFactory(baseUri);
    ViewProcessor viewProcessor = componentFactory.getViewProcessor("main");




A client running as part of the OpenGamma Platform would simply use the real `ViewProcessor </javadoc/index.html?com/opengamma/engine/view/ViewProcessor.html>`_ instance instead.

~~~~~~~~~~~~~~~~~~~~~~~
Obtaining a View Client
~~~~~~~~~~~~~~~~~~~~~~~


In order to access computation results, a `ViewClient </javadoc/index.html?com/opengamma/engine/view/client/ViewClient.html>`_ instance is required. This is representative of the person who wishes to consume the results, and must be obtained by passing in their username:



.. code::

    import com.opengamma.engine.view.client.ViewClient;
    import com.opengamma.livedata.UserPrincipal;
    
    ...
    
    UserPrincipal localUser = UserPrincipal.getLocalUser();
    ViewClient viewClient = viewProcessor.createViewClient(localUser);




For simple clients, access to the *view processor* is no longer required; all operations are now performed on the *view client*.

~~~~~~~~~~~~~~~~~~~~~~~~~~~
Configuring the View Client
~~~~~~~~~~~~~~~~~~~~~~~~~~~


While the *view client* represents an Engine-side object, it is specific to the client which has created it, and as such provides operations which affect its behaviour including the delivery of results. Some of these operations, such as *pause* and *resume* are more useful once results are flowing.



.. code::

    viewClient.setResultMode(ViewResultMode.FULL_ONLY);
    viewClient.setResultListener(this);




The first line requests the full result set on every cycle, rather than just the differences since the previous cycle. The second line assumes that the caller implements `ViewResultListener </javadoc/index.html?com/opengamma/engine/view/listener/ViewResultListener.html>`_ which is the single interface through which the *view process* notifies attached clients of its output:



.. code::

    public interface ViewResultListener {
    
      void viewDefinitionCompiled(CompiledViewDefinition compiledViewDefinition);
      void viewDefinitionCompilationFailed(Instant valuationTime, Exception exception);
      void cycleCompleted(ViewComputationResultModel fullResult, ViewDeltaResultModel deltaResult);
      void cycleExecutionFailed(ViewCycleExecutionOptions executionOptions, Exception exception);
      void processCompleted();
      void processTerminated(boolean executionInterrupted);
    
    }




Sometimes a result listener is not interested in every type of notification, so `AbstractViewResultListener </javadoc/index.html?com/opengamma/engine/view/listener/AbstractViewResultListener.html>`_ is provided with default empty implementations. The main notification that a simple client will be interested in is `cycleCompleted`, which contains the requested computation results.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Attaching the View Client to a View Process
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


Currently the *view client* is configured to receive results, but is detached from any *view process*. The *view client* is attached to a *view process* by describing the requested *view process*:



.. code::

    viewClient.attachToViewProcess(viewDefinitionId, ExecutionOptions.infinite(MarketData.live()));




This describes a *view process* running for the *view definition* with unique identifier `viewDefinitionId`, with an infinite execution sequence using the default live market data source. If another client is already connected to a shared *view process* with the same description, this client will be attached to that same *view process* with little additional overhead. Common *execution options*, such as *real-time*, will lead to frequent sharing of *view processes*.

This call is enough to create demand for computation and cause results to flow to the client. If the *view process* has been created as a result of this client attachment, it will now be `Views and Results </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/Core-Concepts/Views-and-Results/index.rst>`_  which may take time; if the *view process* is already running, results can be expected immediately as the *view process* will always notify new listeners of the latest *compiled view definition* followed by the latest result where they exist.

~~~~~~~~~~~~~~~~~
Using the Results
~~~~~~~~~~~~~~~~~


That's it! The client is now receiving a stream of real-time computation results. All that remains is to use the output in some way:


*  Almost all clients will implement `cycleCompleted` to obtain the computation results.


*  Clients may need the *compiled view definition* to infer the expected structure of the results, for example to draw a grid in which portfolio results will be rendered. This is provided in `viewDefinitionCompiled` which is called whenever the *view definition* needs to be (re)compiled.


*  Clients should monitor failure notifications -- `viewDefinitionCompilationFailed` and `cycleExecutionFailed` -- and take appropriate action. A cycle failure is not as serious as a compilation failure, which could prevent many subsequent cycles from executing.


*  If *execution options* have been used which specify a finite *execution sequence*, the `processCompleted` call will indicate when the last cycle has completed.


``````````````````````````````
Publishing Computation Results
``````````````````````````````


As an example, a client might wish to publish computation results to another system. Taking this further, the OpenGamma Platform could be used in market marking, where the computation results are real-time prices and a client is written to publish these to the market.

~~~~~~~~~~~~~~~~~~~~~~~~~
Detaching the View Client
~~~~~~~~~~~~~~~~~~~~~~~~~


When results are no longer required from the *view process*, the *view client* should be *detached*; this may allow the *view process* itself to terminate.



.. code::

    viewClient.detachFromViewProcess();




The *view client* is now in the same state as it was prior to attaching to the *view process*, and retains all of its configuration. It may be reattached to a *view process* at any time.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Shutting Down the View Client
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


Even in a detached state, the *view client* is still represented by Engine-side state. Therefore, when the *view client* is no longer required, it should be shut down. This terminates the *view client*, cleaning up the associated state, and prevents its reuse.



.. code::

    viewClient.shutdown();




A *view client* can be shut down from any state, even while still attached to a *view process*. In this case, the *view client* will be detached automatically during shut down.
