 <%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
 <%@ taglib prefix="i18n" uri="http://www.yunengzhe.com/ynz/tag" %>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
<!--

-->

.ynzmenuItemOpen {}
.ynzmenuItemSelected {}

</style>
<script type="text/javascript">
	 function setSelectMenu(rootid,menuid){		  
		 $("#"+menuid).parents(".nav-item").addClass("open");
		 $("#"+menuid).parents(".nav-item").addClass("active");
		 $("#"+menuid).parents(".nav-item").children("a.nav-link").children(".ynzmenuItemOpen").addClass("open"); 
		 $("#"+rootid).children("a.nav-link").children(".ynzmenuItemSelected").addClass("selected"); 
 
		var childNode = $("#"+menuid);
		childNode.addClass("open");
		childNode.addClass("active");
	 }
	 
	 function setSelectMenuRoot(rootid){		   
		$("#"+rootid).children("a.nav-link").children(".ynzmenuItemSelected").addClass("selected"); 
		var childNode = $("#"+rootid);
		childNode.addClass("open");
		childNode.addClass("active");
	 }
</script>
			<!-- BEGIN SIDEBAR -->
            <div id="sub_menu" class="page-sidebar-wrapper">
                <!-- BEGIN SIDEBAR -->
                <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
                <!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
                <div class="page-sidebar navbar-collapse collapse"> 
                    <ul class="page-sidebar-menu  page-header-fixed " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200" style="padding-top: 20px">
                        <!-- DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below "sidebar-toggler-wrapper" LI element -->
                        <li class="sidebar-toggler-wrapper hide">
                            <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                            <div class="sidebar-toggler">
                                <span></span>
                            </div>
                            <!-- END SIDEBAR TOGGLER BUTTON -->
                        </li>
                        <!-- DOC: To remove the search box from the sidebar you just need to completely remove the below "sidebar-search-wrapper" LI element -->
                        
                        <li class="nav-item start ">
                            <a href="${subIndex }" class="nav-link nav-toggle">
                                <i class="icon-home"></i>
                                <span class="title">${home }</span>
                                <span class="arrow"></span>
                            </a> 
                        </li>
                        <li class="heading">
                            <h3 class="uppercase">${management}</h3>
                        </li>
						<!--日志管理 -->
                        <li id="logmanager" class="nav-item  ">
                            <a href="${basePath}logsPage/toLogsList.do${urlParams}" class="nav-link nav-toggle">
                                <i class="icon-social-dribbble"></i> 
                                <span class="title"><i18n:message name="menu_logmanager" /></span>
                               <span class="ynzmenuItemSelected"></span>
                            </a>
                        </li> 
						 
	                    <!-- 设备管理-->
                        <li id="equipmentmanager" class="nav-item  ">
                            <a href="${basePath}equipmentPage/equipmentList.do${urlParams}" class="nav-link nav-toggle">
                               <i class="fa fa-share-alt"></i>
                               <span class="title"><i18n:message name="menu_equipment" /></span>
                               <span class="ynzmenuItemSelected"></span>
                            </a>
                        </li>
		               <!-- 设备监控-->
		               
		                        <li id="menuequipmonitor" class="nav-item  ">
		                            <a href="javascript:;" class="nav-link nav-toggle">
		                                <i class="fa fa-eye"></i>
		                                <span class="title"><i18n:message name="menu_equipmonitor" /></span>
		                               <span class="ynzmenuItemSelected"></span>
		                                <span class="arrow ynzmenuItemOpen"></span>
		                            </a>
		                            <ul class="sub-menu">
		                                   <!--逆变器和测量箱-->
				                        <li id="menuinverterbox" class="nav-item  ">
				                            <a href="${basePath}inverterPage/inverterList?powerStationId=${thirdPowerStationId}" class="nav-link">
				                                <i class="fa fa-calendar-o"></i>
				                                <span class="title"><i18n:message name="menu_inverterbox" /></span>
				                               <span class="ynzmenuItemSelected"></span>
<!-- 				                                <span class="arrow ynzmenuItemOpen"></span> -->
				                            </a> 
				                        </li> 
		                                     <!-- 气象站-->
				                        <li id="menuweatherstation" class="nav-item  ">
				                            <a href="${basePath}weatherPageController/weatherPage/${thirdPowerStationId}?powerStationId=${thirdPowerStationId}" class="nav-link nav-toggle">
				                                <i class="fa fa-sun-o"></i>
				                                <span class="title"><i18n:message name="menu_weatherstation" /></span>
				                               <span class="ynzmenuItemSelected"></span>
<!-- 				                                <span class="arrow ynzmenuItemOpen"></span> -->
				                            </a> 
				                        </li>
				                        <!-- 并网柜-->
				                        <li id="menugridark" class="nav-item  ">
				                            <a href="${basePath}gridarkPageController/gridarkPage/${thirdPowerStationId}?powerStationId=${thirdPowerStationId}" class="nav-link nav-toggle">
				                                <i class="fa fa-code-fork"></i>
				                                <span class="title"><i18n:message name="menu_gridark" /></span>
				                               <span class="ynzmenuItemSelected"></span>
<!-- 				                                <span class="arrow ynzmenuItemOpen"></span> -->
				                            </a> 
				                        </li>
				                        <%-- <!-- 单双轴跟踪控制箱 -->
		                                 <li id="menuaxis" class="nav-item  ">
				                            <a href="${basePath}TrackPageController/TrackPage?powerStationId=${thirdPowerStationId}" class="nav-link nav-toggle">
				                                <i class="fa fa-shopping-cart"></i>
				                                <span class="title"><i18n:message name="menu_axis" /></span>
				                               <span class="ynzmenuItemSelected"></span>
<!-- 				                                <span class="arrow ynzmenuItemOpen"></span> -->
				                            </a>
				                        </li> --%>
				                        <!-- IV多通道测试设备 -->
				                         <li id="menuivequipment" class="nav-item  ">
				                            <a href="${basePath}ivequipmentPage/${thirdPowerStationId}?powerStationId=${thirdPowerStationId}" class="nav-link nav-toggle">
				                                <i class="fa fa-contao"></i>
				                                <span class="title"><i18n:message name="menu_ivequipment" /></span>
				                               <span class="ynzmenuItemSelected"></span>
<!-- 				                                <span class="arrow ynzmenuItemOpen"></span> -->
				                            </a>
				                        </li>
		                            </ul>
	                      	      
	                        	</li> 
		                    <!-- 报警管理-->
		                        <li id="menualarmmanager" class="nav-item  ">
		                            <a href="javascript:;" class="nav-link nav-toggle">
		                                <i class="fa fa-warning"></i>
		                                <span class="title"><i18n:message name="menu_alarm" /></span>
		                               <span class="ynzmenuItemSelected"></span>
		                                <span class="arrow ynzmenuItemOpen"></span>
		                            </a>
		                            <ul class="sub-menu">
<%-- 		                             <c:if test="${menuConfig['13'].have_menu!=null && menuConfig['13'].have_menu==1}">  --%>
		                                 <li id="alarmmanager_alarmreal" class="nav-item  ">
		                                    <a href="${basePath}alarmPage/actualAlarm?powerStationId=${thirdPowerStationId}" class="nav-link ">
		                                        <i class="fa fa-tag"></i>
		                                        <span class="title"><i18n:message name="menu_alarmreal" /></span>
		                                    </a>
		                                </li>
<%-- 		                                </c:if> --%>
<%-- 		                                <c:if test="${menuConfig['13'].have_menu!=null && menuConfig['13'].have_menu==1}">  --%>
		                                <li id="alarmmanager_alarmhistory" class="nav-item  ">
		                                    <a href="${basePath}alarmPage/historyAlarm?powerStationId=${thirdPowerStationId}" class="nav-link ">
		                                        <i class="fa fa-history"></i>
		                                        <span class="title"><i18n:message name="menu_alarmhistory" /></span>
		                                    </a>
		                                </li>
<%-- 		                                </c:if>  --%>
		                            </ul>
		                        </li>						
<!-- 		                    历史查询 -->
<%-- 		                      <c:if test="${menuConfig['4'].have_menu!=null && menuConfig['4'].have_menu==1 && isShowSubPage}"> --%>
<!-- 		                        <li id="menumhistory" class="nav-item  ">  -->
<%-- 		                            <a href="${basePath}history/historyPage?powerStationId=${thirdPowerStationId}" class="nav-link nav-toggle"> --%>
<!-- 		                                <i class="fa fa-history"></i> -->
<%-- 		                                <span class="title"><i18n:message name="menu_history" /></span> --%>
<!-- 		                               <span class="ynzmenuItemSelected"></span> -->
<!-- <!-- 		                                <span class="arrow ynzmenuItemOpen"></span> -->
<!-- 		                            </a> -->
<!-- 		                        </li>     -->
<%-- 	                        </c:if> --%>
<!-- 	                                                      自定义曲线 -->
<%-- 	                              <c:if test="${menuConfig['5'].have_menu!=null && menuConfig['5'].have_menu==1 && isShowSubPage}"> --%>
<!-- 		                        <li id="menucustomlinemanager" class="nav-item  "> -->
<%-- 		                            <a href="${basePath}curvePage/curveList.do?powerStationId=${thirdPowerStationId}" class="nav-link nav-toggle"> --%>
<!-- 		                                <i class="fa fa-line-chart"></i> -->
<%-- 		                                <span class="title"><i18n:message name="menu_customline" /></span> --%>
<!-- 		                               <span class="ynzmenuItemSelected"></span> -->
<!-- 		                            </a> -->
<!-- 		                        </li> -->
<%-- 		                        </c:if> --%>
		                         <!--                           数据查询-->
		                        <li id="menudatalistmanager" class="nav-item  ">
		                            <a href="${basePath}dataPage/dataList.do?powerStationId=${thirdPowerStationId}" class="nav-link nav-toggle">
		                                <i class="fa fa-line-chart"></i>
		                                <span class="title"><i18n:message name="data_find" /></span>
		                               <span class="ynzmenuItemSelected"></span>
		                            </a>
		                        </li>
		                         <!--                           报表管理 -->
		                        <li id="menureportmanager" class="nav-item  ">
		                            <a href="${basePath}reportPage/reportList.do?powerStationId=${thirdPowerStationId}" class="nav-link nav-toggle">
		                                <i class="fa fa-newspaper-o"></i>
		                                <span class="title"><i18n:message name="menu_report" /></span>
		                               <span class="ynzmenuItemSelected"></span>
		                            </a>
		                        </li>
                      		 
                    		<!--                           电站 拓扑图-->
<!--                                  <li id="menumenustationtopo" class="nav-item  "> -->
<%-- 		                            <a href="${basePath}stationSource/html/topology6.html" class="nav-link nav-toggle" target="_blank"> --%>
<!-- 		                                <i class="fa fa-object-ungroup"></i> -->
<%-- 		                                <span class="title"><i18n:message name="menu_stationtopo" /></span> --%>
<!-- 		                               <span class="ynzmenuItemSelected"></span>  -->
<!-- 		                            </a>   -->
<!-- 		                         </li> -->
                      		 <!-- 视频监控 -->
<!-- 			                       <li id="menumenuvideo" class="nav-item  "> -->
<!--                                         <a id="monitoringpath" target="_blank" class="nav-link nav-toggle"> -->
<!--                                             <i class="fa fa-video-camera"></i> -->
<%--                                             <span class="title"><i18n:message name="menu_video" /></span> --%>
<!--                                            <span class="ynzmenuItemSelected"></span> -->
<!--                                         </a>   -->
<!--                            			</li> -->
                    </ul> 
                </div> 
                </div>
            <!-- END SIDEBAR -->
             
