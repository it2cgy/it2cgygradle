var id = $('#powerStationId').val(); 

var equipments = [];
function editEqument(powerstationid,equipmentId){
	if(powerstationid==""||powerstationid==null){
		if(!equipment){ 
			message.alert( window.ynz.local.tip, "请选择电站",3,function(){ 
			}); 
		return;
		}
	}
	if(equipmentId==""||equipmentId==null){
		if(!equipment){ 
			message.alert( window.ynz.local.tip, "请选择设备类型",3,function(){ 
			}); 
			return;
		}
	}
	ynzAjax.get(
				window.ynz.path+"/pointInfo/getEquipments.do?powerStationId="+powerstationid+"&equipmentType="+equipmentId,
				function(response){ 
					var data = response.data;
					$('#equipment').empty();
					for (var int = 0; int < data.length; int++) {
//							equipments.push(data[int]);
//							var id = data[int].equipmentTableId + "#" + data[int].equipmentId;
						if(equipmentlogId == data[int].equipmentId){
							$('#equipment').append("<option selected='selected' value="+data[int].equipmentId+">"+data[int].equipmentcontainerName+"</option>");
						}else{
							$('#equipment').append("<option value="+data[int].equipmentId+">"+data[int].equipmentcontainerName+"</option>");
						}
					}
				},
				function(e){ 
					console.log("--------error------"+e);
				}
			);
}
function downloadlog(id){
	var form = $("#downloadForm");
	var input = $('#downloadForm_id');
	input.val(id);
	var input2 = $('#downloadForm_powername');
	input2.val($("#powername").val());
	$("#downloadForm").submit();
}

jQuery(document).ready(function() {
    setSelectMenuRoot("logmanager");
});
