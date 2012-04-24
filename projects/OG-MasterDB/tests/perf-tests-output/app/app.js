(function() {
  var dbtype, deps, flatten, master, operation,
    __slice = Array.prototype.slice;

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
    _ref = ['hsqldb', 'postgres'];
    _results = [];
    for (_i = 0, _len = _ref.length; _i < _len; _i++) {
      dbtype = _ref[_i];
      _results.push((function() {
        var _j, _len2, _ref2, _results2;
        _ref2 = ['com.opengamma.masterdb.DbConfigMasterBulkTest', 'com.opengamma.masterdb.DbExhangeMasterBulkTest', 'com.opengamma.masterdb.DbHolidayMasterBulkTest', 'com.opengamma.masterdb.DbHTSMasterBulkTest', 'com.opengamma.masterdb.DbMarketDataSnapshotMasterBulkTest', 'com.opengamma.masterdb.DbPortfolioMasterBulkTest', 'com.opengamma.masterdb.DbPositionMasterBulkTest', 'com.opengamma.masterdb.DbSecurityMasterBulkTest'];
        _results2 = [];
        for (_j = 0, _len2 = _ref2.length; _j < _len2; _j++) {
          master = _ref2[_j];
          _results2.push((function() {
            var _k, _len3, _ref3, _results3;
            _ref3 = ['search'];
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

  require(['app/theme', 'jquery', 'underscore', 'app/charts'].concat(flatten(deps)), function() {
    var $, charts, series, theme, _;
    theme = arguments[0], $ = arguments[1], _ = arguments[2], charts = arguments[3], series = 5 <= arguments.length ? __slice.call(arguments, 4) : [];
    return $(function() {
      var chart, color, palette, s, _i, _len;
      palette = generateColors(60);
      palette.shift();
      palette.shift();
      chart = charts.time_series_chart('Operations per second', 'chart');
      for (_i = 0, _len = series.length; _i < _len; _i++) {
        s = series[_i];
        if (s != null) {
          color = palette.shift();
          operation = s.operation;
          dbtype = s.dbtype;
          master = s.master;
          chart.addSeries(charts.line_series("" + dbtype + ":" + master + " " + operation + " per second", s.data, color, color), false);
        }
      }
      chart.redraw();
      return chart;
    });
  });

}).call(this);
