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
                cols: [{
                    text: 'foo',
                    id: 'c1'
                }, {
                    text: 'bar',
                    id: 'c2'
                }, {
                    text: 'baz',
                    id: 'c3'
                }, {
                    text: 'boo',
                    id: 'c4'
                }],
                rows: [
                    {
                        id: 'c1',
                        cells: [{
                            type:'string',
                            content: 'Foo Cell 1'
                        },
                        {
                            type:'string',
                            content: 'Foo Cell 2'
                        }]
                    },
                    {
                        id: 'c2',
                        cells: [{
                            type:'string',
                            content: 'Bar Cell 1'
                        },
                        {
                            type:'string',
                            content: 'Bar Cell 2'
                        }]
                    },
                    {
                        id: 'c3',
                        cells: [{
                            type:'string',
                            content: 'Baz Cell 1'
                        },
                        {
                            type:'string',
                            content: 'Baz Cell 2'
                        }]
                    },
                    {
                        id: 'c4',
                        cells: [{
                            type:'string',
                            content: 'Boo Cell 1'
                        },
                        {
                            type:'string',
                            content: 'Boo Cell 2'
                        }]
                    }
                ]
        };
        return function (config) {
            // TODO AG: Implement stand alone component api
        };
    }
});