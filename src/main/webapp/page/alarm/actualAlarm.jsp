<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/page/common/pub.jsp" %>
<%@ include file="/page/common/pubThird.jsp" %>

<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->

    <head>
        <meta charset="utf-8" />
        <title><i18n:message name="menu_alarmreal" /> </title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <!-- BEGIN GLOBAL MANDATORY STYLES --> 
        <%@ include file="/page/common/pubCss.jsp" %>
    <!-- END HEAD -->

    <body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white" >
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
                                <a href="${basePath}index/${powerStationId}.do" ><i18n:message name="index_page" /></a>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <a href="#" class="blueFont"><i18n:message name="menu_alarmreal"/></a>
                                <i class="fa fa-circle"></i>
                            </li>
                        </ul>
                        
                    </div>
                    <!-- END PAGE BAR -->
                    
                    <!-- END PAGE HEADER-->
                    
                    <div class="row">
                        <div class="col-md-12">
                            <!-- BEGIN EXAMPLE TABLE PORTLET-->
                            <div class="portlet light bordered">
                            <div class="portlet-title">
                               <div class="caption font-dark">
                                          <form class="form-inline" role="form">
                                          <label style="font-size: 14px;" >${log_equipment }   :</label>&nbsp;&nbsp;&nbsp;<input style="width:125px!important" id="equipmentName" type="text" value="${usernameKey }"   class="form-control">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                                          <label style="font-size: 14px;" >${curve_pointname }   :</label>&nbsp;&nbsp;&nbsp;<input style="width:125px!important" id="pointName" type="text" value="${nicknameKey }"   class="form-control">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                                          <label style="font-size: 14px;" >${log_description }   :</label>&nbsp;&nbsp;&nbsp; <input style="width:340px!important" name="queryKey" id="queryKey" class="form-control form-control-inline input-medium date-picker" size="16" placeholder="${alarm_pleaseinputsearch}" data-date-format="yyyy-mm-dd" type="text" value="" />
	                                          <button type="button"  id="serach" onclick="queryData()" class="btn sbold green" onclick="searchdata()"> ${query }</button>  
	                                    </form> 
                                     </div>
                               <div class="tools"> </div>
                       		 </div>
                                <div class="portlet-body">
                                    <table class="table table-striped table-bordered table-hover greenTable-ynz" id="actualTable"></table>
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
        <%@ include file="/page/common/pubJs.jsp" %>
        <!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${cdnPath}global/scripts/datatable.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL PLUGINS -->
       
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}/assets/pages/scripts/alarm/actualAlarm.js?version=20170817024514" type='text/javascript'></script>
        <!-- END PAGE LEVEL SCRIPTS -->
      
    </body>

</html>
