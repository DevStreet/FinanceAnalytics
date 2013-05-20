/*
 * Copyright 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * Please see distribution for license.
 */
$.register_module({
    name: 'og.env.recordtable',
    dependencies: [],
    obj: function () {
        return function (selector) {
            new new og.common.gadgets.RecordTable();
        };
    }
});
