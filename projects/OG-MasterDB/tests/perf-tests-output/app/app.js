(function() {
  var flatten;

  flatten = function(arr) {
    return arr.reduce((function(xs, el) {
      if (Array.isArray(el)) {
        return xs.concat(flatten(el));
      } else {
        return xs.concat([el]);
      }
    }), []);
  };

  require(['app/theme', 'jquery', 'underscore', 'app/charts'], function(theme, $, _, charts) {
    return $(function() {
      var benchmark, chart, chart_div, dbtype, master, operation, palette, _i, _len, _ref, _results;
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
            palette = generateColors(10);
            palette.shift();
            palette.shift();
            chart_div = $('<div id="' + master + '" style="width: 1200px; height: 800px; margin: 0 auto"></div>');
            $('body').append(chart_div);
            chart = charts.time_series_chart('Operations per second on ' + master, master);
            _results2.push((function() {
              var _k, _len3, _ref3, _results3;
              _ref3 = ['correct', 'get', 'add', 'remove', 'search', 'update'];
              _results3 = [];
              for (_k = 0, _len3 = _ref3.length; _k < _len3; _k++) {
                operation = _ref3[_k];
                benchmark = "app/" + dbtype + "_" + master + "_" + operation;
                _results3.push((function(c, p) {
                  try {
                    return require([benchmark], function(b) {
                      var batchSize, color;
                      color = p.shift();
                      operation = b.operation;
                      dbtype = b.dbtype;
                      master = b.master;
                      batchSize = b.batchSize;
                      c.addSeries(charts.line_series("" + dbtype + ":" + master + " " + operation + " (" + batchSize + ")", b.data, color, color), false);
                      return c.redraw();
                    });
                  } catch (error) {
                    return "just carry on";
                  }
                })(chart, palette));
              }
              return _results3;
            })());
          }
          return _results2;
        })());
      }
      return _results;
    });
  });

}).call(this);
