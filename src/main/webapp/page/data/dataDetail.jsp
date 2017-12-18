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
        <link href="${cdnPath}global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />
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
			.portlet-body{
				overflow: hidden;
   				position: relative;
			}
			.tabBox{
				float: left;
				line-height: 34px;
				margin-left: 40px;
				margin-right: 90px;
			}
			.btnBox{
				position:absolute;
				right:0;
			}
			
			@media only screen and (min-width: 1600px) and (max-width: 1680px) {
				.tabBox{
					margin-right: 50px;
				}
			}
			@media only screen and (min-width: 1441px) and (max-width: 1599px) {
				.tabBox{
					margin-left:0;
					margin-right:50px;
				}
			}
			@media only screen and (min-width: 1300px) and (max-width: 1440px) {
				.tabBox{
					margin-left:0;
					margin-right:0;
				}
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
                               <li>
                                <a href="${basePath}dataPage/dataList.do?powerStationId=${powerStationId}" ><i18n:message name="data_find_list" /></a>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <a class="blueFont" href="#">${data_search }</a>
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
                                     <span class="caption-subject font-green bold uppercase">${data_search }</span>
                                 </div> 
                             </div>
                             <div class="portlet-body">  
                                 <blockquote>
					                  ${curve_name }：<input id="pointName"  readonly Style="border:none;width:140px;"></input>
					                  ${curve_firstYaxisName }：<input id="pointLeft"  readonly Style="border:none;width:140px;"></input>
					                  ${curve_secondYaxisName }：<input id="pointRight" readonly Style="border:none;width:140px;"></input>
					                  <%-- ${curve_timeSpan }：<input id="pointSpan"  readonly Style="border:none;width:140px;"></input> --%>
                                 </blockquote> 
                             </div>
                         </div>
                          <div class="portlet light bordered">
                               <h4 class="tableBt borderRadius5">${curve_pointlist }</h4>
                               <table class="table table-striped table-bordered table-hover order-column" id="sample_1">
                                    <thead class="flip-content">
                                       <tr>  
	                                       <th class="numeric"> ${equipment_type}</th>
	                                       <th class="numeric"> ${equipment_num}</th>
                                           <th class="numeric"> ${curve_pointname}</th>
                                           <th class="numeric"> ${curve_pointxy} </th>
                                           <th class="numeric"> ${curve_pointcolor} </th>
                                       </tr>
                                   </thead>
                                   <tbody>
                                   <c:forEach items="${CurveDetailVO.pointList}" var="point"> 
                                        <tr>  
	                                        <td class="numeric">${point.equipmentName}</td>
	                                        <td class="numeric">${point.equipmentNumber}</td>
                                            <td class="numeric">${point.pointName}</td>
                                            <td class="numeric">${point.yaxisType==0?curve_leftline:curve_rightline}</td> 
                                            <td class="numeric"><div style='width: 30px;height: 15px;display:inline-block;background-color:${point.colorCode}' ></div>${point.colorCode}</td> 
                                        </tr> 
                                    </c:forEach>
                                   </tbody>
                               </table>
                               <h4 class="tableBt borderRadius5">${history_query }</h4>
                               <div class="portlet-body">  
	                               <div class="tabBox">
	                                   <%-- <label  class="control-label">${inverter_show_data}
	                                      <span class="required"> * </span>
	                                  	</label --%>
	                                    <label class="mt-radio">
	                                          <input type="radio" name="dataType" onclick='powerModel();' id="curveType" value="0" checked> ${current_value }
	                                          <span style="margin-top: 7px"></span>
	                                      </label>&nbsp;&nbsp;
	                                      <label class="mt-radio">
	                                          <input type="radio" name="dataType" onclick='generatModel();' id="curveType" value="1"> ${history_value}
	                                          <span style="margin-top: 7px">&nbsp;&nbsp;</span>
	                                      </label> &nbsp;&nbsp;
	                               </div> 
	                               <div id="historyCondition" style="display:none;">
		                               <div id='powerModelDiv' style='display:block;float: left;line-height: 34px;'>
		                                   <label class="control-label">${inverter_search_time}
		                                       <span class="required"> * </span>  
		                                   </label>
	                                       <div class="input-group input-large date-picker input-daterange" data-date="2017-06-28" data-date-format="yyyy-mm-dd" style="float: right;" >
	                                               <input type="text" class="form-control" id="startTime" name="startTime" >
	                                               <span class="input-group-addon"> ${inverter_to} </span>
	                                               <input type="text" class="form-control" id="endTime" name="endTime" > 
	                                        </div>&nbsp;&nbsp; 
		                               </div>
		                               <div class="col-md-4">
	                                     	    <label class="control-label floatL" style="margin-top: 12px;">${timeSpan }
	                                                <span class="required"> * </span>
	                                            </label>
	                                            <div class="col-md-8">
	                                                <div class="input-group input-large date-picker input-daterange" data-date="2017-06-28" data-date-format="yyyy-mm-dd" style="width: 100%!important;">
	                                        			 <select class="form-control select3me" id="timeInterval" disabled="disabled">
	                                        			    <option value="1">1${minute }</option>
	                                                        <option value="10">10${minute }</option>
	                                                        <option value="60">60${minute }</option>
	                                               	 	</select>
	                                             	</div>
	                                         	</div>
	                                     </div>
	                                   <div class="form-actions" style="float: right;margin-bottom: 15px;padding: 10px 10px 0 0;">
			                                    <div class="pull-right btnBox">
			                                        <div  style="text-align:center;margin-top: -11px;">
			                                           <button id="searchbutton" type="submit" onclick="searchbtn();" disabled="disabled" class="btn btn-success" ><i18n:message name="query"/></button>
			                                            <button type="button" class="btn green"  data-toggle="modal" disabled="disabled"  id="createreport"><i18n:message name="history_createreport"/></button>
			                                            <button type="button" class="btn green" disabled="disabled" id="downloadreport"><i18n:message name="history_downloadreport"/></button>
			                                        </div>
			                                    </div>
			                             </div>
	                               </div>
	                               </div>
                               <div class="clearfix"></div> 
                               <div>
	                               <h4 class="tableBt borderRadius5">${history_showline }</h4>
	                               <div class="portlet-body">
	                                     <div id="echarts_line" style="height:300px;"></div>
	                               </div><!-- 曲线展示end -->
                               </div>
                               <div id="showbar" style="display:none;">
	                               <h4 class="tableBt borderRadius5">${history_showbar }</h4>
	                               <div class="portlet-body">
	                                     <div id="echarts_bar" style="height:300px;"></div>
	                               </div><!-- 曲线展示end -->
                               </div>
                               <div id="showlist" style="display:none;">
	                               <h4 class="tableBt borderRadius5">${history_showlist }</h4> 
									 
                               </div>
                               
                          </div>
                        </div>
                    </div>        
                <!-- END CONTENT BODY -->
            </div>
            <!-- END CONTENT -->
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
        </div>
        <!-- END CONTAINER -->
        
        <!-- BEGIN CORE PLUGINS -->
        <%@ include file="/page/common/pubJs.jsp" %>
        <!-- BEGIN PAGE LEVEL PLUGINS -->
		<script src="${cdnPath}global/scripts/datatable.js" type="text/javascript"></script>
		<script src="${cdnPath}global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
		<script src="${cdnPath}global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
		<script src="${cdnPath}global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
		<script src="${cdnPath}global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
		<!-- END PAGE LEVEL PLUGINS -->
		<script src="${cdnPath}global/plugins/echarts/echarts.js?version=20170817024514" type='text/javascript'></script>
		<!-- BEGIN PAGE LEVEL SCRIPTS -->
		<script src="${basePath}/assets/pages/scripts/data/data-detail.js?version=20170817024514" type='text/javascript'></script>
        <!-- END PAGE LEVEL SCRIPTS -->
        <script type="text/javascript">
      		var curveInfo = ${curvejson};
      	</script>
    </body>

</html>
