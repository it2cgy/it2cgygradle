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
        <title>${alarm_config_list}</title>
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
                                <a href="#">${alarm_config_list}</a>
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
                                  	    <c:if test="${menuConfig['26'].have_menu!=null && menuConfig['26'].handle_premission==1}">
		                                    <div class="caption">
		                                      <button id="addConfigButton" class="btn sbold green">${add_config}
	                                                        <i class="fa fa-plus"></i>
	                                          </button>
	                                          <button id="deleteSelButton" class="btn sbold green"> ${deleteselect }
	                                                        <i class="fa fa-trash-o"></i>
	                                          </button>
		                                    </div>
	                                    </c:if>
                                        <div class="actions">
                                          <form class="form-inline" role="form">
	                                          	<select name="equipmentType" id="equipmentType" class="form-control select3me">
			                                    		<option value="">---${curve_pleaseselectequipmenttype}---</option> 
			                                    	 
			                                    </select>
			                                    
			                                    <select name="measurementtype" id="measurementtype" class="form-control select3me">
			                                    		<option value="">---${select_point_type}---</option> 
			                                    		 
			                                    </select>
                                          	  <label style="font-size: 14px;" >${iv_equipment_number}:</label>&nbsp;&nbsp;&nbsp;<input style="width:125px!important" id="equipKey" type="text" value="${equipKey }"   class="form-control">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                                           <button type="button"  id="soursebutton" class="btn sbold green" onclick="searchdata()"> ${query }</button>    
	                                    </form> 
                                     </div>
                                </div>
                                <div class="portlet-body">
                                    <table class="greenTable-ynz table table-striped table-bordered table-hover" id="sample_1"></table>
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
<script src="${basePath}/assets/pages/scripts/alarm/configList.js?version=20170817024514" type='text/javascript'></script>
        <!-- END PAGE LEVEL SCRIPTS -->
        <script type="text/javascript">
        	var menuConfig = "${menu26}";
        </script>
      
    </body>

</html>
