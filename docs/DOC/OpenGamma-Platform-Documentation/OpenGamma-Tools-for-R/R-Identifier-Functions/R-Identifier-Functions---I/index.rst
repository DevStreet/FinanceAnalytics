title: R Identifier Functions - I
shortcut: DOC:R Identifier Functions - I
---
unversioned.Identifier

......................
unversioned.Identifier
......................


By removing the version suffix, a unique identifier becomes an object identifier referencing the latest version of the object. For example a view using a versioned identifier for a snapshot will reference that exact version whereas one using the unversioned object identifier can be set to "tick" whenever that snapshot changes as it always references the latest version of the snapshot.



+------------+----------+-------------------+
| Parameter  | Required | Description       |
+============+==========+===================+
| identifier | Yes      | Unique identifier |
+------------+----------+-------------------+



