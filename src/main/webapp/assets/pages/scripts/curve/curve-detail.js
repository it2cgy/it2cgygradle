$(function(){
	setSelectMenuRoot("menucustomlinemanager");
	TableDatatablesButtons.init();
	
	 // ECHARTS
    require.config({
        paths: {
            echarts: window.ynz.cdnPath+'global/plugins/echarts/'
        }
    });
    getPointDate();
});
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
	           	"oPaginate":{
	           		"sFirst":window.ynz.local.table_sfirst,
	           		"sPrevious":window.ynz.local.table_sprevious,
	           		"sNext":window.ynz.local.table_snext,
	           		"sLast":window.ynz.local.table_slast
	           	},
	           	"sProcessing": window.ynz.local.table_sProcessing, 
	              },
	            // setup buttons extension: http://datatables.net/extensions/buttons/
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

function setEchars(curveData){
	// DEMOS
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
            var myChart2 = ec.init(document.getElementById('echarts_line'));
            myChart2.setOption({
                title: {
                },
                tooltip: {
                    trigger: 'axis'
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
                    boundaryGap: false,
                    data: curveData.date
                }],
                yAxis: curveData.yaxisData,
                series: curveData.point
            });
        }
    );
}
function getPointDate(){
	var curveData = new Object();
	curveData.name = curveInfo.name;
	curveData.yaxisData=[];
	curveData.yaxisData[0]={"name":curveInfo.firstYaxisName,"type": "value"};
	if(curveInfo.type==2){
		curveData.yaxisData[1]={"name":curveInfo.secondYaxisName,"type": "value"};
	}
	$("#pointName").val(curveInfo.name);
	$("#pointLeft").val(curveInfo.firstYaxisName);
	if(curveInfo.secondYaxisName==null||curveInfo.secondYaxisName==""){
		$("#pointRight").val(window.ynz.local.wu);
	}else{
		$("#pointRight").val(curveInfo.secondYaxisName);
	}
	$("#pointSpan").val(curveInfo.timeSpan+"min");
	var ids = "";
	var point = curveInfo.pointList;
	for(var i=0;i<point.length;i++){
		if(i==0){
			ids+=curveInfo.pointList[i].analoginputId;
		}else{
			ids+=","+curveInfo.pointList[i].analoginputId;
		}
	}
	var endTime = timeUtil.dateToString(new Date(),"yyyy-mm-dd hh:mi:ss");
	var startTime = timeUtil.dateToString(new Date(),"yyyy-mm-dd 00:00:00");
	curveData.point=[];
	curveData.date=[];
	curveData.color=[];
	ynzAjax.post(
    		basePath+"stationmonitor/historyRedress/points.do",
    		{"analoginputIds":ids,
    		 "startTime":startTime,
    		 "endTime":endTime,
    		 "minutesSpan":curveInfo.timeSpan,
    		 "haveReal":1},
    		 //TODO
    		function(response){ 
    			 console.log(response);
    			 for(var j=0;j<response.data.length;j++){
    				 for(var k=0;k<point.length;k++){
    					 var colorCode = point[k].colorCode;
    					 if(colorCode==null || colorCode==""){
    						 colorCode = "#15bda4"; //没颜色，就给给默认颜色，理论上不会执行到这
    					 }
    					 if(response.data[j].analoginputId==point[k].analoginputId){
    						 var result = getPointData(response.data[j].historyDatas);
    						 if(j==0){
    							 curveData.date = result.time;
    						 }
    						 curveData.point[j]={"name": point[k].pointName,
    								 "symbol":"none","smooth":"true",
    								 "type": 'line',"data":result.value,
    								 "itemStyle":{"normal":{"color":colorCode}}};
    					 }
    				 }
    			 }
    			 setEchars(curveData);
	        },
	        function(e){ 
	            console.log("--------error------"+e);
	        }
    	)
}
function getPointData(historyDatas){
	var res = new Object();
	res.time=[];
	res.value=[];
	for(var i=0;i<historyDatas.length;i++){
		res.time.push(timeUtil.timeToString(historyDatas[i].time,"yyyy-MM-dd HH:mi"));
		res.value.push(historyDatas[i].value)
	}
	return res;
}