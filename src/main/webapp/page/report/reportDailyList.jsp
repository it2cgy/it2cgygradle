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
        <title>${report_reportlist}</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <!-- BEGIN GLOBAL MANDATORY STYLES --> 
        <%@ include file="/page/common/pubCss.jsp" %>
        <link href="${basePath}assets/global/plugins/datetime/bootstrap-timepicker.min.css" rel="stylesheet" type="text/css" />
    <!-- END HEAD -->
		<style>
			.fa-angle-down:before {
			    content: "\f107";
			}
		</style>
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
                            <li>
                                <a href="#" class="blueFont">${report_list}</a>
                                <i class="fa fa-circle"></i>
                            </li>
                        </ul>
                        
                    </div>
                    <!-- END PAGE BAR -->
                    <!-- END PAGE HEADER-->
                   <div class="row">
                        <div class="col-md-12">
                            <div class="portlet light bordered">
                             	<c:if test="${(menuConfig['6'].handle_premission!=null && menuConfig['6'].handle_premission==1)}">
                                    <div class="caption font-dark">
                                        <button type="button" class="btn green" data-target="#stack1" data-toggle="modal"  
                                      		  style="margin-top: 5px;margin-bottom: 10px;"> ${createTime} 
                                        </button>
                                    </div>
                                </c:if>
                                <div class="portlet-body">
                                     <table class="greenTable-ynz table table-striped table-bordered table-hover dt-responsive" width="100%" id="reportDailyTable"></table>
                                </div>
                                </div>
                            </div>
                        </div>
                    </div>                                      
                </div>
                <!-- END EXAMPLE TABLE PORTLET-->
                <!-- END CONTENT BODY -->
            </div>
            <div class="modal fade in" id="stack1" tabindex="-1" role="basic" aria-hidden="true" style="display: none; padding-right: 17px;">
                                     <div class="modal-dialog">
                              			   <div class="modal-content" style="height:250px">
                              			    <div class="modal-header">
				                                            <button id="stack1closBtn" type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				                                            <h4 class="modal-title">${configtime}</h4>
				                                        </div>
				                    <div class="portlet-body form">
                                    <!-- BEGIN FORM-->
									<form action="###" class="form-horizontal form-row-seperated">
										<div class="form-body">
											<div class="form-group">
											  <input type="radio" name="configtype"  id="configtype" value="0" style="float:left;margin-left:80px;margin-top:10px" ${taskConfig.type==0?'checked':''}> 
                                                <label class="control-label col-md-2">${defaultTime}
                                                </label>
                                                <div class="col-md-6">
                                                <input type="hidden" name="configId" id="configId" value="${taskConfig.id}" /> 
                                                    <input type="text" name="configtime" id="configtime" data-required="1" class="form-control"  value="${taskConfig.defaultTime}" readonly="readonly"/> </div>
                                           </div>
                                           <div class="form-group">
                                            <input type="radio" name="configtype" id="configtype" value="1"  style="float:left;margin-left:80px;margin-top:10px" ${taskConfig.type==1?'checked':''}> 
                                               <label class="control-label col-md-2">${defindeTime}
                                                </label>
                                                   <div class="col-md-6">
                                                       <div class="input-group">
                                                           <input id="datetime" type="text" value="${taskConfig.configTime}" class="form-control timepicker timepicker-24">
                                                           <span class="input-group-btn">
                                                               <button class="btn default" style="background-color: #bdc2ca" type="button">
                                                                   <i>${changetime }</i>
                                                               </button>
                                                           </span>
                                                       </div>
                                                   </div>
                                               </div>
		                               </div>
										</div>
										<div class="form-actions">
											<div class="row">
												<div class="col-md-offset-4 col-md-9">
													<button type="button" class="btn green" id="configTime">
														<i class="fa fa-check"></i>${sure}</button>
													<button type="button" class="btn grey-salsa btn-outline"
														id="cancelBtn">${cancel}</button>
												</div>
											</div>
										</div>
									</form>
									<!-- END FORM-->
                                </div>
	                                    </div>
                                   </div>
                          	  </div>
        <!-- END CONTAINER -->
        <!-- BEGIN CORE PLUGINS -->
        <%@ include file="/page/common/pubJs.jsp" %>
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <script src="${cdnPath}global/scripts/datatable.js" type="text/javascript"></script>
		<script src="${cdnPath}global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
		<script src="${cdnPath}global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
		<script src="${cdnPath}global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
		<script src="${cdnPath}global/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js" type="text/javascript"></script>
        <script src="${cdnPath}global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
		<script src="${cdnPath}global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
		
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
		<script src="${basePath}/assets/pages/scripts/report/reportDailyList.js?version=20170817024514" type='text/javascript'></script>
        <!-- END PAGE LEVEL SCRIPTS -->
          <script type="text/javascript">
        	var menuConfig = "${menu6}";
        </script>
    </body>

</html>
