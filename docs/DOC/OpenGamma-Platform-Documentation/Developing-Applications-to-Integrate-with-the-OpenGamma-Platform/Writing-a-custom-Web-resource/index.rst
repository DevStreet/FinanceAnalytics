title: Writing a custom Web resource
shortcut: DOC:Writing a custom Web resource
---
The OpenGamma Platform website is intended to be flexible so that it can be extended with new pages. This document explains how to do this.

........
Overview
........


The OpenGamma Platform uses JAX-RS (JSR-311) to drive the website with Jersey as the JAX-RS implementation. This approach allows the website to be extended by developers using the Platform. There are two main steps - writing the resource(s) and integrating the root resource into the OpenGamma Platform JAX-RS instance.

Note that this document covers the addition of a simple HTML pages that either integrate with the "green screen" pages or are completely standalone. The main web GUI is a Javascript application that uses RESTful calls to access a JSON representation of the data. Editing the Javascript GUI itself is not covered here, however the server-side JSON output is done in exactly the same way as the HTML output described below.


........
Resource
........


JAX-RS applications are built up using *resources*. Each resource represents one or more parts of a URL. Thus the `WebAllHistoricalTimeSeriesResource </javadoc/index.html?com/opengamma/web/historicaltimeseries/WebAllHistoricalTimeSeriesResource.html>`_ resource handles the URL `/jax/timeseries`. To extend the website the easiest approach is to add another resource.

Writing a JAX-RS resource is relatively easy. Simply write a class and add the annotations (see a JAX-RS tutorial). The class should have an `@Path` annotation that represents the path where the resource will be located. The other method-based annotations will specify the RESTful method (GET/POST/PUT/DELETE), the relative path and the media types (HTML/JSON/etc).

Note that a resource may create another resource. In this situation, the base resource is known as the *root resource*.

The resource may be a singleton or per-request instance-based. Singletons should typically extend `AbstractWebResource </javadoc/index.html?com/opengamma/web/AbstractWebResource.html>`_, while per-request should typically extends `AbstractPerRequestWebResource </javadoc/index.html?com/opengamma/web/AbstractPerRequestWebResource.html>`_. Note that subclassing is optional, but provides a convenient starting point.

~~~~~~~~~~
Freemarker
~~~~~~~~~~


The RESTful resources have access to `Freemarker <http://freemarker.sourceforge.net/>`_  to create HTML and/or JSON. Freemarker is a powerful template engine that can convert Java objects to text.

The Freemarker integration that is built in works by being passed a single data bean. By convention, OpenGamma uses a `FlexiBean`, which is a wrapper around a `Map`. The data that should be used to produce the page is added to the bean and Freemarker is invoked. The Freemarker system can use all the data in the passed in bean, including beans within beans - see the Freemarker documentation for more details:



.. code::

    FlexiBean out = getFreemarker().createRootData();
     out.put("key1", value1);
     out.put("key2", value2);
     return getFreemarker().build("my-freemarker-template.ftl", out);




The `getFreemarker()` method is available on `AbstractPerRequestWebResource` and returns a `FreemarkerOutputter </javadoc/index.html?com/opengamma/web/FreemarkerOutputter.html>`_ which is stored in the `ServletContext`. If your resource does not extend `AbstractPerRequestWebResource` then it will be necessary to create it using `new FreemarkerOutputter(servletContext)`.



.............
Configuration
.............


This section assumes use of the `Component-based Configuration </confluence/DOC/OpenGamma-Platform-Documentation/Getting-Started/Configuration-Guide/Component-based-Configuration/index.rst>`_ .

The configuration is built up from a list of factories. Each factory specified in the configuration INI file is loaded and run. The `WebsiteBasicsComponentFactory </javadoc/index.html?com/opengamma/component/factory/web/WebsiteBasicsComponentFactory.html>`_ class is the main one used to setup the website (although there are others in the same package). The key part of the factory is where it adds the root resource to the `RestComponents </javadoc/index.html?com/opengamma/component/rest/RestComponents.html>`_ instance. This can be done in two ways.

The first approach is used when the resource you are adding is a singleton. Here a single instance of the resource is created by the factory and registered/published:



.. code::

    repo.getRestComponents().publishResource(new WebMySingletonResource());




The second approach is used when the resource you are adding is request-scoped such that a new instance must be created for every request. Here it is necessary to register a `RestResourceFactory` that will create the instance of the resource on demand, per-request:



.. code::

    JerseyRestResourceFactory rrf = new JerseyRestResourceFactory(WebMyRequestScopedResource.class,
                  getParameter1(), getParameter2(), ...);
     repo.getRestComponents().publishResource(rrf);




Note that the factory is Jersey specific. Note also that if the request-scoped resource requires constructor parameters, then these must be known when the factory runs.


