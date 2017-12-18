<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/page/common/pub.jsp" %>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta name="renderer" content="webkit">
	    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<title>${index_longieb_page}</title>
		<!-- start css style by pjs-->
		<%@ include file="/page/common/pubCss.jsp" %>
		<link href="${basePath}assets/pages/css/ynz/index.css?version=20170817024514" rel='stylesheet' type='text/css'  />
		<link href="${basePath}assets/pages/css/ynz/scrollDefined.css?version=20170817024514" rel='stylesheet' type='text/css'  />
						
		<!--end css style by pjs-->
		
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>		
		<%@ include file="/page/common/companyHead.jsp" %>
		<!--************************-->
		<div class="page-sidebar-wrapper" style="position:absolute;z-index:9999;">
                <!-- BEGIN SIDEBAR -->
                <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
                <!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
                <div class="page-sidebar navbar-collapse collapse" id="indexBox" style="width:205px;margin-left:26px;background:#38679d;border-radius:4px!important;position: absolute;top: 80px;"> 
                    <ul id="indexNav" class="page-sidebar-menu  page-header-fixed page-sidebar-closed" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200" style="text-align:center;display:none;">
                        <!-- DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below "sidebar-toggler-wrapper" LI element -->
                        <li class="sidebar-toggler-wrapper hide">
                            <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                            <div class="sidebar-toggler">
                                <span></span>
                            </div>
                            <!-- END SIDEBAR TOGGLER BUTTON -->
                        </li>
                        <!-- DOC: To remove the search box from the sidebar you just need to completely remove the below "sidebar-search-wrapper" LI element -->
                          <c:if test="${menuConfig['1'].have_menu!=null && menuConfig['1'].have_menu==1}">
                        <li class="nav-item start ">
                            <a href="${basePath}logsPage/toLogsList.do?admin=1" class="nav-link nav-toggle">
                                <i class="icon-social-dribbble"></i>
                                <span class="title">${menu_logmanager}</span>
                            </a>
                         </li>
                         </c:if>
                           <c:if test="${menuConfig['3'].have_menu!=null && menuConfig['3'].have_menu==1}">
	                          <li class="nav-item start ">
	                            <a href="${basePath}equipmentPage/equipmentList.do?admin=1" class="nav-link nav-toggle">
	                                <i class="fa fa-eye"></i>
	                                <span class="title">${menu_equipment}</span>
	                            </a>
	                         </li>
                         </c:if>
                         <c:if test="${menuConfig['11'].have_menu!=null && menuConfig['11'].have_menu==1}">
                          <!-- 用户管理 -->
	                       <li id="companyusrmanager" class="nav-item ">
	                            <a href="${basePath}compantUser/userListPage.do${urlParams}" class="nav-link nav-toggle"> 
	                                <i class="icon-user"></i>
	                                <span class="title"><i18n:message name="menu_usermanager" /></span>
	                                <span class="ynzmenuItemSelected"></span>
	                            </a> 
	                        </li> 
	                        </c:if>
	                        <!-- 角色管理 -->
 						<c:if test="${menuConfig['12'].have_menu!=null && menuConfig['12'].have_menu.intValue()==1}">
	                       <li id="rolemanager" class="nav-item ">
	                            <a href="${basePath}rolePage/roleListPage.do${urlParams}"  class="nav-link nav-toggle"> 
	                                <i class="fa fa-odnoklassniki"></i>
	                                <span class="title"><i18n:message name="menu_rolemanager" /></span>
	                                <span class="ynzmenuItemSelected"></span>
	                            </a> 
	                       </li> 
	                       </c:if>
	                    <!--  电站管理-->
	                       <c:if test="${menuConfig['2'].have_menu!=null && menuConfig['2'].have_menu==1}">
	                        <li id="menustationmanager" class="nav-item  ">
	                            <a href="${basePath}powerstation/powerstationList${urlParams}"  class="nav-link nav-toggle">
	                                <i class="fa fa-language"></i>
	                                <span class="title"><i18n:message name="menu_stationmanager" /></span>
	                               <span class="ynzmenuItemSelected"></span>
	                            </a>
	                         </li>
                         </c:if>
                         <%-- <c:if test="${isCompanyAdmin}">
	                          <li class="nav-item start ">
	                            <a href="${basePath}compantUser/userListPage.do?admin=1" class="nav-link nav-toggle">
	                                <i class="icon-users"></i>
	                                <span class="title">${menu_userlist}</span>
	                            </a>
	                         </li>
	                         
	                         <li class="nav-item start ">
                                <a href="${basePath}rolePage/roleListPage.do?admin=1" class="nav-link nav-toggle">
                                    <i class="fa fa-odnoklassniki"></i>
                                    <span class="title">${menu_rolemanager}</span>
                                </a>
                             </li>
                             
	                          <li class="nav-item start ">
	                            <a href="${basePath}powerstation/powerstationList?admin=1" class="nav-link nav-toggle">
	                                <i class="fa fa-bars"></i>
	                                <span class="title">${menu_stationmanager}</span>
	                            </a>
	                         </li>
                         </c:if> --%>
                       </ul>
                    </div>
          </div>
		<!--************************-->		
		<!--内容区start-->
		<section>
			<div class="content">
				<div class="dotted"></div>
				<!--左边区域 start-->			
				<div class="leftRightCon">
					<!--总发量start-->
					<div class="totalEle installCapaity">
						<h3 class="basicTitle">${index_generation_info}</h3>
						
						<dl class="totalEleList col-md-4 no-padding">
							<dt><img src="${basePath}assets/pages/img/ynz/capacity.gif" alt="装机总容量"></dt>
							<dd id="installCapacity" class="electData deepyellowColor"></dd>
							<dd class="tit">${index_installed_totalcapacity} <p id="installCapacityunit"></p></dd>
						</dl>
						<dl class="totalEleList col-md-4 no-padding">
							<dt><img src="${basePath}assets/pages/img/ynz/gonglv.gif" alt="实际总功率"></dt>
							<dd id="generatedActivePower" class="electData yellowColor"></dd>
							<dd class="tit">${index_current_totalpower} <p id="generatedActivePowerunit"></p></dd>
						</dl>
						<dl class="totalEleList col-md-4 no-padding">
							<dt><img src="${basePath}assets/pages/img/ynz/energy.gif" alt="总发电量"></dt>
							<dd id="generationGross" class="electData"></dd>
							<dd class="tit">${inverter_generating_count} <p id="generationGrossunit"></p></dd>
						</dl>
					</div>
					<!--总发量end-->
					<!--装机容量占比start-->
					<div class="installCapaity twobox" style="margin: 26px 30px 26px 20px;">
						<h3 class="basicTitle">${index_installed_capacity_proportion}</h3>
						<div class="ratioBox" id="echarts_pie"><!--<img src="${basePath}assets/pages/img/ynz/tu1.png"/>--></div>
						<ul id="installCapaitypercent" class="stationDate">
							
						</ul>
					</div>
					<!--装机容量占比end-->
					<!--今日发电量start-->
					<div class="ynz-todyEle">
						<ul class="tanchange" id="tanchange">
							<li id="todyEleEchartLi" class="activeLi"><a href="###">${inverter_generating_day}</a></li>
							<li id="totalPowerLi"><a href="###">${index_total_power_line}</a></li>
						</ul>
						<div class="clearfix"></div>
						<div class="todyEleEchart" id="todyEleEchart"></div>
						<div class="totalPower" id="totalPower" style="display:none;"></div>
					</div>
					<!--今日发电量end-->
				</div>
				<!--左边区域 end-->	
			
				<!--中间区域 start-->
				<div class="centerCon">
					<div class="mainCon">
						<ul class="tanchange" id="tanchange">
							<li id="bgMapBtn"  class="activeLi"><a href="###">${index_mapmode}</a></li>
							<li id="tuModule"><a href="###">${index_graphicsmode}</a></li>
							<li id="listModule"><a href="###">${index_listmode}</a></li>
						</ul>
						<!--地图模式start-->
						<div id="bgMap" class="bgMap"></div>
						<!--地图模式end-->
						<!--图表模式start-->
						<div class="module module_picTable" id="module_picTable">
							<div class="picTable">
								<div class="search">
									<input id="iconcondition" class="txt" type="text" placeholder="${index_please_fill}"/><button id="iconSearch" class="btn floatR"></button>							
								</div>
								<div id="powerStationIcon" class="powerStationIcon wrap">
									
								</div>
							</div>	
							<!--实时报警sart-->
							<div class="wrap">
								<div class="currentAlarm">
									<h3><span>${menu_alarmreal}</span></h3>
									<ul id="alarmIcon" class="currentTable">									
									</ul>
									<ul id="alarmIconPage"  class="page clearfix"></ul>
								</div>							
							</div><!--实时报警end-->
						</div>
						<!--图表模式end-->
						<!--列表模式start-->
						<div class="module nodule_listTable" id="nodule_listTable">
							<div class="picTable">
								<div class="search">
									<input id="listcondition" class="txt" type="text" placeholder="${index_please_fill}"><button id="listSearch" type="button" class="btn floatR"></button>							
								</div>
								<div class="wrap">
									<ul id="powerStationList"class="powerStationList currentTable">									
									</ul>									
								</div>
							<!--实时报警sart-->
							<div class="wrap">
								<div class="currentAlarm">
									<h3 style="height:43px;"><span style="top: 25px;line-height: 32px;">${menu_alarmreal}</span></h3>
									<ul id="alarmList" class="currentTable" >									
									</ul>
									<ul id="alarmListPage"  class="page clearfix"></ul>
								</div>							
							</div><!--实时报警end-->
							</div>
						</div>						
						<!--列表模式end-->
					</div>					
				</div>
				<!--中间区域 end-->
				<!--右边区域 start-->
				<div class="leftRightCon">
					<div class="installCapaity weatherMess style="background:red;">
						<h3 class="basicTitle">${iv_weather_info}</h3>
						<div class="time">
							<dl>
								<dt id="currTime"></dt>
								<dd id="currDate"></dd>
							</dl> 
						</div>
						<ul class="weather">
							<li class="weatherImg" id="weatherImg"><!--<img src="${basePath}assets/pages/img/ynz/tu2.png"/>--></li>
							<li id="city"></li>
							<li id="weath"> </li>
							<li class="dataWeather" id="dataTemp"> </li>
						</ul>
						
					</div>
					<div class="twobox">
						<h3 class="basicTitle">${index_environment_info}</h3>
						<div class="clearfix"></div>
						<div  class="col-md-6 ynzDataBox">
							<dl>
								<dt>
									<img src="${basePath}assets/pages/img/ynz/ranmei.gif" alt="燃煤">
									<p id="sourceNoounit"></p>
								</dt>
								<dd class="ynz-data"><em id="sourceNoo" class="reseda"></em></dd>								
							</dl>
						</div>
						<div  class="col-md-6 ynzDataBox">
							<dl>
								<dt>
									<img src="${basePath}assets/pages/img/ynz/fencen.gif" alt="粉尘">
									<p id="sourceXoounit"></p>
								</dt>
								<dd class="ynz-data"><em id="sourceXoo" class="green"></em></dd>
							</dl>
						</div>
						<div  class="col-lg-6 col-md-6 col-sm-6 col-xs-6 ynzDataBox">
							<dl>
								<dt>
									<img src="${basePath}assets/pages/img/ynz/co2.gif" alt="co2">
									<p id="sourceCoounit"></p>
								</dt>
								<dd class="ynz-data"><em id="sourceCoo" ></em></dd>						
							</dl>
						</div>
						<div  class="col-md-6 ynzDataBox">
							<dl>
								<dt>
									<img src="${basePath}assets/pages/img/ynz/so2.gif" alt="so2">
									<p id="sourceSoounit"></p>
								</dt>
								<dd class="ynz-data"><em id="sourceSoo" class="yellow"></em></dd>
							</dl>
						</div>
					</div>
					<!--系统性能对比start-->
					<div class="todyEle systemPer">
						<h3 class="basicTitle">${index_system}</h3>
						<div class="todyEleEchart" id="systemEchart"></div>
					</div>
					<!--系统性能对比end-->
				</div>
				<!--右边区域 end-->
			</div>
		</section>
		<!--内容区end-->
		<script type="text/javascript">
			window.ynz.weatherUrl= "<%=com.yunengzhe.commons.util.PropertiesUtil.getString("weatherurl") %>";
			
		</script>  
		<%@ include file="/page/common/pubJs.jsp" %>
		<script src="${cdnPath}global/plugins/echarts/echarts.js?version=20170817024514" type='text/javascript'></script>
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
		<!--加载百度地图相关start-->
<!-- 		<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=3j6qn3gMTZgGCzOegAxyF3wP"></script> -->
<!-- 		<script type="text/javascript" src="http://api.map.baidu.com/library/AreaRestriction/1.2/src/AreaRestriction_min.js"/></script> -->
		
<script src="${basePath}assets/global/ynz/weather.js?version=20170817024514" type='text/javascript'></script>
<script src="${basePath}assets/pages/scripts/index/index.js?version=20170817024514" type='text/javascript'></script>
<script src="${basePath}assets/pages/scripts/index/index-echarts-pie.js?version=20170817024514" type='text/javascript'></script>
<script src="${basePath}assets/pages/scripts/ynz/page.js?version=20170817024514" type='text/javascript'></script>
<%-- 		<script src="${basePath}assets/pages/scripts/ynz/jquery-3.1.1.min.js"></script> --%>
		<!--加载百度地图相关end--> 
	</body>
</html>
