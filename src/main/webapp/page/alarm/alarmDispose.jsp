<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/page/common/pub.jsp" %>

<!DOCTYPE html>
<html lang="en">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->

    <head>
        <meta charset="utf-8" />
        <title>${alarm_alarmDispose}</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" /> 
         <%@ include file="/page/common/pubCss.jsp" %>
         <style type="text/css">
         	.col-md-8-this{
         	    width: 67.8%;
         	}
         	.col-md-7-this{
         	    width: 57.5%;
         	}
         	.inputList{
         		display:block;
         		overflow:hidden;
         		margin:8px 0;
         	}
         	.historyMessage{
         		width:1000px;
         		margin:0 auto;
         	}
         	.messageInfo{
         		margin-left:50px;	
         	}
         	@media only screen and (min-width: 1300px) and (max-width: 1500px) {
         		.historyMessage{
	         		width:900px;
	         	}
	         	.messageInfo{
	         		margin-left:38px;	
	         	}
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
                     
                    <!-- BEGIN PAGE BAR -->
                    <div class="page-bar">
                        <ul class="page-breadcrumb">
                            <li>
                                <a href="${subIndex}" ><i18n:message name="index_page" /></a>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <span class="blueFont">${alarm_confirm }</span>
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
                                        <span class="caption-subject font-dark sbold uppercase">${alarm_confirm }</span>
                                    </div>
                                </div>
                                <div class="portlet-body">
                                    <!-- BEGIN FORM-->
                                    <form action="#" id="form_sample_3" class="form-horizontal" method="post" enctype="multipart/form-data">
                                    	<div class="form-body overflow-h">
                                            <div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button>  ${alert_error} </div>
                                            <div class="alert alert-success display-hide">
                                                <button class="close" data-close="alert"></button> ${alert_success} </div>
                                            <div class="historyMessage">
                                            	<div class="form-group overflow-h" style="width:50%;float:left;">
                                            		<div class="inputList">
	                                            		<label class="col-md-3">${alarm_id}
		                                                    <span class="required"> * </span>
		                                                </label>
		                                                <div class="col-md-7">
		                                                	<input type="text" id="id" name="id" disabled data-required="1" class="form-control" value="${alarm.id}"></input>
		                                            	</div>
	                                            	</div>
	                                            	<div class="inputList">
	                                            		<label class="col-md-3">${alarm_alarmGrade}
		                                                    <span class="required"> * </span>
		                                                </label>
		                                                <div class="col-md-7">
		                                                	<input type="text" id="alarmGrade" name="alarmGrade" disabled data-required="1" class="form-control" value="${alarm.alarmGrade==1?'I':alarm.alarmGrade==2?'II':alarm.alarmGrade==3?'III':'III'}"/>
		                                            	</div>
	                                            	</div>
	                                            	<div class="inputList">
	                                            		<label class="col-md-3">${alarm_powerStationName}
		                                                    <span class="required"> * </span>
		                                                </label>
		                                                <div class="col-md-7">
		                                                	 <input type="text" id="powerStationName" name="powerStationName" disabled data-required="1" class="form-control" value="${power.powerStationName}"></input>
		                                            	</div>
	                                            	</div><!-- 报警电站 -->
	                                            	<div class="inputList">
	                                            		<label class="col-md-3">${iv_equipment_number}
		                                                    <span class="required"> * </span>
		                                                </label>
		                                                <div class="col-md-7">
		                                                	<input type="text" id="equipmentcontainerName" name="equipmentcontainerName" disabled data-required="1" class="form-control" value="${alarm.equipmentcontainerName}"></input>
		                                            	</div>
	                                            	</div><!-- 设备编号 -->
                                            	</div>
                                            	<div class="form-group overflow-h" style="width:50%;float:left;">
                                            		<div class="inputList">
	                                            		<label class="col-md-3">${log_company}
		                                                    <span class="required"> * </span>
		                                                </label>
		                                                <div class="col-md-7"  style="margin-left: -14px;">
		                                                	<input type="text" id="company" name="company" disabled data-required="1" class="form-control" value="${power.investFirmName}"></input>
		                                            	</div>
	                                            	</div>
	                                            	<div class="inputList">
	                                            		<label class="col-md-3">${alarm_alarmtime }
		                                                    <span class="required"> * </span>
		                                                </label>
		                                                <div class="col-md-7"  style="margin-left: -14px;">
		                                                	<input type="text" id="eventTime" name="eventTime" disabled data-required="1" class="form-control" value="<fmt:formatDate value='${alarm.eventTime}' pattern='yyyy-MM-dd HH:mm:ss'/>"></input>
		                                            	</div>
	                                            	</div>
                                            		
	                                            	<div class="inputList">
	                                            		<label class="col-md-3">${alarm_descriptionType}
		                                                    <span class="required"> * </span>
		                                                </label>
		                                                <div class="col-md-7" style="margin-left: -14px;">
		                                                	<input type="text" id="descriptionType" name="descriptionType" disabled data-required="1" class="form-control" value="${alarm.descriptionType}"></input>
		                                            	</div><!-- 报警设备 -->
	                                            	</div>
	                                            	
	                                            	<div class="inputList">
	                                            		<label class="col-md-3">${curve_pointname}
		                                                    <span class="required"> * </span>
		                                                </label>
		                                                <div class="col-md-7" style="margin-left: -14px;">
		                                                	<input type="text"  name="measurePointType" disabled data-required="1" class="form-control" value="${alarm.measurePointDiscription}"></input>
		                                            	</div>
	                                            	</div><!-- 测点名称 -->
                                            	</div>
                                            	<div class="clearfix"></div>
                                            	<div class="inputList">
                                            		<label class="control-label pull-left">${alarm_alarmMessage}<span class="required"> * </span></label>
                                            		<div class="col-md-9 messageInfo">
	                                                     <textarea id="alarmMessage" name="alarmMessage" rows="8" class="form-control" disabled>${alarm.alarmMessage}</textarea>
                                                    	 <div id="editor_error"> </div>
	                                                </div>
                                            	</div>
                                            	<div class="inputList">
                                            		<label class="control-label pull-left">${alarm_handling}<span class="required"> * </span></label>
                                            		<div class="col-md-9 messageInfo">
	                                                     <textarea id="handling" name="handling" rows="3"  class="form-control">${alarm.handling}</textarea>
                                                    	 <div id="editor_error"> </div>
	                                                </div>
                                            	</div>
                                            	<div class="inputList">
                                            		<label class="control-label pull-left">${equipment_mark}<span class="required"> * </span></label>
                                            		<div class="col-md-9 messageInfo">
	                                                     <textarea id="remaker" name="remaker" rows="3"  class="form-control">${alarm.remaker}</textarea>
                                                    	 <div id="editor_error"> </div>
	                                                </div>
                                            	</div>
                                            	<div class="inputList">
                                            		<label class="control-label pull-left">${alarm_corporation}<span class="required"> * </span></label>
                                            		<div class="col-md-9 messageInfo">
	                                                      <input type="text" id="corporation" name="corporation" data-required="1" class="form-control" value="${alarm.corporation}"></input>
                                                    	 <div id="editor_error"> </div>
	                                                </div>
                                            	</div>
                                            </div>
                                        </div>
                                        <%-- <div class="form-body overflow-h">
                                            <div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button>  ${alert_error} </div>
                                            <div class="alert alert-success display-hide">
                                                <button class="close" data-close="alert"></button> ${alert_success} </div>
                                            <div class="form-group col-md-5">
                                           		<div class="col-md-4  pull-right" style="padding-left:22px;">
                                                	<input type="text" id="id" name="id" disabled data-required="1" class="form-control" value="${alarm.id}"></input>
                                            	</div>
                                          		 <label class="control-label col-md-3 pull-right paddingR10">${alarm_id}
                                                    <span class="required"> * </span>
                                                </label>
												<input id="id" name="id" type="hidden" value="${logs.data.id}">
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label class="control-label col-md-3">${log_company}
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                   <input type="text" id="company" name="company" disabled data-required="1" class="form-control" value="${power.investFirmName}"></input>
                                            	</div>
                                            </div>
                                            <div class="form-group col-md-5 ">
                                           		<div class="col-md-4  pull-right"  style="padding-left:22px;">
                                                	<input type="text" id="alarmGrade" name="alarmGrade" disabled data-required="1" class="form-control" value="${alarm.alarmGrade==1?'I':alarm.alarmGrade==2?'II':alarm.alarmGrade==3?'III':'III'}"/>
                                            	</div>
                                          		 <label class="control-label col-md-3 pull-right paddingR10">${alarm_alarmGrade}
                                                    <span class="required"> * </span>
                                                </label>
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label class="control-label col-md-3">${alarm_alarmtime}
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                	<input type="text" id="eventTime" name="eventTime" disabled data-required="1" class="form-control" value="<fmt:formatDate value='${alarm.eventTime}' pattern='yyyy-MM-dd HH:mm:ss'/>"></input>
                                            	</div>
                                            </div>
                                            <div class="form-group col-md-5">
                                                <div class="col-md-4 pull-right"  style="padding-left:22px;">
                                                     <input type="text" id="powerStationName" name="powerStationName" disabled data-required="1" class="form-control" value="${power.powerStationName}"></input>
                                                </div>
                                                <label class="control-label col-md-3 pull-right paddingR10">${alarm_powerStationName}
                                                    <span class="required"> * </span>
                                                </label>
                                            </div>
                                             <div class="form-group col-md-6">
                                          		 <label class="control-label col-md-3">${alarm_descriptionType}
                                                    <span class="required"> * </span>
                                                </label>
                                           		<div class="col-md-4">
                                                	<input type="text" id="descriptionType" name="descriptionType" disabled data-required="1" class="form-control" value="${alarm.descriptionType}"></input>
                                            	</div>
                                            </div>
                                            <div class="form-group col-md-5">
                                                <div class="col-md-4 pull-right"  style="padding-left:22px;">
                                                    <input type="text" id="equipmentcontainerName" name="equipmentcontainerName" disabled data-required="1" class="form-control" value="${alarm.equipmentcontainerName}"></input>
                                            	 </div>
                                                <label class="control-label col-md-3 pull-right paddingR10">${iv_equipment_number}
                                                    <span class="required"> * </span>
                                                </label>
                                            </div>
                                            <div class="form-group col-md-6">
                                          		 <label class="control-label col-md-3">${curve_pointname}
                                                    <span class="required"> * </span>
                                                </label>
                                           		<div class="col-md-4">
                                                	<input type="text"  name="measurePointType" disabled data-required="1" class="form-control" value="${alarm.measurePointDiscription}"></input>
                                            	</div>
                                            </div>
                                            <div class="form-group col-md-8-this">
                                                <div class="col-md-7-this pull-right">
                                                    <textarea id="alarmMessage" name="alarmMessage" rows="8"  class="form-control" disabled>${alarm.alarmMessage}</textarea>
                                                    <div id="editor_error"> </div>
                                                </div>
                                                <label class="control-label col-md-3 pull-right">${alarm_alarmMessage}<span class="required"> * </span></label>
                                            </div>
                                            <div class="form-group col-md-8-this">
                                                <div class="col-md-7-this pull-right">
                                                    <textarea id="handling" name="handling" rows="3"  class="form-control"></textarea>
                                                    <div id="editor_error"> </div>
                                                </div>
                                                <label class="control-label col-md-3 pull-right">${alarm_handling} <span class="required"> * </span></label>
                                            </div>
                                            <div class="form-group col-md-8-this">
                                                <div class="col-md-7-this pull-right">
                                                     <textarea id="remaker" name="remaker" rows="3"  class="form-control"></textarea>
                                                    <div id="editor_error"> </div>
                                                </div>
                                                <label class="control-label col-md-3 pull-right">${equipment_mark} <span class="required"> * </span></label>
                                            </div>
                                            <div class="form-group col-md-8-this">
                                                <div class="col-md-7-this pull-right">
                                                      <input type="text" id="corporation" name="corporation" data-required="1" class="form-control"></input>
                                                    <div id="editor_error"> </div>
                                                </div>
                                                <label class="control-label col-md-3 pull-right">${alarm_corporation} <span class="required"> * </span></label>
                                            </div>
                                        </div> --%> 
                                        <div class="form-actions">
                                            <div class="textCenter">
                                                 <button class="btn btn-success" type="submit"><i18n:message name="submit" /></button>
                                                 <button type="button" class="btn default" onClick="window.location.href='${basePath}alarmPage/actualAlarm.do?a=1<c:if test="${(admin!=null && admin==true) }">&admin=1</c:if>&powerStationId=${alarm.powerStationId}'" ><i18n:message name="reback" /></button>
                                              
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
            
        <%@ include file="/page/common/pubJs.jsp" %>
        <!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${cdnPath}global/plugins/select2/js/select2.full.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/ckeditor/ckeditor.js?version=20170817024514" type='text/javascript'></script>

<script src="${basePath}/assets/pages/scripts/alarm/alarm-dispose.js?version=20170817024514" type='text/javascript'></script>
        <!-- END PAGE LEVEL SCRIPTS -->
      <script type="text/javascript">
      	$(function(){
     		 setSelectMenu("menualarmmanager","alarmmanager_alarmreal");
     	})
      </script>
    </body>

</html>
