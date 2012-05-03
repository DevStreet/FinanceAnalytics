flatten = (arr) ->
  arr.reduce ((xs, el) ->
    if Array.isArray el
      xs.concat flatten el
    else
      xs.concat [el]), []

#deps = for dbtype in ['hsqldb', 'postgres']
deps = for dbtype in ['postgres']
          for master in [
            'DbConfigMasterBulkTest',
            'DbExhangeMasterBulkTest',
            'DbHolidayMasterBulkTest',
            'DbHTSMasterBulkTest',
            'DbMarketDataSnapshotMasterBulkTest',
            'DbPortfolioMasterBulkTest',
            'DbPositionMasterBulkTest',
            'DbSecurityMasterWorkerBulkTest']
                       
            for operation in ['correct', 'get', 'insert', 'remove', 'search', 'update']
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
          batchSize = s.batchSize
          chart.addSeries(charts.line_series("#{dbtype}:#{master} #{operation} (#{batchSize})", s.data, color, color), false)   
          chart.redraw()
      catch error
        "just carry on"
          
      
    chart