title: Adding Remote Calculation Nodes
shortcut: DOC:Adding Remote Calculation Nodes
---
Remote calculation nodes allow calculations within OpenGamma to be distributed across multiple physical servers to reduce the time taken to produce a calculation cycle result.

........
Overview
........


A remote calculation node refers to a process containing one or more `com.opengamma.engine.view.calcnode.SimpleCalculationNode` instances that will participate in calculations for one or more view processors. These nodes are managed by a `com.opengamma.engine.view.calcnode.RemoteNodeClient` that will connect to a `com.opengamma.engine.view.calcnode.RemoteNodeServer` to receive jobs from the associated view processor.

A remote node requires a `com.opengamma.engine.function.FunctionExecutionContext` and `com.opengamma.engine.function.FunctionCompilationContext` that are compatible with those of the view processor they are receiving jobs from and of any other calculation nodes such as those local to the view processor. For example, the context used by the view processor and its local calculation nodes might use a `com.opengamma.core.position.PositionSource` implementation that connects to a JDBC database while the context used by the remote nodes has a `com.opengamma.engine.position.rest.RemotePositionSource` to communicate over the REST APIs with the underlying data.

A remote node has a local computation cache for intermediate values. In the example configuration this is an on-disk store. The location of a shared computation cache is also needed for values that are to be shared with calculation nodes from other processes, terminal values produced, and any initial live data inputs. The shared computation cache may either be on the same host as the view processor and its local calculation nodes, or could be on a dedicated host.

..........................
Spring based configuration
..........................


A remote calculation node is typically managed through a Spring configuration file. This can be held centrally on any host running an HTTP stack. When a remote calculation node process starts, it only needs the URL of this configuration file. It will periodically query the file for changes and terminate the process if changes are found. If the process is configured to run in a loop by the host operating system, changes to the calculation node configurations can be easily propogated to all nodes by changing the central file.

The example configuration file is in `OG-Web/web-engine/calcNode/example.xml`. This contains the structural information to construct the objects needed by the remote calculation node. There are additional tunable parameters in the `OG-Web/web-engine/calcNode/example.properties` file - for example:



::

    opengamma.engine.configuration.url=http://name-of-a-server-running-the-OpenGamma-REST-APIs:8080/jax/configuration/0
    opengamma.engine.calcnode.localdatastore=LocalBerkeleyDBBinaryDataStore
    opengamma.engine.calcnode.nodespercore=1.2
    opengamma.engine.calcnode.scalinghint=0.0




The `opengamma.engine.configuration.url` property points to a Fudge message resource that contains members for the location of all the components needed by the remote calculation node - for example:



+---------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------+
| Message member                  | Description                                                                                                                               |
+=================================+===========================================================================================================================================+
| positionSource                  |  `com.opengamma.transport.jaxrs.UriEndPointDescriptionProvider` identifying the REST API of a suitable `PositionSource`                   |
+---------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------+
| securitySource                  |  `UriEndPointDescriptionProvider` identifying the REST API of a suitable `SecuritySource`                                                 |
+---------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------+
| functionRepositoryConfiguration |  `UriEndPointDescriptionProvider` identifying the REST API of a suitable `RepositoryConfigurationSource`                                  |
+---------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------+
| ...                             |  `UriEndPointDescriptionProvider` describing any other REST APIs needed to construct the execution context                                |
+---------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------+
| remoteCalcNode                  |  `com.opengamma.engine.view.calcnode.ConfigurationResource` describing the IP addresses and ports of the job dispatcher and shared cache  |
+---------------------------------+-------------------------------------------------------------------------------------------------------------------------------------------+



The `opengamma.engine.calcnode.localdatastore` property is a folder that the local computation cache should be kept in. If this is a relative path, it is placed under the system temporary folder.

The `opengamma.engine.calcnode.nodespercore` property determines how many node instances (i.e. threads) to create for running calculations as a value per core. Parts of every calculation cycle are spent on I/O, so a value greater than one can often give a higher total throughput. The optimal value depends on the characteristics of the network, portfolios used, and function library.

The `opengamma.engine.calcnode.scalinghint` property is used to control the function cost heuristics reported by remote node processes as each tries to automatically determine its performance capabilities relative to others. Setting to `0.0` disables this automatic tuning which is appropriate when the remote calculation nodes are running on similar hardware. Values nearer to `1.0` enable automatic calibration - refer to the documentation of `com.opengamma.engine.view.calcnode.stats.FunctionInvocationStatisticsSender` for more information.

........................................................
Creating a `opengamma.engine.configuration.url` resource
........................................................


A suitable resource for the location of various system components is typically created with a bean on a server running all or part of the OpenGamma HTTP stack. For example, something like the following bean could be used:



.. code::

    <!-- Distributed component configuration data -->
      <bean id="configurationResource" class="com.opengamma.util.rest.ConfigurationResource">
        <constructor-arg ref="fudgeContext" />
        <constructor-arg>
          <map>
            <entry key="0">
              <map>
                <entry key="positionSource">
                  <bean class="com.opengamma.transport.jaxrs.UriEndPointDescriptionProviderFactoryBean">
                    <property name="local" value="/jax/positionSource/" />
                    <property name="port" value="${jetty.port}" />
                  </bean>
                </entry>
                <entry key="securitySource">
                  <bean class="com.opengamma.transport.jaxrs.UriEndPointDescriptionProviderFactoryBean">
                    <property name="local" value="/jax/securitySource/0/" />
                    <property name="port" value="${jetty.port}" />
                  </bean>
                </entry>
                <entry key="functionRepositoryConfiguration">
                  <bean class="com.opengamma.transport.jaxrs.UriEndPointDescriptionProviderFactoryBean">
                    <property name="local" value="/jax/repositoryConfigurationSource/0/" />
                    <property name="port" value="${jetty.port}" />
                  </bean>
                </entry>
                <!-- ... other entries required for remote nodes to configure their contexts -->
                <entry key="remoteCalcNode" value-ref="calcNodeSocketConfig" />
              </map>
            </entry>
            <!-- ... other entries if there are different configuration sets with different position/security sources etc -->
          </map>
        </constructor-arg>
      </bean>




This requires that a `calcNodeSocketConfig` bean be created that references the server end points the remote calculation nodes will connect to. Refer to the documentation for `ConfigurationResource` for more details. If the resource is on the same host as the view processor, this can be created with code like the following:



.. code::

    <bean id="calcNodeCacheServer" class="com.opengamma.transport.socket.ServerSocketFudgeConnectionReceiver">
        <constructor-arg>
          <bean class="com.opengamma.engine.view.cache.ViewComputationCacheServer">
            <constructor-arg ref="computationCacheSource" />
          </bean>
        </constructor-arg>
        <constructor-arg ref="fudgeContext" />
      </bean>
      <bean id="calcNodeQueryServer" class="com.opengamma.transport.socket.ServerSocketFudgeRequestDispatcher">
        <constructor-arg ref="viewProcessorQueryReceiver" />
        <constructor-arg ref="fudgeContext" />
      </bean>
      <bean id="calcNodeJobServer" class="com.opengamma.transport.socket.ServerSocketFudgeConnectionReceiver">
        <constructor-arg ref="fudgeContext" />
        <constructor-arg>
          <bean class="com.opengamma.engine.view.calcnode.RemoteNodeServer">
            <constructor-arg ref="computationJobDispatcher" />
            <constructor-arg>
              <bean factory-bean="demoComputationCacheSource" factory-method="getIdentifierMap" />
            </constructor-arg>
            <constructor-arg>
              <bean class="com.opengamma.engine.view.calcnode.stats.FunctionCosts" />
            </constructor-arg>
            <constructor-arg ref="functionCompilationContext" />
          </bean>
        </constructor-arg>
      </bean>
      <bean id="calcNodeSocketConfig" class="com.opengamma.engine.view.calcnode.ConfigurationResource">
        <property name="cacheServer" ref="calcNodeCacheServer" />
        <property name="queryServer" ref="calcNodeQueryServer" />
        <property name="jobServer" ref="calcNodeJobServer" />
      </bean>




Where `computationCacheSource`, `viewProcessorQueryReceiver`, `computationJobDispatcher`, and `functionComputationContext` are the items used to construct the view processor and local calculation node set.

..................................
Starting a remote calculation node
..................................


Once the configuration XML document is correct for an installation, and the elements of the document pointed to by the configuration URL describe valid IP addresses, ports and URLs for the components of the system, one or more calculation nodes processes can be started.

Start `com.opengamma.engine.view.calcnode.CalculationNodeProcess` with the URL of the configuration.xml document described above. For example by typing:



::

    java -cp _OpenGamma-classpath_ com.opengamma.engine.view.calcnode.CalculationNodeProcess http://server-where-the-configuration-is-held:8080/demoCalcNode/configuration.xml




A typical installation may wrap the command above in a loop so that if the process terminates (for example if a change to the configuration document is detected) it will be restarted automatically. This will provide a level of resilience to the OpenGamma installation - if the remote calculation node fails, it will restart and attempt to rejoin the view processor (any jobs it was executing at failure will be sent by the view processor to other nodes when the failure is detected).

Alternatively, depending on the deployment environment, a custom wrapper could be written around the supplied `CalculationNodeProcess` class that determines the address of the configuration document programmatically.

Once the remote calculation node process is started it will connect to the job dispatcher and be ready to receive work items. If a computation cycle already executing has job elements that have not already been dispatched to calculation nodes, these may be sent to the new process. If the job elements have already been dispatched the new process will not receive any work until that cycle has completed execution and the next starts.

..................................
Stopping a remote calculation node
..................................


To stop a remote calculation node, kill the process. The view processor it has connected to will detect the dropped connection and any uncompleted jobs will be sent to one or more of the remaining nodes.
