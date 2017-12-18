<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/page/common/pub.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->

    <head>
        <meta charset="utf-8" />
        <title><i18n:message name="sync_add" /></title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" /> 
         <%@ include file="/page/common/pubCss.jsp" %>
         <style>
         	.modal-footer{
         		border:0;
         	}
         	
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
            <!-- BEGIN SIDEBAR -->
            <div class="page-sidebar-wrapper" style="margin-top: 60px;">
                <!-- BEGIN SIDEBAR -->
                  <%@ include file="/page/common/companyAdminMenu.jsp" %>
                <!-- END SIDEBAR -->
            </div>
            <!-- END SIDEBAR -->
            <!-- BEGIN CONTENT -->
            <div class="page-content-wrapper">
                <!-- BEGIN CONTENT BODY -->
                <div class="page-content">
                    <!-- BEGIN PAGE HEADER-->
                    <!-- BEGIN THEME PANEL --> 
                    <!-- BEGIN PAGE BAR -->
                    <div class="page-bar">
                        <ul class="page-breadcrumb">
                            <li>
                                <a href="${subIndex}" ><i18n:message name="index_page" /></a>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                            <c:if test="${redirectStationId!=null&&redirectStationId!=''}">
					            <a href="${basePath}sync/list.do?powerStationId=${redirectStationId}" ><i18n:message name="sync_list" /></a>
					            </c:if>
					            <c:if test="${redirectStationId==null||redirectStationId==''}">
                                <a href="${basePath}sync/list.do" ><i18n:message name="sync_list" /></a>
					            </c:if>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <span class="blueFont"><i18n:message name="sync_add" /></span>
                            </li>
                        </ul>
                    </div>
                    <!-- END PAGE BAR -->
                    
                    
                    
                   
                    <div class="row">
                        <div class="col-md-12">
                            <!-- BEGIN VALIDATION STATES-->
                            <div class="portlet light portlet-fit portlet-form bordered">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="icon-settings font-dark"></i>
                                        <span class="caption-subject font-dark sbold uppercase"><i18n:message name="sync_add"/></span>
                                    </div>
                                </div>
                              <form action="###"  id="form_sample_3" class="form-horizontal">
                                <div class="portlet-body">
                                    <!-- BEGIN FORM-->
                                        <div class="form-body">
                                            <div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button>${alert_error} </div>
                                            <div class="alert alert-success display-hide">
                                                <button class="close" data-close="alert"></button>${alert_success} </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-4">${sync_name}
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5" style=" style="float: left;  width: 320px !important">
                                                  <input type="text" id="syncname" name="syncname"  data-required="1" class="form-control" style="float: left;width:320px"></input>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-4">${pubtime}
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5" >
                                                  <div class="input-group input-large date-picker input-daterange" data-date="2017-06-28" data-date-format="yyyy-mm-dd" style="float: left;" >
	                                               <input type="text" class="form-control" id="startTime" name="startTime" >
	                                               <span class="input-group-addon"> ${inverter_to} </span>
	                                               <input type="text" class="form-control" id="endTime" name="endTime" > 
	                                        </div>&nbsp;&nbsp; 
                                            </div> 
                                            </div>
                                        </div>
                                        <div class="form-actions">
                                            <div class="row">
                                                <div class="col-md-offset-4 col-md-9">
                                                   		 <button class="btn btn-success" type="submit"><i18n:message name="submit" /></button>
	                                                    <button type="button" class="btn default" onClick="window.location.href='${basePath}sync/list.do?a=1<c:if test="${(admin!=null && admin==true) }">&admin=1</c:if><c:if test="${(powerStationId!=null && powerStationId>0) }">&powerStationId=${powerStationId}</c:if>'"><i18n:message name="cancel" /></button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                    <!-- END FORM-->
                                </div>
                                <!-- END VALIDATION STATES-->
                            </div>
                        </div>
                    </div>
                </div>
                <!-- END CONTENT BODY -->
            <!-- END CONTENT -->
             
        <!-- BEGIN FORM-->                                      						                    
    
            <!-- BEGIN QUICK SIDEBAR -->
        <%@ include file="/page/common/pubJs.jsp" %>
        <!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${cdnPath}global/plugins/select2/js/select2.full.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/ckeditor/ckeditor.js?version=20170817024514" type='text/javascript'></script>
<%--         <script src="${basePath}/assets/global/plugins/bootstrap-markdown/lib/markdown.js" type="text/javascript"></script> --%>
<%--         <script src="${basePath}/assets/global/plugins/bootstrap-markdown/js/bootstrap-markdown.js" type="text/javascript"></script> --%>
        <!-- END PAGE LEVEL PLUGINS -->
         
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}/assets/pages/scripts/sync/add-sync.js?version=20170817024514" type='text/javascript'></script>
        <!-- END PAGE LEVEL SCRIPTS -->
      
    </body>

</html>
