<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/page/common/pub.jsp" %>

<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->

    <head>
        <meta charset="utf-8" />
        <title>${menu_weatherstation}</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <!-- BEGIN GLOBAL MANDATORY STYLES --> 
        <%@ include file="/page/common/pubCss.jsp" %>
		<link href="${basePath}assets/pages/css/ynz/common/static.css?version=20170817024514" rel='stylesheet' type='text/css'  />
		<!-- BEGIN PAGE LEVEL PLUGINS -->
		<link href="${cdnPath}global/plugins/bootstrap-select/css/bootstrap-select.min.css" rel="stylesheet" type="text/css" />
		<link href="${cdnPath}global/plugins/jquery-multi-select/css/multi-select.css" rel="stylesheet" type="text/css" />
		<link href="${cdnPath}global/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
		<link href="${cdnPath}global/plugins/select2/css/select2-bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="${basePath}assets/pages/css/ynz/weatherstation.css?version=20170817024514" rel='stylesheet' type='text/css'  />
        <!-- END PAGE LEVEL PLUGINS -->
		</style>
    </head>
    <!-- END HEAD -->

    <body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
        <!-- BEGIN HEADER -->
         <%@ include file="/page/common/companyHead.jsp" %>
        <!-- END HEADER -->
        <!-- BEGIN HEADER & CONTENT DIVIDER -->
        <div class="clearfix"> </div>
        <!-- END HEADER & CONTENT DIVIDER -->
        <!-- BEGIN CONTAINER -->
        <div class="page-container" style="background:#0c1c33">
            <!-- BEGIN SIDEBAR -->
             <%@ include file="/page/common/companyAdminMenu.jsp" %>
            <!-- END SIDEBAR -->
            <!-- BEGIN CONTENT -->
            <div class="page-content-wrapper">
                <!-- BEGIN CONTENT BODY -->
                <div class="page-content">
                    <!-- BEGIN PAGE HEADER-->
                    
                    <!-- BEGIN PAGE BAR -->
                    <div class="page-bar">
                        <ul class="page-breadcrumb">
                               <li>
                                <a href="${basePath}index/${powerStationId}.do" ><i18n:message name="index_page" /></a>
                                <i class="fa fa-circle"></i>
                            </li>
                                <a href="#" class="blueFont">${menu_weatherstation}</a>
                            </li> 
                            
                        </ul>
                        
                    </div>
                    <!-- END PAGE BAR -->
                    
                    <!-- END PAGE HEADER-->
                    
                    <div class="row">
	                   <div class="portlet light bordered">
                                    <div class="portlet-title">
                                        <div class="caption">
                                            <i class="icon-social-dribbble font-green"></i>
                                            <span class="caption-subject font-green bold uppercase">${weather_number}：${meteor.serialNumber}</span><br>
                                            <input hidden="hidden"id="id" value="${meteor.id}">
                                            <input hidden="hidden"id="equipmenttypeid" value="${meteor.equipmenttypeid}">
                                        </div>
                                        
                                    </div>
                                    <div class="portlet-body">
                                        <div class="col-md-12 no-padding">
	                                        <dl class="eleList col-md-3 no-padding col-md-ynz">
	                                        	<dt><i></i><img src="${basePath}assets/pages/img/ynz/weath1.png"/></dt>
	                                        	<dd class="marginT10 marginB10"><em id="irradiance"></em> W/㎡</dd>
	                                        	<dd>${weather_levelirradiance}</dd>
	                                        </dl>
	                                        <dl class="eleList col-md-3 no-padding col-md-ynz">
	                                        	<dt><i></i><img src="${basePath}assets/pages/img/ynz/weath2.png"/></dt>
	                                        	<dd class="marginT10 marginB10"><em id="directIrradiance"></em> W/㎡</dd>
	                                        	<dd>${weather_directRadiation}</dd>
	                                        </dl>
	                                        <dl class="eleList col-md-3 no-padding col-md-ynz">
	                                        	<dt><i></i><img src="${basePath}assets/pages/img/ynz/weath3.png"/></dt>
	                                        	<dd class="marginT10 marginB10"><em id="scateredIrradiance"></em> W/㎡</dd>
	                                        	<dd>${weather_scateredRadiation}</dd>
	                                        </dl>
	                                        <dl class="eleList col-md-3 no-padding col-md-ynz">
	                                        	<dt><i></i><img src="${basePath}assets/pages/img/ynz/weath4.png"/></dt>
	                                        	<dd class="marginT10 marginB10"><em id="inclinedIrradiance"></em> W/㎡</dd>
	                                        	<dd>${weather_inclinedIrradiance}</dd>
	                                        </dl>
	                                        <dl class="eleList col-md-3 no-padding col-md-ynz">
	                                        	<dt><i></i><img src="${basePath}assets/pages/img/ynz/weath6.png"/></dt>
	                                        	<dd class="marginT10 marginB10"><em id="rainFall"></em> mm</dd>
	                                        	<dd>${weather_rainFall}</dd>
	                                        </dl>
	                                        <dl class="eleList col-md-3 no-padding col-md-ynz">
	                                        	<dt><i></i><img src="${basePath}assets/pages/img/ynz/weath7.png"/></dt>
	                                        	<dd class="marginT10 marginB10"><em id="windSpeed"></em> m/s</dd>
	                                        	<dd>${weather_RH_speed}</dd>
	                                        </dl>
	                                        <dl class="eleList col-md-3 no-padding col-md-ynz">
	                                        	<dt><i></i><img src="${basePath}assets/pages/img/ynz/weath7.png"/></dt>
	                                        	<dd class="marginT10 marginB10"><em id="windDirection"></em>°</dd>
	                                        	<dd>${weather_wind_direction}</dd>
	                                        </dl>
	                                        <dl class="eleList col-md-3 no-padding col-md-ynz">
	                                        	<dt><i></i><img src="${basePath}assets/pages/img/ynz/weath8.png"/></dt>
	                                        	<dd class="marginT10 marginB10"><em id="humidity"></em>%</dd>
	                                        	<dd>${weather_RHhumidity}</dd>
	                                        </dl>
	                                        <dl class="eleList col-md-3 no-padding col-md-ynz">
	                                        	<dt><i></i><img src="${basePath}assets/pages/img/ynz/weath5.png"/></dt>
	                                        	<dd class="marginT10 marginB10"><em id="temperature"></em>℃</dd>
	                                        	<dd>${weather_environment_temperature}</dd>
	                                        </dl>
                                        </div> 
                                    </div>
                         </div>
                           
                            
                          <div class="portlet light bordered">
                          		<div>
                          			<table class="greenTable-ynz table table-bordered table-striped table-condensed flip-content" id="sample_2">
                          				<thead>
                          					<th>${project}</th>
                          					<th>${total_day_radiation_value} kWh/m²</th>
                          					<th>${total_month_radiation_value} kWh/m²</th>
                          					<th>${total_year_radiation_value} kWh/m²</th>
                          				</thead>
                          				<tbody>
                          					<tr>
                          						<td>${total_levelirradiance}</td>
                          						<td id="radiantExposure"></td>
                          						<td id="radiantExposureMonth"></td>
                          						<td id="radiantExposureYear"></td>
                          					</tr>
                          					<tr>
                          						<td>${total_inclinedIrradiance}</td>
                          						<td id="inclinedExposureDay"></td>
                          						<td id="inclinedExposureMonth"></td>
                          						<td id="inclinedExposureYear"></td>
                          					</tr>
                          					<tr>
                          						<td>${directRadiation}</td>
                          						<td id="directRadiation"></td>
                          						<td id="directExposureMonth"></td>
                          						<td id="directExposureYear"></td>
                          					</tr>
                          					<tr>
                          						<td>${scateredRadiation}</td>
                          						<td id="scatteredExposure"></td>
                          						<td id="scateredExposureMonth"></td>
                          						<td id="scateredExposureYear"></td>
                          					</tr>
                          				</tbody>
                          			
                          			</table>
                          		</div>
<!--                                 <div class="portlet-title"> -->
<!--                                     <div class="caption"> -->
<!--                                         <i class="icon-bulb font-green"></i> -->
<%--                                         <span class="caption-subject font-green bold uppercase">${weather_element_temperature}</span> --%>
<!--                                     </div>  -->
<!--                                 </div> -->
                                <div class="portlet-title">
                                    <div class="caption font-dark">
                                          <button type="button" class="btn green" data-target="#stack1" data-toggle="modal"  style="margin-top: 5px;margin-bottom: 10px;"> ${weather_select_inductor} </button> 
                                    </div> 
                                </div>
<!--                                 电表信息 -->
                                <div class="portlet-body"> 
	                                <h4 style="font-size: 15px; height: 42px;  line-height: 42px;  color: #fff;  text-align: center;background: #36C6D3;">${history_pointline }</h4>
			                        <div id="echarts_line" style="height:350px;"></div> 
			                         <div>
		                                 <table class="greenTable-ynz table table-bordered table-striped table-condensed flip-content" id="sample_1">
			                                 <thead>
		                                         <tr>
		                                             <th> ${weather_inductor} </th>
		                                             <th> ${weather_datatime} </th>
		                                             <th> ${weather_value} </th>
		                                         </tr>
		                                     </thead>
		                                 </table> 
	                                 </div>

                        		</div>
								<!-- 弹框start -->
								<div id="stack1" class="modal fade"  data-focus-on="input:first" >
                                     <div class="modal-dialog">
                              			   <div class="modal-content">
                              			    <div class="modal-header">
	                                            <button id="stack1closBtn" type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	                                            <h4 class="modal-title">${weather_select_inductor}</h4>
	                                        </div>
				                    <div class="portlet-body form">
                                    <!-- BEGIN FORM-->
                                    
                                       <form action="###" class="form-horizontal form-row-seperated">
                                        <div class="form-body">
                                            <div class="form-group">
                                                <label class="control-label col-md-2">${weather_inductor}</label>
                                                <div class="col-md-10">
<!--                                                     <select multiple="multiple" class="multi-select" id="my_multi_select1" name="my_multi_select1[]"> -->
                                                        
<!--                                                     </select> -->

														 <select  multiple="multiple"   class="multi-select" id="select1" name="my_multi_select1[]"></select>
                                                </div>
                                            </div> 
                                        </div>
                                        <div class="form-actions">
                                          <div class="row">
                                                <div class="col-md-offset-4 col-md-9">
                                                    <button type="button" class="btn green" id="checkdata">
                                                        <i class="fa fa-check"></i>${weather_screen}</button>
                                                    <button type="button" class="btn grey-salsa btn-outline" id="cancelBtn">${cancel}</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form> 
                                    <!-- END FORM-->
                                </div>
	                                    </div>
                                   </div>
                          	  </div><!-- 弹框end -->
                       
                           
                    </div>                                      
                </div>
                <!-- END CONTENT BODY -->
            </div>
            <!-- END CONTENT -->
            </div>
        </div>
        <!-- END CONTAINER -->
        
        <!-- BEGIN CORE PLUGINS -->
        <%@ include file="/page/common/pubJs.jsp" %>
        <!-- BEGIN PAGE LEVEL PLUGINS -->
		<script src="${cdnPath}/global/scripts/datatable.js" type="text/javascript"></script>
		<script src="${cdnPath}/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
		<script src="${cdnPath}/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
		<script src="${cdnPath}/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
		        
		<script src="${basePath}assets/pages/scripts/echarts-all.js?version=20170817024514" type='text/javascript'></script>
		        <!-- END PAGE LEVEL PLUGINS -->
		        <!-- BEGIN PAGE LEVEL PLUGINS --> 
		<script src="${cdnPath}/global/plugins/jquery-multi-select/js/jquery.multi-select.js" type="text/javascript"></script>
		        <!-- END PAGE LEVEL PLUGINS -->
		<script src="${basePath}/assets/pages/scripts/weather/weather.js?version=20170817024514" type='text/javascript'></script>
    </body>

</html>
