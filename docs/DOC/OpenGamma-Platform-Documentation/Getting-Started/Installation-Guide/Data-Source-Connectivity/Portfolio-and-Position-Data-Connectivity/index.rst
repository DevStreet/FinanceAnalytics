title: Portfolio and Position Data Connectivity
shortcut: DOC:Portfolio and Position Data Connectivity
---
Command line tools and classes for importing and exporting portfolios are provided with OpenGamma. They provide users with the ability to populate a portfolio with positions that are laid out in a CSV or Excel sheet, as well as the facility to save the positions in an existing portfolio into a CSV or Excel sheet. Each sheet normally contains positions in one security type. The sheet columns that are produced when saving, and expected when loading, are determined by that security type, which is specified on the command line.&nbsp;
Additionally, the provided tools produce and recognise a multiple asset type input format, which bundles several of the above-mentioned CSV sheets each containing positions in a different security type into a standard ZIP archive. In this case the ZIP archive’s folder structure encodes the portfolio’s hierarchical structure, and the security types involved are automatically inferred.


A single row normally represents a single position, and incorporates security information which is used to (re)create the security while loading a portfolio if it cannot be located in the security master. Alternatively, the tools can be instructed to remove any pre-existing securities, positions and portfolios that match and recreate them using information found in the sheet exclusively.


Scripts to load a portfolio (load-portfolio), save a portfolio (save-portfolio) and to create template files with the required column headers (create-portfolio-template) can be found in the scripts folder in OG-BloombergExample as well as OG-Integration. Alternatively, the tool classes may be accessed from within OG-Integration as&nbsp;com.opengamma.integration.tool.portfolio.PortfolioLoaderTool, com.opengamma.integration.tool.portfolio.PortfolioSaverTool and&nbsp;
com.opengamma.integration.tool.portfolio.PortfolioTemplateCreationTool.

