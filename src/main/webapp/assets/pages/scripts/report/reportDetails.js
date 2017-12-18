var pointMap={};
$(function(){
	loadPoint(window.ynz.longieb.powerStationId);
	getData();
	setSelectMenu("menureportmanager","report_commonuse");
	TableDatatablesButtons.init();
	 // ECHARTS
    require.config({
        paths: {
            echarts: window.ynz.cdnPath+'global/plugins/echarts/'
        }
    });
});

function loadPoint(id){//根据电站id添加测点id和名称
	ynzAjax.post(
		window.ynz.path+"/pointInfo/getPowerStationAllPointNOPage.do",
		{'powerStationId':id},
		function(response){ 
			var data = response.data;
			for (var int = 0; int < data.length; int++) {
				var value = data[int].id+"_"+data[int].name;
				pointMap[data[int].id]=data[int].name;
				$('.form-control.select3me').append("<option value="+value+">"+data[int].name+"</option>");
			}
        },
        function(e){ 
            console.log("--------error------"+JSON.stringify(e));
        }
    );
}

function getData(){
	var startTime = reportInfo.starttime;
	var endTime   = reportInfo.endtime;
	var pointlist = reportInfo.pointList;
	var analoginputIds="" ;//测点id
	for (var int = 0; int < pointlist.length; int++){
			var analoginputId=pointlist[int].analoginputId;
			if(int==0){
				analoginputIds=analoginputId;
			}else{
				analoginputIds+=","+analoginputId;
			}
	}
	console.log("测点数据---"+analoginputIds);
	var paramObj = {
			startTime : "2017-06-26 06:40:02",
			endTime   : "2017-06-26 15:40:02",
			haveReal  : 0,
			analoginputIds:analoginputIds
	};
   	ynzAjax.post(
    		basePath+"stationmonitor/historyRedress/points",
    		paramObj,
    		function(response){ 
    			var legendData = [];
    			var time = [];
    			var series=[];
    			
    			for(var i = 0;i<response.data.length;i++){
    				var serieDatas=[];
    				var seriesData={};
    				legendData[i]=pointMap[response.data[i].analoginputId];
    				seriesData.name=pointMap[response.data[i].analoginputId];
					seriesData.type="line";
    				for(var j=0;j<response.data[i].historyDatas.length;j++){ 
    					time[j]=response.data[i].historyDatas[j].time;
    					serieDatas[j]=response.data[i].historyDatas[j].value;
    				}
    				seriesData.data=serieDatas;
    				series[i]=seriesData;
    			}
    		    require(
    		            [
    		                'echarts',
    		                'echarts/chart/bar',
    		                'echarts/chart/chord',
    		                'echarts/chart/eventRiver',
    		                'echarts/chart/force',
    		                'echarts/chart/funnel',
    		                'echarts/chart/gauge',
    		                'echarts/chart/heatmap',
    		                'echarts/chart/k',
    		                'echarts/chart/line',
    		                'echarts/chart/map',
    		                'echarts/chart/pie',
    		                'echarts/chart/radar',
    		                'echarts/chart/scatter',
    		                'echarts/chart/tree',
    		                'echarts/chart/treemap',
    		                'echarts/chart/venn',
    		                'echarts/chart/wordCloud'
    		            ],
    		            function(ec) {
    		                // --- LINE ---
    		                var myChart = ec.init(document.getElementById('echarts_bar'));
    		                myChart.setOption({
    		                    tooltip: {
    		                        trigger: 'axis'
    		                    },
    		                    legend: {
    		                        data: legendData
    		                    },
    		                    toolbox: {
    		                        show: true,
    		                        feature: {
//    		                            mark: {
//    		                                show: true,
//    		                            },
//    		                            dataView: {
//    		                                show: true,
//    		                                readOnly: false
//    		                            },
//    		                            magicType: {
//    		                                show: true,
//    		                                type: ['line', 'bar']
//    		                            },
    		                            dataZoom : {show: true},
    		            	            restore : {show: true},
    		                            restore: {
    		                                show: true
    		                            },
    		                            saveAsImage: {
    		                                show: true
    		                            },
    		                            
    		                        }
    		                    },
    		                    calculable: true,
    		                    xAxis: [{
    		                        type: 'category',
    		                        data:time
    		                    }],
    		                    yAxis: [{
    		                        type: 'value',
    		                        splitArea: {
    		                            show: true
    		                        }
    		                    }],
    		                    series:series
    		                });

    		            }
    		        );
    		},
	        function(e){ 
	            console.log("--------error------"+JSON.stringify(e));
	        }
    	)
}

/**
 * 表格初始化
 */
var TableDatatablesButtons = function () {
	/**
	 * 初始化表格
	 */
	 var initTable1 = function () {
	        var table = $('#sample_1');
	        var oTable = table.dataTable({
	        	"bAutoWidth":true,//设置自动计算列宽
	        	"bLengthChange":false,
	        	"bDeferRender":false,//设置是否延迟渲染  使用ajax或者js加载数据 开启延迟渲染可提升速度
	            "serverSide":false,//设置服务器端分页方式  false情况下默认使用前端插件分页
	            "responsive": true,//？？？？
	            "bFilter":false,//搜索
	            "bSort":false,//排序
	            "order": [  //排序
	                [0, 'asc']
	            ],  
	            /**
	             * 汉化
	             */
	            */
	            "language": {
		           	"aria": {
		           		"sortAscending": ": activate to sort column ascending",
		           		"sortDescending": ": activate to sort column descending"
		           	},
		           	"emptyTable": window.ynz.local.table_emptyTable,
		           	"info": window.ynz.local.table_info,
		           	"infoEmpty": window.ynz.local.table_infoEmpty,
		           	"lengthMenu": window.ynz.local.table_lengthMenu,
		           	"zeroRecords": window.ynz.local.table_zeroRecords,
		           	"buttons":{
		           		"print":window.ynz.local.table_print,
		           		"copy":window.ynz.local.table_copy,
		           		"pdf":window.ynz.local.table_pdf,
		           		"excel":window.ynz.local.table_excel,
		           		"csv":"csv",
		           		"colvis":"显示/隐藏列"
		           	},
		           	"sProcessing": window.ynz.local.table_sProcessing, 
		              },
	            buttons: [ 
	            ],
	            "lengthMenu": [
	                [10, 15, 20, -1],
	                [10, 15, 20, "All"] // change per page values here
	            ],
	            "dom": "<'row' <'col-md-12'B>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal scrollable datatable
	        });
	    }
    return {
        init: function () {
            if (!jQuery().dataTable) {
                return;
            }
            initTable1();
        }
    };
}(); 