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
        <title>${menu_equipment }  | ${menu_equipmentEdit }</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" /> 
         <%@ include file="/page/common/pubCss.jsp" %>
         <script>
         var powerstationid = "${equipment.powerstationid}";
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
					            <a href="${basePath}equipmentPage/equipmentList.do?powerStationId=${redirectStationId}" ><i18n:message name="menu_equipmentList" /></a>
					            </c:if>
					            <c:if test="${redirectStationId==null||redirectStationId==''}">
                                <a href="${basePath}equipmentPage/equipmentList.do" ><i18n:message name="menu_equipmentList" /></a>
					            </c:if>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <span class="blueFont">${menu_equipmentEdit }</span>
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
                                       <span class="caption-subject font-green bold uppercase">${menu_equipmentEdit }</span>
                                    </div>                                    
                                </div>
                                <div class="portlet-body">
                                    <!-- BEGIN FORM-->
                                    <!-- 基本信息start -->
                                    <div class="row">
                                    	<h4 class="col-md-3" style="text-align:right;color:#32C5D4;">${equipment_base }</h4>
                                    </div>
                                    <form id="equipmentForm" class="form-horizontal">
                                        <div class="form-body">
                                            <div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button> ${alert_error } </div>
                                            <div class="alert alert-success display-hide">
                                                <button class="close" data-close="alert"></button> ${alert_success } </div>
                                                <input hidden="hidden" name="id" id="id" value="${equipment.id}">
                                            <div class="form-group">
                                            	<div class="col-md-6 col-sm-6">
	                                                <label class="control-label col-md-6">${equipment_type}&nbsp;&nbsp;
	                                                    <span class="required"> * </span>
	                                                </label>
	                                                <div class="col-md-6">
	                                                    <input type="text" name="equipmentType" id="equipmentType" value="${equipment.equipmentType}" data-required="1" class="form-control" /> </div>
	                                             </div>
	                                             <div class="col-md-6 col-sm-6">
	                                             	 <label class="control-label col-md-3">${equipment_model }&nbsp;&nbsp;
	                                             	   <span class="required"> * </span>
	                                                </label>
	                                                <div class="col-md-6">
	                                                    <input type="text" name="equipmentModel" id="equipmentModel" value="${equipment.equipmentModel}" data-required="1" class="form-control" /> </div>
	                                             </div>
                                            </div>
                                            <div class="form-group">
                                            	<div class="col-md-6 col-sm-6">
	                                                <label class="control-label col-md-6">${equipment_num }&nbsp;&nbsp;
	                                                </label>
	                                                <div class="col-md-6">
	                                                    <input type="text" name="equipmentNumber" id="equipmentNumber" value="${equipment.equipmentNumber}" data-required="1" class="form-control" /> </div>
	                                                    <span id="waning" style="color:red;margin: 5px 0 0px 20px;text-align: right;display: none" class="col-md-6 col-md-push-2 ">* ${curve_repeequinumber}</span>
	                                             </div>
	                                             <div class="col-md-6 col-sm-6">
	                                             	 <label class="control-label col-md-3">${equipment_company }&nbsp;&nbsp;
	                                                </label>
	                                                <div class="col-md-6">
	                                                    <input type="text" name="factory" id="factory" value="${equipment.factory}" data-required="1" class="form-control" /> </div>
	                                             </div>
                                            </div> 
                                            <div class="form-group">
                                            	<div class="col-md-6 col-sm-6">
	                                                <label class="control-label col-md-6">${powerstationname }&nbsp;&nbsp;
	                                                </label>
	                                                <div class="col-md-6">
	                                                     <select class="form-control select5me" id="powerstationid" name="powerstationid">
                                                  		  </select>
	                                             </div>	                                            
                                            </div>                                           
                                          </div>
                                          </div>
                                      <!-- 基本信息end -->
                                      <!-- 质保信息start -->
                                      <div class="row">
                                    	<h4 class="col-md-3" style="text-align:right;color:#32C5D4;">${equipment_zhibao }</h4>
                                      </div>
                                        <div class="form-body">
                                            <div class="form-group">
                                            	<div class="col-md-6 col-sm-6">
	                                                <label class="control-label col-md-6">${equipment_protime }&nbsp;&nbsp;
	                                                </label>
	                                                <div class="col-md-6">
	                                         		  <div class="input-group input-large date-picker input-daterange"  data-date-format="yyyy-mm-dd" style="width: 100%!important;">
	                                                    <input type="text" name="producedDate" id="producedDate" class="form-control" value="${equipment.producedDate}"  />
	                                                    </div>
	                                                </div>
	                                             </div>
	                                             <div class="col-md-6 col-sm-6">
	                                             	 <label class="control-label col-md-3">${equipment_incompany }&nbsp;&nbsp;
	                                                </label>
	                                                <div class="col-md-6">
	                                                <div class="input-group input-large date-picker input-daterange"  data-date-format="yyyy-mm-dd" style="width: 100%!important;">
	                                                    <input type="text" name="factoryDate" id="factoryDate" value="${equipment.factoryDate}"  class="form-control" /> </div>
	                                             </div>
	                                            </div>
                                            </div>
                                            <div class="form-group">
                                            	<div class="col-md-6 col-sm-6">
	                                                <label class="control-label col-md-6">${equipment_life }&nbsp;&nbsp;
	                                                </label>
	                                                <div class="col-md-6">
	                                                    <input type="text" name="lifetime" id="lifetime" value="${equipment.lifetime}"  data-required="1" class="form-control" /> </div>
	                                             </div>
	                                             <div class="col-md-6 col-sm-6">
	                                             	 <label class="control-label col-md-3">${equipment_jianxiu }&nbsp;&nbsp;
	                                                </label>
	                                                <div class="col-md-6">
	                                                    <input type="text" name="serviceExpense" id="serviceExpense" value="${equipment.serviceExpense}" data-required="1" class="form-control" /> </div>
	                                             </div>
                                            </div> 
                                            <div class="form-group">
                                            	<div class="col-md-6 col-sm-6">
	                                                <label class="control-label col-md-6">${equipment_jiage }&nbsp;&nbsp;
	                                                </label>
	                                                <div class="col-md-6">
	                                                    <input type="text" name="price" id="price" data-required="1" value="${equipment.price}" class="form-control" /> </div>
	                                             </div>
	                                             <div class="col-md-6 col-sm-6">
	                                             	 <label class="control-label col-md-3">${equipment_zhejiu }(%)&nbsp;&nbsp;
	                                                </label>
	                                                <div class="col-md-6">
	                                                    <input type="text" name="deprecition" id="deprecition"  value="${equipment.deprecition}"  data-required="1" class="form-control" /> </div>
	                                             </div>		                                            
                                            </div>                                           
                                          </div>
                                      <!-- 质保信息end -->
                                      <!-- 文档信息start -->
                                      <div class="row">
                                    	   <h4 class="col-md-3" style="text-align:right;color:#32C5D4;">${equipment_word }</h4>
                                    	   <div class="caption font-dark" >
	                                          <button type="button" class="btn sbold green" onclick="addFileButton()" style="margin-left:29px;margin-bottom: 15px;"> ${equipment_addfile }
	                                              <i class="fa fa-plus"></i>
	                                          </button>
		                                   </div>
                                      </div>
                                      	<div id="filePortion">
                                      		<c:forEach items="${equipment.attachmentPO}" var="atta">
                                      			<div class="form-group" id="filemodel">
	                                             <div class="col-md-12 col-sm-12">
	                                                <label class="control-label col-md-3"></label>
	                                                <div class="col-md-3"  style="padding-left:20px;">
	                                                ${equipment_file_name }
                                                    	<input type="text" id="filename" name="filename" data-required="1" class="form-control" style="margin-top:20px;" value="${atta.filename}"/>
                                                    	<input id="filepath" hidden="hidden" name="filepath" value="${atta.filepath}">
                                                    	<input id="remarks" hidden="hidden" name="remarks" value="${atta.remarks}">
	                                                </div>
	                                             <a href="${atta.filepath}"><img id='filenamepath' src="${atta.filepath}" title="${atta.remarks}" style="width:70px;height:70px;margin-left:10px;" alt="${atta.remarks}"/></a>
	                                             <input id="file" name="file" value="${atta.remarks}" class="col-md-6" style="border: 0;background: none;display: none"  readonly="readonly">
<!-- 		                                                <input type="file" id="file" name="file"> -->
		                                               <button type="button" id='butoon' style='margin-left:10px;margin-top: 15px;'  class="btn green" onclick="deleterow(this)">${del}</button>
	                                             </div>	                                            
                                            </div> 
                                      		</c:forEach>
                                         </div> 
                                             <div class="row">
		                                    	 <h4 class="col-md-3" style="text-align:right;color:#32C5D4;">${equipment_mark }</h4>
		                                    	 <div class="col-md-9" style="padding-left:30px;">
		                                           <textarea name="remark" id="remark" data-provide="markdown"  rows="10" data-error-container="#editor_error" style="margin: 0 0 20px; width: 663px; height: 157px;" >${equipment.remark}</textarea>
		                                           <div id="editor_error"> </div>
			                                    </div>
		                                    </div>
				                            <!-- 文档信息end -->
				                            <div class="form-actions">
				                                 <div class="row">
				                                     <div class="col-md-offset-3 col-md-9" style="padding-left: 20px;">
				                                         <button type="submit" class="btn green">${submit}</button>
				                                         <a href="${basePath}equipmentPage/equipmentList.do?a=1<c:if test="${(admin!=null && admin==true) }">&admin=1</c:if><c:if test="${(powerStationId!=null && powerStationId>0) }">&powerStationId=${powerStationId}</c:if>"><button type="button" class="btn default"><i18n:message name="cancel" /></button> </a> 
				                                     </div>
				                                 </div>
				                             </div>  
                                     <!-- 文档信息end -->
                                    </form>
                                  <form id='fileform' target='framFile' method='post' action='${uploadurl}' enctype='multipart/form-data' hidden="hidden">
   									    <input type='text' hidden='hidden' name='fileDir' id='fileDir' value='longieb/equipment'/>
                                </form>
                                   </div>  
                               </div>            
                           </div>     
                           
                          </div>                                 
                       </div>                   
                </div>
                </div>
           <%@ include file="/page/common/pubJs.jsp" %>
        <!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${cdnPath}global/plugins/select2/js/select2.full.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/ckeditor/ckeditor.js?version=20170817024514" type='text/javascript'></script>
        <!-- 日期插件 -->
<script src="${cdnPath}global/plugins/moment.min.js" type="text/javascript"></script>
<script src="${basePath}/assets/pages/scripts/components-date-time-pickers.min.js" type="text/javascript"></script>
         <!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}/assets/pages/scripts/equipment/equipment-edit.js?version=20170817024514" type='text/javascript'></script>
<script src="${basePath}/assets/pages/scripts/ynz/jquery-form.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL SCRIPTS -->
     </body>
</html>                
                                      
