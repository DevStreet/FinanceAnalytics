title: Function Blacklisting
shortcut: DOC:Function Blacklisting
---
........
Overview
........


Function blacklisting allows the use of certain functions from a repository to be suppressed when they are known to cause failures. They are either not selected for graph construction or exist in a dependency graph but are not executed. This ensures that badly written functions cannot systematically cause process restarts or outages at all available calculation nodes as failed jobs get retried. System resources then remain available for other executing views and any other components of a view that contains such a fault.

A function blacklist contains rules which match items that are to be suppressed. For example a rule might identify any application of a named function, any function applied to a named target, or an exact node in a dependency graph. Rules have an activation period (also referred to as a time-to-live in the source code) after which it is removed from the blacklist and its effects cease. The system allows multiple blacklists to exist, each may be used for different suppression purposes. For example, one might suppress use of functions for graph construction, another might suppress their general execution, and other might suppress execution at a specific calculation node.

A function blacklisting policy defines how rules are constructed and put into a function blacklist. When a job item fails, policy entries define which aspects of that item any new rules should match and their activation periods. For example, a policy entry might create a rule to match on the function identifier and target of a failed job item that applies for the next hour. A policy can be defined as a configuration document accessed by `ConfigDBFunctionBlacklistPolicySource`, defined programmatically, or from Spring via `FunctionBlacklistPolicyFactoryBean`. The system allows multiple function blacklisting policies to exist. Different function blacklists may be updated by different policies or the policies used may depend on how or where the job item fault was detected.

...................
Queries and updates
...................


A function blacklist query point (using an instance of the `FunctionBlacklistQuery` interface) is a point at which the rules currently in a function blacklist are checked against a function, function application, or dependency graph node. Below are the query points that are part of the default OpenGamma system:


*  **Dependency graph construction**. Consideration of functions for dependency graph construction is suppressed, possibly allowing a lower priority alternative implementation to be selected.


*  **Dependency graph execution**. Nodes are excluded from the sub-graph submitted for execution on each view cycle. Jobs produced for execution will not contain the excluded functions.


*  **Remote calculation node communication**. Excluded job items are suppressed before the job is sent to a remote node. A "no-op" function is inserted in the place of the suppressed item. <superscript>1</superscript>


*  **Job item execution**. Excluded job items are not executed. <superscript>1</superscript>


<superscript>1</superscript> These query points may be constructed on a per-node basis allowing per-node function blacklists to be queried.

A function blacklist maintenance point (using an instance of the `FunctionBlacklistMaintainer` interface) is a point at which job item failures can be detected and function blacklists may be updated based on the relevant policies. Below are the maintenance points that are part of the default OpenGamma system:


*  **Job item execution**. Job items are reported if exceptions are thrown by their function invoker. JVM crashes caused by native code may not be detected or reported. <superscript>2</superscript>


*  **Remote calculation node communication**. Failed job items from the node are reported before notification of the failure to the main job dispatcher. <superscript>2</superscript>


*  **Job dispatch**. Any jobs that could not be successfully invoked on a calculation node are reported.


<superscript>2</superscript> These maintenance point may be constructed on a per-node basis allowing per-node function blacklists to be updated.

................
Job resubmission
................


Normally an execution job contains multiple job items and may include a "tail" of other jobs that will execute afterwards at the same node. This is to make efficient use of the "private" value caches held at each calculation node and reduce network traffic overhead. When a calculation node failure is observed, the offending job item must be identified before it can be reported to a function blacklist.

After an initial failure, a job may be retried one or more times in its entirety (see the `maxJobAttempts` property of `JobDispatcher`). When retried, the job will be resubmitted to the next available `JobInvoker`. If a job fails twice at the same remote calculation node, or the retry limit is met, it will be partitioned into smaller jobs to allow the faulty job item(s) to be identified.

At the first iteration of job partitioning any "tail" jobs are broken into discreet jobs that may be dispatched in the correct sequence to any of the available job invokers. The job(s) containing the fault item(s) can then be identified. These are in turn divided into two and the two halves submitted (in the correct order) for execution. This process repeats with any failing job until one or more jobs containing individual faulting job items can be identified. These items are then reported to the function blacklists.

Note that the resubmission of jobs to identify the failing job items will mean the failing items may be sent to multiple calculation nodes triggering multiple failures. This can be reduced by limiting the initial number of retries and/or setting a maximum limit on job size. Dividing calculation jobs into smaller fragments will also give a lower performance than the original job as any intermediate values that would normally be held in a node's "private" cache must be written to the "shared" cache.

........
Examples
........


~~~~~~~~~~~~~~~~~~~~~~~~~~
Varying activation periods
~~~~~~~~~~~~~~~~~~~~~~~~~~


A policy may contain multiple rules with different activation periods. For example:


#  Matches on function identifier for 5 minutes


#  Matches on function identifier and computation target for 30 minutes


#  Matches on function identifier, computation target, and function inputs for 24 hours


Broad rules such as the first have a short activation period to allow the system to recover from the immediate effects of identifying the failure as calculation nodes may be restarting and the capacity of the system may be noticeably reduced. More specific rules have longer activation periods to suppress the most likely cause of the failure for much longer.

~~~~~~~~~~~~~~~~~~~~~~~~~~
Global execution blacklist
~~~~~~~~~~~~~~~~~~~~~~~~~~


The simplest configuration is to use a single execution blacklist. This is queried from the dependency graph executor and updated by the job dispatcher. The execution blacklist is created and placed into the `FunctionCompilationContext` at system initialisation. The job dispatcher is then configured to update this, for example from Spring:



.. code::

    <bean ... class="com.opengamma.engine.view.calcnode.JobDispatcher">
      ...
      <!-- Update the graph execution blacklist with partial node matches for 5 minutes -->
      <property name="functionBlacklistMaintainer">
        <bean class="com.opengamma.engine.function.blacklist.DefaultFunctionBlacklistMaintainer">
          <constructor-arg ref="executionBlacklist" /> <!-- execution blacklist set in the compilation context -->
          <constructor-arg>
            <bean class="com.opengamma.engine.function.blacklist.FunctionBlacklistPolicyFactoryBean">
              <property name="defaultEntryActivationPeriod" value="300" />
              <property name="partialNode" value="true" />
            </bean>
          </constructor-arg>
        </bean>
      </property>
      ...
    </bean>




~~~~~~~~~~~~~~~~~~~~~~~~
Node specific blacklists
~~~~~~~~~~~~~~~~~~~~~~~~


Some configurations may benefit from using execution blacklists that are specific to individual remote calculation nodes or groups of similar nodes. This is done via the `RemoteNodeServer` class which is passed objects that will create the blacklist query and maintenance points based on the host identifier of each connecting calculation node. For example from Spring:



.. code::

    <bean ... class="com.opengamma.engine.view.calcnode.RemoteNodeServer">
      ...
      <!-- Update a host specific list for 5 minutes -->
      <property name="blacklistUpdate">
        <bean class="com.opengamma.engine.view.calcnode.RemoteNodeServer$FunctionBlacklistMaintainerProviderBean">
          <property name="blacklistProvider" ref="blacklists" /> <!-- eg an InMemoryFunctionBlacklistProvider -->
          <property name="blacklistPrefix" ref="REMOTE_NODE_" />
          <property name="blacklistPolicy">
            <bean class="com.opengamma.engine.function.blacklist.FunctionBlacklistPolicyFactoryBean">
              <property name="defaultEntryActivationPeriod" value="300" />
              <property name="partialNode" value="true" />
            </bean>
          </property>
        </bean>
      </property>
      <!-- Query a remote host specific blacklist -->
      <property name="blacklistQuery">
        <bean class="com.opengamma.engine.view.calcnode.RemoteNodeServer$FunctionBlacklistQueryProviderBean">
          <property name="blacklistProvider" ref="blacklists" /> <!-- eg a InMemoryFunctionBlacklistProvider -->
          <property name="blacklistPrefix" ref="REMOTE_NODE_" />
        </bean>
      </property>
      ...
    </bean>




~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Mixed node specific and global blacklists
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


Combining both of the above techniques could be used to add rules to the global function blacklist for a brief period of time, but add rules to the directly affected node(s) for a much longer period.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Graph construction blacklist
~~~~~~~~~~~~~~~~~~~~~~~~~~~~


The graph building algorithm queries a blacklist held in the `FunctionCompilationContext` when selecting functions for the dependency graph. This blacklist can be updated from any of the maintenance points, however doing so will only affect new view compilations and will not affect any currently executing views or jobs.

Future versions of the OpenGamma Platform will support partial dependency graph reconstruction (`PLAT-474 <http://jira.opengamma.com/browse/PLAT-474>`_ ). There may be multiple implementations of a function at differing priority levels in the repository. If the higher priority one causes an execution failure and is added to the graph construction blacklist then a dependency graph rebuild could be triggered and a lower priority function selected in its place.
