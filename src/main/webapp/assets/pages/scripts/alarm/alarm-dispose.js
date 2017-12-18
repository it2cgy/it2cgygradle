jQuery(document).ready(function() {
//	var eventTime=alarmInfo.eventTime;
//	if(eventTime!=null&&eventTime!=''){
//		console.log(eventTime);
//  		$("#eventTime").val(timeUtil.dateToString(eventTime,"yyyy-MM-dd HH:mi:ss"));
//  	}
    FormValidation.init();
});

function downloadAlarm(id){
	var form = $("<form>");
	form.attr('style','display:none');
	form.attr('target','');
	form.attr('method','post');
	form.attr('action',basePath+"alarmInfo/downloadAlarm.do");
	var input = $('<input>');
	input.attr('type','hidden');
	input.attr('name','id');
	input.attr('value',id);
	var input2 = $('<input>');
	$('body').append(form);
	form.append(input);
	form.append(input2);
	form.submit();
}
function onSubmit(){
	ynzAjax.post(
		window.ynz.path+"/alarmInfo/confirmAlarmById.do",  
		{
			'id':$("#id").val(),
			'remaker':$("#remaker").val(),
			'handling':$("#handling").val(),
			'corporation':$("#corporation").val(),
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
					window.location.href=basePath+"alarmPage/actualAlarm.do"+param;
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
                	remaker: {
                        required: true
                    }, 
                    handling: {
                        required: true
                    },
                    corporation: {
                        required: true
                    }
                },

                messages: { // custom messages for radio buttons and checkboxes
                	remaker: {
                        required:  window.ynz.local.alarm_remakerrequired
                    }, 
                    handling: {
                        required: window.ynz.local.alarm_handlingrequired
                    },
                    corporation: {
                        required:  window.ynz.local.alarm_corporationrequired
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
        }

    };

}();