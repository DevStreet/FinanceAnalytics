/**
 * Copyright 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * Please see distribution for license.
 */
$.register_module({
    name: 'og.common.gadgets.RecordTable',
    dependencies: ['og.common.gadgets.manager'],
    obj: function () {
        var module = this, actions = {
                cell: [ {name: 'edit'} ],
                row: [
                    {name: 'edit'},
                    {name: 'save'},
                    {name: 'delete'},
                    {name: 'revert'}
                ],
                table: [ {name: 'add'} ]
            },
            field_types = {
                'boolean': 'radio',
                'array': 'select',
                'string': 'text'
            };

        var RecordTable = function (config) {
            if (!config) og.dev.warn('og.common.gadgets.RecordTable: Missing param [config] to constructor.');

            var container = $(config.container), form, data = config.data;

            form = new og.common.util.ui.Form({
                data: {},
                module: 'og.views.gadgets.recordtable.recordtable_tash',
                selector: '.' + config.container
            });
            form.children.push(
                new form.Block({module: 'og.views.gadgets.recordtable.header_tash', extras: {headers: data.headers}}),
                new form.Block({module: 'og.views.gadgets.recordtable.footer_tash'}),
                new form.Block({module: 'og.views.gadgets.recordtable.body_tash',
                    extras: {rows: data.rows, actions: actions}
                })
            );
            form.dom();
        };

        RecordTable.prototype = new og.common.util.ui.Block();
        return RecordTable;
    }
});