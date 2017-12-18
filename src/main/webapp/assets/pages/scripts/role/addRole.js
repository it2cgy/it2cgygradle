$(function(){
	
	
    $('.make-switch').on('switch-change', function () {
//        $('.switch-radio1').bootstrapSwitch('toggleRadioStateAllowUncheck');
        alert(2);
    });
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
			var paramObj ={
					roleName:rolename,
					powerList:""+powerlist,
					menuConfig:menus,
					pushConfig:pushConfig
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

function setConfig(menusId){
	switch (menusId) {
	case 13:
		if($("#premis_"+menusId).bootstrapSwitch('state')){
			$("#premis_8").bootstrapSwitch('state',true);	
		}
		break;
	case 14:
		if($("#premis_"+menusId).bootstrapSwitch('state')){
			$("#premis_8").bootstrapSwitch('state',true);	
		}
		break;
	case 15:
		if($("#premis_"+menusId).bootstrapSwitch('state')){
			$("#premis_8").bootstrapSwitch('state',true);	
		}
		break;
	case 16:
		if($("#premis_"+menusId).bootstrapSwitch('state')){
			$("#premis_8").bootstrapSwitch('state',true);	
		}
		break;
	case 17:
		if($("#premis_"+menusId).bootstrapSwitch('state')){
			$("#premis_8").bootstrapSwitch('state',true);	
		}
		break;
	case 8:
		if(!$("#premis_"+menusId).bootstrapSwitch('state')){
			$("#premis_13").bootstrapSwitch('state',false);
			$("#premis_14").bootstrapSwitch('state',false);	
			$("#premis_15").bootstrapSwitch('state',false);	
			$("#premis_16").bootstrapSwitch('state',false);	
			$("#premis_17").bootstrapSwitch('state',false);	
		}
	case 18:
		if($("#premis_"+menusId).bootstrapSwitch('state')){
			$("#premis_9").bootstrapSwitch('state',true);	
		}
		break;
	case 19:
		if($("#premis_"+menusId).bootstrapSwitch('state')){
			$("#premis_9").bootstrapSwitch('state',true);	
		}
		break;
	case 9:
		if(!$("#premis_"+menusId).bootstrapSwitch('state')){
			$("#premis_18").bootstrapSwitch('state',false);
			$("#premis_19").bootstrapSwitch('state',false);
		}
		break;
	default:
		break;
	}
	
}

var ComponentsDropdowns = function () {


    var handleMultiSelect = function () {
        $('#my_multi_select1').multiSelect();
    }
    var handleBootstrapSwitch = function() {

        $('.switch-radio1').on('switch-change', function () {
            $('.switch-radio1').bootstrapSwitch('toggleRadioState');
            alert(1);
        });

        // or
        $('.switch-radio1').on('switch-change', function () {
            $('.switch-radio1').bootstrapSwitch('toggleRadioStateAllowUncheck');
            alert(2);
        });

        // or
        $('.switch-radio1').on('switch-change', function () {
            $('.switch-radio1').bootstrapSwitch('toggleRadioStateAllowUncheck', false);
            alert(3);
        });

    }

    return {
        //main function to initiate the module
        init: function () {            
            handleMultiSelect();
            handleBootstrapSwitch();
        }
    };

}();

