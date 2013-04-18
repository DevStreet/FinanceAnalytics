title: Writing a custom Source
shortcut: DOC:Writing a custom Source
---
............
Introduction
............


The OpenGamma platform has two main levels of interfaces that provide data - the *sources* and the *masters*. A source interface contains the minimum API necessary for the engine and as such is generally read-only and simple. A master interface is primarily intended for full read-write management of a set of data, typically wrapping a database. The platform provides source implementations that wrap the matching masters, however it is possible to replace the platform code at either level. For more information on this approach, please see `Sources, Masters, and Databases </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/Core-Concepts/Sources,-Masters,-and-Databases/index.rst>`_  in the `Core Concepts </confluence/DOC/OpenGamma-Platform-Documentation/Platform-Overview/Core-Concepts/index.rst>`_  guide.

This document focuses on implementing the `SecuritySource <http://docs-static.opengamma.com/Latest%20Version/java/javadocs/com/opengamma/core/security/SecuritySource.html>`_  interface, however the principles apply to most other sources.


.....................
Implementing a source
.....................


Each source interface is unique, so for discussion let's consider the `SecuritySource`. The purpose of this source is to provide information about securities to the engine, where a security might be an equity, option, bond or anything else that a position might be held in.

`SecuritySource` has three methods providing different ways to lookup a security (see below for more details):

*  `Security getSecurity(UniqueIdentifier uid)`


*  `Collection<Security> getSecurities(IdentifierBundle bundle)`


*  `Security getSecurity(IdentifierBundle bundle)`


The first method takes a `UniqueIdentifier` which must be a unique key for the security within a running instance of OpenGamma. If the masters are used then this will typically be connected to a database primary key.

The second and third methods take an `Identifier` and a `IdentifierBundle`. These provide a weaker form of identifying a security, such as a ticker or remote identifier. A bundle is simply a collection of different `Identifier` objects that all represent the same fundamental security.

Implementing a `SecuritySource` simply involves writing the relevant Java code to implement the interface. Example implementations would be database-backed, a flat file, or an in-memory generator. Where possible, once a security has been returned for a given `UniqueIdentifier` then it should continue to be returned. The versioning component of a `UniqueIdentifier` allows different versions of the same object to be represented.

The source returns securities represented by the `Security` interface.

*  UniqueIdentifier getUniqueId()


*  String getName()


*  IdentifierBundle getIdentifiers()


*  String getSecurityType()


This defines the minimal information needed by the engine to process a security. The OG-Financial project adds classes for common concepts like equity, option, FRA and bond. However, you may implement your own hierarchy of security classes, or re-use existing code. The only requirement to work with OpenGamma is implementing these four methods.


...........
Source code
...........




.. code::

    /**
     * A source of security information as accessed by the main application.
     * <p>
     * This interface provides a simple view of securities as needed by the engine.
     * This may be backed by a full-featured security master, or by a much simpler data structure.
     */
    public interface SecuritySource {
    
      /**
       * Finds a specific security by unique identifier.
       * <p>
       * Since a unique identifier is unique, there are no complex matching issues.
       *
       * @param uid  the unique identifier, null returns null
       * @return the security, null if not found
       * @throws IllegalArgumentException if the identifier is invalid
       */
      Security getSecurity(UniqueIdentifier uid);
    
      /**
       * Finds all securities that match the specified bundle of keys.
       * <p>
       * The identifier bundle represents those keys associated with a single security.
       * In an ideal world, all the identifiers in a bundle would refer to the same security.
       * However, since each identifier is not completely unique, multiple may match.
       * To further complicate matters, some identifiers are more unique than others.
       * <p>
       * The simplest implementation of this method will return a security if it matches one of the keys.
       * A more advanced implementation will choose using some form of priority order which
       * key or keys from the bundle to search for.
       *
       * @param bundle  the bundle keys to match, not null
       * @return all securities matching the specified key, empty if no matches, not null
       * @throws IllegalArgumentException if the identifier bundle is invalid (e.g. empty)
       */
      Collection<Security> getSecurities(IdentifierBundle bundle);
    
      /**
       * Finds the single best-fit security that matches the specified bundle of keys.
       * <p>
       * The identifier bundle represents those keys associated with a single security.
       * In an ideal world, all the identifiers in a bundle would refer to the same security.
       * However, since each identifier is not completely unique, multiple may match.
       * To further complicate matters, some identifiers are more unique than others.
       * <p>
       * An implementation will need some mechanism to decide what the best-fit match is.
       *
       * @param bundle  the bundle keys to match, not null
       * @return the single security matching the bundle of keys, null if not found
       * @throws IllegalArgumentException if the identifier bundle is invalid (e.g. empty)
       */
      Security getSecurity(IdentifierBundle bundle);
    
    }





.. code::

    /**
     * A security that it may be possible to hold a position in.
     * <p>
     * A security generically defined as anything that a position can be held in.
     */
    public interface Security extends UniqueIdentifiable {
    
      /**
       * Gets the unique identifier of the security.
       *
       * @return the identifier, not null
       */
      UniqueIdentifier getUniqueId();
    
      /**
       * Gets the name of the security intended for display purposes.
       *
       * @return the name, not null
       */
      String getName();
    
      /**
       * Gets the bundle of identifiers that define the security.
       *
       * @return the identifiers defining the security, not null
       */
      IdentifierBundle getIdentifiers();
    
      /**
       * Gets the text-based type of this security.
       *
       * @return the text-based type of this security
       */
      String getSecurityType();
    
    }



