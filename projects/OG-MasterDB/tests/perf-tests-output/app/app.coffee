flatten = (arr) ->
  arr.reduce ((xs, el) ->
    if Array.isArray el
      xs.concat flatten el
    else
      xs.concat [el]), []


require ['app/theme', 'jquery', 'underscore', 'app/charts'], (theme, $, _, charts) ->
  $ ->    
    
    for dbtype in ['postgres']
      for master in [
        'DbConfigMasterBulkTest',
        'DbExhangeMasterBulkTest',
        'DbHolidayMasterBulkTest',
        'DbHTSMasterBulkTest',
        'DbMarketDataSnapshotMasterBulkTest',
        'DbPortfolioMasterBulkTest',
        'DbPositionMasterBulkTest',
        'DbSecurityMasterWorkerBulkTest']
          
        palette = generateColors(10)
        palette.shift() # ommit white
        palette.shift() # ommit black
        
        chart_div = $('<div id="'+master+'" style="width: 1200px; height: 800px; margin: 0 auto"></div>')
        $('body').append(chart_div)
        chart = charts.time_series_chart('Operations per second on '+master, master)
                  
        for operation in ['correct', 'get', 'add', 'remove', 'search', 'update']
          benchmark = "app/#{dbtype}_#{master}_#{operation}"
          ((c, p) ->
            try                                                                                                                           
              require [benchmark], (b) ->                                                                                                         
                color = p.shift()                                                                                                   
                operation = b.operation                                                                                                   
                dbtype = b.dbtype                                                                                                         
                master = b.master                                                                                                         
                batchSize = b.batchSize                 
                c.addSeries(charts.line_series("#{dbtype}:#{master} #{operation} (#{batchSize})", b.data, color, color), false)       
                c.redraw()                                                                                                            
            catch error                                                                                                                   
              "just carry on")(chart, palette)                                                                                                                
            
          
         