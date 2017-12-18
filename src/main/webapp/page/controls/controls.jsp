<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/page/common/pub.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta name="renderer" content="webkit">
	    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<title><i18n:message name="menu_axis" /></title>
		<!-- start css style by pjs-->
		<%@ include file="/page/common/pubCss.jsp" %>
		<link href="${basePath}assets/pages/css/ynz/subsiteIndex.css?version=20170817024514" rel='stylesheet' type='text/css'  />
		<link href="${basePath}assets/pages/css/ynz/controls.css?version=20170817024514" rel='stylesheet' type='text/css'  />
		<!--end css style by pjs-->
		
		<script type="text/javascript">
 
			window.ynz.trackList=<%= com.yunengzhe.commons.util.JsonUtil.beanToJson(request.getAttribute("trackList")) %>;
		 
			</script>
	</head>
		
	
    <body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
        <!-- BEGIN HEADER -->
         <%@ include file="/page/common/companyHead.jsp" %>
        <!-- END HEADER -->
        <!-- BEGIN HEADER & CONTENT DIVIDER -->
        <div class="clearfix"> </div>
        <!-- END HEADER & CONTENT DIVIDER -->
        <!-- BEGIN CONTAINER -->
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
                                <a href="${basePath}index/${powerStationId}.do" ><i18n:message name="index_page" /></a>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                              
                                <span class="blueFont">${menu_axis}</span>
                            </li>
                        </ul>
                       
                    </div>
                    <!-- END PAGE BAR -->
                    <div class="row">
                    <c:forEach items="${trackList}" var="data">
                            	<c:if test="${data.model==1}">
	                            	<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 track-body">
			                    		<div class="col-md-12 relaTive track-content track-content3">
			                    			<dl>
			                    				<dd class="track-title">${axis_model }${data.serialNumber}</dd>
			                    				<dt><img class="singleImg" alt="" src="${basePath}assets/pages/img/ynz/doubleAxis.png"></dt>
			                    				<dd><span>${axis_fangwei }</span><em id="qingjiaoValue_${data.id}" class="number">${data.fangvalue}</em></dd>
			                    			</dl>
			                    		</div>
			                    		
			                    	</div>
                            	</c:if>
                            	<c:if test="${data.model==2}">
                            	<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12 track-body">
		                    		<div class="col-md-12 relaTive track-content">
			                    		<div class="col-md-6">
			                    			<dl>
			                    				<dd class="track-title"> ${axis_control }${data.name} </dd>
			                    				<dt><img alt="" src="${basePath}assets/pages/img/ynz/singleAxis.png"></dt>
			                    				<dd><span>${axis_qingjiao } </span><em id="qingjiaoValue_${data.id}" class="number">${data.qinvalue}</em></dd>
			                    			</dl>
			                    		</div>
			                    		<div class="col-md-6 relaTive">
			                    			<dl>
			                    				<dd class="track-title">${axis_model }${data.serialNumber} </dd>
			                    				<dt><img alt="" src="${basePath}assets/pages/img/ynz/doubleAxis.png"></dt>
			                    				<dd><span>${axis_fangwei } </span><em id="fangweijiaoValue_${data.id}" class="number" >${data.fangvalue}</em></dd>
			                    			</dl>
			                    		</div>
			                    	</div>
	                    		</div>
	                            	</c:if>
                            </c:forEach>
                </div>
                <div class="portlet-body form ">
	                  <form id="form_1" class="form-inline">
                                <label class="control-label">${log_select_equipment}
			                      <span class="required"> * </span>
			                 		</label>
						              <div class="input-group input-large date-picker input-daterange">
							                 <select class="form-control select5me" id="equipmentcontainerId" name="equipmentcontainerId">
							                  <c:forEach items="${trackList}" var="data">
							                  		  <option value="${data.id}" name="${data.model}">${data.name}</option>
							                  </c:forEach>
							                 </select>
						             	 </div>&nbsp;&nbsp;
	                          		<label class="control-label">${inverter_search_time}
	                                      <span class="required"> * </span>  
	                                      </label>
	                                      <div class="input-group input-large date-picker input-daterange" data-date="2017-06-28" data-date-format="yyyy-mm-dd" >
	                                              <input id="start" type="text" class="form-control" id="startTime" name="startTime">
	                                              <span class="input-group-addon"> ${inverter_to} </span>
	                                              <input id="end" type="text" class="form-control" id="endTime" name="endTime"> 
	                                       </div>&nbsp;&nbsp; 
                                                     <button type="button" onclick="submitAddPoint();" class="btn btn-success">${inverter_search}</button>
                                         
                                    </form>
                                           
                                </div>
		                        <div id="echarts_line" style="height:250px;"></div> 
		                        <div>
			                    	<table id="sample_1" class="greenTable-ynz table table-bordered table-striped table-condensed flip-content" id="sample_1">
	                                 </table> 
		                        </div>
            </div>
            </div>         
        <!-- END CONTAINER -->
	</body>
	<!-- BEGIN CORE PLUGINS -->
     <%@ include file="/page/common/pubJs.jsp" %>
     <!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${cdnPath}/global/scripts/datatable.js" type="text/javascript"></script>
<script src="${cdnPath}/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
<script src="${cdnPath}/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/select2/js/select2.full.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/ckeditor/ckeditor.js?version=20170817024514" type='text/javascript'></script>
<script src="${basePath}assets/pages/scripts/echarts-all.js?version=20170817024514" type='text/javascript'></script>
<script src="${cdnPath}global/plugins/echarts/echarts.js?version=20170817024514" type='text/javascript'></script>
<script src="${basePath}/assets/pages/scripts/control/control.js?version=20170817024514" type='text/javascript'></script>
           <!-- BEGIN PAGE LEVEL SCRIPTS -->
           <script type="text/javascript">
           	var equipmenttypeid = ${trackList[0].equipmenttypeid};
           </script>
</html>
