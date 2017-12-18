<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.yunengzhe.PVMTS.domain.po.CompanyPO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/page/common/pub.jsp" %>
<%
List<CompanyPO> companyList = (List)request.getAttribute("companyList");
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
        <title><i18n:message name="role_menu_title"/></title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" /> 
         <%@ include file="/page/common/pubCss.jsp" %>
<link href="${cdnPath}global/plugins/jquery-multi-select/css/multi-select.css" rel="stylesheet" type="text/css" />
   		 <!-- END HEAD -->
	    <style type="text/css">
	    	.reset{
	    		padding: 20px 352px!important;
	    	}
	    	 .ms-container { 
			    width: 100%;
			}
			.ms-container .ms-selectable li.ms-elem-selectable{
			  padding: 0px 10px;
			}
			.ms-container .ms-selection li.ms-elem-selection {
			 padding: 0px 10px;
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
                <!-- BEGIN SIDEBAR -->
                  <%@ include file="/page/common/companyAdminMenu.jsp" %>
                <!-- END SIDEBAR --> 
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
                                <span>${role_config }</span>
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
                                        <span class="caption-subject font-dark sbold uppercase">${user_baseinfo }</span>
                                    </div>
                                </div>
                                <div class="portlet-body">
                                    <!-- BEGIN FORM-->
                                    <form action="${basePath}/user/addUser.do" id="addUserForm" method="post" class="form-horizontal">
                                        <div class="form-body">
                                            <div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button> ${alert_error } </div>
                                            <div class="alert alert-success display-hide">
                                                <button class="close" data-close="alert"></button> ${alert_success } </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">${role_rolename }
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                    <input type="text" name="rolename" id="rolename" data-required="1" class="form-control" value="" /> </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">${role_powerstation }
                                                    <span class="required">  </span>
                                                </label>
                                                <div class="col-md-4">
                                                  <select multiple="multiple" class="multi-select" id="my_multi_select1" name="my_multi_select1[]">
                                                	 <c:forEach items="${powerstation}" var="list">
                                                        <option value="${list.id}">${list.name}</option>
                                                     </c:forEach>
                                                  </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-actions reset">
                                            <div class="row">
                                                <div class="col-md-offset-3 col-md-9">
                                                    <button type="button" id="submitUser" class="btn green">${submit }</button>
                                                    <button type="button" id="cancel" class="btn default">${cancel }</button>
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
<script src="${cdnPath}global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL PLUGINS -->
<script src="${cdnPath}/global/plugins/jquery-multi-select/js/jquery.multi-select.js" type="text/javascript"></script>
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}/assets/pages/scripts/role/addRole.js?version=20170817024514" type='text/javascript'></script>
        <!-- END PAGE LEVEL SCRIPTS -->
      
    </body>

</html>
