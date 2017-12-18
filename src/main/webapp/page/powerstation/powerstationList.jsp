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
        <title><i18n:message name="powerstation_listtitle" /></title>
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
                                <span>${powerstation_listStation}</span>
                            </li>
                        </ul> 
                    </div>
                    <!-- END PAGE BAR -->
                    
 					<div class="row">
                        <div class="col-md-12">
                            <!-- BEGIN EXAMPLE TABLE PORTLET-->
                            <div class="portlet light bordered">
<!--                                 <div class="portlet-title"> -->
<!--                                     <div class="caption font-dark"> -->
<%--                                           <button id="addPowerstation" class="btn sbold green"> ${powerstation_addpowerstation} --%>
<!--                                                         <i class="fa fa-plus"></i> -->
<!--                                           </button> -->
<!--                                     </div> -->
<!--                                     <div class="tools"> </div> -->
<!--                                 </div> -->
                                    <div class="portlet-title">
                                     <c:if test="${menuConfig['2'].handle_premission!=null && menuConfig['2'].handle_premission==1}"> 
                                     <div class="caption">
                                         <button id="addPowerstation" class="btn sbold green"> ${powerstation_addpowerstation}
                                                        <i class="fa fa-plus"></i>
                                         </button>
                                     </div>
                                 	 </c:if>
                                     <div class="actions">
                                          <form class="form-inline" role="form"> 
		                                    <input type="text"  id="searchkey" value="${searchkey }" class="form-control">
                                            <button type="button" id="soursebutton" class="btn sbold green" onclick="searchdata()"> ${query }</button>
		                                 </form> 
                                      </div>
                                     </div>     
                                <div class="portlet-body">
                                    <table class="greenTable-ynz table table-striped table-bordered table-hover" id="powerstationTable">
                                    </table>
                                </div>
                            </div>
                        </div>
                   </div>                               </div>
                <!-- END CONTENT BODY -->
            </div>
            <!-- END CONTENT -->
            <!-- BEGIN QUICK SIDEBAR -->
            
             
            <!-- END QUICK SIDEBAR -->
        </div>
        <!-- END CONTAINER -->
        
        <!-- BEGIN CORE PLUGINS -->
        <%@ include file="/page/common/pubJs.jsp" %>
        <!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${cdnPath}global/scripts/datatable.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
        
<script src="${cdnPath}global/plugins/select2/js/select2.full.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/ckeditor/ckeditor.js?version=20170817024514" type='text/javascript'></script>
<%--         <script src="${basePath}/assets/global/plugins/bootstrap-markdown/lib/markdown.js" type="text/javascript"></script> --%>
<%--         <script src="${basePath}/assets/global/plugins/bootstrap-markdown/js/bootstrap-markdown.js" type="text/javascript"></script> --%>
        <!-- END PAGE LEVEL PLUGINS -->
         
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}/assets/pages/scripts/powerstation/powerstationList.js?version=20170817024514" type='text/javascript'></script>
        <!-- END PAGE LEVEL SCRIPTS -->
         <script type="text/javascript">
        	var menuConfig = "${menu2}";
        </script>
    </body>

</html>
