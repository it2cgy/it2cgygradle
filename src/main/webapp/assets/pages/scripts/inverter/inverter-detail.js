var threePhaseActivePowerId="";
var generationDailyId="";
var generationMonthId="";
var generationYearId="";
var generationGrossId="";
var timer;
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
	 // ECHARTS
    require.config({
        paths: {
            echarts: window.ynz.cdnPath+'global/plugins/echarts/'
        }
    });
    TableDatatablesButtons1.init();
	 setSelectMenu("menuequipmonitor","menuinverterbox");
	 inverterDetail();
	 window.setInterval(inverterDetail,TimeOutTime);
	 weatherInfo();
	 window.setInterval(weatherInfo,TimeOutTime);
});
function dateTypeChange(dateIndex){
	$("#generatModelDiv").css("margin-top","");
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
	if(dateIndex==3){
		$("#yearType").hide();
		$("#monthType").hide();
		$("#dayType").hide();
		$("#generatModelDiv").css("margin-top","8px");
	}
}
function powerModel(){
	$("#powerModelDiv").show();
	$("#generatModelDiv").hide();
	
}
function generatModel(){
	$("#powerModelDiv").hide();
	$("#generatModelDiv").show();
	$("#generatModelDiv").css("margin-top","8px");
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
	if(timer){
		getEcharsData(params.startstr,params.endstr,params.pointId,params.pointType,params.index,1);
	}
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
	
	if(pointType==0){
		pointId = threePhaseActivePowerId;
		if(timeUtil.dateToString(new Date(),"yyyy-mm-dd")==endstr){
			endstr = timeUtil.dateToString(new Date(),"yyyy-mm-dd hh:mi:ss")
			getEcharsData(startstr+" 00:00:00",endstr,pointId,pointType,0,1);
		}else{
			startstr+=" 00:00:00";
			endstr+=" 23:59:59";
			getEcharsData(startstr,endstr,pointId,pointType,0,0);
			if(timeUtil.dateToString(new Date(),"yyyy-mm-dd")==startstr){
				var params = new Object();
				params.startstr = startstr;
				params.endstr = endstr;
				params.pointId = pointId;
				params.pointType = pointType;
				params.index = 0;
				timer = window.setInterval(timeEchars,TimeOutTime,params);
			}
		}
	}else{
		if(timer){
			clearInterval(timer);
			timer=null;
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
		}else if(dateType==2){
			dateTime=$("#startday").val();
			var enddateTime = dateTime;
			if(timeUtil.dateToString(new Date(),"yyyy-mm-dd")==dateTime){
				enddateTime = timeUtil.dateToString(new Date(),"yyyy-mm-dd hh:mi:ss")
				getEcharsData(dateTime+" 00:00:00",enddateTime,pointId,pointType,0,1);
			}else{
				getEcharsData(dateTime+" 00:00:00",enddateTime+" 23:59:59",pointId,pointType,0,0);
			}
		}else if(dateType==3){
			pointId = generationGrossId;
			getEcharsData(null,null,pointId,pointType,3,1);
		}
		console.log(dateTime);
	}
}

function getEcharsData(startTime,endTime,pointId,num,dateIndex,haveReal){
	var url ="";
	if(dateIndex==0){
		if(num==0){
			url = window.ynz.basePath+"stationmonitor/historyRedress.do";
		}else{
			url = window.ynz.basePath+"pointInfo/getPointHoursGeneration.do";
		}
		minutesSpan=5;
	}else if(dateIndex==1){
		url = window.ynz.basePath+"pointInfo/getPointMonthgeneration.do";
		minutesSpan=5;
	}else if(dateIndex==2){
		url = window.ynz.basePath+"pointInfo/getPointYearGeneration.do";
		minutesSpan=5;
	}else if(dateIndex==3){
		startTime="2017-01-01 00:00:00";
		endTime=timeUtil.dateToString(new Date(),"yyyy-mm-dd hh:mi:ss");
		url = window.ynz.basePath+"pointInfo/getPointGrossGeneration.do";
		minutesSpan=5;
	}
	ynzAjax.post(//电表当天功率历史
			url,
			{   "analoginputId":pointId,
				"startTime":startTime,
				"endTime":endTime,
				"minutesSpan":minutesSpan,
				"haveReal":haveReal
			},
			function(response){ 
				var result = getPointData(response.data,dateIndex,num)
				var valuearr = [];
				var timearr = [];
				var curveData = new Object();
				curveData.point=[];
				curveData.date=[];
				curveData.yaxisData=[];
				curveData.date = result.time;
				if(num==0){
					curveData.yaxisData.push({"name":"（kW）",
						"splitArea":{
							show:true
						},
						"type":"value",
						"position":"left"});
					curveData.point.push({"name": window.ynz.local.inverter_power,
						"symbol":"none",
						"smooth":true,
						"type": 'line',
						"data":result.value,
					});
				}else{
					curveData.yaxisData.push({"name":"（kWh）",
						"splitArea":{
							show:true
						},
						"type":"value",
						"position":"left"}); 
					var objPointInfo ={"name": window.ynz.local.meter_generating,
							"symbol":"none",
							//"barWidth":"40",
							"smooth":true,
							"type": 'bar',
							"data":result.value,
						};
					if(result.value.length<4){  
						objPointInfo.barWidth="40"
					} 
					curveData.point.push(objPointInfo);
				}
				setEchars(curveData);
			},
			function(e){ 
				console.log("--------error------"+e);
			}
	);
}
function setPointTables(data){
	var dataArr=[];
	var arrtitle=[window.ynz.local.inverter_voltage+"(V)",window.ynz.local.inverter_current+"(A)",
	              window.ynz.local.inverter_power+"(W)",window.ynz.local.meter_frequency+"(Hz)"]
	for(var i =0;i<arrtitle.length;i++){
		var obj = new Object();
		obj.plant1=arrtitle[i];
		obj.plant2="--";
		obj.time="--";
		obj.troub="--";
		obj.rank="--";
		obj.decript1="--";
		obj.decript2="--";
		dataArr.push(obj);
	}
	var totalA=0;
	var totalV=0;
	var totalP=0;
	for(var i in data.mppt){
		var da = data.mppt[i]
		var str = da.name.charAt(da.name.length-1)
		if(str==1){
			dataArr[0].rank=da.value.split(" ")[0].substring(0,da.value.split(" ")[0].length-1);
			dataArr[1].rank=da.value.split(" ")[1].substring(0,da.value.split(" ")[1].length-1);
			dataArr[2].rank=toDecimal(parseFloat(dataArr[0].rank)*parseFloat(dataArr[1].rank),3);
			totalP+=dataArr[2].rank;
			totalA+=parseFloat(dataArr[1].rank);
			totalV+=parseFloat(dataArr[0].rank);
		}
		if(str==2){
			dataArr[0].decript1=da.value.split(" ")[0].substring(0,da.value.split(" ")[0].length-1);
			dataArr[1].decript1=da.value.split(" ")[1].substring(0,da.value.split(" ")[1].length-1);
			dataArr[2].decript1=toDecimal(parseFloat(dataArr[0].decript1)*parseFloat(dataArr[1].decript1),3);
			totalP+=dataArr[2].decript1;
			totalA+=parseFloat(dataArr[1].decript1);
			totalV+=parseFloat(dataArr[0].decript1);
		}
		if(str==3){
			dataArr[0].decript2=da.value.split(" ")[0].substring(0,da.value.split(" ")[0].length-1);
			dataArr[1].decript2=da.value.split(" ")[1].substring(0,da.value.split(" ")[1].length-1);
			dataArr[2].decript2=toDecimal(parseFloat(dataArr[0].decript2)*parseFloat(dataArr[1].decript2),3);
			totalP+=dataArr[2].decript2;
			totalA+=parseFloat(dataArr[1].decript2);
			totalV+=parseFloat(dataArr[0].decript2);
		}
		console.log(dataArr);
	}
	var frequnce = 0;
	var phaseA=0;
	var phaseV=0;
	var phaseP=0;
	for(var i in data.phaseVoltageParam){
		var da = data.phaseVoltageParam[i];
		var str = da.name;
		if(str=="A相"){
			dataArr[0].plant2=da.value.split(" ")[0].substring(0,da.value.split(" ")[0].length-1);
			dataArr[1].plant2=da.value.split(" ")[1].substring(0,da.value.split(" ")[1].length-1);
			dataArr[2].plant2=toDecimal(parseFloat(dataArr[0].plant2)*parseFloat(dataArr[1].plant2),3);
			frequnce = da.value.split(" ")[2].substring(0,da.value.split(" ")[0].length-2);
			phaseP+=dataArr[2].plant2;
			phaseA+=parseFloat(dataArr[1].plant2);
			phaseV+=parseFloat(dataArr[0].plant2);
		}
		if(str=="B相"){
			dataArr[0].time=da.value.split(" ")[0].substring(0,da.value.split(" ")[0].length-1);
			dataArr[1].time=da.value.split(" ")[1].substring(0,da.value.split(" ")[1].length-1);
			dataArr[2].time=toDecimal(parseFloat(dataArr[0].time)*parseFloat(dataArr[1].time),3);
			phaseP+=dataArr[2].plant2;
			phaseA+=parseFloat(dataArr[1].time);
			phaseV+=parseFloat(dataArr[0].time);
		}
		if(str=="C相"){
			dataArr[0].troub=da.value.split(" ")[0].substring(0,da.value.split(" ")[0].length-1);
			dataArr[1].troub=da.value.split(" ")[1].substring(0,da.value.split(" ")[1].length-1);
			dataArr[2].troub=toDecimal(parseFloat(dataArr[0].troub)*parseFloat(dataArr[1].troub),3);
			phaseP+=dataArr[2].troub;
			phaseA+=parseFloat(dataArr[1].troub);
			phaseV+=parseFloat(dataArr[0].troub);
		}
		console.log(dataArr);
	}
	if(data.phaseVoltageParam.length==1){
		dataArr[3].plant2=frequnce;
	}else{
		dataArr[3].plant2=frequnce;
		dataArr[3].troub=frequnce;
		dataArr[3].time=frequnce;
	}
	for(var iimp=0;iimp<data.mppt.length;iimp++){
		if(data.mppt[iimp].name.charAt(data.mppt[iimp].name.length-1)=="1"){
			dataArr[3].rank=frequnce;
		}
		if(data.mppt[iimp].name.charAt(data.mppt[iimp].name.length-1)=="2"){
			dataArr[3].decript1=frequnce;
		}
		if(data.mppt[iimp].name.charAt(data.mppt[iimp].name.length-1)=="3"){
			dataArr[3].decript2=frequnce;
		}
			
	}
	$("#threePhaseA").html(toDecimal(phaseA,3));
	$("#threePhaseV").html(toDecimal(phaseV,3));
	$("#threePhaseP").html(toDecimal(phaseP,3));
	$("#totalA").html(toDecimal(totalA,3));
	$("#totalV").html(toDecimal(totalV,3));
	$("#totalP").html(toDecimal(totalP,3));
	var table = $("#sample_1");  
	table.dataTable().fnClearTable(); 
	table.dataTable().fnAddData(dataArr);
}
/**
 * 获取逆变器数据详情
 */
 function inverterDetail(){
	 ynzAjax.get(
				window.ynz.path+"/inverter/getInverteInfo/"+inverterInfo.inverterId+".do",
				function(response){
					setPointTables(response.data);
					threePhaseActivePowerId=response.data.threePhaseActivePowerId;
					generationDailyId=response.data.generationDailyId;
					/**
					 * 年月累计数据下调一个等级
					 */
					generationMonthId=response.data.generationDailyId;
					generationYearId=response.data.generationMonthId;
					generationGrossId=response.data.generationGrossId
					var pointType=0;
					var radio = $("input[name='curveType']");
					for(var i in radio){
						if(radio[i].checked){
							pointType=radio[i].value;
						}
					}
					var pointId = threePhaseActivePowerId;
					var startstr="";
					var endstr="";
					if(pointType==0){
						pointId = threePhaseActivePowerId;
						startstr=$("#start").val();
						endstr=$("#end").val();
//						startstr = startstr+" 00:00:00";
						if(timeUtil.dateToString(new Date(),"yyyy-mm-dd")==endstr){
							endstr = timeUtil.dateToString(new Date(),"yyyy-mm-dd hh:mi:ss")
							getEcharsData(startstr+" 00:00:00",endstr,pointId,pointType,0,1);
						}else{
							endstr = endstr+" 23:59:59";
							getEcharsData(startstr+" 00:00:00",endstr,pointId,pointType,0,0);
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
						if(dateType==0){
							dateTime=$("#startyear").val();
							pointId = generationGrossId;
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
						}else if(dateType==2){
							dateTime=$("#startday").val();
							startstr= dateTime+" 00:00:00";
							if(timeUtil.dateToString(new Date(),"yyyy-mm-dd")==dateTime){
								endstr = timeUtil.dateToString(new Date(),"yyyy-mm-dd hh:mi:ss")
								getEcharsData(startstr,endstr,pointId,pointType,0,1);
							}else{
								endstr = dateTime+" 23:59:59";
								getEcharsData(startstr,endstr,pointId,pointType,0,0);
							}
						}else if(dateType==3){
							pointId = generationGrossId;
							getEcharsData(null,null,pointId,pointType,3,1);
						}
					}
					console.log(response.data);
					for(var data in response.data){
						if(data=="temperature"){
							value = toDecimal(response.data[data],3);
							$("#temperature").html(value);
						}else if(data=="threePhaseActivePower"){
							value = toDecimal(response.data[data],3);
							$("#currentPower").html(value);
							$("#threePhaseActivePower").html(value);
						}else{
							if($("#"+data)){
								if(data=="generationDaily"||data=="generationMonth"||data=="generationYear"||data=="generationGross"){
									if(data=="generationDaily"){
										$("#generatingDay").html(toDecimal(response.data[data],3));
										$("#generationDaily").html(toDecimal(response.data[data],3));
										continue;
									}
									if(data=="generationGross"){
										$("#generatingTotal").html(toDecimal(response.data[data],3));
										$("#generationGross").html(toDecimal(response.data[data],3));
										continue;
									}
									if(data=="generationMonth"){
										$("#generationMonth").html(toDecimal(response.data[data],3));
										continue;
									}
									if(data=="generationYear"){
										$("#generationYear").html(toDecimal(response.data[data],3));
										continue;
									}
								}
								if(data=="totalWorkingHours"){
									$("#runtimeMonth").html(toDecimal(toDecimal(response.data[data]/24,3)+0.5));
									$("#runtimeYear").html(toDecimal(toDecimal(response.data[data]/24,3)+0.5));
									continue;
								}
								var value = response.data[data];
								if(data=="efficiency"){
									$("#efficiency").html(toDecimal(response.data[data]*100,3));
									continue;
								}
								
								if(data=="totalInputPower"){
									$("#totalP").html(toDecimal(response.data[data],3));
									continue;
								}
								if(data!="name"){
									value = toDecimal(response.data[data],3);
								}
								$("#"+data).html(value);
							}
						}
					}
					console.log(response.data);
//					setparamData(response);
		        },
		        function(e){ 
		            console.log("--------error------"+e);
		        }
		    );
	 
 }
 /**
  * 获取电站气象数据
  */
 function weatherInfo(){
	 ynzAjax.get(
				window.ynz.path+"/powerStation/getWeatherInfo/"+inverterInfo.powerStationId+".do",
				function(response){ 
					for(var data in response.data){
						if($("#"+data)){
							var value = toDecimal(response.data[data],3);
							$("#"+data).html(toDecimal(value,3));
						}
					}
		        },
		        function(e){ 
		            console.log("--------error------"+e);
		        }
		    );
 }
 /**
  * 曲线数据转化成纵横坐标轴数组
  * @param historyDatas
  * @returns {___anonymous13869_13871}
  */
 function getPointData(historyDatas,dateindex,pointType){
 	var res = new Object();
 	res.time=[];
 	res.value=[];
 	for(var i=0;i<historyDatas.length;i++){
 		if(i==0&&dateindex==1){
 			var datedate= timeUtil.timeToString(historyDatas[i].time,"yyyy-MM");
 			var year = parseInt(datedate.split("-")[0]);
 			var month = parseInt(datedate.split("-")[1]);
 			var days = new Date(year,month,0).getDate();
 			if(month<10){
 				month=0+""+month;
 			}
 			for(var ii=0;ii<days;ii++){
 				if(ii<9){
 					res.time.push("0"+(ii+1));
 				}else{
 					res.time.push(ii+1);
 				}
 				res.value.push(0);
 			}
 		}
 		if(dateindex==0){
 			if(pointType==0){
 				res.time.push(timeUtil.timeToString(historyDatas[i].time,"yyyy-MM-dd HH:mi"));
 				res.value.push(toDecimal(historyDatas[i].value,3));
 			}else{
 				res.time.push(timeUtil.timeToString(historyDatas[i].time,"HH:mi"));
 				res.value.push(toDecimal(historyDatas[i].value,3));
 			}
 		}else if(dateindex==1){
 			var timedate = timeUtil.timeToString(historyDatas[i].time,"dd");
 			for(var ij=0;ij<res.time.length;ij++){
 				if(res.time[ij]==timedate){
 					res.time[ij]=timedate;
 					res.value[ij]=toDecimal(historyDatas[i].value,3);
 				}
 			}
 		}else if(dateindex==2){
 			res.time.push(timeUtil.timeToString(historyDatas[i].time,"MM"));
 			res.value.push(toDecimal(historyDatas[i].value,3))
 		}else if(dateindex==3){
 			res.time.push(timeUtil.timeToString(historyDatas[i].time,"yyyy"));
 			res.value.push(toDecimal(historyDatas[i].value,3))
 		}
 	}
 	return res;
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
	        	myChart2 = ec.init(document.getElementById('electLine'));
	        	myChart2.setOption({
	                title: {
	                },
	                tooltip: {
	                    trigger: 'axis'
	                },
	                toolbox: {
	                    show: true,
	                    feature: {
	                    }
	                },
	                axis:{
	                	
	                },
	                grid:{},
	                calculable: true,
	                animation:false,
	                xAxis: [{
	                    type: 'category',
	                    data: curveData.date
	                }],
	                yAxis: curveData.yaxisData,
	                series: curveData.point
	            });
	        }
	    );
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
        "serverSide":false,//设置服务器端分页方式  false情况下默认使用前端插件分页
        "info":false,
        "paging":false,
        "searching":false,
        "sort":false,
        //"sAjaxSource": "",//插件自带ajax请求方式  需要写 但不使用
      //  "fnServerData": retrieveData,//fnServerData属性用于替换原有ajax请求   retrieveData是一个function ，方法中写ajax请求  
        "bFilter":false,
        /**
         * 指定显示列
         *  mDataProp 对应服务端字段名  
         *  sTitle 列展示名称
         *  visible true时显示当前列 设置false 隐藏当前列  不设置默认true
         *  sClass 对齐方式 
         */
        
       "aoColumns": [
        	{"mDataProp":"plant1",
          	 "sTitle": "" 
           	},
       		 {"mDataProp":"plant2",
          	 "sTitle": window.ynz.local.meter_A_phase 
           },
           {"mDataProp":"time", 
        	   "sTitle": window.ynz.local.meter_B_phase 
           },
            {"mDataProp":"troub",
          	 "sTitle": window.ynz.local.meter_C_phase 
           },
           {"mDataProp":"rank", 
        	   "sTitle": window.ynz.local.inverter_mppt1
           },
            {"mDataProp":"decript1",
          	 "sTitle": window.ynz.local.inverter_mppt2
           },
           {"mDataProp":"decript2",
          	 "sTitle": window.ynz.local.inverter_mppt3
           }	                  
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
//      "iDisplayLength":5,  //页面显示行数  
//      "sPaginationType": "full_numbers",  //翻页界面类型    太丑
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