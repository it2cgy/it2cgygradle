<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/page/common/pub.jsp"%>
<%@ include file="/page/common/pubThird.jsp"%>

<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->

<head>
<meta charset="utf-8" />
<title>${log_loglisttitle}</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<%@ include file="/page/common/pubCss.jsp"%>
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
	<div class="page-container">
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
						<li><a href="${subIndex}"><i18n:message
									name="index_page" /></a> <i class="fa fa-circle"></i></li>
						<li><span class="blueFont">${log_captionlist}</span></li>
					</ul>

				</div>
				<!-- END PAGE BAR -->

				<!-- END PAGE HEADER-->

				<div class="row">
					<div class="col-md-12">
						<!-- BEGIN EXAMPLE TABLE PORTLET-->
						<div class="portlet light bordered">
							<div class="portlet-title">
								<c:if test="${menuConfig['1'].handle_premission!=null && menuConfig['1'].handle_premission==1 && isThird==false}">
									<div class="caption font-dark">
										<button id="addLog" class="btn sbold green" onClick="addlogs()">
											${menu_logadd} <i class="fa fa-plus"></i>
										</button>
									</div>
								</c:if>
								<div class="tools"></div>
								 <div class="actions">
                                   <form class="form-inline" role="form"> 
                                    <input type="text"  id="topic" name="topic" class="form-control" placeholder="${log_theme}">
                                    <input type="text"  id="discription" name="discription" class="form-control" placeholder="${log_comprehensive}">
                                    <select name="category" id="category" class="form-control select3me">
                                    	<option value="">---${log_pleaselecategory}---</option>
                                    	<c:forEach items="${category}" var="category">
                                    		<option value="${category.id}">${category.category}</option>
				                     	</c:forEach>
                                    </select>
                                    <select name="powerStationId" id="powerStationId" class="form-control select3me">
                                    	<option value="">---${curve_select_powerStation}---</option>
                                    	<c:forEach items="${powerStations}" var="power">
                                          <option value="${power.id}">${power.name}</option>
                                        </c:forEach>
                                    </select>
                                    <select name="equipmentId" id="equipmentId" class="form-control select3me">
                                    					<option value="">---${curve_pleaseselectequipmenttype}---</option>
                                                        <option value="3">${inverter_inverter}</option>
                                                        <option value="5">${log_meter}</option>
                                                        <option value="6">${log_gridark}</option>
                                                        <option value="8">${log_weather }</option>
                                                        <c:if test="${longiebRoleId!=4}">
                                                        <option value="9">${log_axis}</option>
                                                        </c:if>
                                                        <option value="10">${log_IV }</option>
                                    </select>
                                    <select name="equipment" id="equipment" class="form-control select3me">
                                    	<option value="">---${curve_pleaseselectequipmentnumber}---</option>
                                    </select>
                                    <button type="button" id="soursebutton" class="btn sbold green" onclick="searchdata()"> ${query }</button>
                                  </form> 
                                </div>
							</div>
							<div class="portlet-body" style='white-space:nowrap'>
								<table class="greenTable-ynz table table-striped table-bordered table-hover" id="sample_1">
								</table>
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
	<script src="${cdnPath}global/scripts/datatable.js"
		type="text/javascript"></script>
	<script src="${cdnPath}global/plugins/datatables/datatables.min.js"
		type="text/javascript"></script>
	<script
		src="${cdnPath}global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js"
		type="text/javascript"></script>
	<script
		src="${cdnPath}global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"
		type="text/javascript"></script>
	<!-- END PAGE LEVEL PLUGINS -->

	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="<%=basePath %>/assets/pages/scripts/logs/logs-list.js"
		type="text/javascript"></script>
	<!-- END PAGE LEVEL SCRIPTS -->
 	<script type="text/javascript">
        	var menuConfig = "${menu1}";
        	var thirdPowerStationId = "${thirdPowerStationId}"
     </script>
</body>

</html>
