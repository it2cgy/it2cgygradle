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
        <title>${inverter_title_list} </title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <!-- BEGIN GLOBAL MANDATORY STYLES --> 
        <%@ include file="/page/common/pubCss.jsp" %>
        
<link href="${basePath }assets/pages/css/ynz/common/static.css?version=20170817024514" rel='stylesheet' type='text/css'  />
<link href="${basePath }assets/pages/css/ynz/inverterList.css?version=20170817024514" rel='stylesheet' type='text/css'  />
		<script type="text/javascript">var inverterId  ="${inverterId==-1?null:inverterId}";</script>
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
        <div class="page-container">
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
                            <li>
                                <a  class="blueFont">${inverter_inverterandmeasuringbox}</a>
                                <i class="fa fa-circle"></i>
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
                                            <span class="caption-subject font-green bold uppercase">${inverter_survey}</span>
                                        </div>
                                    </div> 
                                    <div class="portlet-title noBorder">                                         
                                    	<div class="col-md-12 no-padding">
                                    		<dl class="col-md-2 no-padding inverterListDl" style="width: 20%">
                                    			<dt class="firstDt"><i></i>${inverter_totalquantity}</dt>
                                    			<dd><em class="font-blue" id="inverterSum"></em>${inverter_stage}</dd>
                                    		</dl>
                                    		<dl class="col-md-2 no-padding inverterListDl" style="width: 20%">
                                    			<dt class="secondDt"><i></i> ${inverter_measuringbox}</dt>
                                    			<dd><em class="font-blue" id="testBox"></em>${inverter_stage}</dd>
                                    		</dl>
                                    		<dl class="col-md-2 no-padding inverterListDl" style="width: 20%">
                                    			<dt><i></i>${inverter_directcurrent_meter}</dt>
                                    			<dd><em class="font-blue" id="cocurrent"></em>${inverter_stage}</dd>
                                    		</dl>
                                    		<dl class="col-md-2 no-padding inverterListDl" style="width: 20%">
                                    			<dt><i></i>${inverter_singlephase_meter}</dt>
                                    			<dd><em class="font-blue" id="singlePhase"></em>${inverter_stage}</dd>
                                    		</dl>
                                    		<dl class="col-md-2 no-padding inverterListDl noBorder" style="width: 20%">
                                    			<dt><i></i>${inverter_threephase_meter}</dt>
                                    			<dd><em class="font-blue" id="threePhase"></em>${inverter_stage}</dd>
                                    		</dl>
                                    	</div>
                                    </div>
                         </div> 
                            
                          <div class="portlet light bordered">
                                  <div class="portlet-title">
                                    <div class="caption">
                                        <i class="icon-bulb font-green"></i>
                                        <span class="caption-subject font-green bold uppercase">${inverter_inverterandmeasuringbox}</span>
                                    </div> 
                                </div> 
                                <div class="portlet-body">
                                </div>
                                  <table class="table table-striped table-bordered table-hover" id="sample_1" hidden="">
                                    </table>
                            </div>
                         
                    </div> 
                   <!--  <div class="inverterList">
                    	<h3>#9-NBQ10<em>G-3000w组串式</em></h3>
                    	<div class="inverterBox"><div class="leftBox">
                    	<p class="subTitle">#9-NBQ10<em>G-3000w组串式</em></p>
                    	<div class="imgBgTu"></div>
                    	<ul>
                    	<li><span>逆变器直流功率 ：</span><em>1</em>kW</li><li>
                    	<span>逆变器交流功率 ：</span><em>1</em>kW</li>
                    	<li><span>功率因数 ：</span><em>1</em></li>
                    	<li><span>逆变器温度 ：</span><em>1</em>℃</li>
                    	<li><span>日发电量：</span><em>0</em>kWh</li>
                    	</ul></div><div class="rightBox"><div id="measBox0" class="measBox"><h3 class="subTitle"><em>#9-CL10电表箱</em></h3><div class="ammeters"><div id="ammeterTop0" class="ammeterTop"><dt>单相</dt><a href="http://127.0.0.1:8080/longieb/meter/meterDetail/52?powerStationId=6"><dd class="exchangeImg"></dd></a><dd>#9-CL10-AC</dd></div><dl id="ammeterBottom0" class="ammeterBottom"><dl class="oneAmmeter"><dt>直流</dt><a href="http://127.0.0.1:8080/longieb/meter/meterDetail/51?powerStationId=6"><dd class="directImg"></dd></a><dd>#9-CL10-DC</dd></dl></dl></div></div><div class="directTable"><table border="0" cellspacing="0" cellpadding="0"><thead><tr id="meterTable0" class="meterTable"><th></th><th>直流#9-CL10-DC</th><th>单相#9-CL10-AC</th></tr></thead><tbody><tr id="power0" class="power"><td class="element">功率<br><em>(kW)</em></td><td>1</td><td>0</td></tr><tr id="generationSum0" class="generationSum"><td class="element">总发电量<br><em>(kWh)</em></td><td>0</td><td>0</td></tr></tbody></table></div></div></div></div> -->                                     
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
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
		<script src="<%=basePath %>/assets/pages/scripts/inverter/inverter-list.js?version=20170817024514" type='text/javascript'></script>
		<script type="text/javascript">
			var oldpage = ${page};
			var oldpagesize=${pagesize};
		</script>
        <!-- END PAGE LEVEL SCRIPTS -->
    </body>

</html>
