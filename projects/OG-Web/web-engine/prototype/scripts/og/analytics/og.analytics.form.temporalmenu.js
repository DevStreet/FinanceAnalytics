/*
 * Copyright 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * Please see distribution for license.
 */
$.register_module({
    name: 'og.analytics.form.TemporalMenu',
    dependencies: [],
    obj: function () {
        var module = this, Block = og.common.util.ui.Block;
        var TemporalMenu = function (config) {
            if (!config) return og.dev.warn('og.analytics.TemporalMenu: Missing param [config] to constructor.');

            if (!('form' in config) || !config.form)
                return og.dev.warn('og.analytics.TemporalMenu: Missing param key [config.form] to constructor.');

            // Private
            var block = this, menu, initialized = false, form = config.form;

            var display_date_time = function (event) {
                // $dom.date_input.datepicker('show');
            };

            var menu_handler = function (event) {
                var $elem = $(event.srcElement || event.target);
                if ($elem.is('.custom') || $elem.is('.custom input'))
                    return $elem.parents('fieldset').find('.custom-date-time').focus(0);
            };

            form.Block.call(block, { selector: '.og-temporal', module: 'og.analytics.form_temporal_tash' });

            form.on('form:load', function (event) {
                var menu = new og.common.util.ui.DropMenu({cntr: $('.og-temporal')});
                menu.$dom.menu = $('.og-menu', '.og-temporal').on('click', '.custom, .custom input', menu_handler);
                menu.$dom.date_input = $('.custom-date-time', menu.$dom.menu);
                menu.$dom.date_input.datetimepicker({
                    dateFormat:'yy-mm-dd',
                    onSelect: function () {
                        menu.$dom.date_input.off('mouseup.prevent_blurkill');
                    }
                }).datetimepicker('widget').on('mouseup.prevent_blurkill', function(event) {
                    menu.fire('dropmenu:open');
                });
            });
        };

        TemporalMenu.prototype = new Block;

        return TemporalMenu;
    }
});