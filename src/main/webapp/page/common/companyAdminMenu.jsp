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
						<!--日志管理 -->
						<c:if test="${menuConfig['1'].have_menu!=null && menuConfig['1'].have_menu==1}">
	                        <li id="logmanager" class="nav-item  ">
	                            <a href="${basePath}logsPage/toLogsList.do${urlParams}" class="nav-link nav-toggle">
	                                <i class="icon-social-dribbble"></i> 
	                                <span class="title"><i18n:message name="menu_logmanager" /></span>
	                               <span class="ynzmenuItemSelected"></span>
	                            </a>
	                        </li> 
	                     </c:if>   
						 
	                    <!-- 设备管理-->
	    				 <c:if test="${menuConfig['3'].have_menu!=null && menuConfig['3'].have_menu==1}">
                        <li id="equipmentmanager" class="nav-item  ">
                            <a href="${basePath}equipmentPage/equipmentList.do${urlParams}" class="nav-link nav-toggle">
                               <i class="fa fa-share-alt"></i>
                               <span class="title"><i18n:message name="menu_equipment" /></span>
                               <span class="ynzmenuItemSelected"></span>
                            </a>
                        </li>
                        </c:if>
		               <!-- 设备监控-->
		               
		               <c:if test="${menuConfig['8'].have_menu!=null && menuConfig['8'].have_menu==1  && isShowSubPage}">
		                        <li id="menuequipmonitor" class="nav-item  ">
		                            <a href="javascript:;" class="nav-link nav-toggle">
		                                <i class="fa fa-eye"></i>
		                                <span class="title"><i18n:message name="menu_equipmonitor" /></span>
		                               <span class="ynzmenuItemSelected"></span>
		                                <span class="arrow ynzmenuItemOpen"></span>
		                            </a>
		                            <ul class="sub-menu">
		                                   <!--逆变器和测量箱-->
		                               <c:if test="${menuConfig['13'].have_menu!=null && menuConfig['13'].have_menu==1}"> 
				                        <li id="menuinverterbox" class="nav-item  ">
				                            <a href="${basePath}inverterPage/inverterList?powerStationId=${powerStationId}" class="nav-link">
				                                <i class="fa fa-calendar-o"></i>
				                                <span class="title"><i18n:message name="menu_inverterbox" /></span>
				                               <span class="ynzmenuItemSelected"></span>
<!-- 				                                <span class="arrow ynzmenuItemOpen"></span> -->
				                            </a> 
				                        </li> 
				                       </c:if>
		                                     <!-- 气象站-->
		                               <c:if test="${menuConfig['14'].have_menu!=null && menuConfig['14'].have_menu==1}"> 
				                        <li id="menuweatherstation" class="nav-item  ">
				                            <a href="${basePath}weatherPageController/weatherPage/${powerStationId}?powerStationId=${powerStationId}" class="nav-link nav-toggle">
				                                <i class="fa fa-sun-o"></i>
				                                <span class="title"><i18n:message name="menu_weatherstation" /></span>
				                               <span class="ynzmenuItemSelected"></span>
<!-- 				                                <span class="arrow ynzmenuItemOpen"></span> -->
				                            </a> 
				                        </li>
				                        </c:if>
				                        <!-- 并网柜-->
				                   		<c:if test="${menuConfig['15'].have_menu!=null && menuConfig['15'].have_menu==1}"> 
				                        <li id="menugridark" class="nav-item  ">
				                            <a href="${basePath}gridarkPageController/gridarkPage/${powerStationId}?powerStationId=${powerStationId}" class="nav-link nav-toggle">
				                                <i class="fa fa-code-fork"></i>
				                                <span class="title"><i18n:message name="menu_gridark" /></span>
				                               <span class="ynzmenuItemSelected"></span>
<!-- 				                                <span class="arrow ynzmenuItemOpen"></span> -->
				                            </a> 
				                        </li>
				                        </c:if>
				                        <!-- 单双轴跟踪控制箱 -->
				                         <c:if test="${menuConfig['16'].have_menu!=null && menuConfig['16'].have_menu==1}"> 
		                                 <li id="menuaxis" class="nav-item  ">
				                            <a href="${basePath}TrackPageController/TrackPage?powerStationId=${powerStationId}" class="nav-link nav-toggle">
				                                <i class="fa fa-shopping-cart"></i>
				                                <span class="title"><i18n:message name="menu_axis" /></span>
				                               <span class="ynzmenuItemSelected"></span>
<!-- 				                                <span class="arrow ynzmenuItemOpen"></span> -->
				                            </a>
				                        </li>
				                        </c:if>
				                        <!-- IV多通道测试设备 -->
				                         <c:if test="${menuConfig['17'].have_menu!=null && menuConfig['17'].have_menu==1}"> 
				                         <li id="menuivequipment" class="nav-item  ">
				                            <a href="${basePath}ivequipmentPage/${powerStationId}?powerStationId=${powerStationId}" class="nav-link nav-toggle">
				                                <i class="fa fa-contao"></i>
				                                <span class="title"><i18n:message name="menu_ivequipment" /></span>
				                               <span class="ynzmenuItemSelected"></span>
<!-- 				                                <span class="arrow ynzmenuItemOpen"></span> -->
				                            </a>
				                        </li>
				                        </c:if>
		                            </ul>
	                      	      
	                        	</li> 
                            </c:if>
		                    <!-- 报警管理-->
		                   <c:if test="${menuConfig['9'].have_menu!=null && menuConfig['9'].have_menu==1 && isShowSubPage}">
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
		                                    <a href="${basePath}alarmPage/actualAlarm?powerStationId=${powerStationId}" class="nav-link ">
		                                        <i class="fa fa-tag"></i>
		                                        <span class="title"><i18n:message name="menu_alarmreal" /></span>
		                                    </a>
		                                </li>
<%-- 		                                </c:if> --%>
<%-- 		                                <c:if test="${menuConfig['13'].have_menu!=null && menuConfig['13'].have_menu==1}">  --%>
		                                <li id="alarmmanager_alarmhistory" class="nav-item  ">
		                                    <a href="${basePath}alarmPage/historyAlarm?powerStationId=${powerStationId}" class="nav-link ">
		                                        <i class="fa fa-history"></i>
		                                        <span class="title"><i18n:message name="menu_alarmhistory" /></span>
		                                    </a>
		                                </li>
		                                
		                                <li id="alarmmanager_config" class="nav-item  ">
		                                    <a href="${basePath}alarmPage/configList?powerStationId=${powerStationId}" class="nav-link ">
		                                        <i class="fa fa-cog"></i>
		                                        <span class="title"><i18n:message name="alarm_config" /></span>
		                                    </a>
		                                </li>
<%-- 		                                </c:if>  --%>
		                            </ul>
		                        </li>						
	                       </c:if>
<!-- 		                    历史查询 -->
<%-- 		                      <c:if test="${menuConfig['4'].have_menu!=null && menuConfig['4'].have_menu==1 && isShowSubPage}"> --%>
<!-- 		                        <li id="menumhistory" class="nav-item  ">  -->
<%-- 		                            <a href="${basePath}history/historyPage?powerStationId=${powerStationId}" class="nav-link nav-toggle"> --%>
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
<%-- 		                            <a href="${basePath}curvePage/curveList.do?powerStationId=${powerStationId}" class="nav-link nav-toggle"> --%>
<!-- 		                                <i class="fa fa-line-chart"></i> -->
<%-- 		                                <span class="title"><i18n:message name="menu_customline" /></span> --%>
<!-- 		                               <span class="ynzmenuItemSelected"></span> -->
<!-- 		                            </a> -->
<!-- 		                        </li> -->
<%-- 		                        </c:if> --%>
		                         <!--                           数据查询-->
	                              <c:if test="${menuConfig['5'].have_menu!=null && menuConfig['5'].have_menu==1 && isShowSubPage}">
		                        <li id="menudatalistmanager" class="nav-item  ">
		                            <a href="${basePath}dataPage/dataList.do?powerStationId=${powerStationId}" class="nav-link nav-toggle">
		                                <i class="fa fa-line-chart"></i>
		                                <span class="title"><i18n:message name="data_find" /></span>
		                               <span class="ynzmenuItemSelected"></span>
		                            </a>
		                        </li>
		                        </c:if>
		                         <!--                           报表管理 -->
		                      <c:if test="${menuConfig['6'].have_menu!=null && menuConfig['6'].have_menu==1 && isShowSubPage}">
		                        <li id="menureportmanager" class="nav-item  ">
<%-- 		                            <a href="${basePath}reportPage/reportList.do?powerStationId=${powerStationId}" class="nav-link nav-toggle"> --%>
<!-- 		                                <i class="fa fa-newspaper-o"></i> -->
<%-- 		                                <span class="title"><i18n:message name="menu_report" /></span> --%>
<!-- 		                               <span class="ynzmenuItemSelected"></span> -->
<!-- 		                            </a> -->
		                             <a href="javascript:;" class="nav-link nav-toggle">
		                                <i class="fa fa-warning"></i>
		                                <span class="title"><i18n:message name="menu_report" /></span>
		                               <span class="ynzmenuItemSelected"></span>
		                                <span class="arrow ynzmenuItemOpen"></span>
		                            </a>
		                          
		                             <ul class="sub-menu">
<%-- 		                                 <c:if test="${menuConfig['21'].have_menu!=null && menuConfig['21'].have_menu==1 }"> --%>
		                                 <li id="report_commonuse" class="nav-item  ">
		                                    <a href="${basePath}reportPage/reportList.do?powerStationId=${powerStationId}" class="nav-link ">
		                                        <i class="fa fa-tag"></i>
		                                        <span class="title"><i18n:message name="report_commonuse" /></span>
		                                    </a>
<!-- 		                                </li> -->
<%-- 		                               </c:if> --%>
<%-- 		                                   <c:if test="${menuConfig['22'].have_menu!=null && menuConfig['22'].have_menu==1 }"> --%>
		                                <li id="report_everyday" class="nav-item  ">
		                                    <a href="${basePath}reportPage/reportDailyList.do?powerStationId=${powerStationId}" class="nav-link ">
		                                        <i class="fa fa-history"></i>
		                                        <span class="title"><i18n:message name="report_everyday" /></span>
		                                    </a>
		                                </li>
<%-- 		                                </c:if> --%>
		                            </ul>
		                            
		                            
		                            
		                        </li>
		                        
                      		 </c:if>
                      		 
                    		<!--                           电站 拓扑图-->
	                         <c:if test="${menuConfig['20'].have_menu!=null && menuConfig['20'].have_menu==1 && isShowSubPage}">
                                 <li id="menumenustationtopo" class="nav-item  ">
		                            <a href="${basePath}stationSource/html/topology6.html" class="nav-link nav-toggle" target="_blank">
		                                <i class="fa fa-object-ungroup"></i>
		                                <span class="title"><i18n:message name="menu_stationtopo" /></span>
		                               <span class="ynzmenuItemSelected"></span> 
		                            </a>  
		                         </li>
                            </c:if>
                      		 <!-- 视频监控 -->
			                <c:if test="${menuConfig['7'].have_menu!=null && menuConfig['7'].have_menu==1 && isShowSubPage}">
			                       <li id="menumenuvideo" class="nav-item  ">
                                        <a id="monitoringpath" target="_blank" class="nav-link nav-toggle">
                                            <i class="fa fa-video-camera"></i>
                                            <span class="title"><i18n:message name="menu_video" /></span>
                                           <span class="ynzmenuItemSelected"></span>
                                        </a>  
                           			</li>
                   			</c:if>
                   			 <!-- 推送管理 -->
                   			  <c:if test="${menuConfig['24'].have_menu!=null && menuConfig['24'].have_menu==1}">
			                       <li id="menumenupush" class="nav-item  ">
           							  <a href="${basePath}mailPage/pushConfigPage?powerStationId=${powerStationId}" class="nav-link ">
                                            <i class="fa fa-commenting-o"></i>
                                            <span class="title"><i18n:message name="menu_push" /></span>
                                           <span class="ynzmenuItemSelected"></span>
                                        </a>  
                           			</li>
                           	</c:if>
                           	 <!-- 数据同步 --> 
                           	  <c:if test="${menuConfig['25'].have_menu!=null && menuConfig['25'].have_menu==1}">
		                           	<li id="menuSync" class="nav-item  ">
		     							  <a href="${basePath}sync/list?powerStationId=${powerStationId}" class="nav-link ">
		                                      <i class="fa fa-retweet"></i>
		                                      <span class="title"><i18n:message name="sync_menu" /></span>
		                                     <span class="ynzmenuItemSelected"></span>
		                                  </a>  
		                           	</li> 
		                      </c:if>
                    </ul> 
                </div> 
                </div>
            <!-- END SIDEBAR -->
             
