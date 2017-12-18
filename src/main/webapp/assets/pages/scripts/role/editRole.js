$(function(){
	setSelectMenuRoot("rolemanager");
	$('#select1').multiSelect({checkAllText:""});
	ComponentsDropdowns.init(); 
	$("#cancel").click(function(){
		var param ="?a=1";
	 	if(window.ynz.admin){
	 		param  +="&admin=1";
	 	}
	 	
	 	if(window.ynz.longieb.powerStationId>0){
	 		param += "&powerStationId="+window.ynz.longieb.powerStationId;
	 	}
		 window.location.href=basePath+"rolePage/roleListPage"+param;
	});
	$("#submitUser").click(function(){
			var powerlist = $('#my_multi_select1').val();
		    var rolename = $("#rolename").val();
		    var roleId = $("#roleId").val();
			if(rolename==""){
				message.alert( window.ynz.local.tip,window.ynz.local.role_rolename_error,4);
		    	return;
			}
			if(powerlist==""){
				message.alert( window.ynz.local.tip,window.ynz.local.role_rolepower_error,4);
		    	return;
			}
			var menus = new Array();
			$(".menuRole").each(function(){
				var _id =$(this).find("label").attr("id");
				if(_id!="pushId" && _id!=27){
				var obj ={"id":_id,"premis":$("#premis_"+_id).bootstrapSwitch('state'),"handle":$("#handle_"+_id).bootstrapSwitch('state')}
					menus.push(obj);
				}else if(_id==27){
					var obj ={"id":_id,"premis":$("#premis_"+_id).bootstrapSwitch('state'),"handle":$("#premis_"+_id).bootstrapSwitch('state')}
					menus.push(obj);
				}
			});
			var pushConfig = $("#pushConfirm").bootstrapSwitch('state');
			var pushId = $("#pushId").val();
			var paramObj ={
					roleId:roleId,
					pushConfig:pushConfig,
					pushId:pushId,
					roleName:rolename,
					powerList:""+powerlist,
					menuConfig:menus
			}
		
			ynzAjax.post( basePath+"role/addRole.do", paramObj,  function(param) {
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
							 window.location.href=basePath+"rolePage/roleListPage"+param; 
						});
					}
				}, function(){

			    } );
	});
});



var ComponentsDropdowns = function () {


    var handleMultiSelect = function () {
        $('#my_multi_select1').multiSelect();
    }

    return {
        //main function to initiate the module
        init: function () {            
            handleMultiSelect();
        }
    };

}();

