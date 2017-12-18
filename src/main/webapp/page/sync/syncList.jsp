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
        <title><i18n:message name="sync_menu"/> </title>
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
                                <a href="${subIndex}" ><i18n:message name="index_page" /></a>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <a href="#"><i18n:message name="sync_list"/></a>
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
                                   <c:if test="${menuConfig['25'].handle_premission!=null && menuConfig['25'].handle_premission==1}">
	                                    <div class="caption">
	                                    
	                                      <button id="addButton" class="btn sbold green"> <i18n:message name="sync_add"/>
                                                        <i class="fa fa-plus"></i>
                                          </button>
<%--                                           <button id="deleteSelButton" class="btn sbold green"> ${deleteselect } --%>
<!--                                                         <i class="fa fa-trash-o"></i> -->
<!--                                           </button> -->
	                                    </div>
	                                    </c:if>
                                        <div class="actions">
<!--                                           <form class="form-inline" role="form"> -->
<!--                                              <div class="relaTive"> -->
<%-- 	                                          <label style="font-size: 14px;" >${role_rolename }   :</label>&nbsp;&nbsp;&nbsp;<input id="rolenamekey" type="text"   class="form-control">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; --%>
<!-- 	                                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true"  onclick="resetSearch()" style="margin-top: 13px;position:absolute;right:88px"></button> -->
<%-- 	                                          <button type="button"  id="soursebutton" class="btn sbold green" onclick="searchdata()"> ${query }</button>     --%>
<!-- 	                                          </div> -->
<!-- 	                                    </form>  -->
                                     </div>
                                </div>
                                <div class="portlet-body">
                                    <table class="greenTable-ynz table table-striped table-bordered table-hover" id="sample_1">
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
		<script src="${basePath}/assets/pages/scripts/sync/sync-list.js?version=20170817024514" type='text/javascript'></script>
        <!-- END PAGE LEVEL SCRIPTS -->
        <script type="text/javascript">
        	var menuConfig = "${menu12}";
        </script>
    </body>

</html>
