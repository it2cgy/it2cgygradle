var generationDailyList=[];
var powerDataList=[];
var echarType="bar";
var echarType1="line";
var line = 1;
$(function(){
//	$("#showBasic").click(function(){
//		$("#basic").toggle();
//	});
	loadData();
	nowTime();
	window.setInterval(loadData,TimeOutTime);
	window.setInterval(nowTime,1000);
	
	//刷新天气
	refreshWeather();
	window.setInterval(refreshWeather,1800000);  
	$("#electricLi").click(function(){
		$(this).addClass("activeLi");
		$("#totalPowerLi").removeClass("activeLi");
		setEcharsData(generationDailyList,0);
		line = 1;
	});
	$("#totalPowerLi").click(function(){
		$(this).addClass("activeLi");
		$("#electricLi").removeClass("activeLi");
		setEcharsData(powerDataList,1);
		line = 0;
	});
});


var weather = locationWeather();
function refreshWeather(){
	weather.getWeatherByPoint(powerStationBaseInfo.lng,powerStationBaseInfo.lat,function(data) {
//		console.log("获取当前位置天气#"+JSON.stringify(data));
		var _date = new Date();
		var h = _date.getHours();
		var weathers = data.results[0].weather_data;//天气
		var today =   getDateStr(0);
		var tomorrow = getDateStr(1);
		var afterTom = getDateStr(2);
		for(var i in weathers){
			var _data = weathers[i];
			var dateTmp = _data.date;
			var imgUrl = "";
			if(h>=7&&h<=19){
				imgUrl=_data.dayPictureUrl;
			}else{
				imgUrl=_data.nightPictureUrl;
			}	
			if(dateTmp.indexOf(today)>=0){
				if(window.ynz.localType!="en_US"){
					$("#weather_weather").html(_data.weather);
				}
				$("#weatherImg1").attr("src",imgUrl);
			}
			if(dateTmp.indexOf(tomorrow)>=0){
				if(window.ynz.localType!="en_US"){
					$("#weather_weather2").html(_data.weather);
				}
				$("#weatherImg2").attr("src",imgUrl);
			}
			if(dateTmp.indexOf(afterTom)>=0){
				if(window.ynz.localType!="en_US"){
					$("#weather_weather3").html(_data.weather);
				}
				$("#weatherImg3").attr("src",imgUrl);
			}
		}
	});
}

function getDateStr(_count){
	var dd = new Date();
	dd.setDate(dd.getDate()+_count);
	var m = dd.getDay();
	return  "周"+"日一二三四五六".split("")[m];
}

/**
 * 获取当前系统时间
 */
function nowTime(){
	var time = timeUtil.dateToString(new Date(),"yyyy-mm-dd HH:MI:SS");
	var arr = time.split(" ");
	$("#weatherDate").html(arr[0])
	$("#weatherTime").html(arr[1])
}
/**
 * 加载页面数据
 */
function loadData(){
	 ynzAjax.get(//加载电站信息
				window.ynz.path+"/powerStation/powerStationDetails/"+window.ynz.longieb.powerStationId+".do",
				function(response){ 
					powerData(response.data.powerId);
					generationDailyData(response.data.generationDailyId);
					setSubIndexData(response.data);
		        },
		        function(e){ 
		            console.log("--------error------"+e);
		        }
		    );
	 //获取报警列表
	 addPageData();
}
/**
 * 加载电站实时数据
 * @param data
 */
function setSubIndexData(data){
//	if(line == 1){
//		setEcharsData(generationDailyList,0);
//	}
	console.log(data);
	for(var e in data){
		if($("#"+e)){
			if(e=="generationGross"){
				if(data[e]>9999){
					$("#"+e).html(toDecimal(data[e]/1000,3));
					$("#GrossUnit").html("MWh");
				}else{
					$("#"+e).html(toDecimal(data[e],3));
					$("#GrossUnit").html("kWh");
				}
				continue;
			}
			if(e=="runTime"){
				$("#"+e).html(timeUtil.timeToString(data[e],"yyyy-mm-dd"));
				continue;
			}
			if(e=="generationMonth"){
				if(data[e]>999){
					$("#"+e).html(toDecimal(data[e]/1000,3));
					$("#MonthUnit").html("MWh");
				}else{
					$("#"+e).html(toDecimal(data[e],3));
					$("#MonthUnit").html("kWh");
				}
				continue;
			}
			if(e=="generationYear"){
				if(data[e]>999){
					$("#"+e).html(toDecimal(data[e]/1000,3));
					$("#YearUnit").html("MWh");
				}else{
					$("#"+e).html(toDecimal(data[e],3));
					$("#YearUnit").html("kWh");
				}
				continue;
			}
			if(e=="generationDaily"){
				if(data[e]>999){
					$("#"+e).html(toDecimal(data[e]/1000,3));
					$("#DayUnit").html("MWh");
				}else{
					$("#"+e).html(toDecimal(data[e],3));
					$("#DayUnit").html("kWh");
				}
				continue;
			}
//			if(e=="pr"){
//				$("#pr").html("85.6");
//				continue;
//			}
			var value = toDecimal(data[e],3);
			$("#"+e).html(value);
		}
	}
}

/**
 * 获取天气状况和降水状况
 */
function getWeatherInfo(){
	
}


var pageNow =1;
var defaultPageSize = 10;

/**
 * 加载页面数据
 */
function addPageData(){
	if(pageNow==1){ 
		getAlarmInfo(pageNow,defaultPageSize);
	}
}

/**
 * 获取报警列表信息
 */
function getAlarmInfo(page,pagesize){
	 pageNow=page;
	 ynzAjax.get(
		 window.ynz.path+"/alarmInfo/getAlarmListBySearch.do?page="+ page +"&status=0&pagesize="+pagesize+"&&powerStationId="+window.ynz.longieb.powerStationId,
			function(response){ 
				$("#alarmsCounts").html(response.counts)
	        },
	        function(e){ 
	            console.log("--------error------"+e);
	        }
		);
}
function setEcharsData(arr,num){
	var result = getPointData(arr,num)
	var curveData = new Object();
	curveData.point=[];
	curveData.date=[];
	curveData.yaxisData=[];
	curveData.date = result.time;
	if(num==0){
		curveData.yaxisData.push({"name":window.ynz.local.meter_generating+"(kWh)",
			"splitLine":{
				show:false
			},
			"type":"value",
			"nameTextStyle":{
	        	color:"#82a3bc"
	        },
	        "axisLabel":{
	    		textStyle:{
	    			color:"#0383a0"
	    		}
	    	},
			"axisLine":{
				show:true,
				onZero:false,
				lineStyle:{
					borderWidth:"1px",
					color:"#2e354b"
				}
			},
			"position":"left"});
		
		curveData.point.push({"name": window.ynz.local.meter_generating,
			"symbol":"none",
			"smooth":true,
			"type":echarType,
			"data":result.value,
		});
	}else{
		curveData.yaxisData.push({"name":window.ynz.local.inverter_power+"(kW)",
			"splitLine":{
				show:false
			},
			"type":"value",
			"nameTextStyle":{
	        	color:"#82a3bc"
	        },
			"axisLabel":{
	    		textStyle:{
	    			color:"#0383a0"
	    		}
	    	},
			"axisLine":{
				show:true,
				onZero:false,
				lineStyle:{
					borderWidth:"1px",
					color:"#2e354b"
				}
			},
			"position":"left"});

		curveData.point.push({"name": window.ynz.local.inverter_power,
			"symbol":"none",
			"smooth":true,
			"type": echarType1,
			"data":result.value,
		});
	}
	setOption(curveData);
}
/**
 * 曲线数据转化成纵横坐标轴数组
 * @param historyDatas
 * @returns {___anonymous13869_13871}
 */
function getPointData(historyDatas,num){
	var res = new Object();
	res.time=[];
	res.value=[];
	if(historyDatas.length==0){
		if(num==0){
			res.time.push(timeUtil.timeToString(new Date(),"HH:00"));
		}else{
			res.time.push(timeUtil.timeToString(new Date(),"HH:mi"));
		}
		res.value.push(toDecimal(0,3));
	}else{
		if(historyDatas.length!=1){
			for(var i=0;i<historyDatas.length;i++){
				if(historyDatas[i]!=null){
					if(num==0){
						res.time.push(timeUtil.timeToString(historyDatas[i].time,"HH:00"));
					}else{
						res.time.push(timeUtil.timeToString(historyDatas[i].time,"HH:mi"));
					}
					res.value.push(toDecimal(historyDatas[i].value,3));
				}
			}
			if(res.time.length==0){
				if(num==0){
					res.time.push(timeUtil.timeToString(new Date(),"HH:00"));
				}else{
					res.time.push(timeUtil.timeToString(new Date(),"HH:mi"));
				}
				res.value.push(toDecimal(0,3));
			}
		}else{
			if(num==0){
				res.time.push(timeUtil.timeToString(new Date(),"HH:00"));
			}else{
				res.time.push(timeUtil.timeToString(new Date(),"HH:mi"));
			}
			res.value.push(toDecimal(0,3));
		}
	}
	return res;
}

function generationDailyData(generationDailyId){
	var startTime = timeUtil.dateToString(new Date(),"yyyy-mm-dd")+" 04:00:00";
	var endTime1 = timeUtil.dateToString(new Date(),"yyyy-mm-dd");
	var endTime = timeUtil.dateToString(new Date(),"yyyy-mm-dd hh:mi:ss");
	var end2 = (new Date(endTime1+" 21:00:00")).getTime()>(new Date()).getTime()?endTime:endTime1+" 21:00:00";
	ynzAjax.post(//电表当天日发电量历史
			window.ynz.basePath+"pointInfo/getPointHoursGeneration.do",
			{   "analoginputId":generationDailyId,
//				"startTime":"2017-09-04 00:00:00",
//				"endTime":"2017-09-04 23:59:59",
				"startTime":startTime,
				"endTime":end2,
				"minutesSpan":5,
				"haveReal":1
			},
			function(response){
				generationDailyList = response.data;
				if(line != 0){
					setEcharsData(generationDailyList,0);
				}
			},
			function(e){ 
				console.log("--------error------"+e);
			}
	);
}
/**
 * 获取功率当天数据
 * @param powerId
 */
function powerData(powerId){
	var startTime = timeUtil.dateToString(new Date(),"yyyy-mm-dd")+" 04:00:00";
	var endTime1 = timeUtil.dateToString(new Date(),"yyyy-mm-dd");
	var endTime = timeUtil.dateToString(new Date(),"yyyy-mm-dd hh:mi:ss");
	var end2 = (new Date(endTime1+" 21:00:00")).getTime()>(new Date()).getTime()?endTime:endTime1+" 21:00:00";
	ynzAjax.post(//电表当天功率历史
			window.ynz.basePath+"stationmonitor/historyRedress.do",
			{   "analoginputId":powerId,
				"startTime":startTime,
				"endTime":end2,
				"minutesSpan":5,
				"haveReal":1
			},
			function(response){
				powerDataList = response.data;
				if(line == 0){
					setEcharsData(powerDataList,1);
				}
			},
			function(e){ 
				console.log("--------error------"+e);
			}
	);
}