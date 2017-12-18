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
        <title><i18n:message name="powerstation_addtitle" /></title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" /> 
		<%@ include file="/page/common/pubCss.jsp" %>
		<link href="${cdnPath}global/plugins/bootstrap-daterangepicker/daterangepicker.min.css" rel="stylesheet" type="text/css" />
		<link href="${cdnPath}global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />
		<link href="${cdnPath}global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css" rel="stylesheet" type="text/css" />
		<link href="${cdnPath}global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css" />
         <script>
	         var provinceName = "${powerstation_province}";
	         var cityName = "${powerstation_city}";
       		 var countyName = "${powerstation_county}";
         </script>
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
            <div class="page-sidebar-wrapper">
                <!-- BEGIN SIDEBAR -->
                <%@ include file="/page/common/companyAdminMenu.jsp" %>
                <!-- END SIDEBAR -->
            </div>
            <!-- END SIDEBAR -->
            <!-- BEGIN CONTENT -->
            <div class="page-content-wrapper">
                <!-- BEGIN CONTENT BODY -->
                <div class="page-content"> 
                    <div class="page-bar">
                        <ul class="page-breadcrumb">
                            <li>
                                <a href="${subIndex}" ><i18n:message name="index_page" /></a>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <span>${menu_push}</span>
                            </li>
                        </ul> 
                    </div>
                    <!-- END PAGE BAR -->
                    
                     
                   
                    <div class="row">
                        <div class="col-md-12">
                            <!-- BEGIN VALIDATION STATES-->
                                    <form id="mailForm" class="form-horizontal">
                                    <div id="tmp" style="display: none">
<!--                                     防止浏览器自动填充用户密码 -->
                                   		 <input type="text" id="mailUsername" disabled/>
                                    	<input type="password" id="mailPassword" disabled/>
                                    </div>
                            <div class="portlet light portlet-fit portlet-form bordered">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="icon-settings font-dark"></i>
                                       <span class="caption-subject font-green bold uppercase">${push_mailinfo}</span>
                                    </div>                                    
                                </div>
                                <div class="portlet-body">
                                        <div class="form-body">
                                            <div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button> ${alert_error}</div>
                                            <div class="alert alert-success display-hide">
                                                <button class="close" data-close="alert"></button> ${alert_success} </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">${push_serverurl}&nbsp;&nbsp;
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                    <input type="hidden" name="id" id="id" data-required="1" class="form-control" value="${mailConfig.id}"/>
                                                    <input type="text" name="serverPath" id="serverPath" data-required="1" class="form-control" value="${mailConfig.serverPath}"  ${menu24==1?'':'readonly=readonly'}/> </div>
                                            </div>
                                            <div class="form-group ">
                                                <label class="control-label col-md-3">${push_servertype}&nbsp;&nbsp;
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                    <input type="text" name="mailType" id="text" data-required="1" class="form-control" value="${mailConfig.mailType}" ${menu24==1?'':'readonly=readonly'}/> </div>
                                            </div>
                                             <div class="form-group">
                                                <label class="control-label col-md-3">${push_ssl}&nbsp;&nbsp;
													<span class="required"> * </span></label>
												<div class="col-md-4 form-inline">    
	                                               <label class="mt-radio">
	                                               <input type="radio" name="usessl" id="usessl" value="1" ${mailConfig.usessl==1?'checked':''} ${menu24==1?'':'disabled=disabled'}> ${yes}
	                                               <span></span>
		                                           </label>&nbsp;&nbsp;
		                                           <label class="mt-radio">
		                                               <input type="radio" name="usessl"  id="usessl" value="0" ${mailConfig.usessl==0?'checked':''} ${menu24==1?'':'disabled=disabled'}> ${no}		                                               <span>&nbsp;&nbsp;</span>
		                                           </label>
	                                           </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">${push_port}&nbsp;&nbsp;
													<span class="required"> * </span></label>
                                                <div class="col-md-4 form-inline">                                               		
                                                    <input name="mailPort" id="mailPort" type="text" class="form-control pull-left col-md-10" style="width:100%;" value="${mailConfig.mailPort} " ${menu24==1?'':'readonly=readonly'}/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">${push_username }&nbsp;&nbsp;
													<span class="required"> * </span></label>
                                                <div class="col-md-4 form-inline">
                                                    <input name="mailUsername" id="mailUsername" type="text" class="form-control pull-left col-md-10" style="width:100%;" value="${mailConfig.mailUsername}" ${menu24==1?'':'readonly=readonly'}/></div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">${push_password}&nbsp;&nbsp;
													<span class="required"> * </span></label>
                                                <div class="col-md-4 form-inline">
                                                    <input name="mailPassword" id="mailPassword" type="password" value="${mailConfig.mailPassword}"  class="form-control pull-left col-md-10" style="width:100%;" ${menu24==1?'':'readonly=readonly'}/></div>
                                            </div>
                                             <div class="form-group">
                                                <label class="control-label col-md-3">${push_adress}&nbsp;&nbsp;
													<span class="required"> * </span></label>
                                                <div class="col-md-4 form-inline">
                                                    <input name="mailAddress" id="mailAddress" type="text" class="form-control pull-left col-md-10" style="width:100%;" value="${mailConfig.mailAddress}" ${menu24==1?'':'readonly=readonly'}/></div>
                                            </div>
                                             <div class="form-group">
                                                <label class="control-label col-md-3">${push_interval}&nbsp;&nbsp;
													<span class="required"> * </span></label>
                                                <div class="col-md-4 form-inline">
                                                   <select class="form-control" id="pushTime" name="pushTime" ${menu24==1?'':'disabled=disabled'}>
                                                        <option value="10" ${mailConfig.pushTime==10?'selected':''} >10</option>
                                                        <option value="60" ${mailConfig.pushTime==60?'selected':''}>60</option>
                                                        <option value="120" ${mailConfig.pushTime==120?'selected':''}>120</option>
                                                  </select>
                                                </div>
                                            </div>
                                        <div class="form-actions">
                                            <div class="row">
                                                <div class="col-md-offset-3 col-md-9" style="margin-top:70px;clear:both;">
                                                	<c:if test="${menu24==1}">
                                                   		 <button type="submit" id="submit"  class="btn green">${submit }</button>
                                                   		 <a href="${basePath}powerstation/powerstationList?a=1<c:if test="${(admin!=null && admin==true) }">&admin=1</c:if><c:if test="${(powerStationId!=null && powerStationId>0) }">&powerStationId=${powerStationId}</c:if>"><button type="button"  class="btn default" >${cancel }</button></a>
                                                    </c:if>
                                             </div>
                                            </div>
                                        </div>
                                    <!-- END FORM-->
                                    </form>
                                </div>
                                <!-- END VALIDATION STATES-->
                            </div>
                        </div>
                    </div>
                </div>
                <!-- END CONTENT BODY -->
        <!-- END QUICK SIDEBAR -->
        <!-- END CONTAINER -->
        
        <!-- BEGIN CORE PLUGINS -->
        <%@ include file="/page/common/pubJs.jsp" %>
        <!-- BEGIN PAGE LEVEL PLUGINS -->
		<script src="${cdnPath}global/plugins/select2/js/select2.full.min.js" type="text/javascript"></script>
		<script src="${cdnPath}global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
		<script src="${cdnPath}global/plugins/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>
		<script src="${basePath}/assets/pages/scripts/mailConfig/mailConfig.js?version=20170817024514" type='text/javascript'></script>
		<script type="text/javascript">
        	var menuConfig = "${menu24}";
        </script>
    </body>
</html>
