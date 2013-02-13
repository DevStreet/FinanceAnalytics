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

            var block = this, menu, initialized = false, form = config.form, datetimepicker,
                temporal = config.temporal || {};

            var menu_handler = function (event) {
                var $elem = $(event.srcElement || event.target), $custom;
                if ($elem.is('.custom') || $elem.is('.custom input') || $elem.is('.custom-date-time')) {
                    if ($elem.is('.custom-date-time')) {
                        $custom = $elem.siblings('.custom').find('input');
                        if (!($custom.attr('checked'))) $custom.attr('checked', 'checked');
                    }
                    return $elem.parents('fieldset').find('.custom-date-time').focus(0);
                }

                if ($elem.is('.latest') || $elem.is('.latest input')) {
                    $custom = $elem.parents('fieldset').find('.custom-date-time');
                    if ($custom.attr('value') !== '') return $custom.attr('value', '');
                }
            };

            var serialize = function () {
                var val_time = $('.valuation-time .custom-date-time').val(),
                    port_version = $('.portfolio-version .custom-date-time').val(),
                    port_correction = $('.portfolio-correction .custom-date-time').val();
                return {
                    valuation: val_time ? val_time : 'latest',
                    portfolio_version: port_version ? port_version : 'latest',
                    port_correction : port_correction ? port_correction : 'latest'
                };
            };

            var dtp_mousedown = function (event){
                // wip
            };

            var dtp_handler = function (elem) { // wip
                // elem.off('mousedown', elem.selector, dtp_mousedown);
                // elem.on('mousedown', elem.selector, dtp_mousedown);
            };

            form.Block.call(block, {
                module: 'og.analytics.form_temporal_tash',
                generator: function (handler, tmpl, data) {
                    if (temporal.valuation && temporal.valuation.datetime)
                        data.valuation = temporal.valuation;
                    if (temporal.portfolio_version && temporal.portfolio_version.datetime)
                        data.portfolio_version = temporal.portfolio_version;
                    if (temporal.portfolio_correction && temporal.portfolio_correction.datetime)
                        data.portfolio_correction = temporal.portfolio_correction;
                    handler(tmpl(data));
                },
                processor: function (data) {
                    delete data['portfolio-correction'];
                    delete data['portfolio-version'];
                    delete data['valuation-time'];
                    data.temporal = serialize();
                }
            });

            form.on('form:load', function (event) {
                var menu = new og.common.util.ui.DropMenu({cntr: $('.og-temporal')});
                menu.$dom.menu = $('.og-menu', '.og-temporal').on('click', '.custom, .latest', menu_handler);
                menu.$dom.date_input = $('.custom-date-time', menu.$dom.menu).on('click', menu_handler);
                menu.$dom.date_input.each(function (idx, elem) {
                    $(elem).datetimepicker({
                        dateFormat:'yy-mm-dd',
                        onSelect: function () { menu.fire('dropmenu:open'); },
                        onClose: function () { menu.fire('dropmenu:open'); }
                    });
                });
                og.common.events.on('temporal:dropmenu:open', function() {menu.fire('dropmenu:open', this);});
                og.common.events.on('temporal:dropmenu:close', function() {menu.fire('dropmenu:close', this);});
                og.common.events.on('temporal:dropmenu:focus', function() {menu.fire('dropmenu:focus', this);});
            });
        };

        TemporalMenu.prototype = new Block;

        return TemporalMenu;
    }
});