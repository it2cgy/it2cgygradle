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
	var form = $("<form>");
	form.attr('style','display:none');
	form.attr('target','');
	form.attr('method','post');
	form.attr('action',basePath+"logsInfo/downloadlog.do");
	var input = $('<input>');
	input.attr('type','hidden');
	input.attr('name','id');
	input.attr('value',id);
	var input2 = $('<input>');
	input2.attr('type','hidden');
	input2.attr('name','powername');
	input2.attr('value',$("#powername").val());
	$('body').append(form);
	form.append(input);
	form.append(input2);
	form.submit();
}
function onSubmit(){
	ynzAjax.post(
		window.ynz.path+"/logsInfo/updateLogs.do",  
		{
			'id':$("#id").val(),
			'remark':$("#remark").val(),
			'handling':$("#handling").val(),
			'corporation':$("#corporation").val(),
			'responsible':$("#responsible").val(),
			'logStatus':1
		},
		function(response){ 
			if(response.message=="success"){
				message.alert( window.ynz.local.tip,window.ynz.local.action_success,3,null,function(){
					var param ="?a=1";
				 	if(window.ynz.admin){
				 		param  +="&admin=1";
				 	}
				 	
				 	if(window.ynz.longieb.powerStationId>0){
				 		param += "&powerStationId="+window.ynz.longieb.powerStationId;
				 	}
					window.location.href=basePath+"logsPage/toLogsList.do"+param;
				}); 
			}
		},
		function(e){ 
			console.log("--------error------"+e);
		}
	);
}
var FormValidation = function () {
    // advance validation
    var handleValidation3 = function() {
        // for more info visit the official plugin documentation: 
        // http://docs.jquery.com/Plugins/Validation

            var form3 = $('#form_sample_3');
            var error3 = $('.alert-danger', form3);
            var success3 = $('.alert-success', form3);

            //IMPORTANT: update CKEDITOR textarea with actual content before submit

            form3.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block help-block-error', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "", // validate all fields including form hidden input
                rules: {
                	remark: {
                        required: true
                    }, 
                    handling: {
                        required: true
                    },
                    corporation: {
                        required: true
                    },
                    responsible: {
                        required: true
                    }
                },

                messages: { // custom messages for radio buttons and checkboxes
                	remark: {
                        required: "备注不能为空"
                    }, 
                    handling: {
                        required: "处理办法不能为空"
                    },
                    corporation: {
                        required: "责任单位不能为空"
                    },
                    responsible: {
                        required: "责任人不能为空"
                    }
                },

                errorPlacement: function (error, element) { // render error placement for each input type
                    if (element.parent(".input-group").size() > 0) {
                        error.insertAfter(element.parent(".input-group"));
                    } else if (element.attr("data-error-container")) { 
                        error.appendTo(element.attr("data-error-container"));
                    } else if (element.parents('.radio-list').size() > 0) { 
                        error.appendTo(element.parents('.radio-list').attr("data-error-container"));
                    } else if (element.parents('.radio-inline').size() > 0) { 
                        error.appendTo(element.parents('.radio-inline').attr("data-error-container"));
                    } else if (element.parents('.checkbox-list').size() > 0) {
                        error.appendTo(element.parents('.checkbox-list').attr("data-error-container"));
                    } else if (element.parents('.checkbox-inline').size() > 0) { 
                        error.appendTo(element.parents('.checkbox-inline').attr("data-error-container"));
                    } else {
                        error.insertAfter(element); // for other inputs, just perform default behavior
                    }
                },

                invalidHandler: function (event, validator) { //display error alert on form submit   
                    success3.hide();
                    error3.show();
                    App.scrollTo(error3, -200);
                },

                highlight: function (element) { // hightlight error inputs
                   $(element)
                        .closest('.form-group').addClass('has-error'); // set error class to the control group
                },

                unhighlight: function (element) { // revert the change done by hightlight
                    $(element)
                        .closest('.form-group').removeClass('has-error'); // set error class to the control group
                },

                success: function (label) {
                    label
                        .closest('.form-group').removeClass('has-error'); // set success class to the control group
                },
                submitHandler: function (form) {
                    success3.show();
                    error3.hide();
                    onSubmit();
                }


            });
             //apply validation on select2 dropdown value change, this only needed for chosen dropdown integration.
            $('.select2me', form3).change(function () {
                form3.validate().element($(this)); //revalidate the chosen dropdown value and show error or success message for the input
            });

            //initialize datepicker
            $('.date-picker').datepicker({
                rtl: App.isRTL(),
                autoclose: true
            });
            $('#equipmentId').change(function() {
            	editEqument($('#powerStationId').val(),$(this).val());
            })
//            $('[name=equipment]').change(function() {
//            	var value = $('[name=equipment]').val();
//            	var obj = value.split(",");
//            	$(".equipmentId").val(obj[0]);
//            	$(".equipmentType").val(obj[1]);
//            	$(".equipmentName").val(obj[2]);
//            })
    }
    var handleWysihtml5 = function() {
        if (!jQuery().wysihtml5) {
            
            return;
        }

        if ($('.wysihtml5').size() > 0) {
            $('.wysihtml5').wysihtml5({
                "stylesheets": ["../assets/global/plugins/bootstrap-wysihtml5/wysiwyg-color.css"]
            });
        }
    }

    return {
        //main function to initiate the module
        init: function () {
            handleWysihtml5();
            handleValidation3();
            editEqument($('#powerStationId').val(),$('#equipmentId').val());
        }

    };

}();

jQuery(document).ready(function() {
    FormValidation.init();
    setSelectMenuRoot("logmanager");
});
function delCategory(id){
	if(confirm(window.ynz.local.log_delcategory)){
		ynzAjax.get(
				window.ynz.path+"/logsCategory/deleteLogsCate/"+id+".do",  
				function(response){ 
					message.alert( window.ynz.local.tip,window.ynz.local.action_success,3,null,function(){
						$(".categorytr").each(function(){
							if($(this).children('td').html()==id){
								$(this).remove();
							}
						})
						$(".categoryoption").each(function(){
							if($(this).val()==id){
								$(this).remove();
							}
						})
					}); 
				},
				function(e){ 
					console.log("--------error------"+e);
				}
		);
	}
}
function onSubmit4(){
	ynzAjax.post(
			window.ynz.path+"/logsCategory/addLogsCate.do",  
			{
				'category':$("#category").val(),
			},
			function(response){ 
					message.alert( window.ynz.local.tip,window.ynz.local.action_success,3,null,function(){
						$("#sample_1").append("<tr class='categorytr'><td style='width: 30%'>"+response.data.id+"</td>"+
                     			"<td style='width: 30%'>"+response.data.category+"</td>"+
                     			"<td style='width: 30%'> <button type='button' class='btn btn-circle btn-success' onclick='delCategory("+response.data.id+")'>"+window.ynz.local.del+"</button></td></tr>")
					$("#categoryId").append("<option class='categoryoption' value="+response.data.id+">"+response.data.category+"</option>");
					$("#stack2").hide();
			}); 
			},
			function(e){ 
				console.log("--------error------"+e);
			}
		);
}