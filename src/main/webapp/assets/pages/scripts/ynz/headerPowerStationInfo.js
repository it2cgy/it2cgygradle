var monitoringpath = "";
$(function(){
	 if(window.ynz.longieb.powerStationId && window.ynz.longieb.powerStationId>0){
//		 ynzAjax.get(//加载电站信息
//					window.ynz.path+"/powerStation/powerStationDetails/"+window.ynz.longieb.powerStationId+".do",
//					function(response){ 
//						$("#headerAddress").html(response.data.address);
//						$("#headerInstallCapacity").html(response.data.installCapacity+" kWp");
//						$("#headerLat").html(toDecimal(response.data.lat,5)+"°");
//						$("#headerLng").html(toDecimal(response.data.lng,5)+"°");
//						if(!window.ynz.admin||window.ynz.admin==null){
//							$("#headerPowerStationName").hide();
//							$("#powerStationName").html(response.data.name);
//						}else{
//							$("#headerPowerStationName").css("font-size","25px");
//							$("#headerPowerStationName").css("height","80px");
//							$("#headerPowerStationName").css("line-height","80px");
//						}
//						monitoringpath=response.data.monitoring;
//			        },
//			        function(e){ 
//			            console.log("--------error------"+e);
//			        }
//			    );
//		var powerStationBaseInfo = redirectStationBaseInfo;
		$("#headerAddress").html(redirectStationBaseInfo.address);
		$("#headerInstallCapacity").html(redirectStationBaseInfo.installCapacity+" kWp");
		$("#headerLat").html(toDecimal(redirectStationBaseInfo.lat,5)+"°");
		$("#headerLng").html(toDecimal(redirectStationBaseInfo.lng,5)+"°");
		if(!window.ynz.admin||window.ynz.admin==null){
			$("#headerPowerStationName").hide();
			$("#powerStationName").html(redirectStationBaseInfo.name);
		}else{
			$("#headerPowerStationName").css("font-size","25px");
			$("#headerPowerStationName").css("height","80px");
			$("#headerPowerStationName").css("line-height","80px");
		}
		monitoringpath=redirectStationBaseInfo.monitoring;
	}else{
		$("#headerPowerStationName").html(window.ynz.local.index_title);
		$("#headerPowerStationName").css("font-size","25px");
		$("#headerPowerStationName").css("height","80px");
		$("#headerPowerStationName").css("line-height","80px");
	}
	$("#monitoringpath").click(function(){
		if(monitoringpath==""||monitoringpath==null){
			message.alert(window.ynz.local.tip,"暂未设置视频监控地址",3);
		}else{
			window.open(monitoringpath);
		}
	});
})