<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.yunengzhe.PVMTS.domain.po.CompanyPO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/page/common/pub.jsp" %>
<%

%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->

    <head>
        <meta charset="utf-8" />
        <title>${menu_usermanager } | ${user_userreset }</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" /> 
         <%@ include file="/page/common/pubCss.jsp" %>
    <!-- END HEAD -->
    <style type="text/css">
    	.reset{
    		padding: 20px 352px!important;
    	}
    </style>
	</head>
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
              <c:if test="${longiebRoleId==4}">
             <%@ include file="/page/common/companyThirdMenu.jsp" %>
            </c:if>
            <c:if test="${longiebRoleId!=4}">
             <%@ include file="/page/common/companyAdminMenu.jsp" %>
            </c:if>
                <!-- END SIDEBAR -->
            </div>
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
                                <span>${user_updatpwd }</span>
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
                                       <span class="caption-subject font-green bold uppercase">${user_updatpwd }</span>
                                    </div>                                    
                                </div>
                                <div class="portlet-body">
                                    <!-- BEGIN FORM-->
                                    <form action="${basePath}/user/addUser.do" id="updatepsd" method="post" class="form-horizontal">
                                        <div class="form-body">
                                             	 <div id="checkpsd" class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button> ${user_pwderr1 }</div>
                                                 <div id="checkpsd2" class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button> ${user_pwderr2 } </div>
                                                 <div id="checkpsd3" class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button><span id="info"></span> </div>
                                           	 <div class="form-group">
                                                <label class="control-label col-md-3">${user_pswOld }
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                    <input type="password" name="pswOld" id="pswOld" data-required="1" class="form-control" value="${userInfo.username}" /> 
                                                     <input type="hidden" name="userId" id="userId"  data-required="1" class="form-control" value="${userInfo.id}" /> 
                                                 </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">${user_pwdnew1 }
                                                  <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                    <input name="psd" id="psd" type="password" class="form-control"/> </div>
                                            </div>
                                              <div class="form-group">
                                                <label class="control-label col-md-3">${user_pswnew2 }                
                                                  <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                    <input name="psd2" id="psd2" type="password" class="form-control"/> </div>
                                            </div>
                                        </div>
                                        <div class="form-actions reset">
                                            <div class="row">
                                                <div class="col-md-offset-3 col-md-9">
                                                    <button type="button" id="submitUser" class="btn green">${submit }</button>
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
            </div>
            <!-- END CONTENT -->
            <!-- BEGIN QUICK SIDEBAR -->
            <!-- END QUICK SIDEBAR -->
        </div>
        <!-- END CONTAINER -->
        <!-- BEGIN CORE PLUGINS -->
        <%@ include file="/page/common/pubJs.jsp" %>
        <!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${cdnPath}global/plugins/select2/js/select2.full.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/ckeditor/ckeditor.js?version=20170817024514" type='text/javascript'></script>
        <!-- END PAGE LEVEL PLUGINS -->
         
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}/assets/pages/scripts/companyuser/updatepsd.js?version=20170817024514" type='text/javascript'></script>
        <!-- END PAGE LEVEL SCRIPTS -->
      
    </body>

</html>
