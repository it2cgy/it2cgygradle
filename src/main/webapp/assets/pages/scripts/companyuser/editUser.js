$(function(){
	setSelectMenuRoot("companyusrmanager");
  $("#submitUser").click(function(){
	    var headshot = $("#userImg").attr("src");
	    var telephone = $("#telephone").val();
		var username = $("#username").val();
		var realname = $("#realname").val();
		var inputCompany =$("#inputCompany").val();
//		var reg = /^(((13[0-9]{1})||(15[0-9]{1})||(18[0-9]{1})||(17[0-9]{1}))+\d{8})$/;
//		if(telephone!="" && !reg.test(telephone)){
//			message.alert( window.ynz.local.tip,window.ynz.local.user_user_phoneerror,4);
//	    	return;
//		}
		if(username==""){
			message.alert( window.ynz.local.tip,window.ynz.local.user_user_unameerror,4);
	    	return;
		}
		
		if(realname==""){
			message.alert( window.ynz.local.tip,window.ynz.local.user_user_realerror,4);
	    	return;
		}
		var paramObj ={
				userId:$("#userId").val(),
				username:username,
				telephone:telephone,
				companyId:$("#companyId").val(),
				roleId:""+$("#roleId").val(),
				email:$("#email").val(),
				realname:realname,
				headshot:headshot,
				inputCompany:inputCompany
		}
	  $.ajax({
			url : basePath+"user/editUser.do",
			type : "POST",
			contentType : 'application/json',
			dataType:"json",
			data:JSON.stringify(paramObj),
			success : function(param) {
				if(param.code==0){
					message.alert( window.ynz.local.tip,window.ynz.local.user_adderror,3,null,function(){
						 
					});
					
					 return;
				}else{
					message.alert( window.ynz.local.tip,window.ynz.local.action_success,3,null,function(){
						 var param ="?a=1";
						 	if(window.ynz.admin){
						 		param  +="&admin=1";
						 	}
						 	
						 	if(window.ynz.longieb.powerStationId>0){
						 		param += "&powerStationId="+window.ynz.longieb.powerStationId;
						 	}
						  window.location.href=basePath+"compantUser/userListPage"+param;
					});
				}
			},
			error:function(){
				 return;
		    }
		});
		
		
		
  });
  $("#cancel").click(function(){
	  var param ="?a=1";
	 	if(window.ynz.admin){
	 		param  +="&admin=1";
	 	}
	 	
	 	if(window.ynz.longieb.powerStationId>0){
	 		param += "&powerStationId="+window.ynz.longieb.powerStationId;
	 	}
	  window.location.href=basePath+"compantUser/userListPage"+param;
  });
});
function saveData(){
	var telephone = $("#telephone").val();
	var username = $("#username").val();
	var reg = /^(((13[0-9]{1})||(15[0-9]{1})||(18[0-9]{1})||(17[0-9]{1}))+\d{8})$/;
	if(!reg.test(telephone)){
    	 $.messager.alert(window.ynz.local.tip,window.ynz.local.user_user_phoneerror);
    	 return;
	}
	if(username==""){
		 $.messager.alert(window.ynz.local.tip, window.ynz.local.user_user_unameerror);
    	 return;
	}
	var paramObj ={
			telephone:telephone,
			username:username,
			companyId:$("#companyId").combobox("getValue"),
			roleId:""+$("#roleId").combobox("getValues")
	}
	$.ajax({
		url : basePath+"/user/addUser.do",
		type : "POST",
		contentType : 'application/json',
		dataType:"json",
		data:JSON.stringify(paramObj),
		success : function(param) {
			if(param.code==0){
				 return;
			}else{parent.closeTab();}
		},
		error:function(){
			 return;
	    }
	});
}
function fileUpload(){
	$("#file").click();
}
function uploadFile(){
	var form = $("#fileform"); 
	var file =$("#file").val();
	var arr = file.split("\\");
	var fileName = arr[arr.length-1]; 
	var AllImgExt = ".jpg|.jpeg|.gif|.bmp|.png|";
	var extName = file.substring(file.lastIndexOf(".")).toLowerCase();
	if(AllImgExt.indexOf(extName+"|")==-1){
		message.alert( window.ynz.local.tip,window.ynz.local.image_error,3,null,null);
		return false;
	}
	form.ajaxSubmit(function(respense){
		message.alert(window.ynz.local.tip,respense.message,3);
		$("#userImg").attr("src",respense.url);
		$("#uploadDiv").hide();
		$("#fileDiv").show();
	});
}
/**
 * 删除图片
 * @param _obj
 */
function delFile(_obj){
	$("#userImg").attr("src","");
	$("#fileDiv").hide();
	$("#uploadDiv").show();
	var file = $("#file");
	file.after(file.clone().val(""));
	file.remove();
}