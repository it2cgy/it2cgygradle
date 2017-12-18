var dataType = 0//实时数据
var downdata = [];
var downTitle ;
var curveImage="";
var columeImage="";
var pointsData = [];
var myChart1;
var myChart2;
var table_head="";
var table_table="";
$(function(){
	setSelectMenuRoot("menudatalistmanager");
	window.onresize = function(){
		setTimeout(resized,100);
	}
	var table = $('#sample_4');
//
//    var oTable = table.dataTable({
//
//        // Internationalisation. For more info refer to http://datatables.net/manual/i18n
//        "language": {
//            "aria": {
//                "sortAscending": ": activate to sort column ascending",
//                "sortDescending": ": activate to sort column descending"
//            },
//            "emptyTable": "No data available in table",
//            "info": "Showing _START_ to _END_ of _TOTAL_ entries",
//            "infoEmpty": "No entries found",
//            "infoFiltered": "(filtered1 from _MAX_ total entries)",
//            "lengthMenu": "_MENU_ entries",
//            "search": "Search:",
//            "zeroRecords": "No matching records found"
//        },
////
////        // Or you can use remote translation file
////        //"language": {
////        //   url: '//cdn.datatables.net/plug-ins/3cfcc339e89/i18n/Portuguese.json'
////        //},
////
////        // setup buttons extension: http://datatables.net/extensions/buttons/
//        buttons: [
//            { extend: 'print', className: 'btn dark btn-outline' },
//            { extend: 'pdf', className: 'btn green btn-outline' },
//            { extend: 'csv', className: 'btn purple btn-outline ' }
//        ],
//
////        // scroller extension: http://datatables.net/extensions/scroller/
//        scrollY:        300,
//        deferRender:    true,
//        scroller:       true,
//        deferRender:    true,
//        scrollX:        false,
//        scrollCollapse: true,  
//        "bAutoWidth":true,//设置自动计算列宽
//        "bFilter":false,//搜索
//        "bSort":false,//排序
//        "order": [
//            [0, 'asc']
//        ],
//        
//        "lengthMenu": [
//            [10, 15, 20, -1],
//            [10, 15, 20, "All"] // change per page values here
//        ],
//        // set the initial value
//        "pageLength": 10,
//
//        "dom": "<'row' <'col-md-12'B>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal scrollable datatable
//
//        // Uncomment below line("dom" parameter) to fix the dropdown overflow issue in the datatable cells. The default datatable layout
//        // setup uses scrollable div(table-scrollable) with overflow:auto to enable vertical scroll(see: assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js). 
//        // So when dropdowns used the scrollable div should be removed. 
//        //"dom": "<'row' <'col-md-12'T>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r>t<'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>",
//    });
//    
     
	TableDatatablesButtons.init();
//	TableDatatablesButtons2.init();
	FormValidation.init();
	 // ECHARTS
    require.config({
        paths: {
            echarts: window.ynz.cdnPath+'global/plugins/echarts/'
        }
    });

    if (jQuery().datepicker) {
        $('.date-picker').datepicker({
        	language:"zh-CN",
            rtl: App.isRTL(),
            orientation: "left",
            autoclose: true,
    	    format: "yyyy-mm-dd"
        }).click(function(){
         	  $('#startTime').datepicker('setEndDate', new Date());
		      $('#endTime').datepicker('setEndDate', new Date());
        });
        $('#startTime').datepicker("setDate",new Date()); 
        $('#endTime').datepicker("setDate",new Date()); 
    }
    $("#timeInterval").val(curveInfo.timeSpan);
    getPointDate();
    $("#downloadreport").click(function(){
	  	if(downdata.length>0){
	  		downLoaddataword(downdata);
	  	}else{
	  		message.alert( window.ynz.local.tip,window.ynz.local.history_clickquery,3);
	  	}
	  });
     $("#createreport").click(function(){
    	 var target = $("#createreport").attr("data-target");
    	 if(target==undefined){
    		 message.alert( window.ynz.local.tip,window.ynz.local.history_clickquery,3);
    	 }
     });
     

});
function resized(){
	if(myChart1){
		myChart1.resize();
	}
	if(myChart2){
		myChart2.resize();
	}
	if($("#sample_4")){
		$("#dataList").remove();
		$("#showlist").append('<div class="portlet-body table-both-scroll" id="dataList"> <table class="table table-striped table-bordered table-hover order-column" id="sample_4"> <thead id="sample_4_thead_thead"><tr id="sample_4_thead"></tr></thead><tbody id="sample_4_body"></tbody></table></div>');
		$("#sample_4_thead").append(table_head);
		$("#sample_4_body").append(table_table);
	}
}
/**
 * 生成报表
 */
function saveReport(){
	var paramObj={};
	var pointMap = {};
	paramObj.reportname=$("#reportName").val();
	paramObj.powerstationId=window.ynz.longieb.powerStationId;
	var startTime;
	var endTime;
	var mydate = new Date();
	if($("#startTime").val()==undefined){
		startTime = mydate.getFullYear()+"-"+(mydate.getMonth()+1)+"-"+mydate.getDate()+" 00:00:00";
	}else{
		startTime = $("#startTime").val()+" 00:00:00";
	}
	if($("#endTime").val()==undefined){
		endTime = mydate.getFullYear()+"-"+(mydate.getMonth()+1)+"-"+mydate.getDate()+" 23:59:59"; 
	}else{
		endTime = $("#endTime").val()+" 23:59:59"; 
	}
	var timeInterval = $("#timeInterval").val();
	if(timeInterval==null||timeInterval==''){
		message.alert( window.ynz.local.tip,window.ynz.local.pleaseselecttimeSpan,3);
		return;
	}
	var analoginputIds="" ;//测点id
	var length = curveInfo.length;
	if(length<=0){
		message.alert( window.ynz.local.tip,window.ynz.local.history_pleaseaddquerypoint,3);
		return;
	}
	paramObj.points=[];
	var ids = "";
	var point = curveInfo.pointList;
	for(var i=0;i<point.length;i++){
		if(i==0){
			ids+=curveInfo.pointList[i].analoginputId;
		}else{
			ids+=","+curveInfo.pointList[i].analoginputId;
		}
	}
	paramObj.analoginputIds=ids;
	paramObj.curvePoint=point;
	paramObj.startTime=startTime;
	paramObj.endTime=endTime;
	paramObj.curveImage=curveImage;
	paramObj.columeImage=columeImage;
	paramObj.haveReal=1;
	paramObj.reportData=""+JSON.stringify(downdata);
	paramObj.minutesSpan=timeInterval;
	var length = $('.table').children("tbody").find("tr").length;
	if(length<=0){
		message.alert( window.ynz.local.tip,window.ynz.local.history_pleaseaddquerypoint,3);
		return;
	}
	if(paramObj.curveImage==""||paramObj.columeImage==""){
		message.alert( window.ynz.local.tip,window.ynz.local.history_pleaseclickquery,3);
		return;
	}
	ynzAjax.post(
		window.ynz.path+"/reports/saveReport",
		paramObj,
		function(response){
			var id = response.data;
			message.alert( window.ynz.local.tip,window.ynz.local.history_generatedreport,3);
        },
        function(e){ 
            console.log("--------error------"+JSON.stringify(e));
        }
	 );
}
function downLoaddataword(downdata){
	var paramObj={};
	var pointMap = {};
	paramObj.reportname=$("#reportName").val();
	paramObj.powerstationId=window.ynz.longieb.powerStationId;
	var startTime;
	var endTime;
	var mydate = new Date();
	if($("#startTime").val()==undefined){
		startTime = mydate.getFullYear()+"-"+(mydate.getMonth()+1)+"-"+mydate.getDate()+" 00:00:00";
	}else{
		startTime = $("#startTime").val()+" 00:00:00";
	}
	if($("#endTime").val()==undefined){
		endTime = mydate.getFullYear()+"-"+(mydate.getMonth()+1)+"-"+mydate.getDate()+" 23:59:59"; 
	}else{
		endTime = $("#endTime").val()+" 23:59:59"; 
	}
	var timeInterval = $("#timeInterval").val();
	if(timeInterval==null||timeInterval==''){
		message.alert( window.ynz.local.tip,window.ynz.local.pleaseselecttimeSpan,3);
		return;
	}
	var analoginputIds="" ;//测点id
	var length = curveInfo.length;
	if(length<=0){
		message.alert( window.ynz.local.tip,window.ynz.local.history_pleaseaddquerypoint,3);
		return;
	}
	paramObj.points=[];
	var ids = "";
	var point = curveInfo.pointList;
	for(var i=0;i<point.length;i++){
		if(i==0){
			ids+=curveInfo.pointList[i].analoginputId;
		}else{
			ids+=","+curveInfo.pointList[i].analoginputId;
		}
	}
	paramObj.analoginputIds=ids;
	paramObj.curvePoint=point;
	paramObj.startTime=startTime;
	paramObj.endTime=endTime;
	paramObj.curveImage=curveImage;
	paramObj.columeImage=columeImage;
	paramObj.haveReal=1;
	paramObj.reportData=""+JSON.stringify(downdata);
	paramObj.minutesSpan=timeInterval;
	var length = $('.table').children("tbody").find("tr").length;
	if(length<=0){
		message.alert( window.ynz.local.tip,window.ynz.local.history_pleaseaddquerypoint,3);
		return;
	}
	if(paramObj.curveImage==""||paramObj.columeImage==""){
		message.alert( window.ynz.local.tip,window.ynz.local.history_pleaseclickquery,3);
		return;
	}
	ynzAjax.post(
		window.ynz.path+"/reports/reportInfo",
		paramObj,
		function(response){
			if(response.code==0){
				message.alert( window.ynz.local.tip,response.message,3);
			}
			var flag = response.data;
			if(flag){
				var form = $("<form>");
				form.empty();
				form.attr('style','display:none');
				form.attr('target','');
				form.attr('method','post');
				form.attr('action',basePath+"reports/datadownLoadwordReport.do");
				$('body').append(form);
				form.submit();
			}else{
				message.alert( window.ynz.local.tip,"查询数据为空",3);
			}
        },
        function(e){ 
            console.log("--------error------"+JSON.stringify(e));
        }
	 );
	
}
/**
 * 表格初始化
 */
var TableDatatablesButtons2 = function () {
	/**
	 * 初始化表格
	 */
	 var initTable2 = function () {
	        var table = $('#sample_list');

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
            initTable2();
        }
    };
}(); 
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
function setBarEchars(curveData){
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
            myChart1 = ec.init(document.getElementById('echarts_bar'));
            myChart1.setOption({
                title: {
                },
                tooltip: {
                    trigger: 'axis'
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
                animation:false,
                calculable: true,
                xAxis: [{
                    type: 'category',
                    boundaryGap: false,
                    data: curveData.date
              
                }],
                yAxis: curveData.yaxisData,
                series: curveData.barpoint
            });
            if(myChart1.getDataURL()!=null){
            	columeImage = "";
            	columeImage = myChart1.getDataURL('png');
			}
        }
    );
}
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
            myChart2 = ec.init(document.getElementById('echarts_line'));
            myChart2.setOption({
                title: {
                },
                tooltip: {
                    trigger: 'axis'
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
                animation:false,
                calculable: true,
                xAxis: [{
                    type: 'category',
                    boundaryGap: false,
                    data: curveData.date
                }],
                yAxis: curveData.yaxisData,
                series: curveData.point

            });
            if(myChart2.getDataURL()!=null){
            	curveImage = "";
            	curveImage = myChart2.getDataURL('png');
			}
        }
    );
}
function getPointDate(){
	console.log(curveInfo);
	var curveData = new Object();
	curveData.name = curveInfo.name;
	curveData.yaxisData=[];
	curveData.yaxisData[0]={"name":curveInfo.firstYaxisName,"type": "value",position: 'left'};
	if(curveInfo.type==2){
		curveData.yaxisData[1]={"name":curveInfo.secondYaxisName,"type": "value",position: 'right'};
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
	var endTime ="";
	var startTime ="";
	var timeSpanvalue ="";
	var haveReal=1;
	if(dataType==0){
		endTime = timeUtil.dateToString(new Date(),"yyyy-mm-dd hh:mi:ss");
		startTime = timeUtil.dateToString(new Date(),"yyyy-mm-dd 00:00:00");
		timeSpanvalue = curveInfo.timeSpan;
	}else{
		endTime = $("#endTime").val()+" 23:59:59";
		if(timeUtil.dateToString(new Date(),"yyyy-mm-dd")==$("#end").val()){
			haveReal=1;
		}else{
			haveReal=0;
		}
		startTime = $("#startTime").val()+" 00:00:00";
		timeSpanvalue = $("#timeInterval").val();
		if(timeInterval==null||timeInterval==''){
			message.alert( window.ynz.local.tip,window.ynz.local.pleaseselecttimeSpan,3);
			return;
		}
	}
	curveData.barpoint=[];
	curveData.point=[];
	curveData.date=[];
	curveData.color=[];
	


	var timeSpanS = parseInt(timeSpanvalue)*60;
	var startDate = timeUtil.stringToDate(startTime);
	var endDate = timeUtil.stringToDate(endTime);
	ynzAjax.post(
    		basePath+"stationmonitor/historyRedress/points.do",
    		{"analoginputIds":ids,
    		 "startTime":startTime,
    		 "endTime":endTime,
    		 "minutesSpan":timeSpanvalue,
    		 "haveReal":haveReal},
    		 //TODO
    		function(response){ 
    			 console.log(response.data);
    			 if(dataType==1){
    				 setTableList(point,response,startDate,endDate,timeSpanS);
    			 }
    			 for(var j=0;j<response.data.length;j++){
    				 for(var k=0;k<point.length;k++){
    					 var colorCode = point[k].colorCode;
    					 if(colorCode==null || colorCode==""){
    						 colorCode = "#15bda4"; //没颜色，就给给默认颜色，理论上不会执行到这
    					 }
    					 if(response.data[j].analoginputId==point[k].analoginputId){
    						 var result = getPointData(response.data[j].historyDatas,point,startDate,endDate,timeSpanS);
    						 curveData.date = result.time;
    						 if(point[k].yaxisType==0){
    							 curveData.point[j]={"name": point[k].pointName,
        								 "symbol":"none","smooth":"true",
        								 "type": 'line',"yAxisIndex": 0,"data":result.value,
        								 "itemStyle":{"normal":{"color":colorCode}}};
    							 curveData.barpoint[j]={"name": point[k].pointName,
        								 "symbol":"none","smooth":"true",
        								 "type": 'bar',"yAxisIndex": 0,"data":result.value,
        								 "itemStyle":{"normal":{"color":colorCode}}};
    						 }else if(point[k].yaxisType==1){
    							 curveData.point[j]={"name": point[k].pointName,
        								 "symbol":"none","smooth":"true",
        								 "type": 'line',"yAxisIndex": 1,"data":result.value,
        								 "itemStyle":{"normal":{"color":colorCode}}};
    							 curveData.barpoint[j]={"name": point[k].pointName,
        								 "symbol":"none","smooth":"true",
        								 "type": 'bar',"yAxisIndex": 1,"data":result.value,
        								 "itemStyle":{"normal":{"color":colorCode}}};
    						 }
    					 }
    				 }
    			 }
    			 setEchars(curveData);
    			 if(dataType==1){
    				 setBarEchars(curveData);
    			 }
	        },
	        function(e){ 
	            console.log("--------error------"+e);
	        }
    	)
}
function getPointData(historyDatas,point,startDate,endDate,timeSpanS){
	var res = new Object();
	res.time=[];
	res.value=[];
	var dataTimeMap = {};
	/**
	 * 开始时间到结束时间
	 */
	var  statDateTime = startDate.getTime()/1000/timeSpanS*timeSpanS;
	var  endDateTime = endDate.getTime()/1000;
	if(historyDatas.length==0){
		res.time.push(timeUtil.timeToString(new Date(),"yyyy-MM-dd HH:mi"));
		res.value.push(0)
	}else if(historyDatas.length==1){
		res.time.push(timeUtil.timeToString(new Date(),"yyyy-MM-dd HH:mi"));
		res.value.push(0)
	}else{
		for(var i=0;i<historyDatas.length;i++){
			var hisData=historyDatas[i];
			var timeKey = timeUtil.timeToString(hisData.time,"yyyy-mm-dd HH:mi");
			dataTimeMap[timeKey]=toDecimal(historyDatas[i].value,3); 
		}
		for(var start=statDateTime;start<=endDateTime;start=start+timeSpanS){
			var timeKey = timeUtil.timeToString(start*1000,"yyyy-mm-dd hh:mi");
			res.time.push(timeKey);
			if(dataTimeMap[timeKey]==undefined){
				res.value.push(0);
			}else{
				res.value.push(dataTimeMap[timeKey]);
			}
		}
	}
	return res;
}
function searchbtn(){
	var radio = $("input[name='dataType']");
	for(var i in radio){ 
		if(radio[i].checked){
			dataType=radio[i].value;
		}
	}
	console.log(dataType);
	if(dataType==1){
		$("#showbar").show();
		$("#showlist").show();
	}
	if(dataType==0){
		$("#showbar").hide();
		$("#showlist").hide();
	}
	getPointDate();
}
function powerModel(){
	dataType = 0
	$("#historyCondition").hide();
	$('#start').datepicker("setDate",new Date()); 
	$('#end').datepicker("setDate",new Date()); 
	$("#searchbutton").attr("disabled",true);
	$("#createreport").attr("disabled",true);
	$("#downloadreport").attr("disabled",true);
	$("#startTime").attr("disabled",true);
	$("#endTime").attr("disabled",true);
	$("#timeInterval").val(curveInfo.timeSpan);
	$("#timeInterval").attr("disabled",true);
	getPointDate();
	$("#showbar").hide();
	$("#showlist").hide();
}
function generatModel(){
	$("#historyCondition").show();
	$("#searchbutton").attr("disabled",false);
	$("#createreport").attr("disabled",false);
	$("#downloadreport").attr("disabled",false);
	$("#startTime").attr("disabled",false);
	$("#endTime").attr("disabled",false);
	$("#timeInterval").attr("disabled",false);
}

function setTableList(pointinfo,pointvalue,startDate,endDate,timeSpanS){
	var  statDateTime = startDate.getTime()/1000/timeSpanS*timeSpanS;
	var  endDateTime = endDate.getTime()/1000;
	var dataTimeMap = {};
	var dataArray = [];
	//1 统一处理时间
	for(var start=statDateTime;start<=endDateTime;start=start+timeSpanS){
		var timeKey = timeUtil.timeToString(start*1000,"yyyy-mm-dd hh:mi");
		dataTimeMap[timeKey]=[timeKey]; 
		dataArray.push(dataTimeMap[timeKey]);
		for(var i=0;i<pointinfo.length;i++){
			dataTimeMap[timeKey].push("--");
		}
	}
	//2 按时间整合数据
	for(var i=0;i<pointinfo.length;i++){
		for(var j=0;j<pointvalue.data[i].historyDatas.length;j++){
			var hisData = pointvalue.data[i].historyDatas[j];
			var timeKey = timeUtil.timeToString(hisData.time,"yyyy-mm-dd hh:mi");
			var timeDataList = dataTimeMap[timeKey]; 
			if(timeDataList){
				timeDataList[i+1]=toDecimal(hisData.value,3);
			}
		}
	}
	//3 过滤无效数据
	var dataTemp = [];
	for(var i=0;i<dataArray.length;i++){
		for(var j=1;j<dataArray[i].length;j++){
			 if(dataArray[i][j]!=="--"){
				 dataTemp.push(dataArray[i]);
				 break;
			 }
		}  
	}
	dataArray = dataTemp;
 
	var downArr=[];
	downArr.push("日期");
	downArr.push("时间");
	$("#dataList").remove();
	table_head="";
	table_table="";
	$("#showlist").append('<div class="portlet-body table-both-scroll" id="dataList"> <table class="table table-striped table-bordered table-hover order-column" id="sample_4"> <thead id="sample_4_thead_thead"><tr id="sample_4_thead"></tr></thead><tbody id="sample_4_body"></tbody></table></div>');
	/**
	 * 组装表格表头
	 */  
	$("#sample_4_thead").append("<th>"+window.ynz.local.index_theday+"</th><th>"+window.ynz.local.pubtime+"</th>");
	table_head+="<th>"+window.ynz.local.index_theday+"</th><th>"+window.ynz.local.pubtime+"</th>";
	for(var i=0;i<pointinfo.length;i++){
		table_head+="<th>"+pointinfo[i].equipmentNumber+"  "+pointinfo[i].pointName+"</th>";
		$("#sample_4_thead").append("<th>"+pointinfo[i].equipmentNumber+"  "+pointinfo[i].pointName+"</th>");
		downArr.push(pointinfo[i].equipmentNumber+pointinfo[i].pointName);
	} 
	var data1Array=[];
	for(var i=0;i<dataArray.length;i++){ 
		var data2=[];
		var data_tr = "<tr>";
		for(var j=0;j<dataArray[i].length;j++){ 
			if(j==0){
				data_tr+="<td>"+dataArray[i][j].split(" ")[0].replace(/-/g,"/")+"</td>";
				data_tr+="<td>"+dataArray[i][j].split(" ")[1]+"</td>";
				data2.push(dataArray[i][j].split(" ")[0].replace(/-/g,"/"));
				data2.push(dataArray[i][j].split(" ")[1]);
			}else{
				data_tr+="<td>"+dataArray[i][j]+"</td>";
				data2.push(dataArray[i][j]);
			}
		} 
		data1Array.push(data2);
		data_tr+="</tr>";  
		$("#sample_4_body").append(data_tr);
		table_table+=data_tr;
	}
	dataArray = data1Array;
	dataArray.unshift(downArr);
	downdata = dataArray;
	 
	$(".odd").children().css('white-space','pre');
	if(dataArray.length!=0){
		$("#createreport").attr("data-target","#reportModel");
	}
	var table=$("#sample_4");
	var oTable = table.dataTable({
		"bAutoWidth":true,//设置自动计算列宽
	    // Internationalisation. For more info refer to http://datatables.net/manual/i18n
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
           		"excel":"excel",
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
	// 
	    buttons: [
	        { extend: 'print', className: 'btn dark btn-outline' },
	        { extend: 'pdf', className: 'btn green btn-outline' },
	        { extend: 'csv', className: 'btn purple btn-outline ' }
	    ],
	
	//    // scroller extension: http://datatables.net/extensions/scroller/
	    scrollY:        300,
	    deferRender:    true,
	    scroller:       true,
	    deferRender:    true,
	    scrollX:        false,
	    scrollCollapse: true,  
	    "bAutoWidth":true,//设置自动计算列宽
	    "bFilter":false,//搜索
	    "bSort":false,//排序
	    "order": [
	        [0, 'asc']
	    ],
	    
	    "lengthMenu": [
	        [10, 15, 20, -1],
	        [10, 15, 20, "All"] // change per page values here
	    ],
	    // set the initial value
	    "pageLength": 10,
	
	    "dom": "<'row' <'col-md-12'B>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal scrollable datatable
	
	    // Uncomment below line("dom" parameter) to fix the dropdown overflow issue in the datatable cells. The default datatable layout
	    // setup uses scrollable div(table-scrollable) with overflow:auto to enable vertical scroll(see: assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js). 
	    // So when dropdowns used the scrollable div should be removed. 
	    //"dom": "<'row' <'col-md-12'T>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r>t<'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>",
	});

}
var FormValidation = function () {
    // advance validation
    var handleValidation1 = function() { 
            var form_1 = $('#form_1');
            var error3 = $('.alert-danger', form_1);
            var success3 = $('.alert-success', form_1);

            //IMPORTANT: update CKEDITOR textarea with actual content before submit

            form_1.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block help-block-error', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "", // validate all fields including form hidden input
                rules: { 
                    startTime: {
                    	required: true
                    },
                    endTime: {
                    	required: true
                    },
//                    secondYaxisName: {//动态添加
//                        required: true
//                    },
                    timeSpan: {
                        required: true
                    } 
                },

                messages: { // custom messages for radio buttons and checkboxes 
                    startTime: {
                        required: window.ynz.local.history_pleaseselectstarttime
                    },
                    endTime: {
                        required: window.ynz.local.history_pleaseselectendtime
                    },
                    timeSpan: {
                        required: window.ynz.local.history_pleaseselecttimeinterval
                    } 
                },

                errorPlacement: function (error, element) { // render error placement for each input type
                    if (element.parent(".input-group").size() > 0) {
                        error.insertAfter(element.parent(".input-group"));
                    } else if (element.attr("data-error-container")) { 
                        error.appendTo(element.attr("data-error-container"));
                    } else if (element.parents('.radio-list').size() > 0) { 
                        error.appendTo(element.parents('.radio-list').attr("data-error-container"));
                    } else if (element.parents('.radio-inline').size() > 0) { 
                        error.appendTo(element.parents('.radio-inline').attr("data-error-container"));
                    } else if (element.parents('.checkbox-list').size() > 0) {
                        error.appendTo(element.parents('.checkbox-list').attr("data-error-container"));
                    } else if (element.parents('.checkbox-inline').size() > 0) { 
                        error.appendTo(element.parents('.checkbox-inline').attr("data-error-container"));
                    } else {
                        error.insertAfter(element); // for other inputs, just perform default behavior
                    }
                },

                invalidHandler: function (event, validator) { //display error alert on form submit   
                    success3.hide();
                    error3.show();
                    App.scrollTo(error3, -200);
                },

                highlight: function (element) { // hightlight error inputs
                   $(element)
                        .closest('.form-group').addClass('has-error'); // set error class to the control group
                },

                unhighlight: function (element) { // revert the change done by hightlight
                    $(element)
                        .closest('.form-group').removeClass('has-error'); // set error class to the control group
                },

                success: function (label) {
                    label
                        .closest('.form-group').removeClass('has-error'); // set success class to the control group
                },
                
                submitHandler: function (form) {
                    success3.show();
                    error3.hide();
                    charts.queryData();
                    $("#createreport").attr("data-target","#reportModel");
                }

            });
 
            $('#type').change(function() {
            	if($('.form-control.select5me').val()==1){
            		$("#curvesecond").attr("style");
            		$("#curvesecond").css("display","none");
            		
            		$("#secondYaxisName").rules("remove");
            	}else{
            		$("#curvesecond").css("display","block");
            		$("#curvesecond").removeAttr("style");
            		$("#secondYaxisName").rules("add",{required:true,messages:window.ynz.local.curve_required_yname});
            	}
            })
            
             //initialize datepicker
            $('.date-picker').datepicker({
                rtl: App.isRTL(),
                autoclose: true
            });
            $('.date-picker .form-control').change(function() {
            	form_1.validate().element($(this)); //revalidate the chosen dropdown value and show error or success message for the input 
            })
    	}
    
    
    var handleValidation2 = function() { 
        var form_report = $('#form_report');
         
        //IMPORTANT: update CKEDITOR textarea with actual content before submit

        form_report.validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block help-block-error', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "", // validate all fields including form hidden input
            rules: { 
            	reportName: {
                	required: true
                } 
            },

            messages: { // custom messages for radio buttons and checkboxes 
            	reportName: {
                    required: window.ynz.local.history_pleaseinputreportname
                } 
            },

            errorPlacement: function (error, element) { // render error placement for each input type
                if (element.parent(".input-group").size() > 0) {
                    error.insertAfter(element.parent(".input-group"));
                } else if (element.attr("data-error-container")) { 
                    error.appendTo(element.attr("data-error-container"));
                } else if (element.parents('.radio-list').size() > 0) { 
                    error.appendTo(element.parents('.radio-list').attr("data-error-container"));
                } else if (element.parents('.radio-inline').size() > 0) { 
                    error.appendTo(element.parents('.radio-inline').attr("data-error-container"));
                } else if (element.parents('.checkbox-list').size() > 0) {
                    error.appendTo(element.parents('.checkbox-list').attr("data-error-container"));
                } else if (element.parents('.checkbox-inline').size() > 0) { 
                    error.appendTo(element.parents('.checkbox-inline').attr("data-error-container"));
                } else {
                    error.insertAfter(element); // for other inputs, just perform default behavior
                }
            },

            invalidHandler: function (event, validator) { //display error alert on form submit    
               // App.scrollTo(error3, -200);
            },

            highlight: function (element) { // hightlight error inputs
               $(element)
                    .closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            unhighlight: function (element) { // revert the change done by hightlight
                $(element)
                    .closest('.form-group').removeClass('has-error'); // set error class to the control group
            },

            success: function (label) {
                label
                    .closest('.form-group').removeClass('has-error'); // set success class to the control group
            },
            
            submitHandler: function (form) { 
            		saveReport();
            	$("#reportModelclose").click();
            	//message.alert( window.ynz.local.tip,"保存成功！",3);
            }

        });
  
	}

    
    var handleWysihtml5 = function() {
        if (!jQuery().wysihtml5) {            
            return;
        }

        if ($('.wysihtml5').size() > 0) {
            $('.wysihtml5').wysihtml5({
                "stylesheets": ["../assets/global/plugins/bootstrap-wysihtml5/wysiwyg-color.css"]
            });
        }
    }

    return {
        //main function to initiate the module
        init: function () {
            handleWysihtml5();
//            handleValidation1();
            handleValidation2();
            
        }

    };

}();