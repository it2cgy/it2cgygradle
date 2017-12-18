<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/page/common/pub.jsp"%>
<%@ include file="/page/common/pubThird.jsp" %>

<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->

<head>
<meta charset="utf-8" />
<title>${iv_title}</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<%@ include file="/page/common/pubCss.jsp"%>
<link href="${basePath}assets/pages/css/ynz/common/static.css" />
<!-- BEGIN PAGE LEVEL PLUGINS -->
<link href="${cdnPath}global/plugins/bootstrap-select/css/bootstrap-select.min.css" rel="stylesheet" type="text/css" />
<link href="${cdnPath}global/plugins/jquery-multi-select/css/multi-select.css" rel="stylesheet" type="text/css" />
<link href="${cdnPath}global/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
<link href="${cdnPath}global/plugins/select2/css/select2-bootstrap.min.css" rel="stylesheet" type="text/css" />
<!-- END PAGE LEVEL PLUGINS -->
</head>
<!-- END HEAD -->

<body
	class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
	<!-- BEGIN HEADER -->
	<%@ include file="/page/common/companyHead.jsp"%>
	<!-- END HEADER -->
	<!-- BEGIN HEADER & CONTENT DIVIDER -->
	<div class="clearfix"></div>
	<!-- END HEADER & CONTENT DIVIDER -->
	<!-- BEGIN CONTAINER -->
	<div class="page-container" style="background: #0c1c33">
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
				<!-- BEGIN PAGE HEADER-->

				<!-- BEGIN PAGE BAR -->
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
						<li><a href="#" class="blueFont">${iv_title}</a> <i class="fa fa-circle"></i></li>
					</ul>

				</div>
				<!-- END PAGE BAR -->

				<!-- END PAGE HEADER-->

				<div class="row">
				  <div class="col-md-12">
					<h4 class="fontBlue">${history_pointline }</h4>
                          <div class="portlet-body form " style="margin-top: 25px;">
                                 <form id="form_1" class="form-inline">
						          <div class="portlet-title">
                                    <div class="caption font-dark">
                                          <button type="button" class="btn green" data-target="#stack1" data-toggle="modal"  style="margin-top: 5px;margin-bottom: 10px;"> ${iv_number_select} </button> 
                                    </div> 
                                </div>
						              
<!-- 						              <div class="input-group input-large date-picker input-daterange"> -->
<!-- 							                 <select class="form-control select5me" id="childselect" onchange="changeLine()"> -->
<!-- 							                 </select> -->
<!-- 						             	 </div>&nbsp;&nbsp; -->
<%--                                         <button type="button" onclick="submitAddPoint();" class="btn btn-success">${inverter_search}</button>  --%>
                                 </form>         
                             </div>
					<div id="electLine" style="display:block; height:350px" class='electCurve'></div>
					<div class="portlet-body">
							<table class="table table-striped greenTable-ynz table-bordered table-hover" id="ivtable">
							</table>
					</div>
					</div>
					<div id="stack1" class="modal fade"  data-focus-on="input:first" >
                                     <div class="modal-dialog">
                              			   <div class="modal-content">
                              			    <div class="modal-header">
				                                            <button id="stack1closBtn" type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				                                            <h4 class="modal-title">${weather_select_inductor}</h4>
				                                        </div>
				                    <div class="portlet-body form">
                                    <!-- BEGIN FORM-->

									<form action="###" class="form-horizontal form-row-seperated">
										<div class="form-body">
											<div class="form-group">
												<label class="control-label col-md-2">${weather_inductor}</label>
												<div class="col-md-10">
													<select multiple="multiple" class="multi-select"
														id="select1" name="my_multi_select1[]">
													</select>
												</div>
											</div>
										</div>
										<div class="form-actions">
											<div class="row">
												<div class="col-md-offset-4 col-md-9">
													<button type="button" class="btn green" id="checkdata">
														<i class="fa fa-check"></i>${weather_screen}</button>
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
				</div>
			</div>
			<!-- END CONTENT BODY -->
		</div>
		<!-- END CONTENT -->

	</div>
	<!-- END CONTAINER -->

	<!-- BEGIN CORE PLUGINS -->
	<%@ include file="/page/common/pubJs.jsp"%>
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${cdnPath}global/scripts/datatable.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/echarts/echarts.js?version=20170817024514" type='text/javascript'></script>
    <!-- END PAGE LEVEL PLUGINS -->
<script src="${basePath}/assets/pages/scripts/ivequipment/ivequipment.js"  type="text/javascript"></script>
<script src="${cdnPath}/global/plugins/jquery-multi-select/js/jquery.multi-select.js" type="text/javascript"></script>
		        <!-- END PAGE LEVEL PLUGINS -->
</body>

</html>
