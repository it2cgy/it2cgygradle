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
        <title>${workorder_ordermanager}</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <!-- BEGIN GLOBAL MANDATORY STYLES --> 
        <%@ include file="/page/common/pubCss.jsp" %>
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
                                <a href="${basePath}compantUser/userListPage" ><i18n:message name="workorder_ordermanager"/></a>
                                <i class="fa fa-circle"></i>
                            </li>
                        </ul>
                        
                    </div>
                    <!-- END PAGE BAR -->
                    <!-- END PAGE HEADER-->
                    <div class="row" id="menu_list" style="margin-left: 648px;!important">
                        <div class="col-md-12">
                            <div class="margin-bottom-10" id="nestable_list_menu" >
                                <button type="button" class="btn green btn-outline sbold uppercase" data-action="expand-all">${fault_back}</button>
                                <button type="button" class="btn red btn-outline sbold uppercase" data-action="collapse-all">${my_order}</button>
                            </div>
                        </div>
                     </div>
                    <!-- 故障反馈 -->
                    <div class="row">
                        <div class="col-md-12" id="faultDiv">
                            <!-- BEGIN EXAMPLE TABLE PORTLET-->
                            <div class="portlet light bordered">
                                <div class="portlet-title">
                                    <div class="caption font-dark">
                                          <button id="addUserButton" class="btn sbold green"> ${submit_orderback}
                                                        <i class="fa fa-plus"></i>
                                          </button>
                                    </div>
                                    <div class="tools"> </div>
                                </div>
                                <div class="portlet-body">
                                    <table class="table table-striped table-bordered table-hover" id="sample_1">
                                    </table>
                                </div>
                            </div>
                        </div>
                        <!-- 我的工单 -->
                        <div class="col-md-12" style="display:none" id="orderDiv">
                            <!-- BEGIN EXAMPLE TABLE PORTLET-->
                            <div class="portlet light bordered">
                                <div class="portlet-title">
                                 <div class="caption font-dark">
                                          <button id="addUserButton" class="btn sbold green"> ${add_order}
                                                        <i class="fa fa-plus"></i>
                                          </button>
                                    </div>
                                    <div class="tools"> </div>
                                   
                                    <div class="caption font-dark" style="margin-left: 450px">
                                          <button name="selectOrder" class="btn sbold green" type="radio" value="1"> ${unsent_order}</button>
                                          <button name="selectOrder" class="btn sbold green" type="radio" value="2"> ${off_order}</button>
                                          <button name="selectOrder" class="btn sbold green" type="radio" value="3"> ${proceed_order}</button>
                                    </div>
                                    <div class="tools"> </div>
                                </div>
                                <div class="portlet-body">
                                    <table class="table table-striped table-bordered table-hover" id="orderTable">
                                    </table>
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
<script src="${basePath}/assets/pages/scripts/workorder/workOrderList.js?version=20170817024514" type='text/javascript'></script>
        <!-- END PAGE LEVEL SCRIPTS -->
      
    </body>

</html>
