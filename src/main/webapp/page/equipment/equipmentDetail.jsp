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
        <title>${menu_equipment }  | ${equipment_detail }</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" /> 
         <%@ include file="/page/common/pubCss.jsp" %>
         <style>
         	.form-body input{border:0!important;background:none!important;}
         	.form-control[disabled]{
			    cursor: default!important;
			}
			.form-horizontal .control-label{margin-top:5px;}
			.no-padding input{
				padding:0;
			}
			body,html{font-size:14px;}
         </style>
             <script>
        var equipment=<%=com.yunengzhe.commons.util.JsonUtil.beanToJson(request.getAttribute("equipment")) %>
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
                                <span class="blueFont">${menu_equipment }</span>
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
                                       <span class="caption-subject font-green bold uppercase">${equipment_detail }</span>
                                    </div>                                    
                                </div>
                                <div class="portlet-body">
                                    <!-- BEGIN FORM-->
                                    <!-- 基本信息start -->
                                    <div class="row">
                                    	<h4 class="col-md-3" style="text-align:right;color:#32C5D4;">${equipment_base }</h4>
                                    </div>
                                    <form id="powerStationForm" class="form-horizontal">
                                        <div class="form-body">
                                            <div class="alert alert-danger display-hide">
                                                <button class="close" data-close="alert"></button> ${alert_error } </div>
                                            <div class="alert alert-success display-hide">
                                                <button class="close" data-close="alert"></button> ${alert_success }  </div>
                                            <div class="form-group">
                                            	<div class="col-md-6 col-sm-6">
	                                                <label class="control-label col-md-6 col-md-offset-1">${equipment_type}      ：</label>
	                                                <div class="col-md-4 no-padding">
	                                                    <input disabled type="text" name="powerStationName" id="powerStationName" value="${equipment.equipmentType} " data-required="1" class="form-control" /> </div>
	                                             </div>
	                                             <div class="col-md-6 col-sm-6">
	                                             	 <label class="control-label col-md-3">${equipment_model }      ：</label>
	                                                <div class="col-md-4 no-padding">
	                                                    <input disabled type="text" name="powerStationName" id="powerStationName" value="${equipment.equipmentModel} " data-required="1" class="form-control" /> </div>
	                                             </div>
                                            </div>
                                            <div class="form-group">
                                            	<div class="col-md-6 col-sm-6">
	                                                <label class="control-label col-md-6 col-md-offset-1">${equipment_num }      ：</label>
	                                                <div class="col-md-4 no-padding">
	                                                    <input disabled type="text" name="powerStationName" id="powerStationName" value="${equipment.equipmentNumber} " data-required="1" class="form-control" /> </div>
	                                             </div>
	                                             <div class="col-md-6 col-sm-6">
	                                             	 <label class="control-label col-md-3">${equipment_company }      ：</label>
	                                                <div class="col-md-4 no-padding">
	                                                    <input disabled type="text" name="powerStationName" id="powerStationName"  value="${equipment.factory} " data-required="1" class="form-control" /> </div>
	                                             </div>
                                            </div> 
                                            <div class="form-group">
                                            	<div class="col-md-6 col-sm-6">
	                                                <label class="control-label col-md-6 col-md-offset-1">${powerstationname }      ：</label>
	                                                <div class="col-md-4 no-padding">
	                                                    <input disabled type="text" name="powerStationName" id="powerStationName"  value="${equipment.powerstationName} " data-required="1" class="form-control" /> </div>
	                                             </div>	                                            
                                            </div>                                           
                                          </div>
                                      </form>
                                      <!-- 基本信息end -->
                                      <!-- 质保信息start -->
                                      <div class="row">
                                    	<h4 class="col-md-3" style="text-align:right;color:#32C5D4;">${equipment_zhibao }</h4>
                                      </div>
                                    <form id="powerStationForm" class="form-horizontal">
                                        <div class="form-body">
                                            <div class="form-group">
                                            	<div class="col-md-6 col-sm-6">
	                                                <label class="control-label col-md-6 col-md-offset-1">${equipment_protime }      ：</label>
	                                                <div class="col-md-4 no-padding">
	                                                    <input disabled type="text" name="producedDate" id="producedDate" value="${equipment.producedDate} "  data-required="1" class="form-control" /> </div>
	                                             </div>
	                                             <div class="col-md-6 col-sm-6">
	                                             	 <label class="control-label col-md-3">${equipment_incompany }      ：</label>
	                                                <div class="col-md-4 no-padding">
	                                                    <input disabled type="text" name="factoryDate" id="factoryDate" value="${equipment.factoryDate} "  data-required="1" class="form-control" /> </div>
	                                             </div>
                                            </div>
                                            <div class="form-group">
                                            	<div class="col-md-6 col-sm-6">
	                                                <label class="control-label col-md-6 col-md-offset-1">${equipment_life }      ：</label>
	                                                <div class="col-md-4 no-padding">
	                                                    <input disabled type="text" name="powerStationName" id="powerStationName" value="${equipment.lifetime} "  data-required="1" class="form-control" /> </div>
	                                             </div>
	                                             <div class="col-md-6 col-sm-6">
	                                             	 <label class="control-label col-md-3">${equipment_jianxiu }      ：</label>
	                                                <div class="col-md-4 no-padding">
	                                                    <input disabled type="text" name="powerStationName" id="powerStationName" value="${equipment.serviceExpense} " data-required="1" class="form-control" /> </div>
	                                             </div>
                                            </div> 
                                            <div class="form-group">
                                            	<div class="col-md-6 col-sm-6">
	                                                <label class="control-label col-md-6 col-md-offset-1">${equipment_jiage }      ：</label>
	                                                <div class="col-md-4 no-padding">
	                                                    <input disabled type="text" name="powerStationName" id="powerStationName" value="${equipment.price} " data-required="1" class="form-control" /> </div>
	                                             </div>
	                                             <div class="col-md-6 col-sm-6">
	                                             	 <label class="control-label col-md-3">${equipment_zhejiu }      ：</label>
	                                                <div class="col-md-4 no-padding">
	                                                    <input disabled type="text" name="powerStationName" id="powerStationName" value="${equipment.deprecition} " data-required="1" class="form-control" /> </div>
	                                             </div>		                                            
                                            </div>                                           
                                          </div>
                                      </form>
                                      <!-- 质保信息end -->
                                      <!-- 文档信息start -->
                                      <div class="row">
                                    	   <h4 class="col-md-3" style="text-align:right;color:#32C5D4;">${equipment_word }</h4>
                                      </div>
                                      <form id="powerStationForm" class="form-horizontal">
                                      	<div id="filePortion" class="form-body">
                                      	<div class="form-group" id="filemodel">
                                   	<c:if test="${empty equipment.attachmentPO}"><span class="col-md-4 no-padding" style="text-align: right;margin-top: -16px;">${equipment_nofile }</span></c:if>
                                      	<c:if test="${! empty equipment.attachmentPO}">
                                      		<c:forEach items="${equipment.attachmentPO}" var="atta">
                                      			
<!-- 	                                             <div class="col-md-3 col-sm-6"> -->
<!-- 	                                                <label class="control-label col-md-6 col-md-offset-1"> -->
<!-- 	                                                </label> -->
<!-- 	                                                 </div>  -->
	                                                <div class="col-md-11 col-md-offset-3" style="margin-bottom: 10px">
	                                                    <a target="_blank" href="${atta.filepath}"><img title='' src="${atta.filepath}" style="width:70px;height:70px;margin-left:10px;"/></a>
	                                                    &nbsp;&nbsp;&nbsp;&nbsp;<div style="line-height: 40px;">${atta.filename!=null&&atta.filename!=''?atta.filename:atta.remarks}</div> 
	                                                </div>
	                                            
                                           
                                      		</c:forEach>
                                      		</c:if>
                                      		 </div>
                                            </div> 
                                            </form>
                                             <div class="row" style="margin-top:30px;">
		                                    	 <h4 class="col-md-3" style="text-align:right;color:#32C5D4;">${equipment_mark }</h4>
		                                    	 <div class="col-md-9">
		                                           <textarea name="markdown" data-provide="markdown" rows="10" data-error-container="#editor_error" style="margin: 0 0 20px; width: 663px; height: 157px;" placeholder="">${equipment.remark}</textarea>
		                                           <div id="editor_error"> </div>
			                                    </div>
		                                    </div>
                                     <!-- 文档信息end -->
                                    <div class="form-actions">
		                                 <div class="row">
		                                     <div class="col-md-offset-3 col-md-9">
		                                         <a href="${basePath}equipmentPage/equipmentList.do?a=1<c:if test="${(admin!=null && admin==true) }">&admin=1</c:if><c:if test="${(powerStationId!=null && powerStationId>0) }">&powerStationId=${powerStationId}</c:if>"><button type="button" class="btn default"><i18n:message name="cancel" /></button> </a> 
		                                     </div>
		                                 </div>
		                             </div>  
                                  
                                   </div>  
                               </div>            
                           </div>                                      
                       </div>  
                       
                       </div>
                       </div></div>                 
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
        <!-- END PAGE LEVEL SCRIPTS -->
        <script type="text/javascript">
	        jQuery(document).ready(function() {
	        	setSelectMenuRoot("equipmentmanager");
	        	var producedDate = $("#producedDate").val();
	            if(producedDate!=null&&producedDate!=' '&&producedDate!=''){
	            	$("#producedDate").val(timeUtil.timeToString(producedDate,"yyyy-MM-dd"));
	            }
	            if($("#factoryDate").val()!=null&&$("#factoryDate").val()!=''&&$("#factoryDate").val()!=' '){
	            	$("#factoryDate").val(timeUtil.timeToString($("#factoryDate").val(),"yyyy-MM-dd"));
	            }
	        });
        </script>
     </body>
</html>                            
         
