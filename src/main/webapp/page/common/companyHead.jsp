<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--公共头部start-->
<header>
	<div class="topBar">
		<div class="leftRightCon floatL">
			<div class="fold menu-toggler sidebar-toggler" id="fold"></div>
			<div class="line"></div>
			<h1 class="logo"><a href="javascript:;"><img src="${basePath}assets/pages/img/ynz/common/logo.png"/></a></h1>
		</div>
		<div id="stationMessage" class="title_ynz centerCon">
			<h2 id="headerPowerStationName"></h2>
<%-- 			<c:if test="${isShowSubPage && admin==null}"> --%>
<%-- 				<p id ="indexcheck"> <span>${powerstation_address } : <em id=headerAddress></em></span>   <span>  ${powerstation_lng } : <em id="headerLng"></em></span> <span>  ${powerstation_lat } : <em id="headerLat"></em></span>   <span>  ${powerstation_installed_capacity } : <em id="headerInstallCapacity"></em></span></p> --%>
<%-- 			</c:if> --%>
		</div>
		<ul class="leftRightCon floatR topBarR userDiv">			
			<li class="cancel"><a href="${basePath}user/logout"><img src="${basePath}assets/pages/img/ynz/common/cancel.png"/></a></li>
			<li class="line"></li>
			<li id="languageChange" class="languageChange"><a id="chinese" href="###" class="chinese">中文  </a> / <a href="###"  id="english" class="english">English</a></li>
			<li class="setting"><a href="###"><em><img src="${basePath}assets/pages/img/ynz/common/setting.png"/></em></a></li>			
			
			<!-- ***************** -->
			<li class="dropdown dropdown-user">
                 <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                     <img alt="" style="width:25px;height:25px;" class="img-circle" src="${headshot}" />
                     <span class="username username-hide-on-mobile"> ${nickName } </span>
<%--                      <i class="fa fa-angle-down trigon" id="" style="height:8px!important;" ><img style="width:20px;height:25px;margin-left:10px;" src="${headshot}"/></i> --%>
                 </a>
                 <ul class="dropdown-menu dropdown-menu-default">
                     <li>
                         <a href="${basePath}userInfoPage/userInfoPage?powerStationId=${powerStationId}">
                             <i class="icon-user"></i> ${user_personal } </a>
                     </li>
                     <li>
                         <a href="${basePath}userInfoPage/updatePsdPage?powerStationId=${powerStationId}">
                             <i class="icon-calendar"></i> ${user_updatpwd } </a>
                     </li>
                       
                 </ul>
             </li>
			<!-- ***************** -->	
		</ul>
	</div>
</header>
<!--公共头部end-->
     
