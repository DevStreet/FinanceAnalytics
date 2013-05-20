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
                        {id: 'c1', text: 'foo', type:'string'},
                        {id: 'c2', text: 'bar', type:'string'},
                        {id: 'c3', text: 'baz', type:'string'},
                        {id: 'c4', text: 'boo', type:'string'}
                    ],
                    rows: [
                        {
                            cells: [
                                {id:'c1', text: 'Foo Cell 1'},
                                {id:'c2', text: 'Bar Cell 1'},
                                {id:'c3', text: 'Baz Cell 1'},
                                {id:'c4', text: 'Boo Cell 1'}
                            ]
                        },
                        {
                            cells: [
                                {id:'c1', text: 'Foo Cell 2'},
                                {id:'c2', text: 'Bar Cell 2'},
                                {id:'c3', text: 'Baz Cell 2'},
                                {id:'c4', text: 'Boo Cell 2'}
                            ]
                        }
                    ]
                }
            });
        };
    }
});
