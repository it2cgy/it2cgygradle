<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/page/common/pub.jsp"%>

<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->

<head>
<meta charset="utf-8" />
<title>${iv_title_detail}</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<%@ include file="/page/common/pubCss.jsp"%>
<link href="${basePath}assets/pages/css/ynz/common/static.css" rel="stylesheet" type="text/css"/>
<!-- BEGIN PAGE LEVEL PLUGINS -->
<link href="${cdnPath}global/plugins/bootstrap-select/css/bootstrap-select.min.css" rel="stylesheet" type="text/css" />
<link href="${cdnPath}global/plugins/jquery-multi-select/css/multi-select.css" rel="stylesheet" type="text/css" />
<link href="${cdnPath}global/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
<link href="${cdnPath}global/plugins/select2/css/select2-bootstrap.min.css" rel="stylesheet" type="text/css" />

<!-- END PAGE LEVEL PLUGINS -->
</head>
<!-- END HEAD -->

<body
	class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
	<!-- BEGIN HEADER -->
	<%@ include file="/page/common/companyHead.jsp"%>
	<!-- END HEADER -->
	<!-- BEGIN HEADER & CONTENT DIVIDER -->
	<div class="clearfix"></div>
	<!-- END HEADER & CONTENT DIVIDER -->
	<!-- BEGIN CONTAINER -->
	<div class="page-container" style="background: #0c1c33">
		<!-- BEGIN SIDEBAR -->
		<%@ include file="/page/common/companyAdminMenu.jsp"%>
		<!-- END SIDEBAR -->
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<!-- BEGIN CONTENT BODY -->
			<div class="page-content">
				<!-- BEGIN PAGE HEADER-->

				<!-- BEGIN PAGE BAR -->
				<div class="page-bar">
					<ul class="page-breadcrumb">
						<li><a href="${subIndex}"><i18n:message name="index_page" /></a>
							<i class="fa fa-circle"></i></li>
						<li><a href="javascript:;" onclick="reback()">${iv_equpment}</a> <i class="fa fa-circle"></i></li>
						<li><a href="#">${iv_gallery_detail}</a></li>
					</ul>

				</div>
				<!-- END PAGE BAR -->

				<!-- END PAGE HEADER-->

				<div class="row">
					<div class="portlet light bordered">
						<div class="portlet-title" style="margin-top: 18px;">
							<div class="caption">
								<i class=" icon-layers font-green"></i> <span
									class="caption-subject font-green bold uppercase"
									style="font-size: 20;">${iv_weather_info}</span>
							</div>
						</div>
						<div class="portlet-body">
							<blockquote>
								<p>
									${weather_irradiance}： <span id="irradiance" class="font-green"></span> W/㎡
									&nbsp;&nbsp;&nbsp;&nbsp; ${weather_environment_temperature}： <span id="temperature"
										class="font-green"></span>°C &nbsp;&nbsp;&nbsp;&nbsp; ${weather_element_temperature}： <span
										id="temperatureofModules" class="font-green"></span> °C
									&nbsp;&nbsp;&nbsp;&nbsp; ${weather_humidity}： <span id="humidity"
										class="font-green"></span> &nbsp;&nbsp;&nbsp;&nbsp; ${weather_RH_speed}： <span
										id="windSpeed" class="font-green"></span> m/s
									&nbsp;&nbsp;&nbsp;&nbsp; ${weather_wind_direction}： <span id="windDirection"
										class="font-green"></span> °
								</p>
							</blockquote>
						</div>
					</div>
					<div class="col-md-12">
						<div class="portlet light portlet-fit portlet-form bordered">
							<div class="portlet-title" style="margin-top: 18px;">
								<div class="caption">
									<i class=" icon-layers font-green"></i> <span
										class="caption-subject font-green bold uppercase"
										style="font-size: 20;">${iv_equpment}：${equipmentname}</span>
								</div>
							</div>
							<div class="portlet-body">
								<div class="col-md-5 col-sm-6">
									<table
										class="table table-bordered table-striped table-condensed flip-content">
										<tbody>
											<tr>
												<td width="30%">${iv_current_power}(W)</td>
												<td id="totalInputPower" width="70%">${result.GeneratedActivePower}</td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="col-md-5 col-sm-6">
									<table
										class="table table-bordered table-striped table-condensed flip-content">
										<tbody>
											<tr>
												<td width="30%">${iv_generating_mouth}(Wh)</td>
												<td id="totalInputPower" width="70%">${result.PositiveActivePMonth}</td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="col-md-5 col-sm-6">
									<table
										class="table table-bordered table-striped table-condensed flip-content">
										<tbody>
											<tr>
												<td width="30%">${iv_current}(A)</td>
												<td id="totalInputPower" width="70%">${result.Current}</td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="col-md-5 col-sm-6">
									<table
										class="table table-bordered table-striped table-condensed flip-content">
										<tbody>
											<tr>
												<td width="30%">${iv_generating_day}(Wh)</td>
												<td id="totalInputPower" width="70%">${result.PositiveActivePDaily}</td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="col-md-5 col-sm-6">
									<table
										class="table table-bordered table-striped table-condensed flip-content">
										<tbody>
											<tr>
												<td width="30%">${iv_voltage}(V)</td>
												<td id="totalInputPower" width="70%">${result.Voltage}</td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="col-md-5 col-sm-6">
									<table
										class="table table-bordered table-striped table-condensed flip-content">
										<tbody>
											<tr>
												<td width="30%">${iv_generating_year}(Wh)</td>
												<td id="totalInputPower" width="70%">${result.PositiveActivePYear}</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- END CONTENT BODY -->
		</div>
		<!-- END CONTENT -->

	</div>
	<!-- END CONTAINER -->

	<!-- BEGIN CORE PLUGINS -->
	<%@ include file="/page/common/pubJs.jsp"%>
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script src="${cdnPath}global/scripts/datatable.js"
		type="text/javascript"></script>
	<script src="${cdnPath}global/plugins/datatables/datatables.min.js"
		type="text/javascript"></script>
	<script
		src="${cdnPath}global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js"
		type="text/javascript"></script>
	<script
		src="${cdnPath}global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"
		type="text/javascript"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<script
		src="${basePath}/assets/pages/scripts/ivequipment/ivequipment.js"
		type="text/javascript"></script>
	<script type="text/javascript">
		$(function() {
			weatherInfo();
		});
		function reback() {
			window.location.href = basePath + "ivequipmentPage/" + window.ynz.longieb.powerStationId;
		}
		/**
		 * 获取电站气象数据
		 */
		function weatherInfo() {
			ynzAjax.get(window.ynz.path + "/powerStation/getWeatherInfo/"
					+ window.ynz.longieb.powerStationId + ".do", function(
					response) {
				for ( var data in response.data) {
					if ($("#" + data)) {
						var value = toDecimal(response.data[data], 3);
						$("#" + data).html(value);
					}
				}
			}, function(e) {
				console.log("--------error------" + e);
			});
		}
	</script>
</body>

</html>
