<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/page/common/pub.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="com.yunengzhe.PVMTS.domain.po.ReportsPO"%>
<%
ReportsPO reportsPO = (ReportsPO)request.getAttribute("result");
if(reportsPO!=null){
	pageContext.setAttribute("reportsjson", com.yunengzhe.commons.util.JsonUtil.beanToJson(reportsPO));
}
%>

<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->

    <head>
        <meta charset="utf-8" />
        <title>${report_reportdetails} </title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <!-- BEGIN GLOBAL MANDATORY STYLES --> 
        <%@ include file="/page/common/pubCss.jsp" %>
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
                                <a href="${basePath}compantUser/userListPage" >${report_reportmanager}</a>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <a href="#">${report_detail}</a>
                                <i class="fa fa-circle"></i>
                            </li>
                        </ul>
                        
                    </div>
                    <!-- END PAGE BAR -->
                    
                    <!-- END PAGE HEADER-->
                    
                     <div class="row">
	                  <div class="portlet light bordered">
                                    <div class="portlet-title">
                                        <div class="caption">
                                            <i class="icon-social-dribbble font-green"></i>
                                            <span class="caption-subject font-green bold uppercase">${report_curveinfo}</span>
                                        </div>
                                        
                                    </div>
                                    <div class="portlet-body">  
                                        <blockquote>
                                            <p>${report_reportname}：${result.reportname}</p> 
                                            <p>${report_starttime}：${result.starttime}</p> 
                                            <p>${report_endtime}：${result.endtime}</p> 
                                        </blockquote> 
                                    </div>
                         </div>
                          <div class="portlet light bordered">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="icon-bulb font-green"></i>
                                        <span class="caption-subject font-green bold uppercase">${report_curve}</span>
                                    </div> 
                                </div>
                                <div class="portlet-body">
                                      <div id="echarts_bar" style="height:500px;"></div>
                                </div>
                            </div>
                            
                          <div class="portlet light bordered">
                          
<!--                               直流发电参数 -->
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="icon-bulb font-green"></i>
                                        <span class="caption-subject font-green bold uppercase">${report_sightinfo}</span>
                                    </div> 
                                </div>
                                <div class="portlet-body">
                                    <table class="table table-striped table-bordered table-hover order-column" id="sample_1">
                                   
                                         <thead class="flip-content">
                                            <tr>  
                                                <th class="numeric"> ${report_sightname}</th>
                                                <th class="numeric"> ${report_sightcolor}</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${result.pointList}" var="point"> 
	                                            <tr>  
	                                                <td class="numeric">${point.analoginputName}</td>
	                                                <td class="numeric"><div style='width: 30px;height: 15px;display:inline-block;background-color:${point.analoginputColor}' ></div>${point.analoginputColor}</td> 
	                                            </tr> 
                                         </c:forEach>
                                        </tbody>
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
        <%@ include file="/page/common/pubJs.jsp" %>
        <!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${cdnPath}global/scripts/datatable.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
<script src="${cdnPath}global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL PLUGINS -->
<script src="${cdnPath}global/plugins/echarts/echarts.js?version=20170817024514" type='text/javascript'></script>
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${basePath}/assets/pages/scripts/report/reportDetails.js?version=20170817024514" type='text/javascript'></script>
        <!-- END PAGE LEVEL SCRIPTS -->
        <script type="text/javascript">
      		var reportInfo = ${reportsjson};
      	</script>
    </body>

</html>
