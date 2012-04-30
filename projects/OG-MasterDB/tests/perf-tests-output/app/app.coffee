flatten = (arr) ->
  arr.reduce ((xs, el) ->
    if Array.isArray el
      xs.concat flatten el
    else
      xs.concat [el]), []

#deps = for dbtype in ['hsqldb', 'postgres']
deps = for dbtype in ['postgres']
          for master in [
            'com.opengamma.masterdb.DbConfigMasterBulkTest',
            'com.opengamma.masterdb.DbExhangeMasterBulkTest',
            'com.opengamma.masterdb.DbHolidayMasterBulkTest',
            'com.opengamma.masterdb.DbHTSMasterBulkTest',
            'com.opengamma.masterdb.DbMarketDataSnapshotMasterBulkTest',
            'com.opengamma.masterdb.DbPortfolioMasterBulkTest',
            'com.opengamma.masterdb.DbPortfolioMasterBulkTest2',
            'com.opengamma.masterdb.DbPositionMasterBulkTest',
            'com.opengamma.masterdb.DbSecurityMasterWorkerBulkTest']
                       
            for operation in ['search', 'insert']
              "app/#{dbtype}_#{master}_#{operation}"

require ['app/theme', 'jquery', 'underscore', 'app/charts'], (theme, $, _, charts) ->
  $ ->
    palette = generateColors(60)
    palette.shift() # ommit white
    palette.shift() # ommit black
    
    chart = charts.time_series_chart('Operations per second', 'chart')
  
    for d in flatten(deps)
      try
        require [d], (s) ->           
          color = palette.shift()
          operation = s.operation
          dbtype = s.dbtype
          master = s.master
          chart.addSeries(charts.line_series("#{dbtype}:#{master} #{operation} per second", s.data, color, color), false)   
          chart.redraw()
      catch error
        "just carry on"
          
    chart.redraw()
      
    chart