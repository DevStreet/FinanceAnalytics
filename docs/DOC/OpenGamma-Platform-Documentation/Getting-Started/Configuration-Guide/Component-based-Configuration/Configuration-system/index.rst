title: Configuration system
shortcut: DOC:Configuration system
---
This page describes the design and implementation of the `Component-based Configuration </confluence/DOC/OpenGamma-Platform-Documentation/Getting-Started/Configuration-Guide/Component-based-Configuration/index.rst>`_ .


........
Overview
........


The configuration system consists of a number of interacting parts:


*  a command line server


*  a configuration manager


*  a central repository of components



~~~~~~
Server
~~~~~~


The platform is normally started using an instance of `OpenGammaComponentServer`. The class can be invoked directly or via a subclass that captures the classpath. It has a main method, but can also be used by creating an instance and calling `run`. It takes the following arguments:


*  -v or --verbose - causes the system to write helpful messages to system out on startup


*  -q or --quiet - causes the system to write nothing (or the minimum possible) to system out on startup


*  -h or --help - prints the command line help explanation


*  configFileName - the last argument, specifying a single configuration file to start (see below)


The server will call `OpenGammaComponentServerMonitor` to start an optional socket permitting remote shutdown. 

The server is primarily responsible for parsing the standard command line. All supplied OpenGamma servers are started using the same class by passing different configuration files. The server uses the component manager to create the repository.


~~~~~~~~~~~~~~~~~~~~~
Manager and Factories
~~~~~~~~~~~~~~~~~~~~~


The manager is primarily responsible for controlling the reading of the configuration files to initialize and start the underlying repository.

The configuration is in two parts - simple properties and structured factories.

The simple properties part is a map of String key to String value. The properties may be set manually by invoking a method on the manager, however they will normally come from a properties file.

The structured factory part is a list of factories that perform the main work of initializing the application. Each factory is a simple Java class that implements the `ComponentFactory` interface. That interface defines a single `init` method where the factory must create and register the component(s) it is responsible for creating. Most factories create just one component.

A component factory will typically require some other components to complete its task. For example, a `SecuritySource` is normally built on top of a `SecurityMaster`. There are two ways to get these input components.

The first way is to use the power of `Joda-Beans <http://joda-beans.sourceforge.net/userguide.html>`_  and configuration injection. To use this approach, simply define a Joda-Bean property in the factory for each piece of configuration or component that the factory requires. Annotate the property with `validate="notNull"` if it is mandatory. If a component is required, ensure that the type of the variable matches the component type. Before the factory is invoked, the manager will populate each property from the configuration.

The second way is to use the map of String to String configuration data passed to the factory. This will come from the configuration files and can be used as desired, such as to lookup a component in the repository (the service locator pattern). If this approach is taken, then the map must be empty when the factory exits. In other words, the factory implementation should use `map.remove(key)` not `map.get(key)` to extract the configuration it desires.

The Joda-Bean approach is preferred as it is a lot more usable in practice.

The manager is designed to be strict in how input is handled and validated. Each piece of configuration passed in must be used in some way. It must match either a Joda-Bean property, or be used by the factories `init` method by removing all the keys from the map. This strict approach is necessary to ensure that configuration files stay up to date.

Note that the intention is that all aspects of the component are handled by the same factory. Should the component need a caching wrapper, JMS messaging, JMX monitoring or a RESTful public API, the same factory should handle it. This centralises the "glue" knowledge in a single place for maintainability.


~~~~~~~~~~
Repository
~~~~~~~~~~


The core in the configuration system that is being created is the `ComponentRepository`. This is a single central repository of running components, similar to the `ApplicationContext` in Spring. Normally, the repository is managed by the component manager, however it is useful to understand what it stores and how it is setup.

The repository stores four distinct items:


*  a map of `ComponentInfo` to component instance - stored using the component key


*  a list of `Lifecycle` instances - the Spring interface is used here


*  a list of `ServletContextAware` instances - the Spring interface is used here


*  a store of RESTfully published components


Note that the repository is specifically designed around the needs of the OpenGamma platform, which is architected around components linked by RESTful interfaces. This is different from Spring, where all beans are created equal and there is no special support for components or REST.

During creation, the repository goes through three distinct lifecycle phases:


*  initialization - where each component is registered


*  start - where the server is started


*  stop - where the server is stopped


The initialization phase consists of the invocation of a set of registration methods to create the state. The registration methods are:


*  registerComponent(ComponentInfo info, Object componentInstance)


*  registerComponent(Class<?> type, String classifier, Object componentInstance)


*  registerServletContextAware(ServletContextAware scAwareInstance)


*  registerLifecycle(Lifecycle lifecycleInstance)


*  registerLifecycleStop(Object lifecycleInstance, String stopMethodName)


*  registerMBean(Object instance)


*  registerMBean(Object instance, ObjectName name)


The first two methods are the primary ones, with the second one simply creating the `ComponentInfo` from the type and classifier. The latter three methods register objects that implement special interfaces. Note that if a component is registered that implements `Lifecycle` or `ServletContextAware`, then the object is automatically added to the relevant list. In other words, the middle three methods are only used in special cases where an object internal to a component implements the special interface. The MBean methods are manually called to setup JMX.

When registering a component, if the object specified implements `FactoryBean` then it will be treated as a factory. Only the factory-created object will be registered, although both the factory and created object will be checked for `Lifecycle` and `ServletContextAware`.

Once the repository has been initialized, it must be started. This step will typically start any connections, such as to JMS, JMX or the RESTful server.

When desired, the repository can be stopped. This is normally called by invoking the `OpenGammaComponentServerMonitor` socket functionality. This will call any registered stop events, which will typically end the running server and all connections.


~~~~~~~~~~~~~~~~~~
Spring integration
~~~~~~~~~~~~~~~~~~


Integration is provided between the OpenGamma component-based system and Spring. The integration requires a conversion between the name used for beans in Spring and the compound key used in OpenGamma. The Spring name is the reverse of the standard component name and consists of the classifier followed by the component type.

For example, the component `SecurityMaster::user` would be referenced in Spring as `userSecurityMaster`.

The class `ComponentRepositoryBeanPostProcessor` is used to expose all the components in the repository to a Spring file. In the other direction, subclasses of `AbstractSpringComponentFactory` are used to read beans from Spring and convert them to components.

OpenGamma still uses Spring configuration for some elements, notably low level connection management. This is used where the items may become heavily customised in different deployments and where Spring adds value. Note that some Spring benefits, such as AOP and Lifecycle management, may be unavailable, as the Spring context is normally discarded after the creation phase. In other words, Spring is used to create certain objects, but they are then managed by the component repository.


~~~~~~
Detail
~~~~~~



*  read the details of the `Configuration format </confluence/DOC/OpenGamma-Platform-Documentation/Getting-Started/Configuration-Guide/Component-based-Configuration/Configuration-format/index.rst>`_ 


*  explore the RESTful `Component RESTful publishing </confluence/DOC/OpenGamma-Platform-Documentation/Getting-Started/Configuration-Guide/Component-based-Configuration/Component-RESTful-publishing/index.rst>`_ 


*  see information on OpenGamma `Configuration conventions </confluence/DOC/OpenGamma-Platform-Documentation/Getting-Started/Configuration-Guide/Component-based-Configuration/Configuration-conventions/index.rst>`_ 

