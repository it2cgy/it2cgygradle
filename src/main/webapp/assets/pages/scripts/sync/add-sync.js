
function onSubmit(){
	var name = $.trim($("#syncname").val())
	var startTime = $("#startTime").val()+" 00:00:00";
	var endTime = $("#endTime").val()+" 23:59:59"; 
	ynzAjax.post(  
			basePath+"/stationmonitor/history/sync",  
		{
			'name':name,'startTime':startTime,'endTime':endTime
 
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
					window.location.href=basePath+"sync/list.do"+param;
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
                	syncname: {
                        required: true
                    }, 
                    startTime: {
                        required: true
                    },
                    endTime: {
                        required: true
                    } 
                },

                messages: { // custom messages for radio buttons and checkboxes
                	syncname: {
                        required: window.ynz.local.sync_tip_name
                    },
                    startTime: {
                        required: window.ynz.local.sync_tip_start,
                    },
                    endTime: {
                    	required: window.ynz.local.sync_tip_end,
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

jQuery(document).ready(function() {
	FormValidation.init();
	var powerStationId = $("#powerStationId").val()
//	if(powerStationId && powerStationId!=""){ 
//		$("#powerStationId").attr("disabled","disabled");
//	}

	if(window.ynz.isSubStation){ 
		$("#powerStationId").attr("disabled","disabled");
	}
	
	 setSelectMenuRoot("menuSync");
 
});
  