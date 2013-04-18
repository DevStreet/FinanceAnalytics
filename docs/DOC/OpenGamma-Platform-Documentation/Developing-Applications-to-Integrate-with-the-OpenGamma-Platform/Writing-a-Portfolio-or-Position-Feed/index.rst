title: Writing a Portfolio or Position Feed
shortcut: DOC:Writing a Portfolio or Position Feed
---
The OpenGamma Platform contains database-based *masters* that store portfolios, positions, securities and other key objects. Use of these masters is optional, however few alternate systems provide the same support for versions and corrections.


...................
Importing positions
...................


The OpenGamma position database is a relatively simple SQL structure wrapped in Java code by the `PositionMaster` interface and `DbPositionMaster` implementation. The master supports seven basic operations:

*  get - by unique identifier, or object identifier and version-correction co-ordinates


*  add - add a new stored object


*  update - update the stored object, creating a new version


*  delete - logical delete


*  correct - update the stored object, creating a new correction


*  search - searches for positions


*  history - retrieves the history of a single position


The OpenGamma database implementation is setup such that it will never delete data. Each new version or correction creates a new row in the database. Each row is stored using four instants (timestamps):

*  the start instant (inclusive) for the version


*  the end instant (exclusive) for the version, which is a maximum value for the latest version


*  the start instant (inclusive) for the correction


*  the end instant (exclusive) for the correction, which is a maximum value for the latest correction


These four instants, when managed correctly, provide a single version timeline, with a single correction timeline within each version.

To import a position, simply follow these steps:

*  create a `ManageablePosition` object containing the imported data


*  wrap it in a `PositionDocument`


*  obtain a `PositionMaster`


*  add the document to the master


The add method returns the added document, which is what would be retrieved by a get method at that point in time. It contains the unique identifier and the four instants.


