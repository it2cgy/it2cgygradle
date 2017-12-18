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
        <title><i18n:message name="history_historyquery"/> </title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <!-- BEGIN GLOBAL MANDATORY STYLES --> 
        <%@ include file="/page/common/pubCss.jsp" %>
<link href="${cdnPath}global/plugins/bootstrap-colorpicker/css/colorpicker.css" rel="stylesheet" type="text/css" />
<link href="${cdnPath}global/plugins/jquery-minicolors/jquery.minicolors.css" rel="stylesheet" type="text/css" />
<link href="${cdnPath}global/plugins/bootstrap-daterangepicker/daterangepicker.min.css" rel="stylesheet" type="text/css" />
<link href="${cdnPath}global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />
<link href="${cdnPath}global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css" rel="stylesheet" type="text/css" />
<link href="${cdnPath}global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css" />
        <style>
        	.myDefinTable{
        		text-align:center;       		
        	}
        	.table-bordered>tbody>tr>td,
        	.table-bordered>thead>tr>th{
        		border:0;
        	}
        	.myDefinTable thead tr{
        		 background-color: #C8DAE9;
        	}
        	.table thead th{
        		text-align:center;
        		border:0;
        		background:#C8DAE9;
        	}
        	.table-striped>tbody>tr:nth-of-type(even) {
			    background-color: #E1EBF3;
			}
			#endTime-error{
				float:right;
			}
			#startTime-error{
				float:left;
			}
			.portlet-form .form-actions, .form .form-actions{
				border:0;
			}
			.table{
				text-align:center;
			}
			.caption{
				 height: 50px;
				 line-height: 50px;
				 font-size: 16px;
				 padding-left: 15px;
			}
			.portlet.light .dataTables_wrapper .dt-buttons{
				position:relative;
				position: relative;
    			margin: 10px 15px 0 0;
			}
			.dataTables_wrapper{
				padding-bottom:10px;
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
        <div class="page-container">
            <!-- BEGIN SIDEBAR -->
             <%@ include file="/page/common/companyAdminMenu.jsp" %>
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
                            <li><a href="#" class="blueFont">${menu_history}</a></li>
                        </ul>
                        
                    </div> 
                     
                     
                     <div class="row">
                        <div class="col-md-12">
                            <!-- BEGIN VALIDATION STATES-->
                            <div class="portlet light portlet-fit portlet-form bordered">
                                <!-- <div class="portlet-title"> -->
                                    <div class="caption">
                                        <i class=" icon-layers font-green"></i>
	                                        <span class="caption-subject font-green bold uppercase">${history_query }</span>
                                    </div>
                                <!-- </div> -->
	                                <div class="portlet-body">
	                                
	                                 <div class="row">
					                        <div class="col-md-12">
					                            <!-- BEGIN PORTLET-->
					                            <div class="portlet light bordered form-fit">
					                                <div class="portlet-body form">
					                                    <form   id="form_1" class="form-horizontal">
					                                   		 <div class="form-group" style="margin-top: 15px;margin-bottom: 0px;">
						                                   		 <div class="col-md-12">
						                                   			 <!-- 测点列表end -->
									                                 <div class="col-md-12">
									                                 	<table id="queryTable" class="table table-bordered table-striped table-condensed flip-content myDefinTable">
									                                        <thead>
									                                            <tr>
									                                                <th> <i18n:message name="history_pointname"/> </th>
									                                                <th> <i18n:message name="history_pointcolor"/> </th>
									                                                <th> <i18n:message name="del"/> </th>
									                                            </tr>
									                                        </thead>
									                                        <tbody id="queryTablebody" style="position:relative;" class="table tbody" style="height:300px;background:pink;">
									                                        </tbody>
									                                     </table>
									                                      <!-- 测点按钮start -->
									                                      <div style="text-align:center;margin:10px 0;">
									                                           <button type="button" class="btn btn-circle btn-success" data-target="#stack1" data-toggle="modal"><i18n:message name="history_addpoint"/>
						                                          			 	   <i class="fa fa-plus"></i>
						                                      				   </button>
					                                      				   </div><!-- 测点按钮end -->
									                                 </div>
								                                  </div>
						                                      </div>
							                                      
					                                            <div class="form-group" style="margin-bottom: 0px;">
					                                            	<div class="col-md-5">
												                     	 <label class="control-label floatL col-md-3">${history_querytime }
						                                                    <span class="required"> * </span>
						                                                </label>
						                                                <div class="col-md-8">
						                                                    <div class="input-group input-large date-picker input-daterange" data-date="2017-06-28" data-date-format="yyyy-mm-dd" style="width: 100%!important;">
							                                                        <input type="text" class="form-control" id="startTime" name="startTime">
							                                                        <span class="input-group-addon"> - </span>
							                                                        <input type="text" class="form-control" id="endTime" name="endTime"> </div>
							                                                    <!-- /input-group --> 
							                                             </div>
							                                         </div><!-- 察县时间end -->
							                                         <div class="col-md-4">
							                                         	    <label class="control-label floatL">${timeSpan }
							                                                    <span class="required"> * </span>
							                                                </label>
							                                                <div class="col-md-8">
							                                                    <div class="input-group input-large date-picker input-daterange" data-date="2017-06-28" data-date-format="yyyy-mm-dd" style="width: 100%!important;">
								                                           			  <select class="form-control select3me" id="timeInterval">
								                                           			       <option value="1">1${minute }</option>
								                                                           <option value="10">10${minute }</option>
								                                                           <option value="60">60${minute }</option>
									                                                  </select>
								                                                </div>
							                                             	</div>
							                                         </div>
							                                         <div class="form-actions" style="float: right;margin-bottom: 15px;padding: 10px 10px 0 0;">
							                                            <div class="col-md-12">
							                                                <div  style="text-align:center;margin-top: -11px;">
							                                                   <button type="submit" class="btn btn-success" ><i18n:message name="query"/></button>
							                                                    <button type="button" class="btn green"  data-toggle="modal"   id="createreport"><i18n:message name="history_createreport"/></button>
							                                                    <button type="button" class="btn green" id="downloadreport"><i18n:message name="history_downloadreport"/></button>
							                                                </div>
							                                            </div>
								                                    </div>
					                                             </div><!-- 时间间隔 end -->
					                                    </form>
					                                </div>
					                            </div> 
					                        </div>
					                    </div>
					                    
					                    
					                     
                                    <!-- BEGIN FORM-->                                      						                    
		                                      <div id="stack1" class="modal fade" data-focus-on="input:first" >
		                                         <div class="modal-dialog">
                                         			   <div class="modal-content">
						                                        <div class="modal-header">
						                                            <button id="stack1Close" type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
						                                            <h4 class="modal-title" style="margin-top:35px;"><i18n:message name="history_newpoint"/></h4>
						                                        </div>
						                                          
					                                         <form id="form_sample_3" class="form-horizontal form-bordered">
								                                    <div class="form-body">  
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
							                                        <div class="form-group" style="margin-top: 20px">
						                                                <label class="control-label col-md-3"><i18n:message name="history_pointname"/>
						                                                    <span class="required"> * </span>
						                                                </label>
							                                           <div class="col-md-7">
							                                               <select class="form-control col-md-12 select2" id="pointId" name="pointId"></select>  
						                                           		 </div>
				                                           		 	</div>
				                                           		 	
							                                        <div class="form-group">
							                                           <label class="control-label col-md-3"><i18n:message name="history_pointcolor"/>
									                                   <span class="required"> * </span>
									                                    </label>
							                                            <div class="col-md-7"><input type="text" id="colorCode" class="form-control demo" data-control="wheel" value="#ff99ee" name="colorCode"></div>
							                                        </div>
							                                        <div class="form-group">
							                                           <div class="modal-footer" style="margin-top: 20px;">
							                                            <button type="button" class="btn green" id="save" name="save" onclick="savePoint()"><i18n:message name="add"/></button>
							                                            <button type="button" data-dismiss="modal" class="btn btn-outline dark"><i18n:message name="cancel"/></button>
							                                        	</div>
							                                        </div>
									                                        
						                                       		 </div> 
				                                              </form>
				                                            </div> 
						                               </div> 
				                                </div>
	                                       
	                                      
		                                         <div id="reportModel" class="modal fade"   data-focus-on="input:first" >
								                      <div class="modal-dialog">
                                         			   <div class="modal-content">
								                         <div class="modal-header">
								                             <button id="reportModelclose" type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
								                             <h4 class="modal-title">${history_createreport }</h4>
								                         </div>
								                          <form id="form_report" class="form-horizontal form-bordered">
					                                     	  <div class="form-body">  
											                         <div class="form-group">
											                             <label class="control-label col-md-3">${history_reportname }
											                                 <span class="required"> * </span>
											                             </label>
											                             <div class="col-md-7">
											                               <input name="reportName" id="reportName" type="text" class="form-control" /> 
											                           	</div>
											                        </div>
												                    <div class="modal-footer">
											                             <button type="submit" class="btn btn-success" id="save" name="save" >${sure }</button>
											                             <button type="button" data-dismiss="modal" class="btn btn-outline dark">${cancel }</button>
												                   </div>       
										     				  </div>
													     </form>
													    </div>
                                         			  </div>
									       </div>
		                            <!-- BEGIN VALIDATION STATES-->
		                           		 </div>
		                    
                          
                                <!-- END VALIDATION STATES-->
                                  <h4 class="fontBlue borderRadius5">${history_showlist }</h4>
                                  <div class="portlet-body">
                                     <table id="datatable" class="table table-bordered table-striped table-condensed flip-content">
                                        <thead class="flip-content">
                                            <tr>  
                                                <th class="numeric"> ${history_pointname } </th>
                                                <th class="numeric"> ${pubtime } </th>
                                                <th class="numeric"> ${pubvalue } </th>
                                            </tr>
                                        </thead>
                                        <tbody id="datatablebody">
                                         
                                        </tbody>
                                    </table> 
                                  </div>
                            
                            <div class="portlet light portlet-fit bordered">
                                <h4 class="fontBlue borderRadius5">${history_showline }</h4>
                                <div class="portlet-body">
                                    <div id="echarts_line" class="col-md-10" style="height:270px;margin:0 auto;float: none;"></div>
                                </div>
                            </div>
                            
                            <div class="portlet light portlet-fit bordered">
                                <h4 class="fontBlue borderRadius5">${history_showbar }</h4>
                                <div class="portlet-body">
                                    <div id="echarts_bar"  class="col-md-10" style="height:270px;margin:0 auto;float: none;"></div>
                                </div>
                            </div>
                        </div>
                    </div>        
                     </div>     
                <!-- END CONTENT BODY -->
            </div>
            <!-- END CONTENT -->
       		 </div>
             
                
        <!-- END CONTAINER -->
        </div>
        <!-- BEGIN CORE PLUGINS -->
        <%@ include file="/page/common/pubJs.jsp" %>
        
        <!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${cdnPath}global/plugins/select2/js/select2.full.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/scripts/datatable.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
        
<script src="${cdnPath}global/plugins/moment.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/bootstrap-daterangepicker/daterangepicker.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js" type="text/javascript"></script>
        
<script src="${cdnPath}global/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/ckeditor/ckeditor.js?version=20170817024514" type='text/javascript'></script>
<script src="${cdnPath}global/plugins/jquery-minicolors/jquery.minicolors.min.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN PAGE LEVEL SCRIPTS --> 
<script src="${basePath}assets/pages/scripts/echarts-all.js?version=20170817024514" type='text/javascript'></script>
   		
<script src="${cdnPath}/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL SCRIPTS -->
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}/assets/pages/scripts/history/history.js?version=20170817024514" type='text/javascript'></script>
     
        <!-- END PAGE LEVEL SCRIPTS -->
    </body>

</html>
