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
        <title>${menu_equipment }  | ${menu_equipmentList }</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" /> 
         <%@ include file="/page/common/pubCss.jsp" %>
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
                                <a href="${subIndex}" ><i18n:message name="index_page" /></a>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <a href="#" class="blueFont">${equipment_captionlist}</a>
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
                                    <c:if test="${menuConfig['3'].handle_premission!=null && menuConfig['3'].handle_premission==1}">
                                        <div class="caption">
                                           <button type="button"  id="addEquip" class="btn sbold green" onclick="adddata()"> ${menu_equipmentAdd } 
                                             <i class="fa fa-plus"></i></button>
                                        </div>
                                        </c:if>
                                        <div class="actions">
                                              <form class="form-inline" role="form">
                                       
                                        <div class="form-group"> 
                                            <input type="text" name="sourse" id="sourse" class="form-control floatL">
                                            <button type="button"  id="soursebutton" class="btn sbold green" onclick="sourcedata()"> ${query }</button>
                                        </div>  
                                    </form>
                                         </div>
                                </div>
                                
                               
                                
                                <div class="portlet-body">
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
       
<%--  <script src="${cdnPath}global/scripts/datatable.js" type="text/javascript"></script>  --%>
<%--  <script src="${cdnPath}global/plugins/datatables/datatables.min.js" type="text/javascript"></script>  --%>
<%--  <script src="${cdnPath}global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>  --%>
<%--  <script src="${cdnPath}global/scripts/datatable.js" type="text/javascript"></script>  --%>
 <script src="${cdnPath}global/plugins/datatables/datatables.min.js" type="text/javascript"></script> 
 <script src="${basePath}assets/pages/scripts/datatables.bootstrap.js" type="text/javascript"></script> 
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}/assets/pages/scripts/equipment/equipment-list.js?version=20170817024514" type='text/javascript'></script>
        <!-- END PAGE LEVEL SCRIPTS -->
         <script type="text/javascript">
        	var menuConfig = "${menu3}";
        </script>
    </body>
    </html>
