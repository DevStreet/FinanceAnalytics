title: Configuration format
shortcut: DOC:Configuration format
---
This page describes the configuration files used by the `Component-based Configuration </confluence/DOC/OpenGamma-Platform-Documentation/Getting-Started/Configuration-Guide/Component-based-Configuration/index.rst>`_ .

........
Overview
........


The configuration files come in two types:


*  properties files - describing variable system settings


*  INI files - describing the "glue" code of the server


It would not be unusual for the development team to be responsible for INI files and the deployment team to be responsible for properties files.


~~~~~~~~
Chaining
~~~~~~~~


A typical configuration consists of a chain of properties files leading to a single INI file.

The application will normally specify a single properties file. That will contain the special key - `MANAGER.NEXT.FILE` - which specifies the next file in the chain, which may be a properties file or an INI file. When the chain reaches an INI file the chain is complete. File references may use a `classpath:` or `file:` prefix to load via the classpath of file system.

A typical server will have a chain of three files:


*  the properties file specific to the machine/environment, with settings only relevant there


*  the properties file generally configured for the type of server


*  the INI file defining the factories to invoke, effectively describing the "glue" code of the server



~~~~~~~~~~~~~~~~
Properties files
~~~~~~~~~~~~~~~~


The property file format is the standard Java format, as defined by `java.util.Properties`. The keys are loaded from the file into a String to String hash map with one additional rule - each property key can only be defined once. After the key is defined, subsequent attempts to redefine it will be ignored. This is the same behaviour as Apache Ant.

The "set once" behaviour is used in conjunction with the chaining functionality described above. It allows the first file in the chain to define the value and effectively override values in later files in the chain.

If the server is started with verbose mode, then the complete list of properties is output at startup to help debug which properties are used.


~~~~~~~~~
INI files
~~~~~~~~~


The INI file performs the role of defining the "glue" code to start the server. The file format is similar to a properties file, but broken up into sections. Each section has a header, enclosed by square brackets. The header is a simple name that is for property injection and error messages.



.. code::

    [section1]
    key1 = valueInSection1
    
    [section2]
    key1 = valueInSection2




Note that the same key may appear in different sections, but a key may not be duplicated in the same section.

^^^^^^^^^^^^^^^^^^
Property injection
^^^^^^^^^^^^^^^^^^


Before it is processed, any `${property.name}` properties are replaced, as a textual replacement using the loaded properties.

For example, in the following code, the ${jms.server} value in the INI file will be replaced with the text "foo".



.. code::

    // properties file
    jms.server = foo
    
    // INI file
    [securityMaster]
    jms = ${jms.server}




There is one special case. If the property starts with "INI." then the key is interpreted as a direct override of one of the values in the INI file. The format will be "INI.section.keyToOverride".

For example, in the following code, the "INI.securityMaster.database" property will cause the "database" key in the "securityMaster" section of the INI file to be overridden.



.. code::

    // properties file
    INI.securityMaster.database = foo
    
    // INI file
    [securityMaster]
    database = bar





^^^^^^^^^^^^^^^^^
Global properties
^^^^^^^^^^^^^^^^^


One section name is reserved - "global". This is used to define global OpenGamma configuration. The main use is for the key "time.zone", which specifies the time-zone ID string used by `OpenGammaClock`.



.. code::

    [global]
    time.zone = Europe/London




^^^^^^^^^
Factories
^^^^^^^^^


All other INI sections are used to define the component factories. Each section that contains the key "factory" will be processed. The value of the key "factory" must be the full class name of the factory class, which must be an instantiable class.



.. code::

    [myComponent]
    factory = com.opengamma.component.MyComponentFactory
    classifier = foo
    database = bar
    jms = ::standard




If the bean is a Joda-Bean, it is injected with the remaining values of the INI file section. The following rules are used to determine how the value is set:


*  component reference - if the value contains a double colon, then it is treated as a component key


*  null - if the value is "null" then the Joda-Bean property will be set to null


*  properties - if the value is "MANAGER.PROPERTIES" then a Spring resource representing the merged set of properties is set


*  repository - if the property type is `ComponentRepository` then the repository is injected


*  resource - if the property type is `Resource` then the value is treated as a classpath or file resource


*  general - if the property type is a type that can be converted from a String (such as a boolean, int or URI) then it will be converted


The component reference has two variants. The key may be specified in full - `ComponentType::classifier` - or in part - `::classifier`. If it is specified in part, then the component type is inferred from the Joda-Bean property type. This latter form, only specifying the classifier, is the normal form.


~~~~~~
Detail
~~~~~~



*  understand the design of the `Configuration system </confluence/DOC/OpenGamma-Platform-Documentation/Getting-Started/Configuration-Guide/Component-based-Configuration/Configuration-system/index.rst>`_ 


*  explore the RESTful `Component RESTful publishing </confluence/DOC/OpenGamma-Platform-Documentation/Getting-Started/Configuration-Guide/Component-based-Configuration/Component-RESTful-publishing/index.rst>`_ 


*  see information on OpenGamma `Configuration conventions </confluence/DOC/OpenGamma-Platform-Documentation/Getting-Started/Configuration-Guide/Component-based-Configuration/Configuration-conventions/index.rst>`_ 


