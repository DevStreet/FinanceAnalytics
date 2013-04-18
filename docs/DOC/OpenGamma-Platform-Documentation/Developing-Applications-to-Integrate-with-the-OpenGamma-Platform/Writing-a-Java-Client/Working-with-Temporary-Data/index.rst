title: Working with Temporary Data
shortcut: DOC:Working with Temporary Data
---
So far we have considered driving calculations from Java through existing *view definitions* which operate on existing *portfolios* and *securities*. Often a client will need to use the OpenGamma Engine to produce a custom set of outputs, perhaps operating on a portfolio containing hypothetical securities which may have been created by the user. This is *temporary data* to the OpenGamma Platform as it is primarily created and managed by the client as opposed to being managed and versioned through the OpenGamma Platform.

........
Overview
........


Working with *temporary data* involves one key additional step: before any results can be produced, the data must first be injected into the Platform for the Engine to see and use. After this, the data is no different (from the Engine's perspective) from any other data which it may use to perform calculations.

........................
Injecting Temporary Data
........................


Data in the OpenGamma Platform is managed through the `Sources, Masters, and Databases </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/Core-Concepts/Sources,-Masters,-and-Databases/index.rst>`_  interfaces.

A typical Platform configuration includes two categories of *master*: *central* masters and *user* masters. The *central* masters are the core, database-backed masters intended for storing data to be managed and versioned through the OpenGamma Platform. The *user* masters are intended to be used for temporary data, and may be in-memory or backed by a lightweight database. This separation allows the *user* masters to offer a different class of storage more appropriate to their use, and to be purged freely. The Engine sees data injected into both categories of master through its *combined sources*.

The *user* masters may be used directly just like the *central* masters, but in doing so the client would be responsible for cleaning up any temporary data. A more convenient way to store temporary data is to access the *user* masters through a *financial user manager*, which ensures that data written by a client is cleaned up when that client goes away.

Most clients will run remotely from the rest of the OpenGamma Platform, so for this example we will access a remote *financial user manager* to construct a `RemoteClient` representing our client's temporary data needs. This assumes that:


*  the *financial user manager* is running on `opengamma-server`


*  its REST API is configured to use port 8080


*  it has the classifier `main`




.. code::

    // Retrieve the component information for the FinancialUserManager
    URI baseUri = URI.create("http://opengamma-server:8080/jax");
    RemoteComponentServer cs = new RemoteComponentServer(baseUri);
    ComponentInfo info = cs.getComponentServer().getComponentInfo(FinancialUserManager.class, "main");
    
    // Construct a RemoteClient from the FinancialUserManager
    URI userManagerUri = info.getUri();
    RemoteClient remoteClient = RemoteClient.forNewClient(OpenGammaFudgeContext.getInstance(), userManagerUri, UserPrincipal.getLocalUser().getUserName());
    
    // Set up the client's heartbeating
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    scheduler.scheduleWithFixedDelay(remoteClient.createHeartbeatSender(), 5, 5, TimeUnit.MINUTES);




The `RemoteClient` exposes the *user* masters. A client will need to add a temporary *view definition* to request a custom set of *values* or to request outputs on a custom *portfolio*.



.. code::

    PortfolioMaster userPortfolioMaster = remoteClient.getPortfolioMaster();
    userPortfolioMaster.add(...);
    
    ConfigMaster cm = remoteClient.getConfigMaster();
    ConfigItem<ViewDefinition> config = ConfigItem.of(viewDefn);
    ConfigMasterUtils.storeByName(configMaster, item);




For more information on injecting data, see `Using a Master </confluence/DOC/OpenGamma-Platform-Documentation/Developing-Applications-to-Integrate-with-the-OpenGamma-Platform/Using-a-Master/index.rst>`_ .

Any data added to the *user* masters will expire once the `RemoteClient` is no longer in use.

....................
Using Temporary Data
....................


While the `RemoteClient` is active, any temporary documents added to the *user* masters are visible to the Engine automatically through their `Identifiers </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/Core-Concepts/Identifiers/index.rst>`_  in exactly the same way as data added to the *central* masters. The identifier *scheme* distinguishes between the different masters, and the categories of master.

With the identifier of a temporary view definition in hand, calculations can be driven and their results extracted in `Writing a Java Client </confluence/DOC/OpenGamma-Platform-Documentation/Developing-Applications-to-Integrate-with-the-OpenGamma-Platform/Writing-a-Java-Client/index.rst>`_ .
