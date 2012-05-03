(function() {
  var dbtype, deps, flatten, master, operation;

  flatten = function(arr) {
    return arr.reduce((function(xs, el) {
      if (Array.isArray(el)) {
        return xs.concat(flatten(el));
      } else {
        return xs.concat([el]);
      }
    }), []);
  };

  deps = (function() {
    var _i, _len, _ref, _results;
    _ref = ['postgres'];
    _results = [];
    for (_i = 0, _len = _ref.length; _i < _len; _i++) {
      dbtype = _ref[_i];
      _results.push((function() {
        var _j, _len2, _ref2, _results2;
        _ref2 = ['DbConfigMasterBulkTest', 'DbExhangeMasterBulkTest', 'DbHolidayMasterBulkTest', 'DbHTSMasterBulkTest', 'DbMarketDataSnapshotMasterBulkTest', 'DbPortfolioMasterBulkTest', 'DbPositionMasterBulkTest', 'DbSecurityMasterWorkerBulkTest'];
        _results2 = [];
        for (_j = 0, _len2 = _ref2.length; _j < _len2; _j++) {
          master = _ref2[_j];
          _results2.push((function() {
            var _k, _len3, _ref3, _results3;
            _ref3 = ['correct', 'get', 'insert', 'remove', 'search', 'update'];
            _results3 = [];
            for (_k = 0, _len3 = _ref3.length; _k < _len3; _k++) {
              operation = _ref3[_k];
              _results3.push("app/" + dbtype + "_" + master + "_" + operation);
            }
            return _results3;
          })());
        }
        return _results2;
      })());
    }
    return _results;
  })();

  require(['app/theme', 'jquery', 'underscore', 'app/charts'], function(theme, $, _, charts) {
    return $(function() {
      var chart, d, palette, _i, _len, _ref;
      palette = generateColors(60);
      palette.shift();
      palette.shift();
      chart = charts.time_series_chart('Operations per second', 'chart');
      _ref = flatten(deps);
      for (_i = 0, _len = _ref.length; _i < _len; _i++) {
        d = _ref[_i];
        try {
          require([d], function(s) {
            var batchSize, color;
            color = palette.shift();
            operation = s.operation;
            dbtype = s.dbtype;
            master = s.master;
            batchSize = s.batchSize;
            chart.addSeries(charts.line_series("" + dbtype + ":" + master + " " + operation + " (" + batchSize + ")", s.data, color, color), false);
            return chart.redraw();
          });
        } catch (error) {
          "just carry on";
        }
      }
      return chart;
    });
  });

}).call(this);
