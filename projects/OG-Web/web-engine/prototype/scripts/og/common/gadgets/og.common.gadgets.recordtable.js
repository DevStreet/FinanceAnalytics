/**
 * Copyright 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * Please see distribution for license.
 */
$.register_module({
    name: 'og.common.gadgets.Tabular',
    dependencies: ['og.common.gadgets.manager'],
    obj: function () {
        var module = this, data = {
                // TODO AG: data structure is a WIP, heavy review needed.
                cols: [
                    { id: 'c1', text: 'foo', type:'string'},
                    { id: 'c2', text: 'bar', type:'string'},
                    { id: 'c3', text: 'baz', type:'string'},
                    { id: 'c4', text: 'boo', type:'string'}
                ],
                rows: [
                    {
                        id: 'c1',
                        cells: [
                            {text: 'Foo Cell 1'},
                            {text: 'Foo Cell 2'}
                        ]
                    },
                    {
                        id: 'c2',
                        cells: [
                            {text: 'Bar Cell 1' },
                            {text: 'Bar Cell 2'}
                        ]
                    },
                    {
                        id: 'c3',
                        cells: [
                            {text: 'Baz Cell 1' },
                            {text: 'Baz Cell 2'}
                        ]
                    },
                    {
                        id: 'c4',
                        cells: [
                            {text: 'Boo Cell 1' },
                            {text: 'Boo Cell 2'}
                        ]
                    }
                ]
        };
        return function (config) {

        };
    }
});