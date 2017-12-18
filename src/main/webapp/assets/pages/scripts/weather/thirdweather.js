$(function(){
	$('#select1').multiSelect({checkAllText:""});
	setSelectMenu("menuequipmonitor","menuweatherstation");
	weatherInfo();
	window.setInterval(weatherInfo,TimeOutTime); 
	ComponentsDropdowns.init(); 
	getPoints();
});



var hasInit = false;
var selectData = [];

var data=[]; 
var colors=["#36C6D3","#3598dc","#26c281","#d91e18","#525e64","#e7505a","#9a12b3","#f3c200"];
function refreshTableData(data){
	var table = $('#sample_1');  
    table.dataTable().fnClearTable(); 
    if(data.length>0){ 
        table.dataTable().fnAddData(data);
    }
}


$("#stack1closBtn").click(function(){

	$('#select1').val(selectData);
	$('#select1').multiSelect("refresh");
});

$("#cancelBtn").click(function(){
	$("#stack1closBtn").click();
});

$("#checkdata").click(function(){ 
    selectData = $('#select1').val();
    if(!selectData || selectData==""){
    	selectData = [];
    }
    var out = [];
    for (var i = 0; i < data.length; i++) {
    	if(data[i].measuerName.indexOf("TemperatureOfModule")==0){
    		for(var j=0;j<selectData.length;j++){
    			if(data[i].measuerName==selectData[j]){ 
    	    		var time = parseInt(data[i].time);
    	    		var timeStr = timeUtil.timeToString(time/1000,"yyyy-MM-dd HH:mi:ss");
    				var value =toDecimal(data[i].value,3); 
    				out.push( [ data[i].measuerName,timeStr ,value] );
    				break;
    			}
    		}
    	}
	} 
    refreshHistoryData();
    refreshTableData(out);
    
    $("#stack1closBtn").click();
});

function refreshHistoryData(){
	var pointMap = {};
	var dataNow = new Date();
	var startTime = timeUtil.dateToString(dataNow,"yyyy-MM-dd")+" 00:00:00";
	var endTime   =   timeUtil.dateToString(dataNow,"yyyy-MM-dd HH:mi:ss");
	var timeInterval = 5;
	var analoginputIds="" ;//测点id
	
	for (var i = 0; i < data.length; i++) { 
		for(var j=0;j<selectData.length;j++){
			if(data[i].measuerName==selectData[j]){
				if(analoginputIds===""){
					analoginputIds=analoginputIds+data[i].pointid;
				}else{
					analoginputIds+=","+data[i].pointid;
				}
				break;
			}
		} 
	} 
	
	console.log("测点数据---"+analoginputIds);
	var paramObj = {
			startTime : startTime,
			endTime   : endTime,
			haveReal  : 0,
			analoginputIds:analoginputIds,
			minutesSpan:timeInterval
	}
	
   	ynzAjax.post(
    		basePath+"stationmonitor/historyRedress/points",
    		paramObj,
    		function(response){ 
    			var legendData = [];
    			var time = []; 
    			var seriesLine=[];
				//console.log(JSON.stringify(response.data));
				var out = [];
				var dataTableStr ="";
    			for(var i = 0;i<response.data.length;i++){
    				var serieDatas=[]; 
    				var seriesLineData={} 
    				var pointName ="";
    				for (var j = 0; j < data.length; j++) { 
    					 if(response.data[i].analoginputId===data[j].pointid){
    						 pointName = data[j].measuerName;
    					 }
    				} 
    				var colorindex = i%7;
    				var color = colors[colorindex];
    				legendData[i]=pointName;
    				seriesLineData.name=pointName;
    				seriesLineData.type="line";
    				seriesLineData.itemStyle={"normal":{"color":color}}; 
    				for(var j=0;j<response.data[i].historyDatas.length;j++){ 
    					var timeStr = timeUtil.timeToString(response.data[i].historyDatas[j].time,"yyyy-MM-dd HH:mi");
    					var value = response.data[i].historyDatas[j].value;
    					time[j]=timeStr;
    					serieDatas[j]=toDecimal(value,3); 
    				} 
    				seriesLineData.data=serieDatas;
    				seriesLine[i]=seriesLineData;
    			}
    			 
    			refreshLineChart(legendData,time,seriesLine);
    		},
	        function(e){ 
	            console.log("--------error------"+e);
	        }
    )
}

function refreshLineChart(_legendData,_xdata,_series){ 
		if(_legendData.length>10){
			for(var i=10;i<_legendData.length;i++){
				_legendData[i]="";
			}
		}
		 var myChart = echarts.init(document.getElementById('echarts_line'));
		    myChart.setOption({
		        tooltip: {
		            trigger: 'axis'
		        },
		        legend: {//显示的数据列
		            data:_legendData,
		            padding:[30,0,0,0],
		        },
                toolbox: {
                    show: true,
                    feature: {
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
		        animation:false,
		        xAxis: [{//x轴
		            type: 'category',
		            data: _xdata
		        }],
		        yAxis: [{//y轴
		        	name:"（℃）",
		            type: 'value',
		            splitArea: {
		                show: true
		            }
		        }],
		        series:_series
		    }); 
}
/**
 * 获取指定设备的所有测点
 */
function getPoints(){
	console.log(thirdPoints);
		for (var int = 0; int < thirdPoints.length; int++) {
			data.push({measuerName:thirdPoints[int].pointType,time:0,value:0,pointid:thirdPoints[int].pointId});
		} 
		var html = "";
		for (var i = 0; i < data.length; i++) {
			html+="<option value='"+ data[i].measuerName +"'>"+data[i].measuerName+"</option>"
			if(selectData.length<=0){
				selectData.push(data[i].measuerName);
			}
		}
		$('#select1').empty();
		$("#select1").append(html);
		$('#select1').val(selectData);
		$('#select1').multiSelect("refresh");
		
		
		queryData();
		refreshHistoryData();
}
//function getPoints(){
//	var equipmentcontainerTableid = $("#equipmenttypeid").val();
//	var equipmentcontainerId = $("#id").val(); 
//	ynzAjax.post(
//    		basePath+"pointInfo/getPointsNOPage.do",
//    		{
//    			"powerStationId":window.ynz.longieb.powerStationId,
//    			"equipmentcontainerTableid":equipmentcontainerTableid,
//    			"equipmentcontainerId":equipmentcontainerId
//    		},
//    		function(response){   
//    			for (var int = 0; int < response.data.length; int++) {
//    				if(response.data[int].measurementtypeName.indexOf("TemperatureOfModule")==0){
//        				data.push({measuerName:response.data[int].measurementtypeName,time:0,value:0,pointid:response.data[int].id});
//    				}
//    			} 
//    			var html = "";
//        		for (var i = 0; i < data.length; i++) {
//        			html+="<option value='"+ data[i].measuerName +"'>"+data[i].measuerName+"</option>"
//        			if(selectData.length<=0){
//            			selectData.push(data[i].measuerName);
//        			}
//        		}
//        		$('#select1').empty();
//        		$("#select1").append(html);
//        		$('#select1').val(selectData);
//        		$('#select1').multiSelect("refresh");
//        		
//        		
//    			queryData();
//    			refreshHistoryData();
//	        },
//	        function(e){ 
//	            console.log("--------error------"+e);
//	        }
//    	);
//}

function queryData(){
	var isThirdUser = null;
//	if(window.ynz.isThird==true || window.ynz.isThird==1){
//		isThirdUser="0";
//	}
	ynzAjax.post(
		basePath+"stationmonitor/real/equipment.do",
		{"equipmentcontainerId":$("#id").val(),"equipmentcontainerTableid":$("#equipmenttypeid").val(),"isThirdUser":isThirdUser},
		function(response){ 
		//data = [];
        var j=0;
        var out = [];
        for (var i = 0; i < response.data.length; i++) {
        	if(response.data[i].measuerName.indexOf("TemperatureOfModule")==0){
        		var time = parseInt(response.data[i].time)*1000;
        		//var timeStr = timeUtil.timeToString(time,"yyyy-MM-dd HH:mi:ss");
				var value =response.data[i].value;
				//data.push({measuerName:response.data[i].measuerName,time:timeStr,value:value});
				for (var j = 0; j < data.length; j++) {
					console.log();
    				if(data[j].measuerName===response.data[i].measuerName){
    					data[j].time=time;
    					data[j].value=toDecimal(value,3);
        				//data.push({measuerName:response.data[i].measurementtypeName,time:"",value:0});
    				}
    			}  
//				out.push( [ response.data[i].measuerName,timeStr ,value] );
        	}
		}
        
        for (var i = 0; i < data.length; i++) { 
			for(var j=0;j<selectData.length;j++){
    			if(data[i].measuerName==selectData[j]){ 
    	    		var time = parseInt(data[i].time);
    	    		var timeStr = timeUtil.timeToString(time/1000,"yyyy-MM-dd HH:mi:ss");
    				var value =toDecimal(data[i].value,3); 
    				out.push( [ data[i].measuerName,timeStr ,value] );
    				
    				break;
    			}
    		}
		} 
        
        if(data.length>0 && !hasInit){
        	
        	 var table = $('#sample_1');

 	        var oTable = table.dataTable({
 	       	    "bFilter":false,//搜索
 	            "bSort":false,//排序
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
// 		           	"buttons":{
// 		           		"print":window.ynz.local.table_print,
// 		           		"copy":window.ynz.local.table_copy,
// 		           		"pdf":window.ynz.local.table_pdf,
// 		           		"excel":window.ynz.local.table_excel+"excel",
// 		           		"csv":"csv",
// 		           		"colvis":"显示/隐藏列"
// 		           	},
	 		       	"oPaginate":{
		           		"sFirst":window.ynz.local.table_sfirst,
		           		"sPrevious":window.ynz.local.table_sprevious,
		           		"sNext":window.ynz.local.table_snext,
		           		"sLast":window.ynz.local.table_slast
		           	},
	 		           	"sProcessing": window.ynz.local.table_sProcessing, 
 		              },
 	            buttons: [
// 	                { extend: 'pdf', className: 'btn green btn-outline' },
// 	                { extend: 'excel', className: 'btn green btn-outline ' }
 	            ],
 	            scrollY:        250,
 	            deferRender:    true,
 	            scroller:       true,
 	            stateSave:      true,
 	            bInfo:			true,
 	            "order": [
 	                [0, 'asc']
 	            ],
 	            "lengthMenu": [
 	                [10, 15, 20, -1],
 	                [10, 15, 20, "All"]
 	            ],
 	            "pageLength": 10,
 	            "dom": "<'row' <'col-md-12'B>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal scrollable datatable
 	           
 	        }); 
 	        
        	
    		hasInit = true;
    	}
        refreshTableData(out);
    //	var returnData={};
//    	returnData.aaData=data;//设置列表数据
//    	returnData.iTotalRecords =data.length;//设置总数
//    	returnData.iTotalDisplayRecords =data.length;//设置显示的数据总数  此属性需要与iTotalRecords相同 并且必须配置
//    	//returnData.iDisplayLength= 5;//设置每页显示条数
//        fnCallback(returnData);  
        },
        function(e){ 
            console.log("--------error------"+e);
        }
	)
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
function splitNumber(value){
	if(value<0.01){
		return toDecimal(value,3);
	}else{
		return toDecimal(value,2);
	}
}
/**
 * 获取电站气象数据
 */
function weatherInfo(){
	 ynzAjax.get(
				window.ynz.path+"/powerStation/getWeatherInfo/"+window.ynz.longieb.powerStationId+".do",
				function(response){ 
					console.log(response.data);
					for(var data in response.data){
						if(data=="inclinedExposure"){
							var value = splitNumber(response.data[data]);
							$("#"+data).html(value);
							$("#inclinedExposureDaily").html(value);
							continue;
						}
						if($("#"+data)){
							var value = splitNumber(response.data[data]);
							$("#"+data).html(value);
						}
					}
		        },
		        function(e){ 
		            console.log("--------error------"+e);
		        }
		    );
}
