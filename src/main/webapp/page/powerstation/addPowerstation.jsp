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
        <title><i18n:message name="powerstation_addtitle" /></title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" /> 
		<%@ include file="/page/common/pubCss.jsp" %>
		<link href="${cdnPath}global/plugins/bootstrap-daterangepicker/daterangepicker.min.css" rel="stylesheet" type="text/css" />
		<link href="${cdnPath}global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />
		<link href="${cdnPath}global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css" rel="stylesheet" type="text/css" />
		<link href="${cdnPath}global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css" />
         <script>
	         var provinceName = "${powerstation_province}";
	         var cityName = "${powerstation_city}";
       		 var countyName = "${powerstation_county}";
         </script>
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
                               <c:if test="${redirectStationId!=null&&redirectStationId!=''}">
					            <a href="${basePath}powerstation/powerstationList.do?powerStationId=${redirectStationId}" ><i18n:message name="menu_stationlist" /></a>
					            </c:if>
					            <c:if test="${redirectStationId==null||redirectStationId==''}">
                                <a href="${basePath}powerstation/powerstationList.do" ><i18n:message name="menu_stationlist" /></a>
					            </c:if>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <span>${powerstation_manage}</span>
                            </li>
                        </ul> 
                    </div>
                    <!-- END PAGE BAR -->
                    
                     
                   
                    <div class="row">
                        <div class="col-md-12">
                            <!-- BEGIN VALIDATION STATES-->
                                    <form id="powerStationForm" class="form-horizontal">
                            <div class="portlet light portlet-fit portlet-form bordered">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="icon-settings font-dark"></i>
                                       <span class="caption-subject font-green bold uppercase">${powerstation_createpowerstation}</span>
                                    </div>                                    
                                </div>
                                <div class="portlet-body">
                                        <div class="form-body">
                                            <div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button> ${alert_error}</div>
                                            <div class="alert alert-success display-hide">
                                                <button class="close" data-close="alert"></button> ${alert_success} </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">${powerstation_name}&nbsp;&nbsp;
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                    <input type="text" name="powerStationName" id="powerStationName" data-required="1" class="form-control" /> </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">${powerstation_producetime}&nbsp;&nbsp;
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
	                                                <div class="input-group input-large date-picker input-daterange" data-date="2017-06-28" data-date-format="yyyy-mm-dd" style="width: 100%!important;">
	                                                  <input type="text" class="form-control" id="startProduceTime" name="startProduceTime">
	                                                 </div>
                                                 </div>
                                            </div>
                                            <div class="form-group ">
                                                <label class="control-label col-md-3">${powerstation_number}&nbsp;&nbsp;
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                    <input type="text" name="powerstationCode" id="powerstationCode" data-required="1" class="form-control" /> </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">${powerstation_capacity}(kW)&nbsp;&nbsp;
													<span class="required"> * </span></label>
                                                <div class="col-md-4 form-inline">                                               		
                                                    <input name="installCapacity" id="installCapacity" type="text" class="form-control pull-left col-md-10" style="width:100%;"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">${powerstation_lng}(°)&nbsp;&nbsp;
													<span class="required"> * </span></label>
                                                <div class="col-md-4 form-inline">
                                                    <input name="lng" id="lng" type="text" class="form-control pull-left col-md-10" style="width:100%;"/></div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">${powerstation_lat}(°)&nbsp;&nbsp;
													<span class="required"> * </span></label>
                                                <div class="col-md-4 form-inline">
                                                    <input name="lat" id="lat" type="text" class="form-control pull-left col-md-10" style="width:100%;"/></div>
                                            </div>
<!--                                             <div class="form-group"> -->
<%--                                                 <label class="control-label col-md-3">${powerstation_address}&nbsp;&nbsp; --%>
<!-- 													<span class="required"> * </span></label> -->
<!--                                                 <div class="col-md-4 col-sm-12 form-inline"> -->
<%-- 	                                                	<select class="form-control col-md-3 col-sm-3" style="margin-right:5px;" id="province" name="province" placehold="${powerstation_province}"></select> --%>
<%-- 	                                                	<select class="form-control col-md-3 col-sm-3" style="margin-right:5px;" id="city" name="city" placehold="${powerstation_city}"></select> --%>
<%-- 	                                                	<select class="form-control col-md-3 col-sm-3" id="county" name="county" placehold="${powerstation_county}"></select> --%>
<%--                                                 		<input name="occupation" style="width:100%;margin-top:10px;" type="text" class="col-md-12 form-control" placehold="${powerstation_concrete_address}"/> --%>
<!--                                                 </div> -->
<!--                                             </div> -->
                                            
                                             <div class="form-group">
                                                <label class="control-label col-md-3">${powerstation_address}&nbsp;&nbsp;
													<span class="required"> * </span></label>
                                                <div class="col-md-4 form-inline" style="margin-left: -15px;">
                                                	<div class="col-md-2"><select id="province" name="province"  class="form-control pull-left col-md-2" placehold="${powerstation_province}"></select></div>
                                                	<div class="col-md-2"><select id="city" name="city" class="form-control pull-left col-md-2" placehold="${powerstation_city}"></select></div>
                                                	<div class="col-md-2"><select id="county" name="county" class="form-control pull-left col-md-2" placehold="${powerstation_county}"></select></div>
                                                	<div class="col-md-6"><input name="occupation" name="occupation" value="" type="text" placehold="${powerstation_concrete_address}" class="form-control pull-left"/></div>
                                                </div>
                                            </div>
                                            
                                            
                                             <div class="form-group">
                                                <label class="control-label col-md-3">${powerstation_manager}&nbsp;&nbsp;
                                                	<span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                    <input name="investFirmContactName" id="investFirmContactName" type="text" class="form-control" /> </div>
                                            </div>
                                             <div class="form-group">
                                                <label class="control-label col-md-3">${powerstation_investment}&nbsp;&nbsp;
                                                	<span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                    <input name="investFirmName" id="investFirmName" type="text" class="form-control" /> </div>
                                            </div>
                                         	   <!-- 视频监控地址 -->
                                             <div class="form-group">
                                                <label class="control-label col-md-3">${video_surve}&nbsp;&nbsp;
                                                	<span class="required">   </span>
                                                </label>
                                                <div class="col-md-4">
                                                    <input name="monitoring" id="monitoring" type="text" class="form-control" placeholder="例如: http://v.baidu.com/"/> </div>
                                            </div>
                                            </div>
                                            <!-- 电站描述信息 -->
                                            <div class="form-group">
                                                 <label class="control-label col-md-3">${powerstation_description}&nbsp;&nbsp;
                                                	<span class="required"> </span>
                                                </label>
		                                    	 <div class="col-md-9">
		                                           <textarea name="description" id="description" data-provide="markdown" maxlength="512" rows="10" data-error-container="#editor_error" style="margin: 0 0 20px; width: 663px; height: 157px;resize:none" placeholder=""></textarea>
		                                           <div id="editor_error"> </div>
			                                    </div>
		                                    </div>
                                           
                                            <!-- 电站图片 -->
                                            <div class="form-group">
                                                 <label class="control-label col-md-3">${powerstation_uploadPic}&nbsp;&nbsp;
                                                	<span class="required"> </span>
                                                </label>
		                                    	 <div class="col-md-9" id="uploadDiv">
		                                          <a class='fileBtn borderRadius5' onclick="fileUpload()" ><i class='fa fa-camera btnCamera' ></i></a>
		                                           <div id="editor_error"> </div>
			                                    </div>
			                                    <div id="fileDiv" style="display:none;width:69px;height:60px;position:relative;float:left;margin-right:15px;">
													<img name="faultImg" id="faultImg" src="" style="width:169px;height:160px;margin-left:10px"/>
													<i class="fa fa-times-circle" style="position:absolute;color:red;right:-117px;top:-10px;" id="" onclick="delFile(this)"></i>
												</div>
		                                    </div>
                                            </div> 
                                        </div>
                                        <div class="form-actions">
                                            <div class="row">
                                                <div class="col-md-offset-3 col-md-9" style="margin-top:70px;clear:both;">
                                                    <button type="submit" id="submit" class="btn green">${submit }</button>
                                                    <a href="${basePath}powerstation/powerstationList?a=1<c:if test="${(admin!=null && admin==true) }">&admin=1</c:if><c:if test="${(powerStationId!=null && powerStationId>0) }">&powerStationId=${powerStationId}</c:if>"><button type="button"  class="btn default" >${cancel }</button></a>
                                             </div>
                                            </div>
                                        </div>
                                    <!-- END FORM-->
                                    </form>
                                </div>
                                <!-- END VALIDATION STATES-->
                            </div>
                        </div>
                    </div>
                </div>
                <!-- END CONTENT BODY -->
            <!-- END CONTENT -->
            <!-- BEGIN QUICK SIDEBAR -->
            <form id='fileform' target='framFile' method='post' action='${uploadurl}' enctype='multipart/form-data' hidden="hidden">
				<input type="file" id="file" name="file" accept="image/jpg,image/png,image/jpeg,image/jpeg" onchange="uploadFile(this)"  />
                <input type='text' hidden='hidden' name='fileDir' id='fileDir' value='longieb/power'/>
            </form>
             
            <!-- END QUICK SIDEBAR -->
        <!-- END CONTAINER -->
        
        <!-- BEGIN CORE PLUGINS -->
        <%@ include file="/page/common/pubJs.jsp" %>
        <!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${cdnPath}global/plugins/select2/js/select2.full.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/ckeditor/ckeditor.js?version=20170817024514" type='text/javascript'></script>
<script src="${cdnPath}global/plugins/moment.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/bootstrap-daterangepicker/daterangepicker.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js" type="text/javascript"></script>
<script src="${cdnPath}/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
<%--         <script src="${basePath}/assets/global/plugins/bootstrap-markdown/lib/markdown.js" type="text/javascript"></script> --%>
<%--         <script src="${basePath}/assets/global/plugins/bootstrap-markdown/js/bootstrap-markdown.js" type="text/javascript"></script> --%>
        <!-- END PAGE LEVEL PLUGINS -->
<script src="${basePath}/assets/pages/scripts/ynz/City-Country.js?version=20170817024514" type='text/javascript'></script>
<script src="${basePath}/assets/pages/scripts/powerstation/addPowerstation.js?version=20170817024514" type='text/javascript'></script>
<script src="${basePath}/assets/pages/scripts/ynz/jquery-form.js" type="text/javascript"></script>
      	<script>
      		$(function(){
// //       			//加载省市的三级下拉框
      			addressInit('province', 'city', 'county');
      		})
	      	
	   
      	</script>
    </body>

</html>
