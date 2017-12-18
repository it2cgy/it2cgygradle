$(function(){
	$("#checkpsd3").hide();
	$("#psd2").blur(function(){
		$("#checkpsd3").hide();
		var form_1 = $('#updatepsd');
    	var newPassword  = $("#psd").val();
    	var newPassword2 = $("#psd2").val();
    	if(newPassword!=newPassword2){
    		$('#checkpsd').show();
    	}else{
    		$('#checkpsd').hide();
    	}
    });
	$("#psd").blur(function(){
		$("#checkpsd3").hide();
		var form_1 = $('#updatepsd');
    	var newPassword  = $("#psd").val();
    	var newPassword2 = $("#psd2").val();
    	if(newPassword2==null ||newPassword2==""){
    		return;	
    	}
    	if(newPassword!=newPassword2){
    		$('#checkpsd').show();;
    	}else{
    		$('#checkpsd').hide();
    	}
    });
	$("#pswOld").blur(function(){
		$("#checkpsd3").hide();
    });
	
  $("#submitUser").click(function(){
	  $('#checkpsd2').hide();
	    var pswOld = $.trim($("#pswOld").val());
		var psd = $.trim($("#psd").val());
		var psd1 = $.trim($("#psd2").val());
		if(pswOld == null || pswOld == ""){
			$('#checkpsd2').show();
			return;
		}
		
		if( psd != psd1 ){
			$('#checkpsd').show();
			return;
		}
		
		
		var paramObj ={
				oldPassword:pswOld,
				newPassword:psd
		}
		ynzAjax.post(
				window.ynz.path+"/user/resetpsw.do",
				paramObj,
				function(response){ 
					message.alert( window.ynz.local.tip,window.ynz.local.action_success,3,null,function(){
						if(response.data==true){
							window.location.href=window.ynz.path+"/user/login";
						}
					});
		        },
		        function(e){ 
		        	$("#info").html(e.responseJSON.message);
		        	$("#checkpsd3").show();
		            console.log("--------error------"+JSON.stringify(e.responseJSON.message));
		        }
		    );
		
		
		
  });
  $("#cancel").click(function(){
	  window.location.href=basePath+"compantUser/userListPage";
  });
});
function saveData(){
	var telephone = $("#telephone").val();
	var username = $("#username").val();
	var reg = /^(((13[0-9]{1})||(15[0-9]{1})||(18[0-9]{1})||(17[0-9]{1}))+\d{8})$/;
	if(!reg.test(telephone)){
		message.alert( window.ynz.local.tip,window.ynz.local.user_user_phoneerror,4);
    	 return;
	}
	if(username==""){
		 $.messager.alert(window.ynz.local.tip,  window.ynz.local.user_user_unameerror);
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