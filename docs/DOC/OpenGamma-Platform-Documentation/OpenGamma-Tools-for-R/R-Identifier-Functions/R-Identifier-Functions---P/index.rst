title: R Identifier Functions - P
shortcut: DOC:R Identifier Functions - P
---
PortfolioComponentIdentifiers

.............................
PortfolioComponentIdentifiers
.............................


Retrieves the identifiers of components (nodes, positions, trades, securities) that make up a portfolio.



+----------------------+----------+---------------------------------------------------------------------------------------------------------------------------------------+
| Parameter            | Required | Description                                                                                                                           |
+======================+==========+=======================================================================================================================================+
| portfolioIdentifier  | Yes      | The identifier of the portfolio                                                                                                       |
+----------------------+----------+---------------------------------------------------------------------------------------------------------------------------------------+
| preferredScheme      |          | When selecting identifiers from an external identifier bundle, the preferred scheme(s) to use. Omit for the local environment default |
+----------------------+----------+---------------------------------------------------------------------------------------------------------------------------------------+
| includeSecurity      |          | Whether to include security identifiers, defaults to TRUE                                                                             |
+----------------------+----------+---------------------------------------------------------------------------------------------------------------------------------------+
| includePosition      |          | Whether to include position identifiers, defaults to FALSE                                                                            |
+----------------------+----------+---------------------------------------------------------------------------------------------------------------------------------------+
| includeTrade         |          | Whether to include trade identifiers, defaults to FALSE                                                                               |
+----------------------+----------+---------------------------------------------------------------------------------------------------------------------------------------+
| includePortfolioNode |          | Whether to include node identifiers, defaults to FALSE                                                                                |
+----------------------+----------+---------------------------------------------------------------------------------------------------------------------------------------+



