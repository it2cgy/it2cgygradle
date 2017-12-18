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
		<link href="${basePath}assets/pages/css/ynz/powerstationDetail.css" rel='stylesheet' type='text/css'  />
		<link href="${basePath}assets/pages/css/ynz/scrollDefined.css?version=20170817024514" rel='stylesheet' type='text/css'  />
		<!--end css style by pjs-->
			
		<style type="text/css">
			.page-content-ynz
			{
			 background-color: #0B172D; 
			}
		</style>
	</head> 
	  <body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
 
		<%@ include file="/page/common/companyHead.jsp" %>
		  <div class="page-container">
            <!-- BEGIN SIDEBAR -->
            <div class="page-sidebar-wrapper">
				<%@ include file="/page/common/companyAdminMenu.jsp" %>
			</div>
			<div class="page-content-wrapper" >
			<!--内容区start-->
			 <div class="page-content page-content-ynz" > 
			<section>
				<div class="mainContainer">
					<h3 class="basicTitle powerBt">${powerstation.powerStationName}</h3>
					<!-- 左边区域start -->
					<div class="powerLeft">
						<div class="bthBg"><a href="${basePath}index/${powerStationId}">${reback_subindex}</a></div>
						<div class="powerstionBox">
							<div class="powerstionImg"><img src="${powerstation.imgUrl==unknown||powerstation.imgUrl==''?'../assets/pages/img/ynz/plantImg.png':powerstation.imgUrl}" title="电站图片" alt="电站图片"/></div>
						</div>
					</div><!-- 左边区域end -->
					<!-- 右边区域start -->
					<div class="powerRight">
						<h4 class="powerTitle">[ ${powerstation_details} ]</h4>
						<div class="introduceBox">
							<div  class="powerDescription">${powerstation.description==''?'暂无简介':powerstation.description}</div>
						</div>
						<h4 class="powerTitle">[ ${powerstation_info} ]</h4>
						<div class="powerMessage">
							<ul>
								<li class="col-md-12">
									<div class="col-md-6 borderRight powerMessageList">
										<span>${powerstationname}</span>
										<em>${powerstation.powerStationName}</em>
									</div>
									<div class="col-md-6  powerMessageList ">
										<span>${powerstation_installed_capacity}</span>
										<em>${powerstation.installCapacity}</em>
									</div>
								</li>
								<li class="col-md-12">
									<div class="col-md-12  powerMessageList ">
										<span>${powerstation_address}</span>
										<em>${powerstation.provinceName}-${powerstation.cityName}-${powerstation.countyName}-${powerstation.location}</em>
									</div>
								</li>
								<li class="col-md-12">
									<div class="col-md-6 borderRight powerMessageList">
										<span>${powerstation_lng}</span>
	<!-- 									<em>119°38′24″E</em> -->
										<em>${lng}E</em>	
									</div>
									<div class="col-md-6 powerMessageList ">
										<span>${powerstation_lat}</span>
										<em>${lat}N</em>
									</div>
								</li>
								<li class="col-md-12">
									<div class="col-md-6 borderRight powerMessageList">
										<span>${index_station_product_time}</span>
										<em>${startProduceTime}</em>
									</div>
									<div class="col-md-6 powerMessageList ">
										<span>${index_runtime_day}</span>
										<em>${runDays}</em>${index_day}
									</div>
								</li>
								<li class="col-md-12">
									<div class="col-md-6 borderRight powerMessageList">
										<span>${powerstation_inverter}</span>
										<em>${inverter}</em>${index_ge}
									</div>
									<div class="col-md-6 powerMessageList ">
										<span>${powerstation_manager}</span>
										<em>${powerstation.investFirmContactName}</em>
									</div>
								</li>
								<li class="col-md-12 noBorder">
									<div class="col-md-6 borderRight powerMessageList">
										<span>${powerstation_iv}</span>
										<em>${iv} </em>${index_ge}
									</div>
									<div class="col-md-6 powerMessageList ">
										<span>${powerstation_meter}</span>
										<em>${monitor}</em>${index_ge}
									</div>
								</li>
							</ul>
						</div>
					</div><!-- 右边区域end -->
				</div>
			</section>
		</div></div></div>
		<!--内容区end-->
	</body>
	 <%@ include file="/page/common/pubJs.jsp" %>
	 <script>
	 	$(function(){
	 	    $("#fold").click();	 				
	 	})
	 </script>
</html>
