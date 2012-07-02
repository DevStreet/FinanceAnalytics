define(['highcharts'], function (Highcharts) {

    function line_series(title, data, color, bgcolor) {
        return {
            name:title,
            data:data,
            type:'area',
            //pointInterval: 24 * 3600 * 1000,
            //pointStart: Date.UTC(2006, 0, 01),
            color:color || "#F00",
            fillColor:bgcolor && {
                linearGradient:[0, 0, 0, 600],
                stops:[
                    [0, bgcolor],
                    [1, 'rgba(1,0,0,0)'],
                    [2, 'rgba(1,0,0,0)']
                ]
            }
        }
    }

    function time_series_chart(title, renderTo) {
        return new Highcharts.Chart({
            credits:{
                enabled:false
            },
            chart:{
                renderTo:renderTo,
                zoomType:'x',
                spacingRight:20
            },
            title:{
                text:title
            },
            subtitle:{
                text:document.ontouchstart === undefined ?
                    'Click and drag in the plot area to zoom in' :
                    'Drag your finger over the plot to zoom in'
            },
            xAxis:{
                type:'linear',
                title:{
                    text:null
                }
            },
            yAxis:{
                title:{
                    text:'Operations per seond'
                },
                startOnTick:false,
                showFirstLabel:false
            },
            tooltip:{
                shared:true
            },
            legend:{
                enabled:true
            },
            plotOptions:{
                area:{
                    //stacking: 'normal',
                    fillColor:{
                        linearGradient:[0, 0, 0, 600],
                        stops:[
                            //[0, Highcharts.theme.colors[0]],
                            [1, 'rgba(2,0,0,0)']
                        ]
                    },
                    lineWidth:1,
                    marker:{
                        enabled:false,
                        states:{
                            hover:{
                                enabled:true,
                                radius:5
                            }
                        }
                    },
                    shadow:false,
                    states:{
                        hover:{
                            lineWidth:1
                        }
                    }
                }
            },
            series:[]
        })
    }

    return {
        line_series:line_series,
        time_series_chart:time_series_chart
    }
});
