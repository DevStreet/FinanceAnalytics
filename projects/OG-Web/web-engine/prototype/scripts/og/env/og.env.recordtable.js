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
                        {id: 'col-1', val: 'String', data_type: 'string'},
                        {id: 'col-2', val: 'Array', data_type: 'array'},
                        {id: 'col-3', val: 'Boolean', data_type: 'boolean'},
                        {id: 'col-4', val: 'String', data_type: 'string'}
                    ],
                    rows: [
                        {
                            id: 'row-1',
                            cells: [
                                {id:'col-1', val: 'Cell'},
                                {id:'col-2', val: 'A'},
                                {id:'col-3', val: true},
                                {id:'col-4', val: 'Cell'}
                            ]
                        },
                        {
                            id: 'row-2',
                            cells: [
                                {id:'col-1', val: 'Cell'},
                                {id:'col-2', val: 'B'},
                                {id:'col-3', val: true},
                                {id:'col-4', val: 'Cell'}
                            ]
                        }
                    ]
                }
            });
        };
    }
});
