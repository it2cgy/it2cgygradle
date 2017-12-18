var time1,
    myChart;
$(function(){
	 if (jQuery().datepicker) {
         $('.date-picker').datepicker({
             rtl: App.isRTL(),
             orientation: "left",
             autoclose: true
         });
         
         $('#start').datepicker("setDate",new Date()); 
     }
	 
	 setSelectMenu("menuequipmonitor","menuaxis");
	 require.config({
	        paths: {
	            echarts: window.ynz.cdnPath+'global/plugins/echarts/'
	        }
	    });
	 TableDatatablesButtons.init()
	 getPoints();
	 time1 = window.setInterval(getPoints,TimeOutTime);
	 window.onresize = function(){
    	setTimeout(function(){
			if(myChart){
				myChart.resize()
			}
		},100);
    }
});
function submitAddPoint (){
	window.clearInterval(time1);
	getPoints();
	var endTimeDate = $("#end").val();
	var startTimeDate = $("#start").val();
	if(timeUtil.dateToString(new Date(),"yyyy-MM-dd")==endTimeDate&&timeUtil.dateToString(new Date(),"yyyy-MM-dd")==startTimeDate){
		time1 = window.setInterval(getPoints,TimeOutTime);
	}
}
 /**
  * 获取指定设备的所有测点
  */
 function getPoints(){
	 console.log("-----");
 	var equipmentcontainerId = $("#equipmentcontainerId").val(); 
 	var model = 0; 
 	for(var i in window.ynz.trackList){
 		if(window.ynz.trackList[i].id==equipmentcontainerId){
 			model= window.ynz.trackList[i].model;
 		}
 	}
 	ynzAjax.post(
     		basePath+"pointInfo/getPointsNOPage.do",
     		{
     			"powerStationId":window.ynz.longieb.powerStationId,
     			"equipmentcontainerTableid":equipmenttypeid,
     			"equipmentcontainerId":equipmentcontainerId
     		},
     		function(response){   
     			var data = [];
     			for (var int = 0; int < response.data.length; int++) {
     				if(response.data[int].measurementtypeId=="1121"||response.data[int].measurementtypeId=="1122"){
     					if(model==1){
     						if(response.data[int].measurementtypeId=="1122"){
     							data.push({measuerName:response.data[int].measurementtypeName,
     								pointid:response.data[int].id,
     								description:response.data[int].measurementtypeDescription,
     								equipName:response.data[int].equipmentcontainerName});
     						}
     					}else{
     						data.push({measuerName:response.data[int].measurementtypeName,
     							pointid:response.data[int].id,
     							description:response.data[int].measurementtypeDescription,
     							equipName:response.data[int].equipmentcontainerName});
     					}
     				}
     			} 
     			refreshHistoryData(data);
 	        },
 	        function(e){ 
 	            console.log("--------error------"+e);
 	        }
     	);
 }


 var ComponentsDropdowns = function () {


     var handleMultiSelect = function () {
         $('#my_multi_select1').multiSelect();
     }

     return {
         //main function to initiate the module
         init: function () {            
             handleMultiSelect();
         }
     };

 }();
 function refreshHistoryData(hisdata){
		var pointMap = {};
		var startTime = $("#start").val()+" 00:00:00";
		var endTime ="";
		if($("#end").val()==timeUtil.dateToString(new Date(),"yyyy-MM-dd")){
			endTime = timeUtil.dateToString(new Date(),"yyyy-MM-dd hh:mi:ss");
		}else{
			endTime = $("#end").val()+" 23:59:59";
		}
		var timeInterval = 5;
		var analoginputIds="" ;//测点id
		for (var i = 0; i < hisdata.length; i++) { 
			if(analoginputIds==""){
				analoginputIds=analoginputIds+hisdata[i].pointid;
			}else{
				analoginputIds+=","+hisdata[i].pointid;
			}
		} 
//		console.log("测点数据---"+analoginputIds);
		var havaReal = 0;
		if($("#end").val()==timeUtil.dateToString(new Date(),"yyyy-MM-dd")){
			 havaReal = 1;
		}
		var paramObj = {
				startTime : startTime,
				endTime   : endTime,
				haveReal  : havaReal,
				analoginputIds:analoginputIds,
				minutesSpan:timeInterval
		}
		
	   var params = new Object();
		params.paramObj = paramObj;
		params.hisdata = hisdata;
		getecharsData(params);
//		if(timeUtil.dateToString(new Date(),"yyyy-MM-dd")==startTime.split(" ")[0]&&timeUtil.dateToString(new Date(),"yyyy-MM-dd")==endTime.split(" ")[0]){
//			window.setInterval(getecharsData,TimeOutTime,params);
//		}
	}
 function getecharsData(params){
	 	var paramObj = params.paramObj;
		var hisdata = params.hisdata;
		ynzAjax.post(
	    		basePath+"stationmonitor/historyRedress/points",
	    		paramObj,
	    		function(response){ 
//	    			console.log(response.data);
	    			var curveData = new Object();
					curveData.point=[];
					curveData.date=[];
					curveData.name=[];
	    			for(var i in response.data){
	    				var result = getPointData(response.data[i].historyDatas);
	    				if(i==0){
	    					curveData.date=result.time;
	    				}
	    				var name = "";
	    				for(var ii in hisdata){
	    					if(hisdata[ii].pointid==response.data[i].analoginputId){
	    						name = hisdata[ii].description;
	    					}
	    					if(i==0){
	    						curveData.name.push(hisdata[ii].description);
	    					}
	    				}
						curveData.point.push({"name": name,
							"symbol":"none",
							"smooth":true,
							"type": 'line',
							"data":result.value,
						});
	    			}
	    			setTableData(response.data,hisdata);
	    			refreshLineChart(curveData);
	    		},
		        function(e){ 
		            console.log("--------error------"+e);
		        }
	    )
 }
 function setTableData(data,hisdata){
	 var arr = [];
	 for(var i in data){
		 for(var ii in hisdata){
			 if(data[i].analoginputId==hisdata[ii].pointid){
				 var da = data[i].historyDatas;
				 for(var d in da){
					 var obj = new Object();
					 obj.equipName=hisdata[ii].equipName
					 obj.measuerName=hisdata[ii].description
					 obj.time=timeUtil.timeToString(da[d].time,"yyyy-mm-dd hh:mi");
					 obj.value=toDecimal(da[d].value,3);
					 arr.push(obj);
				 }
			 }
		 }
	 }
//	 console.log("arr---------->"+arr);
	 if(arr.length>0){
		 var table = $("#sample_1");  
		 table.dataTable().fnClearTable(); 
		 table.dataTable().fnAddData(arr); 
	 }
 }
 /**
  * 曲线数据转化成纵横坐标轴数组
  * @param historyDatas
  * @returns {___anonymous13869_13871}
  */
 function getPointData(historyDatas){
 	var res = new Object();
 	res.time=[];
 	res.value=[];
 	for(var i=0;i<historyDatas.length;i++){
 		res.time.push(timeUtil.timeToString(historyDatas[i].time,"yyyy-MM-dd HH:mi"));
 		res.value.push(toDecimal(historyDatas[i].value,3))
 	}
 	return res;
 }
	function refreshLineChart(curveData){ 
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
			    myChart = echarts.init(document.getElementById('echarts_line'));
			    myChart.setOption({
			        tooltip: {
			            trigger: 'axis'
			        },
			        legend: {//显示的数据列
			            data:curveData.name
			        },
			        toolbox: {
//			            show: true,
//			            feature: {
//			                mark: {
//			                    show: false
//			                },
//			                dataView: {
//			                    show: false,
//			                    readOnly: false
//			                },
//			                magicType: {
//			                    show: false,
//			                    type: ['line']
//			                },
//			                dataZoom : {show: false},
//	        	            restore : {show: false},
//			                saveAsImage: {
//			                    show: false
//			                }
//			            }
			        },
			        calculable: true,
			        animation:false,
			        xAxis: [{//x轴
			            type: 'category',
			            data: curveData.date
			        }],
			        yAxis: [{//y轴
			        	name:"（°）",
			            type: 'value',
			            splitArea: {
			                show: true
			            }
			        }],
			        series:curveData.point
			    }); 
	}
	        );
}
	/**
	 * 表格初始化
	 */
	var TableDatatablesButtons = function () {
		/**
		 * 初始化表格
		 */
	    var initTable1 = function () {
	        var table = $('#sample_1');//table id
	        var oTable = table.dataTable({
	        	"bAutoWidth":true,//设置自动计算列宽
	        	"bDeferRender":false,//设置是否延迟渲染  使用ajax或者js加载数据 开启延迟渲染可提升速度
	            "serverSide":false,//设置服务器端分页方式  false情况下默认使用前端插件分页
	            "info":true,
	            "paging":false,
	            "searching":false,
	            "sort":false,
	            "bFilter":false,
	            "scrollY":240,
	            /**
	             * 指定显示列
	             *  mDataProp 对应服务端字段名  
	             *  sTitle 列展示名称
	             *  visible true时显示当前列 设置false 隐藏当前列  不设置默认true
	             *  sClass 对齐方式 
	             */
	            "aoColumns": [
							{"mDataProp":"equipName",
							  	 "sTitle": window.ynz.local.index_equipmentname
							     },
							  {"mDataProp":"measuerName",
								 "sTitle": window.ynz.local.curve_pointname
							  },
							  {"mDataProp":"time",
								   "sTitle":window.ynz.local.pubtime,
								   "sClass": "center" //列对齐方式等 
							  },
							  {"mDataProp":"value", 
								   "sTitle": window.ynz.local.pubvalue },
							] ,
	           /**
	            * 设置右上角显示按钮 下列按钮为插件自带
	            */
	            buttons: [  
	            ],
	           
	    		"bLengthChange":false,//表格是否显示每页显示条数（5，10，15，20）设置为false时 用户无法自行更改页面显示条数
	            responsive: true,//？？？？
	            "order": [  //排序
	                [0, 'asc']
	            ],
//	          "iDisplayLength":5,  //页面显示行数  
//	          "sPaginationType": "full_numbers",  //翻页界面类型    太丑
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
	            /**
	             * 每页显示条数
	             */
	            "lengthMenu": [
	                [5, 10, 15, 20, -1],
	                [5, 10, 15, 20, "All"] // 在这里可以变换没页显示条数
	            ],
	            "dom": "<'row' <'col-md-12'B>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal scrollable datatable
	        });
	    }
	    function retrieveData(sSource, aoData, fnCallback, oSettings ) {
	    	/**
	    	 * 当前页码
	    	 */
	    	var page  = parseInt(Math.ceil(oSettings._iDisplayStart / oSettings._iDisplayLength))+1;
	    	/**
	    	 * 每页显示条数
	    	 */
	    	var pagesize =  oSettings._iDisplayLength;

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