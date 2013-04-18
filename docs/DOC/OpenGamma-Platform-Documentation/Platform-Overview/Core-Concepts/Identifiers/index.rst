title: Identifiers
shortcut: DOC:Identifiers
---
The OpenGamma Platform is a weakly-connected system (where connections between major entities are not part of any direct foreign key relationship in a database) that uses lazy loading (where entities are only loaded when required). The way the system supports this is through a complex identification system that operates across a wide range of entities.

The simplest way to view this is as a triplet of concepts:

*  an **ExternalId** is a scoped string which identifies a data element;


*  an **ExternalIdBundle** is a collection of **ExternalId**, where each individual **ExternalId** can be used to identify the data element;


*  a **UniqueId** is an identifier issued and managed by a component of the OpenGamma Platform and includes a version.


*  an **ObjectId** is an identifier issued and managed by a component of the OpenGamma Platform but does not include a version.  It identifies a *versioned object* and remains the same even when the version changes.


Just a reminder: In the 0.9.0 and prior releases, these classes had different names:
* *Identifier* has become *ExternalId*
* *IdentifierBundle* has become *ExternalIdBundle*
* *UniqueIdentifier* has become *UniqueId*
* *ObjectIdentifier* has become *ObjectId*

We think these new names are much quicker to comprehend and are not planning on renaming these key classes again.


The following sections explore these concepts in more detail.

....................
External Identifiers
....................


Within the OpenGamma platform, it is often necessary to identify an item, such as a Security, Position or Portfolio. As an industry, there are many different approaches to identifiers, especially in securities. For example, CUSIP, ISIN, tickers, and "unique identifiers" from the major data providers are all common identifiers for Securities. However, there is no one approach that has been uniformly adopted everywhere. Thus OpenGamma allows existing approaches to still be used.

The first key class is `ExternalId`. This is intended to wrap externally provided third-party identifiers, such as ISIN or tickers. It consists of a *scheme* and a *value*.

An OpenGamma identification scheme is nothing other than a named universe from which identifiers are expected to have some reasonable type of uniqueness constraint. While OpenGamma is capable of handling all common real-world identification schemes, the OpenGamma Platform doesn't either limit or interpret the schemes in any way. Thus it is possible at runtime to expand the universe of schemes in an installation to support things like primary keys into existing data sources to aid in data reconciliation.

The value is the identifier itself, while the scheme is a description of the type of identifier, such as "CUSIP". A standard string representation uses a tilde to separate the two parts:



+----------------------+--------------------+-------------------------------------+---------------------------------+
| Scheme               | Identifier         | Standard String Representation      | Notes                           |
+======================+====================+=====================================+=================================+
|  `BLOOMBERG_TICKER`  |  `US0003M Curncy`  |  `BLOOMBERG_TICKER~US0003M Curncy`  | 3 Month USD LIBOR rate          |
+----------------------+--------------------+-------------------------------------+---------------------------------+
|  `RIC`               |  `USDAM3L1Y=`      |  `RIC~USDAM3L1Y`                    | 1 Year USD IR swap rate (AM/3M) |
+----------------------+--------------------+-------------------------------------+---------------------------------+
|  `ISIN`              |  `GB0031274896`    |  `ISIN~GB0031274896`                | Marks & Spencer Group PLC       |
+----------------------+--------------------+-------------------------------------+---------------------------------+



...........................
External Identifier Bundles
...........................


The second key class is `ExternalIdBundle`. This is a collection of `ExternalId` objects that all represent the same underlying conceptual object. For example, the same equity will be represented by a Bloomberg ticker and a Reuters RIC. The bundle allows the multiple ways of representing the same object to be stored, allowing later lookup to be more flexible.

Both `ExternalId` and `ExternalIdBundle` are *weak* references. This is because the underlying system that defines them is not typically guaranteed to return the same value over time. Sometimes, the values are location dependent as well.

Where there are contradictions or overlaps (nothing in the OpenGamma Platform requires that `ExternalId` values are unique in any way, or that `ExternalIds` are not shared amongst different `ExternalIdBundles`, there is a flexible system of Resolvers capable of providing the data element(s) desired when there are overlapping identifiers.

.............................
Object and Unique Identifiers
.............................


To provide more certainty for the OpenGamma Platform, two more classes exist - `ObjectId` and `UniqueId`. An `ObjectId` is an identifier for a conceptual object within the OpenGamma system. This must reliably and uniquely represent the underlying data. It must not be reused over time for a different conceptual object.

A `UniqueId` layers a version on top of an `ObjectId`. The version allows the same conceptual object to be viewed at multiple points in time. Versioning is currently optional, but strongly recommended. The version itself is just a string, and the contents of the string should not be interpreted. For example, there is no guarantee that a version string of "12" is later than a version string of "6" or that there are 5 versions in-between.



.. code::

    DbSec~12345      // an ObjectIdentifier
      DbSec~12345~2    // a UniqueIdentifier based on the object identifier




In general, it is *highly* recommended that applications use the built-in `ObjectId` and `UniqueId` system. In addition, these values are **not** considered to be end-user friendly, and well-written user interfaces will hide these from end users except in debugging situations.

A common misconception about `UniqueId` and `ObjectId` is that we use them to reference across our subsystems.  In general we *do not* do this, we use `ExternalId` or `ExternalIdBundle` to limit the coupling between our components.  This way components can be swapped out an use completely different internal primary key structures without breaking the entire system.  As a general rule a `UniqueId` or `ObjectId` should only ever be used as a fast way of referencing an object you've already resolved from query on the same `Source` or `Master`.  A good example is the coupling between historical time series and security reference data: while in theory we could store a `UniqueId` of an associated security in the security master with a given time series, to minimise coupling we instead require you to use the whole `ExternalIdBundle` to resolve the security.

