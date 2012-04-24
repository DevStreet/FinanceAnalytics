flatten = (arr) ->
  arr.reduce ((xs, el) ->
    if Array.isArray el
      xs.concat flatten el
    else
      xs.concat [el]), []
  
deps = for dbtype in ['hsqldb', 'postgres']
          #for master in ['exhange', 'holiday', 'hts', 'portfolio', 'position', 'security', 'snapshot']
          for master in [
            'com.opengamma.masterdb.DbConfigMasterBulkTest',
            'com.opengamma.masterdb.DbExhangeMasterBulkTest',
            'com.opengamma.masterdb.DbHolidayMasterBulkTest',
            'com.opengamma.masterdb.DbHTSMasterBulkTest',
            'com.opengamma.masterdb.DbMarketDataSnapshotMasterBulkTest',
            'com.opengamma.masterdb.DbPortfolioMasterBulkTest', 
            'com.opengamma.masterdb.DbPositionMasterBulkTest',
            'com.opengamma.masterdb.DbSecurityMasterBulkTest']
           
            
            for operation in ['search']
              "app/#{dbtype}_#{master}_#{operation}"

require ['app/theme', 'jquery', 'underscore', 'app/charts'].concat(flatten(deps)), (theme, $, _, charts, series...) ->     
  $ ->

    palette = generateColors(60)
    palette.shift()
    palette.shift()


    chart = charts.time_series_chart('Operations per second', 'chart')

    # When \dfrac {1} {\sqrt {3+x^{2}}}\(a \ne 0\), there are two solutions to \(ax^2 + bx + c = 0\) and they are $$x = {-b \pm \sqrt{b^2-4ac} \over 2a}.$$
    for s in series
      if s?
        color = palette.shift()
        operation = s.operation
        dbtype = s.dbtype
        master = s.master
        chart.addSeries(charts.line_series("#{dbtype}:#{master} #{operation} per second", s.data, color, color), false)   
      
    chart.redraw()
    
    chart