<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/page/common/pub.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta name="renderer" content="webkit">
	    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<title>${index_longieb_substation_page}</title>
		<!-- start css style by pjs-->
		<%@ include file="/page/common/pubCss.jsp" %>
		<link href="${basePath}assets/pages/css/ynz/subsiteIndex.css?version=20170817024514" rel='stylesheet' type='text/css'  />
		<link href="${basePath}assets/pages/css/ynz/scrollDefined.css?version=20170817024514" rel='stylesheet' type='text/css'  />
		<!--end css style by pjs-->
		<script type="text/javascript">
		  var powerStationBaseInfo = <%=com.yunengzhe.commons.util.JsonUtil.beanToJson(request.getAttribute("powerstation")) %>
		</script>
		<script type="text/javascript">
		window.ynz.weatherUrl= "<%=com.yunengzhe.commons.util.PropertiesUtil.getString("weatherurl") %>";
		</script> 
		<style type="text/css" id="css">
			.bottomBox{
				height:30px;
				line-height:30px;
				position: fixed;
				bottom: 0;
				left:0;
				z-index:9999;
				font-size:18px;
				animation:play 50s linear infinite;
			}
			.bottomBox span{
				margin-right:10px;
			}
			.indexcheck{
				min-width:514px;
				background:#1E2D48;
				padding:0 10px;
				text-align:center;
			}
		</style> 
	</head>
	<body>
		<%@ include file="/page/common/companyHead.jsp" %>
		<%@ include file="/page/common/companyAdminMenu.jsp" %>
		<!--内容区start-->
		<section>
			<div class="content">
				<!--左边区域 start-->
				<div class="leftRightCon">
					<%-- <div class="menu" id="menu">
						<%@ include file="/page/common/companyAdminMenu.jsp" %>
					</div> --%>
					<div class="detailData" id="detailData">
						<!--数据表1start-->
						<div class="dataList dataList1">
							<h3 class="basicTitle titleBg">${inverter_electric_information}</h3>
							<div class="energyBox">
								<dl class="col-md-6 no-padding borderDiv">
									<dt><img src="${basePath}assets/pages/img/ynz/gonglv.gif" alt="发电功率图片"/></dt>
									<dd><em id="generatedActivePower"></em><i>kW</i></dd>
									<dd>${inverter_current_power}</dd>
								</dl>
								<dl class="col-md-6 no-padding borderDiv">
									<dt><img src="${basePath}assets/pages/img/ynz/energy.gif" alt="发电功率图片"/></dt>
									<dd><em id="generationGross"></em><i id="GrossUnit"></i></dd>
									<dd>${meter_total_generating}</dd>
								</dl>
							</div>
							<div class="energyBox energyBox2">
								<dl class="col-md-4 no-padding borderDiv">
									<dt><img src="${basePath}assets/pages/img/ynz/gonglv.gif" alt="发电功率图片"/></dt>
									<dd><em id="generationDaily"></em><i id="DayUnit"></i></dd>
									<dd>${inverter_generating_days}</dd>
								</dl>
								<dl class="col-md-4 no-padding borderDiv">
									<dt><img src="${basePath}assets/pages/img/ynz/energy.gif" alt="发电功率图片"/></dt>
									<dd><em id="generationMonth"></em><i id="MonthUnit"></i></dd>
									<dd>${inverter_generating_mouth}</dd>
								</dl>
								<dl class="col-md-4 no-padding borderDiv">
									<dt><img src="${basePath}assets/pages/img/ynz/energy.gif" alt="发电功率图片"/></dt>
									<dd><em id="generationYear"></em><i id="YearUnit"></i></dd>
									<dd>${inverter_generating_year}</dd>
								</dl>
							</div>
						</div><!--数据表1end-->
						<!--数据表2start-->
						<div class="dataList dataList2">
							<ul class="tanchange" id="tanchange">
								<li id="electricLi" class="activeLi"><a href="###">${meter_generating}</a></li>
								<li id="totalPowerLi"><a href="###">${inverter_power}</a></li>
							</ul>
							<!-- <p class="tuChange" id="tuChange">
								<span class="activeSpan">曲线图</span>   /   <span>柱状图</span>
							</p> -->
							<div class="tuBox">
								<div class="electricBox" id="todyEleEchart"></div>
								<!-- <div class="electricBox" id="gongLv" style="display:none;background:orange;"></div> -->
							</div>
						</div>
						<!--数据表2end-->
						<!-- 数据表3start -->
						<div class="dataList dataList3">
							<h3 class="basicTitle titleBg">${inverter_alarminfo}</h3>
							<div class="alarmImg"></div>
							<div class="alarmBox">
								<em id="alarmsCounts"></em>
								<p class="totalAlarm">${index_alarms_total}</p>
								<a href="${basePath}alarmPage/actualAlarm?powerStationId=${powerstation.id}" class="detail">${index_find_detail}</a>
							</div>
							
						</div>
						<!-- 数据表3end -->
					</div>
				</div>
				<!--左边区域 end-->
				<!--中间区域 start-->
				<div class="centerCon">
					<div class="mainCon">
						<h3 id="powerStationName" class="basicTitle titleBg"></h3>
						<!-- <h4 class="plantAddr">电站地址：<span>陕西省西安市长安区航天中路186号</span></h4> -->
						<div class="plantImg">							
 							<a href="${basePath}stationSource/html/plantMess${powerStationId }.html"><img src="${basePath}assets/pages/img/ynz/arrangPlant1.png"/></a>
 							<%-- <c:if test="${powerstation.havebuzhi==null or powerstation.havebuzhi!=1}">
 								<iframe src="${basePath}stationSource/html/notFound.html" width="100%" height="100%" style="border:0;"></iframe>
 							</c:if>
							<c:if test="${powerstation.havebuzhi==1}">
 								<iframe src="${basePath}stationSource/html/plantMess${powerStationId }.html" width="100%" height="100%" style="border:0;"></iframe>
 							</c:if> --%>
						</div>
						
					</div>
				</div>
				<!--中间区域 end-->
				<!--右边区域 start-->
				<div class="leftRightCon">
					
					<div class="bgBox weatherMess"><!--气象信息start-->
						<h3 class="basicTitle titleBg">${iv_weather_info}</h3>
						<div class="city"  id="localhostCity">
							<!-- <span id="localhostCity" class="localhostCity"></span> -->
						</div>
						<div class="time timeBox">
							<dl>
								<dt id="weatherTime"></dt>
								<dd id="weatherDate"></dd>
							</dl> 
						</div>
						<div class="weatherBox no-padding">
							<dl>
								<dd>${index_nowtoday}</dd>
								<dt><img id="weatherImg1" src="${basePath}assets/pages/img/ynz/leizhenyu.png" alt="天气图片"/></dt>
								<dd id="weather_weather"></dd>
							</dl>
							<dl class="weatherSeond">
								<dd>${index_tomorrow}</dd>
								<dt><img id="weatherImg2" src="${basePath}assets/pages/img/ynz/leizhenyu.png" alt="天气图片"/></dt>
								<dd id="weather_weather2"></dd>
							</dl>
							<dl>
								<dd>${index_day_after_tomorrow}</dd>
								<dt><img id="weatherImg3" src="${basePath}assets/pages/img/ynz/leizhenyu.png" alt="天气图片"/></dt>
								<dd id="weather_weather3"></dd>
							</dl>
						</div><!--气象信息end-->
						<div class="clearfix"></div>
						<div class="eleDate">
						<div class="eleDateBox">
							<dl class="col-md-3 no-padding eleDateList">
								<dt><span id="irradiance"></span>W/m²</dt>
								<dd>${index_irr_level}</dd>
							</dl>
							<dl class="col-md-3 no-padding eleDateList">
								<dt><span id="inclinedRadiation"></span>W/m²</dt>
								<dd>${index_irr_incil}</dd>
							</dl>
							<dl class="col-md-3 no-padding eleDateList">
								<dt><span id="scateredIrradiance"></span>W/m²</dt>
								<dd>${index_irr_scatered}</dd>
							</dl>
							<dl class="col-md-3 no-padding eleDateList noBorder">
								<dt><span id="directIrradiance"></span>W/m²</dt>
								<dd>${index_irr_direct}</dd>
							</dl>
						</div>
						<div class="eleDateBox noBorder">
							<dl class="col-md-3 no-padding eleDateList">
								<dt style='font-size:16px'><span id="temperature"></span>℃</dt>
								<dd>${index_temprature}</dd>
							</dl>
							<dl class="col-md-3 no-padding eleDateList">
								<dt><span id="windSpeed"></span>m/s</dt>
								<dd>${index_speed}</dd>
							</dl>
							<dl class="col-md-3 no-padding eleDateList">
								<dt style='font-size:16px'><span id="windDirection"></span>°</dt>
								<dd>${index_wind_drict}</dd>
							</dl>
							<dl class="col-md-3 no-padding eleDateList noBorder">
								<dt><span id="rainFall"></span>mm</dt>
								<dd>${index_precipitation}</dd>
							</dl>
						</div>
					</div>
					</div>
					
					<!--各气象要素start-->
					<%-- <div class="weatherMess eleDate">
						<div class="eleDateBox">
							<dl class="col-md-3 no-padding eleDateList">
								<dt><span id="irradiance"></span>w/m²</dt>
								<dd>${index_irr_level}</dd>
							</dl>
							<dl class="col-md-3 no-padding eleDateList">
								<dt><span id="inclinedRadiation"></span>w/m²</dt>
								<dd>${index_irr_incil}</dd>
							</dl>
							<dl class="col-md-3 no-padding eleDateList">
								<dt><span id="scateredRadiation"></span>w/m²</dt>
								<dd>${index_irr_scatered}</dd>
							</dl>
							<dl class="col-md-3 no-padding eleDateList">
								<dt><span id="directRadiation"></span>w/m²</dt>
								<dd>${index_irr_direct}</dd>
							</dl>
						</div>
						<div class="eleDateBox noBorder">
							<dl class="col-md-3 no-padding eleDateList">
								<dt><span id="temperature"></span>℃</dt>
								<dd>${index_temprature}</dd>
							</dl>
							<dl class="col-md-3 no-padding eleDateList">
								<dt><span id="windSpeed"></span>kt</dt>
								<dd>${index_speed}</dd>
							</dl>
							<dl class="col-md-3 no-padding eleDateList">
								<dt><span id="windDirection"></span>°</dt>
								<dd>${index_wind_drict}</dd>
							</dl>
							<dl class="col-md-3 no-padding eleDateList">
								<dt><span id="rainFall"></span>mm</dt>
								<dd>${index_precipitation}</dd>
							</dl>
						</div>
					</div> --%>
					<!--各气象要素end-->
					<!-- 电站概况start -->
					<div class="dataList plantBox">
						<a href="${basePath}/powerstation/powerstationDetails?powerId=${powerStationId}&powerStationId=${powerStationId}" id="showBasic"><h3 class="basicTitle titleBg">${index_station_info}</h3></a>
						<!-- <p class="dataP"><em id="installCapacity"></em>&nbsp;&nbsp;MW</p> -->
						<div class="capacitBox"></div>
						<div class="alarmBox">
							<em id="installCapacity"></em>
							<span class="detail" style="right: -8px;top: 58px;">kWp</span>
							<p class="totalAlarm">${powerstation_installed_capacity}</p>
						</div>
							<%-- <p class="tit col-md-offset-2">${powerstation_installed_capacity}</p>
							<button type="button" class="btn blue-madison col-md-offset-1 alertBtn" onclick="showDes()">${powerstation_description}</button> --%>
						
						<ul class="plantDetailData col-md-12">
							<li>${index_runtime_day}<span><em id="runDays"></em><b>${index_day}</b></span></li>
							<li class="proTimer">${index_station_product_time}<em id="runTime"></em></li>
							<li>${inverter_totalquantity}<span><em id="inverterCounts"></em><b>${inverter_stage}</b></span></li>
							<li>${index_system_efficiency}<span><em id="pr"></em><b>%</b></span></li>
						</ul>
					</div>
					<!-- 电站概况end -->
				</div>
				<!--右边区域 end-->
				<!-- 弹框start -->
				<div class="modal fade in" id="basic" tabindex="-1" role="basic" aria-hidden="true" style="display: none; padding-right: 17px;">
                    <div class="modal-dialog" style="margin-top:230px;">
                        <div class="modal-content borderRadius5" style="width: 500px;">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="cancel()"></button>
                                <h4 class="modal-title">${powerstation_description}</h4>
                            </div>
                            <div class="modal-body" style="overflow: hidden;">                           	
                            	<textarea name="" rows="20" cols="75" style="resize:none" readonly="readonly" style="padding-left:15px;">${powerstation.description}</textarea>
                            </div>
                            <div class="modal-footer btngroup">
                            	<form action="" method="post">
                                	<button type="button" class="btn btn-circle red-sunglo submitBtn" onclick="cancel()">${closed}</button>
                               </form>
                            </div>
                        </div>
                    </div>
                </div>
				<!-- 弹框end -->
			</div>
			<div id="bottomBox" class="bottomBox">
				<p id ="indexcheck" class="indexcheck"> 
					<c:if test="${local!='en_US'}">
						<span>${powerstation_address } : <em id=headerAddress></em></span>   
					</c:if>
					<span>  ${powerstation_lng } : <em id="headerLng"></em></span> 
					<span>  ${powerstation_lat } : <em id="headerLat"></em></span>   
					<span>  ${powerstation_installed_capacity } : <em id="headerInstallCapacity"></em>
					</span>
				</p>
			</div>
		</section>
		<!--内容区end-->
	</body>
	 <%@ include file="/page/common/pubJs.jsp" %>
	 
     <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=3j6qn3gMTZgGCzOegAxyF3wP"></script>
<script src="${cdnPath}global/plugins/echarts/echarts.js?version=20170817024514" type='text/javascript'></script>
<script src="${basePath}assets/global/ynz/weather.js?version=20170817024514" type='text/javascript'></script>
<script src="${basePath}assets/pages/scripts/index/subindex-echarts-pie.js?version=20170817024514" type='text/javascript'></script>
<script src="${basePath}assets/pages/scripts/index/subsiteIndex.js?version=20170817024514" type='text/javascript'></script>
<script src="${basePath}assets/pages/scripts/ynz/page.js?version=20170817024514" type='text/javascript'></script>
	 <script>
	 	$(function(){

	 	    $("#fold").click(function (){
	 	    	var hidden = $("#sub_menu").is(":hidden");
	 	    	if(hidden){
	 	    		$("#sub_menu").show();
	 	    	}else{
	 	    		$("#sub_menu").hide();
	 	    	}
	 	    });
	 		//$("#sub_menu").hide();
	 		$("#fold").click();
	 		//切换菜单区域
	 		/* var toggle=false; 
	 	   	$("#menu").css({width:"9%"});
           	$("#detailData").css({marginLeft:"9%"});
	 		$("#fold").click(function(){
	 			if(toggle){
	 				$("#menu").css({width:"9%"});
		 			$("#detailData").css({marginLeft:"9%"});
		 			toggle=false;
	 			}else{
	 				$("#menu").css({width:"235px"});
		 			$("#detailData").css({marginLeft:"235px"});
		 			toggle=true;
	 			}	 			
	 		}); */
	 		//图切换	 		
	 		/* $("#tuChange span").click(function(){
	 			$(this).addClass("activeSpan").siblings().removeClass("activeSpan");
	 		}); */
	 		//循环运动 
			var h = document.body.clientWidth;
			$("#bottomBox").css("left",h);
			$("#css").append('@keyframes play {0%{left:'+h+';}50%{left:'+h/2+';}100%{left:0;}}')			
	 	})
	 	//分页 
	 	 $("#alarmList").pageUtil({
				"data":"",//显示数据
				"pageSize":3,
				"pageId":"alarmListPage",
				"callBack":function(result){
					var cHtml="";
					for(var i=0;i<result.length;i++){
						cHtml+="<li>"+ result[i].name+"</li>";//处理数据
					}
					$("#demoContent").html(cHtml);//将数据增加到页面中
				}
		});
	 	
	 	function showDes(){
	 		$("#basic").show();
	 	}
	 	function cancel(){
	 		$("#basic").hide();
	 	}
	 </script>
</html>
