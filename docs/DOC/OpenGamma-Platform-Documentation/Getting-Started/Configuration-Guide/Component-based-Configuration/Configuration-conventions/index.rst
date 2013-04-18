title: Configuration conventions
shortcut: DOC:Configuration conventions
---
This page describes the conventions used by OpenGamma within the broader `Component-based Configuration </confluence/DOC/OpenGamma-Platform-Documentation/Getting-Started/Configuration-Guide/Component-based-Configuration/index.rst>`_ .

........
Overview
........


The component-based configuration is tied to OpenGamma, however it is designed in a generic way so that it could be extracted as a separate library in the future. As a result, there are certain conventions in the way that OpenGamma uses the configuration


~~~~~~~~~~~
Conventions
~~~~~~~~~~~


Most OpenGamma configuration is organised into filing system folders - one per server. Thus there is a folder named "fullstack" which contains the "fullstack.ini" file and associated properties and Spring files.

Most Opengamma configuration is based primarily on the INI format, which some use of Spring. There are two main Spring files. The first, infrastructure, creates the connectors which link the application to the database, JMS and other external services. The second creates the engine. Over time, it is intended that the engine file will disappear and be converted to a Java based configuration factory.

Most OpenGamma configuration forms a chain of 3 files:


*  the environment specific file, such as `fullstack-example-bin.properties`


*  the machine specific file, such as `fullstack-example.properties`


*  the INI file, such as `fullstack.ini`


Most OpenGamma configuration uses the `classpath:` prefix to file references. This provides more flexibility in locating the files.


~~~~~~
Detail
~~~~~~



*  understand the design of the `Configuration system </confluence/DOC/OpenGamma-Platform-Documentation/Getting-Started/Configuration-Guide/Component-based-Configuration/Configuration-system/index.rst>`_ 


*  explore the RESTful `Component RESTful publishing </confluence/DOC/OpenGamma-Platform-Documentation/Getting-Started/Configuration-Guide/Component-based-Configuration/Component-RESTful-publishing/index.rst>`_ 


*  read the details of the `Configuration format </confluence/DOC/OpenGamma-Platform-Documentation/Getting-Started/Configuration-Guide/Component-based-Configuration/Configuration-format/index.rst>`_ 


