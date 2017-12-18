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
<title>${curve_listtitle}</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<%@ include file="/page/common/pubCss.jsp"%>
<style type="text/css">
.table-bordered>tbody>tr>td, .table-bordered>thead>tr>th {
	border: 0;
}

.secondTable>tbody>tr>td, .secondTable>thead>tr>th {
	border: 1px solid #f0e9ea;
}

.secondTable {
	border: 1px solid #f0e9ea !important;
	color: #20110d;
}

.secondTable tbody tr {
	text-align: center;
}

.secondTable thead tr th {
	background: rgba(244, 158, 158, 0.63);
}

.greenTable-ynz .even {
	background: rgba(133, 170, 184, 0.23)
}
.installClass {
	padding:10px;
	background-color:#32c5d2;
	width:50%;
}
.installClass-iv {
	padding:10px;
	width:50%;
}
.colorWhite{
	color:#fff;
}
.colorBlack{
	color:#000;
}
#installTbody{
    display: block;
    height: 330px;
    overflow-y: scroll;
}
#installConfigtable thead,#installTbody tr{
	table-layout: fixed;
    width: 100%;
    display: table;
}
#installConfigtable thead{
	width:cacl(100% - 60rem);
} 
#installTbody input{
    width: 100%;
    text-align: center;
}
</style>
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
			<%@ include file="/page/common/companyThirdMenu.jsp"%>
		</c:if>
		<c:if test="${longiebRoleId!=4}">
			<%@ include file="/page/common/companyAdminMenu.jsp"%>
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
						<li><c:if test="${longiebRoleId==4}">
								<a href="${subIndex}"><i18n:message name="index_page" /></a>
							</c:if> <c:if test="${longiebRoleId!=4}">
								<a href="${basePath}index/${powerStationId}.do"><i18n:message
										name="index_page" /></a>
							</c:if> <i class="fa fa-circle"></i></li>
						<li><a class="blueFont" href="#">${data_find_list}</a> <i
							class="fa fa-circle"></i></li>
					</ul>

				</div>
				<!-- END PAGE BAR -->

				<!-- END PAGE HEADER-->

				<div class="row">
					<div class="col-md-12">
						<div class="portlet light bordered">
							<div class="portlet-title">
							<div class="caption font-dark">
								<c:if
									test="${(menuConfig['5'].handle_premission!=null && menuConfig['5'].handle_premission==1)||(longiebRoleId==4)}">
										<button id="addUserButton" class="btn sbold green">${data_add_search}
											<i class="fa fa-plus"></i>
										</button>
									</c:if>
								<c:if
									test="${(menuConfig['27'].handle_premission!=null && menuConfig['27'].handle_premission==1)||(longiebRoleId==4)}">
										<button type="button" id="configInstall" class="btn green" data-target="#stack1"
										   data-toggle="modal" > ${installed_capacity_config}	
										   <i class="fa fa-cog"></i>
									   </button>
								 </c:if>
							</div>
                                    &nbsp;&nbsp;
                                   
								<div class="caption font-dark" style="float: right;">
									<form class="form-inline" role="form">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <label
											style="font-size: 14px;">${curve_name } :</label>&nbsp;&nbsp;&nbsp;<input
											style="width: 125px !important" id="curveNameCondition"
											type="text" value="${usernameKey }" class="form-control">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<label style="font-size: 14px;">${user_createuser } :</label>&nbsp;&nbsp;&nbsp;<input
											style="width: 125px !important" id="createUserCondition"
											type="text" value="${nicknameKey }" class="form-control">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;
										<button type="button" id="serach" onclick="searchCurveList()"
											class="btn sbold green">${query }</button>
									</form>
								</div>
								<div class="tools"></div>
							</div>
							<div class="portlet-body">
								<!-- <table class="table table-striped table-bordered table-hover" id="sample_1">
                                    <table class="table table-striped table-bordered table-hover dt-responsive" width="100%" id="sample_2">
                                    		
                                    </table> -->
								<table
									class="greenTable-ynz table table-striped table-bordered table-hover dt-responsive"
									width="100%" id="sample_1">
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- END EXAMPLE TABLE PORTLET-->
			<!-- END CONTENT BODY -->
		</div>
		<!-- END CONTENT -->
		<div id="stack1" class="modal fade" data-focus-on="input:first">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button id="stack1closBtn" type="button" class="close"
							data-dismiss="modal" aria-hidden="true"></button>
					</div>
					<div class="portlet-body form">
						<!-- BEGIN FORM-->

						<form action="###" class="form-horizontal form-row-seperated">
							<div class="form-body">
								<div class="col-md-12" style="margin: 10">
		               				<ul id="ul_display" class="equipUl whiteBg">
			               				<li class="installClass" style="float:left;" id="li_1"><i></i><a href="javascript:;" id="i_1" class="colorWhite" onclick="changeTab(1)">归一化装机容量设置</a></li>
			               				<li class="installClass-iv" style="float:right;"  id="li_0"><i></i><a href="javascript:;" id="i_2" class="colorBlack" onclick="changeTab(2)" >IV多通道装机容量设置</a></li>
			               			</ul>
	               				</div>
	               					<div class="caption font-dark" style="margin-left: 23px">
										<button id="updateInstallButton" type="button"  onclick="updateInstall()"class="btn sbold green">${update}
										</button>
									</div>
							</div>
							<div class="portlet-body" style="
							    height: 400px;
							    /* background: red; */
							    overflow-y: auto;
							">
								<table
									class="greenTable-ynz table table-striped table-bordered table-hover dt-responsive"
									width="100%" id="installConfigtable">
									<thead>
										<th style="background: #fff!important;color: #000!important;font-size:15px;">发电单元编号</th>
										<th style="background: #fff!important;color: #000!important;font-size:15px;">装机容量</th>
									</thead>
									<tbody id="installTbody">
									</tbody>
								</table>
							</div>
							<div class="form-actions">
								<div class="row">
									<div class="col-md-offset-4 col-md-9">
										<button type="button" class="btn green" id="saveRate">
											<i class="fa fa-check"></i>${confirmUpdate}</button>
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
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script
		src="${basePath}/assets/pages/scripts/data/data-list.js?version=20170817024514"
		type='text/javascript'></script>
	<!-- END PAGE LEVEL SCRIPTS -->
	<script type="text/javascript">
        	var menuConfig = "${menu5}";
        	var menuInstall = "${menu27}";
        </script>
</body>

</html>
