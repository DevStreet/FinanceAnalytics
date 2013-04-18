title: Configuring the UI
shortcut: DOC:Configuring the UI
---
The main OpenGamma Platform UI is a modern web-based application. The UI is controlled by configuration.

.............................
JavaScript and CSS deployment
.............................


The UI is built in a modular way using many JavaScript and CSS fragments. You can configure these to be delivered in different ways for your production and development environments.

~~~~~~~~~~~~~~~~~
XML configuration
~~~~~~~~~~~~~~~~~


The `uiResourceConfig.xml` file is the central location to configure all JavaScript and CSS resources. You can group all the resources into any number of files for deployment. The default is to have a single JavaScript and single CSS file at deployment time, but using the config file to create that from separate development files.

The UI resource config file is XML based and specifies *bundles*. Each bundle is formed of either *fragments* or references to other bundles. The fragments are the paths to the actual CSS/JS files. This example file explains the format:



.. code::

    <bundle id="slickgrid.css">
        <fragment>/styles/lib/jquery/slick_grid/slick.grid.css</fragment>
        <fragment>/styles/common/slickgrid/og.common.slickgrid.css</fragment>
      </bundle>
      <bundle id="analytics.css">
        <fragment>/styles/analytics/og.analytics.footer.OG-analytics-footer.css</fragment>
      </bundle>
      <bundle id="main.css">
        <bundle idref="slickgrid.css" />
        <bundle idref="analytics.css" />
      </bundle>




The default uiResourceConfig.xml is located in `/OG-Web/config/com/opengamma/web/`.

The component-based configuration system uses the key `component.webBundle.configFile` to specify the location of the XML file.

~~~~~~~~~~~~~~~
Deployment mode
~~~~~~~~~~~~~~~


The UI has two modes of running - production and development:

PROD: Turns on concatenation, minification and obfuscation
DEV: Turns off concatenation, minification and obfuscation

The component-based configuration system uses the key `component.webBundle.deployMode` to specify the mode - PROD or DEV.
