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
        <title>${curve_listtitle}</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <!-- BEGIN GLOBAL MANDATORY STYLES --> 
        <%@ include file="/page/common/pubCss.jsp" %>
        <style type="text/css">
        	.table-bordered>tbody>tr>td,
        	.table-bordered>thead>tr>th{
        		border:0;
        	}
        	.secondTable>tbody>tr>td,
        	.secondTable>thead>tr>th{
        		border:1px solid #f0e9ea;
        	}
        	.secondTable{
        		border:1px solid #f0e9ea!important;
        		color:#20110d;
        	}
        	.secondTable tbody tr{
        		text-align:center;
        	}
        	.secondTable thead tr th{
        		background:rgba(244, 158, 158, 0.63);
        		
        	}
        	.greenTable-ynz .even{
        		background:rgba(133, 170, 184, 0.23)
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
                                <a class="blueFont" href="#">${curve_captionlist}</a>
                                <i class="fa fa-circle"></i>
                            </li>
                        </ul>
                        
                    </div>
                    <!-- END PAGE BAR -->
                    
                    <!-- END PAGE HEADER-->
                    
                   <div class="row">
                        <div class="col-md-12">
                            <div class="portlet light bordered">
                                <div class="portlet-title">
                                <c:if test="${menuConfig['5'].handle_premission!=null && menuConfig['5'].handle_premission==1}">
                                    <div class="caption font-dark">
                                          <button id="addUserButton" class="btn sbold green">${curve_addcaption}
                                               <i class="fa fa-plus"></i>
                                           </button>
                                    </div>
                                    </c:if>
                                    <div class="tools"> </div>
                                </div>
                                <div class="portlet-body">
                                    <!-- <table class="table table-striped table-bordered table-hover" id="sample_1">
                                    <table class="table table-striped table-bordered table-hover dt-responsive" width="100%" id="sample_2">
                                    		
                                    </table> -->
                                     <table class="greenTable-ynz table table-striped table-bordered table-hover dt-responsive" width="100%" id="sample_1">
                                    </table>
                                </div>
                                </div>
                            </div>
                        </div>
                    </div>                                      
                            <!-- END EXAMPLE TABLE PORTLET-->
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
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}/assets/pages/scripts/curve/curve-list.js?version=20170817024514" type='text/javascript'></script>
        <!-- END PAGE LEVEL SCRIPTS -->
        <script type="text/javascript">
        	var menuConfig = "${menu5}";
        </script>
    </body>

</html>
