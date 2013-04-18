title: Component RESTful publishing
shortcut: DOC:Component RESTful publishing
---
This page describes how the RESTful aspect of the `Component-based Configuration </confluence/DOC/OpenGamma-Platform-Documentation/Getting-Started/Configuration-Guide/Component-based-Configuration/index.rst>`_  works.

........
Overview
........


Like any large system, OpenGamma can operate across multiple machines. Interaction is performed primarily via RESTful APIs and JMS. The component-based configuration system helps manage the RESTful APIs.


~~~~~~~~~~~~~~~~~~
RESTful publishing
~~~~~~~~~~~~~~~~~~


OpenGamma supports the publishing of component APIs over REST using JAX-RS. A typical example class to achieve this is `DataSecuritySourceResource`. The factory that creates the source component will create the RESTful wrapper class, and then register it:


*  create the component


*  register the component


*  create the RESTful wrapper


*  register the RESTful wrapper


The registration takes place on the instance of `RestComponents` available via the repository.



.. code::

    repo.getRestComponents().publish(componentInfo, resourceClass);




Note that the component information is normally shared between the main (local) component repository and the published (RESTful) one. Each component added in this way forms part of the principal set of published components. These are managed directly by OpenGamma, including their URIs.

Any OpenGamma component published with component information will be available under a special URI - `/jax/components`. Querying this URI returns a Fudge document that describes the available components. For example:



.. code::

    <fudgeEnvelope>
      <uri type="string">components</uri>
      <infos type="message">
        <fudgeField type="message">
          <type type="string">com.opengamma.master.config.ConfigMaster</type>
          <classifier type="string">central</classifier>
          <uri type="string">/jax/components/ConfigMaster/central</uri>
          <attributes type="message">
            <uniqueIdScheme type="string">DbCfg</uniqueIdScheme>
          </attributes>
        </fudgeField>
        <fudgeField type="message">
          <type type="string">com.opengamma.master.exchange.ExchangeMaster</type>
          <classifier type="string">central</classifier>
          <uri type="string">/jax/components/ExchangeMaster/central</uri>
          <attributes type="message">
            <uniqueIdScheme type="string">DbExg</uniqueIdScheme>
          </attributes>
        </fudgeField>
      </infos>
      <fudgeField0 ordinal="0" type="string">com.opengamma.component.ComponentServer</fudgeField0>
      <fudgeField0 ordinal="0" type="string">org.joda.beans.impl.direct.DirectBean</fudgeField0>
    </fudgeEnvelope>




The message consists of a Fudge message for each component. The message contains the component type, the classifier, the URI and any attributes that were registered in the `ComponentInfo` by the factory. As shown in the example above, the attributes can help the client to decide on the right component to talk to.


`````````````
Remote access
`````````````


The main reason to publish the components in an accessible way is to allow clients to connect in a semi-automatic way. While not fully up to the RESTful HATEOAS (Hypertext as the Engine Of Application State) standards, it does handle most basic cases.

The Java class `RemoteComponentServer` can be used to connect to a remote component server and access details of the available components. A key feature of this class is that it resolves the URIs that are received, so all the relative URIs in the message will be seen as absolute URIs by the client.


````````````
Republishing
````````````


One way of using the published components is to consume them on another server. The architecture may however define a three or more machine deployment. In this case, the components picked up from the first machine can be *re-published* on the second machine, and so on.

The key difference between published and re-published is the URI that will be in the message at `/jax/components`. The URI of a locally published component will be relative (and must be resolved by the client). The URI of a re-published component will be absolute. This means that the ultimate client of the component can interact directly with the server that actually hosts the component, without having to go via the middleman server.


``````````````````````````
Non-managed JAX-RS classes
``````````````````````````


Most JAX-RS systems also have helper classes and additional root resources. The `RestComponents` class allows these to be registered. As a result, there is no need for JAX-RS annotation scanning or JAX-RS specific configuration in many cases.

Helper classes include JAX-RS conversions and filters, such as the supplied `FudgeObjectBinaryConsumer` and `HttpMethodFilter`. These must be registered as helper singletons using `publshHelper()`.

Non-managed root resources are any other JAX-RS resources that are not based on the component model. Examples might be classes to produce the website, such as `WebSecuritiesResource`. These may be registered as singletons, or using the `RestResourceFactory` factory if per-request JAX-RS resources are required. Use the `publshResource()` method.


~~~~~~
Detail
~~~~~~



*  understand the design of the `Configuration system </confluence/DOC/OpenGamma-Platform-Documentation/Getting-Started/Configuration-Guide/Component-based-Configuration/Configuration-system/index.rst>`_ 


*  read the details of the `Configuration format </confluence/DOC/OpenGamma-Platform-Documentation/Getting-Started/Configuration-Guide/Component-based-Configuration/Configuration-format/index.rst>`_ 


*  see information on OpenGamma `Configuration conventions </confluence/DOC/OpenGamma-Platform-Documentation/Getting-Started/Configuration-Guide/Component-based-Configuration/Configuration-conventions/index.rst>`_ 

