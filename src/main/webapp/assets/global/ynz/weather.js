function locationWeather() {

	var weather={

	}
	
	weather.getWeatherByPoint = function (lng,lat,calFun){ 
		$.ajax({
			url:"http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location="+lat+","+lng+"&ak=ulSlfgICpKXrN1dfpQ9w9mWITpkZLhUo&output=json&pois=1",
			dataType:"jsonp",
			async:true,
			success:function(data){ 
				var cityInfo = data.result;
				var cityname = "北京";
				if(cityInfo){
					cityname = cityInfo.addressComponent.city;
					console.log("---------"+cityname); 
				}
				$("#localhostCity").html(cityname);
				var url = window.ynz.weatherUrl+cityname;
				$.ajax({
					url:url,
					dataType:"json", 
					async:true,
					success:function(data){
						console.log(data)
//						var _data=data.results[0].weather_data[0];
						if(calFun){
							calFun(data);
						}

					},
					error:function(e) {
						console.log("获取天气失败 "+e); 
					}
				}) 
			},
			error:function(e){
				console.log("获取天气,定位失败");
			}
		});  
	}  

	weather.getWeather = function (calFun){ 
		$.ajax({
			url:"http://api.map.baidu.com/location/ip?ak=ulSlfgICpKXrN1dfpQ9w9mWITpkZLhUo&coor=bd09ll",
			dataType:"jsonp",
			async:true,
			success:function(data){ 
				var cityInfo = data;
				var cityname = "北京";
				if(cityInfo){
					cityname = cityInfo.content.address_detail.city;
					console.log("---------"+cityname); 
					if($("#city")){
						$("#city").html(cityname);
						$("#localhostCity").html(cityname);
					}
				}
				var url = window.ynz.weatherUrl+cityname;
				$.ajax({
					url:url,
					dataType:"json",
					async:true,
					success:function(data){
						console.log(data)
						var _data=data.results[0].weather_data[0];
					 		
						if(calFun){
							calFun(_data);
						}

					},
					error:function(e) {
						console.log("获取天气失败 "+url); 
						console.log(e); 
					}
				}) 
			},
			error:function(e){
				console.log(e);
				console.log("获取天气,定位失败");
			}
		});  
	} 
	weather.getWeatherXiAn = function (calFun){ 
		if($("#city")){
			$("#city").html("西安");
			$("#localhostCity").html("西安");
		}
		var url = window.ynz.weatherUrl+"西安";
		$.ajax({
			url:url,
			dataType:"json",
			async:true,
			success:function(data){
				console.log(data)
				var _data=data.results[0].weather_data[0];
			 		
				if(calFun){
					calFun(_data);
				}

			},
			error:function(e) {
				console.log("获取天气失败 "+url); 
				console.log(e); 
			}
		}) 
	} 
	weather.getWeatherTaiZh = function (calFun){ 
		if($("#city")){
			$("#city").html("泰州");
			$("#localhostCity").html("泰州");
		}
		var url = window.ynz.weatherUrl+"泰州";
		$.ajax({
			url:url,
			dataType:"json",
			async:true,
			success:function(data){
				console.log(data)
				var _data=data.results[0].weather_data[0];
			 		
				if(calFun){
					calFun(_data);
				}

			},
			error:function(e) {
				console.log("获取天气失败 "+url); 
				console.log(e); 
			}
		}) 
	} 
	
	
	return weather;
}


