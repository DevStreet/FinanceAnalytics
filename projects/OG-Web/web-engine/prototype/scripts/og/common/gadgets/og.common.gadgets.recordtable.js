/**
 * Copyright 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * Please see distribution for license.
 */
$.register_module({
    name: 'og.common.gadgets.RecordTable',
    dependencies: ['og.common.gadgets.manager'],
    obj: function () {
        var RecordTable = function (config) {
            if (!config) og.dev.warn('og.common.gadgets.RecordTable: Missing param [config] to constructor.');

            var form, data = config.data, headers = data.headers, rows = data.rows, field_types = {},
                actions = {
                    cell: [ {name: 'edit'} ],
                    row: [
                        {name: 'edit'},
                        {name: 'save'},
                        {name: 'delete'},
                        {name: 'revert'}
                    ],
                    table: [ {name: 'add'} ]
                };

            field_types = rows.map(function (row) {
                return {
                    row: row.id,
                    cols: row.cells.map(function (cell) { // Refactor
                        return {
                            id: cell.id,
                            data_type: headers.filter(function (header) {
                                if (header.id === cell.id) return header.data_type;
                            })
                        };
                    })
                };
            });

            console.log(field_types);

            var add_handler = function () {
                new form.Block({
                    module: 'og.views.gadgets.recordtable.row_tash',
                    extras: {'fields': headers.map(create_field_type)}
                }).html(function (html) {
                    $('.OG-gadget-recordtable tbody').append(html);
                });
            };

            var create_field_type = function (field) {
                var template = {};
                switch (field.type) {
                    case 'string':
                    case 'number': {
                        template.html = '<input type="text" val="{{val}}" />';
                        break;
                    }
                    case 'array': {
                        template.html = '<select>{{#each opts}}<option>{{this.val}}</option>{{/each}}</select>';
                        break;
                    }
                    case 'boolean': {
                        template.html = '<input name="'+field.id+'" type="radio" val="true" /> '+
                                        '<input name="'+field.id+'" type="radio" val="true" />';
                        break;
                    }
                }
                return template;
            };

            var event_delegate = function (event) {
                var $elem = $(event.srcElement || event.target);
                if ($elem.attr('data-action') === 'add') return add_handler();
            };

            var load_handler = function () {};

            form = new og.common.util.ui.Form({
                data: {},
                module: 'og.views.gadgets.recordtable.recordtable_tash',
                selector: '.' + config.container
            });
            form.children.push(
                new form.Block({
                    module: 'og.views.gadgets.recordtable.header_tash',
                    extras: {headers: data.headers}
                }),
                new form.Block({
                    module: 'og.views.gadgets.recordtable.footer_tash',
                    extras: {actions: actions, colspan: data.headers.length+1}
                }),
                new form.Block({
                    module: 'og.views.gadgets.recordtable.body_tash',
                    extras: {rows: data.rows, actions: actions}
                })
            );
            form.on('form:load', load_handler);
            form.on('click', '.OG-gadget-recordtable', event_delegate);
            form.dom();
        };

        RecordTable.prototype = new og.common.util.ui.Block();
        return RecordTable;
    }
});