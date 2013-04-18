title: Monitoring an OpenGamma Installation
shortcut: DOC:Monitoring an OpenGamma Installation
---
...
JMX
...


The OpenGamma Platform is JMX enabled. In order to use this you will need to enable JMX when running the server. This can be done either for `local access <http://download.oracle.com/javase/1.5.0/docs/guide/management/agent.html#local>`_  or `remote access <http://download.oracle.com/javase/1.5.0/docs/guide/management/agent.html#remote>`_ .

Once JMX is enabled you can monitor the server using `JConsole <http://java.sun.com/developer/technicalArticles/J2SE/jconsole.html>`_ :



.. code::

    JDK_HOME/bin/jconsole




As well as access to the standard JVM information available, OpenGamma publishes MBeans showing detailed information about the system state.
