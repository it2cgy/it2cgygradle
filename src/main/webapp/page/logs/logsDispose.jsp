<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/page/common/pub.jsp" %>

<!DOCTYPE html>
<html lang="en">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->

    <head>
        <meta charset="utf-8" />
        <title>${log_editlogtitle}</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" /> 
         <%@ include file="/page/common/pubCss.jsp" %>
        <style>
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
         	.messageSingle label{
         		width:60px;
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
         <%@ include file="/page/common/companyAdminMenu.jsp" %>
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
                                <span class="blueFont">${log_editcaption}</span>
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
                                        <span class="caption-subject font-dark sbold uppercase">${log_editcaption}</span>
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
	                                            		<label class="col-md-3">     ${log_topic}
		                                                    <span class="required"> * </span>
		                                                </label>
		                                                <div class="col-md-7">
		                                                	<input type="text" id="topic" name="topic" disabled data-required="1" class="form-control" value="${logs.data.topic}"></input>
		                                            	</div>
		                                            	<input id="id" name="id" type="hidden" value="${logs.data.id}">
	                                            	</div>
	                                            	<div class="inputList">
	                                            		<label class="col-md-3">     ${user_createuser}
		                                                    <span class="required"> * </span>
		                                                </label>
		                                                <div class="col-md-7">
		                                                	<input type="text" id="topic" name="topic" disabled data-required="1" class="form-control" value="${logs.data.userName}"></input>
		                                            	</div>
	                                            	</div>
	                                            	<div class="inputList">
	                                            		<label class="col-md-3">${log_powerStation}
		                                                    <span class="required"> * </span>
		                                                </label>
		                                                <div class="col-md-7">
		                                                	<select class="form-control select3me" id="powerStationId" name="powerStationId" disabled>
		                                                        <option  value="">${log_select_powerStation}</option>
		                                                        <c:forEach items="${powerStations}" var="power">
			                                                      <c:choose>
				                                                  	<c:when test="${logs.data.powerStationId==power.id}">
				                                                  	 	<option value="${power.id}" selected="selected">${power.name}</option>
				                                                  	<input name="powername" hidden="hidden" id="powername" value="${power.name}"/>
				                                                  	</c:when>
				                                                  	<c:otherwise>
				                                                        <option value="${power.id}">${power.name}</option>
				                                                  	</c:otherwise>
				                                                  </c:choose>
		                                                        </c:forEach>
		                                                    </select>
		                                            	</div>
	                                            	</div>
	                                            	<div class="inputList">
	                                            		<label class="col-md-3">${log_equipmentId}
		                                                    <span class="required"> * </span>
		                                                </label>
		                                                <div class="col-md-7">
		                                                	<select class="form-control select3me" id="equipmentId" name="equipmentId" disabled>
		                                                        <option value="">${log_select_equipment}</option>
		                                                        <option value="0"  ${logs.data.equipmentType==0?'selected':''}>${curve_powerStationId }</option>
		                                                        <option value="3" ${logs.data.equipmentType==3?'selected':''}>${inverter_inverter}</option>
		                                                        <option value="5" ${logs.data.equipmentType==5?'selected':''}>${log_meter}</option>
		                                                        <option value="6" ${logs.data.equipmentType==6?'selected':''}>${log_gridark}</option>
		                                                        <option value="8" ${logs.data.equipmentType==8?'selected':''}>${log_weather }</option>
		                                                        <option value="9" ${logs.data.equipmentType==9?'selected':''}>${log_axis}</option>
		                                                        <option value="10" ${logs.data.equipmentType==10?'selected':''}>${log_IV }</option>
		                                                    </select>
		                                            	</div>
	                                            	</div>
	                                            </div><!-- 左侧end -->
	                                            <div class="form-group overflow-h" style="width:50%;float:left;">
                                            		<div class="inputList">
	                                            		<label class="col-md-3">${log_category}
		                                                    <span class="required"> * </span>
		                                                </label>
		                                                <div class="col-md-7"  style="margin-left: -14px;">
		                                                	<select class="form-control select3me" id="categoryId" name="categoryId" disabled>
		                                                     	<c:forEach items="${category}" var="category">
		                                                     		<c:choose>
					                                                  	<c:when test="${logs.data.categoryId==category.id}">
				                                                 			<option class="categoryoption" value="${category.id}" selected="selected">${category.category}</option>
					                                                  	</c:when>
				                                              		</c:choose>
			                                                 	</c:forEach>
		                                                 	</select>
		                                            	</div>
	                                            	</div>
	                                            	<div class="inputList">
	                                            		<label class="col-md-3">${user_createtime}
		                                                    <span class="required"> * </span>
		                                                </label>
		                                                <div class="col-md-7"  style="margin-left: -14px;">
		                                                	<input type="text" id="createTime" name="createTime" disabled data-required="1" class="form-control" value="${logs.data.createTime}"></input>
		                                            	</div>
	                                            	</div>
                                            		
	                                            	<div class="inputList">
	                                            		<label class="col-md-3">${log_company }
		                                                    <span class="required"> * </span>
		                                                </label>
		                                                <div class="col-md-7" style="margin-left: -14px;">
		                                                	<input type="text" id="kaleidoscope" name="kaleidoscope" disabled data-required="1" class="form-control" value="${logs.data.kaleidoscope}"></input>
		                                                	<div id="editor_error"> </div>
		                                            	</div>
	                                            	</div>
	                                            	<div class="inputList">
	                                            		<label class="col-md-3">${log_equipment }
		                                                    <span class="required"> * </span>
		                                                </label>
		                                                <div class="col-md-7" style="margin-left: -14px;">
		                                                	<select class="form-control select3me" id="equipment" name="equipment" disabled>
		                                                        <option value="">${log_select_equipmentnumber}</option>
		                                                    </select>
		                                            	</div>
	                                            	</div>
                                            	</div>
                                            	<div class="clearfix"></div>
                                            	<div class="messageSingle">
	                                            	<div class="inputList">
	                                            		<label class="control-label pull-left">${log_markdown}<span class="required"> * </span></label>
	                                            		<div class="col-md-9 messageInfo">
		                                                     <textarea noresize id="description" name="description" rows="8"  class="form-control" disabled>${logs.data.description}</textarea>
	                                                    	 <div id="editor_error"> </div>
		                                                </div>
	                                            	</div>
	                                            	<div class="inputList">
	                                            		<label class="control-label pull-left">${alarm_handling}<span class="required"> * </span></label>
	                                            		<div class="col-md-9 messageInfo">
		                                                     <input  name="handler" hidden="hidden" id="handler" value="${logs.data.handler}">
		                                                     <textarea noresize id="handling" name="handling" rows="3"  class="form-control">${logs.data.handling}</textarea>
		                                                     <div id="editor_error"> </div>
		                                                </div>
	                                            	</div>
	                                            	<div class="inputList">
	                                            		<label class="control-label pull-left">${equipment_mark} <span class="required"> * </span></label>
	                                            		<div class="col-md-9 messageInfo">
		                                                     <textarea noresize id="remark" name="remark" rows="3"  class="form-control" >${logs.data.remark}</textarea>
	                                                    	 <div id="editor_error"> </div>
		                                                </div>
	                                            	</div>
	                                            	<div class="inputList">
	                                            		<label class="control-label pull-left">${alarm_corporation}<span class="required"> * </span></label>
	                                            		<div class="col-md-9 messageInfo">
		                                                     <input type="text" id="corporation" name="corporation" data-required="1" class="form-control" value="${logs.data.corporation}"></input>
	                                                    	 <div id="editor_error"> </div>
		                                                </div>
	                                            	</div>
	                                            	<div class="inputList">
	                                            		<label class="control-label pull-left">${alarm_responsible}<span class="required"> * </span></label>
	                                            		<div class="col-md-9 messageInfo">
		                                                      <input type="text" id="responsible" name="responsible" data-required="1" class="form-control" value="${logs.data.responsible}"></input>
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
                                           		<div class="col-md-4  pull-right">
                                                	<input type="text" id="topic" name="topic" disabled data-required="1" class="form-control" value="${logs.data.topic}"></input>
                                            	</div>
                                          		 <label class="control-label col-md-3 pull-right" style="padding-right:9px;">${log_topic}
                                                    <span class="required"> * </span>
                                                </label>
												<input id="id" name="id" type="hidden" value="${logs.data.id}">
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label class="control-label col-md-3">${log_category}
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                    <select class="form-control select3me" id="categoryId" name="categoryId" disabled>
                                                     <c:forEach items="${category}" var="category">
                                                     <c:choose>
		                                                  	<c:when test="${logs.data.categoryId==category.id}">
	                                                 			<option class="categoryoption" value="${category.id}" selected="selected">${category.category}</option>
		                                                  	</c:when>
		                                              </c:choose>
	                                                 </c:forEach>
                                                 </select>
                                            	</div>
                                            </div>
                                            <div class="form-group col-md-5">
                                           		<div class="col-md-4  pull-right">
                                                	<input type="text" id="topic" name="topic" disabled data-required="1" class="form-control" value="${logs.data.userName}"></input>
                                            	</div>
                                          		 <label class="control-label col-md-3 pull-right" style="padding-right:9px;">${user_createuser}
                                                    <span class="required"> * </span>
                                                </label>
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label class="control-label col-md-3">${user_createtime}
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                	<input type="text" id="createTime" name="createTime" disabled data-required="1" class="form-control" value="${logs.data.createTime}"></input>
                                            	</div>
                                            </div>
                                           <!-- 按钮start -->
	                                           <button type="button" class="btn btn-circle btn-success" data-target="#stack1" data-toggle="modal">${log_categorymanage}
<!--                                    			 	   <i class="fa fa-plus"></i> -->
<!--                                				   </button> -->
                              				   <!-- 按钮end -->
                                            <div class="form-group col-md-5">
                                                <div class="col-md-4 pull-right">
                                                    <select class="form-control select3me" id="powerStationId" name="powerStationId" disabled>
                                                        <option  value="">${log_select_powerStation}</option>
                                                        <c:forEach items="${powerStations}" var="power">
	                                                      <c:choose>
		                                                  	<c:when test="${logs.data.powerStationId==power.id}">
		                                                  	 	<option value="${power.id}" selected="selected">${power.name}</option>
		                                                  	</c:when>
		                                                  	<c:otherwise>
		                                                        <option value="${power.id}">${power.name}</option>
		                                                  	</c:otherwise>
		                                                  </c:choose>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <label class="control-label col-md-3 pull-right" style="padding-right:9px;">${log_powerStation}
                                                    <span class="required"> * </span>
                                                </label>
                                            </div>
                                             <div class="form-group col-md-6">
                                          		 <label class="control-label col-md-3">${log_company }
                                                    <span class="required"> * </span>
                                                </label>
                                           		<div class="col-md-4">
                                                	<input type="text" id="kaleidoscope" name="kaleidoscope" disabled data-required="1" class="form-control" value="${logs.data.kaleidoscope}"></input>
                                            	</div>
                                            </div>
                                             <div class="form-group col-md-5">
                                                <div class="col-md-4 pull-right">
                                                    <select class="form-control select3me" id="equipmentId" name="equipmentId" disabled>
                                                        <option value="">${log_select_equipment}</option>
                                                        <option value="3" ${logs.data.equipmentType==3?'selected':''}>${inverter_inverter}</option>
                                                        <option value="5" ${logs.data.equipmentType==5?'selected':''}>${log_meter}</option>
                                                        <option value="6" ${logs.data.equipmentType==6?'selected':''}>${log_gridark}</option>
                                                        <option value="8" ${logs.data.equipmentType==8?'selected':''}>${log_weather }</option>
                                                        <option value="9" ${logs.data.equipmentType==9?'selected':''}>${log_axis}</option>
                                                        <option value="10" ${logs.data.equipmentType==10?'selected':''}>${log_IV }</option>
                                                    </select>
                                                </div>
                                                <label class="control-label col-md-3 pull-right" style="padding-right:9px;">${log_equipmentId}
                                                    <span class="required"> * </span>
                                                </label>
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label class="control-label col-md-3" >${log_equipment }
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                    <select class="form-control select3me" id="equipment" name="equipment" disabled>
                                                        <option value="">${log_select_equipmentnumber}</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group col-md-8">
                                                <div class="col-md-7 pull-right" style="padding:0 28px 0 7px;">
                                                    <textarea id="description" name="description" rows="8"  class="form-control" disabled>${logs.data.description}</textarea>
                                                    <div id="editor_error"> </div>
                                                </div>
                                                <label class="control-label col-md-3 pull-right">${log_markdown} <span class="required"> * </span></label>
                                            </div>
                                            <div class="form-group col-md-8">
                                                <div class="col-md-7 pull-right" style="padding:0 28px 0 7px;">
                                                    <textarea id="handling" name="handling" rows="3"  class="form-control"></textarea>
                                                    <div id="editor_error"> </div>
                                                </div>
                                                <label class="control-label col-md-3 pull-right">${alarm_handling}  <span class="required"> * </span></label>
                                            </div>
                                            <div class="form-group col-md-8">
                                                <div class="col-md-7 pull-right" style="padding:0 28px 0 7px;">
                                                     <textarea id="remark" name="remark" rows="3"  class="form-control"></textarea>
                                                    <div id="editor_error"> </div>
                                                </div>
                                                <label class="control-label col-md-3 pull-right">${equipment_mark}<span class="required"> * </span></label>
                                            </div>
                                            <div class="form-group col-md-8">
                                                <div class="col-md-7 pull-right" style="padding:0 28px 0 7px;">
                                                      <input type="text" id="corporation" name="corporation" data-required="1" class="form-control"></input>
                                                    <div id="editor_error"> </div>
                                                </div>
                                                <label class="control-label col-md-3 pull-right">${alarm_corporation}<span class="required"> * </span></label>
                                            </div>
                                            <div class="form-group col-md-8">
                                                <div class="col-md-7 pull-right" style="padding:0 28px 0 7px;">
                                                       <input type="text" id="responsible" name="responsible" data-required="1" class="form-control"></input>
                                                    <div id="editor_error"> </div>
                                                </div>
                                                <label class="control-label col-md-3 pull-right">${alarm_responsible} <span class="required"> * </span></label>
                                            </div>
                                        </div> --%>
                                        <div class="form-actions">
                                            <div class="row">
                                                <div class="col-md-offset-3 col-md-9">
                                                  	    <button class="btn btn-success" type="submit"><i18n:message name="submit" /></button>
	                                                    <button type="button" class="btn default" onClick="window.location.href='${basePath}logsPage/toLogsList.do?a=1<c:if test="${(admin!=null && admin==true) }">&admin=1</c:if><c:if test="${(powerStationId!=null && powerStationId>0) }">&powerStationId=${powerStationId}</c:if>'" ><i18n:message name="cancel" /></button>
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
             <div id="stack1" class="modal fade" data-focus-on="input:first" >
                <div class="modal-dialog">
              			   <div class="modal-content">
                   <div class="modal-header">
                       <button id="stack1Close" type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                       <h4 class="modal-title" style="margin-top:35px;">${log_category}</h4>
                        <!-- 测点按钮start -->
                             <button type="button" style="text-align:right;margin:-30px 425px;" class="btn btn-circle btn-success" data-target="#stack2" data-toggle="modal">${log_addcategory}
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
                               <button id="stack1Close" type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
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
                                <button type="button" data-dismiss="modal" class="btn btn-outline dark"><i18n:message name="cancel"/></button>
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
<script src="${basePath}/assets/pages/scripts/logs/logs-dispose.js?version=20170817024514" type='text/javascript'></script>
        <!-- END PAGE LEVEL SCRIPTS -->
      <script type="text/javascript">
      	var equipmentlogId = ${logs.data.equipmentId};
      	var createTime = $("#createTime").val();
      	if(createTime!=null&&createTime!=''){
      		$("#createTime").val(timeUtil.timeToString(createTime,"yyyy-MM-dd"));
      	}
      </script>
    </body>

</html>
