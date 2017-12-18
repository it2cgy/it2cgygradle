<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/page/common/pub.jsp"%>

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
<link href="${basePath}assets/pages/css/ynz/common/static.css" rel="stylesheet" type="text/css" />
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
		<%@ include file="/page/common/companyAdminMenu.jsp"%>
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
						<li><a href="#" class="blueFont">${iv_title}</a> <i class="fa fa-circle"></i></li>
					</ul>

				</div>
				<!-- END PAGE BAR -->

				<!-- END PAGE HEADER-->

				<div class="row">
				  <div class="col-md-12">
					<h4 class="fontBlue">${history_pointline }</h4>
					<div id="electLine" style="display:block; height:350px" class='electCurve'></div>
					<div class="portlet-body">
							<table class="table table-striped greenTable-ynz table-bordered table-hover" id="ivtable">
							</table>
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
<%--     <script src="${cdnPath}global/plugins/echarts/echarts.js" type="text/javascript"></script> --%>
<script src="${basePath}assets/pages/scripts/echarts-all.js?version=20170817024514" type='text/javascript'></script>
    <!-- END PAGE LEVEL PLUGINS -->
	<script
		src="${basePath}/assets/pages/scripts/ivequipment/ivequipmentThird.js"
		type="text/javascript"></script>
	<script type="text/javascript">
		 var isThird="${isThird}";
	</script>
</body>

</html>
