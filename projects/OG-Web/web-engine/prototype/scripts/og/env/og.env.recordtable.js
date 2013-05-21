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
                        {id: 'col-1', text: 'String', data_type:'string'},
                        {id: 'col-2', text: 'Array', data_type:'array'},
                        {id: 'col-3', text: 'Boolean', data_type:'boolean'},
                        {id: 'col-4', text: 'String', data_type:'string'}
                    ],
                    rows: [
                        {
                            id: 'row-1',
                            cells: [
                                {id:'col-1', text: 'String Cell 1'},
                                {id:'col-2', text: 'Array Cell 1'},
                                {id:'col-3', text: 'Boolean Cell 1'},
                                {id:'col-4', text: 'String Cell 1'}
                            ]
                        },
                        {
                            id: 'row-2',
                            cells: [
                                {id:'col-1', text: 'String Cell 2'},
                                {id:'col-2', text: 'Array Cell 2'},
                                {id:'col-3', text: 'Boolean Cell 2'},
                                {id:'col-4', text: 'String Cell 2'}
                            ]
                        }
                    ]
                }
            });
        };
    }
});
