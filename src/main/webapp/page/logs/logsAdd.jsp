<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/page/common/pub.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->

    <head>
        <meta charset="utf-8" />
        <title><i18n:message name="log_addlogtitle" /></title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" /> 
         <%@ include file="/page/common/pubCss.jsp" %>
         <style>
         	.modal-footer{
         		border:0;
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
            <!-- BEGIN SIDEBAR -->
            <div class="page-sidebar-wrapper" style="margin-top: 60px;">
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
                    <!-- BEGIN THEME PANEL --> 
                    <!-- BEGIN PAGE BAR -->
                    <div class="page-bar">
                        <ul class="page-breadcrumb">
                            <li>
                                <a href="${subIndex}" ><i18n:message name="index_page" /></a>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                            <c:if test="${redirectStationId!=null&&redirectStationId!=''}">
					            <a href="${basePath}logsPage/toLogsList.do?powerStationId=${redirectStationId}" ><i18n:message name="menu_loglist" /></a>
					            </c:if>
					            <c:if test="${redirectStationId==null||redirectStationId==''}">
                                <a href="${basePath}logsPage/toLogsList.do" ><i18n:message name="menu_loglist" /></a>
					            </c:if>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <span class="blueFont"><i18n:message name="log_addcaption" /></span>
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
                                        <span class="caption-subject font-dark sbold uppercase"><i18n:message name="log_addcaption"/></span>
                                    </div>
                                </div>
                              <form action="###"  id="form_sample_3" class="form-horizontal">
                                <div class="portlet-body">
                                    <!-- BEGIN FORM-->
                                        <div class="form-body">
                                            <div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button>${alert_error} </div>
                                            <div class="alert alert-success display-hide">
                                                <button class="close" data-close="alert"></button>${alert_success} </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">${log_topic}
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                  <input type="text" id="topic" name="topic"  data-required="1" class="form-control"></input>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">${log_category}
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                 <select class="form-control select3me" id="categoryId" name="categoryId">
                                                 <c:forEach items="${category}" var="category">
                                                 	<option class="categoryoption" value="${category.id}">${category.category}</option>
                                                 </c:forEach>
                                                 </select>
                                            </div>
                                            <!-- 按钮start -->
	                                           <button type="button" class="btn btn-circle btn-success" data-target="#stack1" data-toggle="modal">${log_categorymanage}
                                   			 	   <i class="fa fa-plus"></i>
                               				   </button>
                              				   <!-- 按钮end -->
                                            </div>
                                           <div class="form-group">
                                                <label class="control-label col-md-3">${log_powerStation}
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                    <select class="form-control select3me" id="powerStationId" name="powerStationId">
                                                        <option  value="">${log_select_powerStation}</option>
                                                        <c:forEach items="${powerStations}" var="power">
		                                                        <option value="${power.id}"  <c:if test="${powerStationId==power.id }">selected</c:if> >${power.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">${log_equipmentId}
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                    <select class="form-control select3me" id="equipmentId" name="equipmentId">
                                                        <option value="">${log_select_equipment}</option>
                                                        <option value="0">${curve_powerStationId }</option>
                                                        <option value="3">${inverter_inverter}</option>
                                                        <option value="5">${log_meter}</option>
                                                        <option value="6">${log_gridark}</option>
                                                        <option value="8">${log_weather }</option>
                                                        <option value="9">${log_axis}</option>
                                                        <option value="10">${log_IV }</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                 <label class="control-label col-md-3">${equipment_num }
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                    <select class="form-control select3me" id="equipment" name="equipment">
                                                        <option value="">${log_select_equipmentnumber}</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">${log_company}
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                  <input type="text" id="kaleidoscope" name="kaleidoscope"  data-required="1" class="form-control"></input>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3"><i18n:message name="log_markdown" />
                                                <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                    <textarea id="description" name="description" rows="8"   class="form-control"></textarea>
                                                    <div id="editor_error"> </div>
                                                </div>
                                            </div>
                                            </div>
                                        </div>
                                        <div class="form-actions">
                                            <div class="row">
                                                <div class="col-md-offset-3 col-md-9">
                                                   		 <button class="btn btn-success" type="submit"><i18n:message name="submit" /></button>
	                                                    <button type="button" class="btn default" onClick="window.location.href='${basePath}logsPage/toLogsList.do?a=1<c:if test="${(admin!=null && admin==true) }">&admin=1</c:if><c:if test="${(powerStationId!=null && powerStationId>0) }">&powerStationId=${powerStationId}</c:if>'"><i18n:message name="cancel" /></button>
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
            <!-- END CONTENT -->
            <div id="stack1" class="modal fade" data-focus-on="input:first" >
                <div class="modal-dialog">
              			   <div class="modal-content">
                   <div class="modal-header">
                       <button id="stack1Close" type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                       <h4 class="modal-title" style="margin-top:35px;">${log_category}</h4>
                        <!-- 测点按钮start -->
                             <button type="button" style="text-align:right;margin:-30px 425px;" class="btn btn-circle btn-success" data-target="#stack2" data-toggle="modal">${log_addcategory }
	           			 	   <i class="fa fa-plus"></i>
	       				   </button>
	      				<!-- 测点按钮end -->
                   </div>
                   <div class="portlet-body">
						<table class="greenTable-ynz table table-striped table-bordered table-hover" id="sample_1">
<!--                      <table style="table-layout: fixed"> -->
                     	<tr>
                     		<th style="width: 30%;text-align: center;">${gridark_number}</th>
                     		<th style="width: 30%;text-align: center;">${log_category}</th>
                     		<th style="width: 30%;text-align: center;">${action}</th>
                     	</tr>
                     	<c:forEach items="${category}" var="category">
                     	<tr class="categorytr">
                     			<td style="width: 30%">${category.id}</td>
                     			<td style="width: 30%">${category.category}</td>
                     			<td style="width: 30%"> <button type="button" class="btn btn-circle btn-success" onclick="delCategory(${category.id})">${del}</button></td>
                     	</tr>
                     	</c:forEach>
                     </table>
                </div> 
                </div>
          </div> 
        </div>
        <!-- BEGIN FORM-->                                      						                    
                     <div id="stack2" class="modal fade" data-focus-on="input:first" >
                        <div class="modal-dialog">
                      			   <div class="modal-content">
                           <div class="modal-header">
                               <button id="stack2Close" type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                               <h4 class="modal-title" style="margin-top:35px;">${log_addcategory }</h4>
                           </div>
                          <form action="#" id="form_sample_4" class="form-horizontal" method="post" enctype="multipart/form-data">
                         <div class="form-body">  
                          <div class="form-group" style="margin-top: 20px">
                                   <label class="control-label col-md-3">${log_category }
                                       <span class="required"> * </span>
                                   </label>
                               <div class="col-md-7">
                                   <input type="text" id="category" class="form-control demo" name="category">  
                              		 </div>
                            		 	</div>
                            <div class="form-group">
                               <div class="modal-footer" style="margin-top: 20px;">
                                <button class="btn btn-success" type="submit"><i18n:message name="submit" /></button>
<%--                                 <button type="button" class="btn green" id="save" name="save" onclick="savePoint()"><i18n:message name="add"/></button> --%>
                                <button type="button" data-dismiss="modal" class="btn btn-outline dark marginR20"><i18n:message name="cancel"/></button>
                            	</div>
                            </div>
                          		 </div> 
                               </form>
                             </div> 
                  </div> 
                 </div>
            <!-- BEGIN QUICK SIDEBAR -->
        <%@ include file="/page/common/pubJs.jsp" %>
        <!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${cdnPath}global/plugins/select2/js/select2.full.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/ckeditor/ckeditor.js?version=20170817024514" type='text/javascript'></script>
<%--         <script src="${basePath}/assets/global/plugins/bootstrap-markdown/lib/markdown.js" type="text/javascript"></script> --%>
<%--         <script src="${basePath}/assets/global/plugins/bootstrap-markdown/js/bootstrap-markdown.js" type="text/javascript"></script> --%>
        <!-- END PAGE LEVEL PLUGINS -->
         
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}/assets/pages/scripts/logs/logs-add.js?version=20170817024514" type='text/javascript'></script>
        <!-- END PAGE LEVEL SCRIPTS -->
      
    </body>

</html>
