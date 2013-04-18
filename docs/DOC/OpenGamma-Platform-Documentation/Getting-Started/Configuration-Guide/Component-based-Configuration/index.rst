title: Component-based Configuration
shortcut: DOC:Component-based Configuration
---
OpenGamma uses a dedicated component-based configuration system. Use of this system is not mandatory, however it has been designed to meet the specific requirements of the platform.

........
Overview
........


The component-based configuration system is a small library that provides a way to configure and run an OpenGamma system. It has the ability to integrate with Spring. Integration with Java EE CDI should also be possible.

The component system is built on top of the platform and is external to it. All the core OpenGamma projects have no dependencies on the component system, however the integration and example server code does.

A key goal of the system is to ensure that  a large portion of the "glue" code is done in Java. This ensures that it is compiled and is safe from accidental change. This is in contrast to Spring, where large amounts of application are defined solely in XML. The system also integrates with the RESTful server setup, making it easy to publish components remotely.


~~~~~~~~~~
Components
~~~~~~~~~~


The design of the configuration is based around the concept of components - large scale units within the application. Typical components are the `Sources, Masters, and Databases </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/Core-Concepts/Sources,-Masters,-and-Databases/index.rst>`_  and `Views and Results </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/Core-Concepts/Views-and-Results/index.rst>`_ . Most components are defined by a Java interface with potentially multiple implementations, for example the `SecurityMaster`. Most components publish a RESTful API, allowing them to be accessed remotely. A good component is of a suitable size and design to support RESTful access.

The component-based configuration system manages the components using a compound key of two parts. Components are registered and looked up using the compound key:


*  the *component type* - a Java interface/class `java.lang.Class` object defining the API of the component


*  the *classifier* - a `String` name for the instance of the component


For example, all security masters implement the `SecurityMaster` interface and this is used as the component type. The second part of the key is descriptive and allows multiple security masters to be started within the platform. For example, you might have the "central" security master and the "user" security master. The classifier is thus "central" or "user". Debugging and reference will use a fixed text format using a double colon separating the simple name of the component type and the classifier. For this example, the components would be `SecurityMaster::central` and `SecurityMaster::user`.

One key advantage of a component-based configuration system is that it encourages architects to views the system in terms of significant components with a similar basic design. In a well-structured system, most components will have RESTful interfaces. As such, it becomes possible to discuss and design deployment in terms of the components, rather than a lower level of detail.


~~~~~~
Detail
~~~~~~



*  understand the design of the `Configuration system </confluence/DOC/OpenGamma-Platform-Documentation/Getting-Started/Configuration-Guide/Component-based-Configuration/Configuration-system/index.rst>`_ 


*  explore the RESTful `Component RESTful publishing </confluence/DOC/OpenGamma-Platform-Documentation/Getting-Started/Configuration-Guide/Component-based-Configuration/Component-RESTful-publishing/index.rst>`_ 


*  read the details of the `Configuration format </confluence/DOC/OpenGamma-Platform-Documentation/Getting-Started/Configuration-Guide/Component-based-Configuration/Configuration-format/index.rst>`_ 


*  see information on OpenGamma `Configuration conventions </confluence/DOC/OpenGamma-Platform-Documentation/Getting-Started/Configuration-Guide/Component-based-Configuration/Configuration-conventions/index.rst>`_ 



