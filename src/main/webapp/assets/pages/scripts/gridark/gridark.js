var threePhaseActivePowerId="";
var generationDailyId="";
var generationMonthId="";
var generationYearId="";
var myChart2;

$(function(){
	window.onresize = function(){
		setTimeout(function(){
			if(myChart2){
				myChart2.resize()
			}
		},100);
	}
	if (jQuery().datepicker) {
        $('.date-picker').datepicker({
            rtl: App.isRTL(),
            orientation: "left",
            autoclose: true,
            defaultDate:new Date()
        });
        $('#start').datepicker("setDate",new Date()); 
    }
	if(jQuery().datetimepicker){
		$('#yearType').datetimepicker({
			autoclose: true,
			startView:4,
			maxView:4,
			minView:4,
			viewSelect:4,
			isRTL: App.isRTL(),
			format: "yyyy",
			pickerPosition: (App.isRTL() ? "bottom-right" : "bottom-left"),
			forceParse:0
		});
		$('#monthType').datetimepicker({
			  autoclose: true,
			  startView:3,
			  maxView:3,
			  minView:3,
			  viewSelect:3,
	          isRTL: App.isRTL(),
	          format: "yyyy-mm",
	          pickerPosition: (App.isRTL() ? "bottom-right" : "bottom-left"),
	          forceParse:0
	     });
		$('#dayType').datetimepicker({
			  autoclose: true,
			  startView:2,
			  maxView:2,
			  minView:2,
			  viewSelect:2,
	          isRTL: App.isRTL(),
	          format: "yyyy-mm-dd",
	          pickerPosition: (App.isRTL() ? "bottom-right" : "bottom-left"),
	          forceParse:0
	     });
		$('#startyear').val(timeUtil.dateToString(new Date(),'yyyy'));
		$('#startmonth').val(timeUtil.dateToString(new Date(),'yyyy-mm'));
		$('#startday').val(timeUtil.dateToString(new Date(),'yyyy-mm-dd'));
	}
	setSelectMenu("menuequipmonitor","menugridark");
	TableDatatablesButtons1.init()
//	console.log(powerStationId);
	window.setInterval($("#sample_1").DataTable().ajax.reload,60000);
	 // ECHARTS

});
function dateTypeChange(dateIndex){
	if(dateIndex==0){ 
		$("#yearType").show();
		$("#monthType").hide();
		$("#dayType").hide();
	}
	if(dateIndex==1){
		$("#yearType").hide();
		$("#monthType").show();
		$("#dayType").hide();
	}
	if(dateIndex==2){
		$("#yearType").hide();
		$("#monthType").hide();
		$("#dayType").show();
	}
}
function powerModel(){
	$("#powerModelDiv").show();
	$("#generatModelDiv").hide();
	
}
function generatModel(){
	$("#powerModelDiv").hide();
	$("#generatModelDiv").show();
	 $('#yearType').datetimepicker({
		  autoclose: true,
		  startView:4,
		  maxView:4,
		  minView:4,
		  viewSelect:4,
          isRTL: App.isRTL(),
          format: "yyyy",
          pickerPosition: (App.isRTL() ? "bottom-right" : "bottom-left"),
          forceParse:0
     });
}
function timeEchars(params){
	getEcharsData(params.startstr,params.endstr,params.pointId,params.pointType,params.index,1);
}
function submitAddPoint(){
	var startstr=$("#start").val();
	var endstr=$("#end").val();
	var pointType=0;
	var radio = $("input[name='curveType']");
	for(var i in radio){
		if(radio[i].checked){
			pointType=radio[i].value;
		}
	}
	var timer = "";
	if(pointType==0){
		pointId = threePhaseActivePowerId;
		if(timeUtil.dateToString(new Date(),"yyyy-mm-dd")==endstr){
			endstr = timeUtil.dateToString(new Date(),"yyyy-mm-dd hh:mi:ss")
			getEcharsData(startstr+" 00:00:00",endstr,pointId,pointType,0,1);
		}else{
			getEcharsData(startstr+" 00:00:00",endstr+" 23:59:59",pointId,pointType,0,0);
			if(timeUtil.dateToString(new Date(),"yyyy-mm-dd")==startstr){
				var params = new Object();
				params.startstr = startstr+" 00:00:00";
				params.endstr = endstr+" 23:59:59";
				params.pointId = pointId;
				params.pointType = pointType;
				params.index = 0;
				var timer = window.setInterval(timeEchars,TimeOutTime,params);
			}
		}
	}else{
		if(timer!=""){
			clearInterval(timer);
		}
		var dateType = 0;
		pointId = generationDailyId;
		var dateradio = $("input[name='DateType']");
		for(var i in dateradio){
			if(dateradio[i].checked){
				dateType=dateradio[i].value;
			}
		}
		var dateTime="";
		if(dateType==0){
			dateTime=$("#startyear").val();
			pointId = generationYearId;
			var enddateTime = dateTime;
			if(timeUtil.dateToString(new Date(),"yyyy")==dateTime){
				enddateTime = timeUtil.dateToString(new Date(),"yyyy-mm-dd hh:mi:ss")
				getEcharsData(dateTime+"-01-01 00:00:00",enddateTime,pointId,pointType,2,1);
			}else{
				enddateTime = dateTime+"-12-31 23:59:59";
				getEcharsData(dateTime+"-01-01 00:00:00",enddateTime,pointId,pointType,2,0);
			}
			
		}else if(dateType==1){
			pointId = generationMonthId;
			dateTime=$("#startmonth").val();
			var enddateTime = dateTime;
			if(timeUtil.dateToString(new Date(),"yyyy-mm")==dateTime){
				enddateTime = timeUtil.dateToString(new Date(),"yyyy-mm-dd hh:mi:ss")
				getEcharsData(dateTime+"-01 00:00:00",enddateTime,pointId,pointType,1,1);
			}else{
				var ddate = new Date(dateTime.split("-")[0]+"-"+(parseInt(dateTime.split("-")[1])+1)+"-01 00:00:00");
				var ttime = Date.parse(ddate);
				enddateTime = timeUtil.timeToString(ttime-1000,"yyyy-mm-dd hh:mi:ss");
				getEcharsData(dateTime+"-01 00:00:00",enddateTime,pointId,pointType,1,0);
			}
		}else{
			dateTime=$("#startday").val();
			var enddateTime = dateTime;
			if(timeUtil.dateToString(new Date(),"yyyy-mm-dd")==dateTime){
				enddateTime = timeUtil.dateToString(new Date(),"yyyy-mm-dd hh:mi:ss")
				getEcharsData(dateTime+" 00:00:00",enddateTime,pointId,pointType,0,1);
			}else{
				getEcharsData(dateTime+" 00:00:00",enddateTime+" 23:59:59",pointId,pointType,0,0);
			}
		}
		console.log(dateTime);
	}
}
//当天历史功率
function getEcharsData(startTime,endTime,powerId,pointType,dateIndex,haveReal){
	var url ="";
	if(dateIndex==0){
		if(pointType==0){
			url = window.ynz.basePath+"stationmonitor/historyRedress.do";
		}else{
			url = window.ynz.basePath+"pointInfo/getPointHoursGeneration.do";
		}
	}else if(dateIndex==1){
		url = window.ynz.basePath+"pointInfo/getPointMonthgeneration.do";
	}else{
		url = window.ynz.basePath+"pointInfo/getPointYearGeneration.do";
	}
	ynzAjax.post(//电表当天功率历史
			url,
			{   "analoginputId":powerId,
				"startTime":startTime,
				"endTime":endTime,
				"minutesSpan":5,
				"haveReal":haveReal
			},
			function(response){ 
//				console.log(response.data);
				setEcharsData(response.data,pointType,dateIndex);
			},
			function(e){ 
				console.log("--------error------"+e);
			}
	);
}
//功率曲线赋值
function setEcharsData(data,pointType,dateIndex){
	var power = new Object();
	power.time=[];
	power.value=[];
	if(pointType==0){
		power.name=window.ynz.local.inverter_power;
		power.line="line";
		power.type="（kW）";
	}else{
		power.name=window.ynz.local.meter_generating;
		power.line="bar";
		power.type="（kWh）";
	}
	for(var d in data){
		if(d==0&&dateIndex==1){
			var datedate= timeUtil.timeToString(data[d].time,"yyyy-MM");
			var year = parseInt(datedate.split("-")[0]);
			var month = parseInt(datedate.split("-")[1]);
			var days = new Date(year,month,0).getDate();
			if(month<10){
				month=0+""+month;
			}
			for(var ii=0;ii<days;ii++){
				if(ii<9){
					power.time.push("0"+(ii+1));
				}else{
					power.time.push(ii+1);
				}
				power.value.push(0);
			}
		}
		if(dateIndex==0){
			if(pointType==0){
				power.time.push(timeUtil.timeToString(data[d].time,"yyyy-MM-dd hh:mi"));
				power.value.push(toDecimal(data[d].value,3));
			}else{
				power.time.push(timeUtil.timeToString(data[d].time,"hh:mi"));
				power.value.push(toDecimal(data[d].value,3));
			}
		}else if(dateIndex==1){
			var timedate = timeUtil.timeToString(data[d].time,"dd");
 			for(var ij=0;ij<power.time.length;ij++){
 				if(power.time[ij]==timedate){
 					power.value[ij]=toDecimal(data[d].value,3)
 				}
 			}
		}else{
			power.time.push(timeUtil.timeToString(data[d].time,"mm"));
			power.value.push(toDecimal(data[d].value,3));
		}
	}
	setOpreate(power);
}
function getgridarkInfo(fnCallback){
	ynzAjax.get(//获取并网柜信息
			window.ynz.basePath+"gridark/getGridarkDetails/"+powerStationId+".do",
			function(response){ 
				console.log(response);
				setgridarkData(response.data,fnCallback);
			},
			function(e){ 
				console.log("--------error------"+e);
			}
	);
}
//并网柜详情页面赋值
function setgridarkData(data,fnCallback){
	for(var da in data){
		var obj = [];
		var data1 = new Object();
		var data2 = new Object();
		var data3 = new Object();
		for(var da in data){
			if(da=="serialNumber"){
				$("#"+da).html(data[da])
			}else if(da=="name"){
				$("#"+da).html(data[da])
			}else{
				$("#"+da).html(toDecimal(data[da],3))
			}
		 switch (da) {
		   case "powerId":
			   threePhaseActivePowerId = data[da];
			   break;
		   case "reverseActivePDailyId":
			   /**
			    * 年月累计降低一个数量等级
			    */
			   generationDailyId = data[da];
			   generationMonthId = data[da];
			   break;
		   case "reverseActivePMonthId":
			   generationYearId = data[da];
			   break;
			   
		   case "reverseActivePYearId":
			   break;
		   case "reverseActivePGrossId":
//			   generationDailyId = data[da];
			   break;
		   case "phaseVoltageA":
			   data1.title=window.ynz.local.meter_A_phase;
			   data1.plan1=toDecimal(data[da],3);
			   break;
		   case "phaseVoltageB":
			   data2.title=window.ynz.local.meter_B_phase;
			   data2.plan1=toDecimal(data[da],3);
			   break;
		   case "phaseVoltageC":
			   data3.title=window.ynz.local.meter_C_phase;
			   data3.plan1=toDecimal(data[da],3);
			   break;
	 	   case "phaseCurrentA":
	 		   data1.plan2=toDecimal(data[da],3);
			   break;
	 	   case "phaseCurrentB":
	 		   data2.plan2=toDecimal(data[da],3);
			   break;
	 	   case "phaseCurrentC":
	 		   data3.plan2=toDecimal(data[da],3);
			   break;
	 		   
	 		  /**
	 	 		  * 三相
	 	 		  */
	 	 	case "lookedAtEachOtherInPowerA":
	 		 	   data1.plan3=toDecimal(data[da],3);
	 		 	   break;
	 	 	case "lookedAtEachOtherInPowerB":
	 		 	   data2.plan3=toDecimal(data[da],3);
	 		 	   break;
	 	 	case "lookedAtEachOtherInPowerC":
	 		 	   data3.plan3=toDecimal(data[da],3);
	 		 	   break;
	 	 	case "reactivePowerA":
	 		 	   data1.threePhaseReactivePower=toDecimal(data[da],3);
	 		 	   break;
	 	 	case "reactivePowerB":
	 		 	   data2.threePhaseReactivePower=toDecimal(data[da],3);
	 		 	   break;
	 	 	case "reactivePowerC":
	 		 	   data3.threePhaseReactivePower=toDecimal(data[da],3);
	 		 	   break;
	 	 	case "activePowerA":
	 		 	   data1.threePhaseActivePower=toDecimal(data[da],3);
	 		 	   break;
	 	 	case "activePowerB":
	 		 	   data2.threePhaseActivePower=toDecimal(data[da],3);
	 		 	   break;
	 	 	case "activePowerC":
	 		 	   data3.threePhaseActivePower=toDecimal(data[da],3);
	 		 	   break;

	 	   case "powerFactor":
	 		   data1.powerFactor=toDecimal(data[da],3);
	 		   data2.powerFactor=toDecimal(data[da],3);
	 		   data3.powerFactor=toDecimal(data[da],3);
	 		   break;
	 	   case "frequency":
	 		   data1.frequency=toDecimal(data[da],3);
	 		   data2.frequency=toDecimal(data[da],3);
	 		   data3.frequency=toDecimal(data[da],3);
	 		   break;
	 	   }
		}
		obj.push(data1);
		obj.push(data2);
		obj.push(data3);
		var returnData = new Object();
		returnData.aaData=obj;//设置列表数据
		returnData.iTotalRecords =data.length;//设置总数
		returnData.iTotalDisplayRecords =data.length;//设置显示的数据总数  此属性需要与iTotalRecords相同 并且必须配置
	    fnCallback(returnData);  
	  //展示曲线数据
	    var startstr=$("#start").val();
		var endstr=$("#end").val();
	    var pointType=0;
	    var radio = $("input[name='curveType']");
		for(var i in radio){
			if(radio[i].checked){
				pointType=radio[i].value;
			}
		}
		if(pointType==0){
			pointId = threePhaseActivePowerId;
			if(timeUtil.dateToString(new Date(),"yyyy-mm-dd")==endstr){
				endstr = timeUtil.dateToString(new Date(),"yyyy-mm-dd hh:mi:ss")
				getEcharsData(startstr+" 00:00:00",endstr,pointId,pointType,0,1);
			}else{
				getEcharsData(startstr+" 00:00:00",endstr+" 23:59:59",pointId,pointType,0,0);
			}
		}else{
			var dateType = 0;
			pointId = generationDailyId;
			var dateradio = $("input[name='DateType']");
			for(var i in dateradio){
				if(dateradio[i].checked){
					dateType=dateradio[i].value;
				}
			}
			var dateTime="";
			if(dateType==0){
				dateTime=$("#startyear").val();
				pointId = generationYearId;
				var enddateTime = dateTime;
				if(timeUtil.dateToString(new Date(),"yyyy")==dateTime){
					enddateTime = timeUtil.dateToString(new Date(),"yyyy-mm-dd hh:mi:ss")
					getEcharsData(dateTime+"-01-01 00:00:00",enddateTime,pointId,pointType,2,1);
				}else{
					enddateTime = dateTime+"-12-31 23:59:59";
					getEcharsData(dateTime+"-01-01 00:00:00",enddateTime,pointId,pointType,2,0);
				}
				
			}else if(dateType==1){
				pointId = generationMonthId;
				dateTime=$("#startmonth").val();
				var enddateTime = dateTime;
				if(timeUtil.dateToString(new Date(),"yyyy-mm")==dateTime){
					enddateTime = timeUtil.dateToString(new Date(),"yyyy-mm-dd hh:mi:ss")
					getEcharsData(dateTime+"-01 00:00:00",enddateTime,pointId,pointType,1,1);
				}else{
					var ddate = new Date(dateTime.split("-")[0]+"-"+(parseInt(dateTime.split("-")[1])+1)+"-01 00:00:00");
					var ttime = Date.parse(ddate);
					enddateTime = timeUtil.timeToString(ttime-1000,"yyyy-mm-dd hh:mi:ss");
					getEcharsData(dateTime+"-01 00:00:00",enddateTime,pointId,pointType,1,0);
				}
			}else{
				dateTime=$("#startday").val();
				var enddateTime = dateTime;
				if(timeUtil.dateToString(new Date(),"yyyy-mm-dd")==dateTime){
					enddateTime = timeUtil.dateToString(new Date(),"yyyy-mm-dd hh:mi:ss")
					getEcharsData(dateTime+" 00:00:00",enddateTime,pointId,pointType,0,1);
				}else{
					getEcharsData(dateTime+" 00:00:00",enddateTime+" 23:59:59",pointId,pointType,0,0);
				}
			}
			console.log(dateTime);
		}
	}
}

/**
 * 表格初始化
 */
var TableDatatablesButtons1 = function () {
	/**
	 * 初始化表格
	 */
    var initTable1 = function () {
        var table = $('#sample_1');//table id
        var oTable = table.dataTable({
        	"bAutoWidth":true,//设置自动计算列宽
        	"bDeferRender":false,//设置是否延迟渲染  使用ajax或者js加载数据 开启延迟渲染可提升速度
            "serverSide":true,//设置服务器端分页方式  false情况下默认使用前端插件分页
            "sAjaxSource": "",//插件自带ajax请求方式  需要写 但不使用
            "fnServerData": retrieveData,//fnServerData属性用于替换原有ajax请求   retrieveData是一个function ，方法中写ajax请求  
            "bFilter":false,
            "searching":false,
            "ordering":false,
            "info":false,
            "paging":false,
            "sort":false,
            //"sAjaxSource": "",//插件自带ajax请求方式  需要写 但不使用
          //  "fnServerData": retrieveData,//fnServerData属性用于替换原有ajax请求   retrieveData是一个function ，方法中写ajax请求  
            /**
             * 指定显示列
             *  mDataProp 对应服务端字段名  
             *  sTitle 列展示名称
             *  visible true时显示当前列 设置false 隐藏当前列  不设置默认true
             *  sClass 对齐方式 
             */
            
//            "aaData":[
//            	{"plant1":"A相","plant2":"388.5","time":"2017-06-29  10:08","troub":"sdasdas","rank":"asdasd","decript1":"欠压或过温保护","decript2":"欠压或过温保护"},
//            	{"plant1":"B相","plant2":"388.5","time":"2017-06-29  10:08","troub":"sdasdas","rank":"asdasd","decript1":"欠压或过温保护","decript2":"欠压或过温保护"},
//            	{"plant1":"C相","plant2":"388.5","time":"2017-06-29  10:08","troub":"sdasdas","rank":"asdasd","decript1":"欠压或过温保护","decript2":"欠压或过温保护"}
//            ],
            "aoColumns": [
              	{"mDataProp":"title",
                	 "sTitle": "" 
                 	},
             		 {"mDataProp":"plan1",
                	 "sTitle": window.ynz.local.meter_alternator_pressure+"(V)" 
                 },
  	           {"mDataProp":"plan2", 
  	        	   "sTitle": window.ynz.local.meter_alternator_electric+"(A)" 
  	           },
  	            {"mDataProp":"plan3",
                	 "sTitle": window.ynz.local.meter_apparent_power+"(KW)" 
                 },
  	            {"mDataProp":"threePhaseActivePower",
                	 "sTitle": window.ynz.local.meter_active+"(KW)" 
                 },
                 {"mDataProp":"threePhaseReactivePower",
                	 "sTitle": window.ynz.local.meter_reactive+"(KW)" 
                 },
  	            {"mDataProp":"powerFactor",
                  	 "sTitle": window.ynz.local.meter_power_factor+"(pf)" 
                   },
                   {"mDataProp":"frequency",
                  	 "sTitle": window.ynz.local.meter_frequency+"(Hz)" 
                   }	                  
             ] ,
           /**
            * 设置右上角显示按钮 下列按钮为插件自带
            */
            buttons: [  
//                { extend: 'print', className: 'btn dark btn-outline' },
//                { extend: 'copy', className: 'btn red btn-outline' },
//                { extend: 'pdf', className: 'btn green btn-outline' },
//                { extend: 'excel', className: 'btn yellow btn-outline ' },
//                { extend: 'csv', className: 'btn purple btn-outline ' } ,
//                { extend: 'colvis', className: 'btn dark btn-outline', text: 'Columns'}//显示或隐藏某列
            ],
           
			"bLengthChange":false,//表格是否显示每页显示条数（5，10，15，20）设置为false时 用户无法自行更改页面显示条数
            responsive: true,//？？？？
            "order": [  //排序
                [0, 'asc']
            ],
//          "iDisplayLength":5,  //页面显示行数  
//          "sPaginationType": "full_numbers",  //翻页界面类型    太丑
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
	              buttons: [  
	                        ],
            responsive: {
                details: {
                   
                }
            },

            "order": [
                [0, 'asc']
            ],
            
            "lengthMenu": [
                [5, 10, 15, 20, -1],
                [5, 10, 15, 20, "All"] // change per page values here
            ],
            // set the initial value
            "pageLength": 5,
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
    	getgridarkInfo(fnCallback);
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
//ECHARTS
function setOpreate(data){
	require.config({
        paths: {
            echarts: window.ynz.cdnPath+'global/plugins/echarts/'
        }
    });
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
                },
                calculable: true,
                animation:false,
                xAxis: [{
                    type: 'category',
                    data: data.time
                }],
                yAxis: [{
                	name:data.type,
                    type: 'value',
                    splitArea: {
		                show: true
		            }
                }],
                series: [{
                    name: data.name,
                    type: data.line,
                    symbol:'none',
                    smooth:true,
                    data: data.value,
                }]
            });
 
        }
    );
}