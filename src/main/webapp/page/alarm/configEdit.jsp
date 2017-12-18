<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/page/common/pub.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/page/common/pubThird.jsp" %>

<!DOCTYPE html>
<html lang="en">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->

    <head>
        <meta charset="utf-8" />
        <title>${add_config}</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" /> 
         <%@ include file="/page/common/pubCss.jsp" %>
         
         <!-- BEGIN PAGE LEVEL PLUGINS -->
		<link href="${cdnPath}global/plugins/bootstrap-colorpicker/css/colorpicker.css" rel="stylesheet" type="text/css" />
		<link href="${cdnPath}global/plugins/jquery-minicolors/jquery.minicolors.css" rel="stylesheet" type="text/css" />
        <!-- END PAGE LEVEL PLUGINS -->
        <style>
        	.equipType{
    			margin-right:5px;
        		float:left;
        		font-size: 14px;
        	}
        	.equipSort{
        		font-size: 16px;
        		text-align:center;
        		height:30px;
        		
        	}
        	.pointList{
        		overflow: hidden;
			    background: red;
			    height: 30px;
        	}
        	.points{
        		border:1px solid #eee;
        	}
        	.tableBorder{
        		border:1px solid #eee;
        	}
        	.equipmentListTable td{
        		white-space:nowrap;
        	}
        	.borderlR{
        		border-left:1px solid #eee;
        		border-right:1px solid #eee;
        	}
        	.borderR{
        		border-right:1px solid #eee;
        	}
        	.pointListTable table.dataTable tbody th, 
        	.pointListTable table.dataTable tbody td {
			    padding: 0px 9px;
			    vertical-align: middle;
			}
			.pointListTable table.dataTable thead th,
			.pointListTable table.dataTable thead td {
			   width:200px;
			}
			.modal-content table td{
				white-space:nowrap;
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
                                <c:if test="${longiebRoleId==4}">
					            <a href="${subIndex}" ><i18n:message name="index_page" /></a>
					            </c:if>
					            <c:if test="${longiebRoleId!=4}">
                                <a href="${basePath}index/${powerStationId}.do" ><i18n:message name="index_page" /></a>
					            </c:if>
                                <i class="fa fa-circle"></i>
                            </li>
                               <li>
                                <a href="${basePath}alarmPage/configList.do?powerStationId=${powerStationId}" >${alarm_config_list}</a>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                              
                                <span>${update_config}</span>
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
                                        <span class="caption-subject font-dark sbold uppercase">${update_config}</span>
                                    </div>
                                </div>
                                <div class="portlet-body">
                                
                                    <form id="form_1" action="###" class="form-horizontal">
                                    <!-- BEGIN FORM-->
                                        <div class="form-body">
                                            <div class="alert alert-danger display-hide">
                                                
                                                <button class="close" data-close="alert"></button> ${alert_error } </div>
                                            <div class="alert alert-success display-hide">
                                                
                                                <button class="close" data-close="alert"></button> ${alert_success} </div>
                                           
                                         
                                            
                                           <div class="form-group">
                                                <label class="control-label col-md-3">${msg_model}
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                    <input type="text" id="messageTemplate" name="messageTemplate" data-required="1" class="form-control"></input>
                                                 </div>
                                             </div> 
                                             <c:if test="${local!='en_US' }">
	                                             <div class="form-group">
	                                                <label class="control-label col-md-3">
	                                                    <span class="required">例:</span>
	                                                </label>
	                                                <div class="col-md-6">
	                                                    <span  class="required">输入:越运行上限,报警值:\${data.value},运行上限:\${config.value}<br>说明:\${data.value}会被替换为报警时的数值, \${config.value}被替换为配置的报警数值</span>
	                                     
	                                                 </div>
	                                             </div> 
                                             </c:if>
                                              <div class="form-group">
                                                <label class="control-label col-md-3">${alarm_type}
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
	                                               <select class="form-control select5me" id="alarmType" name="alarmType">
	                                               		 <option value="1">${greater_than_alarm_value }</option>
	                                               		 <option value="2">${less_than_alarm_value }</option>
	                                               		 <option value="3">${equal_to_alarm_value }</option>
	                                               		 <option value="4">${not_equal_to_alarm_value }</option>
                                                    </select>
                                           		 </div>
                                             </div> 
                                               <div class="form-group">
                                                <label class="control-label col-md-3">${I_level_value }
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                    <input type="text" id="valueOne" name="valueOne" data-required="1" class="form-control"></input>
                                                 </div>
                                             </div> 
                                               <div class="form-group">
                                                <label class="control-label col-md-3">${II_level_value }
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                    <input type="text" id="valueTwo" name="valueTwo" data-required="1" class="form-control"></input>
                                                 </div>
                                             </div> 
                                               <div class="form-group">
                                                <label class="control-label col-md-3">${III_level_value }
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                    <input type="text" id="valueThree" name="valueThree" data-required="1" class="form-control"></input>
                                                 </div>
                                             </div> 
                                               <div class="form-group" id="yaxisgroup">
                                               
                                                <label class="control-label col-md-3">${log_equipmentId}
                                                    <span class="required"> * </span>
                                                </label>
	                                           <div class="col-md-4">
	                                               <select class="form-control select5me" id="equipmentType" name="equipmentType">
	                                               		 
                                                    </select>
                                           		 </div>
                                         	</div>  
                                             <div class="form-group">
                                                <label class="control-label col-md-3">${point_type }
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                	<select id="measurementtype" name="measurementtype" class="form-control select5me">
                                                		 
                                                	</select>
                                                 </div>
                                             </div>
                                             <div class="form-group">
                                                <label class="control-label col-md-3">${iv_equipment_number}
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                	<select id="pointId" name="pointId" class="form-control select5me">
                                                		 
                                                	</select>
                                                 </div>
                                             </div>
		                                 <!--  stackable -->
			                                       
                                       		  <div class="form-actions">
	                                            <div class="row">
	                                                <div class="col-md-offset-3 col-md-9"> 
	                                                    <button class="btn btn-success" type="submit"><i18n:message name="submit" /></button>
	                                                     <a href="${basePath}dataPage/dataList.do?powerStationId=${powerStationId}"><button type="button" class="btn default"><i18n:message name="cancel" /></button> </a> 
	                                                </div>
	                                            </div>
	                                        </div>
	                                        
                                        </div>
	                                     
                                    </form>
                                    <!-- END FORM-->
                                    		 
                                    </div>
                                </div>
                                <!-- END VALIDATION STATES-->
                            </div>
                        </div>
                    </div>
                </div>
                <!-- END CONTENT BODY -->
            <!-- END CONTENT -->
            <!-- BEGIN QUICK SIDEBAR -->
            
        <%@ include file="/page/common/pubJs.jsp" %>
        <!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${cdnPath}global/plugins/select2/js/select2.full.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/ckeditor/ckeditor.js?version=20170817024514" type='text/javascript'></script>
           <!-- BEGIN PAGE LEVEL SCRIPTS -->
        <!-- BEGIN PAGE LEVEL PLUGINS --> 
<script src="${cdnPath}global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL PLUGINS -->
           <!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${cdnPath}global/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/jquery-minicolors/jquery.minicolors.min.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}/assets/pages/scripts/alarm/configEdit.js?version=20170817024514" type='text/javascript'></script>
        <!-- END PAGE LEVEL SCRIPTS -->
        
    </body>
<script type="text/javascript">
var id = '${id}'
</script>
</html>
