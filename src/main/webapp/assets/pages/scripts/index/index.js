var CO2 = 0.997*1000;
var SO2 = 0.03*1000;
var NO2 = 0.015*1000;
var RM = 0.4*1000;
var XO = 0.272*1000;
var generationdata="";
var echarData="";
var remarkDaliyDay = "";
var remarkgenerationIds = "";
var remarkpowerIds = "";
var curveTypeNum = 1;//默认显示发电量
var indexRestart =0;
var isOnline=false;
var BdMap;
var pageNow =1;
var defaultPageSize = 6;
var searchCondition="";
$(function(){ 
	checkOnline();
	//列表模式得焦事件
	$("#listcondition").focus(function(){
		$(this).removeAttr("placeholder");
	});
	$("#listcondition").blur(function(){
		$(this).attr("placeholder",window.ynz.local.index_please_fill);
	});
	//图表模式得焦事件
	$("#iconcondition").focus(function(){
		$(this).removeAttr("placeholder");
	});
	$("#iconcondition").blur(function(){
		$(this).attr("placeholder",window.ynz.local.index_please_fill);
	});
	
	//首页菜单显隐
	$("#fold").click(function(){
		$("#indexBox").toggle();
		$("#indexNav").toggle();
	})
	//tab切换（地图/图表、列表切换）
	$("#bgMapBtn").click(function(){
		$(this).addClass("activeLi");
		$("#tuModule").removeClass("activeLi");
		$("#listModule").removeClass("activeLi");
		$("#bgMap").show();
		$("#module_picTable").hide();//地图模式
		$("#nodule_listTable").hide();
	});
	$("#tuModule").click(function(){
//		$(this).addClass("activeLi");
//		$("#bgMapBtn").removeClass("activeLi");
//		$("#listModule").removeClass("activeLi");
//		$("#module_picTable").show();//图表模式
//		$("#bgMap").hide();//地图模式
//		$("#nodule_listTable").hide();//列表模式
		clickTuModule();
	});
	$("#listModule").click(function(){
		$(this).addClass("activeLi");
		$("#bgMapBtn").removeClass("activeLi");
		$("#tuModule").removeClass("activeLi");
		$(".nodule_listTable").show();//图表模式
		$("#bgMap").hide();//地图模式
		$("#module_picTable").hide();//列表模式
	});
	//今日发电量和总功率切换
	$("#todyEleEchartLi").click(function(){//发电量
		$(this).addClass("activeLi");
		$("#totalPowerLi").removeClass("activeLi");
		curveTypeNum = 1;
		getPointData();
	});
	$("#totalPowerLi").click(function(){//功率
		$(this).addClass("activeLi");
		$("#todyEleEchartLi").removeClass("activeLi");
		curveTypeNum = 0;
		getPointData();
	});
	if(curveTypeNum==1){
		$("#totalPowerLi").removeClass("activeLi");
		$("#todyEleEchartLi").addClass("activeLi");
	}else{
		$("#todyEleEchartLi").removeClass("activeLi");
		$("#totalPowerLi").addClass("activeLi");
	}
	$("#iconSearch").click(function(){
		searchCondition=$("#iconcondition").val();
		getStationList();
	});
	$("#listSearch").click(function(){
		searchCondition=$("#listcondition").val();
		getStationList();
	});
	 
	 //设置1秒调用一次show_cur_times函数		
	setInterval(function(){
		//获取当前日期
		var date_time = new Date();
	 
		var date_str = timeUtil.dateToString(date_time,"HH:mi:ss");//当前时刻
		

		var currData = timeUtil.dateToString(date_time,"yyyy-MM-dd");//当前日期
		$("#currDate").html(currData);	
		
		 //显示在id为showtimes的容器里
		$("#currTime").html(date_str);
		
	},1000);	
	
	
	function weatherDataBack(data){
		if(data){
			var currData = data.date;//当前日期
			var temp = data.temperature;//温度
			temp=temp.split("~")[1].substring(0,temp.split("~")[1].length-1)+" ~ "+temp.split("~")[0]+"℃"
			var weather = data.weather;//天气
			/*console.log(temp);*/
			var h=new Date().getHours();
			//当前时间	
			var img;
			if(h>=7&&h<=19){
				img="<img src='"+data.dayPictureUrl+"'/>"
			}else{
				img="<img src='"+data.nightPictureUrl+"'/>"
			}			
			$("#weatherImg").html(img);
			$("#weath").html(weather);
			$("#dataTemp").html(temp); 	 
		}
	}
	var weather = locationWeather();
	function refreshWeather(){
//		weather.getWeatherXiAn(weatherDataBack);
		weather.getWeatherTaiZh(weatherDataBack);
		
	} 
	
	//刷新天气
	refreshWeather();
	window.setInterval(refreshWeather,1800000); 
	addPageData();
	window.setInterval(addPageData,60000);

})


	 
	 


	/**
	 * 加载页面数据
	 */
	function addPageData(){
		getStationList();
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
				 window.ynz.basePath+"alarmInfo/getAlarmListBySearch.do?page="+ page +"&status=0&pagesize="+pagesize,
					function(response){ 
						console.log(response);
						setAlarmInfo(response.counts,pageNow);
						dataprocess(response.counts,response.results)
			        },
			        function(e){ 
			            console.log("--------error------"+e);
			        }
			    );
		
	}
	/**
	 * 获取电站列表数据
	 */
	function getStationList(){
		indexRestart = 1;
		ynzAjax.post(//加载电站列表
				window.ynz.basePath+"powerStation/getPowerStationInfo.do",
				{"searchstr":searchCondition},
				function(response){ 
//					searchCondition="";
					console.log(response.data);
					setindexData(response.data);
					setPowerStationList(response.data);
					setPowerStationIcon(response.data);
//					if(window.BMap!=undefined){
						setPowerStationToMap(response.data);
//					}
				},
				function(e){ 
					console.log("--------error------"+e);
				}
		);
	}
	/**
	 * 加载页面数据
	 */
	function setindexData(data){
		var generationGross = 0;
		var generatedActivePower = 0;
		var installCapacity = 0;
		console.log(data);
		for(var da in data){
			generationGross+=data[da].generationGross;
			generatedActivePower+=data[da].generatedActivePower;
			installCapacity+=data[da].installCapacity;
		}
		generationGross = toDecimal(generationGross,2);
		generatedActivePower = toDecimal(generatedActivePower,2);
		installCapacity = toDecimal(installCapacity,2);
		var sourceCoo = toDecimal(generationGross*CO2,2);
		var sourceSoo = toDecimal(generationGross*SO2,2);
//		var sourceNoo = toDecimal(generationGross*NO2,2);
		var sourceNoo = toDecimal(generationGross*RM,2);
		var sourceXoo = toDecimal(generationGross*XO,2);
		if(sourceCoo>=0&&sourceCoo<1000){
			$("#sourceCoo").html(toDecimal(sourceCoo,2));
			$("#sourceCoounit").html("g");
		}else if(sourceCoo>=1000&&sourceCoo<1000000){
			$("#sourceCoo").html(toDecimal(sourceCoo/1000,2));
			$("#sourceCoounit").html("kg");
		}else if(sourceCoo>=1000000){
			$("#sourceCoo").html(toDecimal(sourceCoo/1000000,2));
			$("#sourceCoounit").html("t");
		}
		
		if(sourceSoo>=0&&sourceSoo<1000){
			$("#sourceSoo").html(toDecimal(sourceSoo,2));
			$("#sourceSoounit").html("g");
		}else if(sourceSoo>=1000&&sourceSoo<1000000){
			$("#sourceSoo").html(toDecimal(sourceSoo/1000,2));
			$("#sourceSoounit").html("kg");
		}else if(sourceSoo>=1000000){
			$("#sourceSoo").html(toDecimal(sourceSoo/1000000,2));
			$("#sourceSoounit").html("t");
		}
		
		if(sourceNoo>=0&&sourceNoo<1000){
			$("#sourceNoo").html(toDecimal(sourceNoo,2));
			$("#sourceNoounit").html("g");
		}else if(sourceNoo>=1000&&sourceNoo<1000000){
			$("#sourceNoo").html(toDecimal(sourceNoo/1000,2));
			$("#sourceNoounit").html("kg");
		}else if(sourceNoo>=1000000){
			$("#sourceNoo").html(toDecimal(sourceNoo/1000000,2));
			$("#sourceNoounit").html("t");
		}
		
		if(sourceXoo>=0&&sourceXoo<1000){
			$("#sourceXoo").html(toDecimal(sourceXoo));
			$("#sourceXoounit").html("g");
		}else if(sourceXoo>=1000&&sourceXoo<1000000){
			$("#sourceXoo").html(toDecimal(sourceXoo/1000,2));
			$("#sourceXoounit").html("kg");
		}else if(sourceXoo>=1000000){
			$("#sourceXoo").html(toDecimal(sourceXoo/1000000,2));
			$("#sourceXoounit").html("t");
		}
		
		if(generationGross>=10000){
			$("#generationGross").html(toDecimal(generationGross/1000,2));
			$("#generationGrossunit").html("MWh");
		}else{
			$("#generationGross").html(toDecimal(generationGross,2));
			$("#generationGrossunit").html("kWh");
		}
		
		if(generatedActivePower>=10000){
			$("#generatedActivePower").html(toDecimal(generatedActivePower/1000,2));
			$("#generatedActivePowerunit").html("MW");
		}else{
			$("#generatedActivePower").html(toDecimal(generatedActivePower,2));
			$("#generatedActivePowerunit").html("kW");
		}
		
		if(installCapacity>=10000){
			$("#installCapacity").html(toDecimal(installCapacity/1000,2));
			$("#installCapacityunit").html("MWp");
		}else{
			$("#installCapacity").html(toDecimal(installCapacity,2));
			$("#installCapacityunit").html("kWp");
		}
		
		var daliyDay = new Object();
		daliyDay.time=[];
		daliyDay.value=[];
		daliyDay.pr=[];
		daliyDay.name=[];
		generationdata = new Object();
		generationdata.value=[];
		generationdata.time=[];
		generationdataPower = new Object();
		generationdataPower.value=[];
		generationdataPower.time=[];
		var analogIds="";
		var analogPowerIds="";
		for(var i=0;i<data.length;i++){
			//加载pr值图像
			if(data[i].pr==0){
				daliyDay.pr[i]=0;
//				daliyDay.pr[i]=85.6;
				daliyDay.name[i]=data[i].name;
			}else{
				
				daliyDay.pr[i]=toDecimal(data[i].pr,3);
				daliyDay.name[i]=data[i].name;
			}
			//加载装机容量列表
			var html="";
			html+="<li class='stationName'><span>"+window.ynz.local.powerstation_name+"</span><em>"+window.ynz.local.index_capacity+"(kWp)</em></li>";
			daliyDay.color=[];
			daliyDay.percent=[];
			var colorobj=["#62cf8c",
			              "#09aded","#b261c2","#f7c216",
			              "#999999","#dc4257","#0284dc",
			              "#e1741a","#000"]
			
			for(var e in data){
				var colordata = colorobj[e%9];
				var obj = new Object();
				obj.value=toDecimal(data[e].installCapacity,3);
				obj.name=data[e].name;
				daliyDay.percent[e]=obj;
				daliyDay.color[e]=colordata;
				html+="<li class='list' style='color:"+colordata+";'><i style='background:"+colordata+";'></i><span style='width:96px;display: inline-block;overflow: hidden;text-overflow: ellipsis;'>"+data[e].name+"</span><em>"+toDecimal(data[e].installCapacity,3)+"</em></li>";
			}
			$("#installCapaitypercent").html("");
			$("#installCapaitypercent").append(html);
			//加载日发电量曲线
			if(data[i].generationGrossId!=-1){
				if(analogIds==""){
					analogIds+=data[i].generationDailyId;
//					analogIds+=data[i].generationGrossId;
				}else{
					analogIds+=","+data[i].generationDailyId;
//					analogIds+=","+data[i].generationGrossId;
				}
			}
			if(data[i].generatedActivePowerId!=-1){
				if(analogPowerIds==""){
					analogPowerIds+=data[i].generatedActivePowerId;
				}else{
					analogPowerIds+=","+data[i].generatedActivePowerId;
				}
			}
		}
		remarkDaliyDay = daliyDay;
		remarkgenerationIds = analogIds;
		remarkpowerIds = analogPowerIds;
		getPointData();
	}
	function getPointData(){
		var endTime1 = timeUtil.dateToString(new Date(),"yyyy-mm-dd");
		var endTime = timeUtil.dateToString(new Date(),"yyyy-mm-dd hh:mi:ss");
		var end2 = (new Date(endTime1+" 21:00:00")).getTime()>(new Date()).getTime()?endTime:endTime1+" 21:00:00";
		if(curveTypeNum==1){
			ynzAjax.post(//电站当天日发电量历史
					window.ynz.basePath+"pointInfo/getPointHoursGenerations.do",
					{   "analoginputIds":remarkgenerationIds,
						"startTime":timeUtil.dateToString(new Date(),"yyyy-mm-dd")+" 04:00:00",
						"endTime":end2,
						"minutesSpan":5,
						"haveReal":1
					},
					function(response){ 
						console.log(response.data);
						setgenerationDailyList(response.data,remarkDaliyDay);
					},
					function(e){ 
						console.log("--------error------"+e);
					}
			);
		}else{
			var end = (new Date(endTime1+" 21:00:00")).getTime()>(new Date()).getTime()?endTime:endTime1+" 21:00:00";
			ynzAjax.post(//电站当天功率历史
					window.ynz.basePath+"stationmonitor/historyRedress/points.do",
					{   "analoginputIds":remarkpowerIds,
						"startTime":timeUtil.dateToString(new Date(),"yyyy-mm-dd")+" 04:00:00",
						"endTime":end,
						"minutesSpan":5,
						"haveReal":1
					},
					function(response){ 
						console.log(response.data);
						setgenerationPowerList(response.data,remarkDaliyDay);
					},
					function(e){ 
						console.log("--------error------"+e);
					}
				);
		}
			
	}
	function setgenerationDailyList(data,daliyDay){
			if(data.length!=0){
				if(data.length==1){
					var value = toDecimal(0,3)
					generationdata.time[0]=timeUtil.timeToString(new Date(),"HH:mi");
					generationdata.value[0]=value;
				}else{
					for(var j=0;j<data.length;j++){
						var value = toDecimal(data[j].value,3)
						generationdata.value[j]=value;
						generationdata.time[j]=timeUtil.timeToString(data[j].time,"HH:mi");
					}
				}
			}else{
				generationdata.time[0]=timeUtil.dateToString(new Date(),"HH:mi");
				generationdata.value[0]=0;
			}
		daliyDay.yname=window.ynz.local.meter_generating+"（kWh）"
		daliyDay.lendname=window.ynz.local.meter_generating;
		daliyDay.type="bar";
		daliyDay.time=generationdata.time;
		daliyDay.value=generationdata.value;
		if(curveTypeNum==1){
			echarData = daliyDay;
			setOption(daliyDay);
		}
	}
	function setgenerationPowerList(data,daliyDay){
		for(var ii in data){
			if(data[ii].historyDatas.length!=0){
				if(data[ii].historyDatas.length==1){
					var value = toDecimal(0,3)
					generationdataPower.time[0]=timeUtil.timeToString(new Date(),"HH:mi");
					generationdataPower.value[0]=value;
				}else{
					if(generationdataPower.time.length!=0){
						for(var j=0;j<data[ii].historyDatas.length;j++){
							var value = toDecimal(data[ii].historyDatas[j].value,3)
							generationdataPower.value[j]+=value;
						}
					}else{
						for(var k=0;k<data[ii].historyDatas.length;k++){
							var value = toDecimal(data[ii].historyDatas[k].value,3)
							generationdataPower.time[k]=timeUtil.timeToString(data[ii].historyDatas[k].time,"HH:mi");
							generationdataPower.value[k]=value;
						}
					}
				}
			}else{
				generationdataPower.time[0]=timeUtil.dateToString(new Date(),"HH:mi");
				generationdataPower.value[0]=0;
			}
			daliyDay.yname=window.ynz.local.inverter_power+"（kW）";
			daliyDay.lendname=window.ynz.local.inverter_power;
			daliyDay.type="line";
			daliyDay.time=generationdataPower.time;
			daliyDay.value=generationdataPower.value;
			if(curveTypeNum==0){
				echarData = daliyDay;
				setOption(daliyDay);
			}
		}
		
	}
	/**
	 * 加载电站列表信息
	 * @param data
	 */
	function setPowerStationList(data){
		var html = "";
		html+="<li class='tabTitle'><span style='width:10%'>"+window.ynz.local.powerstation_number+"</span><span style='width:16%'>"+window.ynz.local.powerstation_name+"</span><span style='width:15%'>"+window.ynz.local.powerstation_investment+"</span>" +
				"<span style='width:10%'>"+window.ynz.local.powerstation_manager+"</span><span style='width:10%'>"+window.ynz.local.index_capacity+"(kWp)</span><span style='width:10%'>"+window.ynz.local.index_temperature+"(℃)</span><span style='width:10%'>"+window.ynz.local.index_sunlight_day+"(W/㎡)</span>" +
				"<span style='width:9%'>"+window.ynz.local.inverter_power+"(kW)</span><span style='width:10%'>"+window.ynz.local.index_system_efficiency+"(%)</span></li>";
		for(var i=0;i<data.length;i++){
			html+="<a href='"+window.ynz.basePath+"index/"+data[i].id+".do?powerStationId="+data[i].id+"'><li class='tabTitle'><span style='width:10%'>"+data[i].code+"</span><span style='width:16%'>"+data[i].name+"</span><span style='width:15%'>"+data[i].investFirmName+"</span>" +
			"<span style='width:10%'>"+data[i].manager+"</span><span style='width:10%'>"+toDecimal(data[i].installCapacity,3)+"</span><span style='width:10%'>"+toDecimal(data[i].temperature,3)+"</span>" +
			"<span style='width:10%'>"+toDecimal(data[i].irradiance,3)+"</span>" +
			"<span style='width:9%'>"+toDecimal(data[i].generatedActivePower,3)+"</span><span style='width:10%'>"+toDecimal(data[i].pr,3)+"</span></li></a>";
		}
		$("#powerStationList").html("");
		$("#powerStationList").append(html);
	} 
	/**
	 * 加载电站图表信息
	 * @param data
	 */
	function setPowerStationIcon(data){
		var html = "";
		for(var i=0;i<data.length;i++){
			var url =basePath+"assets/pages/img/ynz/plan.png"; 
			if(data[i].imgUrl!="" && data[i].imgUrl!=null){
				url = data[i].imgUrl;
			}
			var irradiancevalue = toDecimal(data[i].irradiance,3)>999?toDecimal(data[i].irradiance/1000,3):toDecimal(data[i].irradiance,3);
			var irradiancestr = toDecimal(data[i].irradiance,3)>999?"kW/㎡":"W/㎡";
			html+="<div class='stationList'><h3>"+data[i].name+"</h3><div><div class='pjCon'>" +
			"<div class='pjsleftL'><a href='"+window.ynz.basePath+"index/"+data[i].id+".do?powerStationId="+data[i].id+"'><img src='"+url+"'/></a></div>" +
			"<dl class='pjsleftB'>"+
			"<dd><span>"+window.ynz.local.powerstation_number+" :  </span> <em>"+data[i].code+"</em></dd>" +
			"<dd class='companyName'><span>"+window.ynz.local.powerstation_investment+" :  </span> <em>"+data[i].investFirmName+"</em></dd>" +
			"<dd><span>"+window.ynz.local.powerstation_manager+" : </span> <em>"+data[i].manager+"</em></dd></dl>" +
//			"<dd><span>"+window.ynz.local.weather_exposure+" : </span> <em>"+toDecimal(data[i].radiantExposure,3)+"</em><b>kWh/㎡</b></dd>" +
					"</div></div>" +
			"<ul class='clearfix'>" +
			"<li><span>"+window.ynz.local.index_capacity+" ：</span><em>"+toDecimal(data[i].installCapacity,3)+"</em><b>kWp</b></li>" +
			"<li><span>"+window.ynz.local.weather_element_temperature+"：</span><em>"+toDecimal(data[i].temperature,3)+"</em><b>℃</b></li>" +
			"<li><span>"+window.ynz.local.index_sunlight_day+" ：</span><em>"+irradiancevalue+"</em><b>"+irradiancestr+"</b></li>" +
			"<li><span>"+window.ynz.local.inverter_power+" ：</span><em>"+toDecimal(data[i].generatedActivePower,3)+"</em><b>kW</b></li>" +
			"<li><span>"+window.ynz.local.index_system_power+" ：</span><em>"+toDecimal(data[i].pr,3)+"</em><b>%</b></li></ul></div>";
				
				
				
				
				
				
				
				
				
				
				
				
				
				/*"<div class='stationList'><h3>"+data[i].name+"</h3><dl>" +
			"<dt><a href='"+window.ynz.basePath+"index/"+data[i].id+".do?powerStationId="+data[i].id+"'><img src='"+url+"'/></a></dt>" +
			"<dd><span>"+window.ynz.local.powerstation_number+" :  </span> <em>"+data[i].code+"</em></dd>" +
			"<dd class='companyName'><span>"+window.ynz.local.powerstation_investment+" :  </span> <em>"+data[i].investFirmName+"</em></dd>" +
			"<dd><span>"+window.ynz.local.powerstation_manager+" : </span> <em>"+data[i].manager+"</em></dd>" +
//			"<dd><span>"+window.ynz.local.weather_exposure+" : </span> <em>"+toDecimal(data[i].radiantExposure,3)+"</em><b>kWh/㎡</b></dd>" +
					"</dl>" +
			"<ul class='clearfix'>" +
			"<li><span>"+window.ynz.local.index_capacity+" ：</span><em>"+toDecimal(data[i].installCapacity,3)+"</em><b>kWp</b></li>" +
			"<li><span>"+window.ynz.local.weather_element_temperature+"：</span><em>"+toDecimal(data[i].temperature,3)+"</em><b>℃</b></li>" +
			"<li><span>"+window.ynz.local.index_sunlight_day+" ：</span><em>"+irradiancevalue+"</em><b>"+irradiancestr+"</b></li>" +
			"<li><span>"+window.ynz.local.inverter_power+" ：</span><em>"+toDecimal(data[i].generatedActivePower,3)+"</em><b>kW</b></li>" +
			"<li><span>"+window.ynz.local.index_system_power+" ：</span><em>"+toDecimal(data[i].pr,3)+"</em><b>%</b></li></ul></div>";*/
		}
		$("#powerStationIcon").html("");
		$("#powerStationIcon").append(html);
	} 


		function setPowerStationToMap(data){
			if(!BdMap)
				return;
				
			
			
			BdMap.clearOverlays(); 
			
			
			
			
			
			
			for(var i=0;i<data.length;i++){
				var item = data[i];
				(function (item){
					var url =basePath+"assets/pages/img/ynz/plan.png"; 
					if(data[i].imgUrl!="" && data[i].imgUrl!=null){
						url = data[i].imgUrl;
					}
					var myIcon = new BMap.Icon(basePath + "assets/pages/img/powerStation.png",
							new BMap.Size(35, 41), {
								anchor : new BMap.Size(17.5, 41),
								imageOffset : new BMap.Size(0, 0)
							});
					var irradiancevalue = toDecimal(item.irradiance,3)>999?toDecimal(item.irradiance/1000,3):toDecimal(item.irradiance,3);
					var irradiancestr = toDecimal(item.irradiance,3)>999?"kW/㎡":"W/㎡";
					var sContent =
						"<div><h4 class='mapTitle'><a target='_blank' href='"+ window.ynz.basePath+"index/"+data[i].id+".do'>"+ data[i].name +"</a></h4><span class='alarmNum'>"+window.ynz.local.index_alarm+":"+data[i].alarms+"</span>" + 
						"<a  target='_blank'  href='"+ window.ynz.basePath+"index/"+item.id+".do?powerStationId="+item.id+"'><img id='imgDemo' class='imgDemo' src='"+ url +"' title=''/></a>" + 
							"<table class='mapTable'>" +
							"<tr><td><span>"+window.ynz.local.index_capacity+" ：</span><em>"+toDecimal(item.installCapacity,3)+"</em><b>kWp</b></td></tr>" +
							"<tr><td><span>"+window.ynz.local.weather_environment_temperature+" ：</span><em>"+toDecimal(item.temperature,3)+"</em><b>℃</b></td></tr>" +
							"<tr><td><span>"+window.ynz.local.index_sunlight_day+" ：</span><em>"+irradiancevalue+"</em><b>"+irradiancestr+"</b></td></tr>" +
							"<tr><td><span>"+window.ynz.local.inverter_power+" ：</span><em>"+toDecimal(item.generatedActivePower,3)+"</em><b>kW</b></td></tr>" +
	//						"<tr><td><span>"+window.ynz.local.index_system_power+" ：</span><em>"+85.6+"</em><b>%</b></td></tr></table></a>" + 
							"<tr><td><span>"+window.ynz.local.index_system_power+"：</span><em>"+toDecimal(item.pr,3)+"</em><b>%</b></td></tr></table>" + 
							"</div>";
					var infoWindow = new BMap.InfoWindow(sContent);  // 创建信息窗口对象 
				 
					var point = new BMap.Point(item.lng,item.lat); 
					var marker = new BMap.Marker(point,{icon:myIcon});  
						BdMap.addOverlay(marker);
					
					
					
	//				var markerTest = new BMap.Marker(point);  
	//				BdMap.addOverlay(markerTest);
				   //图片加载完毕重绘infowindow
	//			   document.getElementById('imgDemo').onload = function (){
	//				   infoWindow.redraw();   //防止在网速较慢，图片未加载时，生成的信息框高度比图片的总高度小，导致图片部分被隐藏
	//			   }
					var url = window.ynz.basePath+"index/"+item.id+".do";
				
	
					//marker.openInfoWindow(infoWindow);
					
					marker.addEventListener("click",function(e){ 
						var isOpen = infoWindow.isOpen();
						if(!isOpen){
							this.openInfoWindow(infoWindow);
						}
					}); 
					
				}			
				)(item); 
				
			} 
	 
		}



	/**
	 * 加载报警列表赋值
	 * @param data
	 */
	function setAlarmInfo(data,currentPage){//获取报警数据，使用分页插件
			 $("#alarmList").pageUtil({
					"data":data,//显示数据
					"pageSize":defaultPageSize,
					"pageId":"alarmListPage",
					"currentPage":currentPage,
					"callBack":function(results){
						getAlarmInfo(results.currentPage,defaultPageSize);
					}
			});
			 $("#alarmIcon").pageUtil({
				 "data":data,//显示数据
				"pageSize":defaultPageSize,
				"pageId":"alarmIconPage",
				"currentPage":currentPage,
				"callBack":function(results){
					getAlarmInfo(results.currentPage,defaultPageSize);
				}
			 });

		
	}

	function dataprocess(length,results){
		var html="";
		html+="<li class='tabTitle'><i>"+length+"</i>"+
		"<span  style='width:8%;'>"+window.ynz.local.alarm_id+"</span><span  style='width:38%;'>"+window.ynz.local.powerstation_name+"</span><span>"+window.ynz.local.alarm_fault+"</span><span  style='width:11%;'>"+window.ynz.local.alarm_level+"</span><span style='width:23%;'>"+window.ynz.local.alarm_alarmtime+"</span></li>";
		for(var e=0;e<results.length;e++){
//			cHtml+="<li>"+ result[i].name+"</li>";//处理数据
			var level = "";
			if(results[e].alarmGrade==1){
				level="I";
			}else if(results[e].alarmGrade==2){
				level="II";
			}else{
				level="III";
			}
			html+="<li><span style='width:8%;'>"+results[e].id+"</span>" +
			"<span class='stationName' style='width:38%;'>"+results[e].name+"</span>" +
			"<span>"+results[e].alarmMessage+"</span>" +
			"<span style='width:11%;'>"+level+"</span>" +
			"<span style='width:23%;'>"+timeUtil.dateToString(new Date(results[e].eventTime),"mm-dd HH:MI:SS")+"</span></li>";
		}
		$("#alarmIcon").empty();
		$("#alarmIcon").append(html);
		$("#alarmList").empty();
		$("#alarmList").append(html);
		
	}
	

 function asyncLoading()   
    {  
        var script = document.createElement("script");  
        script.type = "text/javascript";  
        script.src = "http://api.map.baidu.com/api?v=2.0&ak=3j6qn3gMTZgGCzOegAxyF3wP&services=false&callback=initialize"; 
        document.getElementsByTagName("head")[0].appendChild(script);  
        var script2 = document.createElement("script");  
        script2.type = "text/javascript";  
        script2.src = "http://api.map.baidu.com/library/AreaRestriction/1.2/src/AreaRestriction_min.js";  
        document.getElementsByTagName("head")[0].appendChild(script2); 
    }  

function initialize(){  
	BdMap = new BMap.Map("bgMap", {minZoom:5,maxZoom:17,enableMapClick: false});
	BdMap.centerAndZoom(new BMap.Point(100, 32.915), 5);
	BdMap.enableScrollWheelZoom();			//开启鼠标滚轮缩放
	BdMap.enableDragging();					//启用地图拖拽
	BdMap.setMapType(BMAP_HYBRID_MAP);		//设置为卫星地图
	BdMap.disableDoubleClickZoom();			//禁用地图的双击放大
	BdMap.disableDoubleClickZoom();
	
	addPageData();
}

function clickTuModule(){
	$("#tuModule").addClass("activeLi");
	$("#bgMapBtn").removeClass("activeLi");
	$("#listModule").removeClass("activeLi");
	$("#module_picTable").show();//图表模式
	$("#bgMap").hide();//地图模式
	$("#nodule_listTable").hide();//列表模式
}

function checkOnline(){
		var url = window.ynz.weatherUrl+"西安";
		$.ajax({
			url:url,
			dataType:"json",
			async:true,
			success:function(data){
				isOnline=true;
				asyncLoading();
			},
			error:function(e) {
				console.log(e); 
				isOnline=false;
				clickTuModule();
			}
		}) 
    }