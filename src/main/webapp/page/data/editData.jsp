<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/page/common/pub.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="com.yunengzhe.PVMTS.domain.vo.CurveDetailVO"%>
<%
CurveDetailVO curveDetailVO = (CurveDetailVO)request.getAttribute("curveDetailVO"); 
%>
<%@ include file="/page/common/pubThird.jsp" %>
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
         <script>
        var curveDetailVO=<%=com.yunengzhe.commons.util.JsonUtil.beanToJson(request.getAttribute("curveDetailVO")) %>
        </script>
        <style>
        	.equipType{
    			margin-right: 5px;
        		float:left;
        		font-size: 14px;
        	}
        	.equipSort{
        		font-size: 16px;
        		text-align:center;
        		height:30px;
        		
        	}
        	.pointList{
        		overflow: hidden;
			    background: red;
			    height: 30px;
        	}
        	.points{
        		border:1px solid #eee;
        	}
        	.tableBorder{
        		border:1px solid #eee;
        	}
        	.borderlR{
        		border-left:1px solid #eee;
        		border-right:1px solid #eee;
        	}
        	.borderR{
        		border-right:1px solid #eee;
        	}
        	.pointListTable table.dataTable tbody th, 
        	.pointListTable table.dataTable tbody td {
			    padding: 0px 9px;
			    vertical-align: middle;
			}
			.pointListTable table.dataTable thead th,
			.pointListTable table.dataTable thead td {
			   width:200px;
			}
			.modal-content table td{
				white-space:nowrap;
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
            <!-- BEGIN SIDEBAR -->
            <div class="page-sidebar-wrapper">
                <!-- BEGIN SIDEBAR -->
                <c:if test="${longiebRoleId==4}">
             <%@ include file="/page/common/companyThirdMenu.jsp" %>
            </c:if>
            <c:if test="${longiebRoleId!=4}">
             <%@ include file="/page/common/companyAdminMenu.jsp" %>
            </c:if>
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
                                <a class="blueFont" href="#">${data_edit_search }</a>
                                <i class="fa fa-circle"></i>
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
                                        <span class="caption-subject font-dark sbold uppercase"><i18n:message name="data_edit_search"/></span>
                                    </div>
                                </div>
                                <div class="portlet-body">
                                
                                    <form id="form_1" class="form-horizontal">
                                    <!-- BEGIN FORM-->
                                        <div class="form-body">
                                            <div class="alert alert-danger display-hide">
                                                
                                                <button class="close" data-close="alert"></button> ${alert_error } </div>
                                            <div class="alert alert-success display-hide">
                                                
                                                <button class="close" data-close="alert"></button> ${alert_success} </div><input hidden="hidden" id="id" name="id" value="${curveDetailVO.id}">
                                           <div class="form-group">
                                                <label class="control-label col-md-3"><i18n:message name="curve_name" />
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                     <input type="text" id="name" name="name" data-required="1" class="form-control" value="${curveDetailVO.name}"></input>
                                                </div>
                                            </div>
                                         
                                             <div class="form-group" id="yaxisgroup">
                                               
                                                <label class="control-label col-md-3">${curve_lines}
                                                    <span class="required"> * </span>
                                                </label>
	                                           <div class="col-md-4">
	                                               <select class="form-control select5me" id="type" name="type">
	                                               		<option value="1" ${curveDetailVO.type==1?'selected':''}>${curve_onelines}</option>
		                                             	  <option value="2" ${curveDetailVO.type==2?'selected':''}>${curve_twolines}</option>  
                                                    </select>
                                           		 </div>
                                         	</div>
                                           <div class="form-group">
                                                <label class="control-label col-md-3"><i18n:message name="curve_firstYaxisName" />
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                    <input type="text" id="firstYaxisName" name="firstYaxisName" data-required="1" class="form-control" value="${curveDetailVO.firstYaxisName}"></input>
                                                 </div>
                                             </div> 
                                               <c:if test="${curveDetailVO.secondYaxisName!=''&&curveDetailVO.secondYaxisName!=null}">
                                               <div class="form-group" id="curvesecond">
                                                <label class="control-label col-md-3"><i18n:message name="curve_secondYaxisName" />
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                    <input type="text" id="secondYaxisName" name="secondYaxisName" data-required="1" class="form-control" value="${curveDetailVO.secondYaxisName}"></input>
                                                 </div>
                                             </div>
                                             </c:if>
                                             <c:if test="${curveDetailVO.secondYaxisName==''||curveDetailVO.secondYaxisName==null}">
                                               <div class="form-group" id="curvesecond" style="display: none">
                                                <label class="control-label col-md-3"><i18n:message name="curve_secondYaxisName" />
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                    <input type="text" id="secondYaxisName" name="secondYaxisName" data-required="1" class="form-control" value="${curveDetailVO.secondYaxisName}"></input>
                                                 </div>
                                             </div>
                                             </c:if>
                                             <div class="form-group">
                                                <label class="control-label col-md-3"><i18n:message name="curve_timeSpan" />
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-4">
                                                	<select id="timeSpan" name="timeSpan" class="form-control select5me">
                                                		<option value="1" ${curveDetailVO.timeSpan==1?'selected':''}>1</option>
                                                		<option value="10" ${curveDetailVO.timeSpan==10?'selected':''}>10</option>
                                                		<option value="60" ${curveDetailVO.timeSpan==60?'selected':''}>60</option>
                                                	</select>
                                                 </div>
                                             </div>
                                             
                                  <div class="form-group">
			                      	  <label class="control-label col-md-3">${curve_point}
                                          <span class="required"> </span>
                                      </label>
	                                    <div class="col-md-6">
	                                     	<%-- <button type="button" class="btn green" data-target="#stack1" data-toggle="modal" style="margin-bottom:10px;">${curve_newpoint}</button>  --%>
	                                     	<button type="button" class="btn green"  onClick="refresh();"  style="margin-top: 5px;margin-bottom: 10px;">${curve_newpoint}</button>
		                                     <table class="table table-striped table-hover table-bordered" id="sample_editable_1" >
		                                        <thead>
		                                            <tr>
		                                           		 <th> ${equipment_type}</th>
	                                     				  <th> ${equipment_num}</th>
		                                                <th> ${curve_point} </th>
		                                                <th> ${curve_pointcolor} </th>
		                                                <th> ${curve_ownline} </th>
		                                                <th> ${action} </th>
		                                            </tr>
		                                        </thead>
		                                         <tbody id="pointSelectId" style="position:relative;" class="table tbody">
		                                          <c:forEach items="${curveDetailVO.pointList}" var="item">
	                                                  <tr>
	                                                        <td>${item.equipmentName }</td>
	                                                        <td>${item.equipmentNumber }</td>
	                                                        <td>${item.pointName }</td>
	                                                        <td><div style='width: 30px;height: 15px;display:inline-block;background-color:${item.colorCode };' >
                                                            </div> <span>${item.colorCode }</span></td>
	                                                        <td>${item.yaxisType==0? curve_leftline:curve_rightline} </td> 
	                                                        <td><a onclick='deleterow(this,${item.analoginputId});'>${del}</a></td>
                                                    </tr>
                                                </c:forEach>
		                                       
		                                      
		                                        </tbody>
		                                      </table>
	                                      </div>
                                </div>
		                                 <!--  stackable -->
			                                       
                                       		  <div class="form-actions">
	                                            <div class="row">
	                                                <div class="col-md-offset-3 col-md-9"> 
	                                                    <button class="btn btn-success" type="submit"><i18n:message name="submit" /></button>
	                                                     <a href="${basePath}dataPage/dataList.do?powerStationId=${powerStationId}"><button type="button" class="btn default"><i18n:message name="cancel" /></button> </a> 
	                                                </div>
	                                            </div>
	                                        </div>
	                                        
                                        </div>
	                                     
                                    </form>
                                    <!-- END FORM-->
                                    <!--添加测点弹窗 -->
                                    <div class="modal fade in" id="stack1" tabindex="-1" role="basic" aria-hidden="true" style="display: none; padding-right: 17px;">
					                    <div class="modal-dialog">
					                        <div class="modal-content borderRadius5" style="width: 1030px;left:50%;margin-left:-500px;">
					                            <div class="modal-header">
					                                <button id="stack1Close" type="button" onclick="closeTab();" class="close" data-dismiss="modal" aria-hidden="true"></button>
					                                <h4 class="modal-title">${curve_newpoint}</h4>
					                            </div>
					                            <div class="modal-body" style="overflow: hidden;">
					                            	<form id="form_2" class="form-horizontal">
					                            		<div class="equipType">
						                            		<div class="tableBorder equipType" style="padding:0 14px;height: 270px;overflow-y: auto;">
							                            		<table>
								                            		<thead>
							                            				<tr>
							                            					<th></th>
							                            					<th style="font-size:17px;text-align:center;border-left:1px solid #eee;padding-left: 12px;">${log_equipmentId}</th>
							                            				</tr>
							                            			</thead>
							                            			<tbody id="equipmentTepyTable">
								                            			<!-- <tr>
								                            				<td class="mt-radio-list borderR"><label class="mt-radio mt-radio-outline"><input type="radio" value="1" name="test"><span></span></label></td>
								                            				<td style="padding-left: 12px;">逆变器</td>
								                            			</tr>
								                            			<tr>
								                            				<td class="mt-radio-list borderR"><label class="mt-radio mt-radio-outline"><input type="radio" value="1" name="test"><span></span></label></td>
								                            				<td style="padding-left: 12px;">电表</td>
								                            			</tr>
								                            			<tr>
								                            				<td class="mt-radio-list borderR"><label class="mt-radio mt-radio-outline"><input type="radio" value="1" name="test"><span></span></label></td>
								                            				<td style="padding-left: 12px;">并网柜</td>
								                            			</tr>
								                            			<tr>
								                            				<td class="mt-radio-list borderR"><label class="mt-radio mt-radio-outline"><input type="radio" value="1" name="test"><span></span></label></td>
								                            				<td style="padding-left: 12px;">气象站</td>
								                            			</tr>
								                                    	<tr>
								                            				<td class="mt-radio-list borderR"><label class="mt-radio mt-radio-outline"><input type="radio" value="1" name="test"><span></span></label></td>
								                            				<td style="padding-left: 12px;">IV 设备</td>
								                            			</tr> -->
								                            		</tbody>
							                            		</table>
						                            		</div>
					                            		</div>
					                            		<div class="equipType">
						                            		<div class="tableBorder" style="padding:0 15px;max-height: 270px;overflow-y: auto;">
							                            		<table>
							                            			<thead>
							                            				<tr>
							                            					<th></th>
							                            					<th style="font-size:17px;text-align:center;border-left:1px solid #eee;">${equipment_num}</th>
							                            				</tr>
							                            			</thead>
							                            			<tbody  id="equipmentListTable">
								                            			<!-- <tr>
								                            				<td class="mt-checkbox-list borderR"><label class="mt-checkbox mt-checkbox-outline"><input type="checkbox" value="1" name="test"><span></span></label></td>
								                            				<td style="padding-left: 12px;">#1-NBQ1</td>
								                            			</tr>
								                            			<tr>
								                            				<td class="mt-checkbox-list borderR"><label class="mt-checkbox mt-checkbox-outline"><input type="checkbox" value="1" name="test"><span></span></label></td>
								                            				<td style="padding-left: 12px;">#2-NBQ1</td>
								                            			</tr>
								                            			<tr>
								                            				<td class="mt-checkbox-list borderR"><label class="mt-checkbox mt-checkbox-outline"><input type="checkbox" value="1" name="test"><span></span></label></td>
								                            				<td style="padding-left: 12px;">#3-NBQ1</td>
								                            			</tr>
								                            			<tr>
								                            				<td class="mt-checkbox-list borderR"><label class="mt-checkbox mt-checkbox-outline"><input type="checkbox" value="1" name="test"><span></span></label></td>
								                            				<td style="padding-left: 12px;">#4-NBQ1</td>
								                            			</tr>
								                            			<tr>
								                            				<td class="mt-checkbox-list borderR"><label class="mt-checkbox mt-checkbox-outline"><input type="checkbox" value="1" name="test"><span></span></label></td>
								                            				<td style="padding-left: 12px;">#5-NBQ1</td>
								                            			</tr>
								                            			<tr>
								                            				<td class="mt-checkbox-list borderR"><label class="mt-checkbox mt-checkbox-outline"><input type="checkbox" value="1" name="test"><span></span></label></td>
								                            				<td style="padding-left: 12px;">#3-NBQ1</td>
								                            			</tr>
								                            			<tr>
								                            				<td class="mt-checkbox-list borderR"><label class="mt-checkbox mt-checkbox-outline"><input type="checkbox" value="1" name="test"><span></span></label></td>
								                            				<td style="padding-left: 12px;">#4-NBQ1</td>
								                            			</tr>
								                            			<tr>
								                            				<td class="mt-checkbox-list borderR"><label class="mt-checkbox mt-checkbox-outline"><input type="checkbox" value="1" name="test"><span></span></label></td>
								                            				<td style="padding-left: 12px;">#5-NBQ1</td>
								                            			</tr> -->
								                            		</tbody>
							                            		</table>
							                            	</div>
					                            		</div>
					                            		<div class="pull-left">						                            		
						                            		<div class="pointListTable" style="width:540px;margin-top: -20px;">
							                                    <table class="table table-striped table-bordered table-hover dt-responsive"  width="100%" max-height="270px"  id="sample_1" >
							                                    	
							                                    </table>
							                                </div>
					                            		</div>
					                            	</form>
					                            	
					                            </div>
					                            <div class="modal-footer btngroup">
					                                <button type="button" class="btn btn-circle default cancel" onclick="closeTab();" data-dismiss="modal">${cancel}</button>
					                                <button type="button" onclick="addPointTab();" class="btn btn-circle red-sunglo submitBtn">${sure}</button>
					                            </div>
					                        </div>
					                    </div>
					                </div>                                  
	                                         <!--添加测点弹窗结束 -->		 
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
<script src="${basePath}/assets/pages/scripts/data/data-edit.js?version=20170817024514" type='text/javascript'></script>
        <!-- END PAGE LEVEL SCRIPTS -->
        
    </body>

</html>
