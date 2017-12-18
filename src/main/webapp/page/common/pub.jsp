<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="i18n" uri="http://www.yunengzhe.com/ynz/tag" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	pageContext.setAttribute("path", path);
	pageContext.setAttribute("basePath", basePath);
	pageContext.setAttribute("cdnPath", com.yunengzhe.commons.util.PropertiesUtil.getString("cdn_url"));
	pageContext.setAttribute("uploadurl", com.yunengzhe.commons.util.PropertiesUtil.getString("uploadurl"));
	pageContext.setAttribute("downloadAppurl", com.yunengzhe.commons.util.PropertiesUtil.getString("downloadAppurl"));
	
	com.yunengzhe.PVMTS.domain.vo.UserVO userVO =null;
	
	String local="";
	try{
		userVO = (com.yunengzhe.PVMTS.domain.vo.UserVO)com.yunengzhe.commons.authentication.HttpSessionUtil.getAttribute("user");
		local = (String)com.yunengzhe.commons.authentication.HttpSessionUtil.getAttribute("local");
	}catch(Exception e){
		
	}

	int longiebRoleId = 0; 
	int powerStationId = 0;
	String nickName = "无名氏";
	String headshot = basePath+"assets/pages/img/ynz/common/vip.png";
	if(userVO!=null){
		nickName = userVO.getNickname();
		if(org.apache.commons.lang3.StringUtils.isNotBlank(userVO.getHeadshot())){
			headshot = userVO.getHeadshot();
		}
		List<com.yunengzhe.PVMTS.domain.po.UserAndRolePO> roleList = userVO.getRoleList();
		if(roleList!=null && roleList.size()>0){
			longiebRoleId = roleList.get(0).getRoleId();
		}
		
// 		List<Integer> powerStationList = userVO.getPowerStationList();
// 		if(powerStationList!=null && powerStationList.size()>0){
// 			powerStationId = powerStationList.get(0);
// 		}
	}
	pageContext.setAttribute("nickName", nickName);
	pageContext.setAttribute("headshot", headshot);
	Object redirectStationId =request.getAttribute("leyepowerStationId");
	Object redirectStationBaseInfo =request.getAttribute("leyepowerStationBaseInfo");
  	
	String token = "";
	if(userVO!=null){
		token =userVO.getToken();
	}
	if(redirectStationId!=null && redirectStationId!=""){
		powerStationId = Integer.valueOf(""+redirectStationId);
	}
	//第三方没电站，写死一个6
// 	if(powerStationId<=0){
//  	powerStationId=6;
// 	}
	pageContext.setAttribute("token", token);
	pageContext.setAttribute("longiebRoleId", longiebRoleId);
	pageContext.setAttribute("powerStationId", powerStationId); 
	pageContext.setAttribute("redirectStationId", redirectStationId); 
	pageContext.setAttribute("redirectStationBaseInfo", com.yunengzhe.PVMTS.util.JsonUtil.beanToJson(redirectStationBaseInfo)); 
	String subIndex = basePath+"index";
	pageContext.setAttribute("subIndex", subIndex);
	
	//用户角色判断，用于处理菜单
	Boolean isCompanyAdmin = false;//是否是公司管理员
	Boolean isJikongRole = false;//集控中心角色 
	Boolean isThird = false;//第三方 
	Boolean isSubStation = false;//是否子站 
	
	if(longiebRoleId==2){//公司管理员
		isCompanyAdmin = true;
	}else if(longiebRoleId==3){//集控中心角色
		isJikongRole = true;
	}else if(longiebRoleId==4){//第三方 
		isThird = true;
	}else{//子站
// 		isSubStation = true;
	}
	pageContext.setAttribute("isJikongRole", isJikongRole); 
	pageContext.setAttribute("isCompanyAdmin", isCompanyAdmin); 
	pageContext.setAttribute("isThird", isThird); 
	pageContext.setAttribute("isSubStation", isSubStation); 
	
	//用户角色判断，用于处理菜单
	Boolean isShowSubPage = false;//是否是进入子站首页 ，集控中心和子站角色都能进入
	if(powerStationId>0){
		isShowSubPage = true;
	}
	System.out.println("-----------电站id----------------"+powerStationId);
	pageContext.setAttribute("isCompanyAdmin", isCompanyAdmin); 
	pageContext.setAttribute("isShowSubPage", isShowSubPage); 

    pageContext.setAttribute("local", local);
    boolean admin = false;
    
    Object adminO = request.getAttribute("admin");
    Boolean b = new Boolean(true);
    if(adminO!=null && b.equals(adminO)){
    		 admin=true;
    }
    String params = "";
   if(admin==true){
	   if(org.apache.commons.lang3.StringUtils.isBlank(params)){
		   params="?";
	   }
	   params+="admin=1";
   }
   if(powerStationId>0){
	   if(params.indexOf("?")<0){
		   params="?powerStationId="+powerStationId;
	   }else{
		   params+="&powerStationId="+powerStationId;
	   }
   }
   pageContext.setAttribute("urlParams", params);
   
   HashMap<String,com.yunengzhe.PVMTS.domain.vo.RolesmenuHandleVO> menuList =(HashMap<String,com.yunengzhe.PVMTS.domain.vo.RolesmenuHandleVO>)request.getAttribute("menuConfig");
   if(menuList!=null){
	   for(String key:menuList.keySet()){
		   System.out.println("--------key==="+key);
		   pageContext.setAttribute("menu"+key,menuList.get(key).getHandle_premission());
	   }  
   }
%> 
<script type="text/javascript">
	var path = "${path}";
	var token = "${token}";
	var basePath ="${basePath}";
	var cdnPath = "${cdnPath}";
	var uploadurl = "${uploadurl}";
	var downloadAppurl = "${downloadAppurl}";
	var subIndex = "${subIndex}";
	
	var isCompanyAdmin = ${isCompanyAdmin};
	var isJikongRole = ${isJikongRole};
	var isThird = ${isThird};
	var isSubStation =${isSubStation};
	
	var redirectStationId = "${redirectStationId}";
	var redirectStationBaseInfo = ${redirectStationBaseInfo};
	if(!window.ynz){
		window.ynz={};
	}
	window.ynz.path=path;
	window.ynz.basePath=basePath;
	window.ynz.local = {}; 
	window.ynz.token = token;
	if(!window.ynz.longieb){
		window.ynz.longieb={};
	}
	window.ynz.longieb.subIndex=subIndex;
	window.ynz.longieb.powerStationId = ${powerStationId};
	window.ynz.longieb.roleId = '${longiebRoleId}';
	window.ynz.cdnPath=cdnPath;
	window.ynz.uploadurl=uploadurl;
	window.ynz.downloadAppurl=downloadAppurl;
	window.ynz.isThird = isThird;
	window.ynz.isSubStation = isSubStation;
	window.ynz.localType = "${local}"
	window.ynz.admin = ${admin!=null && admin}
	//国际化数据
	<% 
	Map<String, String> localMap = com.yunengzhe.PVMTS.i18n.ResourceDataManager.getLocalMap(local);//参数
	for(Map.Entry<String, String> entry:localMap.entrySet()){
		pageContext.setAttribute(entry.getKey(), entry.getValue());
%> 
	window.ynz.local.<%=entry.getKey() %>="<%=entry.getValue() %>";
<%  
	}
%> 
	</script>
