<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/page/common/pub.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="com.yunengzhe.PVMTS.domain.vo.CurveDetailVO"%>
<%
CurveDetailVO curveDetailVO = (CurveDetailVO)request.getAttribute("CurveDetailVO");
if(curveDetailVO!=null){
	pageContext.setAttribute("curvejson", com.yunengzhe.commons.util.JsonUtil.beanToJson(curveDetailVO));
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
        <title>${menu_customline } | ${report_curveinfo } </title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <!-- BEGIN GLOBAL MANDATORY STYLES --> 
        <%@ include file="/page/common/pubCss.jsp" %>
        <style>
        	blockquote{
        		font-size:14px;
        	}
        	.table{
        		text-align:center;
        	}
        	.table thead th{
        		text-align:center;
        		border:0;
        		background:#C8DAE9;
        	}
        	.table-striped>tbody>tr:nth-of-type(even) {
			    background-color: #E1EBF3;
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
                            <li>
                                <a class="blueFont" href="#">${menu_customline }</a>
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
                                            <span class="caption-subject font-green bold uppercase">${report_curveinfo }</span>
                                        </div>
                                        
                                    </div>
                                    <div class="portlet-body">  
                                        <blockquote>
						                  ${curve_name }：<input id="pointName"  readonly Style="border:none;width:140px;"></input>
						                  ${curve_firstYaxisName }：<input id="pointLeft"  readonly Style="border:none;width:140px;"></input>
						                  ${curve_secondYaxisName }：<input id="pointRight" readonly Style="border:none;width:140px;"></input>
						                  ${curve_timeSpan }：<input id="pointSpan"  readonly Style="border:none;width:140px;"></input>
                                        </blockquote> 
                                    </div>
                         </div>
                          <div class="portlet light bordered">
                               <h4 class="tableBt borderRadius5">${history_pointline }</h4>
                               <div class="portlet-body">
                                     <div id="echarts_line" style="height:300px;"></div>
                               </div><!-- 曲线展示end -->
                               <h4 class="tableBt borderRadius5">${curve_pointlist }</h4>
                               <table class="table table-striped table-bordered table-hover order-column" id="sample_1">
                                    <thead class="flip-content">
                                       <tr>  
                                           <th class="numeric"> ${curve_pointname}</th>
                                           <th class="numeric"> ${curve_pointxy} </th>
                                           <th class="numeric"> ${curve_pointcolor} </th>
                                       </tr>
                                   </thead>
                                   <tbody>
                                   <c:forEach items="${CurveDetailVO.pointList}" var="point"> 
                                        <tr>  
                                            <td class="numeric">${point.pointName}</td>
                                            <td class="numeric">${point.yaxisType==0?curve_leftline:curve_rightline}</td> 
                                            <td class="numeric"><div style='width: 30px;height: 15px;display:inline-block;background-color:${point.colorCode}' ></div>${point.colorCode}</td> 
                                        </tr> 
                                    </c:forEach>
                                   </tbody>
                               </table>
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
<script src="${basePath}/assets/pages/scripts/curve/curve-detail.js?version=20170817024514" type='text/javascript'></script>
        <!-- END PAGE LEVEL SCRIPTS -->
        <script type="text/javascript">
      		var curveInfo = ${curvejson};
      	</script>
    </body>

</html>
