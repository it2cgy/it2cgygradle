<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="i18n" uri="http://www.yunengzhe.com/ynz/tag" %>

<%
	com.yunengzhe.PVMTS.domain.vo.ThirdVO thirdVO =null;
    String thirdVOSTR ="";
	try{
		thirdVO = (com.yunengzhe.PVMTS.domain.vo.ThirdVO)request.getAttribute("thirdVO");
		
	}catch(Exception e){
		
	}
	pageContext.setAttribute("thirdVO", net.sf.json.JSONObject.fromObject(thirdVO));
	pageContext.setAttribute("thirdPowerStationId", "6");
%> 
<script type="text/javascript">
	var thirdVO = ${thirdVO};
	if(thirdVO!=null){
		var thirdPoints = thirdVO.plist;
		var thirdEquipments = thirdVO.eqlist;
		window.ynz.longieb.thirdEquipments=thirdEquipments;
		window.ynz.longieb.thirdPoints=thirdPoints;
	}
	window.ynz.longieb.thirdPowerStationId=${thirdPowerStationId};
	</script>
