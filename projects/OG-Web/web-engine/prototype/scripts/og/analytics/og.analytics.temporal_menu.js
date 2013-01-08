/*
 * Copyright 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * Please see distribution for license.
 */
$.register_module({
    name: 'og.analytics.TemporalMenu',
    dependencies: ['og.analytics.DropMenu'],
    obj: function () {
        return function (config) {
            if (!config) return og.dev.warn('og.analytics.TemporalMenu: Missing param [config] to constructor.');

            if (!('cntr' in config) || !config.cntr)
                return og.dev.warn('og.analytics.TemporalMenu: Missing param key [config.cntr] to constructor.');

            // Private
            var menu;

            var init = function (conf) {
                $.when(og.api.text({module: 'og.analytics.form_temporal_tash'})).pipe(function (resp) {
                    menu = new og.analytics.DropMenu({ $cntr: config.cntr, tmpl: resp });
                    if (($dom = menu.$dom))
                        if ($dom.menu) $dom.menu.on('click', 'input', menu_handler);
                });
            };

            var menu_handler = function (event) {
                var $elem = $(event.srcElement || event.target), entry;
            };

            // Public
            get = function () {};

            replay = function () {};

            reset = function () {};

            return init(config), menu;
        };
    }
});