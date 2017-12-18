<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/page/common/pub.jsp" %>
<%@ include file="/page/common/pubThird.jsp" %>

<%
Object inverterInfo = request.getAttribute("InverterInfo");
if(inverterInfo!=null){
	pageContext.setAttribute("inverterjson", com.yunengzhe.commons.util.JsonUtil.beanToJson(inverterInfo));
}
%>
<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->

    <head>
        <meta charset="utf-8" />
        <title>${inverter_title_detail} </title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <!-- BEGIN GLOBAL MANDATORY STYLES --> 
        <%@ include file="/page/common/pubCss.jsp" %>
		<link href="${cdnPath}global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css" />
		<link href="${basePath}assets/pages/css/ynz/inverterDetail.css?version=20170817024514" rel='stylesheet' type='text/css'  />
    </head>
    <style>
    	.divwidth{
    		    width: 9%;
    	}
    </style>
    
    <!-- END HEAD -->

    <body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
        <!-- BEGIN HEADER -->
         <%@ include file="/page/common/companyHead.jsp" %>
        <!-- END HEADER -->
        <!-- BEGIN HEADER & CONTENT DIVIDER -->
        <div class="clearfix"> </div>
        <!-- END HEADER & CONTENT DIVIDER -->
        <!-- BEGIN CONTAINER -->
        <div class="page-container">
            <!-- BEGIN SIDEBAR -->
            <c:if test="${longiebRoleId==4}">
             <%@ include file="/page/common/companyThirdMenu.jsp" %>
            </c:if>
            <c:if test="${longiebRoleId!=4}">
             <%@ include file="/page/common/companyAdminMenu.jsp" %>
            </c:if>
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
                                <c:if test="${longiebRoleId==4}">
					            <a href="${subIndex}" ><i18n:message name="index_page" /></a>
					            </c:if>
					            <c:if test="${longiebRoleId!=4}">
                                <a href="${basePath}index/${powerStationId}.do" ><i18n:message name="index_page" /></a>
					            </c:if>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                            	<c:if test="${longiebRoleId==4}">
					            <%-- <a href="${basePath}inverterPage/inverterList?powerStationId=${powerStationId}" ><i18n:message name="inverter_inverterandmeasuringbox" /></a> --%>
					            <a href="${basePath}inverterPage/inverterList?powerStationId=${powerStationId}&page=${oldpage}&pagesize=${oldpagesize}&id=${oldId}" ><i18n:message name="inverter_inverterandmeasuringbox" /></a>
					            </c:if>
					            <c:if test="${longiebRoleId!=4}">
                                <a href="${basePath}inverterPage/inverterList?powerStationId=${powerStationId}&page=${oldpage}&pagesize=${oldpagesize}&id=${oldId}" ><i18n:message name="inverter_inverterandmeasuringbox" /></a>
					            </c:if>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <span class="blueFont">${inverter_detail}</span>
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
	                                   <em> ${inverter_name}：<span class="caption-subject font-green bold uppercase" style="font-size:16px;">${name}</span></em>
	                                </div>
	                                <div class="caption">
	                                    <i class="icon-social-dribbble font-green"></i>
	                                   <em>${axis_model }： <span class="caption-subject font-green bold uppercase"  style="font-size:16px;">${serialNumber}</span></em>
	                                </div>
                           	    </div>
                                <div class="portlet-body">
                                    <div class="table-scrollable" style="border:0px">
                                          
				                       <div class="col-lg-6 col-md-3 col-sm-6 col-xs-12">
				                            <a class="dashboard-stat dashboard-stat-v2 yellow" href="#">
				                                <div class="visual">
				                                    <i class="fa fa-comments"></i>
				                                </div>
				                                <div class="imgBox">
				                                	<img alt="" style="height: 120px;" src="${basePase}../../assets/pages/img/generationDay.png">
				                                </div>
				                                <div class="details">
				                                    <div class="number">
				                                        <span id="threePhaseActivePower" data-counter="counterup" ></span>
				                                    </div>
				                                    <div class="desc"> ${inverter_alternator_totalpower} (kW)</div>
				                                </div>
				                            </a>
				                        </div>
				                        <div class="col-lg-6 col-md-3 col-sm-6 col-xs-12">
				                            <a class="dashboard-stat dashboard-stat-v2 green" href="#">
				                                <div class="visual">
				                                    <i class="fa fa-comments"></i>
				                                </div>
				                                <div class="imgBox">
				                                	<img alt="" style="height: 120px;" src="${basePase}../../assets/pages/img/generationTotal.png">
				                                </div>
				                                <div class="details">
				                                    <div class="number">
				                                        <span id="totalP" data-counter="counterup" ></span>
				                                    </div>
				                                    <div class="desc"> ${inverter_current_totalpower} (kW)</div>
				                                </div>
				                            </a>
				                        </div> 
			                         
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12">
								<h4 class="fontBlue">${history_pointline }</h4>
	                            <div class="portlet-body form " style='margin-top: 15px;'>
                                    <form id="form_1" class="form-inline">
                                    <div class="col-sm-2 col-xs-2 col-md-3 divwidth nothingBox"></div>
                                    <div style="float: left;line-height: 34px;margin-right: 40px;">
                                        <label  class="control-label">${inverter_show_data}
                                           <span class="required"> * </span>
                                       	</label> 
                                         <label class="mt-radio">
                                               <input type="radio" name="curveType" onclick='powerModel();' id="curveType" value="0" checked> ${inverter_power }
                                               <span></span>
                                           </label>&nbsp;&nbsp;
                                           <label class="mt-radio">
                                               <input type="radio" name="curveType" onclick='generatModel();' id="curveType" value="1"> ${meter_generating}
                                               <span>&nbsp;&nbsp;</span>
                                           </label> &nbsp;&nbsp;
                                    </div> 
                                    <div id='powerModelDiv' style='display:block;float: left;margin-right: 20px;'>
                                        <label class="control-label">${inverter_search_time}
                                            <span class="required"> * </span>  </label>
                                            <div class="input-group input-large date-picker input-daterange" data-date="2017-06-28" data-date-format="yyyy-mm-dd" >
                                                    <input id="start" type="text" class="form-control" id="startTime" name="startTime">
                                                    <span class="input-group-addon"> ${inverter_to} </span>
                                                    <input id="end" type="text" class="form-control" id="endTime" name="endTime"> 
                                             </div>&nbsp;&nbsp; 
                                    </div>
                                    <div id='generatModelDiv' style='display:none;float: left;'>
                                     	<label class="mt-radio">
                                              <input type="radio" name="DateType" onclick='dateTypeChange(3);' id="DateType" value="3" checked> ${gross}
                                              <span></span>
                                          </label>&nbsp;&nbsp;
                                    	 <label class="mt-radio">
                                              <input type="radio" name="DateType" onclick='dateTypeChange(0);' id="DateType" value="0"> ${year}
                                              <span></span>
                                          </label>&nbsp;&nbsp;
                                          <label class="mt-radio">
                                              <input type="radio" name="DateType" onclick='dateTypeChange(1);' id="DateType" value="1"> ${month}
                                              <span>&nbsp;&nbsp;</span>
                                          </label> &nbsp;&nbsp;
                                          <label class="mt-radio">
                                              <input type="radio" name="DateType" onclick='dateTypeChange(2);' id="DateType" value="2"> ${day}
                                              <span>&nbsp;&nbsp;</span>
                                          </label> &nbsp;&nbsp;
                                          <div id='yearType' style='display:none;' class="input-group date form_datetime input-large"> 
                                              <input id="startyear"  type="text" size="16"  readonly class="form-control">
                                              <span class="input-group-btn">
                                                  <button class="btn default date-set" type="button">
                                                      <i class="fa fa-calendar"></i>
                                                  </button>
                                              </span>    
                                           </div>
                                           <div id='monthType' style='display:none;' class="input-group date form_datetime input-large"> 
                                               <input id="startmonth"  type="text" size="16"  readonly class="form-control">
                                               <span class="input-group-btn">
                                                   <button class="btn default date-set" type="button">
                                                       <i class="fa fa-calendar"></i>
                                                   </button>
                                               </span>     
                                           </div>
                                           <div id='dayType' style='display:none;' class="input-group date form_datetime input-large"> 
                                                <input id="startday"  type="text" size="16"  readonly class="form-control">
                                                <span class="input-group-btn">
                                                    <button class="btn default date-set" type="button">
                                                        <i class="fa fa-calendar"></i>
                                                    </button>
                                                </span>   
                                           </div>
                                    	</div>
                                    	 <button type="button" onclick="submitAddPoint();" class="btn btn-success">${inverter_search}</button>
                                    </form>         
                                </div>
                              </div>
                            <div class="clearfix"></div> 
                            <div id="electLine" style="height:350px" class='electCurve'></div>
                          <div class="portlet light bordered">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <!-- <i class="icon-bulb font-green"></i> -->
                                        <i class="titleImg title_img1"></i>
                                        <span class="caption-subject font-green bold uppercase" style="margin-top: 20px;display: inline-block;">${inverter_base_param}</span>
                                    </div> 
                                </div>
<!--                                 电表信息 -->
                                <div class="portlet-body">
                                    <!--数据表start-->
									<div class="ynzTable col-md-12">
									   <table class="table table-striped table-bordered table-hover noBorderTable" id="sample_1"></table>							
									</div><!--数据表end-->
                                  
                                <div class="row equipData" style="margin-bottom:20px">
                                	<div class="col-md-6" style="padding-left:0;">
                                		<p class="par_title col-md-12"><i class="titleImg title_img2"></i><span>${inverter_generation_param}</span></p>
	                                	<div class="col-md-3 pull-left">
											<dl>
												<dt>${inverter_generating_day}（kWh）</dt>
												<dd id="generationDaily"></dd>
											</dl>
										</div>
										<div class="col-md-3 pull-left">
											<dl>
												<dt>${inverter_generating_mouth}（kWh）</dt>
												<dd  id="generationMonth"></dd>
											</dl>
										</div>
										<div class="col-md-3 pull-left">
											<dl>
												<dt>${inverter_generating_year}（kWh）</dt>
												<dd   id="generationYear"></dd>
											</dl>
										</div>
										<div class="col-md-3 pull-left">
											<dl>
												<dt>${inverter_generating_total}（kWh）</dt>
												<dd id="generationGross"></dd>
											</dl>
										</div>
									</div>
									<div class="col-md-6"  style="padding:0;">
										<p class="par_title col-md-12"><i class="titleImg title_img3"></i><span>${inverter_pr_param}</span></p>
										<dl class="col-md-3">
											<dt>${inverter_temperature} （℃）</dt>
											<dd id="temperature"></dd>
										</dl>
										<dl class="col-md-3">
											<dt>${inverter_efficiency} （%）</dt>
											<dd id="efficiency"></dd>
										</dl>
										<dl class="col-md-3">
											<dt>${meter_power_factor}</dt>
											<dd id="powerFactor">--</dd>
										</dl>
										<dl class="col-md-3">
											<dt>${inverter_runtime} （${index_day}）</dt>
											<dd id="runtimeMonth">--</dd>
										</dl>
									</div>
                                </div>                                      
                             </div><!-- 常用数据end -->
                            </div>
                    </div>                                      
                </div>
                <!-- END CONTENT BODY -->
            </div>
            <!-- END CONTENT -->
            
        </div>
        <!-- END CONTAINER -->
        
        <!-- BEGIN CORE PLUGINS -->
        <%@ include file="/page/common/pubJs.jsp" %>
        <!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${cdnPath}global/scripts/datatable.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/moment.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/bootstrap-daterangepicker/daterangepicker.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js" type="text/javascript"></script>
<script src="${cdnPath}/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
<script src="${cdnPath}/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/jquery-minicolors/jquery.minicolors.min.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL PLUGINS -->
       
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
        <!-- END PAGE LEVEL SCRIPTS -->
<script src="${cdnPath}global/plugins/echarts/echarts.js?version=20170817024514" type='text/javascript'></script>
<script src="${basePath}assets/pages/scripts/inverter/inverter-detail.js?version=20170817024514" type='text/javascript'></script>
      	<script type="text/javascript">
      		var inverterInfo = ${inverterjson};
      	</script>
    </body>

</html>
