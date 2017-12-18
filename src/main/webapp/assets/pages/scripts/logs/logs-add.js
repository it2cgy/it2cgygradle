var equipmentId = $('.equipmentId').val();
var equipmentType = $('.equipmentType').val();
//var equipments = [];
function editEqument(powerstationid,equipmentId){
	if(powerstationid==""||powerstationid==null){
		if(!equipment){ 
			message.alert( window.ynz.local.tip, window.ynz.local.role_rolepower_error,3,function(){ 
			}); 
		return;
		}
	}
	if(equipmentId==""||equipmentId==null){
		if(!equipment){ 
			message.alert( window.ynz.local.tip, window.ynz.local.curve_pleaseselectequipmenttype,3,function(){ 
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
						$('#equipment').append("<option value="+data[int].equipmentId+">"+data[int].equipmentcontainerName+"</option>");
					}
				},
				function(e){ 
					console.log("--------error------"+e);
				}
			);
}
function onSubmit(){
//	var equipmentId = $("#equipment").val();
	var equipment = $('#equipment').find("option:selected");
	var equipmentId = equipment[0].value;
	var equipmentName = equipment.text();
	var category = $('#categoryId').find("option:selected");
	var categoryId = category[0].value;
	var categoryName = category.text();
//	for(var i=0;i<equipments.length;i++){
//		var id = equipments[i].equipmentTableId + "#" + equipments[i].equipmentId;
//		if(equipmentId==id){
//			equipment = equipments[i];
//			break;
//		}
//	}
	
	if(!equipment){ 
			message.alert( window.ynz.local.tip, window.ynz.local.log_select_equipment,3,function(){ 
			}); 
		return;
	}
	ynzAjax.post(  
		window.ynz.path+"/logsInfo/addLogs.do",  
		{
			'topic':$("#topic").val(),
			'categoryId':categoryId,
			'categoryName':categoryName,
			'powerStationId':$("[name=powerStationId]").val(),
//			'equipmentType':equipment.equipmentTableId,
//			'equipmentName':equipment.equipmentcontainerName,
//			'equipmentId':equipment.equipmentId,
			'equipmentType':$('#equipmentId').val(),
			'equipmentName':equipmentName,
			'equipmentId':equipmentId,
			'description':$("#description").val(),
			'kaleidoscope':$("#kaleidoscope").val(),
		},
		function(response){  
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
                	topic: {
                        required: true
                    }, 
                    powerStationId: {
                        required: true
                    },
                    equipment: {
                        required: true
                    },
                    equipmentId: {
                    	required: true
                    },
                    description: {
                        required: true
                    },
                    categoryId: {
                    	required: true
                    },
                    kaleidoscope:{
                    	required: true
                    }
                },

                messages: { // custom messages for radio buttons and checkboxes
                	topic: {
                        required: window.ynz.local.log_please_topic
                    },
                    powerStationId: {
                        required: window.ynz.local.log_please_powerstation,
                    },
                    equipment: {
                    	required: window.ynz.local.log_select_equipment,
                    },
                    equipmentId: {
                    	required: window.ynz.local.log_equipmentIdrequired
                    },
                    description: {
                    	required: window.ynz.local.log_please_description,
                    },
                    categoryId: {
                    	required: window.ynz.local.log_please_category,
                    },
                    kaleidoscope:{
                    	required: window.ynz.local.log_kaleidoscoperequired
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
            $('[name=equipment]').change(function() {
            	var value = $('[name=equipment]').val();
             
            })
    }
    var handleValidation4 = function() {
    	// for more info visit the official plugin documentation: 
    	// http://docs.jquery.com/Plugins/Validation
    	
    	var form4 = $('#form_sample_4');
    	var error4 = $('.alert-danger', form4);
    	var success4 = $('.alert-success', form4);
    	
    	//IMPORTANT: update CKEDITOR textarea with actual content before submit
    	
    	form4.validate({
    		errorElement: 'span', //default input error message container
    		errorClass: 'help-block help-block-error', // default input error message class
    		focusInvalid: false, // do not focus the last invalid input
    		ignore: "", // validate all fields including form hidden input
    		rules: {
    			category: {
    				required: true
    			}, 
    		},
    		
    		messages: { // custom messages for radio buttons and checkboxes
    			category: {
    				required: window.ynz.local.log_categorynull
    			},
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
    			success4.hide();
    			error4.show();
    			App.scrollTo(error4, -200);
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
    			success4.show();
    			error4.hide();
    			onSubmit4();
    		}
    		
    		
    	});
    	//apply validation on select2 dropdown value change, this only needed for chosen dropdown integration.
    	$('.select2me', form4).change(function () {
    		form4.validate().element($(this)); //revalidate the chosen dropdown value and show error or success message for the input
    	});
    	
    	//initialize datepicker
    	$('.date-picker').datepicker({
    		rtl: App.isRTL(),
    		autoclose: true
    	});
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
            handleValidation4();
            setSelectMenu("logmanager","logmanager_add");
        }

    };

}();

jQuery(document).ready(function() {
	FormValidation.init();
	var powerStationId = $("#powerStationId").val()
//	if(powerStationId && powerStationId!=""){ 
//		$("#powerStationId").attr("disabled","disabled");
//	}

	if(window.ynz.isSubStation){ 
		$("#powerStationId").attr("disabled","disabled");
	}
	
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
					$("#categoryId").append("<option class='categoryoption' value="+response.data.id+">"+response.data.category+"</option>")
					$("#stack2Close").click();
			}); 
			},
			function(e){ 
				console.log("--------error------"+e);
			}
		);
}
