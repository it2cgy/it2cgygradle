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
        <title>${user_useraddtitle}</title>
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
                  <%@ include file="/page/common/companyAdminMenu.jsp" %>
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
                                <c:if test="${redirectStationId!=null&&redirectStationId!=''}">
					            <a href="${basePath}compantUser/userListPage.do?powerStationId=${redirectStationId}" ><i18n:message name="menu_userlist" /></a>
					            </c:if>
					            <c:if test="${redirectStationId==null||redirectStationId==''}">
                                <a href="${basePath}compantUser/userListPage.do" ><i18n:message name="menu_userlist" /></a>
					            </c:if>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <span>${flag==1?edit:view}</span>
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
                                            <!-- 头像图片 -->
                                            <div class="form-group">
                                                 <label class="control-label col-md-3">${user_userheadshot}&nbsp;&nbsp;
                                                	<span class="required"> </span>
                                                </label>
                                                <c:choose>
                                                	<c:when test="${userInfo.headshot!=null&&userInfo.headshot!=''}">
	                                                	 <div class="col-md-9" id="uploadDiv" style="display:none">
	                                                	  <a class='fileBtn borderRadius5' onclick="fileUpload()" ><i class='fa fa-camera btnCamera' ></i></a>
				                                           <div id="editor_error"> </div>
					                                     </div>
					                                     <div id="fileDiv" style="display:block;width:69px;height:60px;position:relative;float:left;margin-right:15px;">
															<img name="userImg" id="userImg" src="${userInfo.headshot}" style="width:65px;height:65px;margin-left:10px"/>
															 <c:if test="${flag==1}" >
																<i class="fa fa-times-circle" style="position:absolute;color:red;right:-13px;top:-10px;" id="" onclick="delFile(this)"></i>
                                              				 </c:if>
														 </div>
                                                	</c:when>
                                                	<c:otherwise>
                                                		 <div class="col-md-9" id="uploadDiv" style="display:block">
				                                         <c:if test="${flag==1}" >
				                                         	 <a class='fileBtn borderRadius5' onclick="fileUpload()" ><i class='fa fa-camera btnCamera' ></i></a>
				                                         </c:if>
				                                         <c:if test="${flag==0}" >
				                                         	  <a class='fileBtn borderRadius5'  ><i class='fa fa-camera btnCamera' ></i></a>
				                                         </c:if>
				                                           <div id="editor_error"> </div>
					                                     </div>
					                                     <div id="fileDiv" style="display:none;width:69px;height:60px;position:relative;float:left;margin-right:15px;">
															<img name="userImg" id="userImg" src="" style="width:65px;height:65px;margin-left:10px"/>
															 <c:if test="${flag==1}" >
																<i class="fa fa-times-circle" style="position:absolute;color:red;right:-13px;top:-10px;" id="" onclick="delFile(this)"></i>
															</c:if>
														 </div>
                                                	</c:otherwise>
                                                </c:choose>
		                                    </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">${user_username }
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                    <input type="text" name="username" id="username"  data-required="1" class="form-control" ${flag==0?'disabled':''} value="${userInfo.username}" /> 
                                                     <input type="hidden" name="userId" id="userId"  data-required="1" class="form-control" value="${userInfo.id}" /> 
                                                 </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">${user_email }
<!--                                                     <span class="required"> * </span> -->
                                                </label>
                                                <div class="col-md-4">
                                                    <div class="input-group">
                                                        <span class="input-group-addon">
                                                            <i class="fa fa-envelope"></i>
                                                        </span>
                                                        <input type="email" id="email" name="email" class="form-control" placeholder=""  ${flag==0?'disabled':''} value="${userInfo.email}" > </div>
                                                </div>
                                            </div>
                                             <div class="form-group">
                                                <label class="control-label col-md-3">${user_telephone }                 
<!--                                                   <span class="required"> * </span> -->
                                                </label>
                                                <div class="col-md-4">
                                                    <input name="telephone" id="telephone" type="text" class="form-control"  ${flag==0?'disabled':''} value="${userInfo.telephone}" /> </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">${user_nickname }
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                    <input type="text" name="realname" id="realname" data-required="1"  ${flag==0?'disabled':''} class="form-control"  value="${userInfo.nickname}" /> </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">${pub_company }
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                <input type="text" name="inputCompany" id="inputCompany" value="${userInfo.inputCompany }"  ${flag==0?'disabled':''} data-required="1" class="form-control" /> 
                                               
                                                 <select style="display:none;"  class="form-control" id="companyId" name="companyId"  ${flag==0?'disabled':''} >
                                                  <c:forEach items="${companyList}" var="company"> 
	                                                  <c:choose>
	                                                  	<c:when test="${company.id==userInfo.companyId}">
	                                                  	 	<option value="${company.id}" selected="selected">${company.companyName}</option>
	                                                  	</c:when>
	                                                  	<c:otherwise>
	                                                        <option value="${company.id}">${company.companyName}</option>
	                                                  	</c:otherwise>
	                                                  </c:choose>
                                                  </c:forEach>
                                                  </select>
                                                </div>
                                            </div>
                                             <div class="form-group">
                                                <label class="control-label col-md-3">${user_role }
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                  <select class="form-control select2me" id="roleId" name="roleId"  ${flag==0?'disabled':''}  ${userInfo.id==499?'disabled':''}>
                                                	<c:forEach items="${roleList}" var="role"> 
                                                	  <c:choose>
	                                                  	<c:when test="${role.selected}">
	                                                  	 	<option value="${role.id}" selected="selected">${role.roleName} </option>
	                                                  	</c:when>
	                                                  	<c:otherwise>
	                                                        <option value="${role.id}" >${role.roleName} </option>
	                                                  	</c:otherwise>
	                                                  </c:choose>
                                                	</c:forEach>
                                                  </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-actions reset">
                                            <div class="row">
                                                <div class="col-md-offset-3 col-md-9">
                                                <c:if test="${flag==1}" >
                                                    <button type="button" id="submitUser" class="btn green">${submit }</button>
                                                   <a href="${basePath}compantUser/userListPage.do<c:if test="${(admin!=null && admin==true) }">?admin=1</c:if>"><button type="button" id="cancel" class="btn default" >${cancel }</button></a>
                                               </c:if>
                                                <c:if test="${flag==0}" >
                                                   <button type="button" id="cancel" class="btn green">${reback }</button>
                                                </c:if>
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
        <form id='fileform' target='framFile' method='post' action='${uploadurl}' enctype='multipart/form-data' hidden="hidden">
        	  <input type="file" id="file" name="file"  accept="image/jpg,image/png,image/jpeg,image/jpeg" onchange="uploadFile(this)"  />
              <input type='text' hidden='hidden' name='fileDir' id='fileDir' value='longieb/user'/>
        </form>
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
<script src="${basePath}/assets/pages/scripts/companyuser/editUser.js?version=20170817024514" type='text/javascript'></script>
<script src="${basePath}/assets/pages/scripts/ynz/jquery-form.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL SCRIPTS -->
      
    </body>

</html>
