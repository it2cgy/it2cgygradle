<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/page/common/pub.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->

    <head>
        <meta charset="utf-8" />
        <title><i18n:message name="curve_addtitle" /></title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" /> 
         <%@ include file="/page/common/pubCss.jsp" %>
         
         <!-- BEGIN PAGE LEVEL PLUGINS -->
<link href="${cdnPath}global/plugins/bootstrap-colorpicker/css/colorpicker.css" rel="stylesheet" type="text/css" />
<link href="${cdnPath}global/plugins/jquery-minicolors/jquery.minicolors.css" rel="stylesheet" type="text/css" />
        <!-- END PAGE LEVEL PLUGINS -->
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
                              
                                <span>${curve_addcaption}</span>
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
                                        <span class="caption-subject font-dark sbold uppercase"><i18n:message name="curve_addcaption"/></span>
                                    </div>
                                </div>
                                <div class="portlet-body">
                                
                                    <form id="form_1" class="form-horizontal">
                                    <!-- BEGIN FORM-->
                                        <div class="form-body">
                                            <div class="alert alert-danger display-hide">
                                                
                                                <button class="close" data-close="alert"></button> ${alert_error } </div>
                                            <div class="alert alert-success display-hide">
                                                
                                                <button class="close" data-close="alert"></button> ${alert_success} </div>
                                           <div class="form-group">
                                                <label class="control-label col-md-3"><i18n:message name="curve_name" />
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                     <input type="text" id="name" name="name" data-required="1" class="form-control"></input>
                                                </div>
                                            </div>
                                         
                                             <div class="form-group" id="yaxisgroup">
                                               
                                                <label class="control-label col-md-3">${curve_lines}
                                                    <span class="required"> * </span>
                                                </label>
	                                           <div class="col-md-4">
	                                               <select class="form-control select5me" id="type" name="type">
	                                               		
	                                               		  <option value="1">${curve_onelines}</option>
		                                             	  <option value="2">${curve_twolines}</option>
                                                    </select>
                                           		 </div>
                                         	</div>
                                           <div class="form-group">
                                                <label class="control-label col-md-3"><i18n:message name="curve_firstYaxisName" />
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                    <input type="text" id="firstYaxisName" name="firstYaxisName" data-required="1" class="form-control"></input>
                                                 </div>
                                             </div> 
                                               <div class="form-group" id="curvesecond" style="display: none">
                                                <label class="control-label col-md-3"><i18n:message name="curve_secondYaxisName" />
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                    <input type="text" id="secondYaxisName" name="secondYaxisName" data-required="1" class="form-control"></input>
                                                 </div>
                                             </div>
                                             <div class="form-group">
                                                <label class="control-label col-md-3"><i18n:message name="curve_timeSpan" />
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                	<select id="timeSpan" name="timeSpan" class="form-control select5me">
                                                		<option value="1">1${minute }</option>
                                                		<option value="10">10${minute }</option>
                                                		<option value="60">60${minute }</option>
                                                	</select>
                                                 </div>
                                             </div>
                                             
                                             <div class="form-group">
			                                    
			                                    <label class="control-label col-md-3">${curve_pointlist}
                                                    <span class="required"> * </span>
                                                </label>
                                                 <div class="col-md-4">
                                                 	<button type="button" class="btn green" data-target="#stack1" data-toggle="modal"  style="margin-top: 5px;margin-bottom: 10px;">${curve_newpoint}</button> 
	                                                  <table class="table table-striped table-hover table-bordered" id="sample_editable_1"  >
				                                        <thead>
				                                            <tr>
				                                                <th> ${curve_point} </th>
				                                                <th> ${curve_pointcolor}</th>
				                                                <th> ${curve_ownline} </th>
				                                                <th> ${action} </th>
				                                            </tr>
				                                        </thead>
				                                       
				                                        <tbody id="pointSelectId" style="position:relative;" class="table tbody">
				                                        </tbody>
				                                      </table>
                                                 </div>
			                                      </div>
		                                 <!--  stackable -->
			                                       
                                       		  <div class="form-actions">
	                                            <div class="row">
	                                                <div class="col-md-offset-3 col-md-9"> 
	                                                    <button class="btn btn-success" type="submit"><i18n:message name="submit" /></button>
	                                                     <a href="${basePath}curvePage/curveList.do?powerStationId=${powerStationId}"><button type="button" class="btn default"><i18n:message name="cancel" /></button> </a> 
	                                                </div>
	                                            </div>
	                                        </div>
	                                        
                                        </div>
	                                     
                                    </form>
                                    <!-- END FORM-->
                                    <!--                        			             添加测点弹窗 -->

                                    <div id="stack1" class="modal fade"  data-focus-on="input:first" >
		                                         <div class="modal-dialog">
                                         			   <div class="modal-content">
                                     <form id="form_2" class="form-horizontal">
				                                        <div class="modal-header">
				                                            <button id="stack1Close" type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				                                            <h4 class="modal-title">${curve_newpoint}</h4>
				                                        </div>
				                                        <div class="alert alert-danger display-hide">
			                                                <button class="close" data-close="alert"></button>${form_error}</div>
			                                            <div class="alert alert-success display-hide">
			                                                <button class="close" data-close="alert"></button>${form_success} </div>
					                                     <div class="form-group" style="margin-top: 20px">
			                                                <label class="control-label col-md-3">${equipment_type}
			                                                    <span class="required"> * </span>
			                                                </label>
				                                           <div class="col-md-7">
				                                               <select class="form-control col-md-12 select2" id="equipmenttype" name="equipmenttype"></select>  
			                                           		 </div>
	                                           		 	</div>
					                                     <div class="form-group" style="margin-top: 20px">
			                                                <label class="control-label col-md-3">${equipment_num}
			                                                    <span class="required"> * </span>
			                                                </label>
				                                           <div class="col-md-7">
				                                               <select class="form-control col-md-12 select2" id="equipmentnum" name="equipmentnum"><option value="-1">${curve_pleaseselectequipmentnumber}</option></select>  
			                                           		 </div>
	                                           		 	</div>
				                                        <div class="form-group"  style="margin-top: 20px">
			                                                <label class="control-label col-md-3">${curve_point}
			                                                    <span class="required"> * </span>
			                                                </label>
			                                                 <div class="col-md-7">
				                                               <select class="form-control select2" id="pointSelect" name="pointSelect"> 
			                                                    </select>
			                                           		 </div>
	                                           		 	</div>
				                                        <div class="form-group">
				                                           <label class="control-label col-md-3">${curve_pointcolor}
						                                                    <span class="required"> * </span>
						                                                </label>
				                                            <div class="col-md-7"><input type="text" id="colorCode" class="form-control demo" data-control="wheel" value="#ff99ee" name="colorCode"></div>
				                                        </div>
				                                        <div class="form-group">
			                                                <label class="control-label col-md-3">${curve_ownline}
			                                                    <span class="required"> * </span>
			                                                </label>
					                                           <div class="col-md-7">
					                                               <select class="form-control select4me" id="yaxisType" name="yaxisType">
					                                             	  <option value="0">${curve_leftline}</option>
					                                             	  <option value="1">${curve_rightline}</option>
				                                                    </select>
				                                           		 </div>
				                                           		 </div>
				                                          <div class="form-actions">
				                                            <div class="row">
						                                            <div class="modal-footer">
						                                           	  <button type="submit" class="btn btn-success" id="save" name="save" >${submit}</button>
						                                              <button onClick="$('#stack1alertsuccess').hide();" type="button" data-dismiss="modal" class="btn btn-outline dark">${cancel}</button>
						                                            </div>
				                                         
				                                            </div>
				                                        </div>
	                                     		</form> 
	                                     		</div></div>
	                                      </div>
	                                         <!--                        			             添加测点弹窗结束 -->		 
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
<script src="${basePath}/assets/pages/scripts/curve/curve-add.js?version=20170817024514" type='text/javascript'></script>
        <!-- END PAGE LEVEL SCRIPTS -->
        
    </body>

</html>
