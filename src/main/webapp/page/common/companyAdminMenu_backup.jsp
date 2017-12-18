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
            <div class="page-sidebar-wrapper">
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
<!--                         公司管理员菜单 -->
                        <c:if test="${isCompanyAdmin && (admin!=null && admin==true)}">
                          <!-- 用户管理 -->
	                       <li id="companyusrmanager" class="nav-item ">
	                            <a href="${basePath}compantUser/userListPage.do<c:if test="${(admin!=null && admin==true) }">?admin=1</c:if>" class="nav-link nav-toggle"> 
	                                <i class="icon-user"></i>
	                                <span class="title"><i18n:message name="menu_usermanager" /></span>
	                                <span class="ynzmenuItemSelected"></span>
<!-- 	                                <span class="arrow ynzmenuItemOpen"></span> -->
	                            </a> 
	                        </li> 
	                          
	                       <li id="rolemanager" class="nav-item ">
	                            <a href="${basePath}rolePage/roleListPage.do<c:if test="${(admin!=null && admin==true) }">?admin=1</c:if>" class="nav-link nav-toggle"> 
	                                <i class="fa fa-odnoklassniki"></i>
	                                <span class="title"><i18n:message name="menu_rolemanager" /></span>
	                                <span class="ynzmenuItemSelected"></span>
<!-- 	                                <span class="arrow ynzmenuItemOpen"></span> -->
	                            </a> 
	                       </li> 
	                            <!--                           电站管理-->
	                        <li id="menustationmanager" class="nav-item  ">
	                            <a href="${basePath}powerstation/powerstationList<c:if test="${(admin!=null && admin==true) }">?admin=1</c:if>" class="nav-link nav-toggle">
	                                <i class="fa fa-language"></i>
	                                <span class="title"><i18n:message name="menu_stationmanager" /></span>
	                               <span class="ynzmenuItemSelected"></span>
<!-- 	                                <span class="arrow ynzmenuItemOpen"></span> -->
	                            </a>
	                             
	                         </li>
                         
                        </c:if>
<!--                         公司管理员菜单 end -->
						
						
						<!--                         集团显示日志管理  设备管理菜单 -->
                        <c:if test="${(isJikongRole  || isSubStation || isThird || isCompanyAdmin)}">
						<!--日志管理 -->
	                        <li id="logmanager" class="nav-item  ">
	                            <a href="${basePath}logsPage/toLogsList.do<c:if test="${(admin!=null && admin==true) }">?admin=1</c:if>" class="nav-link nav-toggle">
	                                <i class="icon-social-dribbble"></i> 
	                                <span class="title"><i18n:message name="menu_logmanager" /></span>
	                               <span class="ynzmenuItemSelected"></span>
<!-- 	                                <span class="arrow ynzmenuItemOpen"></span> -->
	                            </a>
<!-- 	                            <ul class="sub-menu"> -->
<!-- 	                                 <li id="logmanager_list" class="nav-item  ">  -->
<%-- 	                                    <a href="${basePath}logsPage/toLogsList.do<c:if test="${(admin!=null && admin==true) }">?admin=1</c:if>" class="nav-link "> --%>
<!-- 	                                         <i class="icon-users"></i> -->
<%-- 	                                         <span class="title"><i18n:message name="menu_loglist" /></span>  --%>
<!-- 	                                         <span class="ynzmenuItemSelected"></span> -->
<!-- 	                                    </a> -->
<!-- 	                                </li>  -->
<%-- 	                                <c:if test="${isThird==false}"> --%>
<!-- 	                                <li   id="logmanager_add"  class="nav-item  ">  -->
<%-- 	                          				 <a href="${basePath}logsPage/toLogsAdd.do<c:if test="${(admin!=null && admin==true) }">?admin=1</c:if>" class="nav-link "> --%>
<!-- 	                                         <i class="fa fa-user-plus"></i> -->
<%-- 	                                         <span class="title"><i18n:message name="menu_logadd" /></span>  --%>
<!-- 	                                         <span class="ynzmenuItemSelected"></span> -->
<!-- 	                                    </a> -->
<!-- 	                                </li> -->
<%-- 	                                </c:if> --%>
<!-- 	                            </ul> -->
	                        </li> 
	                        
	                         
                        
                        </c:if>
                        
                        <!--                         集团显示日志管理  设备管理菜单 -->
                        <c:if test="${(isJikongRole || isSubStation || isCompanyAdmin) }">
						 
	                         <!--                           设备管理-->
                        <li id="equipmentmanager" class="nav-item  ">
                            <a href="${basePath}equipmentPage/equipmentList.do<c:if test="${(admin!=null && admin==true) }">?admin=1</c:if>" class="nav-link nav-toggle">
                               <i class="fa fa-share-alt"></i>
                               <span class="title"><i18n:message name="menu_equipment" /></span>
                               <span class="ynzmenuItemSelected"></span>
<!--                                <span class="arrow ynzmenuItemOpen"></span> -->
                            </a>
<!--                             <ul class="sub-menu"> -->
<!--                                  <li id="equipment_list" class="nav-item  "> -->
<%--                                     <a href="${basePath}equipmentPage/equipmentList.do<c:if test="${(admin!=null && admin==true) }">?admin=1</c:if>" class="nav-link "> --%>
<!--                                         <i class="fa fa-bars"></i> -->
<%--                                         <span class="title"><i18n:message name="menu_equipmentList" /></span> --%>
<!--                                          <span class="ynzmenuItemSelected"></span> -->
<!--                                     </a> -->
<!--                                 </li>  -->
                                
<!--                                  <li id="equipment_add" class="nav-item  "> -->
<%--                                     <a href="${basePath}equipmentPage/equipmentAdd.do<c:if test="${(admin!=null && admin==true) }">?admin=1</c:if>" class="nav-link "> --%>
<!--                                         <i class="fa fa-plus-square"></i> -->
<%--                                         <span class="title"><i18n:message name="menu_equipmentAdd" /></span> --%>
<!--                                          <span class="ynzmenuItemSelected"></span> -->
<!--                                     </a> -->
<!--                                 </li> -->
                           
<!--                             </ul> -->
                        </li>
                        
                        </c:if>
<!--                          集团显示日志管理 菜单 end --> 
                        
                        
                        <!--                      集控中心+子站 + 当前是显示子站界面 --> 
                        <c:if test="${(isJikongRole || isSubStation || isCompanyAdmin) && isShowSubPage &&  (admin==null || admin==false) }"> 
		                        <!--                           设备监控-->
		                        <li id="menuequipmonitor" class="nav-item  ">
		                            <a href="javascript:;" class="nav-link nav-toggle">
		                                <i class="fa fa-eye"></i>
		                                <span class="title"><i18n:message name="menu_equipmonitor" /></span>
		                               <span class="ynzmenuItemSelected"></span>
		                                <span class="arrow ynzmenuItemOpen"></span>
		                            </a>
		                            <ul class="sub-menu">
		                                   <!--                           逆变器和测量箱-->
				                        <li id="menuinverterbox" class="nav-item  ">
				                            <a href="${basePath}inverterPage/inverterList?powerStationId=${powerStationId}" class="nav-link">
				                                <i class="fa fa-calendar-o"></i>
				                                <span class="title"><i18n:message name="menu_inverterbox" /></span>
				                               <span class="ynzmenuItemSelected"></span>
<!-- 				                                <span class="arrow ynzmenuItemOpen"></span> -->
				                            </a> 
				                        </li> 
				                         
		                                     <!--                           气象站-->
				                        <li id="menuweatherstation" class="nav-item  ">
				                            <a href="${basePath}weatherPageController/weatherPage/${powerStationId}?powerStationId=${powerStationId}" class="nav-link nav-toggle">
				                                <i class="fa fa-sun-o"></i>
				                                <span class="title"><i18n:message name="menu_weatherstation" /></span>
				                               <span class="ynzmenuItemSelected"></span>
<!-- 				                                <span class="arrow ynzmenuItemOpen"></span> -->
				                            </a> 
				                        </li>
				                                  <!--                           并网柜-->
				                        <li id="menugridark" class="nav-item  ">
				                            <a href="${basePath}gridarkPageController/gridarkPage/${powerStationId}?powerStationId=${powerStationId}" class="nav-link nav-toggle">
				                                <i class="fa fa-code-fork"></i>
				                                <span class="title"><i18n:message name="menu_gridark" /></span>
				                               <span class="ynzmenuItemSelected"></span>
<!-- 				                                <span class="arrow ynzmenuItemOpen"></span> -->
				                            </a> 
				                        </li>
				                        <!-- 单双轴跟踪控制箱 -->
		                                 <li id="menuaxis" class="nav-item  ">
				                            <a href="${basePath}TrackPageController/TrackPage?powerStationId=${powerStationId}" class="nav-link nav-toggle">
				                                <i class="fa fa-shopping-cart"></i>
				                                <span class="title"><i18n:message name="menu_axis" /></span>
				                               <span class="ynzmenuItemSelected"></span>
<!-- 				                                <span class="arrow ynzmenuItemOpen"></span> -->
				                            </a>
				                        </li>
				                        <!-- IV多通道测试设备 -->
				                         <li id="menuivequipment" class="nav-item  ">
				                            <a href="${basePath}ivequipmentPage/${powerStationId}?powerStationId=${powerStationId}" class="nav-link nav-toggle">
				                                <i class="fa fa-contao"></i>
				                                <span class="title"><i18n:message name="menu_ivequipment" /></span>
				                               <span class="ynzmenuItemSelected"></span>
<!-- 				                                <span class="arrow ynzmenuItemOpen"></span> -->
				                            </a>
				                        </li>
		                            </ul>
	                      	      
	                        	</li> 
                            
		                    <!--                           报警管理-->
		                        <li id="menualarmmanager" class="nav-item  ">
		                            <a href="javascript:;" class="nav-link nav-toggle">
		                                <i class="fa fa-warning"></i>
		                                <span class="title"><i18n:message name="menu_alarm" /></span>
		                               <span class="ynzmenuItemSelected"></span>
		                                <span class="arrow ynzmenuItemOpen"></span>
		                            </a>
		                            <ul class="sub-menu">
		                                 <li id="alarmmanager_alarmreal" class="nav-item  ">
		                                    <a href="${basePath}alarmPage/actualAlarm?powerStationId=${powerStationId}" class="nav-link ">
		                                        <i class="fa fa-tag"></i>
		                                        <span class="title"><i18n:message name="menu_alarmreal" /></span>
<!-- 		                                         <span class="ynzmenuItemSelected"></span> -->
		                                    </a>
		                                </li>
		                                
		                                <li id="alarmmanager_alarmhistory" class="nav-item  ">
		                                    <a href="${basePath}alarmPage/historyAlarm?powerStationId=${powerStationId}" class="nav-link ">
		                                        <i class="fa fa-history"></i>
		                                        <span class="title"><i18n:message name="menu_alarmhistory" /></span>
<!-- 		                                         <span class="ynzmenuItemSelected"></span> -->
		                                    </a>
		                                </li>
		                                 
		                            </ul>
		                        </li>						
	                        
		                          <!--                          历史查询-->
		                        <li id="menumhistory" class="nav-item  "> 
		                            <a href="${basePath}history/historyPage?powerStationId=${powerStationId}" class="nav-link nav-toggle">
		                                <i class="fa fa-history"></i>
		                                <span class="title"><i18n:message name="menu_history" /></span>
		                               <span class="ynzmenuItemSelected"></span>
<!-- 		                                <span class="arrow ynzmenuItemOpen"></span> -->
		                            </a>
		                        </li>    
	                        
	                            <!--                           自定义曲线-->
		                        <li id="menucustomlinemanager" class="nav-item  ">
		                            <a href="${basePath}curvePage/curveList.do?powerStationId=${powerStationId}" class="nav-link nav-toggle">
		                                <i class="fa fa-line-chart"></i>
		                                <span class="title"><i18n:message name="menu_customline" /></span>
		                               <span class="ynzmenuItemSelected"></span>
<!-- 		                                <span class="arrow ynzmenuItemOpen"></span> -->
		                            </a>
<!-- 		                            <ul class="sub-menu"> -->
<!-- 		                                 <li id="customlinemanager_list" class="nav-item  "> -->
<%-- 		                                    <a href="${basePath}curvePage/curveList.do?powerStationId=${powerStationId}" class="nav-link "> --%>
<!-- 		                                        <i class="fa fa-bars"></i> -->
<%-- 		                                        <span class="title"><i18n:message name="menu_customlineList" /></span> --%>
<!-- 		                                         <span class="ynzmenuItemSelected"></span> -->
<!-- 		                                    </a> -->
<!-- 		                                </li>  -->
		                                
<!-- 		                                 <li id="customlinemanager_add" class="nav-item  "> -->
<%-- 		                                    <a href="${basePath}curvePage/addCurve.do?powerStationId=${powerStationId}" class="nav-link "> --%>
<!-- 		                                        <i class="fa fa-plus-square"></i> -->
<%-- 		                                        <span class="title"><i18n:message name="menu_customlineAdd" /></span> --%>
<!-- 		                                         <span class="ynzmenuItemSelected"></span> -->
<!-- 		                                    </a> -->
<!-- 		                                </li> -->
		                           
<!-- 		                            </ul> -->
		                        </li>
		                        
		                         <!--                           报表管理 -->
		                        <li id="menureportmanager" class="nav-item  ">
		                            <a href="${basePath}reportPage/reportList.do?powerStationId=${powerStationId}" class="nav-link nav-toggle">
		                                <i class="fa fa-newspaper-o"></i>
		                                <span class="title"><i18n:message name="menu_report" /></span>
		                               <span class="ynzmenuItemSelected"></span>
<!-- 		                                <span class="arrow ynzmenuItemOpen"></span> -->
		                            </a>
<!-- 		                            <ul class="sub-menu"> -->
<!-- 		                                 <li id="menureportmanager_list" class="nav-item  "> -->
<%-- 		                                    <a href="${basePath}reportPage/reportList.do?powerStationId=${powerStationId}" class="nav-link "> --%>
<!-- 		                                        <i class="fa fa-bars"></i> -->
<%-- 		                                        <span class="title"><i18n:message name="menu_reportlist" /></span> --%>
<!-- 		                                         <span class="ynzmenuItemSelected"></span> -->
<!-- 		                                    </a> -->
<!-- 		                                </li> -->
		                                
<!-- 		                            </ul> -->
		                        </li>
                       
			                        <!--                           电站 拓扑图-->
<!-- 			                        <li id="menumenustationtopo" class="nav-item  "> -->
<!-- 			                            <a href="javascript:;" class="nav-link nav-toggle"> -->
<!-- 			                                <i class="fa fa-object-ungroup"></i> -->
<%-- 			                                <span class="title"><i18n:message name="menu_stationtopo" /></span> --%>
<!-- 			                               <span class="ynzmenuItemSelected"></span>  -->
<!-- 			                            </a>   -->
<!-- 			                         </li> -->
			                       <li id="menumenuvideo" class="nav-item  ">
                                        <a id="monitoringpath" target="_blank" class="nav-link nav-toggle">
                                            <i class="fa fa-video-camera"></i>
                                            <span class="title"><i18n:message name="menu_video" /></span>
                                           <span class="ynzmenuItemSelected"></span>
<!--                                             <span class="arrow ynzmenuItemOpen"></span> -->
                                        </a>  
                                     </li>
                         </c:if>
                               
                                        
                        <!--                      集控中心+子站 + 当前是显示子站界面 -->
                        <c:if test="${isThird }"> 
	                        <!--                           设备监控-->
	                        <li id="menuequipmonitor" class="nav-item  ">
	                            <a href="javascript:;" class="nav-link nav-toggle">
	                                <i class="fa fa-eye"></i>
	                                <span class="title"><i18n:message name="menu_equipmonitor" /></span>
	                               <span class="ynzmenuItemSelected"></span>
	                                <span class="arrow ynzmenuItemOpen"></span>
	                            </a>
	                            <ul class="sub-menu">
	                                   <!--                           逆变器和测量箱-->
			                        <li id="menuinverterbox" class="nav-item  ">
			                            <a href="${basePath}inverterPage/inverterList?powerStationId=${powerStationId}" class="nav-link">
			                                <i class="fa fa-calendar-o"></i>
			                                <span class="title"><i18n:message name="menu_inverterbox" /></span>
			                               <span class="ynzmenuItemSelected"></span>
<!-- 			                                <span class="arrow ynzmenuItemOpen"></span> -->
			                            </a> 
			                        </li> 
			                         
	                                     <!--                           气象站-->
			                        <li id="menuweatherstation" class="nav-item  ">
			                            <a href="${basePath}weatherPageController/weatherPage/${powerStationId}?powerStationId=${powerStationId}" class="nav-link nav-toggle">
			                                <i class="fa fa-sun-o"></i>
			                                <span class="title"><i18n:message name="menu_weatherstation" /></span>
			                               <span class="ynzmenuItemSelected"></span>
<!-- 			                                <span class="arrow ynzmenuItemOpen"></span> -->
			                            </a> 
			                        </li>
			                        <li id="menuivequipment" class="nav-item  ">
				                            <a href="${basePath}ivequipmentPage/ivEquipmentThirdPage/${powerStationId}?powerStationId=${powerStationId}" class="nav-link nav-toggle">
				                                <i class="fa fa-contao"></i>
				                                <span class="title"><i18n:message name="menu_ivequipment" /></span>
				                               <span class="ynzmenuItemSelected"></span>
<!-- 				                                <span class="arrow ynzmenuItemOpen"></span> -->
				                            </a>
				                    </li>
	                            </ul>
                      	      
                        	</li> 
                             
	                        
                         </c:if>                
                    </ul> 
                </div> 
                </div>
<%--             <script src="${basePath}assets/pages/scripts/ynz/headerPowerStationInfo.js"></script> --%>
            <!-- END SIDEBAR -->
             
