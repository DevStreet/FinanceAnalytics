/*
 * Copyright 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * Please see distribution for license.
 */
$.register_module({
    name: 'og.env.recordtable',
    dependencies: [],
    obj: function () {
        return function (selector) {
            new og.common.gadgets.RecordTable({
                container: selector,
                data: { // TODO AG: data structure is a WIP, heavy review needed.
                    headers: [
                        {id: 'col-1', text: 'foo', type:'string'},
                        {id: 'col-2', text: 'bar', type:'string'},
                        {id: 'col-3', text: 'baz', type:'string'},
                        {id: 'col-4', text: 'boo', type:'string'}
                    ],
                    rows: [
                        {
                            cells: [
                                {id:'col-1', text: 'Foo Cell 1'},
                                {id:'col-2', text: 'Bar Cell 1'},
                                {id:'col-3', text: 'Baz Cell 1'},
                                {id:'col-4', text: 'Boo Cell 1'}
                            ]
                        },
                        {
                            cells: [
                                {id:'col-1', text: 'Foo Cell 2'},
                                {id:'col-2', text: 'Bar Cell 2'},
                                {id:'col-3', text: 'Baz Cell 2'},
                                {id:'col-4', text: 'Boo Cell 2'}
                            ]
                        }
                    ]
                }
            });
        };
    }
});
