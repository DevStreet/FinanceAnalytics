title: Using a Master
shortcut: DOC:Using a Master
---
The OpenGamma Platform *masters* store portfolios, positions, securities and other key objects. Most masters have a standard API design, discussed here. to aid the overall usability of the API.


........
Overview
........


The master interfaces are normal Java interfaces and can be implemented in any way. Typically however, they are ultimately implemented by a database.

The standard masters store *documents*, which are wrappers around the core object itself. The document stores meta-data about the object, such as the version and correction information. The basic features of a standard master are:


*  Search the documents


*  Get a document by unique identifier


*  Get a document by object identifier and version-correction


*  Add a new document


*  Update a document


*  Apply a correction to a document


*  Remove a document (logical deletion, not physical deletion)



.........
Searching
.........


The search on most masters follows a standard pattern. The method takes a `FooSearchRequest` object and returns a `FooSearchResult`. Each search request is different and specific to the type of the master. However most are similar in nature.

A typical search request object is a classic bean consisting of a number of properties. Each property is essentially an independent database filter, effectively via a database `WHERE a` clause. Setting two properties applies two filters to the result, effectively via a database `WHERE a AND b` clause, and so on. If a property is null, then the filter is not applied.



.. code::

    SecuritySearchRequest req = new SecuritySearchRequest();
    req.setName("*OpenGamma*");
    req.setSortOrder(SecuritySearchSortOrder.NAME_ASC);
    SecuritySearchResult res = master.search(req);





`````````````````
Identifier search
`````````````````


One property commonly used is `IdentifierSearch`. This contains a set of `Identifier` objects and a type to indicate how the filter should work. The type has four options:

*  Exact match - the database row must contain exactly the same set of identifiers as the query, no more and no less


*  All - the database row will match if it contains all the identifiers in the query. It may contain additional identifiers


*  Any - the database row will match if any of the database identifiers match any of the query identifiers (normally the default)


*  None - the database row wil match if it conatins none of the query identifiers


``````````````````
Version correction
``````````````````


Another common property is the `VersionCorrection`. This is used to identify a single co-ordinate in the version history and corrections of each version to search at. If it is not explicitly set it will typically default to the latest version and correction.

`````````
Wildcards
`````````


The description of a text search field may refer to the ability to use wildcards. The accepted wildcards are `*` for zero to many characters and `?` for exactly one character.


.......
Getting
.......


The primary key of the master is the unique identifier. This uniquely references a single version/correction of the document.

The master may also be queried using the object identifier and a `VersionCorrection` instance that specifies the time co-ordinates to search at. Special values allow the version-correction search to be for the "latest".



.. code::

    SecurityDocument doc = master.get(uniqueId);





......
Adding
......


To add an object to the master requires three steps:


*  create the object


*  wrap it in a document


*  call the master's add method


The document will typically be an empty wrapper on input to the master. The key fields, the unique identifier and version/correction instants, are controlled by the master and specified in the result.

There is no way in the API to control the unique identifier that will be used. Similarly, the version and correction instants will be whatever the master determines the current instant to be. Note that this does not allow the loading of data where the effective version is in the past. This restriction is deliberate, as the primary reason for the version-correction system is to permit accurate restatements, something that is not possible if history is changed.

It would however be appropriate to load data in the past when initially setting up a new OpenGamma system. This can be achieved indirectly by manually creating an instance of the database master implementation, such as `DbSecurityMaster`, and setting the `TimeSource` to the date in the past that insertion should be at. Note that it is vital that the time source is not changed on a master that is shared!



.. code::

    ManageableSecurity sec = new ManageableSecurity();
    ... setup security ...
    SecurityDocument addedDoc = master.add(new SecurityDocument(sec));





.......................
Updating and Correcting
.......................


Updates and corrections are similar. In both cases, the object within the document is changed, and then the document is sent to the master. The unique identifier and version-correction will be updated.



.. code::

    SecurityDocument originalDoc= master.get(id);
    ... alter security ...
    SecurityDocument updatedDoc = master.update(originalDoc);





