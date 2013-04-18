title: Writing a custom OpenGamma Live Data Server
shortcut: DOC:Writing a custom OpenGamma Live Data Server
---
The OpenGamma Live Data subsystem is a standalone library which supports everything usually required to handle live financial data in a generic way. The client portion of the library is used by the OpenGamma Engine to make the subscriptions required for live data inputs. These will be satisfied by a remote live data server, without any coupling between the OpenGamma Engine and the available live data sources.


The usual purpose of an *OpenGamma Live Data* server is to wrap a third-party live data API, to allow clients to work with just the *OpenGamma Live Data* client API regardless of the number of live data sources.

This guide is aimed at developers wishing to write a custom *OpenGamma Live Data* server, perhaps to make a custom live data feed available to the OpenGamma Engine for use in calculations, or to any other *OpenGamma Live Data* client. This is written purely from the perspective of writing a server, without the need for a prior understanding of the client API, although brief references to this API are inevitable for clarity.

................
Writing a Server
................


An *OpenGamma Live Data* server is a concrete subclass of `AbstractLiveDataServer </javadoc/index.html?com/opengamma/livedata/server/AbstractLiveDataServer.html>`_. Only a minimal set of methods require implementations to produce such a subclass, making the server as simple as possible to implement; all of the core functionality that is common to many live data servers is provided by the library.

~~~~~~~~~~~~~~
Initialisation
~~~~~~~~~~~~~~


If the server needs to perform initialisation, for example by establishing a connection to the ultimate source of live data, this should be done in the implementation of:



.. code::

    void doConnect();




~~~~~~~~~~~~~
Subscriptions
~~~~~~~~~~~~~


*OpenGamma Live Data* operates using a subscription mechanism to individual data lines: a client must make a subscription to a particular element of live data in order to begin receiving updates to it, normally called *ticks*. Often this will map directly to the pattern used by a third-party library which is being wrapped, so an important part of writing an *OpenGamma Live Data* server is to manage these subscriptions.

When a client makes a subscription, it does so by providing an [IdentifierBundle|Identifiers#Identifier Bundles] describing the line of live data required. For example, this might reference a security for which the current market value is required. This security might have many associated [Identifier|Identifiers#Identifiers] instances, for example a Bloomberg Ticker, a Reuters RIC, and an in-house identifier. Each of these is an identifier in a different _identification domain_.


An *OpenGamma Live Data* server operates in a single *identification domain*. When a client makes a subscription request, the `Identifiers </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/Core-Concepts/Identifiers/index.rst>`_  is resolved into an `Identifiers </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/Core-Concepts/Identifiers/index.rst>`_  in the server's *identification domain*, and only this is presented when making a subscription.

The live data server reports its desired `IdentificationScheme </javadoc/index.html?com/opengamma/id/IdentificationScheme.html>`_ by implementing


.. code::

    IdentificationScheme getUniqueIdDomain();




Since the server operates entirely with `Identifiers </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/Core-Concepts/Identifiers/index.rst>`_  instances with this `IdentificationScheme </javadoc/index.html?com/opengamma/id/IdentificationScheme.html>`_, only the *value* of an `Identifiers </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/Core-Concepts/Identifiers/index.rst>`_  is now required to specify the requested live data line. The server should interpret this value in the context of the expected `IdentificationScheme </javadoc/index.html?com/opengamma/id/IdentificationScheme.html>`_. As such, when subscriptions are made, only the `String` value of each `Identifiers </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/Core-Concepts/Identifiers/index.rst>`_  is passed to the server implementation. The following method must be implemented to act on the subscription requests, perhaps making the subscriptions with a third-party library:



.. code::

    Map<String, Object> doSubscribe(Collection<String> uniqueIds);




This may be called with bulk subscription requests, hence the collection of identifier values. It should return a map from `Identifiers </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/Core-Concepts/Identifiers/index.rst>`_  value to an internal handle. This handle is used when unsubscribing, where it is passed in to identify the original subscription:



.. code::

    void doUnsubscribe(Collection<Object> subscriptionHandles);




Again, this method may be called for bulk unsubscriptions.

~~~~~
Ticks
~~~~~


With subscriptions made, the client expects to be notified when changes occur. This is done by making a call to the following method inherited from the parent:



.. code::

    void liveDataReceived(String uniqueId, FudgeMsg liveDataFields);




This method takes the `Identifiers </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/Core-Concepts/Identifiers/index.rst>`_  value corresponding to the subscription, and a Fudge message containing the updated set of live data for this subscription. This should represent, as much as possible, the raw data received from the underlying provider. Fudge messages are used here as a convenient way to represent a collection of key-value pairs, as well being required for later transformations that occur during normalisation.

~~~~~~~~~
Snapshots
~~~~~~~~~


Normally a subscription to live data changes is not enough; most clients need the *current value* of each live data line when they make a subscription, to be followed by these changes as they occur. This is supported through *snapshots*.

The underlying library looks after precisely when a snapshot is requested, which may be based on an explicit client request. The server simply needs to implement



.. code::

    Map<String, FudgeMsg> doSnapshot(Collection<String> uniqueIds);




As usual, this method can handle a bulk snapshot request. It should return a map from `Identifiers </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/Core-Concepts/Identifiers/index.rst>`_  value to the raw data representing the snapshot, as a Fudge message. This should be the same structured raw data that would be provided to `liveDataReceived` if the subscription were to tick.

Underlying live data providers may differ in their behaviour when a subscription is made: some may automatically push through a snapshot as the first tick, while others may require an explicit snapshot request to achieve this behaviour. As such, a method must be implemented to allow the server to control whether an explicit snapshot request is made:



.. code::

    boolean snapshotOnSubscriptionStartRequired(Subscription subscription)




This behaviour can be made to differ by subscription, if required, by inspecting the `subscription` argument.

~~~~~~~~
Shutdown
~~~~~~~~


When the live data server is stopped, any clean-up should be done in the implementation of



.. code::

    void doDisconnect();




.............................
Required Pluggable Components
.............................


While the methods described above are all that must be implemented to write an *OpenGamma Live Data* server, this server is not yet capable of running: there are several pluggable components which must be injected at runtime to allow the server to operate. These would ideally be injected using a dependency injection framework such as Spring.

~~~~~~~~~~~~~~~~~~~
Entitlement Checker
~~~~~~~~~~~~~~~~~~~


An instance of `LiveDataEntitlementChecker </javadoc/index.html?com/opengamma/livedata/entitlement/LiveDataEntitlementChecker.html>`_ must be injected using `setEntitlementChecker`. This is a relatively straightforward interface which is used automatically by the library to perform entitlement checking as subscriptions are made.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Distribution Specification Resolver
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


An instance of `DistributionSpecificationResolver </javadoc/index.html?com/opengamma/livedata/resolver/DistributionSpecificationResolver.html>`_ is required through `setDistributionSpecificationResolver`. This defines how any line of live data is distributed to clients, including the normalisation applied to the data and the JMS topic over which it is distributed.

A default resolver, `DefaultDistributionSpecificationResolver </javadoc/index.html?com/opengamma/livedata/resolver/DefaultDistributionSpecificationResolver.html>`_, is provided which provides a standard implementation in the context of three further interfaces:

``````````
IdResolver
``````````


The `IdResolver </javadoc/index.html?com/opengamma/livedata/resolver/IdResolver.html>`_ provides the mapping from an `Identifiers </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/Core-Concepts/Identifiers/index.rst>`_  to an `Identifiers </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/Core-Concepts/Identifiers/index.rst>`_  in the server's *identification domain*. A simple implementation may expect to find the required identifier in the bundle, but more advanced implementations may use identifiers from the bundle to to look up the required identifier with the underlying data source.

`````````````````````````
NormalizationRuleResolver
`````````````````````````


The `NormalizationRuleResolver </javadoc/index.html?com/opengamma/livedata/resolver/NormalizationRuleResolver.html>`_ allows a set of normalisation rules to be found from a rule set name. A `StandardRuleResolver </javadoc/index.html?com/opengamma/livedata/normalization/StandardRuleResolver.html>`_ implementation is provided.

````````````````````
JmsTopicNameResolver
````````````````````


The `JmsTopicNameResolver </javadoc/index.html?com/opengamma/livedata/resolver/JmsTopicNameResolver.html>`_ provides the mapping from an `Identifiers </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/Core-Concepts/Identifiers/index.rst>`_  used in a subscription to the JMS topic to which updates will be published.

~~~~~~~~~~~~~~~~~~
Market Data Sender
~~~~~~~~~~~~~~~~~~


In order that the live data server implementation is decoupled from the underlying transport, a `MarketDataSenderFactory </javadoc/index.html?com/opengamma/livedata/server/distribution/MarketDataSenderFactory.html>`_ must be injected. This is capable of creating `MarketDataSender </javadoc/index.html?com/opengamma/livedata/server/distribution/MarketDataSender.html>`_ instances which are used to send live data updates.

Where JMS is being used as the transport, the `JmsSenderFactory </javadoc/index.html?com/opengamma/livedata/server/distribution/JmsSenderFactory.html>`_ is provided.
